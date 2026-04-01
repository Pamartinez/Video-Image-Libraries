package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewModel {
    private final Map<String, Object> mBagOfTags = new HashMap();
    private volatile boolean mCleared = false;
    private final Set<Closeable> mCloseables = new LinkedHashSet();

    private static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final void clear() {
        this.mCleared = true;
        Map<String, Object> map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                try {
                    for (Object closeWithRuntimeException : this.mBagOfTags.values()) {
                        closeWithRuntimeException(closeWithRuntimeException);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                try {
                    for (Closeable closeWithRuntimeException2 : this.mCloseables) {
                        closeWithRuntimeException(closeWithRuntimeException2);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        onCleared();
    }

    public <T> T getTag(String str) {
        T t;
        Map<String, Object> map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t = this.mBagOfTags.get(str);
        }
        return t;
    }

    public <T> T setTagIfAbsent(String str, T t) {
        T t3;
        synchronized (this.mBagOfTags) {
            try {
                t3 = this.mBagOfTags.get(str);
                if (t3 == null) {
                    this.mBagOfTags.put(str, t);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (t3 != null) {
            t = t3;
        }
        if (this.mCleared) {
            closeWithRuntimeException(t);
        }
        return t;
    }

    public void onCleared() {
    }
}
