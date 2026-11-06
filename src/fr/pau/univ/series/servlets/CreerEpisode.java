package fr.pau.univ.series.servlets;

import java.io.IOException;
import fr.pau.univ.series.dao.DaoFactory;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Episode;
import fr.pau.univ.series.model.Saison;
import fr.pau.univ.series.model.Serie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/creerEpisode")
public class CreerEpisode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String serieIdStr = request.getParameter("serie");
        String saisonIdStr = request.getParameter("saison");
        String nomEpisode = request.getParameter("nomEpisode");

        if (serieIdStr != null && !serieIdStr.isBlank() && saisonIdStr != null && !saisonIdStr.isBlank()) {
            try {
                int serieId = Integer.parseInt(serieIdStr);
                int saisonId = Integer.parseInt(saisonIdStr);
                
                // On passe par SerieDao qui fonctionne déjà à 100%
                Serie serie = DaoFactory.getInstance().getSerieDao().readSerie(serieId);
                
                if (serie != null && serie.getSaisons() != null) {
                    for (Saison saison : serie.getSaisons()) {
                        if (saison.getId() == saisonId) {
                            int numEpisode = (saison.getEpisodes() != null) ? saison.getEpisodes().size() + 1 : 1;
                            if (nomEpisode == null || nomEpisode.isBlank()) {
                                nomEpisode = "Episode N°" + numEpisode;
                            }
                            Episode episode = new Episode(nomEpisode, numEpisode);
                            saison.addEpisode(episode);
                            break;
                        }
                    }
                    // La cascade JPA sauvegarde la série -> la saison -> le nouvel épisode !
                    DaoFactory.getInstance().getSerieDao().updateSerie(serie);
                }
            } catch (DaoException | NumberFormatException e) {
                request.setAttribute("erreur", e.getMessage());
                e.printStackTrace();
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/liste");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}