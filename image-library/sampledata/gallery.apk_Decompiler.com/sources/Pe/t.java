package Pe;

import java.util.Arrays;
import java.util.LinkedHashSet;
import jf.C1108h;
import kotlin.jvm.internal.j;
import ne.C1182C;
import ne.C1195m;
import ne.C1200r;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t {

    /* renamed from: a  reason: collision with root package name */
    public static final LinkedHashSet f3652a = C1182C.c0(C1108h.g("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");
    public static final LinkedHashSet b;

    /* renamed from: c  reason: collision with root package name */
    public static final LinkedHashSet f3653c = C1108h.g("List", "getFirst()Ljava/lang/Object;", "getLast()Ljava/lang/Object;");
    public static final LinkedHashSet d = C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(C1108h.f("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), C1108h.g("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), C1108h.f("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;")), C1108h.f("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V")), C1108h.g("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z")), C1108h.g("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "addFirst(Ljava/lang/Object;)V", "addLast(Ljava/lang/Object;)V", "removeFirst()Ljava/lang/Object;", "removeLast()Ljava/lang/Object;")), C1108h.g("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
    public static final LinkedHashSet e = C1182C.b0(C1182C.b0(C1108h.g("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), C1108h.g("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V", "addFirst(Ljava/lang/Object;)V", "addLast(Ljava/lang/Object;)V", "removeFirst()Ljava/lang/Object;", "removeLast()Ljava/lang/Object;")), C1108h.g("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
    public static final LinkedHashSet f;
    public static final LinkedHashSet g;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C1359c cVar : C1195m.q0(C1359c.BOOLEAN, C1359c.CHAR)) {
            String b5 = cVar.g().f().b();
            j.d(b5, "asString(...)");
            C1200r.A0(C1108h.f(b5, cVar.e() + "Value()" + cVar.d()), linkedHashSet);
        }
        b = C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(C1182C.b0(linkedHashSet, C1108h.g("List", "sort(Ljava/util/Comparator;)V", "reversed()Ljava/util/List;")), C1108h.f("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;")), C1108h.f("Double", "isInfinite()Z", "isNaN()Z")), C1108h.f("Float", "isInfinite()Z", "isNaN()Z")), C1108h.f("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V")), C1108h.f("CharSequence", "isEmpty()Z"));
        C1359c cVar2 = C1359c.BOOLEAN;
        C1359c cVar3 = C1359c.BYTE;
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (C1359c g3 : C1195m.q0(cVar2, cVar3, C1359c.DOUBLE, C1359c.FLOAT, cVar3, C1359c.INT, C1359c.LONG, C1359c.SHORT)) {
            String b8 = g3.g().f().b();
            j.d(b8, "asString(...)");
            String[] a7 = C1108h.a("Ljava/lang/String;");
            C1200r.A0(C1108h.f(b8, (String[]) Arrays.copyOf(a7, a7.length)), linkedHashSet2);
        }
        String[] a10 = C1108h.a("D");
        LinkedHashSet b0 = C1182C.b0(linkedHashSet2, C1108h.f("Float", (String[]) Arrays.copyOf(a10, a10.length)));
        String[] a11 = C1108h.a("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        f = C1182C.b0(b0, C1108h.f("String", (String[]) Arrays.copyOf(a11, a11.length)));
        String[] a12 = C1108h.a("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        g = C1108h.f("Throwable", (String[]) Arrays.copyOf(a12, a12.length));
    }
}
