package com.samsung.android.gallery.app.controller.album;

import D7.g;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateNamedAlbumCmd extends CreateAlbumCmd {
    private final String DEFAULT_PATH = (StorageInfo.getDefault().album + File.separator);
    private String mError = null;
    private MediaItem mMediaItem;
    private String mParsedAlbumName = null;
    private Uri mUri;

    private boolean checkInvalidName(String str) {
        if (TextUtils.isEmpty(str) || str.equals(".") || str.equals("..")) {
            return true;
        }
        return false;
    }

    private boolean checkNameExists(String str) {
        ErrorType checkNameExist = AlbumHelper.getInstance().checkNameExist((this.DEFAULT_PATH + str).trim(), getAlbumType());
        if (checkNameExist.isCreateAlbumErrorCase()) {
            this.mError = getApplicationContext().getString(checkNameExist.getStringId());
        }
        return !checkNameExist.isNone();
    }

    /* access modifiers changed from: private */
    public void decideExecution() {
        String parsedName = getParsedName(this.mUri.getQueryParameter("KEY_ALBUM_NAME"));
        if (isInvalidAlbumName(parsedName)) {
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem == null || !mediaItem.isFolder()) {
                super.onExecute(getHandler(), 0, null, Boolean.TRUE);
            } else {
                super.onExecute(getHandler(), Integer.valueOf(this.mMediaItem.getFolderID()), this.mMediaItem.getFolderName(), Boolean.TRUE);
            }
        } else {
            MediaItem mediaItem2 = this.mMediaItem;
            if (mediaItem2 != null && mediaItem2.isFolder()) {
                this.mFolderId = this.mMediaItem.getFolderID();
                this.mFolderName = this.mMediaItem.getFolderName();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Object[]{parsedName, C0212a.p(new StringBuilder(), this.DEFAULT_PATH, parsedName), Boolean.TRUE});
            createAlbum(getHandler(), arrayList);
        }
    }

    private int getAlbumType() {
        AlbumType albumType;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            albumType = mediaItem.getAlbumType();
        } else {
            albumType = AlbumType.None;
        }
        return albumType.toInt();
    }

    private String getParsedName(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("\n")) {
            return str;
        }
        return str.replaceAll("\n", " ");
    }

    private boolean isInvalidAlbumName(String str) {
        if (checkInvalidName(str)) {
            Log.bxe(this.TAG, "unable to create album. album name is invalid");
            this.mParsedAlbumName = null;
            return true;
        } else if (str.length() > 50) {
            Log.bxe(this.TAG, "unable to create album. max album name exceed");
            this.mParsedAlbumName = str.substring(0, 50);
            this.mError = getApplicationContext().getResources().getQuantityString(R.plurals.more_than_limit, 50, new Object[]{50});
            return true;
        } else if (checkNameExists(str)) {
            Log.bxe(this.TAG, "unable to create album. same album name exist");
            this.mParsedAlbumName = str;
            return true;
        } else {
            this.mParsedAlbumName = str;
            return false;
        }
    }

    public String getTargetKey(EventContext eventContext) {
        return new UriBuilder("data://user/dialog/AlbumName").appendArg("screenId", eventContext.getScreenId()).appendArg("title", this.mParsedAlbumName).appendArg("error", this.mError).build();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        Uri uri = objArr[1];
        this.mUri = uri;
        if (uri == null) {
            Log.bxe(this.TAG, "unable to create album, null data");
        } else {
            SimpleThreadPool.getInstance().execute(new g(28, this));
        }
    }
}
