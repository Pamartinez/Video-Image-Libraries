package com.samsung.android.sdk.mobileservice.social.share.result;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpaceImageDownloadResult implements Result {
    private DownloadedImage mImage;
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

    public SpaceImageDownloadResult(CommonResultStatus commonResultStatus, DownloadedImage downloadedImage) {
        this.mStatus = commonResultStatus;
        this.mImage = downloadedImage;
    }

    public DownloadedImage getResult() {
        return this.mImage;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
