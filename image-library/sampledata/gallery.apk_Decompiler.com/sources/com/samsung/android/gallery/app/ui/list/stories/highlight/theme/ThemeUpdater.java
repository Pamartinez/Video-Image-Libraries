package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import B8.g;
import F8.a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeUpdater {
    private static final boolean SUPPORT_FILTER = Features.isEnabled(Features.SUPPORT_HDR2SDR);
    private BgmExtraInfo mBgmExtraInfo;
    private final EventHandler mEventHandler;
    private Filter mFilter;
    private boolean mIsChanged;
    private final MediaItem mItem;
    private final int mOriginSyncFlags;
    private EffectTheme mTheme;
    private int mUpdateSyncFlags;

    public ThemeUpdater(MediaItem mediaItem, EventHandler eventHandler) {
        this.mItem = mediaItem;
        this.mEventHandler = eventHandler;
        int storyUpdatedByUser = MediaItemStory.getStoryUpdatedByUser(mediaItem);
        this.mOriginSyncFlags = storyUpdatedByUser;
        this.mUpdateSyncFlags = storyUpdatedByUser;
    }

    private BgmExtraInfo createBgmInfoIfValid(String str) {
        BgmExtraInfo parse = BgmExtraInfo.parse(str);
        if (parse == null) {
            return parse;
        }
        if ((!parse.isMyMusic || FileUtils.exists(parse.path)) && (parse.isMyMusic || Bgm.isBgm(parse.bgmName))) {
            return parse;
        }
        return null;
    }

    private EffectTheme createRandomTheme(MediaItem mediaItem) {
        EffectTheme[] storyEffects = EffectTheme.getStoryEffects(mediaItem);
        return storyEffects[RandomNumber.nextInt(storyEffects.length)];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateBgmToDb$1(MediaItem mediaItem, boolean z, String str) {
        StoryApi storyApi = DbCompat.storyApi();
        int maskBgmSyncFlag = maskBgmSyncFlag(storyApi.getStoryUpdatedByUser(mediaItem.getAlbumID()), z);
        if (storyApi.updateStoryBgmInfo(mediaItem.getAlbumID(), str, maskBgmSyncFlag)) {
            Log.d("ThemeUpdater", "updateBgmToDb is successful", Integer.valueOf(maskBgmSyncFlag));
        } else {
            Log.e((CharSequence) "ThemeUpdater", "updateBgmToDb is fail", Logger.getEncodedString(str));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateFilterToDb$2(MediaItem mediaItem, boolean z, String str, Filter filter) {
        StoryApi storyApi = DbCompat.storyApi();
        if (storyApi.updateStoryFilter(mediaItem.getAlbumID(), str, maskFilterSyncFlag(storyApi.getStoryUpdatedByUser(mediaItem.getAlbumID()), z))) {
            Log.d("ThemeUpdater", "updateFilterToDb is successful");
        } else {
            Log.e((CharSequence) "ThemeUpdater", "updateFilterToDb is fail", filter);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateThemeToDb$0(MediaItem mediaItem, String str, String str2, String str3, int i2) {
        if (DbCompat.storyApi().updateStoryThemeInfo(mediaItem.getAlbumID(), str, str2, str3, i2)) {
            Log.d("ThemeUpdater", "updateThemeToDb is successful");
        } else {
            Log.e("ThemeUpdater", "updateThemeToDb is failed");
        }
    }

    private static int maskBgmSyncFlag(int i2, boolean z) {
        if (z) {
            return maskSyncFlag(i2, true) & -3;
        }
        return i2 | 2;
    }

    private static int maskFilterSyncFlag(int i2, boolean z) {
        if (z) {
            return maskSyncFlag(i2, true) & -5;
        }
        return i2 | 4;
    }

    private static int maskSyncFlag(int i2, boolean z) {
        if (z) {
            return i2 | 1;
        }
        return i2 & -2;
    }

    private void setChanged() {
        this.mIsChanged = true;
    }

    public static void updateBgmToDb(MediaItem mediaItem, BgmExtraInfo bgmExtraInfo, boolean z) {
        Log.d("ThemeUpdater", "updateBgmToDb", bgmExtraInfo);
        if (mediaItem == null) {
            Log.e("ThemeUpdater", "updateBgmToDb fail due to no item");
            return;
        }
        SimpleThreadPool.getInstance().execute(new g((Object) mediaItem, z, (Object) BgmExtraInfo.getBgmExtraInfoString(bgmExtraInfo), 17));
    }

    public static void updateFilterToDb(MediaItem mediaItem, Filter filter, boolean z) {
        String rawName;
        Log.d("ThemeUpdater", "updateFilterToDb", filter);
        if (mediaItem == null) {
            Log.e("ThemeUpdater", "updateFilterToDb fail due to no item");
            return;
        }
        if (filter != null) {
            rawName = filter.getRawName();
        } else {
            rawName = Filter.NONE.getRawName();
        }
        SimpleThreadPool.getInstance().execute(new a((Object) mediaItem, z, (Object) rawName, (Object) filter, 11));
    }

    public static void updateThemeToDb(MediaItem mediaItem, EffectTheme effectTheme, Filter filter, BgmExtraInfo bgmExtraInfo, int i2) {
        String rawName;
        Log.d("ThemeUpdater", "updateThemeToDb");
        if (mediaItem == null) {
            Log.e("ThemeUpdater", "updateThemeToDb fail due to no item");
            return;
        }
        String name = effectTheme.name();
        if (filter != null) {
            rawName = filter.getRawName();
        } else {
            rawName = effectTheme.getFilter().getRawName();
        }
        String str = rawName;
        SimpleThreadPool.getInstance().execute(new D7.a(mediaItem, name, str, BgmExtraInfo.getBgmExtraInfoString(bgmExtraInfo), i2));
    }

    public BgmExtraInfo getBgmExtraInfo() {
        return this.mBgmExtraInfo;
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public String getSyncFlags() {
        return this.mOriginSyncFlags + "/" + this.mUpdateSyncFlags;
    }

    public EffectTheme getTheme() {
        return this.mTheme;
    }

    public ThemeUpdater setBgm(String str) {
        BgmExtraInfo createBgmInfoIfValid = createBgmInfoIfValid(str);
        this.mBgmExtraInfo = createBgmInfoIfValid;
        if (createBgmInfoIfValid == null) {
            Log.d("ThemeUpdater", "invalid sound", Logger.getEncodedString(str));
            this.mBgmExtraInfo = BgmHelper.createBgmExtraInfo(this.mEventHandler, this.mTheme, false, (String) null);
            this.mUpdateSyncFlags = maskBgmSyncFlag(this.mUpdateSyncFlags, false);
            setChanged();
        }
        return this;
    }

    public ThemeUpdater setFilter(String str) {
        Filter filter;
        boolean z = SUPPORT_FILTER;
        if (z) {
            filter = FilterHolder.findCurrentSepFilter(str);
        } else {
            filter = Filter.NONE;
        }
        this.mFilter = filter;
        if (z && Filter.hasFilter(str)) {
            return this;
        }
        this.mUpdateSyncFlags = maskFilterSyncFlag(this.mUpdateSyncFlags, false);
        setChanged();
        return this;
    }

    public ThemeUpdater setTheme(String str) {
        EffectTheme effectTheme = EffectTheme.get(str);
        this.mTheme = effectTheme;
        if (effectTheme == null) {
            this.mTheme = createRandomTheme(this.mItem);
            this.mUpdateSyncFlags = maskSyncFlag(this.mUpdateSyncFlags, true);
            setChanged();
        }
        return this;
    }

    public ThemeUpdater setupTheme() {
        EffectTheme createRandomTheme = createRandomTheme(this.mItem);
        this.mTheme = createRandomTheme;
        this.mBgmExtraInfo = BgmHelper.createBgmExtraInfo(this.mEventHandler, createRandomTheme, false, (String) null);
        this.mFilter = Filter.NONE;
        int maskSyncFlag = maskSyncFlag(this.mUpdateSyncFlags, true);
        this.mUpdateSyncFlags = maskSyncFlag;
        if (!SUPPORT_FILTER) {
            this.mUpdateSyncFlags = maskFilterSyncFlag(maskSyncFlag, false);
        }
        setChanged();
        return this;
    }

    public void updateDbIfNecessary() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            int i2 = this.mOriginSyncFlags;
            int i7 = this.mUpdateSyncFlags;
            if (i2 != i7 || this.mIsChanged) {
                updateThemeToDb(this.mItem, this.mTheme, this.mFilter, this.mBgmExtraInfo, i7);
            }
        }
    }
}
