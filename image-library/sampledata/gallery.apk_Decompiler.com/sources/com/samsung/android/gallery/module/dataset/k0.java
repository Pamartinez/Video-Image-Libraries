package com.samsung.android.gallery.module.dataset;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k0 implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataSearchCreatureSelectForRelation e;

    public /* synthetic */ k0(MediaDataSearchCreatureSelectForRelation mediaDataSearchCreatureSelectForRelation, int i2) {
        this.d = i2;
        this.e = mediaDataSearchCreatureSelectForRelation;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaDataSearchCreatureSelectForRelation mediaDataSearchCreatureSelectForRelation = this.e;
        String str = (String) obj;
        switch (i2) {
            case 0:
                mediaDataSearchCreatureSelectForRelation.lambda$onCreate$2(str);
                return;
            case 1:
                mediaDataSearchCreatureSelectForRelation.lambda$reopenChildMediaData$7(str);
                return;
            default:
                mediaDataSearchCreatureSelectForRelation.lambda$new$0(str);
                return;
        }
    }
}
