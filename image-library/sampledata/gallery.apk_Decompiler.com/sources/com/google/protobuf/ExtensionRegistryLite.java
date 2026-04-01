package com.google.protobuf;

import java.util.Collections;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExtensionRegistryLite {

    /* renamed from: a  reason: collision with root package name */
    public static volatile ExtensionRegistryLite f1579a;
    public static final ExtensionRegistryLite b;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.ExtensionRegistryLite, java.lang.Object] */
    static {
        ? obj = new Object();
        Map map = Collections.EMPTY_MAP;
        b = obj;
    }

    public static ExtensionRegistryLite a() {
        ExtensionRegistryLite extensionRegistryLite;
        ExtensionRegistryLite extensionRegistryLite2 = f1579a;
        if (extensionRegistryLite2 != null) {
            return extensionRegistryLite2;
        }
        synchronized (ExtensionRegistryLite.class) {
            try {
                extensionRegistryLite = f1579a;
                if (extensionRegistryLite == null) {
                    Class cls = C0145q.f1627a;
                    ExtensionRegistryLite extensionRegistryLite3 = null;
                    if (cls != null) {
                        try {
                            extensionRegistryLite3 = (ExtensionRegistryLite) cls.getDeclaredMethod("getEmptyRegistry", (Class[]) null).invoke((Object) null, (Object[]) null);
                        } catch (Exception unused) {
                        }
                    }
                    if (extensionRegistryLite3 != null) {
                        extensionRegistryLite = extensionRegistryLite3;
                    } else {
                        extensionRegistryLite = b;
                    }
                    f1579a = extensionRegistryLite;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return extensionRegistryLite;
    }
}
