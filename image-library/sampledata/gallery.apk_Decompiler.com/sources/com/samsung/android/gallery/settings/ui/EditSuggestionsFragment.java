package com.samsung.android.gallery.settings.ui;

import A4.O;
import Ba.h;
import Da.f;
import E9.a;
import O3.l;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import com.samsung.android.gallery.module.account.AwesomeIntelligenceServiceManager;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.settings.widget.HelpPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditSuggestionsFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private final boolean mFromSettings;

    public EditSuggestionsFragment(boolean z) {
        this.mFromSettings = z;
    }

    private void handleIntelligenceServiceConsentResult(int i2) {
        boolean z;
        if (i2 == -1) {
            AwesomeIntelligenceServiceManager.getInstance().setIntelligenceServiceShown(getContext());
            boolean hasAccount = SamsungAccountManager.getInstance().reload().hasAccount();
            if (!hasAccount || !SamsungAccountManager.isSamsungAiSupportAccount(getContext())) {
                z = false;
            } else {
                z = true;
            }
            Log.d(this.TAG, "handleIntelligenceServiceConsentResult success", Boolean.valueOf(hasAccount), Boolean.valueOf(z));
            if (!hasAccount) {
                new NavigateAppCmd((IBaseView) this).startSamsungAccountSignIn(getActivity());
            } else if (z) {
                new NavigateAppCmd((IBaseView) this).startMoreIntelligentFeatures(getContext());
            } else {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        } else {
            Log.w((CharSequence) this.TAG, "handleIntelligenceServiceConsentResult failed", Integer.valueOf(i2));
        }
    }

    private void handleSamsungAccountSignIn(int i2, Intent intent) {
        String str;
        if (i2 == -1) {
            boolean isSamsungAiSupportAccount = SamsungAccountManager.isSamsungAiSupportAccount(getContext());
            Log.d(this.TAG, "handleSamsungAccountSignIn success", Boolean.valueOf(isSamsungAiSupportAccount));
            if (isSamsungAiSupportAccount) {
                new NavigateAppCmd((IBaseView) this).startMoreIntelligentFeatures(getContext());
            } else {
                AwesomeIntelligenceServiceManager.getInstance().showRestrictedAccountToast(getContext());
            }
        } else {
            String str2 = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (intent != null) {
                str = intent.getStringExtra("error_message");
            } else {
                str = "";
            }
            Log.w((CharSequence) str2, "handleSamsungAccountSignIn failed", valueOf, str);
        }
    }

    private void initPreference(Context context) {
        Optional.ofNullable((HelpPreference) findPreference(SettingPreference.EditSuggestionsHelp.key)).ifPresent(new a(7, this));
        if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || !this.mFromSettings) {
            computePreference(context, SettingPreference.MoreIntelligentFeatures, new h(4, this));
        } else {
            removePreference(SettingPreference.MoreIntelligentFeatures.key);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$0(HelpPreference helpPreference) {
        new NavigateAppCmd((IBaseView) this).startTermsAndConditions(helpPreference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$1(HelpPreference helpPreference) {
        helpPreference.setHelpImage(helpPreference.getContext().getDrawable(R$drawable.setting_help_edit_suggestion));
        helpPreference.setHelpDescription(helpPreference.getContext().getString(R$string.edit_suggestions_help_description));
        helpPreference.setLinkDescription(helpPreference.getContext().getString(R$string.edit_suggestions_link_description));
        helpPreference.setLinkClickListener(new f(15, this, helpPreference));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$2(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startMoreIntelligentFeatures(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$3(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new O(25, this));
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.edit_suggestions_screen);
            initPreference(getContext());
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("loadPreference failed e="), this.TAG);
        }
    }

    private void onActivityResult(EventMessage eventMessage) {
        int i2 = eventMessage.arg1;
        int i7 = eventMessage.arg2;
        Intent intent = (Intent) eventMessage.obj;
        if (i2 == 798) {
            handleIntelligenceServiceConsentResult(i7);
        } else if (i2 == 799) {
            handleSamsungAccountSignIn(i7, intent);
        }
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_EDIT_SUGGESTIONS.toString();
    }

    public int getTitleId() {
        return R$string.edit_suggestions;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public void onDestroy() {
        Optional.ofNullable((HelpPreference) findPreference(SettingPreference.EditSuggestionsHelp.key)).ifPresent(new Bb.l(26));
        super.onDestroy();
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        String str = this.TAG;
        Log.d(str, "onHandleEvent " + eventMessage);
        if (eventMessage.what != 7009) {
            return super.onHandleEvent(obj, bundle);
        }
        onActivityResult(eventMessage);
        return true;
    }
}
