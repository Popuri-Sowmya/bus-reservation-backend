package com.fastx.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class BusOperator {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int operatorid;
	private String name;
	private String email;
	private String mobileNumber;
	
	public BusOperator() {
		
	}

	public BusOperator(int operatorid, String name, String email, String mobileNumber) {
		super();
		this.operatorid = operatorid;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public int getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(int operatorid) {
		this.operatorid = operatorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "BusOperator [operatorid=" + operatorid + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + "]";
	}
}