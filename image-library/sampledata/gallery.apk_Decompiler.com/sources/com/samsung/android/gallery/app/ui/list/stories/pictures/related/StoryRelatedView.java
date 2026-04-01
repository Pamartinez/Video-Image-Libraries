package com.samsung.android.gallery.app.ui.list.stories.pictures.related;

import A2.d;
import A4.C0367b;
import A4.O;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryRelatedView<V extends IStoryPicturesView> {
    private StoryRelatedAdapter mAdapter;
    LinearLayout mContainer;
    RecyclerView mRecyclerView;
    private View mRootView;
    protected V mView;

    public StoryRelatedView(V v, Context context, MediaItem mediaItem) {
        this.mView = v;
        bindView();
        initAdapter();
        initListView();
        if (mediaItem != null) {
            loadData(mediaItem);
        }
    }

    private void bindView() {
        View inflate = LayoutInflater.from(this.mView.getContext()).inflate(getRelatedLayout(), (ViewGroup) null, false);
        this.mRootView = inflate;
        this.mContainer = (LinearLayout) inflate.findViewById(R.id.related_container);
        this.mRecyclerView = (RecyclerView) this.mRootView.findViewById(R.id.related_list);
    }

    private void initAdapter() {
        StoryRelatedAdapter createAdapter = createAdapter();
        this.mAdapter = createAdapter;
        createAdapter.setOnRelatedClickListener(new O(21, this));
    }

    private void initListView() {
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mRootView.getContext(), setLayoutType(), false));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$0(RelatedDataHolder relatedDataHolder) {
        notifyDataSetChanged(relatedDataHolder.pickedStories);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadData$1(RelatedDataHolder relatedDataHolder) {
        ThreadUtil.runOnUiThread(new d(29, this, relatedDataHolder));
    }

    private void notifyDataSetChanged(ArrayList<MediaItem> arrayList) {
        int i2;
        ThreadUtil.assertUiThread("StoryRelated");
        this.mAdapter.setData(arrayList);
        this.mAdapter.notifyDataSetChanged();
        this.mView.notifyFooterChanged(getView());
        View view = this.mRootView;
        if (arrayList.size() == 0) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        ViewUtils.setVisibility(view, i2);
    }

    /* access modifiers changed from: private */
    public void onRelatedItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mContainer.isEnabled()) {
            this.mView.onRelatedItemClick(listViewHolder, mediaItem);
        }
    }

    private void restoreViewState(View view, View view2) {
        ViewUtils.setVisibility(this.mRootView, view.getVisibility());
        setEnabled(view2.getAlpha(), view2.isEnabled());
    }

    public StoryRelatedAdapter createAdapter() {
        return new StoryRelatedAdapter();
    }

    public int getRelatedLayout() {
        return R.layout.story_pictures_related_layout;
    }

    public View getView() {
        return this.mRootView;
    }

    public void loadData(MediaItem mediaItem) {
        RelatedDataHolder relatedDataHolder = new RelatedDataHolder();
        V v = this.mView;
        Objects.requireNonNull(v);
        new RelatedStoryLoader.Requester(mediaItem, relatedDataHolder, new O(20, v)).setFavoriteOnly(false).enableCollageStory(true).request(new C0367b(23, this));
    }

    public void setEnabled(float f, boolean z) {
        ViewUtils.setAlpha(this.mContainer, f);
        ViewUtils.setViewEnabled(this.mContainer, z);
    }

    public int setLayoutType() {
        return 1;
    }

    public void updateView(Context context) {
        View view = this.mRootView;
        LinearLayout linearLayout = this.mContainer;
        bindView();
        initListView();
        restoreViewState(view, linearLayout);
        this.mView.notifyFooterChanged(this.mRootView);
    }

    public void destroy() {
    }
}
