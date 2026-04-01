package com.samsung.android.motionphoto.utils.v2;

import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs;", "", "<init>", "()V", "Companion", "Image", "Video", "Meta", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaDefs {
    public static final Companion Companion = new Companion((e) null);
    private static final String MEDIA_FTYP_SIGNATURE = "ftyp";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Companion;", "", "<init>", "()V", "MEDIA_FTYP_SIGNATURE", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Image;", "", "<init>", "()V", "JPEG", "HEIF", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Image {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Image$HEIF;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class HEIF {
            public static final Companion Companion = new Companion((e) null);
            public static final String HEIF_CDIF_BOX = "cdif";
            public static final String HEIF_CDSC_BOX = "cdsc";
            public static final String HEIF_EXIF_BOX = "Exif";
            public static final String HEIF_FREE_BOX = "free";
            public static final String HEIF_FTYP_BOX = "ftyp";
            /* access modifiers changed from: private */
            public static final int[] HEIF_FTYP_MARKER = {102, 116, 121, 112};
            /* access modifiers changed from: private */
            public static final int[] HEIF_HEIC_MARKER = {104, 101, 105, 99};
            public static final String HEIF_IINF_BOX = "iinf";
            public static final String HEIF_ILOC_BOX = "iloc";
            public static final String HEIF_INFE_BOX = "infe";
            public static final String HEIF_IPRP_BOX = "iprp";
            public static final String HEIF_IREF_BOX = "iref";
            public static final String HEIF_MDAT_BOX = "mdat";
            public static final String HEIF_META_BOX = "meta";
            public static final String HEIF_MIME_BOX = "mime";
            public static final String HEIF_MPVD_BOX = "mpvd";
            public static final String HEIF_PITM_BOX = "pitm";

            @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Image$HEIF$Companion;", "", "<init>", "()V", "HEIF_FTYP_MARKER", "", "getHEIF_FTYP_MARKER", "()[I", "HEIF_HEIC_MARKER", "getHEIF_HEIC_MARKER", "HEIF_IINF_BOX", "", "HEIF_IREF_BOX", "HEIF_PITM_BOX", "HEIF_ILOC_BOX", "HEIF_META_BOX", "HEIF_INFE_BOX", "HEIF_MIME_BOX", "HEIF_EXIF_BOX", "HEIF_MPVD_BOX", "HEIF_CDSC_BOX", "HEIF_IPRP_BOX", "HEIF_FTYP_BOX", "HEIF_MDAT_BOX", "HEIF_FREE_BOX", "HEIF_CDIF_BOX", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                public final int[] getHEIF_FTYP_MARKER() {
                    return HEIF.HEIF_FTYP_MARKER;
                }

                public final int[] getHEIF_HEIC_MARKER() {
                    return HEIF.HEIF_HEIC_MARKER;
                }

                private Companion() {
                }
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Image$JPEG;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class JPEG {
            public static final Companion Companion = new Companion((e) null);
            public static final short JPEG_APP1_MARKER = -31;
            public static final short JPEG_APP4_MARKER = -28;
            public static final short JPEG_EOI_MARKER = -39;
            public static final int JPEG_LENGTH_SIZE = 2;
            public static final int JPEG_MARKER_SIZE = 2;
            public static final int JPEG_MIN_SIZE_TO_META_TYPE_CHECK = 32;
            public static final short JPEG_SOI_MARKER = -40;
            public static final short JPEG_SOS_MARKER = -38;

            @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Image$JPEG$Companion;", "", "<init>", "()V", "JPEG_MARKER_SIZE", "", "JPEG_LENGTH_SIZE", "JPEG_SOI_MARKER", "", "JPEG_APP1_MARKER", "JPEG_APP4_MARKER", "JPEG_SOS_MARKER", "JPEG_EOI_MARKER", "JPEG_MIN_SIZE_TO_META_TYPE_CHECK", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                private Companion() {
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta;", "", "<init>", "()V", "SEF", "XMP", "EXIF", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Meta {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$EXIF;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class EXIF {
            /* access modifiers changed from: private */
            public static String[] AllExifTags = {"ApertureValue", "Artist", "BitsPerSample", "BrightnessValue", "CFAPattern", "ColorSpace", "ComponentsConfiguration", "CompressedBitsPerPixel", "Compression", "Contrast", "Copyright", "CustomRendered", "DateTime", "DateTimeDigitized", "DateTimeOriginal", "DefaultCropSize", "DeviceSettingDescription", "DigitalZoomRatio", "DNGVersion", "ExifVersion", "ExposureBiasValue", "ExposureIndex", "ExposureMode", "ExposureProgram", "ExposureTime", "FileSource", "Flash", "FlashpixVersion", "FlashEnergy", "FocalLength", "FocalLengthIn35mmFilm", "FocalPlaneResolutionUnit", "FocalPlaneXResolution", "FocalPlaneYResolution", "FNumber", "GainControl", "GPSAltitude", "GPSAltitudeRef", "GPSAreaInformation", "GPSDateStamp", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDifferential", "GPSDOP", "GPSImgDirection", "GPSImgDirectionRef", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSMapDatum", "GPSMeasureMode", "GPSProcessingMethod", "GPSSatellites", "GPSSpeed", "GPSSpeedRef", "GPSStatus", "GPSTimeStamp", "GPSTrack", "GPSTrackRef", "GPSVersionID", "ImageDescription", "ImageLength", "ImageUniqueID", "ImageWidth", "InteroperabilityIndex", "ISOSpeedRatings", "LightSource", "Make", "MakerNote", "MaxApertureValue", "MeteringMode", "Model", "NewSubfileType", "OECF", "AspectFrame", "PreviewImageLength", "PreviewImageStart", "ThumbnailImage", "Orientation", "PhotometricInterpretation", "PixelXDimension", "PixelYDimension", "PlanarConfiguration", "PrimaryChromaticities", "ReferenceBlackWhite", "RelatedSoundFile", "ResolutionUnit", "RowsPerStrip", "ISO", "JpgFromRaw", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "SamplesPerPixel", "Saturation", "SceneCaptureType", "SceneType", "SensingMethod", "Sharpness", "ShutterSpeedValue", "Software", "SpatialFrequencyResponse", "SpectralSensitivity", "StripByteCounts", "StripOffsets", "SubfileType", "SubjectArea", "SubjectDistance", "SubjectDistanceRange", "SubjectLocation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "TransferFunction", "UserComment", "WhiteBalance", "WhitePoint", "XResolution", "YCbCrCoefficients", "YCbCrPositioning", "YCbCrSubSampling", "YResolution", "OffsetTimeOriginal", "OffsetTime", "OffsetTimeDigitized"};
            public static final Companion Companion = new Companion((e) null);

            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$EXIF$Companion;", "", "<init>", "()V", "AllExifTags", "", "", "getAllExifTags", "()[Ljava/lang/String;", "setAllExifTags", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                public final String[] getAllExifTags() {
                    return EXIF.AllExifTags;
                }

                public final void setAllExifTags(String[] strArr) {
                    j.e(strArr, "<set-?>");
                    EXIF.AllExifTags = strArr;
                }

                private Companion() {
                }
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$SEF;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SEF {
            public static final Companion Companion = new Companion((e) null);
            public static final int SEF_DEFAULT_VERSION = 107;
            public static final String SEF_HEIF_BOX_TYPE = "sefd";
            public static final String SEF_KEY_NAME_CAMERA_CAPTURE_MODE_INFO = "Camera_Capture_Mode_Info";
            public static final String SEF_KEY_NAME_CAMERA_SCENE_INFO = "Camera_Scene_Info";
            public static final String SEF_KEY_NAME_COLOR_DISPLAY_P3 = "Color_Display_P3";
            public static final String SEF_KEY_NAME_IMAGE_UTC_DATA = "Image_UTC_Data";
            public static final String SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO = "Long_Exposure_Effect_Info";
            public static final String SEF_KEY_NAME_MOTION_PHOTO_AUTOPLAY = "MotionPhoto_AutoPlay";
            public static final String SEF_KEY_NAME_MOTION_PHOTO_DATA = "MotionPhoto_Data";
            public static final String SEF_KEY_NAME_MOTION_PHOTO_INFO = "MotionPhoto_Info";
            public static final String SEF_KEY_NAME_MOTION_PHOTO_VERSION = "MotionPhoto_Version";
            public static final int SEF_KEY_TYPE_CAMERA_CAPTURE_MODE_INFO = 3169;
            public static final int SEF_KEY_TYPE_CAMERA_SCENE_INFO = 3361;
            public static final int SEF_KEY_TYPE_COLOR_DISPLAY_P3 = 3265;
            public static final int SEF_KEY_TYPE_IMAGE_UTC_DATA = 2561;
            public static final int SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO = 3409;
            public static final int SEF_KEY_TYPE_MOTION_PHOTO_AUTOPLAY = 2611;
            public static final int SEF_KEY_TYPE_MOTION_PHOTO_DATA = 2608;
            public static final int SEF_KEY_TYPE_MOTION_PHOTO_INFO = 2610;
            public static final int SEF_KEY_TYPE_MOTION_PHOTO_VERSION = 2609;
            public static final int SEF_MAX_DATA_COUNT = 100;
            public static final int SEF_MIN_SIZE = 1048576;
            public static final int SEF_SDR_SIZE = 12;
            public static final String SEF_TAIL_SIGNATURE = "SEFT";
            public static final String SEF_TAIL_START_SIGNATURE = "SEFH";

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$SEF$Companion;", "", "<init>", "()V", "SEF_KEY_TYPE_MOTION_PHOTO_DATA", "", "SEF_KEY_NAME_MOTION_PHOTO_DATA", "", "SEF_KEY_TYPE_MOTION_PHOTO_VERSION", "SEF_KEY_NAME_MOTION_PHOTO_VERSION", "SEF_KEY_TYPE_MOTION_PHOTO_INFO", "SEF_KEY_NAME_MOTION_PHOTO_INFO", "SEF_KEY_TYPE_MOTION_PHOTO_AUTOPLAY", "SEF_KEY_NAME_MOTION_PHOTO_AUTOPLAY", "SEF_KEY_TYPE_IMAGE_UTC_DATA", "SEF_KEY_NAME_IMAGE_UTC_DATA", "SEF_KEY_TYPE_COLOR_DISPLAY_P3", "SEF_KEY_NAME_COLOR_DISPLAY_P3", "SEF_KEY_TYPE_CAMERA_CAPTURE_MODE_INFO", "SEF_KEY_NAME_CAMERA_CAPTURE_MODE_INFO", "SEF_KEY_TYPE_CAMERA_SCENE_INFO", "SEF_KEY_NAME_CAMERA_SCENE_INFO", "SEF_KEY_TYPE_LONG_EXPOSURE_EFFECT_INFO", "SEF_KEY_NAME_LONG_EXPOSURE_EFFECT_INFO", "SEF_MAX_DATA_COUNT", "SEF_DEFAULT_VERSION", "SEF_TAIL_START_SIGNATURE", "SEF_TAIL_SIGNATURE", "SEF_MIN_SIZE", "SEF_SDR_SIZE", "SEF_HEIF_BOX_TYPE", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                private Companion() {
                }
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$XMP;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class XMP {
            public static final Companion Companion = new Companion((e) null);
            public static final String XMP_GOOGLE_CAMERA_NS = "http://ns.google.com/photos/1.0/camera/";
            public static final String XMP_GOOGLE_CONTAINER_NS = "http://ns.google.com/photos/1.0/container/";
            public static final String XMP_GOOGLE_ITEM_NS = "http://ns.google.com/photos/1.0/container/item/";
            public static final String XMP_HDR_GAINMAP_FORMAT_VERSION = "Version";
            public static final String XMP_HDR_GAINMAP_NS = "http://ns.adobe.com/hdr-gain-map/1.0/";
            public static final String XMP_KEY_LENGTH = "Length";
            public static final String XMP_KEY_MOTION_PHOTO = "MotionPhoto";
            public static final String XMP_KEY_PADDING = "Padding";
            public static final String XMP_KEY_PRIMARY = "Primary";
            public static final int XMP_MIX_RESERVED_SIZE = 1280;
            public static final String XMP_MOTION_PHOTO_CAPTURE_TIMESTAMP = "MotionPhotoPresentationTimestampUs";
            public static final String XMP_MOTION_PHOTO_FORMAT_VERSION = "MotionPhotoVersion";
            public static final String XMP_MOTION_PHOTO_TIMESTAMP = "MotionPhotoPresentationTimestampUs";
            public static final String XMP_SIGNATURE = "http://ns.adobe.com/xap/1.0/\u0000";

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Meta$XMP$Companion;", "", "<init>", "()V", "XMP_MIX_RESERVED_SIZE", "", "XMP_SIGNATURE", "", "XMP_GOOGLE_CAMERA_NS", "XMP_GOOGLE_CONTAINER_NS", "XMP_GOOGLE_ITEM_NS", "XMP_HDR_GAINMAP_NS", "XMP_MOTION_PHOTO_FORMAT_VERSION", "XMP_MOTION_PHOTO_CAPTURE_TIMESTAMP", "XMP_HDR_GAINMAP_FORMAT_VERSION", "XMP_MOTION_PHOTO_TIMESTAMP", "XMP_KEY_PRIMARY", "XMP_KEY_MOTION_PHOTO", "XMP_KEY_PADDING", "XMP_KEY_LENGTH", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                private Companion() {
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Video;", "", "<init>", "()V", "MP4", "MOV", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Video {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Video$MOV;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class MOV {
            public static final Companion Companion = new Companion((e) null);
            public static final String MOV_FTYP_SIGNATURE = "ftypqt";

            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Video$MOV$Companion;", "", "<init>", "()V", "MOV_FTYP_SIGNATURE", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                private Companion() {
                }
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Video$MP4;", "", "<init>", "()V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class MP4 {
            public static final Companion Companion = new Companion((e) null);
            public static final String MP4_FTYP_SIGNATURE = "ftypmp42";
            public static final String MP4_MOOV_BOX = "moov";

            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaDefs$Video$MP4$Companion;", "", "<init>", "()V", "MP4_MOOV_BOX", "", "MP4_FTYP_SIGNATURE", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                private Companion() {
                }
            }
        }
    }
}
