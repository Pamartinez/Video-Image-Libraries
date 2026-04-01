package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PanoramaShotHandler extends AbsShotModeHandler {
    static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_LIVE_EFFECT);

    public PanoramaShotHandler() {
        this.mScaleToMin = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeInternal$0(EventContext eventContext, String str) {
        startActivity(eventContext.getActivity(), str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeInternal$1(MediaItem mediaItem, EventContext eventContext, Bitmap bitmap) {
        try {
            String str = "data://intent/panorama/" + mediaItem.getComplexHashCode();
            Blackboard.getApplicationInstance().publish(str, new Object[]{mediaItem, bitmap});
            ThreadUtil.runOnUiThread(new a(this, eventContext, str, 1));
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_PANORAMA_VIEWER.toString());
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "execute failed", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$2(MediaItem mediaItem, Blackboard blackboard, Consumer consumer) {
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).adjustInSampleSize(BitmapSizeHolder.size()));
        String viewerBitmapKey = MediaItemUtil.getViewerBitmapKey(mediaItem);
        blackboard.publish(viewerBitmapKey, decodedBitmap);
        Log.d(this.TAG, "loadBitmap#decode", viewerBitmapKey, Logger.toString(decodedBitmap));
        consumer.accept(decodedBitmap);
    }

    private void loadBitmap(Blackboard blackboard, MediaItem mediaItem, Consumer<Bitmap> consumer) {
        Bitmap bitmap = (Bitmap) blackboard.read(MediaItemUtil.getViewerBitmapKey(mediaItem));
        if (bitmap != null) {
            consumer.accept(bitmap);
        } else {
            SimpleThreadPool.getInstance().execute(new c(this, mediaItem, blackboard, consumer));
        }
    }

    private void startActivity(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.plugins.panorama.PanoramaActivity");
        intent.putExtra("data-key", str);
        intent.putExtra("caller-blackboard", activity.toString());
        intent.putExtra("system-no-status-bar", SystemUi.isInNoStatusBarMode(activity));
        intent.putExtra("system-status-bar-color", SystemUi.getStatusBarColor(activity));
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, (Pair<View, String>[]) null).toBundle());
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        loadBitmap(eventContext.getBlackboard(), mediaItem, new b(this, mediaItem, eventContext));
    }

    public int getTitleId() {
        return R.string.view_panorama;
    }

    public String getType() {
        return "panorama";
    }

    public boolean isEditable() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        if (SUPPORT && mediaItem.isShotMode("panorama") && !mediaItem.isPrivateItem()) {
            int orientation = mediaItem.getOrientation();
            if (orientation == 0 || orientation == 180) {
                if (mediaItem.getWidth() > mediaItem.getHeight()) {
                    return true;
                }
            } else if (mediaItem.getHeight() > mediaItem.getWidth()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean supportUpsm() {
        return false;
    }
}
