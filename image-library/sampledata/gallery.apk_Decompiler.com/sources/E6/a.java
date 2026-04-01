package E6;

import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.widget.editdetails.EditDetailsEditText;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnLongClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onLongClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ((StoryCoverViewHolder) obj).onItemLongClicked(view);
            case 1:
                return ((VideoController) obj).onPlayPauseViewLongClicked(view);
            case 2:
                return ((DrawerTabItemViewHolder) obj).onLongClick(view);
            default:
                return ((EditDetailsEditText) obj).lambda$init$1(view);
        }
    }
}
