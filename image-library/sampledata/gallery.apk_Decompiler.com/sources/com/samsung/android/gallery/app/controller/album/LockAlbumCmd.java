package com.samsung.android.gallery.app.controller.album;

import A.a;
import A8.C0545b;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LockAlbumCmd extends BaseCommand {
    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        boolean z3;
        try {
            boolean booleanValue = objArr[0].booleanValue();
            MediaItem mediaItem = objArr[1];
            if (booleanValue) {
                if (mediaItem.isMergedAlbum()) {
                    z3 = LockedAlbum.getInstance().add((List<Integer>) (List) mediaItem.getChildList().stream().mapToInt(new C0545b(2)).boxed().collect(Collectors.toList()));
                } else {
                    z3 = LockedAlbum.getInstance().add(mediaItem.getAlbumID());
                }
                if (z3) {
                    int firstItemDataPositionFromSelected = eventContext.getFirstItemDataPositionFromSelected();
                    getBlackboard().postEvent(EventMessage.obtain(1003));
                    getBlackboard().postBroadcastEvent(EventMessage.obtain(2009, Integer.valueOf(firstItemDataPositionFromSelected)));
                    Blackboard.getApplicationInstance().post("global://setting/albums/lockedAlbums", Boolean.TRUE);
                    return;
                }
                return;
            }
            if (mediaItem.isMergedAlbum()) {
                z = LockedAlbum.getInstance().remove((List<Integer>) (List) mediaItem.getChildList().stream().mapToInt(new C0545b(2)).boxed().collect(Collectors.toList()));
            } else {
                z = LockedAlbum.getInstance().remove(mediaItem.getAlbumID());
            }
            if (z) {
                getBlackboard().postBroadcastEvent(EventMessage.obtain(2009));
                Blackboard.getApplicationInstance().post("global://setting/albums/lockedAlbums", Boolean.FALSE);
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("LockAlbumCmd failed. e="), this.TAG);
        }
    }
}
