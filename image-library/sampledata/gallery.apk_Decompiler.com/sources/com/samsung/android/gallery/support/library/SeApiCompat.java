package com.samsung.android.gallery.support.library;

import N2.j;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.library.abstraction.DrmStoreCompat;
import com.samsung.android.gallery.support.library.abstraction.HoverStatusManagerCompat;
import com.samsung.android.gallery.support.library.abstraction.HoverViewCompat;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaResourceHelperCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaTranscodeCompat;
import com.samsung.android.gallery.support.library.abstraction.SeApiCompatible;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.library.abstraction.VideoTranscoderCompat;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.v0.GedApiCompatImpl;
import com.samsung.android.gallery.support.library.v0.Sem80ApiCompatImpl;
import com.samsung.android.gallery.support.library.v0.Sem81ApiCompatImpl;
import com.samsung.android.gallery.support.library.v0.Sem85ApiCompatImpl;
import com.samsung.android.gallery.support.library.v0.Sem90ApiCompatImpl;
import com.samsung.android.gallery.support.library.v0.Sem95ApiCompatImpl;
import com.samsung.android.gallery.support.library.v11.Sem120ApiCompatImpl;
import com.samsung.android.gallery.support.library.v11.Sem121ApiCompatImpl;
import com.samsung.android.gallery.support.library.v11.Sem125ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem130ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem131ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem135ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem140ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem141ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem145ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem150ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.Sem160ApiCompatImpl;
import com.samsung.android.gallery.support.library.v16.Sem170ApiCompatImpl;
import com.samsung.android.gallery.support.library.v16.Sem175ApiCompatImpl;
import com.samsung.android.gallery.support.library.v9.Sem100ApiCompatImpl;
import com.samsung.android.gallery.support.library.v9.Sem110ApiCompatImpl;
import com.samsung.android.gallery.support.library.v9.Sem111ApiCompatImpl;
import com.samsung.android.gallery.support.library.v9.Sem115ApiCompatImpl;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeApiCompat {
    /* access modifiers changed from: private */
    public static volatile SeApiCompatible sApiCompat;
    private static volatile Boolean sIsFreeFormMode;
    public static volatile int version;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DrmStoreCompatHolder {
        static final DrmStoreCompat sInstance = SeApiCompat.sApiCompat.getDrmStoreCompat();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HoverViewCompatHolder {
        static final HoverViewCompat sInstance = SeApiCompat.sApiCompat.getHoverViewCompat();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaResourceHelperCompatHolder {
        static final MediaResourceHelperCompat sInstance = SeApiCompat.sApiCompat.getMediaResourceHelper();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaTranscodeCompatHolder {
        static final MediaTranscodeCompat sInstance = SeApiCompat.sApiCompat.getMediaTranscodeCompat();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PassStorageHolder {
        static final KeepStorage instance = SeApiCompat.sApiCompat.getKeepStorage();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SecurityPatchLevelHolder {
        static final long version = load();

        public static long load() {
            try {
                return Long.parseLong(SeApiCompat.getSystemPropertiesString("ro.build.version.security_patch", "0").replaceAll("\\D", ""));
            } catch (Error | Exception unused) {
                return 0;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SefFileCompatHolder {
        static final SefFileCompat sInstance = SeApiCompat.sApiCompat.getSefFileCompat();
    }

    static {
        init();
    }

    public static void addBitmapTag(Bitmap bitmap, String str, Object obj) {
        if (bitmap != null) {
            sApiCompat.addBitmapTag(bitmap, str, obj);
        }
    }

    public static void adjustPopOverOptions(Activity activity, int[] iArr, int[] iArr2, Point[] pointArr, int[] iArr3) {
        sApiCompat.adjustPopOverOptions(activity, iArr, iArr2, pointArr, iArr3);
    }

    public static void announceVoiceAssistant(Context context, String str) {
        if (context != null) {
            sApiCompat.announceVoiceAssistant(context, str);
        }
    }

    public static void clearBitmapTag(Bitmap bitmap) {
        if (bitmap != null) {
            sApiCompat.clearBitmapTag(bitmap);
        }
    }

    public static void clearDexMode(Context context) {
        sApiCompat.clearDexMode(context);
    }

    public static void convertActivityFromTranslucent(Activity activity) {
        if (activity != null) {
            sApiCompat.convertActivityFromTranslucent(activity);
        }
    }

    public static void convertActivityToTranslucent(Activity activity, Activity.SemTranslucentConversionListener semTranslucentConversionListener) {
        if (activity != null) {
            sApiCompat.convertActivityToTranslucent(activity, semTranslucentConversionListener);
        }
    }

    public static boolean copyOnVold(Context context, String str, String str2) {
        return sApiCompat.copyOnVold(context, str, str2);
    }

    public static Intent createClipboardIntent(String str, String str2) {
        return sApiCompat.createClipboardIntent(str, str2);
    }

    public static MediaCaptureCompat createMediaCaptureCompat() {
        return sApiCompat.getMediaCaptureCompat();
    }

    public static MediaPlayerCompat createMediaPlayer(int i2) {
        return sApiCompat.createMediaPlayer(i2);
    }

    public static MediaPlayerCompat createSecBgmAudioPlayer(int i2) {
        return sApiCompat.createSecBgmAudioPlayer(i2);
    }

    public static MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return sApiCompat.createSecBgmVideoPlayer(i2);
    }

    public static MediaPlayerCompat createSecMediaPlayer(int i2) {
        return sApiCompat.createSecMediaPlayer(i2);
    }

    public static VideoTranscoderCompat createVideoTranscoder() {
        return sApiCompat.createVideoTranscoderCompat();
    }

    public static VslMesDetectorCompat createVslMesDetectorCompat(String str) {
        return sApiCompat.getVslMesDetectorCompat(str);
    }

    public static void disableViewRoundedCorner(View view) {
        sApiCompat.disableViewRoundedCorner(view);
    }

    public static String getAudioFocusedPackageName(Context context) {
        return sApiCompat.getAudioFocusedPackageName(context);
    }

    public static <T> T getBitmapTag(Bitmap bitmap, String str, T t) {
        if (bitmap != null) {
            return sApiCompat.getBitmapTag(bitmap, str, t);
        }
        return t;
    }

    public static BoosterCompat getBoosterCompat(Context context) {
        return sApiCompat.getBoosterCompat(context);
    }

    public static String getBrandName() {
        String floatingFeatureString = sApiCompat.getFloatingFeatureString("SEC_FLOATING_FEATURE_SETTINGS_CONFIG_BRAND_NAME");
        if (TextUtils.isEmpty(floatingFeatureString)) {
            return "Samsung Galaxy";
        }
        return floatingFeatureString;
    }

    public static ArrayList<ClipData.Item> getClipDataItems(ClipData clipData) {
        return sApiCompat.getClipDataItems(clipData);
    }

    public static boolean getCscFeatureBoolean(String str, boolean z) {
        return sApiCompat.getCscFeatureBoolean(str, z);
    }

    public static String getCscFeatureString(String str, String str2) {
        return sApiCompat.getCscFeatureString(str, str2);
    }

    public static DisplayManagerCompat getDisplayManagerCompat(Context context) {
        return sApiCompat.getDisplayManagerCompat(context);
    }

    public static DrmStoreCompat getDrmStoreCompat() {
        return DrmStoreCompatHolder.sInstance;
    }

    public static int getDualAppProfileId() {
        return sApiCompat.getDualAppProfileId();
    }

    public static boolean getFloatingFeatureBoolean(String str) {
        return sApiCompat.getFloatingFeatureBoolean(str);
    }

    public static int getFloatingFeatureInt(String str) {
        return sApiCompat.getFloatingFeatureInt(str);
    }

    public static String getFloatingFeatureString(String str) {
        return sApiCompat.getFloatingFeatureString(str);
    }

    public static HoverStatusManagerCompat getHoverStatusManager(boolean z) {
        return sApiCompat.getHoverStatusManager(z);
    }

    public static HoverViewCompat getHoverViewCompat() {
        return HoverViewCompatHolder.sInstance;
    }

    public static String getKnoxContainerLabel(Context context) {
        return sApiCompat.getKnoxContainerLabel(context);
    }

    public static MediaResourceHelperCompat getMediaResourceHelper() {
        return MediaResourceHelperCompatHolder.sInstance;
    }

    public static MediaTranscodeCompat getMediaTranscodeCompat() {
        return MediaTranscodeCompatHolder.sInstance;
    }

    public static String getMountState(Context context) {
        return sApiCompat.getMountState(context);
    }

    public static ArrayList<Bundle> getMoveToKnoxMenuList(Context context) {
        return sApiCompat.getMoveToKnoxMenuList(context);
    }

    public static int getMyUserId() {
        return sApiCompat.getMyUserId();
    }

    public static KeepStorage getPassStorage() {
        return PassStorageHolder.instance;
    }

    public static int getPinnedEdgeWidth(Context context) {
        return sApiCompat.getPinnedEdgeWidth(context);
    }

    public static ActivityOptions getPopoverActivityOptions(RectF rectF, Point point) {
        return sApiCompat.getPopoverActivityOptions(rectF, point);
    }

    public static String getPrefixForSpan(TextView textView, CharSequence charSequence, String str) {
        return sApiCompat.getPrefixForSpan(textView, charSequence, str);
    }

    public static String getQuickCropFormats() {
        return sApiCompat.getQuickCropFormats();
    }

    public static ByteBuffer getQuickCropStream(File file, Rect rect) {
        return sApiCompat.getQuickCropStream(file, rect);
    }

    public static String getSdcardId(Context context) {
        return sApiCompat.getSdcardId(context);
    }

    public static long getSecurityPatchLevel() {
        return SecurityPatchLevelHolder.version;
    }

    public static byte[] getSefData(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return SefFileCompatHolder.sInstance.getData(new File(str), str2);
        } catch (Exception e) {
            j.D(e, new StringBuilder("getSefData failed. e="), "SeApiCompat");
            return null;
        }
    }

    public static SefFileCompat getSefFileCompat() {
        return SefFileCompatHolder.sInstance;
    }

    public static String getSefString(String str, String str2) {
        byte[] sefData = getSefData(str, str2);
        if (sefData == null || sefData.length <= 0) {
            return "";
        }
        return new String(sefData, StandardCharsets.UTF_8);
    }

    public static int getSettingsGlobalInt(Context context, String str, int i2) {
        return sApiCompat.getSettingsGlobalInt(context, str, i2);
    }

    public static int getSettingsSystemInt(Context context, String str, int i2) {
        if (context == null || str == null) {
            return i2;
        }
        return sApiCompat.getSettingsSystemInt(context, str, i2, false);
    }

    public static List<StorageVolumeCompat> getStorageVolumes(Context context) {
        return sApiCompat.getStorageVolumes(context);
    }

    public static String getSystemProperties(String str) {
        return sApiCompat.getSystemProperties(str, "");
    }

    public static boolean getSystemPropertiesBoolean(String str, boolean z) {
        return sApiCompat.getSystemProperties(str, z);
    }

    public static int getSystemPropertiesInt(String str, int i2) {
        return sApiCompat.getSystemProperties(str, i2);
    }

    public static String getSystemPropertiesString(String str, String str2) {
        return sApiCompat.getSystemProperties(str, str2);
    }

    public static Bitmap getThumbnailFromHeif(String str) {
        return sApiCompat.getThumbnailHeif(str, (BitmapFactory.Options) null);
    }

    public static Bitmap getVideoFrameAtTime(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return sApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, i2);
    }

    private static void init() {
        try {
            version = Build.VERSION.SEM_PLATFORM_INT;
            if (version >= 170500) {
                sApiCompat = new Sem175ApiCompatImpl();
            } else if (version >= 170000) {
                sApiCompat = new Sem170ApiCompatImpl();
            } else if (version >= 160000) {
                sApiCompat = new Sem160ApiCompatImpl();
            } else if (version >= 150000) {
                sApiCompat = new Sem150ApiCompatImpl();
            } else if (version >= 140500) {
                sApiCompat = new Sem145ApiCompatImpl();
            } else if (version >= 140100) {
                sApiCompat = new Sem141ApiCompatImpl();
            } else if (version >= 140000) {
                sApiCompat = new Sem140ApiCompatImpl();
            } else if (version >= 130500) {
                sApiCompat = new Sem135ApiCompatImpl();
            } else if (version >= 130100) {
                sApiCompat = new Sem131ApiCompatImpl();
            } else if (version >= 130000) {
                sApiCompat = new Sem130ApiCompatImpl();
            } else if (version >= 120500) {
                sApiCompat = new Sem125ApiCompatImpl();
            } else if (version >= 120100) {
                sApiCompat = new Sem121ApiCompatImpl();
            } else if (version >= 120000) {
                sApiCompat = new Sem120ApiCompatImpl();
            } else if (version >= 110500) {
                sApiCompat = new Sem115ApiCompatImpl();
            } else if (version >= 110100) {
                sApiCompat = new Sem111ApiCompatImpl();
            } else if (version >= 110000) {
                sApiCompat = new Sem110ApiCompatImpl();
            } else if (version >= 100000) {
                sApiCompat = new Sem100ApiCompatImpl();
            } else if (version >= 90500) {
                sApiCompat = new Sem95ApiCompatImpl();
            } else if (version >= 90000) {
                sApiCompat = new Sem90ApiCompatImpl();
            } else if (version >= 80500) {
                sApiCompat = new Sem85ApiCompatImpl();
            } else if (version >= 80100) {
                sApiCompat = new Sem81ApiCompatImpl();
            } else if (version >= 80000) {
                sApiCompat = new Sem80ApiCompatImpl();
            }
        } catch (Error | Exception unused) {
            version = 0;
            sApiCompat = null;
            Log.e("SeApiCompat", "init failed");
        }
        if (sApiCompat == null) {
            sApiCompat = new GedApiCompatImpl();
        }
    }

    public static boolean isAccessoryKeyboardState(Context context) {
        if (context == null || !sApiCompat.isAccessoryKeyboardState(context)) {
            return false;
        }
        return true;
    }

    public static boolean isActivityResumed(Activity activity) {
        if (activity == null || !sApiCompat.isActivityResumed(activity)) {
            return false;
        }
        return true;
    }

    public static boolean isAfw(Context context) {
        if (context == null || !sApiCompat.isAfw(context)) {
            return false;
        }
        return true;
    }

    public static boolean isAutoRotateEnabled(Context context) {
        if (context == null || sApiCompat.isAutoRotateEnabled(context)) {
            return true;
        }
        return false;
    }

    public static boolean isAutoRotateSecondEnabled(Context context) {
        if (context == null || sApiCompat.isAutoRotateSecondEnabled(context)) {
            return true;
        }
        return false;
    }

    public static boolean isBrightnessModeAutomatic(Context context) {
        if (context == null || !sApiCompat.isBrightnessModeAutomatic(context)) {
            return false;
        }
        return true;
    }

    public static boolean isClearCoverAttached(Context context) {
        if (context == null || !sApiCompat.isClearCoverAttached(context)) {
            return false;
        }
        return true;
    }

    public static boolean isClipboardEnabled(Context context) {
        if (context == null || !sApiCompat.isClipboardEnabled(context)) {
            return false;
        }
        return true;
    }

    public static boolean isClipboardShowing(Context context) {
        if (context == null || !sApiCompat.isClipboardShowing(context)) {
            return false;
        }
        return true;
    }

    public static boolean isDexMode(Context context) {
        return sApiCompat.isDexMode(context);
    }

    public static boolean isDexOnPc(Context context) {
        return sApiCompat.isDexOnPc(context);
    }

    public static boolean isDexStandAloneMode(Context context) {
        return sApiCompat.isDexStandAloneMode(context);
    }

    public static boolean isDualSecondScreen(Context context) {
        if (context == null || !sApiCompat.isDualSecondScreen(context)) {
            return false;
        }
        return true;
    }

    public static boolean isFolded(Activity activity) {
        return sApiCompat.isFolded(activity);
    }

    public static boolean isFreeFormMode(boolean z) {
        if (sIsFreeFormMode == null || z) {
            sIsFreeFormMode = Boolean.valueOf(sApiCompat.isFreeFormMode());
        }
        try {
            return sIsFreeFormMode.booleanValue();
        } catch (NullPointerException unused) {
            return sApiCompat.isFreeFormMode();
        }
    }

    public static boolean isKnoxMode(Context context) {
        return sApiCompat.isKnoxMode(context);
    }

    public static boolean isLeftPinnedEdge(Context context) {
        return sApiCompat.isLeftPinnedEdge(context);
    }

    public static boolean isMainScreen(Configuration configuration) {
        return sApiCompat.isMainScreen(configuration);
    }

    public static boolean isMobileKeyboardCovered(Context context) {
        return sApiCompat.isMobileKeyboardCovered(context);
    }

    public static boolean isMyUserIdAsUserCurrent() {
        return sApiCompat.isMyUserIdAsUserCurrent();
    }

    public static boolean isMyUserIdAsUserOwner() {
        return sApiCompat.isMyUserIdAsUserOwner();
    }

    public static boolean isOnSecureFolder(Context context) {
        return sApiCompat.isOnSecureFolder(context);
    }

    public static boolean isPinEdgeEnabled(Context context) {
        if (context == null || !sApiCompat.isPinEdgeEnabled(context)) {
            return false;
        }
        return true;
    }

    public static boolean isProfileNotSupportSdCard(Context context) {
        return sApiCompat.isProfileNotSupportSdCard(context);
    }

    public static boolean isReducedTransparency(Context context) {
        if (context == null || !sApiCompat.isReducedTransparency(context)) {
            return false;
        }
        return true;
    }

    public static boolean isRepairMode() {
        if (getMyUserId() == 77) {
            return true;
        }
        return false;
    }

    public static boolean isScreenLocked(Context context) {
        return sApiCompat.isScreenLocked(context);
    }

    public static boolean isSdcardHealthy(Context context) {
        return sApiCompat.isSdcardHealthy(context);
    }

    public static boolean isSdcardMounted(Context context) {
        return sApiCompat.isSdcardMounted(context);
    }

    public static boolean isSharedAlbumBlocked(Context context) {
        return sApiCompat.isSharedAlbumBlocked(context);
    }

    public static boolean isShopDemoMode(Context context) {
        return sApiCompat.isShopDemoMode(context);
    }

    public static boolean isUpsm(Context context) {
        if (context == null || !sApiCompat.isUpsm(context)) {
            return false;
        }
        return true;
    }

    public static boolean isVoiceServiceEnabled(Context context) {
        if (context == null || !sApiCompat.isVoiceServiceEnabled(context)) {
            return false;
        }
        return true;
    }

    public static boolean minimizeSoftInput(Context context, IBinder iBinder, int i2) {
        if (context == null || !sApiCompat.minimizeSoftInput(context, iBinder, i2)) {
            return false;
        }
        return true;
    }

    public static void moveFilesForApp(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i2) {
        sApiCompat.moveFilesForApp(context, arrayList, arrayList2, i2);
    }

    public static String naturalizeText(String str) {
        return sApiCompat.naturalizeText(str);
    }

    public static void performHapticFeedback(Context context, int i2) {
        sApiCompat.performHapticFeedback(context, i2);
    }

    public static void removeBlur(View view) {
        sApiCompat.removeBlur(view);
    }

    public static boolean requestAccessibilityFocus(View view) {
        return sApiCompat.requestAccessibilityFocus(view);
    }

    public static boolean requestDismissKeyguard(Activity activity, KeyguardManager.KeyguardDismissCallback keyguardDismissCallback) {
        return sApiCompat.requestDismissKeyguard(activity, keyguardDismissCallback);
    }

    public static boolean setAutoBrightnessLimit(Context context, int i2, int i7) {
        return sApiCompat.setAutoBrightnessLimit(context, i2, i7);
    }

    public static void setButtonShapeEnabled(TextView textView) {
        if (textView != null) {
            sApiCompat.setButtonShapeEnabled(textView);
        }
    }

    public static void setCanvasBlurPreset(View view, int i2) {
        sApiCompat.setCanvasBlurPreset(view, i2);
    }

    public static void setConfigurationDirty() {
        Log.i("SeApiCompat", "setConfigurationDirty");
        sIsFreeFormMode = null;
    }

    public static void setLaunchOverTargetTask(Intent intent, int i2, boolean z) {
        sApiCompat.setLaunchOverTargetTask(intent, i2, z);
    }

    public static void setMarginsRelative(View view, int i2, int i7, int i8, int i10) {
        sApiCompat.setMarginsRelative(view, i2, i7, i8, i10);
    }

    public static void setPopoverDialogStyle(Dialog dialog, View view, boolean z) {
        sApiCompat.setPopoverDialogStyle(dialog, view, z);
    }

    public static void setVideoHwCodecEnabled(MediaMetadataRetriever mediaMetadataRetriever, boolean z) {
        sApiCompat.setVideoHwCodecEnabled(mediaMetadataRetriever, z);
    }

    public static void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7) {
        sApiCompat.setVideoSize(mediaMetadataRetriever, i2, i7);
    }

    public static void setViewRoundedCorner(View view, int i2) {
        sApiCompat.setViewRoundedCorner(view, i2);
    }

    public static void setViewRoundedCornerColor(View view, int i2, int i7) {
        sApiCompat.setViewRoundedCornerColor(view, i2, i7);
    }

    public static void showClipboardDialog(Context context) {
        sApiCompat.showClipboardDialog(context);
    }

    public static boolean supportHeif() {
        return sApiCompat.supportHeif();
    }

    public static boolean supportSetVideoSize() {
        return sApiCompat.supportSetVideoSize();
    }

    public static boolean touchOnVold(Context context, String str) {
        return sApiCompat.touchOnVold(context, str);
    }

    public static boolean updateClipData(View view, ClipData clipData) {
        return sApiCompat.updateClipData(view, clipData);
    }

    public static void moveFilesForApp(Context context, Uri uri, int i2, int i7) {
        sApiCompat.moveFilesForApp(context, uri, i2, i7);
    }

    public static void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7, boolean z) {
        sApiCompat.setVideoSize(mediaMetadataRetriever, i2, i7, z);
    }

    public static void setLegacyFragmentStateManager() {
    }
}
