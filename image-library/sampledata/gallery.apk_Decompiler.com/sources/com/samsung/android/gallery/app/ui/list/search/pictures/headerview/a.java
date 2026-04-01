package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.RecommendContactDelegate;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements RecommendContactDelegate.DataLoadListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2542a;
    public final /* synthetic */ SearchCountHeaderView b;

    public /* synthetic */ a(SearchCountHeaderView searchCountHeaderView, int i2) {
        this.f2542a = i2;
        this.b = searchCountHeaderView;
    }

    public final void onLoadCompleted(CreatureNameData creatureNameData) {
        int i2 = this.f2542a;
        SearchCountHeaderView searchCountHeaderView = this.b;
        switch (i2) {
            case 0:
                ((SearchCreatureHeader2View) searchCountHeaderView).lambda$loadContactRecommendData$4(creatureNameData);
                return;
            default:
                ((SearchCreatureHeaderView) searchCountHeaderView).lambda$getRecommendContactDelegate$0(creatureNameData);
                return;
        }
    }
}
