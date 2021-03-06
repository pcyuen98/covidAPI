package com.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {

	private Long id;

	private Country country;

	private String name;

	private String code;
}
