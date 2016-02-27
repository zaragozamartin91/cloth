package com.mkyong.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//EJEMPLO ONE-ONE MAPPING
@Entity
@Table(name = "stock", uniqueConstraints = {
		@UniqueConstraint(columnNames = "STOCK_NAME"),
		@UniqueConstraint(columnNames = "STOCK_CODE") })
public class Stock implements Serializable {
	private Integer id;
	private String code;
	private String name;
	private StockDetail detail;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "STOCK_CODE", nullable = false, unique = true, length = 10)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STOCK_NAME", nullable = false, unique = true, length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
	public StockDetail getDetail() {
		return detail;
	}

	public void setDetail(StockDetail detail) {
		this.detail = detail;
	}

	public Stock(String code, String name, StockDetail detail) {
		super();
		this.code = code;
		this.name = name;
		this.detail = detail;
	}

	public Stock(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Stock() {
		super();
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", name=" + name
				+ ", detail=" + detail + "]";
	}

}
