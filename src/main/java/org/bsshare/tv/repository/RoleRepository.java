package org.bsshare.tv.repository;

import java.util.List;

import org.bsshare.tv.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {//

	List<Role> findByName(String name);

}
