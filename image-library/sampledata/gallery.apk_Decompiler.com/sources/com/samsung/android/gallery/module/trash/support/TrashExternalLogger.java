package com.samsung.android.gallery.module.trash.support;

import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashExternalLogger extends TrashLogger {
    private boolean mChanged = false;
    private int mExtra1 = 0;
    private int mExtra2 = 0;
    private Task mTask = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Task {
        MOUNTED,
        UNMOUNTED,
        SIGNED_OUT,
        TRASH_OFF,
        MIGRATION_TO_ONE_DRIVE,
        NONE
    }

    public TrashExternalLogger(Context context) {
    }

    public void dump(TrashLogType trashLogType, String str, boolean z) {
        if (this.mChanged) {
            DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
            deleteInstance.insertLog("[" + trashLogType + "]" + getDumpString());
        }
    }

    public String getDetail(boolean z) {
        return null;
    }

    public String getDumpString() {
        StringBuilder sb2 = new StringBuilder("[");
        sb2.append(this.mTask);
        sb2.append("][");
        sb2.append(this.mChanged);
        sb2.append("][");
        sb2.append(this.mExtra1);
        sb2.append("][");
        return C0086a.l(sb2, this.mExtra2, "]");
    }

    public void increaseExtra1(int i2) {
        this.mExtra1 += i2;
    }

    public void increaseExtra2() {
        this.mExtra2++;
    }

    public boolean isSucceed() {
        return true;
    }

    public void setChanged() {
        this.mChanged = true;
    }

    public void setTask(Task task) {
        this.mTask = task;
    }
}
