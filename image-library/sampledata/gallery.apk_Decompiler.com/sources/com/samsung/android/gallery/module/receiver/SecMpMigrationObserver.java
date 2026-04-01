package com.samsung.android.gallery.module.receiver;

import android.net.Uri;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SecMpMigrationObserver extends AbsContentObserver {
    public SecMpMigrationObserver() {
        super(ThreadUtil.createMainThreadHandler());
    }

    public ArrayList<Uri> getWatchUris() {
        return new ArrayList<>(Collections.singletonList(Uri.parse("content://secmedia/migration/notify/3")));
    }

    public void onChange(boolean z) {
        super.onChange(z);
        Blackboard.getApplicationInstance().post("event/secmp/migration", (Object) null);
    }
}
