package com.samsung.android.sdk.moneta.memory.option.wrapper.v2.query;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryOption;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \b\u0007\u0018\u0000 >2\u00020\u0001:\u0001>BË\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010 \u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0007¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0007¢\u0006\u0004\b\"\u0010#R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b'\u0010&R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010#R\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b.\u0010/R\u001f\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010-\u001a\u0004\b0\u0010/R\u001f\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\f\u0010-\u001a\u0004\b1\u0010/R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\r\u0010-\u001a\u0004\b2\u0010/R\u0017\u0010\u000e\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000e\u0010+\u001a\u0004\b3\u0010#R\u0017\u0010\u000f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000f\u0010+\u001a\u0004\b4\u0010#R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0010\u0010(\u001a\u0004\b5\u0010*R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u00106\u001a\u0004\b9\u00108R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0014\u00106\u001a\u0004\b:\u00108R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010;\u001a\u0004\b<\u0010=¨\u0006?"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v2/query/EngramQueryOptionWrapperV2;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "contentId", "", "queryType", "", "includeTags", "excludeTags", "includeActivityTypes", "excludeActivityTypes", "limit", "offset", "engramId", "", "latitude", "longitude", "radius", "", "contentFill", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Z)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/lang/String;", "getContentId", "()Ljava/lang/String;", "I", "getQueryType", "Ljava/util/List;", "getIncludeTags", "()Ljava/util/List;", "getExcludeTags", "getIncludeActivityTypes", "getExcludeActivityTypes", "getLimit", "getOffset", "getEngramId", "Ljava/lang/Double;", "getLatitude", "()Ljava/lang/Double;", "getLongitude", "getRadius", "Z", "getContentFill", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramQueryOptionWrapperV2 implements Parcelable {
    public static final Parcelable.Creator<EngramQueryOptionWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean contentFill;
    private final String contentId;
    private final Long endTimestamp;
    private final String engramId;
    private final List<Integer> excludeActivityTypes;
    private final List<String> excludeTags;
    private final List<Integer> includeActivityTypes;
    private final List<String> includeTags;
    private final Double latitude;
    private final int limit;
    private final Double longitude;
    private final int offset;
    private final int queryType;
    private final Double radius;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v2/query/EngramQueryOptionWrapperV2$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v2/query/EngramQueryOptionWrapperV2;", "engramQueryOption", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramQueryOptionWrapperV2 fromOption(EngramQueryOption engramQueryOption) {
            int value;
            ArrayList arrayList;
            EngramQueryOption engramQueryOption2 = engramQueryOption;
            j.e(engramQueryOption2, "engramQueryOption");
            Long startTimestamp = engramQueryOption2.getStartTimestamp();
            Long endTimestamp = engramQueryOption2.getEndTimestamp();
            String contentId = engramQueryOption2.getContentId();
            if (engramQueryOption2.getQueryType() == EngramQueryType.BETWEEN_TIMESTAMP_LIST) {
                value = 0;
            } else {
                value = engramQueryOption2.getQueryType().getValue();
            }
            int i2 = value;
            List<String> includeTags = engramQueryOption2.getIncludeTags();
            List<String> excludeTags = engramQueryOption2.getExcludeTags();
            List<ActivityType> includeActivityTypes = engramQueryOption2.getIncludeActivityTypes();
            ArrayList arrayList2 = null;
            if (includeActivityTypes != null) {
                Iterable<ActivityType> iterable = includeActivityTypes;
                arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (ActivityType value2 : iterable) {
                    arrayList.add(Integer.valueOf(value2.getValue()));
                }
            } else {
                arrayList = null;
            }
            List<ActivityType> excludeActivityTypes = engramQueryOption2.getExcludeActivityTypes();
            if (excludeActivityTypes != null) {
                Iterable<ActivityType> iterable2 = excludeActivityTypes;
                arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                for (ActivityType value3 : iterable2) {
                    arrayList2.add(Integer.valueOf(value3.getValue()));
                }
            }
            return new EngramQueryOptionWrapperV2(startTimestamp, endTimestamp, contentId, i2, includeTags, excludeTags, arrayList, arrayList2, engramQueryOption2.getLimit(), engramQueryOption2.getOffset(), engramQueryOption2.getEngramId(), engramQueryOption2.getLatitude(), engramQueryOption2.getLongitude(), engramQueryOption2.getRadius(), engramQueryOption2.getContentFill());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramQueryOptionWrapperV2> {
        public final EngramQueryOptionWrapperV2 createFromParcel(Parcel parcel) {
            ArrayList arrayList;
            ArrayList arrayList2;
            Parcel parcel2 = parcel;
            j.e(parcel2, "parcel");
            Double d = null;
            Long valueOf = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            Long valueOf2 = parcel2.readInt() == 0 ? null : Long.valueOf(parcel2.readLong());
            String readString = parcel2.readString();
            int readInt = parcel2.readInt();
            ArrayList<String> createStringArrayList = parcel2.createStringArrayList();
            ArrayList<String> createStringArrayList2 = parcel2.createStringArrayList();
            boolean z = false;
            if (parcel2.readInt() == 0) {
                arrayList = null;
            } else {
                int readInt2 = parcel2.readInt();
                arrayList = new ArrayList(readInt2);
                for (int i2 = 0; i2 != readInt2; i2++) {
                    arrayList.add(Integer.valueOf(parcel2.readInt()));
                }
            }
            if (parcel2.readInt() == 0) {
                arrayList2 = null;
            } else {
                int readInt3 = parcel2.readInt();
                arrayList2 = new ArrayList(readInt3);
                for (int i7 = 0; i7 != readInt3; i7++) {
                    arrayList2.add(Integer.valueOf(parcel2.readInt()));
                }
            }
            int readInt4 = parcel2.readInt();
            int readInt5 = parcel2.readInt();
            String readString2 = parcel2.readString();
            Double valueOf3 = parcel2.readInt() == 0 ? null : Double.valueOf(parcel2.readDouble());
            Double valueOf4 = parcel2.readInt() == 0 ? null : Double.valueOf(parcel2.readDouble());
            if (parcel2.readInt() != 0) {
                d = Double.valueOf(parcel2.readDouble());
            }
            Double d2 = d;
            if (parcel2.readInt() != 0) {
                z = true;
            }
            return new EngramQueryOptionWrapperV2(valueOf, valueOf2, readString, readInt, createStringArrayList, createStringArrayList2, arrayList, arrayList2, readInt4, readInt5, readString2, valueOf3, valueOf4, d2, z);
        }

        public final EngramQueryOptionWrapperV2[] newArray(int i2) {
            return new EngramQueryOptionWrapperV2[i2];
        }
    }

    public EngramQueryOptionWrapperV2() {
        this((Long) null, (Long) null, (String) null, 0, (List) null, (List) null, (List) null, (List) null, 0, 0, (String) null, (Double) null, (Double) null, (Double) null, false, 32767, (e) null);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean getContentFill() {
        return this.contentFill;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getEngramId() {
        return this.engramId;
    }

    public final List<Integer> getExcludeActivityTypes() {
        return this.excludeActivityTypes;
    }

    public final List<String> getExcludeTags() {
        return this.excludeTags;
    }

    public final List<Integer> getIncludeActivityTypes() {
        return this.includeActivityTypes;
    }

    public final List<String> getIncludeTags() {
        return this.includeTags;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getQueryType() {
        return this.queryType;
    }

    public final Double getRadius() {
        return this.radius;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final /* synthetic */ EngramQueryOption toOption() {
        ArrayList arrayList;
        Long l = this.startTimestamp;
        Long l8 = this.endTimestamp;
        String str = this.contentId;
        EngramQueryType.Companion companion = EngramQueryType.Companion;
        int i2 = this.queryType;
        if (i2 == 1) {
            i2 = 0;
        }
        EngramQueryType fromInt = companion.fromInt(i2);
        if (fromInt == null) {
            fromInt = EngramQueryType.BETWEEN_TIMESTAMP;
        }
        List<String> list = this.includeTags;
        List<String> list2 = this.excludeTags;
        List<Integer> list3 = this.includeActivityTypes;
        ArrayList arrayList2 = null;
        if (list3 != null) {
            Iterable<Number> iterable = list3;
            ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
            for (Number intValue : iterable) {
                arrayList3.add(ActivityType.Companion.fromInt(intValue.intValue()));
            }
            arrayList = arrayList3;
        } else {
            arrayList = null;
        }
        List<Integer> list4 = this.excludeActivityTypes;
        if (list4 != null) {
            Iterable<Number> iterable2 = list4;
            arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
            for (Number intValue2 : iterable2) {
                arrayList2.add(ActivityType.Companion.fromInt(intValue2.intValue()));
            }
        }
        return new EngramQueryOption.WrapBuilder(l, l8, str, fromInt, list, list2, arrayList, arrayList2, this.limit, this.offset, this.engramId, this.latitude, this.longitude, this.radius, this.contentFill, (Integer) null, 32768, (e) null).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        parcel.writeString(this.contentId);
        parcel.writeInt(this.queryType);
        parcel.writeStringList(this.includeTags);
        parcel.writeStringList(this.excludeTags);
        List<Integer> list = this.includeActivityTypes;
        if (list == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(list.size());
            for (Integer intValue : list) {
                parcel.writeInt(intValue.intValue());
            }
        }
        List<Integer> list2 = this.excludeActivityTypes;
        if (list2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(list2.size());
            for (Integer intValue2 : list2) {
                parcel.writeInt(intValue2.intValue());
            }
        }
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.engramId);
        Double d = this.latitude;
        if (d == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d);
        }
        Double d2 = this.longitude;
        if (d2 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d2);
        }
        Double d3 = this.radius;
        if (d3 == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d3);
        }
        parcel.writeInt(this.contentFill ? 1 : 0);
    }

    public EngramQueryOptionWrapperV2(Long l, Long l8, String str, int i2, List<String> list, List<String> list2, List<Integer> list3, List<Integer> list4, int i7, int i8, String str2, Double d, Double d2, Double d3, boolean z) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.contentId = str;
        this.queryType = i2;
        this.includeTags = list;
        this.excludeTags = list2;
        this.includeActivityTypes = list3;
        this.excludeActivityTypes = list4;
        this.limit = i7;
        this.offset = i8;
        this.engramId = str2;
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
        this.contentFill = z;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ EngramQueryOptionWrapperV2(java.lang.Long r17, java.lang.Long r18, java.lang.String r19, int r20, java.util.List r21, java.util.List r22, java.util.List r23, java.util.List r24, int r25, int r26, java.lang.String r27, java.lang.Double r28, java.lang.Double r29, java.lang.Double r30, boolean r31, int r32, kotlin.jvm.internal.e r33) {
        /*
            r16 = this;
            r0 = r32
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x000a
        L_0x0008:
            r1 = r17
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0010
            r3 = 0
            goto L_0x0012
        L_0x0010:
            r3 = r18
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0018
            r4 = 0
            goto L_0x001a
        L_0x0018:
            r4 = r19
        L_0x001a:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0025
            com.samsung.android.sdk.moneta.memory.option.EngramQueryType r5 = com.samsung.android.sdk.moneta.memory.option.EngramQueryType.BETWEEN_TIMESTAMP
            int r5 = r5.getValue()
            goto L_0x0027
        L_0x0025:
            r5 = r20
        L_0x0027:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x002d
            r6 = 0
            goto L_0x002f
        L_0x002d:
            r6 = r21
        L_0x002f:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0035
            r7 = 0
            goto L_0x0037
        L_0x0035:
            r7 = r22
        L_0x0037:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x003d
            r8 = 0
            goto L_0x003f
        L_0x003d:
            r8 = r23
        L_0x003f:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0045
            r9 = 0
            goto L_0x0047
        L_0x0045:
            r9 = r24
        L_0x0047:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x004e
            r10 = 20
            goto L_0x0050
        L_0x004e:
            r10 = r25
        L_0x0050:
            r11 = r0 & 512(0x200, float:7.175E-43)
            r12 = 0
            if (r11 == 0) goto L_0x0057
            r11 = r12
            goto L_0x0059
        L_0x0057:
            r11 = r26
        L_0x0059:
            r13 = r0 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x005f
            r13 = 0
            goto L_0x0061
        L_0x005f:
            r13 = r27
        L_0x0061:
            r14 = r0 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0067
            r14 = 0
            goto L_0x0069
        L_0x0067:
            r14 = r28
        L_0x0069:
            r15 = r0 & 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x006f
            r15 = 0
            goto L_0x0071
        L_0x006f:
            r15 = r29
        L_0x0071:
            r2 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r2 == 0) goto L_0x0077
            r2 = 0
            goto L_0x0079
        L_0x0077:
            r2 = r30
        L_0x0079:
            r0 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L_0x009e
            r32 = r12
        L_0x007f:
            r17 = r16
            r18 = r1
            r31 = r2
            r19 = r3
            r20 = r4
            r21 = r5
            r22 = r6
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r10
            r27 = r11
            r28 = r13
            r29 = r14
            r30 = r15
            goto L_0x00a1
        L_0x009e:
            r32 = r31
            goto L_0x007f
        L_0x00a1:
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.option.wrapper.v2.query.EngramQueryOptionWrapperV2.<init>(java.lang.Long, java.lang.Long, java.lang.String, int, java.util.List, java.util.List, java.util.List, java.util.List, int, int, java.lang.String, java.lang.Double, java.lang.Double, java.lang.Double, boolean, int, kotlin.jvm.internal.e):void");
    }
}
