package com.samsung.android.gallery.app.controller.internals;

import J6.c;
import M4.j;
import N9.d;
import O3.A;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewLiveEffectCmd extends BaseCommand {
    private void checkAndUpdateFileInfo(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.getFileSize() == 0) {
            Log.d(this.TAG, "updateFileInfo -> file size is 0");
            if (mediaItem.getPath() != null) {
                mediaItem.setFileSize(FileUtils.length(mediaItem.getPath()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$2(MediaItem mediaItem, Consumer consumer) {
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).adjustInSampleSize(BitmapSizeHolder.size()));
        String viewerBitmapKey = MediaItemUtil.getViewerBitmapKey(mediaItem);
        getBlackboard().publish(viewerBitmapKey, decodedBitmap);
        Log.d(this.TAG, "loadBitmap#decode", viewerBitmapKey, Logger.toString(decodedBitmap));
        consumer.accept(decodedBitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(MediaItem mediaItem, boolean z, boolean z3, Bitmap bitmap) {
        ViewLiveEffectCmd viewLiveEffectCmd;
        try {
            viewLiveEffectCmd = this;
            try {
                ThreadUtil.runOnUiThread(new d(viewLiveEffectCmd, mediaItem, bitmap, z, z3, 2));
            } catch (Exception e) {
                e = e;
                Log.e((CharSequence) viewLiveEffectCmd.TAG, "execute failed", (Throwable) e);
            }
        } catch (Exception e7) {
            e = e7;
            viewLiveEffectCmd = this;
            Log.e((CharSequence) viewLiveEffectCmd.TAG, "execute failed", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$startLiveEffectActivity$3(View view) {
        if (view.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    private void loadBitmap(MediaItem mediaItem, Consumer<Bitmap> consumer) {
        Bitmap bitmap = (Bitmap) getBlackboard().read(MediaItemUtil.getViewerBitmapKey(mediaItem));
        if (bitmap != null) {
            consumer.accept(bitmap);
        } else {
            SimpleThreadPool.getInstance().execute(new c(this, mediaItem, consumer, 21));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: startLiveEffectActivity */
    public void lambda$onExecute$0(MediaItem mediaItem, Bitmap bitmap, boolean z, boolean z3) {
        Activity activity = getActivity();
        String str = "data://intent/gen-portrait/" + mediaItem.getComplexHashCode();
        Blackboard.getApplicationInstance().publish(str, new Object[]{mediaItem, bitmap});
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.portrait.LiveEffectActivity");
        intent.putExtra("data-key", str);
        intent.putExtra("caller-blackboard", activity.toString());
        intent.putExtra("system-no-status-bar", SystemUi.isInNoStatusBarMode(activity));
        intent.putExtra("system-status-bar-color", SystemUi.getStatusBarColor(activity));
        intent.putExtra("auto-save", z);
        intent.putExtra("show-init-progress", z3);
        ArrayList arrayList = new ArrayList();
        View view = (View) getParameter("app_transition_view");
        if (view != null) {
            Optional.of(view).filter(new j(16)).ifPresent(new B8.d(arrayList, 12));
        }
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, (Pair[]) arrayList.toArray(new Pair[0])).toBundle());
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_VIEWING_LIVE_EFFECT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr != null && objArr.length >= 3) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem instanceof MediaItem) {
                MediaItem mediaItem2 = mediaItem;
                boolean booleanValue = objArr[1].booleanValue();
                boolean booleanValue2 = objArr[2].booleanValue();
                checkAndUpdateFileInfo(mediaItem2);
                loadBitmap(mediaItem2, new A(this, mediaItem2, booleanValue, booleanValue2));
                return;
            }
        }
        String str = this.TAG;
        if (objArr == null) {
            objArr = new Object[]{"null"};
        }
        Log.e((CharSequence) str, "onExecute failed. wrong argument", objArr);
    }
}
