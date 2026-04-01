package com.samsung.android.gallery.module.story.transcode.config;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KenBurnsInfo {
    Property[] mProperties = new Property[3];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Property {
        public float mDelay = 0.0f;
        public float mInitAlpha = 1.0f;
        private float mInitScaleX = 1.0f;
        private float mInitScaleY = 1.0f;
        public float mInitTranslateX = 0.0f;
        public float mInitTranslateY = 0.0f;
        public float mPivotX = 0.5f;
        public float mPivotY = 0.5f;
        private float mProgress;
        public float mTargetAlpha = 1.0f;
        public float mTargetScaleX = 1.0f;
        public float mTargetScaleY = 1.0f;
        public float mTargetTranslateX = 0.0f;
        public float mTargetTranslateY = 0.0f;

        private float getRelativeProgress() {
            float f = this.mProgress;
            float f5 = this.mDelay;
            if (f > f5) {
                return (f - f5) / (1.0f - f5);
            }
            return 0.0f;
        }

        public float getAlpha() {
            float f = this.mInitAlpha;
            return ((this.mTargetAlpha - f) * getRelativeProgress()) + f;
        }

        public float getDelay() {
            return this.mDelay;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getProgress() {
            return getRelativeProgress();
        }

        public float getScaleX() {
            float f = this.mInitScaleX;
            return ((this.mTargetScaleX - f) * getRelativeProgress()) + f;
        }

        public float getScaleY() {
            float f = this.mInitScaleY;
            return ((this.mTargetScaleY - f) * getRelativeProgress()) + f;
        }

        public float getTranslateX() {
            float f = this.mInitTranslateX;
            return ((this.mTargetTranslateX - f) * getRelativeProgress()) + f;
        }

        public float getTranslateY() {
            float f = this.mInitTranslateY;
            return ((this.mTargetTranslateY - f) * getRelativeProgress()) + f;
        }

        public Property setDelay(float f) {
            this.mDelay = f;
            return this;
        }

        public Property setInitAlpha(float f) {
            this.mInitAlpha = f;
            return this;
        }

        public Property setInitScaleX(float f) {
            this.mInitScaleX = f;
            return this;
        }

        public Property setInitScaleY(float f) {
            this.mInitScaleY = f;
            return this;
        }

        public Property setInitTranslateX(float f) {
            this.mInitTranslateX = -f;
            return this;
        }

        public Property setInitTranslateY(float f) {
            this.mInitTranslateY = -f;
            return this;
        }

        public Property setProgress(float f) {
            this.mProgress = f;
            return this;
        }

        public Property setTargetAlpha(float f) {
            this.mTargetAlpha = f;
            return this;
        }

        public Property setTargetScaleX(float f) {
            this.mTargetScaleX = f;
            return this;
        }

        public Property setTargetScaleY(float f) {
            this.mTargetScaleY = f;
            return this;
        }

        public Property setTargetTranslateX(float f) {
            this.mTargetTranslateX = -f;
            return this;
        }

        public Property setTargetTranslateY(float f) {
            this.mTargetTranslateY = -f;
            return this;
        }

        public String toString() {
            return "SX(" + this.mInitScaleX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetScaleX + "),SY(" + this.mInitScaleY + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetScaleY + "),TX(" + this.mInitTranslateX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetTranslateX + "),TY(" + this.mInitTranslateY + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetTranslateY + "),A(" + this.mInitAlpha + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTargetAlpha + "),P(" + this.mPivotX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mPivotY + "),D(" + this.mDelay + ")";
        }
    }

    private Property getProperty(int i2) {
        Property[] propertyArr = this.mProperties;
        if (propertyArr[i2] == null) {
            propertyArr[i2] = new Property();
        }
        return this.mProperties[i2];
    }

    public Property getTransformProperty() {
        return getProperty(0);
    }

    public Property getTransitionInProperty() {
        return getProperty(1);
    }

    public Property getTransitionOutProperty() {
        return getProperty(2);
    }

    public String toString() {
        return "transform=" + this.mProperties[0] + "||transitionIn=" + this.mProperties[1] + "||transitionOut=" + this.mProperties[2];
    }
}
