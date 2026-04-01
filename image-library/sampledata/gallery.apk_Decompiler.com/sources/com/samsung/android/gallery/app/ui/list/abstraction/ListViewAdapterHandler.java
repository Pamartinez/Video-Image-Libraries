package com.samsung.android.gallery.app.ui.list.abstraction;

import A.a;
import A2.d;
import A4.X;
import A4.Y;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadExceptionHandler;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.lang.Thread;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ListViewAdapterHandler<Adapter extends BaseListViewAdapter> extends Handler {
    private final WeakReference<Adapter> mAdapterRef;

    public ListViewAdapterHandler(Looper looper, Adapter adapter) {
        super(looper);
        this.mAdapterRef = new WeakReference<>(adapter);
    }

    private void delayLoadMediaItemDone(Adapter adapter, int i2, int i7, ListViewHolder listViewHolder) {
        if (BaseListViewAdapter.DEBUG_LOGGABLE) {
            Log.d("ListViewAdapterHandler", "load done mediaItem : " + adapter.mLocationKey + "/" + i2);
        }
        if (BaseListViewAdapter.isViewSame(listViewHolder, i2)) {
            adapter.onBindViewHolder(listViewHolder, i2, i7);
        }
    }

    private void delayLoadMediaItemStart(Adapter adapter, int i2, int i7, ListViewHolder listViewHolder) {
        if (BaseListViewAdapter.isViewSame(listViewHolder, i2)) {
            if (BaseListViewAdapter.DEBUG_LOGGABLE) {
                a.k(i2, "delay load mediaItem : ", "ListViewAdapterHandler");
            }
            adapter.loadMediaItemAsync(adapter.getMediaItemPosition(i2), new X((BaseListViewAdapter) adapter, i2, i7, listViewHolder), new Y((Object) listViewHolder, i2, 0));
        } else if (BaseListViewAdapter.DEBUG_LOGGABLE) {
            a.k(i2, "ignore request mediaItem : ", "ListViewAdapterHandler");
        }
    }

    private void delayLoadThumbLoadDone(ThumbnailRequestHolder thumbnailRequestHolder) {
        if (!BaseListViewAdapter.isViewSame(thumbnailRequestHolder.getViewHolder(), thumbnailRequestHolder.getPosition())) {
            return;
        }
        if (thumbnailRequestHolder.checkImageUid()) {
            thumbnailRequestHolder.bindResult();
            return;
        }
        thumbnailRequestHolder.setResult((Bitmap) null);
        if (BaseListViewAdapter.DEBUG_LOGGABLE) {
            Log.d("ListViewAdapterHandler", "delayLoadThumbLoadDone skip. wrong tag");
        }
    }

    private void delayLoadThumbnailStart(Adapter adapter, int i2, int i7, ListViewHolder listViewHolder) {
        if (BaseListViewAdapter.isViewSame(listViewHolder, i2)) {
            adapter.requestThumbnail(listViewHolder, i7);
        } else if (BaseListViewAdapter.DEBUG_LOGGABLE) {
            a.k(i2, "ignore request thumb 2 : ", "ListViewAdapterHandler");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$delayLoadMediaItemStart$1(ListViewHolder listViewHolder, BaseListViewAdapter baseListViewAdapter, int i2, int i7, MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.e("ListViewAdapterHandler", "delayLoadMediaItemStart failed");
        } else if (!listViewHolder.requireThumbnail()) {
            baseListViewAdapter.mFgHandler.post(new d(4, listViewHolder, mediaItem));
        } else {
            baseListViewAdapter.mFgHandler.sendMessage(1, i2, i7, listViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$delayLoadMediaItemStart$2(ListViewHolder listViewHolder, int i2) {
        return !BaseListViewAdapter.isViewSame(listViewHolder, i2);
    }

    private void reloadThumbnail(Adapter adapter) {
        GalleryListView galleryListView;
        int i2;
        ListViewHolder listViewHolder;
        MediaItem mediaItem;
        if (adapter != null) {
            galleryListView = adapter.mGalleryListView;
        } else {
            galleryListView = null;
        }
        if (galleryListView == null) {
            Log.e("ListViewAdapterHandler", "reloadThumbnail skip. null view");
            return;
        }
        RecyclerView.LayoutManager layoutManager = galleryListView.getLayoutManager();
        if (layoutManager != null) {
            i2 = layoutManager.getChildCount();
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            View childAt = layoutManager.getChildAt(i7);
            if (childAt != null) {
                RecyclerView.ViewHolder childViewHolder = galleryListView.getChildViewHolder(childAt);
                if (!(!(childViewHolder instanceof ListViewHolder) || (mediaItem = listViewHolder.getMediaItem()) == null || (listViewHolder = (ListViewHolder) childViewHolder).getThumbKind() == null || listViewHolder.getThumbKind() == adapter.getThumbnailKind())) {
                    listViewHolder.setImageUid(mediaItem.getPath());
                    adapter.requestThumbnail(listViewHolder, adapter.getGridSize());
                }
            }
        }
    }

    public void handleMessage(Message message) {
        BaseListViewAdapter baseListViewAdapter = (BaseListViewAdapter) this.mAdapterRef.get();
        if (baseListViewAdapter == null) {
            Log.e("ListViewAdapterHandler", "handleMessage skip " + message);
            return;
        }
        try {
            baseListViewAdapter.acquireReadLock("ListViewAdapterHandler.handleMessage");
            int i2 = message.what;
            if (i2 == 0) {
                delayLoadMediaItemStart(baseListViewAdapter, message.arg1, message.arg2, (ListViewHolder) message.obj);
            } else if (i2 == 1) {
                delayLoadMediaItemDone(baseListViewAdapter, message.arg1, message.arg2, (ListViewHolder) message.obj);
            } else if (i2 == 2) {
                delayLoadThumbnailStart(baseListViewAdapter, message.arg1, message.arg2, (ListViewHolder) message.obj);
            } else if (i2 == 3) {
                delayLoadThumbLoadDone((ThumbnailRequestHolder) message.obj);
            } else if (i2 == 5) {
                reloadThumbnail(baseListViewAdapter);
            }
            baseListViewAdapter.releaseReadLock("ListViewAdapterHandler.handleMessage");
            return;
        } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
            Log.e((CharSequence) "ListViewAdapterHandler", "handleMessage failed during swap. ignore", e);
        } catch (Exception e7) {
            if (!baseListViewAdapter.isDataAvailable()) {
                Log.e("ListViewAdapterHandler", "handleMessage failed by no data. ignore " + baseListViewAdapter.mMediaData);
            } else {
                Log.e("ListViewAdapterHandler", "handleMessage failed. unexpected " + baseListViewAdapter.mMediaData);
                throw e7;
            }
        } catch (Throwable th) {
            baseListViewAdapter.releaseReadLock("ListViewAdapterHandler.handleMessage");
            throw th;
        }
        baseListViewAdapter.releaseReadLock("ListViewAdapterHandler.handleMessage");
    }

    public void sendMessage(int i2, int i7, int i8, ListViewHolder listViewHolder) {
        sendMessage(obtainMessage(i2, i7, i8, listViewHolder));
    }

    public boolean sendMessageAtTime(Message message, long j2) {
        if (Logger.isAllowDebug() && !ThreadUtil.isMainThread()) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
            if (uncaughtExceptionHandler instanceof ThreadExceptionHandler) {
                ((ThreadExceptionHandler) uncaughtExceptionHandler).saveCallTimeStack(Thread.currentThread().getStackTrace());
            }
        }
        return super.sendMessageAtTime(message, j2);
    }

    public void sendMessageDelayed(int i2, int i7, int i8, ListViewHolder listViewHolder) {
        sendMessageDelayed(obtainMessage(i2, i7, i8, listViewHolder), 50);
    }

    public void sendReloadThumbMessage() {
        sendMessage(obtainMessage(5));
    }

    public void sendThumbLoadDoneMessage(ThumbnailRequestHolder thumbnailRequestHolder) {
        sendMessage(obtainMessage(3, thumbnailRequestHolder));
    }
}
