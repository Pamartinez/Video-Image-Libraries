package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import Fb.h;
import I5.a;
import I5.b;
import I5.c;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import k2.d;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaceClusterMergeView extends SuggesterView {
    private BottomNavigationView mBottomNavigationView;
    private final AtomicInteger mCheckIndex = new AtomicInteger(0);
    private boolean mIsRtl;
    private ImageView mMainImageView;
    private MergeInfo mMainMergeInfo;
    private List<Pair<String, MediaItem>> mMergeList;
    private final q mNavigationMenuItemListener = new h(22, this);
    private TextView mProcessCountView;
    private ImageView mSuggestionImageView;
    private MergeInfo mSuggetsionMergeInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MergeInfo {
        ArrayList<String> excludedIds = new ArrayList<>();
        ArrayList<String> groupIds = new ArrayList<>();
        final MediaItem mediaItem;

        public MergeInfo(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
        }
    }

    public FaceClusterMergeView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    private void bindImage(MediaItem mediaItem, ImageView imageView, Bitmap bitmap) {
        if (mediaItem != null) {
            if (bitmap == null) {
                mediaItem.setBroken(true);
                bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(imageView.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
            }
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private boolean findMainAndSuggestionItem() {
        if (isLastItem()) {
            return false;
        }
        Pair pair = this.mMergeList.get(this.mCheckIndex.get());
        MediaItem loadMainItem = loadMainItem((String) pair.first);
        MediaItem mediaItem = (MediaItem) pair.second;
        if (loadMainItem == null || mediaItem == null) {
            this.mMergeList.remove(pair);
            return findMainAndSuggestionItem();
        }
        this.mMainMergeInfo = new MergeInfo(loadMainItem);
        this.mSuggetsionMergeInfo = new MergeInfo(mediaItem);
        return true;
    }

    private String getProcessCountWithMax(int i2, int i7) {
        if (this.mIsRtl) {
            return String.format(Locale.getDefault(), "%d/%d", new Object[]{Integer.valueOf(i7), Integer.valueOf(i2)});
        }
        return String.format(Locale.getDefault(), "%d/%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)});
    }

    private void handleNextItem() {
        ThreadUtil.postOnUiThread(new a(this, 2));
    }

    private void initBottomNavigation() {
        this.mBottomNavigationView.setItemIconTintList((ColorStateList) null);
        ViewUtils.setVisibility(this.mBottomNavigationView, 0);
        setCustomNavigationItemView(R.id.menu_search_creature_different, R.string.face_cluster_merge_button_different, R.color.face_merge_different_icon_color);
        setCustomNavigationItemView(R.id.menu_search_creature_same, R.string.face_cluster_merge_button_same, R.color.face_merge_same_icon_color);
        setCustomNavigationItemView(R.id.menu_search_creature_not_sure, R.string.face_cluster_merge_button_not_sure, -1);
        ViewUtils.setHeight(this.mBottomNavigationView, this.mSuggesterLayout.getContext().getResources().getDimensionPixelOffset(R.dimen.custom_bottom_navigation_view_height));
        this.mBottomNavigationView.setOnItemSelectedListener(this.mNavigationMenuItemListener);
    }

    private boolean isAvailable() {
        if (!this.mIsEnabled || this.mMainMergeInfo == null || this.mSuggetsionMergeInfo == null) {
            return false;
        }
        return true;
    }

    private boolean isLastItem() {
        int size = this.mMergeList.size();
        if (this.mCheckIndex.get() >= size || size == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleNextItem$9() {
        this.mCheckIndex.incrementAndGet();
        if (isLastItem()) {
            hide();
            return;
        }
        updateProcessCountView(this.mCheckIndex.get());
        prepareItems();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$5(Long l) {
        if (!this.mMainMergeInfo.excludedIds.contains(l)) {
            this.mMainMergeInfo.excludedIds.add(Long.toString(l.longValue()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6() {
        PeopleDataManager.getGroupIds(this.mSuggetsionMergeInfo.mediaItem.getSubCategory()).forEach(new b(this, 1));
        PeopleDataManager.updateFaceClusterExcludedSuggestion(this.mMainMergeInfo.mediaItem.getSubCategory(), this.mMainMergeInfo.excludedIds);
        handleNextItem();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$7(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_search_creature_different:
                if (!InputBlock.getInstance().set(this.TAG)) {
                    return true;
                }
                if (isAvailable()) {
                    postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_NO);
                    SimpleThreadPool.getInstance().execute(new a(this, 0));
                    return true;
                }
                handleNextItem();
                return true;
            case R.id.menu_search_creature_not_sure:
                if (!InputBlock.getInstance().set(this.TAG)) {
                    return true;
                }
                handleNextItem();
                return true;
            case R.id.menu_search_creature_same:
                if (!InputBlock.getInstance().set(this.TAG)) {
                    return true;
                }
                if (isAvailable()) {
                    postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_YES);
                    new MergeCreatureMultipleCmd().addExecuteListener(new b(this, 0)).execute(this.mEventContext, this.mSuggetsionMergeInfo.mediaItem, this.mMainMergeInfo.mediaItem);
                    return true;
                }
                handleNextItem();
                return true;
            default:
                return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareItems$0() {
        updateProcessCountView(this.mCheckIndex.get());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareItems$1() {
        hide();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareItems$2() {
        if (findMainAndSuggestionItem()) {
            setItem(this.mMainMergeInfo, this.mMainImageView, true);
            setItem(this.mSuggetsionMergeInfo, this.mSuggestionImageView, false);
            ThreadUtil.postOnUiThread(new a(this, 4));
            return;
        }
        ThreadUtil.postOnUiThread(new a(this, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomNavigationItemView$8(int i2, int i7, int i8, MenuItem menuItem) {
        d dVar = (d) this.mBottomNavigationView.findViewById(i2);
        if (dVar != null) {
            ViewUtils.removeAllViews(dVar);
            View inflate = View.inflate(this.mSuggesterLayout.getContext(), R.layout.custom_bottom_navigation_view_button, (ViewGroup) null);
            String string = this.mSuggesterLayout.getContext().getString(i7);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.btn_icon);
            if (i2 == R.id.menu_search_creature_not_sure) {
                ViewUtils.setVisibleOrGone(imageView, false);
            } else {
                Drawable icon = menuItem.getIcon();
                icon.setTint(this.mSuggesterLayout.getContext().getColor(i8));
                imageView.setImageDrawable(icon);
                ViewUtils.setVisibleOrGone(imageView, true);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.btn_title);
            textView.setText(string);
            textView.setContentDescription(string);
            dVar.addView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setItem$3(MergeInfo mergeInfo, ImageView imageView, Bitmap bitmap) {
        bindImage(mergeInfo.mediaItem, imageView, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setItem$4(ImageView imageView, MergeInfo mergeInfo, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(imageView, new b(this, mergeInfo, imageView, bitmap));
    }

    private MediaItem loadMainItem(String str) {
        ArrayList<MediaItem> loadItemsFromCache = PeopleDataManager.loadItemsFromCache(new ArrayList(List.of(str)));
        if (loadItemsFromCache.isEmpty()) {
            loadItemsFromCache = PeopleDataManager.loadItems(new ArrayList(List.of(str)));
        }
        if (!loadItemsFromCache.isEmpty()) {
            return loadItemsFromCache.get(0);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onCompleteMerge(Object obj) {
        if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
            Log.d(this.TAG, "merge cancel on dialog");
        } else {
            handleNextItem();
        }
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString(), analyticsEventId.toString());
    }

    private void prepareItems() {
        SimpleThreadPool.getInstance().execute(new a(this, 3));
    }

    private void setCustomNavigationItemView(int i2, int i7, int i8) {
        Optional.ofNullable(this.mBottomNavigationView.getMenu().findItem(i2)).ifPresent(new c(this, i2, i7, i8, 0));
    }

    private void setItem(MergeInfo mergeInfo, ImageView imageView, boolean z) {
        if (z) {
            PeopleDataManager.getFaceClusterData(mergeInfo.mediaItem.getSubCategory(), mergeInfo.groupIds, mergeInfo.excludedIds);
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = mergeInfo.mediaItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        String subCategory = mediaItem.getSubCategory();
        Objects.requireNonNull(subCategory);
        instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new a(this, imageView, mergeInfo));
    }

    private void updateProcessCountView(int i2) {
        ViewUtils.setText(this.mProcessCountView, getProcessCountWithMax(i2 + 1, this.mMergeList.size()));
    }

    public void bind() {
        Context context = this.mEventContext.getContext();
        if (context != null) {
            this.mIsRtl = context.getResources().getBoolean(R.bool.is_right_to_left);
            if (this.mSuggesterLayout == null) {
                LinearLayout linearLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.face_merge_suggestion_layout);
                this.mSuggesterLayout = linearLayout;
                SimpleAnimator.setAlphaVisible(linearLayout, 200);
                ViewUtils.setViewShape((LinearLayout) this.mSuggesterLayout.findViewById(R.id.main_thumbnail_layout), 0, 0.0f);
                this.mMainImageView = (ImageView) this.mSuggesterLayout.findViewById(R.id.main_thumbnail);
                ViewUtils.setViewShape((LinearLayout) this.mSuggesterLayout.findViewById(R.id.suggestion_thumbnail_layout), 0, 0.0f);
                ImageView imageView = (ImageView) this.mSuggesterLayout.findViewById(R.id.suggestion_thumbnail);
                this.mSuggestionImageView = imageView;
                ViewUtils.setViewShape(imageView, 0, 0.0f);
                prepareItems();
                ViewUtils.postDelayed(this.mSuggestionView.getEmptyView(), new a(this, 1), 50);
                this.mBottomNavigationView = (BottomNavigationView) this.mSuggesterLayout.findViewById(R.id.edit_bottom_navigation);
                initBottomNavigation();
                ViewUtils.setBackgroundResource(this.mSuggesterLayout.findViewById(R.id.tip_card_layout), R.color.search_tag_me_tip_card_background);
                LinearLayout linearLayout2 = this.mSuggesterLayout;
                int i2 = this.mBackgroundColor;
                if (i2 == -1) {
                    i2 = R.color.default_fw_background;
                }
                ViewUtils.setBackgroundResource(linearLayout2, i2);
                this.mProcessCountView = (TextView) this.mSuggesterLayout.findViewById(R.id.process_count);
            }
        }
    }

    public void setData(Object obj) {
        this.mMergeList = (List) obj;
    }
}
