package com.samsung.android.gallery.settings.ui;

import A4.O;
import Ab.a;
import Fa.F;
import Fa.L;
import Fa.M;
import Fa.N;
import O3.l;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.settings.MiscSettingPreference;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$style;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSettingFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private void initCustomizePreference() {
        MiscSettingPreference miscSettingPreference = MiscSettingPreference.Customize;
        if (miscSettingPreference.support(getContext())) {
            Optional.ofNullable(findPreference(miscSettingPreference.key)).ifPresent(new L(this, 2));
        } else {
            removePreference(miscSettingPreference.key);
        }
    }

    /* access modifiers changed from: private */
    public void initRecentOption() {
        MiscSettingPreference miscSettingPreference = MiscSettingPreference.SearchSaveRecent;
        Optional.ofNullable((SwitchPreferenceCompat) findPreference(miscSettingPreference.key)).ifPresent(new M(this, miscSettingPreference, 1));
    }

    /* access modifiers changed from: private */
    public void initSuggestionOption() {
        MiscSettingPreference miscSettingPreference = MiscSettingPreference.SearchShowSuggestions;
        Optional.ofNullable((SwitchPreferenceCompat) findPreference(miscSettingPreference.key)).ifPresent(new M(this, miscSettingPreference, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCustomizePreference$8(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startSearchCustom(preference.getContext(), AnalyticsEventId.EVENT_SEARCH_SETTING_CATEGORY_CUSTOMIZE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initCustomizePreference$9(Object obj) {
        ((Preference) obj).setOnPreferenceClickListener(new O(28, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initRecentOption$4(MiscSettingPreference miscSettingPreference, Preference preference, Object obj) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_SETTING_SHOW_RECENT_SEARCH.toString());
        Boolean bool = (Boolean) obj;
        if (!bool.booleanValue()) {
            SearchHistory.getInstance().deleteAllHistory();
            Blackboard.postEventGlobal(EventMessage.obtain(8515));
        }
        return miscSettingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initRecentOption$5(MiscSettingPreference miscSettingPreference, SwitchPreferenceCompat switchPreferenceCompat) {
        switchPreferenceCompat.setChecked(miscSettingPreference.isEnabled());
        switchPreferenceCompat.setOnPreferenceChangeListener(new N(this, miscSettingPreference, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initSuggestionOption$6(MiscSettingPreference miscSettingPreference, Preference preference, Object obj) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_SETTING_SHOW_SUGGESTION.toString());
        Blackboard.postEventGlobal(EventMessage.obtain(8525));
        return miscSettingPreference.setAndNotifyIfChanged(preference.getContext(), ((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSuggestionOption$7(MiscSettingPreference miscSettingPreference, SwitchPreferenceCompat switchPreferenceCompat) {
        switchPreferenceCompat.setChecked(miscSettingPreference.isEnabled());
        switchPreferenceCompat.setOnPreferenceChangeListener(new N(this, miscSettingPreference, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        BlackboardUtils.publishBackKeyEvent(getBlackboard());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(GalleryToolbar galleryToolbar) {
        galleryToolbar.setTitle(getTitleId());
        galleryToolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_with_inset);
        galleryToolbar.setNavigationContentDescription(R$string.navigate_up);
        galleryToolbar.setNavigationOnClickListener(new a(22, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setTitle(getTitleId());
        galleryAppBarLayout.setSubtitle((CharSequence) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateMainLayoutBackgroundColor$3(View view) {
        View rootView = view.getRootView();
        if (rootView != null) {
            rootView.setBackgroundColor(rootView.getResources().getColor(R$color.default_fw_background, (Resources.Theme) null));
        }
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.search_settings);
            initRecentOption();
            initSuggestionOption();
            initCustomizePreference();
        } catch (Exception e) {
            String str = this.TAG;
            Log.se(str, "loadPreference failed. e=" + e.getMessage());
        }
    }

    public BasePresenter<IBasePreferenceView> createPresenter() {
        return new BasePresenter<IBasePreferenceView>(this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$setGlobalSubscriberList$0(Object obj, Bundle bundle) {
                SearchSettingFragment.this.initRecentOption();
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$setGlobalSubscriberList$1(Object obj, Bundle bundle) {
                SearchSettingFragment.this.initSuggestionOption();
            }

            public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                arrayList.add(new SubscriberInfo("global://search/setting/save/recent/changed", new C(this, 0)).setWorkingOnUI());
                arrayList.add(new SubscriberInfo("global://search/setting/show/suggestion/changed", new C(this, 1)).setWorkingOnUI());
            }
        };
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SETTINGS.toString();
    }

    public int getTitleId() {
        return R$string.search_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (viewGroup != null) {
            viewGroup.getContext().setTheme(R$style.SearchSettings);
        }
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setFitsSystemWindows(true);
        View findViewById = onCreateView.findViewById(R$id.appbar);
        if (findViewById instanceof ViewStub) {
            ViewStub viewStub = (ViewStub) findViewById;
            viewStub.setLayoutResource(R$layout.appbar_custom_setting_style_layout);
            viewStub.inflate();
        }
        return onCreateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Optional.ofNullable((GalleryToolbar) view.findViewById(R$id.toolbar)).ifPresent(new L(this, 0));
        Optional.ofNullable((GalleryAppBarLayout) view.findViewById(R$id.appbar)).ifPresent(new L(this, 1));
        updateMainLayoutBackgroundColor();
    }

    public void updateMainLayoutBackgroundColor() {
        super.updateMainLayoutBackgroundColor();
        Optional.ofNullable(getView()).ifPresent(new F(4));
    }
}
