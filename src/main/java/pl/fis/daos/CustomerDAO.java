package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.Customer;

@Stateless
public class CustomerDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public void addCustomer(Customer customer)
	{
		em.persist(customer);
	}
	
	public List<Customer> getCustomers()
	{
		Query query = em.createQuery("select c from Customer c");
		@SuppressWarnings("unchecked")
		List<Customer> customerList = query.getResultList();
		return customerList;
	}
}
