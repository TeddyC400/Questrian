package com.github.tblaze.entity;

import com.github.tblaze.quest.Quest;

public interface QuestEntity {

    default void addQuest(Quest quest) {
        QuestTracker.add(this, quest);
    }

    default void removeQuest(Quest quest) {
        QuestTracker.remove(this, quest);
    }

}
