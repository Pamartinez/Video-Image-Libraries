package com.samsung.android.vexfwk.metadata;

import Be.a;
import android.util.Log;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.s;
import kotlin.jvm.internal.y;
import ne.C1194l;
import ne.C1196n;
import oe.C1216e;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\"\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\nH\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataValidator;", "", "<init>", "()V", "TAG", "", "validate", "", "validateKeyMatching", "getDuplicates", "", "T", "list", "validateKeyUniqueness", "validateTagMatched", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkMetadataValidator {
    public static final VexFwkMetadataValidator INSTANCE = new VexFwkMetadataValidator();
    private static final String TAG = "VexFwkMetadataValidator";

    private VexFwkMetadataValidator() {
    }

    private final <T> List<T> getDuplicates(List<? extends T> list) {
        VexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1 vexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1 = new VexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1(list);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator sourceIterator = vexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object keyOf = vexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1.keyOf(sourceIterator.next());
            Object obj = linkedHashMap.get(keyOf);
            if (obj == null && !linkedHashMap.containsKey(keyOf)) {
                obj = new Object();
            }
            s sVar = (s) obj;
            sVar.d++;
            linkedHashMap.put(keyOf, sVar);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            j.c(entry, "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace>");
            if (!(entry instanceof a) || (entry instanceof C1216e)) {
                entry.setValue(Integer.valueOf(((s) entry.getValue()).d));
            } else {
                y.e(entry, "kotlin.collections.MutableMap.MutableEntry");
                throw null;
            }
        }
        Map a7 = y.a(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry2 : a7.entrySet()) {
            if (((Number) entry2.getValue()).intValue() > 1) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        return C1194l.k1(linkedHashMap2.keySet());
    }

    private final boolean validateKeyUniqueness() {
        VexFwkMetadataKey.Companion<VexFwkMetadataKey> companion = VexFwkMetadataKey.Companion;
        ArrayList arrayList = new ArrayList(C1196n.w0(companion, 10));
        for (VexFwkMetadataKey name : companion) {
            arrayList.add(name.getName());
        }
        Iterable<String> duplicates = getDuplicates(arrayList);
        for (String str : duplicates) {
            Log.e(TAG, "Duplicate name found: " + str);
        }
        boolean isEmpty = ((List) duplicates).isEmpty();
        VexFwkMetadataKey.Companion<VexFwkMetadataKey> companion2 = VexFwkMetadataKey.Companion;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(companion2, 10));
        for (VexFwkMetadataKey tag : companion2) {
            arrayList2.add(Integer.valueOf(tag.getTag()));
        }
        Iterable<Number> duplicates2 = getDuplicates(arrayList2);
        for (Number intValue : duplicates2) {
            int intValue2 = intValue.intValue();
            Log.e(TAG, "Duplicate name found: " + intValue2);
        }
        boolean isEmpty2 = ((List) duplicates2).isEmpty();
        if (!isEmpty || !isEmpty2) {
            return false;
        }
        return true;
    }

    private final boolean validateTagMatched() {
        boolean z;
        VexFwkMetadataKey.Companion<VexFwkMetadataKey> companion = VexFwkMetadataKey.Companion;
        if ((companion instanceof Collection) && ((Collection) companion).isEmpty()) {
            return true;
        }
        for (VexFwkMetadataKey vexFwkMetadataKey : companion) {
            int tag = VexFwkMetadataNative.Companion.getTag(vexFwkMetadataKey.getName());
            if (tag != vexFwkMetadataKey.getTag()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                String name = vexFwkMetadataKey.getName();
                int tag2 = vexFwkMetadataKey.getTag();
                StringBuilder u = C0212a.u("Mismatched tag found: ", name, " (expected: ", tag, ", actual: ");
                u.append(tag2);
                u.append(")");
                Log.e(TAG, u.toString());
                continue;
            }
            if (z) {
                return false;
            }
        }
        return true;
    }

    public final boolean validate() {
        return validate(false);
    }

    public final boolean validate(boolean z) {
        Log.d(TAG, "Validating VexFwkMetadataKey...");
        boolean validateKeyUniqueness = validateKeyUniqueness();
        if (!validateKeyUniqueness) {
            Log.e(TAG, "VexFwkMetadataKey has duplicate keys.");
        }
        boolean validateTagMatched = z ? validateTagMatched() : true;
        if (!validateTagMatched) {
            Log.e(TAG, "VexFwkMetadataKey has mismatched tags.");
        }
        return validateTagMatched & validateKeyUniqueness;
    }
}
