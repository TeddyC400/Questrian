package com.github.tblaze.entity;

import com.github.tblaze.quest.Quest;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.instance.Instance;

public interface QuestEntity {

    /**
     * Creates the entity in memory, but does
     * not spawn it.
     *
     * See {@link #spawn(Entity, Instance, Pos)}
     *
     * @param entity
     */
    void create(Entity entity);

    /**
     * Loads the entity in the world with the
     * specified instance and position
     *
     * First must be created with {@link #create(Entity)}
     * then this method can be executed
     *
     * @param entity
     * @param instance
     * @param position
     */
    void spawn(Entity entity, Instance instance, Pos position);

    /**
     * Unloads the entity from the world
     *
     * @param entity
     */
    void despawn(Entity entity);

    /**
     * Completely removes the entity from the
     * world and memory
     *
     * @param entity
     */
    void delete(Entity entity);

    void addQuest(Entity entity, Quest quest);

    void removeQuest(Entity entity, Quest quest);

}
