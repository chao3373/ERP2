package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 生产通知单实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_production")
public class Production {

	@Id
	@GeneratedValue
	private Integer id;//id

	@Column(length = 50)
	private String allocationState;//分配状态

	@ManyToOne
	@JoinColumn(name = "jiTaiId")
	private JiTai jiTai;//所分配的机台

	@Column(length = 50)
	private String issueState;//下发状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAllocationState() {
		return allocationState;
	}

	public void setAllocationState(String allocationState) {
		this.allocationState = allocationState;
	}

	public JiTai getJiTai() {
		return jiTai;
	}

	public void setJiTai(JiTai jiTai) {
		this.jiTai = jiTai;
	}

	public String getIssueState() {
		return issueState;
	}

	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	@Override
	public String toString() {
		return "Production [id=" + id + ", allocationState=" + allocationState + ", jiTai=" + jiTai + ", issueState="
				+ issueState + "]";
	}
	
}
