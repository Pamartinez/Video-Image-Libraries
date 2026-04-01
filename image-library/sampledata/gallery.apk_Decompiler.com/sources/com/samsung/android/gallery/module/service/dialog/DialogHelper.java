package com.samsung.android.gallery.module.service.dialog;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DialogHelper {
    private Blackboard mBlackboard = null;
    private int mPercentCount = 0;
    private int mProgressCount = 0;

    public boolean checkUpdateCondition(int i2, int i7) {
        if (this.mPercentCount < i7 && i7 <= 100) {
            this.mPercentCount = i7;
            return true;
        } else if (this.mProgressCount >= i2) {
            return false;
        } else {
            this.mProgressCount = i2;
            return true;
        }
    }

    public void dismissDialog() {
        Blackboard.publishGlobal("command://DismissDialog", (Object) null);
    }

    public void init(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        this.mPercentCount = 0;
        this.mProgressCount = 0;
    }

    public void postEvent(String str) {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.post(str, (Object) null);
        } else {
            Log.w("DialogHelper", "blackboard is not initialized");
        }
    }

    public void showDialog(String str, int i2, int i7, int i8) {
        showDialog(str, i2, i7, i8, false, false);
    }

    public abstract void showDialog(String str, int i2, int i7, int i8, boolean z, boolean z3);

    public abstract void showFileOperationDialog(String str, String str2, String str3);

    public abstract void showRenameDialog(String str);

    public abstract void updateDialog(int i2, int i7, int i8);
}
