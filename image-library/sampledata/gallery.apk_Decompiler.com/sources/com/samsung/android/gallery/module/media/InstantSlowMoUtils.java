package com.samsung.android.gallery.module.media;

import A4.C0375j;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.media.InstantSlowMoPlayLogger;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class InstantSlowMoUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Limitation {
        static final LimitationHolder holder = new LimitationHolder();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class LimitationHolder {
            final int MAX_FRAME_RATE;
            final int MAX_LONG_SIZE;
            final int MAX_SHORT_SIZE;
            final int MIN_FRAME_RATE;
            final int MIN_SIZE;
            final boolean MP_PLAYBACK_OFF;
            final boolean SUPPORT_HDR;

            public LimitationHolder() {
                int i2;
                int i7;
                int i8;
                int i10;
                int i11;
                boolean z;
                String[] loadFeatures = loadFeatures();
                boolean z3 = false;
                if (loadFeatures.length > 0) {
                    i2 = Integer.parseInt(loadFeatures[0]);
                } else {
                    i2 = 720;
                }
                this.MIN_SIZE = i2;
                if (loadFeatures.length > 2) {
                    i7 = Integer.parseInt(loadFeatures[2]);
                } else {
                    i7 = 3840;
                }
                this.MAX_LONG_SIZE = i7;
                if (loadFeatures.length > 3) {
                    i8 = Integer.parseInt(loadFeatures[3]);
                } else {
                    i8 = 2160;
                }
                this.MAX_SHORT_SIZE = i8;
                if (loadFeatures.length > 4) {
                    i10 = Integer.parseInt(loadFeatures[4]);
                } else {
                    i10 = 24;
                }
                this.MIN_FRAME_RATE = i10;
                if (loadFeatures.length > 5) {
                    i11 = Integer.parseInt(loadFeatures[5]);
                } else {
                    i11 = 60;
                }
                this.MAX_FRAME_RATE = i11;
                if (loadFeatures.length <= 6 || !"HDR".equalsIgnoreCase(loadFeatures[6])) {
                    z = false;
                } else {
                    z = true;
                }
                this.SUPPORT_HDR = z;
                if (loadFeatures.length > 7 && "MP_PLAYBACK_OFF".equalsIgnoreCase(loadFeatures[7])) {
                    z3 = true;
                }
                this.MP_PLAYBACK_OFF = z3;
            }

            public String[] loadFeatures() {
                try {
                    String floatingFeatureString = SeApiCompat.getFloatingFeatureString("SEC_FLOATING_FEATURE_CAMERA_CONFIG_AIFRC_SPEC");
                    Log.d("InstantSlowMoUtils", "Limitation=" + floatingFeatureString);
                    if (!TextUtils.isEmpty(floatingFeatureString)) {
                        return floatingFeatureString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                } catch (Error | Exception unused) {
                }
                return new String[0];
            }
        }

        public static boolean allowFrameRate(int i2) {
            LimitationHolder limitationHolder = holder;
            if (i2 < limitationHolder.MIN_FRAME_RATE || i2 > limitationHolder.MAX_FRAME_RATE) {
                return false;
            }
            return true;
        }

        public static boolean allowMaxSize(int i2, int i7) {
            LimitationHolder limitationHolder = holder;
            if (i2 > limitationHolder.MAX_LONG_SIZE || i7 > limitationHolder.MAX_SHORT_SIZE) {
                return false;
            }
            return true;
        }

        public static boolean allowMinSize(int i2, int i7) {
            int i8 = holder.MIN_SIZE;
            if (i2 < i8 || i7 < i8) {
                return false;
            }
            return true;
        }

        public static boolean lowFrameRate(int i2) {
            if (i2 < holder.MIN_FRAME_RATE) {
                return true;
            }
            return false;
        }

        public static boolean supportHdr() {
            return holder.SUPPORT_HDR;
        }

        public static boolean supportMotionPhotoFpsScaleUp() {
            return !holder.MP_PLAYBACK_OFF;
        }
    }

    public static boolean checkInstantSlowMoEditAndShareGuidePreference() {
        if (PreferenceCache.InstantSlowMoEditAndShareGuide.getBoolean() || PreferenceCache.InstantSlowMoEditAndShareGuideCount.getInt() >= 3) {
            return false;
        }
        return true;
    }

    public static boolean checkInstantSlowMoEditGuidePreference() {
        if (PreferenceCache.InstantSlowMoEditGuide.getBoolean() || PreferenceCache.InstantSlowMoEditGuideCount.getInt() >= 3) {
            return false;
        }
        return true;
    }

    public static boolean checkInstantSlowMoGuidePreference(int i2) {
        if (PreferenceCache.InstantSlowMoGuide.getBoolean() || PreferenceCache.InstantSlowMoGuideCount.getInt() >= i2) {
            return false;
        }
        return true;
    }

    public static int checkInstantSlowMoPlayable(MediaItem mediaItem, boolean z) {
        InstantSlowMoPlayLogger.ErrorType errorType;
        InstantSlowMoPlayLogger.ErrorType errorType2;
        if (unsupportableItem(mediaItem)) {
            return InstantSlowMoPlayLogger.ErrorType.ETC.toInt();
        }
        InstantSlowMoPlayLogger.ErrorType errorType3 = InstantSlowMoPlayLogger.ErrorType.NONE;
        if (!Limitation.supportHdr() && mediaItem.isHdrVideo()) {
            errorType3 = InstantSlowMoPlayLogger.ErrorType.HDR;
        } else if (MediaItemUtil.isSuperSlowMotion(mediaItem)) {
            errorType3 = InstantSlowMoPlayLogger.ErrorType.SUPER_SLOW_MOTION;
        } else {
            boolean supportMotionPhoto = supportMotionPhoto(mediaItem);
            if (z && !supportMotionPhoto) {
                int i2 = VideoPropData.of(mediaItem).videoFrameRate;
                if (i2 <= 0) {
                    errorType3 = InstantSlowMoPlayLogger.ErrorType.NO_FPS;
                } else if (Limitation.allowFrameRate(i2)) {
                    int width = mediaItem.getWidth();
                    int height = mediaItem.getHeight();
                    int min = Math.min(width, height);
                    int max = Math.max(width, height);
                    if (!Limitation.allowMinSize(max, min) || !Limitation.allowMaxSize(max, min)) {
                        if (!Limitation.allowMinSize(max, min)) {
                            errorType2 = InstantSlowMoPlayLogger.ErrorType.LOW_RESOLUTION;
                        } else {
                            errorType2 = InstantSlowMoPlayLogger.ErrorType.HIGH_RESOLUTION;
                        }
                        errorType3 = errorType2;
                        Log.w("InstantSlowMoUtils", "not support [out of size spec] : " + max + "x" + min);
                    }
                } else {
                    if (Limitation.lowFrameRate(i2)) {
                        errorType = InstantSlowMoPlayLogger.ErrorType.LOW_FPS;
                    } else {
                        errorType = InstantSlowMoPlayLogger.ErrorType.HIGH_FPS;
                    }
                    errorType3 = errorType;
                    Log.w("InstantSlowMoUtils", "not support [out of frameRate spec] : " + i2);
                }
            }
        }
        return errorType3.toInt();
    }

    public static boolean checkInstantSlowMoSaveClipTitleShownPreference() {
        return PreferenceCache.InstantSlowMoSaveClipTipCount.incrementIf(new C0375j(25));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkInstantSlowMoSaveClipTitleShownPreference$0(Integer num) {
        if (num.intValue() < 3) {
            return true;
        }
        return false;
    }

    public static void setIntentInstantSlowMoExtra(Intent intent, MediaItem mediaItem) {
        long[] jArr;
        if (supportInstantSlowMoPlay(mediaItem, true) && (jArr = VideoPropData.of(mediaItem).instantSlowMoExecutedSection) != null) {
            VideoPropData.of(mediaItem).instantSlowMoExecutedSection = null;
            StringBuilder sb2 = new StringBuilder("instant slow-mo executed section to editor [");
            sb2.append(jArr[0]);
            sb2.append("-");
            Log.i("InstantSlowMoUtils", C0212a.o(sb2, jArr[1], "]"), Integer.valueOf(VideoPropData.getVideoDuration(mediaItem)));
            intent.putExtra("StartTimeOfSpeedSection", jArr[0]);
            intent.putExtra("EndTimeOfSpeedSection", jArr[1]);
            intent.putExtra("SpeedValue", 0.25f);
        }
    }

    public static boolean supportInstantSlowMoPlay(MediaItem mediaItem) {
        return supportInstantSlowMoPlay(mediaItem, false);
    }

    public static boolean supportLocation(String str) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO || LocationKey.isTrash(str) || LocationKey.isSuggestionViewList(str) || LocationKey.isColorCorrectView(str)) {
            return false;
        }
        return true;
    }

    private static boolean supportMotionPhoto(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_MOTION_PHOTO_INSTANT_SLOW_MO || !mediaItem.isMotionPhoto() || mediaItem.isSharing() || MediaItemMde.isDownloadMotionPhotoPath(mediaItem.getPath())) {
            return false;
        }
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE || MediaItemUtil.getMotionPhotoViewMode(mediaItem) == MotionPhotoViewMode.ON) {
            return true;
        }
        return false;
    }

    public static boolean supportMotionPhotoFpsScaleUp() {
        return Limitation.supportMotionPhotoFpsScaleUp();
    }

    public static boolean supportTips(String str) {
        if (!Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO_TIPS) || PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode) || LocationKey.isTrash(str) || LocationKey.isSuggestionViewList(str)) {
            return false;
        }
        return true;
    }

    private static boolean unsupportableItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        if ((mediaItem.isVideo() || supportMotionPhoto(mediaItem)) && !TrashData.isTrash(mediaItem) && !VideoPropData.of(mediaItem).longExposure && !MediaItemUtil.isHighLightClip(mediaItem) && !MediaItemUtil.isSuggestedEditVideo(mediaItem) && !MediaItemUtil.isMotionPhotoAutoPlayViewMode(mediaItem) && mediaItem.getRecordingMode() != 29) {
            return false;
        }
        return true;
    }

    public static void updateInstantSlowMoOptionsTipShown(Blackboard blackboard) {
        PreferenceCache preferenceCache;
        if (Features.isEnabled(Features.SUPPORT_INSTANT_SLOW_MO_ON_SHARE_SHEET)) {
            preferenceCache = PreferenceCache.InstantSlowMoEditAndShareGuideCount;
        } else {
            preferenceCache = PreferenceCache.InstantSlowMoEditGuideCount;
        }
        preferenceCache.incrementAndGet();
        if (blackboard != null) {
            blackboard.publish("instant_slow_mo_option_menu_tip_shown", Boolean.TRUE);
        }
    }

    public static void updateInstantSlowMoTipShown(Blackboard blackboard) {
        PreferenceCache.InstantSlowMoGuideCount.incrementAndGet();
        if (blackboard != null) {
            blackboard.publish("instant_slow_mo_tip_shown", Boolean.TRUE);
        }
    }

    public static boolean supportInstantSlowMoPlay(MediaItem mediaItem, boolean z) {
        return checkInstantSlowMoPlayable(mediaItem, z) == InstantSlowMoPlayLogger.ErrorType.NONE.toInt();
    }
}
