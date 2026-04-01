package com.samsung.android.gallery.settings.ui;

import A.a;
import A4.A;
import A4.B;
import A4.C0382q;
import A4.J;
import A4.L;
import Bb.l;
import D3.i;
import Da.f;
import Fa.C0548b;
import Fa.C0549c;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.preference.TwoStatePreference;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.settings.SettingPrefApi;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$bool;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseActivity;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.widget.ChunkingBackgroundView;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasePreferenceFragment<V extends IBasePreferenceView> extends PreferenceFragmentCompat implements IBasePreferenceView {
    static final boolean FEATURES_FOR_LEGACY = (!SdkConfig.atLeast(SdkConfig.SEM.Q_MR1));
    protected final String TAG = getClass().getSimpleName();
    private ChunkingBackgroundView mBackgroundView;
    private Blackboard mBlackboard;
    protected final BasePresenter<V> mPresenter = createPresenter();
    private boolean mViewActive;

    private String getBlackboardTag() {
        try {
            return getActivity().toString();
        } catch (Exception unused) {
            Log.e(this.TAG, "fail to refer activity");
            return null;
        }
    }

    private CollapsingToolbarLayout getCollapsingToolbar() {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            return (CollapsingToolbarLayout) toolbar.getRootView().findViewById(R$id.toolbar_layout);
        }
        return null;
    }

    private int getFadingEdgeColor() {
        if (getActivity() == null || !getActivity().getResources().getBoolean(R$bool.isNightTheme)) {
            return R$color.sesl_round_and_bgcolor_light;
        }
        return R$color.sesl_round_and_bgcolor_dark;
    }

    private void initOverlayView(View view) {
        ChunkingBackgroundView chunkingBackgroundView = new ChunkingBackgroundView(view.getContext());
        this.mBackgroundView = chunkingBackgroundView;
        chunkingBackgroundView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mBackgroundView.setBackgroundColor(view.getResources().getColor(R$color.default_fw_background, (Resources.Theme) null));
        this.mBackgroundView.setOnApplyWindowInsetsListener(new C0548b(this, 1));
        ViewUtils.addView((ViewGroup) view, this.mBackgroundView);
    }

    private void initPreferenceList() {
        RecyclerView listView = getListView();
        if (listView != null) {
            listView.seslSetFadingEdgeEnabled(true);
            listView.seslSetFadingEdgeColor(getResources().getColor(getFadingEdgeColor(), (Resources.Theme) null));
            listView.setClipToPadding(false);
            updateToolbarFadingEdge(listView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsets lambda$initOverlayView$1(View view, WindowInsets windowInsets) {
        Optional.ofNullable(this.mBackgroundView).ifPresent(new C0382q(windowInsets, 1));
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onMultiWindowModeChanged$13(RecyclerView recyclerView) {
        ViewUtils.removeAllViewsInLayout(recyclerView);
        recyclerView.setAdapter(recyclerView.getAdapter());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$2(ActionBar actionBar) {
        int titleId = getTitleId();
        actionBar.setTitle(titleId);
        actionBar.setDisplayHomeAsUpEnabled(isShowHomeAsUp());
        actionBar.setDisplayShowHomeEnabled(false);
        updateToolbarTitle(titleId);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$removePreference$7(Preference preference) {
        PreferenceGroup parent = preference.getParent();
        if (parent != null) {
            parent.removePreference(preference);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPreferenceScreen$4(PreferenceManager preferenceManager) {
        PreferenceScreen preferenceScreen = preferenceManager.getPreferenceScreen();
        setPreferenceScreen((PreferenceScreen) null);
        setPreferenceScreen(preferenceScreen);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setTwoStatePreference$12(SettingPrefApi settingPrefApi, Preference.OnPreferenceChangeListener onPreferenceChangeListener, TwoStatePreference twoStatePreference) {
        twoStatePreference.setChecked(settingPrefApi.isEnabled());
        if (onPreferenceChangeListener != null) {
            twoStatePreference.setOnPreferenceChangeListener(onPreferenceChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showHighlightIfGuided$15(String str) {
        HighlightGroupAdapter highlightGroupAdapter;
        boolean z;
        boolean z3;
        RecyclerView listView = getListView();
        if (listView != null) {
            highlightGroupAdapter = (HighlightGroupAdapter) listView.getAdapter();
        } else {
            highlightGroupAdapter = null;
        }
        if (highlightGroupAdapter == null || findPreference(str) == null) {
            String str2 = this.TAG;
            boolean z7 = false;
            if (listView != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (findPreference(str) != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z3);
            if (highlightGroupAdapter != null) {
                z7 = true;
            }
            Log.e((CharSequence) str2, "showHighlightIfGuided skip. invalid view", str, valueOf, valueOf2, Boolean.valueOf(z7));
            return;
        }
        Log.d(this.TAG, "showHighlightIfGuided", str);
        highlightGroupAdapter.requestHighlight(listView, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMainLayoutBackgroundColor$3(View view) {
        RecyclerView listView = getListView();
        if (listView != null) {
            listView.setBackgroundColor(view.getContext().getColor(R$color.settings_preference_list_item_background));
        }
        view.setBackgroundColor(view.getContext().getColor(R$color.default_fw_background));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarTitle$5(int i2, CollapsingToolbarLayout collapsingToolbarLayout) {
        collapsingToolbarLayout.setTitle(getString(i2));
    }

    private void resetPreferenceScreen() {
        Optional.ofNullable(getPreferenceManager()).ifPresent(new C0549c(this, 2));
    }

    private void updatePreferencePadding(IBasePreferenceView iBasePreferenceView, WindowInsets windowInsets) {
        Activity activity = iBasePreferenceView.getActivity();
        if (activity != null && windowInsets != null) {
            int preferencePadding = SettingLayoutUtils.getPreferencePadding(activity);
            ((BasePreferenceFragment) iBasePreferenceView).setPadding(preferencePadding, 0, preferencePadding, 0);
        }
    }

    private void updateToolbarFadingEdge(RecyclerView recyclerView) {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            ViewParent parent = toolbar.getParent();
            if (parent instanceof FloatingToolbarLayout) {
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) parent;
                if (recyclerView != null) {
                    floatingToolbarLayout.setRecyclerView(recyclerView);
                }
            }
        }
    }

    public Preference addGenericPreference(String str, String str2, String str3, String str4, Preference.OnPreferenceClickListener onPreferenceClickListener) {
        return addGenericPreference((PreferenceGroup) getPreferenceScreen().findPreference(str), str2, str3, str4, onPreferenceClickListener);
    }

    public void adjustLayoutForGalleryActivity(View view, WindowInsets windowInsets) {
        if (!(getActivity() instanceof IBaseActivity) && view != null) {
            SettingLayoutUtils.adjustSettingToolbarLayout(view.findViewById(R$id.toolbar), (AppBarLayout) view.findViewById(R$id.appbar), windowInsets);
            ViewMarginUtils.setStartPadding(view, WindowUtils.getSystemInsetsStart(windowInsets));
            ViewMarginUtils.setEndPadding(view, WindowUtils.getSystemInsetsEnd(windowInsets));
        }
    }

    public boolean commitFragment(Fragment fragment) {
        return commitFragment(fragment, (Bundle) null);
    }

    public final <T extends Preference> T computePreference(Context context, SettingPreference settingPreference, BiConsumer<T, SettingPreference> biConsumer) {
        T findPreference = findPreference(settingPreference.key);
        if (findPreference == null) {
            return null;
        }
        if (settingPreference.support(context)) {
            biConsumer.accept(findPreference, settingPreference);
            return findPreference;
        }
        removePreference((Preference) findPreference);
        return null;
    }

    public final <T extends SwitchPreferenceCompat> T computeSwitchPreference(PreferenceGroup preferenceGroup, SettingPreference settingPreference, BiConsumer<T, SettingPreference> biConsumer) {
        T t = (SwitchPreferenceCompat) preferenceGroup.findPreference(settingPreference.key);
        if (t == null) {
            return null;
        }
        if (settingPreference.support(preferenceGroup.getContext())) {
            t.setChecked(settingPreference.isEnabled());
            biConsumer.accept(t, settingPreference);
            return t;
        }
        preferenceGroup.removePreference(t);
        return null;
    }

    public BasePresenter<V> createPresenter() {
        return new BasePresenter<>(this);
    }

    public <T extends Preference> T findPreference(String str) {
        return super.findPreference(str);
    }

    public ActionBar getActionBar() {
        return (ActionBar) Optional.ofNullable((AppCompatActivity) getActivity()).map(new i(20)).orElse((Object) null);
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return getActivity();
    }

    public final AppBarLayout getAppbarLayout() {
        View view;
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            view = (View) blackboard.read("data://settings/appbar", null);
        } else {
            view = null;
        }
        if (view instanceof AppBarLayout) {
            return (AppBarLayout) view;
        }
        return null;
    }

    public final Context getApplicationContext() {
        return (Context) Optional.ofNullable(getContext()).map(new i(21)).orElse((Object) null);
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public BasePresenter<V> getPresenter() {
        return this.mPresenter;
    }

    public String getScreenId() {
        return null;
    }

    public int getTitleId() {
        return R$string.gallery_settings;
    }

    public Toolbar getToolbar() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            return (Toolbar) blackboard.read("data://settings/toolbar");
        }
        return null;
    }

    public boolean isDestroyed() {
        return !this.mViewActive;
    }

    public boolean isShowHomeAsUp() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        updatePreferencePadding(this, windowInsets);
        adjustLayoutForGalleryActivity(view, windowInsets);
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        String blackboardTag = getBlackboardTag();
        if (blackboardTag != null) {
            this.mBlackboard = Blackboard.getInstance(blackboardTag);
        }
        this.mViewActive = true;
        this.mPresenter.onAttach(this.mBlackboard);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getPresenter().onConfigurationChanged(configuration);
        updateFragment();
        resetPreferenceScreen();
    }

    public void onCreate(Bundle bundle) {
        Log.l(this.TAG, "onCreate");
        super.onCreate(bundle);
        this.mPresenter.onCreate();
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        seslSetRoundedCorner(true);
    }

    public void onDestroy() {
        Log.d(this.TAG, "onDestroy");
        ViewUtils.removeSelf(this.mBackgroundView);
        this.mBackgroundView = null;
        this.mViewActive = false;
        this.mPresenter.onDestroy();
        super.onDestroy();
    }

    public void onDetach() {
        this.mPresenter.onDetach();
        super.onDetach();
    }

    public boolean onHandleBroadcastEvent(Object obj, Bundle bundle) {
        return false;
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        return false;
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        String str = this.TAG;
        Log.d(str, "onMultiWindowModeChanged " + z);
        Optional.ofNullable(getListView()).ifPresent(new l(24));
    }

    public void onResume() {
        Log.l(this.TAG, "onResume");
        super.onResume();
        Optional.ofNullable(getActionBar()).ifPresent(new C0549c(this, 1));
        updateToolbarFadingEdge(getListView());
        this.mPresenter.onResume();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            seslSetPredictiveBackEnabled(false, true);
            getBlackboard().post("command:///OnBackPressInvokableStateChanged", (Object) null);
        }
        initPreferenceList();
        initOverlayView(view.getRootView());
        view.setOnApplyWindowInsetsListener(new C0548b(this, 0));
    }

    public final void postAnalyticsLog(String str) {
        postAnalyticsLog(str, (String) null);
    }

    public void removePreference(String str) {
        Optional.ofNullable(findPreference(str)).ifPresent(new l(25));
    }

    public void removePreferenceIfEmpty(PreferenceGroup preferenceGroup) {
        if (preferenceGroup != null && preferenceGroup.getPreferenceCount() <= 0) {
            removePreference((Preference) preferenceGroup);
        }
    }

    public boolean setInputBlock(String str, int i2) {
        BasePresenter<V> basePresenter = this.mPresenter;
        if (basePresenter == null || !basePresenter.setInputBlock(str, i2)) {
            return false;
        }
        return true;
    }

    public void setPreferenceCategoryVisible(String str) {
        setPreferenceCategoryVisible((PreferenceGroup) findPreference(str));
    }

    public final void setPreferenceEnabled(String str, boolean z) {
        Optional.ofNullable(findPreference(str)).ifPresent(new L(z, 7));
    }

    public void setPreferenceScreenEnabled(boolean z) {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new L(z, 8));
    }

    public final void setPreferenceSummary(String str, String str2) {
        Optional.ofNullable(findPreference(str)).ifPresent(new B(str2, 3));
    }

    public final void setPreferenceVisible(String str, boolean z) {
        Optional.ofNullable(findPreference(str)).ifPresent(new L(z, 6));
    }

    public void setScreenMode() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            SystemUi.setNormalSystemBar(activity);
        }
    }

    public final void setSwitchPreferenceChecked(String str, boolean z) {
        Optional.ofNullable((SwitchPreferenceCompat) findPreference(str)).ifPresent(new L(z, 9));
    }

    public final void setTwoStatePreference(SettingPrefApi settingPrefApi, Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
        Optional.ofNullable((TwoStatePreference) findPreference(settingPrefApi.getKey())).ifPresent(new A(13, (Object) settingPrefApi, (Object) onPreferenceChangeListener));
    }

    public void showHighlightIfGuided(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("preference_key");
            if (!TextUtils.isEmpty(string)) {
                ThreadUtil.postOnUiThreadDelayed(new f(14, this, string), 600);
                bundle.remove("preference_key");
            }
        }
    }

    public void updateFragment() {
        try {
            getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commitNowAllowingStateLoss();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "updateFragment failed", (Throwable) e);
        }
    }

    public void updateMainLayoutBackgroundColor() {
        Optional.ofNullable(getView()).ifPresent(new C0549c(this, 0));
    }

    public final void updateToolbarTitle(int i2) {
        Optional.ofNullable(getCollapsingToolbar()).ifPresent(new J((Object) this, i2, 1));
    }

    public Preference addGenericPreference(PreferenceGroup preferenceGroup, String str, String str2, String str3, Preference.OnPreferenceClickListener onPreferenceClickListener) {
        if (preferenceGroup == null) {
            return null;
        }
        Preference preference = new Preference(preferenceGroup.getContext());
        if (str != null) {
            preference.setKey(str);
        }
        preference.setTitle((CharSequence) str2);
        preference.setSummary((CharSequence) str3);
        preference.setOnPreferenceClickListener(onPreferenceClickListener);
        preferenceGroup.addPreference(preference);
        return preference;
    }

    public boolean commitFragment(Fragment fragment, Bundle bundle) {
        String simpleName = fragment.getClass().getSimpleName();
        if (bundle != null) {
            try {
                fragment.setArguments(bundle);
            } catch (Exception e) {
                a.s(e, j.k("commitFragment failed with ", simpleName, ", e="), this.TAG);
                return true;
            }
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R$id.content, fragment, simpleName).addToBackStack(simpleName).commit();
        return true;
    }

    public final void postAnalyticsLog(String str, boolean z) {
        postAnalyticsLog(str, AnalyticsDetail.getOnOff(z));
    }

    public void removePreference(Preference preference) {
        PreferenceGroup parent;
        if (preference != null && (parent = preference.getParent()) != null) {
            parent.removePreference(preference);
        }
    }

    public final void setPreferenceCategoryVisible(PreferenceGroup preferenceGroup) {
        if (preferenceGroup != null) {
            int preferenceCount = preferenceGroup.getPreferenceCount();
            for (int i2 = 0; i2 < preferenceCount; i2++) {
                if (preferenceGroup.getPreference(i2).isVisible()) {
                    preferenceGroup.setVisible(true);
                    return;
                }
            }
            preferenceGroup.setVisible(false);
        }
    }

    public final void postAnalyticsLog(String str, String str2) {
        String screenId = getScreenId();
        if (screenId == null) {
            Log.d(this.TAG, "invalid screenId");
        } else if (str2 == null) {
            AnalyticsLogger.getInstance().postLog(screenId, str);
        } else {
            AnalyticsLogger.getInstance().postLog(screenId, str, str2);
        }
    }

    public final void setSwitchPreferenceChecked(SettingPrefApi settingPrefApi) {
        setTwoStatePreference(settingPrefApi, (Preference.OnPreferenceChangeListener) null);
    }

    public final <T extends Preference> T computePreference(PreferenceGroup preferenceGroup, SettingPreference settingPreference, BiConsumer<T, SettingPreference> biConsumer) {
        T findPreference = preferenceGroup.findPreference(settingPreference.key);
        if (findPreference == null) {
            return null;
        }
        if (settingPreference.support(preferenceGroup.getContext())) {
            biConsumer.accept(findPreference, settingPreference);
            return findPreference;
        }
        preferenceGroup.removePreference(findPreference);
        return null;
    }
}
