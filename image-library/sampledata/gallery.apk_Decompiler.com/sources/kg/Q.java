package kg;

import He.C0748d;
import a.C0068a;
import c0.C0086a;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import gg.a;
import gg.b;
import i.C0212a;
import ig.f;
import ig.i;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Q {

    /* renamed from: a  reason: collision with root package name */
    public static final f[] f4678a = new f[0];
    public static final a[] b = new a[0];

    public static final D a(a aVar, String str) {
        return new D(str, new E(aVar));
    }

    public static final Set b(f fVar) {
        j.e(fVar, "<this>");
        if (fVar instanceof C1129j) {
            return ((C1129j) fVar).a();
        }
        HashSet hashSet = new HashSet(fVar.e());
        int e = fVar.e();
        for (int i2 = 0; i2 < e; i2++) {
            hashSet.add(fVar.f(i2));
        }
        return hashSet;
    }

    public static final f[] c(List list) {
        f[] fVarArr;
        Collection collection = list;
        if (collection == null || collection.isEmpty()) {
            list = null;
        }
        if (list == null || (fVarArr = (f[]) list.toArray(new f[0])) == null) {
            return f4678a;
        }
        return fVarArr;
    }

    public static final C1141w d(String str, Enum[] enumArr, String[] strArr, Annotation[][] annotationArr) {
        j.e(enumArr, StateHandler.VALUES);
        C1139u uVar = new C1139u(str, enumArr.length);
        int length = enumArr.length;
        int i2 = 0;
        int i7 = 0;
        while (i2 < length) {
            Enum enumR = enumArr[i2];
            int i8 = i7 + 1;
            String str2 = (String) C1192j.p0(i7, strArr);
            if (str2 == null) {
                str2 = enumR.name();
            }
            uVar.k(str2, false);
            Annotation[] annotationArr2 = (Annotation[]) C1192j.p0(i7, annotationArr);
            if (annotationArr2 != null) {
                for (Annotation annotation : annotationArr2) {
                    j.e(annotation, "annotation");
                    int i10 = uVar.d;
                    List[] listArr = uVar.f;
                    List list = listArr[i10];
                    if (list == null) {
                        list = new ArrayList(1);
                        listArr[uVar.d] = list;
                    }
                    list.add(annotation);
                }
            }
            i2++;
            i7 = i8;
        }
        C1141w wVar = new C1141w(str, enumArr);
        wVar.b = uVar;
        return wVar;
    }

    public static final int e(f fVar, f[] fVarArr) {
        int i2;
        j.e(fVarArr, "typeParams");
        int hashCode = (fVar.i().hashCode() * 31) + Arrays.hashCode(fVarArr);
        i iVar = new i(fVar);
        int i7 = 1;
        int i8 = 1;
        while (true) {
            int i10 = 0;
            if (!iVar.hasNext()) {
                break;
            }
            int i11 = i8 * 31;
            String i12 = ((f) iVar.next()).i();
            if (i12 != null) {
                i10 = i12.hashCode();
            }
            i8 = i11 + i10;
        }
        i iVar2 = new i(fVar);
        while (iVar2.hasNext()) {
            int i13 = i7 * 31;
            C0068a b5 = ((f) iVar2.next()).b();
            if (b5 != null) {
                i2 = b5.hashCode();
            } else {
                i2 = 0;
            }
            i7 = i13 + i2;
        }
        return (((hashCode * 31) + i8) * 31) + i7;
    }

    public static final void f(int i2, int i7, f fVar) {
        String str;
        j.e(fVar, "descriptor");
        ArrayList arrayList = new ArrayList();
        int i8 = (~i2) & i7;
        for (int i10 = 0; i10 < 32; i10++) {
            if ((i8 & 1) != 0) {
                arrayList.add(fVar.f(i10));
            }
            i8 >>>= 1;
        }
        String i11 = fVar.i();
        j.e(i11, "serialName");
        if (arrayList.size() == 1) {
            str = C0212a.q(new StringBuilder("Field '"), (String) arrayList.get(0), "' is required for type with serial name '", i11, "', but it was missing");
        } else {
            str = "Fields " + arrayList + " are required for type with serial name '" + i11 + "', but they were missing";
        }
        throw new b(arrayList, str, (b) null);
    }

    public static final void g(String str, C0748d dVar) {
        String str2;
        j.e(dVar, "baseClass");
        String str3 = "in the polymorphic scope of '" + dVar.o() + '\'';
        if (str == null) {
            str2 = C0086a.h('.', "Class discriminator was missing and no default serializers were registered ", str3);
        } else {
            StringBuilder q = C0086a.q("Serializer for subclass '", str, "' is not found ", str3, ".\nCheck if class with serial name '");
            C0086a.z(q, str, "' exists and serializer is registered in a corresponding SerializersModule.\nTo be registered automatically, class '", str, "' has to be '@Serializable', and the base class '");
            q.append(dVar.o());
            q.append("' has to be sealed and '@Serializable'.");
            str2 = q.toString();
        }
        throw new IllegalArgumentException(str2);
    }
}
