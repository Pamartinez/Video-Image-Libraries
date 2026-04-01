package com.samsung.android.sdk.mobileservice.social.feedback;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmotionList {
    private List<Emotion> mEmotions;
    private int mMyEmotionType;

    public EmotionList(int i2, List<Emotion> list) {
        this.mMyEmotionType = i2;
        this.mEmotions = list;
    }

    public List<Emotion> getEmotions() {
        return this.mEmotions;
    }

    public int getMyEmotionType() {
        return this.mMyEmotionType;
    }
}
