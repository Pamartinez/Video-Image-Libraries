package com.samsung.android.gallery.module.smartswitch;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final void accept(Object obj) {
        ((Blackboard) obj).publish("command://RemoveSiblingsFragments", new String[]{GalleryPreference.getInstance().loadString("location://variable/currentv1", "location://timeline")});
    }
}
