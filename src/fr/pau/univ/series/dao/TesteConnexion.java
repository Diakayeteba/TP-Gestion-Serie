package fr.pau.univ.series.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConnexion {

    public static void main(String[] args) {
        try {
            System.out.println("Tentative de connexion JPA...");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionSeries");
            EntityManager em = emf.createEntityManager();
            
            System.out.println(" Connexion réussie à la base PostgreSQL !");
            
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println("❌ Échec de la connexion :");
            e.printStackTrace();
        }
    }
}