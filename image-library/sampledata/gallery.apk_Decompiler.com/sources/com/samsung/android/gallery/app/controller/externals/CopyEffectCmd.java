package com.samsung.android.gallery.app.controller.externals;

import M5.a;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CopyEffectCmd extends BaseCommand {
    /* access modifiers changed from: private */
    /* renamed from: copyFilterAndTone */
    public void lambda$onExecute$0(MediaItem mediaItem) {
        int i2;
        boolean z = false;
        try {
            if (!isPackageInstalled(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) {
                Log.e(this.TAG, "copy failed, photo editor is not installed");
                guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
                return;
            }
            String sefString = SeApiCompat.getSefString(mediaItem.getPath(), SefInfo.PE_RE_EDIT_DATA.keyName);
            if (TextUtils.isEmpty(sefString)) {
                Log.e(this.TAG, "unable to get filter and tone data");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("data", sefString);
            Bundle call = getContext().getContentResolver().call(LocalDatabase.URI, "clipboard://update", "filter", bundle);
            if (call != null && call.getInt("result") == 0) {
                z = true;
            }
            consumeExecuteListener(Boolean.valueOf(z));
            Context context = getContext();
            if (z) {
                i2 = R.string.edits_copied;
            } else {
                i2 = R.string.could_not_copy_edits;
            }
            Utils.showToast(context, i2);
            getBlackboard().postEvent(EventMessage.obtain(1003));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "saveFilterAndToneData failed", (Throwable) e);
        }
    }

    private boolean isInvalidItem(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.hasFilterAndTone() || mediaItem.isVideo() || mediaItem.isCloudOnly() || mediaItem.isDrm() || !FileUtils.exists(mediaItem.getPath())) {
            return true;
        }
        return false;
    }

    public Long getAnalyticsValue() {
        return 1L;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_COPY_EFFECT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        if (mediaItemArr == null || mediaItemArr.length < 1) {
            Log.e(this.TAG, "invalid data");
            return;
        }
        MediaItem mediaItem = mediaItemArr[0];
        if (isInvalidItem(mediaItem)) {
            Log.e((CharSequence) this.TAG, "invalid source", mediaItem);
        } else {
            SimpleThreadPool.getInstance().execute(new a(4, this, mediaItem));
        }
    }
}
