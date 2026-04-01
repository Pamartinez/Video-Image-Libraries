package com.samsung.android.sum.core.filter;

import android.media.MediaExtractor;
import android.util.Pair;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ MediaParserFilter d;
    public final /* synthetic */ MediaExtractor e;
    public final /* synthetic */ long f;
    public final /* synthetic */ int g;

    public /* synthetic */ l(MediaParserFilter mediaParserFilter, MediaExtractor mediaExtractor, long j2, int i2) {
        this.d = mediaParserFilter;
        this.e = mediaExtractor;
        this.f = j2;
        this.g = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$run$4(this.e, this.f, this.g, (Pair) obj);
    }
}
