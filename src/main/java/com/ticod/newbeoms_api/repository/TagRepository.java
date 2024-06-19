package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
