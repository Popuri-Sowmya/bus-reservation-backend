package com.fastx.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class RegisterDTO {
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
	
	public RegisterDTO() {
		
	}

	public RegisterDTO(@NotNull int userid, @NotEmpty String userName, String gender, @NotEmpty String password,
			@NotNull(message = "Mobile number cannot be null!") @NotBlank(message = "Mobile number cannot be blank!") @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number") @Size(min = 10, max = 10) String mobileNumber,
			@Email String email) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.gender = gender;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
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

	@Override
	public String toString() {
		return "RegisterDTO [userid=" + userid + ", userName=" + userName + ", gender=" + gender + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", email=" + email + "]";
	}
}