package r6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.CollagePage;

/* renamed from: r6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0511a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CollagePage e;

    public /* synthetic */ C0511a(CollagePage collagePage, int i2) {
        this.d = i2;
        this.e = collagePage;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        CollagePage collagePage = this.e;
        switch (i2) {
            case 0:
                collagePage.lambda$onBindItem$0(view);
                return;
            default:
                collagePage.lambda$onBindItem$1(view);
                return;
        }
    }
}
