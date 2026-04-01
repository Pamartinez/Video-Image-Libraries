package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import A4.C0386v;
import A4.C0387w;
import H3.l;
import J6.c;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.ISlideshowV2View;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowV2Presenter<V extends ISlideshowV2View> extends MvpBasePresenter<V> {
    protected final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0(MediaData mediaData) {
            mediaData.changeOrder(SlideshowV2Presenter.this.mOrder.get());
        }

        public void onDataChanged() {
            if (SlideshowV2Presenter.this.fromSelection() && SlideshowV2Presenter.this.mFirstLoading.getAndSet(false)) {
                Optional.ofNullable(SlideshowV2Presenter.this.getMediaData()).ifPresent(new d(this));
            }
            ThreadUtil.runOnUiThread(new l(14, SlideshowV2Presenter.this));
        }
    };
    private final EventHandler mEventHandler;
    private final SimpleExportHandler mExportHandler;
    /* access modifiers changed from: private */
    public final AtomicBoolean mFirstLoading = new AtomicBoolean(true);
    private MediaData mMediaData;
    private final MenuUpdater mMenuUpdater;
    /* access modifiers changed from: private */
    public Order mOrder = Order.TIME_ASC;

    public SlideshowV2Presenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        this.mEventHandler = v.getEventHandler();
        this.mExportHandler = new SimpleExportHandler(v, this);
        this.mMenuUpdater = new MenuUpdater(v);
    }

    private void changeOrder(Order order) {
        MediaData mediaData = getMediaData();
        if (mediaData != null) {
            ThreadUtil.runOnBgThread(new c(this, mediaData, order, 0));
            this.mOrder = order;
        }
        Log.d("SlideshowPresenter", "changeOrder", order);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeOrder$1(MediaData mediaData, Order order) {
        mediaData.changeOrder(order.get());
        this.mDataChangeListener.onDataChanged();
        this.mEventHandler.postEvent(Event.MOVE_TO_HIGHLIGHT, 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$0(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        int i2;
        MediaData mediaData = getMediaData();
        if (mediaData == null || mediaData.getCount() == 0) {
            Utils.showToast(getContext(), (int) R.string.empty_set_description_no_pictures_and_videos, 1);
            ((ISlideshowV2View) this.mView).finish();
            if (mediaData != null) {
                i2 = mediaData.getCount();
            } else {
                i2 = -1;
            }
            Log.d("SlideshowPresenter", "onDataChangedOnUi #finish", Integer.valueOf(i2));
            return;
        }
        Log.d("SlideshowPresenter", "onDataChangedOnUi", Integer.valueOf(mediaData.getCount()));
        ((ISlideshowV2View) this.mView).onDataChangedOnUi();
    }

    /* access modifiers changed from: private */
    public boolean onHandleMenu(int i2) {
        if (i2 == R.id.action_share) {
            this.mExportHandler.startSimpleExport(true, 100, 3000);
        } else if (i2 == R.id.action_save_as_video) {
            this.mExportHandler.startSimpleExport(false, 100, 3000);
        } else if (i2 == R.id.action_order_asc) {
            changeOrder(Order.TIME_ASC);
        } else if (i2 == R.id.action_order_desc) {
            changeOrder(Order.TIME_DESC);
        }
        return true;
    }

    private void openMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(DataKey.getSlideshowDataKey(getLocationKey()));
        this.mMediaData = open;
        open.register(this.mDataChangeListener);
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        if (fromSelection()) {
            return MenuFactory.create(getBlackboard(), (ISlideshowV2View) this.mView);
        }
        return null;
    }

    public MenuHandler createMenuHandler() {
        return new MenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                return SlideshowV2Presenter.this.onHandleMenu(menuItem.getItemId());
            }
        };
    }

    public boolean fromSelection() {
        V v = this.mView;
        if (v == null || !((ISlideshowV2View) v).fromSelection()) {
            return false;
        }
        return true;
    }

    public MediaItem[] getAllItems() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return new MediaItem[0];
        }
        String str = hashTag() + ".getAllItems";
        try {
            this.mMediaData.acquireReadLock(str);
            IntStream range = IntStream.range(0, this.mMediaData.getCount());
            MediaData mediaData2 = this.mMediaData;
            Objects.requireNonNull(mediaData2);
            return (MediaItem[]) range.mapToObj(new C0386v(4, mediaData2)).toArray(new C0387w(18));
        } finally {
            this.mMediaData.releaseReadLock(str);
        }
    }

    public MediaItem getCurrentItem() {
        PreviewViewHolder currentViewHolder = this.mEventHandler.getCurrentViewHolder();
        if (currentViewHolder != null) {
            return currentViewHolder.getMediaItem();
        }
        return null;
    }

    public Object getEventContextData(String str) {
        PreviewViewHolder currentViewHolder;
        if (!"app_transition_view".equals(str) || (currentViewHolder = this.mEventHandler.getCurrentViewHolder()) == null) {
            return null;
        }
        return currentViewHolder.getImage();
    }

    public MediaItem getHeaderItem() {
        return null;
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public int getNaviUpResourceId() {
        return R.drawable.tw_ic_ab_back_mtrl_detailview_light;
    }

    public MediaItem[] getSelectedItems() {
        PreviewViewHolder currentViewHolder;
        if (isDestroyed() || (currentViewHolder = this.mEventHandler.getCurrentViewHolder()) == null) {
            return null;
        }
        return new MediaItem[]{currentViewHolder.getMediaItem()};
    }

    public Order getSortType() {
        return this.mOrder;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        SimpleExportHandler simpleExportHandler = this.mExportHandler;
        if (simpleExportHandler == null || !simpleExportHandler.handleEvent(eventMessage)) {
            return super.handleEvent(eventMessage);
        }
        return true;
    }

    public void handlePostEvent(Event event, Object... objArr) {
        if (event == Event.PLAYER_KEEP_PAUSE) {
            ((ISlideshowV2View) this.mView).keepScreenOn(!objArr[0].booleanValue());
            ((ISlideshowV2View) this.mView).invalidateOptionsMenu();
        }
    }

    public boolean isKeepPaused() {
        return ((Boolean) this.mEventHandler.requestData(DataRequest.PLAYER_KEEP_PAUSED, Boolean.FALSE)).booleanValue();
    }

    public void onDestroy() {
        super.onDestroy();
        closeMediaData();
    }

    public void onViewAttach() {
        super.onViewAttach();
        openMediaData();
    }

    public void prepareOptionsMenu(Menu menu) {
        this.mMenuUpdater.prepareOptionsMenu(menu, getMenuDataBinding());
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) "");
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
