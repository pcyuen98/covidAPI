package com.app.mapper;

import com.app.entity.CountryEntity;
import com.app.model.Country;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

@Mapper
public interface CountryMapper  {

	@Maps(withIgnoreMissing = IgnoreMissing.ALL, withIgnoreFields = { "Country.id" })
	public CountryEntity asEntity(Country country);

	@Maps(withIgnoreMissing = IgnoreMissing.ALL)
	public Country asResource(CountryEntity entity);

}
