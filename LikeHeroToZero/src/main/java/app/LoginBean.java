package app;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private EntityManager em;

    private String email;
    private String passwort;
    private String fehlermeldung;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswort() { return passwort; }
    public void setPasswort(String passwort) { this.passwort = passwort; }
    public String getFehlermeldung() { return fehlermeldung; }

    public String login() {
        try {
            Scientist s = em.createQuery(
                "SELECT s FROM Scientist s WHERE s.email = :email AND s.passwortHash = :hash",
                Scientist.class)
                .setParameter("email", email)
                .setParameter("hash", hash(passwort))
                .getSingleResult();

            FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("eingeloggterWissenschaftler", s.getId());
            fehlermeldung = null;
            return "backend/neuer-eintrag?faces-redirect=true";
        } catch (NoResultException ex) {
            fehlermeldung = "E-Mail oder Passwort falsch.";
            return null;
        }
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public static String hash(String text) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}