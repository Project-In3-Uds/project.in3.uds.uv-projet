package cm.amcloud.platform.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import cm.amcloud.platform.model.Invitation;
import cm.amcloud.platform.repository.InvitationRepository;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final NotificationService notificationService;

    public InvitationService(InvitationRepository invitationRepository, NotificationService notificationService) {
        this.invitationRepository = invitationRepository;
        this.notificationService = notificationService;
    }

    public Invitation createInvitation(String email) {
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setToken(UUID.randomUUID().toString()); // Génération token unique
        invitation.setExpirationDate(LocalDateTime.now().plusDays(3)); // Expire après 3 jours
        invitation.setAccepted(false);

        Invitation savedInvitation = invitationRepository.save(invitation);

        // Envoyer l'email d'invitation
        notificationService.sendEmail(
            email,
            "Invitation à rejoindre la plateforme",
            "Vous êtes invité à rejoindre la plateforme. Veuillez valider votre invitation avec le lien envoyé."
        );

        return savedInvitation;
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
