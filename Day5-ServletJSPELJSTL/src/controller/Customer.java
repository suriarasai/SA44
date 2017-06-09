package controller;

import java.util.Calendar;
import java.util.Date;

public class Customer {
	
	private Date time;
	private String customerName;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
		this.time = Calendar.getInstance().getTime();
		this.customerName = "SPONGE BOB";
	}
	
	

}
