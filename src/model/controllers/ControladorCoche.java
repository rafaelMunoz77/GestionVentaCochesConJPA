package model.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Coche;

public class ControladorCoche {

	private static ControladorCoche instance = null;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TutorialJavaCochesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorCoche getInstance () {
		if (instance == null) {
			instance = new ControladorCoche();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorCoche() {
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Coche findPrimero () {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche order by id limit 1", Coche.class);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findUltimo () {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche order by id desc limit 1", Coche.class);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findSiguiente (int idActual) {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche where id > ? order by id limit 1", Coche.class);
		q.setParameter(1, idActual);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findAnterior (int idActual) {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche where id < ? order by id desc limit 1", Coche.class);
		q.setParameter(1, idActual);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	
	/**
	 * 
	 * @return
	 */
	public void nuevo (Coche c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}


	/**
	 * 
	 * @return
	 */
	public void modificar (Coche c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();
	}


	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void borrar(Coche c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	
	

}
