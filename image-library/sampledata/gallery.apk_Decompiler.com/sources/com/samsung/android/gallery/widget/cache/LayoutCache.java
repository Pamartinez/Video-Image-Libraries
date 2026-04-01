package com.samsung.android.gallery.widget.cache;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import c0.C0086a;
import com.google.android.material.tabs.c;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import ob.C0704a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LayoutCache {
    private static final boolean IS_TABLET = Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    private static final LayoutCache sInstance = new LayoutCache();
    private int mBottomTabLayoutId;
    private final ConcurrentLinkedQueue<View> mCachedDrawerItemView = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<View> mCachedItemView = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<Integer, View> mCachedLayout = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, CountDownLatch> mCachedLock = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, View> mCommonCache = new ConcurrentHashMap<>();
    private int mContentTypeHolderLayoutId;
    private int mDividerLayoutId;
    private int mDrawerItemLayoutId;
    private String mFirstLocationKey;
    private GridHelper mGridHelper;
    private int mListLayoutId;
    private int mSmartAlbumLayoutId;
    private final Pools$Pool<c> mTabPool = new Pools$SynchronizedPool(4);
    private int mViewHolderCount;
    private int mViewHolderLayoutId;

    private LayoutCache() {
    }

    private int getColumnSize(Context context) {
        if (context == null) {
            return 1;
        }
        try {
            GridHelper gridHelper = getGridHelper();
            return gridHelper.getColumnSize(context, gridHelper.getGridDepth());
        } catch (Exception e) {
            a.s(e, new StringBuilder("getListViewInfo failed e="), "LayoutCache");
            return 1;
        }
    }

    private String getFirstLocationKey() {
        String currentLocation = LocationKey.getCurrentLocation();
        if ("location://mtp".equals(currentLocation)) {
            return "location://timeline";
        }
        if (!Features.isEnabled(Features.IS_UPSM) || (!"location://story/albums".equals(currentLocation) && !"location://sharing/albums".equals(currentLocation))) {
            return currentLocation;
        }
        return "location://timeline";
    }

    private GridHelper getGridHelper() {
        if (this.mGridHelper == null) {
            this.mGridHelper = GridHelper.getInstance(this.mFirstLocationKey);
        }
        return this.mGridHelper;
    }

    public static LayoutCache getInstance() {
        return sInstance;
    }

    private void inflate(Activity activity) {
        ThreadPool.getInstance().submit(new C0704a(this, activity, 0));
        ThreadPool.getInstance().submit(new C0704a(this, activity, 1));
    }

    private int inflateContentTypeView(LayoutInflater layoutInflater) {
        if (this.mContentTypeHolderLayoutId <= 0) {
            return 0;
        }
        ViewPoolCache.getInstance().openContentTypeViewPool();
        for (int i2 = 0; i2 < 10; i2++) {
            ViewPoolCache.getInstance().cacheContentTypeView(layoutInflater.inflate(this.mContentTypeHolderLayoutId, (ViewGroup) null, false));
        }
        return 10;
    }

    private int inflateDividerView(LayoutInflater layoutInflater) {
        int i2 = this.mDividerLayoutId;
        if (i2 <= 0) {
            return 0;
        }
        View inflate = layoutInflater.inflate(i2, (ViewGroup) null, false);
        inflate.setTag(-100, "c");
        putView(this.mDividerLayoutId, inflate);
        return 1;
    }

    private int inflateDrawerItemView(LayoutInflater layoutInflater) {
        for (int i2 = 0; i2 < 11; i2++) {
            View inflate = layoutInflater.inflate(this.mDrawerItemLayoutId, (ViewGroup) null, false);
            inflate.setTag(-100, "c");
            this.mCachedDrawerItemView.add(inflate);
        }
        return this.mCachedDrawerItemView.size();
    }

    private View inflateLayout(LayoutInflater layoutInflater, int i2) {
        Trace.beginSection("inflateLayout#" + i2);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mCachedLock.put(Integer.valueOf(i2), countDownLatch);
        View inflate = layoutInflater.inflate(i2, (ViewGroup) null, false);
        this.mCachedLayout.put(Integer.valueOf(i2), inflate);
        countDownLatch.countDown();
        Trace.endSection();
        return inflate;
    }

    private int inflateListItem(LayoutInflater layoutInflater) {
        int i2 = 0;
        while (true) {
            int i7 = this.mViewHolderCount;
            if (i2 >= i7) {
                return i7;
            }
            View inflate = layoutInflater.inflate(this.mViewHolderLayoutId, (ViewGroup) null, false);
            inflate.setTag(-100, "c");
            this.mCachedItemView.add(inflate);
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$inflate$0(Activity activity, ThreadPool.JobContext jobContext) {
        int i2;
        BottomTabLayout bottomTabLayout;
        try {
            Trace.beginSection("LayoutCache#inflate#1");
            TimeTickLog timeTickLog = new TimeTickLog();
            LayoutInflater cloneInContext = LayoutInflater.from(activity).cloneInContext(activity);
            int i7 = 0;
            if (this.mDrawerItemLayoutId > 0) {
                Trace.beginSection("inflateDrawerItemView");
                i2 = inflateDrawerItemView(cloneInContext);
                Trace.endSection();
                timeTickLog.tick();
            } else {
                i2 = 0;
            }
            int i8 = this.mBottomTabLayoutId;
            if (i8 > 0) {
                View inflateLayout = inflateLayout(cloneInContext, i8);
                if (inflateLayout == null) {
                    bottomTabLayout = null;
                } else {
                    bottomTabLayout = (BottomTabLayout) inflateLayout.findViewById(R$id.tab_layout);
                }
                if (bottomTabLayout != null) {
                    for (int i10 = 0; i10 < 4; i10++) {
                        Trace.beginSection("newTab#" + i10);
                        this.mTabPool.release(bottomTabLayout.newTab());
                        Trace.endSection();
                    }
                }
                timeTickLog.tick();
            }
            int inflateListItem = inflateListItem(cloneInContext);
            timeTickLog.tick();
            int inflateContentTypeView = inflateContentTypeView(cloneInContext);
            timeTickLog.tick();
            int inflateDividerView = inflateDividerView(cloneInContext);
            timeTickLog.tick();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("inflate#1");
            if (this.mBottomTabLayoutId > 0) {
                i7 = 1;
            }
            sb2.append(Logger.vt(Integer.valueOf(i7), Integer.valueOf(i2), Integer.valueOf(inflateListItem), Integer.valueOf(inflateContentTypeView), Integer.valueOf(inflateDividerView), timeTickLog));
            Log.p("LayoutCache", sb2.toString());
        } catch (Exception e) {
            Log.e((CharSequence) "LayoutCache", "inflate#1 failed", (Throwable) e);
        } catch (Throwable th) {
            Throwable th2 = th;
            Trace.endSection();
            throw th2;
        }
        Trace.endSection();
        if (activity.isDestroyed()) {
            close();
            ViewPoolCache.releaseInstance();
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$inflate$1(Activity activity, ThreadPool.JobContext jobContext) {
        try {
            Trace.beginSection("LayoutCache#inflate#2");
            TimeTickLog timeTickLog = new TimeTickLog();
            LayoutInflater cloneInContext = LayoutInflater.from(activity).cloneInContext(activity);
            View inflateLayout = inflateLayout(cloneInContext, this.mListLayoutId);
            timeTickLog.tick();
            int i2 = this.mSmartAlbumLayoutId;
            int i7 = 0;
            if (i2 > 0) {
                ViewStub viewStub = (ViewStub) inflateLayout.findViewById(i2);
                if (viewStub != null) {
                    int layoutResource = viewStub.getLayoutResource();
                    putView(layoutResource, cloneInContext.inflate(layoutResource, (ViewGroup) null, false));
                }
                timeTickLog.tick();
            }
            StringBuilder sb2 = new StringBuilder("inflate#2");
            if (this.mSmartAlbumLayoutId > 0) {
                i7 = 1;
            }
            sb2.append(Logger.vt(1, Integer.valueOf(i7), timeTickLog));
            Log.p("LayoutCache", sb2.toString());
        } catch (Exception e) {
            Log.e((CharSequence) "LayoutCache", "inflate#2 failed", (Throwable) e);
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        Trace.endSection();
        if (activity.isDestroyed()) {
            close();
            ViewPoolCache.releaseInstance();
        }
        return Boolean.TRUE;
    }

    public void close() {
        this.mCachedLayout.clear();
        this.mCachedLock.clear();
        this.mCachedItemView.clear();
        this.mCachedDrawerItemView.clear();
        this.mCommonCache.clear();
        ViewPoolCache.getInstance().closeContentTypeViewPool();
        do {
        } while (this.mTabPool.acquire() != null);
    }

    public View getCachedDrawerItemView(int i2) {
        if (i2 != this.mDrawerItemLayoutId) {
            return null;
        }
        try {
            return this.mCachedDrawerItemView.poll();
        } catch (Exception unused) {
            Log.d("LayoutCache", "empty view holders");
            return null;
        }
    }

    public View getCachedItemView(int i2) {
        if (i2 != this.mViewHolderLayoutId) {
            return null;
        }
        try {
            return this.mCachedItemView.poll();
        } catch (Exception unused) {
            Log.d("LayoutCache", "empty view holders");
            return null;
        }
    }

    public int getCachedItemViewCount(int i2) {
        if (i2 == this.mViewHolderLayoutId) {
            return this.mCachedItemView.size();
        }
        return 0;
    }

    public View getCachedLayout(int i2) {
        boolean z;
        View remove = this.mCachedLayout.remove(Integer.valueOf(i2));
        if (remove == null) {
            CountDownLatch countDownLatch = this.mCachedLock.get(Integer.valueOf(i2));
            if (countDownLatch != null) {
                try {
                    countDownLatch.await(30, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                remove = this.mCachedLayout.remove(Integer.valueOf(i2));
                StringBuilder o2 = C0086a.o(i2, "getCachedLayout {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (remove != null) {
                    z = true;
                } else {
                    z = false;
                }
                o2.append(z);
                o2.append("} loading delayed");
                Log.d("LayoutCache", o2.toString());
            }
            return remove;
        }
        this.mCachedLock.remove(Integer.valueOf(i2));
        return remove;
    }

    public c getCachedTab() {
        return this.mTabPool.acquire();
    }

    public View getView(int i2) {
        return this.mCommonCache.remove(Integer.valueOf(i2));
    }

    public boolean hasView(int i2) {
        return this.mCommonCache.containsKey(Integer.valueOf(i2));
    }

    public void open(Activity activity) {
        int i2;
        int i7;
        int i8;
        close();
        String firstLocationKey = getFirstLocationKey();
        this.mFirstLocationKey = firstLocationKey;
        int i10 = 0;
        this.mBottomTabLayoutId = 0;
        this.mSmartAlbumLayoutId = 0;
        this.mDividerLayoutId = 0;
        boolean z = IS_TABLET;
        if (z) {
            this.mDrawerItemLayoutId = R$layout.recycler_item_drawer_tab_layout;
        } else {
            this.mBottomTabLayoutId = R$layout.fragment_bottom_tab_container;
        }
        if ("location://albums".equals(firstLocationKey)) {
            this.mListLayoutId = R$layout.fragment_albums_layout;
            int columnSize = getColumnSize(activity);
            if (columnSize == 1) {
                if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    i8 = R$layout.recycler_item_albums_image_list_layout;
                } else if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                    i8 = R$layout.recycler_item_mx_blur_albums_image_list_layout;
                } else {
                    i8 = R$layout.recycler_item_mx_albums_image_list_layout;
                }
                this.mViewHolderLayoutId = i8;
                this.mViewHolderCount = 8;
            } else {
                if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    i2 = R$layout.recycler_item_albums_image_grid_layout;
                } else if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                    i2 = R$layout.recycler_item_mx_blur_albums_image_grid_layout;
                } else {
                    i2 = R$layout.recycler_item_mx_albums_image_grid_layout;
                }
                this.mViewHolderLayoutId = i2;
                if (z) {
                    i7 = (columnSize + 1) * columnSize;
                } else {
                    i7 = columnSize * columnSize;
                }
                this.mViewHolderCount = i7;
            }
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS && (!z || DrawerUtil.supportEssentialAlbumsLayout((Context) activity))) {
                this.mDividerLayoutId = R$layout.recycler_item_mx_albums_first_divider_layout;
            }
        } else if (LocationKey.isTimeline(this.mFirstLocationKey)) {
            this.mListLayoutId = R$layout.fragment_timeline_layout;
            if (ViewPoolCache.getInstance().getViewPoolCount() <= 0) {
                i10 = 20;
            }
            this.mViewHolderCount = i10;
            if (!GridHelper.getTimeline().isMonthForViewing()) {
                this.mViewHolderLayoutId = R$layout.recycler_item_pictures_previewable_image_layout;
            } else if (PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL) {
                this.mViewHolderLayoutId = R$layout.recycler_item_pictures_simple_image_layout;
            } else {
                this.mViewHolderLayoutId = R$layout.recycler_item_pictures_month_for_viewing_image_layout;
            }
            this.mContentTypeHolderLayoutId = R$layout.content_type_container;
        } else if ("location://story/albums".equals(this.mFirstLocationKey)) {
            this.mViewHolderCount = 4;
            if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
                this.mListLayoutId = R$layout.fragment_stories_pinch_layout;
                this.mViewHolderLayoutId = R$layout.recycler_item_stories_pinch_image_layout;
            } else if (PreferenceFeatures.OneUi30.MEMORIES) {
                this.mListLayoutId = R$layout.fragment_stories_layout;
                this.mViewHolderLayoutId = R$layout.recycler_item_stories_image_layout;
            } else {
                this.mListLayoutId = R$layout.fragment_stories_layout_legacy;
                this.mViewHolderLayoutId = R$layout.recycler_item_stories_image_layout_legacy;
            }
        } else if ("location://sharing/albums".equals(this.mFirstLocationKey)) {
            this.mListLayoutId = R$layout.fragment_sharings_layout;
            this.mViewHolderCount = 0;
        } else if ("location://search".equals(this.mFirstLocationKey)) {
            this.mListLayoutId = R$layout.fragment_search_layout;
        }
        inflate(activity);
    }

    public void putView(int i2, View view) {
        this.mCommonCache.put(Integer.valueOf(i2), view);
    }
}
