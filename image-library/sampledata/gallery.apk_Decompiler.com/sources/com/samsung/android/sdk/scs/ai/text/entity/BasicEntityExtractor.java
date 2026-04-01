package com.samsung.android.sdk.scs.ai.text.entity;

import Tc.a;
import Tc.b;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import c4.C0431a;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicEntityExtractor {
    private static final int MAP_POI_STABLE_VERSION_CODE = 140032000;
    private static final Set<String> SUPPORTED_LANGUAGE = ((Set) Stream.of(new String[]{"KO", "ZH", "JA", "EN", "DE", "FR", "ES", "IT", "RU"}).collect(Collectors.collectingAndThen(Collectors.toSet(), new C0431a(25))));
    private static final String TAG = "ScsApi@BasicEntityExtractor";
    private final boolean isBankAccountSupported;
    private final boolean isBasicEntityBulkSupported;
    private final boolean isBasicEntitySupported;
    private final boolean isDateTimeNumeralSupported;
    private final boolean isDateTimeSearchSupported;
    private final boolean isMapAddressPoiSupported;
    private final boolean isPhoneNumberSupported;
    private final boolean isUnitSupported;
    private final boolean isUpiIdSupported;
    private TextServiceExecutor mServiceExecutor;
    private RequestType requestType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DateTimeUnit {
        DATE,
        TIME,
        AMPM
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EntityType {
        DATE_TIME,
        DATE_TIME_NUMERAL,
        DATE_TIME_SEARCH,
        PHONE_NUMBER,
        EMAIL_ADDRESS,
        URL,
        MAP_ADDRESS,
        MAP_ADDRESS_POI,
        BANK_ACCOUNT,
        VERIFICATION_CODE,
        UPI_ID,
        UNIT
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Hemisphere {
        NORTHERN,
        SOUTHERN
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RecurrenceUnit {
        MONTH,
        WEEKDAY,
        DAY,
        START_TIME,
        END_TIME
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestType {
        CALENDAR,
        REMINDER,
        SUGGESTION,
        GALLERY,
        CAPTURE,
        DEFAULT
    }

    public BasicEntityExtractor(Context context) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14 = false;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isBasicEntitySupported = z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_BULK) == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.isBasicEntityBulkSupported = z3;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_DATETIME_NUMERAL) == 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.isDateTimeNumeralSupported = z7;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_DATETIME_SEARCH) == 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        this.isDateTimeSearchSupported = z9;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_PHONE_NUMBER) == 0) {
            z10 = true;
        } else {
            z10 = false;
        }
        this.isPhoneNumberSupported = z10;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_POI) != 0 || Feature.getScsVersionCode(context) < MAP_POI_STABLE_VERSION_CODE) {
            z11 = false;
        } else {
            z11 = true;
        }
        this.isMapAddressPoiSupported = z11;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_BANK) == 0) {
            z12 = true;
        } else {
            z12 = false;
        }
        this.isBankAccountSupported = z12;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_UPI_ID) == 0) {
            z13 = true;
        } else {
            z13 = false;
        }
        this.isUpiIdSupported = z13;
        this.isUnitSupported = Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_ENTITY_UNIT) == 0 ? true : z14;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    private Bundle createInputBundle(String str, String str2, Set<EntityType> set, long j2, Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        ArrayList arrayList = new ArrayList();
        for (EntityType next : set) {
            if ((next != EntityType.DATE_TIME_NUMERAL || this.isDateTimeNumeralSupported) && ((next != EntityType.DATE_TIME_SEARCH || this.isDateTimeSearchSupported) && ((next != EntityType.PHONE_NUMBER || this.isPhoneNumberSupported) && ((next != EntityType.MAP_ADDRESS_POI || this.isMapAddressPoiSupported) && ((next != EntityType.BANK_ACCOUNT || this.isBankAccountSupported) && ((next != EntityType.UPI_ID || this.isUpiIdSupported) && (next != EntityType.UNIT || this.isUnitSupported))))))) {
                arrayList.add(next.name());
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("language", str);
        bundle.putString(BuddyContract.Address.COUNTRY, str2);
        bundle.putStringArrayList("entityTypeList", arrayList);
        bundle.putLong("baseTime", j2);
        bundle.putString("hemisphere", hemisphere.name());
        bundle.putString("startOfWeek", dayOfWeek.name());
        RequestType requestType2 = this.requestType;
        if (requestType2 != null) {
            bundle.putString("requestType", requestType2.name());
        }
        return bundle;
    }

    private static String getTruncatedText(String str) {
        int length = str.length();
        if (length <= 10000) {
            return str;
        }
        Log.i(TAG, String.format("BasicEntity input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 10000, 10000}));
        return str.substring(0, 10000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupported$0(String str, String str2) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            bundle.putString(BuddyContract.Address.COUNTRY, str2);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getEntitySupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "BasicEntityExtractor.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "BasicEntityExtractor.isSupported(). result is empty!! App version is lower than Sdk so just check in static Array");
                return Boolean.valueOf(SUPPORTED_LANGUAGE.contains(str.toUpperCase()));
            } else {
                int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
                if (i2 == 1) {
                    return Boolean.valueOf(call.getBoolean("textSupportedBoolean"));
                }
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception :: isSupported", e);
            return Boolean.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Bundle lambda$requestExtract$1(String str, String str2, String str3, Set set, long j2, Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        try {
            String truncatedText = getTruncatedText(str);
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle createInputBundle = createInputBundle(str2, str3, set, j2, hemisphere, dayOfWeek);
            createInputBundle.putString("string", truncatedText);
            return textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getBasicEntity", (String) null, createInputBundle);
        } catch (Exception e) {
            Log.e(TAG, "Exception :: requestExtract", e);
            return null;
        }
    }

    private Bundle requestExtract(String str, String str2, String str3, Set<EntityType> set, long j2, Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new b(this, str, str2, str3, set, j2, hemisphere, dayOfWeek));
        try {
            Bundle bundle = (Bundle) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return bundle;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in requestExtract : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Exception exc = e7;
            Log.e(TAG, "Exception occurred in requestExtract : " + exc.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            newSingleThreadExecutor.shutdownNow();
            throw th2;
        }
    }

    public List<BasicEntity> extract(String str, String str2, String str3, Set<EntityType> set) {
        return extract(str, str2, str3, set, System.currentTimeMillis());
    }

    public boolean isSupported(String str, String str2) {
        Boolean bool;
        Log.d(TAG, "BasicEntityExtractor isSupported - language : " + str + ", country : " + str2);
        if (!this.isBasicEntitySupported) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a((Object) this, (Object) str, (Object) str2, 0));
        try {
            bool = (Boolean) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in isSupported : " + e.getMessage());
            bool = Boolean.FALSE;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in isSupported : " + e7.getMessage());
            bool = Boolean.FALSE;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
        newSingleThreadExecutor.shutdownNow();
        return bool.booleanValue();
    }

    public void setRequestType(RequestType requestType2) {
        this.requestType = requestType2;
    }

    public List<BasicEntity> extract(String str, String str2, String str3, Set<EntityType> set, long j2) {
        return extract(str, str2, str3, set, j2, Hemisphere.NORTHERN);
    }

    public List<BasicEntity> extract(String str, String str2, String str3, Set<EntityType> set, long j2, Hemisphere hemisphere) {
        return extract(str, str2, str3, set, j2, hemisphere, DayOfWeek.SUNDAY);
    }

    public List<BasicEntity> extract(String str, String str2, String str3, Set<EntityType> set, long j2, Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        ArrayList<Integer> arrayList;
        Class<DateTimeUnit> cls;
        int i2;
        ArrayList arrayList2;
        Set set2;
        Set<String> set3;
        Log.d(TAG, "BasicEntity extract - language:" + str2 + ", country:" + str3);
        ArrayList arrayList3 = new ArrayList();
        if (!this.isBasicEntitySupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_GET_ENTITY not supported!");
            return arrayList3;
        } else if (str.length() < 2) {
            Log.e(TAG, "BasicEntityExtractor.extract() input length is less than 2 so return empty");
            return arrayList3;
        } else {
            Bundle requestExtract = requestExtract(str, str2, str3, set, j2, hemisphere, dayOfWeek);
            if (requestExtract == null) {
                Log.e(TAG, "BasicEntityExtractor.extract(). ContentResolver result is null!!");
                return arrayList3;
            }
            int i7 = requestExtract.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i7 != 1) {
                C0086a.u(i7, "unexpected resultCode!!! resultCode: ", TAG);
                return arrayList3;
            }
            ArrayList<String> stringArrayList = requestExtract.getStringArrayList("entityTypeList");
            ArrayList<Integer> integerArrayList = requestExtract.getIntegerArrayList("startIndexList");
            ArrayList<Integer> integerArrayList2 = requestExtract.getIntegerArrayList("endtIndexList");
            ArrayList<String> stringArrayList2 = requestExtract.getStringArrayList("textList");
            ArrayList arrayList4 = (ArrayList) requestExtract.getSerializable("startDateList");
            ArrayList arrayList5 = (ArrayList) requestExtract.getSerializable("endDateList");
            ArrayList arrayList6 = (ArrayList) requestExtract.getSerializable("startLocalDateTimeList");
            ArrayList arrayList7 = (ArrayList) requestExtract.getSerializable("endLocalDateTimeList");
            ArrayList arrayList8 = (ArrayList) requestExtract.getSerializable("unresolvedStartDateTimeUnitList");
            ArrayList arrayList9 = (ArrayList) requestExtract.getSerializable("unresolvedEndDateTimeUnitList");
            ArrayList<String> stringArrayList3 = requestExtract.getStringArrayList("repeatInfoList");
            ArrayList<String> stringArrayList4 = requestExtract.getStringArrayList("bankNameList");
            ArrayList<String> stringArrayList5 = requestExtract.getStringArrayList("bankAccountNumberList");
            ArrayList arrayList10 = arrayList3;
            ArrayList<String> stringArrayList6 = requestExtract.getStringArrayList("bankTransferAmountList");
            boolean[] booleanArray = requestExtract.getBooleanArray("poiMappableArray");
            boolean[] booleanArray2 = requestExtract.getBooleanArray("isRelativeList");
            boolean[] booleanArray3 = requestExtract.getBooleanArray("isSpecialDayArray");
            boolean[] booleanArray4 = requestExtract.getBooleanArray("hasYearArray");
            boolean[] booleanArray5 = requestExtract.getBooleanArray("hasMonthArray");
            boolean[] booleanArray6 = requestExtract.getBooleanArray("hasDayArray");
            ArrayList<String> stringArrayList7 = requestExtract.getStringArrayList("unitValue");
            ArrayList<String> stringArrayList8 = requestExtract.getStringArrayList("unitSymbol");
            ArrayList arrayList11 = (ArrayList) requestExtract.getSerializable("recurrenceInfoList");
            boolean[] booleanArray7 = requestExtract.getBooleanArray("hasRecurrenceWithinRangeArray");
            if (stringArrayList == null || stringArrayList2 == null) {
                ArrayList arrayList12 = arrayList10;
                Log.e(TAG, "null!! entityTypeList: " + stringArrayList + ", textList: " + stringArrayList2);
                return arrayList12;
            }
            int size = stringArrayList.size();
            boolean[] zArr = booleanArray7;
            if (size != stringArrayList2.size()) {
                StringBuilder o2 = C0086a.o(size, "unexpected size!!! : ", " vs ");
                o2.append(stringArrayList2.size());
                Log.e(TAG, o2.toString());
                return arrayList10;
            }
            int i8 = 0;
            while (i8 < size) {
                BasicEntity basicEntity = new BasicEntity();
                int i10 = size;
                basicEntity.setType(EntityType.valueOf(stringArrayList.get(i8)));
                basicEntity.setString(stringArrayList2.get(i8));
                if (integerArrayList != null) {
                    basicEntity.setStartIndex(integerArrayList.get(i8).intValue());
                }
                if (integerArrayList2 != null) {
                    basicEntity.setEndIndex(integerArrayList2.get(i8).intValue());
                }
                if (arrayList4 != null) {
                    basicEntity.setStartDateTime((Date) arrayList4.get(i8));
                }
                if (arrayList5 != null) {
                    basicEntity.setEndDateTime((Date) arrayList5.get(i8));
                }
                if (arrayList6 != null) {
                    basicEntity.setStartLocalDateTime((LocalDateTime) arrayList6.get(i8));
                }
                if (arrayList7 != null) {
                    basicEntity.setEndLocalDateTime((LocalDateTime) arrayList7.get(i8));
                }
                Class<DateTimeUnit> cls2 = DateTimeUnit.class;
                if (arrayList8 == null || (set3 = (Set) arrayList8.get(i8)) == null) {
                    cls = cls2;
                    arrayList = integerArrayList;
                } else {
                    cls = cls2;
                    EnumSet<E> noneOf = EnumSet.noneOf(cls);
                    for (String valueOf : set3) {
                        noneOf.add(DateTimeUnit.valueOf(valueOf));
                        integerArrayList = integerArrayList;
                    }
                    arrayList = integerArrayList;
                    basicEntity.setUnresolvedStartDateTimeUnit(noneOf);
                }
                if (!(arrayList9 == null || (set2 = (Set) arrayList9.get(i8)) == null)) {
                    EnumSet<E> noneOf2 = EnumSet.noneOf(cls);
                    for (Iterator it = set2.iterator(); it.hasNext(); it = it) {
                        noneOf2.add(DateTimeUnit.valueOf((String) it.next()));
                    }
                    basicEntity.setUnresolvedEndDateTimeUnit(noneOf2);
                }
                if (stringArrayList3 != null) {
                    basicEntity.setRepeatInfo(stringArrayList3.get(i8));
                }
                if (stringArrayList4 != null) {
                    basicEntity.setBankName(stringArrayList4.get(i8));
                }
                if (stringArrayList5 != null) {
                    basicEntity.setBankAccountNumber(stringArrayList5.get(i8));
                }
                ArrayList<String> arrayList13 = stringArrayList6;
                if (stringArrayList6 != null) {
                    basicEntity.setBankAmount(arrayList13.get(i8));
                }
                if (booleanArray != null) {
                    basicEntity.setMappable(booleanArray[i8]);
                }
                if (booleanArray2 != null) {
                    basicEntity.setRelative(booleanArray2[i8]);
                }
                if (booleanArray3 != null) {
                    basicEntity.setSpecialDay(booleanArray3[i8]);
                }
                if (booleanArray4 != null) {
                    basicEntity.setHasYear(booleanArray4[i8]);
                }
                if (booleanArray5 != null) {
                    basicEntity.setHasMonth(booleanArray5[i8]);
                }
                if (booleanArray6 != null) {
                    basicEntity.setHasDay(booleanArray6[i8]);
                }
                ArrayList<String> arrayList14 = stringArrayList7;
                if (stringArrayList7 != null) {
                    stringArrayList6 = arrayList13;
                    basicEntity.setUnitValue(arrayList14.get(i8));
                } else {
                    stringArrayList6 = arrayList13;
                }
                if (stringArrayList8 != null) {
                    basicEntity.setUnitSymbol(stringArrayList8.get(i8));
                }
                if (arrayList11 != null) {
                    ArrayList arrayList15 = arrayList11;
                    if (arrayList15.get(i8) != null) {
                        stringArrayList7 = arrayList14;
                        HashMap hashMap = new HashMap();
                        for (Map.Entry entry : ((Map) arrayList15.get(i8)).entrySet()) {
                            int i11 = i8;
                            hashMap.put(RecurrenceUnit.valueOf((String) entry.getKey()), (List) entry.getValue());
                            arrayList15 = arrayList15;
                            i8 = i11;
                        }
                        i2 = i8;
                        arrayList2 = arrayList15;
                        basicEntity.setRecurrenceInfo(hashMap);
                    } else {
                        i2 = i8;
                        arrayList2 = arrayList15;
                        stringArrayList7 = arrayList14;
                    }
                } else {
                    i2 = i8;
                    stringArrayList7 = arrayList14;
                    arrayList2 = arrayList11;
                }
                if (zArr != null) {
                    basicEntity.setHasRecurrenceWithinRange(zArr[i2]);
                }
                ArrayList arrayList16 = arrayList10;
                arrayList16.add(basicEntity);
                arrayList10 = arrayList16;
                i8 = i2 + 1;
                size = i10;
                arrayList11 = arrayList2;
                integerArrayList = arrayList;
            }
            return arrayList10;
        }
    }

    public Task<List<List<BasicEntity>>> extract(ArrayList<String> arrayList, String str, String str2, Set<EntityType> set, long j2, Hemisphere hemisphere) {
        return extract(arrayList, str, str2, set, j2, hemisphere, DayOfWeek.SUNDAY);
    }

    public Task<List<List<BasicEntity>>> extract(ArrayList<String> arrayList, String str, String str2, Set<EntityType> set, long j2, Hemisphere hemisphere, DayOfWeek dayOfWeek) {
        Log.d(TAG, "BasicEntity Extract for textList");
        BasicEntityExtractRunnable basicEntityExtractRunnable = new BasicEntityExtractRunnable(this.mServiceExecutor);
        basicEntityExtractRunnable.setBasicEntityBulkSupported(this.isBasicEntityBulkSupported);
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(getTruncatedText(it.next()));
        }
        Bundle createInputBundle = createInputBundle(str, str2, set, j2, hemisphere, dayOfWeek);
        createInputBundle.putStringArrayList("stringList", arrayList2);
        basicEntityExtractRunnable.setInputBundle(createInputBundle);
        this.mServiceExecutor.execute(basicEntityExtractRunnable);
        return basicEntityExtractRunnable.getTask();
    }
}
