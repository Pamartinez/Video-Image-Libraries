package r6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;

/* renamed from: r6.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0513c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ OnDemandPage e;

    public /* synthetic */ C0513c(OnDemandPage onDemandPage, int i2) {
        this.d = i2;
        this.e = onDemandPage;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        OnDemandPage onDemandPage = this.e;
        switch (i2) {
            case 0:
                onDemandPage.onSaveClick(view);
                return;
            default:
                onDemandPage.onRewritePrompt(view);
                return;
        }
    }
}
