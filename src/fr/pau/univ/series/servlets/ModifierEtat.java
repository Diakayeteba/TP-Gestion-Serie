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
@WebServlet(urlPatterns = "/etat")
public class ModifierEtat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		
		final String serieIdStr = request.getParameter("serie");
		final String saisonIdStr = request.getParameter("saison");
		final String episodeIdStr = request.getParameter("episode");

		try {
			if (serieIdStr != null && !serieIdStr.isBlank()) {
				int serieId = Integer.parseInt(serieIdStr);
				Serie serie = DaoFactory.getInstance().getSerieDao().readSerie(serieId);
				
				if (serie != null) {
					// Cas 1 : Changement d'état de l'épisode (icône œil)
					if (saisonIdStr != null && !saisonIdStr.isBlank() && episodeIdStr != null && !episodeIdStr.isBlank()) {
						int saisonId = Integer.parseInt(saisonIdStr);
						int episodeId = Integer.parseInt(episodeIdStr);
						
						for (Saison s : serie.getSaisons()) {
							if (s.getId() == saisonId && s.getEpisodes() != null) {
								for (Episode ep : s.getEpisodes()) {
									if (ep.getId() == episodeId) {
										ep.setVu(!ep.isVu());
										break;
									}
								}
							}
						}
					} 
					// Cas 2 : Changement d'état global de la série
					else {
						serie.setToutVu(!serie.isToutVu());
					}
					
					DaoFactory.getInstance().getSerieDao().updateSerie(serie);
				}
			}
		} catch (final DaoException | NumberFormatException e) {
			request.setAttribute("erreur", e.getMessage());
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/liste");
	}
}