package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID>  {

	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 2", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast2Records();

	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 5", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast5Records();
	
	// TODO: Practical bonus
	// Change the Query above to Spring JPA Query or JPQL
	// Simple SQL Should not use native query as change of db the query syntax need to be changed. 
	// If use Spring JPQL, the SQL below can be maintained even change of DB
	
	//List<CovidCasesAreaEntity> listLast5RecordsHQL(Pageable pageable);
}