package com.app.util;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.CovidCasesAreaEntity;
import com.app.error.IDNotFoundException;
import com.app.model.CovidCasesArea;

public class Mapper {

	// TODO: Practical Bonus advance 1 
	// implement the for loop below to return
	// covidCasesAreas.
	public static List<CovidCasesArea> asResources(List<CovidCasesAreaEntity> covidCaseEntities) {
		List<CovidCasesArea> covidCasesAreas = new ArrayList<CovidCasesArea>();
		if (covidCaseEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesAreaEntity covidCasesEntity : covidCaseEntities) {
			}
		}

		return covidCasesAreas;
	}

}
