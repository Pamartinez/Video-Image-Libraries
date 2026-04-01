package androidx.media3.exoplayer.trackselection;

import B8.f;
import D6.a;
import E2.l;
import F2.B;
import F2.C;
import F2.E;
import F2.Q;
import F2.U;
import F2.v0;
import F2.w0;
import I.b;
import N.c;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.Spatializer;
import android.media.Spatializer$OnSpatializerStateChangedListener;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.accessibility.CaptioningManager;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultTrackSelector extends MappingTrackSelector implements RendererCapabilities.Listener {
    /* access modifiers changed from: private */
    public static final w0 FORMAT_VALUE_ORDERING = new B(new a(4));
    private AudioAttributes audioAttributes;
    public final Context context;
    private Boolean deviceIsTV;
    private final Object lock;
    private Parameters parameters;
    private Thread playbackThread;
    private SpatializerWrapperV32 spatializer;
    private final ExoTrackSelection.Factory trackSelectionFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AudioTrackInfo extends TrackInfo<AudioTrackInfo> implements Comparable<AudioTrackInfo> {
        private final boolean allowMixedMimeTypes;
        private final int bitrate;
        private final int channelCount;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean isDefaultSelectionFlag;
        private final boolean isObjectBasedAudio;
        private final boolean isWithinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final String language;
        private final int localeLanguageMatchIndex;
        private final int localeLanguageScore;
        private final Parameters parameters;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int sampleRate;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        public AudioTrackInfo(int i2, TrackGroup trackGroup, int i7, Parameters parameters2, int i8, boolean z, l lVar, int i10) {
            super(i2, trackGroup, i7);
            int i11;
            boolean z3;
            int i12;
            int i13;
            boolean z7;
            boolean z9;
            boolean z10;
            int i14;
            boolean z11;
            this.parameters = parameters2;
            if (parameters2.allowAudioNonSeamlessAdaptiveness) {
                i11 = 24;
            } else {
                i11 = 16;
            }
            boolean z12 = true;
            if (!parameters2.allowAudioMixedMimeTypeAdaptiveness || (i10 & i11) == 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.allowMixedMimeTypes = z3;
            this.language = DefaultTrackSelector.normalizeUndeterminedLanguageToNull(this.format.language);
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(i8, false);
            int i15 = 0;
            while (true) {
                i12 = Integer.MAX_VALUE;
                if (i15 >= parameters2.preferredAudioLanguages.size()) {
                    i13 = 0;
                    i15 = Integer.MAX_VALUE;
                    break;
                }
                i13 = DefaultTrackSelector.getFormatLanguageScore(this.format, (String) parameters2.preferredAudioLanguages.get(i15), false);
                if (i13 > 0) {
                    break;
                }
                i15++;
            }
            this.preferredLanguageIndex = i15;
            this.preferredLanguageScore = i13;
            this.preferredRoleFlagsScore = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, parameters2.preferredAudioRoleFlags);
            Format format = this.format;
            int i16 = format.roleFlags;
            if (i16 == 0 || (i16 & 1) != 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            this.hasMainOrNoRoleFlag = z7;
            if ((format.selectionFlags & 1) != 0) {
                z9 = true;
            } else {
                z9 = false;
            }
            this.isDefaultSelectionFlag = z9;
            this.isObjectBasedAudio = DefaultTrackSelector.isObjectBasedAudio(format);
            Format format2 = this.format;
            int i17 = format2.channelCount;
            this.channelCount = i17;
            this.sampleRate = format2.sampleRate;
            int i18 = format2.bitrate;
            this.bitrate = i18;
            if ((i18 == -1 || i18 <= parameters2.maxAudioBitrate) && ((i17 == -1 || i17 <= parameters2.maxAudioChannelCount) && lVar.apply(format2))) {
                z10 = true;
            } else {
                z10 = false;
            }
            this.isWithinConstraints = z10;
            String[] systemLanguageCodes = Util.getSystemLanguageCodes();
            int i19 = 0;
            while (true) {
                if (i19 >= systemLanguageCodes.length) {
                    i14 = 0;
                    i19 = Integer.MAX_VALUE;
                    break;
                }
                i14 = DefaultTrackSelector.getFormatLanguageScore(this.format, systemLanguageCodes[i19], false);
                if (i14 > 0) {
                    break;
                }
                i19++;
            }
            this.localeLanguageMatchIndex = i19;
            this.localeLanguageScore = i14;
            int i20 = 0;
            while (true) {
                if (i20 < parameters2.preferredAudioMimeTypes.size()) {
                    String str = this.format.sampleMimeType;
                    if (str != null && str.equals(parameters2.preferredAudioMimeTypes.get(i20))) {
                        i12 = i20;
                        break;
                    }
                    i20++;
                } else {
                    break;
                }
            }
            this.preferredMimeTypeMatchIndex = i12;
            if (RendererCapabilities.getDecoderSupport(i8) == 128) {
                z11 = true;
            } else {
                z11 = false;
            }
            this.usesPrimaryDecoder = z11;
            this.usesHardwareAcceleration = RendererCapabilities.getHardwareAccelerationSupport(i8) != 64 ? false : z12;
            this.selectionEligibility = evaluateSelectionEligibility(i8, z, i11);
        }

        public static int compareSelections(List<AudioTrackInfo> list, List<AudioTrackInfo> list2) {
            return ((AudioTrackInfo) Collections.max(list)).compareTo((AudioTrackInfo) Collections.max(list2));
        }

        public static U createForTrackGroup(int i2, TrackGroup trackGroup, Parameters parameters2, int[] iArr, boolean z, l lVar, int i7) {
            Q x9 = U.x();
            for (int i8 = 0; i8 < trackGroup.length; i8++) {
                x9.a(new AudioTrackInfo(i2, trackGroup, i8, parameters2, iArr[i8], z, lVar, i7));
            }
            return x9.f();
        }

        private int evaluateSelectionEligibility(int i2, boolean z, int i7) {
            if (!RendererCapabilities.isFormatSupported(i2, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinConstraints && !this.parameters.exceedAudioConstraintsIfNecessary) {
                return 0;
            }
            Parameters parameters2 = this.parameters;
            if (parameters2.audioOffloadPreferences.audioOffloadMode == 2 && !DefaultTrackSelector.rendererSupportsOffload(parameters2, i2, this.format)) {
                return 0;
            }
            if (!RendererCapabilities.isFormatSupported(i2, false) || !this.isWithinConstraints || this.format.bitrate == -1) {
                return 1;
            }
            Parameters parameters3 = this.parameters;
            if (parameters3.forceHighestSupportedBitrate || parameters3.forceLowestBitrate) {
                return 1;
            }
            if ((!parameters3.allowMultipleAdaptiveSelections && z) || parameters3.audioOffloadPreferences.audioOffloadMode == 2 || (i2 & i7) == 0) {
                return 1;
            }
            return 2;
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public int compareTo(AudioTrackInfo audioTrackInfo) {
            w0 w0Var;
            if (!this.isWithinConstraints || !this.isWithinRendererCapabilities) {
                w0Var = DefaultTrackSelector.FORMAT_VALUE_ORDERING.a();
            } else {
                w0Var = DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            }
            E d = E.f239a.d(this.isWithinRendererCapabilities, audioTrackInfo.isWithinRendererCapabilities);
            Integer valueOf = Integer.valueOf(this.preferredLanguageIndex);
            Integer valueOf2 = Integer.valueOf(audioTrackInfo.preferredLanguageIndex);
            v0 v0Var = v0.f;
            E c5 = d.c(valueOf, valueOf2, v0Var).a(this.preferredLanguageScore, audioTrackInfo.preferredLanguageScore).a(this.preferredRoleFlagsScore, audioTrackInfo.preferredRoleFlagsScore).d(this.isDefaultSelectionFlag, audioTrackInfo.isDefaultSelectionFlag).d(this.hasMainOrNoRoleFlag, audioTrackInfo.hasMainOrNoRoleFlag).c(Integer.valueOf(this.localeLanguageMatchIndex), Integer.valueOf(audioTrackInfo.localeLanguageMatchIndex), v0Var).a(this.localeLanguageScore, audioTrackInfo.localeLanguageScore).d(this.isWithinConstraints, audioTrackInfo.isWithinConstraints).c(Integer.valueOf(this.preferredMimeTypeMatchIndex), Integer.valueOf(audioTrackInfo.preferredMimeTypeMatchIndex), v0Var);
            if (this.parameters.forceLowestBitrate) {
                c5 = c5.c(Integer.valueOf(this.bitrate), Integer.valueOf(audioTrackInfo.bitrate), DefaultTrackSelector.FORMAT_VALUE_ORDERING.a());
            }
            E c6 = c5.d(this.usesPrimaryDecoder, audioTrackInfo.usesPrimaryDecoder).d(this.usesHardwareAcceleration, audioTrackInfo.usesHardwareAcceleration).d(this.isObjectBasedAudio, audioTrackInfo.isObjectBasedAudio).c(Integer.valueOf(this.channelCount), Integer.valueOf(audioTrackInfo.channelCount), w0Var).c(Integer.valueOf(this.sampleRate), Integer.valueOf(audioTrackInfo.sampleRate), w0Var);
            if (Objects.equals(this.language, audioTrackInfo.language)) {
                c6 = c6.c(Integer.valueOf(this.bitrate), Integer.valueOf(audioTrackInfo.bitrate), w0Var);
            }
            return c6.f();
        }

        public boolean isCompatibleForAdaptationWith(AudioTrackInfo audioTrackInfo) {
            int i2;
            String str;
            int i7;
            if (!this.parameters.allowAudioMixedChannelCountAdaptiveness && ((i7 = this.format.channelCount) == -1 || i7 != audioTrackInfo.format.channelCount)) {
                return false;
            }
            if (!this.allowMixedMimeTypes && ((str = this.format.sampleMimeType) == null || !TextUtils.equals(str, audioTrackInfo.format.sampleMimeType))) {
                return false;
            }
            Parameters parameters2 = this.parameters;
            if (!parameters2.allowAudioMixedSampleRateAdaptiveness && ((i2 = this.format.sampleRate) == -1 || i2 != audioTrackInfo.format.sampleRate)) {
                return false;
            }
            if (!parameters2.allowAudioMixedDecoderSupportAdaptiveness) {
                return this.usesPrimaryDecoder == audioTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == audioTrackInfo.usesHardwareAcceleration;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImageTrackInfo extends TrackInfo<ImageTrackInfo> implements Comparable<ImageTrackInfo> {
        private final int pixelCount = this.format.getPixelCount();
        private final int selectionEligibility;

        public ImageTrackInfo(int i2, TrackGroup trackGroup, int i7, Parameters parameters, int i8) {
            super(i2, trackGroup, i7);
            this.selectionEligibility = RendererCapabilities.isFormatSupported(i8, parameters.exceedRendererCapabilitiesIfNecessary) ? 1 : 0;
        }

        public static int compareSelections(List<ImageTrackInfo> list, List<ImageTrackInfo> list2) {
            return list.get(0).compareTo(list2.get(0));
        }

        public static U createForTrackGroup(int i2, TrackGroup trackGroup, Parameters parameters, int[] iArr) {
            Q x9 = U.x();
            for (int i7 = 0; i7 < trackGroup.length; i7++) {
                x9.a(new ImageTrackInfo(i2, trackGroup, i7, parameters, iArr[i7]));
            }
            return x9.f();
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(ImageTrackInfo imageTrackInfo) {
            return false;
        }

        public int compareTo(ImageTrackInfo imageTrackInfo) {
            return Integer.compare(this.pixelCount, imageTrackInfo.pixelCount);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OtherTrackScore implements Comparable<OtherTrackScore> {
        private final boolean isDefault;
        private final boolean isWithinRendererCapabilities;

        public OtherTrackScore(Format format, int i2) {
            this.isDefault = (format.selectionFlags & 1) == 0 ? false : true;
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(i2, false);
        }

        public int compareTo(OtherTrackScore otherTrackScore) {
            return E.f239a.d(this.isWithinRendererCapabilities, otherTrackScore.isWithinRendererCapabilities).d(this.isDefault, otherTrackScore.isDefault).f();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Parameters extends TrackSelectionParameters {
        public static final Parameters DEFAULT;
        @Deprecated
        public static final Parameters DEFAULT_WITHOUT_CONTEXT;
        private static final String FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER);
        private static final String FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_NETWORK_TIMEOUT);
        private static final String FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS = Util.intToStringMaxRadix(1004);
        private static final String FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS = Util.intToStringMaxRadix(1005);
        private static final String FIELD_ALLOW_AUDIO_NON_SEAMLESS_ADAPTIVENESS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_RESOURCE_NOT_FOUND);
        private static final String FIELD_ALLOW_INVALIDATE_SELECTIONS_ON_RENDERER_CAPABILITIES_CHANGE = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_PARSING);
        private static final String FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_FILE_IO_ERROR);
        private static final String FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED);
        private static final String FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS = Util.intToStringMaxRadix(1001);
        private static final String FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS = Util.intToStringMaxRadix(1002);
        private static final String FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_INTERNAL_NETWORK);
        private static final String FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY = Util.intToStringMaxRadix(1003);
        private static final String FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_INTERNAL_ERROR);
        private static final String FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY = Util.intToStringMaxRadix(1000);
        private static final String FIELD_RENDERER_DISABLED_INDICES = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER);
        private static final String FIELD_SELECTION_OVERRIDES = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED);
        private static final String FIELD_SELECTION_OVERRIDES_RENDERER_INDICES = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED);
        private static final String FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS = Util.intToStringMaxRadix(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN);
        private static final String FIELD_TUNNELING_ENABLED = Util.intToStringMaxRadix(1008);
        public final boolean allowAudioMixedChannelCountAdaptiveness;
        public final boolean allowAudioMixedDecoderSupportAdaptiveness;
        public final boolean allowAudioMixedMimeTypeAdaptiveness;
        public final boolean allowAudioMixedSampleRateAdaptiveness;
        public final boolean allowAudioNonSeamlessAdaptiveness;
        public final boolean allowInvalidateSelectionsOnRendererCapabilitiesChange;
        public final boolean allowMultipleAdaptiveSelections;
        public final boolean allowVideoMixedDecoderSupportAdaptiveness;
        public final boolean allowVideoMixedMimeTypeAdaptiveness;
        public final boolean allowVideoNonSeamlessAdaptiveness;
        public final boolean constrainAudioChannelCountToDeviceCapabilities;
        public final boolean exceedAudioConstraintsIfNecessary;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        /* access modifiers changed from: private */
        public final SparseBooleanArray rendererDisabledFlags;
        /* access modifiers changed from: private */
        public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final boolean tunnelingEnabled;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder extends TrackSelectionParameters.Builder {
            /* access modifiers changed from: private */
            public boolean allowAudioMixedChannelCountAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedDecoderSupportAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedMimeTypeAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioMixedSampleRateAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowAudioNonSeamlessAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowInvalidateSelectionsOnRendererCapabilitiesChange;
            /* access modifiers changed from: private */
            public boolean allowMultipleAdaptiveSelections;
            /* access modifiers changed from: private */
            public boolean allowVideoMixedDecoderSupportAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowVideoMixedMimeTypeAdaptiveness;
            /* access modifiers changed from: private */
            public boolean allowVideoNonSeamlessAdaptiveness;
            /* access modifiers changed from: private */
            public boolean constrainAudioChannelCountToDeviceCapabilities;
            /* access modifiers changed from: private */
            public boolean exceedAudioConstraintsIfNecessary;
            /* access modifiers changed from: private */
            public boolean exceedRendererCapabilitiesIfNecessary;
            /* access modifiers changed from: private */
            public boolean exceedVideoConstraintsIfNecessary;
            /* access modifiers changed from: private */
            public final SparseBooleanArray rendererDisabledFlags;
            /* access modifiers changed from: private */
            public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
            /* access modifiers changed from: private */
            public boolean tunnelingEnabled;

            private static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
                SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2 = new SparseArray<>();
                for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                    sparseArray2.put(sparseArray.keyAt(i2), new HashMap(sparseArray.valueAt(i2)));
                }
                return sparseArray2;
            }

            private void init() {
                this.exceedVideoConstraintsIfNecessary = true;
                this.allowVideoMixedMimeTypeAdaptiveness = false;
                this.allowVideoNonSeamlessAdaptiveness = true;
                this.allowVideoMixedDecoderSupportAdaptiveness = false;
                this.exceedAudioConstraintsIfNecessary = true;
                this.allowAudioMixedMimeTypeAdaptiveness = false;
                this.allowAudioMixedSampleRateAdaptiveness = false;
                this.allowAudioMixedChannelCountAdaptiveness = false;
                this.allowAudioMixedDecoderSupportAdaptiveness = false;
                this.allowAudioNonSeamlessAdaptiveness = true;
                this.constrainAudioChannelCountToDeviceCapabilities = true;
                this.exceedRendererCapabilitiesIfNecessary = true;
                this.tunnelingEnabled = false;
                this.allowMultipleAdaptiveSelections = true;
                this.allowInvalidateSelectionsOnRendererCapabilitiesChange = false;
            }

            public Builder set(TrackSelectionParameters trackSelectionParameters) {
                super.set(trackSelectionParameters);
                return this;
            }

            public Builder setConstrainAudioChannelCountToDeviceCapabilities(boolean z) {
                this.constrainAudioChannelCountToDeviceCapabilities = z;
                return this;
            }

            public Builder setForceHighestSupportedBitrate(boolean z) {
                super.setForceHighestSupportedBitrate(z);
                return this;
            }

            public Builder() {
                this.selectionOverrides = new SparseArray<>();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            public Parameters build() {
                return new Parameters(this);
            }

            @Deprecated
            public Builder(Context context) {
                this();
            }

            private Builder(Parameters parameters) {
                super(parameters);
                this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
                this.allowVideoMixedMimeTypeAdaptiveness = parameters.allowVideoMixedMimeTypeAdaptiveness;
                this.allowVideoNonSeamlessAdaptiveness = parameters.allowVideoNonSeamlessAdaptiveness;
                this.allowVideoMixedDecoderSupportAdaptiveness = parameters.allowVideoMixedDecoderSupportAdaptiveness;
                this.exceedAudioConstraintsIfNecessary = parameters.exceedAudioConstraintsIfNecessary;
                this.allowAudioMixedMimeTypeAdaptiveness = parameters.allowAudioMixedMimeTypeAdaptiveness;
                this.allowAudioMixedSampleRateAdaptiveness = parameters.allowAudioMixedSampleRateAdaptiveness;
                this.allowAudioMixedChannelCountAdaptiveness = parameters.allowAudioMixedChannelCountAdaptiveness;
                this.allowAudioMixedDecoderSupportAdaptiveness = parameters.allowAudioMixedDecoderSupportAdaptiveness;
                this.allowAudioNonSeamlessAdaptiveness = parameters.allowAudioNonSeamlessAdaptiveness;
                this.constrainAudioChannelCountToDeviceCapabilities = parameters.constrainAudioChannelCountToDeviceCapabilities;
                this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
                this.tunnelingEnabled = parameters.tunnelingEnabled;
                this.allowMultipleAdaptiveSelections = parameters.allowMultipleAdaptiveSelections;
                this.allowInvalidateSelectionsOnRendererCapabilitiesChange = parameters.allowInvalidateSelectionsOnRendererCapabilitiesChange;
                this.selectionOverrides = cloneSelectionOverrides(parameters.selectionOverrides);
                this.rendererDisabledFlags = parameters.rendererDisabledFlags.clone();
            }
        }

        static {
            Parameters build = new Builder().build();
            DEFAULT = build;
            DEFAULT_WITHOUT_CONTEXT = build;
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray sparseBooleanArray, SparseBooleanArray sparseBooleanArray2) {
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() != size) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i2)) < 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2) {
            int size = sparseArray.size();
            if (sparseArray2.size() != size) {
                return false;
            }
            for (int i2 = 0; i2 < size; i2++) {
                int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i2));
                if (indexOfKey < 0 || !areSelectionOverridesEqual(sparseArray.valueAt(i2), sparseArray2.valueAt(indexOfKey))) {
                    return false;
                }
            }
            return true;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Parameters.class == obj.getClass()) {
                Parameters parameters = (Parameters) obj;
                if (super.equals(parameters) && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.allowVideoMixedMimeTypeAdaptiveness == parameters.allowVideoMixedMimeTypeAdaptiveness && this.allowVideoNonSeamlessAdaptiveness == parameters.allowVideoNonSeamlessAdaptiveness && this.allowVideoMixedDecoderSupportAdaptiveness == parameters.allowVideoMixedDecoderSupportAdaptiveness && this.exceedAudioConstraintsIfNecessary == parameters.exceedAudioConstraintsIfNecessary && this.allowAudioMixedMimeTypeAdaptiveness == parameters.allowAudioMixedMimeTypeAdaptiveness && this.allowAudioMixedSampleRateAdaptiveness == parameters.allowAudioMixedSampleRateAdaptiveness && this.allowAudioMixedChannelCountAdaptiveness == parameters.allowAudioMixedChannelCountAdaptiveness && this.allowAudioMixedDecoderSupportAdaptiveness == parameters.allowAudioMixedDecoderSupportAdaptiveness && this.allowAudioNonSeamlessAdaptiveness == parameters.allowAudioNonSeamlessAdaptiveness && this.constrainAudioChannelCountToDeviceCapabilities == parameters.constrainAudioChannelCountToDeviceCapabilities && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.tunnelingEnabled == parameters.tunnelingEnabled && this.allowMultipleAdaptiveSelections == parameters.allowMultipleAdaptiveSelections && this.allowInvalidateSelectionsOnRendererCapabilitiesChange == parameters.allowInvalidateSelectionsOnRendererCapabilitiesChange && areRendererDisabledFlagsEqual(this.rendererDisabledFlags, parameters.rendererDisabledFlags) && areSelectionOverridesEqual(this.selectionOverrides, parameters.selectionOverrides)) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public boolean getRendererDisabled(int i2) {
            return this.rendererDisabledFlags.get(i2);
        }

        @Deprecated
        public SelectionOverride getSelectionOverride(int i2, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i2);
            if (map == null || map.get(trackGroupArray) == null) {
                return null;
            }
            throw new ClassCastException();
        }

        @Deprecated
        public boolean hasSelectionOverride(int i2, TrackGroupArray trackGroupArray) {
            Map map = this.selectionOverrides.get(i2);
            if (map == null || !map.containsKey(trackGroupArray)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((super.hashCode() + 31) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowVideoMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.exceedAudioConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowAudioMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedSampleRateAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedChannelCountAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.constrainAudioChannelCountToDeviceCapabilities ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.tunnelingEnabled ? 1 : 0)) * 31) + (this.allowMultipleAdaptiveSelections ? 1 : 0)) * 31) + (this.allowInvalidateSelectionsOnRendererCapabilitiesChange ? 1 : 0);
        }

        private Parameters(Builder builder) {
            super(builder);
            this.exceedVideoConstraintsIfNecessary = builder.exceedVideoConstraintsIfNecessary;
            this.allowVideoMixedMimeTypeAdaptiveness = builder.allowVideoMixedMimeTypeAdaptiveness;
            this.allowVideoNonSeamlessAdaptiveness = builder.allowVideoNonSeamlessAdaptiveness;
            this.allowVideoMixedDecoderSupportAdaptiveness = builder.allowVideoMixedDecoderSupportAdaptiveness;
            this.exceedAudioConstraintsIfNecessary = builder.exceedAudioConstraintsIfNecessary;
            this.allowAudioMixedMimeTypeAdaptiveness = builder.allowAudioMixedMimeTypeAdaptiveness;
            this.allowAudioMixedSampleRateAdaptiveness = builder.allowAudioMixedSampleRateAdaptiveness;
            this.allowAudioMixedChannelCountAdaptiveness = builder.allowAudioMixedChannelCountAdaptiveness;
            this.allowAudioMixedDecoderSupportAdaptiveness = builder.allowAudioMixedDecoderSupportAdaptiveness;
            this.allowAudioNonSeamlessAdaptiveness = builder.allowAudioNonSeamlessAdaptiveness;
            this.constrainAudioChannelCountToDeviceCapabilities = builder.constrainAudioChannelCountToDeviceCapabilities;
            this.exceedRendererCapabilitiesIfNecessary = builder.exceedRendererCapabilitiesIfNecessary;
            this.tunnelingEnabled = builder.tunnelingEnabled;
            this.allowMultipleAdaptiveSelections = builder.allowMultipleAdaptiveSelections;
            this.allowInvalidateSelectionsOnRendererCapabilitiesChange = builder.allowInvalidateSelectionsOnRendererCapabilitiesChange;
            this.selectionOverrides = builder.selectionOverrides;
            this.rendererDisabledFlags = builder.rendererDisabledFlags;
        }

        /* JADX WARNING: Removed duplicated region for block: B:6:0x001a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean areSelectionOverridesEqual(java.util.Map<androidx.media3.exoplayer.source.TrackGroupArray, androidx.media3.exoplayer.trackselection.DefaultTrackSelector.SelectionOverride> r4, java.util.Map<androidx.media3.exoplayer.source.TrackGroupArray, androidx.media3.exoplayer.trackselection.DefaultTrackSelector.SelectionOverride> r5) {
            /*
                int r0 = r4.size()
                int r1 = r5.size()
                r2 = 0
                if (r1 == r0) goto L_0x000c
                return r2
            L_0x000c:
                java.util.Set r4 = r4.entrySet()
                java.util.Iterator r4 = r4.iterator()
            L_0x0014:
                boolean r0 = r4.hasNext()
                if (r0 == 0) goto L_0x003b
                java.lang.Object r0 = r4.next()
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                java.lang.Object r1 = r0.getKey()
                androidx.media3.exoplayer.source.TrackGroupArray r1 = (androidx.media3.exoplayer.source.TrackGroupArray) r1
                boolean r3 = r5.containsKey(r1)
                if (r3 == 0) goto L_0x003a
                java.lang.Object r0 = r0.getValue()
                java.lang.Object r1 = r5.get(r1)
                boolean r0 = java.util.Objects.equals(r0, r1)
                if (r0 != 0) goto L_0x0014
            L_0x003a:
                return r2
            L_0x003b:
                r4 = 1
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.DefaultTrackSelector.Parameters.areSelectionOverridesEqual(java.util.Map, java.util.Map):boolean");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SelectionOverride {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpatializerWrapperV32 {
        private final Handler handler;
        private final Spatializer$OnSpatializerStateChangedListener listener;
        private final boolean spatializationSupported;
        private final Spatializer spatializer;

        public SpatializerWrapperV32(Context context, final DefaultTrackSelector defaultTrackSelector, Boolean bool) {
            AudioManager audioManager;
            if (context == null) {
                audioManager = null;
            } else {
                audioManager = AudioManagerCompat.getAudioManager(context);
            }
            boolean z = false;
            if (audioManager == null || (bool != null && bool.booleanValue())) {
                this.spatializer = null;
                this.spatializationSupported = false;
                this.handler = null;
                this.listener = null;
                return;
            }
            Spatializer b = audioManager.getSpatializer();
            this.spatializer = b;
            this.spatializationSupported = b.getImmersiveAudioLevel() != 0 ? true : z;
            AnonymousClass1 r5 = new Spatializer$OnSpatializerStateChangedListener() {
                public void onSpatializerAvailableChanged(Spatializer spatializer, boolean z) {
                    defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                }

                public void onSpatializerEnabledChanged(Spatializer spatializer, boolean z) {
                    defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                }
            };
            this.listener = r5;
            Handler handler2 = new Handler((Looper) Assertions.checkStateNotNull(Looper.myLooper()));
            this.handler = handler2;
            b.addOnSpatializerStateChangedListener(new b(1, handler2), r5);
        }

        public boolean canBeSpatialized(AudioAttributes audioAttributes, Format format) {
            int i2;
            if (Objects.equals(format.sampleMimeType, "audio/eac3-joc")) {
                i2 = format.channelCount;
                if (i2 == 16) {
                    i2 = 12;
                }
            } else if (Objects.equals(format.sampleMimeType, "audio/iamf")) {
                i2 = format.channelCount;
                if (i2 == -1) {
                    i2 = 6;
                }
            } else if (Objects.equals(format.sampleMimeType, "audio/ac4")) {
                i2 = format.channelCount;
                if (i2 == 18 || i2 == 21) {
                    i2 = 24;
                }
            } else {
                i2 = format.channelCount;
            }
            int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(i2);
            if (audioTrackChannelConfig == 0) {
                return false;
            }
            AudioFormat.Builder channelMask = new AudioFormat.Builder().setEncoding(2).setChannelMask(audioTrackChannelConfig);
            int i7 = format.sampleRate;
            if (i7 != -1) {
                channelMask.setSampleRate(i7);
            }
            return C.a.c(Assertions.checkNotNull(this.spatializer)).canBeSpatialized(audioAttributes.getAudioAttributesV21().audioAttributes, channelMask.build());
        }

        public boolean isAvailable() {
            return C.a.c(Assertions.checkNotNull(this.spatializer)).isAvailable();
        }

        public boolean isEnabled() {
            return C.a.c(Assertions.checkNotNull(this.spatializer)).isEnabled();
        }

        public boolean isSpatializationSupported() {
            return this.spatializationSupported;
        }

        public void release() {
            Spatializer$OnSpatializerStateChangedListener spatializer$OnSpatializerStateChangedListener;
            Spatializer spatializer2 = this.spatializer;
            if (spatializer2 != null && (spatializer$OnSpatializerStateChangedListener = this.listener) != null && this.handler != null) {
                spatializer2.removeOnSpatializerStateChangedListener(spatializer$OnSpatializerStateChangedListener);
                this.handler.removeCallbacksAndMessages((Object) null);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TextTrackInfo extends TrackInfo<TextTrackInfo> implements Comparable<TextTrackInfo> {
        private final boolean hasCaptionRoleFlags;
        private final boolean isDefault;
        private final boolean isForced;
        private final boolean isWithinRendererCapabilities;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredRoleFlagsScore;
        private final int selectedAudioLanguageScore;
        private final int selectionEligibility;

        public TextTrackInfo(int i2, TrackGroup trackGroup, int i7, Parameters parameters, int i8, String str, String str2) {
            super(i2, trackGroup, i7);
            boolean z;
            boolean z3;
            U u;
            int i10;
            int i11;
            boolean z7;
            boolean z9;
            boolean z10;
            int i12 = 0;
            this.isWithinRendererCapabilities = RendererCapabilities.isFormatSupported(i8, false);
            int i13 = this.format.selectionFlags & (~parameters.ignoredTextSelectionFlags);
            if ((i13 & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.isDefault = z;
            if ((i13 & 2) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            this.isForced = z3;
            if (str2 != null) {
                u = U.B(str2);
            } else if (parameters.preferredTextLanguages.isEmpty()) {
                u = U.B("");
            } else {
                u = parameters.preferredTextLanguages;
            }
            int i14 = 0;
            while (true) {
                if (i14 >= u.size()) {
                    i14 = Integer.MAX_VALUE;
                    i10 = 0;
                    break;
                }
                i10 = DefaultTrackSelector.getFormatLanguageScore(this.format, (String) u.get(i14), parameters.selectUndeterminedTextLanguage);
                if (i10 > 0) {
                    break;
                }
                i14++;
            }
            this.preferredLanguageIndex = i14;
            this.preferredLanguageScore = i10;
            if (str2 != null) {
                i11 = 1088;
            } else {
                i11 = parameters.preferredTextRoleFlags;
            }
            int access$4200 = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, i11);
            this.preferredRoleFlagsScore = access$4200;
            if ((1088 & this.format.roleFlags) != 0) {
                z7 = true;
            } else {
                z7 = false;
            }
            this.hasCaptionRoleFlags = z7;
            if (DefaultTrackSelector.normalizeUndeterminedLanguageToNull(str) == null) {
                z9 = true;
            } else {
                z9 = false;
            }
            int formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(this.format, str, z9);
            this.selectedAudioLanguageScore = formatLanguageScore;
            if (i10 > 0 || ((parameters.preferredTextLanguages.isEmpty() && access$4200 > 0) || this.isDefault || (this.isForced && formatLanguageScore > 0))) {
                z10 = true;
            } else {
                z10 = false;
            }
            if (RendererCapabilities.isFormatSupported(i8, parameters.exceedRendererCapabilitiesIfNecessary) && z10) {
                i12 = 1;
            }
            this.selectionEligibility = i12;
        }

        public static int compareSelections(List<TextTrackInfo> list, List<TextTrackInfo> list2) {
            return list.get(0).compareTo(list2.get(0));
        }

        public static U createForTrackGroup(int i2, TrackGroup trackGroup, Parameters parameters, int[] iArr, String str, String str2) {
            Q x9 = U.x();
            for (int i7 = 0; i7 < trackGroup.length; i7++) {
                x9.a(new TextTrackInfo(i2, trackGroup, i7, parameters, iArr[i7], str, str2));
            }
            return x9.f();
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(TextTrackInfo textTrackInfo) {
            return false;
        }

        public int compareTo(TextTrackInfo textTrackInfo) {
            E d = E.f239a.d(this.isWithinRendererCapabilities, textTrackInfo.isWithinRendererCapabilities);
            Integer valueOf = Integer.valueOf(this.preferredLanguageIndex);
            Integer valueOf2 = Integer.valueOf(textTrackInfo.preferredLanguageIndex);
            v0 v0Var = v0.e;
            v0 v0Var2 = v0.f;
            E d2 = d.c(valueOf, valueOf2, v0Var2).a(this.preferredLanguageScore, textTrackInfo.preferredLanguageScore).a(this.preferredRoleFlagsScore, textTrackInfo.preferredRoleFlagsScore).d(this.isDefault, textTrackInfo.isDefault);
            Boolean valueOf3 = Boolean.valueOf(this.isForced);
            Boolean valueOf4 = Boolean.valueOf(textTrackInfo.isForced);
            if (this.preferredLanguageScore != 0) {
                v0Var = v0Var2;
            }
            E a7 = d2.c(valueOf3, valueOf4, v0Var).a(this.selectedAudioLanguageScore, textTrackInfo.selectedAudioLanguageScore);
            if (this.preferredRoleFlagsScore == 0) {
                a7 = a7.e(this.hasCaptionRoleFlags, textTrackInfo.hasCaptionRoleFlags);
            }
            return a7.f();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class TrackInfo<T extends TrackInfo<T>> {
        public final Format format;
        public final int rendererIndex;
        public final TrackGroup trackGroup;
        public final int trackIndex;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface Factory<T extends TrackInfo<T>> {
            List<T> create(int i2, TrackGroup trackGroup, int[] iArr);
        }

        public TrackInfo(int i2, TrackGroup trackGroup2, int i7) {
            this.rendererIndex = i2;
            this.trackGroup = trackGroup2;
            this.trackIndex = i7;
            this.format = trackGroup2.getFormat(i7);
        }

        public abstract int getSelectionEligibility();

        public abstract boolean isCompatibleForAdaptationWith(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VideoTrackInfo extends TrackInfo<VideoTrackInfo> {
        private final boolean allowMixedMimeTypes;
        private final int bitrate;
        private final int codecPreferenceScore;
        private final boolean hasMainOrNoRoleFlag;
        private final boolean hasReasonableFrameRate;
        private final boolean isWithinMaxConstraints;
        private final boolean isWithinMinConstraints;
        private final boolean isWithinRendererCapabilities;
        private final Parameters parameters;
        private final int pixelCount;
        private final int preferredLanguageIndex;
        private final int preferredLanguageScore;
        private final int preferredMimeTypeMatchIndex;
        private final int preferredRoleFlagsScore;
        private final int selectedAudioLanguageScore;
        private final int selectionEligibility;
        private final boolean usesHardwareAcceleration;
        private final boolean usesPrimaryDecoder;

        /* JADX WARNING: Removed duplicated region for block: B:54:0x0090  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x0092  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00ab  */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00db  */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00dd  */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x00e6  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x00e8  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x00fa  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x011b  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x011d  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x0129  */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x00bf A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0111 A[EDGE_INSN: B:93:0x0111->B:82:0x0111 ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public VideoTrackInfo(int r5, androidx.media3.common.TrackGroup r6, int r7, androidx.media3.exoplayer.trackselection.DefaultTrackSelector.Parameters r8, int r9, java.lang.String r10, int r11, boolean r12) {
            /*
                r4 = this;
                r4.<init>(r5, r6, r7)
                r4.parameters = r8
                boolean r5 = r8.allowVideoNonSeamlessAdaptiveness
                if (r5 == 0) goto L_0x000c
                r5 = 24
                goto L_0x000e
            L_0x000c:
                r5 = 16
            L_0x000e:
                boolean r6 = r8.allowVideoMixedMimeTypeAdaptiveness
                r7 = 1
                r0 = 0
                if (r6 == 0) goto L_0x001a
                r6 = r11 & r5
                if (r6 == 0) goto L_0x001a
                r6 = r7
                goto L_0x001b
            L_0x001a:
                r6 = r0
            L_0x001b:
                r4.allowMixedMimeTypes = r6
                r6 = -1082130432(0xffffffffbf800000, float:-1.0)
                r11 = -1
                if (r12 == 0) goto L_0x004b
                androidx.media3.common.Format r1 = r4.format
                int r2 = r1.width
                if (r2 == r11) goto L_0x002c
                int r3 = r8.maxVideoWidth
                if (r2 > r3) goto L_0x004b
            L_0x002c:
                int r2 = r1.height
                if (r2 == r11) goto L_0x0034
                int r3 = r8.maxVideoHeight
                if (r2 > r3) goto L_0x004b
            L_0x0034:
                float r2 = r1.frameRate
                int r3 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r3 == 0) goto L_0x0041
                int r3 = r8.maxVideoFrameRate
                float r3 = (float) r3
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 > 0) goto L_0x004b
            L_0x0041:
                int r1 = r1.bitrate
                if (r1 == r11) goto L_0x0049
                int r2 = r8.maxVideoBitrate
                if (r1 > r2) goto L_0x004b
            L_0x0049:
                r1 = r7
                goto L_0x004c
            L_0x004b:
                r1 = r0
            L_0x004c:
                r4.isWithinMaxConstraints = r1
                if (r12 == 0) goto L_0x0079
                androidx.media3.common.Format r12 = r4.format
                int r1 = r12.width
                if (r1 == r11) goto L_0x005a
                int r2 = r8.minVideoWidth
                if (r1 < r2) goto L_0x0079
            L_0x005a:
                int r1 = r12.height
                if (r1 == r11) goto L_0x0062
                int r2 = r8.minVideoHeight
                if (r1 < r2) goto L_0x0079
            L_0x0062:
                float r1 = r12.frameRate
                int r2 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r2 == 0) goto L_0x006f
                int r2 = r8.minVideoFrameRate
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 < 0) goto L_0x0079
            L_0x006f:
                int r12 = r12.bitrate
                if (r12 == r11) goto L_0x0077
                int r11 = r8.minVideoBitrate
                if (r12 < r11) goto L_0x0079
            L_0x0077:
                r11 = r7
                goto L_0x007a
            L_0x0079:
                r11 = r0
            L_0x007a:
                r4.isWithinMinConstraints = r11
                boolean r11 = androidx.media3.exoplayer.RendererCapabilities.isFormatSupported(r9, r0)
                r4.isWithinRendererCapabilities = r11
                androidx.media3.common.Format r11 = r4.format
                float r12 = r11.frameRate
                int r6 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
                if (r6 == 0) goto L_0x0092
                r6 = 1092616192(0x41200000, float:10.0)
                int r6 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
                if (r6 < 0) goto L_0x0092
                r6 = r7
                goto L_0x0093
            L_0x0092:
                r6 = r0
            L_0x0093:
                r4.hasReasonableFrameRate = r6
                int r6 = r11.bitrate
                r4.bitrate = r6
                int r6 = r11.getPixelCount()
                r4.pixelCount = r6
                r6 = r0
            L_0x00a0:
                F2.U r11 = r8.preferredVideoLanguages
                int r11 = r11.size()
                r12 = 2147483647(0x7fffffff, float:NaN)
                if (r6 >= r11) goto L_0x00bf
                androidx.media3.common.Format r11 = r4.format
                F2.U r1 = r8.preferredVideoLanguages
                java.lang.Object r1 = r1.get(r6)
                java.lang.String r1 = (java.lang.String) r1
                int r11 = androidx.media3.exoplayer.trackselection.DefaultTrackSelector.getFormatLanguageScore(r11, r1, r0)
                if (r11 <= 0) goto L_0x00bc
                goto L_0x00c1
            L_0x00bc:
                int r6 = r6 + 1
                goto L_0x00a0
            L_0x00bf:
                r6 = r12
                r11 = r0
            L_0x00c1:
                r4.preferredLanguageIndex = r6
                r4.preferredLanguageScore = r11
                androidx.media3.common.Format r6 = r4.format
                int r6 = r6.roleFlags
                int r11 = r8.preferredVideoRoleFlags
                int r6 = androidx.media3.exoplayer.trackselection.DefaultTrackSelector.getRoleFlagMatchScore(r6, r11)
                r4.preferredRoleFlagsScore = r6
                androidx.media3.common.Format r6 = r4.format
                int r6 = r6.roleFlags
                if (r6 == 0) goto L_0x00dd
                r6 = r6 & r7
                if (r6 == 0) goto L_0x00db
                goto L_0x00dd
            L_0x00db:
                r6 = r0
                goto L_0x00de
            L_0x00dd:
                r6 = r7
            L_0x00de:
                r4.hasMainOrNoRoleFlag = r6
                java.lang.String r6 = androidx.media3.exoplayer.trackselection.DefaultTrackSelector.normalizeUndeterminedLanguageToNull(r10)
                if (r6 != 0) goto L_0x00e8
                r6 = r7
                goto L_0x00e9
            L_0x00e8:
                r6 = r0
            L_0x00e9:
                androidx.media3.common.Format r11 = r4.format
                int r6 = androidx.media3.exoplayer.trackselection.DefaultTrackSelector.getFormatLanguageScore(r11, r10, r6)
                r4.selectedAudioLanguageScore = r6
                r6 = r0
            L_0x00f2:
                F2.U r10 = r8.preferredVideoMimeTypes
                int r10 = r10.size()
                if (r6 >= r10) goto L_0x0111
                androidx.media3.common.Format r10 = r4.format
                java.lang.String r10 = r10.sampleMimeType
                if (r10 == 0) goto L_0x010e
                F2.U r11 = r8.preferredVideoMimeTypes
                java.lang.Object r11 = r11.get(r6)
                boolean r10 = r10.equals(r11)
                if (r10 == 0) goto L_0x010e
                r12 = r6
                goto L_0x0111
            L_0x010e:
                int r6 = r6 + 1
                goto L_0x00f2
            L_0x0111:
                r4.preferredMimeTypeMatchIndex = r12
                int r6 = androidx.media3.exoplayer.RendererCapabilities.getDecoderSupport(r9)
                r8 = 128(0x80, float:1.794E-43)
                if (r6 != r8) goto L_0x011d
                r6 = r7
                goto L_0x011e
            L_0x011d:
                r6 = r0
            L_0x011e:
                r4.usesPrimaryDecoder = r6
                int r6 = androidx.media3.exoplayer.RendererCapabilities.getHardwareAccelerationSupport(r9)
                r8 = 64
                if (r6 != r8) goto L_0x0129
                goto L_0x012a
            L_0x0129:
                r7 = r0
            L_0x012a:
                r4.usesHardwareAcceleration = r7
                androidx.media3.common.Format r6 = r4.format
                java.lang.String r6 = r6.sampleMimeType
                int r6 = androidx.media3.exoplayer.trackselection.DefaultTrackSelector.getVideoCodecPreferenceScore(r6)
                r4.codecPreferenceScore = r6
                int r5 = r4.evaluateSelectionEligibility(r9, r5)
                r4.selectionEligibility = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.DefaultTrackSelector.VideoTrackInfo.<init>(int, androidx.media3.common.TrackGroup, int, androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters, int, java.lang.String, int, boolean):void");
        }

        /* access modifiers changed from: private */
        public static int compareNonQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            E d = E.f239a.d(videoTrackInfo.isWithinRendererCapabilities, videoTrackInfo2.isWithinRendererCapabilities);
            Integer valueOf = Integer.valueOf(videoTrackInfo.preferredLanguageIndex);
            Integer valueOf2 = Integer.valueOf(videoTrackInfo2.preferredLanguageIndex);
            v0 v0Var = v0.f;
            E d2 = d.c(valueOf, valueOf2, v0Var).a(videoTrackInfo.preferredLanguageScore, videoTrackInfo2.preferredLanguageScore).a(videoTrackInfo.preferredRoleFlagsScore, videoTrackInfo2.preferredRoleFlagsScore).d(videoTrackInfo.hasMainOrNoRoleFlag, videoTrackInfo2.hasMainOrNoRoleFlag).a(videoTrackInfo.selectedAudioLanguageScore, videoTrackInfo2.selectedAudioLanguageScore).d(videoTrackInfo.hasReasonableFrameRate, videoTrackInfo2.hasReasonableFrameRate).d(videoTrackInfo.isWithinMaxConstraints, videoTrackInfo2.isWithinMaxConstraints).d(videoTrackInfo.isWithinMinConstraints, videoTrackInfo2.isWithinMinConstraints).c(Integer.valueOf(videoTrackInfo.preferredMimeTypeMatchIndex), Integer.valueOf(videoTrackInfo2.preferredMimeTypeMatchIndex), v0Var).d(videoTrackInfo.usesPrimaryDecoder, videoTrackInfo2.usesPrimaryDecoder).d(videoTrackInfo.usesHardwareAcceleration, videoTrackInfo2.usesHardwareAcceleration);
            if (videoTrackInfo.usesPrimaryDecoder && videoTrackInfo.usesHardwareAcceleration) {
                d2 = d2.a(videoTrackInfo.codecPreferenceScore, videoTrackInfo2.codecPreferenceScore);
            }
            return d2.f();
        }

        /* access modifiers changed from: private */
        public static int compareQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            w0 w0Var;
            if (!videoTrackInfo.isWithinMaxConstraints || !videoTrackInfo.isWithinRendererCapabilities) {
                w0Var = DefaultTrackSelector.FORMAT_VALUE_ORDERING.a();
            } else {
                w0Var = DefaultTrackSelector.FORMAT_VALUE_ORDERING;
            }
            boolean z = videoTrackInfo.parameters.forceLowestBitrate;
            C c5 = E.f239a;
            E e = c5;
            if (z) {
                e = c5.c(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), DefaultTrackSelector.FORMAT_VALUE_ORDERING.a());
            }
            return e.c(Integer.valueOf(videoTrackInfo.pixelCount), Integer.valueOf(videoTrackInfo2.pixelCount), w0Var).c(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), w0Var).f();
        }

        public static int compareSelections(List<VideoTrackInfo> list, List<VideoTrackInfo> list2) {
            return C.g(compareNonQualityPreferences((VideoTrackInfo) Collections.max(list, new a(4)), (VideoTrackInfo) Collections.max(list2, new a(4)))).a(list.size(), list2.size()).c((VideoTrackInfo) Collections.max(list, new a(5)), (VideoTrackInfo) Collections.max(list2, new a(5)), new a(5)).f();
        }

        public static U createForTrackGroup(int i2, TrackGroup trackGroup, Parameters parameters2, int[] iArr, String str, int i7, Point point) {
            int i8;
            int i10;
            boolean z;
            Point point2 = point;
            if (point2 != null) {
                i8 = point2.x;
            } else {
                i8 = parameters2.viewportWidth;
            }
            if (point2 != null) {
                i10 = point2.y;
            } else {
                i10 = parameters2.viewportHeight;
            }
            int access$4100 = DefaultTrackSelector.getMaxVideoPixelsToRetainForViewport(trackGroup, i8, i10, parameters2.viewportOrientationMayChange);
            Q x9 = U.x();
            for (int i11 = 0; i11 < trackGroup.length; i11++) {
                int pixelCount2 = trackGroup.getFormat(i11).getPixelCount();
                if (access$4100 == Integer.MAX_VALUE || (pixelCount2 != -1 && pixelCount2 <= access$4100)) {
                    z = true;
                } else {
                    z = false;
                }
                x9.a(new VideoTrackInfo(i2, trackGroup, i11, parameters2, iArr[i11], str, i7, z));
            }
            return x9.f();
        }

        private int evaluateSelectionEligibility(int i2, int i7) {
            if ((this.format.roleFlags & 16384) != 0 || !RendererCapabilities.isFormatSupported(i2, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinMaxConstraints && !this.parameters.exceedVideoConstraintsIfNecessary) {
                return 0;
            }
            if (!RendererCapabilities.isFormatSupported(i2, false) || !this.isWithinMinConstraints || !this.isWithinMaxConstraints || this.format.bitrate == -1) {
                return 1;
            }
            Parameters parameters2 = this.parameters;
            if (parameters2.forceHighestSupportedBitrate || parameters2.forceLowestBitrate || (i2 & i7) == 0) {
                return 1;
            }
            return 2;
        }

        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(VideoTrackInfo videoTrackInfo) {
            if (!this.allowMixedMimeTypes && !Objects.equals(this.format.sampleMimeType, videoTrackInfo.format.sampleMimeType)) {
                return false;
            }
            if (!this.parameters.allowVideoMixedDecoderSupportAdaptiveness) {
                return this.usesPrimaryDecoder == videoTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == videoTrackInfo.usesHardwareAcceleration;
            }
            return true;
        }
    }

    public DefaultTrackSelector(Context context2) {
        this(context2, new AdaptiveTrackSelection.Factory());
    }

    private static void applyLegacyRendererOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, Parameters parameters2, ExoTrackSelection.Definition[] definitionArr) {
        int rendererCount = mappedTrackInfo.getRendererCount();
        for (int i2 = 0; i2 < rendererCount; i2++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i2);
            if (parameters2.hasSelectionOverride(i2, trackGroups)) {
                parameters2.getSelectionOverride(i2, trackGroups);
                definitionArr[i2] = null;
            }
        }
    }

    private static void applyTrackSelectionOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Definition[] definitionArr) {
        int rendererCount = mappedTrackInfo.getRendererCount();
        HashMap hashMap = new HashMap();
        int i2 = 0;
        for (int i7 = 0; i7 < rendererCount; i7++) {
            collectTrackSelectionOverrides(mappedTrackInfo.getTrackGroups(i7), trackSelectionParameters, hashMap);
        }
        collectTrackSelectionOverrides(mappedTrackInfo.getUnmappedTrackGroups(), trackSelectionParameters, hashMap);
        while (i2 < rendererCount) {
            if (hashMap.get(Integer.valueOf(mappedTrackInfo.getRendererType(i2))) == null) {
                i2++;
            } else {
                throw new ClassCastException();
            }
        }
    }

    private static void collectTrackSelectionOverrides(TrackGroupArray trackGroupArray, TrackSelectionParameters trackSelectionParameters, Map<Integer, Object> map) {
        int i2 = 0;
        while (i2 < trackGroupArray.length) {
            if (trackSelectionParameters.overrides.get(trackGroupArray.get(i2)) == null) {
                i2++;
            } else {
                throw new ClassCastException();
            }
        }
    }

    public static int getFormatLanguageScore(Format format, String str, boolean z) {
        if (!TextUtils.isEmpty(str) && str.equals(format.language)) {
            return 4;
        }
        String normalizeUndeterminedLanguageToNull = normalizeUndeterminedLanguageToNull(str);
        String normalizeUndeterminedLanguageToNull2 = normalizeUndeterminedLanguageToNull(format.language);
        if (normalizeUndeterminedLanguageToNull2 == null || normalizeUndeterminedLanguageToNull == null) {
            if (!z || normalizeUndeterminedLanguageToNull2 != null) {
                return 0;
            }
            return 1;
        } else if (normalizeUndeterminedLanguageToNull2.startsWith(normalizeUndeterminedLanguageToNull) || normalizeUndeterminedLanguageToNull.startsWith(normalizeUndeterminedLanguageToNull2)) {
            return 3;
        } else {
            if (Util.splitAtFirst(normalizeUndeterminedLanguageToNull2, "-")[0].equals(Util.splitAtFirst(normalizeUndeterminedLanguageToNull, "-")[0])) {
                return 2;
            }
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static int getMaxVideoPixelsToRetainForViewport(TrackGroup trackGroup, int i2, int i7, boolean z) {
        int i8;
        int i10 = Integer.MAX_VALUE;
        if (!(i2 == Integer.MAX_VALUE || i7 == Integer.MAX_VALUE)) {
            for (int i11 = 0; i11 < trackGroup.length; i11++) {
                Format format = trackGroup.getFormat(i11);
                int i12 = format.width;
                if (i12 > 0 && (i8 = format.height) > 0) {
                    Point maxVideoSizeInViewport = TrackSelectionUtil.getMaxVideoSizeInViewport(z, i2, i7, i12, i8);
                    int i13 = format.width;
                    int i14 = format.height;
                    int i15 = i13 * i14;
                    if (i13 >= ((int) (((float) maxVideoSizeInViewport.x) * 0.98f)) && i14 >= ((int) (((float) maxVideoSizeInViewport.y) * 0.98f)) && i15 < i10) {
                        i10 = i15;
                    }
                }
            }
        }
        return i10;
    }

    private static String getPreferredLanguageFromCaptioningManager(Context context2) {
        CaptioningManager captioningManager;
        Locale locale;
        if (context2 == null || (captioningManager = (CaptioningManager) context2.getSystemService("captioning")) == null || !captioningManager.isEnabled() || (locale = captioningManager.getLocale()) == null) {
            return null;
        }
        return Util.getLocaleLanguageTag(locale);
    }

    /* access modifiers changed from: private */
    public static int getRoleFlagMatchScore(int i2, int i7) {
        if (i2 == 0 || i2 != i7) {
            return Integer.bitCount(i2 & i7);
        }
        return Integer.MAX_VALUE;
    }

    /* access modifiers changed from: private */
    public static int getVideoCodecPreferenceScore(String str) {
        if (str == null) {
            return 0;
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1851077871:
                if (str.equals("video/dolby-vision")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1662735862:
                if (str.equals("video/av01")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1662541442:
                if (str.equals("video/hevc")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1331836730:
                if (str.equals(Encode.CodecsMime.VIDEO_CODEC_H264)) {
                    c5 = 3;
                    break;
                }
                break;
            case 1599127257:
                if (str.equals("video/x-vnd.on2.vp9")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 5;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 1;
            case 4:
                return 2;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: isAudioFormatWithinAudioChannelCountConstraints */
    public boolean lambda$selectAudioTrack$2(Format format, Parameters parameters2) {
        int i2;
        SpatializerWrapperV32 spatializerWrapperV32;
        SpatializerWrapperV32 spatializerWrapperV322;
        if (!parameters2.constrainAudioChannelCountToDeviceCapabilities) {
            return true;
        }
        Boolean bool = this.deviceIsTV;
        if ((bool != null && bool.booleanValue()) || (i2 = format.channelCount) == -1 || i2 <= 2) {
            return true;
        }
        if (isDolbyAudio(format) && (Build.VERSION.SDK_INT < 32 || (spatializerWrapperV322 = this.spatializer) == null || !spatializerWrapperV322.isSpatializationSupported())) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 32 || (spatializerWrapperV32 = this.spatializer) == null || !spatializerWrapperV32.isSpatializationSupported() || !this.spatializer.isAvailable() || !this.spatializer.isEnabled() || !this.spatializer.canBeSpatialized(this.audioAttributes, format)) {
            return false;
        }
        return true;
    }

    private static boolean isDolbyAudio(Format format) {
        String str = format.sampleMimeType;
        if (str == null) {
            return false;
        }
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals("audio/eac3-joc")) {
                    c5 = 0;
                    break;
                }
                break;
            case 187078296:
                if (str.equals("audio/ac3")) {
                    c5 = 1;
                    break;
                }
                break;
            case 187078297:
                if (str.equals("audio/ac4")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1504578661:
                if (str.equals("audio/eac3")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isObjectBasedAudio(Format format) {
        String str = format.sampleMimeType;
        if (str == null) {
            return false;
        }
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals("audio/eac3-joc")) {
                    c5 = 0;
                    break;
                }
                break;
            case 187078297:
                if (str.equals("audio/ac4")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1504698186:
                if (str.equals("audio/iamf")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$selectAudioTrack$3(Parameters parameters2, boolean z, int[] iArr, int i2, TrackGroup trackGroup, int[] iArr2) {
        int i7 = i2;
        boolean z3 = z;
        Parameters parameters3 = parameters2;
        return AudioTrackInfo.createForTrackGroup(i7, trackGroup, parameters3, iArr2, z3, new c(this, parameters3), iArr[i7]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$selectTextTrack$4(Parameters parameters2, String str, String str2, int i2, TrackGroup trackGroup, int[] iArr) {
        String str3 = str2;
        Parameters parameters3 = parameters2;
        int i7 = i2;
        int[] iArr2 = iArr;
        String str4 = str3;
        return TextTrackInfo.createForTrackGroup(i7, trackGroup, parameters3, iArr2, str, str4);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$selectVideoTrack$1(Parameters parameters2, String str, int[] iArr, Point point, int i2, TrackGroup trackGroup, int[] iArr2) {
        int i7 = iArr[i2];
        Parameters parameters3 = parameters2;
        int i8 = i2;
        String str2 = str;
        TrackGroup trackGroup2 = trackGroup;
        int i10 = i7;
        return VideoTrackInfo.createForTrackGroup(i8, trackGroup2, parameters3, iArr2, str2, i10, point);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Integer num, Integer num2) {
        if (num.intValue() == -1) {
            if (num2.intValue() == -1) {
                return 0;
            }
            return -1;
        } else if (num2.intValue() == -1) {
            return 1;
        } else {
            return num.intValue() - num2.intValue();
        }
    }

    private static void maybeConfigureRendererForOffload(Parameters parameters2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr) {
        int i2;
        int i7 = -1;
        boolean z = false;
        int i8 = 0;
        int i10 = 0;
        while (i8 < mappedTrackInfo.getRendererCount()) {
            int rendererType = mappedTrackInfo.getRendererType(i8);
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i8];
            if (rendererType == 1 || exoTrackSelection == null) {
                if (rendererType == 1 && exoTrackSelection != null && exoTrackSelection.length() == 1) {
                    if (rendererSupportsOffload(parameters2, iArr[i8][mappedTrackInfo.getTrackGroups(i8).indexOf(exoTrackSelection.getTrackGroup())][exoTrackSelection.getIndexInTrackGroup(0)], exoTrackSelection.getSelectedFormat())) {
                        i10++;
                        i7 = i8;
                    }
                }
                i8++;
            } else {
                return;
            }
        }
        if (i10 == 1) {
            if (parameters2.audioOffloadPreferences.isGaplessSupportRequired) {
                i2 = 1;
            } else {
                i2 = 2;
            }
            RendererConfiguration rendererConfiguration = rendererConfigurationArr[i7];
            if (rendererConfiguration != null && rendererConfiguration.tunneling) {
                z = true;
            }
            rendererConfigurationArr[i7] = new RendererConfiguration(i2, z);
        }
    }

    private static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr) {
        boolean z;
        boolean z3;
        int i2 = -1;
        int i7 = -1;
        int i8 = 0;
        while (true) {
            if (i8 >= mappedTrackInfo.getRendererCount()) {
                z = true;
                break;
            }
            int rendererType = mappedTrackInfo.getRendererType(i8);
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i8];
            if ((rendererType == 1 || rendererType == 2) && exoTrackSelection != null && rendererSupportsTunneling(iArr[i8], mappedTrackInfo.getTrackGroups(i8), exoTrackSelection)) {
                if (rendererType == 1) {
                    if (i7 != -1) {
                        break;
                    }
                    i7 = i8;
                } else if (i2 != -1) {
                    break;
                } else {
                    i2 = i8;
                }
            }
            i8++;
        }
        z = false;
        if (i7 == -1 || i2 == -1) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z && z3) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(0, true);
            rendererConfigurationArr[i7] = rendererConfiguration;
            rendererConfigurationArr[i2] = rendererConfiguration;
        }
    }

    /* access modifiers changed from: private */
    public void maybeInvalidateForAudioChannelCountConstraints() {
        boolean z;
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                if (!this.parameters.constrainAudioChannelCountToDeviceCapabilities || Build.VERSION.SDK_INT < 32 || (spatializerWrapperV32 = this.spatializer) == null || !spatializerWrapperV32.isSpatializationSupported()) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z) {
            invalidate();
        }
    }

    public static String normalizeUndeterminedLanguageToNull(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "und")) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: private */
    public static boolean rendererSupportsOffload(Parameters parameters2, int i2, Format format) {
        boolean z;
        boolean z3;
        if (RendererCapabilities.getAudioOffloadSupport(i2) == 0) {
            return false;
        }
        if (parameters2.audioOffloadPreferences.isSpeedChangeSupportRequired && (RendererCapabilities.getAudioOffloadSupport(i2) & 2048) == 0) {
            return false;
        }
        if (parameters2.audioOffloadPreferences.isGaplessSupportRequired) {
            if (format.encoderDelay == 0 && format.encoderPadding == 0) {
                z = false;
            } else {
                z = true;
            }
            if ((RendererCapabilities.getAudioOffloadSupport(i2) & 1024) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z || z3) {
                return true;
            }
            return false;
        }
        return true;
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, ExoTrackSelection exoTrackSelection) {
        if (exoTrackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
        for (int i2 = 0; i2 < exoTrackSelection.length(); i2++) {
            if (RendererCapabilities.getTunnelingSupport(iArr[indexOf][exoTrackSelection.getIndexInTrackGroup(i2)]) != 32) {
                return false;
            }
        }
        return true;
    }

    private <T extends TrackInfo<T>> Pair<ExoTrackSelection.Definition, Integer> selectTracksForType(int i2, MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, TrackInfo.Factory<T> factory, Comparator<List<T>> comparator) {
        int i7;
        Object obj;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        ArrayList arrayList = new ArrayList();
        int rendererCount = mappedTrackInfo2.getRendererCount();
        int i8 = 0;
        while (i8 < rendererCount) {
            if (i2 == mappedTrackInfo2.getRendererType(i8)) {
                TrackGroupArray trackGroups = mappedTrackInfo2.getTrackGroups(i8);
                int i10 = 0;
                while (i10 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i10);
                    List<T> create = factory.create(i8, trackGroup, iArr[i8][i10]);
                    boolean[] zArr = new boolean[trackGroup.length];
                    int i11 = 0;
                    while (i11 < trackGroup.length) {
                        TrackInfo trackInfo = (TrackInfo) create.get(i11);
                        int selectionEligibility = trackInfo.getSelectionEligibility();
                        if (zArr[i11] || selectionEligibility == 0) {
                            i7 = rendererCount;
                        } else {
                            if (selectionEligibility == 1) {
                                obj = U.B(trackInfo);
                            } else {
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(trackInfo);
                                int i12 = i11 + 1;
                                while (i12 < trackGroup.length) {
                                    TrackInfo trackInfo2 = (TrackInfo) create.get(i12);
                                    int i13 = rendererCount;
                                    if (trackInfo2.getSelectionEligibility() == 2 && trackInfo.isCompatibleForAdaptationWith(trackInfo2)) {
                                        arrayList2.add(trackInfo2);
                                        zArr[i12] = true;
                                    }
                                    i12++;
                                    MappingTrackSelector.MappedTrackInfo mappedTrackInfo3 = mappedTrackInfo;
                                    rendererCount = i13;
                                }
                                obj = arrayList2;
                            }
                            i7 = rendererCount;
                            arrayList.add(obj);
                        }
                        i11++;
                        MappingTrackSelector.MappedTrackInfo mappedTrackInfo4 = mappedTrackInfo;
                        rendererCount = i7;
                    }
                    int i14 = rendererCount;
                    i10++;
                    MappingTrackSelector.MappedTrackInfo mappedTrackInfo5 = mappedTrackInfo;
                }
            }
            TrackInfo.Factory<T> factory2 = factory;
            i8++;
            mappedTrackInfo2 = mappedTrackInfo;
            rendererCount = rendererCount;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        List list = (List) Collections.max(arrayList, comparator);
        int[] iArr2 = new int[list.size()];
        for (int i15 = 0; i15 < list.size(); i15++) {
            iArr2[i15] = ((TrackInfo) list.get(i15)).trackIndex;
        }
        TrackInfo trackInfo3 = (TrackInfo) list.get(0);
        return Pair.create(new ExoTrackSelection.Definition(trackInfo3.trackGroup, iArr2), Integer.valueOf(trackInfo3.rendererIndex));
    }

    private void setParametersInternal(Parameters parameters2) {
        boolean equals;
        Assertions.checkNotNull(parameters2);
        synchronized (this.lock) {
            equals = this.parameters.equals(parameters2);
            this.parameters = parameters2;
        }
        if (!equals) {
            if (parameters2.constrainAudioChannelCountToDeviceCapabilities && this.context == null) {
                Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
            }
            invalidate();
        }
    }

    public Parameters getParameters() {
        Parameters parameters2;
        synchronized (this.lock) {
            parameters2 = this.parameters;
        }
        return parameters2;
    }

    public boolean isSetParametersSupported() {
        return true;
    }

    public void release() {
        SpatializerWrapperV32 spatializerWrapperV32;
        boolean z;
        synchronized (this.lock) {
            try {
                Thread thread = this.playbackThread;
                if (thread != null) {
                    if (thread == Thread.currentThread()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.checkState(z, "DefaultTrackSelector is accessed on the wrong thread.");
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null) {
            spatializerWrapperV32.release();
            this.spatializer = null;
        }
        super.release();
    }

    public ExoTrackSelection.Definition[] selectAllTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2) {
        String str;
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] definitionArr = new ExoTrackSelection.Definition[rendererCount];
        Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack = selectAudioTrack(mappedTrackInfo, iArr, iArr2, parameters2);
        if (selectAudioTrack != null) {
            definitionArr[((Integer) selectAudioTrack.second).intValue()] = (ExoTrackSelection.Definition) selectAudioTrack.first;
        }
        Pair<ExoTrackSelection.Definition, Integer> pair = null;
        if (selectAudioTrack == null) {
            str = null;
        } else {
            Object obj = selectAudioTrack.first;
            str = ((ExoTrackSelection.Definition) obj).group.getFormat(((ExoTrackSelection.Definition) obj).tracks[0]).language;
        }
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        int[][][] iArr3 = iArr;
        Parameters parameters3 = parameters2;
        Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack = selectVideoTrack(mappedTrackInfo2, iArr3, iArr2, parameters3, str);
        String str2 = str;
        if (parameters3.isPrioritizeImageOverVideoEnabled || selectVideoTrack == null) {
            pair = selectImageTrack(mappedTrackInfo2, iArr3, parameters3);
        }
        if (pair != null) {
            definitionArr[((Integer) pair.second).intValue()] = (ExoTrackSelection.Definition) pair.first;
        } else if (selectVideoTrack != null) {
            definitionArr[((Integer) selectVideoTrack.second).intValue()] = (ExoTrackSelection.Definition) selectVideoTrack.first;
        }
        Pair<ExoTrackSelection.Definition, Integer> selectTextTrack = selectTextTrack(mappedTrackInfo2, iArr3, parameters3, str2);
        if (selectTextTrack != null) {
            definitionArr[((Integer) selectTextTrack.second).intValue()] = (ExoTrackSelection.Definition) selectTextTrack.first;
        }
        for (int i2 = 0; i2 < rendererCount; i2++) {
            int rendererType = mappedTrackInfo2.getRendererType(i2);
            if (!(rendererType == 2 || rendererType == 1 || rendererType == 3 || rendererType == 4)) {
                definitionArr[i2] = selectOtherTrack(rendererType, mappedTrackInfo2.getTrackGroups(i2), iArr3[i2], parameters3);
            }
        }
        return definitionArr;
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2) {
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < mappedTrackInfo.getRendererCount()) {
                if (2 == mappedTrackInfo.getRendererType(i2) && mappedTrackInfo.getTrackGroups(i2).length > 0) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return selectTracksForType(1, mappedTrackInfo, iArr, new N.b(this, parameters2, z, iArr2), new a(2));
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectImageTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, Parameters parameters2) {
        if (parameters2.audioOffloadPreferences.audioOffloadMode == 2) {
            return null;
        }
        return selectTracksForType(4, mappedTrackInfo, iArr, new N.a(parameters2), new a(0));
    }

    public ExoTrackSelection.Definition selectOtherTrack(int i2, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters2) {
        if (parameters2.audioOffloadPreferences.audioOffloadMode == 2) {
            return null;
        }
        int i7 = 0;
        TrackGroup trackGroup = null;
        OtherTrackScore otherTrackScore = null;
        for (int i8 = 0; i8 < trackGroupArray.length; i8++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i8);
            int[] iArr2 = iArr[i8];
            for (int i10 = 0; i10 < trackGroup2.length; i10++) {
                if (RendererCapabilities.isFormatSupported(iArr2[i10], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    OtherTrackScore otherTrackScore2 = new OtherTrackScore(trackGroup2.getFormat(i10), iArr2[i10]);
                    if (otherTrackScore == null || otherTrackScore2.compareTo(otherTrackScore) > 0) {
                        trackGroup = trackGroup2;
                        i7 = i10;
                        otherTrackScore = otherTrackScore2;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new ExoTrackSelection.Definition(trackGroup, i7);
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectTextTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, Parameters parameters2, String str) {
        String str2 = null;
        if (parameters2.audioOffloadPreferences.audioOffloadMode == 2) {
            return null;
        }
        if (parameters2.usePreferredTextLanguagesAndRoleFlagsFromCaptioningManager) {
            str2 = getPreferredLanguageFromCaptioningManager(this.context);
        }
        return selectTracksForType(3, mappedTrackInfo, iArr, new A4.Q((Object) parameters2, (Object) str, (Object) str2, 7), new a(3));
    }

    public final Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
        Parameters parameters2;
        RendererConfiguration rendererConfiguration;
        Context context2;
        synchronized (this.lock) {
            this.playbackThread = Thread.currentThread();
            parameters2 = this.parameters;
        }
        if (this.deviceIsTV == null && (context2 = this.context) != null) {
            this.deviceIsTV = Boolean.valueOf(Util.isTv(context2));
        }
        if (parameters2.constrainAudioChannelCountToDeviceCapabilities && Build.VERSION.SDK_INT >= 32 && this.spatializer == null) {
            this.spatializer = new SpatializerWrapperV32(this.context, this, this.deviceIsTV);
        }
        int rendererCount = mappedTrackInfo.getRendererCount();
        ExoTrackSelection.Definition[] selectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters2);
        applyTrackSelectionOverrides(mappedTrackInfo, parameters2, selectAllTracks);
        applyLegacyRendererOverrides(mappedTrackInfo, parameters2, selectAllTracks);
        for (int i2 = 0; i2 < rendererCount; i2++) {
            int rendererType = mappedTrackInfo.getRendererType(i2);
            if (parameters2.getRendererDisabled(i2) || parameters2.disabledTrackTypes.contains(Integer.valueOf(rendererType))) {
                selectAllTracks[i2] = null;
            }
        }
        ExoTrackSelection[] createTrackSelections = this.trackSelectionFactory.createTrackSelections(selectAllTracks, getBandwidthMeter(), mediaPeriodId, timeline);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCount];
        for (int i7 = 0; i7 < rendererCount; i7++) {
            int rendererType2 = mappedTrackInfo.getRendererType(i7);
            if (parameters2.getRendererDisabled(i7) || parameters2.disabledTrackTypes.contains(Integer.valueOf(rendererType2)) || (mappedTrackInfo.getRendererType(i7) != -2 && createTrackSelections[i7] == null)) {
                rendererConfiguration = null;
            } else {
                rendererConfiguration = RendererConfiguration.DEFAULT;
            }
            rendererConfigurationArr[i7] = rendererConfiguration;
        }
        if (parameters2.tunnelingEnabled) {
            maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, createTrackSelections);
        }
        if (parameters2.audioOffloadPreferences.audioOffloadMode != 0) {
            maybeConfigureRendererForOffload(parameters2, mappedTrackInfo, iArr, rendererConfigurationArr, createTrackSelections);
        }
        return Pair.create(rendererConfigurationArr, createTrackSelections);
    }

    public Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters2, String str) {
        Context context2;
        Point point = null;
        if (parameters2.audioOffloadPreferences.audioOffloadMode == 2) {
            return null;
        }
        if (parameters2.isViewportSizeLimitedByPhysicalDisplaySize && (context2 = this.context) != null) {
            point = Util.getCurrentDisplayModeSize(context2);
        }
        int[] iArr3 = iArr2;
        int[][][] iArr4 = iArr;
        return selectTracksForType(2, mappedTrackInfo, iArr4, new f((Object) parameters2, (Object) str, (Object) iArr3, (Object) point, 2), new a(1));
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!this.audioAttributes.equals(audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            maybeInvalidateForAudioChannelCountConstraints();
        }
    }

    public void setParameters(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters instanceof Parameters) {
            setParametersInternal((Parameters) trackSelectionParameters);
        }
        setParametersInternal(new Parameters.Builder().set(trackSelectionParameters).build());
    }

    public DefaultTrackSelector(Context context2, ExoTrackSelection.Factory factory) {
        this(context2, (TrackSelectionParameters) Parameters.DEFAULT, factory);
    }

    public DefaultTrackSelector(Context context2, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory) {
        this(trackSelectionParameters, factory, context2);
    }

    private DefaultTrackSelector(TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory, Context context2) {
        this.lock = new Object();
        this.context = context2 != null ? context2.getApplicationContext() : null;
        this.trackSelectionFactory = factory;
        if (trackSelectionParameters instanceof Parameters) {
            this.parameters = (Parameters) trackSelectionParameters;
        } else {
            this.parameters = Parameters.DEFAULT.buildUpon().set(trackSelectionParameters).build();
        }
        this.audioAttributes = AudioAttributes.DEFAULT;
        if (this.parameters.constrainAudioChannelCountToDeviceCapabilities && context2 == null) {
            Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        }
    }

    public RendererCapabilities.Listener getRendererCapabilitiesListener() {
        return this;
    }
}
