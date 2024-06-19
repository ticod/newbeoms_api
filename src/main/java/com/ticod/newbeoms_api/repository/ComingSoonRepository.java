package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.ComingSoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComingSoonRepository extends JpaRepository<ComingSoon, Long> {
}
