package com.backdrop.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountName;
    @Column(unique = true)
    private String accountNumber;
    private String bankCode;
    private Boolean isVerified;
}
