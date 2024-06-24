package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.HardwareNews;
import com.ticod.newbeoms_api.entity.Publication;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HardwareNewsDto {

    private final String content;
    private final String link;

    @Builder
    public HardwareNewsDto(String content, String link) {
        this.content = content;
        this.link = link;
    }

    public static HardwareNewsDto from(HardwareNews hardwareNews) {
        return HardwareNewsDto.builder()
                .content(hardwareNews.getContent())
                .link(hardwareNews.getLink())
                .build();
    }

    public static HardwareNews toEntity(HardwareNewsDto hardwareNewsDto, Publication publication) {
        return HardwareNews.builder()
                .publication(publication)
                .content(hardwareNewsDto.getContent())
                .link(hardwareNewsDto.getLink())
                .build();
    }

}
