package com.app.service.covid;

import com.app.model.CovidCasesDesc;

public interface CovidAdvanceService {

	void addCovidRollBack(String desc);

	CovidCasesDesc addCovidNoRollBack(String desc);

}
