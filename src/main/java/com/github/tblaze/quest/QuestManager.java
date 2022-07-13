package com.github.tblaze.quest;

import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;

public class QuestManager {

    private static Map<Integer, Quest> questMap;

    static {
        questMap = new HashMap<>();
    }

    private QuestManager() { }

    public static Quest get(int id) {
        return questMap.get(id);
    }

    public static void create(Quest quest) {
        QuestCounter c = new QuestCounter.Builder()
                .counter(0)
                .maximum(5)
                .title(Component.text("Test"))
                .initialCondition(() -> {
                    return true;
                })
                .completionCondition(() -> {
                    return false;
                })
                .onComplete(p -> {
                    p.sendMessage("LOL");
                })
                .build();
    }

}
