package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import java.util.Set;
import java.util.Vector;
import java.util.function.BiConsumer;

/* renamed from: com.samsung.android.gallery.module.dataset.e0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0599e0 implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaDataRemasterV2 f2972a;
    public final /* synthetic */ Set b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaDataRemasterV2.DataInfo f2973c;

    public /* synthetic */ C0599e0(MediaDataRemasterV2 mediaDataRemasterV2, Set set, MediaDataRemasterV2.DataInfo dataInfo) {
        this.f2972a = mediaDataRemasterV2;
        this.b = set;
        this.f2973c = dataInfo;
    }

    public final void accept(Object obj, Object obj2) {
        this.f2972a.lambda$loadItems$4(this.b, this.f2973c, (Integer) obj, (Vector) obj2);
    }
}
