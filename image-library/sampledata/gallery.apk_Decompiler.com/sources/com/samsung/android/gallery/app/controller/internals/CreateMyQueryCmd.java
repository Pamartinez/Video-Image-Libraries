package com.samsung.android.gallery.app.controller.internals;

import A4.C0371f;
import C3.C0392b;
import I3.f;
import K4.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.myquery.MyQueryFilterDataUtil;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMyQueryCmd extends BaseCommand {
    private String getTargetKey(EventContext eventContext) {
        return new UriBuilder("data://user/dialog/MyQueryName").appendArg("screenId", eventContext.getScreenId()).build();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addMyQuery$2(String str, String str2, EventContext eventContext, String str3, String str4) {
        int i2;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        HashMap<Integer, String> myQueryFilterData = MyQueryUtil.getMyQueryFilterData();
        if (myQueryFilterData.containsValue(str)) {
            myQueryFilterData.entrySet().stream().filter(new C0392b(str, 8)).findFirst().ifPresent(new C0371f((Object) atomicBoolean, (Object) str2, (Object) eventContext, 11));
        } else {
            atomicBoolean.set(MyQueryUtil.createMyQuery(str2, eventContext.getTotalCount(), str));
        }
        if (atomicBoolean.get()) {
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                i2 = R.string.added_to_search_screen;
            } else {
                i2 = R.string.added_to_search_tab;
            }
            showToast(i2);
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        }
    }

    public void addMyQuery(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && (arrayList.get(0) instanceof Object[])) {
            String locationKey = eventContext.getLocationKey();
            String argValue = ArgumentsUtil.getArgValue(locationKey, "title");
            String argValue2 = ArgumentsUtil.getArgValue(locationKey, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
            String argValue3 = ArgumentsUtil.getArgValue(locationKey, "sub");
            String argValue4 = ArgumentsUtil.getArgValue(locationKey, "SelectedFilter");
            String composeFilterData = MyQueryFilterDataUtil.composeFilterData(argValue, argValue2, argValue3, argValue4, ArgumentsUtil.getArgValue(locationKey, "term"), ArgumentsUtil.getArgValue(locationKey, "people_only_them", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
            String str = argValue;
            SimpleThreadPool.getInstance().submit(new f(this, composeFilterData, (String) ((Object[]) arrayList.get(0))[0], eventContext, str, argValue4));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(getTargetKey(eventContext)).setOnDataCompleteListener(new a(20, this)).start();
    }
}
