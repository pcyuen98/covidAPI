package com.app.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidCasesArea {

	private Long id;

	private Area area;

	private LocalDate date;

	private int cases;

}
