package com.samsung.android.gallery.support.library.v0;

import A5.a;
import N2.j;
import O3.o;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
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
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefFileGedImpl;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.library.v0.media.GedMediaPlayerCompat;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GedApiCompatImpl implements SeApiCompatible {
    static final String ANDROID;
    static final String EMULATED;
    private BoosterCompat mBoosterCompat;
    private volatile DisplayManagerCompat mDisplayManagerCompat;
    private final ConcurrentHashMap<String, Method> mPrivateApiMap = new ConcurrentHashMap<>();

    static {
        StringBuilder sb2 = new StringBuilder();
        String str = File.separator;
        ANDROID = j.f(sb2, str, "Android", str);
        EMULATED = str + "storage" + str + "emulated" + str;
    }

    private Method getPrivateApi(String str) {
        return this.mPrivateApiMap.computeIfAbsent(str, new a(22, this));
    }

    /* access modifiers changed from: private */
    public Method newPrivateApi(String str) {
        try {
            int hashCode = str.hashCode();
            if (hashCode != -1249359687) {
                if (hashCode != 102230) {
                    if (hashCode != 1101572082) {
                        return null;
                    }
                    if (str.equals("getBoolean")) {
                        return Reflector.getMethod(Class.forName("android.os.SystemProperties"), str, Boolean.TYPE);
                    }
                    return null;
                } else if (str.equals("get")) {
                    return Reflector.getMethod(Class.forName("android.os.SystemProperties"), str, String.class);
                } else {
                    return null;
                }
            } else if (str.equals("getInt")) {
                return Reflector.getMethod(Class.forName("android.os.SystemProperties"), str, Integer.TYPE);
            } else {
                return null;
            }
        } catch (Exception e) {
            j.D(e, j.k("newPrivateApi {", str, "} failed e="), "GedApiCompatImpl");
            return null;
        }
    }

    public MediaPlayerCompat createMediaPlayer(int i2) {
        return new GedMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecBgmAudioPlayer(int i2) {
        return new GedMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return new GedMediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new GedMediaPlayerCompat(i2);
    }

    public BoosterCompat getBoosterCompat(Context context) {
        if (this.mBoosterCompat == null) {
            this.mBoosterCompat = new BoosterCompat();
        }
        return this.mBoosterCompat;
    }

    public DisplayManagerCompat getDisplayManagerCompat(Context context) {
        if (this.mDisplayManagerCompat == null && context != null) {
            try {
                this.mDisplayManagerCompat = new DisplayManagerCompat(context);
            } catch (IllegalStateException e) {
                j.t(e, new StringBuilder("getDisplayManagerCompat failed e="), "GedApiCompatImpl");
            }
        }
        return this.mDisplayManagerCompat;
    }

    public DrmStoreCompat getDrmStoreCompat() {
        return new DrmStoreCompat();
    }

    public HoverStatusManagerCompat getHoverStatusManager(boolean z) {
        return new HoverStatusManagerCompat();
    }

    public HoverViewCompat getHoverViewCompat() {
        return new HoverViewCompat();
    }

    public KeepStorage getKeepStorage() {
        return new KeepStorage() {
        };
    }

    public MediaCaptureCompat getMediaCaptureCompat() {
        return new MediaCaptureCompat();
    }

    public MediaResourceHelperCompat getMediaResourceHelper() {
        return new MediaResourceHelperCompat();
    }

    public MediaTranscodeCompat getMediaTranscodeCompat() {
        return new MediaTranscodeCompat();
    }

    public String getMountState(Context context) {
        StorageManager storageManager;
        String str = "MountState ";
        if (!(context == null || (storageManager = (StorageManager) context.getSystemService("storage")) == null)) {
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            for (int i2 = 0; i2 < storageVolumes.size(); i2++) {
                StorageVolume storageVolume = storageVolumes.get(i2);
                str = str + "/[def] " + storageVolume + " : " + storageVolume.getState();
            }
        }
        return str;
    }

    public SefFileCompat getSefFileCompat() {
        return new SefFileGedImpl();
    }

    public List<StorageVolumeCompat> getStorageVolumes(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            try {
                loadStorageVolumesR(context, arrayList);
                return arrayList;
            } catch (Exception e) {
                j.C(e, new StringBuilder("getStorageVolumes e="), "GedApiCompatImpl");
                loadStorageVolumesPrimary(context, arrayList);
            }
        }
        return arrayList;
    }

    public String getSystemProperties(String str, String str2) {
        try {
            return (String) getPrivateApi("get").invoke((Object) null, new Object[]{str, str2});
        } catch (Exception e) {
            Log.w("GedApiCompatImpl", "get() e=" + e.getClass());
            return str2;
        }
    }

    public Bitmap getThumbnailHeif(String str, BitmapFactory.Options options) {
        try {
            byte[] thumbnail = new ExifInterface(str).getThumbnail();
            if (thumbnail == null || thumbnail.length <= 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.length);
        } catch (Error | Exception e) {
            Log.w("GedApiCompatImpl", "getThumbnailHeif failed. e=" + e.getMessage());
            return null;
        }
    }

    public Bitmap getVideoFrameAtTime(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return mediaMetadataRetriever.getFrameAtTime(j2, i2);
    }

    public boolean isActivityResumed(Activity activity) {
        return false;
    }

    public boolean isAfw(Context context) {
        return false;
    }

    public boolean isAutoRotateEnabled(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation") != 0) {
                return true;
            }
            return false;
        } catch (Settings.SettingNotFoundException e) {
            Log.e("GedApiCompatImpl", "Settings.SettingNotFoundException : " + e.toString());
            return true;
        }
    }

    public boolean isAutoRotateSecondEnabled(Context context) {
        return isAutoRotateEnabled(context);
    }

    public boolean isDualSecondScreen(Context context) {
        return false;
    }

    public boolean isFolded(Activity activity) {
        if (activity != null) {
            try {
                if (activity.getDisplay().getDisplayId() == 1) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean isFreeFormMode() {
        return false;
    }

    public boolean isScreenLocked(Context context) {
        KeyguardManager keyguardManager;
        if (context != null) {
            keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        } else {
            keyguardManager = null;
        }
        if (keyguardManager == null || !keyguardManager.isKeyguardLocked() || !keyguardManager.isKeyguardSecure()) {
            return false;
        }
        return true;
    }

    public boolean isSdcardMounted(Context context) {
        return false;
    }

    public void loadStorageVolumesPrimary(Context context, ArrayList<StorageVolumeCompat> arrayList) {
        try {
            StorageVolumeCompat storageVolumeCompat = new StorageVolumeCompat();
            storageVolumeCompat.directory = Environment.getExternalStorageDirectory().getPath();
            storageVolumeCompat.primary = true;
            storageVolumeCompat.removable = false;
            storageVolumeCompat.name = "external_primary";
            storageVolumeCompat.subSystem = "fuse";
            storageVolumeCompat.state = "mounted";
            arrayList.add(storageVolumeCompat);
        } catch (Exception e) {
            j.D(e, new StringBuilder("getStorageVolumes failed e="), "GedApiCompatImpl");
        }
    }

    public void loadStorageVolumesR(Context context, ArrayList<StorageVolumeCompat> arrayList) {
        List<StorageVolume> list;
        String str;
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        if (storageManager != null) {
            list = storageManager.getStorageVolumes();
        } else {
            list = null;
        }
        if (list != null) {
            for (StorageVolume next : list) {
                StorageVolumeCompat storageVolumeCompat = new StorageVolumeCompat();
                storageVolumeCompat.directory = (String) Optional.ofNullable(next.getDirectory()).map(new o(13)).orElse("");
                storageVolumeCompat.primary = next.isPrimary();
                boolean isRemovable = next.isRemovable();
                storageVolumeCompat.removable = isRemovable;
                if (!isRemovable || storageVolumeCompat.primary) {
                    str = "fuse";
                } else {
                    str = "sd";
                }
                storageVolumeCompat.subSystem = str;
                storageVolumeCompat.state = next.getState();
                storageVolumeCompat.name = next.getMediaStoreVolumeName();
                arrayList.add(storageVolumeCompat);
            }
        }
    }

    public void moveFilesForApp(Context context, Uri uri, int i2, int i7) {
    }

    public boolean requestDismissKeyguard(Activity activity, KeyguardManager.KeyguardDismissCallback keyguardDismissCallback) {
        KeyguardManager keyguardManager;
        if (activity != null) {
            keyguardManager = (KeyguardManager) activity.getSystemService("keyguard");
        } else {
            keyguardManager = null;
        }
        if (keyguardManager == null) {
            return false;
        }
        keyguardManager.requestDismissKeyguard(activity, keyguardDismissCallback);
        return true;
    }

    public boolean supportHeif() {
        return true;
    }

    public void moveFilesForApp(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i2) {
    }

    public int getSystemProperties(String str, int i2) {
        try {
            return ((Integer) getPrivateApi("getInt").invoke((Object) null, new Object[]{str, Integer.valueOf(i2)})).intValue();
        } catch (Exception e) {
            Log.w("GedApiCompatImpl", "getInt() e=" + e.getClass());
            return i2;
        }
    }

    public boolean getSystemProperties(String str, boolean z) {
        try {
            return ((Boolean) getPrivateApi("getBoolean").invoke((Object) null, new Object[]{str, Boolean.valueOf(z)})).booleanValue();
        } catch (Exception e) {
            Log.w("GedApiCompatImpl", "getBoolean() e=" + e.getClass());
            return z;
        }
    }

    public void convertActivityFromTranslucent(Activity activity) {
    }

    public void convertActivityToTranslucent(Activity activity, Activity.SemTranslucentConversionListener semTranslucentConversionListener) {
    }

    public boolean getCscFeatureBoolean(String str, boolean z) {
        return z;
    }

    public String getCscFeatureString(String str, String str2) {
        return str2;
    }
}
