package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteViewV2;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompletePresenterV2;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.progressbar.SearchProgressCircle;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompleteFragmentV2<V extends ISearchAutoCompleteViewV2, P extends SearchAutoCompletePresenterV2> extends MvpBaseFragment<V, P> implements ISearchAutoCompleteViewV2 {
    private AlphaAnimator mAlphaAnimator;
    private SearchAutoCompleteAdapterV2 mAutoCompleteAdapterV2;
    ImageView mBackground;
    SearchProgressCircle mProgressCircle;
    GalleryRecyclerView mRecyclerView;

    private void initAnimator() {
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) this.mRecyclerView, 0.0f, 1.0f);
        this.mAlphaAnimator = alphaAnimator;
        alphaAnimator.setDuration(500);
    }

    private void initRecyclerView(GalleryRecyclerView galleryRecyclerView) {
        galleryRecyclerView.setHasFixedSize(true);
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        galleryRecyclerView.drawBottomColor();
    }

    private void resetView() {
        GalleryRecyclerView galleryRecyclerView = this.mRecyclerView;
        if (galleryRecyclerView != null) {
            ViewUtils.removeAllViews(galleryRecyclerView);
            this.mRecyclerView.setVisibility(8);
        }
        this.mBackground.setVisibility(8);
    }

    private void setAutoCompleteVisibility() {
        int i2;
        if (this.mRecyclerView != null) {
            if (this.mAutoCompleteAdapterV2.isEmpty()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            this.mBackground.setVisibility(i2);
            this.mRecyclerView.setVisibility(i2);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mRecyclerView = (GalleryRecyclerView) view.findViewById(R.id.auto_complete_recycler_view);
        this.mProgressCircle = (SearchProgressCircle) view.findViewById(R.id.progressCircle);
        this.mBackground = (ImageView) view.findViewById(R.id.background_layer);
    }

    public String getKeyword() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((SearchAutoCompletePresenterV2) p6).getKeyword();
        }
        return "";
    }

    public int getLayoutId() {
        return R.layout.search_suggestion_keyword_fragment_v2;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString();
    }

    public void initView(View view) {
        initRecyclerView(this.mRecyclerView);
        initAnimator();
        setProgressBarVisibility(true);
    }

    public void onAttach(Context context) {
        setLocationKey(getTag());
        super.onAttach(context);
    }

    public void onAutoCompleteKeywordClick(AutoCompleteItem autoCompleteItem) {
        ((SearchAutoCompletePresenterV2) this.mPresenter).onAutoCompleteItemClick(autoCompleteItem);
    }

    public void onAutoCompletePublished(ArrayList<AutoCompleteItem> arrayList) {
        setProgressBarVisibility(false);
        SearchAutoCompleteAdapterV2 searchAutoCompleteAdapterV2 = this.mAutoCompleteAdapterV2;
        if (searchAutoCompleteAdapterV2 == null) {
            SearchAutoCompleteAdapterV2 searchAutoCompleteAdapterV22 = new SearchAutoCompleteAdapterV2(this);
            this.mAutoCompleteAdapterV2 = searchAutoCompleteAdapterV22;
            searchAutoCompleteAdapterV22.setData(arrayList);
            this.mRecyclerView.setAdapter(this.mAutoCompleteAdapterV2);
            this.mAlphaAnimator.start();
            return;
        }
        searchAutoCompleteAdapterV2.setData(arrayList);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBlackboard.erase("command://MoveURL");
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        setProgressBarVisibility(!z);
        if (z) {
            resetView();
            ((SearchAutoCompletePresenterV2) this.mPresenter).resetKeyword();
            return;
        }
        ((SearchAutoCompletePresenterV2) this.mPresenter).setKeywordFromLocationKey();
    }

    public void onLoadAutoCompleteItems() {
        setAutoCompleteVisibility();
    }

    public void setProgressBarVisibility(boolean z) {
        SearchProgressCircle searchProgressCircle = this.mProgressCircle;
        if (searchProgressCircle != null) {
            searchProgressCircle.updateVisibility(z);
        }
    }

    public boolean supportActivityToolbar() {
        return !PickerUtil.isNormalLaunchMode(this.mBlackboard);
    }

    public String toString() {
        return getClass().getSimpleName() + Log.TAG_SEPARATOR + hashCode();
    }

    public SearchAutoCompletePresenterV2 createPresenter(ISearchAutoCompleteViewV2 iSearchAutoCompleteViewV2) {
        return new SearchAutoCompletePresenterV2(this.mBlackboard, iSearchAutoCompleteViewV2);
    }
}
