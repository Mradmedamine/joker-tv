package org.joker.tv.repository;



import org.joker.tv.model.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {//

//	@Query("SELECT e FROM DeviceSubscription e WHERE e.activeCode = :activeCode AND e.macAddress = :macAddress "
//	        + "AND e.serialNumber = :serialNumber")
//	List<DeviceSubscription> findByCriteria(@Param("activeCode") String activeCode, @Param("macAddress") String macAddress,
//	        @Param("serialNumber") String from);

}
