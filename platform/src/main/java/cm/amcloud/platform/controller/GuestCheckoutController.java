package cm.amcloud.platform.controller;

import cm.amcloud.platform.model.GuestSession;
import cm.amcloud.platform.service.GuestSessionService;
import cm.amcloud.platform.service.ReservationService;
import cm.amcloud.platform.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestCheckoutController {

    private final GuestSessionService guestSessionService;
    private final ReservationService reservationService;

    public GuestCheckoutController(GuestSessionService guestSessionService, ReservationService reservationService) {
        this.guestSessionService = guestSessionService;
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public Reservation reserveAsGuest(@RequestParam String email,
                                       @RequestParam String passengerName,
                                       @RequestParam String destination,
                                       @RequestParam String date) {
        // Créer une GuestSession
        guestSessionService.createGuestSession(email);

        // Enregistrer la réservation
        Reservation reservation = new Reservation();
        reservation.setPassengerName(passengerName);
        reservation.setDestination(destination);
        reservation.setDate(LocalDate.parse(date)); // Format attendu yyyy-MM-dd
        return reservationService.save(reservation);
    }

    @PostMapping("/register")
    public String convertGuestToAccount(@RequestParam String email) {
        Optional<GuestSession> guestOpt = guestSessionService.findByEmail(email);

        if (guestOpt.isPresent() && guestSessionService.isSessionValid(guestOpt.get())) {
            guestSessionService.markAsConverted(guestOpt.get());
            return "Guest session converted successfully. You can now register.";
        } else {
            return "Session invalid or expired.";
        }
    }
}
