package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.functional.DescriptorLoader;
import com.samsung.android.sum.core.plugin.ImgpPlugin;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements DescriptorLoader {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4138a;

    public /* synthetic */ f(int i2) {
        this.f4138a = i2;
    }

    public final MFDescriptor load() {
        switch (this.f4138a) {
            case 0:
                return MFDescriptor.newBuilder().setImgpPluginType(ImgpPlugin.Type.NATIVE_UNIIMGP).build(ImgpDescriptor.class);
            default:
                return MFDescriptor.newBuilder().setImgpPluginType(ImgpPlugin.Type.SIMGP).build(ImgpDescriptor.class);
        }
    }
}
