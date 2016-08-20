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

		Headphones hp = new Headphones();
		hp.setDescription("adasdf1");
		hp.setSummary("Masdfsadf");

		removeHeadphonesEntry(3);

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
