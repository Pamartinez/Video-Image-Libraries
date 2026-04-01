package com.samsung.android.gallery.app.ui.list.sharings.family;

import H7.A;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySuggestedPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.family.IFamilySuggestedPicturesView;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilySuggestedPicturesFragment<V extends IFamilySuggestedPicturesView, P extends FamilySuggestedPicturesPresenter<V>> extends PicturesFragment<V, P> implements IFamilySuggestedPicturesView {
    private AtomicBoolean mFirstUpdate = new AtomicBoolean(false);
    private Integer mMaxSelectionCount;

    public int getLayoutId() {
        return R.layout.fragment_family_suggested_pictures_layout;
    }

    public int getMaxUploadedCount() {
        if (this.mMaxSelectionCount == null) {
            this.mMaxSelectionCount = Integer.valueOf(MdeSharingHelper.getMaxUploadCount(getContext()));
        }
        return this.mMaxSelectionCount.intValue();
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_SHARED_FAMILY_SUGGESTED_PICTURES_VIEW_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_SHARED_FAMILY_SUGGESTED_PICTURES_VIEW_NORMAL.toString();
    }

    public void initializeEmptyView() {
        super.initializeEmptyView();
        View view = this.mEmptyView;
        if (view != null) {
            NoItemView noItemView = (NoItemView) view.findViewById(R.id.no_item_view);
            if (((FamilySuggestedPicturesPresenter) this.mPresenter).hasPeopleList()) {
                noItemView.setDescription(getResources().getString(R.string.no_people_analyzed_on_your_phone));
                return;
            }
            Features features = Features.SUPPORT_PET_ON_AUTO_ALBUM;
            if (Features.isEnabled(features)) {
                noItemView.setLabel((String) null);
                noItemView.setDescription((int) R.string.select_people_and_pets_suggestions_description);
                ViewMarginUtils.setBottomMargin(noItemView, getResources().getDimensionPixelOffset(R.dimen.album_empty_view_container_vertical_spacing));
            } else {
                noItemView.setVisibility(8);
            }
            LayoutInflater.from(getContext()).inflate(R.layout.sharing_pictures_empty_button_layout, (ViewGroup) this.mEmptyView.findViewById(R.id.no_item_view_container));
            View findViewById = this.mEmptyView.findViewById(R.id.empty_button);
            if (Features.isEnabled(features) && (findViewById instanceof TextView)) {
                ((TextView) findViewById).setText(R.string.select_people_and_pets);
            }
            FamilySuggestedPicturesPresenter familySuggestedPicturesPresenter = (FamilySuggestedPicturesPresenter) this.mPresenter;
            Objects.requireNonNull(familySuggestedPicturesPresenter);
            findViewById.setOnClickListener(new A(12, familySuggestedPicturesPresenter));
            findViewById.setVisibility(0);
        }
    }

    public boolean isNormalMode() {
        return ((FamilySuggestedPicturesPresenter) this.mPresenter).isNormalMode();
    }

    public boolean onBackPressed() {
        if (!isNormalMode() || !super.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (getItemCount() > 0 && getContext() != null && !isNormalMode() && !this.mFirstUpdate.getAndSet(true)) {
            enterSelectionMode(MdeSharingHelper.getMaxUploadCount(getContext()));
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public FamilySuggestedPicturesViewAdapter<IFamilySuggestedPicturesView> createListViewAdapter(GalleryListView galleryListView) {
        return new FamilySuggestedPicturesViewAdapter<>(this, getLocationKey(), true);
    }

    public FamilySuggestedPicturesPresenter createPresenter(IFamilySuggestedPicturesView iFamilySuggestedPicturesView) {
        return new FamilySuggestedPicturesPresenter(this.mBlackboard, iFamilySuggestedPicturesView);
    }

    public FamilySuggestedPicturesViewAdapter<V> getAdapter() {
        return (FamilySuggestedPicturesViewAdapter) this.mListAdapter;
    }
}
