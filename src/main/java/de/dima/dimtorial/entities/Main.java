package de.dima.dimtorial.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

	private static final String PERSISTENCE_UNIT_NAME = "todos";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {

		getHeadphones(4);

	}

	public static Headphones getHeadphones(int id) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("SELECT t FROM Headphones t WHERE t.id=" + id);
		List<Headphones> resSet = q.getResultList();

		em.close();

		if (resSet.size() != 0) {
			System.out.println("Found item !!");
			System.out.println("Description: " + resSet.get(0).getDescription());
			System.out.println("Summary: " + resSet.get(0).getSummary());
			System.out.println("ID: " + resSet.get(0).getId());
			return resSet.get(0);
		} else {
			System.out.println("Nothing found !!!");
			return null;
		}

	}

	public static void createHeadphonesEntry(Headphones entry) {

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		em.merge(entry);

		em.getTransaction().commit();

		em.close();

	}

	public static void removeHeadphonesEntry(int id) {

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("SELECT t FROM Headphones t WHERE t.id=" + id);
		List<Headphones> resSet = q.getResultList();

		if (resSet.size() != 0) {
			em.getTransaction().begin();

			em.remove(resSet.get(0));

			em.getTransaction().commit();
		}

		em.close();
	}

	public static void removeAllHeadphoneEntries() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select t from Headphones t");
		List<Headphones> todoList = q.getResultList();

		em.getTransaction().begin();

		for (int i = 0; i < todoList.size(); i++) {
			em.remove(todoList.get(i));
		}

		em.getTransaction().commit();
	}

}
