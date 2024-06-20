package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class PublicationDto {

    private LocalDate publicationDate;
    private List<NewsDto> newsDtoList;
    private List<ComingSoonDto> comingSoonDtoList;
    private List<GossipDto> gossipDtoList;
    private List<HardwareNewsDto> hardwareNewsDtoList;
    private List<WorkCitedDto> workCitedDtoList;

    @Builder
    public PublicationDto(LocalDate publicationDate, List<NewsDto> newsDtoList,
                          List<ComingSoonDto> comingSoonDtoList,
                          List<GossipDto> gossipDtoList,
                          List<HardwareNewsDto> hardwareNewsDtoList,
                          List<WorkCitedDto> workCitedDtoList) {
        this.publicationDate = publicationDate;
        this.newsDtoList = newsDtoList;
        this.comingSoonDtoList = comingSoonDtoList;
        this.gossipDtoList = gossipDtoList;
        this.hardwareNewsDtoList = hardwareNewsDtoList;
        this.workCitedDtoList = workCitedDtoList;
    }

    public static PublicationDto of(Publication publication,
                                    List<NewsDto> newsDtoList,
                                    List<ComingSoonDto> comingSoonDtoList,
                                    List<GossipDto> gossipDtoList,
                                    List<HardwareNewsDto> hardwareNewsDtoList,
                                    List<WorkCitedDto> workCitedDtoList) {

        return PublicationDto.builder()
                .publicationDate(publication.getPublicationDate())
                .newsDtoList(newsDtoList)
                .comingSoonDtoList(comingSoonDtoList)
                .gossipDtoList(gossipDtoList)
                .hardwareNewsDtoList(hardwareNewsDtoList)
                .workCitedDtoList(workCitedDtoList)
                .build();

    }

}
