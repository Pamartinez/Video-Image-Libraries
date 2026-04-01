package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.app.activity.GalleryFragmentFactory;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoiceFragment;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoiceFragment;
import com.samsung.android.gallery.app.ui.list.albums.hide.AlbumsHideFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.virtual.RepairPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.virtual.VirtualAlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.UpdatableMapFragment;
import com.samsung.android.gallery.app.ui.list.mtp.MtpFragment;
import com.samsung.android.gallery.app.ui.list.mtp.pictures.MtpPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.CollectionFragment;
import com.samsung.android.gallery.app.ui.list.search.SearchFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHideFragment;
import com.samsung.android.gallery.app.ui.list.search.floating.FloatingRecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerFragment;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoiceFragment;
import com.samsung.android.gallery.app.ui.list.sharings.factory.SharedAlbumFactory;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySharedAlbumWelcomeFragment;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySuggestedPicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.invitations.InvitationsFragment;
import com.samsung.android.gallery.app.ui.list.sharings.storage.SharingStorageUseFragment;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.HideRuleFragment;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionFragment;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionFragmentV2;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.CleanOutPicturesFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.fixup.FixUpPicturesFragment;
import com.samsung.android.gallery.app.ui.list.trash.TrashFragment;
import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements GalleryFragmentFactory.FragmentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2496a;

    public /* synthetic */ g(int i2) {
        this.f2496a = i2;
    }

    public final MvpBaseFragment create() {
        switch (this.f2496a) {
            case 0:
                return new AlbumPicturesFragment();
            case 1:
                return new UpdatableMapFragment();
            case 2:
                return new SuggestionContainerFragment();
            case 3:
                return new FloatingRecommendationFragment();
            case 4:
                return new TrashContainerFragment();
            case 5:
                return new TrashFragment();
            case 6:
                return new MtpPicturesFragment();
            case 7:
                return new VirtualAlbumPicturesFragment();
            case 8:
                return new RepairPicturesFragment();
            case 9:
                return new AlbumsHideFragment();
            case 10:
                return new AlbumChoiceFragment();
            case 11:
                return new CategoryFragment();
            case 12:
                return new AlbumFolderChoiceFragment();
            case 13:
                return new MtpFragment();
            case 14:
                return SharedAlbumFactory.create();
            case 15:
                return new SharingAlbumChoiceFragment();
            case 16:
                return new InvitationsFragment();
            case 17:
                return new SearchFragment();
            case 18:
                return new FamilySharedAlbumWelcomeFragment();
            case 19:
                return new FamilySuggestedPicturesFragment();
            case 20:
                return new SharingStorageUseFragment();
            case 21:
                return new SelectionViewFragment();
            case 22:
                return new CreatureHideFragment();
            case 23:
                return new SuggestionsFragment();
            case 24:
                return new CleanOutPicturesFragment();
            case 25:
                return new CollectionFragment();
            case 26:
                return new FixUpPicturesFragment();
            case 27:
                return new HideRuleFragment();
            case 28:
                return new HideSceneSelectionFragmentV2();
            default:
                return new HideSceneSelectionFragment();
        }
    }
}
