package com.samsung.android.gallery.module.settings;

import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.scsp.media.api.constant.MediaApiContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarketUpgradeManager {
    private static volatile MarketUpgradeManager sInstance;
    private Integer mDeviceVersion;

    private int getDeviceVersion() {
        if (this.mDeviceVersion == null) {
            this.mDeviceVersion = Integer.valueOf(UnsafeCast.toInt(PackageMonitorCompat.getVersionCode(), 1));
        }
        return this.mDeviceVersion.intValue();
    }

    public static MarketUpgradeManager getInstance() {
        if (sInstance == null) {
            synchronized (MarketUpgradeManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new MarketUpgradeManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean isDeviceVersionLatest() {
        if (getDeviceVersion() >= MarketHelper.getApkVersion()) {
            return true;
        }
        return false;
    }

    private boolean isUpgradeCheckPossible() {
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || MarketHelper.isUpgradeCheckRequired()) {
            return true;
        }
        return false;
    }

    private boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    /* access modifiers changed from: private */
    public void onUpgradeCheckCompleted(int i2) {
        if (!MarketHelper.isApkAvailable()) {
            Blackboard.postEventGlobal(EventMessage.obtain(7007));
        } else if (i2 != 3 && i2 != 5) {
            Blackboard.postEventGlobal(EventMessage.obtain(7006));
        }
    }

    public void checkUpgrade() {
        String str;
        if (!isUpsm() && Features.isEnabled(Features.SUPPORT_GALAXY_APPS)) {
            if (System.currentTimeMillis() - MarketHelper.getLastCheckTime() <= MediaApiContract.DAY_IN_MILLI) {
                boolean isDeviceVersionLatest = isDeviceVersionLatest();
                StringBuilder sb2 = new StringBuilder("checkUpgrade {");
                if (isDeviceVersionLatest) {
                    str = "latest";
                } else {
                    str = MarketHelper.getApkVersionName();
                }
                sb2.append(str);
                sb2.append("}");
                Log.d("MarketUpgradeManager", sb2.toString());
                if (isDeviceVersionLatest) {
                    MarketHelper.setApkAvailable(false);
                    MarketHelper.setUpgradeState(-1);
                    return;
                }
                MarketHelper.setApkAvailable(true);
            } else if (isUpgradeCheckPossible()) {
                new UpgradeManager(new a(19, this)).execute(AppResources.getAppContext());
            } else {
                Log.d("MarketUpgradeManager", "checkUpgrade skip");
            }
        }
    }

    public boolean isUpgradeAvailable() {
        if (isUpsm() || !MarketHelper.isApkAvailable() || !MarketHelper.isUpgradeAvailable() || isDeviceVersionLatest()) {
            return false;
        }
        return true;
    }

    public boolean isUpgradeIgnored() {
        if (MarketHelper.getCheckedVersion() >= MarketHelper.getApkVersion()) {
            return true;
        }
        return false;
    }
}
