package com.swastika_company.PowerReading.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(name="name")
	private String userName;
	@Column(name="password")
	private String userPassword;
	
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Meter> meter=new ArrayList<>();


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getuserPassword() {
		return userPassword;
	}


	public void setuserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public List<Meter> getMeter() {
		return meter;
	}


	public void setMeter(List<Meter> meter) {
		this.meter = meter;
	}


	@Override
	public String toString() {
		//System.out.println(meter);
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
	

}
