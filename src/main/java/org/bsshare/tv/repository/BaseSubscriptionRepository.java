package org.bsshare.tv.repository;

import org.bsshare.tv.model.entity.BaseSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseSubscriptionRepository<T extends BaseSubscription, ID extends Serializable> extends JpaRepository<T, ID> {

    T findOneById(Long id);

    Optional<T> findOneByActiveCode(String activeCode);

    List<T> findByDevice_SerialNumber(String serial);

}
