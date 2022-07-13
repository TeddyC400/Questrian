package com.github.tblaze.entity;

import com.github.tblaze.quest.Quest;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks map of entities with assigned quests
 */
public class QuestTracker {

    private static Map<QuestEntity, Quest> map;

    static {
        map = new HashMap<>();
    }

    public static Quest get(QuestEntity entity) {
        return map.get(entity);
    }

    public static void add(QuestEntity entity, Quest quest) {
        map.put(entity, quest);
    }

    public static void remove(QuestEntity entity) {
        map.remove(entity);
    }

    public static void remove(QuestEntity entity, Quest quest) {
        map.remove(entity, quest);
    }



}
