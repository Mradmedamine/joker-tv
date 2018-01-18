package org.joker.tv.repository;

import org.joker.tv.model.entity.DeviceSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<DeviceSubscription, Long> {//

}
