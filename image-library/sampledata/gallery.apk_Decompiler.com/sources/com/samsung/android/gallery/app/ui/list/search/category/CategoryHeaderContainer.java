package com.samsung.android.gallery.app.ui.list.search.category;

import A4.C0369d;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.search.category.header.AbsCategoryHeaderView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import g6.g;
import ic.l;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryHeaderContainer implements ISearchHeaderView {
    private final ArrayList<AbsCategoryHeaderView> mCategoryHeaderViews = new ArrayList<>();
    private ViewGroup mHeaderContainer;
    private View mHeaderView;
    private final ISearchView mView;

    public CategoryHeaderContainer(ISearchView iSearchView) {
        this.mView = iSearchView;
    }

    private int getLayoutId() {
        return R.layout.recycler_item_cardlist_layout_header_only;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleOrientationChange$1(int i2, AbsCategoryHeaderView absCategoryHeaderView) {
        throw null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleResolutionChange$2(int i2, AbsCategoryHeaderView absCategoryHeaderView) {
        throw null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(AbsCategoryHeaderView absCategoryHeaderView) {
        throw null;
    }

    public void dataChanged() {
        this.mCategoryHeaderViews.forEach(new l(8));
    }

    public void destroy() {
        this.mHeaderContainer.removeAllViews();
    }

    public View get() {
        return this.mHeaderView;
    }

    public void handleOrientationChange(int i2) {
        this.mCategoryHeaderViews.forEach(new C0369d(i2, 25));
    }

    public void handleResolutionChange(int i2) {
        this.mCategoryHeaderViews.forEach(new C0369d(i2, 26));
    }

    public void initView() {
        View inflate = LayoutInflater.from(this.mView.getActivity()).inflate(getLayoutId(), (ViewGroup) null);
        this.mHeaderView = inflate;
        this.mHeaderContainer = (ViewGroup) inflate.findViewById(R.id.header_container);
        boolean z = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
        this.mCategoryHeaderViews.forEach(new g(10, this));
    }
}
