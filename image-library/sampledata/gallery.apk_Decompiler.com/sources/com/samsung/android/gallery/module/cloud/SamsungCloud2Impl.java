package com.samsung.android.gallery.module.cloud;

import Sd.C0836a;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloud2Sdk;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.media.Media;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungCloud2Impl extends SamsungCloudTImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadCancellerImpl implements DownloadCanceller {
        private CloudDownloadMonitor mMonitor;

        /* access modifiers changed from: private */
        public void requestCancelDownload(String str) {
            if (!TextUtils.isEmpty(str)) {
                C0836a downloadRequest = this.mMonitor.getDownloadRequest(str);
                if (downloadRequest != null) {
                    SamsungCloud2Sdk.cancelDownload(downloadRequest);
                }
                this.mMonitor.clear(str);
                return;
            }
            Log.e("SamsungCloud2Impl", "requestCancelDownload failed, cloud server id empty");
        }

        public void onCancel(Context context) {
            CloudDownloadMonitor cloudDownloadMonitor = this.mMonitor;
            if (cloudDownloadMonitor != null) {
                DownloadMonitorImpl downloadMonitorImpl = (DownloadMonitorImpl) cloudDownloadMonitor;
                downloadMonitorImpl.getAllKeys().forEach(new a(4, this));
                downloadMonitorImpl.clear();
            }
        }

        public void setMonitor(CloudDownloadMonitor cloudDownloadMonitor) {
            this.mMonitor = cloudDownloadMonitor;
        }
    }

    public boolean changeContentSyncState(Context context, boolean z) {
        return SamsungCloud2Sdk.requestSetSyncState(context, z);
    }

    public SyncStateHelper createSyncStateHelper() {
        return new SyncStateHelperV2();
    }

    public ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        return SamsungCloud2Sdk.download(context, downloadParams);
    }

    public boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        return SamsungCloud2Sdk.downloadFileDirectly(context, downloadParams);
    }

    public boolean downloadOriginal(Context context, String str, String str2, String str3, boolean z) {
        return SamsungCloud2Sdk.downloadItemOriginalFile(context, str, str2);
    }

    public Media empty(Context context, Media media) {
        SamsungCloud2Sdk.requestEmptyTrash(context);
        return null;
    }

    public DownloadCanceller getDownloadCanceller() {
        return new DownloadCancellerImpl();
    }

    public CloudDownloadMonitor getDownloadMonitor() {
        return new DownloadMonitorImpl();
    }

    public String getStreamingUrl(Context context, long j2, String str) {
        return SamsungCloud2Sdk.getStreamingUrl(context, str);
    }

    public boolean isContentSyncOn(Context context) {
        return SamsungCloud2Sdk.isContentSyncOn(context);
    }

    public Media restore(Context context, Media media) {
        return new Media();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadMonitorImpl implements CloudDownloadMonitor {
        private final Object LOCK = new Object();
        final HashMap<String, CloudDownloadListener> mDownloadListeners = new HashMap<>();
        final HashMap<String, C0836a> mDownloadRequesters = new HashMap<>();

        public void addDownloadRequest(String str, C0836a aVar) {
            synchronized (this.LOCK) {
                this.mDownloadRequesters.put(str, aVar);
            }
        }

        public void addProgressListener(String str, CloudDownloadListener cloudDownloadListener) {
            if (!TextUtils.isEmpty(str)) {
                synchronized (this.LOCK) {
                    this.mDownloadListeners.put(str, cloudDownloadListener);
                }
            }
        }

        public void clear(String str) {
            synchronized (this.LOCK) {
                this.mDownloadRequesters.remove(str);
                this.mDownloadListeners.remove(str);
            }
        }

        public List<String> getAllKeys() {
            ArrayList arrayList;
            synchronized (this.LOCK) {
                arrayList = new ArrayList(this.mDownloadRequesters.keySet());
            }
            return arrayList;
        }

        public C0836a getDownloadRequest(String str) {
            C0836a aVar;
            synchronized (this.LOCK) {
                aVar = this.mDownloadRequesters.get(str);
            }
            return aVar;
        }

        public CloudDownloadListener getProgressListener(String str) {
            CloudDownloadListener cloudDownloadListener;
            synchronized (this.LOCK) {
                cloudDownloadListener = this.mDownloadListeners.get(str);
            }
            return cloudDownloadListener;
        }

        public void clear() {
            synchronized (this.LOCK) {
                this.mDownloadRequesters.clear();
                this.mDownloadListeners.clear();
            }
        }
    }
}
