import java.util.List;
import java.util.Scanner;
import controller.CarItemHelper;
import model.CarItem;


public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static CarItemHelper cih = new CarItemHelper();

	/**
	 * This method is used to enter data to be sent to the cars table
	 */
	private static void addAnItem() {
		System.out.print("Enter a Make: ");
		String make = in.nextLine();
		System.out.print("Enter a Model: ");
		String model = in.nextLine();
		System.out.print("Enter a Color: ");
		String color = in.nextLine();
		CarItem toAdd = new CarItem(make, model, color);
		cih.insertItem(toAdd);

	}
	
	/**
	 * This method is used to select an item to be deleted from the table
	 */
	private static void deleteAnItem() {
		System.out.print("Enter a Make to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the Model to delete: ");
		String model = in.nextLine();
		System.out.print("Enter the Color to delete: ");
		String color = in.nextLine();
		CarItem toDelete =	new	CarItem(make, model, color);
		cih.deleteItem(toDelete);
	}
	
	/**
	 * This method is used to select an item to search for in the table data
	 */
	private static void editAnItem() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Make");
		System.out.println("2 : Search by Model");
		System.out.println("3 : Search by Color");
		int searchBy = in.nextInt();
		in.nextLine();
		List<CarItem> foundItems;
		// Search by Make
		if (searchBy == 1) {
			System.out.print("Enter the Make name: ");
			String makeName = in.nextLine();
			foundItems = cih.searchForItemByMake(makeName);
		}
		// Search by Model
		if (searchBy == 2) {
			System.out.print("Enter the Model: ");
			String modelName = in.nextLine();
			foundItems = cih.searchForItemByModel(modelName);
		} 
		// Search by Color
		else {
			System.out.println("Enter the Color: ");
			String colorName = in.nextLine();
			foundItems = cih.searchForItemByColor(colorName);
		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (CarItem c : foundItems) {
				System.out.println(c.getId() + " : " + c.returnCarDetails());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			CarItem toEdit = cih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getModel() + " by " + toEdit.getMake() + " in " + toEdit.getColor());
			System.out.println("1 : Update Make");
			System.out.println("2 : Update Model");
			System.out.println("3 : Update Color");
			int update = in.nextInt();
			in.nextLine();

			// Update by Make
			if (update == 1) {
				System.out.print("New Make: ");
				String newMake = in.nextLine();
				toEdit.setMake(newMake);
			}
			// Update by Model
			if (update == 2) {
				System.out.print("New Model: ");
				String newModel = in.nextLine();
				toEdit.setModel(newModel);
			}
			// Update by Color
			else {
				System.out.println("New Color: ");
				String newColor = in.nextLine();
				toEdit.setColor(newColor);
			}

			// Send selected update
			cih.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		// Run startup menu for user
		runMenu();
	}
	
	/**
	 * This is the method used to display the initial information to the user
	 */
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Car Dealership Car Lookup");
		while (goAgain) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 -- Add a Car");
			System.out.println("*  2 -- Edit a Car");
			System.out.println("*  3 -- Delete a Car");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit this program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			// Selection for adding a car
			if (selection == 1) {
				addAnItem();
			// Selection for editing a car
			} else if (selection == 2) {
				editAnItem();
			// Selection for deleting a car
			} else if (selection == 3) {
				deleteAnItem();
			// Selection for viewing the list of cars
			} else if (selection == 4) {
				viewTheList();
			// Selection for program exit
			} else {
				cih.cleanUp();
				System.out.println("Program ending....");
				goAgain = false;
			}

		}

	}
	
	/**
	 * This method pulls one car at a time to be displayed.
	 */
	private static void viewTheList() {
		List<CarItem> allItems = cih.showAllItems();
		for(CarItem singleItem : allItems){
		System.out.println(singleItem.returnCarDetails());
		}
	}
}
