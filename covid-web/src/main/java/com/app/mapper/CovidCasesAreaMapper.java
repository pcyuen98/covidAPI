package com.app.mapper;

import com.app.entity.CovidCasesAreaEntity;
import com.app.model.CovidCasesArea;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

@Mapper( )
public interface CovidCasesAreaMapper {

	@Maps(withIgnoreMissing = IgnoreMissing.ALL, withIgnoreFields = { "CovidCasesAreaEntity.id" })
	public CovidCasesAreaEntity asEntity(CovidCasesArea covidCasesArea);

	@Maps(withIgnoreFields = "country")
	public CovidCasesArea asResource(CovidCasesAreaEntity covidCasesAreaEntity);
}
