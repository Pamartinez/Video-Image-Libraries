package com.samsung.android.gallery.module.cloud.abstraction;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.FileUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadParams {
    private final String cloudServerId;
    private final long dateTaken;
    private final DownloadCanceller downloadCanceller;
    private final CloudDownloadMonitor downloadMonitor;
    private final long fileId;
    private final int mediaType;
    private final String mimeType;
    private final String originTargetPath;
    private final String targetPath;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String cloudServerId;
        /* access modifiers changed from: private */
        public long dateTaken;
        /* access modifiers changed from: private */
        public DownloadCanceller downloadCanceller;
        /* access modifiers changed from: private */
        public CloudDownloadMonitor downloadMonitor;
        /* access modifiers changed from: private */
        public long fileId;
        /* access modifiers changed from: private */
        public FileItemInterface fileItemInterface;
        /* access modifiers changed from: private */
        public int mediaType;
        /* access modifiers changed from: private */
        public String mimeType;
        /* access modifiers changed from: private */
        public String targetPath;

        public /* synthetic */ Builder(int i2) {
            this();
        }

        public DownloadParams build() {
            return new DownloadParams(this, 0);
        }

        public Builder setCloudServerId(String str) {
            this.cloudServerId = str;
            return this;
        }

        public Builder setDateTaken(long j2) {
            this.dateTaken = j2;
            return this;
        }

        public Builder setDownloadCanceller(DownloadCanceller downloadCanceller2) {
            this.downloadCanceller = downloadCanceller2;
            return this;
        }

        public Builder setDownloadMonitor(CloudDownloadMonitor cloudDownloadMonitor) {
            this.downloadMonitor = cloudDownloadMonitor;
            return this;
        }

        public Builder setFileId(long j2) {
            this.fileId = j2;
            return this;
        }

        public Builder setFileItemInterface(FileItemInterface fileItemInterface2) {
            this.fileItemInterface = fileItemInterface2;
            return this;
        }

        public Builder setMediaType(int i2) {
            this.mediaType = i2;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setTargetPath(String str) {
            this.targetPath = str;
            return this;
        }

        private Builder() {
        }
    }

    public /* synthetic */ DownloadParams(Builder builder, int i2) {
        this(builder);
    }

    public static Builder builder() {
        return new Builder(0);
    }

    private String getUniqueTargetPath() {
        String[] splitPathAndName = FileUtils.splitPathAndName(this.originTargetPath, true);
        if (TextUtils.isEmpty(splitPathAndName[0]) || TextUtils.isEmpty(splitPathAndName[1])) {
            return null;
        }
        return splitPathAndName[0] + "/" + splitPathAndName[1];
    }

    public void clear() {
        if (this.downloadMonitor != null && !TextUtils.isEmpty(this.cloudServerId)) {
            this.downloadMonitor.clear(this.cloudServerId);
        }
    }

    public String getCloudServerId() {
        return this.cloudServerId;
    }

    public long getDateTaken() {
        return this.dateTaken;
    }

    public DownloadCanceller getDownloadCanceller() {
        return this.downloadCanceller;
    }

    public CloudDownloadMonitor getDownloadMonitor() {
        return this.downloadMonitor;
    }

    public long getFileId() {
        return this.fileId;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getOriginTargetPath() {
        return this.originTargetPath;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    private DownloadParams(Builder builder) {
        DownloadCanceller c5 = builder.downloadCanceller;
        this.downloadCanceller = c5;
        CloudDownloadMonitor d = builder.downloadMonitor;
        this.downloadMonitor = d;
        this.originTargetPath = builder.targetPath;
        this.targetPath = getUniqueTargetPath();
        if (c5 != null) {
            c5.setMonitor(d);
        }
        if (builder.fileItemInterface != null) {
            this.cloudServerId = builder.fileItemInterface.getCloudServerId();
            this.dateTaken = builder.fileItemInterface.getDateTaken();
            this.fileId = builder.fileItemInterface.getFileId();
            this.mediaType = builder.fileItemInterface.getMediaType().toInt();
            this.mimeType = builder.fileItemInterface.getMimeType();
            return;
        }
        this.cloudServerId = builder.cloudServerId;
        this.dateTaken = builder.dateTaken;
        this.fileId = builder.fileId;
        this.mediaType = builder.mediaType;
        this.mimeType = builder.mimeType;
    }
}
