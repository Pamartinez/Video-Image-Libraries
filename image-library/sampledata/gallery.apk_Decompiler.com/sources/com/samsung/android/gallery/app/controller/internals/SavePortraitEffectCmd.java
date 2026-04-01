package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import O3.w;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SavePortraitEffectCmd extends BaseCommand {
    private void handleSaveAsCopy(MediaItem mediaItem, String str, boolean z) {
        handleSefData(mediaItem, str);
        MediaScannerConnection.scanFile(getContext(), new String[]{str}, (String[]) null, new w(this, z));
    }

    private void handleSefData(MediaItem mediaItem, String str) {
        if (mediaItem.isBurstShot()) {
            removeSefInfo(str, SefInfo.BURST_SHOT_INFO.keyName);
            removeSefInfo(str, SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
        } else if (mediaItem.isSingleTakenShot()) {
            removeSefInfo(str, SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
            removeSefInfo(str, SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSaveAsCopy$3(boolean z, String str, Uri uri) {
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        if (z) {
            ThreadUtil.postOnUiThread(new a(23, this, uri));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem mediaItem) {
        lambda$handleSaveAsCopy$2(ContentUri.getUri(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(MediaItem mediaItem, Bundle bundle, boolean z) {
        Uri uri;
        updatePortraitData(mediaItem);
        if (bundle != null) {
            uri = (Uri) bundle.get("RESULT_FILE_PATH");
        } else {
            uri = null;
        }
        if (uri != null) {
            handleSaveAsCopy(mediaItem, uri.toString(), z);
        } else if (z) {
            getBlackboard().postEvent(EventMessage.obtain(101));
            ThreadUtil.postOnUiThread(new a(22, this, mediaItem));
        }
    }

    private void removeSefInfo(String str, String str2) {
        try {
            SeApiCompat.getSefFileCompat().deleteData(new SecureFile(str), str2);
        } catch (IOException unused) {
            Log.e(this.TAG, "removeSefInfo failed");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showSnackBar */
    public void lambda$handleSaveAsCopy$2(Uri uri) {
        new ShowSnackBarForViewerCmd().execute(getHandler(), uri, null);
    }

    private void updatePortraitData(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(mediaItem.getFileId()));
        SuggestedHelper.getInstance().updatePortraitData(getContext(), arrayList, true);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        Bundle bundle = objArr[1];
        boolean booleanValue = objArr[2].booleanValue();
        if (mediaItem == null) {
            Log.d(this.TAG, "portrait data update failed : null mediaItem");
        } else {
            ThreadUtil.postOnBgThread(new F8.a((Object) this, (Object) mediaItem, (Object) bundle, booleanValue, 4));
        }
    }
}
