package com.samsung.android.sum.core.plugin;

import android.content.Context;
import com.samsung.android.sum.core.descriptor.MFDescriptorParser;
import com.samsung.android.sum.core.functional.DescriptorStreamLoader;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4140a;
    public final /* synthetic */ Object b;

    public /* synthetic */ m(int i2, Object obj) {
        this.f4140a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4140a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return MFDescriptorParser.of(MFDescriptorParser.Type.JSON).parse(((DescriptorStreamLoader) obj2).load((Context) obj));
            case 1:
                return ((ImgpPlugin) obj2).lambda$getImgProcessor$1((Enum) obj);
            default:
                return ((PluginFixture) obj2).lambda$getOperator$3((Enum) obj);
        }
    }
}
