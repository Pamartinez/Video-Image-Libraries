package com.samsung.android.gallery.app.ui.list.stories;

import A.a;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DeleteAnimDelegate<V extends IBaseListView> {
    private int mAnimStartPosition;
    private final ArrayList<Integer> mAnimationPositions = new ArrayList<>();
    private final ArrayList<Integer> mDeletePositions = new ArrayList<>();
    final V mView;

    public DeleteAnimDelegate(V v) {
        this.mView = v;
    }

    private void clear() {
        this.mAnimationPositions.clear();
        this.mAnimStartPosition = -1;
        this.mDeletePositions.clear();
    }

    private int findViewPosition(MediaItem mediaItem) {
        GalleryListView galleryListView;
        V v = this.mView;
        if (v != null) {
            galleryListView = v.getListView();
        } else {
            galleryListView = null;
        }
        if (galleryListView == null) {
            return -1;
        }
        int findLastVisibleItemPositionCompat = galleryListView.findLastVisibleItemPositionCompat();
        for (int findFirstVisibleItemPositionCompat = galleryListView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
            ListViewHolder listViewHolder = (ListViewHolder) galleryListView.findViewHolderForAdapterPosition(findFirstVisibleItemPositionCompat);
            if (listViewHolder != null && listViewHolder.getMediaItem() == mediaItem) {
                return findFirstVisibleItemPositionCompat;
            }
        }
        return -1;
    }

    private boolean isAvailableSlideAnimation(ListViewHolder listViewHolder) {
        if (listViewHolder.hasBitmap() || !this.mAnimationPositions.contains(Integer.valueOf(listViewHolder.getViewPosition()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$handleDeleteWithItemAnimator$1(Integer num, Integer num2) {
        if (num.intValue() > num2.intValue()) {
            return -1;
        }
        return 1;
    }

    private boolean useItemAnimator() {
        if (this.mView.isDestroyed() || this.mView.getAdapter() == null || this.mView.getListView() == null || this.mView.getListView().getColumnCount() <= 1) {
            return false;
        }
        return true;
    }

    public boolean handleDeleteWithItemAnimator() {
        if (!useItemAnimator() || this.mDeletePositions.isEmpty()) {
            this.mDeletePositions.clear();
            return false;
        }
        this.mDeletePositions.sort(new a(1));
        if (this.mDeletePositions.isEmpty()) {
            clear();
            return false;
        }
        Iterator<Integer> it = this.mDeletePositions.iterator();
        while (it.hasNext()) {
            this.mView.getAdapter().notifyItemRemoved(it.next().intValue());
        }
        clear();
        return true;
    }

    public void listSlideAnimation(ListViewHolder listViewHolder) {
        if (isAvailableSlideAnimation(listViewHolder)) {
            View rootView = listViewHolder.getRootView();
            int viewPosition = listViewHolder.getViewPosition();
            int height = ((viewPosition - this.mAnimStartPosition) + 1) * rootView.getHeight();
            rootView.setY(rootView.getY() + ((float) height));
            rootView.animate().translationYBy((float) (-height)).setDuration(200).start();
            this.mAnimationPositions.remove(Integer.valueOf(viewPosition));
            if (this.mAnimationPositions.size() == 0) {
                this.mAnimStartPosition = -1;
            }
            a.k(viewPosition, "listSlideAnimation slide viewPosition : ", "DeleteAnimDelegate");
        }
    }

    public void onAbortDelete() {
        clear();
    }

    public void onPrepareDelete(GridLayoutManager gridLayoutManager, ArrayList<Integer> arrayList) {
        clear();
        ArrayList arrayList2 = new ArrayList();
        int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        if (arrayList == null || arrayList.isEmpty()) {
            V v = this.mView;
            if (v != null) {
                MediaItem mediaItem = (MediaItem) v.getBlackboard().read("data://stories/current", null);
                if (mediaItem != null) {
                    arrayList2.add(Integer.valueOf(findViewPosition(mediaItem)));
                    this.mView.getBlackboard().publish("data://stories/current", (Object) null);
                }
            } else {
                Log.e("DeleteAnimDelegate", "onStartDelete mView is null. ");
                return;
            }
        } else {
            this.mDeletePositions.addAll(arrayList);
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                int intValue = next.intValue();
                if (intValue <= findFirstVisibleItemPosition) {
                    if (!arrayList2.contains(Integer.valueOf(findFirstVisibleItemPosition))) {
                        arrayList2.add(Integer.valueOf(findFirstVisibleItemPosition));
                    }
                } else if (intValue <= findLastVisibleItemPosition) {
                    arrayList2.add(next);
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList2.sort(new a(0));
            int intValue2 = ((Integer) arrayList2.get(0)).intValue();
            this.mAnimStartPosition = intValue2;
            while (intValue2 <= findLastVisibleItemPosition) {
                this.mAnimationPositions.add(Integer.valueOf(intValue2));
                intValue2++;
            }
            Log.d("DeleteAnimDelegate", "onStartDelete mAnimationPositions : " + this.mAnimationPositions);
        }
    }
}
