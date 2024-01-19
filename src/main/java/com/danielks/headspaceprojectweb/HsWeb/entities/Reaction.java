package com.danielks.headspaceprojectweb.HsWeb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private UUID entityId;
    private String entity_type;
    private String react_type;
    private LocalDate create_time;

    public Reaction() {
    }

    public Reaction(UUID id, User user, UUID entityId, String entity_type, String react_type, LocalDate create_time) {
        this.id = id;
        this.user = user;
        this.entityId = entityId;
        this.entity_type = entity_type;
        this.react_type = react_type;
        this.create_time = create_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction = (Reaction) o;
        return id.equals(reaction.id) && user.equals(reaction.user) && entityId.equals(reaction.entityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, entityId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getReact_type() {
        return react_type;
    }

    public void setReact_type(String react_type) {
        this.react_type = react_type;
    }

    public LocalDate getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDate create_time) {
        this.create_time = create_time;
    }
}
