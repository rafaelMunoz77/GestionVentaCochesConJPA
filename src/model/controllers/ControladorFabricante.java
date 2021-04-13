package model.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Fabricante;

public class ControladorFabricante {

	private static ControladorFabricante instance = null;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("TutorialJavaCochesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorFabricante getInstance () {
		if (instance == null) {
			instance = new ControladorFabricante();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorFabricante() {
	}
	
	
	public Fabricante find(int id) {
		Fabricante f = null;
		EntityManager em = factory.createEntityManager();
		f = (Fabricante) em.find(Fabricante.class, id);
		em.close();
		return f;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Fabricante findPrimero () {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante order by id limit 1", Fabricante.class);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findUltimo () {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante order by id desc limit 1", Fabricante.class);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findSiguiente (int idActual) {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante where id > ? order by id limit 1", Fabricante.class);
		q.setParameter(1, idActual);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findAnterior (int idActual) {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante where id < ? order by id desc limit 1", Fabricante.class);
		q.setParameter(1, idActual);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	
	/**
	 * 
	 * @return
	 */
	public void nuevo (Fabricante c) {
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
	public void modificar (Fabricante c) {
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
	public void borrar(Fabricante c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	
	public List<Fabricante> findAll () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM fabricante", Fabricante.class);
		
		List<Fabricante> list = (List<Fabricante>) q.getResultList();
		em.close();
		return list;
	}
	

}
