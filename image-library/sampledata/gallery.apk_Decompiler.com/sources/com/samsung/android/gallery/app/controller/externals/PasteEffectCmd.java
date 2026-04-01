package com.samsung.android.gallery.app.controller.externals;

import M5.a;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.provider.LocalClipboard;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.PresetServiceBinder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteEffectCmd extends BaseCommand {
    private int mItemCount = 0;

    private Uri getGrantedUri(MediaItem mediaItem) {
        Uri uri = ContentUri.getUri(mediaItem);
        getContext().grantUriPermission(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, uri, 3);
        return uri;
    }

    private ArrayList<Uri> getUriList(MediaItem[] mediaItemArr) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null && !mediaItem.isPostProcessing()) {
                arrayList.add(getGrantedUri(mediaItem));
                ThumbnailLoader.getInstance().updateEffectMemCache(mediaItem, ThumbKind.MEDIUM_KIND);
            }
        }
        this.mItemCount = arrayList.size();
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: pasteFilterAndTone */
    public void lambda$onExecute$0(MediaItem[] mediaItemArr) {
        try {
            if (!isPackageInstalled(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME)) {
                Log.e(this.TAG, "paste failed, photo editor is not installed");
                guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            } else {
                ArrayList<Uri> uriList = getUriList(mediaItemArr);
                if (uriList.isEmpty()) {
                    Log.e(this.TAG, "paste failed, invalid uris");
                    Utils.showToast(getContext(), getContext().getString(R.string.unsupported_file));
                } else if (!LocalClipboard.isFilterAvailable()) {
                    Log.e(this.TAG, "paste failed, filter is not available");
                } else {
                    PresetServiceBinder.getInstance().onStart(getContext(), uriList);
                    postExecution();
                }
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "paste failed, error=", (Throwable) e);
        } catch (Throwable th) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
            throw th;
        }
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    private void postExecution() {
        if (LocationKey.isContentViewer(getHandler().getLocationKey())) {
            getBlackboard().post("command://event/DataDirty", (Object) null);
        }
        postAnalyticsLog();
    }

    public Long getAnalyticsValue() {
        return Long.valueOf((long) this.mItemCount);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_PASTE_EFFECT.toString();
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "invalid data");
        } else {
            SimpleThreadPool.getInstance().execute(new a(5, this, mediaItemArr));
        }
    }
}
