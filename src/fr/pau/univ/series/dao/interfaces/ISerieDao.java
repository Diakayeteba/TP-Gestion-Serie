package fr.pau.univ.series.dao.interfaces;

import java.util.List;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Serie;

public interface ISerieDao {
    Serie createSerie(Serie serie) throws DaoException;
    Serie readSerie(int serieID) throws DaoException;
    List<Serie> readAllSeries() throws DaoException;
    Serie readSerieBySaison(int saisonId) throws DaoException;
    Serie updateSerie(Serie serie) throws DaoException;
    void deleteSerie(Serie serie) throws DaoException;
}