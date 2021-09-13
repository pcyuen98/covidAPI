package com.app.advance.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

// https://mkyong.com/hibernate/cascade-jpa-hibernate-annotation-common-mistake/

@Entity
@Table(name = "trx_stock")
@Getter
@Setter
public class StockEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174876690328458921L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stock_id", unique = true, nullable = false)
	private Long stockId;

	@Column(name = "stock_code", nullable = false, length = 10)
	private String stockCode;

	@Column(name = "stock_name", nullable = false, length = 200)
	private String stockName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockId", orphanRemoval = true)
	@Cascade({CascadeType.ALL})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<StockDailyRecordEntity> stockDailyRecords = new ArrayList<StockDailyRecordEntity>();
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "stockId", orphanRemoval = true)
	//@Cascade({CascadeType.ALL})
	//@OnDelete(action = OnDeleteAction.CASCADE)
	//private List<StockDailyRecordEntity> stockDailyRecordsEager = new ArrayList<StockDailyRecordEntity>();
}
