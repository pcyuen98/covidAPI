package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID> {

	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 2", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast2Records();

	//@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 5", nativeQuery = true)
	//List<CovidCasesAreaEntity> listLast5Records();

	// TODO: Practical Bonus Desc 2
	// ========================
	// remove the listLast5Records() method above and change the Query above to
	// Spring JPA Query or JPQL using the listLast5RecordsHQL method below
	// Simple SQL Should not use native query as change of db the query syntax need
	// to be changed.
	// If use Spring JPQL, the SQL below can be maintained even change of DB

	// Hint JPQL (CustomerRepository Class File) -
	// https://mkyong.com/spring-data/spring-data-add-custom-method-to-repository/

	// JpaRepository vs CrudRepository
	// https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring

	@Query("SELECT c  FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQL();

	// TODO: Practical Bonus 3
	// ========================
	// List<CovidCasesAreaEntity> listLast5RecordsHQLWithSize(Pageable pageable);
}