package androidx.media3.common;

import F2.D0;
import F2.G;
import F2.Q;
import F2.U;
import F2.Y;
import F2.y0;
import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaItem {
    public static final MediaItem EMPTY = new Builder().build();
    private static final String FIELD_CLIPPING_PROPERTIES = Util.intToStringMaxRadix(3);
    private static final String FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(1);
    private static final String FIELD_LOCAL_CONFIGURATION = Util.intToStringMaxRadix(5);
    private static final String FIELD_MEDIA_ID = Util.intToStringMaxRadix(0);
    private static final String FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(2);
    private static final String FIELD_REQUEST_METADATA = Util.intToStringMaxRadix(4);
    public final ClippingConfiguration clippingConfiguration;
    @Deprecated
    public final ClippingProperties clippingProperties;
    public final LiveConfiguration liveConfiguration;
    public final LocalConfiguration localConfiguration;
    public final String mediaId;
    public final MediaMetadata mediaMetadata;
    @Deprecated
    public final LocalConfiguration playbackProperties;
    public final RequestMetadata requestMetadata;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AdsConfiguration {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ClippingConfiguration.Builder clippingConfiguration;
        private String customCacheKey;
        private DrmConfiguration.Builder drmConfiguration;
        private long imageDurationMs;
        private LiveConfiguration.Builder liveConfiguration;
        private String mediaId;
        private MediaMetadata mediaMetadata;
        private String mimeType;
        private RequestMetadata requestMetadata;
        private List<Object> streamKeys;
        private U subtitleConfigurations;
        private Object tag;
        private Uri uri;

        public MediaItem build() {
            boolean z;
            LocalConfiguration localConfiguration;
            if (this.drmConfiguration.licenseUri == null || this.drmConfiguration.scheme != null) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            Uri uri2 = this.uri;
            DrmConfiguration drmConfiguration2 = null;
            if (uri2 != null) {
                String str = this.mimeType;
                if (this.drmConfiguration.scheme != null) {
                    drmConfiguration2 = this.drmConfiguration.build();
                }
                localConfiguration = new LocalConfiguration(uri2, str, drmConfiguration2, (AdsConfiguration) null, this.streamKeys, this.customCacheKey, this.subtitleConfigurations, this.tag, this.imageDurationMs);
            } else {
                localConfiguration = null;
            }
            String str2 = this.mediaId;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = str2;
            ClippingProperties buildClippingProperties = this.clippingConfiguration.buildClippingProperties();
            LiveConfiguration build = this.liveConfiguration.build();
            MediaMetadata mediaMetadata2 = this.mediaMetadata;
            if (mediaMetadata2 == null) {
                mediaMetadata2 = MediaMetadata.EMPTY;
            }
            return new MediaItem(str3, buildClippingProperties, localConfiguration, build, mediaMetadata2, this.requestMetadata);
        }

        public Builder setClippingConfiguration(ClippingConfiguration clippingConfiguration2) {
            this.clippingConfiguration = clippingConfiguration2.buildUpon();
            return this;
        }

        public Builder setLiveConfiguration(LiveConfiguration liveConfiguration2) {
            this.liveConfiguration = liveConfiguration2.buildUpon();
            return this;
        }

        public Builder setMediaId(String str) {
            this.mediaId = (String) Assertions.checkNotNull(str);
            return this;
        }

        public Builder setSubtitleConfigurations(List<SubtitleConfiguration> list) {
            this.subtitleConfigurations = U.y(list);
            return this;
        }

        public Builder setTag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder setUri(String str) {
            return setUri(str == null ? null : Uri.parse(str));
        }

        public Builder() {
            this.clippingConfiguration = new ClippingConfiguration.Builder();
            this.drmConfiguration = new DrmConfiguration.Builder();
            this.streamKeys = Collections.EMPTY_LIST;
            G g = U.e;
            this.subtitleConfigurations = y0.f278h;
            this.liveConfiguration = new LiveConfiguration.Builder();
            this.requestMetadata = RequestMetadata.EMPTY;
            this.imageDurationMs = -9223372036854775807L;
        }

        public Builder setUri(Uri uri2) {
            this.uri = uri2;
            return this;
        }

        private Builder(MediaItem mediaItem) {
            this();
            DrmConfiguration.Builder builder;
            this.clippingConfiguration = mediaItem.clippingConfiguration.buildUpon();
            this.mediaId = mediaItem.mediaId;
            this.mediaMetadata = mediaItem.mediaMetadata;
            this.liveConfiguration = mediaItem.liveConfiguration.buildUpon();
            this.requestMetadata = mediaItem.requestMetadata;
            LocalConfiguration localConfiguration = mediaItem.localConfiguration;
            if (localConfiguration != null) {
                this.customCacheKey = localConfiguration.customCacheKey;
                this.mimeType = localConfiguration.mimeType;
                this.uri = localConfiguration.uri;
                this.streamKeys = localConfiguration.streamKeys;
                this.subtitleConfigurations = localConfiguration.subtitleConfigurations;
                this.tag = localConfiguration.tag;
                DrmConfiguration drmConfiguration2 = localConfiguration.drmConfiguration;
                if (drmConfiguration2 != null) {
                    builder = drmConfiguration2.buildUpon();
                } else {
                    builder = new DrmConfiguration.Builder();
                }
                this.drmConfiguration = builder;
                this.imageDurationMs = localConfiguration.imageDurationMs;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClippingConfiguration {
        private static final String FIELD_ALLOW_UNSEEKABLE_MEDIA = Util.intToStringMaxRadix(7);
        private static final String FIELD_END_POSITION_MS = Util.intToStringMaxRadix(1);
        static final String FIELD_END_POSITION_US = Util.intToStringMaxRadix(6);
        private static final String FIELD_RELATIVE_TO_DEFAULT_POSITION = Util.intToStringMaxRadix(3);
        private static final String FIELD_RELATIVE_TO_LIVE_WINDOW = Util.intToStringMaxRadix(2);
        private static final String FIELD_STARTS_AT_KEY_FRAME = Util.intToStringMaxRadix(4);
        private static final String FIELD_START_POSITION_MS = Util.intToStringMaxRadix(0);
        static final String FIELD_START_POSITION_US = Util.intToStringMaxRadix(5);
        public static final ClippingConfiguration UNSET = new Builder().build();
        public final boolean allowUnseekableMedia;
        public final long endPositionMs;
        public final long endPositionUs;
        public final boolean relativeToDefaultPosition;
        public final boolean relativeToLiveWindow;
        public final long startPositionMs;
        public final long startPositionUs;
        public final boolean startsAtKeyFrame;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public boolean allowUnseekableMedia;
            /* access modifiers changed from: private */
            public long endPositionUs;
            /* access modifiers changed from: private */
            public boolean relativeToDefaultPosition;
            /* access modifiers changed from: private */
            public boolean relativeToLiveWindow;
            /* access modifiers changed from: private */
            public long startPositionUs;
            /* access modifiers changed from: private */
            public boolean startsAtKeyFrame;

            public ClippingConfiguration build() {
                return new ClippingConfiguration(this);
            }

            @Deprecated
            public ClippingProperties buildClippingProperties() {
                return new ClippingProperties(this);
            }

            public Builder setEndPositionUs(long j2) {
                boolean z;
                if (j2 == Long.MIN_VALUE || j2 >= 0) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkArgument(z);
                this.endPositionUs = j2;
                return this;
            }

            public Builder setStartPositionMs(long j2) {
                return setStartPositionUs(Util.msToUs(j2));
            }

            public Builder setStartPositionUs(long j2) {
                boolean z;
                if (j2 >= 0) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkArgument(z);
                this.startPositionUs = j2;
                return this;
            }

            public Builder setStartsAtKeyFrame(boolean z) {
                this.startsAtKeyFrame = z;
                return this;
            }

            public Builder() {
                this.endPositionUs = Long.MIN_VALUE;
            }

            private Builder(ClippingConfiguration clippingConfiguration) {
                this.startPositionUs = clippingConfiguration.startPositionUs;
                this.endPositionUs = clippingConfiguration.endPositionUs;
                this.relativeToLiveWindow = clippingConfiguration.relativeToLiveWindow;
                this.relativeToDefaultPosition = clippingConfiguration.relativeToDefaultPosition;
                this.startsAtKeyFrame = clippingConfiguration.startsAtKeyFrame;
                this.allowUnseekableMedia = clippingConfiguration.allowUnseekableMedia;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClippingConfiguration)) {
                return false;
            }
            ClippingConfiguration clippingConfiguration = (ClippingConfiguration) obj;
            if (this.startPositionUs == clippingConfiguration.startPositionUs && this.endPositionUs == clippingConfiguration.endPositionUs && this.relativeToLiveWindow == clippingConfiguration.relativeToLiveWindow && this.relativeToDefaultPosition == clippingConfiguration.relativeToDefaultPosition && this.startsAtKeyFrame == clippingConfiguration.startsAtKeyFrame && this.allowUnseekableMedia == clippingConfiguration.allowUnseekableMedia) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long j2 = this.startPositionUs;
            long j3 = this.endPositionUs;
            return (((((((((((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31) + (this.relativeToLiveWindow ? 1 : 0)) * 31) + (this.relativeToDefaultPosition ? 1 : 0)) * 31) + (this.startsAtKeyFrame ? 1 : 0)) * 31) + (this.allowUnseekableMedia ? 1 : 0);
        }

        private ClippingConfiguration(Builder builder) {
            this.startPositionMs = Util.usToMs(builder.startPositionUs);
            this.endPositionMs = Util.usToMs(builder.endPositionUs);
            this.startPositionUs = builder.startPositionUs;
            this.endPositionUs = builder.endPositionUs;
            this.relativeToLiveWindow = builder.relativeToLiveWindow;
            this.relativeToDefaultPosition = builder.relativeToDefaultPosition;
            this.startsAtKeyFrame = builder.startsAtKeyFrame;
            this.allowUnseekableMedia = builder.allowUnseekableMedia;
        }
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClippingProperties extends ClippingConfiguration {
        public static final ClippingProperties UNSET = new ClippingConfiguration.Builder().buildClippingProperties();

        private ClippingProperties(ClippingConfiguration.Builder builder) {
            super(builder);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DrmConfiguration {
        private static final String FIELD_FORCED_SESSION_TRACK_TYPES = Util.intToStringMaxRadix(6);
        private static final String FIELD_FORCE_DEFAULT_LICENSE_URI = Util.intToStringMaxRadix(5);
        private static final String FIELD_KEY_SET_ID = Util.intToStringMaxRadix(7);
        private static final String FIELD_LICENSE_REQUEST_HEADERS = Util.intToStringMaxRadix(2);
        private static final String FIELD_LICENSE_URI = Util.intToStringMaxRadix(1);
        private static final String FIELD_MULTI_SESSION = Util.intToStringMaxRadix(3);
        static final String FIELD_PLAY_CLEAR_CONTENT_WITHOUT_KEY = Util.intToStringMaxRadix(4);
        private static final String FIELD_SCHEME = Util.intToStringMaxRadix(0);
        public final boolean forceDefaultLicenseUri;
        public final U forcedSessionTrackTypes;
        /* access modifiers changed from: private */
        public final byte[] keySetId;
        public final Y licenseRequestHeaders;
        public final Uri licenseUri;
        public final boolean multiSession;
        public final boolean playClearContentWithoutKey;
        @Deprecated
        public final Y requestHeaders;
        public final UUID scheme;
        @Deprecated
        public final U sessionForClearTypes;
        @Deprecated
        public final UUID uuid;

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrmConfiguration)) {
                return false;
            }
            DrmConfiguration drmConfiguration = (DrmConfiguration) obj;
            if (!this.scheme.equals(drmConfiguration.scheme) || !Objects.equals(this.licenseUri, drmConfiguration.licenseUri) || !Objects.equals(this.licenseRequestHeaders, drmConfiguration.licenseRequestHeaders) || this.multiSession != drmConfiguration.multiSession || this.forceDefaultLicenseUri != drmConfiguration.forceDefaultLicenseUri || this.playClearContentWithoutKey != drmConfiguration.playClearContentWithoutKey || !this.forcedSessionTrackTypes.equals(drmConfiguration.forcedSessionTrackTypes) || !Arrays.equals(this.keySetId, drmConfiguration.keySetId)) {
                return false;
            }
            return true;
        }

        public byte[] getKeySetId() {
            byte[] bArr = this.keySetId;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
            return null;
        }

        public int hashCode() {
            int i2;
            int hashCode = this.scheme.hashCode() * 31;
            Uri uri = this.licenseUri;
            if (uri != null) {
                i2 = uri.hashCode();
            } else {
                i2 = 0;
            }
            int hashCode2 = this.licenseRequestHeaders.hashCode();
            int hashCode3 = this.forcedSessionTrackTypes.hashCode();
            return Arrays.hashCode(this.keySetId) + ((hashCode3 + ((((((((hashCode2 + ((hashCode + i2) * 31)) * 31) + (this.multiSession ? 1 : 0)) * 31) + (this.forceDefaultLicenseUri ? 1 : 0)) * 31) + (this.playClearContentWithoutKey ? 1 : 0)) * 31)) * 31);
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public boolean forceDefaultLicenseUri;
            /* access modifiers changed from: private */
            public U forcedSessionTrackTypes;
            /* access modifiers changed from: private */
            public byte[] keySetId;
            /* access modifiers changed from: private */
            public Y licenseRequestHeaders;
            /* access modifiers changed from: private */
            public Uri licenseUri;
            /* access modifiers changed from: private */
            public boolean multiSession;
            /* access modifiers changed from: private */
            public boolean playClearContentWithoutKey;
            /* access modifiers changed from: private */
            public UUID scheme;

            public DrmConfiguration build() {
                return new DrmConfiguration(this);
            }

            @Deprecated
            private Builder() {
                this.licenseRequestHeaders = D0.k;
                this.playClearContentWithoutKey = true;
                G g = U.e;
                this.forcedSessionTrackTypes = y0.f278h;
            }

            private Builder(DrmConfiguration drmConfiguration) {
                this.scheme = drmConfiguration.scheme;
                this.licenseUri = drmConfiguration.licenseUri;
                this.licenseRequestHeaders = drmConfiguration.licenseRequestHeaders;
                this.multiSession = drmConfiguration.multiSession;
                this.playClearContentWithoutKey = drmConfiguration.playClearContentWithoutKey;
                this.forceDefaultLicenseUri = drmConfiguration.forceDefaultLicenseUri;
                this.forcedSessionTrackTypes = drmConfiguration.forcedSessionTrackTypes;
                this.keySetId = drmConfiguration.keySetId;
            }
        }

        private DrmConfiguration(Builder builder) {
            Assertions.checkState(!builder.forceDefaultLicenseUri || builder.licenseUri != null);
            UUID uuid2 = (UUID) Assertions.checkNotNull(builder.scheme);
            this.scheme = uuid2;
            this.uuid = uuid2;
            this.licenseUri = builder.licenseUri;
            this.requestHeaders = builder.licenseRequestHeaders;
            this.licenseRequestHeaders = builder.licenseRequestHeaders;
            this.multiSession = builder.multiSession;
            this.forceDefaultLicenseUri = builder.forceDefaultLicenseUri;
            this.playClearContentWithoutKey = builder.playClearContentWithoutKey;
            this.sessionForClearTypes = builder.forcedSessionTrackTypes;
            this.forcedSessionTrackTypes = builder.forcedSessionTrackTypes;
            this.keySetId = builder.keySetId != null ? Arrays.copyOf(builder.keySetId, builder.keySetId.length) : null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LiveConfiguration {
        private static final String FIELD_MAX_OFFSET_MS = Util.intToStringMaxRadix(2);
        private static final String FIELD_MAX_PLAYBACK_SPEED = Util.intToStringMaxRadix(4);
        private static final String FIELD_MIN_OFFSET_MS = Util.intToStringMaxRadix(1);
        private static final String FIELD_MIN_PLAYBACK_SPEED = Util.intToStringMaxRadix(3);
        private static final String FIELD_TARGET_OFFSET_MS = Util.intToStringMaxRadix(0);
        public static final LiveConfiguration UNSET = new Builder().build();
        public final long maxOffsetMs;
        public final float maxPlaybackSpeed;
        public final long minOffsetMs;
        public final float minPlaybackSpeed;
        public final long targetOffsetMs;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public long maxOffsetMs;
            /* access modifiers changed from: private */
            public float maxPlaybackSpeed;
            /* access modifiers changed from: private */
            public long minOffsetMs;
            /* access modifiers changed from: private */
            public float minPlaybackSpeed;
            /* access modifiers changed from: private */
            public long targetOffsetMs;

            public LiveConfiguration build() {
                return new LiveConfiguration(this);
            }

            public Builder setMaxOffsetMs(long j2) {
                this.maxOffsetMs = j2;
                return this;
            }

            public Builder setMaxPlaybackSpeed(float f) {
                this.maxPlaybackSpeed = f;
                return this;
            }

            public Builder setMinOffsetMs(long j2) {
                this.minOffsetMs = j2;
                return this;
            }

            public Builder setMinPlaybackSpeed(float f) {
                this.minPlaybackSpeed = f;
                return this;
            }

            public Builder setTargetOffsetMs(long j2) {
                this.targetOffsetMs = j2;
                return this;
            }

            public Builder() {
                this.targetOffsetMs = -9223372036854775807L;
                this.minOffsetMs = -9223372036854775807L;
                this.maxOffsetMs = -9223372036854775807L;
                this.minPlaybackSpeed = -3.4028235E38f;
                this.maxPlaybackSpeed = -3.4028235E38f;
            }

            private Builder(LiveConfiguration liveConfiguration) {
                this.targetOffsetMs = liveConfiguration.targetOffsetMs;
                this.minOffsetMs = liveConfiguration.minOffsetMs;
                this.maxOffsetMs = liveConfiguration.maxOffsetMs;
                this.minPlaybackSpeed = liveConfiguration.minPlaybackSpeed;
                this.maxPlaybackSpeed = liveConfiguration.maxPlaybackSpeed;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveConfiguration)) {
                return false;
            }
            LiveConfiguration liveConfiguration = (LiveConfiguration) obj;
            if (this.targetOffsetMs == liveConfiguration.targetOffsetMs && this.minOffsetMs == liveConfiguration.minOffsetMs && this.maxOffsetMs == liveConfiguration.maxOffsetMs && this.minPlaybackSpeed == liveConfiguration.minPlaybackSpeed && this.maxPlaybackSpeed == liveConfiguration.maxPlaybackSpeed) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i2;
            long j2 = this.targetOffsetMs;
            long j3 = this.minOffsetMs;
            long j8 = this.maxOffsetMs;
            int i7 = ((((((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) ((j8 >>> 32) ^ j8))) * 31;
            float f = this.minPlaybackSpeed;
            int i8 = 0;
            if (f != 0.0f) {
                i2 = Float.floatToIntBits(f);
            } else {
                i2 = 0;
            }
            int i10 = (i7 + i2) * 31;
            float f5 = this.maxPlaybackSpeed;
            if (f5 != 0.0f) {
                i8 = Float.floatToIntBits(f5);
            }
            return i10 + i8;
        }

        private LiveConfiguration(Builder builder) {
            this(builder.targetOffsetMs, builder.minOffsetMs, builder.maxOffsetMs, builder.minPlaybackSpeed, builder.maxPlaybackSpeed);
        }

        @Deprecated
        public LiveConfiguration(long j2, long j3, long j8, float f, float f5) {
            this.targetOffsetMs = j2;
            this.minOffsetMs = j3;
            this.maxOffsetMs = j8;
            this.minPlaybackSpeed = f;
            this.maxPlaybackSpeed = f5;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LocalConfiguration {
        private static final String FIELD_ADS_CONFIGURATION = Util.intToStringMaxRadix(3);
        private static final String FIELD_CUSTOM_CACHE_KEY = Util.intToStringMaxRadix(5);
        private static final String FIELD_DRM_CONFIGURATION = Util.intToStringMaxRadix(2);
        private static final String FIELD_IMAGE_DURATION_MS = Util.intToStringMaxRadix(7);
        private static final String FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
        private static final String FIELD_STREAM_KEYS = Util.intToStringMaxRadix(4);
        private static final String FIELD_SUBTITLE_CONFIGURATION = Util.intToStringMaxRadix(6);
        private static final String FIELD_URI = Util.intToStringMaxRadix(0);
        public final String customCacheKey;
        public final DrmConfiguration drmConfiguration;
        public final long imageDurationMs;
        public final String mimeType;
        public final List<Object> streamKeys;
        public final U subtitleConfigurations;
        @Deprecated
        public final List<Subtitle> subtitles;
        public final Object tag;
        public final Uri uri;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LocalConfiguration)) {
                return false;
            }
            LocalConfiguration localConfiguration = (LocalConfiguration) obj;
            if (!this.uri.equals(localConfiguration.uri) || !Objects.equals(this.mimeType, localConfiguration.mimeType) || !Objects.equals(this.drmConfiguration, localConfiguration.drmConfiguration) || !this.streamKeys.equals(localConfiguration.streamKeys) || !Objects.equals(this.customCacheKey, localConfiguration.customCacheKey) || !this.subtitleConfigurations.equals(localConfiguration.subtitleConfigurations) || !Objects.equals(this.tag, localConfiguration.tag) || this.imageDurationMs != localConfiguration.imageDurationMs) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i2;
            int i7;
            int i8;
            int hashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int i10 = 0;
            if (str == null) {
                i2 = 0;
            } else {
                i2 = str.hashCode();
            }
            int i11 = (hashCode + i2) * 31;
            DrmConfiguration drmConfiguration2 = this.drmConfiguration;
            if (drmConfiguration2 == null) {
                i7 = 0;
            } else {
                i7 = drmConfiguration2.hashCode();
            }
            int hashCode2 = (this.streamKeys.hashCode() + ((i11 + i7) * 961)) * 31;
            String str2 = this.customCacheKey;
            if (str2 == null) {
                i8 = 0;
            } else {
                i8 = str2.hashCode();
            }
            int hashCode3 = (this.subtitleConfigurations.hashCode() + ((hashCode2 + i8) * 31)) * 31;
            Object obj = this.tag;
            if (obj != null) {
                i10 = obj.hashCode();
            }
            return (int) ((((long) (hashCode3 + i10)) * 31) + this.imageDurationMs);
        }

        private LocalConfiguration(Uri uri2, String str, DrmConfiguration drmConfiguration2, AdsConfiguration adsConfiguration, List<Object> list, String str2, U u, Object obj, long j2) {
            this.uri = uri2;
            this.mimeType = MimeTypes.normalizeMimeType(str);
            this.drmConfiguration = drmConfiguration2;
            this.streamKeys = list;
            this.customCacheKey = str2;
            this.subtitleConfigurations = u;
            Q x9 = U.x();
            for (int i2 = 0; i2 < u.size(); i2++) {
                x9.a(((SubtitleConfiguration) u.get(i2)).buildUpon().buildSubtitle());
            }
            this.subtitles = x9.f();
            this.tag = obj;
            this.imageDurationMs = j2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RequestMetadata {
        public static final RequestMetadata EMPTY = new Builder().build();
        private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(2);
        private static final String FIELD_MEDIA_URI = Util.intToStringMaxRadix(0);
        private static final String FIELD_SEARCH_QUERY = Util.intToStringMaxRadix(1);
        public final Bundle extras;
        public final Uri mediaUri;
        public final String searchQuery;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public Bundle extras;
            /* access modifiers changed from: private */
            public Uri mediaUri;
            /* access modifiers changed from: private */
            public String searchQuery;

            public RequestMetadata build() {
                return new RequestMetadata(this);
            }
        }

        public boolean equals(Object obj) {
            boolean z;
            boolean z3;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RequestMetadata)) {
                return false;
            }
            RequestMetadata requestMetadata = (RequestMetadata) obj;
            if (Objects.equals(this.mediaUri, requestMetadata.mediaUri) && Objects.equals(this.searchQuery, requestMetadata.searchQuery)) {
                if (this.extras == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (requestMetadata.extras == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z == z3) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i2;
            int i7;
            Uri uri = this.mediaUri;
            int i8 = 0;
            if (uri == null) {
                i2 = 0;
            } else {
                i2 = uri.hashCode();
            }
            int i10 = i2 * 31;
            String str = this.searchQuery;
            if (str == null) {
                i7 = 0;
            } else {
                i7 = str.hashCode();
            }
            int i11 = (i10 + i7) * 31;
            if (this.extras != null) {
                i8 = 1;
            }
            return i11 + i8;
        }

        private RequestMetadata(Builder builder) {
            this.mediaUri = builder.mediaUri;
            this.searchQuery = builder.searchQuery;
            this.extras = builder.extras;
        }
    }

    @Deprecated
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Subtitle extends SubtitleConfiguration {
        private Subtitle(SubtitleConfiguration.Builder builder) {
            super(builder);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SubtitleConfiguration {
        private static final String FIELD_ID = Util.intToStringMaxRadix(6);
        private static final String FIELD_LABEL = Util.intToStringMaxRadix(5);
        private static final String FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
        private static final String FIELD_MIME_TYPE = Util.intToStringMaxRadix(1);
        private static final String FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
        private static final String FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
        private static final String FIELD_URI = Util.intToStringMaxRadix(0);
        public final String id;
        public final String label;
        public final String language;
        public final String mimeType;
        public final int roleFlags;
        public final int selectionFlags;
        public final Uri uri;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            /* access modifiers changed from: private */
            public String id;
            /* access modifiers changed from: private */
            public String label;
            /* access modifiers changed from: private */
            public String language;
            /* access modifiers changed from: private */
            public String mimeType;
            /* access modifiers changed from: private */
            public int roleFlags;
            /* access modifiers changed from: private */
            public int selectionFlags;
            /* access modifiers changed from: private */
            public Uri uri;

            /* access modifiers changed from: private */
            public Subtitle buildSubtitle() {
                return new Subtitle(this);
            }

            private Builder(SubtitleConfiguration subtitleConfiguration) {
                this.uri = subtitleConfiguration.uri;
                this.mimeType = subtitleConfiguration.mimeType;
                this.language = subtitleConfiguration.language;
                this.selectionFlags = subtitleConfiguration.selectionFlags;
                this.roleFlags = subtitleConfiguration.roleFlags;
                this.label = subtitleConfiguration.label;
                this.id = subtitleConfiguration.id;
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SubtitleConfiguration)) {
                return false;
            }
            SubtitleConfiguration subtitleConfiguration = (SubtitleConfiguration) obj;
            if (!this.uri.equals(subtitleConfiguration.uri) || !Objects.equals(this.mimeType, subtitleConfiguration.mimeType) || !Objects.equals(this.language, subtitleConfiguration.language) || this.selectionFlags != subtitleConfiguration.selectionFlags || this.roleFlags != subtitleConfiguration.roleFlags || !Objects.equals(this.label, subtitleConfiguration.label) || !Objects.equals(this.id, subtitleConfiguration.id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i2;
            int i7;
            int i8;
            int hashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int i10 = 0;
            if (str == null) {
                i2 = 0;
            } else {
                i2 = str.hashCode();
            }
            int i11 = (hashCode + i2) * 31;
            String str2 = this.language;
            if (str2 == null) {
                i7 = 0;
            } else {
                i7 = str2.hashCode();
            }
            int i12 = (((((i11 + i7) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31;
            String str3 = this.label;
            if (str3 == null) {
                i8 = 0;
            } else {
                i8 = str3.hashCode();
            }
            int i13 = (i12 + i8) * 31;
            String str4 = this.id;
            if (str4 != null) {
                i10 = str4.hashCode();
            }
            return i13 + i10;
        }

        private SubtitleConfiguration(Builder builder) {
            this.uri = builder.uri;
            this.mimeType = builder.mimeType;
            this.language = builder.language;
            this.selectionFlags = builder.selectionFlags;
            this.roleFlags = builder.roleFlags;
            this.label = builder.label;
            this.id = builder.id;
        }
    }

    public static MediaItem fromUri(String str) {
        return new Builder().setUri(str).build();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (!Objects.equals(this.mediaId, mediaItem.mediaId) || !this.clippingConfiguration.equals(mediaItem.clippingConfiguration) || !Objects.equals(this.localConfiguration, mediaItem.localConfiguration) || !Objects.equals(this.liveConfiguration, mediaItem.liveConfiguration) || !Objects.equals(this.mediaMetadata, mediaItem.mediaMetadata) || !Objects.equals(this.requestMetadata, mediaItem.requestMetadata)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.mediaId.hashCode() * 31;
        LocalConfiguration localConfiguration2 = this.localConfiguration;
        if (localConfiguration2 != null) {
            i2 = localConfiguration2.hashCode();
        } else {
            i2 = 0;
        }
        int hashCode2 = this.liveConfiguration.hashCode();
        int hashCode3 = this.clippingConfiguration.hashCode();
        int hashCode4 = this.mediaMetadata.hashCode();
        return this.requestMetadata.hashCode() + ((hashCode4 + ((hashCode3 + ((hashCode2 + ((hashCode + i2) * 31)) * 31)) * 31)) * 31);
    }

    private MediaItem(String str, ClippingProperties clippingProperties2, LocalConfiguration localConfiguration2, LiveConfiguration liveConfiguration2, MediaMetadata mediaMetadata2, RequestMetadata requestMetadata2) {
        this.mediaId = str;
        this.localConfiguration = localConfiguration2;
        this.playbackProperties = localConfiguration2;
        this.liveConfiguration = liveConfiguration2;
        this.mediaMetadata = mediaMetadata2;
        this.clippingConfiguration = clippingProperties2;
        this.clippingProperties = clippingProperties2;
        this.requestMetadata = requestMetadata2;
    }

    public static MediaItem fromUri(Uri uri) {
        return new Builder().setUri(uri).build();
    }
}
