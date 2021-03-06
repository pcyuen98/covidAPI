package com.app.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidCasesArea {

	private Long id;

	private Area area;

	private Date date;

	private int cases;

}
