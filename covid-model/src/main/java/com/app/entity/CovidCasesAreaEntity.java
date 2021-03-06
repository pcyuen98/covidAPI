package com.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trx_covid_cases")
@Getter
@Setter
public class CovidCasesAreaEntity {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_areaId", referencedColumnName = "id")
	private AreaEntity area;

	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private int cases;
	
	
	
}
