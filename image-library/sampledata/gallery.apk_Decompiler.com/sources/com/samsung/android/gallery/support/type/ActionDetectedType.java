package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ActionDetectedType {
    BLOWING(Integer.valueOf(R$string.blowing)),
    CUTTING(Integer.valueOf(R$string.cutting)),
    DRINKING(Integer.valueOf(R$string.drinking)),
    EATING(Integer.valueOf(r1)),
    FEEDING_PET(Integer.valueOf(r2)),
    HOLDING(Integer.valueOf(R$string.holding)),
    HOLDING_BABY(Integer.valueOf(R$string.holding_baby)),
    HUGGING_PET(Integer.valueOf(R$string.hugging_pet)),
    JUMPING(Integer.valueOf(R$string.jumping)),
    KICKING(Integer.valueOf(R$string.kicking)),
    LYING_DOWN(Integer.valueOf(r3)),
    PLAYING_INSTRUMENT(Integer.valueOf(R$string.playing_instrument)),
    PLAYING_WITH_TOYS(Integer.valueOf(R$string.playing_with_toys)),
    READING(Integer.valueOf(R$string.reading)),
    RIDING(Integer.valueOf(R$string.riding)),
    SITTING(Integer.valueOf(r4)),
    THROWING(Integer.valueOf(R$string.throwing)),
    CATCHING(Integer.valueOf(R$string.catching)),
    WALKING_PET(Integer.valueOf(R$string.walking_pet)),
    WRITING(Integer.valueOf(r5)),
    DRAWING(Integer.valueOf(r5)),
    DANCING(Integer.valueOf(R$string.dancing)),
    FEEDING(Integer.valueOf(r2)),
    SHAKING_HANDS(Integer.valueOf(R$string.shaking_hands)),
    HIGH_FIVING(Integer.valueOf(R$string.high_fiving)),
    CARRYING(Integer.valueOf(R$string.carrying)),
    HUGGING(Integer.valueOf(R$string.hugging)),
    KISSING(Integer.valueOf(R$string.kissing)),
    EATING_PET(Integer.valueOf(r1)),
    PLAYING_PET(Integer.valueOf(R$string.playing)),
    SITTING_PET(Integer.valueOf(r4)),
    LYING_DOWN_PET(Integer.valueOf(r3)),
    NOT_DEFINED(Integer.valueOf(R$string.action));
    
    private static final String TAG = "ActionDetectedType";
    private final Integer mTitleResId;

    private ActionDetectedType(Integer num) {
        this.mTitleResId = num;
    }

    public static Integer getTitleResId(String str) {
        try {
            return valueOf(str.toUpperCase()).mTitleResId;
        } catch (IllegalArgumentException | NullPointerException unused) {
            Log.w(TAG, "getTitleResId : invalid key = " + str);
            return NOT_DEFINED.getTitleResId();
        }
    }

    public Integer getTitleResId() {
        return this.mTitleResId;
    }
}
