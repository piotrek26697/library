package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.daos.qualifiers.CustomerImp;
import pl.fis.data.Customer;

@Stateless
@CustomerImp
public class CustomerDAO implements ObjectDAO<Customer>
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addObject(Customer customer)
	{
		em.persist(customer);
	}
	
	@Override
	public List<Customer> getObjects()
	{
		Query query = em.createQuery("select c from Customer c");
		@SuppressWarnings("unchecked")
		List<Customer> customerList = query.getResultList();
		return customerList;
	}

	@Override
	public Customer getObject(long id)
	{
		return em.find(Customer.class, id);
	}
}
