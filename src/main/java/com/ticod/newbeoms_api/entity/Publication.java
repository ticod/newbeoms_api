package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "publication", schema = "newbeoms_db")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Builder
    public Publication(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}