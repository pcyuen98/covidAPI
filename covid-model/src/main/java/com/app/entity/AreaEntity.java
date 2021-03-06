package com.app.entity;

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
@Table(name = "area")
@Getter
@Setter
public class AreaEntity {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_stateId", referencedColumnName = "id")
	private StateEntity state;

	@Column(nullable = false, length = 200)
	private String name;
	
	@Column(nullable = false, length = 20)
	private String code;
}
