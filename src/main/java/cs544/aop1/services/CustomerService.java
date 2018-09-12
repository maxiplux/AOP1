package cs544.aop1.services;

import cs544.aop1.dao.ICustomerDAO;
import cs544.aop1.models.Address;
import cs544.aop1.models.Customer;
import cs544.aop1.services.interfaces.ICustomerService;
import cs544.aop1.services.interfaces.IEmailSender;

public class CustomerService implements ICustomerService {
	private ICustomerDAO customerDAO;
	private IEmailSender emailSender;

	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void addCustomer(String name, String email, String street,
			String city, String zip)
	{
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerDAO.save(customer);

		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
