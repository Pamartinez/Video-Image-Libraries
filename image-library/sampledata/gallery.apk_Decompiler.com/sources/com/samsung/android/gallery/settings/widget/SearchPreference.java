package com.samsung.android.gallery.settings.widget;

import E9.a;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPreference extends Preference {
    final LabsSearchDelegate mLabsSearchDelegate = new LabsSearchDelegate();
    PreferenceScreen mPreferenceScreen;

    public SearchPreference(Context context) {
        super(context);
        setLayoutResource(R$layout.labs_search_layout);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(View view) {
        this.mLabsSearchDelegate.setSearchView((SearchView) view, this.mPreferenceScreen);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        Optional.ofNullable(preferenceViewHolder.itemView.findViewById(R$id.labs_search_view)).ifPresent(new a(25, this));
        super.onBindViewHolder(preferenceViewHolder);
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        this.mPreferenceScreen = preferenceScreen;
    }

    public SearchPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setLayoutResource(R$layout.labs_search_layout);
    }

    public SearchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R$layout.labs_search_layout);
    }
}
