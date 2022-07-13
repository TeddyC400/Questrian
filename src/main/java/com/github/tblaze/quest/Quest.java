package com.github.tblaze.quest;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Quest implements Requirement, Progression {

    private static int idCounter;

    private int id;
    private Component title;
    private List<Component> description;

    private List<Supplier<Boolean>> initialRequirements;
    private List<Supplier<Boolean>> completionRequirements;
    private Consumer<Player> completeFunc;

    static {
        idCounter = 0;
    }

    protected Quest() {
        this.id = idCounter++;
        this.description = new ArrayList<>();

        this.initialRequirements = new ArrayList<>();
        this.completionRequirements = new ArrayList<>();
    }

    protected Quest(Builder builder) {
        this();
        this.title = builder.title;
        this.description = builder.description;
        this.initialRequirements = builder.initialConditions;
        this.completionRequirements = builder.completionConditions;
        this.completeFunc = builder.completeFunc;
    }

    @Override
    public boolean meetsInitialRequirements() {
        for (var cond : this.initialRequirements) {
            return cond.get();
        }

        return false;
    }

    @Override
    public boolean meetsCompletionRequirements() {
        for (var cond : this.completionRequirements) {
            return cond.get();
        }

        return false;
    }

    @Override
    public void addInitialRequirement(Supplier<Boolean> condition) {
        this.initialRequirements.add(condition);
    }

    @Override
    public void addCompletionRequirement(Supplier<Boolean> condition) {
        this.completionRequirements.add(condition);
    }

    @Override
    public void setCompletion(Consumer<Player> reward) {
        this.completeFunc = reward;
    }

    @Override
    public void onCompletion(Player player) {
        this.completeFunc.accept(player);
    }

    public int getId() {
        return this.id;
    }

    public Component getTitle() {
        return this.title;
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public List<Component> getDescription() {
        return this.description;
    }

    public void addDescription(Component text) {
        this.description.add(text);
    }

    public static class Builder<T extends Builder<T>> {

        private Component title;
        private List<Component> description;
        private List<Supplier<Boolean>> initialConditions;
        private List<Supplier<Boolean>> completionConditions;
        private Consumer<Player> completeFunc;

        public Builder() {
            this.description = new ArrayList<>();
            this.initialConditions = new ArrayList<>();
            this.completionConditions = new ArrayList<>();
        }

        public T title(Component title) {
            this.title = title;
            return (T) this;
        }

        public T desc(Component... description) {
            for (Component lore : description) {
                this.description.add(lore);
            }
            return (T) this;
        }

        /**
         * Must return true for the initial condition
         * to be met and start the quest
         *
         * @param condition
         * @return
         */
        public T initialCondition(Supplier<Boolean> condition) {
            this.initialConditions.add(condition);
            return (T) this;
        }

        /**
         * Must return true for the completion condition
         * to be met and finish the quest
         *
         * @param condition
         * @return
         */
        public T completionCondition(Supplier<Boolean> condition) {
            this.completionConditions.add(condition);
            return (T) this;
        }

        /**
         * When the quest is completed, then this
         * function is called with player argument
         *
         * @param completion
         * @return
         */
        public T onComplete(Consumer<Player> completion) {
            this.completeFunc = completion;
            return (T) this;
        }
    }

}
