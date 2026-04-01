package yf;

import Ne.l;
import i.C0212a;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import qf.C1236c;

/* renamed from: yf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1359c {
    BOOLEAN(l.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(l.CHAR, "char", "C", "java.lang.Character"),
    BYTE(l.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(l.SHORT, "short", "S", "java.lang.Short"),
    INT(l.INT, "int", "I", "java.lang.Integer"),
    FLOAT(l.FLOAT, "float", "F", "java.lang.Float"),
    LONG(l.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(l.DOUBLE, "double", "D", "java.lang.Double");
    
    private static final Map<String, String> OWNER_TO_BOXING_METHOD_DESCRIPTOR = null;
    private static final Map<String, C1359c> TYPE_BY_DESC = null;
    private static final Map<String, C1359c> TYPE_BY_NAME = null;
    private static final Map<l, C1359c> TYPE_BY_PRIMITIVE_TYPE = null;
    private static final Set<String> WRAPPER_CLASS_INTERNAL_NAMES = null;
    private final String desc;
    private final String name;
    private final l primitiveType;
    private final C1236c wrapperFqName;

    static {
        TYPE_BY_NAME = new HashMap();
        TYPE_BY_PRIMITIVE_TYPE = new EnumMap(l.class);
        TYPE_BY_DESC = new HashMap();
        WRAPPER_CLASS_INTERNAL_NAMES = new HashSet();
        OWNER_TO_BOXING_METHOD_DESCRIPTOR = new HashMap();
        for (C1359c cVar : values()) {
            TYPE_BY_NAME.put(cVar.e(), cVar);
            TYPE_BY_PRIMITIVE_TYPE.put(cVar.f(), cVar);
            TYPE_BY_DESC.put(cVar.d(), cVar);
            String replace = cVar.wrapperFqName.b().replace('.', '/');
            WRAPPER_CLASS_INTERNAL_NAMES.add(replace);
            OWNER_TO_BOXING_METHOD_DESCRIPTOR.put(replace, C0212a.q(new StringBuilder("("), cVar.desc, ")L", replace, ";"));
        }
    }

    /* access modifiers changed from: public */
    C1359c(l lVar, String str, String str2, String str3) {
        if (lVar != null) {
            this.primitiveType = lVar;
            this.name = str;
            this.desc = str2;
            this.wrapperFqName = new C1236c(str3);
            return;
        }
        a(8);
        throw null;
    }

    public static C1359c b(l lVar) {
        C1359c cVar = TYPE_BY_PRIMITIVE_TYPE.get(lVar);
        if (cVar != null) {
            return cVar;
        }
        a(6);
        throw null;
    }

    public static C1359c c(String str) {
        C1359c cVar = TYPE_BY_NAME.get(str);
        if (cVar != null) {
            return cVar;
        }
        throw new AssertionError("Non-primitive type name passed: ".concat(str));
    }

    public final String d() {
        String str = this.desc;
        if (str != null) {
            return str;
        }
        a(14);
        throw null;
    }

    public final String e() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        a(13);
        throw null;
    }

    public final l f() {
        l lVar = this.primitiveType;
        if (lVar != null) {
            return lVar;
        }
        a(12);
        throw null;
    }

    public final C1236c g() {
        C1236c cVar = this.wrapperFqName;
        if (cVar != null) {
            return cVar;
        }
        a(15);
        throw null;
    }
}
