package com.github.tblaze.quest;

public class QuestPercent extends Quest {

    private float progress;

    public QuestPercent(Builder builder) {
        super(builder);
        this.progress = builder.progress;
    }

    @Override
    public float getProgress() {
        return this.progress;
    }

    @Override
    public void setProgress(float value) {
        this.progress = value;
    }

    @Override
    public void increment(float amount) {
        this.progress += amount;
    }

    @Override
    public boolean isCompleted() {
        return this.progress >= 1.0f;
    }

    @Override
    public String getPercentage() {
        return String.valueOf(this.progress * 100) + "%";
    }

    public static class Builder extends Quest.Builder<Builder> {

        private float progress;

        public Builder() {

        }

        public Builder progress(float progress) {
            this.progress = progress;
            return this;
        }

        public QuestPercent build() {
            return new QuestPercent(this);
        }
    }
}
