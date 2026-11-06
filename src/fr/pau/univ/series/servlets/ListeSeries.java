package fr.pau.univ.series.servlets;

import java.io.IOException;
import fr.pau.univ.series.dao.DaoFactory;
import fr.pau.univ.series.exception.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/liste")
public class ListeSeries extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Charge la liste à jour depuis PostgreSQL via JPA
            request.setAttribute("series", DaoFactory.getInstance().getSerieDao().readAllSeries());
        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/ListeSeries.jsp").forward(request, response);
    }
}

/** 1. **L'import** `fr.pau.univ.series.model.DataFactory` est remplacé par `DaoFactory` et `DaoException`[cite: 26].
2. **L'appel de méthode** passe désormais par `DaoFactory.getInstance().getSerieDao().readAllSeries()`[cite: 26].
3. **Le bloc `try-catch`** intercepte d'éventuelles erreurs provenant de la couche DAO[cite: 26].

Tu peux effectuer le remplacement, sauvegarder (`Ctrl + S`), et vérifier que la classe compile sans erreur.

Une fois que c'est fait, dis-moi si tu es prêt à passer à l'**Étape 4 : Conversion en projet Maven (`pom.xml`)**[cite: 28, 29] !
<FollowUp>
<Suggest>
<Option>
<Text>C'est mis à jour et ça compile sans erreur. Passons à l'Étape 4 (Maven) !</Text>
</Option>
<Option>
<Text>J'ai un doute sur l'import ou la gestion de DaoException.</Text>
</Option>
</Suggest>
</FollowUp>**/