package org.bsshare.tv.repository;

import org.bsshare.tv.model.entity.IPTVSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPTVSubscriptionRepository extends BaseSubscriptionRepository<IPTVSubscription, Long> {

	@Query("SELECT e FROM IPTVSubscription e WHERE e.activeCode = :activeCode AND e.device.macAddress = :macAddress "
	 + "AND e.device.serialNumber = :serialNumber")
	IPTVSubscription findOneByCriteria(@Param("activeCode") String activeCode,
			@Param("macAddress") String macAddress,
			@Param("serialNumber") String serialNumber);

}
