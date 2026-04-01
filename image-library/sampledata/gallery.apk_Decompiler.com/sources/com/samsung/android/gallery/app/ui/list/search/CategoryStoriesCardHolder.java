package com.samsung.android.gallery.app.ui.list.search;

import A4.C0368c;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryStoriesItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.AbsPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryStoriesCardHolder extends CategoryCardHolderV2 implements ViewPagerListener {
    private final Config mConfiguration = new Config(0);
    Integer mDensity;
    private final LifecycleEvent mLifecycleEvent = new LifecycleEvent(this, 0);
    MediaData mMediaData;
    private final VideoPreviewDelegate mPreviewDelegate;
    Integer mResolution;
    private final Bundle mRestore = new Bundle();
    private final MediaData.SimpleDataChangeListener mStoriesDataListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY) {
                CategoryStoriesCardHolder.this.updateNoItemVisibility();
            }
        }
    };
    MediaData mStoriesMediaData;
    private TopColorEffectHandler mTopColorEffect;
    /* access modifiers changed from: private */
    public final CategoryStoriesTransitoryHolder mTransitoryHolder;
    private ISearchView mView;

    /* renamed from: com.samsung.android.gallery.app.ui.list.search.CategoryStoriesCardHolder$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.lifecycle.Lifecycle$Event[] r0 = androidx.lifecycle.Lifecycle.Event.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$lifecycle$Lifecycle$Event = r0
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_CREATE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$Event     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_START     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$Event     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_RESUME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$Event     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_PAUSE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$Event     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_STOP     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$Event     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_DESTROY     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.CategoryStoriesCardHolder.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Config {
        int density;
        int resolution;

        public /* synthetic */ Config(int i2) {
            this();
        }

        public boolean compareAndSet(Context context) {
            Configuration configuration = context.getResources().getConfiguration();
            int i2 = configuration.screenWidthDp * configuration.smallestScreenWidthDp;
            int i7 = configuration.densityDpi;
            if (this.resolution == i2 && this.density == i7) {
                return false;
            }
            this.resolution = i2;
            this.density = i7;
            return true;
        }

        private Config() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class LifecycleEvent implements LifecycleEventObserver {
        Blackboard mBlackBoard;
        private final SubscriberListener mEventCommand;
        private final SubscriberListener mOnHiddenChanged;

        public /* synthetic */ LifecycleEvent(CategoryStoriesCardHolder categoryStoriesCardHolder, int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void onEventCommand(Object obj, Bundle bundle) {
            CategoryStoriesCardHolder.this.onEvent(obj);
        }

        /* access modifiers changed from: private */
        public void onHiddenChanged(Object obj, Bundle bundle) {
            CategoryStoriesCardHolder.this.onHiddenChanged(((Boolean) obj).booleanValue());
        }

        private void subscribe() {
            Log.d(CategoryStoriesCardHolder.this.TAG, "subscribe", Integer.valueOf(hashCode()));
            Blackboard blackboard = this.mBlackBoard;
            if (blackboard != null) {
                blackboard.subscribeOnUi("command:///OnHiddenChanged", this.mOnHiddenChanged);
                this.mBlackBoard.subscribeOnUi("command:///event_command", this.mEventCommand);
            }
        }

        private void unsubscribe() {
            Log.d(CategoryStoriesCardHolder.this.TAG, "unsubscribe", Integer.valueOf(hashCode()));
            Blackboard blackboard = this.mBlackBoard;
            if (blackboard != null) {
                blackboard.unsubscribe("command:///OnHiddenChanged", this.mOnHiddenChanged);
                this.mBlackBoard.unsubscribe("command:///event_command", this.mEventCommand);
            }
        }

        public void attach(IMvpBaseView iMvpBaseView) {
            if (iMvpBaseView != null) {
                iMvpBaseView.getLifecycle().removeObserver(this);
                iMvpBaseView.getLifecycle().addObserver(this);
                this.mBlackBoard = iMvpBaseView.getBlackboard();
                subscribe();
            }
        }

        public void detach(IBaseFragment iBaseFragment) {
            if (iBaseFragment != null) {
                iBaseFragment.getLifecycle().removeObserver(this);
            }
            unsubscribe();
        }

        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Log.d(CategoryStoriesCardHolder.this.TAG, "LifecycleEvent onStateChanged", event);
            int i2 = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()];
            if (i2 == 3) {
                CategoryStoriesCardHolder.this.resume();
            } else if (i2 == 4) {
                CategoryStoriesCardHolder.this.pause();
            } else if (i2 == 5) {
                CategoryStoriesCardHolder.this.stop();
            } else if (i2 == 6) {
                CategoryStoriesCardHolder.this.destroy();
            }
        }

        private LifecycleEvent() {
            this.mOnHiddenChanged = new l(0, this);
            this.mEventCommand = new l(1, this);
        }
    }

    public CategoryStoriesCardHolder(View view, int i2) {
        super(view, i2);
        this.mTransitoryHolder = new CategoryStoriesTransitoryHolder(view, i2);
        this.mPreviewDelegate = new VideoPreviewDelegate(this);
    }

    private int compareConfiguration(Configuration configuration) {
        Integer num = this.mDensity;
        if (num == null || num.intValue() != configuration.densityDpi) {
            return 1;
        }
        Integer num2 = this.mResolution;
        if (num2 == null || num2.intValue() != configuration.screenWidthDp * configuration.smallestScreenWidthDp) {
            return -1;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void destroy() {
        this.mTransitoryHolder.destroy();
        this.mPreviewDelegate.destroy();
        TopColorEffectHandler topColorEffectHandler = this.mTopColorEffect;
        if (topColorEffectHandler != null) {
            topColorEffectHandler.destroy();
        }
    }

    private void handleEffectOnPageSelected(int i2) {
        ThreadUtil.postOnUiThread(new C0368c(this, i2, 22));
    }

    private void initTopColorEffect(IBaseListView iBaseListView) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesTopColorEffect)) {
            if (this.mTopColorEffect == null) {
                this.mTopColorEffect = new TopColorEffectHandler(iBaseListView);
            }
            this.mTopColorEffect.initView(iBaseListView.getView());
            if (!TextUtils.equals("location://search/fileList/Category/Stories", VisualSearchCategory.listOf().get(0))) {
                this.mTopColorEffect.setEnable(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEffectOnPageSelected$1(int i2) {
        ListViewHolder currentViewHolder = this.mTransitoryHolder.getCurrentViewHolder();
        if (this.mTopColorEffect != null && currentViewHolder != null) {
            int absoluteAdapterPosition = currentViewHolder.getAbsoluteAdapterPosition();
            this.mTopColorEffect.handleInternalEvent(InternalEvent.TRANSITORY_PAGE_SELECTED, Integer.valueOf(absoluteAdapterPosition), currentViewHolder.getMediaItem());
            if (i2 != absoluteAdapterPosition) {
                Log.d(this.TAG, "mismatch position", Integer.valueOf(absoluteAdapterPosition), Integer.valueOf(i2));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDataChangedOnBind$0(TopColorEffectHandler topColorEffectHandler) {
        if (topColorEffectHandler != null) {
            topColorEffectHandler.onDataChanged();
        }
    }

    private void notifyChange(String str) {
        if (this.mItemAdapter == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
            categoryItemAdapterV2.notifyItemRangeChanged(0, categoryItemAdapterV2.getItemCount(), str);
            return;
        }
        notifyDataSetChanged();
    }

    private void onDataChangedOnBind(boolean z) {
        ThreadUtil.postOnUiThreadDelayed(new m(2, this.mTopColorEffect), 100);
        if (z && this.mItemAdapter != null) {
            notifyDataSetChanged();
            Log.d(this.TAG, "notify data changed on bind");
        }
    }

    /* access modifiers changed from: private */
    public void onEvent(Object obj) {
        Object[] objArr = (Object[]) obj;
        if (objArr == null || objArr.length == 0) {
            Log.e(this.TAG, "no EventCommand");
            return;
        }
        String str = (String) objArr[0];
        if ("update_favorite".equals(str)) {
            notifyChange(str);
        } else if (InternalEvent.UPDATE_EXTRA_PADDING.name().equals(str)) {
            this.mTransitoryHolder.onHandleInternalEvent(str, objArr[1]);
        } else if (InternalEvent.UPDATE_BADGE.name().equals(str)) {
            notifyChange(str);
            this.mTransitoryHolder.onHandleInternalEvent(str, new Object[0]);
        } else if (InternalEvent.ON_DATE_TIME_CHANGED.name().equals(str)) {
            this.mTransitoryHolder.onHandleInternalEvent(str, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onHiddenChanged(boolean z) {
        this.mTransitoryHolder.onHandleInternalEvent("SHOW_SLIDESHOW", Boolean.valueOf(!z));
        if (!z) {
            this.mPreviewDelegate.requestPreview();
        } else {
            this.mPreviewDelegate.stop();
        }
    }

    private void openSearchStoriesData() {
        ISearchView iSearchView = this.mView;
        if (iSearchView != null && PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY && this.mStoriesMediaData == null) {
            MediaData mediaData = iSearchView.getMediaData("location://search/fileList/Category/Stories");
            this.mStoriesMediaData = mediaData;
            mediaData.register(this.mStoriesDataListener);
        }
    }

    /* access modifiers changed from: private */
    public void pause() {
        this.mTransitoryHolder.pause();
        this.mPreviewDelegate.pause();
    }

    private Bundle restoreState() {
        return this.mRestore;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.mTransitoryHolder.resume();
        this.mPreviewDelegate.resume();
    }

    private void saveState() {
        this.mRestore.putInt(Message.KEY_POSITION, this.mTransitoryHolder.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public void stop() {
        this.mTransitoryHolder.stop();
        this.mPreviewDelegate.stop();
    }

    /* access modifiers changed from: private */
    public void updateNoItemVisibility() {
        int i2;
        MediaData mediaData = this.mStoriesMediaData;
        boolean z = false;
        if (mediaData != null) {
            i2 = mediaData.getCount();
        } else {
            i2 = 0;
        }
        if (!OnDemandStory.getInstance().isEnabled() && i2 == 0) {
            z = true;
        }
        ViewUtils.setVisibleOrGone(this.itemView.findViewById(R.id.content_container), !z);
        ViewUtils.setVisibleOrGone(this.itemView.findViewById(R.id.no_item_container), z);
    }

    private void updateTitle() {
        MediaData mediaData;
        ISearchView iSearchView = this.mView;
        if (iSearchView != null) {
            mediaData = iSearchView.getMediaData("location://search/fileList/Category/Stories");
        } else {
            mediaData = null;
        }
        if (mediaData != null) {
            bindBasicInfo(this.mView, mediaData);
        }
    }

    public void bind(ISearchView iSearchView, MediaData mediaData) {
        boolean z;
        this.mMediaData = mediaData;
        if (this.mItemAdapter != null) {
            z = true;
        } else {
            z = false;
        }
        super.bind(iSearchView, mediaData);
        initTopColorEffect(iSearchView);
        if (this.mConfiguration.compareAndSet(this.itemView.getContext())) {
            saveState();
            this.mTransitoryHolder.clearView();
        }
        this.mView = iSearchView;
        this.mTransitoryHolder.setViewPagerLister(this);
        this.mTransitoryHolder.bindData(iSearchView, restoreState());
        this.mLifecycleEvent.attach(iSearchView);
        this.mPreviewDelegate.attach(iSearchView.getListView());
        this.mPreviewDelegate.setPlayable(new h(this, 3));
        openSearchStoriesData();
        onDataChangedOnBind(z);
        updateNoItemVisibility();
        if (compareConfiguration(this.itemView.getContext().getResources().getConfiguration()) != 0) {
            this.mTransitoryHolder.invalidateLayout();
        }
    }

    public CategoryItemAdapterV2<?> createAdapter(ISearchView iSearchView, String str) {
        return new CategoryStoriesItemAdapter(iSearchView, str, this.mListView, this.mPropertyHelper, false);
    }

    public int getContentPaddingBottom(boolean z) {
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return getDimensionPixelOffset(R.dimen.search_card_horizontal_list_margin_bottom_8x);
        }
        return super.getContentPaddingBottom(z);
    }

    public String getExpansionTargetLocation(MediaData mediaData, int i2) {
        return "location://story/albums";
    }

    public boolean isPlayable() {
        ISearchView iSearchView = this.mView;
        if (iSearchView == null || !iSearchView.isViewResumed() || !this.mView.isViewActive() || !this.itemView.isAttachedToWindow()) {
            return false;
        }
        return true;
    }

    public void notifyDataSetChanged() {
        ISearchView iSearchView;
        MediaData mediaData;
        if (this.mItemAdapter != null || (iSearchView = this.mView) == null || (mediaData = this.mMediaData) == null) {
            super.notifyDataSetChanged();
            updateTitle();
            return;
        }
        super.bind(iSearchView, mediaData);
    }

    public void onChildPageSelected(int i2, int i7, MediaItem mediaItem) {
        this.mPreviewDelegate.requestPreview();
    }

    public void onConfigurationChanged() {
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.clearChildViews();
        }
        this.mTransitoryHolder.invalidateLayout();
        super.onConfigurationChanged();
    }

    public void onPageSelected(int i2, int i7) {
        this.mPreviewDelegate.requestPreview();
        handleEffectOnPageSelected(i2);
    }

    public void recycle() {
        this.mMediaData = null;
        this.mConfiguration.compareAndSet(this.itemView.getContext());
        this.mLifecycleEvent.detach(this.mView);
        this.mPreviewDelegate.stop();
        TopColorEffectHandler topColorEffectHandler = this.mTopColorEffect;
        if (topColorEffectHandler != null) {
            topColorEffectHandler.destroy();
        }
        super.recycle();
        this.mTransitoryHolder.recycle();
        this.mView = null;
    }

    public /* bridge */ /* synthetic */ void setNotifySupplier(BooleanSupplier booleanSupplier) {
        super.setNotifySupplier(booleanSupplier);
    }

    public void transformPage(View view, float f) {
        if (this.mTopColorEffect != null) {
            this.mTopColorEffect.handleInternalEvent(InternalEvent.TRANSITORY_TRANSFORM_PAGE, Integer.valueOf(((RecyclerView.LayoutParams) view.getLayoutParams()).getAbsoluteAdapterPosition()), Float.valueOf(f));
        }
    }

    public /* bridge */ /* synthetic */ void updateContentPadding(boolean z) {
        super.updateContentPadding(z);
    }

    public /* bridge */ /* synthetic */ void updateDivider(boolean z) {
        super.updateDivider(z);
    }

    public /* bridge */ /* synthetic */ void updateDividerMarginTop() {
        super.updateDividerMarginTop();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoPreviewDelegate extends AbsPreviewDelegate {
        private static final int TIMER_ID = Timer.getTimerId();
        final CategoryStoriesCardHolder mCardHolder;

        public VideoPreviewDelegate(CategoryStoriesCardHolder categoryStoriesCardHolder) {
            super(TIMER_ID);
            this.mCardHolder = categoryStoriesCardHolder;
        }

        public PreviewViewHolder findHighPriorityPreviewHolder() {
            return this.mCardHolder.mTransitoryHolder.getPreviewableViewHolder(this.mList);
        }

        public void makeCandidates() {
        }
    }
}
