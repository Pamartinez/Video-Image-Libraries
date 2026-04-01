package com.google.protobuf;

/* renamed from: com.google.protobuf.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0152y implements U {
    public static final C0152y b = new C0152y(0);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1632a;

    public /* synthetic */ C0152y(int i2) {
        this.f1632a = i2;
    }

    public final f0 a(Class cls) {
        switch (this.f1632a) {
            case 0:
                Class cls2 = GeneratedMessageLite.class;
                if (cls2.isAssignableFrom(cls)) {
                    try {
                        return (f0) GeneratedMessageLite.getDefaultInstance(cls.asSubclass(cls2)).buildMessageInfo();
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }

    public final boolean b(Class cls) {
        switch (this.f1632a) {
            case 0:
                return GeneratedMessageLite.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }
}
