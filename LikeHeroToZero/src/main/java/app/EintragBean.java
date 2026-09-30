package app;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EintragBean implements Serializable {

    @Inject
    private EntityManager em;

    private Long id;
    private Long countryId;
    private int jahr;
    private double wertKt;
    private String quelle;
    private String erfolgsmeldung;

    public List<Country> getLaender() {
        return em.createQuery("SELECT c FROM Country c ORDER BY c.name", Country.class).getResultList();
    }

    public List<CO2Emission> getEintraege() {
        return em.createQuery("SELECT e FROM CO2Emission e ORDER BY e.country.name, e.jahr DESC", CO2Emission.class).getResultList();
    }

    public void bearbeiten(Long emissionId) {
        CO2Emission e = em.find(CO2Emission.class, emissionId);
        if (e != null) {
            this.id = e.getId();
            this.countryId = e.getCountry().getId();
            this.jahr = e.getJahr();
            this.wertKt = e.getWertKt();
            this.quelle = e.getQuelle();
        }
    }

    @Transactional
    public String speichern() {
        if (jahr < 1900 || jahr > 2100) {
            erfolgsmeldung = "Bitte ein gueltiges Jahr eingeben (1900-2100).";
            return null;
        }
        if (wertKt < 0) {
            erfolgsmeldung = "Der Wert darf nicht negativ sein.";
            return null;
        }

        Country country = em.find(Country.class, countryId);
        CO2Emission e;
        if (id != null) {
            e = em.find(CO2Emission.class, id);
        } else {
            e = new CO2Emission();
        }
        e.setCountry(country);
        e.setJahr(jahr);
        e.setWertKt(wertKt);
        e.setQuelle(quelle);
        e.setStatus("freigegeben");

        if (id == null) {
            em.persist(e);
        }

        erfolgsmeldung = "Eintrag gespeichert.";
        zuruecksetzen();
        return null;
    }

    public void zuruecksetzen() {
        id = null;
        countryId = null;
        jahr = 0;
        wertKt = 0;
        quelle = null;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCountryId() { return countryId; }
    public void setCountryId(Long countryId) { this.countryId = countryId; }
    public int getJahr() { return jahr; }
    public void setJahr(int jahr) { this.jahr = jahr; }
    public double getWertKt() { return wertKt; }
    public void setWertKt(double wertKt) { this.wertKt = wertKt; }
    public String getQuelle() { return quelle; }
    public void setQuelle(String quelle) { this.quelle = quelle; }
    public String getErfolgsmeldung() { return erfolgsmeldung; }
}