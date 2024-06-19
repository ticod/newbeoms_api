package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "coming_soon", schema = "newbeoms_db")
public class ComingSoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "coming_soon_date", nullable = false)
    private LocalDate comingSoonDate;

    @Builder
    public ComingSoon(Publication publication, LocalDate comingSoonDate) {
        this.publication = publication;
        this.comingSoonDate = comingSoonDate;
    }

}