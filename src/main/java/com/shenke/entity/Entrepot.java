package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 仓库实体类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "t_entrepot")
public class Entrepot {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 50)
	private String name;// 仓位名称

	private Integer space;// 仓位大小

	private Integer usespace;// 已用空间

	private Integer residuespace;// 剩余空间

	@Column(length = 50)
	private String remark;// 备注

	@ManyToOne
	@JoinColumn(name = "typeId")
	private EntrepotType type;

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

	public Integer getSpace() {
		return space;
	}

	public void setSpace(Integer space) {
		this.space = space;
	}

	public Integer getUsespace() {
		return usespace;
	}

	public void setUsespace(Integer usespace) {
		this.usespace = usespace;
	}

	public Integer getResiduespace() {
		return residuespace;
	}

	public void setResiduespace(Integer residuespace) {
		this.residuespace = residuespace;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public EntrepotType getType() {
		return type;
	}

	public void setType(EntrepotType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Entrepot [id=" + id + ", name=" + name + ", space=" + space + ", usespace=" + usespace
				+ ", residuespace=" + residuespace + ", remark=" + remark + ", type=" + type + "]";
	}
	
}
