package com.github.tblaze.entity;

import com.github.tblaze.quest.Quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks map of entities with assigned quests
 */
public class QuestTracker {

    private static Map<QuestEntity, List<Quest>> map;

    static {
        map = new HashMap<>();
    }

    public static List<Quest> get(QuestEntity entity) {
        return map.get(entity);
    }

    public static Quest get(QuestEntity entity, int questId) {
        for (Quest q : map.get(entity)) {
            if (q.getId() == questId) {
                return q;
            }
        }

        return null;
    }

    public static void add(QuestEntity entity, Quest quest) {
        List<Quest> quests = map.get(entity);
        if (quests == null)
            quests = new ArrayList<>();
        quests.add(quest);
    }

    public static void remove(QuestEntity entity) {
        map.remove(entity);
    }

    public static void remove(QuestEntity entity, Quest quest) {
        List<Quest> quests = map.get(entity);
        for (Quest q : quests) {
            if (q == quest) {
                quests.remove(q);
            }
        }
    }



}
