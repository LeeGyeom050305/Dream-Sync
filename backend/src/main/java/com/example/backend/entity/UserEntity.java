package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 사용자 엔티티
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "role_type", length = 50)
    private String roleType;

    @Column(name = "delete_yn", length = 1)
    private String deleteYn;

    @Column(name = "insert_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime insertDate;

    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;
}
