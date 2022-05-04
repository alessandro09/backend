package br.com.texo.testback.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.texo.testback.backend.entities.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
	
}
