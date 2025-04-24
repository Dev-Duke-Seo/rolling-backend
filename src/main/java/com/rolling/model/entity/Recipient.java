package com.rolling.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.enums.ColorType;

@Entity
@Table(name = "recipients")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ColorType backgroundColor;

    @Column(nullable = true)
    private String backgroundImageURL;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Reaction> reactions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


    public static Recipient fromDto(RecipientCreateDto recipientDto) {
        ColorType colorType = ColorType.fromValue(recipientDto.getBackgroundColor());

        Recipient recipient = new Recipient();
        recipient.name = recipientDto.getName();
        recipient.backgroundColor = colorType;
        recipient.backgroundImageURL = recipientDto.getBackgroundImageURL();
        recipient.createdAt = LocalDateTime.now();
        return recipient;
    }
}
