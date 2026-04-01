package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import A4.C0374i;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o6.B;
import p4.C0499a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerPopupMenu {
    private PopupMenu mPopupMenu;

    private View createAnchorView(PopupMenuData popupMenuData, Context context, boolean z) {
        float f;
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
        view.setBackgroundColor(0);
        popupMenuData.mViewGroup.addView(view);
        int paddingStart = popupMenuData.mViewGroup.getPaddingStart();
        if (z) {
            f = (popupMenuData.mPosition.x - ((float) popupMenuData.mViewGroup.getRight())) + ((float) paddingStart);
        } else {
            f = popupMenuData.mPosition.x - ((float) paddingStart);
        }
        view.setX(f);
        view.setY(popupMenuData.mPosition.y);
        return view;
    }

    private void createMenu(PopupMenu popupMenu) {
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.menu_drawer_popup, menu);
        menu.findItem(R.id.action_album_view_new_album).setVisible(true);
        menu.findItem(R.id.action_folder_add_folder).setVisible(true);
    }

    private int getFolderId(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isFolder()) {
            return 0;
        }
        return mediaItem.getFolderID();
    }

    private Object getFolderName(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isFolder()) {
            return null;
        }
        return mediaItem.getFolderName();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$create$0(EventContext eventContext, PopupMenuData popupMenuData, MenuItem menuItem) {
        return onPopupMenuSelected(menuItem, eventContext, popupMenuData.mMediaItem, popupMenuData.mViewGroup);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$create$1(EventContext eventContext, PopupMenuData popupMenuData, View view, PopupMenu popupMenu) {
        removeAnchorView(eventContext, popupMenuData.mViewGroup, view);
        this.mPopupMenu = null;
    }

    private boolean onPopupMenuSelected(MenuItem menuItem, EventContext eventContext, MediaItem mediaItem, ViewGroup viewGroup) {
        if (menuItem.getItemId() == R.id.action_album_view_new_album) {
            new CreateAlbumCmd().execute(eventContext, Integer.valueOf(getFolderId(mediaItem)), getFolderName(mediaItem), Boolean.FALSE);
            publishPopoverToolbarInfo(eventContext, viewGroup, menuItem.getItemId());
            return true;
        } else if (menuItem.getItemId() != R.id.action_folder_add_folder) {
            return false;
        } else {
            CreateFolderCmd createFolderCmd = new CreateFolderCmd();
            CreateFolderCmd.Type type = CreateFolderCmd.Type.CREATE_EMPTY;
            Boolean bool = Boolean.TRUE;
            if (mediaItem == null || !mediaItem.isFolder()) {
                mediaItem = null;
            }
            createFolderCmd.execute(eventContext, null, type, bool, mediaItem);
            publishPopoverToolbarInfo(eventContext, viewGroup, menuItem.getItemId());
            return true;
        }
    }

    private void publishPopoverToolbarInfo(EventContext eventContext, View view, int i2) {
        if (SystemUi.supportPopoverUi(eventContext.getContext(), false)) {
            PopoverHelper.publishPopoverInfo(eventContext.getBlackboard(), view, 0, i2);
        }
    }

    private void removeAnchorView(EventContext eventContext, ViewGroup viewGroup, View view) {
        if (view != null) {
            eventContext.getBlackboard().publish("data://floating_pop_menu", Boolean.FALSE);
            ViewUtils.removeView(viewGroup, view);
        }
    }

    public void create(EventContext eventContext, Object obj) {
        int i2;
        PopupMenuData popupMenuData = (PopupMenuData) obj;
        boolean booleanValue = ((Boolean) eventContext.getBlackboard().read("data://floating_pop_menu", Boolean.FALSE)).booleanValue();
        if (popupMenuData.mViewGroup != null && !booleanValue) {
            eventContext.getBlackboard().publish("data://floating_pop_menu", Boolean.TRUE);
            Context context = popupMenuData.mViewGroup.getContext();
            boolean z = context.getResources().getBoolean(R.bool.is_right_to_left);
            View createAnchorView = createAnchorView(popupMenuData, context, z);
            if (z) {
                i2 = 5;
            } else {
                i2 = 3;
            }
            PopupMenu popupMenu = new PopupMenu(context, createAnchorView, i2);
            this.mPopupMenu = popupMenu;
            createMenu(popupMenu);
            this.mPopupMenu.setOnMenuItemClickListener(new C0499a(this, eventContext, popupMenuData));
            this.mPopupMenu.setOnDismissListener(new C0374i(this, eventContext, popupMenuData, createAnchorView, 1));
            this.mPopupMenu.show();
        }
    }

    public void dismiss() {
        Optional.ofNullable(this.mPopupMenu).ifPresent(new B(2));
    }
}
