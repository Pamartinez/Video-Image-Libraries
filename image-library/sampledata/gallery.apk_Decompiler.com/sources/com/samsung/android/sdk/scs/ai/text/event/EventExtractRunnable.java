package com.samsung.android.sdk.scs.ai.text.event;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.ai.text.event.EventExtractor;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EventExtractRunnable extends TaskRunnable<List<Event>> {
    private static final String TAG = "ScsApi@EventExtractRunnable";
    private boolean isEventHasYearMonthDaySupported;
    private boolean isEventIndexSupported;
    private String mLanguage;
    private final TextServiceExecutor mServiceExecutor;
    private EventExtractor.SourceType mSourceType;
    private String mText;

    public EventExtractRunnable(TextServiceExecutor textServiceExecutor) {
        this.mServiceExecutor = textServiceExecutor;
    }

    public void execute() {
        int i2;
        ArrayList<Integer> arrayList;
        ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
        Bundle bundle = new Bundle();
        bundle.putString("language", this.mLanguage);
        bundle.putString("string", this.mText);
        bundle.putString("sourceString", this.mSourceType.name());
        Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getEvent", (String) null, bundle);
        if (call == null) {
            Log.e(TAG, "EventExtractor.extract(). ContentResolver result is null!!");
            C0086a.t(5, "ContentResolver result is null", this.mSource);
            return;
        }
        int i7 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i7 != 1) {
            C0086a.u(i7, "unexpected resultCode!!! resultCode: ", TAG);
            if (i7 == 500) {
                C0086a.s(500, this.mSource);
            } else {
                this.mSource.setException(new ResultException(2000, Integer.toString(i7)));
            }
        } else {
            ArrayList<String> stringArrayList = call.getStringArrayList("eventTitleList");
            ArrayList<String> stringArrayList2 = call.getStringArrayList("eventLocationList");
            ArrayList arrayList2 = (ArrayList) call.getSerializable("eventStartDateTimeList");
            ArrayList arrayList3 = (ArrayList) call.getSerializable("eventEndDateTimeList");
            boolean[] booleanArray = call.getBooleanArray("isAllDayList");
            boolean[] booleanArray2 = call.getBooleanArray("untilFlagList");
            boolean[] booleanArray3 = call.getBooleanArray("isRelativeList");
            ArrayList<String> stringArrayList3 = call.getStringArrayList("cyclicTimeList");
            boolean[] booleanArray4 = call.getBooleanArray("hasYearArray");
            boolean[] booleanArray5 = call.getBooleanArray("hasMonthArray");
            boolean[] booleanArray6 = call.getBooleanArray("hasDayArray");
            ArrayList<Integer> integerArrayList = call.getIntegerArrayList("startIndexList");
            ArrayList<Integer> integerArrayList2 = call.getIntegerArrayList("endtIndexList");
            if (stringArrayList == null || stringArrayList2 == null || arrayList2 == null || arrayList3 == null || booleanArray == null || booleanArray2 == null || booleanArray3 == null || stringArrayList3 == null) {
                Log.e(TAG, "null!! eventTitleList: " + stringArrayList + ", eventLocationList: " + stringArrayList2 + ", startDateTimeList: " + arrayList2 + ", endDateTimeList: " + arrayList3 + ", isAllDayList: " + Arrays.toString(booleanArray) + ", untilFlagList: " + Arrays.toString(booleanArray2) + ", isRelativeList : " + Arrays.toString(booleanArray3) + ", cyclicTimeList: " + stringArrayList3);
                C0086a.t(2000, "bundle content is null", this.mSource);
                return;
            }
            int size = stringArrayList.size();
            ArrayList<Integer> arrayList4 = integerArrayList2;
            if (size != stringArrayList2.size() || size != arrayList2.size() || size != arrayList3.size() || size != booleanArray.length || size != booleanArray2.length || size != booleanArray3.length || size != stringArrayList3.size()) {
                StringBuilder o2 = C0086a.o(size, "unexpected size!!! : ", " vs ");
                o2.append(booleanArray.length);
                o2.append(" vs ");
                o2.append(booleanArray2.length);
                o2.append(" vs ");
                o2.append(booleanArray3.length);
                o2.append(stringArrayList3.size());
                Log.e(TAG, o2.toString());
                C0086a.t(2000, "list size mismatched", this.mSource);
            } else if (this.isEventHasYearMonthDaySupported && (booleanArray4 == null || size != booleanArray4.length || booleanArray5 == null || size != booleanArray5.length || booleanArray6 == null || size != booleanArray6.length)) {
                Log.e(TAG, "has year month day null or unexpected size!!!");
                C0086a.t(2000, "event has year month day mismatch", this.mSource);
            } else if (!this.isEventIndexSupported || (integerArrayList != null && size == integerArrayList.size() && arrayList4 != null && size == arrayList4.size())) {
                ArrayList arrayList5 = new ArrayList();
                int i8 = 0;
                while (i8 < size) {
                    boolean[] zArr = booleanArray4;
                    Event create = Event.create();
                    boolean[] zArr2 = booleanArray5;
                    create.setTitle(stringArrayList.get(i8));
                    create.setLocation(stringArrayList2.get(i8));
                    create.setStartDateTime((LocalDateTime) arrayList2.get(i8));
                    create.setEndDateTime((LocalDateTime) arrayList3.get(i8));
                    create.setAllDay(booleanArray[i8]);
                    create.setUntilFlag(booleanArray2[i8]);
                    create.setRelative(booleanArray3[i8]);
                    create.setCyclicTime(stringArrayList3.get(i8));
                    if (this.isEventHasYearMonthDaySupported) {
                        create.setHasYear(zArr[i8]);
                        create.setHasMonth(zArr2[i8]);
                        create.setHasDay(booleanArray6[i8]);
                    }
                    if (this.isEventIndexSupported) {
                        create.setStartIndex(integerArrayList.get(i8).intValue());
                        arrayList = arrayList4;
                        i2 = i8;
                        create.setEndIndex(arrayList.get(i8).intValue());
                    } else {
                        i2 = i8;
                        arrayList = arrayList4;
                    }
                    arrayList5.add(create);
                    i8 = i2 + 1;
                    arrayList4 = arrayList;
                    booleanArray4 = zArr;
                    booleanArray5 = zArr2;
                }
                this.mSource.setResult(arrayList5);
            } else {
                Log.e(TAG, "start end index null or unexpected size!!!");
                C0086a.t(2000, "event start end index mismatch", this.mSource);
            }
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_TEXT_GET_EVENT;
    }

    public void setEventHasYearMonthDaySupported(boolean z) {
        this.isEventHasYearMonthDaySupported = z;
    }

    public void setEventIndexSupported(boolean z) {
        this.isEventIndexSupported = z;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public void setSourceType(EventExtractor.SourceType sourceType) {
        this.mSourceType = sourceType;
    }

    public void setText(String str) {
        this.mText = str;
    }
}
