package com.samsung.android.gallery.app.ui.list.albums.drag;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.drag.IAlbumsDragView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsDragPresenter<V extends IAlbumsDragView> extends AlbumsBasePresenter<V> {
    public AlbumsDragPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void onFolderCreated(Object obj) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9 = true;
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            z7 = ((Boolean) objArr[0]).booleanValue();
            if (z7) {
                z3 = ((Boolean) objArr[1]).booleanValue();
                int intValue = ((Integer) objArr[2]).intValue();
                String str = (String) objArr[3];
                if (z3) {
                    z = onFolderCreatedFromMenu(intValue, str, (ArrayList) objArr[4]);
                } else {
                    z = ((IAlbumsDragView) this.mView).onFolderCreatedFromDrag(intValue, str);
                }
            } else {
                z = false;
                z3 = false;
            }
        } else {
            z = false;
            z7 = false;
            z3 = false;
        }
        if (!z) {
            if (!z3 && z7) {
                z9 = false;
            }
            onFolderCreationFailed(z9, z7);
        }
    }

    private void onFolderCreatedByDrag(Object obj) {
        CreateFolderCmd.Type type;
        Object[] objArr = (Object[]) obj;
        boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
        MediaItem[] mediaItemArr = (MediaItem[]) objArr[1];
        int intValue = ((Integer) objArr[2]).intValue();
        if (((IAlbumsDragView) this.mView).isActivityResumed()) {
            CreateFolderCmd createFolderCmd = new CreateFolderCmd();
            if (booleanValue) {
                type = CreateFolderCmd.Type.GROUPING;
            } else {
                type = CreateFolderCmd.Type.UPDATE;
            }
            createFolderCmd.execute(this, mediaItemArr, type, Boolean.FALSE, getCurrentItem(), Integer.valueOf(((IAlbumsDragView) this.mView).getAdapter().getMediaItemPosition(intValue)));
            if (((IAlbumsDragView) this.mView).isSelectionMode()) {
                this.mBlackboard.postEvent(EventMessage.obtain(1003));
                return;
            }
            return;
        }
        forceReloadData();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1012) {
            onFolderCreatedByDrag(eventMessage.obj);
            return true;
        } else if (i2 != 1013) {
            return super.handleEvent(eventMessage);
        } else {
            onFolderCreated(eventMessage.obj);
            return true;
        }
    }

    public boolean isAllowItemClick() {
        if (((IAlbumsDragView) this.mView).useAdvancedMouseDragAndDrop()) {
            Blackboard blackboard = this.mBlackboard;
            Boolean bool = Boolean.FALSE;
            if (((Boolean) blackboard.pop("data://motion_event_tool_type_mouse", bool)).booleanValue() && !((IAlbumsDragView) this.mView).isMoveMode()) {
                return ((Boolean) this.mBlackboard.pop("data://gesture_on_double_tapped", bool)).booleanValue();
            }
        }
        return super.isAllowItemClick();
    }

    public boolean onFolderCreatedFromMenu(int i2, String str, ArrayList<Integer> arrayList) {
        forceReloadData();
        return true;
    }

    public void onFolderCreationFailed(boolean z, boolean z3) {
        ((IAlbumsDragView) this.mView).onFolderCreationFailed(z3);
    }

    public void updateToolbar(Toolbar toolbar) {
        if (((IAlbumsDragView) this.mView).isMoveMode()) {
            toolbar.setTitle((CharSequence) getContext().getString(R.string.select_a_group));
            return;
        }
        toolbar.setTitle((CharSequence) null);
        toolbar.setSubtitle((CharSequence) null);
    }
}
