package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesArea> addCovid();


}
