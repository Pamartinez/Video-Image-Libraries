package com.samsung.android.sdk.mobileservice.social.group.result;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupImageDownloadResult implements Result {
    private DownloadedImage mDownloadedImage;
    private CommonResultStatus mStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadedImage {
        private Uri mFileUri;
        private String mId;

        public DownloadedImage(String str, Uri uri) {
            this.mId = str;
            this.mFileUri = uri;
        }

        public Uri getFileUri() {
            return this.mFileUri;
        }

        public String getId() {
            return this.mId;
        }
    }

    public GroupImageDownloadResult(CommonResultStatus commonResultStatus, DownloadedImage downloadedImage) {
        this.mStatus = commonResultStatus;
        this.mDownloadedImage = downloadedImage;
    }

    public DownloadedImage getResult() {
        return this.mDownloadedImage;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
