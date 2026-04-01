package com.samsung.android.gallery.module.story.transcode.config;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameProperty implements Cloneable {
    /* access modifiers changed from: private */
    public float alpha = 1.0f;
    /* access modifiers changed from: private */
    public boolean inTransition;
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public float pivotX = 0.5f;
    /* access modifiers changed from: private */
    public float pivotY = 0.5f;
    /* access modifiers changed from: private */
    public float progress = 0.0f;
    /* access modifiers changed from: private */
    public float scaleX = 1.0f;
    /* access modifiers changed from: private */
    public float scaleY = 1.0f;
    /* access modifiers changed from: private */
    public float transX = 0.0f;
    /* access modifiers changed from: private */
    public float transY = 0.0f;

    public float getAlpha() {
        return this.alpha;
    }

    public int getIndex() {
        return this.index;
    }

    public float getPivotX() {
        return this.pivotX;
    }

    public float getPivotY() {
        return this.pivotY;
    }

    public float getProgress() {
        return this.progress;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public float getTransX() {
        return this.transX;
    }

    public float getTransY() {
        return this.transY;
    }

    public boolean inTransition() {
        return this.inTransition;
    }

    public String toString() {
        return "@index=" + this.index + ",A=" + this.alpha + " ,SX=" + this.scaleX + " ,SY=" + this.scaleY + " ,TX=" + this.transX + " ,TY=" + this.transY;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private final FrameProperty property;

        public Builder() {
            this.property = new FrameProperty();
        }

        public FrameProperty build() {
            return this.property;
        }

        public Builder setAlpha(float f) {
            this.property.alpha = f;
            return this;
        }

        public Builder setIndex(int i2) {
            this.property.index = i2;
            return this;
        }

        public Builder setPivotX(float f) {
            this.property.pivotX = f;
            return this;
        }

        public Builder setPivotY(float f) {
            this.property.pivotY = f;
            return this;
        }

        public Builder setProgress(float f) {
            this.property.progress = f;
            return this;
        }

        public Builder setScaleX(float f) {
            this.property.scaleX = f;
            return this;
        }

        public Builder setScaleY(float f) {
            this.property.scaleY = f;
            return this;
        }

        public Builder setTranslateX(float f) {
            this.property.transX = f;
            return this;
        }

        public Builder setTranslateY(float f) {
            this.property.transY = f;
            return this;
        }

        public Builder(FrameProperty frameProperty) {
            this.property = frameProperty.clone();
        }

        public Builder(boolean z) {
            this();
            this.property.inTransition = z;
        }
    }

    public FrameProperty clone() {
        try {
            return (FrameProperty) super.clone();
        } catch (CloneNotSupportedException unused) {
            return new FrameProperty();
        }
    }
}
