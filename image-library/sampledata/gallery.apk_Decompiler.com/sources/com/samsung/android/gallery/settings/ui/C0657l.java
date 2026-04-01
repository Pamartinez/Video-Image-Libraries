package com.samsung.android.gallery.settings.ui;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;
import androidx.preference.Preference;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.utils.StringResources;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.settings.ui.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0657l implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0657l(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((AppBarLayout) obj).setExpanded(false);
                return;
            case 1:
                ((TextView) obj).setTextColor(-16776961);
                return;
            case 2:
                Toast.makeText((Context) obj, ((Context) obj).getString(R$string.cannot_connect_to_cloud, new Object[]{StringResources.getCloudBrand()}), 0).show();
                return;
            case 3:
                ((Preference) obj).setVisible(false);
                return;
            default:
                ((Preference) obj).setVisible(false);
                return;
        }
    }
}
