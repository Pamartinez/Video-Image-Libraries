package com.samsung.android.gallery.module.groupshot;

import O3.b;
import android.content.Context;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.extendedformat.SefFormat;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.scs.ai.language.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GroupShotFormat extends SefFormat {
    private OnUpdateCompletionListener mListener;
    private boolean mResult;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnUpdateCompletionListener {
    }

    private boolean updateDbBestItem(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        boolean z;
        if (skipDbUpdate()) {
            return true;
        }
        if (updateMediaBestImage(fileItemInterface, 1) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (fileItemInterface2 == null) {
            return z;
        }
        if (!z || updateMediaBestImage(fileItemInterface2, 0) <= 0) {
            return false;
        }
        return true;
    }

    public abstract FileItemInterface findBestItem(FileItemInterface fileItemInterface);

    public void handleSefInfo(FileItemInterface fileItemInterface, String str) {
        if (fileItemInterface.isRemasterSaved()) {
            removeSefInfo(str, SefInfo.ORIGINAL_PATH_HASH_KEY.keyName);
            removeSefInfo(str, SefInfo.PE_RE_EDIT_DATA.keyName);
            removeSefInfo(str, SefInfo.REMASTER_INFO.keyName);
        } else if (hasPortraitEffectSaved(fileItemInterface.getSefFileTypes())) {
            removeSefInfo(str, SefInfo.ORIGINAL_PATH_HASH_KEY.keyName);
            removeSefInfo(str, SefInfo.PE_RE_EDIT_DATA.keyName);
        }
    }

    public boolean hasPortraitEffectSaved(String str) {
        if (str == null || !str.contains(Integer.toString(3105))) {
            return false;
        }
        return true;
    }

    public void notifyCompletion() {
        notifyCompletion(true);
    }

    public Object[] saveAsNew(Context context, FileItemInterface fileItemInterface, double d, double d2, String str) {
        String path = fileItemInterface.getPath();
        if (path == null) {
            return null;
        }
        SecureFile secureFile = new SecureFile(path);
        File dstFile = getDstFile(secureFile, str, FileUtils.getExtension(path, true));
        if (!FileUtils.copy((File) secureFile, dstFile)) {
            return null;
        }
        handleSefInfo(fileItemInterface, dstFile.getPath());
        handleExifInfo(dstFile.getPath(), d, d2);
        scanFile(context, dstFile);
        return new Object[]{this.mResultUri, str};
    }

    public void scanFiles(Context context, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (skipDbUpdate()) {
            ArrayList arrayList = new ArrayList();
            if (fileItemInterface2 != null) {
                arrayList.add(fileItemInterface2.getPath());
            }
            arrayList.add(fileItemInterface.getPath());
            MediaScanner.scanFiles((Collection<String>) arrayList, (MediaScannerListener) new a(27, this));
            return;
        }
        notifyCompletion();
    }

    public abstract boolean setBestItemC2pa(Context context, FileItemInterface fileItemInterface, double[] dArr);

    public void setCompletionListener(OnUpdateCompletionListener onUpdateCompletionListener) {
        this.mListener = onUpdateCompletionListener;
    }

    public boolean skipDbUpdate() {
        return SdkConfig.atLeast(SdkConfig.GED.R);
    }

    public abstract boolean unsetBestItemC2pa(FileItemInterface fileItemInterface);

    public void updateBestItem(Context context, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, double[] dArr) {
        boolean z;
        if (fileItemInterface.getPath() == null) {
            notifyCompletion(false);
            return;
        }
        if (fileItemInterface2 == null) {
            fileItemInterface2 = findBestItem(fileItemInterface);
        }
        if (!updateFileBestItem(fileItemInterface2, fileItemInterface.getPath()) || !updateDbBestItem(fileItemInterface, fileItemInterface2)) {
            z = false;
        } else {
            z = true;
        }
        this.mResult = z;
        if (z) {
            scanFiles(context, fileItemInterface, fileItemInterface2);
            updateLocation(context, fileItemInterface, dArr);
            return;
        }
        Log.e(this.TAG, "fail update bestItem");
        Utils.showDebugToast(context, "fail set best");
        notifyCompletion(false);
    }

    public boolean updateFileBestItem(FileItemInterface fileItemInterface, String str) {
        return true;
    }

    public int updateMediaBestImage(FileItemInterface fileItemInterface, int i2) {
        return new FilesApi().updateMediaBestImage(ContentUri.getWritableUri(fileItemInterface), i2);
    }

    public void notifyCompletion(boolean z) {
        OnUpdateCompletionListener onUpdateCompletionListener = this.mListener;
        if (onUpdateCompletionListener != null) {
            b bVar = (b) onUpdateCompletionListener;
            ((ChangeBestItemCmd) bVar.f).lambda$onExecute$0((MediaItem[]) bVar.e, this.mResult && z);
            this.mListener = null;
        }
    }

    public void handleExifInfo(String str, double d, double d2) {
    }

    public void updateLocation(Context context, FileItemInterface fileItemInterface, double[] dArr) {
    }
}
