package com.samsung.android.gallery.settings.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import c0.C0086a;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.SearchCustomFragment;
import com.samsung.android.gallery.settings.ui.SearchCustomFragmentV2;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericSettingActivity extends BasePreferenceActivity {
    protected String mBaseLocationKey;
    protected String mLocationKey;

    public void commitFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("locationKey", this.mLocationKey);
        bundle.putString("locationKey", this.mLocationKey);
        bundle.putString("blackboardKey", this.mBlackboard.getName());
        Fragment fragment = getFragment(this.mLocationKey);
        if (fragment == null) {
            String str = this.TAG;
            Log.e(str, "Fail to execute GenericSettingActivity [" + this.mLocationKey + "][" + this.mBaseLocationKey + "]");
            finish();
            return;
        }
        fragment.setArguments(bundle);
        commitFragment(fragment);
    }

    public String getActivityTitle() {
        int i2;
        String str = this.mBaseLocationKey;
        str.getClass();
        if (str.equals("location://searchsettings")) {
            return getString(R$string.search_settings);
        }
        if (!str.equals("location://searchsettings/custom")) {
            String str2 = this.TAG;
            Log.e(str2, "getTitle invalid location key=" + this.mLocationKey);
            return "";
        }
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            i2 = R$string.edit_collections;
        } else {
            i2 = R$string.search_categories;
        }
        return getString(i2);
    }

    public Fragment getFragment(String str) {
        String str2 = this.mBaseLocationKey;
        str2.getClass();
        if (str2.equals("location://searchsettings")) {
            return new SearchSettingFragment();
        }
        if (!str2.equals("location://searchsettings/custom")) {
            return null;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.Collection)) {
            return new SearchCustomFragmentV2(this);
        }
        return new SearchCustomFragment(this);
    }

    public Fragment getTopFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return null;
        }
        return (Fragment) C0086a.f(1, fragments);
    }

    public void onBackPressed() {
        if (!onBackPressedOnFragment()) {
            super.onBackPressed();
        }
    }

    public boolean onBackPressedOnFragment() {
        Fragment topFragment = getTopFragment();
        if (!(topFragment instanceof IBaseView) || !((IBaseView) topFragment).onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onCreate(Bundle bundle) {
        String str;
        String stringExtra = getIntent().getStringExtra("locationKey");
        this.mLocationKey = stringExtra;
        if (stringExtra == null) {
            str = "";
        } else {
            str = ArgumentsUtil.removeArgs(stringExtra);
        }
        this.mBaseLocationKey = str;
        super.onCreate(bundle);
        setTitle(getActivityTitle());
        commitFragment();
    }

    public void onHomeAsUpPressed() {
        if (!onBackPressedOnFragment()) {
            super.onHomeAsUpPressed();
        }
    }
}
