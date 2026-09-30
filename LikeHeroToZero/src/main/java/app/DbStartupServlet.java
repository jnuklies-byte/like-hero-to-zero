package app;

import jakarta.inject.Inject;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class DbStartupServlet extends HttpServlet {

    @Inject
    private DbSeeder dbSeeder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbSeeder.seedIfNeeded();
    }
}