package com.samsung.android.gallery.settings.ui;

import android.view.MotionEvent;
import android.view.View;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.SearchCustomFragmentV2;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.support.utils.TriConsumer;
import com.samsung.android.gallery.support.utils.ZipCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements ZipCompat.OnProgressListener, TriConsumer, Preference.OnPreferenceChangeListener, TroubleshootingFragment.TroubleResolverViewHolder.OnViewHolderClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ y(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void a(int i2, int i7) {
        ((LabsDevManageFragment.CacheDumpWorker) this.e).lambda$onExecute$0(i2, i7);
    }

    public void accept(Object obj, Object obj2, Object obj3) {
        SearchCustomViewAdapter searchCustomViewAdapter;
        int i2 = this.d;
        Object obj4 = this.e;
        switch (i2) {
            case 1:
                searchCustomViewAdapter = (SearchCustomFragmentV2.AnonymousClass1) obj4;
                break;
            default:
                searchCustomViewAdapter = (SearchCustomViewAdapter) obj4;
                break;
        }
        searchCustomViewAdapter.onReorderItemTouch((RecyclerView.ViewHolder) obj, (View) obj2, (MotionEvent) obj3);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return ((SettingCloud2) this.e).lambda$initSamsungCloudSync$3(preference, obj);
    }
}
