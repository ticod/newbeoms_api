package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Gossip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GossipRepository extends JpaRepository<Gossip, Long> {
}
