package com.samsung.android.gallery.module.abstraction;

import android.content.Context;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IdleWorkerJob {
    protected final String TAG = getClass().getSimpleName();
    private final int mJobId;
    protected final GalleryPreference mSaPreference = GalleryPreference.getInstanceAnalytics();
    protected final Type mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        IDLE,
        CHARGED_IDLE
    }

    public IdleWorkerJob(int i2, Type type) {
        this.mJobId = i2;
        this.mType = type;
    }

    public long duration() {
        return MediaApiContract.DAY_IN_MILLI;
    }

    public final boolean executable(long j2) {
        if (j2 - this.mSaPreference.loadLong(C0212a.p(new StringBuilder(), this.TAG, "#last"), 0) >= duration()) {
            return true;
        }
        return false;
    }

    public abstract void execute(Context context);

    public int getJobId() {
        return this.mJobId;
    }

    public boolean isChargingRequired() {
        if (this.mType == Type.CHARGED_IDLE) {
            return true;
        }
        return false;
    }

    public final void run(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        if (executable(currentTimeMillis)) {
            this.mSaPreference.saveState(C0212a.p(new StringBuilder(), this.TAG, "#last"), currentTimeMillis);
            execute(context);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        if (this.mType == Type.CHARGED_IDLE) {
            str = "(C):";
        } else {
            str = "(I):";
        }
        sb2.append(str);
        sb2.append(this.mJobId);
        return sb2.toString();
    }

    public boolean wantsReschedule() {
        return false;
    }
}
