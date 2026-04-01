package com.samsung.android.sdk.mobileservice.social.feedback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Emotion {
    public static final int EMOTION_TYPE_LIKE = 0;
    public static final int EMOTION_TYPE_NONE = -1;
    private int mCount;
    private int mEmotionType;

    public Emotion(int i2, int i7) {
        this.mEmotionType = i2;
        this.mCount = i7;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getEmotionType() {
        return this.mEmotionType;
    }
}
