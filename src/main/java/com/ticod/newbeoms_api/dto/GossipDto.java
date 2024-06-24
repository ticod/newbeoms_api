package com.ticod.newbeoms_api.dto;

import com.ticod.newbeoms_api.entity.Gossip;
import com.ticod.newbeoms_api.entity.GossipLink;
import com.ticod.newbeoms_api.entity.Publication;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static GossipDto from(Gossip gossip) {
        return GossipDto.builder()
                .title(gossip.getTitle())
                .links(gossip.getGossipLinks().stream().map(GossipLink::getLink).toList())
                .build();
    }

    public static Gossip toGossip(GossipDto gossipDto, Publication publication) {
        return Gossip.builder()
                .publication(publication)
                .title(gossipDto.getTitle())
                .build();
    }

    public static List<GossipLink> toGossipLinks(GossipDto gossipDto, Gossip gossip) {
        return gossipDto.getLinks().stream()
                .map(link -> GossipLink.builder()
                        .gossip(gossip)
                        .link(link)
                        .build())
                .toList();
    }

    public static Map<Gossip, List<GossipLink>> toEntityMap(GossipDto gossipDto, Publication publication) {
        Gossip gossip = toGossip(gossipDto, publication);
        List<GossipLink> gossipLinks = toGossipLinks(gossipDto, gossip);

        Map<Gossip, List<GossipLink>> result = new HashMap<>();
        result.put(gossip, gossipLinks);
        return result;
    }

}
