package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "gossip_link", schema = "newbeoms_db")
public class GossipLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gossip_id", nullable = false)
    private Gossip gossip;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    @Builder
    public GossipLink(Gossip gossip, String link) {
        this.gossip = gossip;
        this.link = link;
    }
}