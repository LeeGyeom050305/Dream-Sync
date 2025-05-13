package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_blocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "blocker_id", nullable = false)
    private UserEntity blocker;

    @ManyToOne
    @JoinColumn(name = "blocked_id", nullable = false)
    private UserEntity blocked;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // @PrePersist로 createdAt 자동 관리
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
