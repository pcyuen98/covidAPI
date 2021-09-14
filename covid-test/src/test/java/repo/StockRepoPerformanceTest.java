package repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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

public class StockRepoPerformanceTest {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyReportRepo;

	@Test
	@Transactional
	public void testPerformanceInsert() {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockId(System.currentTimeMillis());
		stockEntity.setStockName(new Date().toString());
		stockEntity.setStockId(System.currentTimeMillis());
		List<StockDailyRecordEntity> stockDailyRecords = new ArrayList<StockDailyRecordEntity>();
		stockEntity = stockRepo.save(stockEntity);

		for (int i = 0; i < 10; i++) {

			StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
			stockDailyRecordEntity.setStockId(stockEntity);
			stockDailyRecordEntity.setDesc(new Date().toString());

			stockDailyRecords.add(stockDailyRecordEntity);
			stockDailyReportRepo.save(stockDailyRecordEntity);

		}
		stockEntity.setStockDailyRecords(stockDailyRecords);
		stockEntity = stockRepo.save(stockEntity);
		log.info("<------  insert ends ------>" + stockEntity.getStockDailyRecords().size());

		stockEntity = stockRepo.findAll().get(0);

		log.info("<------  Starting new Search ------>");

		log.info("fetching childs using get method stockEntity id=" + stockEntity.getStockId());

		log.info("<------  Lazy or Eager Logging Below ------>");
		List<StockDailyRecordEntity> childs = stockEntity.getStockDailyRecords();

		log.info("child entity size -->" + childs.size());

		StockDailyRecordEntity child = childs.get(0);
		log.info("child desc-->" + child.getDesc());

	}
}
