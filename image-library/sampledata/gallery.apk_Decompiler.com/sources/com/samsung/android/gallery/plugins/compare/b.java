package com.samsung.android.gallery.plugins.compare;

import android.view.View;
import com.samsung.android.gallery.plugins.compare.CompareActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CompareActivity e;
    public final /* synthetic */ CompareActivity.PhotoViewHolder f;

    public /* synthetic */ b(CompareActivity compareActivity, CompareActivity.PhotoViewHolder photoViewHolder, int i2) {
        this.d = i2;
        this.e = compareActivity;
        this.f = photoViewHolder;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$onCreate$0(this.f, view);
                return;
            default:
                this.e.lambda$onCreate$3(this.f, view);
                return;
        }
    }
}
