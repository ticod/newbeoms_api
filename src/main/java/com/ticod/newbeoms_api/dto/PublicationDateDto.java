package com.ticod.newbeoms_api.dto;

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

}
