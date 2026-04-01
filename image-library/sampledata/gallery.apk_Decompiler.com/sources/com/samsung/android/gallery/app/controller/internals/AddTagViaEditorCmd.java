package com.samsung.android.gallery.app.controller.internals;

import A.a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.AddTagBaseCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagViaEditorCmd extends AddTagBaseCmd {
    public AddTagViaEditorCmd(MediaItem mediaItem, ArrayList<MediaItem> arrayList, AddTagBaseCmd.OnMyTagListener onMyTagListener) {
        super(mediaItem, arrayList, onMyTagListener);
    }

    public void onTagEditorResult(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            try {
                onTagSelected((ArrayList) ((Object[]) arrayList.get(0))[0]);
            } catch (Exception e) {
                a.s(e, new StringBuilder("tag edit failed e="), this.TAG);
            }
        }
    }
}
