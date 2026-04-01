package com.samsung.android.gallery.app.ui.list.abstraction;

import A2.d;
import A4.C0366a;
import A4.C0367b;
import A4.C0370e;
import A4.C0371f;
import A4.C0372g;
import A4.C0373h;
import A4.C0374i;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.abstraction.DefaultEntryAnim;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseListDelegate {
    private final CharSequence TAG;
    private Runnable mDismissPopupMenu;
    private final DefaultEntryAnim mEntryAnim;
    private final IBaseListView mView;

    public BaseListDelegate(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
        this.TAG = iBaseListView.tag();
        DefaultEntryAnim defaultEntryAnim = new DefaultEntryAnim();
        this.mEntryAnim = defaultEntryAnim;
        defaultEntryAnim.setAnimationEndListener(new C0367b(1, this));
    }

    private Blackboard getBlackboard() {
        return this.mView.getBlackboard();
    }

    private BaseListPresenter<?> getPresenter() {
        return this.mView.getPresenter();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindImage$0(Bitmap[] bitmapArr, MediaItem mediaItem) {
        bitmapArr[0] = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindImage$1(MediaItem mediaItem) {
        if (mediaItem.isVideo() && mediaItem.getOrientation() == 0) {
            MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem);
            if (videoInfo != null) {
                mediaItem.setOrientation(videoInfo.orientation);
            } else {
                mediaItem.setOrientation(MediaHelper.getVideoOrientation(mediaItem.getPath()));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindImage$2(MediaItem mediaItem, Bitmap[] bitmapArr, ListViewHolder listViewHolder, Boolean bool) {
        Bitmap bitmap;
        if (bool.booleanValue()) {
            bitmap = ThumbnailUtil.createDummyBitmap(mediaItem);
        } else {
            bitmap = bitmapArr[0];
        }
        listViewHolder.bindImage(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createPopupMenu$3(Blackboard blackboard, MenuItem menuItem) {
        return this.mView.onOptionsItemSelected(menuItem, !PickerUtil.isNormalLaunchMode(blackboard));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createPopupMenu$4(Blackboard blackboard, ViewGroup viewGroup, View view, PopupMenu popupMenu) {
        removeAnchorView(blackboard, viewGroup, view);
    }

    private void removeAnchorView(Blackboard blackboard, ViewGroup viewGroup, View view) {
        if (view != null) {
            blackboard.publish("data://floating_pop_menu", Boolean.FALSE);
            blackboard.publish("data://focused_item", (Object) null);
            ViewUtils.removeView(viewGroup, view);
            Log.d(this.TAG, "Dismiss Pop-up(remove view) menu", this.mView.getLocationKey());
        }
    }

    public final void bindImage(ListViewHolder listViewHolder, MediaItem mediaItem, boolean z) {
        if ((!mediaItem.isBroken() && listViewHolder.getThumbKind().size() < ThumbKind.MEDIUM_KIND.size()) || z) {
            Bitmap[] bitmapArr = new Bitmap[1];
            new LatchBuilder("onListItemClick").addWorker(new d(2, bitmapArr, mediaItem)).addWorker(new C0370e(mediaItem, 0)).setPostExecutor((Consumer<Boolean>) new C0371f((Object) mediaItem, (Object) bitmapArr, (Object) listViewHolder, 0)).setTimeout(600).start();
        }
    }

    public Animation createAnimation(Context context, int i2) {
        return this.mEntryAnim.createAnimation(context, i2);
    }

    public void createPopupMenu(ViewGroup viewGroup, PointF pointF, MediaItem mediaItem) {
        float f;
        int i2;
        FragmentActivity activity = this.mView.getActivity();
        Blackboard blackboard = getBlackboard();
        BaseListPresenter<?> presenter = getPresenter();
        if (activity != null && blackboard != null && presenter != null) {
            boolean booleanValue = ((Boolean) blackboard.read("data://floating_pop_menu", Boolean.FALSE)).booleanValue();
            if (viewGroup != null && !booleanValue) {
                if (mediaItem == null || !this.mView.isSelectionMode() || this.mView.getSelectedItemCount() != 0) {
                    Context context = viewGroup.getContext();
                    View view = new View(context);
                    int i7 = 1;
                    view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
                    view.setBackgroundColor(0);
                    viewGroup.addView(view);
                    Rect bounds = activity.getWindowManager().getCurrentWindowMetrics().getBounds();
                    if (view.getResources().getBoolean(R.bool.is_right_to_left)) {
                        f = pointF.x;
                        i2 = bounds.right;
                    } else {
                        f = pointF.x;
                        i2 = bounds.left;
                    }
                    view.setX(f - ((float) i2));
                    view.setY(pointF.y - ((float) bounds.top));
                    blackboard.publish("data://floating_pop_menu", Boolean.TRUE);
                    blackboard.publish("data://focused_item", mediaItem);
                    PopupMenu popupMenu = new PopupMenu(context, view, 8388611);
                    Menu menu = popupMenu.getMenu();
                    this.mDismissPopupMenu = new C0372g(0, popupMenu);
                    if (presenter.onCreatePopupMenu(menu, popupMenu.getMenuInflater())) {
                        if (mediaItem == null && this.mView.getSelectedItemCount() == 0) {
                            i7 = 0;
                        }
                        if (presenter.onPreparePopupMenu(menu, i7)) {
                            popupMenu.setOnMenuItemClickListener(new C0373h(0, this, blackboard));
                            popupMenu.setOnDismissListener(new C0374i(this, blackboard, viewGroup, view, 0));
                            popupMenu.show();
                            Log.d(this.TAG, "Show Pop-up(add view) menu", this.mView.getLocationKey());
                            return;
                        }
                        removeAnchorView(blackboard, viewGroup, view);
                        return;
                    }
                    removeAnchorView(blackboard, viewGroup, view);
                }
            }
        }
    }

    public void dismissPopupView() {
        Runnable runnable = this.mDismissPopupMenu;
        if (runnable != null) {
            runnable.run();
            this.mDismissPopupMenu = null;
        }
    }

    public boolean isEnterTransitionState() {
        return this.mEntryAnim.isEnterTransitionState();
    }

    public void onEnterTransitionEnd(boolean z) {
        Log.d(this.TAG, "defaultEnterTransitionEnd");
    }

    public void performDragAutoHaptic() {
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            SeApiCompat.performHapticFeedback(this.mView.getActivity(), 14);
        }
    }

    public void updateEmptyViewDensity(View view) {
        if (view != null) {
            Optional.ofNullable((TextView) view.findViewById(R.id.label)).ifPresent(new C0366a(2));
            Optional.ofNullable((TextView) view.findViewById(R.id.description)).ifPresent(new C0366a(3));
        }
    }
}
