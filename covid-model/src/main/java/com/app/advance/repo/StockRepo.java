package com.app.advance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.advance.entity.StockEntity;

public interface StockRepo extends JpaRepository<StockEntity, Long> {

	@Query("SELECT c  FROM StockEntity AS c where c.stockId = :stockId")
	List<StockEntity> findByStockId(Long stockId);

}
