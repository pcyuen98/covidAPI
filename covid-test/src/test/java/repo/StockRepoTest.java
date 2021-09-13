package repo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
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

public class StockRepoTest {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyReportRepo;

	private StockEntity insertSingleRecord(String name) {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockName(name);

		StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
		stockDailyRecordEntity.setStock(stockEntity);

		Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>();
		stockDailyRecords.add(stockDailyRecordEntity);
		stockRepo.save(stockEntity);
		stockDailyReportRepo.save(stockDailyRecordEntity);

		return stockEntity;
	}

	@Test
	public void testCascadeAllAndDeleteChind() {

		StockEntity stockEntity = insertSingleRecord(new Date().toString());
		long id = stockEntity.getStockId();
		log.info("inserted ID ----->" + id);
		List<StockEntity> list = stockRepo.findByStockId(id);
		log.info("Before Delete Size-->" + list.size());
		stockRepo.delete(stockEntity);
		list = stockRepo.findByStockId(id);
		log.info("After Delete Size-->" + list.size());
		Assert.assertTrue(list.size() == 0);
	}

}
