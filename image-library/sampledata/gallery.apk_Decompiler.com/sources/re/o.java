package Re;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.i;
import ne.C1194l;
import ne.C1195m;
import ne.z;
import o1.C0246a;
import te.C1295a;
import te.C1296b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum o {
    CLASS("class", true),
    ANNOTATION_CLASS("annotation class", true),
    TYPE_PARAMETER("type parameter", false),
    PROPERTY("property", true),
    FIELD("field", true),
    LOCAL_VARIABLE("local variable", true),
    VALUE_PARAMETER("value parameter", true),
    CONSTRUCTOR("constructor", true),
    FUNCTION("function", true),
    PROPERTY_GETTER("getter", true),
    PROPERTY_SETTER("setter", true),
    TYPE("type usage", false),
    EXPRESSION("expression", false),
    FILE("file", false),
    TYPEALIAS("typealias", false),
    TYPE_PROJECTION("type projection", false),
    STAR_PROJECTION("star projection", false),
    PROPERTY_PARAMETER("property constructor parameter", false),
    CLASS_ONLY("class", false),
    OBJECT("object", false),
    STANDALONE_OBJECT("standalone object", false),
    COMPANION_OBJECT("companion object", false),
    INTERFACE("interface", false),
    ENUM_CLASS("enum class", false),
    ENUM_ENTRY("enum entry", false),
    LOCAL_CLASS("local class", false),
    LOCAL_FUNCTION("local function", false),
    MEMBER_FUNCTION("member function", false),
    TOP_LEVEL_FUNCTION("top level function", false),
    MEMBER_PROPERTY("member property", false),
    MEMBER_PROPERTY_WITH_BACKING_FIELD("member property with backing field", false),
    MEMBER_PROPERTY_WITH_DELEGATE("member property with delegate", false),
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("member property without backing field or delegate", false),
    TOP_LEVEL_PROPERTY("top level property", false),
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD("top level property with backing field", false),
    TOP_LEVEL_PROPERTY_WITH_DELEGATE("top level property with delegate", false),
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("top level property without backing field or delegate", false),
    BACKING_FIELD("backing field", true),
    INITIALIZER("initializer", false),
    DESTRUCTURING_DECLARATION("destructuring declaration", false),
    LAMBDA_EXPRESSION("lambda expression", false),
    ANONYMOUS_FUNCTION("anonymous function", false),
    OBJECT_LITERAL("object literal", false);
    
    private static final Set<o> ALL_TARGET_SET = null;
    private static final List<o> ANNOTATION_CLASS_LIST = null;
    private static final List<o> CLASS_LIST = null;
    private static final List<o> COMPANION_OBJECT_LIST = null;
    public static final n Companion = null;
    private static final Set<o> DEFAULT_TARGET_SET = null;
    private static final List<o> ENUM_ENTRY_LIST = null;
    private static final List<o> ENUM_LIST = null;
    private static final List<o> FILE_LIST = null;
    private static final List<o> FUNCTION_LIST = null;
    private static final List<o> INTERFACE_LIST = null;
    private static final List<o> LOCAL_CLASS_LIST = null;
    private static final List<o> OBJECT_LIST = null;
    private static final List<o> PROPERTY_GETTER_LIST = null;
    private static final List<o> PROPERTY_SETTER_LIST = null;
    private static final Map<d, o> USE_SITE_MAPPING = null;
    private static final HashMap<String, o> map = null;
    private final String description;
    private final boolean isDefault;

    /* JADX WARNING: type inference failed for: r1v32, types: [Re.n, java.lang.Object] */
    static {
        o[] oVarArr;
        C1296b<o> t = c.t(oVarArr);
        $ENTRIES = t;
        Companion = new Object();
        map = new HashMap<>();
        for (o oVar : t) {
            map.put(oVar.name(), oVar);
        }
        C1295a aVar = $ENTRIES;
        ArrayList arrayList = new ArrayList();
        for (Object next : aVar) {
            if (((o) next).isDefault) {
                arrayList.add(next);
            }
        }
        DEFAULT_TARGET_SET = C1194l.p1(arrayList);
        ALL_TARGET_SET = C1194l.p1($ENTRIES);
        o oVar2 = ANNOTATION_CLASS;
        o oVar3 = CLASS;
        ANNOTATION_CLASS_LIST = C1195m.q0(oVar2, oVar3);
        LOCAL_CLASS_LIST = C1195m.q0(LOCAL_CLASS, oVar3);
        CLASS_LIST = C1195m.q0(CLASS_ONLY, oVar3);
        o oVar4 = COMPANION_OBJECT;
        o oVar5 = OBJECT;
        COMPANION_OBJECT_LIST = C1195m.q0(oVar4, oVar5, oVar3);
        OBJECT_LIST = C1195m.q0(STANDALONE_OBJECT, oVar5, oVar3);
        INTERFACE_LIST = C1195m.q0(INTERFACE, oVar3);
        ENUM_LIST = C1195m.q0(ENUM_CLASS, oVar3);
        o oVar6 = ENUM_ENTRY;
        o oVar7 = PROPERTY;
        o oVar8 = FIELD;
        ENUM_ENTRY_LIST = C1195m.q0(oVar6, oVar7, oVar8);
        o oVar9 = PROPERTY_SETTER;
        PROPERTY_SETTER_LIST = C0246a.e0(oVar9);
        o oVar10 = PROPERTY_GETTER;
        PROPERTY_GETTER_LIST = C0246a.e0(oVar10);
        FUNCTION_LIST = C0246a.e0(FUNCTION);
        o oVar11 = FILE;
        FILE_LIST = C0246a.e0(oVar11);
        d dVar = d.CONSTRUCTOR_PARAMETER;
        o oVar12 = VALUE_PARAMETER;
        USE_SITE_MAPPING = z.b0(new i(dVar, oVar12), new i(d.FIELD, oVar8), new i(d.PROPERTY, oVar7), new i(d.FILE, oVar11), new i(d.PROPERTY_GETTER, oVar10), new i(d.PROPERTY_SETTER, oVar9), new i(d.RECEIVER, oVar12), new i(d.SETTER_PARAMETER, oVar12), new i(d.PROPERTY_DELEGATE_FIELD, oVar8));
    }

    /* access modifiers changed from: public */
    o(String str, boolean z) {
        this.description = str;
        this.isDefault = z;
    }
}
