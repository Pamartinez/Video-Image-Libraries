package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CleanOutPicturesPresenter extends PicturesPresenter<ICleanOutPicturesView> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CleanOutPicturesMenuUpdater extends ListMenuUpdater {
        public CleanOutPicturesMenuUpdater(ICleanOutPicturesView iCleanOutPicturesView) {
            super(iCleanOutPicturesView, CleanOutPicturesPresenter.this);
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            setMenuVisibility(menu, (int) R.id.action_keep_contents, !LocationKey.isCleanOutDuplicatedPictures(getLocationKey()));
        }
    }

    public CleanOutPicturesPresenter(Blackboard blackboard, ICleanOutPicturesView iCleanOutPicturesView) {
        super(blackboard, iCleanOutPicturesView);
    }

    private String getToolbarString() {
        int i2;
        if (((ICleanOutPicturesView) this.mView).isCleanOutDuplicatedPictures()) {
            i2 = R.string.suggestions_title_delete_duplicate_pictures;
        } else {
            i2 = R.string.old_documents;
        }
        return AppResources.getString(i2);
    }

    private boolean isNeedToShowTitle() {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return false;
        }
        if (((ICleanOutPicturesView) this.mView).isCleanOutDuplicatedPictures() || ((ICleanOutPicturesView) this.mView).isCleanOutExpiredPictures()) {
            return true;
        }
        return false;
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(getContext(), getLocationKey());
    }

    public MenuHandler createMenuHandler() {
        return new CleanOutPicturesMenuHandler();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
    }

    public void updateToolbar(Toolbar toolbar) {
        String str;
        if (!isSelectionMode()) {
            toolbar.setSubtitle((CharSequence) null);
            if (isNeedToShowTitle()) {
                str = getToolbarString();
            } else {
                str = null;
            }
            toolbar.setTitle((CharSequence) str);
            ((ICleanOutPicturesView) this.mView).getAppbarLayout().setTitle((CharSequence) null);
            ((ICleanOutPicturesView) this.mView).getAppbarLayout().setSubtitle((CharSequence) null);
            setNavigationUpButton(toolbar);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(ICleanOutPicturesView iCleanOutPicturesView) {
        return new CleanOutPicturesMenuUpdater(iCleanOutPicturesView);
    }

    public void notifyDataChanged(MediaData mediaData) {
    }
}
