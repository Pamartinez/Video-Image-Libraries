package com.samsung.android.sum.core.filter.factory;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaFilterFactory e;

    public /* synthetic */ h(MediaFilterFactory mediaFilterFactory, int i2) {
        this.d = i2;
        this.e = mediaFilterFactory;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaFilterFactory mediaFilterFactory = this.e;
        MediaFilterCreator mediaFilterCreator = (MediaFilterCreator) obj;
        switch (i2) {
            case 0:
                mediaFilterFactory.lambda$init$1(mediaFilterCreator);
                return;
            default:
                mediaFilterFactory.lambda$init$3(mediaFilterCreator);
                return;
        }
    }
}
