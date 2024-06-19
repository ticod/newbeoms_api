package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "hardware_news", schema = "newbeoms_db")
public class HardwareNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    @Builder
    public HardwareNews(Publication publication, String content, String link) {
        this.publication = publication;
        this.content = content;
        this.link = link;
    }
}