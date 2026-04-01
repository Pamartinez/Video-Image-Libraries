package com.samsung.android.gallery.settings.ui;

import A.a;
import Fa.C0551e;
import Fa.C0552f;
import O3.l;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.samsung.android.gallery.module.settings.DetailEnhancerOption;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$attr;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.helper.SettingLayoutUtils;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.widget.RadioPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailEnhancerFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private void initPreference() {
        setTwoStatePreference(SettingPreference.DetailEnhancerMaximum, new C0552f(this, 0));
        setTwoStatePreference(SettingPreference.DetailEnhancerMinimum, new C0552f(this, 1));
        setTwoStatePreference(SettingPreference.DetailEnhancerOff, new C0552f(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$2(Preference preference, Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return false;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_DETAIL_ENHANCER_OPTION.toString(), DetailEnhancerOption.MAXIMUM.getValue());
        return SettingPreference.DetailEnhancerMaximum.setAndNotifyIfChanged(preference.getContext(), true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$3(Preference preference, Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return false;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_DETAIL_ENHANCER_OPTION.toString(), DetailEnhancerOption.MINIMUM.getValue());
        return SettingPreference.DetailEnhancerMinimum.setAndNotifyIfChanged(preference.getContext(), true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$4(Preference preference, Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return false;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_DETAIL_ENHANCER_OPTION.toString(), DetailEnhancerOption.OFF.getValue());
        return SettingPreference.DetailEnhancerOff.setAndNotifyIfChanged(preference.getContext(), true);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateRadioPrefs$1(PreferenceScreen preferenceScreen, Preference preference) {
        for (int i2 = 0; i2 < preferenceScreen.getPreferenceCount(); i2++) {
            Preference preference2 = preferenceScreen.getPreference(i2);
            if (preference2 instanceof RadioPreference) {
                RadioPreference radioPreference = (RadioPreference) preference2;
                radioPreference.setChecked(TextUtils.equals(preference.getKey(), radioPreference.getKey()));
            }
        }
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.detail_enhancer_screen);
            initPreference();
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadPreference failed e="), this.TAG);
        }
    }

    private void updateDescriptionLayoutPadding(WindowInsets windowInsets) {
        if (getActivity() != null) {
            int preferencePadding = SettingLayoutUtils.getPreferencePadding(getActivity());
            Optional.ofNullable(getActivity().findViewById(R$id.description_layout)).ifPresent(new C0551e(WindowUtils.getSystemInsetsStart(windowInsets) + SettingLayoutUtils.getPaddingFromAttr(getActivity(), R$attr.listPreferredItemPaddingStart), preferencePadding, WindowUtils.getSystemInsetsEnd(windowInsets) + SettingLayoutUtils.getPaddingFromAttr(getActivity(), R$attr.listPreferredItemPaddingEnd)));
        }
    }

    /* access modifiers changed from: private */
    public void updateRadioPrefs(DetailEnhancerOption detailEnhancerOption) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            Optional.ofNullable(findPreference(detailEnhancerOption.getKey())).ifPresent(new E9.a(6, preferenceScreen));
        }
    }

    public BasePresenter<IBasePreferenceView> createPresenter() {
        return new BasePresenter<IBasePreferenceView>(this) {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$setGlobalSubscriberList$0(Object obj, Bundle bundle) {
                DetailEnhancerFragment.this.updateRadioPrefs((DetailEnhancerOption) obj);
            }

            public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                arrayList.add(new SubscriberInfo("global://setting/detailEnhancer", new C0646a(0, this)).setWorkingOnUI());
            }
        };
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_DETAIL_ENHANCER.toString();
    }

    public int getTitleId() {
        return R$string.detail_enhancer;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        updateDescriptionLayoutPadding(windowInsets);
        return super.onApplyWindowInsets(view, windowInsets);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        updateDescriptionLayoutPadding((WindowInsets) null);
        super.onCreatePreferences(bundle, str);
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        String str = this.TAG;
        Log.d(str, "onHandleEvent " + eventMessage);
        if (eventMessage.what != 7005) {
            return false;
        }
        loadPreference();
        return true;
    }
}
