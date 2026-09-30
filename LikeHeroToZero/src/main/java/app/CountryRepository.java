package app;
	import jakarta.enterprise.context.ApplicationScoped;
	import jakarta.inject.Inject;
	import jakarta.persistence.EntityManager;
	import jakarta.persistence.NoResultException;
	import java.util.List;
	@ApplicationScoped public class CountryRepository { @Inject private EntityManager em;
	public List<Country> findAll() { return em.createQuery("SELECT c FROM Country c ORDER BY c.name", Country.class).getResultList(); }
	public Country findById(Long id) { return em.find(Country.class, id); }
	public CO2Emission findLatestEmission(Country country)
	{ try { return em.createQuery( "SELECT e FROM CO2Emission e WHERE e.country = :country ORDER BY e.jahr DESC", CO2Emission.class)
			.setParameter("country", country) .setMaxResults(1) .getSingleResult(); } 
	catch (NoResultException ex) { return null; }
	} 
} 