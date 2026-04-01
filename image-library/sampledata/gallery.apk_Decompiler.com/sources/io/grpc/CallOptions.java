package io.grpc;

import B1.a;
import Df.n;
import E2.k;
import He.F;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CallOptions {

    /* renamed from: j  reason: collision with root package name */
    public static final CallOptions f4611j;

    /* renamed from: a  reason: collision with root package name */
    public final Deadline f4612a;
    public final Executor b;

    /* renamed from: c  reason: collision with root package name */
    public final CallCredentials f4613c;
    public final String d;
    public final Object[][] e;
    public final List f;
    public final Boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final Integer f4614h;

    /* renamed from: i  reason: collision with root package name */
    public final Integer f4615i;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Key {

        /* renamed from: a  reason: collision with root package name */
        public final String f4616a;

        public Key(String str) {
            this.f4616a = str;
        }

        public final String toString() {
            return this.f4616a;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Df.n, java.lang.Object] */
    static {
        ? obj = new Object();
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = 0;
        obj.e = (Object[][]) Array.newInstance(Object.class, iArr);
        obj.f = Collections.EMPTY_LIST;
        f4611j = new CallOptions(obj);
    }

    public CallOptions(n nVar) {
        this.f4612a = (Deadline) nVar.f3358a;
        this.b = (Executor) nVar.b;
        this.f4613c = (CallCredentials) nVar.f3359c;
        this.d = (String) nVar.d;
        this.e = (Object[][]) nVar.e;
        this.f = (List) nVar.f;
        this.g = (Boolean) nVar.g;
        this.f4614h = (Integer) nVar.f3360h;
        this.f4615i = (Integer) nVar.f3361i;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Df.n, java.lang.Object] */
    public static n b(CallOptions callOptions) {
        ? obj = new Object();
        obj.f3358a = callOptions.f4612a;
        obj.b = callOptions.b;
        obj.f3359c = callOptions.f4613c;
        obj.d = callOptions.d;
        obj.e = callOptions.e;
        obj.f = callOptions.f;
        obj.g = callOptions.g;
        obj.f3360h = callOptions.f4614h;
        obj.f3361i = callOptions.f4615i;
        return obj;
    }

    public final Object a(Key key) {
        F.n(key, "key");
        int i2 = 0;
        while (true) {
            Object[][] objArr = this.e;
            if (i2 >= objArr.length) {
                return null;
            }
            if (key.equals(objArr[i2][0])) {
                return objArr[i2][1];
            }
            i2++;
        }
    }

    public final CallOptions c(int i2) {
        boolean z;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        F.g(i2, "invalid maxsize %s", z);
        n b5 = b(this);
        b5.f3360h = Integer.valueOf(i2);
        return new CallOptions(b5);
    }

    public final CallOptions d(int i2) {
        boolean z;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        F.g(i2, "invalid maxsize %s", z);
        n b5 = b(this);
        b5.f3361i = Integer.valueOf(i2);
        return new CallOptions(b5);
    }

    public final CallOptions e(Key key, Object obj) {
        Object[][] objArr;
        int i2;
        F.n(key, "key");
        F.n(obj, "value");
        n b5 = b(this);
        int i7 = 0;
        while (true) {
            objArr = this.e;
            if (i7 >= objArr.length) {
                i7 = -1;
                break;
            } else if (key.equals(objArr[i7][0])) {
                break;
            } else {
                i7++;
            }
        }
        int length = objArr.length;
        if (i7 == -1) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = length + i2;
        Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, iArr);
        b5.e = objArr2;
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        if (i7 == -1) {
            ((Object[][]) b5.e)[objArr.length] = new Object[]{key, obj};
        } else {
            ((Object[][]) b5.e)[i7] = new Object[]{key, obj};
        }
        return new CallOptions(b5);
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4612a, "deadline");
        Class<?> cls = null;
        V.a((Object) null, "authority");
        V.a(this.f4613c, "callCredentials");
        Executor executor = this.b;
        if (executor != null) {
            cls = executor.getClass();
        }
        V.a(cls, "executor");
        V.a(this.d, "compressorName");
        V.a(Arrays.deepToString(this.e), "customOptions");
        V.c("waitForReady", Boolean.TRUE.equals(this.g));
        V.a(this.f4614h, "maxInboundMessageSize");
        V.a(this.f4615i, "maxOutboundMessageSize");
        V.a(this.f, "streamTracerFactories");
        return V.toString();
    }
}
