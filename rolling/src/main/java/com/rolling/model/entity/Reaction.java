package com.rolling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "reactions",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"recipient_id", "emoji"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    @ToString.Exclude
    private Recipient recipient;

    @Column(nullable = false)
    private String emoji;

    @Column(nullable = false)
    private Integer count;
}
