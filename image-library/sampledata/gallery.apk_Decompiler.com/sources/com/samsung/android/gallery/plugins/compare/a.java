package com.samsung.android.gallery.plugins.compare;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                CompareActivity.lambda$updateGridViews$11((ArrayList) obj2, (CompareActivity.PhotoViewHolder) obj);
                return;
            case 1:
                ((CompareActivity.PhotoViewHolder) obj).imageView.setLogTag(String.valueOf(((AtomicInteger) obj2).getAndIncrement()));
                return;
            default:
                ((StringJoiner) obj2).add(String.valueOf(((MediaItem) obj).getFileId()));
                return;
        }
    }
}
