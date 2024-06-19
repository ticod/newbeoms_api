package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.Gossip;
import com.ticod.newbeoms_api.entity.GossipLink;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GossipDto {

    private String title;
    private List<String> links;

    @Builder
    public GossipDto(String title, List<String> links) {
        this.title = title;
        this.links = links;
    }

    public static GossipDto of(Gossip gossip, List<GossipLink> gossipLinks) {
        return GossipDto.builder()
                .title(gossip.getTitle())
                .links(gossipLinks.stream().map(GossipLink::getLink).toList())
                .build();
    }

}
