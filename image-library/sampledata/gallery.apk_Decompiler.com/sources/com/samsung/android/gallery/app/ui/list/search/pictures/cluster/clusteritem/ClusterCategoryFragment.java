package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterCategoryFragment extends CategoryFragment {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePaddingHorizontal$0(Context context) {
        ViewMarginUtils.setHorizontalPadding(getListView(), context.getResources().getDimensionPixelSize(R.dimen.search_cluster_results_horizontal_padding));
    }

    private void updatePaddingHorizontal() {
        Optional.ofNullable(getContext()).ifPresent(new a(27, this));
    }

    public void bindView(View view) {
        super.bindView(view);
        updatePaddingHorizontal();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SEARCH_RESULT_CLUSTER_CATEGORY.toString();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updatePaddingHorizontal();
    }

    public ClusterCategoryItemAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new ClusterCategoryItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public ClusterCategoryPresenter createPresenter(ICategoryView iCategoryView) {
        return new ClusterCategoryPresenter(this.mBlackboard, iCategoryView);
    }
}
