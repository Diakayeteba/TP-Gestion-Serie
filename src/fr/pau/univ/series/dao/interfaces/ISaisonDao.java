package fr.pau.univ.series.dao.interfaces;

import java.util.List;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Saison;

public interface ISaisonDao {
    Saison createSaison(Saison s) throws DaoException;
    Saison readSaison(int id) throws DaoException;
    List<Saison> readAllSaisons() throws DaoException;
    void updateSaison(Saison s) throws DaoException;
    void deleteSaison(Saison s) throws DaoException;
}