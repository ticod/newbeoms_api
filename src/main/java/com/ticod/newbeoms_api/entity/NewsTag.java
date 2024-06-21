package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "news_tag", schema = "newbeoms_db",
        uniqueConstraints = @UniqueConstraint(name="UniqueNewsAndTag", columnNames = {"news_id", "tag_id"})
)
public class NewsTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Builder
    public NewsTag(News news, Tag tag) {
        this.news = news;
        this.tag = tag;
    }
}