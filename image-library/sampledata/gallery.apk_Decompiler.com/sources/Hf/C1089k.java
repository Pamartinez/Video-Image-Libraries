package hf;

import L2.m;
import ge.W0;
import java.util.LinkedHashMap;

/* renamed from: hf.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1089k {

    /* renamed from: a  reason: collision with root package name */
    public static final C1082d f4589a = new C1082d(C1085g.NULLABLE, false);
    public static final C1082d b;

    /* renamed from: c  reason: collision with root package name */
    public static final C1082d f4590c;
    public static final LinkedHashMap d;

    static {
        C1085g gVar = C1085g.NOT_NULL;
        b = new C1082d(gVar, false);
        f4590c = new C1082d(gVar, true);
        String concat = "java/lang/".concat("Object");
        String concat2 = "java/util/function/".concat("Predicate");
        String concat3 = "java/util/function/".concat("Function");
        String concat4 = "java/util/function/".concat("Consumer");
        String concat5 = "java/util/function/".concat("BiFunction");
        String concat6 = "java/util/function/".concat("BiConsumer");
        String concat7 = "java/util/function/".concat("UnaryOperator");
        String concat8 = "java/util/".concat("stream/Stream");
        String concat9 = "java/util/".concat("Optional");
        m mVar = new m(1);
        new W0(mVar, "java/util/".concat("Iterator")).w0("forEachRemaining", (String) null, new C1087i(concat4, 0));
        new W0(mVar, "java/lang/".concat("Iterable")).w0("spliterator", (String) null, new C1091m(4));
        W0 w02 = new W0(mVar, "java/util/".concat("Collection"));
        w02.w0("removeIf", (String) null, new C1087i(concat2, 15));
        w02.w0("stream", (String) null, new C1087i(concat8, 22));
        w02.w0("parallelStream", (String) null, new C1087i(concat8, 23));
        W0 w03 = new W0(mVar, "java/util/".concat("List"));
        w03.w0("replaceAll", (String) null, new C1087i(concat7, 24));
        w03.w0("addFirst", "2.1", new C1087i(concat, 25));
        w03.w0("addLast", "2.1", new C1087i(concat, 26));
        w03.w0("removeFirst", "2.1", new C1087i(concat, 27));
        w03.w0("removeLast", "2.1", new C1087i(concat, 28));
        W0 w04 = new W0(mVar, "java/util/".concat("LinkedList"));
        w04.w0("addFirst", "2.1", new C1087i(concat, 1));
        w04.w0("addLast", "2.1", new C1087i(concat, 2));
        w04.w0("removeFirst", "2.1", new C1087i(concat, 3));
        w04.w0("removeLast", "2.1", new C1087i(concat, 4));
        W0 w05 = new W0(mVar, "java/util/".concat("Map"));
        w05.w0("forEach", (String) null, new C1087i(concat6, 5));
        w05.w0("putIfAbsent", (String) null, new C1087i(concat, 6));
        w05.w0("replace", (String) null, new C1087i(concat, 7));
        w05.w0("replace", (String) null, new C1087i(concat, 8));
        w05.w0("replaceAll", (String) null, new C1087i(concat5, 9));
        w05.w0("compute", (String) null, new C1088j(concat, concat5, 0));
        w05.w0("computeIfAbsent", (String) null, new C1088j(concat, concat3, 1));
        w05.w0("computeIfPresent", (String) null, new C1088j(concat, concat5, 2));
        w05.w0("merge", (String) null, new C1088j(concat, concat5, 3));
        W0 w06 = new W0(mVar, concat9);
        w06.w0("empty", (String) null, new C1087i(concat9, 10));
        w06.w0("of", (String) null, new C1088j(concat, concat9, 4));
        w06.w0("ofNullable", (String) null, new C1088j(concat, concat9, 5));
        w06.w0("get", (String) null, new C1087i(concat, 11));
        String str = concat4;
        w06.w0("ifPresent", (String) null, new C1087i(str, 12));
        new W0(mVar, "java/lang/".concat("ref/Reference")).w0("get", (String) null, new C1087i(concat, 13));
        new W0(mVar, concat2).w0("test", (String) null, new C1087i(concat, 14));
        String str2 = "java/util/function/";
        new W0(mVar, str2.concat("BiPredicate")).w0("test", (String) null, new C1087i(concat, 16));
        new W0(mVar, str).w0("accept", (String) null, new C1087i(concat, 17));
        new W0(mVar, concat6).w0("accept", (String) null, new C1087i(concat, 18));
        new W0(mVar, concat3).w0("apply", (String) null, new C1087i(concat, 19));
        new W0(mVar, concat5).w0("apply", (String) null, new C1087i(concat, 20));
        new W0(mVar, str2.concat("Supplier")).w0("get", (String) null, new C1087i(concat, 21));
        d = mVar.f400a;
    }
}
