package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.Publication;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PublicationDateDto {

    private LocalDate date;

    public PublicationDateDto(LocalDate date) {
        this.date = date;
    }

    public static PublicationDateDto from(Publication publication) {
        return new PublicationDateDto(publication.getPublicationDate());
    }

}
