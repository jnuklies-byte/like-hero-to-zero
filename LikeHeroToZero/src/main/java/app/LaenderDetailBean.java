package app;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject; 
import jakarta.inject.Named; 
@Named @RequestScoped public class LaenderDetailBean { @Inject private CountryRepository countryRepository; private Long id; 
private Country land; 
private CO2Emission neuesterWert; 
public void setId(Long id) { this.id = id; } 
public Long getId() { return id; } 
public void load() { land = countryRepository.findById(id); 
if (land != null) { neuesterWert = countryRepository.findLatestEmission(land); } } 
public Country getLand() { return land; } public CO2Emission getNeuesterWert() 
{ return neuesterWert; } 
}
