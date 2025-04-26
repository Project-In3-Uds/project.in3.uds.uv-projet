package cm.amcloud.platform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class GuestSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private LocalDateTime expirationDate;

    private boolean isConvertedToAccount = false;

    // Getters & Setters
    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDateTime expirationDate) { this.expirationDate = expirationDate; }

    public boolean isConvertedToAccount() { return isConvertedToAccount; }
    public void setConvertedToAccount(boolean convertedToAccount) { isConvertedToAccount = convertedToAccount; }
}
