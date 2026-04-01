package com.samsung.scsp.media;

import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaConstants {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FileType {
        ORIGINAL(ShareApi.ORIGINAL_SIZE_IMAGE),
        LOW("240"),
        CACHE("large"),
        THUMBNAIL(BuddyContract.Image.THUMBNAIL);
        
        private final String name;

        private FileType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MediaType {
        IMAGE("image"),
        VIDEO("video"),
        ALL("all");
        
        private final String name;

        private MediaType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }
}
