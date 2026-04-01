package com.samsung.android.gallery.app.ui.list.dragdrop;

import A9.c;
import Fa.C0571z;
import Fa.I;
import O3.o;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.tag.TagEditor;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadow;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NormalDragAndDrop extends DragAndDrop {
    private MediaItem mTargetItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDataCompletionListener {
        void onDataCompleted();
    }

    private void addToAutoAlbum(Supplier<MediaItem[]> supplier) {
        AutoAlbumHelper.getInstance().addAutoAlbumContents((String) Arrays.stream(supplier.get()).map(new o(16)).distinct().collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), (long) this.mTargetItem.getAlbumID());
    }

    private void addToFavorite(Supplier<MediaItem[]> supplier) {
        ((Map) Arrays.stream(supplier.get()).filter(new C0571z(4)).collect(Collectors.groupingBy(new o(3)))).forEach(new c(3));
    }

    private boolean isConfirmed(ArrayList<Object> arrayList) {
        if (arrayList == null || ((Integer) arrayList.get(0)).intValue() != 1) {
            return false;
        }
        return true;
    }

    private boolean isEmptySpaceDrop(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addToFavorite$6(Boolean bool, List list) {
        if (bool.booleanValue()) {
            list.forEach(new I(AppResources.getAppContext(), 3));
        } else if (new TagEditor().addFavoriteInBulk(list) == 0) {
            Utils.showToast(AppResources.getAppContext(), (int) R.string.unable_to_add_item);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDrop$1(IBaseListView iBaseListView, ClipData clipData) {
        addToFavorite(getSelectedItemsSupplier(iBaseListView.getPresenter(), clipData));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDrop$2(IBaseListView iBaseListView, ClipData clipData) {
        addToAutoAlbum(getSelectedItemsSupplier(iBaseListView.getPresenter(), clipData));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataComplete$4(OnDataCompletionListener onDataCompletionListener, EventContext eventContext) {
        onDataCompletionListener.onDataCompleted();
        eventContext.getBlackboard().postEvent(EventMessage.obtain(1003));
        eventContext.getBlackboard().publish("data://albums/moveTo/target", this.mTargetItem);
        eventContext.getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
    }

    /* access modifiers changed from: private */
    /* renamed from: onDataComplete */
    public void lambda$showConfirmDialog$3(EventContext eventContext, ArrayList<Object> arrayList, OnDataCompletionListener onDataCompletionListener) {
        if (isConfirmed(arrayList)) {
            SimpleThreadPool.getInstance().execute(new c(this, onDataCompletionListener, eventContext));
        }
    }

    private void showConfirmDialog(IBaseListView iBaseListView, MediaItem mediaItem, OnDataCompletionListener onDataCompletionListener) {
        Context context = iBaseListView.getContext();
        if (context == null) {
            Log.e(this.TAG, "fail to show confirm dialog -> Context is null");
            return;
        }
        this.mTargetItem = mediaItem;
        DataCollectionDelegate.getInitInstance(iBaseListView.getPresenter()).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", context.getString(R.string.add_items_to_this_album)).appendArg("option1", context.getString(R.string.ok)).build()).setOnDataCompleteListener(new b(this, onDataCompletionListener)).start();
    }

    public boolean dragStart(GalleryListView galleryListView, View view, ClipData clipData, ListViewHolder listViewHolder, int i2) {
        String str = this.TAG;
        Log.d(str, "dragStart by " + this.TAG);
        Bitmap dragStartBitmap = ClipDataManager.getInstance().getDragStartBitmap(listViewHolder, i2);
        Context context = view.getContext();
        view.startDragAndDrop(clipData, new DragShadow(new ImageView(context), context, dragStartBitmap), (Object) null, ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8);
        return true;
    }

    public Supplier<MediaItem[]> getSelectedItemsSupplier(EventContext eventContext, ClipData clipData) {
        Objects.requireNonNull(eventContext);
        return new J5.c(7, eventContext);
    }

    public boolean handleDrop(IBaseListView iBaseListView, DragEvent dragEvent, MediaItem mediaItem) {
        if (isEmptySpaceDrop(mediaItem)) {
            return false;
        }
        ClipData clipData = dragEvent.getClipData();
        if (isObjectCapture(dragEvent)) {
            if (PreferenceFeatures.OneUi6x.IS_ONE_UI_60 && !isInvalidTargetItem(mediaItem)) {
                showConfirmDialog(iBaseListView, mediaItem, new a(iBaseListView, clipData, mediaItem));
                return true;
            }
        } else if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            showConfirmDialog(iBaseListView, mediaItem, new a(this, iBaseListView, clipData, 0));
            return true;
        } else if (AlbumType.isAutoAlbum(mediaItem.getAlbumType())) {
            showConfirmDialog(iBaseListView, mediaItem, new a(this, iBaseListView, clipData, 1));
            return true;
        } else if (!isInvalidTargetItem(mediaItem)) {
            startFileOperation(iBaseListView, clipData, mediaItem);
            return true;
        }
        if (!supportToast()) {
            return false;
        }
        Utils.showToast(iBaseListView.getContext(), (int) R.string.drag_and_drop_not_supported);
        return false;
    }

    public final boolean isInvalidTargetItem(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isFolder() || mediaItem.isReadOnlyAlbum() || mediaItem.isSharing()) {
            return true;
        }
        return false;
    }

    public boolean isValidDropEvent(DragEvent dragEvent) {
        return isDragStartedFromGallery(dragEvent);
    }

    public void startFileOperation(IBaseListView iBaseListView, ClipData clipData, MediaItem mediaItem) {
        new FileOpCmd().execute(iBaseListView.getPresenter(), FileOpCmdHelper.CmdType.TYPE_DRAG_TO_ITEMS, iBaseListView.getPresenter().getSelectedItems(), mediaItem, null);
    }

    public boolean supportToast() {
        return true;
    }
}
