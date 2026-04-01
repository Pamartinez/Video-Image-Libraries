package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.map.staticmarker.MarkerViewHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ e(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((View) obj).setVisibility(8);
                return;
            case 1:
                ((View) obj).setVisibility(8);
                return;
            case 2:
                ((View) obj).setVisibility(8);
                return;
            case 3:
                ((RecyclerView) obj).setAdapter((RecyclerView.Adapter) null);
                return;
            case 4:
                ((RecyclerView) obj).setAdapter((RecyclerView.Adapter) null);
                return;
            case 5:
                ((View) obj).setVisibility(0);
                return;
            case 6:
                ((ImageView) obj).setImageBitmap((Bitmap) null);
                return;
            default:
                ((MarkerViewHolder) obj).setVisibility(8);
                return;
        }
    }
}
