package org.angularjs.poc.pojo;


import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_details", schema = "public")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L; 

	private BigInteger custId;
	private String name;
	private String email;


	@Id 
	@Column(name="customer_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public BigInteger getCustId() {
		return custId;
	}

	public void setCustId(BigInteger custId) {
		this.custId = custId;
	}

	@Column(name="customer_name", nullable=false, length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="customer_email", nullable=false, length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(){}

	public Customer(BigInteger custId){this.custId = custId;}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name
				+ ", email=" + email + "]";
	}
}
