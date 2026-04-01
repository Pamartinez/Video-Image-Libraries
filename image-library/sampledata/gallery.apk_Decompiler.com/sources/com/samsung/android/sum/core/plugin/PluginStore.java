package com.samsung.android.sum.core.plugin;

import android.content.Context;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.C0923a;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginStore {
    private static final String TAG = Def.tagOf((Class<?>) PluginStore.class);
    protected Context context;
    /* access modifiers changed from: protected */
    public Map<String, Entry> registry;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Entry {
        MFDescriptor descriptor;
        PluginFixture<?> pluginFixture;

        public Entry(MFDescriptor mFDescriptor, PluginFixture<?> pluginFixture2) {
            this.descriptor = mFDescriptor;
            this.pluginFixture = pluginFixture2;
        }

        public MFDescriptor getDescriptor() {
            return this.descriptor;
        }

        public PluginFixture<?> getPluginFixture() {
            return this.pluginFixture;
        }

        public void setDescriptor(MFDescriptor mFDescriptor) {
            this.descriptor = mFDescriptor;
        }

        public void setPluginFixture(PluginFixture<?> pluginFixture2) {
            this.pluginFixture = pluginFixture2;
        }
    }

    public PluginStore(Context context2) {
        this.registry = new LinkedHashMap();
        this.context = context2;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.samsung.android.sum.core.plugin.Plugin<?>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getPluginName(com.samsung.android.sum.core.plugin.Plugin<?> r2) {
        /*
            r1 = this;
            boolean r1 = r2 instanceof com.samsung.android.sum.core.plugin.PluginAdapter
            if (r1 == 0) goto L_0x000f
            com.samsung.android.sum.core.plugin.PluginAdapter r2 = (com.samsung.android.sum.core.plugin.PluginAdapter) r2
            java.lang.Class r1 = r2.getPluginType()
            java.lang.String r1 = r1.getName()
            return r1
        L_0x000f:
            java.lang.Class r1 = r2.getClass()
            java.lang.reflect.Type[] r2 = r1.getGenericInterfaces()
        L_0x0017:
            int r0 = r2.length
            if (r0 != 0) goto L_0x002a
            java.lang.Class r1 = r1.getSuperclass()
            if (r1 == 0) goto L_0x002a
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            if (r1 != r0) goto L_0x0025
            goto L_0x002a
        L_0x0025:
            java.lang.reflect.Type[] r2 = r1.getGenericInterfaces()
            goto L_0x0017
        L_0x002a:
            java.util.stream.Stream r1 = java.util.Arrays.stream(r2)
            com.samsung.android.sum.core.plugin.k r2 = new com.samsung.android.sum.core.plugin.k
            r0 = 2
            r2.<init>(r0)
            java.util.stream.Stream r1 = r1.filter(r2)
            com.samsung.android.sum.core.plugin.b r2 = new com.samsung.android.sum.core.plugin.b
            r0 = 5
            r2.<init>(r0)
            java.util.stream.Stream r1 = r1.map(r2)
            com.samsung.android.sum.core.plugin.k r2 = new com.samsung.android.sum.core.plugin.k
            r0 = 0
            r2.<init>(r0)
            java.util.stream.Stream r1 = r1.filter(r2)
            java.util.Optional r1 = r1.findFirst()
            com.samsung.android.sum.core.channel.h r2 = new com.samsung.android.sum.core.channel.h
            r2.<init>(r0)
            java.lang.Object r1 = r1.orElseThrow(r2)
            java.lang.String r1 = (java.lang.String) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.plugin.PluginStore.getPluginName(com.samsung.android.sum.core.plugin.Plugin):java.lang.String");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$add$3(Plugin plugin) {
        PluginFixture pluginFixture;
        try {
            String pluginName = getPluginName(plugin);
            String str = TAG;
            SLog.d(str, "found plugin type:" + pluginName);
            if (ImgpPlugin.class.getName().equals(pluginName)) {
                pluginFixture = new ImgpPlugin(plugin);
            } else if (NNPlugin.class.getName().equals(pluginName)) {
                pluginFixture = new NNPlugin(plugin);
            } else if (StaplePluginFixture.class.getName().equals(pluginName)) {
                pluginFixture = new StaplePluginFixture(plugin);
            } else if (StreamPluginFixture.class.getName().equals(pluginName)) {
                pluginFixture = new StreamPluginFixture(plugin);
            } else {
                throw new IllegalArgumentException("unknown plugin type: " + pluginName);
            }
            pluginFixture.init(this.context);
            add((PluginFixture<?>) pluginFixture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getPluginName$0(Type type) {
        return type instanceof ParameterizedType;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$of$5(PluginStore pluginStore) {
        if (pluginStore.context != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$of$8(Map map, Map map2) {
        return (Map) Stream.concat(map.entrySet().stream(), map2.entrySet().stream()).collect(Collectors.toMap(new C0923a(17), new b(3)));
    }

    public static PluginStore of() {
        return of((Context) null);
    }

    public abstract PluginStore add(PluginFixture<?> pluginFixture);

    public PluginStore add(Plugin<?>... pluginArr) {
        Arrays.stream(pluginArr).flatMap(new b(4)).forEach(new i(1, this));
        return this;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.function.Consumer, java.lang.Object] */
    public synchronized void clear() {
        this.registry.values().forEach(new Object());
        this.registry.clear();
    }

    public abstract Entry get(MFDescriptor mFDescriptor);

    public Context getContext() {
        return this.context;
    }

    public Stream<String> keyStream() {
        return this.registry.keySet().stream();
    }

    public abstract PluginFixture<?> remove(PluginFixture<?> pluginFixture);

    public static PluginStore of(Context context2) {
        return new StaplePluginStore(context2);
    }

    public static PluginStore of(PluginStore... pluginStoreArr) {
        return of((List<PluginStore>) Arrays.asList(pluginStoreArr));
    }

    public PluginStore(Context context2, Map<String, Entry> map) {
        this.context = context2;
        this.registry = map;
    }

    public static PluginStore of(List<PluginStore> list) {
        Def.require(!list.isEmpty());
        PluginStore of2 = of((Context) list.stream().filter(new k(1)).findFirst().flatMap(new b(1)).orElse((Object) null));
        if (list.size() == 1) {
            of2.registry = list.get(0).registry;
            return of2;
        }
        of2.registry = (Map) list.stream().map(new b(2)).reduce(new c(1)).orElse(new HashMap());
        return of2;
    }
}
