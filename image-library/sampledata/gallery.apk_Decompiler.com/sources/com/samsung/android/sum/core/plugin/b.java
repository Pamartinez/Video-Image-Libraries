package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.functional.DescriptorLoader;
import com.samsung.android.sum.core.plugin.PluginStore;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4136a;

    public /* synthetic */ b(int i2) {
        this.f4136a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4136a) {
            case 0:
                return ((ImgpPlugin) obj).operatorMap.entrySet().stream();
            case 1:
                return Optional.ofNullable(((PluginStore) obj).getContext());
            case 2:
                return ((PluginStore) obj).registry;
            case 3:
                return (PluginStore.Entry) ((Map.Entry) obj).getValue();
            case 4:
                return ((Plugin) obj).stream();
            case 5:
                return ((ParameterizedType) ((Type) obj)).getActualTypeArguments()[0].getTypeName();
            default:
                return ((DescriptorLoader) obj).load();
        }
    }
}
