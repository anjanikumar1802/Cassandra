package com.example.CassandraDemo;

import java.util.List;
import java.util.UUID;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;
import com.example.Model.Car;
import com.example.Repository.CarRepository;
import com.example.Repository.KeyspaceRepository;

public class CassandraClient {
//	private static final Logger LOG = LoggerFactory.getLogger(CassandraClient.class);

	public static void main(String args[]) {
		CassandraConnector connector = new CassandraConnector();
		connector.connect("127.0.0.1", null);
		Session session = connector.getSession();

		KeyspaceRepository sr = new KeyspaceRepository(session);
		sr.createKeyspace("library", "SimpleStrategy", 1);
		sr.useKeyspace("library");

		CarRepository cr = new CarRepository(session);
		cr.createCarTable();
		System.out.println("-------------------------- Created car table -------------------------------");
		System.out.println();
		
		Car car = new Car();
		car.setId(UUID.randomUUID());
		car.setName("URUS-SUV");
		car.setNumber("ka51 5163");
		car.setOwner("Raj");
		car.setManufacturer("Lamborgini");

		Car car2 = new Car(UUID.randomUUID(), "Mustang","Vivek","ka05 4309", "Ford");

		cr.insertACar(car);
		cr.insertACar(car2);

		System.out.println("------------- inserted cars (two records) successfully ------------------");
		System.out.println();

		List<Car> cars = cr.selectAll();
		for (Car c : cars) {
			System.out.println(c.getId() + "  " + c.getName() + "  " + c.getNumber() + "  " + c.getOwner() + "  "
					+ c.getManufacturer());
			System.out.println("-------------------------------------------------------------------------");
		}
		
		System.out.println();
		System.out.println("----------------------- Printed all cars ----------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
		
		Car c = cr.selectById(car.getId());
		System.out.println(c.getId() + "  " + c.getName() + "  " + c.getNumber() + "  " + c.getOwner() + "  "
				+ c.getManufacturer());
		System.out.println("-------------------------Fetching one car by ID-----------------------");
		System.out.println();

		cr.deleteTable("cars");
		System.out.println();
		System.out.println("--------------------------Deleted table-------------------------------");
		System.out.println();
		
		sr.deleteKeyspace("library");
		System.out.println();
		System.out.println("-----------------------Deleted keyspace--------------------------------");
		System.out.println();

		connector.close();
	}
}




































//BookRepository br = new BookRepository(session);
//br.createTable();
//br.alterTablebooks("publisher", "text");
//
//br.createTableBooksByTitle();
//
//Book book = new Book(UUIDs.timeBased(), "Effective Java", "Joshua Bloch", "Programming");
//br.insertBookBatch(book);
//
//br.selectAll().forEach(o -> LOG.info("Title in books: " + o.getTitle()));
//br.selectAllBookByTitle().forEach(o -> LOG.info("Title in booksByTitle: " + o.getTitle()));
//
//br.deletebookByTitle("Effective Java");
//br.deleteTable("books");
//br.deleteTable("booksByTitle");
