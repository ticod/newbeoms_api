package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByContentContaining(String content);
    Tag findByContent(String content);

}
