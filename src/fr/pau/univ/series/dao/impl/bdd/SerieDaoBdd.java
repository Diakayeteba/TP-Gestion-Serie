package fr.pau.univ.series.dao.impl.bdd;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pau.univ.series.dao.interfaces.ISerieDao;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Serie;

public class SerieDaoBdd implements ISerieDao {

    private final EntityManagerFactory emf;

    public SerieDaoBdd() {
        this.emf = Persistence.createEntityManagerFactory("GestionSeries");
    }

    @Override
    public Serie createSerie(Serie serie) throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(serie);
            em.getTransaction().commit();
            return serie;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Erreur lors de la création de la série : " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Serie readSerie(int serieID) throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            Serie serie = em.find(Serie.class, serieID);
            if (serie == null) {
                throw new DaoException("Aucune série trouvée avec l'ID : " + serieID);
            }
            return serie;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Serie> readAllSeries() throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Serie s", Serie.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Erreur lors de la récupération des séries : " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Serie readSerieBySaison(int saisonId) throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            // Recherche la série associée à la saison spécifiée
            return em.createQuery("SELECT s FROM Serie s JOIN s.saisons sa WHERE sa.id = :saisonId", Serie.class)
                     .setParameter("saisonId", saisonId)
                     .getSingleResult();
        } catch (Exception e) {
            throw new DaoException("Aucune série trouvée pour la saison ID : " + saisonId);
        } finally {
            em.close();
        }
    }

    @Override
    public Serie updateSerie(Serie serie) throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Serie updatedSerie = em.merge(serie);
            em.getTransaction().commit();
            return updatedSerie;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Erreur lors de la mise à jour de la série : " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteSerie(Serie serie) throws DaoException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Serie toDelete = em.find(Serie.class, serie.getId());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Erreur lors de la suppression de la série : " + e.getMessage());
        } finally {
            em.close();
        }
    }
}