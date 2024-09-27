package uz.doublem.delevery_for_exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone_number;
    private String password;
    private String firstName;
    private String lastName;
    private Timestamp dateOfBirth;
    @CreationTimestamp
    private Timestamp createdAt;
    @Builder.Default
    private boolean isActive = false;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    private boolean isDeleted = false;
    private String code;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
