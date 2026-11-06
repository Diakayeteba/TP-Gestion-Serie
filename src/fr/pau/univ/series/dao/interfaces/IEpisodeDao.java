package fr.pau.univ.series.dao.interfaces;

import java.util.List;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Episode;

public interface IEpisodeDao {
    Episode createEpisode(Episode e) throws DaoException;
    Episode readEpisode(int id) throws DaoException;
    List<Episode> readAllEpisodes() throws DaoException;
    void updateEpisode(Episode e) throws DaoException;
    void deleteEpisode(Episode e) throws DaoException;
}