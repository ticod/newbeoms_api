package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.HardwareNews;
import com.ticod.newbeoms_api.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HardwareNewsRepository extends JpaRepository<HardwareNews, Long> {
    List<HardwareNews> findByPublication(Publication publication);
}
