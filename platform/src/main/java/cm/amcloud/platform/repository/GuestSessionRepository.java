package cm.amcloud.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cm.amcloud.platform.model.GuestSession;

import java.util.Optional;

public interface GuestSessionRepository extends JpaRepository<GuestSession, Long> {
    Optional<GuestSession> findByEmail(String email);
}
