package com.samsung.android.gallery.app.ui.list.stories.highlight;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightOptions<T extends IStoryHighlightView> extends Options {
    private Boolean mDefaultMuted;
    final T mView;

    public StoryHighlightOptions(T t) {
        this.mView = t;
    }

    private boolean fromDeepLink(LaunchIntent launchIntent) {
        String str;
        if (launchIntent != null) {
            str = LocationKey.parseDeepLink(launchIntent.getUriData());
        } else {
            str = "";
        }
        if (LocationKey.isStoryHighlight(str) || LocationKey.isStoryPictures(str)) {
            return true;
        }
        return false;
    }

    private boolean fromSearchCategory() {
        return LocationKey.isSearchCategoryStories(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "from_location", ""));
    }

    private boolean withSTOnCollection() {
        if (PocFeatures.RECAP_SHARED_TRANSITION || !fromSearchCategory()) {
            return true;
        }
        return false;
    }

    public boolean isAudioDefaultMuted() {
        if (this.mDefaultMuted == null) {
            this.mDefaultMuted = Boolean.valueOf(GalleryPreference.getInstance().loadBoolean(PreferenceName.STORY_HIGHLIGHT_AUDIO_MUTED, false));
        }
        return this.mDefaultMuted.booleanValue();
    }

    public boolean isFromExternal() {
        LaunchIntent launchIntent = (LaunchIntent) this.mView.getBlackboard().read("data://launch_intent");
        if (launchIntent == null) {
            return false;
        }
        if (launchIntent.isFromReminder() || launchIntent.isViewStoryPicture() || fromDeepLink(launchIntent)) {
            return true;
        }
        return false;
    }

    public boolean isFromTransitoryStory() {
        if (StoryType.isTransitoryType(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "type", 0)) || StoryType.isTransitoryType(MediaItemStory.getStoryType(this.mView.getHeaderItem()))) {
            return true;
        }
        return false;
    }

    public boolean isOnDemandStory() {
        return "location://stories/category/ondemand".equals(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "categoryKey", ""));
    }

    public boolean supportBgm() {
        if (!Features.isEnabled(Features.SUPPORT_BGM_SERVICE) || !SdkConfig.atLeast(SdkConfig.GED.T)) {
            return false;
        }
        return true;
    }

    public boolean supportBgmPicker() {
        return supportBgm();
    }

    public boolean supportEditFilter() {
        return supportFilter();
    }

    public boolean supportFilter() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter);
    }

    public boolean supportLiveEffect() {
        return PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE;
    }

    public boolean useDefaultEnterTransition() {
        if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || ((!isOnDemandStory() || withOnDemandVi()) && withSTOnCollection())) {
            return false;
        }
        return true;
    }

    public boolean useDefaultExitTransition() {
        if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            return false;
        }
        if (isOnDemandStory() || !withSTOnCollection()) {
            return true;
        }
        return false;
    }

    public boolean withOnDemandVi() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "launchWithOnDemandVi", false);
    }
}
