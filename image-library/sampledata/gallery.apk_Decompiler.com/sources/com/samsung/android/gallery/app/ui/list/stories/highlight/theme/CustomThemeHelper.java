package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.Arrays;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CustomThemeHelper {
    public static ThemeItem loadCustomFilterItem(MediaItem mediaItem) {
        String loadString = GalleryPreference.getInstance().loadString(PreferenceName.STORY_HIGHLIGHT_CUSTOM_THEME, "");
        if (TextUtils.isEmpty(loadString)) {
            return null;
        }
        String[] split = loadString.split("%#1A!@");
        EffectTheme effectTheme = EffectTheme.get(split[0]);
        if (effectTheme == null) {
            return null;
        }
        Filter filter = Filter.NONE;
        if (!filter.name().equals(split[1])) {
            filter = effectTheme.getFilter();
        }
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = split[2];
        bgmExtraInfo.path = split[3];
        bgmExtraInfo.isFragmentedBgm = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(split[4]);
        bgmExtraInfo.isMyMusic = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(split[5]);
        ThemeItem themeItem = new ThemeItem(mediaItem, effectTheme, filter, true);
        themeItem.setBgmExtraInfo(bgmExtraInfo);
        return themeItem;
    }

    public static ThemeItem loadCustomThemeItemForDefaultTheme(IStoryHighlightView iStoryHighlightView, MediaItem mediaItem) {
        EventHandler eventHandler = iStoryHighlightView.getEventHandler();
        if (!eventHandler.isCustomTheme()) {
            return null;
        }
        ThemeItem themeItem = new ThemeItem(mediaItem, eventHandler.getEffectTheme(), eventHandler.getFilter(), true);
        themeItem.setBgmExtraInfo(eventHandler.getBgmExtraInfo());
        return themeItem;
    }

    public static void reset() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            GalleryPreference.getInstance().removeState(PreferenceName.STORY_HIGHLIGHT_CUSTOM_THEME);
        }
    }

    public static boolean saveCustomTheme(EffectTheme effectTheme, Filter filter, BgmExtraInfo bgmExtraInfo) {
        String str;
        if (Arrays.asList(effectTheme.getBgmList()).contains(bgmExtraInfo.bgmName)) {
            return false;
        }
        StringJoiner stringJoiner = new StringJoiner("%#1A!@");
        stringJoiner.add(effectTheme.name());
        stringJoiner.add(filter.name());
        stringJoiner.add(bgmExtraInfo.bgmName);
        if (TextUtils.isEmpty(bgmExtraInfo.path)) {
            str = "";
        } else {
            str = bgmExtraInfo.path;
        }
        stringJoiner.add(str);
        stringJoiner.add("" + bgmExtraInfo.isFragmentedBgm);
        stringJoiner.add("" + bgmExtraInfo.isMyMusic);
        GalleryPreference.getInstance().saveState(PreferenceName.STORY_HIGHLIGHT_CUSTOM_THEME, stringJoiner.toString());
        return true;
    }
}
