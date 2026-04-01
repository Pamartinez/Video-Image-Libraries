package com.samsung.android.sdk.scs.ai.text.entity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.ai.text.entity.BasicEntityExtractor;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicEntityExtractRunnable extends TaskRunnable<List<List<BasicEntity>>> {
    private static final String TAG = "ScsApi@BasicEntityExtractRunnable";
    private Bundle inputBundle;
    private boolean isBasicEntityBulkSupported;
    private TextServiceExecutor mServiceExecutor;

    public BasicEntityExtractRunnable(TextServiceExecutor textServiceExecutor) {
        this.mServiceExecutor = textServiceExecutor;
    }

    public void execute() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        ArrayList arrayList7;
        ArrayList arrayList8;
        ArrayList arrayList9;
        ArrayList arrayList10;
        ArrayList arrayList11;
        ArrayList arrayList12;
        ArrayList arrayList13;
        ArrayList arrayList14;
        ArrayList arrayList15;
        ArrayList arrayList16;
        ArrayList arrayList17;
        ArrayList arrayList18;
        ArrayList arrayList19;
        ArrayList arrayList20;
        ArrayList arrayList21;
        String str;
        String str2;
        String str3;
        BasicEntityExtractRunnable basicEntityExtractRunnable;
        ArrayList arrayList22;
        ArrayList arrayList23;
        ArrayList arrayList24;
        ArrayList arrayList25;
        ArrayList arrayList26;
        Class<BasicEntityExtractor.DateTimeUnit> cls;
        ArrayList arrayList27;
        int i2;
        ArrayList arrayList28;
        Set set;
        Set<String> set2;
        if (this.isBasicEntityBulkSupported) {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            if (this.inputBundle != null) {
                Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getBasicEntityBulk", (String) null, this.inputBundle);
                if (call == null) {
                    Log.e(TAG, "BasicEntityExtractor.extract() for textList. ContentResolver result is null!!");
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
                    ArrayList arrayList29 = (ArrayList) call.getSerializable("entityTypeList");
                    ArrayList arrayList30 = (ArrayList) call.getSerializable("startIndexList");
                    ArrayList arrayList31 = (ArrayList) call.getSerializable("endtIndexList");
                    ArrayList arrayList32 = (ArrayList) call.getSerializable("textList");
                    ArrayList arrayList33 = (ArrayList) call.getSerializable("startDateList");
                    ArrayList arrayList34 = (ArrayList) call.getSerializable("endDateList");
                    ArrayList arrayList35 = (ArrayList) call.getSerializable("startLocalDateTimeList");
                    ArrayList arrayList36 = (ArrayList) call.getSerializable("endLocalDateTimeList");
                    ArrayList arrayList37 = (ArrayList) call.getSerializable("unresolvedStartDateTimeUnitList");
                    ArrayList arrayList38 = (ArrayList) call.getSerializable("unresolvedEndDateTimeUnitList");
                    ArrayList arrayList39 = (ArrayList) call.getSerializable("repeatInfoList");
                    ArrayList arrayList40 = (ArrayList) call.getSerializable("bankNameList");
                    ArrayList arrayList41 = (ArrayList) call.getSerializable("bankAccountNumberList");
                    ArrayList arrayList42 = (ArrayList) call.getSerializable("bankTransferAmountList");
                    String str4 = TAG;
                    ArrayList arrayList43 = (ArrayList) call.getSerializable("unitValue");
                    ArrayList arrayList44 = (ArrayList) call.getSerializable("unitSymbol");
                    ArrayList arrayList45 = (ArrayList) call.getSerializable("recurrenceInfoList");
                    ArrayList arrayList46 = (ArrayList) call.getSerializable("poiMappableArray");
                    ArrayList arrayList47 = (ArrayList) call.getSerializable("isRelativeList");
                    ArrayList arrayList48 = (ArrayList) call.getSerializable("isSpecialDayArray");
                    ArrayList arrayList49 = (ArrayList) call.getSerializable("hasYearArray");
                    ArrayList arrayList50 = (ArrayList) call.getSerializable("hasMonthArray");
                    ArrayList arrayList51 = (ArrayList) call.getSerializable("hasDayArray");
                    ArrayList arrayList52 = (ArrayList) call.getSerializable("hasRecurrenceWithinRangeArray");
                    if (arrayList29 == null || arrayList30 == null || arrayList31 == null || arrayList32 == null || arrayList33 == null || arrayList34 == null || arrayList37 == null || arrayList38 == null || arrayList39 == null || arrayList40 == null || arrayList41 == null || arrayList42 == null || arrayList43 == null || arrayList44 == null || arrayList45 == null || arrayList46 == null || arrayList47 == null || arrayList48 == null || arrayList49 == null || arrayList50 == null || arrayList51 == null || arrayList52 == null) {
                        ArrayList arrayList53 = arrayList42;
                        ArrayList arrayList54 = arrayList52;
                        Log.e(str4, "null!! entityTypeListResult : " + arrayList29 + ", startIndexListResult : " + arrayList30 + ", endIndexListResult : " + arrayList31 + ", textListResult : " + arrayList32 + ", startDateListResult : " + arrayList33 + ", endDateListResult : " + arrayList34 + ", unresolvedStartDateTimeUnitListResult : " + arrayList37 + ", unresolvedEndDateTimeUnitListResult : " + arrayList38 + ", repeatInfoListResult : " + arrayList39 + ", bankNameListResult : " + arrayList40 + ", bankAccountNumberListResult : " + arrayList41 + ", bankAmountListResult : " + arrayList53 + ", unitValuesResult : " + arrayList43 + ", unitSymbolsResult : " + arrayList44 + ", recurrenceInfoListResult : " + arrayList45 + ", mappableArrayResult : " + arrayList46 + ", isRelativeArrayResult : " + arrayList47 + ", isSpecialDayArrayResult : " + arrayList48 + ", hasYearArrayResult : " + arrayList49 + ", hasMonthArrayResult : " + arrayList50 + ", hasDayArrayResult : " + arrayList51 + ", hasRecurrenceWithinRangeArrayResult : " + arrayList54);
                        C0086a.t(2000, "bundle content is null", this.mSource);
                        return;
                    }
                    int size = arrayList29.size();
                    ArrayList arrayList55 = arrayList52;
                    ArrayList arrayList56 = arrayList42;
                    String str5 = "unexpected size!!! : ";
                    String str6 = " vs ";
                    if (size != arrayList30.size() || size != arrayList31.size() || size != arrayList32.size() || size != arrayList33.size() || size != arrayList34.size() || size != arrayList37.size() || size != arrayList38.size() || size != arrayList39.size() || size != arrayList40.size() || size != arrayList41.size() || size != arrayList56.size() || size != arrayList43.size() || size != arrayList44.size() || size != arrayList45.size() || size != arrayList46.size() || size != arrayList47.size() || size != arrayList48.size() || size != arrayList49.size() || size != arrayList50.size() || size != arrayList51.size()) {
                        arrayList21 = arrayList30;
                        arrayList13 = arrayList40;
                        arrayList12 = arrayList41;
                        arrayList20 = arrayList31;
                        arrayList19 = arrayList32;
                        arrayList18 = arrayList33;
                        arrayList17 = arrayList34;
                        arrayList16 = arrayList37;
                        arrayList15 = arrayList38;
                        arrayList14 = arrayList39;
                        str3 = str4;
                        arrayList4 = arrayList43;
                        arrayList3 = arrayList44;
                        arrayList2 = arrayList45;
                        arrayList10 = arrayList46;
                        arrayList9 = arrayList47;
                        arrayList8 = arrayList48;
                        arrayList7 = arrayList49;
                        arrayList6 = arrayList50;
                        arrayList5 = arrayList51;
                        arrayList = arrayList55;
                        arrayList11 = arrayList56;
                        String str7 = str6;
                        basicEntityExtractRunnable = this;
                        str = str7;
                        str2 = str5;
                    } else if (size != arrayList55.size()) {
                        arrayList21 = arrayList30;
                        arrayList13 = arrayList40;
                        arrayList12 = arrayList41;
                        arrayList20 = arrayList31;
                        arrayList19 = arrayList32;
                        arrayList18 = arrayList33;
                        arrayList17 = arrayList34;
                        arrayList16 = arrayList37;
                        arrayList15 = arrayList38;
                        arrayList14 = arrayList39;
                        str3 = str4;
                        arrayList4 = arrayList43;
                        arrayList3 = arrayList44;
                        arrayList2 = arrayList45;
                        arrayList10 = arrayList46;
                        arrayList9 = arrayList47;
                        arrayList8 = arrayList48;
                        arrayList7 = arrayList49;
                        arrayList6 = arrayList50;
                        arrayList5 = arrayList51;
                        arrayList = arrayList55;
                        arrayList11 = arrayList56;
                        str2 = str5;
                        str = str6;
                        basicEntityExtractRunnable = this;
                    } else {
                        ArrayList arrayList57 = new ArrayList();
                        int i8 = 0;
                        while (i8 < size) {
                            int i10 = size;
                            ArrayList arrayList58 = new ArrayList();
                            ArrayList arrayList59 = arrayList29;
                            ArrayList arrayList60 = (ArrayList) arrayList29.get(i8);
                            ArrayList arrayList61 = arrayList30;
                            ArrayList arrayList62 = (ArrayList) arrayList30.get(i8);
                            ArrayList arrayList63 = arrayList31;
                            ArrayList arrayList64 = (ArrayList) arrayList31.get(i8);
                            ArrayList arrayList65 = arrayList32;
                            ArrayList arrayList66 = (ArrayList) arrayList32.get(i8);
                            ArrayList arrayList67 = arrayList33;
                            ArrayList arrayList68 = (ArrayList) arrayList33.get(i8);
                            ArrayList arrayList69 = arrayList34;
                            ArrayList arrayList70 = (ArrayList) arrayList34.get(i8);
                            if (arrayList35 != null) {
                                arrayList22 = arrayList35;
                                arrayList23 = (ArrayList) arrayList35.get(i8);
                            } else {
                                arrayList22 = arrayList35;
                                arrayList23 = null;
                            }
                            if (arrayList36 != null) {
                                arrayList24 = arrayList36;
                                arrayList25 = (ArrayList) arrayList36.get(i8);
                            } else {
                                arrayList24 = arrayList36;
                                arrayList25 = null;
                            }
                            ArrayList arrayList71 = arrayList37;
                            ArrayList arrayList72 = (ArrayList) arrayList37.get(i8);
                            ArrayList arrayList73 = arrayList38;
                            ArrayList arrayList74 = (ArrayList) arrayList38.get(i8);
                            ArrayList arrayList75 = arrayList39;
                            ArrayList arrayList76 = (ArrayList) arrayList39.get(i8);
                            ArrayList arrayList77 = arrayList40;
                            ArrayList arrayList78 = (ArrayList) arrayList40.get(i8);
                            ArrayList arrayList79 = arrayList41;
                            ArrayList arrayList80 = (ArrayList) arrayList41.get(i8);
                            ArrayList arrayList81 = arrayList58;
                            ArrayList arrayList82 = arrayList56;
                            ArrayList arrayList83 = arrayList82;
                            ArrayList arrayList84 = (ArrayList) arrayList82.get(i8);
                            ArrayList arrayList85 = arrayList46;
                            boolean[] zArr = (boolean[]) arrayList85.get(i8);
                            ArrayList arrayList86 = arrayList85;
                            ArrayList arrayList87 = arrayList47;
                            boolean[] zArr2 = (boolean[]) arrayList87.get(i8);
                            ArrayList arrayList88 = arrayList87;
                            ArrayList arrayList89 = arrayList48;
                            boolean[] zArr3 = (boolean[]) arrayList89.get(i8);
                            ArrayList arrayList90 = arrayList89;
                            ArrayList arrayList91 = arrayList49;
                            boolean[] zArr4 = (boolean[]) arrayList91.get(i8);
                            ArrayList arrayList92 = arrayList91;
                            ArrayList arrayList93 = arrayList50;
                            boolean[] zArr5 = (boolean[]) arrayList93.get(i8);
                            ArrayList arrayList94 = arrayList93;
                            ArrayList arrayList95 = arrayList51;
                            boolean[] zArr6 = (boolean[]) arrayList95.get(i8);
                            ArrayList arrayList96 = arrayList95;
                            ArrayList arrayList97 = arrayList43;
                            ArrayList arrayList98 = arrayList97;
                            ArrayList arrayList99 = (ArrayList) arrayList97.get(i8);
                            ArrayList arrayList100 = arrayList44;
                            ArrayList arrayList101 = arrayList100;
                            ArrayList arrayList102 = (ArrayList) arrayList100.get(i8);
                            ArrayList arrayList103 = arrayList45;
                            ArrayList arrayList104 = arrayList103;
                            ArrayList arrayList105 = (ArrayList) arrayList103.get(i8);
                            ArrayList arrayList106 = arrayList55;
                            boolean[] zArr7 = (boolean[]) arrayList106.get(i8);
                            if (arrayList60 == null || arrayList66 == null) {
                                Log.e(str4, "null!! entityTypeList: " + arrayList60 + ", textList: " + arrayList66);
                                C0086a.t(2000, "more than one of inner lists is null", this.mSource);
                                return;
                            }
                            int i11 = i8;
                            int size2 = arrayList60.size();
                            ArrayList arrayList107 = arrayList106;
                            if (size2 != arrayList66.size()) {
                                StringBuilder o2 = C0086a.o(size2, str5, str6);
                                o2.append(arrayList66.size());
                                Log.e(str4, o2.toString());
                                C0086a.t(2000, "inner list size mismatched", this.mSource);
                                return;
                            }
                            String str8 = str4;
                            String str9 = str6;
                            int i12 = 0;
                            while (i12 < size2) {
                                int i13 = size2;
                                BasicEntity basicEntity = new BasicEntity();
                                String str10 = str8;
                                basicEntity.setType(BasicEntityExtractor.EntityType.valueOf((String) arrayList60.get(i12)));
                                basicEntity.setString((String) arrayList66.get(i12));
                                if (arrayList62 != null) {
                                    basicEntity.setStartIndex(((Integer) arrayList62.get(i12)).intValue());
                                }
                                if (arrayList64 != null) {
                                    basicEntity.setEndIndex(((Integer) arrayList64.get(i12)).intValue());
                                }
                                if (arrayList68 != null) {
                                    basicEntity.setStartDateTime((Date) arrayList68.get(i12));
                                }
                                if (arrayList70 != null) {
                                    basicEntity.setEndDateTime((Date) arrayList70.get(i12));
                                }
                                if (arrayList23 != null) {
                                    basicEntity.setStartLocalDateTime((LocalDateTime) arrayList23.get(i12));
                                }
                                if (arrayList25 != null) {
                                    basicEntity.setEndLocalDateTime((LocalDateTime) arrayList25.get(i12));
                                }
                                Class<BasicEntityExtractor.DateTimeUnit> cls2 = BasicEntityExtractor.DateTimeUnit.class;
                                if (arrayList72 == null || (set2 = (Set) arrayList72.get(i12)) == null) {
                                    cls = cls2;
                                    arrayList26 = arrayList62;
                                } else {
                                    cls = cls2;
                                    EnumSet<E> noneOf = EnumSet.noneOf(cls);
                                    for (String valueOf : set2) {
                                        noneOf.add(BasicEntityExtractor.DateTimeUnit.valueOf(valueOf));
                                        arrayList62 = arrayList62;
                                    }
                                    arrayList26 = arrayList62;
                                    basicEntity.setUnresolvedStartDateTimeUnit(noneOf);
                                }
                                if (!(arrayList74 == null || (set = (Set) arrayList74.get(i12)) == null)) {
                                    EnumSet<E> noneOf2 = EnumSet.noneOf(cls);
                                    for (Iterator it = set.iterator(); it.hasNext(); it = it) {
                                        noneOf2.add(BasicEntityExtractor.DateTimeUnit.valueOf((String) it.next()));
                                    }
                                    basicEntity.setUnresolvedEndDateTimeUnit(noneOf2);
                                }
                                if (arrayList76 != null) {
                                    basicEntity.setRepeatInfo((String) arrayList76.get(i12));
                                }
                                if (arrayList78 != null) {
                                    basicEntity.setBankName((String) arrayList78.get(i12));
                                }
                                if (arrayList80 != null) {
                                    basicEntity.setBankAccountNumber((String) arrayList80.get(i12));
                                }
                                ArrayList arrayList108 = arrayList84;
                                if (arrayList84 != null) {
                                    basicEntity.setBankAmount((String) arrayList108.get(i12));
                                }
                                if (zArr != null) {
                                    basicEntity.setMappable(zArr[i12]);
                                }
                                if (zArr2 != null) {
                                    basicEntity.setRelative(zArr2[i12]);
                                }
                                if (zArr3 != null) {
                                    basicEntity.setSpecialDay(zArr3[i12]);
                                }
                                if (zArr4 != null) {
                                    basicEntity.setHasYear(zArr4[i12]);
                                }
                                if (zArr5 != null) {
                                    basicEntity.setHasMonth(zArr5[i12]);
                                }
                                if (zArr6 != null) {
                                    basicEntity.setHasDay(zArr6[i12]);
                                }
                                if (arrayList99 != null) {
                                    arrayList27 = arrayList99;
                                    arrayList84 = arrayList108;
                                    basicEntity.setUnitValue((String) arrayList27.get(i12));
                                } else {
                                    arrayList84 = arrayList108;
                                    arrayList27 = arrayList99;
                                }
                                if (arrayList102 != null) {
                                    basicEntity.setUnitSymbol((String) arrayList102.get(i12));
                                }
                                if (arrayList105 != null) {
                                    ArrayList arrayList109 = arrayList105;
                                    if (arrayList109.get(i12) != null) {
                                        arrayList99 = arrayList27;
                                        HashMap hashMap = new HashMap();
                                        for (Map.Entry entry : ((Map) arrayList109.get(i12)).entrySet()) {
                                            hashMap.put(BasicEntityExtractor.RecurrenceUnit.valueOf((String) entry.getKey()), (List) entry.getValue());
                                            arrayList109 = arrayList109;
                                            i12 = i12;
                                        }
                                        i2 = i12;
                                        arrayList28 = arrayList109;
                                        basicEntity.setRecurrenceInfo(hashMap);
                                    } else {
                                        i2 = i12;
                                        arrayList28 = arrayList109;
                                        arrayList99 = arrayList27;
                                    }
                                } else {
                                    i2 = i12;
                                    arrayList99 = arrayList27;
                                    arrayList28 = arrayList105;
                                }
                                if (zArr7 != null) {
                                    basicEntity.setHasRecurrenceWithinRange(zArr7[i2]);
                                }
                                ArrayList arrayList110 = arrayList81;
                                arrayList110.add(basicEntity);
                                arrayList81 = arrayList110;
                                i12 = i2 + 1;
                                size2 = i13;
                                str8 = str10;
                                arrayList105 = arrayList28;
                                arrayList62 = arrayList26;
                            }
                            String str11 = str8;
                            arrayList57.add(arrayList81);
                            size = i10;
                            arrayList35 = arrayList22;
                            arrayList29 = arrayList59;
                            arrayList30 = arrayList61;
                            arrayList31 = arrayList63;
                            arrayList32 = arrayList65;
                            arrayList33 = arrayList67;
                            arrayList34 = arrayList69;
                            arrayList36 = arrayList24;
                            arrayList37 = arrayList71;
                            arrayList38 = arrayList73;
                            arrayList39 = arrayList75;
                            arrayList40 = arrayList77;
                            arrayList41 = arrayList79;
                            arrayList56 = arrayList83;
                            arrayList46 = arrayList86;
                            arrayList47 = arrayList88;
                            arrayList48 = arrayList90;
                            arrayList49 = arrayList92;
                            arrayList50 = arrayList94;
                            arrayList51 = arrayList96;
                            arrayList43 = arrayList98;
                            arrayList44 = arrayList101;
                            arrayList45 = arrayList104;
                            arrayList55 = arrayList107;
                            i8 = i11 + 1;
                            str6 = str9;
                            str4 = str11;
                        }
                        this.mSource.setResult(arrayList57);
                        return;
                    }
                    StringBuilder o3 = C0086a.o(size, str2, str);
                    o3.append(arrayList21.size());
                    o3.append(str);
                    o3.append(arrayList20.size());
                    o3.append(str);
                    o3.append(arrayList19.size());
                    o3.append(str);
                    o3.append(arrayList18.size());
                    o3.append(str);
                    o3.append(arrayList17.size());
                    o3.append(str);
                    o3.append(arrayList16.size());
                    o3.append(str);
                    o3.append(arrayList15.size());
                    o3.append(str);
                    o3.append(arrayList14.size());
                    o3.append(str);
                    o3.append(arrayList13.size());
                    o3.append(str);
                    o3.append(arrayList12.size());
                    o3.append(str);
                    o3.append(arrayList11.size());
                    o3.append(str);
                    o3.append(arrayList4.size());
                    o3.append(str);
                    o3.append(arrayList3.size());
                    o3.append(str);
                    o3.append(arrayList2.size());
                    o3.append(str);
                    o3.append(arrayList10.size());
                    o3.append(str);
                    o3.append(arrayList9.size());
                    o3.append(str);
                    o3.append(arrayList8.size());
                    o3.append(str);
                    o3.append(arrayList7.size());
                    o3.append(str);
                    o3.append(arrayList6.size());
                    o3.append(str);
                    o3.append(arrayList5.size());
                    o3.append(str);
                    o3.append(arrayList.size());
                    Log.e(str3, o3.toString());
                    C0086a.t(2000, "final list size mismatched", basicEntityExtractRunnable.mSource);
                }
            }
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_TEXT_GET_ENTITY_BULK;
    }

    public void setBasicEntityBulkSupported(boolean z) {
        this.isBasicEntityBulkSupported = z;
    }

    public void setInputBundle(Bundle bundle) {
        this.inputBundle = bundle;
    }
}
