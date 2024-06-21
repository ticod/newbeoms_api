package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.News;
import com.ticod.newbeoms_api.entity.NewsTag;
import com.ticod.newbeoms_api.entity.Publication;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class NewsDto {

    private String title;
    private String subtitle;
    private String content;
    private LocalDate date;
    private String imagePath;
    private List<String> tags;

    @Builder
    public NewsDto(String title, String subtitle, String content,
                   LocalDate date, String imagePath, List<String> tags) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.date = date;
        this.imagePath = imagePath;
        this.tags = tags;
    }

    public static NewsDto from(News news) {
        return NewsDto.builder().title(news.getTitle())
                .subtitle(news.getSubtitle())
                .content(news.getContent())
                .imagePath(news.getImagePath())
                .date(news.getNewsDate())
                .build();
    }

    public static News toEntity(NewsDto newsDto, Publication publication) {
        return News.builder()
                .publication(publication)
                .title(newsDto.getTitle())
                .subtitle(newsDto.getSubtitle())
                .content(newsDto.getContent())
                .imagePath(newsDto.getImagePath())
                .newsDate(newsDto.getDate())
                .build();
    }

}
