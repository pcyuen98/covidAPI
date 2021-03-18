package com.app.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Covid19ApiModel {

	String Country;

	String Date;
	
	int Cases;
}
