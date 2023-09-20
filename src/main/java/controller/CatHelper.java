package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cat;

public class CatHelper {

    EntityManagerFactory emfactory;

    public CatHelper() {
        // Initialize the EntityManagerFactory
        emfactory = Persistence.createEntityManagerFactory("Week3CatCRUDProject");
    }

    public void insertCat(Cat cat) {
        // Create a new EntityManager
        EntityManager em = emfactory.createEntityManager();
        // Begin a transaction
        em.getTransaction().begin();
        // Insert the cat object into the database
        em.persist(cat);
        // Commit the transaction
        em.getTransaction().commit();
        // Close the EntityManager
        em.close();
    }

    public List<Cat> showAllCats() {
        // Create a new EntityManager
        EntityManager em = emfactory.createEntityManager();
        // Query the database to retrieve all Cat objects and store them in a list
        List<Cat> allCats = em.createQuery("SELECT c FROM Cat c", Cat.class).getResultList();
        // Close the EntityManager
        em.close();
        // Return the list of all cats
        return allCats;
    }

    public void deleteCat(Cat cat) {
        // Create a new EntityManager
        EntityManager em = emfactory.createEntityManager();
        // Begin a transaction
        em.getTransaction().begin();
        // Remove the cat object from the database
        em.remove(em.merge(cat));
        // Commit the transaction
        em.getTransaction().commit();
        // Close the EntityManager
        em.close();
    }

    public void updateCat(Cat cat) {
        // Create a new EntityManager
        EntityManager em = emfactory.createEntityManager();
        // Begin a transaction
        em.getTransaction().begin();
        // Update the provided cat object with the database
        em.merge(cat);
        // Commit the transaction
        em.getTransaction().commit();
        // Close the EntityManager to release resources
        em.close();
    }
    
    public Cat findCatById(int id) {
        // Create a new EntityManager
        EntityManager em = emfactory.createEntityManager();
        // Find and retrieve a cat by its ID from the database
        Cat cat = em.find(Cat.class, id);
        // Close the EntityManager
        em.close();
        // Return the retrieved cat (null if not found)
        return cat;
    }

    public void cleanUp() {
        // Close the EntityManagerFactory
        emfactory.close();
    }
}
