package com.samsung.android.gallery.app.ui.list.search.category.people;

import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICreatureCategoryView extends ICategoryView {
    void editTop5Done();

    void enableEditMode(boolean z);

    void executeAddFaces();

    boolean hasHiddenCreature();

    boolean hasOnlyMe();

    boolean hasTop5();

    boolean isTop5Changed();

    boolean isTop5EditMode();

    void onSelectMeDone();
}
