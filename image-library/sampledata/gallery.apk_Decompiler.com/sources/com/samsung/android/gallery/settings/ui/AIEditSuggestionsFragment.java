package com.samsung.android.gallery.settings.ui;

import A.a;
import Bb.l;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$drawable;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.widget.HelpPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AIEditSuggestionsFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private void initPreference(Context context) {
        Optional.ofNullable((HelpPreference) findPreference(SettingPreference.AIEditSuggestionsHelp.key)).ifPresent(new l(23));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initPreference$0(HelpPreference helpPreference) {
        helpPreference.setHelpImage(helpPreference.getContext().getDrawable(R$drawable.setting_help_ai_edit_suggestion));
        helpPreference.setHelpDescription(helpPreference.getContext().getString(R$string.ai_edit_suggestions_help_description));
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new O3.l(7));
        try {
            addPreferencesFromResource(R$xml.ai_edit_suggestions_screen);
            initPreference(getContext());
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadPreference failed e="), this.TAG);
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
        super.onDestroy();
    }
}
