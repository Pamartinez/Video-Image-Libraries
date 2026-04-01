package com.samsung.android.gallery.app.controller.internals;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.AddTagBaseCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagViaDialogCmd extends AddTagBaseCmd {
    public AddTagViaDialogCmd(MediaItem mediaItem, ArrayList<MediaItem> arrayList, AddTagBaseCmd.OnMyTagListener onMyTagListener) {
        super(mediaItem, arrayList, onMyTagListener);
    }

    public void addTag(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            String str = (String) arrayList.get(0);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (contains(str.toLowerCase(Locale.getDefault()))) {
                showToast((int) R.string.tag_already_added);
                return;
            }
            ArrayList<String> prevList = getPrevList();
            prevList.add(str);
            onTagSelected(prevList);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (getPrevListCount() >= 30) {
            showToast(getContext().getString(R.string.you_cant_add_more_than_limits_tags, new Object[]{30}));
        } else {
            super.onExecute(eventContext, objArr);
        }
    }
}
