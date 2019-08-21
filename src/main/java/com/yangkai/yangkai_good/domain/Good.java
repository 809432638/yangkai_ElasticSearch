package com.yangkai.yangkai_good.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName="yangkai",type="yangkai_ElasticSearch_good")
public class Good implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String name;
	
	private Double price;
	
	private String types;
	
	@Field(format=DateFormat.date)
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Good(Integer id, String name, Double price, String types, Date created) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.types = types;
		this.created = created;
	}

	public Good() {
		super();
	}

	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", price=" + price + ", types=" + types + ", created=" + created
				+ "]";
	}


	
	
	
	
}
