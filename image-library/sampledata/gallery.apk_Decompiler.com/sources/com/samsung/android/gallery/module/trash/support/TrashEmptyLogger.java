package com.samsung.android.gallery.module.trash.support;

import N2.j;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.trash.abstraction.TrashEmptyItem;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.scsp.media.Media;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashEmptyLogger extends TrashLogger {
    private int mCloudEmptyFailed = 0;
    private int mDeleteFailed = 0;
    private boolean mGDPRError = false;
    private int mGDPRImg = 0;
    private int mGDPRMov = 0;
    private final boolean mIsEmptyAll;

    public TrashEmptyLogger(Context context, boolean z) {
        this.mIsEmptyAll = z;
    }

    public void emptyCloudFail(TrashEmptyItem trashEmptyItem, Media media, CloudErrorType cloudErrorType) {
        Object obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
        sb2.append("\t[Empty Cloud Fail]\t");
        sb2.append(trashEmptyItem.getStorageType());
        sb2.append("\t");
        sb2.append(Logger.getEncodedString(trashEmptyItem.getCloudServerPath()));
        sb2.append("\t");
        if (media == null) {
            obj = "null";
        } else {
            obj = media.rcode;
        }
        sb2.append(obj);
        sb2.append("\t");
        sb2.append(trashEmptyItem.getCloudServerId());
        sb2.append("\t");
        sb2.append(trashEmptyItem.getCloudTimestamp());
        sb2.append("\r\n");
        this.mExtras.add(sb2.toString());
        this.mCloudEmptyFailed++;
        if (cloudErrorType == CloudErrorType.GDPR) {
            this.mGDPRError = true;
            if (trashEmptyItem.getMediaType() == MediaType.Video) {
                this.mGDPRMov++;
            } else {
                this.mGDPRImg++;
            }
        }
    }

    public void emptyDeleteFail(boolean z, String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
        sb2.append("\t[Empty Delete Fail]\t");
        sb2.append(str);
        sb2.append("\t");
        sb2.append(z);
        this.mExtras.add(j.f(sb2, "\t", str2, "\r\n"));
        this.mDeleteFailed++;
    }

    public String getDetail(boolean z) {
        StringBuilder sb2 = new StringBuilder(" All[");
        sb2.append(this.mIsEmptyAll);
        sb2.append("] CF[");
        sb2.append(this.mCloudEmptyFailed);
        sb2.append("] DF[");
        sb2.append(this.mDeleteFailed);
        sb2.append("] GDPRI[");
        sb2.append(this.mGDPRImg);
        sb2.append("] GDPRV[");
        return C0086a.l(sb2, this.mGDPRMov, "]");
    }

    public int getGDPRImageCount() {
        return this.mGDPRImg;
    }

    public int getGDPRVideoCount() {
        return this.mGDPRMov;
    }

    public boolean isGDPRErrorHappened() {
        return this.mGDPRError;
    }
}
