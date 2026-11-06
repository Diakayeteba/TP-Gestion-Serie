package fr.pau.univ.series.servlets;

import java.io.IOException;

import fr.pau.univ.series.dao.DaoFactory;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Saison;
import fr.pau.univ.series.model.Serie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/creerSaison")
public class CreerSaison extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String serieIdStr = request.getParameter("serie");
		if (serieIdStr != null && !serieIdStr.isBlank()) {
			try {
				int serieId = Integer.parseInt(serieIdStr);
				Serie serie = DaoFactory.getInstance().getSerieDao().readSerie(serieId);
				if (serie != null) {
					int numSaison = (serie.getSaisons() != null) ? serie.getSaisons().size() + 1 : 1;
					Saison saison = new Saison("Saison N°" + numSaison, numSaison);
					serie.addSaison(saison);
					DaoFactory.getInstance().getSerieDao().updateSerie(serie);
				}
			} catch (final DaoException | NumberFormatException e) {
				request.setAttribute("erreur", e.getMessage());
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath() + "/liste");
	}
}