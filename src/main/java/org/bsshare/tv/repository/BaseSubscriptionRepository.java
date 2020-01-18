package org.bsshare.tv.repository;

import java.util.List;
import java.util.Optional;

import org.bsshare.tv.model.entity.BaseSubscription;

public interface BaseSubscriptionRepository<T extends BaseSubscription> {

	T findById(Long id);

	Optional<T> findOneByActiveCode(String activeCode);

	List<T> findByDevice_SerialNumber(String serial);

	T save(T entity);
	
	void delete(Long id);
	
}
