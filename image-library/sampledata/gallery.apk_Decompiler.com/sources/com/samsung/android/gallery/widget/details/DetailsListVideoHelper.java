package com.samsung.android.gallery.widget.details;

import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsListVideoHelper {
    private final String TAG = "DetailsListViewHelper";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private BooleanSupplier mIsResumed;
    private RecyclerView mListView;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                ViewHolderPlayer b = DetailsListVideoHelper.this.findPlayingHolder();
                if (b == null) {
                    DetailsListVideoHelper.this.playVideo();
                } else if (!DetailsListVideoHelper.this.isPlayablePosition(recyclerView, ((ListViewHolder) b).getRootView())) {
                    Log.e("DetailsListViewHelper", "playingItem is not in playablePosition");
                    b.stop();
                    DetailsListVideoHelper.this.playVideo();
                }
            }
        }
    };

    private MediaItem findNextPlayableItem(MediaItem mediaItem) {
        int i2;
        if (mediaItem == null) {
            return null;
        }
        ArrayList<MediaItem> findPlayableItems = findPlayableItems();
        if (findPlayableItems.isEmpty()) {
            return null;
        }
        for (int i7 = 0; i7 < findPlayableItems.size(); i7++) {
            if (mediaItem == findPlayableItems.get(i7) && (i2 = i7 + 1) < findPlayableItems.size()) {
                return findPlayableItems.get(i2);
            }
        }
        return findPlayableItems.get(0);
    }

    private ArrayList<MediaItem> findPlayableItems() {
        LinearLayoutManager linearLayoutManager;
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        RecyclerView recyclerView = this.mListView;
        if (!(recyclerView == null || (linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()) == null)) {
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                ListViewHolder listViewHolder = (ListViewHolder) this.mListView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition);
                if (listViewHolder != null && isPlayablePosition(this.mListView, listViewHolder.getRootView())) {
                    arrayList.add(listViewHolder.getMediaItem());
                }
            }
        }
        return arrayList;
    }

    private ViewHolderPlayer findPlayableViewHolder(MediaItem mediaItem) {
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        if (mediaItem == null || (recyclerView = this.mListView) == null || (linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()) == null) {
            return null;
        }
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            ViewHolderPlayer viewHolderPlayer = (ViewHolderPlayer) this.mListView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition);
            if (viewHolderPlayer != null && mediaItem == viewHolderPlayer.getMediaItem()) {
                return viewHolderPlayer;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public ViewHolderPlayer findPlayingHolder() {
        LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView == null || (linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager()) == null) {
            return null;
        }
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            ViewHolderPlayer viewHolderPlayer = (ViewHolderPlayer) this.mListView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition);
            if (viewHolderPlayer != null && viewHolderPlayer.isPlaying()) {
                return viewHolderPlayer;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean isPlayablePosition(View view, View view2) {
        RectF viewRect = ViewUtils.getViewRect(view);
        RectF viewRect2 = ViewUtils.getViewRect(view2);
        if (!RectUtils.isValidRect(viewRect) || !RectUtils.isValidRect(viewRect2)) {
            return true;
        }
        if ((viewRect2.width() / 2.0f) + viewRect2.left <= viewRect.left) {
            return false;
        }
        if (viewRect.right > (viewRect2.width() / 2.0f) + viewRect2.left) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: playVideoInternal */
    public void lambda$postPlay$0(MediaItem mediaItem) {
        BooleanSupplier booleanSupplier = this.mIsResumed;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            Log.e((CharSequence) "DetailsListViewHelper", "play fail: not resumed", this.mIsResumed);
            return;
        }
        ViewHolderPlayer findPlayableViewHolder = findPlayableViewHolder(mediaItem);
        if (findPlayableViewHolder != null) {
            Log.d("DetailsListViewHelper", "play");
            findPlayableViewHolder.play();
            return;
        }
        Log.e("DetailsListViewHelper", "play fail: null holder");
    }

    private void postPlay(MediaItem mediaItem) {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler.postDelayed(new e(9, this, mediaItem), 300);
    }

    public void init(RecyclerView recyclerView, BooleanSupplier booleanSupplier) {
        this.mListView = recyclerView;
        this.mIsResumed = booleanSupplier;
        recyclerView.addOnScrollListener(this.mScrollListener);
    }

    public void onCompletedVideo(MediaItem mediaItem) {
        MediaItem findNextPlayableItem = findNextPlayableItem(mediaItem);
        if (findNextPlayableItem != null) {
            postPlay(findNextPlayableItem);
        } else {
            Log.e("DetailsListViewHelper", "play fail: null nextPlayItem");
        }
    }

    public void playVideo() {
        ArrayList<MediaItem> findPlayableItems = findPlayableItems();
        if (!findPlayableItems.isEmpty()) {
            postPlay(findPlayableItems.get(0));
        } else {
            Log.e("DetailsListViewHelper", "play fail: null playList");
        }
    }

    public void stopVideo() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        ViewHolderPlayer findPlayingHolder = findPlayingHolder();
        if (findPlayingHolder != null) {
            Log.d("DetailsListViewHelper", "stop");
            findPlayingHolder.stop();
        }
    }
}
