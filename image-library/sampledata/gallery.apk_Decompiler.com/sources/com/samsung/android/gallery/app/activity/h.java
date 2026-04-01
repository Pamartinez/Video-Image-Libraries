package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.app.activity.GalleryFragmentFactory;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.all.MxAllAlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.MxManageAlbumsFragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;
import com.samsung.android.gallery.app.ui.list.search.category.document.SearchDocumentFragment;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationFragment;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectFragmentV2;
import com.samsung.android.gallery.app.ui.list.search.category.people.HiddenCreatureFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.SelectMeFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectFragment;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNameFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterCategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterPicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements GalleryFragmentFactory.FragmentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2497a;

    public /* synthetic */ h(int i2) {
        this.f2497a = i2;
    }

    public final MvpBaseFragment create() {
        switch (this.f2497a) {
            case 0:
                return new OnDemandFloatingFragment();
            case 1:
                return new ClusterCategoryFragment();
            case 2:
                return new ClusterPicturesFragment();
            case 3:
                return new MxAllAlbumsFragment();
            case 4:
                return new SearchPicturesFragment();
            case 5:
                return new HiddenCreatureFragment();
            case 6:
                return new SelectMeFragment();
            case 7:
                return new SuggestedCreatureSelectFragment();
            case 8:
                return new SearchLocationFragment();
            case 9:
                return new EditCreatureNameFragment();
            case 10:
                return new CreatureCoverChoiceFragment();
            case 11:
                return new SearchDocumentFragment();
            case 12:
                return new CreatureCategoryFragment();
            case 13:
                return new MxManageAlbumsFragment();
            case 14:
                return new PeopleSelectFragment();
            case 15:
                return new CreatureSelectFragmentV2();
            case 16:
                return new SearchMyQueryFragment();
            case 17:
                return new SharingPicturesFragment();
            default:
                return new ClusteringMapFragmentV2();
        }
    }
}
