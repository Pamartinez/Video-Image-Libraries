package com.samsung.android.gallery.app.ui.list.search.toolbar;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.sec.android.gallery3d.R;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchToolbarDelegateFactory {
    private static final boolean SUPPORT_NEW_SEARCH_BAR = PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar);

    /* renamed from: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode[] r0 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode = r0
                com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r1 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.LEGACY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r1 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.LEGACY_PICK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r1 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.NEW_BOTTOM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r1 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.NEW_BOTTOM_PICK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?>, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter, com.samsung.android.gallery.support.blackboard.Subscriber] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate build(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r3) {
        /*
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r3.getBlackboard()
            boolean r0 = com.samsung.android.gallery.module.utils.PickerUtil.isPickerMode(r0)
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r0 = com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode.getMode(r0)
            java.lang.String r1 = r3.getLocationKey()
            boolean r1 = r0.isNotSupportedView(r1)
            if (r1 == 0) goto L_0x001b
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate r3 = buildEmpty()
            return r3
        L_0x001b:
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r0 = buildOptions(r3, r0)
            boolean r1 = SUPPORT_NEW_SEARCH_BAR
            if (r1 == 0) goto L_0x0029
            com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater r1 = new com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater
            r1.<init>()
            goto L_0x002e
        L_0x0029:
            com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySearchToolbarInflater r1 = new com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySearchToolbarInflater
            r1.<init>()
        L_0x002e:
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r3 = r3.getView()
            r2.<init>(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.build(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter):com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate");
    }

    public static SearchToolbarDelegate buildEmpty() {
        return new EmptySearchToolbarDelegate();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?>, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter, com.samsung.android.gallery.support.blackboard.Subscriber] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate buildForAlbumPictures(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r4) {
        /*
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            boolean r0 = com.samsung.android.gallery.module.foldable.FoldUtils.isFlipCoverScreen(r0)
            r1 = 1
            if (r0 == 0) goto L_0x0017
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r4.getBlackboard()
            boolean r0 = com.samsung.android.gallery.module.abstraction.LaunchIntent.isFlipCoverWidgetTheme(r0)
            if (r0 == 0) goto L_0x0017
            r0 = r1
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            boolean r2 = SUPPORT_NEW_SEARCH_BAR
            if (r2 == 0) goto L_0x0074
            com.samsung.android.gallery.support.utils.Features r2 = com.samsung.android.gallery.support.utils.Features.SUPPORT_SCS_ALBUM_PICTURES_SEARCH
            boolean r2 = com.samsung.android.gallery.support.utils.Features.isEnabled(r2)
            if (r2 == 0) goto L_0x0074
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r4.getBlackboard()
            boolean r2 = com.samsung.android.gallery.module.utils.PickerUtil.isPickerMode(r2)
            if (r2 != 0) goto L_0x0074
            if (r0 != 0) goto L_0x0074
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r0 = r4.getView()
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r0 = (com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView) r0
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = new com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder
            r2.<init>()
            r3 = 2131492911(0x7f0c002f, float:1.8609287E38)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.setSearchLayout(r3)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.setClearFocus()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.useCustomQueryHandling()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r2.setFocusable(r1)
            java.lang.String r2 = "new_search_toolbar_containable"
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setContainerViewWithTag(r2)
            com.samsung.android.gallery.app.ui.list.search.toolbar.BottomAlbumPicturesBehavior r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.BottomAlbumPicturesBehavior
            r2.<init>(r4)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setBehavior(r2)
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r4 = r4.getView()
            android.view.View r0 = r0.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r0 = r1.build(r0)
            com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater r1 = new com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater
            r1.<init>()
            r2.<init>(r4, r0, r1)
            return r2
        L_0x0074:
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate r4 = buildEmpty()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.buildForAlbumPictures(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter):com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate buildForFloatingRecommendation(com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter<?> r3) {
        /*
            boolean r0 = SUPPORT_NEW_SEARCH_BAR
            if (r0 != 0) goto L_0x0009
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate r3 = buildEmpty()
            return r3
        L_0x0009:
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r0 = r3.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = new com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder
            r1.<init>()
            r2 = 1
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setFocusable(r2)
            r2 = 2131492911(0x7f0c002f, float:1.8609287E38)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setSearchLayout(r2)
            java.lang.String r2 = "new_search_toolbar_containable"
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setContainerViewWithTag(r2)
            com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationBehavior r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationBehavior
            r2.<init>(r0)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r1 = r1.setBehavior(r2)
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r3 = r3.getView()
            android.view.View r0 = r0.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r0 = r1.build(r0)
            com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater r1 = new com.samsung.android.gallery.app.ui.list.search.toolbar.NewSearchToolbarInflater
            r1.<init>()
            r2.<init>(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.buildForFloatingRecommendation(com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter):com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate");
    }

    public static SearchToolbarDelegate buildForStory(IMvpBaseView iMvpBaseView, Supplier<BaseSearchToolbarBehavior> supplier) {
        if (!PickerUtil.isPickerMode(iMvpBaseView.getBlackboard())) {
            return new SearchToolbarDelegateImpl(iMvpBaseView, new SearchToolbarOptions.Builder().setSearchLayout(R.layout.bottom_story_search_toolbar).setClearFocus().useCustomQueryHandling().doNotHideIMEWhenSearch().setFocusable(true).setContainerViewWithTag("new_search_toolbar_containable").setBehavior(supplier.get()).disableInstantSearch().build(iMvpBaseView.getView()), new NewSearchToolbarInflater());
        }
        return buildEmpty();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter<?>, com.samsung.android.gallery.support.blackboard.Subscriber] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate buildForSuggestionContainer(com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter<?> r4) {
        /*
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r4.getBlackboard()
            boolean r0 = com.samsung.android.gallery.module.utils.PickerUtil.isPickerMode(r0)
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r1 = r4.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = new com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder
            r2.<init>()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.setEnableSuggestionHint()
            r3 = 1
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.setFocusable(r3)
            com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySuggestionContainerBehavior r3 = new com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySuggestionContainerBehavior
            r3.<init>(r1, r0)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions$Builder r2 = r2.setBehavior(r3)
            if (r0 == 0) goto L_0x002a
            java.lang.String r0 = "legacy_search_toolbar_containable"
            r2.setContainerViewWithTag(r0)
        L_0x002a:
            com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl r0 = new com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateImpl
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r4 = r4.getView()
            android.view.View r1 = r1.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r1 = r2.build(r1)
            com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySearchToolbarInflater r2 = new com.samsung.android.gallery.app.ui.list.search.toolbar.LegacySearchToolbarInflater
            r2.<init>()
            r0.<init>(r4, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.buildForSuggestionContainer(com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter):com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?>, java.lang.Object, com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions buildOptions(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter<?> r2, com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode r3) {
        /*
            java.lang.String r0 = r2.getLocationKey()
            if (r0 != 0) goto L_0x001e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "buildOptions fail. locationKey is null. "
            r3.<init>(r0)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = "SearchToolbarDelegateFactory"
            com.samsung.android.gallery.support.utils.Log.e(r3, r2)
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions.buildEmpty()
            return r2
        L_0x001e:
            java.lang.String r0 = com.samsung.android.gallery.support.utils.ArgumentsUtil.removeArgs(r0)
            java.lang.String r1 = "location://search"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x002f
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = buildOptionsForSearch(r2, r3)
            return r2
        L_0x002f:
            boolean r1 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isSearchCategory(r0)
            if (r1 == 0) goto L_0x003a
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = buildOptionsForSearchCategory(r2, r3)
            return r2
        L_0x003a:
            boolean r0 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isSearchPictures(r0)
            if (r0 == 0) goto L_0x0045
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = buildOptionsForSearchPictures(r2, r3)
            return r2
        L_0x0045:
            boolean r3 = r3.isPickMode()
            if (r3 == 0) goto L_0x0054
            com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView r2 = r2.getView()
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = buildOptionsForPicker(r2)
            return r2
        L_0x0054:
            com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions r2 = com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions.buildEmpty()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory.buildOptions(com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter, com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarMode):com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions");
    }

    private static SearchToolbarOptions buildOptionsForPicker(IMvpBaseView iMvpBaseView) {
        return new SearchToolbarOptions.Builder().setSearchLayout(R.layout.search_toolbar_in_picker).setContainerViewWithTag("legacy_search_toolbar_containable").setClearFocus().build(iMvpBaseView.getView());
    }

    private static SearchToolbarOptions buildOptionsForSearch(BaseListPresenter<?> baseListPresenter, SearchToolbarMode searchToolbarMode) {
        boolean z;
        IBaseListView iBaseListView = (IBaseListView) baseListPresenter.getView();
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || !searchToolbarMode.isPickMode()) {
            z = false;
        } else {
            z = true;
        }
        SearchToolbarOptions.Builder customBlurTargetView = new SearchToolbarOptions.Builder().setClearFocus().setCustomBlurTargetView(iBaseListView.getListView());
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode[searchToolbarMode.ordinal()];
        if (i2 == 1) {
            customBlurTargetView.setEnableSuggestionHint().setOptionMenuListener(baseListPresenter).setDisableDirectWriting().setBehavior(new LegacySearchBehavior(iBaseListView, z));
        } else if (i2 == 2) {
            customBlurTargetView.setBehavior(new LegacySearchBehavior(iBaseListView, z)).setDisableDirectWriting().setContainerViewWithTag("legacy_search_toolbar_containable");
        } else if (i2 == 3 || i2 == 4) {
            customBlurTargetView.setSearchLayout(R.layout.bottom_search_toolbar).setFocusable(true).setContainerViewWithTag("new_search_toolbar_containable").disableMoreButton().setBehavior(new BottomSearchBehavior(iBaseListView, z));
        }
        return customBlurTargetView.build(iBaseListView.getView());
    }

    private static SearchToolbarOptions buildOptionsForSearchCategory(BaseListPresenter<?> baseListPresenter, SearchToolbarMode searchToolbarMode) {
        boolean z;
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_PICKER_SEARCH || !searchToolbarMode.isPickMode()) {
            z = false;
        } else {
            z = true;
        }
        IBaseListView iBaseListView = (IBaseListView) baseListPresenter.getView();
        SearchToolbarOptions.Builder clearFocus = new SearchToolbarOptions.Builder().setClearFocus();
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode[searchToolbarMode.ordinal()];
        if (i2 == 1) {
            clearFocus.setOptionMenuListener(baseListPresenter).setDisableDirectWriting().setBehavior(new LegacyCategoryBehavior(iBaseListView, z));
            if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE && iBaseListView.isEnableOptionsMenu()) {
                clearFocus.setMoreOptionVisible();
            }
        } else if (i2 == 2) {
            clearFocus.setSearchLayout(R.layout.search_toolbar_picker).setDisableDirectWriting().setContainerViewWithTag("legacy_search_toolbar_containable").setBehavior(new LegacyCategoryBehavior(iBaseListView, z));
        } else if (i2 == 3 || i2 == 4) {
            clearFocus.setSearchLayout(R.layout.bottom_search_toolbar).setContainerViewWithTag("new_search_toolbar_containable").disableMoreButton().setBehavior(new BottomCategoryBehavior(iBaseListView, z));
        }
        return clearFocus.build(iBaseListView.getView());
    }

    private static SearchToolbarOptions buildOptionsForSearchPictures(BaseListPresenter<?> baseListPresenter, SearchToolbarMode searchToolbarMode) {
        boolean isPickMode = searchToolbarMode.isPickMode();
        IBaseListView iBaseListView = (IBaseListView) baseListPresenter.getView();
        SearchToolbarOptions.Builder hideCursor = new SearchToolbarOptions.Builder().setHideCursor();
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            hideCursor.enableFiltersView();
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$search$toolbar$SearchToolbarMode[searchToolbarMode.ordinal()];
        if (i2 == 1) {
            hideCursor.setOptionMenuListener(baseListPresenter).setBehavior(new LegacySearchPicturesBehavior(iBaseListView, isPickMode));
        } else if (i2 == 2) {
            hideCursor.setSearchLayout(R.layout.search_toolbar_in_picker).setContainerViewWithTag("legacy_search_toolbar_containable").setBehavior(new LegacySearchPicturesBehavior(iBaseListView, isPickMode));
        } else if (i2 == 3 || i2 == 4) {
            hideCursor.setSearchLayout(R.layout.bottom_search_toolbar).setContainerViewWithTag("new_search_toolbar_containable").disableMoreButton().fromInstantSearch(ArgumentsUtil.getArgValue(baseListPresenter.getLocationKey(), "from_instant_search", false)).setBehavior(new BottomSearchPicturesBehavior(iBaseListView, isPickMode));
            if (LocationKey.isSearchKeyword(baseListPresenter.getLocationKey())) {
                hideCursor.useCustomQueryHandling().setShowCursor();
            }
            if (checkInflateWithGoneState(baseListPresenter.getLocationKey(), isPickMode)) {
                hideCursor.inflateWithGoneState();
            }
        }
        if (PocFeatures.ASK_SCREENSHOT && LocationKey.isAskScreenshot(baseListPresenter.getLocationKey())) {
            hideCursor.disableInstantSearch();
        }
        return hideCursor.build(iBaseListView.getView());
    }

    private static boolean checkInflateWithGoneState(String str, boolean z) {
        if (LocationKey.supportScopedSearch(str) && !ArgumentsUtil.getArgValue(str, "my_query", false)) {
            return true;
        }
        if (!z || !LocationKey.isSearchCategoryLocation(str)) {
            return false;
        }
        return true;
    }
}
