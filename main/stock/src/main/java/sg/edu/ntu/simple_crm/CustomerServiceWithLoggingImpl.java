package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceWithLoggingImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceWithLoggingImpl.class);

    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;

    // @Autowired
    public CustomerServiceWithLoggingImpl(CustomerRepository customerRepository,
            InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.interactionRepository = interactionRepository;
    }

    // CREATE
    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("CustomerServiceWithLoggingImpl.createCustomer() called");
        // return customerRepository.createCustomer(customer);
        return customerRepository.save(customer);
    }

    // Read - Get one
    @Override
    public Customer getCustomer(Long id) {
        logger.info("CustomerServiceWithLoggingImpl.getCustomer() called");
        // return customerRepository.getCustomer(getCustomerIndex(id));
        return customerRepository.findById(id).get();
    }

    // Read - Get all
    @Override
    public ArrayList<Customer> getAllCustomers() {
        logger.info("CustomerServiceWithLoggingImpl.getAllCustomers() called");
        // return customerRepository.getAllCustomers();
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    // update
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        logger.info("CustomerServiceWithLoggingImpl.updateCustomer() called");
        // return customerRepository.updateCustomer(getCustomerIndex(id), customer);

        // Retrieve customer from database
        Customer customerToUpdate = customerRepository.findById(id).get();

        // Update the customer retrieved
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());

        // Saved the updated customer back to the db
        return customerRepository.save(customerToUpdate);
    }

    // delete
    @Override
    public void deleteCustomer(Long id) {
        logger.info("CustomerServiceWithLoggingImpl.deleteCustomer() called");
        // customerRepository.deleteCustomer(getCustomerIndex(id));
        customerRepository.deleteById(id);
        ;
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        // Retrieve the customer from database
        Customer selectedCustomer = customerRepository.findById(id).get();
        // Add this customer to the interaction
        interaction.setCustomer(selectedCustomer);
        // Save the interaction to the db
        return interactionRepository.save(interaction);
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
