package repo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.advance.entity.StockDailyRecordEntity;
import com.app.advance.entity.StockEntity;
import com.app.advance.repo.StockDailyRecordRepo;
import com.app.advance.repo.StockRepo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest

public class StockRepoPerformance {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyReportRepo;

	@Test
	public void testPerformanceInsert() {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockName(new Date().toString());
		stockRepo.save(stockEntity);
		for (int i = 0; i < 1000; i++) {

			StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
			stockDailyRecordEntity.setStock(stockEntity);
			stockDailyRecordEntity.setDesc(new Date().toString());
			Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>();
			stockDailyRecords.add(stockDailyRecordEntity);
			stockDailyReportRepo.save(stockDailyRecordEntity);

		}
		log.info("Total StockDailyRecordEntity Inserted ----->" + stockDailyReportRepo.findAll().size());
		stockRepo.save(stockEntity);
		log.info("Starting Lazy Search");
		Set<StockDailyRecordEntity> setLazy = stockEntity.getStockDailyRecords();
		log.info("Starting Lazy Search size ==>" + setLazy.size());

		log.info("Starting Eager Search");
		log.info("testPerformanceInsert ends");
	}
}
