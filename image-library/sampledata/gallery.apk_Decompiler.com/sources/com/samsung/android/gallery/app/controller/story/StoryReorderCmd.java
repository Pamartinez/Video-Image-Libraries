package com.samsung.android.gallery.app.controller.story;

import Ab.b;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryReorderCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateOrder$0(int i2, List list) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean updateContentOrder = DbCompat.storyApi().updateContentOrder(i2, list);
        String str = this.TAG;
        Boolean valueOf = Boolean.valueOf(updateContentOrder);
        Integer valueOf2 = Integer.valueOf(list.size());
        Log.d(str, "updateOrder", valueOf, valueOf2, " + " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void updateOrder(int i2, List<FileItemInterface> list) {
        ThreadUtil.postOnBgThread(new b((Object) this, i2, (Object) list, 28));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (Features.isEnabled(Features.SUPPORT_STORY_REORDER)) {
            MediaItem headerItem = eventContext.getHeaderItem();
            if (headerItem == null) {
                Log.e(this.TAG, "reorder failed due to no header");
                return;
            }
            updateOrder(headerItem.getAlbumID(), (List) Arrays.stream(eventContext.getAllItems()).collect(Collectors.toList()));
        }
    }
}
