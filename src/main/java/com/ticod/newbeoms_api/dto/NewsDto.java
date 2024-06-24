package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Tag> toTags(NewsDto newsDto) {
        return newsDto.getTags().stream()
                .map(Tag::new)
                .toList();
    }

    public static Map<News, List<Tag>> toEntityMap(NewsDto newsDto, Publication publication) {
        News news = NewsDto.toEntity(newsDto, publication);
        List<Tag> tags = NewsDto.toTags(newsDto);

        Map<News, List<Tag>> result = new HashMap<>();
        result.put(news, tags);
        return result;
    }

}
