package com.github.tblaze.quest;

public class QuestPercent extends Quest {

    private float progress;

    protected QuestPercent(Builder builder) {
        super(builder);
        this.progress = builder.progress;
    }

    public static Builder create() {
        return new Builder();
    }

    public static Builder create(int id) {
        return new Builder(id);
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

        protected Builder() {
            super();
        }

        protected Builder(int id) {
            super(id);
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
