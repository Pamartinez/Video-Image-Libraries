package com.samsung.android.gallery.app.ui.list.search.pictures.category;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryPicturesFragment<V extends ISearchPicturesView> extends SearchPicturesFragment<V, CategoryPicturesPresenter<V>> implements ISearchPicturesView {
    public boolean supportContentHeader() {
        return true;
    }

    public CategoryPicturesPresenter<ISearchPicturesView> createPresenter(ISearchPicturesView iSearchPicturesView) {
        return new CategoryPicturesPresenter<>(this.mBlackboard, iSearchPicturesView);
    }
}
