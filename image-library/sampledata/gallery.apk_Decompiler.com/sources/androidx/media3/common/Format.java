package androidx.media3.common;

import Ad.j;
import E2.i;
import F2.C0040v;
import F2.G;
import F2.U;
import F2.y0;
import I2.b;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Format {
    private static final Format DEFAULT = new Builder().build();
    private static final String FIELD_ACCESSIBILITY_CHANNEL = Util.intToStringMaxRadix(28);
    private static final String FIELD_AUXILIARY_TRACK_TYPE = Util.intToStringMaxRadix(33);
    private static final String FIELD_AVERAGE_BITRATE = Util.intToStringMaxRadix(5);
    private static final String FIELD_CHANNEL_COUNT = Util.intToStringMaxRadix(23);
    private static final String FIELD_CODECS = Util.intToStringMaxRadix(7);
    private static final String FIELD_COLOR_INFO = Util.intToStringMaxRadix(22);
    private static final String FIELD_CONTAINER_MIME_TYPE = Util.intToStringMaxRadix(9);
    private static final String FIELD_CRYPTO_TYPE = Util.intToStringMaxRadix(29);
    private static final String FIELD_DECODED_HEIGHT = Util.intToStringMaxRadix(36);
    private static final String FIELD_DECODED_WIDTH = Util.intToStringMaxRadix(35);
    private static final String FIELD_DRM_INIT_DATA = Util.intToStringMaxRadix(13);
    private static final String FIELD_ENCODER_DELAY = Util.intToStringMaxRadix(26);
    private static final String FIELD_ENCODER_PADDING = Util.intToStringMaxRadix(27);
    private static final String FIELD_FRAME_RATE = Util.intToStringMaxRadix(17);
    private static final String FIELD_HEIGHT = Util.intToStringMaxRadix(16);
    private static final String FIELD_ID = Util.intToStringMaxRadix(0);
    private static final String FIELD_INITIALIZATION_DATA = Util.intToStringMaxRadix(12);
    private static final String FIELD_LABEL = Util.intToStringMaxRadix(1);
    private static final String FIELD_LABELS = Util.intToStringMaxRadix(32);
    private static final String FIELD_LANGUAGE = Util.intToStringMaxRadix(2);
    private static final String FIELD_MAX_INPUT_SIZE = Util.intToStringMaxRadix(11);
    private static final String FIELD_MAX_SUB_LAYERS = Util.intToStringMaxRadix(34);
    private static final String FIELD_PCM_ENCODING = Util.intToStringMaxRadix(25);
    private static final String FIELD_PEAK_BITRATE = Util.intToStringMaxRadix(6);
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(19);
    private static final String FIELD_PROJECTION_DATA = Util.intToStringMaxRadix(20);
    private static final String FIELD_ROLE_FLAGS = Util.intToStringMaxRadix(4);
    private static final String FIELD_ROTATION_DEGREES = Util.intToStringMaxRadix(18);
    private static final String FIELD_SAMPLE_MIME_TYPE = Util.intToStringMaxRadix(10);
    private static final String FIELD_SAMPLE_RATE = Util.intToStringMaxRadix(24);
    private static final String FIELD_SELECTION_FLAGS = Util.intToStringMaxRadix(3);
    private static final String FIELD_STEREO_MODE = Util.intToStringMaxRadix(21);
    private static final String FIELD_SUBSAMPLE_OFFSET_US = Util.intToStringMaxRadix(14);
    private static final String FIELD_TILE_COUNT_HORIZONTAL = Util.intToStringMaxRadix(30);
    private static final String FIELD_TILE_COUNT_VERTICAL = Util.intToStringMaxRadix(31);
    private static final String FIELD_WIDTH = Util.intToStringMaxRadix(15);
    private static final String UNUSED_FIELD_METADATA = Util.intToStringMaxRadix(8);
    public final int accessibilityChannel;
    public final int auxiliaryTrackType;
    public final int averageBitrate;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final int cryptoType;
    public final int cueReplacementBehavior;
    public final Object customData;
    public final int decodedHeight;
    public final int decodedWidth;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    public final boolean hasPrerollSamples;
    private int hashCode;
    public final int height;
    public final String id;
    public final List<byte[]> initializationData;
    public final String label;
    public final List<Label> labels;
    public final String language;
    public final int maxInputSize;
    public final int maxNumReorderSamples;
    public final int maxSubLayers;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final int peakBitrate;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int roleFlags;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int tileCountHorizontal;
    public final int tileCountVertical;
    public final int width;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int accessibilityChannel;
        /* access modifiers changed from: private */
        public int auxiliaryTrackType;
        /* access modifiers changed from: private */
        public int averageBitrate;
        /* access modifiers changed from: private */
        public int channelCount;
        /* access modifiers changed from: private */
        public String codecs;
        /* access modifiers changed from: private */
        public ColorInfo colorInfo;
        /* access modifiers changed from: private */
        public String containerMimeType;
        /* access modifiers changed from: private */
        public int cryptoType;
        /* access modifiers changed from: private */
        public int cueReplacementBehavior;
        /* access modifiers changed from: private */
        public Object customData;
        /* access modifiers changed from: private */
        public int decodedHeight;
        /* access modifiers changed from: private */
        public int decodedWidth;
        /* access modifiers changed from: private */
        public DrmInitData drmInitData;
        /* access modifiers changed from: private */
        public int encoderDelay;
        /* access modifiers changed from: private */
        public int encoderPadding;
        /* access modifiers changed from: private */
        public float frameRate;
        /* access modifiers changed from: private */
        public boolean hasPrerollSamples;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public List<byte[]> initializationData;
        /* access modifiers changed from: private */
        public String label;
        /* access modifiers changed from: private */
        public List<Label> labels;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public int maxInputSize;
        /* access modifiers changed from: private */
        public int maxNumReorderSamples;
        /* access modifiers changed from: private */
        public int maxSubLayers;
        /* access modifiers changed from: private */
        public Metadata metadata;
        /* access modifiers changed from: private */
        public int pcmEncoding;
        /* access modifiers changed from: private */
        public int peakBitrate;
        /* access modifiers changed from: private */
        public float pixelWidthHeightRatio;
        /* access modifiers changed from: private */
        public byte[] projectionData;
        /* access modifiers changed from: private */
        public int roleFlags;
        /* access modifiers changed from: private */
        public int rotationDegrees;
        /* access modifiers changed from: private */
        public String sampleMimeType;
        /* access modifiers changed from: private */
        public int sampleRate;
        /* access modifiers changed from: private */
        public int selectionFlags;
        /* access modifiers changed from: private */
        public int stereoMode;
        /* access modifiers changed from: private */
        public long subsampleOffsetUs;
        /* access modifiers changed from: private */
        public int tileCountHorizontal;
        /* access modifiers changed from: private */
        public int tileCountVertical;
        /* access modifiers changed from: private */
        public int width;

        public Format build() {
            return new Format(this);
        }

        public Builder setAccessibilityChannel(int i2) {
            this.accessibilityChannel = i2;
            return this;
        }

        public Builder setAuxiliaryTrackType(int i2) {
            this.auxiliaryTrackType = i2;
            return this;
        }

        public Builder setAverageBitrate(int i2) {
            this.averageBitrate = i2;
            return this;
        }

        public Builder setChannelCount(int i2) {
            this.channelCount = i2;
            return this;
        }

        public Builder setCodecs(String str) {
            this.codecs = str;
            return this;
        }

        public Builder setColorInfo(ColorInfo colorInfo2) {
            this.colorInfo = colorInfo2;
            return this;
        }

        public Builder setContainerMimeType(String str) {
            this.containerMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        public Builder setCryptoType(int i2) {
            this.cryptoType = i2;
            return this;
        }

        public Builder setCueReplacementBehavior(int i2) {
            this.cueReplacementBehavior = i2;
            return this;
        }

        public Builder setDecodedHeight(int i2) {
            this.decodedHeight = i2;
            return this;
        }

        public Builder setDecodedWidth(int i2) {
            this.decodedWidth = i2;
            return this;
        }

        public Builder setDrmInitData(DrmInitData drmInitData2) {
            this.drmInitData = drmInitData2;
            return this;
        }

        public Builder setEncoderDelay(int i2) {
            this.encoderDelay = i2;
            return this;
        }

        public Builder setEncoderPadding(int i2) {
            this.encoderPadding = i2;
            return this;
        }

        public Builder setFrameRate(float f) {
            this.frameRate = f;
            return this;
        }

        public Builder setHasPrerollSamples(boolean z) {
            this.hasPrerollSamples = z;
            return this;
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setInitializationData(List<byte[]> list) {
            this.initializationData = list;
            return this;
        }

        public Builder setLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder setLanguage(String str) {
            this.language = str;
            return this;
        }

        public Builder setMaxInputSize(int i2) {
            this.maxInputSize = i2;
            return this;
        }

        public Builder setMaxNumReorderSamples(int i2) {
            this.maxNumReorderSamples = i2;
            return this;
        }

        public Builder setMaxSubLayers(int i2) {
            this.maxSubLayers = i2;
            return this;
        }

        public Builder setMetadata(Metadata metadata2) {
            this.metadata = metadata2;
            return this;
        }

        public Builder setPcmEncoding(int i2) {
            this.pcmEncoding = i2;
            return this;
        }

        public Builder setPeakBitrate(int i2) {
            this.peakBitrate = i2;
            return this;
        }

        public Builder setPixelWidthHeightRatio(float f) {
            this.pixelWidthHeightRatio = f;
            return this;
        }

        public Builder setProjectionData(byte[] bArr) {
            this.projectionData = bArr;
            return this;
        }

        public Builder setRoleFlags(int i2) {
            this.roleFlags = i2;
            return this;
        }

        public Builder setRotationDegrees(int i2) {
            this.rotationDegrees = i2;
            return this;
        }

        public Builder setSampleMimeType(String str) {
            this.sampleMimeType = MimeTypes.normalizeMimeType(str);
            return this;
        }

        public Builder setSampleRate(int i2) {
            this.sampleRate = i2;
            return this;
        }

        public Builder setSelectionFlags(int i2) {
            this.selectionFlags = i2;
            return this;
        }

        public Builder setStereoMode(int i2) {
            this.stereoMode = i2;
            return this;
        }

        public Builder setSubsampleOffsetUs(long j2) {
            this.subsampleOffsetUs = j2;
            return this;
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }

        public Builder() {
            G g = U.e;
            this.labels = y0.f278h;
            this.averageBitrate = -1;
            this.peakBitrate = -1;
            this.maxInputSize = -1;
            this.maxNumReorderSamples = -1;
            this.subsampleOffsetUs = Long.MAX_VALUE;
            this.width = -1;
            this.height = -1;
            this.decodedWidth = -1;
            this.decodedHeight = -1;
            this.frameRate = -1.0f;
            this.pixelWidthHeightRatio = 1.0f;
            this.stereoMode = -1;
            this.maxSubLayers = -1;
            this.channelCount = -1;
            this.sampleRate = -1;
            this.pcmEncoding = -1;
            this.accessibilityChannel = -1;
            this.cueReplacementBehavior = 1;
            this.tileCountHorizontal = -1;
            this.tileCountVertical = -1;
            this.cryptoType = 0;
            this.auxiliaryTrackType = 0;
        }

        public Builder setId(int i2) {
            this.id = Integer.toString(i2);
            return this;
        }

        private Builder(Format format) {
            this.id = format.id;
            this.label = format.label;
            this.labels = format.labels;
            this.language = format.language;
            this.selectionFlags = format.selectionFlags;
            this.roleFlags = format.roleFlags;
            this.averageBitrate = format.averageBitrate;
            this.peakBitrate = format.peakBitrate;
            this.codecs = format.codecs;
            this.metadata = format.metadata;
            this.customData = format.customData;
            this.containerMimeType = format.containerMimeType;
            this.sampleMimeType = format.sampleMimeType;
            this.maxInputSize = format.maxInputSize;
            this.maxNumReorderSamples = format.maxNumReorderSamples;
            this.initializationData = format.initializationData;
            this.drmInitData = format.drmInitData;
            this.subsampleOffsetUs = format.subsampleOffsetUs;
            this.hasPrerollSamples = format.hasPrerollSamples;
            this.width = format.width;
            this.height = format.height;
            this.decodedWidth = format.decodedWidth;
            this.decodedHeight = format.decodedHeight;
            this.frameRate = format.frameRate;
            this.rotationDegrees = format.rotationDegrees;
            this.pixelWidthHeightRatio = format.pixelWidthHeightRatio;
            this.projectionData = format.projectionData;
            this.stereoMode = format.stereoMode;
            this.colorInfo = format.colorInfo;
            this.maxSubLayers = format.maxSubLayers;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.pcmEncoding = format.pcmEncoding;
            this.encoderDelay = format.encoderDelay;
            this.encoderPadding = format.encoderPadding;
            this.accessibilityChannel = format.accessibilityChannel;
            this.cueReplacementBehavior = format.cueReplacementBehavior;
            this.tileCountHorizontal = format.tileCountHorizontal;
            this.tileCountVertical = format.tileCountVertical;
            this.cryptoType = format.cryptoType;
        }
    }

    private static String getDefaultLabel(List<Label> list, String str) {
        for (Label next : list) {
            if (TextUtils.equals(next.language, str)) {
                return next.value;
            }
        }
        return list.get(0).value;
    }

    private static boolean isLabelPartOfLabels(Builder builder) {
        if (builder.labels.isEmpty() && builder.label == null) {
            return true;
        }
        for (int i2 = 0; i2 < builder.labels.size(); i2++) {
            if (((Label) builder.labels.get(i2)).value.equals(builder.label)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toLogString$0(Label label2) {
        return label2.language + ": " + label2.value;
    }

    public static String toLogString(Format format) {
        if (format == null) {
            return "null";
        }
        i iVar = new i(String.valueOf(','));
        StringBuilder s = C0212a.s("id=");
        s.append(format.id);
        s.append(", mimeType=");
        s.append(format.sampleMimeType);
        if (format.containerMimeType != null) {
            s.append(", container=");
            s.append(format.containerMimeType);
        }
        if (format.bitrate != -1) {
            s.append(", bitrate=");
            s.append(format.bitrate);
        }
        if (format.codecs != null) {
            s.append(", codecs=");
            s.append(format.codecs);
        }
        if (format.drmInitData != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i2 = 0;
            while (true) {
                DrmInitData drmInitData2 = format.drmInitData;
                if (i2 >= drmInitData2.schemeDataCount) {
                    break;
                }
                UUID uuid = drmInitData2.get(i2).uuid;
                if (uuid.equals(C.COMMON_PSSH_UUID)) {
                    linkedHashSet.add("cenc");
                } else if (uuid.equals(C.CLEARKEY_UUID)) {
                    linkedHashSet.add("clearkey");
                } else if (uuid.equals(C.PLAYREADY_UUID)) {
                    linkedHashSet.add("playready");
                } else if (uuid.equals(C.WIDEVINE_UUID)) {
                    linkedHashSet.add("widevine");
                } else if (uuid.equals(C.UUID_NIL)) {
                    linkedHashSet.add("universal");
                } else {
                    linkedHashSet.add("unknown (" + uuid + ")");
                }
                i2++;
            }
            s.append(", drm=[");
            iVar.a(s, linkedHashSet.iterator());
            s.append(']');
        }
        if (!(format.width == -1 || format.height == -1)) {
            s.append(", res=");
            s.append(format.width);
            s.append("x");
            s.append(format.height);
        }
        if (!(format.decodedWidth == -1 || format.decodedHeight == -1)) {
            s.append(", decRes=");
            s.append(format.decodedWidth);
            s.append("x");
            s.append(format.decodedHeight);
        }
        double d = (double) format.pixelWidthHeightRatio;
        int i7 = b.f347a;
        if (Math.copySign(d - 1.0d, 1.0d) > 0.001d && d != 1.0d && (!Double.isNaN(d) || !Double.isNaN(1.0d))) {
            s.append(", par=");
            s.append(Util.formatInvariant("%.3f", Float.valueOf(format.pixelWidthHeightRatio)));
        }
        ColorInfo colorInfo2 = format.colorInfo;
        if (colorInfo2 != null && colorInfo2.isValid()) {
            s.append(", color=");
            s.append(format.colorInfo.toLogString());
        }
        if (format.frameRate != -1.0f) {
            s.append(", fps=");
            s.append(format.frameRate);
        }
        if (format.maxSubLayers != -1) {
            s.append(", maxSubLayers=");
            s.append(format.maxSubLayers);
        }
        if (format.channelCount != -1) {
            s.append(", channels=");
            s.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            s.append(", sample_rate=");
            s.append(format.sampleRate);
        }
        if (format.language != null) {
            s.append(", language=");
            s.append(format.language);
        }
        if (!format.labels.isEmpty()) {
            s.append(", labels=[");
            iVar.a(s, C0040v.t(format.labels, new j(4)).iterator());
            s.append("]");
        }
        if (format.selectionFlags != 0) {
            s.append(", selectionFlags=[");
            iVar.a(s, Util.getSelectionFlagStrings(format.selectionFlags).iterator());
            s.append("]");
        }
        if (format.roleFlags != 0) {
            s.append(", roleFlags=[");
            iVar.a(s, Util.getRoleFlagStrings(format.roleFlags).iterator());
            s.append("]");
        }
        if (format.customData != null) {
            s.append(", customData=");
            s.append(format.customData);
        }
        if ((format.roleFlags & 32768) != 0) {
            s.append(", auxiliaryTrackType=");
            s.append(Util.getAuxiliaryTrackTypeString(format.auxiliaryTrackType));
        }
        return s.toString();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public Format copyWithCryptoType(int i2) {
        return buildUpon().setCryptoType(i2).build();
    }

    public boolean equals(Object obj) {
        int i2;
        if (this == obj) {
            return true;
        }
        if (obj != null && Format.class == obj.getClass()) {
            Format format = (Format) obj;
            int i7 = this.hashCode;
            if ((i7 == 0 || (i2 = format.hashCode) == 0 || i7 == i2) && this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.auxiliaryTrackType == format.auxiliaryTrackType && this.averageBitrate == format.averageBitrate && this.peakBitrate == format.peakBitrate && this.maxInputSize == format.maxInputSize && this.subsampleOffsetUs == format.subsampleOffsetUs && this.width == format.width && this.height == format.height && this.decodedWidth == format.decodedWidth && this.decodedHeight == format.decodedHeight && this.rotationDegrees == format.rotationDegrees && this.stereoMode == format.stereoMode && this.maxSubLayers == format.maxSubLayers && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.accessibilityChannel == format.accessibilityChannel && this.tileCountHorizontal == format.tileCountHorizontal && this.tileCountVertical == format.tileCountVertical && this.cryptoType == format.cryptoType && Float.compare(this.frameRate, format.frameRate) == 0 && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && Objects.equals(this.id, format.id) && Objects.equals(this.label, format.label) && this.labels.equals(format.labels) && Objects.equals(this.codecs, format.codecs) && Objects.equals(this.containerMimeType, format.containerMimeType) && Objects.equals(this.sampleMimeType, format.sampleMimeType) && Objects.equals(this.language, format.language) && Arrays.equals(this.projectionData, format.projectionData) && Objects.equals(this.metadata, format.metadata) && Objects.equals(this.colorInfo, format.colorInfo) && Objects.equals(this.drmInitData, format.drmInitData) && initializationDataEquals(format) && Objects.equals(this.customData, format.customData)) {
                return true;
            }
        }
        return false;
    }

    public int getPixelCount() {
        int i2;
        int i7 = this.width;
        if (i7 == -1 || (i2 = this.height) == -1) {
            return -1;
        }
        return i7 * i2;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        if (this.hashCode == 0) {
            String str = this.id;
            int i14 = 0;
            if (str == null) {
                i2 = 0;
            } else {
                i2 = str.hashCode();
            }
            int i15 = (527 + i2) * 31;
            String str2 = this.label;
            if (str2 == null) {
                i7 = 0;
            } else {
                i7 = str2.hashCode();
            }
            int hashCode2 = (this.labels.hashCode() + ((i15 + i7) * 31)) * 31;
            String str3 = this.language;
            if (str3 == null) {
                i8 = 0;
            } else {
                i8 = str3.hashCode();
            }
            int i16 = (((((((((((hashCode2 + i8) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.auxiliaryTrackType) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31;
            String str4 = this.codecs;
            if (str4 == null) {
                i10 = 0;
            } else {
                i10 = str4.hashCode();
            }
            int i17 = (i16 + i10) * 31;
            Metadata metadata2 = this.metadata;
            if (metadata2 == null) {
                i11 = 0;
            } else {
                i11 = metadata2.hashCode();
            }
            int i18 = (i17 + i11) * 31;
            Object obj = this.customData;
            if (obj == null) {
                i12 = 0;
            } else {
                i12 = obj.hashCode();
            }
            int i19 = (i18 + i12) * 31;
            String str5 = this.containerMimeType;
            if (str5 == null) {
                i13 = 0;
            } else {
                i13 = str5.hashCode();
            }
            int i20 = (i19 + i13) * 31;
            String str6 = this.sampleMimeType;
            if (str6 != null) {
                i14 = str6.hashCode();
            }
            this.hashCode = ((((((((((((((((((((((Float.floatToIntBits(this.pixelWidthHeightRatio) + ((((Float.floatToIntBits(this.frameRate) + ((((((((((((((i20 + i14) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31) + this.decodedWidth) * 31) + this.decodedHeight) * 31)) * 31) + this.rotationDegrees) * 31)) * 31) + this.stereoMode) * 31) + this.maxSubLayers) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31) + this.accessibilityChannel) * 31) + this.tileCountHorizontal) * 31) + this.tileCountVertical) * 31) + this.cryptoType;
        }
        return this.hashCode;
    }

    public boolean initializationDataEquals(Format format) {
        if (this.initializationData.size() != format.initializationData.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.initializationData.size(); i2++) {
            if (!Arrays.equals(this.initializationData.get(i2), format.initializationData.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Format(");
        sb2.append(this.id);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.label);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.containerMimeType);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.sampleMimeType);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.codecs);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.bitrate);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.language);
        sb2.append(", [");
        sb2.append(this.width);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.height);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.frameRate);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(this.colorInfo);
        sb2.append("], [");
        sb2.append(this.channelCount);
        sb2.append(ArcCommonLog.TAG_COMMA);
        return C0086a.l(sb2, this.sampleRate, "])");
    }

    private Format(Builder builder) {
        this.id = builder.id;
        String normalizeLanguageCode = Util.normalizeLanguageCode(builder.language);
        this.language = normalizeLanguageCode;
        if (builder.labels.isEmpty() && builder.label != null) {
            this.labels = U.B(new Label(normalizeLanguageCode, builder.label));
            this.label = builder.label;
        } else if (builder.labels.isEmpty() || builder.label != null) {
            Assertions.checkState(isLabelPartOfLabels(builder));
            this.labels = builder.labels;
            this.label = builder.label;
        } else {
            this.labels = builder.labels;
            this.label = getDefaultLabel(builder.labels, normalizeLanguageCode);
        }
        this.selectionFlags = builder.selectionFlags;
        int i2 = 0;
        Assertions.checkState(builder.auxiliaryTrackType == 0 || (builder.roleFlags & 32768) != 0, "Auxiliary track type must only be set to a value other than AUXILIARY_TRACK_TYPE_UNDEFINED only when ROLE_FLAG_AUXILIARY is set");
        this.roleFlags = builder.roleFlags;
        this.auxiliaryTrackType = builder.auxiliaryTrackType;
        int access$800 = builder.averageBitrate;
        this.averageBitrate = access$800;
        int access$900 = builder.peakBitrate;
        this.peakBitrate = access$900;
        this.bitrate = access$900 != -1 ? access$900 : access$800;
        this.codecs = builder.codecs;
        this.metadata = builder.metadata;
        this.customData = builder.customData;
        this.containerMimeType = builder.containerMimeType;
        this.sampleMimeType = builder.sampleMimeType;
        this.maxInputSize = builder.maxInputSize;
        this.maxNumReorderSamples = builder.maxNumReorderSamples;
        this.initializationData = builder.initializationData == null ? Collections.EMPTY_LIST : builder.initializationData;
        DrmInitData access$1800 = builder.drmInitData;
        this.drmInitData = access$1800;
        this.subsampleOffsetUs = builder.subsampleOffsetUs;
        this.hasPrerollSamples = builder.hasPrerollSamples;
        this.width = builder.width;
        this.height = builder.height;
        this.decodedWidth = builder.decodedWidth;
        this.decodedHeight = builder.decodedHeight;
        this.frameRate = builder.frameRate;
        this.rotationDegrees = builder.rotationDegrees == -1 ? 0 : builder.rotationDegrees;
        this.pixelWidthHeightRatio = builder.pixelWidthHeightRatio == -1.0f ? 1.0f : builder.pixelWidthHeightRatio;
        this.projectionData = builder.projectionData;
        this.stereoMode = builder.stereoMode;
        this.colorInfo = builder.colorInfo;
        this.maxSubLayers = builder.maxSubLayers;
        this.channelCount = builder.channelCount;
        this.sampleRate = builder.sampleRate;
        this.pcmEncoding = builder.pcmEncoding;
        this.encoderDelay = builder.encoderDelay == -1 ? 0 : builder.encoderDelay;
        this.encoderPadding = builder.encoderPadding != -1 ? builder.encoderPadding : i2;
        this.accessibilityChannel = builder.accessibilityChannel;
        this.cueReplacementBehavior = builder.cueReplacementBehavior;
        this.tileCountHorizontal = builder.tileCountHorizontal;
        this.tileCountVertical = builder.tileCountVertical;
        if (builder.cryptoType != 0 || access$1800 == null) {
            this.cryptoType = builder.cryptoType;
        } else {
            this.cryptoType = 1;
        }
    }
}
