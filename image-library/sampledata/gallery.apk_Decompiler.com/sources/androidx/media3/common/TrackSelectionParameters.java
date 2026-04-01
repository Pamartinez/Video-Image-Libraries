package androidx.media3.common;

import F2.C0010c0;
import F2.C0040v;
import F2.G;
import F2.U;
import F2.Y;
import F2.y0;
import androidx.media3.common.util.Util;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrackSelectionParameters {
    public static final TrackSelectionParameters DEFAULT;
    @Deprecated
    public static final TrackSelectionParameters DEFAULT_WITHOUT_CONTEXT;
    private static final String FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = Util.intToStringMaxRadix(27);
    private static final String FIELD_AUDIO_OFFLOAD_PREFERENCES = Util.intToStringMaxRadix(30);
    private static final String FIELD_DISABLED_TRACK_TYPE = Util.intToStringMaxRadix(24);
    private static final String FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE = Util.intToStringMaxRadix(22);
    private static final String FIELD_FORCE_LOWEST_BITRATE = Util.intToStringMaxRadix(21);
    private static final String FIELD_IGNORED_TEXT_SELECTION_FLAGS = Util.intToStringMaxRadix(26);
    private static final String FIELD_IS_GAPLESS_SUPPORT_REQUIRED = Util.intToStringMaxRadix(28);
    private static final String FIELD_IS_PREFER_IMAGE_OVER_VIDEO_ENABLED = Util.intToStringMaxRadix(31);
    private static final String FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = Util.intToStringMaxRadix(29);
    private static final String FIELD_IS_VIEWPORT_SIZE_LIMITED_BY_PHYSICAL_DISPLAY_SIZE = Util.intToStringMaxRadix(33);
    private static final String FIELD_MAX_AUDIO_BITRATE = Util.intToStringMaxRadix(19);
    private static final String FIELD_MAX_AUDIO_CHANNEL_COUNT = Util.intToStringMaxRadix(18);
    private static final String FIELD_MAX_VIDEO_BITRATE = Util.intToStringMaxRadix(9);
    private static final String FIELD_MAX_VIDEO_FRAMERATE = Util.intToStringMaxRadix(8);
    private static final String FIELD_MAX_VIDEO_HEIGHT = Util.intToStringMaxRadix(7);
    private static final String FIELD_MAX_VIDEO_WIDTH = Util.intToStringMaxRadix(6);
    private static final String FIELD_MIN_VIDEO_BITRATE = Util.intToStringMaxRadix(13);
    private static final String FIELD_MIN_VIDEO_FRAMERATE = Util.intToStringMaxRadix(12);
    private static final String FIELD_MIN_VIDEO_HEIGHT = Util.intToStringMaxRadix(11);
    private static final String FIELD_MIN_VIDEO_WIDTH = Util.intToStringMaxRadix(10);
    private static final String FIELD_PREFERRED_AUDIO_LANGUAGES = Util.intToStringMaxRadix(1);
    private static final String FIELD_PREFERRED_AUDIO_MIME_TYPES = Util.intToStringMaxRadix(20);
    private static final String FIELD_PREFERRED_AUDIO_ROLE_FLAGS = Util.intToStringMaxRadix(2);
    private static final String FIELD_PREFERRED_TEXT_LANGUAGES = Util.intToStringMaxRadix(3);
    private static final String FIELD_PREFERRED_TEXT_ROLE_FLAGS = Util.intToStringMaxRadix(4);
    private static final String FIELD_PREFERRED_VIDEO_LANGUAGES = Util.intToStringMaxRadix(32);
    private static final String FIELD_PREFERRED_VIDEO_MIMETYPES = Util.intToStringMaxRadix(17);
    private static final String FIELD_PREFERRED_VIDEO_ROLE_FLAGS = Util.intToStringMaxRadix(25);
    private static final String FIELD_SELECTION_OVERRIDES = Util.intToStringMaxRadix(23);
    private static final String FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE = Util.intToStringMaxRadix(5);
    private static final String FIELD_USE_PREFERRED_TEXT_LANGUAGES_AND_ROLE_FLAGS_FROM_CAPTIONING_MANAGER = Util.intToStringMaxRadix(34);
    private static final String FIELD_VIEWPORT_HEIGHT = Util.intToStringMaxRadix(15);
    private static final String FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE = Util.intToStringMaxRadix(16);
    private static final String FIELD_VIEWPORT_WIDTH = Util.intToStringMaxRadix(14);
    public final AudioOffloadPreferences audioOffloadPreferences;
    public final C0010c0 disabledTrackTypes;
    public final boolean forceHighestSupportedBitrate;
    public final boolean forceLowestBitrate;
    public final int ignoredTextSelectionFlags;
    public final boolean isPrioritizeImageOverVideoEnabled;
    public final boolean isViewportSizeLimitedByPhysicalDisplaySize;
    public final int maxAudioBitrate;
    public final int maxAudioChannelCount;
    public final int maxVideoBitrate;
    public final int maxVideoFrameRate;
    public final int maxVideoHeight;
    public final int maxVideoWidth;
    public final int minVideoBitrate;
    public final int minVideoFrameRate;
    public final int minVideoHeight;
    public final int minVideoWidth;
    public final Y overrides;
    public final U preferredAudioLanguages;
    public final U preferredAudioMimeTypes;
    public final int preferredAudioRoleFlags;
    public final U preferredTextLanguages;
    public final int preferredTextRoleFlags;
    public final U preferredVideoLanguages;
    public final U preferredVideoMimeTypes;
    public final int preferredVideoRoleFlags;
    public final boolean selectUndeterminedTextLanguage;
    public final boolean usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
    public final int viewportHeight;
    public final boolean viewportOrientationMayChange;
    public final int viewportWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AudioOffloadPreferences {
        public static final AudioOffloadPreferences DEFAULT = new Builder().build();
        private static final String FIELD_AUDIO_OFFLOAD_MODE_PREFERENCE = Util.intToStringMaxRadix(1);
        private static final String FIELD_IS_GAPLESS_SUPPORT_REQUIRED = Util.intToStringMaxRadix(2);
        private static final String FIELD_IS_SPEED_CHANGE_SUPPORT_REQUIRED = Util.intToStringMaxRadix(3);
        public final int audioOffloadMode;
        public final boolean isGaplessSupportRequired;
        public final boolean isSpeedChangeSupportRequired;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public int audioOffloadMode = 0;
            /* access modifiers changed from: private */
            public boolean isGaplessSupportRequired = false;
            /* access modifiers changed from: private */
            public boolean isSpeedChangeSupportRequired = false;

            public AudioOffloadPreferences build() {
                return new AudioOffloadPreferences(this);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && AudioOffloadPreferences.class == obj.getClass()) {
                AudioOffloadPreferences audioOffloadPreferences = (AudioOffloadPreferences) obj;
                if (this.audioOffloadMode == audioOffloadPreferences.audioOffloadMode && this.isGaplessSupportRequired == audioOffloadPreferences.isGaplessSupportRequired && this.isSpeedChangeSupportRequired == audioOffloadPreferences.isSpeedChangeSupportRequired) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return ((((this.audioOffloadMode + 31) * 31) + (this.isGaplessSupportRequired ? 1 : 0)) * 31) + (this.isSpeedChangeSupportRequired ? 1 : 0);
        }

        private AudioOffloadPreferences(Builder builder) {
            this.audioOffloadMode = builder.audioOffloadMode;
            this.isGaplessSupportRequired = builder.isGaplessSupportRequired;
            this.isSpeedChangeSupportRequired = builder.isSpeedChangeSupportRequired;
        }
    }

    static {
        TrackSelectionParameters build = new Builder().build();
        DEFAULT = build;
        DEFAULT_WITHOUT_CONTEXT = build;
    }

    public TrackSelectionParameters(Builder builder) {
        this.maxVideoWidth = builder.maxVideoWidth;
        this.maxVideoHeight = builder.maxVideoHeight;
        this.maxVideoFrameRate = builder.maxVideoFrameRate;
        this.maxVideoBitrate = builder.maxVideoBitrate;
        this.minVideoWidth = builder.minVideoWidth;
        this.minVideoHeight = builder.minVideoHeight;
        this.minVideoFrameRate = builder.minVideoFrameRate;
        this.minVideoBitrate = builder.minVideoBitrate;
        this.viewportWidth = builder.viewportWidth;
        this.viewportHeight = builder.viewportHeight;
        this.isViewportSizeLimitedByPhysicalDisplaySize = builder.isViewportSizeLimitedByPhysicalDisplaySize;
        this.viewportOrientationMayChange = builder.viewportOrientationMayChange;
        this.preferredVideoMimeTypes = builder.preferredVideoMimeTypes;
        this.preferredVideoLanguages = builder.preferredVideoLanguages;
        this.preferredVideoRoleFlags = builder.preferredVideoRoleFlags;
        this.preferredAudioLanguages = builder.preferredAudioLanguages;
        this.preferredAudioRoleFlags = builder.preferredAudioRoleFlags;
        this.maxAudioChannelCount = builder.maxAudioChannelCount;
        this.maxAudioBitrate = builder.maxAudioBitrate;
        this.preferredAudioMimeTypes = builder.preferredAudioMimeTypes;
        this.audioOffloadPreferences = builder.audioOffloadPreferences;
        this.preferredTextLanguages = builder.preferredTextLanguages;
        this.preferredTextRoleFlags = builder.preferredTextRoleFlags;
        this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = builder.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
        this.ignoredTextSelectionFlags = builder.ignoredTextSelectionFlags;
        this.selectUndeterminedTextLanguage = builder.selectUndeterminedTextLanguage;
        this.isPrioritizeImageOverVideoEnabled = builder.isPrioritizeImageOverVideoEnabled;
        this.forceLowestBitrate = builder.forceLowestBitrate;
        this.forceHighestSupportedBitrate = builder.forceHighestSupportedBitrate;
        this.overrides = Y.a(builder.overrides);
        this.disabledTrackTypes = C0010c0.y(builder.disabledTrackTypes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TrackSelectionParameters trackSelectionParameters = (TrackSelectionParameters) obj;
            if (this.maxVideoWidth == trackSelectionParameters.maxVideoWidth && this.maxVideoHeight == trackSelectionParameters.maxVideoHeight && this.maxVideoFrameRate == trackSelectionParameters.maxVideoFrameRate && this.maxVideoBitrate == trackSelectionParameters.maxVideoBitrate && this.minVideoWidth == trackSelectionParameters.minVideoWidth && this.minVideoHeight == trackSelectionParameters.minVideoHeight && this.minVideoFrameRate == trackSelectionParameters.minVideoFrameRate && this.minVideoBitrate == trackSelectionParameters.minVideoBitrate && this.viewportOrientationMayChange == trackSelectionParameters.viewportOrientationMayChange && this.viewportWidth == trackSelectionParameters.viewportWidth && this.viewportHeight == trackSelectionParameters.viewportHeight && this.isViewportSizeLimitedByPhysicalDisplaySize == trackSelectionParameters.isViewportSizeLimitedByPhysicalDisplaySize && this.preferredVideoMimeTypes.equals(trackSelectionParameters.preferredVideoMimeTypes) && this.preferredVideoLanguages.equals(trackSelectionParameters.preferredVideoLanguages) && this.preferredVideoRoleFlags == trackSelectionParameters.preferredVideoRoleFlags && this.preferredAudioLanguages.equals(trackSelectionParameters.preferredAudioLanguages) && this.preferredAudioRoleFlags == trackSelectionParameters.preferredAudioRoleFlags && this.maxAudioChannelCount == trackSelectionParameters.maxAudioChannelCount && this.maxAudioBitrate == trackSelectionParameters.maxAudioBitrate && this.preferredAudioMimeTypes.equals(trackSelectionParameters.preferredAudioMimeTypes) && this.audioOffloadPreferences.equals(trackSelectionParameters.audioOffloadPreferences) && this.preferredTextLanguages.equals(trackSelectionParameters.preferredTextLanguages) && this.preferredTextRoleFlags == trackSelectionParameters.preferredTextRoleFlags && this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager == trackSelectionParameters.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager && this.ignoredTextSelectionFlags == trackSelectionParameters.ignoredTextSelectionFlags && this.selectUndeterminedTextLanguage == trackSelectionParameters.selectUndeterminedTextLanguage && this.isPrioritizeImageOverVideoEnabled == trackSelectionParameters.isPrioritizeImageOverVideoEnabled && this.forceLowestBitrate == trackSelectionParameters.forceLowestBitrate && this.forceHighestSupportedBitrate == trackSelectionParameters.forceHighestSupportedBitrate) {
                Y y = this.overrides;
                Y y8 = trackSelectionParameters.overrides;
                y.getClass();
                if (!C0040v.e(y8, y) || !this.disabledTrackTypes.equals(trackSelectionParameters.disabledTrackTypes)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.preferredVideoMimeTypes.hashCode();
        int hashCode2 = this.preferredVideoLanguages.hashCode();
        int hashCode3 = this.preferredAudioLanguages.hashCode();
        int hashCode4 = this.preferredAudioMimeTypes.hashCode();
        int hashCode5 = this.audioOffloadPreferences.hashCode();
        int hashCode6 = this.preferredTextLanguages.hashCode();
        int hashCode7 = this.overrides.hashCode();
        return this.disabledTrackTypes.hashCode() + ((hashCode7 + ((((((((((((((((hashCode6 + ((hashCode5 + ((hashCode4 + ((((((((hashCode3 + ((((hashCode2 + ((hashCode + ((((((((((((((((((((((((this.maxVideoWidth + 31) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.maxVideoBitrate) * 31) + this.minVideoWidth) * 31) + this.minVideoHeight) * 31) + this.minVideoFrameRate) * 31) + this.minVideoBitrate) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + (this.isViewportSizeLimitedByPhysicalDisplaySize ? 1 : 0)) * 31)) * 31)) * 31) + this.preferredVideoRoleFlags) * 31)) * 31) + this.preferredAudioRoleFlags) * 31) + this.maxAudioChannelCount) * 31) + this.maxAudioBitrate) * 31)) * 31)) * 31)) * 31) + this.preferredTextRoleFlags) * 31) + (this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager ? 1 : 0)) * 31) + this.ignoredTextSelectionFlags) * 31) + (this.selectUndeterminedTextLanguage ? 1 : 0)) * 31) + (this.isPrioritizeImageOverVideoEnabled ? 1 : 0)) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.forceHighestSupportedBitrate ? 1 : 0)) * 31)) * 31);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public AudioOffloadPreferences audioOffloadPreferences;
        /* access modifiers changed from: private */
        public HashSet<Integer> disabledTrackTypes;
        /* access modifiers changed from: private */
        public boolean forceHighestSupportedBitrate;
        /* access modifiers changed from: private */
        public boolean forceLowestBitrate;
        /* access modifiers changed from: private */
        public int ignoredTextSelectionFlags;
        /* access modifiers changed from: private */
        public boolean isPrioritizeImageOverVideoEnabled;
        /* access modifiers changed from: private */
        public boolean isViewportSizeLimitedByPhysicalDisplaySize;
        /* access modifiers changed from: private */
        public int maxAudioBitrate;
        /* access modifiers changed from: private */
        public int maxAudioChannelCount;
        /* access modifiers changed from: private */
        public int maxVideoBitrate;
        /* access modifiers changed from: private */
        public int maxVideoFrameRate;
        /* access modifiers changed from: private */
        public int maxVideoHeight;
        /* access modifiers changed from: private */
        public int maxVideoWidth;
        /* access modifiers changed from: private */
        public int minVideoBitrate;
        /* access modifiers changed from: private */
        public int minVideoFrameRate;
        /* access modifiers changed from: private */
        public int minVideoHeight;
        /* access modifiers changed from: private */
        public int minVideoWidth;
        /* access modifiers changed from: private */
        public HashMap<TrackGroup, Object> overrides;
        /* access modifiers changed from: private */
        public U preferredAudioLanguages;
        /* access modifiers changed from: private */
        public U preferredAudioMimeTypes;
        /* access modifiers changed from: private */
        public int preferredAudioRoleFlags;
        /* access modifiers changed from: private */
        public U preferredTextLanguages;
        /* access modifiers changed from: private */
        public int preferredTextRoleFlags;
        /* access modifiers changed from: private */
        public U preferredVideoLanguages;
        /* access modifiers changed from: private */
        public U preferredVideoMimeTypes;
        /* access modifiers changed from: private */
        public int preferredVideoRoleFlags;
        /* access modifiers changed from: private */
        public boolean selectUndeterminedTextLanguage;
        /* access modifiers changed from: private */
        public boolean usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
        /* access modifiers changed from: private */
        public int viewportHeight;
        /* access modifiers changed from: private */
        public boolean viewportOrientationMayChange;
        /* access modifiers changed from: private */
        public int viewportWidth;

        public Builder() {
            this.maxVideoWidth = Integer.MAX_VALUE;
            this.maxVideoHeight = Integer.MAX_VALUE;
            this.maxVideoFrameRate = Integer.MAX_VALUE;
            this.maxVideoBitrate = Integer.MAX_VALUE;
            this.viewportWidth = Integer.MAX_VALUE;
            this.viewportHeight = Integer.MAX_VALUE;
            this.isViewportSizeLimitedByPhysicalDisplaySize = true;
            this.viewportOrientationMayChange = true;
            G g = U.e;
            y0 y0Var = y0.f278h;
            this.preferredVideoMimeTypes = y0Var;
            this.preferredVideoLanguages = y0Var;
            this.preferredVideoRoleFlags = 0;
            this.preferredAudioLanguages = y0Var;
            this.preferredAudioRoleFlags = 0;
            this.maxAudioChannelCount = Integer.MAX_VALUE;
            this.maxAudioBitrate = Integer.MAX_VALUE;
            this.preferredAudioMimeTypes = y0Var;
            this.audioOffloadPreferences = AudioOffloadPreferences.DEFAULT;
            this.preferredTextLanguages = y0Var;
            this.preferredTextRoleFlags = 0;
            this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = true;
            this.ignoredTextSelectionFlags = 0;
            this.selectUndeterminedTextLanguage = false;
            this.isPrioritizeImageOverVideoEnabled = false;
            this.forceLowestBitrate = false;
            this.forceHighestSupportedBitrate = false;
            this.overrides = new HashMap<>();
            this.disabledTrackTypes = new HashSet<>();
        }

        private void init(TrackSelectionParameters trackSelectionParameters) {
            this.maxVideoWidth = trackSelectionParameters.maxVideoWidth;
            this.maxVideoHeight = trackSelectionParameters.maxVideoHeight;
            this.maxVideoFrameRate = trackSelectionParameters.maxVideoFrameRate;
            this.maxVideoBitrate = trackSelectionParameters.maxVideoBitrate;
            this.minVideoWidth = trackSelectionParameters.minVideoWidth;
            this.minVideoHeight = trackSelectionParameters.minVideoHeight;
            this.minVideoFrameRate = trackSelectionParameters.minVideoFrameRate;
            this.minVideoBitrate = trackSelectionParameters.minVideoBitrate;
            this.viewportWidth = trackSelectionParameters.viewportWidth;
            this.viewportHeight = trackSelectionParameters.viewportHeight;
            this.isViewportSizeLimitedByPhysicalDisplaySize = trackSelectionParameters.isViewportSizeLimitedByPhysicalDisplaySize;
            this.viewportOrientationMayChange = trackSelectionParameters.viewportOrientationMayChange;
            this.preferredVideoMimeTypes = trackSelectionParameters.preferredVideoMimeTypes;
            this.preferredVideoLanguages = trackSelectionParameters.preferredVideoLanguages;
            this.preferredVideoRoleFlags = trackSelectionParameters.preferredVideoRoleFlags;
            this.preferredAudioLanguages = trackSelectionParameters.preferredAudioLanguages;
            this.preferredAudioRoleFlags = trackSelectionParameters.preferredAudioRoleFlags;
            this.maxAudioChannelCount = trackSelectionParameters.maxAudioChannelCount;
            this.maxAudioBitrate = trackSelectionParameters.maxAudioBitrate;
            this.preferredAudioMimeTypes = trackSelectionParameters.preferredAudioMimeTypes;
            this.audioOffloadPreferences = trackSelectionParameters.audioOffloadPreferences;
            this.preferredTextLanguages = trackSelectionParameters.preferredTextLanguages;
            this.preferredTextRoleFlags = trackSelectionParameters.preferredTextRoleFlags;
            this.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager = trackSelectionParameters.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager;
            this.ignoredTextSelectionFlags = trackSelectionParameters.ignoredTextSelectionFlags;
            this.selectUndeterminedTextLanguage = trackSelectionParameters.selectUndeterminedTextLanguage;
            this.isPrioritizeImageOverVideoEnabled = trackSelectionParameters.isPrioritizeImageOverVideoEnabled;
            this.forceLowestBitrate = trackSelectionParameters.forceLowestBitrate;
            this.forceHighestSupportedBitrate = trackSelectionParameters.forceHighestSupportedBitrate;
            this.disabledTrackTypes = new HashSet<>(trackSelectionParameters.disabledTrackTypes);
            this.overrides = new HashMap<>(trackSelectionParameters.overrides);
        }

        public TrackSelectionParameters build() {
            return new TrackSelectionParameters(this);
        }

        public Builder set(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
            return this;
        }

        public Builder setForceHighestSupportedBitrate(boolean z) {
            this.forceHighestSupportedBitrate = z;
            return this;
        }

        public Builder(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
        }
    }
}
