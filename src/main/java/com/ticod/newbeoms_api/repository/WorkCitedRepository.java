package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.WorkCited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCitedRepository extends JpaRepository<WorkCited, Long> {
}