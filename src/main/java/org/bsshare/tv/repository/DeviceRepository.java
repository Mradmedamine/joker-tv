package org.bsshare.tv.repository;

import java.util.Optional;

import org.bsshare.tv.model.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

	Optional<DeviceEntity> findOneBySerialNumber(String serialNumber);
	
	Optional<DeviceEntity> findOneByMacAddress(String macAddress);
	
	
}
