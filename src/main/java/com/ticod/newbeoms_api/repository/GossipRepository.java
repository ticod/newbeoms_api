package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Gossip;
import com.ticod.newbeoms_api.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GossipRepository extends JpaRepository<Gossip, Long> {
    List<Gossip> findByPublication(Publication publication);
}
