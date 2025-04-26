package cm.amcloud.platform.service;

import org.springframework.stereotype.Service;
import cm.amcloud.platform.model.GuestSession;
import cm.amcloud.platform.repository.GuestSessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GuestSessionService {

    private final GuestSessionRepository repository;

    public GuestSessionService(GuestSessionRepository repository) {
        this.repository = repository;
    }

    public GuestSession createGuestSession(String email) {
        GuestSession guest = new GuestSession();
        guest.setEmail(email);
        guest.setExpirationDate(LocalDateTime.now().plusHours(24)); // Session valable 24h
        return repository.save(guest);
    }

    public Optional<GuestSession> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean isSessionValid(GuestSession guestSession) {
        return guestSession.getExpirationDate().isAfter(LocalDateTime.now()) && !guestSession.isConvertedToAccount();
    }

    public GuestSession markAsConverted(GuestSession guestSession) {
        guestSession.setConvertedToAccount(true);
        return repository.save(guestSession);
    }
}
