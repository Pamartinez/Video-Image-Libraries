package com.samsung.android.gallery.widget.dialog;

import M3.C0406b;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiProgressDialogCompat {
    private final String TAG = "AiProcessing";
    private ProgressAvdCompat mAvdDialog;
    private final Blackboard mBlackboard;
    private boolean mIsOverlayViewState = true;
    private AlertDialog mTransparentDialog;
    private final boolean mUseEffectLayout;

    public AiProgressDialogCompat(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        this.mUseEffectLayout = Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT);
    }

    private void show(Context context, int i2, Runnable runnable) {
        C0406b bVar;
        if (runnable != null) {
            bVar = new C0406b(3, runnable);
        } else {
            bVar = null;
        }
        if (this.mUseEffectLayout) {
            this.mBlackboard.postEvent(EventMessage.obtain(3075, i2, Boolean.valueOf(this.mIsOverlayViewState)));
            AlertDialog create = new ProgressCircleBuilder(context).setProgressMessage("").setFlexMode(false).removeCircle().setCancelListener(bVar).removeDimBehind().create();
            this.mTransparentDialog = create;
            create.show();
            return;
        }
        ProgressAvdCompat build = new ProgressAvdCompat(context).setOnCancelListener(bVar).setProgressMessage(i2).setProgressMessage(i2).build();
        this.mAvdDialog = build;
        build.show();
    }

    public void dismissAiProgress() {
        AlertDialog alertDialog;
        try {
            Log.d("AiProcessing", "dismiss");
            this.mBlackboard.postEvent(EventMessage.obtain(3076));
            if (!this.mUseEffectLayout || (alertDialog = this.mTransparentDialog) == null) {
                ProgressAvdCompat progressAvdCompat = this.mAvdDialog;
                if (progressAvdCompat != null) {
                    progressAvdCompat.dismiss();
                    this.mAvdDialog = null;
                    return;
                }
                return;
            }
            alertDialog.dismiss();
            this.mTransparentDialog = null;
        } catch (IllegalArgumentException e) {
            Log.e("AiProcessing", "dismissAiProgress Failed" + e);
        }
    }

    public void setOverlayViewState(boolean z) {
        this.mIsOverlayViewState = z;
    }

    public void showAiProgress(Context context, int i2, Runnable runnable) {
        if (context != null) {
            Log.d("AiProcessing", "show");
            show(context, i2, runnable);
            return;
        }
        Log.e("AiProcessing", "fail show");
    }

    public void updateMessage(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (!this.mUseEffectLayout || this.mTransparentDialog == null) {
            ProgressAvdCompat progressAvdCompat = this.mAvdDialog;
            if (progressAvdCompat != null) {
                progressAvdCompat.updateMessage(i2);
                this.mAvdDialog.hideProgressBar();
                return;
            }
            return;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3073, Integer.valueOf(i2)));
    }

    public AiProgressDialogCompat(Blackboard blackboard, boolean z) {
        this.mBlackboard = blackboard;
        this.mUseEffectLayout = z;
    }
}
