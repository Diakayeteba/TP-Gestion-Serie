package fr.pau.univ.series.servlets;

import java.io.IOException;

import fr.pau.univ.series.dao.DaoFactory;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Serie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/supprimerSerie")
public class SupprimerSerie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String idStr = request.getParameter("serie");
		if (idStr != null && !idStr.isBlank()) {
			try {
				int serieId = Integer.parseInt(idStr);
				Serie serie = DaoFactory.getInstance().getSerieDao().readSerie(serieId);
				if (serie != null) {
					DaoFactory.getInstance().getSerieDao().deleteSerie(serie);
				}
			} catch (final DaoException | NumberFormatException e) {
				request.setAttribute("erreur", e.getMessage());
			}
		}
		response.sendRedirect(request.getContextPath() + "/liste");
	}
}