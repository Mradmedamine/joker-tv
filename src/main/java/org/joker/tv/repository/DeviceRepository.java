package org.joker.tv.repository;

import java.util.Optional;

import org.joker.tv.model.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

	Optional<DeviceEntity> findOneBySerialNumber(String serial);

}
