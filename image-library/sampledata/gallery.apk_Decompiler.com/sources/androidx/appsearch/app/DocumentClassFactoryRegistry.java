package androidx.appsearch.app;

import androidx.appsearch.exceptions.AppSearchException;
import androidx.core.util.Preconditions;
import i.C0212a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DocumentClassFactoryRegistry {
    private static volatile DocumentClassFactoryRegistry sInstance;
    private final Map<Class<?>, DocumentClassFactory<?>> mFactories = new HashMap();

    private DocumentClassFactoryRegistry() {
    }

    public static DocumentClassFactoryRegistry getInstance() {
        if (sInstance == null) {
            synchronized (DocumentClassFactoryRegistry.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DocumentClassFactoryRegistry();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private DocumentClassFactory<?> loadFactoryByReflection(Class<?> cls) {
        String str;
        Package packageR = cls.getPackage();
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            if (packageR != null) {
                str = packageR.getName() + ".";
                canonicalName = canonicalName.substring(str.length()).replace(".", "$$__");
            } else {
                str = "";
            }
            String B = C0212a.B(str, "$$__AppSearch__", canonicalName);
            Class<? super Object> cls2 = null;
            try {
                try {
                    return (DocumentClassFactory) Class.forName(B).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                } catch (Exception e) {
                    throw new AppSearchException(2, C0212a.m("Failed to construct document class converter \"", B, "\""), e);
                }
            } catch (ClassNotFoundException e7) {
                Class<? super Object> superclass = cls.getSuperclass();
                Class[] interfaces = cls.getInterfaces();
                if (superclass != Object.class) {
                    cls2 = superclass;
                }
                int length = interfaces.length;
                if (cls2 != null) {
                    length++;
                }
                if (length != 1) {
                    String m = C0212a.m("Failed to find document class converter \"", B, "\". Perhaps the annotation processor was not run or the class was proguarded out?");
                    if (length > 1) {
                        m = C0212a.A(m, " Or, this class may not have been annotated with @Document, and there is an ambiguity to determine a unique @Document annotated parent class/interface.");
                    }
                    throw new AppSearchException(2, m, e7);
                } else if (cls2 != null) {
                    return loadFactoryByReflection(cls2);
                } else {
                    return loadFactoryByReflection(interfaces[0]);
                }
            }
        } else {
            throw new AppSearchException(2, "Failed to find simple name for document class \"" + cls + "\". Perhaps it is anonymous?");
        }
    }

    public <T> DocumentClassFactory<T> getOrCreateFactory(Class<T> cls) {
        DocumentClassFactory<T> documentClassFactory;
        Preconditions.checkNotNull(cls);
        synchronized (this) {
            documentClassFactory = this.mFactories.get(cls);
        }
        if (documentClassFactory != null) {
            return documentClassFactory;
        }
        DocumentClassFactory<?> loadFactoryByReflection = loadFactoryByReflection(cls);
        synchronized (this) {
            try {
                DocumentClassFactory<?> documentClassFactory2 = this.mFactories.get(cls);
                if (documentClassFactory2 == null) {
                    this.mFactories.put(cls, loadFactoryByReflection);
                } else {
                    loadFactoryByReflection = documentClassFactory2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return loadFactoryByReflection;
    }
}
