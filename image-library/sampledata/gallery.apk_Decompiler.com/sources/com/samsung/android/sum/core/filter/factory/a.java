package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWProfile;
import com.samsung.android.sum.core.filter.ContentFilterRegister;
import com.samsung.android.sum.core.filter.DecoratedPluginFilter;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.filter.MediaFilterBase;
import com.samsung.android.sum.core.filter.PluginFilter;
import com.samsung.android.sum.core.filter.StaplePluginFilter;
import com.samsung.android.sum.core.filter.factory.MediaFilterCreatorChain;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4081a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f4081a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4081a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return MediaFilterCreatorChain.lambda$prepare$0((Map) obj2, (Enum) obj);
            case 1:
                return ((MediaFilterCreatorChain.DescriptorFinder) obj).find((MFDescriptor) obj2);
            case 2:
                return NNFilterCreator.lambda$newFilter$0((NNDescriptor) obj2, (NNFWProfile) obj);
            case 3:
                return PluginFilterCreator.lambda$createStreamFilter$8((PluginFilter) obj2, (ContentFilterRegister) obj);
            case 4:
                return PluginFilterCreator.lambda$createDecorateFilter$7((DecoratedPluginFilter) obj2, (ContentFilterRegister) obj);
            case 5:
                return PluginFilterCreator.lambda$createNNFilter$12((MediaFilterBase) obj2, (ContentFilterRegister) obj);
            case 6:
                return PluginFilterCreator.lambda$createPluginFilter$6((StaplePluginFilter) obj2, (ContentFilterRegister) obj);
            case 7:
                return ((PluginFilterCreator) obj2).lambda$createImgpFilter$3((String) obj);
            default:
                return PluginFilterCreator.lambda$createImgpFilter$5((ImgpFilter) obj2, (ContentFilterRegister) obj);
        }
    }
}
