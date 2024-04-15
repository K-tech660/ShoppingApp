package com.first.entity;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Orders {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Long userId;
 private int quantity;
 private int amount;
 private String coupon;
 private String Status;
 private String transactionId;
 private Date date;
 

 // Getters and setters
 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public Long getUserId() {
     return userId;
 }

 public void setUserId(Long userId) {
     this.userId = userId;
 }

 public int getQuantity() {
     return quantity;
 }

 public void setQuantity(int quantity) {
     this.quantity = quantity;
 }

 public int getAmount() {
     return amount;
 }

 public void setAmount(int amount) {
     this.amount = amount;
 }

 public String getCoupon() {
     return coupon;
 }

 public void setCoupon(String coupon) {
     this.coupon = coupon;
 }

public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}

public String getTransactionId() {
	return transactionId;
}

public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

}