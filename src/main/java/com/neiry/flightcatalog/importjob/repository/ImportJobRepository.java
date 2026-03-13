package com.neiry.flightcatalog.importjob.repository;

import com.neiry.flightcatalog.importjob.entity.ImportJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportJobRepository extends JpaRepository<ImportJob, Long> {
}