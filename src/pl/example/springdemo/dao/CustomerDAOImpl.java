package pl.example.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.example.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//session factory injection
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		//create a query (sort by last name)
		Query<Customer> theQuery=
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//get result list
		List<Customer> customers=theQuery.getResultList();
		
		//return list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save new or updated customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//read from DB using the primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

}
