package com.samsung.android.gallery.module.dynamicview;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaybackOption {
    public final int mDurationMs;
    public final int mEndMs;
    public boolean mIsPreOrPostAction = false;
    public final int mRelativeDurationMs;
    public final float mSpeed;
    public final int mStartMs;

    public PlaybackOption(int i2, int i7, float f) {
        this.mStartMs = i2;
        this.mEndMs = i7;
        this.mSpeed = f;
        int i8 = i7 - i2;
        this.mDurationMs = i8;
        this.mRelativeDurationMs = (int) (((float) i8) / f);
    }

    public void setPreOrPostAction(boolean z) {
        this.mIsPreOrPostAction = z;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(this.mStartMs);
        sb2.append("-");
        sb2.append(this.mEndMs);
        sb2.append(Log.TAG_SEPARATOR);
        sb2.append(this.mSpeed);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mIsPreOrPostAction) {
            str = "T";
        } else {
            str = "F";
        }
        return C0212a.p(sb2, str, ")");
    }
}
