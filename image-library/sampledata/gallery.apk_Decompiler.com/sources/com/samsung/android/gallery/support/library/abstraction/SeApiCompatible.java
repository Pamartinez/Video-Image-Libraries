package com.samsung.android.gallery.support.library.abstraction;

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
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SeApiCompatible {
    void convertActivityFromTranslucent(Activity activity);

    void convertActivityToTranslucent(Activity activity, Activity.SemTranslucentConversionListener semTranslucentConversionListener);

    boolean copyOnVold(Context context, String str, String str2) {
        return false;
    }

    Intent createClipboardIntent(String str, String str2) {
        return new Intent();
    }

    MediaPlayerCompat createMediaPlayer(int i2);

    MediaPlayerCompat createSecBgmAudioPlayer(int i2);

    MediaPlayerCompat createSecBgmVideoPlayer(int i2);

    MediaPlayerCompat createSecMediaPlayer(int i2);

    VideoTranscoderCompat createVideoTranscoderCompat() {
        return null;
    }

    String getAudioFocusedPackageName(Context context) {
        return "";
    }

    BoosterCompat getBoosterCompat(Context context);

    ArrayList<ClipData.Item> getClipDataItems(ClipData clipData) {
        return null;
    }

    boolean getCscFeatureBoolean(String str, boolean z);

    String getCscFeatureString(String str, String str2);

    DisplayManagerCompat getDisplayManagerCompat(Context context);

    DrmStoreCompat getDrmStoreCompat();

    int getDualAppProfileId() {
        return -1;
    }

    boolean getFloatingFeatureBoolean(String str) {
        return false;
    }

    int getFloatingFeatureInt(String str) {
        return -1;
    }

    String getFloatingFeatureString(String str) {
        return "";
    }

    HoverStatusManagerCompat getHoverStatusManager(boolean z);

    HoverViewCompat getHoverViewCompat();

    KeepStorage getKeepStorage();

    String getKnoxContainerLabel(Context context) {
        return null;
    }

    MediaCaptureCompat getMediaCaptureCompat();

    MediaResourceHelperCompat getMediaResourceHelper();

    MediaTranscodeCompat getMediaTranscodeCompat();

    String getMountState(Context context);

    ArrayList<Bundle> getMoveToKnoxMenuList(Context context) {
        return null;
    }

    int getMyUserId() {
        return 0;
    }

    int getPinnedEdgeWidth(Context context) {
        return 0;
    }

    ActivityOptions getPopoverActivityOptions(RectF rectF, Point point) {
        return null;
    }

    String getPrefixForSpan(TextView textView, CharSequence charSequence, String str) {
        return null;
    }

    String getQuickCropFormats() {
        return "";
    }

    ByteBuffer getQuickCropStream(File file, Rect rect) {
        return null;
    }

    String getSdcardId(Context context) {
        return "";
    }

    SefFileCompat getSefFileCompat();

    int getSettingsGlobalInt(Context context, String str, int i2) {
        if (!(context == null || str == null)) {
            try {
                return Settings.Global.getInt(context.getContentResolver(), str, i2);
            } catch (Exception unused) {
            }
        }
        return i2;
    }

    int getSettingsSystemInt(Context context, String str, int i2, boolean z) {
        try {
            return Settings.System.getInt(context.getContentResolver(), str, i2);
        } catch (Exception e) {
            j.C(e, j.k("getSettingsSystemInt[", str, "] failed. e="), getClass().getSimpleName());
            return i2;
        }
    }

    List<StorageVolumeCompat> getStorageVolumes(Context context);

    int getSystemProperties(String str, int i2);

    String getSystemProperties(String str, String str2);

    boolean getSystemProperties(String str, boolean z);

    Bitmap getThumbnailHeif(String str, BitmapFactory.Options options) {
        return null;
    }

    Bitmap getVideoFrameAtTime(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2);

    VslMesDetectorCompat getVslMesDetectorCompat(String str) {
        return new VslMesDetectorCompat();
    }

    boolean isAccessoryKeyboardState(Context context) {
        return false;
    }

    boolean isActivityResumed(Activity activity);

    boolean isAfw(Context context);

    boolean isAutoRotateEnabled(Context context);

    boolean isAutoRotateSecondEnabled(Context context);

    boolean isBrightnessModeAutomatic(Context context) {
        return false;
    }

    boolean isClearCoverAttached(Context context) {
        return false;
    }

    boolean isClipboardEnabled(Context context) {
        return false;
    }

    boolean isClipboardShowing(Context context) {
        return false;
    }

    boolean isDexMode(Context context) {
        return false;
    }

    boolean isDexOnPc(Context context) {
        return false;
    }

    boolean isDexStandAloneMode(Context context) {
        return false;
    }

    boolean isDualSecondScreen(Context context);

    boolean isFolded(Activity activity) {
        return false;
    }

    boolean isFreeFormMode();

    boolean isKnoxMode(Context context) {
        return false;
    }

    boolean isLeftPinnedEdge(Context context) {
        return false;
    }

    boolean isMainScreen(Configuration configuration) {
        return false;
    }

    boolean isMobileKeyboardCovered(Context context) {
        return false;
    }

    boolean isMyUserIdAsUserCurrent() {
        return false;
    }

    boolean isMyUserIdAsUserOwner() {
        return false;
    }

    boolean isOnSecureFolder(Context context) {
        return false;
    }

    boolean isPinEdgeEnabled(Context context) {
        return false;
    }

    boolean isProfileNotSupportSdCard(Context context) {
        return false;
    }

    boolean isReducedTransparency(Context context) {
        return false;
    }

    boolean isScreenLocked(Context context);

    boolean isSdcardHealthy(Context context) {
        return true;
    }

    boolean isSdcardMounted(Context context);

    boolean isSharedAlbumBlocked(Context context) {
        return false;
    }

    boolean isShopDemoMode(Context context) {
        return false;
    }

    boolean isUpsm(Context context) {
        return false;
    }

    boolean isVoiceServiceEnabled(Context context) {
        return false;
    }

    boolean minimizeSoftInput(Context context, IBinder iBinder, int i2) {
        return false;
    }

    void moveFilesForApp(Context context, Uri uri, int i2, int i7);

    void moveFilesForApp(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i2);

    boolean requestAccessibilityFocus(View view) {
        return false;
    }

    boolean requestDismissKeyguard(Activity activity, KeyguardManager.KeyguardDismissCallback keyguardDismissCallback);

    boolean setAutoBrightnessLimit(Context context, int i2, int i7) {
        return false;
    }

    void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7) {
    }

    boolean supportHeif();

    boolean supportSetVideoSize() {
        return false;
    }

    boolean touchOnVold(Context context, String str) {
        return false;
    }

    boolean updateClipData(View view, ClipData clipData) {
        return false;
    }

    void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7, boolean z) {
    }

    void clearBitmapTag(Bitmap bitmap) {
    }

    void clearDexMode(Context context) {
    }

    void disableViewRoundedCorner(View view) {
    }

    String naturalizeText(String str) {
        return str;
    }

    void removeBlur(View view) {
    }

    void setButtonShapeEnabled(TextView textView) {
    }

    void showClipboardDialog(Context context) {
    }

    void announceVoiceAssistant(Context context, String str) {
    }

    void performHapticFeedback(Context context, int i2) {
    }

    void setCanvasBlurPreset(View view, int i2) {
    }

    void setVideoHwCodecEnabled(MediaMetadataRetriever mediaMetadataRetriever, boolean z) {
    }

    void setViewRoundedCorner(View view, int i2) {
    }

    void addBitmapTag(Bitmap bitmap, String str, Object obj) {
    }

    <T> T getBitmapTag(Bitmap bitmap, String str, T t) {
        return t;
    }

    void setLaunchOverTargetTask(Intent intent, int i2, boolean z) {
    }

    void setPopoverDialogStyle(Dialog dialog, View view, boolean z) {
    }

    void setViewRoundedCornerColor(View view, int i2, int i7) {
    }

    void adjustPopOverOptions(Activity activity, int[] iArr, int[] iArr2, Point[] pointArr, int[] iArr3) {
    }

    void setMarginsRelative(View view, int i2, int i7, int i8, int i10) {
    }
}
