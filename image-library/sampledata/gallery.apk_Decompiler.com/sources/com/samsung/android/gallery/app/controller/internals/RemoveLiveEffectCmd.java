package com.samsung.android.gallery.app.controller.internals;

import Ba.h;
import M5.a;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.o3dp.lib3dphotography.O3DPhotoConfig;
import com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveLiveEffectCmd extends BaseCommand {
    private void executeInternal(MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new a(16, this, mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$executeInternal$0(FileItemInterface fileItemInterface) {
        return Boolean.valueOf(remove(fileItemInterface.getPath()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeInternal$1(int i2, int i7, int i8) {
        Log.d(this.TAG, "updated result : ", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeInternal$2(Integer num, Integer num2) {
        String str = this.TAG;
        Log.d(str, "scan requested: " + num + ", scanned: " + num2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeInternal$3(MediaItem mediaItem) {
        Log.d(this.TAG, "delete LiveEffect - E");
        FileRedactorBuilder.edit(new MediaItem[]{mediaItem}).setOperation(new A5.a(19, this)).setCallback(new K4.a(26, this)).withMediaScan(new h(8, this)).ignoreGroup().build().run();
        Log.d(this.TAG, "delete LiveEffect - X");
    }

    private boolean remove(String str) {
        O3DPhotoPipeline o3DPhotoPipeline = new O3DPhotoPipeline(getContext(), new O3DPhotoConfig());
        try {
            o3DPhotoPipeline.removeLiveEffect(str);
            o3DPhotoPipeline.release();
            Log.i(this.TAG, "Successfully removed live effect from image");
            return true;
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            Log.e((CharSequence) this.TAG, "Failed to remove live effect", e);
            o3DPhotoPipeline.release();
            return false;
        } catch (Throwable th) {
            o3DPhotoPipeline.release();
            throw th;
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_REMOVE_LIVE_EFFECT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getPath())) {
            Log.e(this.TAG, "Unable to remove live effect. item is null");
        } else {
            executeInternal(mediaItem);
        }
    }
}
