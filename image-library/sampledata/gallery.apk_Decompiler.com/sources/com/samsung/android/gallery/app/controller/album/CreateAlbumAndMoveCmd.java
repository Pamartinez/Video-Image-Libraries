package com.samsung.android.gallery.app.controller.album;

import A4.C0387w;
import Fa.C0571z;
import H3.C0397b;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateAlbumAndMoveCmd extends BaseCommand {
    private int mAlbumId;
    private String mAlbumName;
    private String mAlbumPath;
    private boolean mIsEmptyAlbum;
    private boolean mIsNewAlbum;
    private MediaItem[] mItems;

    private boolean createDirectory() {
        return FileUtils.makeDirectoryIfAbsent(this.mAlbumPath);
    }

    private MediaItem[] getFilteredMediaItem() {
        return (MediaItem[]) Arrays.stream(this.mItems).filter(new C0571z(4)).toArray(new C0387w(7));
    }

    private boolean isValid(Uri uri) {
        this.mAlbumId = UnsafeCast.toInt(uri.getQueryParameter("KEY_ALBUM_ID"), -1);
        this.mAlbumPath = uri.getQueryParameter("KEY_PATH");
        this.mIsEmptyAlbum = UnsafeCast.toBoolean(uri.getQueryParameter("KEY_EMPTY_ALBUM"), false);
        boolean z = UnsafeCast.toBoolean(uri.getQueryParameter("KEY_NEW_ALBUM"), false);
        this.mIsNewAlbum = z;
        if ((this.mAlbumId != -1 || z) && !TextUtils.isEmpty(this.mAlbumPath)) {
            this.mAlbumName = new File(this.mAlbumPath).getName();
            return true;
        }
        String str = this.TAG;
        Log.bxe(str, "invalid data [" + this.mAlbumId + "][" + this.mIsNewAlbum + "][" + Logger.getEncodedString(this.mAlbumPath) + "]");
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getFilteredMediaItem$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToAlbum$1() {
        startService(this.mAlbumPath, true, false, -1);
    }

    /* access modifiers changed from: private */
    public void moveToAlbum() {
        String str = this.TAG;
        Log.bx(str, "move to album [" + this.mAlbumId + "][" + Logger.getEncodedString(this.mAlbumPath) + "]");
        if (!this.mIsNewAlbum) {
            startService(this.mAlbumPath, false, this.mIsEmptyAlbum, this.mAlbumId);
        } else if (!createDirectory()) {
            Log.bxe(this.TAG, "unable to create new album. directory creation failed.");
        } else if (AlbumHelper.getInstance().addNewEmptyAlbum(this.mAlbumPath, this.mAlbumName, 0, (String) null) != 1) {
            showErrorToast();
        } else {
            notifyNewAlbumCreated();
            ThreadUtil.postOnBgThreadDelayed(new C0397b(this, 1), 800);
        }
    }

    private void notifyNewAlbumCreated() {
        Blackboard.publishGlobal("data://usernew_item_creation", new Object[]{this.mAlbumName, this.mAlbumPath, 0, null});
        Blackboard.postEventGlobal(EventMessage.obtain(2004, FileUtils.getBucketId(this.mAlbumPath), new Object[]{Boolean.FALSE, this.mAlbumPath}));
    }

    private void showErrorToast() {
        if (StorageUtil.isLowStorage()) {
            Toast.makeText(getContext(), R.string.operation_failed_low_storage, 1).show();
        } else {
            Toast.makeText(getContext(), R.string.unable_to_create_album, 1).show();
        }
    }

    private void startService(String str, boolean z, boolean z3, int i2) {
        String str2 = this.TAG;
        Log.bx(str2, "start file op service [" + z + "][" + z3 + "]");
        FileOpCmdHelper.getInstance().startService(getHandler(), getFilteredMediaItem(), str, "move", z, i2, z3);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mItems = objArr[0];
        if (isValid(objArr[1])) {
            ThreadUtil.postOnBgThread(new C0397b(this, 0));
        }
    }
}
