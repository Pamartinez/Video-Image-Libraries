package com.samsung.android.sum.core.filter;

import android.media.MediaExtractor;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaParserFilter f4093a;
    public final /* synthetic */ MediaExtractor b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ FileDescriptor f4094c;
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ k(MediaParserFilter mediaParserFilter, MediaExtractor mediaExtractor, FileDescriptor fileDescriptor, int i2, ArrayList arrayList) {
        this.f4093a = mediaParserFilter;
        this.b = mediaExtractor;
        this.f4094c = fileDescriptor;
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(int i2) {
        this.f4093a.lambda$run$2(this.b, this.f4094c, this.d, this.e, i2);
    }
}
