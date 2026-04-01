package E9;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ILargeImage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.LargeImageLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.mde.executor.CreateSpace;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2798a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2799c;
    public final /* synthetic */ Object d;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i2) {
        this.f2798a = i2;
        this.b = obj;
        this.f2799c = obj2;
        this.d = obj3;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2798a) {
            case 0:
                ((CreateSpace) this.b).lambda$execute$0((String) this.f2799c, (Bundle) this.d, (Integer) obj, (String) obj2);
                return;
            case 1:
                ((LabsDevManageFragment) this.b).lambda$loadCacheInfo$57((HashMap) this.f2799c, (HashMap) this.d, (String) obj, (Integer) obj2);
                return;
            case 2:
                ShotModeList.lambda$findShotMode$0((BiPredicate) this.b, (String) this.f2799c, (ArrayList) this.d, (String) obj, (ShotMode) obj2);
                return;
            case 3:
                GalleryPreference.lambda$removeStateIf$0((Predicate) this.b, (ArrayList) this.f2799c, (HashMap) this.d, (String) obj, obj2);
                return;
            case 4:
                Bitmap bitmap = (Bitmap) obj;
                ((LargeImageLoader) this.b).lambda$bindOriginImage$2((MediaItem) this.f2799c, (ILargeImage) this.d, bitmap, (Filter) obj2);
                return;
            default:
                ((StoryHighlightViewPagerAdapter) this.b).lambda$onThumbnailLoadCompleted$2((MediaItem) this.f2799c, (ViewPagerHolder) this.d, (Bitmap) obj, (Filter) obj2);
                return;
        }
    }
}
