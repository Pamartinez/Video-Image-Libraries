package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDateSingleCmd extends BaseCommand {
    private Consumer<Object[]> mCallback;

    private boolean isValidDateTimeRange(long j2, String str) {
        long j3;
        long j8;
        if (TimeUtil.isValidLocalTime(j2)) {
            j3 = TimeUtil.IsoUtcDateTime.toTimeInMillis(str);
        } else {
            j3 = TimeUtil.getExifTimeInMillis(str);
        }
        if (TimeUtil.isValidLocalTime(j2)) {
            j8 = TimeUtil.IsoUtcDateTime.toTimeInMillis("2100-12-31 23:59:59");
        } else {
            j8 = TimeUtil.getExifTimeInMillis("2100:12:31 23:59:59");
        }
        if (j3 <= j8) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext, ArrayList arrayList) {
        onDataCompleted(arrayList);
    }

    private void onDataCompleted(ArrayList<Object> arrayList) {
        Object[] objArr;
        if (this.mCallback != null && arrayList != null && !arrayList.isEmpty() && (objArr = (Object[]) arrayList.get(0)) != null && objArr.length == 2) {
            this.mCallback.accept(objArr);
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        long longValue = objArr[0].longValue();
        long longValue2 = objArr[1].longValue();
        Boolean bool = objArr[2];
        if (TimeUtil.isValidLocalTime(longValue2)) {
            str = TimeUtil.getIsoUtcDateTime(longValue2);
        } else {
            str = TimeUtil.getExifDateTime(longValue);
        }
        this.mCallback = objArr[3];
        if (!isValidDateTimeRange(longValue2, str)) {
            String str2 = this.TAG;
            Log.d(str2, "update wrong date {" + str + "}");
            str = TimeUtil.getExifDateTime(System.currentTimeMillis());
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/DateTimePicker").appendArg("current_date", str.replace("-", NumericEnum.SEP)).appendArg("has_video", bool.booleanValue()).build()).setOnDataCompleteListener(new a(13, this)).start();
    }
}
