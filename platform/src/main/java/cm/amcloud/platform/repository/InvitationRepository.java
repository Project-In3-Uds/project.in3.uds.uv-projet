package cm.amcloud.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cm.amcloud.platform.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByToken(String token);
}
