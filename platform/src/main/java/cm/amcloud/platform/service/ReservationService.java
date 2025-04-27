package cm.amcloud.platform.service;

import cm.amcloud.platform.model.Reservation;
import cm.amcloud.platform.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;

    public ReservationService(ReservationRepository reservationRepository, RestTemplate restTemplate) {
        this.reservationRepository = reservationRepository;
        this.restTemplate = restTemplate;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        String agencyName = "AgenceAlpha"; // ou récupéré dynamiquement si besoin
        
        Boolean isValid = restTemplate.getForObject(
            "http://localhost:8080/api/billing/validate?agencyName=" + agencyName,
            Boolean.class
        );

        if (Boolean.FALSE.equals(isValid)) {
            throw new RuntimeException("Agence non abonnée ou abonnement expiré !");
        }

        return reservationRepository.save(reservation);
    }     
}
