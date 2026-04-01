package com.samsung.android.gallery.module.story;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryUriBuilder {
    private Blackboard blackboard;
    Bitmap blurBitmap;
    UriBuilder builder;
    private ThumbnailInterface headerItem;
    boolean publishHeader;
    private final int storyId;

    public StoryUriBuilder(Blackboard blackboard2, ThumbnailInterface thumbnailInterface) {
        this(blackboard2, thumbnailInterface, true);
    }

    private void publishBlurBitmap() {
        ThumbnailInterface thumbnailInterface;
        Blackboard blackboard2 = this.blackboard;
        if (blackboard2 != null && (thumbnailInterface = this.headerItem) != null && this.blurBitmap != null) {
            blackboard2.publish(LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(thumbnailInterface.getFileId())), this.blurBitmap);
        }
    }

    private void publishHeader() {
        Blackboard blackboard2;
        if (this.publishHeader && (blackboard2 = this.blackboard) != null && this.headerItem != null) {
            blackboard2.publish(LocationKey.getHeaderCacheKey("stories", this.storyId), this.headerItem);
        }
    }

    private StoryUriBuilder withCategoryKey(String str) {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            appendArg("categoryKey", str);
        }
        return this;
    }

    public StoryUriBuilder appendArg(String str, Object obj) {
        if (obj instanceof Long) {
            this.builder.appendArg(str, ((Long) obj).longValue());
            return this;
        } else if (obj instanceof Integer) {
            this.builder.appendArg(str, ((Integer) obj).intValue());
            return this;
        } else if (obj instanceof Double) {
            this.builder.appendArg(str, ((Double) obj).doubleValue());
            return this;
        } else if (obj instanceof String) {
            this.builder.appendArg(str, (String) obj);
            return this;
        } else if (obj instanceof Boolean) {
            this.builder.appendArg(str, ((Boolean) obj).booleanValue());
            return this;
        } else {
            if (obj instanceof String[]) {
                this.builder.appendArg(str, (String[]) obj);
            }
            return this;
        }
    }

    public String build() {
        publishHeader();
        publishBlurBitmap();
        return this.builder.build();
    }

    public StoryUriBuilder withBlurBitmap() {
        Bitmap bitmap;
        int i2;
        if (this.headerItem == null) {
            return this;
        }
        Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(this.headerItem, ThumbKind.FREE_KIND);
        Bitmap bitmap2 = null;
        if (loadThumbnailSync != null) {
            bitmap = BitmapUtils.blurAfterResize(AppResources.getAppContext(), loadThumbnailSync, 64);
        } else {
            bitmap = null;
        }
        if (this.headerItem.isBroken() || this.headerItem.isVideo()) {
            i2 = 0;
        } else {
            i2 = this.headerItem.getOrientation();
        }
        if (bitmap != null) {
            bitmap2 = BitmapUtils.rotateBitmap(bitmap, i2);
        }
        this.blurBitmap = bitmap2;
        return this;
    }

    public StoryUriBuilder withFavorite(boolean z) {
        appendArg("fromStoryFavorite", Boolean.valueOf(z));
        return this;
    }

    public StoryUriBuilder withFromLocation(String str) {
        appendArg("from_location", str);
        return this;
    }

    public StoryUriBuilder withOnDemandVi(boolean z) {
        appendArg("launchWithOnDemandVi", Boolean.valueOf(z));
        return this;
    }

    public StoryUriBuilder withPosition(int i2) {
        appendArg(Message.KEY_POSITION, Integer.valueOf(i2));
        return this;
    }

    public StoryUriBuilder withStoryId(int i2) {
        appendArg("id", Integer.valueOf(i2));
        return this;
    }

    public StoryUriBuilder(Blackboard blackboard2, ThumbnailInterface thumbnailInterface, boolean z) {
        this(blackboard2, thumbnailInterface, z, LocationKey.getStoryPicturesAliasKey());
    }

    public StoryUriBuilder(Blackboard blackboard2, ThumbnailInterface thumbnailInterface, boolean z, String str) {
        this(str, MediaItemStory.getStoryId(thumbnailInterface));
        this.blackboard = blackboard2;
        this.headerItem = thumbnailInterface;
        this.publishHeader = z;
        withCategoryKey(MediaItemStory.getStoryCategoryKey(thumbnailInterface));
        appendArg("type", Integer.valueOf(MediaItemStory.getStoryType(thumbnailInterface)));
    }

    private StoryUriBuilder(String str, int i2) {
        this.storyId = i2;
        this.builder = new UriBuilder(str + "/" + i2);
        appendArg("id", Integer.valueOf(i2));
    }
}
