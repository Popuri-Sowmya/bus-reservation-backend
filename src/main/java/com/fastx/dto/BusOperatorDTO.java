package com.fastx.dto;

import java.util.ArrayList;
import java.util.List;

import com.fastx.models.Booking;
import com.fastx.models.Bus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BusOperatorDTO {

		@NotNull
		private int operatorid;
		@NotEmpty
		private String operatorName;
		@Email
		private String email;
		@NotNull(message="Mobile number cannot be null!")
		@NotBlank(message= "Mobile number cannot be blank!")
		@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
		@Size(min = 10, max = 10)
		private String mobileNumber;
		
		public BusOperatorDTO() {
			
		}
		
		public BusOperatorDTO(@NotNull int operatorid, @NotEmpty String operatorName, @Email String email,
				@NotNull(message = "Mobile number cannot be null!") @NotBlank(message = "Mobile number cannot be blank!") @Pattern(regexp = "[6789]{1}[0-9]{9}", 
				message = "Enter valid 10 digit mobile number") @Size(min = 10, max = 10) String mobileNumber) {
			super();
			this.operatorid = operatorid;
			this.operatorName = operatorName;
			this.email = email;
			this.mobileNumber = mobileNumber;
		}

		public String getOperatorName() {
			return operatorName;
		}

		public void setOperatorName(String operatorName) {
			this.operatorName = operatorName;
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

		public int getOperatorid() {
			return operatorid;
		}

		public void setOperatorid(int operatorid) {
			this.operatorid = operatorid;
		}

		@Override
		public String toString() {
			return "BusOperatorDTO [operatorid=" + operatorid + ", operatorName=" + operatorName + ", email=" + email
					+ ", mobileNumber=" + mobileNumber +  "]";
		}
}
