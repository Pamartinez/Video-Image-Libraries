package com.samsung.android.gallery.app.ui.list.stories.category;

import O3.o;
import T3.e;
import U5.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.view.ViewStub;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.ondemand.OnDemandStory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TopColorEffectHandler {
    private static final RectF EMPTY = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    /* access modifiers changed from: private */
    public static final LruCache<Integer, Integer> sColorCache = new LruCache<>(20);
    final EffectDimenRes mEffectDimenRes = new EffectDimenRes();
    private final HashMap<Integer, EffectHolder> mEffectHolders = new HashMap<>();
    private RecyclerView mList;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            if (i7 != 0) {
                TopColorEffectHandler.this.adjustAlphaByPosition();
            }
        }
    };
    private View mTransitoryView;
    private final IBaseListView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EffectDimenRes {
        float brightness = 0.95f;
        float colorAlpha = 0.65f;
        float gradientStart = 0.3f;
        float maxSaturation = 0.6f;
        float minSaturation = 0.5f;

        public void init(Context context) {
            this.minSaturation = ResourceCompat.getFloatFromDimension(context, (int) R.dimen.stories_top_color_min_saturation, 0.5f);
            this.maxSaturation = ResourceCompat.getFloatFromDimension(context, (int) R.dimen.stories_top_color_max_saturation, 0.6f);
            this.brightness = ResourceCompat.getFloatFromDimension(context, (int) R.dimen.stories_top_color_brightness, 0.95f);
            this.colorAlpha = ResourceCompat.getFloatFromDimension(context, (int) R.dimen.stories_top_color_color_alpha, 0.65f);
            this.gradientStart = ResourceCompat.getFloatFromDimension(context, (int) R.dimen.stories_top_color_gradient_start, 0.3f);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EffectHolder {
        EffectItem EMPTY;
        EffectItem[] items = new EffectItem[3];
        View parent;

        public EffectHolder(View view, EffectDimenRes effectDimenRes) {
            this.parent = view;
            this.EMPTY = new EffectItem((View) null, effectDimenRes);
            this.items[0] = new EffectItem(view.findViewById(R.id.color_effect_view_0), effectDimenRes);
            this.items[1] = new EffectItem(view.findViewById(R.id.color_effect_view_1), effectDimenRes);
            this.items[2] = new EffectItem(view.findViewById(R.id.color_effect_view_2), effectDimenRes);
        }

        public void bindColor(int i2, int i7, MediaItem mediaItem) {
            getItem(i2).compareAndUpdate(i2, i7, mediaItem);
        }

        public EffectItem getItem(int i2) {
            if (i2 < 0) {
                return this.EMPTY;
            }
            EffectItem[] effectItemArr = this.items;
            return effectItemArr[i2 % effectItemArr.length];
        }

        public void updateByTransform(int i2, float f) {
            float f5;
            if (Math.abs(f) == 1.0f || Math.abs(f) == 2.0f) {
                getItem(i2).setAlpha(0.0f);
            } else if (Math.abs(f) < 1.0f) {
                EffectItem item = getItem(i2);
                if (f < 0.0f) {
                    f5 = f + 1.0f;
                } else {
                    f5 = 1.0f - f;
                }
                item.setAlpha(f5);
            }
        }
    }

    public TopColorEffectHandler(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
    }

    private void bindEffectView(View view) {
        if (view == null) {
            Log.e("TopColorEffectHandler", "bindEffectView fail due to no parent");
            return;
        }
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.top_color_effect_layout_stub);
        if (viewStub != null) {
            this.mEffectHolders.put(0, new EffectHolder(viewStub.inflate(), this.mEffectDimenRes));
            return;
        }
        for (EffectHolder effectHolder : this.mEffectHolders.values()) {
            ViewUtils.setViewSize(effectHolder.parent, (Integer) null, Integer.valueOf(view.getResources().getDimensionPixelOffset(R.dimen.stories_top_color_effect_view_height)));
        }
    }

    private int getDataCount() {
        MediaData transitoryStoriesData = getTransitoryStoriesData();
        if (transitoryStoriesData != null) {
            return transitoryStoriesData.getCount();
        }
        return 0;
    }

    private MediaItem getMediaItem(int i2, int i7) {
        if (!PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY || i7 != 102) {
            MediaData transitoryStoriesData = getTransitoryStoriesData();
            if (transitoryStoriesData == null || i2 >= transitoryStoriesData.getCount()) {
                return null;
            }
            return transitoryStoriesData.readCache(i2);
        }
        ListViewHolder viewHolder = getViewHolder(i2);
        if (viewHolder != null) {
            return viewHolder.getMediaItem();
        }
        return null;
    }

    private View getTransitoryCatView() {
        RecyclerView recyclerView = this.mList;
        if (recyclerView != null && this.mTransitoryView == null) {
            this.mTransitoryView = recyclerView.findViewById(R.id.transitory_container);
        }
        return this.mTransitoryView;
    }

    private MediaData getTransitoryStoriesData() {
        return MediaDataFactory.getInstance(this.mView.getBlackboard()).find("location://stories/category/transitory");
    }

    private ListViewHolder getViewHolder(int i2) {
        View findViewByPosition;
        if (this.mView.isDestroyed() || getTransitoryCatView() == null || i2 < 0) {
            return null;
        }
        ViewPager2 viewPager2 = (ViewPager2) getTransitoryCatView().findViewById(R.id.transitory_viewpager);
        RecyclerView.LayoutManager layoutManager = viewPager2.seslGetListView().getLayoutManager();
        if (layoutManager == null || (findViewByPosition = layoutManager.findViewByPosition(i2)) == null) {
            return null;
        }
        return (ListViewHolder) viewPager2.seslGetListView().getChildViewHolder(findViewByPosition);
    }

    private ViewPager2 getViewPager() {
        if (getTransitoryCatView() != null) {
            return (ViewPager2) getTransitoryCatView().findViewById(R.id.transitory_viewpager);
        }
        return null;
    }

    private RectF intersect(RectF rectF, RectF rectF2) {
        if (!rectF.intersect(rectF2)) {
            return EMPTY;
        }
        RectF rectF3 = new RectF();
        rectF3.left = Math.max(rectF.left, rectF2.left);
        rectF3.right = Math.min(rectF.right, rectF2.right);
        rectF3.top = Math.max(rectF.top, rectF2.top);
        rectF3.bottom = Math.min(rectF.bottom, rectF2.bottom);
        return rectF3;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroy$1(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(this.mScrollListener);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPageSelected$2(ViewPager2 viewPager2) {
        if (viewPager2.getScrollState() == 0) {
            viewPager2.requestTransform();
        }
    }

    private void onPageSelected(int i2, MediaItem mediaItem) {
        int max = Math.max(0, i2 - 1);
        for (int i7 = max; i7 <= max + 2; i7++) {
            for (EffectHolder bindColor : this.mEffectHolders.values()) {
                int intValue = ((Integer) Optional.ofNullable(getViewHolder(i7)).map(new o(26)).orElse(-1)).intValue();
                bindColor.bindColor(i7, intValue, getMediaItem(i7, intValue));
            }
        }
        Optional.ofNullable(getViewPager()).ifPresent(new e(21));
    }

    private void onPageTransformed(int i2, float f) {
        for (EffectHolder updateByTransform : this.mEffectHolders.values()) {
            updateByTransform.updateByTransform(i2, f);
        }
    }

    private void updateColor() {
        ViewPager2 viewPager2;
        if (!this.mView.isDestroyed() && getTransitoryCatView() != null && (viewPager2 = (ViewPager2) getTransitoryCatView().findViewById(R.id.transitory_viewpager)) != null) {
            onPageSelected(viewPager2.getCurrentItem(), (MediaItem) null);
        }
    }

    public void adjustAlphaByPosition() {
        View transitoryCatView = getTransitoryCatView();
        if (transitoryCatView == null) {
            return;
        }
        if (transitoryCatView.getHeight() > 0) {
            lambda$adjustAlphaByPosition$0(transitoryCatView);
        } else {
            transitoryCatView.post(new c(1, this, transitoryCatView));
        }
    }

    public void destroy() {
        setEnable(false);
        Optional.ofNullable(this.mList).ifPresent(new Qb.c(28, this));
    }

    public boolean handleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        if (internalEvent == InternalEvent.TRANSITORY_TRANSFORM_PAGE) {
            onPageTransformed(objArr[0].intValue(), objArr[1].floatValue());
            return true;
        } else if (internalEvent != InternalEvent.TRANSITORY_PAGE_SELECTED) {
            return false;
        } else {
            onPageSelected(objArr[0].intValue(), objArr[1]);
            return true;
        }
    }

    public void initView(View view) {
        this.mEffectDimenRes.init(view.getContext());
        GalleryListView listView = this.mView.getListView();
        this.mList = listView;
        listView.addOnScrollListener(this.mScrollListener);
        bindEffectView(view);
        setEnable(true);
    }

    public void onDataChanged() {
        updateColor();
    }

    public void refreshView(View view) {
        bindEffectView(view);
    }

    public void setEnable(boolean z) {
        for (EffectHolder effectHolder : this.mEffectHolders.values()) {
            ViewUtils.setVisibleOrInvisible(effectHolder.parent, z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EffectItem {
        final EffectDimenRes effectDimenRes;
        int key;
        int position = -1;
        View view;

        public EffectItem(View view2, EffectDimenRes effectDimenRes2) {
            this.view = view2;
            this.effectDimenRes = effectDimenRes2;
        }

        private void bindOnDemandColor(int i2, MediaItem mediaItem) {
            View view2;
            if (isValid(i2, mediaItem) && (view2 = this.view) != null) {
                view2.setBackground(createMultiColorDrawable(ResourceUtil.BG_ON_DEMAND_TOP_GRADIENT, new float[]{0.25f, 0.5f, 0.75f, 1.0f}));
            }
        }

        private Drawable createDrawable(final int i2) {
            PaintDrawable paintDrawable = new PaintDrawable();
            paintDrawable.setShape(new RectShape());
            paintDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
                public Shader resize(int i2, int i7) {
                    int i8 = i2;
                    return new LinearGradient(0.0f, 0.0f, 0.0f, (float) i7, new int[]{i8, i8, 0}, new float[]{0.0f, EffectItem.this.effectDimenRes.gradientStart, 1.0f}, Shader.TileMode.MIRROR);
                }
            });
            return paintDrawable;
        }

        private Drawable createMultiColorDrawable(final int[] iArr, final float[] fArr) {
            PaintDrawable paintDrawable = new PaintDrawable();
            paintDrawable.setShape(new RectShape());
            paintDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
                public Shader resize(int i2, int i7) {
                    return new LinearGradient(0.0f, 0.0f, 0.0f, (float) i7, iArr, fArr, Shader.TileMode.MIRROR);
                }
            });
            return paintDrawable;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bindColor$0(int i2, MediaItem mediaItem, int i7) {
            bindColor(i2, mediaItem, Integer.valueOf(i7));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bindColor$1(int i2, MediaItem mediaItem) {
            if (isValid(i2, mediaItem)) {
                long currentTimeMillis = System.currentTimeMillis();
                Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND);
                if (loadThumbnailSync != null) {
                    int orientation = mediaItem.getOrientation();
                    EffectDimenRes effectDimenRes2 = this.effectDimenRes;
                    int createTopGradientColor = BitmapUtils.createTopGradientColor(loadThumbnailSync, orientation, 0.3f, effectDimenRes2.minSaturation, effectDimenRes2.maxSaturation, effectDimenRes2.brightness, effectDimenRes2.colorAlpha);
                    TopColorEffectHandler.sColorCache.put(Integer.valueOf(readKey(mediaItem)), Integer.valueOf(createTopGradientColor));
                    Log.d("TopColorEffectHandler", "extract color", Integer.valueOf(i2), Logger.vt(currentTimeMillis));
                    ThreadUtil.runOnUiThread(new n(this, i2, mediaItem, createTopGradientColor));
                }
            }
        }

        public void bindColor(int i2, int i7, MediaItem mediaItem) {
            if (TopColorEffectHandler.sColorCache.containsKey(Integer.valueOf(readKey(mediaItem)))) {
                bindColor(i2, mediaItem, (Integer) TopColorEffectHandler.sColorCache.get(Integer.valueOf(readKey(mediaItem))));
            } else if (!PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY || i7 != 102) {
                SimpleThreadPool.getInstance().execute(new m(this, i2, mediaItem));
            } else {
                bindOnDemandColor(i2, mediaItem);
            }
        }

        public void compareAndUpdate(int i2, int i7, MediaItem mediaItem) {
            if (mediaItem == null || this.view == null) {
                View view2 = this.view;
                if (view2 != null) {
                    view2.setBackground((Drawable) null);
                }
                setKey(-1, 0);
            } else if (!isValid(i2, mediaItem)) {
                setKey(i2, readKey(mediaItem));
                bindColor(i2, i7, mediaItem);
            }
        }

        public boolean isValid(int i2, MediaItem mediaItem) {
            if (this.position == i2 && this.key == readKey(mediaItem)) {
                return true;
            }
            return false;
        }

        public int readKey(MediaItem mediaItem) {
            return mediaItem.getThumbCacheKey().hashCode();
        }

        public void setAlpha(float f) {
            ViewUtils.setAlpha(this.view, f);
        }

        public void setKey(int i2, int i7) {
            this.position = i2;
            this.key = i7;
        }

        private void bindColor(int i2, MediaItem mediaItem, Integer num) {
            View view2;
            if (isValid(i2, mediaItem) && (view2 = this.view) != null && num != null) {
                view2.setBackground(createDrawable(num.intValue()));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: adjustAlphaByPosition */
    public void lambda$adjustAlphaByPosition$0(View view) {
        float f;
        if (view.getHeight() > 0) {
            if (!view.isAttachedToWindow() || (getDataCount() <= 0 && !OnDemandStory.getInstance().isEnabled())) {
                f = 0.0f;
            } else {
                f = intersect(ViewUtils.getViewRect(view), ViewUtils.getViewRect(this.mList)).height() / ((float) view.getHeight());
            }
            for (EffectHolder effectHolder : this.mEffectHolders.values()) {
                ViewUtils.setAlpha(effectHolder.parent, f);
            }
        }
    }
}
