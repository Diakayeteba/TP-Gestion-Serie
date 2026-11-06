package fr.pau.univ.series.dao;

import fr.pau.univ.series.dao.impl.bdd.EpisodeDaoBdd;
import fr.pau.univ.series.dao.impl.bdd.SaisonDaoBdd;
import fr.pau.univ.series.dao.impl.bdd.SerieDaoBdd;
import fr.pau.univ.series.dao.interfaces.IEpisodeDao;
import fr.pau.univ.series.dao.interfaces.ISaisonDao;
import fr.pau.univ.series.dao.interfaces.ISerieDao;
import fr.pau.univ.series.exception.DaoException;

public class DaoFactory {

    // 1. Variable statique du Singleton
    private static DaoFactory instance;

    // 2. Attributs pour stocker les DAO
    private ISerieDao serieDao = null;
    private ISaisonDao saisonDao = null;
    private IEpisodeDao episodeDao = null;

    // 3. Constructeur privé (empeche l'instanciation directe)
    private DaoFactory() throws DaoException {
    }

    // 4. Méthode d'accès au Singleton (Lazy Loading)
    public static final DaoFactory getInstance() throws DaoException {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    // 5. Getters de la Factory (Lazy Loading des DAO)
    public ISerieDao getSerieDao() {
        if (this.serieDao == null) {
            this.serieDao = new SerieDaoBdd();
        }
        return this.serieDao;
    }

    public ISaisonDao getSaisonDao() {
        if (this.saisonDao == null) {
            this.saisonDao = new SaisonDaoBdd();
        }
        return this.saisonDao;
    }

    public IEpisodeDao getEpisodeDao() {
        if (this.episodeDao == null) {
            this.episodeDao = new EpisodeDaoBdd();
        }
        return this.episodeDao;
    }
}