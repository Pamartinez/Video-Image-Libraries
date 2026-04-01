package ic;

import android.graphics.Bitmap;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.menu.BottomTabTouchDelegate;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationFragment;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.location.b;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.NaviUpDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.module.story.transcode.decoder.video.Decoder;
import com.samsung.android.gallery.module.story.transcode.decoder.video.ImageDecoder;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.AudioManagerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.GestureDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.InstantSlowMoPlayDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaSessionDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.RoundedCornerDelegate;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ l(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((RoundedCornerDelegate) obj).onAttachedToWindow();
                return;
            case 1:
                ((GestureDelegate) obj).cancelFlingAnimation();
                return;
            case 2:
                ((RoundedCornerDelegate) obj).onConfigurationChanged();
                return;
            case 3:
                ((InstantSlowMoPlayDelegate) obj).clearInstantSlowMoExecutedSection();
                return;
            case 4:
                ((MediaSessionDelegate) obj).createMediaSession();
                return;
            case 5:
                ((AudioManagerDelegate) obj).close();
                return;
            case 6:
                ((MediaSessionDelegate) obj).releaseMediaSession();
                return;
            case 7:
                ((ClipViewDelegate) obj).refreshView();
                return;
            case 8:
                obj.getClass();
                throw new ClassCastException();
            case 9:
                ((ViewerMenuDelegate) obj).closeMoreMenu();
                return;
            case 10:
                ((FragmentActivity) obj).setResult(3200);
                return;
            case 11:
                ((DecorViewDelegate) obj).onToggleOsd();
                return;
            case 12:
                ((BottomTabTouchDelegate.OnMenuTabTouchListener) obj).onMenuTabTouched();
                return;
            case 13:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1003));
                return;
            case 14:
                ((NaviUpDelegate) obj).initNaviUp();
                return;
            case 15:
                ((AlertDialog) obj).dismiss();
                return;
            case 16:
                ((Bitmap) obj).recycle();
                return;
            case 17:
                ((ImageDecoder) obj).release();
                return;
            case 18:
                ((Decoder) obj).release();
                return;
            case 19:
                ((Decoder) obj).interrupt();
                return;
            case 20:
                ((MvpBaseFragment) obj).clearAdvancedMouseFocus();
                return;
            case 21:
                ((MvpBaseFragment) obj).postAnalyticsLog(AnalyticsEventId.EVENT_BOTTOM_TAB_MENU);
                return;
            case 22:
                ((c) obj).a();
                return;
            case 23:
                SearchLocationFragment.lambda$onHandleEvent$0((SearchLocationItemAdapter) obj);
                return;
            case 24:
                ((ArrayList) obj).sort(new b(0));
                return;
            case 25:
                ((ArrayList) obj).sort(new b(1));
                return;
            case 26:
                ((FilmStripView) obj).stopScroll();
                return;
            case 27:
                ((ByteBuffer) obj).clear();
                return;
            case 28:
                SearchMyQuery.getInstance().deleteById(((Integer) obj).intValue());
                return;
            default:
                ((Blackboard) obj).postBroadcastEvent(EventMessage.obtain(103));
                return;
        }
    }
}
