package com.samsung.android.gallery.app.ui.list.albums;

import A4.C0372g;
import A4.O;
import android.content.res.Resources;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragAdapter;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountReorderHolder;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsViewAdapter<V extends IAlbumsView> extends AlbumsDragAdapter<V> {
    private AlbumsFooterView mFooterView;
    private boolean mIsCustomOrder = SortByType.isSortByCustom();
    private final boolean mIsRtl = Features.isEnabled(Features.IS_RTL);
    private ArrayList<MediaItem> mOldItems;
    private int mSlideAmountForAnim;
    private HashSet<Integer> mUnAffectedFolders;
    private HashSet<Integer> mUnAffectedItems;
    private boolean mUngroupAnimationPrepared = false;
    private HashSet<Integer> mUngroupedFolders;

    public AlbumsViewAdapter(V v, String str) {
        super(v, str);
    }

    private void attachFooterViewToHolder(ListViewHolder listViewHolder) {
        if (this.mFooterView != null) {
            ViewUtils.removeSelf(this.mFooterView);
            ((ViewGroup) listViewHolder.getRootView()).addView(this.mFooterView, 0);
        }
    }

    private int getSlideAmount(Resources resources) {
        return resources.getDimensionPixelOffset(R.dimen.list_album_horizontal_gap) + resources.getDimensionPixelOffset(R.dimen.check_box_width);
    }

    private boolean handleUngroupingAnimation() {
        ArrayList<MediaItem> saveDataToList = saveDataToList();
        try {
            Iterator<MediaItem> it = this.mOldItems.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (isUnAffected(next)) {
                    while (i2 < saveDataToList.size() && !isSame(next, saveDataToList.get(i2))) {
                        notifyItemInserted(getViewPosition(i2));
                        i2++;
                    }
                } else {
                    if (i2 < saveDataToList.size()) {
                        if (!isUnAffected(saveDataToList.get(i2))) {
                            notifyItemChanged(getViewPosition(i2));
                        }
                    }
                    notifyItemRemoved(getViewPosition(i2));
                }
                i2++;
            }
            while (i2 < saveDataToList.size()) {
                notifyItemInserted(getViewPosition(i2));
                i2++;
            }
            this.mGalleryListView.setPreparingItemAnimation(false);
            this.mGalleryListView.requestLayout();
            return true;
        } catch (IndexOutOfBoundsException | NullPointerException unused) {
            Log.e(this.TAG, "Mismatch in media data : Issue in ungroup animation");
            return false;
        }
    }

    private boolean isFooter(ListViewHolder listViewHolder) {
        if (!hasFooter() || listViewHolder == null || !ViewHolderValue.isFooter(listViewHolder.getItemViewType())) {
            return false;
        }
        return true;
    }

    private boolean isSame(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.isFolder() && mediaItem2.isFolder() && mediaItem.getFolderID() == mediaItem2.getFolderID()) {
            return true;
        }
        if (mediaItem.isFolder() || mediaItem2.isFolder() || mediaItem.getAlbumID() != mediaItem2.getAlbumID()) {
            return false;
        }
        return true;
    }

    private boolean isUnAffected(MediaItem mediaItem) {
        if (mediaItem.isFolder()) {
            HashSet<Integer> hashSet = this.mUnAffectedFolders;
            if (hashSet == null || !hashSet.contains(Integer.valueOf(mediaItem.getFolderID()))) {
                return false;
            }
            return true;
        }
        HashSet<Integer> hashSet2 = this.mUnAffectedItems;
        if (hashSet2 == null || !hashSet2.contains(Integer.valueOf(mediaItem.getAlbumID()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0() {
        notifyItemChanged(getFooterPosition());
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002f A[Catch:{ IllegalArgumentException -> 0x0016 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0033 A[Catch:{ IllegalArgumentException -> 0x0016 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void listSlideAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4) {
        /*
            r3 = this;
            boolean r0 = r3.isSelectionMode()
            boolean r1 = r4.isCheckBoxEnabled()
            if (r0 != r1) goto L_0x000b
            return
        L_0x000b:
            boolean r0 = r3.isSelectionMode()     // Catch:{ IllegalArgumentException -> 0x0016 }
            if (r0 == 0) goto L_0x0018
            boolean r0 = r3.mIsRtl     // Catch:{ IllegalArgumentException -> 0x0016 }
            if (r0 == 0) goto L_0x0022
            goto L_0x0018
        L_0x0016:
            r4 = move-exception
            goto L_0x0053
        L_0x0018:
            boolean r0 = r3.isSelectionMode()     // Catch:{ IllegalArgumentException -> 0x0016 }
            if (r0 != 0) goto L_0x0024
            boolean r0 = r3.mIsRtl     // Catch:{ IllegalArgumentException -> 0x0016 }
            if (r0 == 0) goto L_0x0024
        L_0x0022:
            r0 = 1
            goto L_0x0025
        L_0x0024:
            r0 = 0
        L_0x0025:
            android.view.View r1 = r4.getRootView()     // Catch:{ IllegalArgumentException -> 0x0016 }
            float r1 = r1.getX()     // Catch:{ IllegalArgumentException -> 0x0016 }
            if (r0 == 0) goto L_0x0033
            int r0 = r3.mSlideAmountForAnim     // Catch:{ IllegalArgumentException -> 0x0016 }
            int r0 = -r0
            goto L_0x0035
        L_0x0033:
            int r0 = r3.mSlideAmountForAnim     // Catch:{ IllegalArgumentException -> 0x0016 }
        L_0x0035:
            float r0 = (float) r0     // Catch:{ IllegalArgumentException -> 0x0016 }
            float r1 = r1 + r0
            android.view.View r0 = r4.getRootView()     // Catch:{ IllegalArgumentException -> 0x0016 }
            r0.setX(r1)     // Catch:{ IllegalArgumentException -> 0x0016 }
            android.graphics.Matrix r0 = new android.graphics.Matrix     // Catch:{ IllegalArgumentException -> 0x0016 }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x0016 }
            r2 = 0
            r0.preTranslate(r1, r2)     // Catch:{ IllegalArgumentException -> 0x0016 }
            float r1 = -r1
            r0.postTranslate(r1, r2)     // Catch:{ IllegalArgumentException -> 0x0016 }
            android.view.View r4 = r4.getRootView()     // Catch:{ IllegalArgumentException -> 0x0016 }
            r4.setAnimationMatrix(r0)     // Catch:{ IllegalArgumentException -> 0x0016 }
            return
        L_0x0053:
            java.lang.String r3 = r3.TAG
            java.lang.String r4 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.e(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter.listSlideAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder):void");
    }

    private void resetUngroupData() {
        this.mUngroupAnimationPrepared = false;
        this.mOldItems = null;
        this.mUngroupedFolders = null;
        this.mUnAffectedItems = null;
        this.mUnAffectedFolders = null;
    }

    private void runDeleteAnimation(ArrayList<Integer> arrayList) {
        if (!arrayList.isEmpty()) {
            arrayList.sort(Comparator.reverseOrder());
        }
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            this.mMediaData.removeItemAt(intValue);
            notifyItemRemoved(getViewPosition(intValue));
        }
    }

    private ArrayList<MediaItem> saveDataToList() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
            arrayList.add(this.mMediaData.read(i2));
        }
        return arrayList;
    }

    public boolean checkIfEmpty() {
        if (getItemCount() == 0 || (hasFooter() && getItemCount() == 1)) {
            return true;
        }
        return false;
    }

    public void destroy() {
        super.destroy();
        AlbumsFooterView albumsFooterView = this.mFooterView;
        if (albumsFooterView != null) {
            albumsFooterView.destroy();
        }
    }

    public int getFooterPosition() {
        return getItemCount() - 1;
    }

    public int getFooterViewHeight() {
        AlbumsFooterView albumsFooterView;
        if (!hasFooter() || (albumsFooterView = this.mFooterView) == null) {
            return 0;
        }
        return albumsFooterView.getHeight();
    }

    public int getItemCount() {
        return (hasFooter() ? 1 : 0) + super.getItemCount();
    }

    public int getItemHeight(int i2) {
        if (!hasFooter() || !isFooter(i2)) {
            return super.getItemHeight(i2);
        }
        return getFooterViewHeight();
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (!hasFooter() || !isFooter(i2)) {
            return super.getMediaItemFromCache(i2);
        }
        return null;
    }

    public int getPrevRowIndex(int i2) {
        if (!hasFooter() || !isFooter(i2)) {
            return super.getPrevRowIndex(i2);
        }
        return i2 - 1;
    }

    public int getSpanSize(int i2) {
        if (!hasFooter() || !isFooter(i2)) {
            return super.getSpanSize(i2);
        }
        return ((IAlbumsView) this.mView).getLayoutManager().getSpanCount();
    }

    public boolean handleDataUpdateAnimation() {
        boolean z;
        if (!this.mUngroupAnimationPrepared || !handleUngroupingAnimation()) {
            z = false;
        } else {
            z = true;
        }
        resetUngroupData();
        if (z) {
            ThreadUtil.postOnUiThreadDelayed(new C0372g(7, this), 500);
        }
        if (z || super.handleDataUpdateAnimation()) {
            return true;
        }
        return false;
    }

    public boolean hasFooter() {
        return false;
    }

    public void initResourceValues() {
        super.initResourceValues();
        this.mSlideAmountForAnim = getSlideAmount(getContext().getResources());
    }

    public void onDataChanged() {
        super.onDataChanged();
        this.mIsCustomOrder = SortByType.isSortByCustom();
    }

    public void onDataRangeInserted(int i2, int i7) {
        this.mGalleryListView.setPreparingItemAnimation(false);
        super.onDataRangeInserted(i2, i7);
    }

    public boolean onFolderCreated(int i2, String str, ArrayList<Integer> arrayList) {
        int i7;
        HashSet hashSet = new HashSet(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i8 = 0; i8 < this.mMediaData.getCount(); i8++) {
            MediaItem read = this.mMediaData.read(i8);
            if (read != null && hashSet.contains(Integer.valueOf(read.getAlbumID()))) {
                arrayList3.add(read);
                arrayList2.add(Integer.valueOf(i8));
            }
        }
        if (arrayList3.isEmpty()) {
            return false;
        }
        arrayList3.remove(0);
        if (SortByType.isSortByCustom()) {
            i7 = ((Integer) arrayList2.remove(0)).intValue();
            runDeleteAnimation(arrayList2);
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                this.mMediaData.createFolderAt(i7, (MediaItem) it.next(), i2, str);
            }
            updateCluster();
            notifyItemChanged(getViewPosition(i7));
        } else {
            int intValue = ((Integer) arrayList2.get(0)).intValue();
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                this.mMediaData.createFolderAt(intValue, (MediaItem) it2.next(), i2, str);
            }
            updateCluster();
            int updateFolderAt = this.mMediaData.updateFolderAt(intValue, i2, str);
            if (intValue == updateFolderAt) {
                arrayList2.remove(0);
                runDeleteAnimation(arrayList2);
                notifyItemChanged(getViewPosition(intValue));
            } else {
                MediaItem read2 = this.mMediaData.read(intValue);
                runDeleteAnimation(arrayList2);
                this.mMediaData.insertItemAt(updateFolderAt, read2);
                updateCluster();
                notifyItemInserted(getViewPosition(updateFolderAt));
            }
            i7 = updateFolderAt;
        }
        smoothScrollToPosition(i7);
        return true;
    }

    public void onPrepareUngrouping(ArrayList<Integer> arrayList) {
        this.mUngroupedFolders = new HashSet<>(arrayList);
        this.mOldItems = saveDataToList();
        this.mUnAffectedItems = new HashSet<>();
        this.mUnAffectedFolders = new HashSet<>();
        Iterator<MediaItem> it = this.mOldItems.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isFolder() && !this.mUngroupedFolders.contains(Integer.valueOf(next.getFolderID()))) {
                this.mUnAffectedFolders.add(Integer.valueOf(next.getFolderID()));
            } else if (!next.isFolder()) {
                this.mUnAffectedItems.add(Integer.valueOf(next.getAlbumID()));
            }
        }
        this.mUngroupAnimationPrepared = true;
        this.mGalleryListView.setPreparingItemAnimation(true);
    }

    public void setFooterEnabled(boolean z) {
        AlbumsFooterView albumsFooterView = this.mFooterView;
        if (albumsFooterView != null) {
            albumsFooterView.onEnableFooterView(z);
        }
    }

    public void setHolderEnabled(ListViewHolder listViewHolder, boolean z) {
        float f;
        listViewHolder.itemView.setEnabled(z);
        ImageView image = listViewHolder.getImage();
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        ViewUtils.setAlpha(image, f);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (this.mFooterView == null && hasFooter() && ViewHolderValue.isFooter(i2)) {
            AlbumsFooterView albumsFooterView = new AlbumsFooterView(((IAlbumsView) this.mView).getEventContext(), getDataCount());
            this.mFooterView = albumsFooterView;
            albumsFooterView.setOnDataChangedListener(new O(8, this));
        }
        return super.onCreateViewHolder(viewGroup, i2);
    }

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        if (isFooter(listViewHolder)) {
            attachFooterViewToHolder(listViewHolder);
        }
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (!hasFooter() || !isFooter(i2)) {
            return super.getMediaItemFromCache(i2, i7);
        }
        return null;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("checkBox") && listViewHolder.getViewType() == 1) {
            listSlideAnimation(listViewHolder);
        }
        super.onBindViewHolder(listViewHolder, i2, list);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isFooter(listViewHolder)) {
            attachFooterViewToHolder(listViewHolder);
            return;
        }
        super.onBindViewHolder(listViewHolder, i2);
        if (listViewHolder.getViewType() == 1) {
            ((AlbumTitleCountReorderHolder) listViewHolder).setIsCustomOrder(this.mIsCustomOrder);
        }
    }
}
