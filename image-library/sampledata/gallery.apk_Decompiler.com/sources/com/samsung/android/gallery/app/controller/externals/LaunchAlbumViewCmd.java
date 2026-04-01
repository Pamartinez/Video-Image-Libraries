package com.samsung.android.gallery.app.controller.externals;

import M9.o;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LaunchAlbumViewCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        getBlackboard().post("command://request_suicide", (Object) null);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent.setAction("com.android.gallery.action.ALBUM_VIEW");
            getActivity().startActivity(intent);
            ThreadUtil.postOnBgThreadDelayed(new o(1, this), 0);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "launchAlbumView failed", (Throwable) e);
            getBlackboard().post("command://request_suicide", (Object) null);
        }
    }
}
