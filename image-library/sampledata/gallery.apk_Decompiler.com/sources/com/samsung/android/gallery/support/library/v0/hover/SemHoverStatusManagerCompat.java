package com.samsung.android.gallery.support.library.v0.hover;

import N2.j;
import android.content.Context;
import android.provider.Settings;
import com.samsung.android.gallery.support.library.abstraction.HoverStatusManagerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemHoverStatusManagerCompat extends HoverStatusManagerCompat {
    private static final String FINGER_AIR_VIEW = null;
    private static final String FINGER_AIR_VIEW_SOUND_AND_HAPTIC_FEEDBACK = null;
    private static final String MOUSE_HOVERING = null;
    private final boolean mIsAfwMode;

    public SemHoverStatusManagerCompat(boolean z) {
        this.mIsAfwMode = z;
    }

    private int getModeAirViewSoundAndHaptic(Context context, int i2) {
        if (isAirViewFeedback(context, i2)) {
            return 2;
        }
        return 0;
    }

    private int getSettingsInt(Context context, String str, int i2, boolean z) {
        if (!(context == null || str == null)) {
            try {
                return Settings.System.getInt(context.getContentResolver(), str, i2);
            } catch (Exception e) {
                j.C(e, new StringBuilder("getSettingsInt failed e="), "SemHoverStatusManagerCompat");
            }
        }
        return i2;
    }

    private boolean isAirViewFeedback(Context context, int i2) {
        int i7 = Settings.System.getInt(context.getContentResolver(), "deleted feature", 0);
        if (i7 == 1 || isFingerAirView(context)) {
            return isSettingEnabled(context, FINGER_AIR_VIEW_SOUND_AND_HAPTIC_FEEDBACK);
        }
        if (i7 == 0) {
            if (!isSettingEnabled(context, "spen_feedback_sound") || !isSettingEnabled(context, "spen_feedback_sound_air_view")) {
                return false;
            }
            return true;
        } else if (i7 != 2) {
            return isSettingEnabled(context, FINGER_AIR_VIEW_SOUND_AND_HAPTIC_FEEDBACK);
        } else {
            if (i2 == 2) {
                if (!isSettingEnabled(context, "spen_feedback_sound") || !isSettingEnabled(context, "spen_feedback_sound_air_view")) {
                    return false;
                }
                return true;
            } else if (i2 == 1) {
                return isSettingEnabled(context, FINGER_AIR_VIEW_SOUND_AND_HAPTIC_FEEDBACK);
            } else {
                return false;
            }
        }
    }

    private boolean isSettingEnabled(Context context, String str) {
        if (getSettingsInt(context, str, 0, this.mIsAfwMode) == 1) {
            return true;
        }
        return false;
    }

    public boolean isFingerAirView(Context context) {
        return isSettingEnabled(context, FINGER_AIR_VIEW);
    }

    public boolean isMouseHovering(Context context) {
        return isSettingEnabled(context, MOUSE_HOVERING);
    }

    public boolean isPenHovering(Context context) {
        return isSettingEnabled(context, "pen_hovering");
    }

    public void playHapticSound(Context context, int i2) {
        try {
            int modeAirViewSoundAndHaptic = getModeAirViewSoundAndHaptic(context, i2);
            if (modeAirViewSoundAndHaptic != 1) {
                if (modeAirViewSoundAndHaptic != 2) {
                    return;
                }
            }
            SoundEffect.play(context, 100);
        } catch (Exception e) {
            j.D(e, new StringBuilder("playHapticSound failed e="), "SemHoverStatusManagerCompat");
        }
    }
}
