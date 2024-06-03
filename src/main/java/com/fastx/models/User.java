package com.fastx.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fastx.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userid;
    private String userName;
    private String password;
    private String gender;
    private String mobileNumber;
    private String email;
	private String roles; 
	
	@OneToMany(mappedBy="user",cascade=CascadeType.MERGE)
    private List<Booking> bookingList = new ArrayList<>();

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	public User(Integer userid, String userName, String password, String gender, String mobileNumber, String email,
			String roles, List<Booking> bookingList) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.roles = roles;
		this.bookingList = bookingList;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", userName=" + userName + ", password=" + password + ", gender=" + gender
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", roles=" + roles + ", bookingList="
				+ bookingList + "]";
	}
}