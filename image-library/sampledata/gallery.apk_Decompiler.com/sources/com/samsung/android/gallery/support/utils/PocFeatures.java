package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PocFeatures implements BooleanFeatures {
    LockedAlbum((String) false),
    StoryAppBar((String) false),
    RecoverLastStack((String) false),
    ShowCpuClock((String) false),
    ShowTrashStorage((String) false),
    GmpLocOnly((String) new C0671i(3)),
    GmpAll((String) new C0671i(4)),
    TimelineStableIds((String) false),
    MediumCacheEnhance((String) false),
    ImageFilterAlways((String) false),
    DetailsVI((String) false),
    InsensitiveFastScroll((String) false),
    AdaptiveFastScroll((String) false),
    UndoDelete((String) false),
    ThumbnailPreviewHdr((String) false),
    AlbumSortByDateModified((String) false),
    ExpandedViewNavWidget((String) false),
    PhotoStripHighQualityPreview((String) false),
    DualPhotoPreview((String) 2, (int) false),
    DebugSmartCropRectInfo((String) false),
    UseSwitchableDialog((String) false),
    SimilarSpannable((String) false),
    CloudVideoStreamingPreview((String) false),
    DateTimeLocationRestore((String) false),
    RegionDecodingInfo((String) false),
    UndoPeopleMerge((String) false),
    VideoAutoPlaybackNext((String) false),
    PresentationEnabled((String) true),
    Viewer2DebugTxt((String) false),
    DoubleTapSeek((String) false),
    PreviewVideoOnPageChanged((String) false),
    EnhancedVideoThumbnail((String) false),
    ShowNameMergedAlbumIcon((String) 1, (int) false),
    DebugFaceRect((String) false),
    SaveAsPdf((String) false),
    PrintMultiple((String) false),
    SkipAliveZoomOutput((String) false),
    SupportAliveZoom((String) true),
    ShowVirtualAlbums((String) true),
    ObjectCaptureDebug((String) false),
    SavePppTempImage((String) false),
    CleanOutBurstSimilar((String) false),
    AMapPreferred((String) false),
    MediaView((String) true),
    FilmSmoothScroll((String) true),
    WifiGalleryClient((String) 1, (int) false),
    WifiGallery((String) 1, (int) false),
    QuickSearch((String) false),
    AlbumCoverSync((String) false),
    StoryOriginScaleWhenPaused((String) false),
    GalleryLabs("gallery_labs", (int) false),
    GalleryLabsDev("gallery_labs_developer", (int) false),
    DumpSlowMessage((String) false),
    PerformanceLog((String) new C0671i(5)),
    MoreInfoExif((String) false),
    MoreInfoDebug((String) false),
    AospCodecDecoding("aosp_codec_decoding", (int) false),
    SearchDebugInfo((String) false),
    AlbumAutoGroup((String) false),
    GotoStudioMenuEnabled((String) true),
    SearchJumpToTimeline((String) false),
    QuickDelete((String) new C0671i(6)),
    StoryContentsReorder((String) new C0671i(7)),
    FileOpService2((String) true),
    SlideshowWithSelectedContents((String) false),
    SlideshowV2Bgm((String) false),
    ExportPhotoMotionClips((String) false),
    ClipParallel((String) new C0671i(8)),
    OpenInOtherWindow((String) false),
    CompareImages((String) false),
    PrivateAlbum((String) false),
    AlbumMonthExtend((String) new C0671i(9)),
    SetAudioUnMuteAlways((String) false),
    SetAudioUnMuteUntilAppDestroy((String) false),
    BurstShotSeeker((String) false),
    OpenNavigation((String) true),
    StoriesIrregularCover((String) false),
    SearchLog((String) false),
    ShowSearchLog((String) false),
    GotoAppLink((String) false),
    C2paFileEdit((String) true),
    C2paSecMp((String) false),
    FullAddressDisplay((String) false),
    SearchForegroundAnalysis((String) false),
    RemoveBackgroundEffectInfo((String) false),
    CreatureMultiPick((String) false),
    MapViewBlur((String) false),
    BottomTabV2((String) false),
    ImageCodec2((String) false),
    FragmentPredictiveBack((String) new C0671i(2)),
    DownloadResumeService((String) false),
    Recap((String) false),
    GradientProtection((String) false),
    ViewerGradientProtection((String) true),
    ViewerZoomedOsd((String) true),
    AskScreenshot((String) false),
    GalleryGradientBlur((String) false),
    RobinService((String) false),
    RecapStackUi((String) false),
    ScreenshotWithSubSubCat((String) false),
    RecapSharedTransition((String) false),
    RecapCategory((String) false),
    EOF((String) false);
    
    public static final boolean ALBUM_COVER_SYNC = false;
    public static final boolean ALBUM_PICTURES_EXTEND = false;
    public static final boolean ASK_SCREENSHOT = false;
    public static final boolean CLIP_PARALLEL = false;
    public static final boolean DEBUG_FACE_RECT = false;
    public static final boolean DEBUG_VIEWER2 = false;
    public static final boolean DUAL_PHOTO_PREVIEW = false;
    public static final boolean FILM_SMOOTH_SCROLL = false;
    public static final boolean PRESENTATION_ENABLED = false;
    public static final boolean QUICK_SEARCH = false;
    public static final boolean RECAP_SHARED_TRANSITION = false;
    public static boolean RESTORE_DATETIME_LOCATION = false;
    public static final boolean SHARE_STORY_VIA_QUICKSHARE = false;
    public static final boolean SHOW_VIRTUAL_ALBUMS = false;
    public static final boolean SLIDESHOW_AUTO_REPLAY = true;
    public static final boolean SLIDESHOW_SELECTED_ITEMS = false;
    public static final boolean STORY_APPBAR = false;
    public static final boolean STORY_ORIGIN_SCALE_WHEN_PAUSE = false;
    public static boolean SUPPORT_CLOUD_VIDEO_STREAMING_PREVIEW = false;
    public static boolean SUPPORT_LOCKED_ALBUM = false;
    public static final boolean SUPPORT_OTG_ALBUM = false;
    public static final boolean SUPPORT_PRIVATE_ALBUM = false;
    public static final boolean SUPPORT_QUICK_DELETE = false;
    public static final boolean SUPPORT_RECAP_STACK_UI = false;
    public static final boolean SUPPORT_ROBIN_SERVICE = false;
    public static final boolean SUPPORT_SEARCH_JUMP_TO_TIMELINE = false;
    public static final boolean SUPPORT_SIMILAR_SPANNABLE = false;
    public static final boolean SUPPORT_STORY_AUTO_FRAME = false;
    private static final String TAG = "PocFeatures";
    public static final boolean THUMBNAIL_PREVIEW_HDR = false;
    public static boolean UNDO_DELETE;
    public static final boolean USE_ANDROID_CODEC = false;
    public static final boolean VIDEO_AUTO_PLAYBACK_NEXT = false;
    public static final boolean VIEWER_DETAILS_VI = false;
    private final BooleanSupplier mDefaultSupplier;
    private final boolean mDefaultValue;
    private Boolean mIsEnabled;
    private final String mPreferenceKey;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TBD {
        public static final boolean COMPARE_IMAGES = false;
        public static final boolean OPEN_IN_OTHER_WINDOW = false;
        public static final boolean RECOVER_LAST_STACK = false;

        static {
            RECOVER_LAST_STACK = PocFeatures.RecoverLastStack.isEnabled();
            OPEN_IN_OTHER_WINDOW = PocFeatures.isEnabled(PocFeatures.OpenInOtherWindow);
            COMPARE_IMAGES = PocFeatures.isEnabled(PocFeatures.CompareImages);
        }
    }

    static {
        PocFeatures pocFeatures;
        PocFeatures pocFeatures2;
        PocFeatures pocFeatures3;
        PocFeatures pocFeatures4;
        PocFeatures pocFeatures5;
        PocFeatures pocFeatures6;
        PocFeatures pocFeatures7;
        PocFeatures pocFeatures8;
        PocFeatures pocFeatures9;
        PocFeatures pocFeatures10;
        PocFeatures pocFeatures11;
        PocFeatures pocFeatures12;
        PocFeatures pocFeatures13;
        PocFeatures pocFeatures14;
        PocFeatures pocFeatures15;
        PocFeatures pocFeatures16;
        PocFeatures pocFeatures17;
        PocFeatures pocFeatures18;
        PocFeatures pocFeatures19;
        PocFeatures pocFeatures20;
        PocFeatures pocFeatures21;
        PocFeatures pocFeatures22;
        PocFeatures pocFeatures23;
        boolean z;
        VIEWER_DETAILS_VI = isEnabled(pocFeatures18);
        UNDO_DELETE = isEnabled(pocFeatures);
        THUMBNAIL_PREVIEW_HDR = isEnabled(pocFeatures2);
        DUAL_PHOTO_PREVIEW = isEnabled(pocFeatures3);
        VIDEO_AUTO_PLAYBACK_NEXT = isEnabled(pocFeatures6);
        SUPPORT_CLOUD_VIDEO_STREAMING_PREVIEW = isEnabled(pocFeatures4);
        SHOW_VIRTUAL_ALBUMS = isEnabled(pocFeatures9);
        SUPPORT_LOCKED_ALBUM = isEnabled(pocFeatures11);
        DEBUG_FACE_RECT = false;
        RESTORE_DATETIME_LOCATION = isEnabled(pocFeatures5);
        DEBUG_VIEWER2 = isEnabled(pocFeatures8);
        FILM_SMOOTH_SCROLL = isEnabled(pocFeatures10);
        QUICK_SEARCH = false;
        STORY_ORIGIN_SCALE_WHEN_PAUSE = isEnabled(pocFeatures12);
        SUPPORT_QUICK_DELETE = isEnabled(pocFeatures13);
        SUPPORT_SEARCH_JUMP_TO_TIMELINE = false;
        USE_ANDROID_CODEC = isEnabled(pocFeatures14);
        SLIDESHOW_SELECTED_ITEMS = isEnabled(pocFeatures15);
        CLIP_PARALLEL = isEnabled(pocFeatures16);
        ALBUM_COVER_SYNC = false;
        ALBUM_PICTURES_EXTEND = isEnabled(pocFeatures19);
        PRESENTATION_ENABLED = isEnabled(pocFeatures7);
        if ((SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || isEnabled(pocFeatures17)) && Features.isEnabled(Features.PRIVATE_ALBUM)) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT_PRIVATE_ALBUM = z;
        ASK_SCREENSHOT = isEnabled(pocFeatures20);
        SUPPORT_ROBIN_SERVICE = isEnabled(pocFeatures21);
        SUPPORT_RECAP_STACK_UI = isEnabled(pocFeatures22);
        RECAP_SHARED_TRANSITION = isEnabled(pocFeatures23);
    }

    private PocFeatures(boolean z) {
        this.mPreferenceKey = name();
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    public static String toDebugString() {
        GalleryPreference instance = GalleryPreference.getInstance();
        Stream map = Stream.of(values()).map(new C0670h(22));
        Objects.requireNonNull(instance);
        return C0212a.m("PocFeatures=[", (String) map.filter(new C0678p(1, instance)).map(new r(3, instance)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]");
    }

    public boolean getDefaultValue() {
        BooleanSupplier booleanSupplier = this.mDefaultSupplier;
        if (booleanSupplier == null) {
            return this.mDefaultValue;
        }
        return booleanSupplier.getAsBoolean();
    }

    public String getKey() {
        return this.mPreferenceKey;
    }

    public boolean isEnabled() {
        if (this.mIsEnabled == null) {
            GalleryPreference instance = GalleryPreference.getInstance();
            String str = this.mPreferenceKey;
            BooleanSupplier booleanSupplier = this.mDefaultSupplier;
            this.mIsEnabled = Boolean.valueOf(instance.loadBoolean(str, booleanSupplier != null ? booleanSupplier.getAsBoolean() : this.mDefaultValue));
        }
        return this.mIsEnabled.booleanValue();
    }

    public void recycle() {
        this.mIsEnabled = null;
    }

    public boolean setEnabled(boolean z) {
        Boolean bool = this.mIsEnabled;
        if (bool == null || bool.booleanValue() != z) {
            BooleanSupplier booleanSupplier = this.mDefaultSupplier;
            if ((booleanSupplier != null ? booleanSupplier.getAsBoolean() : this.mDefaultValue) == z) {
                GalleryPreference.getInstance().removeState(this.mPreferenceKey);
            } else {
                GalleryPreference.getInstance().saveState(this.mPreferenceKey, z);
            }
        }
        this.mIsEnabled = Boolean.valueOf(z);
        return true;
    }

    public boolean toggleEnabled() {
        boolean z = !isEnabled();
        setEnabled(z);
        return z;
    }

    public static boolean toggleEnabled(PocFeatures pocFeatures) {
        return pocFeatures.toggleEnabled();
    }

    public static boolean isEnabled(PocFeatures pocFeatures) {
        return pocFeatures.isEnabled();
    }

    private PocFeatures(String str, boolean z) {
        this.mPreferenceKey = str;
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    public static boolean setEnabled(PocFeatures pocFeatures, boolean z) {
        pocFeatures.setEnabled(z);
        return true;
    }

    private PocFeatures(int i2, boolean z) {
        this.mPreferenceKey = name() + i2;
        this.mDefaultValue = z;
        this.mDefaultSupplier = null;
    }

    private PocFeatures(BooleanSupplier booleanSupplier) {
        this.mPreferenceKey = name();
        this.mDefaultValue = false;
        this.mDefaultSupplier = booleanSupplier;
    }
}
