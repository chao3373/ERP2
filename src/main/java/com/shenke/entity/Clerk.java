package com.shenke.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  职员实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_clerk")
public class Clerk {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String sex;
	
	@Column(length=50)
	private String duty;//职务
	
	@Column(length=50)
	private String position;//职称
	
	@Column(length=50)
	private String school;//学历
	
	@Column(length=50)
	private String tel;//电话
	
	@Column(length=500)
	private String address;//地址
	
	@Column(length=50)
	private String age;//年龄
	
	@Column(length=50)
	private String politics;//政治面貌
	
	@Column(length=500)
	private String idcar;//身份证
	
	@Column(length=50)
	private String start;//入厂时间
	
	@Column(length=500)
	private String strong;//特长
	
	@Column(length=500)
	private String remark;//备注
	
	@ManyToOne
	@JoinColumn(name="depId")
	private Dep dep;//所属部门

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getIdcar() {
		return idcar;
	}

	public void setIdcar(String idcar) {
		this.idcar = idcar;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStrong() {
		return strong;
	}

	public void setStrong(String strong) {
		this.strong = strong;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	@Override
	public String toString() {
		return "Clerk [id=" + id + ", name=" + name + ", sex=" + sex + ", duty=" + duty + ", position=" + position
				+ ", school=" + school + ", tel=" + tel + ", address=" + address + ", age=" + age + ", politics="
				+ politics + ", idcar=" + idcar + ", start=" + start + ", strong=" + strong + ", remark=" + remark
				+ ", dep=" + dep + "]";
	}
	
}
