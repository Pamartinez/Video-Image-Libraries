package com.samsung.android.gallery.module.details;

import android.text.SpannableString;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsRelatedDataLoader;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((DetailsRelatedDataLoader.CreatureGroupConverter) obj2).lambda$invoke$1((MediaItem) obj);
                return;
            case 1:
                ((DetailsRelatedDataLoader.CreatureGroupConverter) obj2).lambda$invoke$0((Long) obj);
                return;
            default:
                ((EditDetailsModel) obj2).lambda$new$0((SpannableString) obj);
                return;
        }
    }
}
