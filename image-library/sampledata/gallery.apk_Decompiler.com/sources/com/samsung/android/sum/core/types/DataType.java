package com.samsung.android.sum.core.types;

import B8.b;
import com.samsung.android.motionphoto.utils.v2.i;
import com.samsung.android.sum.core.Def;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DataType implements NumericEnum {
    NONE(-1),
    U8(0),
    S8(1),
    U16(2),
    S16(3),
    U32(4),
    S32(5),
    U64(6),
    S64(7),
    F16(8),
    F32(9),
    F64(10),
    U10(11),
    S10(12),
    MAX_DEPTH(15),
    U8C1(makeType(r0, 1)),
    U8C2(makeType(r0, 2)),
    U8C3(makeType(r0, 3)),
    U8C4(makeType(r0, 4)),
    S8C1(makeType(r1, 1)),
    S8C2(makeType(r1, 2)),
    S8C3(makeType(r1, 3)),
    S8C4(makeType(r1, 4)),
    U10C1(makeType(r8, 1)),
    U10C2(makeType(r8, 2)),
    U10C3(makeType(r8, 3)),
    U10C4(makeType(r8, 4)),
    S10C1(makeType(r6, 1)),
    S10C2(makeType(r6, 2)),
    S10C3(makeType(r6, 3)),
    S10C4(makeType(r6, 4)),
    U16C1(makeType(r3, 1)),
    U16C2(makeType(r3, 2)),
    U16C3(makeType(r3, 3)),
    U16C4(makeType(r3, 4)),
    S16C1(makeType(r5, 1)),
    S16C2(makeType(r5, 2)),
    S16C3(makeType(r5, 3)),
    S16C4(makeType(r5, 4)),
    U32C1(makeType(r7, 1)),
    U32C2(makeType(r7, 2)),
    U32C3(makeType(r7, 3)),
    U32C4(makeType(r7, 4)),
    S32C1(makeType(r9, 1)),
    S32C2(makeType(r9, 2)),
    S32C3(makeType(r9, 3)),
    S32C4(makeType(r9, 4)),
    U64C1(makeType(r10, 1)),
    U64C2(makeType(r10, 2)),
    U64C3(makeType(r10, 3)),
    U64C4(makeType(r10, 4)),
    S64C1(makeType(r11, 1)),
    S64C2(makeType(r11, 2)),
    S64C3(makeType(r11, 3)),
    S64C4(makeType(r11, 4)),
    F16C1(makeType(r12, 1)),
    F16C2(makeType(r12, 2)),
    F16C3(makeType(r12, 3)),
    F16C4(makeType(r12, 4)),
    F32C1(makeType(r2, 1)),
    F32C2(makeType(r2, 2)),
    F32C3(makeType(r2, 3)),
    F32C4(makeType(r2, 4)),
    F64C1(makeType(r2, 1)),
    F64C2(makeType(r2, 2)),
    F64C3(makeType(r2, 3)),
    F64C4(makeType(r2, 4));
    
    static final int DT_CN_SHIFT = 4;
    static final int DT_SET_MASK = 15;
    private static final int DT_SET_MAX = 16;
    private final int value;

    /* renamed from: com.samsung.android.sum.core.types.DataType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$types$DataType = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.sum.core.types.DataType[] r0 = com.samsung.android.sum.core.types.DataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$types$DataType = r0
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.U8     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.S8     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.U10     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.S10     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.U16     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.S16     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.U32     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.S32     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.F16     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.F32     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.U64     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.S64     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$types$DataType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.samsung.android.sum.core.types.DataType r1 = com.samsung.android.sum.core.types.DataType.F64     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.types.DataType.AnonymousClass1.<clinit>():void");
        }
    }

    private DataType(int i2) {
        this.value = i2;
    }

    private static DataType from(int i2) {
        return (DataType) Arrays.stream(values()).filter(new b(i2, 19)).findFirst().orElseThrow(new i(i2, 1));
    }

    private static int makeType(DataType dataType, int i2) {
        boolean z;
        if (dataType == NONE || dataType.getValue() > MAX_DEPTH.getValue()) {
            z = false;
        } else {
            z = true;
        }
        Def.require(z, "1st argument is not depth", new Object[0]);
        return (dataType.getValue() & 15) + (i2 << 4);
    }

    public static DataType of(DataType dataType, int i2) {
        return from(makeType(dataType, i2));
    }

    public int channels() {
        return this.value >> 4;
    }

    public DataType depth() {
        return from(this.value & 15);
    }

    public int getValue() {
        return this.value;
    }

    public boolean isByte() {
        if (depth() == U8 || depth() == S8) {
            return true;
        }
        return false;
    }

    public boolean isFloat() {
        if (depth() == F16 || depth() == F32 || depth() == F64) {
            return true;
        }
        return false;
    }

    public boolean isInt() {
        if (depth() == U32 || depth() == S32) {
            return true;
        }
        return false;
    }

    public boolean isLong() {
        if (depth() == U64 || depth() == S64) {
            return true;
        }
        return false;
    }

    public boolean isShort() {
        if (depth() == U16 || depth() == S16) {
            return true;
        }
        return false;
    }

    public float size() {
        switch (AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$types$DataType[depth().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return 1.0f;
            case 5:
            case 6:
                return 2.0f;
            case 7:
            case 8:
            case 9:
            case 10:
                return 4.0f;
            case 11:
            case 12:
            case 13:
                return 8.0f;
            default:
                return 0.0f;
        }
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }

    public static float size(DataType dataType) {
        return dataType.size();
    }
}
