package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "news", schema = "newbeoms_db")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "news_date", nullable = false)
    private LocalDate newsDate;

    @Column(name = "subtitle", nullable = false, length = 100)
    private String subtitle;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_path", nullable = false, length = 100)
    private String imagePath;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsTag> tagList = new ArrayList<>();

    @Builder
    public News(Publication publication, String title, LocalDate newsDate, String subtitle, String content, String imagePath) {
        this.publication = publication;
        this.title = title;
        this.newsDate = newsDate;
        this.subtitle = subtitle;
        this.content = content;
        this.imagePath = imagePath;
    }
}