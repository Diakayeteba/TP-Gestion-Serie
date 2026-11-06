package fr.pau.univ.series.dao.impl.bdd;

import java.util.List;
import fr.pau.univ.series.dao.interfaces.ISaisonDao;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Saison;

public class SaisonDaoBdd implements ISaisonDao {

    @Override
    public Saison createSaison(Saison s) throws DaoException {
        return null;
    }

    @Override
    public Saison readSaison(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Saison> readAllSaisons() throws DaoException {
        return null;
    }

    @Override
    public void updateSaison(Saison s) throws DaoException {
        // Sera implémenté avec JPA
    }

    @Override
    public void deleteSaison(Saison s) throws DaoException {
        // Sera implémenté avec JPA
    }
}