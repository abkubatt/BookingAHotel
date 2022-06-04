package com.example.bookinghotel.models.entities;

import com.example.bookinghotel.models.enums.ERole;
import com.example.bookinghotel.models.enums.StatusOfUser;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "tb_user")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank
    @Size(max = 25)
    String name;
    @NotBlank
    @Size(max = 50)
    @Email
    String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    StatusOfUser statusOfUser;


}
