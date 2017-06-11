package org.angularjs.poc.pojo;


import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
	@NamedQuery(
			name = "listAllOrders",
			query = "from Order order"
			)
})

@Entity
@Table(name="customer_orders", schema = "public")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L; 

	private BigInteger orderId;
	private String orderItem;
	private String itemDesc;
	private Date orderDate;
	private Customer customer;


	public Order(){}

	public Order(BigInteger orderId){this.orderId = orderId;}


	@Id 
	@Column(name="order_id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public BigInteger getOrderId() {
		return orderId;
	}


	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}


	@Column(name="order_item_name", nullable=false, length=50)
	public String getOrderItem() {
		return orderItem;
	}


	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	@Column(name="order_item_desc", nullable=false, length=50)
	public String getItemDesc() {
		return itemDesc;
	}


	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@Basic(optional = false)
	@Column(name = "order_dt", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)  
	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderItem=" + orderItem
				+ ", itemDesc=" + itemDesc + ", orderDate=" + orderDate+ "]";
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
