package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Publication;
import com.ticod.newbeoms_api.entity.WorkCited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkCitedRepository extends JpaRepository<WorkCited, Long> {
    List<WorkCited> findByPublication(Publication publication);
}