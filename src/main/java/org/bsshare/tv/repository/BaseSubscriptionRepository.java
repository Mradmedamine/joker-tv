package org.bsshare.tv.repository;

import java.util.List;

import org.bsshare.tv.model.entity.BaseSubscription;

public interface BaseSubscriptionRepository {

	<T extends BaseSubscription> T findOneById(Long id);
	
	<T extends BaseSubscription> T findOneByActiveCode(String activeCode);

	List<? extends BaseSubscription> findByDevice_SerialNumber(String serial);

	void save(BaseSubscription subscription);

	void delete(Long id);
	
}
