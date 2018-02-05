package org.joker.tv.repository;

import java.util.List;

import org.joker.tv.model.entity.IPTVSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPTVSubscriptionRepository extends JpaRepository<IPTVSubscription, Long> {//

	@Query("SELECT e FROM IPTVSubscription e WHERE e.activeCode = :activeCode AND e.macAddress = :macAddress "
	        + "AND e.serialNumber = :serialNumber")
	List<IPTVSubscription> findByCriteria(@Param("activeCode") String activeCode,
	        @Param("macAddress") String macAddress, @Param("serialNumber") String from);

}
