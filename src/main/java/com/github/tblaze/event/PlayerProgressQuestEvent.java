package com.github.tblaze.event;

import com.github.tblaze.quest.Quest;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Event when the player progresses through a quest
 */
public class PlayerProgressQuestEvent implements QuestEvent {

    private final Player player;
    private final Quest quest;

    public PlayerProgressQuestEvent(@NotNull Player player, @NotNull Quest quest) {
        this.player = player;
        this.quest = quest;
    }

    @Override
    public @NotNull Quest getQuest() {
        return this.quest;
    }

    @Override
    public @NotNull Player getPlayer() {
        return this.player;
    }
}
