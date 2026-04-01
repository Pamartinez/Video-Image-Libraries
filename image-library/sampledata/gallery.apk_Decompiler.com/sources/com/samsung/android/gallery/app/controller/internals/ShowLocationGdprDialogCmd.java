package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShowLocationGdprDialogCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                boolean booleanValue = ((Boolean) arrayList.get(0)).booleanValue();
                SettingPreference.LocationAuth.setAndNotifyIfChanged(getContext(), booleanValue);
                if (booleanValue) {
                    MiscSettingPreference.AllowDataUsageForChn.setEnabled(true);
                    MarketHelper.setUpgradeCheckRequired(true);
                    getBlackboard().postEvent(EventMessage.obtain(1026));
                    Toast.makeText(getContext(), R.string.show_location_info_turned_on, 0).show();
                    return;
                }
                MiscSettingPreference miscSettingPreference = MiscSettingPreference.AllowDataUsageForChn;
                if (!miscSettingPreference.isEnabled()) {
                    miscSettingPreference.setEnabled(false);
                    MarketHelper.setUpgradeCheckRequired(false);
                }
            } catch (ClassCastException unused) {
                String str = this.TAG;
                Log.d(str, "data : " + arrayList);
                new InternalException("GdprDialogFail").post();
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/GDPRLocation").setOnDataCompleteListener(new a(28, this)).start();
    }
}
