package com.samsung.android.gallery.bixby.cmd.abstraction;

import A4.I;
import A9.c;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdResultBuilder;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.chain.Chain;
import g8.a;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseCmd implements Chain<BaseCmd> {
    protected static String mAction;
    protected static int mTaskId;
    protected static Uri mUri;
    protected final String TAG = getClass().getSimpleName();
    protected final RequestResult$CmdResultBuilder mBuilder = new RequestResult$CmdResultBuilder();
    private BaseCmd mNext;

    /* access modifiers changed from: private */
    public boolean findMatched(Map.Entry<String, Blackboard> entry) {
        if (BlackboardUtils.readActivity(entry.getValue()) == null || entry.getKey().contains("com.samsung.android.gallery.app.activity.external.GalleryExternalActivity")) {
            return false;
        }
        return true;
    }

    private void finishGalleryActivity() {
        Blackboard.getBlackboardMap().forEach(new c(6));
    }

    public static void init(Uri uri, int i2) {
        mUri = uri;
        String lastPathSegment = uri.getLastPathSegment();
        mAction = lastPathSegment;
        mTaskId = i2;
        if (lastPathSegment == null) {
            throw new IllegalStateException("null action is passed.");
        }
    }

    private boolean isActivityCreated() {
        return Blackboard.getBlackboardMap().entrySet().stream().anyMatch(new I(28, this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$finishGalleryActivity$0(String str, Blackboard blackboard) {
        Activity readActivity;
        if (str.contains("com.samsung.android.gallery.app.activity.GalleryActivity") && (readActivity = BlackboardUtils.readActivity(blackboard)) != null) {
            if (readActivity.isInMultiWindowMode()) {
                ThreadUtil.postOnBgThreadDelayed(new a(readActivity, 0), 500);
            } else {
                readActivity.finish();
            }
        }
    }

    private void setLaunchOverTargetTask(Intent intent) {
        int i2 = mTaskId;
        if (i2 != -1) {
            SeApiCompat.setLaunchOverTargetTask(intent, i2, false);
        }
    }

    private void startGalleryActivity(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
            intent.addFlags(PropertyOptions.DELETE_EXISTING);
            setLaunchOverTargetTask(intent);
            context.startActivity(intent);
            Log.bx(this.TAG, "startGalleryActivity");
        } catch (ActivityNotFoundException e) {
            Log.e((CharSequence) this.TAG, "startGalleryActivity failed", (Throwable) e);
        }
    }

    public abstract void execute(Context context);

    public BaseCmd get() {
        if (support()) {
            return this;
        }
        BaseCmd baseCmd = this.mNext;
        if (baseCmd != null) {
            return baseCmd.get();
        }
        return null;
    }

    public Intent getDefaultIntent() {
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    public void startGalleryExternalActivity(Context context, Intent intent, boolean z) {
        if (!z) {
            finishGalleryActivity();
        } else if (!isActivityCreated()) {
            startGalleryActivity(context);
        }
        try {
            intent.addFlags(335544320);
            intent.putExtra("from_bixby", true);
            context.startActivity(intent);
            Log.bx(this.TAG, "startGalleryExternalActivity");
        } catch (ActivityNotFoundException e) {
            Log.e((CharSequence) this.TAG, "startGalleryExternalActivity failed", (Throwable) e);
        }
    }

    public abstract boolean support();

    public void setNext(BaseCmd baseCmd) {
        this.mNext = baseCmd;
    }
}
