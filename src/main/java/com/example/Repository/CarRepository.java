package com.example.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.example.Model.Car;

public class CarRepository {

	private static final String TABLE_NAME = "cars";
	private Session session;

	public CarRepository(Session session) {
		this.session = session;
	}

	//Creating a Table
	public void createCarTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id uuid PRIMARY KEY, ").append("name text,").append("owner text,").append("number text,")
				.append("manufacturer text);");
		;
		final String query = sb.toString();
		session.execute(query);
	}

	//Writing Data to a table
	public void insertACar(Car car) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
				.append("(id, name, owner, number, manufacturer) ").append("VALUES (").append(car.getId()).append(", '")
				.append(car.getName()).append("', '").append(car.getOwner()).append("', '").append(car.getNumber())
				.append("', '").append(car.getManufacturer()).append("');");

		final String query = sb.toString();
		session.execute(query);
	}

	//Delete entire table
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);

		final String query = sb.toString();
		session.execute(query);
	}

	//Reading All Records
	public List<Car> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

		final String query = sb.toString();
		ResultSet rs = session.execute(query);

		List<Car> cars = new ArrayList<Car>();

		for (Row r : rs) {
			Car car = new Car(r.getUUID("id"), r.getString("name"), r.getString("owner"), r.getString("number"),
					r.getString("manufacturer"));
			cars.add(car);
		}
		return cars;
	}

	//Reading Data
	public Car selectById(UUID id) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE id = ").append(id)
				.append(";");

		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		List<Car> cars = new ArrayList<Car>();

		for (Row r : rs) {
			Car car = new Car(r.getUUID("id"), r.getString("name"), r.getString("owner"), r.getString("number"),
					r.getString("manufacturer"));
			cars.add(car);
		}

		return cars.get(0);
	}

}
