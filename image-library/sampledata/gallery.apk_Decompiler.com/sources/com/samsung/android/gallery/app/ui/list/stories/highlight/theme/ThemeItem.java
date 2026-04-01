package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.AppResources;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeItem {
    private BgmExtraInfo mBgmExtraInfo;
    private boolean mCustom;
    private final Filter mFilter;
    private final MediaItem mMediaItem;
    private EffectTheme mTheme;

    public ThemeItem(MediaItem mediaItem, Filter filter) {
        this.mMediaItem = mediaItem;
        this.mFilter = filter;
    }

    public BgmExtraInfo getBgmExtraInfo() {
        return this.mBgmExtraInfo;
    }

    public String getDisplayName() {
        if (this.mCustom) {
            return AppResources.getString(R.string.custom);
        }
        return this.mFilter.getDisplayName();
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public EffectTheme getTheme() {
        return this.mTheme;
    }

    public int getUniqueId() {
        String str;
        if (this.mCustom) {
            str = "$$custom";
        } else {
            str = this.mFilter.name();
        }
        return str.hashCode();
    }

    public boolean isCustom() {
        return this.mCustom;
    }

    public void setBgmExtraInfo(BgmExtraInfo bgmExtraInfo) {
        this.mBgmExtraInfo = bgmExtraInfo;
    }

    public ThemeItem(MediaItem mediaItem, EffectTheme effectTheme, Filter filter, boolean z) {
        this(mediaItem, filter);
        this.mTheme = effectTheme;
        this.mCustom = z;
    }
}
