package com.app.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.advance.entity.StockDailyRecordEntity;
import com.app.advance.entity.StockEntity;
import com.app.advance.repo.StockDailyRecordRepo;
import com.app.advance.repo.StockRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CascadeAllControllerTest {
	private final static String CASCADE_ALL = "/covid/cascadeall";

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyReportRepo;

	private StockEntity insertSingleRecord(String name) {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockName(name);

		StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
		stockDailyRecordEntity.setStockId(stockEntity);

		Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>();
		stockDailyRecords.add(stockDailyRecordEntity);
		stockRepo.save(stockEntity);
		stockDailyReportRepo.save(stockDailyRecordEntity);

		return stockEntity;
	}

	@Transactional
	@GetMapping(CASCADE_ALL)
	public String testCascadeAllAndDeleteChind() {

		StockEntity stockEntity = insertSingleRecord(new Date().toString());
		long id = stockEntity.getStockId();
		log.info("inserted ID ----->" + id);
		List<StockEntity> list = stockRepo.findByStockId(id);
		log.info("Before Delete Size-->" + list.size());
		stockRepo.delete(stockEntity);
		list = stockRepo.findByStockId(id);
		log.info("After Delete Size-->" + list.size());
		return "child list after deletion, it should be 0 -->" + list.size();
	}
}
