package com.samsung.android.gallery.module.lottie.recorder;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.transformer.EditedMediaItem;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ ArrayList d;

    public /* synthetic */ c(ArrayList arrayList) {
        this.d = arrayList;
    }

    public final void accept(Object obj) {
        this.d.add(new EditedMediaItem.Builder(new MediaItem.Builder().setUri((Uri) obj).build()).build());
    }
}
