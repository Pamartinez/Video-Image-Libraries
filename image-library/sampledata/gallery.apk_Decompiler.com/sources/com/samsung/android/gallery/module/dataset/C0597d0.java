package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import java.util.function.IntConsumer;

/* renamed from: com.samsung.android.gallery.module.dataset.d0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0597d0 implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2969a;
    public final /* synthetic */ MediaDataRemasterV2 b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaDataRemasterV2.DataInfo f2970c;

    public /* synthetic */ C0597d0(MediaDataRemasterV2 mediaDataRemasterV2, MediaDataRemasterV2.DataInfo dataInfo, int i2) {
        this.f2969a = i2;
        this.b = mediaDataRemasterV2;
        this.f2970c = dataInfo;
    }

    public final void accept(int i2) {
        switch (this.f2969a) {
            case 0:
                this.b.lambda$swapChildInternal$11(this.f2970c, i2);
                return;
            default:
                this.b.lambda$compareChildData$10(this.f2970c, i2);
                return;
        }
    }
}
