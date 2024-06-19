package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "coming_soon_content", schema = "newbeoms_db")
public class ComingSoonContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coming_soon_id", nullable = false)
    private ComingSoon comingSoon;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Builder
    public ComingSoonContent(ComingSoon comingSoon, String content) {
        this.comingSoon = comingSoon;
        this.content = content;
    }
}