package com.samsung.android.gallery.settings.activity;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements View.OnClickListener {
    public final /* synthetic */ ThirdPartyAccessActivity d;

    public /* synthetic */ h(ThirdPartyAccessActivity thirdPartyAccessActivity) {
        this.d = thirdPartyAccessActivity;
    }

    public final void onClick(View view) {
        this.d.onLinkClicked(view);
    }
}
