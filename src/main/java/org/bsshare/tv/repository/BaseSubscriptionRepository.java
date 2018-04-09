package org.bsshare.tv.repository;

import java.util.List;

import org.bsshare.tv.model.entity.BaseSubscription;

public interface BaseSubscriptionRepository {

	List<? extends BaseSubscription> findOneByCriteria(String activeCode);

	void save(BaseSubscription subscription);

	List<? extends BaseSubscription> findByDevice_SerialNumber(String serial);

}
