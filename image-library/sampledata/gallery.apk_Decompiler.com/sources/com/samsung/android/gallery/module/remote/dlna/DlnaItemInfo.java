package com.samsung.android.gallery.module.remote.dlna;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.allshare.Item;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaItemInfo {
    private final String TAG = "DlnaItemInfo";
    private final long mCloudId;
    private final String mCloudServerId;
    private final String mFilePath;
    private final String mMimeType;
    private final String mName;

    public DlnaItemInfo(String str, String str2, String str3, String str4, long j2) {
        this.mFilePath = str;
        this.mMimeType = str2;
        this.mName = str3;
        this.mCloudServerId = str4;
        this.mCloudId = j2;
    }

    public long getCloudId() {
        return this.mCloudId;
    }

    public String getCloudServerId() {
        return this.mCloudServerId;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public String getName() {
        return this.mName;
    }

    public Item prepareItem(Context context) {
        String filePath = getFilePath();
        Item item = null;
        if (filePath == null) {
            Log.rme("DlnaItemInfo", "prepareItem failed. null path");
            return null;
        }
        if (!filePath.contains("/data/sec/cloud/") || context == null) {
            try {
                item = new Item.LocalContentBuilder(filePath, getMimeType()).setTitle(getName()).build();
            } catch (NullPointerException e) {
                Log.rme("DlnaItemInfo", "prepareItem failed e=" + e.getMessage());
            }
        } else {
            String downloadUrl = SamsungCloudCompat.getDownloadUrl(context, getCloudId(), getCloudServerId());
            if (downloadUrl == null || downloadUrl.isEmpty()) {
                Log.rme("DlnaItemInfo", "prepareItem failed. null url");
                return null;
            }
            item = new Item.WebContentBuilder(Uri.parse(downloadUrl), getMimeType()).setTitle(getName()).build();
        }
        if (item == null) {
            Log.rme("DlnaItemInfo", "prepareItem failed. null item {" + filePath + "}");
        }
        return item;
    }
}
