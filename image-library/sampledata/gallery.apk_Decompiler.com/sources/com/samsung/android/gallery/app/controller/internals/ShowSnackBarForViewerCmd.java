package com.samsung.android.gallery.app.controller.internals;

import Ba.g;
import J6.c;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowSnackBarForViewerCmd extends BaseCommand {
    private WeakReference<Activity> mActivityWeakRef;
    private String mEventId;
    private boolean mIsForcePlayVideoInGallery;
    private boolean mIsFromSuggestion;

    private void action(MediaItem mediaItem) {
        new VuLauncher(getBlackboard()).disableTimeline().addTrueArgument(this.mIsFromSuggestion, "from_suggestion_pictures").prepareThumbnail().forcePlayVideoInGallery(this.mIsForcePlayVideoInGallery).forceDisableTransition().launchSingle(mediaItem);
    }

    private String getDefaultContextText(MediaItem mediaItem) {
        int i2;
        String path = mediaItem.getPath();
        if (path == null) {
            return "";
        }
        String translatedDirectory = BucketUtils.getTranslatedDirectory(path);
        if (mediaItem.isImage()) {
            i2 = R.string.toast_image_saved_in;
        } else {
            i2 = R.string.video_saved_in;
        }
        return AppResources.getString(i2, translatedDirectory);
    }

    private boolean isFromVideo() {
        MediaItem currentItem = getHandler().getCurrentItem();
        if (currentItem == null || !currentItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$makeSnackBar$1(MediaItem mediaItem, View view) {
        action(mediaItem);
    }

    private MediaItem loadItem(Object obj) {
        QueryParams queryParams = new QueryParams(DbKey.ALL_PICTURES);
        if (obj instanceof Uri) {
            queryParams.addUri((Uri) obj);
        } else {
            if (obj instanceof String) {
                queryParams.setFilePath((String) obj);
            }
            return null;
        }
        Cursor query = DbCompat.query(queryParams);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    MediaItem create = MediaItemBuilder.create(query);
                    query.close();
                    return create;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return null;
        throw th;
    }

    private r makeSnackBar(MediaItem mediaItem, String str) {
        boolean z;
        boolean z3 = false;
        if (mediaItem == null || this.mActivityWeakRef.get() == null) {
            String str2 = this.TAG;
            if (mediaItem == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mActivityWeakRef.get() == null) {
                z3 = true;
            }
            Log.w((CharSequence) str2, "unable to make snack bar", valueOf, Boolean.valueOf(z3));
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            str = getDefaultContextText(mediaItem);
        }
        r j2 = r.j(this.mActivityWeakRef.get().findViewById(R.id.content), 0, 0, isFromVideo(), str);
        j2.k(j2.f2220h.getText(R.string.view), new g(7, this, mediaItem));
        return j2;
    }

    /* access modifiers changed from: private */
    /* renamed from: show */
    public void lambda$onExecute$0(Object obj, String str) {
        MediaItem loadItem = loadItem(obj);
        r makeSnackBar = makeSnackBar(loadItem, str);
        if (makeSnackBar != null) {
            makeSnackBar.l();
        } else {
            Log.w((CharSequence) this.TAG, "failed to load a mediaItem: ", MediaItemUtil.getSimpleLog(loadItem));
        }
    }

    public String getEventId() {
        return this.mEventId;
    }

    public boolean isAnalyticsEnabled() {
        if (this.mEventId != null) {
            return true;
        }
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Object obj = objArr[0];
        String str = objArr[1];
        if (objArr.length > 2) {
            this.mIsFromSuggestion = objArr[2].booleanValue();
        }
        if (objArr.length > 3) {
            this.mEventId = objArr[3];
        }
        if (objArr.length > 4) {
            this.mIsForcePlayVideoInGallery = objArr[4].booleanValue();
        }
        this.mActivityWeakRef = new WeakReference<>(getActivity());
        ThreadUtil.postOnBgThreadDelayed(new c(this, obj, str, 20), 500);
    }
}
