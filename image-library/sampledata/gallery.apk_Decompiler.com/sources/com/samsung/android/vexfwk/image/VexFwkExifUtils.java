package com.samsung.android.vexfwk.image;

import android.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/image/VexFwkExifUtils;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkExifUtils {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final List<String> allTags = C1195m.q0("FNumber", "ApertureValue", "Artist", "BitsPerSample", "BrightnessValue", "CFAPattern", "ColorSpace", "ComponentsConfiguration", "CompressedBitsPerPixel", "Compression", "Contrast", "Copyright", "CustomRendered", "DateTime", "DateTimeDigitized", "DateTimeOriginal", "DefaultCropSize", "DeviceSettingDescription", "DigitalZoomRatio", "DNGVersion", "ExifVersion", "ExposureBiasValue", "ExposureIndex", "ExposureMode", "ExposureProgram", "ExposureTime", "FileSource", "Flash", "FlashpixVersion", "FlashEnergy", "FocalLength", "FocalLengthIn35mmFilm", "FocalPlaneResolutionUnit", "FocalPlaneXResolution", "FocalPlaneYResolution", "FNumber", "GainControl", "GPSAltitude", "GPSAltitudeRef", "GPSAreaInformation", "GPSDateStamp", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDifferential", "GPSDOP", "GPSImgDirection", "GPSImgDirectionRef", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSMapDatum", "GPSMeasureMode", "GPSProcessingMethod", "GPSSatellites", "GPSSpeed", "GPSSpeedRef", "GPSStatus", "GPSTimeStamp", "GPSTrack", "GPSTrackRef", "GPSVersionID", "ImageDescription", "ImageLength", "ImageUniqueID", "ImageWidth", "InteroperabilityIndex", "ISOSpeedRatings", "ISOSpeedRatings", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "LightSource", "Make", "MakerNote", "MaxApertureValue", "MeteringMode", "Model", "NewSubfileType", "OECF", "AspectFrame", "PreviewImageLength", "PreviewImageStart", "ThumbnailImage", "Orientation", "PhotometricInterpretation", "PixelXDimension", "PixelYDimension", "PlanarConfiguration", "PrimaryChromaticities", "ReferenceBlackWhite", "RelatedSoundFile", "ResolutionUnit", "RowsPerStrip", "ISO", "JpgFromRaw", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "SamplesPerPixel", "Saturation", "SceneCaptureType", "SceneType", "SensingMethod", "Sharpness", "ShutterSpeedValue", "Software", "SpatialFrequencyResponse", "SpectralSensitivity", "StripByteCounts", "StripOffsets", "SubfileType", "SubjectArea", "SubjectDistance", "SubjectDistanceRange", "SubjectLocation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeDigitized", "SubSecTimeOriginal", "SubSecTimeOriginal", "ThumbnailImageLength", "ThumbnailImageWidth", "TransferFunction", "UserComment", "WhiteBalance", "WhitePoint", "XResolution", "YCbCrCoefficients", "YCbCrPositioning", "YCbCrSubSampling", "YResolution", "OffsetTimeOriginal", "OffsetTime", "OffsetTimeDigitized");
    /* access modifiers changed from: private */
    public static final List<String> timeTags = C1195m.q0("DateTime", "DateTimeOriginal", "DateTimeDigitized");

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ%\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/image/VexFwkExifUtils$Companion;", "", "<init>", "()V", "", "inputFilePath", "outputFilePath", "Lme/x;", "copyExifInfo", "(Ljava/lang/String;Ljava/lang/String;)V", "finalDate", "updateTimeExifInfo", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "allTags", "Ljava/util/List;", "getAllTags", "()Ljava/util/List;", "timeTags", "getTimeTags", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final void copyExifInfo(String str, String str2) {
            j.e(str, "inputFilePath");
            j.e(str2, "outputFilePath");
            ExifInterface exifInterface = new ExifInterface(str);
            ExifInterface exifInterface2 = new ExifInterface(str2);
            for (String next : getAllTags()) {
                String attribute = exifInterface.getAttribute(next);
                if (attribute != null) {
                    exifInterface2.setAttribute(next, attribute);
                }
            }
            exifInterface2.saveAttributes();
        }

        public final List<String> getAllTags() {
            return VexFwkExifUtils.allTags;
        }

        public final List<String> getTimeTags() {
            return VexFwkExifUtils.timeTags;
        }

        public final void updateTimeExifInfo(String str, String str2, String str3) {
            j.e(str, "inputFilePath");
            j.e(str2, "outputFilePath");
            j.e(str3, "finalDate");
            ExifInterface exifInterface = new ExifInterface(str);
            ExifInterface exifInterface2 = new ExifInterface(str2);
            for (String next : getTimeTags()) {
                if (exifInterface.getAttribute(next) != null) {
                    exifInterface2.setAttribute(next, str3);
                }
            }
            exifInterface2.saveAttributes();
        }

        private Companion() {
        }
    }
}
