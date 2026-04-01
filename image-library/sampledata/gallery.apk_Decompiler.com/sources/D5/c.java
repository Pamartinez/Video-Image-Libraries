package D5;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Adapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public /* synthetic */ c(View view, int i2) {
        this.d = i2;
        this.e = view;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        View view = this.e;
        switch (i2) {
            case 0:
                ((FloatingItemDelegate) obj).init(view);
                return;
            case 1:
                ((Drawable) obj).setHotspot(((float) view.getWidth()) / 2.0f, ((float) view.getHeight()) / 2.0f);
                return;
            case 2:
                ((Consumer) obj).accept(view);
                return;
            case 3:
                ((StoryHighlightListV2Adapter) obj).setThumbnailShape(view);
                return;
            default:
                ((Delegate) obj).initView(view);
                return;
        }
    }
}
