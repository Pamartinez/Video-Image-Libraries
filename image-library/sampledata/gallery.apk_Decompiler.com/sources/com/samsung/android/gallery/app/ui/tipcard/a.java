package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbsTipCard e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ TipCardViewHolder g;

    public /* synthetic */ a(AbsTipCard absTipCard, Context context, TipCardViewHolder tipCardViewHolder, int i2) {
        this.d = i2;
        this.e = absTipCard;
        this.f = context;
        this.g = tipCardViewHolder;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$initializeView$2(this.f, this.g, view);
                return;
            default:
                this.e.lambda$initializeView$3(this.f, this.g, view);
                return;
        }
    }
}
