package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class CarItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="MAKE")
	private String make;
	@Column(name="MODEL")
	private String model;
	@Column(name="COLOR")
	private String color;
	
	/**
	 * Default no-arg constructor
	 */
	public CarItem() {

	}

	/**
	 * Begin Getters and Setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * End Getters and Setters
	 */
	
	/**
	 * Helper Methods
	 */
	
	/**
	 * Non-Default constructor used to populate a car object with make, model and color.
	 * @param make
	 * @param model
	 * @param color
	 */
	public CarItem(String make, String model, String color) {
		this.make = make;
		this.model = model;
		this.color = color;
	}
	
	/**
	 * This method outputs the car item data in a format
	 * @return String
	 */
	public String returnCarDetails() {
		return "Make: " + make + " Model: " + model + " Color: " + color;
	}
}
