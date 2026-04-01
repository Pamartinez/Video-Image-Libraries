package com.samsung.android.gallery.settings.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.EditSuggestionsFragment;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditSuggestionsActivity extends BasePreferenceActivity {
    public String getActivityTitle() {
        return getString(R$string.edit_suggestions);
    }

    public Fragment getFragment(String str) {
        return new EditSuggestionsFragment(getIntent().hasExtra("from_settings"));
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        super.onActivityResult(i2, i7, intent);
        this.mBlackboard.postEvent(EventMessage.obtain(7009, i2, i7, intent));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        commitFragment();
    }
}
