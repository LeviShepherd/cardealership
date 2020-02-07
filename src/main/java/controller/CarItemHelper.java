package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.CarItem;

public class CarItemHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("cardealership");
	
	/**
	 * This method is used to 
	 * @param ci
	 */
	public void insertItem(CarItem ci) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ci);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method uses a query to view all car items
	 * @return allItems
	 */
	public List<CarItem> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<CarItem> allItems	= em.createQuery("SELECT i FROM CarItem i").getResultList();
		return allItems;
	}
	
	/**
	 * This method is used to delete the entire record of a car item
	 * @param toDelete
	 */
	public	void deleteItem(CarItem toDelete)	{
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CarItem> typedQuery = em.createQuery("select ci from "
				+ "CarItem ci where ci.make = :selectedMake and ci.model = :selectedModel and ci.color = :selectedColor", CarItem.class);
		//Substitute	parameter	with	actual	data	from	the	toDelete	item
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedColor", toDelete.getColor());
		//we only want one result
		typedQuery.setMaxResults(1);
		//get	the	result	and	save	it	into	a	new	list	item
		CarItem	result = typedQuery.getSingleResult();
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		}
	
	/**
	 * This method is used to search for a car item by ID
	 * @param idToEdit
	 * @return
	 */
	public CarItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		CarItem found = em.find(CarItem.class, idToEdit);
		em.close();
		return found;
	}
	
	/**
	 * This method is used to update an existing car item
	 * @param toEdit
	 */
	public void updateItem(CarItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method performs a search by the car Make
	 * @param makeName
	 * @return
	 */
	public List<CarItem> searchForItemByMake(String makeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CarItem> typedQuery	= em.createQuery("select ci	from " +
		"CarItem ci where ci.make =:selectedMake", CarItem.class);
		typedQuery.setParameter("selectedMake", makeName);
		List<CarItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * This method performs a search by the car Model
	 * @param modelName
	 * @return
	 */
	public List<CarItem> searchForItemByModel(String modelName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CarItem> typedQuery = em.createQuery("select	ci	from " +
		"CarItem ci where ci.model =:selectedModel", CarItem.class);
		typedQuery.setParameter("selectedModel", modelName);
		List<CarItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * This method performs a search by the car color
	 * @param colorName
	 * @return
	 */
	public List<CarItem> searchForItemByColor(String colorName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CarItem> typedQuery = em.createQuery("select	ci	from " +
		"CarItem ci where ci.color =:selectedColor", CarItem.class);
		typedQuery.setParameter("selectedColor", colorName);
		List<CarItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * This method closes the Entity Manager
	 */
	public void cleanUp(){
		emfactory.close();
	}
}
