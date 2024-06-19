package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.Publication;
import com.ticod.newbeoms_api.entity.WorkCited;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkCitedDto {

    private String link;

    @Builder
    public WorkCitedDto(String link) {
        this.link = link;
    }

    public static WorkCitedDto from(WorkCited workCited) {
        return WorkCitedDto.builder()
                .link(workCited.getLink())
                .build();
    }

    public WorkCited toEntity(Publication publication) {
        return WorkCited.builder()
                .publication(publication)
                .link(this.link)
                .build();
    }
}
