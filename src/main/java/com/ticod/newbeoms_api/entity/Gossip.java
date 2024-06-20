package com.ticod.newbeoms_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "gossip", schema = "newbeoms_db")
public class Gossip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @OneToMany(mappedBy = "gossip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GossipLink> gossipLinks = new ArrayList<>();

    @Builder
    public Gossip(Publication publication, String title) {
        this.publication = publication;
        this.title = title;
    }
}