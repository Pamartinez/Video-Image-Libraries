package com.samsung.android.gallery.app.controller.trash;

import A.a;
import Qb.e;
import U5.c;
import U9.b;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LaunchTrashBinCmd extends BaseCommand {
    private boolean mChildOfListContainer;
    private String mTrashLocationKey = "location://trash";

    private void checkFamilyAlbumTrash(Consumer<Boolean> consumer) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !MdeSharingService.getInstance().isServiceSupported()) {
            consumer.accept(Boolean.FALSE);
        } else {
            SimpleThreadPool.getInstance().execute(new c(3, this, consumer));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkFamilyAlbumTrash$1(Consumer consumer) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean hasFamilySharedAlbum = new MdeDatabase().hasFamilySharedAlbum();
        String str = this.TAG;
        a.A(new Object[]{Boolean.valueOf(hasFamilySharedAlbum), Long.valueOf(currentTimeMillis)}, new StringBuilder("checkFamilyAlbumTrash"), str);
        if (hasFamilySharedAlbum) {
            this.mTrashLocationKey = ArgumentsUtil.appendArgs("location://trash", "has_family_album", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        consumer.accept(Boolean.valueOf(hasFamilySharedAlbum));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchTrashBin$0() {
        startTurnOnTrashDialog(getHandler());
    }

    /* access modifiers changed from: private */
    public void launchTrashBin(boolean z) {
        if (z || PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            moveToTrash();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new e(29, this), 100);
        }
    }

    private void moveToTrash() {
        Blackboard blackboard = getBlackboard();
        try {
            blackboard.erase(DataKey.DATA_CURSOR("location://trash"));
            blackboard.erase(CommandKey.DATA_REQUEST("location://trash"));
            if (!this.mChildOfListContainer || !DrawerUtil.supportDrawerLayout(blackboard) || !LocationKey.isRootOfContainerExceptTab(this.mTrashLocationKey)) {
                blackboard.post("command://MoveURL", this.mTrashLocationKey);
            } else {
                blackboard.postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 0, 0, this.mTrashLocationKey));
            }
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "unable to remove previous data. " + e.getMessage());
            if (!this.mChildOfListContainer || !DrawerUtil.supportDrawerLayout(blackboard) || !LocationKey.isRootOfContainerExceptTab(this.mTrashLocationKey)) {
                blackboard.post("command://MoveURL", this.mTrashLocationKey);
            } else {
                blackboard.postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 0, 0, this.mTrashLocationKey));
            }
        } catch (Throwable th) {
            if (!this.mChildOfListContainer || !DrawerUtil.supportDrawerLayout(blackboard) || !LocationKey.isRootOfContainerExceptTab(this.mTrashLocationKey)) {
                blackboard.post("command://MoveURL", this.mTrashLocationKey);
            } else {
                blackboard.postEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 0, 0, this.mTrashLocationKey));
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void onTurnOnTrash(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            try {
                i2 = ((Integer) arrayList.get(0)).intValue();
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "unexpected result delivered." + e.toString());
                return;
            }
        } else {
            i2 = -1;
        }
        if (i2 == 1) {
            turnOnTrash();
        } else {
            Log.e(this.TAG, "cancel or unexpected option selected.");
        }
    }

    private void startTurnOnTrashDialog(EventContext eventContext) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/TurnOnTrash").build()).setOnDataCompleteListener(new U3.a(9, this)).start();
    }

    private void turnOnTrash() {
        SettingPreference.Trash.setAndNotifyIfChanged(getContext(), true);
        getBlackboard().post("command://MoveURL", this.mTrashLocationKey);
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.insertLog("[" + TrashLogType.TRASH_ENABLE + "][true][MoveToTrashCmd]");
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_TRASH.toString();
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr.length > 0) {
            this.mChildOfListContainer = objArr[0].booleanValue();
        }
        checkFamilyAlbumTrash(new b(1, this));
    }
}
