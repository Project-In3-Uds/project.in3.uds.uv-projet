package cm.amcloud.platform.controller;

import cm.amcloud.platform.model.Reservation;
import cm.amcloud.platform.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }
}
