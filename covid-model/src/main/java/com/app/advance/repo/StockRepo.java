package com.app.advance.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.advance.entity.StockEntity;

public interface StockRepo extends JpaRepository<StockEntity, UUID> {
	List<StockEntity> findByStockId(Integer StockId);
}
