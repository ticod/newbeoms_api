package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.News;
import com.ticod.newbeoms_api.entity.Publication;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class SearchNewsDto {

    private LocalDate publicationDate;
    private String title;
    private String subtitle;
    private String content;
    private LocalDate date;
    private String imagePath;

    @Builder
    public SearchNewsDto(LocalDate publicationDate, String title, String subtitle,
                         String content, LocalDate date, String imagePath) {
        this.publicationDate = publicationDate;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.date = date;
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchNewsDto newsDto = (SearchNewsDto) o;
        return Objects.equals(getTitle(), newsDto.getTitle()) && Objects.equals(getDate(), newsDto.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDate());
    }

    public static SearchNewsDto of(News news, Publication publication) {
        return SearchNewsDto.builder()
                .publicationDate(publication.getPublicationDate())
                .title(news.getTitle())
                .subtitle(news.getSubtitle())
                .content(news.getContent())
                .imagePath(news.getImagePath())
                .date(news.getNewsDate())
                .build();
    }
}
