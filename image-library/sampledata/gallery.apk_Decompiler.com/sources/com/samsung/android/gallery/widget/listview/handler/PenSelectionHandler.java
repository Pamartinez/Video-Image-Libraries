package com.samsung.android.gallery.widget.listview.handler;

import A5.a;
import Ab.b;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PenSelectionHandler implements DisHandler {
    protected final String TAG;
    private int mAppBarVisibleHeight;
    protected int mChildCount;
    private final AtomicBoolean mChildOnceInit;
    protected final ArrayList<Integer> mDividerPosition;
    private int mFinalX;
    private int mFinalY;
    protected String mIdTag;
    private final int[] mListDelta;
    protected final ArrayList<Item> mSelectedItems;
    private int mStartX;
    private int mStartY;
    protected final GalleryListView mTargetList;
    private final ArrayList<Item> mVhList;
    private final ArrayList<Integer> mVhPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Item {
        final Rect mRect;
        final int mViewPosition;

        public Item(ListViewHolder listViewHolder, int i2) {
            View rootView = listViewHolder.getRootView();
            int paddingLeft = rootView.getPaddingLeft();
            int x9 = ((int) rootView.getX()) + paddingLeft;
            int y = ((int) rootView.getY()) + i2;
            this.mRect = new Rect(x9, y, (rootView.getWidth() - paddingLeft) + x9, rootView.getHeight() + y);
            this.mViewPosition = listViewHolder.getViewPosition();
        }

        public String toString() {
            return "Item{mViewPosition=" + this.mViewPosition + ", mRect=" + this.mRect + "}";
        }
    }

    public PenSelectionHandler(GalleryListView galleryListView) {
        this(galleryListView, "main");
    }

    private void addAllVisibleViewHolders(GalleryListView galleryListView, int i2, int i7) {
        while (i2 <= i7) {
            ListViewHolder listViewHolder = (ListViewHolder) galleryListView.findViewHolderForLayoutPosition(i2);
            if (listViewHolder != null) {
                if (isData(listViewHolder)) {
                    addViewHolder(listViewHolder, listViewHolder.getViewPosition());
                } else if (isDivider(listViewHolder)) {
                    addDivider(listViewHolder.getViewPosition());
                }
            }
            i2++;
        }
    }

    private void addDivider(int i2) {
        if (i2 >= 0 && !this.mDividerPosition.contains(Integer.valueOf(i2))) {
            this.mDividerPosition.add(Integer.valueOf(i2));
        }
    }

    private void addViewHolder(ListViewHolder listViewHolder, int i2) {
        if (!this.mVhPosition.contains(Integer.valueOf(i2))) {
            Item item = new Item(listViewHolder, getVerticalOffset());
            this.mVhList.add(item);
            this.mVhPosition.add(Integer.valueOf(i2));
            if (this.mDividerPosition.isEmpty()) {
                Optional.ofNullable(this.mTargetList.getAdapter()).ifPresent(new a(this, item));
            }
        }
    }

    private void fillPreviousVisibleViewHolders(int i2) {
        if (this.mVhList.size() > 0) {
            int i7 = ((Item) C0212a.i(this.mVhList, 1)).mViewPosition + 1;
            if (i7 <= i2) {
                int i8 = i7;
                i7 = i2;
                i2 = i8;
            }
            addAllVisibleViewHolders(this.mTargetList, i2, i7);
            String str = this.TAG;
            Log.d(str, "fillPreviousVisibleViewHolders : count=" + this.mVhList.size());
        }
    }

    private void findInnerItems() {
        Rect rect = new Rect(Math.min(this.mStartX, this.mFinalX), Math.min(this.mStartY, this.mFinalY), Math.max(this.mStartX, this.mFinalX), Math.max(this.mStartY, this.mFinalY));
        Iterator<Item> it = this.mVhList.iterator();
        while (it.hasNext()) {
            Item next = it.next();
            if (Rect.intersects(rect, next.mRect)) {
                this.mSelectedItems.add(next);
            }
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("findInnerItems : count=");
        C0086a.A(sb2, this.mSelectedItems, "/");
        sb2.append(this.mVhList.size());
        Log.d(str, sb2.toString());
    }

    private int getAppBarOffset() {
        if (getVerticalOffset() > 0) {
            return this.mAppBarVisibleHeight;
        }
        return 0;
    }

    private int getVerticalOffset() {
        return this.mTargetList.computeVerticalScrollOffset();
    }

    private boolean isData(ListViewHolder listViewHolder) {
        GalleryListView galleryListView;
        if (listViewHolder == null || (galleryListView = this.mTargetList) == null || !galleryListView.isData(listViewHolder.getViewPosition())) {
            return false;
        }
        return true;
    }

    private boolean isDivider(ListViewHolder listViewHolder) {
        GalleryListView galleryListView;
        if (listViewHolder == null || (galleryListView = this.mTargetList) == null || !galleryListView.isDivider(listViewHolder.getViewPosition())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addViewHolder$1(Item item, GalleryListAdapter galleryListAdapter) {
        addDivider(galleryListAdapter.getDividerIndex(item.mViewPosition));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(int i2, ListViewHolder listViewHolder) {
        fillPreviousVisibleViewHolders(i2);
        addViewHolder(listViewHolder, i2);
    }

    private void putAllVisibleViewHolders(GalleryListView galleryListView) {
        addAllVisibleViewHolders(galleryListView, this.mTargetList.findFirstVisibleItemPositionCompat(), this.mTargetList.findLastVisibleItemPositionCompat());
        String str = this.TAG;
        Log.d(str, "putAllVisibleViewHolders : count=" + this.mVhList.size());
    }

    /* renamed from: createChildDisHandler */
    public DisHandler lambda$getChildDisHandler$2(GalleryListView galleryListView) {
        return new PenSelectionHandler(galleryListView, getChildTag());
    }

    public List<DisHandler> getChildDisHandler() {
        GalleryListAdapter adapter;
        List<GalleryListView> selectableHeaderRecyclerListView;
        if (this.mChildOnceInit.getAndSet(true) || (adapter = this.mTargetList.getAdapter()) == null || !adapter.supportHeader() || adapter.getHeaderView() == null || (selectableHeaderRecyclerListView = adapter.getSelectableHeaderRecyclerListView()) == null) {
            return null;
        }
        return (List) selectableHeaderRecyclerListView.stream().map(new a(11, this)).collect(Collectors.toList());
    }

    public String getChildTag() {
        StringBuilder sb2 = new StringBuilder("child#");
        int i2 = this.mChildCount + 1;
        this.mChildCount = i2;
        sb2.append(i2);
        return sb2.toString();
    }

    public void handleCancel() {
        reset();
    }

    public void handleDown(MotionEvent motionEvent, int i2) {
        handleDown((int) motionEvent.getX(), (int) motionEvent.getY(), i2);
    }

    public void handleMove(int i2, int i7) {
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter == null || adapter.isYear() || adapter.isMonthForViewing()) {
            Log.w(this.TAG, "handleMove skipped. do not support year or month for view");
            return;
        }
        this.mTargetList.getLocationInWindow(this.mListDelta);
        int[] iArr = this.mListDelta;
        this.mFinalX = i2 - iArr[0];
        this.mFinalY = (i7 - iArr[1]) + getVerticalOffset() + getAppBarOffset();
        findInnerItems();
        updateSelectionInternal(true);
        this.mSelectedItems.clear();
        this.mDividerPosition.clear();
    }

    public void handleUp(int i2, int i7) {
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter == null || adapter.isYear() || adapter.isMonthForViewing()) {
            Log.w(this.TAG, "handleUp skipped. do not support year or month for view");
            return;
        }
        int[] iArr = this.mListDelta;
        this.mFinalX = i2 - iArr[0];
        this.mFinalY = (i7 - iArr[1]) + getVerticalOffset() + getAppBarOffset();
        String str = this.TAG;
        Log.d(str, "handleUp from {" + this.mStartX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mStartY + "} to {" + this.mFinalX + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mFinalY + "}");
        findInnerItems();
        updateSelectionInternal(false);
        reset();
    }

    public boolean isChildType() {
        return this.mIdTag.contains("child");
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isData(listViewHolder)) {
            listViewHolder.getRootView().post(new b((Object) this, i2, (Object) listViewHolder, 17));
        }
    }

    public void release() {
        resetListener();
        this.mChildOnceInit.set(false);
        this.mChildCount = 0;
    }

    public void reset() {
        Log.d(this.TAG, "reset");
        this.mVhList.clear();
        this.mVhPosition.clear();
        this.mSelectedItems.clear();
        this.mDividerPosition.clear();
    }

    public String toString() {
        return this.TAG + "#" + this.mIdTag + " {" + this.mTargetList + "}";
    }

    public final void updateSelection() {
        GalleryListAdapter adapter;
        if (this.mSelectedItems.size() != 0 && (adapter = this.mTargetList.getAdapter()) != null) {
            if (!adapter.isSelectionMode()) {
                adapter.startSelect(0);
            }
            Iterator<Item> it = this.mSelectedItems.iterator();
            while (it.hasNext()) {
                Item next = it.next();
                adapter.selectItem(next.mViewPosition, !this.mTargetList.isSelected(next.mViewPosition), true);
            }
            this.mDividerPosition.forEach(new E9.a(18, adapter));
            adapter.notifySelectedItemChanged(0, adapter.getItemCount());
        }
    }

    public void updateSelectionInternal(boolean z) {
        updateSelection();
    }

    public PenSelectionHandler(GalleryListView galleryListView, String str) {
        this.TAG = getClass().getSimpleName();
        this.mListDelta = new int[2];
        this.mDividerPosition = new ArrayList<>();
        this.mVhPosition = new ArrayList<>();
        this.mVhList = new ArrayList<>();
        this.mSelectedItems = new ArrayList<>();
        this.mChildOnceInit = new AtomicBoolean(false);
        this.mChildCount = 0;
        this.mTargetList = galleryListView;
        this.mIdTag = str;
    }

    public void handleDown(int i2, int i7, int i8) {
        Log.d(this.TAG, "handleDown", Integer.valueOf(i2), Integer.valueOf(i7));
        this.mTargetList.getLocationInWindow(this.mListDelta);
        this.mAppBarVisibleHeight = i8;
        int[] iArr = this.mListDelta;
        this.mStartX = i2 - iArr[0];
        this.mStartY = (i7 - iArr[1]) + getVerticalOffset() + getAppBarOffset();
        putAllVisibleViewHolders(this.mTargetList);
    }
}
