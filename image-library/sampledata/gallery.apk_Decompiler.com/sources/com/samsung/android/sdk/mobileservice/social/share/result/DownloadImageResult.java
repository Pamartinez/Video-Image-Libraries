package com.samsung.android.sdk.mobileservice.social.share.result;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadImageResult implements Result {
    private DownloadedImage mImage;
    private CommonResultStatus mStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DownloadedImage {
        private String mHash;
        private Uri mImageUri;
        private String mItemId;

        public DownloadedImage(String str, String str2, Uri uri) {
            this.mItemId = str;
            this.mHash = str2;
            this.mImageUri = uri;
        }

        public String getHash() {
            return this.mHash;
        }

        public Uri getImageUri() {
            return this.mImageUri;
        }

        public String getItemId() {
            return this.mItemId;
        }
    }

    public DownloadImageResult(CommonResultStatus commonResultStatus, DownloadedImage downloadedImage) {
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
