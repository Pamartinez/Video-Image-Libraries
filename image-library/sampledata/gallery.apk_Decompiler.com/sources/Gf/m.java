package Gf;

import Ae.a;
import Ae.b;
import G0.e;
import Tf.n;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class m implements p {
    public static final String d;
    public static final b e = new m("NO_LOCKS", a.d);

    /* renamed from: a  reason: collision with root package name */
    public final o f3416a;
    public final a b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3417c;

    /* JADX WARNING: type inference failed for: r0v3, types: [Gf.b, Gf.m] */
    static {
        String str;
        String canonicalName = m.class.getCanonicalName();
        j.e(canonicalName, "<this>");
        int E02 = n.E0(0, 6, canonicalName, ".");
        if (E02 == -1) {
            str = "";
        } else {
            str = canonicalName.substring(0, E02);
            j.d(str, "substring(...)");
        }
        d = str;
    }

    public m(String str) {
        this(str, new e((Object) new ReentrantLock()));
    }

    public static void e(AssertionError assertionError) {
        StackTraceElement[] stackTrace = assertionError.getStackTrace();
        int length = stackTrace.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (!stackTrace[i2].getClassName().startsWith(d)) {
                break;
            } else {
                i2++;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i2, length);
        assertionError.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Gf.h, Gf.i] */
    public final i a(a aVar) {
        return new h(this, aVar);
    }

    public final e b(b bVar) {
        return new e(this, new ConcurrentHashMap(3, 1.0f, 2), bVar, 1);
    }

    public final j c(b bVar) {
        return new j(this, new ConcurrentHashMap(3, 1.0f, 2), bVar);
    }

    public l d(Object obj, String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder("Recursion detected ");
        sb2.append(str);
        if (obj == null) {
            str2 = "";
        } else {
            str2 = "on input: " + obj;
        }
        sb2.append(str2);
        sb2.append(" under ");
        sb2.append(this);
        AssertionError assertionError = new AssertionError(sb2.toString());
        e(assertionError);
        throw assertionError;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append(Log.TAG_SEPARATOR);
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append(" (");
        return C0212a.p(sb2, this.f3417c, ")");
    }

    public m(String str, o oVar) {
        a aVar = a.e;
        this.f3416a = oVar;
        this.b = aVar;
        this.f3417c = str;
    }
}
