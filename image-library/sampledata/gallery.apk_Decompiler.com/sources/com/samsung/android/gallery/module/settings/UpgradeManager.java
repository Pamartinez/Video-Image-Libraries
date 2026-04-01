package com.samsung.android.gallery.module.settings;

import A.a;
import android.content.Context;
import android.os.AsyncTask;
import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpgradeManager {
    private final UpgradeTask mTask;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnUpdateCheckListener {
        void onUpdateCheckCompleted(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpgradeTask extends AsyncTask<Context, Void, Integer> {
        private final OnUpdateCheckListener mListener;

        public UpgradeTask(OnUpdateCheckListener onUpdateCheckListener) {
            this.mListener = onUpdateCheckListener;
        }

        public Integer doInBackground(Context... contextArr) {
            Context context = contextArr[0];
            MarketHelper marketHelper = new MarketHelper();
            int upgradeInfo = marketHelper.getUpgradeInfo(context);
            int i2 = UnsafeCast.toInt(PackageMonitorCompat.getVersionCode(), 1);
            int i7 = UnsafeCast.toInt(marketHelper.getVersionCode(), 0);
            StringBuilder h5 = a.h(i2, i7, "UpgradeTask {device=", ", market=", ", name=");
            h5.append(marketHelper.getVersionName());
            h5.append(", result=");
            h5.append(upgradeInfo);
            h5.append("}");
            Log.i("UpgradeManager", h5.toString());
            MarketHelper.setLastCheckTime(System.currentTimeMillis());
            if (i2 >= i7) {
                MarketHelper.setApkAvailable(false);
                MarketHelper.setApkVersionInfo(i2, (String) null);
            } else {
                MarketHelper.setApkAvailable(true);
                MarketHelper.setApkVersionInfo(i7, marketHelper.getVersionName());
            }
            MarketHelper.setUpgradeState(upgradeInfo);
            return Integer.valueOf(upgradeInfo);
        }

        public void onCancelled() {
            super.onCancelled();
            MarketHelper.setUpgradeState(3);
        }

        public void onPostExecute(Integer num) {
            OnUpdateCheckListener onUpdateCheckListener = this.mListener;
            if (onUpdateCheckListener != null) {
                onUpdateCheckListener.onUpdateCheckCompleted(num.intValue());
            }
        }

        public void onCancelled(Integer num) {
            super.onCancelled(num);
            MarketHelper.setUpgradeState(3);
        }
    }

    public UpgradeManager(OnUpdateCheckListener onUpdateCheckListener) {
        this.mTask = new UpgradeTask(onUpdateCheckListener);
    }

    public void execute(Context context) {
        this.mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Context[]{context});
    }
}
