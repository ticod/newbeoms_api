package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.News;
import com.ticod.newbeoms_api.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByPublication(Publication publication);

}
