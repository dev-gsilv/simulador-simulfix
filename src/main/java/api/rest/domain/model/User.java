package api.rest.domain.model;

import jakarta.persistence.*;

@Entity(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(unique = true)
    String email;
    String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Investment investment;

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", investment=" + investment +
                '}';
    }
}