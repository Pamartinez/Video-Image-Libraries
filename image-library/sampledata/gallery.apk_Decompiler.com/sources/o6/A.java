package o6;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPager;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewPagerDelegate e;

    public /* synthetic */ A(ViewPagerDelegate viewPagerDelegate, int i2) {
        this.d = i2;
        this.e = viewPagerDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ViewPagerDelegate viewPagerDelegate = this.e;
        switch (i2) {
            case 0:
                viewPagerDelegate.onThemeInitialized((Object[]) obj);
                return;
            case 1:
                viewPagerDelegate.lambda$addListenEvent$5((Object[]) obj);
                return;
            case 2:
                viewPagerDelegate.lambda$addListenEvent$6((Object[]) obj);
                return;
            case 3:
                viewPagerDelegate.onTransitionEnd((Object[]) obj);
                return;
            case 4:
                viewPagerDelegate.setVisible((Object[]) obj);
                return;
            case 5:
                viewPagerDelegate.onKeepPaused((Object[]) obj);
                return;
            case 6:
                viewPagerDelegate.lambda$addListenEvent$1((Object[]) obj);
                return;
            case 7:
                viewPagerDelegate.lambda$addListenEvent$2((Object[]) obj);
                return;
            case 8:
                viewPagerDelegate.onBottomSheetStateChanged((Object[]) obj);
                return;
            case 9:
                viewPagerDelegate.onStoryChanged((Object[]) obj);
                return;
            case 10:
                viewPagerDelegate.moveToHighlight((Object[]) obj);
                return;
            case 11:
                viewPagerDelegate.onRelatedViewStateChanged((Object[]) obj);
                return;
            case 12:
                viewPagerDelegate.lambda$initView$0((RecyclerView.ViewHolder) obj);
                return;
            case 13:
                viewPagerDelegate.lambda$onThemeInitialized$15((StoryHighlightViewPager) obj);
                return;
            case 14:
                viewPagerDelegate.onFilmCenterChanged((Object[]) obj);
                return;
            case 15:
                viewPagerDelegate.onFilterChanged((Object[]) obj);
                return;
            case 16:
                viewPagerDelegate.lambda$addListenEvent$3((Object[]) obj);
                return;
            default:
                viewPagerDelegate.lambda$addListenEvent$4((Object[]) obj);
                return;
        }
    }
}
