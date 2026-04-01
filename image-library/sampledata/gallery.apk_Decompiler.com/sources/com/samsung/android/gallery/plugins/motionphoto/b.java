package com.samsung.android.gallery.plugins.motionphoto;

import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.support.observable.ObservableArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ ObservableArrayList d;

    public /* synthetic */ b(ObservableArrayList observableArrayList) {
        this.d = observableArrayList;
    }

    public final void accept(Object obj) {
        ((VideoCtrlView.SeekbarAdapter) obj).updateData(this.d);
    }
}
