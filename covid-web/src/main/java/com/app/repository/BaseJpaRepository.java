package com.app.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	@Query(value = "SELECT count(u.id) FROM #{#entityName} u WHERE u.deleted='true'", nativeQuery = true)
	long countDeletedEntries();

	Optional<T> findById(ID id);

}
