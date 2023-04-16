package Service;

import Domain.Car;
import Repository.IRepo;

public class CarService extends IdentifiableService<Car> {

    public CarService(IRepo<String,Car> repo) {
        super(repo);
    }
}
