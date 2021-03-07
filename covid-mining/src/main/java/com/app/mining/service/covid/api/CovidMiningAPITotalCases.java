package com.app.mining.service.covid.api;

import java.util.List;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidMiningAPITotalCases {

	//String getTotalCasesMY() throws Exception;

	String doMining() throws Exception;

	List<CovidCasesAreaEntity> getLast5RecordsMY() throws Exception;
}
