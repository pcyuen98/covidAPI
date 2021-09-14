package com.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.advance.entity.StockDailyRecordEntity;
import com.app.advance.entity.StockEntity;
import com.app.advance.repo.StockDailyRecordRepo;
import com.app.advance.repo.StockRepo;

import lombok.extern.slf4j.Slf4j;

@Transactional
@RestController
@Slf4j

public class StockPerformanceControllerTest {

	private final static String GET_STOCK = "/stock/get";

	private final static String INSERT_STOCK = "/stock/insert";

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyRecordRepo;
	@GetMapping(GET_STOCK)
	String getStock() throws Exception {
		log.info("getLatest() started");

		StockEntity stockEntity = stockRepo.findAll().get(0);

		log.info("<------  Starting new Search ------>");

		log.info("fetching childs using get method stockEntity id=" + stockEntity.getStockId());

		log.info("<------  Lazy or Eager Logging Below ------>");
		// Hibernate: select stockentit0_.stock_id as stock_id1_6_,
		// stockentit0_.stock_code as stock_co2_6_, stockentit0_.stock_name as
		// stock_na3_6_ from trx_stock stockentit0_

		// == Note For Eager = By Loading Eager these this loaded in advance
		// Hibernate: select stockdaily0_.fk_stock_id as fk_stock3_7_0_,
		// stockdaily0_.record_id as record_i1_7_0_, stockdaily0_.record_id as
		// record_i1_7_1_, stockdaily0_.stock_desc as stock_de2_7_1_,
		// stockdaily0_.fk_stock_id as fk_stock3_7_1_ from trx_stock_daily_record
		// stockdaily0_ where stockdaily0_.fk_stock_id=?

		// List<StockDailyRecordEntity> childs = stockEntity.getStockDailyRecordsEager();

		// == Note For lazy Load = By Loading Lazy, un comment these this loaded "On
		// Demand"
		// Hibernate: select stockdaily0_.fk_stock_id as fk_stock3_7_0_,
		// stockdaily0_.record_id as record_i1_7_0_, stockdaily0_.record_id as
		// record_i1_7_1_, stockdaily0_.stock_desc as stock_de2_7_1_,
		// stockdaily0_.fk_stock_id as fk_stock3_7_1_ from trx_stock_daily_record
		// stockdaily0_ where stockdaily0_.fk_stock_id=?
		
		List<StockDailyRecordEntity> childs = stockEntity.getStockDailyRecords();

		log.info("child entity size -->" + childs.size());

		StockDailyRecordEntity child = childs.get(0);
		log.info("child desc-->" + child.getDesc());

		return "child at position 1 desc-->" + child.getDesc();
	}

	@Transactional
	@GetMapping(INSERT_STOCK)
	String insertStock() throws Exception {
		log.info("InsertStock() started");
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockName(new Date().toString());
		stockEntity.setStockId(System.currentTimeMillis());
		List<StockDailyRecordEntity> stockDailyRecords = new ArrayList<StockDailyRecordEntity>();
		stockEntity = stockRepo.save(stockEntity);
		
		for (int i = 0; i < 10; i++) {

			StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
			stockDailyRecordEntity.setStockId(stockEntity);
			stockDailyRecordEntity.setDesc(new Date().toString());

			stockDailyRecords.add(stockDailyRecordEntity);
			stockDailyRecordRepo.save(stockDailyRecordEntity);

		}
		
		stockEntity.setStockDailyRecords(stockDailyRecords);

		return "# of child inserted->" + stockEntity.getStockDailyRecords().size();
	}

}
