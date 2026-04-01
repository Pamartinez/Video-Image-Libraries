package M6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.TableModeHandler;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPager;
import com.samsung.android.gallery.widget.tag.MyTagView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(TableModeHandler tableModeHandler, boolean z, int i2) {
        this.d = 4;
        this.g = tableModeHandler;
        this.f = z;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StoriesPinchViewHolder) this.g).lambda$updateTopRightDecoLayout$0(this.e, this.f);
                return;
            case 1:
                ((StoriesViewPager) this.g).lambda$setCurrentItem$1(this.e, this.f);
                return;
            case 2:
                ((MyTagView) this.g).lambda$setVisibility$2(this.e, this.f);
                return;
            case 3:
                ((ViewPagerDelegate) this.g).lambda$moveToHighlight$14(this.e, this.f);
                return;
            default:
                ((TableModeHandler) this.g).lambda$onTableModeChanged$3(this.f, this.e);
                return;
        }
    }

    public /* synthetic */ a(Object obj, int i2, boolean z, int i7) {
        this.d = i7;
        this.g = obj;
        this.e = i2;
        this.f = z;
    }
}
