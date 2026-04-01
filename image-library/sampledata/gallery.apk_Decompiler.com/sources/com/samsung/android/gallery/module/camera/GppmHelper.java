package com.samsung.android.gallery.module.camera;

import E5.b;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GppmHelper {
    public static final boolean SUPPORT_DONATION;
    public static final boolean SUPPORT_DONATION_MULTIPLE;

    static {
        boolean z;
        boolean z3 = false;
        if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.isEnabled(Features.IS_SEP_LOW_SEGMENT)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT_DONATION = z;
        if (z && supportMultiDonation()) {
            z3 = true;
        }
        SUPPORT_DONATION_MULTIPLE = z3;
    }

    public static void donate(FileItemInterface fileItemInterface) {
        if (SUPPORT_DONATION) {
            GppmApi.donate(fileItemInterface.getFileId());
        }
    }

    public static boolean isDonationEnabled() {
        if (SUPPORT_DONATION) {
            return GppmApi.isEnabled();
        }
        return false;
    }

    public static void setDonationEnabled(boolean z) {
        if (SUPPORT_DONATION) {
            GppmApi.setEnabled(z);
        }
    }

    public static boolean supportMultiDonation() {
        boolean z;
        long packageVersion = PackageMonitorCompat.getInstance().getPackageVersion("com.samsung.android.globalpostprocmgr");
        Long valueOf = Long.valueOf(packageVersion);
        int i2 = (packageVersion > 120800000 ? 1 : (packageVersion == 120800000 ? 0 : -1));
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        Log.d("GPPM", "multi-donation", valueOf, Boolean.valueOf(z));
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public static void donate(Collection<FileItemInterface> collection) {
        if (SUPPORT_DONATION_MULTIPLE) {
            GppmApi.donate(collection);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GppmApi {
        private static final String KEY;
        private static final Uri URI = Uri.parse("content://com.samsung.provider.gppm/ppapp_info");
        private static volatile boolean sEnabled;
        private static volatile long sLastId;
        private static volatile boolean sMultipleEnabled = sEnabled;

        static {
            String str = "GPPM:" + SeApiCompat.version;
            KEY = str;
            sEnabled = GalleryPreference.getInstance().loadBoolean(str, true);
        }

        public static void donate(long j2) {
            if (sEnabled && sLastId != j2) {
                sLastId = j2;
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    AppResources.getAppContext().getContentResolver().call(URI, "add_media_post_processing", String.valueOf(j2), (Bundle) null);
                    Log.d("GPPM", "donate" + Logger.vt(Long.valueOf(j2), Long.valueOf(currentTimeMillis)));
                } catch (IllegalArgumentException | SecurityException | UnsupportedOperationException e) {
                    String str = "donate failed. e=" + e.getClass().getSimpleName() + "{" + e.getMessage() + "}";
                    Log.w("GPPM", str);
                    DebugLogger.getInstance().lambda$apply$0("GPPM", str);
                    setEnabled(false);
                } catch (Error | Exception e7) {
                    Log.e("GPPM", "donate failed(temporary). e=" + e7.getClass().getSimpleName() + "{" + e7.getMessage() + "}");
                    sEnabled = false;
                }
            }
        }

        public static synchronized boolean isEnabled() {
            boolean z;
            synchronized (GppmApi.class) {
                z = sEnabled;
            }
            return z;
        }

        public static synchronized void setEnabled(boolean z) {
            synchronized (GppmApi.class) {
                try {
                    sEnabled = z;
                    if (z) {
                        GalleryPreference.getInstance().removeState(KEY);
                    } else {
                        GalleryPreference.getInstance().saveState(KEY, false);
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        public static void donate(Collection<FileItemInterface> collection) {
            if (sEnabled && sMultipleEnabled) {
                donate(collection.stream().mapToLong(new b(2)).toArray());
            }
        }

        public static void donate(long[] jArr) {
            if (sEnabled && sMultipleEnabled) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    Bundle bundle = new Bundle();
                    bundle.putLongArray("sec_mp_ids", jArr);
                    AppResources.getAppContext().getContentResolver().call(URI, "add_media_post_processing", (String) null, bundle);
                    StringBuilder sb2 = new StringBuilder("donate");
                    sb2.append(Logger.vt("#" + jArr.length, StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, Arrays.stream(jArr).limit(5).iterator()), Long.valueOf(currentTimeMillis)));
                    Log.d("GPPM", sb2.toString());
                } catch (IllegalArgumentException | SecurityException | UnsupportedOperationException e) {
                    String str = "donate failed(m). e=" + e.getClass().getSimpleName() + "{" + e.getMessage() + "}";
                    Log.w("GPPM", str);
                    DebugLogger.getInstance().lambda$apply$0("GPPM", str);
                    sMultipleEnabled = false;
                } catch (Error | Exception e7) {
                    Log.e("GPPM", "donate failed(m,temporary). e=" + e7.getClass().getSimpleName() + "{" + e7.getMessage() + "}");
                    sMultipleEnabled = false;
                }
            }
        }
    }
}
