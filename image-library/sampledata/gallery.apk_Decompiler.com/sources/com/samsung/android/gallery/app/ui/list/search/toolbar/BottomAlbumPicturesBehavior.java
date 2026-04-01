package com.samsung.android.gallery.app.ui.list.search.toolbar;

import J5.a;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.FloatingAutoCompleteDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.filter.MultipleFilterKey;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.effects.RenderEffectBlur;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomAlbumPicturesBehavior extends BaseSearchToolbarBehavior {
    private FloatingAutoCompleteDelegate mAutoCompleteDelegate;
    private final RenderEffectBlur mBlurEffect;
    private ViewGroup mFilterLayout;
    private final MvpBasePresenter<?> mPresenter;
    private String mTargetUrl;
    private final IBaseListView mView;

    /* JADX WARNING: type inference failed for: r2v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?>, com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomAlbumPicturesBehavior(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r2) {
        /*
            r1 = this;
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r0 = r2.getView()
            r1.<init>(r0)
            r1.mPresenter = r2
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r2 = r2.getView()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r2 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r2
            r1.mView = r2
            com.samsung.android.gallery.widget.effects.RenderEffectBlur$Builder r0 = new com.samsung.android.gallery.widget.effects.RenderEffectBlur$Builder
            android.view.View r2 = r2.getView()
            r0.<init>(r2)
            com.samsung.android.gallery.widget.effects.RenderEffectBlur r2 = r0.build()
            r1.mBlurEffect = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.BottomAlbumPicturesBehavior.<init>(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter):void");
    }

    private void createMockAlbumFilter(SearchToolbar searchToolbar) {
        String str;
        MediaItem currentItem = this.mPresenter.getCurrentItem();
        if (currentItem != null) {
            if (BucketUtils.isVideos(currentItem.getAlbumID())) {
                str = AppResources.getString(R.string.smart_album_videos);
            } else {
                str = currentItem.getDisplayName();
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.mFilterLayout == null) {
                    ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(searchToolbar.getContext()).inflate(R.layout.recycler_item_search_selected_filters, (ViewGroup) null, false);
                    this.mFilterLayout = viewGroup;
                    ViewUtils.setVisibleOrGone(viewGroup.findViewById(R.id.type_icon), false);
                    ViewUtils.setVisibleOrGone(this.mFilterLayout.findViewById(R.id.delete_icon), false);
                    setTextView(str);
                    ViewMarginUtils.setStartMargin(searchToolbar.getSearchTextView(), 0);
                    searchToolbar.addViewBeforeInputField(this.mFilterLayout);
                    return;
                }
                setTextView(str);
            }
        }
    }

    private String createRecentAlbumTarget(String str) {
        String build = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", str)).appendArg("sub", str).appendArg("title", str).appendArg("term", "key_word").appendArg("collect_keyword", str).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).build();
        if (PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD) {
            return ArgumentsUtil.appendArgs(build, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return build;
    }

    private boolean fromRecentAlbum() {
        String argValue = ArgumentsUtil.getArgValue(this.mTargetUrl, "sub");
        if (TextUtils.isEmpty(argValue) || !BucketUtils.isRecent(UnsafeCast.toInt(argValue))) {
            return false;
        }
        return true;
    }

    private FloatingAutoCompleteDelegate getAutoCompleteDelegate(SearchToolbar searchToolbar) {
        if (this.mAutoCompleteDelegate == null) {
            this.mAutoCompleteDelegate = new FloatingAutoCompleteDelegate(this.mView.getBlackboard(), searchToolbar.getView());
        }
        return this.mAutoCompleteDelegate;
    }

    private void hideSearchToolbarWithAnim(SearchToolbar searchToolbar) {
        searchToolbar.getView().animate().alpha(0.0f).setDuration(120).withEndAction(new a(searchToolbar, 0)).start();
    }

    private boolean isConsumeBackPress(SearchToolbar searchToolbar) {
        if (!searchToolbar.hasFocus()) {
            return false;
        }
        searchToolbar.clear();
        return true;
    }

    private void setDecorViewsHeight(SearchToolbar searchToolbar, int i2, int i7) {
        if (this.mView.getView() != null) {
            int height = ((this.mView.getView().getHeight() - i2) - i7) - searchToolbar.getGradientAreaHeight();
            FloatingAutoCompleteDelegate autoCompleteDelegate = getAutoCompleteDelegate(searchToolbar);
            if (!this.mView.isTabletDpi() && this.mView.isLandscape()) {
                height = 0;
            }
            autoCompleteDelegate.setHeight(height);
        }
    }

    private void setTextView(String str) {
        TextView textView = (TextView) this.mFilterLayout.findViewById(R.id.title);
        textView.setText(str);
        textView.setMaxWidth(StatusCodes.INPUT_MISSING);
    }

    private void showSearchToolbarWithAnimation(SearchToolbar searchToolbar) {
        InputMethodManager inputMethodManager;
        searchToolbar.setGradientVisibility(false);
        searchToolbar.getView().setAlpha(0.0f);
        searchToolbar.getView().animate().alpha(1.0f).setDuration(200).withStartAction(new a(searchToolbar, 1)).start();
        searchToolbar.requestFocus();
        searchToolbar.setQuery("", false);
        if (this.mView.getContext() != null && (inputMethodManager = (InputMethodManager) this.mView.getContext().getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(searchToolbar.getSearchTextView(), 0);
        }
    }

    public void applyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(windowInsets);
        ViewMarginUtils.setBottomMargin(searchToolbar.getView(), iMEInsetsBottom);
        setDecorViewsHeight(searchToolbar, iMEInsetsBottom, 0);
    }

    public Object getData(SearchToolbar searchToolbar, SearchToolbarDataKey searchToolbarDataKey) {
        if (searchToolbarDataKey == SearchToolbarDataKey.IS_BACK_PRESS_CONSUMED) {
            return Boolean.valueOf(isConsumeBackPress(searchToolbar));
        }
        return null;
    }

    public boolean handleEvent(SearchToolbar searchToolbar, EventMessage eventMessage) {
        String str;
        int i2 = eventMessage.what;
        if (i2 == 8016 || i2 == 8017) {
            if (this.mView.isViewResumed()) {
                searchToolbar.clearFocus();
                this.mView.getBlackboard().post("command://MoveURL", MultipleFilterKey.build(this.mTargetUrl, (FilterResultsEntity) eventMessage.obj));
            }
        } else if (i2 == 8520) {
            Object[] objArr = (Object[]) eventMessage.obj;
            if (objArr.length > 1) {
                str = (String) objArr[1];
            } else {
                str = null;
            }
            this.mTargetUrl = str;
            showSearchToolbarWithAnimation(searchToolbar);
        } else if (i2 != 8521) {
            return false;
        } else {
            searchToolbar.setVisibility(8);
            createMockAlbumFilter(searchToolbar);
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        if (searchToolbarEvent.what != 19) {
            return false;
        }
        searchToolbar.getView().setPaddingRelative(((Integer) searchToolbarEvent.obj).intValue(), 0, 0, 0);
        return true;
    }

    public void handleQuery(String str, boolean z) {
        String str2;
        if (fromRecentAlbum()) {
            str2 = createRecentAlbumTarget(str);
        } else {
            FilterResultsEntity filterResultsEntity = new FilterResultsEntity(str, "key_word");
            filterResultsEntity.addRawLabel(str);
            str2 = MultipleFilterKey.build(this.mTargetUrl, filterResultsEntity);
        }
        this.mView.getBlackboard().post("command://MoveURL", str2);
    }

    public void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        searchToolbar.setVisibility(4);
        searchToolbar.getSearchTextView().setTag("SEARCH_AUTO_COMPLETE_VIEW_POSITIONING_ANCHOR");
        searchToolbar.getSearchTextView().setHint((CharSequence) null);
        createMockAlbumFilter(searchToolbar);
        this.mBlurEffect.setDimTouchListener(new a(searchToolbar, 2));
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        this.mView.getBlackboard().post("data:///SearchToolbarFocusChanged", Boolean.valueOf(z));
        if (!z) {
            getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
            hideSearchToolbarWithAnim(searchToolbar);
            this.mBlurEffect.hide();
            searchToolbar.setQuery("", false);
            return;
        }
        this.mBlurEffect.show();
        if (TextUtils.isEmpty(searchToolbar.getQuery())) {
            getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
        } else {
            getAutoCompleteDelegate(searchToolbar).onTextChanged((CharSequence) searchToolbar.getQuery().toString(), true);
        }
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
        if (z) {
            searchToolbar.setVisibility(8);
        }
    }

    public void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
        if (searchToolbar.hasFocus()) {
            if (TextUtils.isEmpty(str)) {
                getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
            } else {
                getAutoCompleteDelegate(searchToolbar).onTextChanged(this.mTargetUrl, str);
            }
        }
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
    }
}
