package app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CO2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Country country;

    private int jahr;
    private double wertKt;
    private String quelle;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }
    public int getJahr() { return jahr; }
    public void setJahr(int jahr) { this.jahr = jahr; }
    public double getWertKt() { return wertKt; }
    public void setWertKt(double wertKt) { this.wertKt = wertKt; }
    public String getQuelle() { return quelle; }
    public void setQuelle(String quelle) { this.quelle = quelle; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}