package com.app.mapper;

import com.app.entity.CovidCasesDescEntity;
import com.app.model.CovidCasesDesc;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

@Mapper()
public interface CovidAreaDescMapper {

	@Maps(withIgnoreMissing = IgnoreMissing.ALL)
	public CovidCasesDescEntity asEntity(CovidCasesDesc covidCasesDesc);

	@Maps(withIgnoreMissing = IgnoreMissing.ALL)
	public CovidCasesDesc asResource(CovidCasesDescEntity covidAreaDescEntity);

}
