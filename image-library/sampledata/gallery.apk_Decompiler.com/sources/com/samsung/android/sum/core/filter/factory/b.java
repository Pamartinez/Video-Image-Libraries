package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.types.nn.NNFileDescriptor;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4082a;

    public /* synthetic */ b(int i2) {
        this.f4082a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4082a) {
            case 0:
                return MediaFilterCreatorChain.lambda$prepare$1((MediaFilterCreator) obj);
            case 1:
                return MediaFilterFactory.lambda$init$0((MediaFilterCreator) obj);
            case 2:
                return MediaFilterFactory.lambda$init$2((List) obj);
            case 3:
                return Objects.nonNull((NNFileDescriptor) obj);
            case 4:
                return PluginFilterCreator.lambda$createImgpFilter$0((ImgpPlugin.Type) obj);
            default:
                return ((Optional) obj).isPresent();
        }
    }
}
