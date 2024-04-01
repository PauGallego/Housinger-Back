package net.paugallego.housinger.model.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Data
public class UserEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable=false)
    private Boolean enableAccount;

    @CreatedDate
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Column(nullable = true)
    private LocalDateTime deleteTime;

    @Column(nullable = true)
    private String reasonForExpiredAccount;

    private LocalDateTime lastPasswordChange;
    private LocalDateTime nextPasswordChange;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RoleEnum> roles;

    @OneToOne(mappedBy = "userEntity")
    private CustomerEntity customerEntity;


    public UserEntity() {
        super();
        this.setEnableAccount(false);
        this.roles = new HashSet<>();
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.lastPasswordChange = LocalDateTime.now();
        this.nextPasswordChange = LocalDateTime.now().plus(Period.ofYears(1));
    }

    public UserEntity(String username, Collection<? extends RoleEnum> authorities) {
        this.username = username;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.roles = new HashSet<>();
        this.lastPasswordChange = LocalDateTime.now();
        this.nextPasswordChange = LocalDateTime.now().plus(Period.ofYears(1));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        Boolean expired = true;
        if (deleteTime != null) {
            expired = this.deleteTime.isAfter(LocalDateTime.now());
        }
        this.reasonForExpiredAccount = "Account needed to be activated";

        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enableAccount;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        boolean expired = false;
        if (deleteTime != null) {
            expired = true;
            this.reasonForExpiredAccount = "Account Deleted";

        } else {
            expired = this.lastPasswordChange.isBefore(this.nextPasswordChange);
            if (expired) {
                this.reasonForExpiredAccount = "Password Expired. Please, recover your password.";
            }

        }
        return expired;
    }

    @Override
    public boolean isEnabled() {
        if (!this.enableAccount) {
            this.reasonForExpiredAccount = "Account not active. Please check your email.";
        }

        return this.enableAccount;
    }

}
