package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "work_cited", schema = "newbeoms_db")
public class WorkCited {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    @Builder
    public WorkCited(Publication publication, String link) {
        this.publication = publication;
        this.link = link;
    }
}