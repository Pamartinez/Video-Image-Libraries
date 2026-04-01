package com.samsung.android.gallery.module.thumbnail.logic;

import F9.e;
import android.graphics.Bitmap;
import c0.C0086a;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpThumbnailLoaderImpl extends AbsThumbnailLoaderImpl {
    private volatile MtpClient mMtpClient;

    private MtpClient getMtpClient() {
        if (this.mMtpClient == null) {
            synchronized (MtpThumbnailLoaderImpl.class) {
                try {
                    if (this.mMtpClient == null) {
                        this.mMtpClient = MtpClient.getInstance(AppResources.getAppContext());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mMtpClient;
    }

    public int getCacheId(ThumbKind thumbKind) {
        return 3;
    }

    public Bitmap getThumbnail(ReqInfo reqInfo) {
        Bitmap bitmap;
        byte[] bArr;
        ArrayList arrayList = new ArrayList();
        TimeTickLog timeTickLog = new TimeTickLog("MtpImageThumb");
        MtpClient mtpClient = getMtpClient();
        ThumbnailInterface item = reqInfo.getItem();
        int fileId = (int) item.getFileId();
        new LatchBuilder(C0086a.i(fileId, "mtp_thumb_")).addWorker(new e((Object) arrayList, (Object) mtpClient, (Object) item.getPath(), fileId, 7)).setTimeout(10000).start();
        if (arrayList.isEmpty() || (bArr = (byte[]) arrayList.get(0)) == null || bArr.length <= 0) {
            bitmap = null;
        } else {
            timeTickLog.tick("decodeByteArray");
            bitmap = ImageDecoder.decodeByteArray(bArr, 0, bArr.length, new BitmapOptions());
        }
        if (checkInterrupted(reqInfo, bitmap)) {
            return null;
        }
        if (Logger.THUMBNAIL) {
            timeTickLog.tock(200);
        }
        return bitmap;
    }

    public boolean support(ThumbnailInterface thumbnailInterface) {
        if (!thumbnailInterface.isMtp() || !thumbnailInterface.isImage()) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "MtpThumbnailLoaderImpl";
    }
}
