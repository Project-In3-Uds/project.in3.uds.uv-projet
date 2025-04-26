package cm.amcloud.platform.controller;

import org.springframework.web.bind.annotation.*;

import cm.amcloud.platform.model.Invitation;
import cm.amcloud.platform.service.InvitationService;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping
    public String invite(@RequestParam String email) {
        Invitation invitation = invitationService.createInvitation(email);
        return invitation.getToken(); // Retourne le token UUID généré
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam String token) {
        return invitationService.validateInvitation(token);
    }
}
