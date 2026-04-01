package com.samsung.android.gallery.module.bgm.bgmdata;

import N2.j;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmItem {
    public static int STATE_DOWNLOADED = 1;
    public static int STATE_DOWNLOADING = 2;
    private String mCategory;
    public int mDownloadStatus;
    private String mDownloadUrl;
    private long mInstalledTimeStamp;
    private boolean mIsFragmented;
    private boolean mLicenseExpired;
    private String mName;
    private String mPackageId;
    private int mRevision;
    private String mTitle;

    public static BgmItem build(Cursor cursor) {
        boolean z;
        boolean z3 = false;
        BgmItem bgmItem = new BgmItem();
        try {
            bgmItem.mPackageId = (String) getValue(cursor.getString(cursor.getColumnIndex("package_id")), "");
            bgmItem.mTitle = (String) getValue(cursor.getString(cursor.getColumnIndex("title")), "");
            bgmItem.mName = (String) getValue(cursor.getString(cursor.getColumnIndex("name")), "");
            bgmItem.mCategory = (String) getValue(cursor.getString(cursor.getColumnIndex(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY)), "");
            bgmItem.mDownloadUrl = (String) getValue(cursor.getString(cursor.getColumnIndex("download_url")), "");
            if (((Integer) getValue(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("is_fragmented"))), 1)).intValue() > 0) {
                z = true;
            } else {
                z = false;
            }
            bgmItem.mIsFragmented = z;
            bgmItem.mDownloadStatus = ((Integer) getValue(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("download_status"))), 0)).intValue();
            bgmItem.mRevision = ((Integer) getValue(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("revision"))), 0)).intValue();
            if (((Integer) getValue(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("license_expired"))), 0)).intValue() > 0) {
                z3 = true;
            }
            bgmItem.mLicenseExpired = z3;
            bgmItem.mInstalledTimeStamp = ((Long) getValue(Long.valueOf(cursor.getLong(cursor.getColumnIndex("installed_timestamp"))), 0L)).longValue();
            return bgmItem;
        } catch (Exception e) {
            Log.e((CharSequence) "BgmItem", "error while parsing column", e.getMessage());
            return bgmItem;
        }
    }

    private static <T> T getValue(T t, T t3) {
        if (t != null) {
            return t;
        }
        return t3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BgmItem) || TextUtils.isEmpty(this.mName)) {
            return false;
        }
        BgmItem bgmItem = (BgmItem) obj;
        if (!this.mName.equals(bgmItem.mName) || this.mDownloadStatus != bgmItem.mDownloadStatus) {
            return false;
        }
        return true;
    }

    public String getName() {
        return this.mName;
    }

    public boolean isDownloaded() {
        if (this.mDownloadStatus == STATE_DOWNLOADED) {
            return true;
        }
        return false;
    }

    public boolean isDownloading() {
        if (this.mDownloadStatus == STATE_DOWNLOADING) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BgmItem{mPackageId=");
        sb2.append(this.mPackageId);
        sb2.append(", mName=");
        sb2.append(this.mName);
        sb2.append(", mIsFragmented=");
        sb2.append(this.mIsFragmented);
        sb2.append(", mDownloadStatus=");
        return j.e(sb2, this.mDownloadStatus, '}');
    }
}
