package com.github.tblaze.event;

import com.github.tblaze.quest.Quest;
import net.minestom.server.event.trait.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public interface QuestEvent extends PlayerEvent {

    /**
     * Gets the quest
     *
     * @return the quest
     */
    @NotNull Quest getQuest();

}
