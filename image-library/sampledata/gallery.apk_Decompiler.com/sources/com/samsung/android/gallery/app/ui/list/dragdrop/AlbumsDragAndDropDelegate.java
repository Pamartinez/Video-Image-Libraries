package com.samsung.android.gallery.app.ui.list.dragdrop;

import A4.S;
import B5.c;
import B8.d;
import D3.k;
import Fa.C0571z;
import T3.e;
import T4.a;
import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dragdrop.AlbumsDropTargetFinder;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsDragAndDropDelegate extends ListDragAndDropDelegate {
    private final AlbumsDragViewHolderFinder mAlbumsDragViewHolderFinder = new AlbumsDragViewHolderFinder();
    DragAndDropListScroller mDragAndDropListScroller;
    AlbumsDropTargetFinder mTargetFinder = new AlbumsDropTargetFinder();
    Runnable mUpdateMenu;

    public AlbumsDragAndDropDelegate(IBaseListView iBaseListView) {
        super(iBaseListView);
    }

    private ArrayList<MediaItem> getAlbumItems(ArrayList<Integer> arrayList) {
        String str;
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        if (LocationKey.isStories(this.mView.getLocationKey())) {
            str = DbKey.STORY_FILES;
        } else {
            str = DbKey.ALBUM_FILES;
        }
        Cursor query = DbCompat.query(str, new d(arrayList, 15));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList2.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList2;
        throw th;
    }

    private DragAndDropListScroller getAlbumListScroller() {
        if (this.mDragAndDropListScroller == null) {
            this.mDragAndDropListScroller = new DragAndDropListScroller(this.mView);
        }
        return this.mDragAndDropListScroller;
    }

    private ArrayList<MediaItem> getAutoAlbumItems(ArrayList<Integer> arrayList) {
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        Cursor autoAlbumPicturesCursor = DbCompat.autoAlbumApi().getAutoAlbumPicturesCursor((Collection<Integer>) arrayList, 0);
        if (autoAlbumPicturesCursor != null) {
            try {
                if (autoAlbumPicturesCursor.moveToFirst()) {
                    do {
                        arrayList2.add(MediaItemLoader.load(autoAlbumPicturesCursor));
                    } while (autoAlbumPicturesCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (autoAlbumPicturesCursor != null) {
            autoAlbumPicturesCursor.close();
        }
        return arrayList2;
        throw th;
    }

    private MediaItem[] getDragAlbums(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        Arrays.stream(mediaItemArr).filter(new C0571z(4)).forEach(new d(arrayList, 16));
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
    }

    private MediaItem[] getDragItems(MediaItem[] mediaItemArr) {
        int[] iArr = {-1};
        int[] iArr2 = {-1};
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Arrays.stream(mediaItemArr).filter(new C0571z(4)).forEach(new S(arrayList, arrayList2, iArr2, iArr, 4));
        if (iArr2[0] != -1) {
            arrayList.clear();
            arrayList.add(Integer.valueOf(iArr2[0]));
        } else {
            if (iArr[0] != -1) {
                arrayList3.addAll(getFavoriteAlbumItems());
            }
            if (!arrayList2.isEmpty()) {
                arrayList3.addAll(getAutoAlbumItems(arrayList2));
            }
        }
        if (!arrayList.isEmpty()) {
            arrayList3.addAll(getAlbumItems(arrayList));
        }
        return (MediaItem[]) arrayList3.toArray(new MediaItem[0]);
    }

    private ArrayList<MediaItem> getFavoriteAlbumItems() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query(DbKey.VIRTUAL_ALBUM_FAVORITE, new e(3));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    private int getTotalItemCount(MediaItem[] mediaItemArr) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Arrays.stream(mediaItemArr).filter(new C0571z(4)).forEach(new k(atomicInteger, 2));
        return atomicInteger.get();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getDragAlbums$6(MediaItem mediaItem) {
        return !mediaItem.isEmptyAlbum();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getDragAlbums$7(ArrayList arrayList, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            Stream filter = Arrays.stream(mediaItem.getItemsInFolder()).filter(new S3.d(9));
            Objects.requireNonNull(arrayList);
            filter.forEach(new d(arrayList, 23));
        } else if (!mediaItem.isEmptyAlbum()) {
            arrayList.add(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getDragItems$9(ArrayList arrayList, ArrayList arrayList2, int[] iArr, int[] iArr2, MediaItem mediaItem) {
        int albumID = mediaItem.getAlbumID();
        if (mediaItem.isFolder() || mediaItem.isMergedAlbum()) {
            Arrays.stream(mediaItem.getAlbumsInFolder()).forEach(new d(arrayList, 14));
        } else if (mediaItem.isAutoAlbum()) {
            arrayList2.add(Integer.valueOf(albumID));
        } else if (BucketUtils.isRecent(albumID)) {
            iArr[0] = albumID;
        } else if (BucketUtils.isFavourite(albumID)) {
            iArr2[0] = albumID;
        } else {
            arrayList.add(Integer.valueOf(albumID));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getTotalItemCount$12(AtomicInteger atomicInteger, MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            Arrays.stream(mediaItem.getItemsInFolder()).forEach(new k(atomicInteger, 3));
        } else {
            atomicInteger.addAndGet(mediaItem.getCount());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDrop$2(DragEvent dragEvent, AtomicBoolean atomicBoolean) {
        try {
            this.mView.getActivity().requestDragAndDropPermissions(dragEvent);
            DragAndDrop handler = getHandler();
            IBaseListView iBaseListView = this.mView;
            atomicBoolean.set(handler.handleDrop(iBaseListView, dragEvent, this.mTargetFinder.getTargetItem(iBaseListView.getListView())));
        } catch (SecurityException e) {
            String str = this.TAG;
            Log.e(str, "fail to get the access permission for content URIs in DragEvent. " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleDrop$3(Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            Log.e(this.TAG, "fail to get the access permission for content URIs in DragEvent. interrupted");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runStartDragOnUI$14() {
        Optional.ofNullable(this.mView).ifPresent(new e(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runStartDragOnUI$15() {
        ThreadUtil.postOnUiThreadDelayed(new a(this, 0), 100);
    }

    /* access modifiers changed from: private */
    /* renamed from: prepareStartDragOnBG */
    public void lambda$startDragInner$0(View view, MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
        ListViewHolder find;
        MediaItem[] dragItems = getDragItems(mediaItemArr);
        if (this.mView.getListView() != null && (find = this.mAlbumsDragViewHolderFinder.find(this.mView.getListView(), listViewHolder)) != null) {
            ThreadUtil.postOnUiThread(new c(this, view, mediaItemArr, dragItems, find, 8));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: runStartDragOnUI */
    public void lambda$prepareStartDragOnBG$13(View view, MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2, ListViewHolder listViewHolder) {
        if (this.mView.getContext() == null || this.mView.getListView() == null) {
            Log.d(this.TAG, "start drag failed. view is destroyed");
            return;
        }
        ClipData clipDataForAlbum = ClipDataManager.getInstance().getClipDataForAlbum(this.mView.getContext(), ClipDataCreatorFactory.createForAlbum(getMode(), mediaItemArr2, getDragAlbums(mediaItemArr)));
        if (clipDataForAlbum == null) {
            Log.d(this.TAG, "start drag failed. invalid clip data");
            return;
        }
        getHandler().start(this.mView.getListView(), view, clipDataForAlbum, listViewHolder, mediaItemArr2.length);
        this.mUpdateMenu = new a(this, 1);
    }

    public boolean handleDragLocation(View view, DragEvent dragEvent) {
        float x9 = dragEvent.getX();
        int y = (int) dragEvent.getY();
        getAlbumListScroller().processAutoScroll(y, this.mView.getListView());
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        this.mTargetFinder.findDropView(((int) x9) + iArr[0], y + iArr[1], this.mView.getListView());
        return true;
    }

    public boolean handleDragStarted(DragEvent dragEvent) {
        Runnable runnable;
        if (getHandler().isDragStartedFromGallery(dragEvent) && (runnable = this.mUpdateMenu) != null) {
            runnable.run();
            this.mUpdateMenu = null;
        }
        return super.handleDragStarted(dragEvent);
    }

    public boolean handleDrop(View view, DragEvent dragEvent) {
        super.handleDrop(view, dragEvent);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new LatchBuilder("handle_drop").addWorker(new R6.c(this, dragEvent, atomicBoolean, 4)).setTimeout(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS).setPostExecutor((Consumer<Boolean>) new Qb.c(23, this)).start();
        this.mTargetFinder.resetDrag();
        String str = this.TAG;
        Log.d(str, "handleDrag {DROP," + atomicBoolean + "}");
        return atomicBoolean.get();
    }

    public boolean isAutoDragPossible() {
        if (Features.isEnabled(Features.IS_UPSM) || this.mView.isMoveMode()) {
            return false;
        }
        return true;
    }

    public boolean onKeyDown(GalleryListView galleryListView, int i2, KeyEvent keyEvent) {
        ListViewHolder listViewHolder;
        if (!isCtrlVPressed(i2, keyEvent) || !getMode().isDex()) {
            return false;
        }
        View focusedChild = galleryListView.getFocusedChild();
        MediaItem mediaItem = null;
        if (!(focusedChild == null || (listViewHolder = (ListViewHolder) galleryListView.findContainingViewHolder(focusedChild)) == null)) {
            mediaItem = listViewHolder.getMediaItem();
        }
        if (mediaItem != null) {
            return new DexOnPCDragAndDrop().handlePaste(this.mView, mediaItem);
        }
        Log.w(this.TAG, "target path is null");
        return false;
    }

    public boolean startAutoDrag(int i2) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mView.getListView().findViewHolderForAdapterPosition(i2);
        if (listViewHolder == null) {
            Log.e(this.TAG, "startAutoDrag skipped, viewHolder is null");
            return false;
        } else if (this.mView.getSelectedItemCount() == 0) {
            Log.w(this.TAG, "startAutoDrag skipped, nothing selected");
            return false;
        } else {
            if (!getMode().isGallerySolelyMode() && this.mView.isSelectionMode() && !this.mView.getListView().isOngoingPinchAnimation()) {
                if (!this.mView.getListView().isSelected(i2)) {
                    this.mView.getListView().select(i2, true);
                    listViewHolder.setChecked(true);
                    if (this.mView.getListView().getSelectedItemCount() == 1) {
                        this.mView.updateToolbar(false);
                    }
                }
                MediaItem[] selectedItems = this.mView.getSelectedItems();
                if (selectedItems == null || selectedItems.length == 0) {
                    Log.w(this.TAG, "startAutoDrag skipped, null selected");
                } else {
                    startDrag(selectedItems, listViewHolder);
                    return true;
                }
            }
            return false;
        }
    }

    public void startDragInner(View view, MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
        if (getTotalItemCount(mediaItemArr) <= 1000) {
            ThreadUtil.postOnBgThread(new A6.a((Object) this, (Object) view, (Object) mediaItemArr, (Object) listViewHolder, 18));
        } else {
            Optional.ofNullable(this.mView.getContext()).ifPresent(new e(2));
        }
    }

    public boolean supportPopupMenu(Context context) {
        return false;
    }
}
