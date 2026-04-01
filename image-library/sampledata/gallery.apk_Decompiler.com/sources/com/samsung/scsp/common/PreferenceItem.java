package com.samsung.scsp.common;

import A4.I;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.scsp.error.FaultBarrier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreferenceItem<T> implements Supplier<T>, Consumer<T> {
    private static final Map<Class<?>, BiConsumer<PreferenceItem<?>, Object>> SETTERS;
    private final T defaultValue;
    private final String name;
    private final Preferences preferences;

    static {
        HashMap hashMap = new HashMap();
        SETTERS = hashMap;
        hashMap.put(Boolean.class, new f(0));
        hashMap.put(Float.class, new f(1));
        hashMap.put(Integer.class, new f(2));
        hashMap.put(Long.class, new f(3));
        hashMap.put(String.class, new f(4));
        hashMap.put(Set.class, new f(5));
    }

    public PreferenceItem(Preferences preferences2, String str, T t) {
        this.preferences = preferences2;
        this.name = str;
        this.defaultValue = t;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$accept$8(Object obj, Map.Entry entry) {
        ((BiConsumer) entry.getValue()).accept(this, obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$get$6() {
        Object obj = this.preferences.sharedPreferences.getAll().get(this.name);
        if (obj != null) {
            return obj;
        }
        return this.defaultValue;
    }

    public void accept(T t) {
        SETTERS.entrySet().stream().filter(new I(25, t)).findAny().ifPresent(new g(this, t));
    }

    public T get() {
        return FaultBarrier.get(new a(10, this), this.defaultValue).obj;
    }

    public void remove() {
        this.preferences.sharedPreferences.edit().remove(this.name).apply();
    }
}
