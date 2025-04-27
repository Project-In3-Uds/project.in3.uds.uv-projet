package cm.amcloud.platform.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
 

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    //add agencyName
    private String agencyName;
    private String planType;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Getters and Setters
    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for roles
    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }
    // Getter for id
    public Long getId() {
        return id;
    }
    // Getter for agencyName
    public String getAgencyName() {
        return agencyName;
    }
    // Getter for planType
    public String getPlanType() {
        return planType;
    }
    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
    // Setter for roles
    public Set<Role> getRolesSet() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
