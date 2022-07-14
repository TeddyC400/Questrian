package com.github.tblaze.quest;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Quest implements Requirement, Progression {

    private static Map<Integer, Quest> questMap;

    private int id;
    private Component title;
    private List<Component> description;

    private List<Supplier<Boolean>> initialRequirements;
    private List<Supplier<Boolean>> completionRequirements;
    private Consumer<Player> completeFunc;

    static {
        questMap = new HashMap<>();
    }

    private Quest(int id) {
        questMap.put(id, this);
    }

    protected Quest(Builder builder) {
        this(builder.id);
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.initialRequirements = builder.initialConditions;
        this.completionRequirements = builder.completionConditions;
        this.completeFunc = builder.completeFunc;
    }

    public Quest get(int id) {
        return questMap.get(id);
    }

    public void remove(int id) {
        questMap.remove(id);
    }

    public void remove(Quest quest) {
        questMap.remove(quest.id);
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

        private int id;
        private Component title;
        private List<Component> description;
        private List<Supplier<Boolean>> initialConditions;
        private List<Supplier<Boolean>> completionConditions;
        private Consumer<Player> completeFunc;

        protected Builder() {
            this(new Random().nextInt(5000 - 1000 + 1) + 1000);
        }

        protected Builder(int id) {
            this.id = id;
            this.description = new ArrayList<>();
            this.initialConditions = new ArrayList<>();
            this.completionConditions = new ArrayList<>();
        }

        public T id(int id) {
            this.id = id;
            return (T) this;
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
