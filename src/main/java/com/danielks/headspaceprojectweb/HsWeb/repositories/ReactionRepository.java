package com.danielks.headspaceprojectweb.HsWeb.repositories;

import com.danielks.headspaceprojectweb.HsWeb.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
    UUID countByEntityId(UUID entityId);
}

