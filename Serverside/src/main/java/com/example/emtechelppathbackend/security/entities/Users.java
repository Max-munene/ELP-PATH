package com.example.emtechelppathbackend.security.entities;

import com.example.emtechelppathbackend.entities.Events;
import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.entities.userProfile.*;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_details", uniqueConstraints = {
        @UniqueConstraint(columnNames = "scholarNumber"),
        @UniqueConstraint(columnNames = "userEmail")
})
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String scholarNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userEmail;

    private String userPassword;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_roles")
    private Role role;

    //  overriding userdetails methods

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Education> education = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Feeds> feeds = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Events> events = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Career> careers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Hub> hubs = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JoinColumn(name = "social_id")
    private SocialMedia socialMedia;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JoinColumn(name = "bio_id")
    private Bio bio;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JoinColumn(name = "image_id")
    private Image image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return this.role.getPermissions().stream()
////                .map(permission -> new SimpleGrantedAuthority(permission.name()))
////                .toList();
//        return role.getAuthorities();
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}


