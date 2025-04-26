package cm.amcloud.platform.service;

import cm.amcloud.platform.model.Reservation;
import cm.amcloud.platform.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getAll() {
        return repository.findAll();
    }

    public Reservation save(Reservation r) {
        return repository.save(r);
    }
}
