package cm.amcloud.platform.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import cm.amcloud.platform.model.Invitation;
import cm.amcloud.platform.repository.InvitationRepository;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public Invitation createInvitation(String email) {
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setToken(UUID.randomUUID().toString()); // Génération token unique
        invitation.setExpirationDate(LocalDateTime.now().plusDays(3)); // Expire après 3 jours
        invitation.setAccepted(false);
        return invitationRepository.save(invitation);
    }

    public boolean validateInvitation(String token) {
        return invitationRepository.findByToken(token)
                .filter(invitation -> !invitation.isAccepted() && invitation.getExpirationDate().isAfter(LocalDateTime.now()))
                .map(invitation -> {
                    invitation.setAccepted(true);
                    invitationRepository.save(invitation);
                    return true;
                }).orElse(false);
    }
}
