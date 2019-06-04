package com.nmhung.entity;

import java.sql.Timestamp;

import com.nmhung.annotations.Column;
import com.nmhung.annotations.Entity;


@Entity
public class BaseEntity {
	
	
	
	@Column(name="modifiedby")
	protected String modifiedBy;
	
	@Column(name="createddate")
	protected Timestamp createdDate;
	
	@Column(name="modifieddate")
	protected Timestamp modifiedDate;
	@Column(name="createdby")
	protected String createdBy;
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
