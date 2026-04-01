package com.samsung.android.gallery.support.library.v0;

import N2.j;
import Qa.a;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.os.SemSystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.samsung.android.app.SemMultiWindowManager;
import com.samsung.android.content.clipboard.SemClipboardManager;
import com.samsung.android.feature.SemCscFeature;
import com.samsung.android.feature.SemFloatingFeature;
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
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefFileSemImpl;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.library.v0.display.SemDisplayManagerCompat;
import com.samsung.android.gallery.support.library.v0.hover.SemHoverStatusManagerCompat;
import com.samsung.android.gallery.support.library.v0.hover.SemHoverViewCompat;
import com.samsung.android.gallery.support.library.v0.media.SemMediaPlayerCompat;
import com.samsung.android.gallery.support.library.v0.media.SemMediaResourceHelperCompat;
import com.samsung.android.gallery.support.library.v0.media.SemMediaTranscodeCompat;
import com.samsung.android.gallery.support.library.v0.media.SemVideoTranscoderCompat;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v0.system.SemBoosterCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.knox.SemPersonaManager;
import com.samsung.android.knox.SemRemoteContentManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem80ApiCompatImpl implements SeApiCompatible {
    protected final String TAG = getClass().getSimpleName();
    protected final ConcurrentHashMap<String, DexInfo> mDexMap = new ConcurrentHashMap<>();
    private volatile DisplayManagerCompat mDisplayManagerCompat;
    private KeyguardManager mKeyguardManager;
    private Bundle mKnoxInfo;
    private PowerManager mPowerManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LegacyMethod {
        static final Method semSetAutoBrightnessLimit;

        static {
            Class cls = Integer.TYPE;
            semSetAutoBrightnessLimit = Reflector.getMethod(PowerManager.class, "semSetAutoBrightnessLimit", cls, cls);
        }
    }

    private DexInfo getDexInfo(Context context) {
        return this.mDexMap.computeIfAbsent(context.toString(), new a(0, (Object) this, (Object) context));
    }

    private KeyguardManager getKeyguardManager(Context context) {
        KeyguardManager keyguardManager;
        if (this.mKeyguardManager == null) {
            if (context != null) {
                keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            } else {
                keyguardManager = null;
            }
            this.mKeyguardManager = keyguardManager;
        }
        return this.mKeyguardManager;
    }

    private Bundle getKnoxInfoForApp(Context context) {
        if (this.mKnoxInfo == null) {
            this.mKnoxInfo = SemPersonaManager.getKnoxInfoForApp(context);
        }
        return this.mKnoxInfo;
    }

    private boolean isAfwForByod(Context context) {
        try {
            UserManager userManager = (UserManager) context.getSystemService("user");
            if (userManager != null && userManager.getUserCount() > 1) {
                List<UserHandle> userProfiles = userManager.getUserProfiles();
                if (getUserHandle(0).equals(Process.myUserHandle()) || userProfiles == null || userProfiles.size() <= 1) {
                    return false;
                }
                return true;
            }
        } catch (SecurityException e) {
            String str = this.TAG;
            Log.e(str, "isAfwForByd failed. e=" + e.getMessage());
        }
        return false;
    }

    private boolean isAfwForCl(Context context) {
        List<ComponentName> list;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager != null) {
            list = devicePolicyManager.getActiveAdmins();
        } else {
            list = null;
        }
        if (list == null) {
            return false;
        }
        for (ComponentName packageName : list) {
            if (devicePolicyManager.isDeviceOwnerApp(packageName.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ DexInfo lambda$getDexInfo$3(Context context, String str) {
        return computeDexInfo(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getMountState$2(Context context, StorageVolume storageVolume) {
        return "[" + storageVolume.getDescription(context) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + storageVolume.getUuid() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + storageVolume.getState() + "]";
    }

    public void announceVoiceAssistant(Context context, String str) {
        try {
            Trace.beginSection("announceVoiceAssistant");
            long currentTimeMillis = System.currentTimeMillis();
            Context applicationContext = context.getApplicationContext();
            AccessibilityManager accessibilityManager = (AccessibilityManager) applicationContext.getSystemService("accessibility");
            if (accessibilityManager != null && accessibilityManager.isEnabled() && isVoiceServiceEnabled(applicationContext)) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
                obtain.getText().clear();
                obtain.getText().add(str);
                obtain.setPackageName("com.sec.android.gallery3d");
                accessibilityManager.sendAccessibilityEvent(obtain);
                String str2 = this.TAG;
                Log.i(str2, "announceVoiceAssistant {***} +" + (System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Error | Exception e) {
            String str3 = this.TAG;
            Log.w(str3, "announceVoiceAssistant failed e=" + e.getMessage());
        } finally {
            Trace.endSection();
        }
    }

    public void clearDexMode(Context context) {
        if (context == null) {
            this.mDexMap.clear();
        } else {
            this.mDexMap.remove(context.toString());
        }
    }

    public DexInfo computeDexInfo(Context context) {
        return new DexInfo().compute(context);
    }

    public void convertActivityFromTranslucent(Activity activity) {
        activity.semConvertFromTranslucent(false);
    }

    public void convertActivityToTranslucent(Activity activity, Activity.SemTranslucentConversionListener semTranslucentConversionListener) {
        activity.semConvertToTranslucent(semTranslucentConversionListener);
    }

    public SemDisplayManagerCompat createDisplayManagerCompat(Context context) {
        return new SemDisplayManagerCompat(context);
    }

    public MediaPlayerCompat createMediaPlayer(int i2) {
        return new SemMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecBgmAudioPlayer(int i2) {
        return new SemMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return new SemMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new SemMediaPlayerCompat(i2);
    }

    public VideoTranscoderCompat createVideoTranscoderCompat() {
        return new SemVideoTranscoderCompat();
    }

    public String getAudioFocusedPackageName(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return "";
        }
        try {
            return audioManager.semGetAudioFocusedPackageName();
        } catch (Exception e) {
            j.C(e, new StringBuilder("hasAudioFocus failed. e="), this.TAG);
            return "";
        }
    }

    public BoosterCompat getBoosterCompat(Context context) {
        return SemBoosterCompat.getInstance(context);
    }

    public boolean getCscFeatureBoolean(String str, boolean z) {
        return SemCscFeature.getInstance().getBoolean(str, z);
    }

    public String getCscFeatureString(String str, String str2) {
        return SemCscFeature.getInstance().getString(str, str2);
    }

    public DisplayManagerCompat getDisplayManagerCompat(Context context) {
        if (this.mDisplayManagerCompat == null && context != null) {
            try {
                this.mDisplayManagerCompat = createDisplayManagerCompat(context);
            } catch (Exception e) {
                Log.e(this.TAG, "getDisplayManagerCompat failed", e);
            }
        }
        return this.mDisplayManagerCompat;
    }

    public DrmStoreCompat getDrmStoreCompat() {
        return new DrmStoreCompat();
    }

    public boolean getFloatingFeatureBoolean(String str) {
        return SemFloatingFeature.getInstance().getBoolean(str);
    }

    public int getFloatingFeatureInt(String str) {
        return SemFloatingFeature.getInstance().getInt(str);
    }

    public String getFloatingFeatureString(String str) {
        return SemFloatingFeature.getInstance().getString(str);
    }

    public HoverStatusManagerCompat getHoverStatusManager(boolean z) {
        return new SemHoverStatusManagerCompat(z);
    }

    public HoverViewCompat getHoverViewCompat() {
        return new SemHoverViewCompat();
    }

    public KeepStorage getKeepStorage() {
        return new KeepStorage() {
        };
    }

    public String getKnoxContainerLabel(Context context) {
        try {
            Bundle knoxInfoForApp = getKnoxInfoForApp(context, "getContainerLabel");
            if (knoxInfoForApp != null) {
                return knoxInfoForApp.getString("getContainerLabel");
            }
            return null;
        } catch (Error | Exception e) {
            String str = this.TAG;
            Log.w(str, "getKnoxContainerLabel failed. e=" + e.getMessage());
            return null;
        }
    }

    public MediaCaptureCompat getMediaCaptureCompat() {
        return new MediaCaptureCompat();
    }

    public MediaResourceHelperCompat getMediaResourceHelper() {
        return new SemMediaResourceHelperCompat();
    }

    public MediaTranscodeCompat getMediaTranscodeCompat() {
        return new SemMediaTranscodeCompat();
    }

    public String getMountState(Context context) {
        List<StorageVolume> list;
        if (context == null || isProfileNotSupportSdCard(context)) {
            return "MountState{null}";
        }
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            if (storageManager != null) {
                list = storageManager.getStorageVolumes();
            } else {
                list = null;
            }
            if (list == null || list.size() <= 0) {
                return "MountState{null}";
            }
            return "MountState{" + ((String) list.stream().filter(new M4.j(24)).map(new G9.a(context, 1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX))) + "}";
        } catch (Exception unused) {
            return "MountState{null}";
        }
    }

    public ArrayList<Bundle> getMoveToKnoxMenuList(Context context) {
        try {
            return ((SemPersonaManager) context.getSystemService("persona")).getMoveToKnoxMenuList(context);
        } catch (IllegalArgumentException | NullPointerException e) {
            Log.e(this.TAG, "getMoveToKnoxMenuList failed", e);
            return null;
        }
    }

    public int getMyUserId() {
        return UserHandle.semGetMyUserId();
    }

    public final PowerManager getPowerManagerService(Context context) {
        if (this.mPowerManager == null) {
            this.mPowerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        }
        return this.mPowerManager;
    }

    public String getPrefixForSpan(TextView textView, CharSequence charSequence, String str) {
        try {
            char[] semGetPrefixCharForSpan = TextUtils.semGetPrefixCharForSpan(textView.getPaint(), charSequence, str.toCharArray());
            if (semGetPrefixCharForSpan != null) {
                return new String(semGetPrefixCharForSpan);
            }
            return null;
        } catch (Exception e) {
            j.C(e, new StringBuilder("getPrefixForSpan failed e="), this.TAG);
            return null;
        }
    }

    public SefFileCompat getSefFileCompat() {
        return new SefFileSemImpl();
    }

    public final SemClipboardManager getSemClipboardManager(Context context) {
        return (SemClipboardManager) context.getSystemService("semclipboard");
    }

    public int getSettingsGlobalInt(Context context, String str, int i2) {
        if (!(context == null || str == null)) {
            try {
                return Settings.Global.getInt(context.getContentResolver(), str, i2);
            } catch (Exception e) {
                j.C(e, new StringBuilder("getSettingsGlobalInt failed e="), this.TAG);
            }
        }
        return i2;
    }

    public int getSettingsSystemInt(Context context, String str, int i2, boolean z) {
        try {
            return Settings.System.getInt(context.getContentResolver(), str, i2);
        } catch (Exception e) {
            j.C(e, j.k("getSettingsSystemInt[", str, "] failed e="), this.TAG);
            return i2;
        }
    }

    public List<StorageVolumeCompat> getStorageVolumes(Context context) {
        List<StorageVolume> list;
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            try {
                StorageManager storageManager = (StorageManager) context.getSystemService("storage");
                if (storageManager != null) {
                    list = storageManager.getStorageVolumes();
                } else {
                    list = null;
                }
                if (list != null) {
                    for (StorageVolume storageVolumeCompat : list) {
                        arrayList.add(toStorageVolumeCompat(storageVolumeCompat));
                    }
                }
            } catch (Exception e) {
                j.C(e, new StringBuilder("getStorageVolumes e="), this.TAG);
            }
        }
        return arrayList;
    }

    public String getSystemProperties(String str, String str2) {
        return SemSystemProperties.get(str, str2);
    }

    public UserHandle getUserHandle(int i2) {
        if (i2 == 0) {
            return UserHandle.SEM_OWNER;
        }
        if (i2 == -1) {
            return UserHandle.SEM_ALL;
        }
        return UserHandle.SEM_CURRENT;
    }

    public Bitmap getVideoFrameAtTime(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return (Bitmap) Reflector.invoke(MediaMetadataRetriever.class, mediaMetadataRetriever, "semGetFrameAtTime", Long.valueOf(j2), Integer.valueOf(i2), 1);
    }

    public boolean isAccessoryKeyboardState(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager == null || !inputMethodManager.semIsAccessoryKeyboard()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isAccessoryKeyboardState failed e="), this.TAG);
            return false;
        }
    }

    public boolean isActivityResumed(Activity activity) {
        return activity.semIsResumed();
    }

    public boolean isAfw(Context context) {
        if (isAfwForByod(context) || isAfwForCl(context)) {
            return true;
        }
        return false;
    }

    public boolean isAutoRotateEnabled(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation") != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isAutoRotateEnabled failed e="), this.TAG);
            return true;
        }
    }

    public boolean isAutoRotateSecondEnabled(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation_second") != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isAutoRotateSecondEnabled failed e="), this.TAG);
            return true;
        }
    }

    public boolean isBrightnessModeAutomatic(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode", -1) == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isBrightnessModeAutomatic failed e="), this.TAG);
            return false;
        }
    }

    public boolean isClipboardEnabled(Context context) {
        try {
            return getSemClipboardManager(context).isEnabled();
        } catch (Exception e) {
            j.C(e, new StringBuilder("isClipboardEnabled failed e="), this.TAG);
            return false;
        }
    }

    public boolean isClipboardShowing(Context context) {
        try {
            return getSemClipboardManager(context).isShowing();
        } catch (Exception e) {
            j.C(e, new StringBuilder("isClipboardShowing failed e="), this.TAG);
            return false;
        }
    }

    public boolean isDexMode(Context context) {
        return getDexInfo(context).isEnabled();
    }

    public boolean isDexOnPc(Context context) {
        return getDexInfo(context).isDexOnPc(context);
    }

    public boolean isDexStandAloneMode(Context context) {
        return getDexInfo(context).isStandAlone();
    }

    public boolean isDualSecondScreen(Context context) {
        if (context.getResources().getConfiguration().hardKeyboardHidden == 1) {
            return true;
        }
        return false;
    }

    public boolean isFreeFormMode() {
        if ((new SemMultiWindowManager().getMode() & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean isKnoxMode(Context context) {
        boolean z;
        int i2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle knoxInfoForApp = getKnoxInfoForApp(context);
            if (knoxInfoForApp == null || !SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(knoxInfoForApp.getString("isKnoxMode"))) {
                z = false;
            } else {
                z = true;
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("isKnoxMode {");
            sb2.append(z);
            sb2.append(',');
            if (knoxInfoForApp != null) {
                i2 = knoxInfoForApp.getInt("userId");
            } else {
                i2 = -1;
            }
            sb2.append(i2);
            sb2.append("} +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d(str, sb2.toString());
            return z;
        } catch (Error | Exception e) {
            Log.w(this.TAG, "isKnoxMode failed. e=" + e.getMessage());
            return false;
        }
    }

    public boolean isMobileKeyboardCovered(Context context) {
        if (context.getResources().getConfiguration().semMobileKeyboardCovered == 1) {
            return true;
        }
        return false;
    }

    public boolean isMyUserIdAsUserCurrent() {
        if (UserHandle.semGetMyUserId() == -2) {
            return true;
        }
        return false;
    }

    public boolean isMyUserIdAsUserOwner() {
        if (UserHandle.semGetMyUserId() == 0) {
            return true;
        }
        return false;
    }

    public boolean isOnSecureFolder(Context context) {
        try {
            Bundle knoxInfoForApp = getKnoxInfoForApp(context);
            if (knoxInfoForApp == null || !SemPersonaManager.isSecureFolderId(knoxInfoForApp.getInt("userId"))) {
                return false;
            }
            return true;
        } catch (Error | Exception e) {
            String str = this.TAG;
            Log.w(str, "isOnSecureFolder failed. e=" + e.getMessage());
            return false;
        }
    }

    public boolean isProfileNotSupportSdCard(Context context) {
        try {
            UserManager userManager = (UserManager) context.getSystemService("user");
            if (userManager == null || !userManager.semIsManagedProfile()) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            String str = this.TAG;
            Log.w(str, "isProfileNotSupportSdCard failed e=" + e.getMessage());
            return false;
        }
    }

    public boolean isScreenLocked(Context context) {
        KeyguardManager keyguardManager = getKeyguardManager(context.getApplicationContext());
        if (keyguardManager == null || !keyguardManager.isKeyguardLocked() || !keyguardManager.isKeyguardSecure()) {
            return false;
        }
        return true;
    }

    public boolean isSdcardMounted(Context context) {
        StorageManager storageManager;
        if (context == null || isProfileNotSupportSdCard(context) || (storageManager = (StorageManager) context.getSystemService("storage")) == null) {
            return false;
        }
        for (StorageVolume next : storageManager.getStorageVolumes()) {
            if ("sd".equals(next.semGetSubSystem())) {
                return "mounted".equals(next.getState());
            }
        }
        return false;
    }

    public boolean isSharedAlbumBlocked(Context context) {
        if (context != null) {
            try {
                if (Settings.Secure.getInt(context.getContentResolver(), "rampart_blocked_shared_album_gallery", 0) == 1) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean isShopDemoMode(Context context) {
        if (context != null) {
            try {
                if (Settings.Secure.getInt(context.getContentResolver(), "shopdemo", 0) == 1) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean isUpsm(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "ultra_powersaving_mode", 0) == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isUpsm failed e="), this.TAG);
            return false;
        }
    }

    public boolean isVoiceServiceEnabled(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
            if (string == null || !string.contains("com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService")) {
                return false;
            }
            return true;
        } catch (Error | Exception e) {
            String str = this.TAG;
            Log.w(str, "isVoiceServiceEnabled failed e=" + e.getMessage());
            return false;
        }
    }

    public boolean minimizeSoftInput(Context context, IBinder iBinder, int i2) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager == null || !inputMethodManager.semMinimizeSoftInput(iBinder, i2)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("minimizeSoftInput failed e="), this.TAG);
            return false;
        }
    }

    public void moveFilesForApp(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i2) {
        try {
            ((SemRemoteContentManager) context.getSystemService("rcp")).moveFiles(1, arrayList, arrayList2, i2);
        } catch (RemoteException | NullPointerException e) {
            j.D(e, new StringBuilder("moveFilesForApp e="), this.TAG);
        }
    }

    public boolean requestDismissKeyguard(Activity activity, KeyguardManager.KeyguardDismissCallback keyguardDismissCallback) {
        boolean z;
        KeyguardManager keyguardManager = getKeyguardManager(activity.getApplicationContext());
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("requestDismissKeyguard ");
        if (keyguardManager != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        Log.i(str, sb2.toString());
        if (keyguardManager == null) {
            return false;
        }
        keyguardManager.requestDismissKeyguard(activity, keyguardDismissCallback);
        return true;
    }

    public boolean setAutoBrightnessLimit(Context context, int i2, int i7) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            PowerManager powerManagerService = getPowerManagerService(context);
            if (powerManagerService == null) {
                return false;
            }
            LegacyMethod.semSetAutoBrightnessLimit.invoke(powerManagerService, new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)});
            String str = this.TAG;
            Log.i(str, "setAutoBrightnessLimit {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("setAutoBrightnessLimit failed e="), this.TAG);
            return false;
        }
    }

    public void setLaunchOverTargetTask(Intent intent, int i2, boolean z) {
        if (Build.VERSION.SEM_INT < 2802) {
            Reflector.invoke(Intent.class, intent, "semSetLaunchOverTargetTask", Integer.valueOf(i2), Boolean.valueOf(z));
            return;
        }
        intent.semSetLaunchOverTargetTask(i2, z);
    }

    public void setMarginsRelative(View view, int i2, int i7, int i8, int i10) {
        try {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).semSetMarginsRelative(i2, i7, i8, i10);
        } catch (Exception unused) {
        }
    }

    public void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7) {
        mediaMetadataRetriever.semSetVideoSize(i2, i7, false, true);
    }

    public void showClipboardDialog(Context context) {
        try {
            getSemClipboardManager(context).showDialog();
        } catch (Exception e) {
            j.C(e, new StringBuilder("showClipboardDialog failed e="), this.TAG);
        }
    }

    public boolean supportHeif() {
        return false;
    }

    public boolean supportSetVideoSize() {
        return true;
    }

    public StorageVolumeCompat toStorageVolumeCompat(StorageVolume storageVolume) {
        StorageVolumeCompat storageVolumeCompat = new StorageVolumeCompat();
        storageVolumeCompat.directory = storageVolume.semGetPath();
        storageVolumeCompat.subSystem = storageVolume.semGetSubSystem();
        storageVolumeCompat.state = storageVolume.getState();
        storageVolumeCompat.primary = storageVolume.isPrimary();
        storageVolumeCompat.removable = storageVolume.isRemovable();
        storageVolumeCompat.name = storageVolume.getMediaStoreVolumeName();
        return storageVolumeCompat;
    }

    public int getSystemProperties(String str, int i2) {
        return SemSystemProperties.getInt(str, i2);
    }

    public void setVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2, int i7, boolean z) {
        mediaMetadataRetriever.semSetVideoSize(i2, i7, z, true);
    }

    public boolean getSystemProperties(String str, boolean z) {
        return SemSystemProperties.getBoolean(str, z);
    }

    private Bundle getKnoxInfoForApp(Context context, String str) {
        return SemPersonaManager.getKnoxInfoForApp(context, str);
    }

    public void moveFilesForApp(Context context, Uri uri, int i2, int i7) {
        try {
            ((SemRemoteContentManager) context.getSystemService("rcp")).moveFiles(1, uri, i2, i7);
        } catch (RemoteException | NullPointerException e) {
            j.D(e, new StringBuilder("moveFilesForApp e="), this.TAG);
        }
    }
}
