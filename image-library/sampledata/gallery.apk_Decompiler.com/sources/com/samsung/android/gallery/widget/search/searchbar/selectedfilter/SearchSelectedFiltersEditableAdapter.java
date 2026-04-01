package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import Ba.h;
import V3.b;
import Xb.a;
import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteDataLoader;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteDataLoaderFactory;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.search.filter.MultipleFilterKey;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.search.searchbar.InstantSearchHandler;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFiltersEditableAdapter extends SearchSelectedFiltersAdapter {
    private BiConsumer<ArrayList<AutoCompleteItem>, String> mAutoCompleteDataChangeListener;
    private final AutoCompleteDataLoader mAutoCompleteDataLoader = AutoCompleteDataLoaderFactory.create();
    /* access modifiers changed from: private */
    public String mCurrentKeyword;
    private Runnable mDismissAutoCompleteView;
    private Boolean mFromRecentAlbum;
    /* access modifiers changed from: private */
    public InstantSearchHandler mInstantSearchHandler;
    private boolean mIsDataAdded;
    /* access modifiers changed from: private */
    public Runnable mLoadAutoComplete;
    private boolean mPendingRemoveFirstBubble;
    private final boolean mScopedSearchEnabled;
    private String mSearchedKeyword;
    private final boolean mSubCategoryRemovable;
    private final TextWatcher mWatcher = new SimpleTextWatcher() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTextChanged$0(String str) {
            SearchSelectedFiltersEditableAdapter.this.loadAutoComplete(str);
        }

        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            Blackboard blackboard = SearchSelectedFiltersEditableAdapter.this.mBlackboard;
            if (blackboard != null) {
                String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(blackboard);
                SearchSelectedFiltersEditableAdapter.this.mCurrentKeyword = charSequence.toString();
                if (SearchSelectedFiltersEditableAdapter.this.mLoadAutoComplete != null) {
                    ThreadUtil.removeCallbackOnUiThread(SearchSelectedFiltersEditableAdapter.this.mLoadAutoComplete);
                }
                SearchSelectedFiltersEditableAdapter.this.mLoadAutoComplete = new b(0, this, readLocationKeyCurrent);
                ThreadUtil.postOnUiThreadDelayed(SearchSelectedFiltersEditableAdapter.this.mLoadAutoComplete, 200);
                if (SearchSelectedFiltersEditableAdapter.this.mInstantSearchHandler != null) {
                    SearchSelectedFiltersEditableAdapter.this.mInstantSearchHandler.postSearch(SearchSelectedFiltersEditableAdapter.this.mCurrentKeyword);
                }
            }
        }
    };

    public SearchSelectedFiltersEditableAdapter(Blackboard blackboard) {
        super(blackboard);
        boolean z = true;
        if (PreferenceFeatures.OneUi8x.INSTANT_SEARCH) {
            this.mInstantSearchHandler = new InstantSearchHandler(true, new h(20, this));
        }
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        this.mSubCategoryRemovable = ArgumentsUtil.getArgValue(readLocationKeyCurrent, "subCategoryRemovable", true);
        this.mPendingRemoveFirstBubble = fromRecentAlbum(readLocationKeyCurrent);
        this.mScopedSearchEnabled = (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || LocationKey.isSearchKeyword(readLocationKeyCurrent)) ? false : z;
    }

    private void actionSearch(SearchSelectedFiltersEditableViewHolder searchSelectedFiltersEditableViewHolder, TextView textView) {
        if (!TextUtils.isEmpty(textView.getText().toString().trim())) {
            this.mAutoCompleteDataLoader.cancelLastRequest();
            searchKeyword(textView.getText().toString());
            clearIME(searchSelectedFiltersEditableViewHolder.itemView.getContext(), textView);
        }
    }

    private void addDataWithCheckingFuzzyKeyword(FilterResultsEntity filterResultsEntity, String str) {
        if (hasFuzzyKeyword(str)) {
            reopenData(changeFilterUsingFuzzyKeyword(filterResultsEntity, str), filterResultsEntity);
        } else {
            super.addData(filterResultsEntity);
        }
    }

    private String changeFilterUsingFuzzyKeyword(FilterResultsEntity filterResultsEntity, String str) {
        int size = this.mData.size() - 1;
        FilterResultsEntity filterResultsEntity2 = this.mData.get(size);
        this.mData.remove(size);
        FilterResultsEntity changeSearchedKeywordToFilter = changeSearchedKeywordToFilter(getBubbleKeyword(str));
        this.mData.add(changeSearchedKeywordToFilter);
        if (filterResultsEntity != null) {
            this.mData.add(filterResultsEntity);
        }
        notifyDataSetChanged();
        if (hasFuzzyKeywordInSelectedFilter(str)) {
            return SearchFilterUtil.replaceFilterToFuzzyOnLocationKey(str, changeSearchedKeywordToFilter, filterResultsEntity2.getName());
        }
        return str;
    }

    private FilterResultsEntity changeSearchedKeywordToFilter(String str) {
        FilterResultsEntity filterResultsEntity = new FilterResultsEntity(str, IntelligentSearchIndexFieldIcon.create("key_word", str), "key_word");
        filterResultsEntity.addRawLabel(str);
        return filterResultsEntity;
    }

    private void clearIME(Context context, TextView textView) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
        }
        textView.clearFocus();
    }

    private boolean fromRecentAlbum(String str) {
        boolean z;
        if (this.mFromRecentAlbum == null) {
            String argValue = ArgumentsUtil.getArgValue(str, "sub");
            if (TextUtils.isEmpty(argValue) || !BucketUtils.isRecent(UnsafeCast.toInt(argValue))) {
                z = false;
            } else {
                z = true;
            }
            this.mFromRecentAlbum = Boolean.valueOf(z);
        }
        return this.mFromRecentAlbum.booleanValue();
    }

    private String getBubbleKeyword(String str) {
        String argValue = ArgumentsUtil.getArgValue(str, "fuzzyKeyword");
        if (TextUtils.isEmpty(argValue)) {
            return this.mSearchedKeyword;
        }
        return argValue;
    }

    private String getLocationKeyUsingQuery(String str, String str2) {
        UriBuilder uriBuilder;
        String removeEntity = removeEntity(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), str);
        if (ArgumentsUtil.getArgValue(removeEntity, "from_instant_search", false)) {
            removeEntity = ArgumentsUtil.removeArg(removeEntity, "fuzzyKeyword");
        }
        if (str2.isEmpty()) {
            return new UriBuilder(removeEntity).build();
        }
        if (fromRecentAlbum(removeEntity)) {
            this.mPendingRemoveFirstBubble = true;
            uriBuilder = replaceTargetFromRecentAlbum(clearExpansionInfo(removeEntity), str2);
        } else {
            uriBuilder = new UriBuilder(MultipleFilterKey.build(clearExpansionInfo(removeEntity), createKeywordEntity(str2)));
        }
        uriBuilder.appendArg("from_instant_search", true);
        return uriBuilder.build();
    }

    private boolean handleOnlyThem(FilterResultsEntity filterResultsEntity) {
        if (!filterResultsEntity.isOnlyThem()) {
            return false;
        }
        String clearExpansionInfo = clearExpansionInfo(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard));
        if (hasFuzzyKeyword(clearExpansionInfo)) {
            reopenData(changeFilterUsingFuzzyKeyword((FilterResultsEntity) null, clearExpansionInfo), filterResultsEntity);
            return true;
        }
        reopenData(clearExpansionInfo, filterResultsEntity);
        return true;
    }

    private boolean hasFuzzyKeyword(String str) {
        return !TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, "fuzzyKeyword"));
    }

    private boolean hasFuzzyKeywordInSelectedFilter(String str) {
        if (ArgumentsUtil.getArgValue(str, "SelectedFilter") == null || ArgumentsUtil.getArgValue(str, "fuzzyKeyword") == null) {
            return false;
        }
        return true;
    }

    private boolean isKeyword(FilterResultsEntity filterResultsEntity) {
        return TextUtils.equals("key_word", filterResultsEntity.getCategory());
    }

    private boolean isKeywordAlreadySearched() {
        if (this.mSearchedKeyword != null) {
            return true;
        }
        return false;
    }

    private boolean isMainKeywordFuzzy(String str) {
        if (ArgumentsUtil.getArgValue(str, "SelectedFilter") != null || !hasFuzzyKeyword(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onBindViewHolder$0(SearchSelectedFiltersEditableViewHolder searchSelectedFiltersEditableViewHolder, TextView textView, int i2, KeyEvent keyEvent) {
        if (i2 != 3) {
            return false;
        }
        actionSearch(searchSelectedFiltersEditableViewHolder, textView);
        return true;
    }

    /* access modifiers changed from: private */
    public void loadAutoComplete(String str) {
        if (this.mCurrentKeyword.isEmpty()) {
            this.mAutoCompleteDataChangeListener.accept(new ArrayList(), "");
        } else {
            this.mAutoCompleteDataLoader.load(str, this.mCurrentKeyword, true, this.mAutoCompleteDataChangeListener);
        }
    }

    private String removeEntity(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return MultipleFilterKey.removeEntity(str, createKeywordEntity(str2));
    }

    private String removeInstantSearchInfo(String str) {
        if (this.mInstantSearchHandler == null) {
            return str;
        }
        UriBuilder removeArg = new UriBuilder(str).removeArg("from_instant_search").removeArg("search_skip_save_history");
        if (ArgumentsUtil.getArgValue(str, "from_instant_search", false)) {
            removeArg.removeArg("fuzzyKeyword");
        }
        return removeArg.build();
    }

    private UriBuilder replaceTargetFromRecentAlbum(String str, String str2) {
        return new UriBuilder(str).appendArg("sub", str2).appendArg("title", str2).appendArg("term", "key_word").appendArg("collect_keyword", str2);
    }

    private String updateLocationKeyUsingFuzzyKeyword(String str, String str2) {
        return ArgumentsUtil.removeArg(ArgumentsUtil.appendArgs(str, "sub", str2), "fuzzyKeyword");
    }

    public void addData(FilterResultsEntity filterResultsEntity) {
        InstantSearchHandler instantSearchHandler;
        if (handleOnlyThem(filterResultsEntity)) {
            Log.d("FilterEditableAdapter", "addData : no need to add filter for only them");
            return;
        }
        if (this.mPendingRemoveFirstBubble) {
            this.mData.remove(0);
            notifyItemRemoved(0);
            if (!isKeyword(filterResultsEntity) && (instantSearchHandler = this.mInstantSearchHandler) != null) {
                String lastQuery = instantSearchHandler.getLastQuery();
                if (!TextUtils.isEmpty(lastQuery)) {
                    this.mData.add(0, createKeywordEntity(lastQuery));
                    notifyItemInserted(0);
                }
            }
            this.mPendingRemoveFirstBubble = false;
        }
        this.mIsDataAdded = true;
        String removeInstantSearchInfo = removeInstantSearchInfo(clearExpansionInfo(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard)));
        if (isKeywordAlreadySearched()) {
            addDataWithCheckingFuzzyKeyword(filterResultsEntity, removeInstantSearchInfo);
            this.mSearchedKeyword = null;
        } else if (isMainKeywordFuzzy(removeInstantSearchInfo)) {
            String bubbleKeyword = getBubbleKeyword(removeInstantSearchInfo);
            this.mData.add(filterResultsEntity);
            notifyItemRangeInserted(getItemCount() - 1, 1);
            reopenData(updateLocationKeyUsingFuzzyKeyword(removeInstantSearchInfo, bubbleKeyword), filterResultsEntity);
        } else {
            addDataWithCheckingFuzzyKeyword(filterResultsEntity, removeInstantSearchInfo);
        }
    }

    public String createReopenKey(String str, FilterResultsEntity filterResultsEntity) {
        if (fromRecentAlbum(str) && isKeyword(filterResultsEntity)) {
            return replaceTargetFromRecentAlbum(str, filterResultsEntity.getName()).build();
        }
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler == null) {
            return super.createReopenKey(str, filterResultsEntity);
        }
        String lastQuery = instantSearchHandler.getLastQuery();
        this.mInstantSearchHandler.reset();
        if (TextUtils.equals(filterResultsEntity.getName(), lastQuery) && isKeyword(filterResultsEntity)) {
            return removeInstantSearchInfo(str);
        }
        if (TextUtils.isEmpty(lastQuery)) {
            return super.createReopenKey(str, filterResultsEntity);
        }
        String createReopenKey = super.createReopenKey(str, filterResultsEntity);
        this.mFromRecentAlbum = Boolean.FALSE;
        return removeInstantSearchInfo(removeEntity(createReopenKey, lastQuery));
    }

    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public int getItemViewType(int i2) {
        if (i2 == this.mData.size()) {
            return 1;
        }
        return 0;
    }

    public void handleInstantSearch(String str, String str2) {
        this.mBlackboard.postEvent(EventMessage.obtain(1066, getLocationKeyUsingQuery(str, str2)));
    }

    public boolean isSubCategoryFixed() {
        if (this.mScopedSearchEnabled) {
            return true;
        }
        if (this.mIsDataAdded || this.mSubCategoryRemovable) {
            return false;
        }
        return true;
    }

    public void notifyDataPrepared() {
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler != null) {
            instantSearchHandler.searchPendingKeyword(this.mCurrentKeyword);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder.getItemViewType() == 1) {
            SearchSelectedFiltersEditableViewHolder searchSelectedFiltersEditableViewHolder = (SearchSelectedFiltersEditableViewHolder) viewHolder;
            searchSelectedFiltersEditableViewHolder.mEditText.setTag("SEARCH_AUTO_COMPLETE_VIEW_POSITIONING_ANCHOR");
            searchSelectedFiltersEditableViewHolder.mEditText.setOnEditorActionListener(new a(this, searchSelectedFiltersEditableViewHolder));
            searchSelectedFiltersEditableViewHolder.mEditText.addTextChangedListener(this.mWatcher);
            ThreadUtil.postOnUiThreadDelayed(new b(15, searchSelectedFiltersEditableViewHolder), 100);
            return;
        }
        super.onBindViewHolder(viewHolder, i2);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == 1) {
            SearchSelectedFiltersEditableViewHolder searchSelectedFiltersEditableViewHolder = (SearchSelectedFiltersEditableViewHolder) viewHolder;
            searchSelectedFiltersEditableViewHolder.mEditText.setText((CharSequence) null);
            searchSelectedFiltersEditableViewHolder.mEditText.setOnEditorActionListener((TextView.OnEditorActionListener) null);
            searchSelectedFiltersEditableViewHolder.mEditText.removeTextChangedListener(this.mWatcher);
            searchSelectedFiltersEditableViewHolder.mEditText.setTag((Object) null);
        }
        super.onViewRecycled(viewHolder);
    }

    public void postReopenEvent(Blackboard blackboard, int i2, String str) {
        if (getFilterCount() == i2) {
            str = ArgumentsUtil.removeArg(str, "fuzzyKeyword");
        }
        if (Boolean.parseBoolean(ArgumentsUtil.getArgValue(str, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE))) {
            blackboard.postEvent(EventMessage.obtain(8014, str));
        }
        super.postReopenEvent(this.mBlackboard, i2, str);
    }

    public void replaceLastData(FilterResultsEntity filterResultsEntity) {
        String str;
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        this.mData.remove(this.mData.size() - 1);
        this.mData.add(filterResultsEntity);
        notifyDataSetChanged();
        String removeLastKeyword = SearchFilterUtil.removeLastKeyword(ArgumentsUtil.getArgValue(readLocationKeyCurrent, "SelectedFilter", (String) null));
        if (TextUtils.isEmpty(removeLastKeyword)) {
            str = ArgumentsUtil.removeArg(readLocationKeyCurrent, "SelectedFilter");
        } else {
            str = ArgumentsUtil.appendArgs(readLocationKeyCurrent, "SelectedFilter", removeLastKeyword);
        }
        reopenData(clearExpansionInfo(str), filterResultsEntity);
    }

    public void replaceLastDataToFuzzyKeyword(FilterResultsEntity filterResultsEntity) {
        this.mData.remove(this.mData.size() - 1);
        this.mData.add(filterResultsEntity);
        notifyDataSetChanged();
    }

    public void searchKeyword(String str) {
        if (this.mBlackboard != null) {
            this.mSearchedKeyword = str;
            this.mDismissAutoCompleteView.run();
            this.mBlackboard.postEvent(EventMessage.obtain(8016, createKeywordEntity(str)));
        }
    }

    public void setAutoCompleteDataListener(BiConsumer<ArrayList<AutoCompleteItem>, String> biConsumer) {
        this.mAutoCompleteDataChangeListener = biConsumer;
    }

    public void setAutoCompleteViewListener(Runnable runnable) {
        this.mDismissAutoCompleteView = runnable;
    }
}
