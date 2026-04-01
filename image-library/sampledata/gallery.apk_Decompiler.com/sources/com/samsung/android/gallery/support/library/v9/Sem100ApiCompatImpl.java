package com.samsung.android.gallery.support.library.v9;

import N2.j;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaTranscodeCompat;
import com.samsung.android.gallery.support.library.v0.Sem95ApiCompatImpl;
import com.samsung.android.gallery.support.library.v9.media.Sec100MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v9.media.Sem100MediaTranscodeCompat;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem100ApiCompatImpl extends Sem95ApiCompatImpl {
    public Intent createClipboardIntent(String str, String str2) {
        Intent intent = new Intent("com.samsung.android.content.clipboard.action.ADD_CLIP");
        try {
            intent.addFlags(32);
            intent.putExtra("type", 2);
            intent.putExtra(FileApiContract.Parameter.PATH, str2);
            intent.putExtra("darkTheme", true);
            return intent;
        } catch (Exception e) {
            j.C(e, new StringBuilder("createClipboardIntent failed e="), this.TAG);
            return intent;
        }
    }

    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new Sec100MediaPlayerCompat(i2);
    }

    public void disableViewRoundedCorner(View view) {
        try {
            view.semSetRoundedCorners(0);
        } catch (NoSuchMethodError | NullPointerException e) {
            C0212a.y(e, new StringBuilder("semSetRoundedCorners failed e="), this.TAG);
        }
    }

    public KeepStorage getKeepStorage() {
        return new KeepStorage100Impl();
    }

    public MediaTranscodeCompat getMediaTranscodeCompat() {
        return new Sem100MediaTranscodeCompat();
    }

    public void performHaptic(Vibrator vibrator, int i2) {
        vibrator.vibrate(VibrationEffect.semCreateWaveform(HapticFeedbackConstants.semGetVibrationIndex(i2), -1, VibrationEffect.SemMagnitudeType.TYPE_TOUCH));
    }

    public void performHapticFeedback(Context context, int i2) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 1) {
                Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
                if (vibrator != null) {
                    performHaptic(vibrator, i2);
                }
            }
        } catch (NoSuchMethodError | NullPointerException | SecurityException e) {
            C0212a.y(e, new StringBuilder("performHapticFeedback failed e="), this.TAG);
        }
    }

    public boolean requestAccessibilityFocus(View view) {
        if (view == null) {
            return false;
        }
        try {
            return view.semRequestAccessibilityFocus();
        } catch (Exception | NoSuchMethodError e) {
            C0212a.y(e, new StringBuilder("fail to requestAccessibilityFocus"), this.TAG);
            return false;
        }
    }

    public void setButtonShapeEnabled(TextView textView) {
        try {
            textView.semSetButtonShapeEnabled(true);
        } catch (NoSuchMethodError | NullPointerException e) {
            C0212a.y(e, new StringBuilder("semSetRoundedCorners failed e="), this.TAG);
        }
    }

    public void setViewRoundedCorner(View view, int i2) {
        try {
            view.semSetRoundedCorners(i2);
        } catch (NoSuchMethodError | NullPointerException e) {
            C0212a.y(e, new StringBuilder("semSetRoundedCorners failed e="), this.TAG);
        }
    }

    public void setViewRoundedCornerColor(View view, int i2, int i7) {
        try {
            view.semSetRoundedCornerColor(i2, i7);
        } catch (NoSuchMethodError | NullPointerException e) {
            C0212a.y(e, new StringBuilder("semSetRoundedCornerColor failed e="), this.TAG);
        }
    }
}
