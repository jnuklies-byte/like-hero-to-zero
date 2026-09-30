package app; 
	import jakarta.enterprise.context.RequestScoped;
	import jakarta.inject.Inject;
	import jakarta.inject.Named;
	import java.util.List; @Named @RequestScoped
	public class LaenderListeBean { @Inject private CountryRepository countryRepository;
	public List<Country> getLaender()
	{ return countryRepository.findAll(); } 
}