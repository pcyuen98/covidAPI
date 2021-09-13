package com.app.advance.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.advance.entity.StockDailyRecordEntity;

public interface StockDailyRecordRepo extends JpaRepository<StockDailyRecordEntity, UUID> {

}
