package com.samsung.android.gallery.app.controller.internals;

import android.app.Activity;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestRuntimePermissionCmd extends BaseCommand {
    private boolean isFinishActivity(int i2) {
        if (i2 == 106) {
            return true;
        }
        return false;
    }

    private boolean isNeedToShowRationaleDialog(Activity activity, ArrayList<String> arrayList, int i2) {
        String str = arrayList.get(0);
        if (RuntimePermissionUtil.isNotYetExistPermission(str) || activity.shouldShowRequestPermissionRationale(str)) {
            return true;
        }
        activity.requestPermissions((String[]) arrayList.toArray(new String[0]), i2);
        return false;
    }

    private boolean isRequestNotificationPermission(int i2) {
        if (i2 == 123) {
            return true;
        }
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Consumer consumer;
        View view;
        String[] strArr = objArr[0];
        int intValue = objArr[1].intValue();
        int intValue2 = objArr[2].intValue();
        if (objArr.length > 3) {
            consumer = objArr[3];
        } else {
            consumer = null;
        }
        if (objArr.length > 4) {
            view = objArr[4];
        } else {
            view = null;
        }
        ArrayList<String> disabledPermissionList = RuntimePermissionUtil.getDisabledPermissionList(getContext(), strArr);
        if (isNeedToShowRationaleDialog(getActivity(), disabledPermissionList, intValue)) {
            if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                PermissionHelper.showSnackBar(eventContext.getContext(), view, isRequestNotificationPermission(intValue));
                if (consumer != null) {
                    consumer.accept(Boolean.FALSE);
                    return;
                }
                return;
            }
            UriBuilder appendArg = new UriBuilder("data://user/dialog/PermissionRequest").appendArg("size", disabledPermissionList.size()).appendArg("isFinishActivity", isFinishActivity(intValue)).appendArg("function", intValue2);
            for (int i2 = 0; i2 < disabledPermissionList.size(); i2++) {
                appendArg.appendArg(C0086a.i(i2, "request"), disabledPermissionList.get(i2));
            }
            getBlackboard().post(CommandKey.DATA_REQUEST(appendArg.build()), (Object) null);
        }
    }
}
