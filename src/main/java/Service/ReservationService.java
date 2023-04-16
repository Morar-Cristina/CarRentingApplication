package Service;

import Domain.Reservation;
import Repository.IRepo;

public class ReservationService extends IdentifiableService<Reservation> {

    public ReservationService(IRepo<String, Reservation> repo) {
        super(repo);
    }
}
