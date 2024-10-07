package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Read - Get one
    public Customer getCustomer(Long id) {
        Customer foundCustomer = customerRepository.findById(id).get();
        return foundCustomer;
    }

    // Read - Get all
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    // update
    public Customer updateCustomer(Long id, Customer customer) {
        // retrieve the customer from the database
        Customer customerToUpdate = customerRepository.findById(id).get();
        // update the customer retrieved from the database
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
        // save the updated customer back to the database
        return customerRepository.save(customerToUpdate);
    }

    // delete
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addInteractionToCustomer'");
    }

    // private int getCustomerIndex(String id) {
    // for (Customer customer : customerRepository.getAllCustomers()) {
    // if (customer.getId().equals(id)) {
    // return customerRepository.getAllCustomers().indexOf(customer);
    // }
    // }

    // // Not found
    // throw new CustomerNotFoundException(id);
    // }
}
