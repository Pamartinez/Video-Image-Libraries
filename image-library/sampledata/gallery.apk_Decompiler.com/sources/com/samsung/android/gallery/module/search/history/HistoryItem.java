package com.samsung.android.gallery.module.search.history;

import M8.a;
import U5.c;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HistoryItem {
    public String mCategory;
    private final long mCoverId;
    private MediaItem mCoverItem;
    public final long mDateAdded;
    private final IntelligentSearchIndexFieldIcon mIcon;
    public final String mTarget;
    public final String mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BrokenItemHolder {
        static final MediaItem item;

        static {
            MediaItem mediaItem = new MediaItem();
            item = mediaItem;
            mediaItem.setBroken(true);
        }
    }

    public HistoryItem(String str, long j2, long j3) {
        this.mTitle = ArgumentsUtil.getArgValue(str, "title");
        this.mTarget = str;
        this.mCategory = ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        this.mIcon = IntelligentSearchIndexFieldIcon.create(ArgumentsUtil.getArgValue(str, "term"), ArgumentsUtil.getArgValue(str, "sub"));
        this.mCoverId = j2;
        this.mDateAdded = j3;
    }

    private MediaItem getCreatureMediaItem() {
        String argValue = ArgumentsUtil.getArgValue(this.mTarget, "sub");
        if (isPeople()) {
            return PeopleDataManager.loadHeaderItem(argValue);
        }
        return PetDataManager.loadHeaderItem(argValue);
    }

    private String getUntaggedString() {
        return AppResources.getAppContext().getString(R$string.untagged_face);
    }

    private boolean isTag() {
        return "My tags".equals(this.mCategory);
    }

    private boolean isUntaggedCreature() {
        if (!TextUtils.isEmpty(this.mTitle)) {
            return false;
        }
        if (isPeople() || isPet()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCreatureThumbnail$1(Consumer consumer) {
        MediaItem creatureMediaItem = getCreatureMediaItem();
        if (creatureMediaItem != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.SMALL_NC_KIND;
            String subCategory = creatureMediaItem.getSubCategory();
            Objects.requireNonNull(subCategory);
            instance.getOrLoad(creatureMediaItem, thumbKind, new a(subCategory, 9), new D9.a(7, consumer));
            return;
        }
        consumer.accept((Object) null);
    }

    public void assignCoverItem(HashMap<Long, MediaItem> hashMap) {
        MediaItem mediaItem = hashMap.get(Long.valueOf(this.mCoverId));
        if (mediaItem == null) {
            mediaItem = BrokenItemHolder.item;
        }
        this.mCoverItem = mediaItem;
    }

    public boolean equals(Object obj) {
        if (obj instanceof HistoryItem) {
            HistoryItem historyItem = (HistoryItem) obj;
            if (!TextUtils.equals(this.mTitle, historyItem.mTitle) || !MediaItemUtil.equals(this.mCoverItem, historyItem.getCoverItem())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getContentDescription() {
        if (isUntaggedCreature()) {
            return getUntaggedString();
        }
        return getDisplayName();
    }

    public long getCoverId() {
        return this.mCoverId;
    }

    public MediaItem getCoverItem() {
        return this.mCoverItem;
    }

    public void getCreatureThumbnail(Consumer<Bitmap> consumer) {
        SimpleThreadPool.getInstance().execute(new c(21, this, consumer));
    }

    public String getDisplayName() {
        if (isTag()) {
            return StringCompat.excludePrefix(this.mTitle, "#");
        }
        if (isUntaggedCreature()) {
            return AppResources.getAppContext().getString(R$string.unnamed);
        }
        return this.mTitle;
    }

    public Integer getTypeIconResId() {
        return this.mIcon.getDrawableResId();
    }

    public Integer getTypeIconTintColor() {
        return this.mIcon.getTintColorResId();
    }

    public boolean hasTypeIcon() {
        if (this.mIcon.getDrawableResId() != null) {
            return true;
        }
        return false;
    }

    public boolean isPeople() {
        return "People".equals(this.mCategory);
    }

    public boolean isPet() {
        return "Pet".equals(this.mCategory);
    }
}
