package com.app.mapper;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.CovidCasesAreaEntity;
import com.app.error.IDNotFoundException;
import com.app.model.CovidCasesArea;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import fr.xebia.extras.selma.Selma;

@Mapper( )
public interface CovidCasesAreaMapper {

	static CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();
	
	@Maps(withIgnoreMissing = IgnoreMissing.ALL, withIgnoreFields = { "CovidCasesAreaEntity.id" })
	public CovidCasesAreaEntity asEntity(CovidCasesArea covidCasesArea);

	@Maps(withIgnoreFields = "country")
	public CovidCasesArea asResource(CovidCasesAreaEntity covidCasesAreaEntity);
	
	// TODO: Practical Bonus advance 1
	// implement the for loop below to return covidCasesAreas..
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
