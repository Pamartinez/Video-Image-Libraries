package lf;

import rf.C1268s;
import rf.r;

/* renamed from: lf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1150c implements r {
    BYTE(0),
    CHAR(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BOOLEAN(7),
    STRING(8),
    CLASS(9),
    ENUM(10),
    ANNOTATION(11),
    ARRAY(12);
    
    private static C1268s internalValueMap;
    private final int value;

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, rf.s] */
    static {
        internalValueMap = new Object();
    }

    /* access modifiers changed from: public */
    C1150c(int i2) {
        this.value = i2;
    }

    public static C1150c b(int i2) {
        switch (i2) {
            case 0:
                return BYTE;
            case 1:
                return CHAR;
            case 2:
                return SHORT;
            case 3:
                return INT;
            case 4:
                return LONG;
            case 5:
                return FLOAT;
            case 6:
                return DOUBLE;
            case 7:
                return BOOLEAN;
            case 8:
                return STRING;
            case 9:
                return CLASS;
            case 10:
                return ENUM;
            case 11:
                return ANNOTATION;
            case 12:
                return ARRAY;
            default:
                return null;
        }
    }

    public final int a() {
        return this.value;
    }
}
