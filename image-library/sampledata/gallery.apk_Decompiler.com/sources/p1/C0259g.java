package p1;

import com.google.android.appfunctions.schema.internal.dependencies.C0099i;
import com.google.android.appfunctions.schema.internal.dependencies.C0101k;
import com.google.android.appfunctions.schema.internal.dependencies.C0102l;
import com.google.android.appfunctions.schema.internal.dependencies.I;
import com.google.android.appfunctions.schema.internal.dependencies.L;

/* renamed from: p1.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0259g extends C0102l {
    private static final C0259g DEFAULT_INSTANCE;
    public static final int MEDIA_URL_FIELD_NUMBER = 2;
    private static volatile I PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    public static final int VIDEO_METADATA_FIELD_NUMBER = 3;
    private int bitField0_;
    private String mediaUrl_ = "";
    private int type_;
    private C0258f videoMetadata_;

    static {
        C0259g gVar = new C0259g();
        DEFAULT_INSTANCE = gVar;
        C0102l.g(C0259g.class, gVar);
    }

    public static C0255c i() {
        return (C0255c) ((C0099i) DEFAULT_INSTANCE.b(C0101k.zze));
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [java.lang.Object, com.google.android.appfunctions.schema.internal.dependencies.I] */
    public final Object b(C0101k kVar) {
        I i2;
        int ordinal = kVar.ordinal();
        if (ordinal == 0) {
            return (byte) 1;
        }
        if (ordinal == 2) {
            return new L(DEFAULT_INSTANCE, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ለ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "type_", "mediaUrl_", "videoMetadata_"});
        } else if (ordinal == 3) {
            return new C0259g();
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
                synchronized (C0259g.class) {
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

    public final void j(C0256d dVar) {
        this.type_ = dVar.a();
        this.bitField0_ |= 1;
    }

    public final void k(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.mediaUrl_ = str;
    }

    public final void l(C0258f fVar) {
        this.videoMetadata_ = fVar;
        this.bitField0_ |= 4;
    }
}
