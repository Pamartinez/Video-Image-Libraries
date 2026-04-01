package p1;

import com.google.android.appfunctions.schema.internal.dependencies.C0099i;
import com.google.android.appfunctions.schema.internal.dependencies.C0101k;
import com.google.android.appfunctions.schema.internal.dependencies.C0102l;
import com.google.android.appfunctions.schema.internal.dependencies.C0105o;
import com.google.android.appfunctions.schema.internal.dependencies.I;
import com.google.android.appfunctions.schema.internal.dependencies.K;
import com.google.android.appfunctions.schema.internal.dependencies.L;
import com.google.android.appfunctions.schema.internal.dependencies.a0;

/* renamed from: p1.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0254b extends C0102l {
    private static final C0254b DEFAULT_INSTANCE;
    public static final int MEDIA_ITEMS_FIELD_NUMBER = 1;
    private static volatile I PARSER;
    private C0105o mediaItems_ = K.f1208h;

    static {
        C0254b bVar = new C0254b();
        DEFAULT_INSTANCE = bVar;
        C0102l.g(C0254b.class, bVar);
    }

    public static C0253a i() {
        return (C0253a) ((C0099i) DEFAULT_INSTANCE.b(C0101k.zze));
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [java.lang.Object, com.google.android.appfunctions.schema.internal.dependencies.I] */
    public final Object b(C0101k kVar) {
        I i2;
        int ordinal = kVar.ordinal();
        if (ordinal == 0) {
            return (byte) 1;
        }
        if (ordinal == 2) {
            return new L(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"mediaItems_", C0259g.class});
        } else if (ordinal == 3) {
            return new C0254b();
        } else {
            if (ordinal == 4) {
                return new C0099i(DEFAULT_INSTANCE);
            }
            if (ordinal == 5) {
                return DEFAULT_INSTANCE;
            }
            if (ordinal == 6) {
                I i7 = PARSER;
                if (i7 != null) {
                    return i7;
                }
                synchronized (C0254b.class) {
                    try {
                        I i8 = PARSER;
                        i2 = i8;
                        if (i8 == null) {
                            ? obj = new Object();
                            PARSER = obj;
                            i2 = obj;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return i2;
            }
            throw null;
        }
    }

    public final void j(C0259g gVar) {
        C0105o oVar = this.mediaItems_;
        if (!((a0) oVar).d) {
            int size = oVar.size();
            this.mediaItems_ = ((K) oVar).q(size + size);
        }
        this.mediaItems_.add(gVar);
    }
}
