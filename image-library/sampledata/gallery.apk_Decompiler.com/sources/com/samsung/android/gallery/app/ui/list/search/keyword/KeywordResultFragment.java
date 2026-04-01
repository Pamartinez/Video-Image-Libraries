package com.samsung.android.gallery.app.ui.list.search.keyword;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.keyword.IKeywordResultView;
import com.samsung.android.gallery.app.ui.list.search.keyword.KeywordResultPresenter;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.SearchResultStoriesContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.noitem.EmptyViewListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeywordResultFragment<V extends IKeywordResultView, P extends KeywordResultPresenter<V>> extends SearchPicturesFragment<V, P> implements IKeywordResultView {
    private View mContainer;
    private ViewGroup mFilterContainer;
    private boolean mIsFirstLoading = true;
    /* access modifiers changed from: private */
    public boolean mIsPicturesOnlyMode;
    private View mParent;
    private boolean mPendingLayoutChange;
    private TextView mPicturesCount;
    /* access modifiers changed from: private */
    public boolean mShowSearchPictures;
    private SearchResultStoriesContainer mStoriesContainer;

    private void bindKeywordResultLayout(View view) {
        this.mContainer = view.findViewById(R.id.keyword_result_container);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.filter_result_container);
        this.mFilterContainer = viewGroup;
        viewGroup.addView(createHeaderView(), -1, -2);
        GalleryListView galleryListView = (GalleryListView) view.findViewById(R.id.pictures_recycler_view);
        this.mRecyclerView = galleryListView;
        galleryListView.seslSetHoverScrollEnabled(true);
        View findViewById = view.findViewById(R.id.pictures_title_header);
        this.mPicturesCount = (TextView) findViewById.findViewById(R.id.search_keyword_divider_count);
        findViewById.findViewById(R.id.search_keyword_divider_arrow).setOnClickListener(new C0496a(18, this));
        this.mStoriesContainer = new SearchResultStoriesContainer(view.findViewById(R.id.search_result_stories_container), this.mPresenter);
    }

    private void bindPicturesOnlyLayout(View view) {
        this.mContainer = view.findViewById(R.id.list_container);
        GalleryListView galleryListView = (GalleryListView) view.findViewById(R.id.my_recycler_view);
        this.mRecyclerView = galleryListView;
        galleryListView.seslSetHoverScrollEnabled(true);
        if (useAdvancedMouseDragAndDrop()) {
            this.mKeyHandler.rebindMouseHandler();
        }
        this.mEmptyView = view.findViewById(R.id.empty_view);
        initializeEmptyView();
        registerEmptyViewListener();
        addAppbarOffsetChangedListener();
    }

    private void bindViewInternal() {
        if (this.mIsPicturesOnlyMode) {
            bindPicturesOnlyLayout(this.mParent);
        } else {
            bindKeywordResultLayout(this.mParent);
        }
    }

    private void changeViewLayout() {
        this.mPendingLayoutChange = false;
        this.mShowSearchPictures = true;
        this.mIsPicturesOnlyMode = true;
        setContainerVisibility(false);
        destroyListView();
        bindViewInternal();
        loadPinchColumns();
        initListView();
        updateMenu();
    }

    private void destroyListView() {
        this.mLayoutManager = null;
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.destroy();
            this.mListAdapter = null;
        }
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setAdapter((RecyclerView.Adapter) null);
            listView.setEmptyViewListener((EmptyViewListener) null);
            listView.setRecycledViewPool((RecyclerView.RecycledViewPool) null);
        }
    }

    private void initListView() {
        int i2;
        this.mRecyclerView.setColumnCount(getPinchColumn());
        GalleryListView galleryListView = this.mRecyclerView;
        if (isDexMode()) {
            i2 = getStartPinchDepthDex();
        } else {
            i2 = getStartPinchDepth();
        }
        galleryListView.setStartDepth(i2);
        this.mRecyclerView.setLayoutManager(getLayoutManager());
        this.mRecyclerView.setPinchAnimationManager(getPinchAnimationManager());
    }

    private boolean isPicturesOnlyModeFrom(String str) {
        return ArgumentsUtil.getArgValue(str, "search_keyword_pictures_only", false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindKeywordResultLayout$0(View view) {
        ((KeywordResultPresenter) this.mPresenter).onPicturesViewAllClicked();
    }

    private void updateMenu() {
        ((KeywordResultPresenter) this.mPresenter).updateMenu();
    }

    public void addLayoutListenerForAutoScroll() {
        if (supportShrinkTransition()) {
            int returnPosition = SharedTransition.getReturnPosition(this.mBlackboard);
            if (getAdapter() == null || getAdapter().getItemCount() <= returnPosition) {
                this.mBlackboard.erase("data://shrink_active");
            } else {
                super.addLayoutListenerForAutoScroll();
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mParent = view;
        bindViewInternal();
    }

    public int getLayoutId() {
        return R.layout.fragment_keyword_result;
    }

    public int getStartPinchDepth() {
        if (this.mIsPicturesOnlyMode) {
            return super.getStartPinchDepth();
        }
        return 0;
    }

    public void initArguments(Bundle bundle) {
        super.initArguments(bundle);
        this.mIsPicturesOnlyMode = isPicturesOnlyModeFrom(getLocationKey());
    }

    public int[] loadPinchColumnResource() {
        if (this.mIsPicturesOnlyMode) {
            return super.loadPinchColumnResource();
        }
        return getResources().getIntArray(R.array.search_keyword_column_count);
    }

    public void onDataChangedOnUi() {
        if (this.mPendingLayoutChange) {
            changeViewLayout();
        }
        super.onDataChangedOnUi();
        if (this.mIsFirstLoading) {
            this.mIsFirstLoading = false;
        }
    }

    public void onDestroy() {
        SearchResultStoriesContainer searchResultStoriesContainer = this.mStoriesContainer;
        if (searchResultStoriesContainer != null) {
            searchResultStoriesContainer.destroy();
            this.mStoriesContainer = null;
        }
        super.onDestroy();
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        setContainerVisibility(!isEmptyViewShowing());
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsFirstLoading && !this.mIsPicturesOnlyMode) {
            ((KeywordResultPresenter) this.mPresenter).reopenData();
        }
    }

    public void replaceHeaderView(View view) {
        if (this.mShowSearchPictures) {
            super.replaceHeaderView(view);
            return;
        }
        ViewUtils.removeAllViews(this.mFilterContainer);
        this.mFilterContainer.addView(view, -1, -2);
    }

    public void savePinchDepth(String str, int i2) {
        if (this.mIsPicturesOnlyMode) {
            super.savePinchDepth(str, i2);
        }
    }

    public void setContainerVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mContainer, z);
    }

    public void setPendingLayoutChange() {
        if (!this.mShowSearchPictures) {
            this.mPendingLayoutChange = true;
            this.mShowSearchPictures = true;
        }
    }

    public void storyDataLoaded(String str) {
        StringCompat stringCompat = this.TAG;
        Log.s(stringCompat, "storyDataLoaded : " + Logger.getEncodedString(str));
        if (this.mStoriesContainer != null && str.length() > 2) {
            this.mStoriesContainer.update(str);
        } else if (!isPicturesOnlyModeFrom(getLocationKey()) && !this.mShowSearchPictures) {
            setPendingLayoutChange();
        }
    }

    public boolean supportContentHeader() {
        return this.mShowSearchPictures;
    }

    public boolean supportMenu() {
        if (this.mShowSearchPictures || this.mIsPicturesOnlyMode) {
            return true;
        }
        return false;
    }

    public boolean supportSelection() {
        return this.mIsPicturesOnlyMode;
    }

    public boolean supportViewPool() {
        return false;
    }

    public void updateItemCounts() {
        if (supportContentHeader()) {
            super.updateItemCounts();
        } else {
            ViewUtils.setText(this.mPicturesCount, String.valueOf(getDataCount()));
        }
    }

    public SearchPicturesAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchPicturesAdapter<ISearchPicturesView>(this, getLocationKey(), this.mShowSearchPictures ? createHeaderView() : null, this.mIsPicturesOnlyMode) {
            public int getHintStartSpan(int i2, int i7) {
                if (KeywordResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getHintStartSpan(i2, i7);
                }
                return i2 % i7;
            }

            public int getItemCount() {
                if (KeywordResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getItemCount();
                }
                return Math.min(KeywordResultFragment.this.getColumnCount() * KeywordResultFragment.this.getColumnCount(), this.mDataCount);
            }

            public int getItemViewType(int i2) {
                if (KeywordResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getItemViewType(i2);
                }
                return 0;
            }

            public int getMediaItemPosition(int i2, int i7) {
                return KeywordResultFragment.this.mIsPicturesOnlyMode ? super.getMediaItemPosition(i2, i7) : i2;
            }

            public int getSpanSize(int i2) {
                if (KeywordResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getSpanSize(i2);
                }
                return 1;
            }

            public int getViewPosition(int i2) {
                if (KeywordResultFragment.this.mIsPicturesOnlyMode) {
                    return super.getViewPosition(i2);
                }
                return i2;
            }

            public boolean isDivider(int i2) {
                return KeywordResultFragment.this.mIsPicturesOnlyMode && this.mMultiClusterAdapter.isDivider(i2);
            }

            public boolean supportHeader() {
                return KeywordResultFragment.this.mShowSearchPictures;
            }

            public int getMediaItemPosition(int i2) {
                return KeywordResultFragment.this.mIsPicturesOnlyMode ? super.getMediaItemPosition(i2) : i2;
            }

            public boolean isDivider(int i2, int i7) {
                return KeywordResultFragment.this.mIsPicturesOnlyMode && this.mMultiClusterAdapter.isDivider(i2, i7);
            }
        };
    }

    public KeywordResultPresenter createPresenter(IKeywordResultView iKeywordResultView) {
        return new KeywordResultPresenter(this.mBlackboard, iKeywordResultView);
    }
}
