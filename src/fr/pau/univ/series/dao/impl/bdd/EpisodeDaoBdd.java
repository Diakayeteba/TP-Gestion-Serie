package fr.pau.univ.series.dao.impl.bdd;

import java.util.List;
import fr.pau.univ.series.dao.interfaces.IEpisodeDao;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Episode;

public class EpisodeDaoBdd implements IEpisodeDao {

    @Override
    public Episode createEpisode(Episode e) throws DaoException {
        return null;
    }

    @Override
    public Episode readEpisode(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Episode> readAllEpisodes() throws DaoException {
        return null;
    }

    @Override
    public void updateEpisode(Episode e) throws DaoException {
        // Sera implémenté avec JPA
    }

    @Override
    public void deleteEpisode(Episode e) throws DaoException {
        // Sera implémenté avec JPA
    }
}