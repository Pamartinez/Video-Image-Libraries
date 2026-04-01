package com.samsung.android.gallery.app.ui.tipcard;

import O3.y;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.module.settings.CmhFeatures;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineTipCardDelegate<V extends IBaseListView> extends BaseTipCardDelegate<V> {
    private final TipCardUpdateListener mListener;
    private TipCardCheckTask mTask;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TipCardCheckTask extends AsyncTask<Void, Void, TipCardView> {
        private final String mCurrentHeaderTag;
        private final WeakReference<TimelineTipCardDelegate<?>> mDelegate;

        public TipCardCheckTask(TimelineTipCardDelegate<?> timelineTipCardDelegate, String str) {
            this.mDelegate = new WeakReference<>(timelineTipCardDelegate);
            this.mCurrentHeaderTag = str;
        }

        public void recycle() {
            cancel(true);
            this.mDelegate.clear();
        }

        public TipCardView doInBackground(Void... voidArr) {
            TimelineTipCardDelegate timelineTipCardDelegate = this.mDelegate.get();
            if (timelineTipCardDelegate == null) {
                return null;
            }
            for (TipCardView next : timelineTipCardDelegate.createTipCardList()) {
                if (isCancelled()) {
                    return null;
                }
                try {
                    Context context = timelineTipCardDelegate.getView().getContext();
                    if (context != null && next.checkTipCard(context)) {
                        if (!next.getTag().equals(this.mCurrentHeaderTag)) {
                            return next;
                        }
                        cancel(true);
                        Log.d("checkTipCard(w) skip same", this.mCurrentHeaderTag);
                        return null;
                    }
                } catch (Exception e) {
                    Log.e((CharSequence) "TimelineTipCardDelegate", "checkTipCard(w) failed", (Throwable) e);
                }
            }
            return null;
        }

        public void onPostExecute(TipCardView tipCardView) {
            TimelineTipCardDelegate timelineTipCardDelegate = this.mDelegate.get();
            if (!isCancelled() && timelineTipCardDelegate != null) {
                Log.d("TimelineTipCardDelegate", "checkTipCard(ui)", Logger.getSimpleName((Object) tipCardView));
                try {
                    if (timelineTipCardDelegate.getView().getContext() != null) {
                        timelineTipCardDelegate.updateTipCard(tipCardView != null ? tipCardView.createTipCardView(timelineTipCardDelegate.getView()) : null);
                    }
                } catch (Exception unused) {
                    new InternalException("checkTipCard(ui) create failed " + tipCardView).post();
                }
            }
            this.mDelegate.clear();
        }
    }

    public TimelineTipCardDelegate(V v, TipCardUpdateListener tipCardUpdateListener) {
        super(v);
        this.mListener = tipCardUpdateListener;
    }

    /* access modifiers changed from: private */
    public void updateTipCard(View view) {
        TipCardUpdateListener tipCardUpdateListener = this.mListener;
        if (tipCardUpdateListener != null) {
            ((TimelineFragment) ((y) tipCardUpdateListener).e).updateTipCard(view);
        }
    }

    public List<TipCardView> createTipCardList() {
        boolean z;
        boolean z3;
        ArrayList arrayList = new ArrayList();
        if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
            arrayList.add(new SecMPPermissionTipCard());
        }
        if (Features.isEnabled(Features.SUPPORT_CMH_PROVIDER_PERMISSION)) {
            arrayList.add(new CmhProviderPermissionTipCard());
        }
        if (Features.isEnabled(Features.IS_UPSM) || !Features.isEnabled(Features.SUPPORT_CLOUD)) {
            z = false;
        } else {
            z = true;
        }
        if (z && Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            arrayList.add(new OneDriveEosTipCard());
        }
        if (z && SdkConfig.FirstApiLevel.LESS_THAN_P) {
            arrayList.add(new SCloudMigrationTipCard());
        }
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            arrayList.add(new SecMpMigrationTipCard());
        }
        if (z) {
            arrayList.add(new OneDriveSyncTipCard());
            arrayList.add(new OneDriveTurnOnSyncTipCard());
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.S)) {
            arrayList.add(new NoMediaTipCard());
        }
        if (Features.isEnabled(Features.SUPPORT_LOCATION) && Features.isEnabled(Features.SUPPORT_PLACE_GDPR) && Features.isEnabled(Features.SUPPORT_GDPR)) {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                z3 = Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE);
            } else {
                z3 = CmhFeatures.supportPoi();
            }
            if (z3) {
                arrayList.add(new PlaceGdprTipCard());
            }
        }
        if (z) {
            if (Features.isEnabled(Features.SUPPORT_ONE_DRIVE_PROMOTION)) {
                arrayList.add(new OneDrivePromotionTipCard());
            }
            arrayList.add(new OneDriveQuotaTipCard());
        }
        if (Features.isEnabled(Features.SUPPORT_SD_CARD_ERRORS_TIP_CARD)) {
            arrayList.add(new SdCardErrorsTipCard());
        }
        return arrayList;
    }

    public TipCardView getAndCheckTipCard(Context context, String str) {
        Log.d("TimelineTipCardDelegate", "checkTipCard", str);
        stopTask();
        TipCardCheckTask tipCardCheckTask = new TipCardCheckTask(this, str);
        this.mTask = tipCardCheckTask;
        try {
            tipCardCheckTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            return null;
        } catch (IllegalStateException | NullPointerException e) {
            Log.e("TimelineTipCardDelegate", e.getMessage());
            return null;
        }
    }

    public void release() {
        stopTask();
    }

    public void stopTask() {
        try {
            TipCardCheckTask tipCardCheckTask = this.mTask;
            if (tipCardCheckTask != null) {
                tipCardCheckTask.recycle();
                this.mTask = null;
            }
        } catch (Exception e) {
            Log.e("TimelineTipCardDelegate", e.getMessage());
        }
    }
}
