package com.github.tblaze.quest;

import java.util.function.Supplier;

public interface Requirement {

    boolean meetsInitialRequirements();

    boolean meetsCompletionRequirements();

    /**
     * Adds a condition that must be met in order
     * for the quest to be assigned
     */
    void addInitialRequirement(Supplier<Boolean> condition);

    /**
     * Adds a condition that must be met in order
     * for the quest to be completed
     */
    void addCompletionRequirement(Supplier<Boolean> condition);

}
