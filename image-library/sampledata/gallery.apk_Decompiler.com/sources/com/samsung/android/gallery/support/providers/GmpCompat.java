package com.samsung.android.gallery.support.providers;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GmpCompat {
    public static final Uri GALLERY_FILES_URI;
    public static final Uri GALLERY_LOCATION_URI;
    public static final Uri GALLERY_MEDIA_PROVIDER_URI;
    public static final Uri GALLERY_POI_CACHE_URI;
    public static final Uri GALLERY_RAW_URI;
    private static final CharSequence TAG = "GmpCompat";
    public static int mDelay = 1000;
    public static volatile boolean sHaveUi = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GmpDataChangeDetails {
        HashMap<String, ContentValues> mContentValueMap = null;
        ArrayList<String> mIdList;
        int mType;

        public GmpDataChangeDetails(int i2, ArrayList<String> arrayList) {
            this.mType = i2;
            this.mIdList = arrayList;
        }

        public boolean isRemoved() {
            if (this.mType == 2) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "GmpDataChangeDetails{mType=" + this.mType + ", mIdList=" + this.mIdList + ", mContentValueMap=" + this.mContentValueMap + '}';
        }
    }

    static {
        Uri parse = Uri.parse("content://com.sec.android.gallery3d.provider.GalleryMediaProvider");
        GALLERY_MEDIA_PROVIDER_URI = parse;
        GALLERY_POI_CACHE_URI = Uri.withAppendedPath(parse, "poicache");
        GALLERY_LOCATION_URI = Uri.withAppendedPath(parse, "location");
        GALLERY_FILES_URI = Uri.withAppendedPath(parse, "files");
        GALLERY_RAW_URI = Uri.withAppendedPath(parse, "raw_sql");
    }

    public static Bundle invoke(Context context, String str, String str2) {
        return context.getContentResolver().call("com.sec.android.gallery3d.provider.GalleryMediaProvider", str, str2, (Bundle) null);
    }

    public static void scheduleDataSync(Context context, boolean z) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            Uri mediaSyncUri = MediaUri.getInstance().getMediaSyncUri();
            JobInfo.Builder builder = new JobInfo.Builder(3, new ComponentName("com.sec.android.gallery3d", "com.samsung.android.gallery.gmp.mediasync.DataSyncJobService"));
            if (z) {
                builder.addTriggerContentUri(new JobInfo.TriggerContentUri(mediaSyncUri, 1));
                if (sHaveUi) {
                    builder.setMinimumLatency(100).setTriggerContentMaxDelay(1000).setTriggerContentUpdateDelay(1000);
                } else {
                    builder.setMinimumLatency((long) mDelay).setTriggerContentMaxDelay(30000).setTriggerContentUpdateDelay(1000);
                }
            }
            builder.setRequiresStorageNotLow(true);
            if (jobScheduler.schedule(builder.build()) == 0) {
                Log.e(TAG, "fail schedule DataSyncJobService");
            } else {
                Log.d(TAG, "schedule DataSyncJobService");
            }
        }
    }

    public static void scheduleGmpLocationServiceJob(Context context, int i2, boolean z) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null) {
            JobInfo.Builder builder = new JobInfo.Builder(i2, new ComponentName("com.sec.android.gallery3d", "com.samsung.android.gallery.gmp.location.LocationService"));
            if (z) {
                builder.setRequiredNetworkType(1);
                builder.setRequiresCharging(true);
            }
            if (jobScheduler.schedule(builder.build()) == 0) {
                Log.e(TAG, "Failed schedule location service");
            } else {
                Log.d(TAG, "Schedule location service");
            }
        }
    }

    public static void setOnUi(boolean z) {
        sHaveUi = z;
        mDelay = 1000;
    }
}
