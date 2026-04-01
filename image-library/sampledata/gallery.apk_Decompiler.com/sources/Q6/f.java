package q6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.ReplayView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ReplayView e;

    public /* synthetic */ f(ReplayView replayView, int i2) {
        this.d = i2;
        this.e = replayView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        ReplayView replayView = this.e;
        switch (i2) {
            case 0:
                replayView.lambda$initView$0(view);
                return;
            case 1:
                replayView.lambda$initView$1(view);
                return;
            default:
                replayView.lambda$initView$2(view);
                return;
        }
    }
}
