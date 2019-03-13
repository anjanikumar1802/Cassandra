package com.example.Model;

import java.util.UUID;

public class Car {

	private UUID id;

	private String name;

	private String owner;

	private String number;

	private String manufacturer;

	public Car() {
	}

	public Car(UUID id, String name, String owner, String number, String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.number = number;
		this.manufacturer = manufacturer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
