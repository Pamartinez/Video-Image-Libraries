package com.samsung.android.gallery.app.controller.story;

import Ob.a;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.effectfilter.FilterManager;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartHighlightPlayerEditCmd extends StartHighlightPlayerCmd {
    protected BgmExtraInfo mBgmExtraInfo;
    private BgmUriService mBgmUriService;
    protected String mEffectFilterName;
    protected String mSubTitle;
    private EffectTheme mTheme;
    protected String mThemeName;
    protected String mTitle;

    private String getAspectRatio() {
        if (ResourceCompat.isLandscape(getContext())) {
            return "16:9";
        }
        return "9:16";
    }

    private String getFilterPath(Filter filter) {
        return FilterManager.getInstance().getFilterRawPath(filter);
    }

    private ArrayList<int[]> getVideoPlaybackList(EventContext eventContext) {
        MediaItem[] allItems = eventContext.getAllItems();
        ArrayList<int[]> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : allItems) {
            if (!mediaItem.isVideo() || mediaItem.isBroken()) {
                arrayList.add(new int[0]);
            } else {
                arrayList.add(new int[]{0, Math.min(mediaItem.getFileDuration(), TextToSpeechConst.MAX_SPEECH_INPUT)});
            }
        }
        return arrayList;
    }

    private int isCustomThemeInt() {
        EffectTheme effectTheme;
        if (this.mBgmExtraInfo == null || (effectTheme = this.mTheme) == null || Arrays.asList(effectTheme.getBgmList()).contains(this.mBgmExtraInfo.bgmName)) {
            return 0;
        }
        return 1;
    }

    private int isOriginalFilterInt() {
        return Filter.NONE.getRawName().equalsIgnoreCase(this.mEffectFilterName) ? 1 : 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setBgmUrisIfEmpty$0(BgmExtraInfo bgmExtraInfo) {
        BgmUriService bgmUriService = this.mBgmUriService;
        if (bgmUriService == null) {
            bgmUriService = new BgmUriService();
        }
        ArrayList<Uri> urisSync = bgmUriService.getUrisSync(bgmExtraInfo.bgmName);
        bgmExtraInfo.uris = urisSync;
        if (urisSync == null) {
            bgmExtraInfo.uris = new ArrayList<>();
            Log.e(this.TAG, "fail to load bgm uris");
        }
    }

    private void printEditInfo(ExportOptions exportOptions) {
        String str;
        String str2;
        String str3 = this.TAG;
        ExportType type = ExportType.getType(exportOptions.getRequestCode());
        Integer valueOf = Integer.valueOf(exportOptions.getRequestCode());
        EffectTheme effectTheme = this.mTheme;
        String str4 = this.mThemeName;
        String str5 = this.mEffectFilterName;
        if (isCustomThemeInt() == 1) {
            str = "C";
        } else {
            str = "c";
        }
        String str6 = str;
        if (isOriginalFilterInt() == 1) {
            str2 = "O";
        } else {
            str2 = "o";
        }
        Log.v(str3, "Highlight Info", type, valueOf, effectTheme, str4, str5, str6, str2, this.mBgmExtraInfo);
    }

    private void setBgmUrisIfEmpty(BgmExtraInfo bgmExtraInfo) {
        Log.w((CharSequence) this.TAG, "no bgmUri", bgmExtraInfo.bgmName);
        new LatchBuilder("load_bgmUris").addWorker(new a(28, this, bgmExtraInfo)).setTimeout(1000).start();
    }

    private String verifyBgmPath(String str) {
        if ("null".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public Intent createIntent(EventContext eventContext, int i2) {
        Intent createIntent = super.createIntent(eventContext, i2);
        createIntent.putExtra("theme_name", this.mThemeName);
        createIntent.putExtra("title", this.mTitle);
        createIntent.putExtra("sub_title", this.mSubTitle);
        createIntent.putExtra("aspect_ratio", getAspectRatio());
        createIntent.putExtra("video_playback_list", getVideoPlaybackList(eventContext));
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter);
        createIntent.putExtra("support_filter", isEnabled);
        if (isEnabled) {
            Filter findCurrentSepFilter = FilterHolder.findCurrentSepFilter(this.mEffectFilterName);
            createIntent.putExtra("filter_name", this.mEffectFilterName);
            createIntent.putExtra("filter_path", getFilterPath(findCurrentSepFilter));
            createIntent.putExtra("filter_intensity", findCurrentSepFilter.getIntensity());
            createIntent.putExtra(ShareApi.ORIGINAL_SIZE_IMAGE, isOriginalFilterInt());
            createIntent.putExtra("custom", isCustomThemeInt());
        }
        BgmExtraInfo bgmExtraInfo = this.mBgmExtraInfo;
        if (bgmExtraInfo != null) {
            createIntent.putExtra("bgm_name", bgmExtraInfo.bgmName);
            createIntent.putExtra("file_path", verifyBgmPath(this.mBgmExtraInfo.path));
            createIntent.putExtra("isBgmFragmented", this.mBgmExtraInfo.isFragmentedBgm);
            createIntent.putExtra("is_mymusic", this.mBgmExtraInfo.isMyMusic);
            BgmExtraInfo bgmExtraInfo2 = this.mBgmExtraInfo;
            if (!bgmExtraInfo2.isMyMusic && !bgmExtraInfo2.hasUris()) {
                setBgmUrisIfEmpty(this.mBgmExtraInfo);
            }
            createIntent.putExtra("bgm_data", this.mBgmExtraInfo.uris);
            return createIntent;
        }
        Log.d(this.TAG, "no bgmInfo", Boolean.valueOf(Features.isEnabled(Features.SUPPORT_BGM_SERVICE)));
        return createIntent;
    }

    public final ExportOptions getExportOptions(Object... objArr) {
        return objArr[1];
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ExportOptions exportOptions = getExportOptions(objArr);
        this.mBgmUriService = objArr[2];
        this.mTitle = exportOptions.getTitle();
        this.mSubTitle = exportOptions.getSubTitle();
        EffectTheme theme = exportOptions.getTheme();
        this.mTheme = theme;
        this.mThemeName = EffectTheme.getHighlightEditShareKey(theme);
        this.mEffectFilterName = exportOptions.getFilterName();
        this.mBgmExtraInfo = exportOptions.getBgmExtraInfo();
        super.onExecute(eventContext, objArr);
        printEditInfo(exportOptions);
    }
}
