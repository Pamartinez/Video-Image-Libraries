package com.samsung.android.gallery.module.details;

import A.a;
import Ad.C0720a;
import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.SparseArray;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.abstraction.ScreenCaptureInfo;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.MetadataTimeHelper;
import com.samsung.android.gallery.support.library.sef.CameraCaptureMode;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SingleTakeType;
import com.samsung.android.gallery.support.providers.CameraUsbUri;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.ExifMakerNoteCompat;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsDataRefiner {
    private static final Lazy<NumberFormat> sNumberFormat = new Lazy<>(new C0720a(11));

    private static String changePppPath(String str) {
        String str2 = FileUtils.PPP_TEMP_DIR;
        return str.replace(str2, "/" + AppResources.getString(R$string.dcim_name) + "/");
    }

    private static String convertFocalLength(Double d) {
        if (d != null) {
            return sNumberFormat.get().format((double) MathUtils.roundToDecimalPlace(d.doubleValue(), 3.0d));
        }
        return "";
    }

    public static ExifTag createExifFromMetadata(MediaItem mediaItem) {
        SparseArray<String> sparseArray = null;
        ExifTag exifTag = new ExifTag((ExifInterface) null);
        if (mediaItem != null) {
            sparseArray = VideoPropData.of(mediaItem).videoMetadata;
        }
        if (sparseArray == null) {
            return exifTag;
        }
        exifTag.orientation = sparseArray.get(24);
        exifTag.originalTime = sparseArray.get(5);
        exifTag.originalTimeOffset = sparseArray.get(1032);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sparseArray.get(35));
        sb2.append(NumericEnum.SEP);
        sb2.append(sparseArray.get(36));
        sb2.append(NumericEnum.SEP);
        sb2.append(sparseArray.get(37));
        sb2.append(" (");
        sb2.append(sparseArray.get(17));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(sparseArray.get(16));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(sparseArray.get(26));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        exifTag.colorSpace = C0212a.p(sb2, sparseArray.get(1027), ")");
        return exifTag;
    }

    public static String getAperture(MediaItem mediaItem) {
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag != null) {
            return exifTag.aperture;
        }
        return null;
    }

    public static String getAudioCodec(MediaItem mediaItem) {
        return VideoPropData.of(mediaItem).audioCodec;
    }

    private static CameraCaptureMode getCameraCaptureModeInfo(MediaItem mediaItem) {
        byte[] sefParserData = DetailsData.of(mediaItem).getSefParserData(SefInfo.CAMERA_CAPTURE_MODE_INFO.keyName);
        if (sefParserData == null || sefParserData.length <= 0) {
            return null;
        }
        return CameraCaptureMode.of(UnsafeCast.toInt(new String(sefParserData)));
    }

    public static String getCameraInfoTitle(MediaItem mediaItem) {
        String str;
        int i2;
        String cameraModelName;
        if (mediaItem == null) {
            return "";
        }
        if (MediaItemUtil.isSuggestedEditVideo(mediaItem)) {
            return AppResources.getString(R$string.suggested_edits);
        }
        ScreenCaptureInfo screenCaptureInfo = getScreenCaptureInfo(mediaItem);
        if (screenCaptureInfo != null) {
            return screenCaptureInfo.getString();
        }
        SparseArray<String> sparseArray = VideoPropData.of(mediaItem).videoMetadata;
        if (sparseArray != null) {
            str = sparseArray.get(11);
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag != null && (cameraModelName = getCameraModelName(exifTag)) != null) {
            return StringCompat.capitalize(cameraModelName);
        }
        if (mediaItem.isVideo()) {
            i2 = R$string.video_info;
        } else {
            i2 = R$string.image_info;
        }
        return AppResources.getString(i2);
    }

    private static String getCameraModelName(ExifTag exifTag) {
        String str = exifTag.model;
        String str2 = exifTag.make;
        if (!TextUtils.isEmpty(str)) {
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.MoreInfoShowCameraMakerName) || TextUtils.isEmpty(str2) || str.toLowerCase().contains(str2.toLowerCase())) {
                return str;
            }
            return C0212a.B(str2, " ", str);
        } else if (!TextUtils.isEmpty(str2)) {
            return str2;
        } else {
            return null;
        }
    }

    private static Map<String, String> getCameraVirtualApertureMap(MediaItem mediaItem) {
        byte[] sefParserData;
        if (getCameraCaptureModeInfo(mediaItem) != CameraCaptureMode.VirtualAperture || (sefParserData = DetailsData.of(mediaItem).getSefParserData(SefInfo.VIRTUAL_APERTURE_EXPOSURE_INFO.keyName)) == null || sefParserData.length <= 0) {
            return null;
        }
        return makeVirtualApertureMap(sefParserData);
    }

    public static ArrayList<String> getCaptureModeList(MediaItem mediaItem) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMainCaptureMode(mediaItem));
        arrayList.addAll(getSubCaptureModeList(mediaItem));
        return arrayList;
    }

    public static SpannableString getCapturedUrlText(MediaItem mediaItem) {
        String str = DetailsData.of(mediaItem).capturedUrl;
        try {
            str = new URL(str).getHost();
        } catch (NullPointerException | MalformedURLException unused) {
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        return spannableString;
    }

    public static String getDateString(MediaItem mediaItem, String str, String str2) {
        String originalDateString = getOriginalDateString(mediaItem, str, str2);
        if (!TextUtils.isEmpty(originalDateString)) {
            return originalDateString;
        }
        return getDateString(mediaItem != null ? mediaItem.getDateTaken() : 0, str, str2);
    }

    public static String getDebugDateTime(String str, String str2, String str3) {
        String str4;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder s = C0212a.s(str);
        String str5 = "";
        if (TextUtils.isEmpty(str2)) {
            str4 = str5;
        } else {
            str4 = C0212a.l(NumericEnum.SEP, str2);
        }
        s.append(str4);
        if (!TextUtils.isEmpty(str3)) {
            str5 = C0212a.l(" ", str3);
        }
        s.append(str5);
        return s.toString();
    }

    public static String getDisplayName(MediaItem mediaItem) {
        String str;
        if (mediaItem == null) {
            return "";
        }
        if (mediaItem.isSharing() || mediaItem.isMtp()) {
            str = mediaItem.getTitle();
        } else if (mediaItem.isUriItem()) {
            str = getDisplayNameFromUri(mediaItem);
        } else {
            str = FileUtils.getNameFromPath(mediaItem.getReferencePath());
        }
        if (Features.isEnabled(Features.IS_RTL)) {
            return StringCompat.toRtlDisplayName(str);
        }
        return str;
    }

    private static String getDisplayNameFromUri(MediaItem mediaItem) {
        String str;
        if (mediaItem == null) {
            return "";
        }
        if (mediaItem.isUriItem() && MediaItemUtil.isUsbFile(mediaItem) && mediaItem.getPath() != null) {
            String displayName = CameraUsbUri.getDisplayName(mediaItem.getPath(), mediaItem.getTitle());
            return "/" + replaceFirstWord(displayName, AppResources.getString(R$string.usb_storage));
        } else if (!TextUtils.isEmpty(mediaItem.getDisplayName())) {
            return mediaItem.getDisplayName();
        } else {
            if (mediaItem.getHttpUri() != null) {
                str = mediaItem.getHttpUri();
            } else {
                str = mediaItem.getPath();
            }
            if (str != null) {
                try {
                    Uri parse = Uri.parse(str);
                    String scheme = parse.getScheme();
                    if (!"content".equals(scheme) && !"file".equals(scheme) && !"http".equals(scheme)) {
                        if ("https".equals(scheme)) {
                        }
                    }
                    return parse.getLastPathSegment();
                } catch (Exception e) {
                    a.s(e, new StringBuilder("getDisplayNameFromUri e="), "DetailsDataRefiner");
                }
            }
            return "";
        }
    }

    public static String getExposureBias(MediaItem mediaItem) {
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag == null || TextUtils.isEmpty(exifTag.exposureBias)) {
            return null;
        }
        return C0212a.p(new StringBuilder(), exifTag.exposureBias, "ev");
    }

    public static String getExposureTime(MediaItem mediaItem) {
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag != null) {
            return exifTag.exposureTime;
        }
        return null;
    }

    public static String getFileSizeString(MediaItem mediaItem) {
        long j2;
        if (mediaItem == null) {
            return "";
        }
        if (mediaItem.isCloudOnly()) {
            j2 = mediaItem.getCloudOriginalSize();
        } else {
            j2 = mediaItem.getFileSize();
        }
        return StringCompat.formatFileSize(j2);
    }

    public static String getFlashOn(MediaItem mediaItem) {
        Boolean bool;
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag == null || (bool = exifTag.flash) == null || !bool.booleanValue()) {
            return "";
        }
        return AppResources.getString(R$string.flash_on);
    }

    public static String getFocalLength(MediaItem mediaItem) {
        String str;
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag == null) {
            return "";
        }
        if (exifTag.focalLength35mm != null) {
            str = String.format(Locale.getDefault(), "%d", new Object[]{exifTag.focalLength35mm});
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = convertFocalLength(exifTag.focalLength);
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder s = C0212a.s(str);
        s.append(AppResources.getString(R$string.unit_mm));
        return s.toString();
    }

    public static String getFrameRateString(MediaItem mediaItem) {
        Integer num;
        if (!Features.isEnabled(Features.SUPPORT_MP_CAPTURE_FRAMERATE) || !RecordingMode.isSlowMo(mediaItem.getRecordingMode())) {
            num = DetailsFrameRateGetter.getInstance().get(mediaItem);
        } else {
            num = Integer.valueOf((int) Math.ceil(VideoPropData.of(mediaItem).videoCaptureFrameRate));
        }
        if (num == null || num.intValue() <= 0) {
            return null;
        }
        return StringCompat.toReadableNumber((long) num.intValue()) + "fps";
    }

    public static String getIso(MediaItem mediaItem) {
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag == null || TextUtils.isEmpty(exifTag.iso)) {
            return null;
        }
        return AppResources.getString(R$string.iso) + " " + exifTag.iso;
    }

    public static String getLensInfo(MediaItem mediaItem, Context context) {
        ExifTag exifTag = mediaItem.getExifTag();
        if (exifTag == null || !"samsung".equalsIgnoreCase(exifTag.make) || context == null) {
            return null;
        }
        if (exifTag.lensInfo == null) {
            ExifMakerNoteCompat unpack = new ExifMakerNoteCompat(exifTag.makerNote).unpack();
            String str = "";
            if (unpack.available()) {
                long focalLengthIn35mm = unpack.getFocalLengthIn35mm();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(unpack.getReadableLensType(context));
                if (focalLengthIn35mm > 0) {
                    str = " - " + String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(focalLengthIn35mm)}) + context.getString(R$string.unit_mm);
                }
                sb2.append(str);
                exifTag.lensInfo = sb2.toString();
            } else {
                exifTag.lensInfo = str;
            }
        }
        return exifTag.lensInfo;
    }

    public static long getLocalDateTime(MediaItem mediaItem) {
        String str;
        if (mediaItem == null || !Features.isEnabled(Features.SUPPORT_LOCAL_TIME)) {
            return -1;
        }
        long dateLocal = mediaItem.getDateLocal();
        if (TimeUtil.isValidLocalTime(dateLocal)) {
            str = "lt";
        } else if (!mediaItem.isImage() || mediaItem.getExifTag() == null) {
            if (mediaItem.isVideo()) {
                long localTimeZone = MetadataTimeHelper.getLocalTimeZone(mediaItem);
                if (localTimeZone != -1) {
                    dateLocal = mediaItem.getDateTaken() + localTimeZone;
                    str = "mt";
                }
            }
            str = "";
        } else {
            dateLocal = mediaItem.getExifTag().getDateTimeOriginalMilli();
            str = "ex";
        }
        Log.i("DetailsDataRefiner", "getLocalDateTime:[" + str + "][" + dateLocal + "]");
        return dateLocal;
    }

    private static String getMainCaptureMode(MediaItem mediaItem) {
        String str;
        int mainCaptureModeByRecordingMode;
        if (mediaItem == null) {
            return "";
        }
        if (mediaItem.isMotionPhoto() && !mediaItem.isShotMode("motion_photo")) {
            return AppResources.getString(R$string.speak_motion_photo);
        }
        CameraCaptureMode cameraCaptureModeInfo = getCameraCaptureModeInfo(mediaItem);
        if (cameraCaptureModeInfo != null) {
            str = cameraCaptureModeInfo.getString(AppResources.getAppContext());
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        int mainCaptureModeByShotMode = getMainCaptureModeByShotMode(mediaItem);
        if (mainCaptureModeByShotMode > 0) {
            return AppResources.getString(mainCaptureModeByShotMode);
        }
        if (!mediaItem.isVideo() || (mainCaptureModeByRecordingMode = getMainCaptureModeByRecordingMode(mediaItem)) <= 0) {
            return "";
        }
        return AppResources.getString(mainCaptureModeByRecordingMode);
    }

    private static int getMainCaptureModeByRecordingMode(MediaItem mediaItem) {
        int recordingMode = mediaItem.getRecordingMode();
        if (RecordingMode.isPortraitVideo(recordingMode)) {
            return R$string.camera_capture_mode_portrait_video;
        }
        if (RecordingMode.isPro(recordingMode)) {
            return R$string.camera_capture_mode_pro_video;
        }
        return -1;
    }

    private static int getMainCaptureModeByShotMode(MediaItem mediaItem) {
        boolean z;
        String str;
        if (mediaItem.isSingleTakenShot() || mediaItem.isBurstShot()) {
            z = true;
        } else {
            z = false;
        }
        ShotModeList instance = ShotModeList.getInstance();
        if (z) {
            str = mediaItem.getGroupMode();
        } else {
            str = mediaItem.getShotMode();
        }
        ShotMode byType = instance.getByType(str);
        if (byType == null || MediaItemUtil.isApv(mediaItem)) {
            return -1;
        }
        return byType.titleResForCapture;
    }

    private static String getMegaPixel(MediaItem mediaItem) {
        int round = Math.round(((float) (((long) mediaItem.getWidth()) * ((long) mediaItem.getHeight()))) / 1000000.0f);
        if (round <= 0) {
            return "";
        }
        return StringCompat.toReadableNumber((long) round) + AppResources.getString(R$string.mega_pixel);
    }

    private static String getMyDevicePath(MediaItem mediaItem) {
        return FileUtils.getDirectoryFromPath(BucketUtils.translatePath(mediaItem.getReferencePath(), mediaItem.isCloudOnly()), false);
    }

    public static SpannableStringBuilder getNoAuthLocationText(MediaItem mediaItem) {
        int i2;
        if (mediaItem.isImage()) {
            i2 = R$string.no_authority_location_image;
        } else {
            i2 = R$string.no_authority_location_video;
        }
        String string = AppResources.getString(R$string.gallery_settings);
        String string2 = AppResources.getString(i2, string);
        int indexOf = string2.indexOf(string);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(AppResources.getColor(R$color.moreInfo_details_header_text_color));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string2);
        spannableStringBuilder.setSpan(new UnderlineSpan(), indexOf, string.length() + indexOf, 18);
        spannableStringBuilder.setSpan(new StyleSpan(1), indexOf, string.length() + indexOf, 18);
        spannableStringBuilder.setSpan(new TypefaceSpan("sec-roboto-condensed"), indexOf, string.length() + indexOf, 18);
        spannableStringBuilder.setSpan(foregroundColorSpan, indexOf, string.length() + indexOf, 18);
        return spannableStringBuilder;
    }

    private static String getOriginalDateString(MediaItem mediaItem, String str, String str2) {
        long localDateTime = getLocalDateTime(mediaItem);
        if (TimeUtil.isValidLocalTime(localDateTime)) {
            return getOriginalDateString(localDateTime, str, str2);
        }
        return "";
    }

    private static String getPathFromUri(Uri uri) {
        String path;
        if (uri == null || !"file".equals(uri.getScheme()) || (path = uri.getPath()) == null || path.startsWith("/data/data")) {
            return "";
        }
        return path;
    }

    public static String getPathString(MediaItem mediaItem) {
        String str = "";
        if (mediaItem != null && !mediaItem.isSharing() && !mediaItem.isMtp()) {
            if (mediaItem.isUriItem()) {
                if (mediaItem.getHttpUri() != null) {
                    str = mediaItem.getHttpUri();
                } else {
                    ExtrasID extrasID = ExtrasID.ORIGIN_PATH;
                    if (!TextUtils.isEmpty((CharSequence) mediaItem.getExtra(extrasID))) {
                        str = BucketUtils.translatePath((String) mediaItem.getExtra(extrasID), false);
                    } else {
                        str = getPathFromUri(Uri.parse(mediaItem.getPath()));
                    }
                }
            } else if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !mediaItem.isPrivateItem()) {
                str = getMyDevicePath(mediaItem);
            } else {
                str = new File(BucketUtils.translatePath((String) mediaItem.getExtra(ExtrasID.ORIGIN_PATH, str), false)).getParent();
            }
            if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && str != null && mediaItem.isCommonPostProcessing()) {
                str = changePppPath(str);
            }
            if (Features.isEnabled(Features.IS_RTL) && str != null && !mediaItem.isUriItem()) {
                return StringCompat.toRtlDisplayPath(str);
            }
        }
        return str;
    }

    public static String getResolutionSpecString(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "";
        }
        if (mediaItem.isVideo()) {
            return getVideoResolutionSpec(mediaItem);
        }
        return getMegaPixel(mediaItem);
    }

    public static String getResolutionString(MediaItem mediaItem) {
        boolean z;
        int i2;
        int i7;
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.getWidth() == 0 || mediaItem.getHeight() == 0) {
            return "";
        }
        if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = mediaItem.getHeight();
        } else {
            i2 = mediaItem.getWidth();
        }
        if (z) {
            i7 = mediaItem.getWidth();
        } else {
            i7 = mediaItem.getHeight();
        }
        if (Features.isEnabled(Features.IS_RTL)) {
            return String.format(Locale.getDefault(), "%dx%d", new Object[]{Integer.valueOf(i7), Integer.valueOf(i2)});
        }
        return String.format(Locale.getDefault(), "%dx%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)});
    }

    private static ScreenCaptureInfo getScreenCaptureInfo(MediaItem mediaItem) {
        byte[] sefParserData = DetailsData.of(mediaItem).getSefParserData(SefInfo.SCREEN_CAPTURE_INFO.keyName);
        if (sefParserData == null || sefParserData.length <= 0) {
            return null;
        }
        return ScreenCaptureInfo.get(new String(sefParserData));
    }

    private static ArrayList<String> getSubCaptureModeList(MediaItem mediaItem) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (mediaItem != null) {
            int recordingMode = mediaItem.getRecordingMode();
            if (RecordingMode.isLogVideo(recordingMode)) {
                arrayList.add(AppResources.getString(R$string.camera_capture_mode_log_video));
            }
            if (RecordingMode.isHdr10Plus(recordingMode)) {
                arrayList.add(AppResources.getString(R$string.camera_capture_mode_hdr_10_plus));
            } else if (mediaItem.isHdr10Video() || mediaItem.isHlgVideo()) {
                arrayList.add(AppResources.getString(R$string.camera_capture_mode_hdr));
            }
            if (mediaItem.is3dCapture()) {
                arrayList.add(AppResources.getString(R$string.capture_3d));
            }
        }
        return arrayList;
    }

    private static String getSuggestedEditVideoTitle(MediaItem mediaItem) {
        String string = AppResources.getString(RecordingMode.toSuggestedEditTitleResId(mediaItem.getRecordingMode()));
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        if (!MediaItemUtil.isSuggestedEditSuperSlowVideo(mediaItem)) {
            return string;
        }
        return AppResources.getString(R$string.camera_capture_mode_super_slow_motion) + " - " + string;
    }

    public static String getVideoCodec(MediaItem mediaItem) {
        String str = VideoPropData.of(mediaItem).videoCodec;
        if ("h.265".equalsIgnoreCase(str)) {
            return "HEVC";
        }
        return str;
    }

    public static String getVideoDuration(MediaItem mediaItem) {
        int fileDuration = mediaItem.getFileDuration();
        if (fileDuration > 0) {
            return TimeUtil.formatDuration(AppResources.getAppContext(), fileDuration, new int[]{R$string.details_ms, R$string.details_hms});
        }
        return null;
    }

    public static String getVideoLensInfo(MediaItem mediaItem) {
        SparseArray<String> sparseArray;
        VideoPropData of2 = VideoPropData.of(mediaItem);
        String str = of2.videoLensInfo;
        if (str != null || (sparseArray = of2.videoMetadata) == null || sparseArray.size() <= 0) {
            return str;
        }
        String parseVideoLensInfo = parseVideoLensInfo(sparseArray);
        of2.videoLensInfo = parseVideoLensInfo;
        return parseVideoLensInfo;
    }

    private static String getVideoResolutionSpec(MediaItem mediaItem) {
        int max = Math.max(mediaItem.getWidthInDB(), mediaItem.getHeightInDB());
        int min = Math.min(mediaItem.getWidthInDB(), mediaItem.getHeightInDB());
        if (max == 1280 && min == 720) {
            return "HD";
        }
        if (max == 1920 && (min == 1080 || min == 824)) {
            return "FHD";
        }
        if (max == 2560 && min == 1440) {
            return "QHD";
        }
        if (max == 3840 && (min == 2160 || min == 1644 || min == 1648)) {
            return "UHD";
        }
        if (max != 7680) {
            return "";
        }
        if (min == 4320 || min == 3296) {
            return "8K";
        }
        return "";
    }

    public static String getVideoShotType(MediaItem mediaItem) {
        if (mediaItem.isSingleTakenShot()) {
            return AppResources.getString(SingleTakeType.toStringResId(mediaItem.getSefFileTypes()));
        }
        if (MediaItemUtil.isSuggestedEditVideo(mediaItem)) {
            return getSuggestedEditVideoTitle(mediaItem);
        }
        return "";
    }

    public static String getVirtualAperture(MediaItem mediaItem) {
        Map<String, String> cameraVirtualApertureMap = getCameraVirtualApertureMap(mediaItem);
        if (cameraVirtualApertureMap == null || !cameraVirtualApertureMap.containsKey("F")) {
            return "";
        }
        float f = UnsafeCast.toFloat(cameraVirtualApertureMap.get("F"));
        if (!UnsafeCast.isInvalid(f)) {
            return String.format(Locale.getDefault(), "F%.1f", new Object[]{Float.valueOf(f)});
        }
        return "";
    }

    public static String getWhiteBalance(MediaItem mediaItem) {
        byte[] sefParserData = DetailsData.of(mediaItem).getSefParserData(SefInfo.PRO_WHITE_BALANCE_INFO.keyName);
        if (sefParserData == null || sefParserData.length <= 0) {
            return null;
        }
        return "WB ".concat(new String(sefParserData));
    }

    private static HashMap<String, String> makeVirtualApertureMap(byte[] bArr) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String split : new String(bArr).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            String[] split2 = split.split("=", 2);
            if (split2.length >= 2) {
                hashMap.put(split2[0].trim(), split2[1].trim());
            }
        }
        return hashMap;
    }

    private static String parseVideoLensInfo(SparseArray<String> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            return null;
        }
        String str = sparseArray.get(1033);
        String str2 = sparseArray.get(1034);
        String str3 = "";
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            long parseLong = Long.parseLong(str);
            long parseLong2 = Long.parseLong(str2);
            if (parseLong != 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(ExifMakerNoteCompat.getReadableLensType(AppResources.getAppContext(), (int) parseLong));
                if (parseLong2 > 0) {
                    str3 = " - " + str2 + AppResources.getString(R$string.unit_mm);
                }
                sb2.append(str3);
                return sb2.toString();
            }
        }
        return str3;
    }

    private static String replaceFirstWord(String str, String str2) {
        String[] split = str.split("/");
        split[0] = str2;
        return String.join("/", split);
    }

    private static String getDateString(long j2, String str, String str2) {
        return j2 != 0 ? TimeUtil.getLocalizedDateTime(j2, str, 0) : str2;
    }

    private static String getOriginalDateString(long j2, String str, String str2) {
        return j2 != 0 ? TimeUtil.getOriginalDateTime(j2, str, 0) : str2;
    }
}
