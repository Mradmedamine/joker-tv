package org.bsshare.tv.service.impl;

import org.bsshare.tv.model.DeviceDataConsistancyException;
import org.bsshare.tv.model.HasSubscriptionAlreadyException;
import org.bsshare.tv.model.entity.BaseSubscription;
import org.bsshare.tv.model.entity.DeviceEntity;
import org.bsshare.tv.model.entity.IPTVSubscription;
import org.bsshare.tv.model.entity.SharingSubscription;
import org.bsshare.tv.model.front.web.ComponentStatus;
import org.bsshare.tv.model.front.web.DeviceDto;
import org.bsshare.tv.model.front.web.SubscriptionDto;
import org.bsshare.tv.model.front.web.SubscriptionType;
import org.bsshare.tv.repository.BaseSubscriptionRepository;
import org.bsshare.tv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public abstract class BaseSubscriptionServiceImpl<T extends BaseSubscription> extends ServiceBaseImpl {

    protected static final Period DEFAULT_EXPIRATION = Period.ofMonths(12);

    @Autowired
    private DeviceRepository deviceRepository;

    public Optional<T> getSubscription(SubscriptionDto subscriptionDto) {
        return Optional.ofNullable(getSubscriptionRepository().findOneByActiveCode(subscriptionDto.getLogin()));
    }

    protected boolean isExpired(BaseSubscription subscription) {
        boolean activated = subscription.getStatus() == ComponentStatus.ACTIVATED;
        boolean notExpired = subscription.getExpiration().isBefore(LocalDate.now());
        getLogger().debug("account is " + (activated ? "" : "not") + " activated");
        getLogger().debug("account is " + (notExpired ? " not " : "") + " expired");
        return activated && notExpired;
    }

    protected boolean isNotExpired(BaseSubscription subscription) {
        return !isExpired(subscription);
    }

    protected Boolean isValidSubscription(SubscriptionDto subscriptionDto) {
        Optional<? extends BaseSubscription> subscription = getSubscription(subscriptionDto);
        return subscription.isPresent() && !isExpired(subscription.get())
                && sameDevice(subscriptionDto, subscription.get());
    }

    private boolean sameDevice(SubscriptionDto subscriptionDto, BaseSubscription baseSubscription) {
        return baseSubscription.getDevice().getMacAddress().trim().equals(subscriptionDto.getUid().trim());
    }

    protected Boolean hasAnyValidOrNewSubscription(DeviceDto deviceDto) {
        List<? extends BaseSubscription> subscriptions = getSubscriptionRepository()
                .findByDevice_SerialNumber(deviceDto.getSerialNumber());
        return subscriptions.stream().anyMatch(this::isNotExpired);
    }

    protected void newSubscription(DeviceDto deviceDto, SubscriptionType type) throws HasSubscriptionAlreadyException {
        if (hasAnyValidOrNewSubscription(deviceDto)) {
            throw new HasSubscriptionAlreadyException();
        }
        DeviceEntity entityDevice = saveSubscriptionDeviceIfNotExistant(deviceDto);
        BaseSubscription subscription = type == SubscriptionType.IPTV ? new IPTVSubscription()
                : new SharingSubscription();
        subscription.setDevice(entityDevice);
        subscription.setStatus(ComponentStatus.NEW);
        getSubscriptionRepository().save(subscription);
    }

    protected void newSubscription(SubscriptionType type, int period) {
        BaseSubscription subscription = type == SubscriptionType.IPTV ? new IPTVSubscription()
                : new SharingSubscription();
        subscription.setStatus(ComponentStatus.NEW);
        subscription.setPeriodInMonths(period);
        getSubscriptionRepository().save(subscription);
    }

    protected DeviceEntity saveSubscriptionDeviceIfNotExistant(DeviceDto device) {
        Optional<DeviceEntity> entityDevice;
        if (device.getSerialNumber() != null) {
            entityDevice = deviceRepository.findOneBySerialNumber(device.getSerialNumber());
        } else if (device.getSerialNumber() != null) {
            entityDevice = deviceRepository.findOneByMacAddress(device.getMacAddress());
        } else {
            throw new RuntimeException("device doesnt have serialnumber nor max address");
        }

        return entityDevice.orElseGet(() -> saveNewDevice(device));
    }

    private DeviceEntity saveNewDevice(DeviceDto device) {
        try {
            DeviceEntity entityDevice = new DeviceEntity();
            entityDevice.setMacAddress(device.getMacAddress().trim());
            entityDevice.setSerialNumber(device.getSerialNumber().trim());
            entityDevice.setModel(device.getModel());
            return deviceRepository.save(entityDevice);
        } catch (Exception ex) {
            throw new DeviceDataConsistancyException();
        }
    }

    protected Long deleteSubscription(Long id) {
        try {
            Optional<Long> deviceId = Optional.ofNullable(getSubscriptionRepository().findOneById(id).getDevice())
                    .map(e -> e.getId());
            getSubscriptionRepository().delete(id);
            getLogger().debug("Deleted Sharing Subscription with id :" + id);
            deleteCorrespondingDevice(deviceId);
        } catch (DataIntegrityViolationException err) {
            return -1L;
        } catch (Exception err) {
            return -100L;
        }
        return id;
    }

    protected void deleteCorrespondingDevice(Optional<Long> deviceId) {
        try {
            deviceId.ifPresent((id) -> {
                deviceRepository.delete(id);
                getLogger().debug("Subscription Corresponding Device has been deleted deviceId :" + id);
            });
        } catch (DataIntegrityViolationException err) {
            // DO NOTHING
        }
    }

    protected abstract BaseSubscriptionRepository getSubscriptionRepository();

}
