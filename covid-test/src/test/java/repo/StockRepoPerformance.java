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

public class StockRepoPerformance {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	StockDailyRecordRepo stockDailyReportRepo;

	@Test
	@Transactional
	public void testPerformanceInsert() {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("Code");
		stockEntity.setStockName(new Date().toString());
		// stockRepo.save(stockEntity);
		List<StockDailyRecordEntity> stockDailyRecords = new ArrayList<StockDailyRecordEntity>();
		for (int i = 0; i < 10; i++) {

			StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
			stockDailyRecordEntity.setStockId(stockEntity);
			stockDailyRecordEntity.setDesc(new Date().toString());

			stockDailyRecords.add(stockDailyRecordEntity);
			// stockDailyReportRepo.save(stockDailyRecordEntity);

		}

	}
}
