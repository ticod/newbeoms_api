package com.ticod.newbeoms_api.repository;

import com.ticod.newbeoms_api.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Publication findPublicationByPublicationDate(LocalDate date);

}
