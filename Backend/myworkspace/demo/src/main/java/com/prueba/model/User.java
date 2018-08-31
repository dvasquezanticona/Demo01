package com.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User extends ParentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317577938147481044L;

/*
first_name varchar(255) not null,
second_name varchar(255) null,
first_surname varchar(255) not null,
second_surname varchar(255) null,
phone varchar(30) null,
address varchar(150) not null
login varchar(50) not null
password varchar(50) not null
 * */	

	public User(){}
	
	public User(String firstName, String secondName, String firstSurname, String secondSurname, String phone,
			String address, String login, String password) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.phone = phone;
		this.address = address;
		this.login = login;
		this.password = password;
	}
	
	@Column(name="first_name", nullable=false,length=255)
	private String firstName;

	@Column(name="second_name", nullable=true,length=255)
	private String secondName;
	
	@Column(name="first_surname", nullable=false,length=255)
	private String firstSurname;
	
	@Column(name="second_surname", nullable=true,length=255)
	private String secondSurname;
	
	@Column(name="phone", nullable=true,length=30)
	private String phone;
	
	@Column(name="address", nullable=false,length=150)
	private String address;
	
	@Column(name="login", nullable=false,length=50)
	private String login;
	
	@Column(name="password", nullable=false,length=50)
	private String password;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
