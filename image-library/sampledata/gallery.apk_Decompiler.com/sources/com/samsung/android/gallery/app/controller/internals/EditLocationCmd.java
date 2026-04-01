package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditLocationCmd extends BaseCommand {
    private Consumer<Object[]> mCallback;

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onDataReady(EventContext eventContext, ArrayList<Object> arrayList) {
        Object[] objArr;
        if (arrayList != null && arrayList.size() > 0 && (objArr = (Object[]) arrayList.get(0)) != null && objArr.length > 2) {
            this.mCallback.accept(objArr);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mCallback = objArr[3];
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/EditLocation").appendArg("address", objArr[0]).appendArg("latitude", objArr[1].doubleValue()).appendArg("longitude", objArr[2].doubleValue()).build(), null).setOnDataCompleteListener(new a(23, this)).start();
    }
}
