package com.samsung.android.gallery.app.controller.album;

import D7.g;
import Fb.h;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateNamedFolderCmd extends BaseCommand {
    private String mError = null;
    private MediaItem mMediaItem;
    private String mParsedGroupName = null;
    private Uri mUri;

    private void createEmptyFolder(String str) {
        String str2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isFolder()) {
            str2 = str;
            AlbumHelper.getInstance().addNewEmptyFolder(str2, 1000000000000000007L);
        } else {
            str2 = str;
            AlbumHelper.getInstance().addNewEmptyFolder(this.mMediaItem.getFolderID(), this.mMediaItem.getFolderName(), str2, 1000000000000000007L);
        }
        refreshFolderInfo();
        getBlackboard().post("data://usernew_item_creation", new Object[]{str2, null, -1, null});
    }

    /* access modifiers changed from: private */
    public void decideExecution() {
        String queryParameter = this.mUri.getQueryParameter("KEY_GROUP_NAME");
        if (isInvalidGroupName(queryParameter)) {
            showFolderNameDialog();
        } else {
            createEmptyFolder(queryParameter);
        }
    }

    private boolean isInvalidGroupName(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.bxe(this.TAG, "unable to create group. group name is invalid");
            this.mParsedGroupName = null;
            return true;
        } else if (str.length() > 50) {
            Log.bxe(this.TAG, "unable to create group. max group name exceed");
            this.mParsedGroupName = str.substring(0, 50);
            this.mError = getApplicationContext().getResources().getQuantityString(R.plurals.more_than_limit, 50, new Object[]{50});
            return true;
        } else if (AlbumHelper.getInstance().checkDirectoriesFolderExist(str)) {
            Log.bxe(this.TAG, "unable to create group. same group name exist");
            this.mParsedGroupName = str;
            this.mError = getApplicationContext().getString(R.string.duplicate_group_name);
            return true;
        } else {
            this.mParsedGroupName = str;
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null) {
            Log.bxe(this.TAG, "unable to create group, cancel or unexpected option selected");
            return;
        }
        try {
            createEmptyFolder((String) ((Object[]) arrayList.get(0))[0]);
        } catch (ClassCastException e) {
            String str = this.TAG;
            Log.bxe(str, "unable to create group, unexpected result delivered. " + e.getMessage());
        }
    }

    private void refreshFolderInfo() {
        PreferenceCache.FolderNameIndex.incrementAndGet();
        Blackboard.postGlobal("data://useradd_items_to_folder", (Object) null);
    }

    private void showFolderNameDialog() {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/FolderName").appendArg("screenId", getScreenId()).appendArg("title", this.mParsedGroupName).appendArg("error", this.mError).build()).setOnDataCompleteListener(new h(5, this)).start();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        Uri uri = objArr[1];
        this.mUri = uri;
        if (uri == null) {
            Log.bxe(this.TAG, "unable to create group, null data");
        } else {
            SimpleThreadPool.getInstance().execute(new g(29, this));
        }
    }
}
