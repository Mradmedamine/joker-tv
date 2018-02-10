package org.joker.tv.repository;

import java.util.Optional;

import org.joker.tv.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	Optional<Device> findOneBySerialNumber(String serial);

}
