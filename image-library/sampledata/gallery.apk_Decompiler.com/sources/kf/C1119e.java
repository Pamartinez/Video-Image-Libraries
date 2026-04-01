package kf;

import B1.b;
import G0.c;
import G0.e;
import Ve.a;
import Ze.x;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.security.AccessControlException;
import java.util.HashMap;
import jf.o;
import jf.q;
import og.k;
import qf.C1235b;
import qf.C1236c;

/* renamed from: kf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1119e implements q {
    public static final boolean l;
    public static final HashMap m;
    public int[] d;
    public String e;
    public int f;
    public String[] g;

    /* renamed from: h  reason: collision with root package name */
    public String[] f4659h;

    /* renamed from: i  reason: collision with root package name */
    public String[] f4660i;

    /* renamed from: j  reason: collision with root package name */
    public C1116b f4661j;
    public String[] k;

    static {
        try {
            l = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(System.getProperty("kotlin.ignore.old.metadata"));
        } catch (AccessControlException unused) {
            l = false;
        }
        HashMap hashMap = new HashMap();
        m = hashMap;
        hashMap.put(k.U(new C1236c("kotlin.jvm.internal.KotlinClass")), C1116b.CLASS);
        hashMap.put(k.U(new C1236c("kotlin.jvm.internal.KotlinFileFacade")), C1116b.FILE_FACADE);
        hashMap.put(k.U(new C1236c("kotlin.jvm.internal.KotlinMultifileClass")), C1116b.MULTIFILE_CLASS);
        hashMap.put(k.U(new C1236c("kotlin.jvm.internal.KotlinMultifileClassPart")), C1116b.MULTIFILE_CLASS_PART);
        hashMap.put(k.U(new C1236c("kotlin.jvm.internal.KotlinSyntheticClass")), C1116b.SYNTHETIC_CLASS);
    }

    public final o a(C1235b bVar, a aVar) {
        C1116b bVar2;
        C1236c a7 = bVar.a();
        if (a7.equals(x.f3963a)) {
            return new e((Object) this);
        }
        if (a7.equals(x.f3968o)) {
            return new b(15, this);
        }
        if (l || this.f4661j != null || (bVar2 = (C1116b) m.get(bVar)) == null) {
            return null;
        }
        this.f4661j = bVar2;
        return new c(15, (Object) this);
    }
}
