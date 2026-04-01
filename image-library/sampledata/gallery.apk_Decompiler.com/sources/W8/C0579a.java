package W8;

import android.animation.ValueAnimator;
import android.database.Cursor;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.view.View;
import android.view.ViewStub;
import androidx.appcompat.animation.SeslRecoilAnimator;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransform;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPagerAdapter;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* renamed from: W8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0579a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0579a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                PetDataManager.sIndexingDelegate.index(new CreatureIndexingBuilder().unifiedId(Long.MIN_VALUE, ((Long) obj).longValue() + 100000).targetGroupId(((Long) obj).longValue()));
                return;
            case 1:
                ((AnimatedVectorDrawable) obj).reset();
                return;
            case 2:
                ((AnimatedVectorDrawable) obj).start();
                return;
            case 3:
                ((PageTransform) obj).onCancel();
                return;
            case 4:
                ((ViewStub) obj).inflate();
                return;
            case 5:
                Utils.recreateActivity((FragmentActivity) obj);
                return;
            case 6:
                ((GalleryListView) obj).updateGoToTopBottomPadding((float) ((GalleryListView) obj).getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
                return;
            case 7:
                ((Cursor) obj).close();
                return;
            case 8:
                ((PdcRecommendDelegate.Listener) obj).onLaunchFullList();
                return;
            case 9:
                VibeRenderEffectBase.stop$lambda$5((View) obj);
                return;
            case 10:
                VibeRenderEffectBase.start$lambda$4((View) obj);
                return;
            case 11:
                SeslRecoilAnimator.Holder.lambda$setRelease$0((SeslRecoilAnimator) obj);
                return;
            case 12:
                ((Consumer) obj).accept(2);
                return;
            case 13:
                ((Consumer) obj).accept(1);
                return;
            case 14:
                ((Consumer) obj).accept(1);
                return;
            case 15:
                ((View) obj).playSoundEffect(0);
                return;
            case 16:
                SearchMarkerManagerDelegate.lambda$removeHighlightMarkers$4((MarkerManager) obj);
                return;
            case 17:
                ((MarkerManager) obj).unsetEntryMarker();
                return;
            case 18:
                ((ValueAnimator) obj).pause();
                return;
            case 19:
                ((ValueAnimator) obj).cancel();
                return;
            case 20:
                ((ListViewHolder) obj).resetViewProperty();
                return;
            case 21:
                ((ListViewHolder) obj).handleEvent(1002, new Object[0]);
                return;
            case 22:
                ((StoriesViewPagerAdapter) obj).notifyItemRangeChanged(0, ((StoriesViewPagerAdapter) obj).getItemCount(), "PAYLOAD_UPDATE_BADGE");
                return;
            case 23:
                ((ViewPagerTouchDelegate.OnTouchEventListener) obj).onTouchUp();
                return;
            case 24:
                ((ViewPagerTouchDelegate.OnTouchEventListener) obj).onTouchCancel();
                return;
            case 25:
                ((ViewPagerTouchDelegate.OnTouchEventListener) obj).swipe();
                return;
            case 26:
                ((DownloadTask) obj).interrupt();
                return;
            case 27:
                ((DialogHelper) obj).dismissDialog();
                return;
            case 28:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1092));
                return;
            default:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1117));
                return;
        }
    }
}
