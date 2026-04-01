package Eb;

import android.view.View;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ HoverPreviewViewHolder e;

    public /* synthetic */ b(HoverPreviewViewHolder hoverPreviewViewHolder, int i2) {
        this.d = i2;
        this.e = hoverPreviewViewHolder;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        HoverPreviewViewHolder hoverPreviewViewHolder = this.e;
        switch (i2) {
            case 0:
                hoverPreviewViewHolder.lambda$setEnableDelete$3(view);
                return;
            default:
                hoverPreviewViewHolder.lambda$setEnableShare$2(view);
                return;
        }
    }
}
