package com.samsung.android.gallery.settings.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.AIEditSuggestionsFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AIEditSuggestionsActivity extends BasePreferenceActivity {
    public String getActivityTitle() {
        return getString(R$string.edit_suggestions);
    }

    public Fragment getFragment(String str) {
        return new AIEditSuggestionsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        commitFragment();
    }
}
