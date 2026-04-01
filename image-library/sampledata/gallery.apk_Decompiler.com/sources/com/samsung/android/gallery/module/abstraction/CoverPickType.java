package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CoverPickType {
    ;
    
    private final int mValue;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.abstraction.CoverPickType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends CoverPickType {
        public /* synthetic */ AnonymousClass1() {
            this("NONE", 0, -1);
        }

        public String getLocationKey(LaunchIntent launchIntent) {
            return null;
        }

        public String getScreenId() {
            return null;
        }

        private AnonymousClass1(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.abstraction.CoverPickType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends CoverPickType {
        public /* synthetic */ AnonymousClass2() {
            this("FROM_ALBUM", 1, 0);
        }

        public String getLocationKey(LaunchIntent launchIntent) {
            String albumBucketIds = launchIntent.getAlbumBucketIds();
            UriBuilder uriBuilder = new UriBuilder("location://albums/fileList");
            uriBuilder.appendArg(Message.KEY_POSITION, launchIntent.getAlbumPosition()).appendArg("type", launchIntent.getAlbumType());
            if (albumBucketIds == null || albumBucketIds.isEmpty()) {
                uriBuilder.appendArg("id", launchIntent.getAlbumBucketId());
            } else {
                uriBuilder.appendArg("ids", albumBucketIds);
                String mergedAlbumId = launchIntent.getMergedAlbumId();
                if (mergedAlbumId != null && !mergedAlbumId.isEmpty()) {
                    uriBuilder.appendArg("mergedAlbumId", mergedAlbumId);
                }
            }
            return uriBuilder.build();
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_ALBUM_CHANGE_COVER_PICK.toString();
        }

        private AnonymousClass2(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.abstraction.CoverPickType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends CoverPickType {
        public /* synthetic */ AnonymousClass3() {
            this("FROM_STORY", 2, 1);
        }

        public String getLocationKey(LaunchIntent launchIntent) {
            int storyAlbumBucketId = launchIntent.getStoryAlbumBucketId();
            return new UriBuilder(LocationKey.getStoryPicturesAliasKey() + "/" + storyAlbumBucketId).appendArg("id", storyAlbumBucketId).appendArg(Message.KEY_POSITION, launchIntent.getStoryAlbumPosition()).appendArg("cover_pick", true).build();
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_STORY_CHANGE_COVER_PICK.toString();
        }

        private AnonymousClass3(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.abstraction.CoverPickType$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends CoverPickType {
        public /* synthetic */ AnonymousClass4() {
            this("FROM_SHARED_ALBUM", 3, 2);
        }

        public String getLocationKey(LaunchIntent launchIntent) {
            return new UriBuilder("location://sharing/albums/fileList").appendArg("id", launchIntent.getSharedAlbumSpaceId()).appendArg(Message.KEY_POSITION, launchIntent.getSharedAlbumPosition()).appendArg("groupId", launchIntent.getSharedAlbumGroupId()).build();
        }

        public String getScreenId() {
            return AnalyticsScreenId.SCREEN_SHARED_ALBUM_CHANGE_COVER_PICK.toString();
        }

        private AnonymousClass4(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    public static CoverPickType getType(int i2) {
        if (i2 == 0) {
            return FROM_ALBUM;
        }
        if (i2 == 1) {
            return FROM_STORY;
        }
        if (i2 != 2) {
            return NONE;
        }
        return FROM_SHARED_ALBUM;
    }

    public abstract String getLocationKey(LaunchIntent launchIntent);

    public abstract String getScreenId();

    public int toInt() {
        return this.mValue;
    }

    private CoverPickType(int i2) {
        this.mValue = i2;
    }
}
