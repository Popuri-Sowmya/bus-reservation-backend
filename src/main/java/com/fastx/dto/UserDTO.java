package com.fastx.dto;

import java.util.ArrayList;
import java.util.List;

import com.fastx.models.Booking;
import com.fastx.models.User;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class UserDTO {
	@NotNull
	private int userid;
	@NotEmpty
	private String userName;
	private String gender;
	@NotEmpty
	private String password;
	@NotNull(message="Mobile number cannot be null!")
	@NotBlank(message= "Mobile number cannot be blank!")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	@Size(min = 10, max = 10)
	private String mobileNumber;
	@Email
	private String email;
	@NotEmpty
	private String roles;
	
	public UserDTO() {
		
	}

	public UserDTO(@NotNull int userid, @NotEmpty String userName, String gender, @NotEmpty String password,
			@NotNull(message = "Mobile number cannot be null!") @NotBlank(message = "Mobile number cannot be blank!") @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number") @Size(min = 10, max = 10) String mobileNumber,
			@Email String email, @NotEmpty String roles) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.gender = gender;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.roles = roles;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", userName=" + userName + ", gender=" + gender + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", roles=" + roles + "]";
	}
   
}