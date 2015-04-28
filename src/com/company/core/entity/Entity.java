package com.company.core.entity;

import com.company.core.utils.IdGenerator;

/**
 * Parent com.company.core.entity for all entities of the project
 *
 * @author Sofia Ruban
 */
public abstract class Entity {

    /**
     * Identifier com.company.core.entity
     */
    private String id;

    public Entity() {
        id = IdGenerator.generatorId();
    }

    public String getId() {
        return id;
    }
}
