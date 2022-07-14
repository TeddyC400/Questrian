package com.github.tblaze.quest;

public class QuestCounter extends Quest {

    private int maximum;
    private int counter;

    protected QuestCounter(Builder builder) {
        super(builder);
        this.maximum = builder.maximum;
        this.counter = builder.counter;
    }

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(int id) {
        return new Builder(id);
    }

    @Override
    public float getProgress() {
        return this.counter;
    }

    @Override
    public void setProgress(float value) {
        this.counter = (int) value;
    }

    @Override
    public void increment(float amount) {
        this.counter += (int) amount;
    }

    @Override
    public boolean isCompleted() {
        return this.counter >= this.maximum;
    }

    @Override
    public String getPercentage() {
        return String.valueOf((float) this.counter / this.maximum) + "%";
    }

    public static class Builder extends Quest.Builder<Builder> {

        private int maximum;
        private int counter;

        protected Builder() {
            super();
        }

        protected Builder(int id) {
            super(id);
        }

        public Builder maximum(int maximum) {
            this.maximum = maximum;
            return this;
        }

        public Builder counter(int counter) {
            this.counter = counter;
            return this;
        }

        public QuestCounter build() {
            return new QuestCounter(this);
        }

    }
}
