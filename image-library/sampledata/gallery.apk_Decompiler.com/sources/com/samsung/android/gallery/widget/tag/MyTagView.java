package com.samsung.android.gallery.widget.tag;

import A4.C0368c;
import Ad.C0720a;
import M6.a;
import S3.d;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.embedding.c;
import bc.C0584a;
import c4.C0431a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagView extends RelativeLayout implements ListViewHolder.OnItemClickListener {
    /* access modifiers changed from: private */
    public MyTagAdapter2 mAdapter;
    /* access modifiers changed from: private */
    public MyTagClickListener mClickListener;
    private View mContainerView = LayoutInflater.from(getContext()).inflate(R$layout.viewer_tag_view_layout, this, true);
    private BooleanSupplier mIsTagVisibleSupplier;
    /* access modifiers changed from: private */
    public final MyTagHelper mTagButton = new MyTagHelper();
    final View.OnClickListener mTagButtonClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            MyTagView.this.mTagButton.clearMessage();
            if (MyTagView.this.mAdapter == null) {
                Log.e("MyTagView", "edit/add tag skip");
            } else {
                MyTagView.this.mClickListener.onExecuteTagEditor(MyTagView.this.mAdapter.getData());
            }
        }
    };
    private RecyclerView mTagListView;
    private final Runnable mVisibilityHandler = new C0584a(14, this);

    public MyTagView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private ArrayList<String> getTagList() {
        return (ArrayList) this.mAdapter.getData().stream().filter(new d(25)).map(new C0431a(6)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private void initAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MyTagAdapter2("MyTagView", this);
        }
    }

    private void initTagHelper() {
        View view = this.mContainerView;
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R$id.tag_list_recycler_view);
            this.mTagListView = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(this.mContainerView.getContext(), 0, false));
            this.mTagListView.setAdapter(this.mAdapter);
            this.mTagButton.initTagButton(this.mContainerView, this.mTagListView, this.mTagButtonClickListener);
            return;
        }
        Log.w("MyTagView", "init tag button failed : containerView is null");
    }

    private boolean isTagExist() {
        MyTagAdapter2 myTagAdapter2 = this.mAdapter;
        if (myTagAdapter2 == null || myTagAdapter2.getDataCount() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getTagList$3(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getTitle() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItem$0(MediaItem mediaItem) {
        int dataCount = this.mAdapter.getDataCount();
        this.mAdapter.loadData(mediaItem);
        updateVisibilityAfterChanged(dataCount);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVisibility$2(int i2, boolean z) {
        this.mTagButton.setVisibility(i2, z, isTagExist());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTagData$5(ArrayList arrayList, ArrayList arrayList2) {
        MyTagAdapter2 myTagAdapter2 = this.mAdapter;
        if (myTagAdapter2 != null) {
            myTagAdapter2.lambda$loadData$0(arrayList);
            if (this.mTagButton.isTagButtonChanged(arrayList2.size()) && this.mContainerView != null) {
                this.mTagButton.resetTagEditButton();
                this.mContainerView.post(this.mVisibilityHandler);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateVisibilityAfterChanged$1(int i2) {
        MyTagAdapter2 myTagAdapter2 = this.mAdapter;
        if (myTagAdapter2 != null) {
            int dataCount = myTagAdapter2.getDataCount();
            if (i2 == 0 && dataCount > 0) {
                resetTagVisibility();
            }
        }
    }

    private void setVisibility(int i2, boolean z) {
        View view = this.mContainerView;
        if (view == null) {
            return;
        }
        if (z || view.getVisibility() != i2) {
            ThreadUtil.postOnUiThread(new a(this, i2, z, 2));
        }
    }

    /* access modifiers changed from: private */
    public void updateVisibility() {
        int i2;
        BooleanSupplier booleanSupplier = this.mIsTagVisibleSupplier;
        if (booleanSupplier != null) {
            if (booleanSupplier.getAsBoolean()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            setVisibility(i2, true);
        }
    }

    private void updateVisibilityAfterChanged(int i2) {
        View view = this.mContainerView;
        if (view != null) {
            view.postDelayed(new C0368c(this, i2, 21), 100);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        RecyclerView recyclerView = this.mTagListView;
        if (recyclerView != null && recyclerView.computeHorizontalScrollRange() > this.mTagListView.computeHorizontalScrollExtent()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                requestDisallowInterceptTouchEvent(true);
            } else if (actionMasked == 1 || actionMasked == 3) {
                requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void initMyTagView(BooleanSupplier booleanSupplier, MyTagClickListener myTagClickListener) {
        this.mIsTagVisibleSupplier = booleanSupplier;
        this.mClickListener = myTagClickListener;
        initAdapter();
        initTagHelper();
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (i7 == 1001) {
            this.mTagButton.clearMessage();
            this.mClickListener.onTagClickListener(mediaItem);
        } else if (i7 != 1002) {
            Log.w("MyTagView", "onItemClick invalid type " + i7);
        } else if (this.mAdapter == null) {
            Log.w("MyTagView", "delete tag skip : adapter is null");
        } else {
            ArrayList<String> tagList = getTagList();
            tagList.remove(mediaItem.getTitle());
            this.mClickListener.onTagDeleteClickListener(getTagList(), tagList);
            updateTagData(tagList);
        }
    }

    public void resetTagVisibility() {
        setVisibility(8, false);
        View view = this.mContainerView;
        if (view != null) {
            view.removeCallbacks(this.mVisibilityHandler);
            this.mContainerView.postDelayed(this.mVisibilityHandler, 100);
        }
    }

    public void setMediaItem(MediaItem mediaItem) {
        if (this.mAdapter != null) {
            ThreadUtil.postOnBgThread(new c(17, this, mediaItem));
        } else {
            Log.w("MyTagView", "loadData skip : adapter is null");
        }
    }

    public void setTagVisibility() {
        View view = this.mContainerView;
        if (view != null) {
            view.removeCallbacks(this.mVisibilityHandler);
            this.mContainerView.postDelayed(this.mVisibilityHandler, 100);
        }
    }

    public void updatePosition(int i2, int i7, int i8) {
        View view = this.mContainerView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int dimensionPixelOffset = this.mContainerView.getResources().getDimensionPixelOffset(R$dimen.my_tag_layout_bottom_margin);
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.rightMargin = i7 + dimensionPixelOffset;
            marginLayoutParams.bottomMargin = i8 + dimensionPixelOffset;
            this.mContainerView.setLayoutParams(marginLayoutParams);
        }
    }

    public void updateTagData(ArrayList<String> arrayList) {
        try {
            Collections.sort(arrayList, new D6.a(21));
            ThreadUtil.postOnUiThread(new bc.d((Object) this, (Object) (ArrayList) arrayList.stream().map(new C0431a(7)).collect(Collectors.toCollection(new C0720a(1))), (Object) arrayList, 3));
        } catch (Exception e) {
            Log.e((CharSequence) "MyTagView", "updateTagData failed", (Throwable) e);
        }
    }
}
