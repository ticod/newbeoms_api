package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.NewsTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTagRepository extends JpaRepository<NewsTag, Long> {
}
