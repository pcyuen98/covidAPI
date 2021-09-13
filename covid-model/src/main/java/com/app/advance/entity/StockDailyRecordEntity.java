package com.app.advance.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trx_stock_daily_record")
@Getter
@Setter
public class StockDailyRecordEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7102491956593238645L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RECORD_ID", unique = true, nullable = false)
	private Integer recordId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	private StockEntity stock;
	
	@Column(name = "STOCK_DESC", length = 200)
	private String desc;

}
