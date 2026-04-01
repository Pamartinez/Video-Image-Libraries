package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StringJoiner e;

    public /* synthetic */ c(StringJoiner stringJoiner, int i2) {
        this.d = i2;
        this.e = stringJoiner;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StringJoiner stringJoiner = this.e;
        switch (i2) {
            case 0:
                stringJoiner.add(Long.toString(((Long) obj).longValue()));
                return;
            case 1:
                ((QueryParams) obj).setGroupMediaFilter(stringJoiner.toString());
                return;
            case 2:
                stringJoiner.add(Long.toString(((Long) obj).longValue()));
                return;
            default:
                ((QueryParams) obj).setGroupMediaFilter(stringJoiner.toString());
                return;
        }
    }
}
