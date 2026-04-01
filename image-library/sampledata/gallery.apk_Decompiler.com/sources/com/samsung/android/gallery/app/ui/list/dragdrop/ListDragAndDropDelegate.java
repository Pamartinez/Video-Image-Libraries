package com.samsung.android.gallery.app.ui.list.dragdrop;

import A.a;
import A4.C0371f;
import Fb.d;
import T4.c;
import android.content.Context;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.DummyPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.IPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListDragAndDropDelegate implements DragAndDropDelegate {
    protected final String TAG = getClass().getSimpleName();
    boolean mIsDraggingOnGallery;
    Lazy<DnDMode> mMode;
    private final IPopupMenuDelegate mPopupMenuDelegate;
    IBaseListView mView;

    public ListDragAndDropDelegate(IBaseListView iBaseListView) {
        IPopupMenuDelegate iPopupMenuDelegate;
        Context context = iBaseListView.getContext();
        this.mView = iBaseListView;
        this.mMode = new Lazy<>(new c(context, 0));
        if (supportPopupMenu(context)) {
            iPopupMenuDelegate = new ListPopupMenuDelegate(iBaseListView);
        } else {
            iPopupMenuDelegate = new DummyPopupMenuDelegate();
        }
        this.mPopupMenuDelegate = iPopupMenuDelegate;
    }

    private boolean isDropOnTheSameView(DragEvent dragEvent) {
        return TextUtils.equals(this.mView.getLocationKey(), ClipDataUtils.getStringExtra(dragEvent.getClipData(), "started_location"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DnDMode lambda$new$0(Context context) {
        return new DnDMode(context);
    }

    private void postEventToBlackboard(DragEvent dragEvent) {
        Blackboard blackboard = this.mView.getBlackboard();
        boolean isSelectionMode = this.mView.isSelectionMode();
        blackboard.postEvent(EventMessage.obtain(1005, isSelectionMode ? 1 : 0, Integer.valueOf(dragEvent.getAction())));
    }

    private void stopSelection() {
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    private void updateMenuByTouchUp() {
        if (this.mView.getListView() == null) {
            return;
        }
        if (!this.mView.getListView().isTouchOngoing()) {
            this.mView.getListView().onTouchUp();
            return;
        }
        GalleryListView listView = this.mView.getListView();
        Objects.requireNonNull(listView);
        ThreadUtil.postOnUiThreadDelayed(new d(1, listView), 100);
    }

    public void destroy() {
        this.mPopupMenuDelegate.destroy();
    }

    public DragAndDrop getHandler() {
        return getMode().getHandler();
    }

    public DnDMode getMode() {
        return this.mMode.get();
    }

    public final boolean handleDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case 1:
                return handleDragStarted(dragEvent);
            case 2:
                return handleDragLocation(view, dragEvent);
            case 3:
                return handleDrop(view, dragEvent);
            case 4:
                return handleDragEnded(dragEvent);
            case 5:
                return handleDragEntered(dragEvent);
            case 6:
                return handleDragExit(dragEvent);
            default:
                return true;
        }
    }

    public boolean handleDragEnded(DragEvent dragEvent) {
        postEventToBlackboard(dragEvent);
        if (!isDraggingOnGallery()) {
            stopSelection();
        }
        setIsDraggingOnGallery(false);
        return true;
    }

    public boolean handleDragEntered(DragEvent dragEvent) {
        setIsDraggingOnGallery(true);
        postEventToBlackboard(dragEvent);
        Log.d(this.TAG, "handleDrag {ENTERED}");
        return true;
    }

    public boolean handleDragExit(DragEvent dragEvent) {
        postEventToBlackboard(dragEvent);
        setIsDraggingOnGallery(false);
        return false;
    }

    public boolean handleDragLocation(View view, DragEvent dragEvent) {
        return true;
    }

    public boolean handleDragStarted(DragEvent dragEvent) {
        boolean dropPossible = getMode().dropPossible(dragEvent);
        a.v("handleDrag {STARTED,", "}", this.TAG, dropPossible);
        setIsDraggingOnGallery(dropPossible);
        if (dropPossible) {
            postEventToBlackboard(dragEvent);
        }
        return dropPossible;
    }

    public boolean handleDrop(View view, DragEvent dragEvent) {
        if (this.mView.isSelectionMode()) {
            this.mView.invalidateToolbar();
        }
        updateMenuByTouchUp();
        if (isDropOnTheSameView(dragEvent)) {
            return false;
        }
        stopSelection();
        return false;
    }

    public boolean isCtrlVPressed(int i2, KeyEvent keyEvent) {
        if (i2 == 50 && keyEvent.getMetaState() > 0 && (keyEvent.getMetaState() & KeyEvent.getModifierMetaStateMask() & -28673) == 0) {
            return true;
        }
        return false;
    }

    public boolean isDraggingOnGallery() {
        return this.mIsDraggingOnGallery;
    }

    public boolean isPopupMenuShowing() {
        return this.mPopupMenuDelegate.isPopupMenuShowing();
    }

    public void release() {
        this.mPopupMenuDelegate.release();
    }

    public void resetCurrentMode() {
        this.mMode.remove();
    }

    public void setIsDraggingOnGallery(boolean z) {
        this.mIsDraggingOnGallery = z;
        this.mView.getBlackboard().publish("data://dragging_selection", Boolean.valueOf(z));
    }

    public void startDrag(MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
        if (supportPopupMenu(this.mView.getContext())) {
            this.mPopupMenuDelegate.show(listViewHolder, new C0371f((Object) this, (Object) mediaItemArr, (Object) listViewHolder, 12), mediaItemArr.length);
        } else {
            lambda$startDrag$1(this.mView.getListView(), mediaItemArr, listViewHolder);
        }
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=com.samsung.android.gallery.widget.listviewholder.ListViewHolder, code=androidx.recyclerview.widget.RecyclerView$ViewHolder, for r2v0, types: [com.samsung.android.gallery.widget.listviewholder.ListViewHolder] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startDragAndDropOnAdvancedMouseAction(int r1, androidx.recyclerview.widget.RecyclerView.ViewHolder r2, java.lang.Runnable r3) {
        /*
            r0 = this;
            if (r2 == 0) goto L_0x0003
            goto L_0x000d
        L_0x0003:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r2 = r0.mView
            com.samsung.android.gallery.widget.listview.GalleryListView r2 = r2.getListView()
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r2.findViewHolderForLayoutPosition(r1)
        L_0x000d:
            boolean r1 = r2 instanceof com.samsung.android.gallery.widget.listviewholder.ListViewHolder
            if (r1 == 0) goto L_0x0059
            boolean r1 = r0.mIsDraggingOnGallery
            if (r1 == 0) goto L_0x0016
            goto L_0x0059
        L_0x0016:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r0.mView
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r1.getAdapter()
            if (r1 == 0) goto L_0x0037
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r0.mView
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r1.getAdapter()
            com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager r1 = r1.getAdvancedMouseFocusManager()
            if (r1 == 0) goto L_0x0037
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r0.mView
            com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter r1 = r1.getAdapter()
            com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager r1 = r1.getAdvancedMouseFocusManager()
            r1.clearViewPosition()
        L_0x0037:
            r3.run()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r0.mView
            com.samsung.android.gallery.module.data.MediaItem[] r1 = r1.getSelectedItems()
            if (r1 == 0) goto L_0x0052
            int r1 = r1.length
            if (r1 != 0) goto L_0x0046
            goto L_0x0052
        L_0x0046:
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r1 = r0.mView
            com.samsung.android.gallery.module.data.MediaItem[] r1 = r1.getSelectedItems()
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2
            r0.startDrag(r1, r2)
            return
        L_0x0052:
            java.lang.String r0 = r0.TAG
            java.lang.String r1 = "startDragAndDropOnAdvancedMouseAction skipped, nothing selected"
            com.samsung.android.gallery.support.utils.Log.w(r0, r1)
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.dragdrop.ListDragAndDropDelegate.startDragAndDropOnAdvancedMouseAction(int, com.samsung.android.gallery.widget.listviewholder.ListViewHolder, java.lang.Runnable):void");
    }

    /* renamed from: startDragInner */
    public abstract void lambda$startDrag$1(View view, MediaItem[] mediaItemArr, ListViewHolder listViewHolder);

    public boolean supportPopupMenu(Context context) {
        if (context == null || !PreferenceFeatures.isEnabled(PreferenceFeatures.PopupMenuInDragAndDrop)) {
            return false;
        }
        return true;
    }
}
