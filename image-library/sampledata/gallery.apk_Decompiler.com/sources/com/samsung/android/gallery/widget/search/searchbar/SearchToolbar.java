package com.samsung.android.gallery.widget.search.searchbar;

import B2.C0000a;
import I5.e;
import J5.a;
import U5.c;
import U9.b;
import Vb.d;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.LengthFilter;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteView;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchToolbar extends SearchView {
    protected SearchToolbarBehavior mBehavior = new EmptyBehavior();
    protected Blackboard mBlackboard;
    protected ImageView mCloseButton;
    private boolean mEnabledUpButton;
    private SearchSelectedFiltersView mFilterView;
    private final Runnable mMinimizeImeRunnable = new a(this, 6);
    protected ImageView mMoreButton;
    private String mOldQueryText = "";
    private final View.OnFocusChangeListener mOnFocusChangeListener = new C0000a(4, this);
    private OptionMenuListener mOptionMenuListener;
    protected SearchToolbarOptions mOptions = SearchToolbarOptions.buildEmpty();
    private PopupMenu mPopupMenu;
    private final AtomicBoolean mRequestFocusOnResume = new AtomicBoolean();
    protected LinearLayout mSearchPlateLayout;
    protected EditText mSearchTextView;
    private final Runnable mShowImeRunnable = new a(this, 5);
    private int mSoftInputMode;
    private final SearchView.OnQueryTextListener mTextListener = new SearchView.OnQueryTextListener() {
        public boolean onQueryTextChange(String str) {
            SearchToolbar.this.onQueryTextChanged(str);
            return true;
        }

        public boolean onQueryTextSubmit(String str) {
            SearchToolbar.this.onSubmitQuery();
            return true;
        }
    };
    protected ImageView mVoiceButton;
    private VoiceSearchHandler mVoiceSearchHandler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OptionMenuListener {
        void createOptionsMenu(Menu menu, MenuInflater menuInflater);

        boolean onOptionsItemSelected(MenuItem menuItem);

        void prepareOptionsMenu(Menu menu);
    }

    public SearchToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAttr(context, attributeSet);
        initSearchView();
    }

    private void delegateRequestFocus() {
        if (isEditableFilterViewVisible()) {
            requestFilterViewFocus();
        } else {
            requestFocus();
        }
    }

    private void filterRemoved(FilterResultsEntity filterResultsEntity) {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView != null) {
            searchSelectedFiltersView.removeFilter(filterResultsEntity);
        }
    }

    private boolean handleDebugKey(String str) {
        if (str == null) {
            return false;
        }
        if (!str.startsWith("*#9900#debug") && !str.startsWith("*#0911#func")) {
            return false;
        }
        this.mBlackboard.post("debug://showDebugLog", str);
        return true;
    }

    private void hideCursor() {
        this.mSearchTextView.setCursorVisible(false);
    }

    private void hideIME() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            Log.d("SearchToolbar", "hide IME");
            getActivity().getWindow().setSoftInputMode(this.mSoftInputMode);
            clearFocus();
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    private void hideMenu() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    private void initBackButtonIfNeeded() {
        if (this.mEnabledUpButton) {
            ImageView seslGetUpButton = seslGetUpButton();
            seslGetUpButton.setVisibility(0);
            seslGetUpButton.setOnClickListener(new d(this, 3));
        }
    }

    private void initCloseView() {
        ImageView imageView = (ImageView) findViewById(R$id.search_close_btn);
        this.mCloseButton = imageView;
        imageView.setOnClickListener(new d(this, 4));
        this.mCloseButton.setImageDrawable(getContext().getDrawable(R$drawable.gallery_ic_search_cancel_circle));
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            this.mCloseButton.clearColorFilter();
        }
    }

    private void initSearchView() {
        Activity activity = getActivity();
        this.mBlackboard = Blackboard.getInstance(activity.toString());
        registerVoiceSearchHandler(activity);
        setOnQueryTextListener(this.mTextListener);
        updateSearchableInfo(activity);
        setSuggestionsAdapter((CursorAdapter) null);
        initLayout();
        initTextView();
        initVoiceView();
        initCloseView();
        initBackButtonIfNeeded();
        updateBackground(true);
    }

    private void initVoiceView() {
        ImageView imageView = (ImageView) findViewById(R$id.search_voice_btn);
        this.mVoiceButton = imageView;
        imageView.setOnClickListener(new d(this, 2));
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            this.mVoiceButton.clearColorFilter();
        }
    }

    private void invalidateOptionMenu() {
        PopupMenu popupMenu;
        OptionMenuListener optionMenuListener = this.mOptionMenuListener;
        if (optionMenuListener != null && (popupMenu = this.mPopupMenu) != null) {
            optionMenuListener.createOptionsMenu(popupMenu.getMenu(), this.mPopupMenu.getMenuInflater());
            this.mOptionMenuListener.prepareOptionsMenu(this.mPopupMenu.getMenu());
        }
    }

    private boolean isFilterViewEditable() {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView == null || !searchSelectedFiltersView.isEditable()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateSelectedFiltersView$2(View view) {
        onClickCloseButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initCloseView$7(View view) {
        onClickCloseButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initVoiceView$6(View view) {
        setImeVisibility(false);
        postDelayed(new a(this, 4), 500);
        this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_VOICE_BTN);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$11() {
        this.mSoftInputMode = getActivity().getWindow().getAttributes().softInputMode;
        getActivity().getWindow().setSoftInputMode(16);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            Log.d("SearchToolbar", "show IME");
            inputMethodManager.showSoftInput(this.mSearchTextView, 0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$12() {
        SeApiCompat.minimizeSoftInput(getContext().getApplicationContext(), getWindowToken(), 22);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$9(View view, boolean z) {
        Log.v("SearchToolbar", "SearchView onFocusChange : " + z);
        onFocusChanged(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuItemClickInternal$8(MenuItem menuItem) {
        OptionMenuListener optionMenuListener = this.mOptionMenuListener;
        if (optionMenuListener != null) {
            optionMenuListener.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceLastFilter$4(FilterResultsEntity filterResultsEntity, SearchSelectedFiltersView searchSelectedFiltersView) {
        if (filterResultsEntity != null) {
            searchSelectedFiltersView.replaceLastFilter(filterResultsEntity, getQuery().toString());
            switchToFilterView();
            return;
        }
        onClickCloseButton();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceLastFilterToFuzzyKeyword$5(FilterResultsEntity filterResultsEntity, SearchSelectedFiltersView searchSelectedFiltersView) {
        searchSelectedFiltersView.replaceLastFilterToFuzzyKeyword(filterResultsEntity);
        switchToFilterView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setImeVisibility$10() {
        this.mFilterView.showIme();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSearchableInfo$0(SearchableInfo searchableInfo) {
        setSearchableInfo(searchableInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateSearchableInfo$1(Activity activity, ThreadPool.JobContext jobContext) {
        SearchableInfo searchableInfo;
        SearchManager searchManager = (SearchManager) activity.getSystemService("search");
        if (searchManager != null) {
            searchableInfo = searchManager.getSearchableInfo(activity.getComponentName());
        } else {
            searchableInfo = null;
        }
        if (searchableInfo != null) {
            ThreadUtil.postOnUiThread(new c(7, this, searchableInfo));
        }
        return null;
    }

    private void minimizeIME() {
        post(this.mMinimizeImeRunnable);
    }

    private void onClickCloseButton() {
        clearFilterData();
        this.mBehavior.onClickCloseButton(this);
        setQuery("", false);
        ViewUtils.setVisibleOrGone(this.mFilterView, false);
        ViewUtils.setVisibleOrGone(this.mSearchTextView, true);
    }

    /* access modifiers changed from: private */
    public void onFirstDraw() {
        this.mBehavior.onFirstDraw(this);
    }

    /* access modifiers changed from: private */
    public boolean onMenuItemClickInternal(MenuItem menuItem) {
        postDelayed(new c(8, this, menuItem), 80);
        return true;
    }

    /* access modifiers changed from: private */
    public void onMoreButtonClickInternal(View view) {
        if (this.mPopupMenu != null) {
            this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_MENU_MORE_OPTIONS);
            OptionMenuListener optionMenuListener = this.mOptionMenuListener;
            if (optionMenuListener != null) {
                optionMenuListener.prepareOptionsMenu(this.mPopupMenu.getMenu());
            }
            SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
            if (searchSelectedFiltersView != null) {
                searchSelectedFiltersView.autoCompleteDismiss();
            }
            this.mPopupMenu.show();
        }
    }

    /* access modifiers changed from: private */
    public void onUpButtonClick(View view) {
        this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        this.mBlackboard.publish("data:///NaviUpPressed", Boolean.TRUE);
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
    }

    private void registerVoiceSearchHandler(Activity activity) {
        boolean seslSetSviEnabled = seslSetSviEnabled(SdkConfig.atLeast(SdkConfig.SEM.V));
        Log.i("SearchToolbar", "Support Samsung Voice Input [" + seslSetSviEnabled + "]");
        this.mVoiceSearchHandler = new VoiceSearchHandler(activity, seslSetSviEnabled ? 1 : 0);
    }

    private void removeImeCallbacks() {
        removeCallbacks(this.mShowImeRunnable);
        removeCallbacks(this.mMinimizeImeRunnable);
    }

    private void removeSelf() {
        ViewUtils.removeSelf(getView());
    }

    private void requestFilterViewFocus() {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView != null && !searchSelectedFiltersView.hasFocus()) {
            this.mFilterView.requestFocus();
        }
    }

    private void requestFocusToCloseButton() {
        this.mCloseButton.requestFocus();
    }

    private void requestFocusToUpButton() {
        if (this.mEnabledUpButton) {
            seslGetUpButton().requestFocus();
        }
    }

    private void setAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SearchToolbar);
            this.mEnabledUpButton = obtainStyledAttributes.getBoolean(R$styleable.SearchToolbar_enabledUpButton, isEnableUpButton());
            obtainStyledAttributes.recycle();
        }
    }

    private void setDisableDirectWriting() {
        this.mSearchTextView.setPrivateImeOptions("disableDirectWriting=true");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 33) {
            this.mSearchTextView.setAutoHandwritingEnabled(false);
        }
        if (i2 >= 34) {
            this.mSearchTextView.setHandwritingBoundsOffsets(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void setSuggestedHint(String str) {
        EditText editText = this.mSearchTextView;
        if (editText != null && !str.contentEquals(editText.getHint())) {
            startHintSwitchAnim(str);
        }
    }

    private void startHintSwitchAnim(final String str) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), 17432577);
        loadAnimation.setRepeatCount(1);
        loadAnimation.setRepeatMode(2);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
                SearchToolbar.this.mSearchTextView.setHint(str);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.mSearchTextView.startAnimation(loadAnimation);
    }

    /* access modifiers changed from: private */
    public void startVoiceRecognitionActivity() {
        if (this.mVoiceSearchHandler != null) {
            handleInternalEvent(SearchToolbarEvent.obtain(30));
            this.mVoiceSearchHandler.onVoiceRecognitionStarted();
            handleInternalEvent(SearchToolbarEvent.obtain(31));
        }
    }

    private void switchSearchTextTitle() {
        clearFilterData();
        ViewUtils.setVisibility(this.mFilterView, 8);
        ViewUtils.setVisibility(this.mSearchTextView, 0);
        setQuery(ArgumentsUtil.getArgValue((String) this.mBlackboard.read("location://variable/currentv1"), "title"), false);
    }

    private void switchToFilterView() {
        ViewUtils.setVisibleOrGone(this.mFilterView, true);
        ViewUtils.setVisibleOrGone(this.mSearchTextView, false);
    }

    private void updateSearchableInfo(Activity activity) {
        ThreadPool.getInstance().submit(new e(6, this, activity));
    }

    public void addViewBeforeInputField(ViewGroup viewGroup) {
        LinearLayout linearLayout = this.mSearchPlateLayout;
        linearLayout.addView(viewGroup, linearLayout.indexOfChild(this.mSearchTextView));
    }

    public void clear() {
        setQuery("", false);
        clearFocus();
        updateBackground(true);
    }

    public void clearDataExceptMainEntity() {
        Optional.ofNullable(this.mFilterView).ifPresent(new T3.e(25));
    }

    public void clearFilterData() {
        Optional.ofNullable(this.mFilterView).ifPresent(new T3.e(26));
    }

    public void clickVoiceButton() {
        ImageView imageView = this.mVoiceButton;
        if (imageView != null) {
            imageView.performClick();
        }
    }

    public void createPopupMenu() {
        createPopupMenu((View) getParent());
    }

    public void destroy() {
        this.mBehavior.onDestroy(this);
        setVisibility(8);
        setOptionMenuListener((OptionMenuListener) null);
        setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        clearFilterData();
        this.mBlackboard.pop("data:///NaviUpPressed");
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) | this.mBehavior.dispatchKeyEvent(keyEvent);
    }

    public boolean doNotHideIMEWhenSearch() {
        return this.mOptions.doNotHideIMEWhenSearch();
    }

    public boolean fromInstantSearch() {
        return false;
    }

    public Activity getActivity() {
        Context context = getContext();
        while (!(context instanceof Activity) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) context;
    }

    public Object getData(SearchToolbarDataKey searchToolbarDataKey) {
        return this.mBehavior.getData(this, searchToolbarDataKey);
    }

    public ArrayList<FilterResultsEntity> getFilterResult() {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView != null) {
            return searchSelectedFiltersView.getFilterResult();
        }
        return null;
    }

    public int getGradientAreaHeight() {
        return getHeight();
    }

    public EditText getSearchTextView() {
        return this.mSearchTextView;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        String str;
        int i2;
        int i7 = eventMessage.what;
        if (i7 == 8511) {
            filterRemoved((FilterResultsEntity) eventMessage.obj);
        } else if (i7 == 8512) {
            invalidateOptionMenu();
        } else if (i7 == 8517) {
            replaceLastFilter((FilterResultsEntity) eventMessage.obj);
        } else if (i7 != 8519) {
            if (i7 == 8531) {
                setQuery((String) eventMessage.obj, true);
            } else if (i7 != 8526) {
                if (i7 != 8527) {
                    switch (i7) {
                        case 8501:
                            switchSearchTextTitle();
                            break;
                        case 8502:
                            if (!Features.isEnabled(Features.IS_USA_DEVICE)) {
                                clearFocus();
                                break;
                            } else {
                                minimizeIME();
                                break;
                            }
                        case 8503:
                            handleVoiceInput((String) eventMessage.obj);
                            break;
                        case 8504:
                            requestFocusToUpButton();
                            break;
                        case 8505:
                            requestFocusToCloseButton();
                            break;
                        case 8506:
                            setImeVisibility(((Boolean) eventMessage.obj).booleanValue());
                            break;
                        case 8507:
                            hideMenu();
                            break;
                        default:
                            return this.mBehavior.handleEvent(this, eventMessage);
                    }
                } else {
                    replaceLastFilterToFuzzyKeyword((FilterResultsEntity) eventMessage.obj);
                }
            } else if (this.mBehavior.isViewOnResumed()) {
                boolean booleanValue = ((Boolean) eventMessage.obj).booleanValue();
                if (booleanValue) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                setVisibility(i2);
                setImeVisibility(booleanValue);
                if (!booleanValue) {
                    this.mBehavior.onSearchToolbarVisibilityChanged(false);
                } else {
                    setAutoGoToTopOffsetMove(true);
                }
            }
        } else if (this.mOptions.isEnableSuggestionHint()) {
            if (eventMessage.obj != null) {
                str = getContext().getString(R$string.search_hint_guide_text, new Object[]{eventMessage.obj});
            } else {
                str = getContext().getString(R$string.search);
            }
            setSuggestedHint(str);
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        int i2;
        int i7 = searchToolbarEvent.what;
        if (i7 == 2) {
            this.mBehavior.updateToolbar(this, (String) searchToolbarEvent.obj);
            return true;
        } else if (i7 == 4) {
            this.mBehavior.initMenu(this);
            return true;
        } else if (i7 == 6) {
            if (((Boolean) searchToolbarEvent.obj).booleanValue()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            setVisibility(i2);
            this.mBehavior.onSearchToolbarVisibilityChanged(((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        } else if (i7 == 14) {
            this.mBehavior.onFocusChanged(this, ((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        } else if (i7 == 23) {
            setGradientVisibility(((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        } else if (i7 == 32) {
            clearDataExceptMainEntity();
            return true;
        } else if (i7 == 9) {
            this.mBehavior.onHiddenChange(this, ((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        } else if (i7 != 10) {
            switch (i7) {
                case 27:
                    delegateRequestFocus();
                    return true;
                case 28:
                    onClickCloseButton();
                    return true;
                case 29:
                    clearFilterData();
                    return true;
                default:
                    return this.mBehavior.handleInternalEvent(this, searchToolbarEvent);
            }
        } else {
            setMoreButtonVisibility(((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        }
    }

    public void handleQueryOnBehavior(String str) {
        if (handleDebugKey(str)) {
            this.mSearchTextView.setText((CharSequence) null);
        } else {
            this.mBehavior.handleQuery(str, fromInstantSearch());
        }
    }

    public void handleVoiceInput(String str) {
        this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_VOICE_BTN);
        if (this.mOptions.useCustomQueryHandling()) {
            handleQueryOnBehavior(str);
            return;
        }
        this.mBehavior.onPreQuery(this);
        moveToQueryResultView(str);
    }

    public SearchSelectedFiltersView inflateSelectedFiltersEditableView(FilterAutoCompleteView filterAutoCompleteView) {
        SearchSelectedFiltersView createWithEditable = SearchSelectedFiltersView.createWithEditable(this.mBlackboard, getActivity(), filterAutoCompleteView, new b(3, this));
        this.mFilterView = createWithEditable;
        return createWithEditable;
    }

    public SearchSelectedFiltersView inflateSelectedFiltersView(Blackboard blackboard) {
        SearchSelectedFiltersView create = SearchSelectedFiltersView.create(blackboard, getContext(), new b(3, this));
        this.mFilterView = create;
        View view = create.getView();
        if (view != null) {
            view.setOnClickListener(new d(this, 0));
        }
        return this.mFilterView;
    }

    public void initLayout() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.search_plate);
        this.mSearchPlateLayout = linearLayout;
        ViewMarginUtils.setStartMargin(linearLayout, 0);
    }

    public void initMoreButton() {
        ImageView seslGetOverflowMenuButton = seslGetOverflowMenuButton();
        this.mMoreButton = seslGetOverflowMenuButton;
        seslGetOverflowMenuButton.setOnClickListener(new d(this, 1));
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            this.mMoreButton.clearColorFilter();
        }
    }

    public void initTextView() {
        EditText editText = (EditText) findViewById(R$id.search_src_text);
        this.mSearchTextView = editText;
        editText.setFilters(new InputFilter[]{new LengthFilter(getContext(), 100)});
        setOnQueryTextFocusChangeListener(this.mOnFocusChangeListener);
        Utils.setCustomTextCursor(this.mSearchTextView, R$drawable.search_view_cursor);
    }

    public boolean isEditableFilterViewVisible() {
        if (!isFilterViewEditable() || !ViewUtils.isVisible(this.mFilterView)) {
            return false;
        }
        return true;
    }

    public boolean isEnableUpButton() {
        return !PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    }

    public boolean isHintVisible() {
        if (!ViewUtils.isVisible(this.mSearchTextView) || !TextUtils.isEmpty(this.mSearchTextView.getText()) || TextUtils.isEmpty(this.mSearchTextView.getHint())) {
            return false;
        }
        return true;
    }

    public void moveToQueryResultView(String str) {
        if (!handleDebugKey(str)) {
            UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", str)).appendArg("sub", str).appendArg("title", str).appendArg("term", "key_word").appendArg("collect_keyword", str).appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString());
            if (PreferenceFeatures.OneUi8x.INSTANT_SEARCH) {
                appendArg.appendArg("from_instant_search", fromInstantSearch());
            }
            String build = appendArg.build();
            if (PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD && !PickerUtil.isPickerMode(this.mBlackboard)) {
                build = ArgumentsUtil.appendArgs(build, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
            this.mBlackboard.post("command://MoveURL", build);
        }
    }

    public void onAttached(SearchToolbarOptions searchToolbarOptions) {
        this.mOptions = searchToolbarOptions;
        this.mBehavior = searchToolbarOptions.getBehavior();
        WindowInsets rootWindowInsets = getRootWindowInsets();
        if (rootWindowInsets != null) {
            this.mBehavior.onApplyInsets(this, rootWindowInsets);
        }
        if (searchToolbarOptions.supportMoreButton()) {
            initMoreButton();
            if (searchToolbarOptions.isMoreOptionVisible()) {
                setMoreButtonVisibility(true);
            }
        }
        if (searchToolbarOptions.isDisableDirectWriting()) {
            setDisableDirectWriting();
        }
        if (searchToolbarOptions.getOptionMenuListener() != null) {
            setOptionMenuListener(searchToolbarOptions.getOptionMenuListener());
        }
        boolean isFocusable = searchToolbarOptions.isFocusable();
        setFocusable(isFocusable);
        setFocusableInTouchMode(isFocusable);
        if (searchToolbarOptions.isClearFocus()) {
            clearFocus();
        }
        if (searchToolbarOptions.isHideCursor()) {
            hideCursor();
        }
        this.mBehavior.onAttached(this, searchToolbarOptions);
        this.mRequestFocusOnResume.set(searchToolbarOptions.fromInstantSearch());
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SearchToolbar.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SearchToolbar.this.onFirstDraw();
            }
        });
    }

    public void onFocusChanged(boolean z) {
        if (z) {
            this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_FIELD);
        }
        this.mBehavior.onFocusChanged(this, z);
        updateBackground(!z);
    }

    public void onQueryTextChanged(String str) {
        if (!TextUtils.equals(str, this.mOldQueryText)) {
            this.mBehavior.onQueryTextChanged(this, str);
        }
        if (str != null) {
            this.mOldQueryText = str;
        }
    }

    public void onResume() {
        this.mBehavior.onResume(this);
        if (this.mRequestFocusOnResume.getAndSet(false)) {
            this.mSearchTextView.requestFocus();
            setImeVisibility(true);
        }
    }

    public void onSubmitQuery() {
        CharSequence query = getQuery();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            this.mBehavior.postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_KEYBOARD_SEARCH_BTN);
            FragmentVolatileStatus.resetVolatile();
            if (this.mOptions.useCustomQueryHandling()) {
                handleQueryOnBehavior(query.toString());
            } else {
                this.mBehavior.onPreQuery(this);
                moveToQueryResultView(query.toString());
            }
            if (!doNotHideIMEWhenSearch()) {
                setImeVisibility(false);
            }
        }
    }

    public void replace(SearchToolbar searchToolbar) {
        searchToolbar.removeSelf();
        setQuery(searchToolbar.getQuery(), false);
        setVisibility(searchToolbar.getVisibility());
        swapFilterResult(searchToolbar.getFilterResult());
        searchToolbar.destroy();
    }

    public void replaceLastFilter(FilterResultsEntity filterResultsEntity) {
        Optional.ofNullable(this.mFilterView).ifPresent(new Vb.c(this, filterResultsEntity, 1));
    }

    public void replaceLastFilterToFuzzyKeyword(FilterResultsEntity filterResultsEntity) {
        Optional.ofNullable(this.mFilterView).ifPresent(new Vb.c(this, filterResultsEntity, 0));
    }

    public void requestFilterViewVoiceRecognition(String str) {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView != null) {
            searchSelectedFiltersView.requestFilterViewVoiceRecognition(str);
        }
    }

    public void setAutoGoToTopOffsetMove(boolean z) {
        ViewGroup findParentViewById = ViewUtils.findParentViewById(this, com.samsung.android.gallery.widget.R$id.bottom_search_toolbar_floating_container);
        if (findParentViewById instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) findParentViewById;
            if (z) {
                floatingBottomLayout.f1894G = Boolean.TRUE;
                floatingBottomLayout.getFloatingScrollableManager$material_release().f = true;
                floatingBottomLayout.requestLayout();
                return;
            }
            floatingBottomLayout.f1894G = Boolean.FALSE;
            floatingBottomLayout.getFloatingScrollableManager$material_release().f = false;
        }
    }

    public void setBehavior(SearchToolbarBehavior searchToolbarBehavior) {
        this.mBehavior = searchToolbarBehavior;
    }

    public void setImeVisibility(boolean z) {
        SearchSelectedFiltersView searchSelectedFiltersView;
        removeImeCallbacks();
        if (((InputMethodManager) getContext().getSystemService("input_method")) == null) {
            Log.e("SearchToolbar", "can not handle ime visibility change");
        } else if (!z) {
            hideIME();
        } else if (!isEditableFilterViewVisible() || (searchSelectedFiltersView = this.mFilterView) == null) {
            EditText editText = this.mSearchTextView;
            if (editText != null) {
                editText.requestFocus();
            }
            postDelayed(this.mShowImeRunnable, 0);
        } else {
            searchSelectedFiltersView.post(new a(this, 7));
        }
    }

    public void setMoreButtonVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mMoreButton, z);
    }

    public void setOptionMenuListener(OptionMenuListener optionMenuListener) {
        this.mOptionMenuListener = optionMenuListener;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        super.setQuery(charSequence, z);
        if (charSequence != null) {
            try {
                EditText editText = this.mSearchTextView;
                editText.setSelection(editText.getSelectionStart());
                if (ViewUtils.isVisible(this.mFilterView) && !isFilterViewEditable()) {
                    ViewUtils.setVisibility(this.mCloseButton, 8);
                }
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                Log.e((CharSequence) "SearchToolbar", "setQuery failed", e);
            }
        }
    }

    public void swapFilterResult(ArrayList<FilterResultsEntity> arrayList) {
        Optional.ofNullable(this.mFilterView).ifPresent(new B8.d(arrayList, 18));
    }

    public void updateBackground(boolean z) {
        if (!Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
                z = false;
            }
            if (z) {
                setBackgroundResource(R$drawable.search_toolbar_background);
            } else {
                setBackground((Drawable) null);
            }
        }
    }

    public void updateFilterView(FilterResultsEntity filterResultsEntity, String str) {
        SearchSelectedFiltersView searchSelectedFiltersView = this.mFilterView;
        if (searchSelectedFiltersView == null) {
            return;
        }
        if (filterResultsEntity != null) {
            searchSelectedFiltersView.updateFilter(filterResultsEntity, str);
            switchToFilterView();
            return;
        }
        onClickCloseButton();
    }

    private void createPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), view, 8388613);
        this.mPopupMenu = popupMenu;
        popupMenu.setOnMenuItemClickListener(new U3.a(10, this));
        this.mPopupMenu.seslSetOffset(0, view.getHeight() / 5);
        OptionMenuListener optionMenuListener = this.mOptionMenuListener;
        if (optionMenuListener != null) {
            optionMenuListener.createOptionsMenu(this.mPopupMenu.getMenu(), this.mPopupMenu.getMenuInflater());
        }
    }

    public ViewGroup getView() {
        return this;
    }

    public void setGradientVisibility(boolean z) {
    }
}
