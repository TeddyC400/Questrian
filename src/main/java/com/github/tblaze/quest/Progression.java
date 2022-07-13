package com.github.tblaze.quest;

import net.minestom.server.entity.Player;

import java.util.function.Consumer;

public interface Progression {

    float getProgress();

    void setProgress(float value);

    void increment(float amount);

    /**
     * Returns true if the progress hits 100%
     *
     * @return boolean
     */
    boolean isCompleted();

    /**
     * Returns percentage of float value
     *
     * @return String
     */
    String getPercentage();

    void setCompletion(Consumer<Player> reward);

    void onCompletion(Player player);

}
