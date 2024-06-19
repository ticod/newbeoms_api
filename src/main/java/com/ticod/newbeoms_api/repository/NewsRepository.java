package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
