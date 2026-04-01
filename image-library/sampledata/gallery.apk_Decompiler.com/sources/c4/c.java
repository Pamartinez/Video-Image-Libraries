package C4;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import com.samsung.android.gallery.widget.PinchBlurImageView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.LayoutRuleHolder;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2279h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2280i;

    public /* synthetic */ c(int i2, Object obj, Object obj2, Object obj3, Object obj4, boolean z) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = z;
        this.f2279h = obj3;
        this.f2280i = obj4;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                AlbumsBaseBlurPinchAnimationManager.lambda$prepareThumbnailBlurAnimation$6((ListViewHolder) this.f, (View) this.g, this.e, (PinchBlurImageView) this.f2279h, (LayoutRuleHolder) this.f2280i);
                return;
            case 1:
                ((UpdateStoryFavoriteCmd) this.f).lambda$onExecute$1((MediaItem[]) this.g, this.e, (MediaItem) this.f2279h, (String) this.f2280i);
                return;
            case 2:
                ((StoriesOnDemandDelegate) this.f).lambda$loadAndLaunchStory$9((MediaItem) this.g, (Bitmap) this.f2279h, (ArrayList) this.f2280i, this.e);
                return;
            default:
                ((ThumbProvider) this.f).lambda$applyFilter$0((AtomicReference) this.g, (Filter) this.f2279h, this.e, (Bitmap) this.f2280i);
                return;
        }
    }

    public /* synthetic */ c(StoriesOnDemandDelegate storiesOnDemandDelegate, MediaItem mediaItem, Bitmap bitmap, ArrayList arrayList, boolean z) {
        this.d = 2;
        this.f = storiesOnDemandDelegate;
        this.g = mediaItem;
        this.f2279h = bitmap;
        this.f2280i = arrayList;
        this.e = z;
    }

    public /* synthetic */ c(ThumbProvider thumbProvider, AtomicReference atomicReference, Filter filter, boolean z, Bitmap bitmap) {
        this.d = 3;
        this.f = thumbProvider;
        this.g = atomicReference;
        this.f2279h = filter;
        this.e = z;
        this.f2280i = bitmap;
    }
}
