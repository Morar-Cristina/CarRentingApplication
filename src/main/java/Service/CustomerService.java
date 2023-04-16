package Service;

import Domain.Customer;
import Repository.IRepo;

public class CustomerService extends IdentifiableService<Customer> {

    public CustomerService(IRepo<String, Customer> repo) {
        super(repo);
    }
}
