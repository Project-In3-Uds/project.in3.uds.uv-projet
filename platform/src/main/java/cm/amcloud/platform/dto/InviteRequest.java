package cm.amcloud.platform.dto;

public class InviteRequest {
    private String email;

    // Constructeur vide nécessaire
    public InviteRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
