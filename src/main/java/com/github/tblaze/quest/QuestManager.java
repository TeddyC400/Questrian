package com.github.tblaze.quest;

import net.kyori.adventure.text.Component;

public class QuestManager {

    private QuestManager() { }

    public static void create(Quest quest) {
        QuestCounter c = QuestCounter.create()
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
        QuestCounter.create()
                .title(Component.text("Test"))
                .maximum(10)
                .build();
        QuestPercent.create(10)
                .build();
    }

}
