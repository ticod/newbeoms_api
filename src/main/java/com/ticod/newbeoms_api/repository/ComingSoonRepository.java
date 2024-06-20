package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.ComingSoon;
import com.ticod.newbeoms_api.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComingSoonRepository extends JpaRepository<ComingSoon, Long> {

    List<ComingSoon> findByPublication(Publication publication);

}
