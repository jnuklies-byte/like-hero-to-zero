package app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DbSeeder {

    @Inject
    private EntityManager em;

    @Transactional
    public void seedIfNeeded() {
        Long anzahl = em.createQuery("SELECT COUNT(c) FROM Country c", Long.class).getSingleResult();
        if (anzahl == 0) {
            seedData();
            System.out.println(">>> CO2-Datensatz eingespielt.");
        } else {
            System.out.println(">>> Daten bereits vorhanden. Anzahl Laender: " + anzahl);
        }
    }

    private void seedData() {
        String[][] daten = {
            {"China", "CHN", "2022", "11397000"},
            {"USA", "USA", "2022", "5057000"},
            {"Indien", "IND", "2022", "2830000"},
            {"Russland", "RUS", "2022", "1909000"},
            {"Japan", "JPN", "2022", "1064000"},
            {"Iran", "IRN", "2022", "749000"},
            {"Deutschland", "DEU", "2022", "674000"},
            {"S\u00fcdkorea", "KOR", "2022", "611000"},
            {"Saudi-Arabien", "SAU", "2022", "697000"},
            {"Indonesien", "IDN", "2022", "742000"},
            {"Kanada", "CAN", "2022", "582000"},
            {"Mexiko", "MEX", "2022", "445000"},
            {"Brasilien", "BRA", "2022", "482000"},
            {"S\u00fcdafrika", "ZAF", "2022", "435000"},
            {"Australien", "AUS", "2022", "389000"},
            {"T\u00fcrkei", "TUR", "2022", "453000"},
            {"Gro\u00dfbritannien", "GBR", "2022", "321000"},
            {"Italien", "ITA", "2022", "330000"},
            {"Frankreich", "FRA", "2022", "302000"},
            {"Polen", "POL", "2022", "293000"}
        };

        for (String[] zeile : daten) {
            Country c = new Country();
            c.setName(zeile[0]);
            c.setIsoCode(zeile[1]);
            em.persist(c);

            CO2Emission e = new CO2Emission();
            e.setCountry(c);
            e.setJahr(Integer.parseInt(zeile[2]));
            e.setWertKt(Double.parseDouble(zeile[3]));
            e.setQuelle("Global Carbon Project / Our World in Data");
            e.setStatus("freigegeben");
            em.persist(e);
        }

        Scientist wissenschaftlerin = new Scientist();
        wissenschaftlerin.setName("Max Mustermann");
        wissenschaftlerin.setEmail("max.mustermann@example.com");
        wissenschaftlerin.setPasswortHash(LoginBean.hash("test1234"));
        em.persist(wissenschaftlerin);
    }
}