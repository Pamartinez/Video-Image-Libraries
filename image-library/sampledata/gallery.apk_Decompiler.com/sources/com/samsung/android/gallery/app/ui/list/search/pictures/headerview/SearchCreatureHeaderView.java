package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import H7.x;
import I5.c;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameCmd;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation.AnimationParam;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation.FaceClusterAnimationHelper;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;
import k2.d;
import l6.a;
import u4.C0518a;
import w4.C0533c;
import z5.h;
import z5.i;
import z5.j;
import z5.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCreatureHeaderView extends SearchCountHeaderView {
    private static final boolean SUPPORT_SLIDESHOW = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
    private BottomNavigationView mBottomNavigationView;
    private MediaItem mCompareHeaderItem;
    private View mContactRecommendContainer;
    private ImageView mContactRecommendThumb;
    private TextView mContactRecommendTitle;
    private CreatureType mCreatureType = CreatureType.NORMAL;
    private RelativeLayout mCurImageViewLayout;
    private ImageView mEditArrow;
    private TextView mEditTitle;
    private TextView mEditTitleDescription;
    private LinearLayout mEditTitleLayout;
    private final EventContext mEventContext;
    private FaceClusterAnimationHelper mFaceClusterAnimationHelper;
    private FaceClusterMergeDelegate mFaceClusterMergeDelegate;
    private final FaceClusterMergeDelegate.MergeListener mFaceClusterMergeListener = new FaceClusterMergeDelegate.MergeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onCompletedMerge$0(MediaItem mediaItem, Bitmap bitmap) {
            SearchCreatureHeaderView.this.updateViewOnMergeCompleted(mediaItem, bitmap);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onMerged$1() {
            SearchCreatureHeaderView.this.setFaceClusterMergeView(true);
        }

        public void onCompletedMerge(MediaItem mediaItem, Bitmap bitmap, boolean z) {
            if (mediaItem != null) {
                Log.s("SearchCreatureHeaderView", "onCompletedMerge: confirmed = " + z);
                if (z) {
                    SearchCreatureHeaderView.this.getFaceClusterAnimationHelper().startMergeAnimation(new AnimationParam.AnimationParamBuilder().setAnimImagePair(new ImageView[]{SearchCreatureHeaderView.this.mImageView, SearchCreatureHeaderView.this.mPrevImageView}).setFlipImageView(new ImageView[]{SearchCreatureHeaderView.this.mImageViewForFlipPrev, SearchCreatureHeaderView.this.mImageViewForFlipNext}).setMergedFaceBitmap(bitmap).build(), new c(this, mediaItem, bitmap));
                } else {
                    SearchCreatureHeaderView.this.updateViewOnMergeCompleted(mediaItem, bitmap);
                }
            }
        }

        public void onLoadCompleted(boolean z, boolean z3) {
            String str;
            if (z3) {
                if (z) {
                    str = " [TipCard]";
                } else {
                    str = "";
                }
                Log.s("SearchCreatureHeaderView", "FaceClusterMerge : onLoadCompleted()".concat(str));
                SearchCreatureHeaderView.this.updateFaceClusterHeader(z);
            } else if (z && SearchCreatureHeaderView.this.isNeedToCheckRecommendContact()) {
                SearchCreatureHeaderView.this.getRecommendContactDelegate().loadRecommendContact();
            }
        }

        public void onMerged() {
            ThreadUtil.postOnUiThread(new d(this));
        }
    };
    private TextView mFaceClusterMergeTitle;
    private ImageView mFaceClusterTipCardCloseButton;
    /* access modifiers changed from: private */
    public LinearLayout mFaceClusterTipCardHeader;
    private ImageView mFaceClusterTipCardTargetImageView;
    private TextView mFaceClusterTipCardTitle;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    /* access modifiers changed from: private */
    public ImageView mImageViewForFlipNext;
    /* access modifiers changed from: private */
    public ImageView mImageViewForFlipPrev;
    private LinearLayout mImageViewLayout;
    private boolean mIsEnabled;
    private final boolean mIsPickMode;
    /* access modifiers changed from: private */
    public ImageView mPrevImageView;
    private LinearLayout mPrevImageViewLayout;
    private RecommendContactDelegate mRecommendContactDelegate;
    private TextView mRelationshipTypeText;
    private SearchCreatureSlideshow mSlideshow;
    private View mSubTouchView;
    private TextView mTitleText;
    private View mTouchView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CreatureType {
        NORMAL,
        RECOMMENDED_CONTACT,
        FACE_CLUSTER_TIP_CARD,
        FACE_CLUSTER_MERGE
    }

    public SearchCreatureHeaderView(ViewGroup viewGroup, EventContext eventContext) {
        super(viewGroup);
        this.mEventContext = eventContext;
        this.mIsPickMode = PickerUtil.isPickerMode(eventContext.getBlackboard());
        initImage();
        if (PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER) {
            initPrevImage();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImage */
    public void lambda$bindImage$18(MediaItem mediaItem, ImageView imageView, Bitmap bitmap) {
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

    /* access modifiers changed from: private */
    public void bindMainImage(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(this.mImageView, new k(this, bitmap, 1));
    }

    private boolean checkLastFaceClusterRecommendedTime() {
        if (PreferenceCache.SearchFaceClusterLastRecommendTime.getLong() < TimeUtil.getDaysAgo(7)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public FaceClusterAnimationHelper getFaceClusterAnimationHelper() {
        if (this.mFaceClusterAnimationHelper == null) {
            this.mFaceClusterAnimationHelper = new FaceClusterAnimationHelper();
        }
        return this.mFaceClusterAnimationHelper;
    }

    /* access modifiers changed from: private */
    public RecommendContactDelegate getRecommendContactDelegate() {
        if (this.mRecommendContactDelegate == null) {
            RecommendContactDelegate recommendContactDelegate = new RecommendContactDelegate(this.mEventContext, this.mMediaItem);
            this.mRecommendContactDelegate = recommendContactDelegate;
            recommendContactDelegate.setDataLoadListener(new a(this, 1));
        }
        return this.mRecommendContactDelegate;
    }

    private void hideFaceClusterMergeHeader() {
        ViewUtils.setVisibility(this.mPrevImageViewLayout, 8);
        ViewUtils.setVisibility(this.mFaceClusterMergeTitle, 8);
        ViewUtils.setVisibility(this.mBottomNavigationView, 8);
        this.mTouchView.setClickable(true);
        updateTitleLayout(false);
    }

    private void hideFaceClusterTipCardView() {
        ViewUtils.setVisibility(this.mFaceClusterTipCardHeader, 8);
    }

    private void initBottomNavigation(boolean z) {
        int i2;
        int i7;
        this.mBottomNavigationView.setItemIconTintList((ColorStateList) null);
        if (!z) {
            ViewUtils.setVisibility(this.mBottomNavigationView, 0);
        }
        if (isFaceClusterMergeHeaderType()) {
            i2 = R.string.face_cluster_merge_button_different;
        } else {
            i2 = R.string.no;
        }
        if (isFaceClusterMergeHeaderType()) {
            i7 = R.string.face_cluster_merge_button_same;
        } else {
            i7 = R.string.yes;
        }
        setCustomNavigationItemView(R.id.menu_search_creature_no, i2, R.color.face_merge_different_icon_color);
        setCustomNavigationItemView(R.id.menu_search_creature_yes, i7, R.color.face_merge_same_icon_color);
        ViewUtils.setHeight(this.mBottomNavigationView, getContext().getResources().getDimensionPixelOffset(R.dimen.custom_bottom_navigation_view_height));
        this.mBottomNavigationView.setOnItemSelectedListener(new h(this, 0));
    }

    private void initContactRecommendThumbnail(CreatureNameData creatureNameData) {
        if (this.mContactRecommendThumb == null) {
            return;
        }
        if (creatureNameData.getFaceBitmap() != null || !creatureNameData.hasValidPhoto()) {
            Drawable contactPresetDrawable = creatureNameData.getContactPresetDrawable(getContext());
            if (contactPresetDrawable != null) {
                ViewUtils.setVisibleOrGone(this.mContactRecommendThumb, true);
                this.mContactRecommendThumb.setImageDrawable(contactPresetDrawable);
                this.mContactRecommendThumb.setClipToOutline(true);
                return;
            }
            ViewUtils.setVisibleOrGone(this.mContactRecommendThumb, false);
            return;
        }
        ThumbnailLoader.getInstance().loadThumbnail(creatureNameData.getMediaItem(), ThumbKind.SMALL_NC_KIND, new a(14, this, creatureNameData));
    }

    private void initImage() {
        ViewUtils.setViewShape(this.mCurImageViewLayout, 0, 0.0f);
        ViewUtils.setViewShape(this.mImageView, 0, 0.0f);
        ViewUtils.setViewShape(this.mImageViewForFlipPrev, 0, 0.0f);
        ViewUtils.setViewShape(this.mImageViewForFlipNext, 0, 0.0f);
    }

    private void initPrevImage() {
        ViewUtils.setViewShape(this.mPrevImageViewLayout, 0, 0.0f);
        ViewUtils.setViewShape(this.mPrevImageView, 0, 0.0f);
        ViewUtils.setViewShape(this.mFaceClusterTipCardTargetImageView, 0, 0.0f);
    }

    private boolean isFaceClusterTipCardHeaderType() {
        if (this.mCreatureType == CreatureType.FACE_CLUSTER_TIP_CARD) {
            return true;
        }
        return false;
    }

    private boolean isNeedToCheckFaceCluster() {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER || PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "people_from_visual_search", false) || !checkLastFaceClusterRecommendedTime()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isNeedToCheckRecommendContact() {
        return getRecommendContactDelegate().isNeedToCheckRecommendContact();
    }

    private boolean isRecommendContactHeaderType() {
        if (this.mCreatureType == CreatureType.RECOMMENDED_CONTACT) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$16() {
        this.mFaceClusterTipCardTargetImageView.setForeground((Drawable) null);
        ViewUtils.setVisibility(this.mFaceClusterTipCardTargetImageView, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$17(MediaItem mediaItem, Bitmap bitmap) {
        lambda$bindImage$18(mediaItem, this.mImageViewForFlipPrev, bitmap);
        getFaceClusterAnimationHelper().startRepeatAnimation(this.mPrevImageViewLayout, this.mCurImageViewLayout);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindMainImage$15(Bitmap bitmap) {
        ViewUtils.setVisibility(this.mImageViewLayout, 0);
        lambda$bindImage$18(this.mMediaItem, this.mImageView, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getRecommendContactDelegate$0(CreatureNameData creatureNameData) {
        if (creatureNameData != null) {
            updateRecommendedContactHeader(creatureNameData);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initBottomNavigation$20(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.menu_search_creature_no) {
            if (itemId != R.id.menu_search_creature_yes) {
                return true;
            }
            if (isFaceClusterMergeHeaderType()) {
                onClickFaceClusterMergePositive();
                return true;
            }
            onClickRecommendContactPositive();
            return true;
        } else if (isFaceClusterMergeHeaderType()) {
            onClickFaceClusterMergeNegative();
            return true;
        } else {
            onClickRecommendContactNegative();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initContactRecommendThumbnail$2(Bitmap bitmap) {
        ViewUtils.setVisibleOrGone(this.mContactRecommendThumb, true);
        this.mContactRecommendThumb.setImageBitmap(bitmap);
        this.mContactRecommendThumb.setClipToOutline(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initContactRecommendThumbnail$3(CreatureNameData creatureNameData, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        creatureNameData.setFaceBitmap(bitmap);
        ThreadUtil.postOnUiThread(new k(this, bitmap, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomNavigationItemView$19(int i2, int i7, int i8, MenuItem menuItem) {
        d dVar = (d) this.mBottomNavigationView.findViewById(i2);
        if (dVar != null) {
            ViewUtils.removeAllViews(dVar);
            View inflate = View.inflate(getContext(), R.layout.custom_bottom_navigation_view_button, (ViewGroup) null);
            int color = getContext().getColor(i7);
            String string = getContext().getString(i8);
            Drawable icon = menuItem.getIcon();
            icon.setTint(color);
            ((ImageView) inflate.findViewById(R.id.btn_icon)).setImageDrawable(icon);
            TextView textView = (TextView) inflate.findViewById(R.id.btn_title);
            textView.setText(string);
            textView.setContentDescription(string);
            textView.setTextColor(color);
            dVar.addView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderItemClickListener$12(OnHeaderClickListener onHeaderClickListener, View view) {
        onHeaderClickListener.onHeaderClicked(this.mTouchView, -1, (MediaItem) null, (Bitmap) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startEnterFaceClusterMergeAnimation$10() {
        getFaceClusterMergeDelegate().openFaceClusterMergeData();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$4(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_TIP_CARD_CLICK);
        this.mFaceClusterTipCardHeader.setClickable(false);
        this.mCreatureType = CreatureType.FACE_CLUSTER_MERGE;
        startEnterFaceClusterMergeAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$5(View view) {
        PreferenceCache.SearchFaceClusterLastRecommendTime.setLong(System.currentTimeMillis());
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_TIP_CARD_CLOSE);
        setFaceClusterTipCardView(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$6() {
        this.mFaceClusterTipCardHeader.setOnClickListener(new j(this, 0));
        this.mFaceClusterTipCardCloseButton.setOnClickListener(new j(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$7() {
        boolean z;
        if (this.mCompareHeaderItem != null) {
            z = true;
        } else {
            z = false;
        }
        setFaceClusterTipCardView(z);
        if (z) {
            lazyUpdateEnabled();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$8(Future future) {
        this.mCompareHeaderItem = (MediaItem) future.get();
        ThreadUtil.postOnUiThread(new i(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFaceClusterHeader$9() {
        setPrevImageViewOnFaceClusterMergeHeader();
        lazyUpdateEnabled();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateRecommendedContactHeader$1(String str, CreatureNameData creatureNameData) {
        ViewUtils.setVisibility(this.mEditArrow, 8);
        ViewUtils.setVisibility(this.mEditTitle, 8);
        ViewUtils.setVisibility(this.mContactRecommendContainer, 0);
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_CONTACT_RECOMMENDED_TIP_CARD_POPUP);
        if (str != null) {
            TextViewUtils.highlightKeyword(getContext(), this.mContactRecommendTitle, (int) R.string.recommend_similar_contact_with_relationship, creatureNameData.getName(), str);
        } else {
            TextViewUtils.highlightKeyword(getContext(), this.mContactRecommendTitle, (int) R.string.recommend_similar_contact, creatureNameData.getName());
        }
        initContactRecommendThumbnail(creatureNameData);
        initBottomNavigation(false);
        updateContactRecommendTipCardWidth();
        lazyUpdateEnabled();
    }

    private void lazyUpdateEnabled() {
        setEnabledForHeaderType(this.mIsEnabled);
    }

    private void onClickFaceClusterMergeNegative() {
        if (InputBlock.getInstance().set("SearchCreatureHeaderView")) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_NO);
            getFaceClusterMergeDelegate().handleMergeCancel();
        }
    }

    private void onClickFaceClusterMergePositive() {
        if (InputBlock.getInstance().set("SearchCreatureHeaderView")) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_CLUSTER_MERGE_YES);
            getFaceClusterMergeDelegate().handleMergeConfirm();
        }
    }

    private void onClickRecommendContactNegative() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RECOMMEND_CONTACT_NO);
        resetEditText();
        setTitleVisibility(this.mMediaItem);
        resetRecommendedContact();
        getRecommendContactDelegate().onCancel();
    }

    private void onClickRecommendContactPositive() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RECOMMEND_CONTACT_YES);
        resetEditText();
        CreatureNameData personNameData = getRecommendContactDelegate().getPersonNameData();
        if (personNameData != null) {
            String name = personNameData.getName();
            String relationship = personNameData.getRelationship();
            CreatureData of2 = CreatureData.of(this.mMediaItem);
            of2.creatureName = name;
            of2.relationshipType = relationship;
            setTitleVisibility(this.mMediaItem);
            this.mTitleText.setText(name);
            if (!TextUtils.isEmpty(relationship)) {
                ViewUtils.setText(this.mRelationshipTypeText, RelationshipKeySet.getRelationshipName(getContext(), relationship));
            }
            resetRecommendedContact();
            getRecommendContactDelegate().onSave(BitmapUtils.getBitmapFromDrawable(this.mImageView.getDrawable()));
        }
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString(), analyticsEventId.toString());
    }

    private void resetEditText() {
        ViewUtils.setVisibility(this.mEditTitle, 0);
        this.mEditTitle.setText(R.string.add_name);
        this.mEditTitle.setTextColor(getContext().getColor(R.color.search_people_header_text_color));
        this.mEditTitle.setBackground(getContext().getDrawable(R.drawable.search_people_tag_edit_title_shape));
    }

    private void resetRecommendedContact() {
        ViewUtils.setVisibility(this.mBottomNavigationView, 8);
        ViewUtils.setVisibility(this.mContactRecommendContainer, 8);
    }

    private void setBottomNavigationEnabledWithAlpha(boolean z, float f) {
        this.mBottomNavigationView.setEnabled(z);
        this.mBottomNavigationView.setAlpha(f);
        setEnableBottomMenuItem(R.id.menu_search_creature_no, z);
        setEnableBottomMenuItem(R.id.menu_search_creature_yes, z);
    }

    private void setCustomNavigationItemView(int i2, int i7, int i8) {
        Optional.ofNullable(this.mBottomNavigationView.getMenu().findItem(i2)).ifPresent(new c(this, i2, i8, i7, 2));
    }

    private void setEditTitleAreaAlpha(float f) {
        if (SUPPORT_SLIDESHOW) {
            ViewUtils.setAlpha(this.mEditTitle, f);
            ViewUtils.setAlpha(this.mEditTitleDescription, f);
            ViewUtils.setAlpha(this.mEditArrow, f);
            return;
        }
        ViewUtils.setAlpha(this.mEditTitleLayout, f);
    }

    private void setEditTitleAreaVisibility(int i2) {
        if (SUPPORT_SLIDESHOW) {
            ViewUtils.setVisibility(this.mEditTitle, i2);
            ViewUtils.setVisibility(this.mEditTitleDescription, i2);
            ViewUtils.setVisibility(this.mEditArrow, i2);
            if (i2 == 0) {
                ViewMarginUtils.setHorizontalPadding(this.mEditTitle, getContext().getResources().getDimensionPixelOffset(R.dimen.search_face_no_name_bubble_side_padding));
                return;
            }
            return;
        }
        ViewUtils.setVisibility(this.mEditTitleLayout, i2);
    }

    private void setEnableBottomMenuItem(int i2, boolean z) {
        MenuItem findItem = this.mBottomNavigationView.getMenu().findItem(i2);
        if (findItem != null) {
            findItem.setEnabled(z);
        }
    }

    private void setEnabledForHeaderType(boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        if (isRecommendContactHeaderType() && Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT)) {
            setBottomNavigationEnabledWithAlpha(z, f);
        } else if (isFaceClusterTipCardHeaderType()) {
            this.mFaceClusterTipCardHeader.setAlpha(f);
            this.mFaceClusterTipCardHeader.setEnabled(z);
            this.mFaceClusterTipCardCloseButton.setEnabled(z);
        } else if (isFaceClusterMergeHeaderType()) {
            this.mFaceClusterMergeTitle.setAlpha(f);
            this.mFaceClusterMergeTitle.setEnabled(z);
            setBottomNavigationEnabledWithAlpha(z, f);
        }
    }

    /* access modifiers changed from: private */
    public void setFaceClusterMergeView(boolean z) {
        if (z) {
            setPrevImageViewOnFaceClusterMergeHeader();
            showFaceClusterMergeHeader();
            return;
        }
        hideFaceClusterMergeHeader();
    }

    private void setFaceClusterTipCardView(boolean z) {
        if (z) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            MediaItem mediaItem = this.mCompareHeaderItem;
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            String subCategory = mediaItem.getSubCategory();
            Objects.requireNonNull(subCategory);
            instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new h(this, 4));
            showFaceClusterTipCardView();
            return;
        }
        hideFaceClusterTipCardView();
    }

    private void setPrevImageViewOnFaceClusterMergeHeader() {
        this.mCompareHeaderItem = getFaceClusterMergeDelegate().getLastMergedItem();
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = this.mCompareHeaderItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        String subCategory = mediaItem.getSubCategory();
        Objects.requireNonNull(subCategory);
        instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new h(this, 4));
    }

    private void setSlideshowEnabled(boolean z, float f) {
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            if (z) {
                searchCreatureSlideshow.onResume();
            } else {
                searchCreatureSlideshow.onPause();
            }
            this.mSlideshow.setAlpha(f);
        }
    }

    private void setTitleVisibility(MediaItem mediaItem) {
        boolean z;
        boolean z3;
        int i2;
        int i7;
        int i8 = 0;
        if (mediaItem != null) {
            CreatureData of2 = CreatureData.of(mediaItem);
            z3 = !TextUtils.isEmpty(of2.creatureName);
            z = !TextUtils.isEmpty(of2.relationshipType);
        } else {
            z = false;
            z3 = false;
        }
        TextView textView = this.mTitleText;
        if (z3) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(textView, i2);
        TextView textView2 = this.mRelationshipTypeText;
        if (!z3 || !z) {
            i7 = 8;
        } else {
            i7 = 0;
        }
        ViewUtils.setVisibility(textView2, i7);
        if (z3 || this.mCreatureType == CreatureType.FACE_CLUSTER_MERGE) {
            i8 = 8;
        }
        setEditTitleAreaVisibility(i8);
    }

    private void showFaceClusterMergeHeader() {
        showFaceClusterMergeHeader(false);
    }

    private void showFaceClusterTipCardView() {
        setTitleVisibility(this.mMediaItem);
        updateTitleLayout(CreatureData.hasName(this.mMediaItem));
        this.mImageView.setVisibility(0);
        this.mFaceClusterTipCardHeader.setAlpha(1.0f);
        TextView textView = this.mFaceClusterTipCardTitle;
        textView.setText(getContext().getString(R.string.face_cluster_same_or_different_person) + "\n" + getContext().getString(R.string.face_cluster_improve_your_search_results));
        ViewUtils.setVisibility(this.mFaceClusterTipCardHeader, 0);
        this.mFaceClusterTipCardHeader.setClickable(true);
        updateFaceClusterTipCardWidth();
    }

    private void startEnterFaceClusterMergeAnimation() {
        CreatureData creatureData;
        getFaceClusterAnimationHelper().startEnterFaceClusterMergeAnimation(getContext(), new AnimationParam.AnimationParamBuilder().setHeaderView(this.mView).setTipCardThumbView(new ImageView[]{this.mFaceClusterTipCardTargetImageView, this.mImageView}).setMainImageView(new ImageView[]{this.mImageView, this.mPrevImageView}).build(), new h(this, 1));
        getFaceClusterAnimationHelper().startTipCardAlphaAnimation(this.mFaceClusterTipCardHeader, new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                SearchCreatureHeaderView.this.showFaceClusterMergeHeader(true);
                ViewUtils.setVisibility(SearchCreatureHeaderView.this.mFaceClusterTipCardHeader, 8);
            }
        });
        MediaItem mediaItem = this.mCompareHeaderItem;
        if (mediaItem == null) {
            creatureData = null;
        } else {
            creatureData = CreatureData.of(mediaItem);
        }
        if (creatureData == null || TextUtils.isEmpty(creatureData.creatureName)) {
            ViewUtils.setVisibility(this.mTitleText, 8);
            ViewUtils.setVisibility(this.mRelationshipTypeText, 8);
        } else {
            if (TextUtils.isEmpty(this.mTitleText.getText())) {
                this.mTitleText.setText(creatureData.creatureName);
            }
            int i2 = 4;
            ViewUtils.setVisibility(this.mTitleText, 4);
            TextView textView = this.mRelationshipTypeText;
            if (TextUtils.isEmpty(creatureData.relationshipType)) {
                i2 = 8;
            }
            ViewUtils.setVisibility(textView, i2);
        }
        setEditTitleAreaVisibility(8);
    }

    private void updateContactRecommendTipCardWidth() {
        if (isRecommendContactHeaderType() && ViewUtils.isVisible(this.mSubTouchView)) {
            try {
                if (!ResourceCompat.isLandscape(this.mView) || this.mEventContext.getActivity().isInMultiWindowMode()) {
                    ViewGroup.LayoutParams layoutParams = this.mSubTouchView.getLayoutParams();
                    layoutParams.width = -1;
                    this.mSubTouchView.setLayoutParams(layoutParams);
                    return;
                }
                ViewUtils.setWidth(this.mSubTouchView, (int) (((double) ResourceCompat.getWindowWidth(getContext())) * 0.55d));
            } catch (NullPointerException e) {
                Log.se("SearchCreatureHeaderView", "updateContactRecommendTipCardWidth failed: " + e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateFaceClusterHeader(boolean z) {
        CreatureType creatureType;
        if (z) {
            creatureType = CreatureType.FACE_CLUSTER_TIP_CARD;
        } else {
            creatureType = CreatureType.FACE_CLUSTER_MERGE;
        }
        this.mCreatureType = creatureType;
        Log.s("SearchCreatureHeaderView", "updateFaceClusterHeader : " + this.mCreatureType);
        if (this.mCreatureType == CreatureType.FACE_CLUSTER_TIP_CARD) {
            ThreadUtil.postOnUiThread(new i(this, 2));
            getFaceClusterMergeDelegate().loadClusterItem(new h(this, 3));
            return;
        }
        ThreadUtil.postOnUiThread(new i(this, 3));
    }

    private void updateFaceClusterTipCardWidth() {
        if (ViewUtils.isVisible(this.mFaceClusterTipCardHeader)) {
            try {
                if (!ResourceCompat.isLandscape(this.mView) || this.mEventContext.getActivity().isInMultiWindowMode()) {
                    ViewGroup.LayoutParams layoutParams = this.mFaceClusterTipCardHeader.getLayoutParams();
                    layoutParams.width = -1;
                    this.mFaceClusterTipCardHeader.setLayoutParams(layoutParams);
                    return;
                }
                ViewUtils.setWidth(this.mFaceClusterTipCardHeader, (int) (((double) ResourceCompat.getWindowWidth(getContext())) * 0.55d));
            } catch (NullPointerException e) {
                Log.se("SearchCreatureHeaderView", "updateFaceClusterTipCardWidth failed: " + e);
            }
        }
    }

    private void updateRecommendedContactHeader(CreatureNameData creatureNameData) {
        this.mCreatureType = CreatureType.RECOMMENDED_CONTACT;
        ThreadUtil.postOnUiThread(new C0533c(this, RelationshipKeySet.getRelationshipName(getContext(), creatureNameData.getRelationship()), creatureNameData, 6));
    }

    private void updateTitleLayout(boolean z) {
        int i2;
        if (!SUPPORT_SLIDESHOW) {
            if (z) {
                i2 = this.mEditTitleLayout.getResources().getDimensionPixelSize(R.dimen.creature_title_margin_start);
            } else {
                i2 = 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mEditTitleLayout.getLayoutParams();
            marginLayoutParams.setMarginStart(i2);
            this.mEditTitleLayout.setLayoutParams(marginLayoutParams);
        }
    }

    /* access modifiers changed from: private */
    public void updateViewOnMergeCompleted(MediaItem mediaItem, Bitmap bitmap) {
        CreatureData of2 = CreatureData.of(mediaItem);
        String subCategory = mediaItem.getSubCategory();
        String str = of2.creatureName;
        String str2 = of2.relationshipType;
        Log.s("SearchCreatureHeaderView", "updateViewOnMergeCompleted: id = " + subCategory);
        setFaceClusterMergeView(false);
        if (!TextUtils.isEmpty(subCategory)) {
            setTitleVisibility(mediaItem);
            this.mTitleText.setText(str);
            if (str2 != null) {
                ViewUtils.setText(this.mRelationshipTypeText, RelationshipKeySet.getRelationshipName(getContext(), str2));
            }
            if (bitmap != null) {
                this.mImageView.setImageBitmap(bitmap);
            }
        }
        this.mFaceClusterMergeDelegate.reopenData(subCategory, str);
    }

    public void bindData(MediaData... mediaDataArr) {
        super.bindData(mediaDataArr);
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.bindData(mediaDataArr[0], PickerUtil.isPickerMode(this.mEventContext.getBlackboard()));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTouchView = view.findViewById(R.id.touchable_view);
        this.mSubTouchView = view.findViewById(R.id.sub_touchable_view);
        this.mImageView = (ImageView) view.findViewById(R.id.creature_header_thumbnail);
        this.mImageViewForFlipPrev = (ImageView) view.findViewById(R.id.thumbnail_for_flip_prev);
        this.mImageViewForFlipNext = (ImageView) view.findViewById(R.id.thumbnail_for_flip_next);
        this.mPrevImageView = (ImageView) view.findViewById(R.id.prev_thumbnail);
        this.mTitleText = (TextView) view.findViewById(R.id.title);
        this.mEditTitle = (TextView) view.findViewById(R.id.editTitle);
        this.mEditTitleDescription = (TextView) view.findViewById(R.id.editTitleDescription);
        this.mEditArrow = (ImageView) view.findViewById(R.id.editArrow);
        this.mRelationshipTypeText = (TextView) view.findViewById(R.id.relationshipType);
        this.mBottomNavigationView = (BottomNavigationView) view.findViewById(R.id.edit_bottom_navigation);
        this.mImageViewLayout = (LinearLayout) view.findViewById(R.id.thumbnail_layout);
        this.mCurImageViewLayout = (RelativeLayout) view.findViewById(R.id.cur_thumbnail_layout);
        this.mPrevImageViewLayout = (LinearLayout) view.findViewById(R.id.prev_thumbnail_layout);
        this.mEditTitleLayout = (LinearLayout) view.findViewById(R.id.edit_title_layout);
        this.mContactRecommendTitle = (TextView) view.findViewById(R.id.contact_recommend_title);
        this.mContactRecommendThumb = (ImageView) view.findViewById(R.id.contact_recommend_thumb);
        this.mContactRecommendContainer = view.findViewById(R.id.contact_recommend_container);
        this.mFaceClusterMergeTitle = (TextView) view.findViewById(R.id.face_cluster_merge_title);
        this.mFaceClusterTipCardHeader = (LinearLayout) view.findViewById(R.id.face_cluster_tipcard_Header);
        this.mFaceClusterTipCardTitle = (TextView) view.findViewById(R.id.face_cluster_tipcard_title);
        this.mFaceClusterTipCardTargetImageView = (ImageView) view.findViewById(R.id.face_cluster_tipcard_image);
        this.mFaceClusterTipCardCloseButton = (ImageView) view.findViewById(R.id.face_cluster_tipcard_close_button);
        if (SUPPORT_SLIDESHOW) {
            this.mSlideshow = new SearchCreatureSlideshow(view);
        }
        ViewUtils.setViewShape(this.mSubTouchView, 1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_contact_recommend_tip_card_radius));
    }

    public FaceClusterMergeDelegate getFaceClusterMergeDelegate() {
        if (this.mFaceClusterMergeDelegate == null) {
            FaceClusterMergeDelegate faceClusterMergeDelegate = new FaceClusterMergeDelegate(this.mEventContext);
            this.mFaceClusterMergeDelegate = faceClusterMergeDelegate;
            faceClusterMergeDelegate.setMergeListener(this.mFaceClusterMergeListener);
        }
        return this.mFaceClusterMergeDelegate;
    }

    public int getLayoutId() {
        if (SUPPORT_SLIDESHOW) {
            return R.layout.recycler_item_search_pictures_header_creature_slideshow;
        }
        return R.layout.recycler_item_pictures_header_creature_layout;
    }

    public void handleOrientationChange(int i2) {
        Log.s("SearchCreatureHeaderView", "handleOrientationChange {" + i2 + "}");
        if (isRecommendContactHeaderType()) {
            updateContactRecommendTipCardWidth();
        } else if (isFaceClusterTipCardHeaderType()) {
            updateFaceClusterTipCardWidth();
        }
    }

    public void handleResolutionChanged() {
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.handleResolutionChanged();
        }
    }

    public void initHeaderItem() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            CreatureData of2 = CreatureData.of(mediaItem);
            this.mTitleText.setText(of2.creatureName);
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                this.mEditTitle.setContentDescription(AppResources.getString(R.string.untagged_face));
                this.mEditTitleDescription.setText(R.string.names_help_you_find_faces_faster);
            }
            this.mRelationshipTypeText.setText(RelationshipKeySet.getRelationshipName(getContext(), of2.relationshipType));
            setTitleVisibility(this.mMediaItem);
            if (PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER) {
                getFaceClusterAnimationHelper().cancelRepeatAnimation(this.mCurImageViewLayout);
            }
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            MediaItem mediaItem2 = this.mMediaItem;
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            String subCategory = mediaItem2.getSubCategory();
            Objects.requireNonNull(subCategory);
            instance.getOrLoad(mediaItem2, thumbKind, new M8.a(subCategory, 9), new h(this, 2));
            if (!this.mIsPickMode && this.mMediaItem.isPeople()) {
                if (isNeedToCheckFaceCluster()) {
                    getFaceClusterMergeDelegate().loadFaceCluster(this.mEventContext.getLocationKey(), this.mMediaItem);
                } else if (isNeedToCheckRecommendContact()) {
                    getRecommendContactDelegate().loadRecommendContact();
                } else {
                    resetRecommendedContact();
                }
            }
        }
    }

    public boolean isFaceClusterMergeHeaderType() {
        if (this.mCreatureType == CreatureType.FACE_CLUSTER_MERGE) {
            return true;
        }
        return false;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (ViewUtils.isTouchedOnView(this.mItemCount, motionEvent)) {
            return true;
        }
        if (ViewUtils.isTouchedOnView(this.mTouchView, motionEvent) || ViewUtils.isTouchedOnView(this.mBottomNavigationView, motionEvent) || ViewUtils.isTouchedOnView(this.mFaceClusterTipCardHeader, motionEvent)) {
            return false;
        }
        return true;
    }

    public boolean onBackPressed() {
        if (this.mEventContext == null || !PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER) {
            return false;
        }
        return getFaceClusterMergeDelegate().onBackPressed();
    }

    public void onDestroyView() {
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.onDestroyView();
        }
    }

    public boolean onHeaderItemClicked() {
        EditCreatureNameCmd editCreatureNameCmd = new EditCreatureNameCmd();
        EventContext eventContext = this.mEventContext;
        editCreatureNameCmd.execute(eventContext, eventContext.getLocationKey(), this.mMediaItem);
        return true;
    }

    public void onPause() {
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.onPause();
        }
    }

    public void onRequestPermissionResult(int i2) {
        if (i2 == 119) {
            getRecommendContactDelegate().onRequestPermissionResult();
        } else if (i2 == 120) {
            getFaceClusterMergeDelegate().onRequestPermissionResult();
        }
    }

    public void onResume() {
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.onResume();
        }
    }

    public void recover(SearchHeaderView searchHeaderView) {
        if (searchHeaderView instanceof SearchCreatureHeaderView) {
            SearchCreatureHeaderView searchCreatureHeaderView = (SearchCreatureHeaderView) searchHeaderView;
            if (searchCreatureHeaderView.isFaceClusterMergeHeaderType()) {
                Log.d("SearchCreatureHeaderView", "recover (FACE_CLUSTER_MERGE) : finish and re-launch");
                searchCreatureHeaderView.getFaceClusterMergeDelegate().relaunch();
                return;
            }
            return;
        }
        Log.d("SearchCreatureHeaderView", "recover (FACE_CLUSTER_MERGE) : oldHeader was not CreatureHeader");
    }

    public void recycle() {
        if (this.mEventContext != null && PreferenceFeatures.OneUi5x.SUPPORT_FACE_CLUSTER) {
            getFaceClusterMergeDelegate().release();
            getFaceClusterAnimationHelper().removeFakeAnimationView((ViewGroup) this.mView);
            getFaceClusterAnimationHelper().cancelRepeatAnimation(this.mCurImageViewLayout);
        }
    }

    public void setEnabled(boolean z) {
        float f;
        this.mIsEnabled = z;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        this.mTouchView.setEnabled(z);
        ViewUtils.childOf(this.mImageViewLayout).forEach(new x(f, 7));
        setSlideshowEnabled(z, f);
        this.mTitleText.setAlpha(f);
        setEditTitleAreaAlpha(f);
        this.mRelationshipTypeText.setAlpha(f);
        setEnabledForHeaderType(z);
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(mediaItem);
        this.mMediaItem = cloneCreatureItem;
        if (cloneCreatureItem != null && cloneCreatureItem.getSubCategory() != null) {
            return true;
        }
        Log.se("SearchCreatureHeaderView", "Couldn't setHeaderItem");
        return false;
    }

    public void setHeaderItemClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mTouchView.setOnClickListener(new C0518a(6, this, onHeaderClickListener));
        Optional.ofNullable(this.mSlideshow).ifPresent(new b(0, onHeaderClickListener));
    }

    public void showCountHeaderOnly(boolean z) {
        ViewUtils.setVisibleOrGone(this.mTouchView, !z);
        ViewUtils.setVisibleOrGone(this.mSubTouchView, !z);
        SearchCreatureSlideshow searchCreatureSlideshow = this.mSlideshow;
        if (searchCreatureSlideshow != null) {
            searchCreatureSlideshow.setVisibility(!z);
        }
    }

    public boolean supportSlideShow() {
        return SUPPORT_SLIDESHOW;
    }

    /* access modifiers changed from: private */
    public void showFaceClusterMergeHeader(boolean z) {
        updateTitleLayout(false);
        setEditTitleAreaAlpha(1.0f);
        if (CreatureData.hasName(this.mCompareHeaderItem)) {
            ViewUtils.setAlpha(this.mTitleText, 1.0f);
        }
        ViewUtils.setVisibility(this.mPrevImageViewLayout, 0);
        ViewUtils.setVisibility(this.mFaceClusterMergeTitle, z ? 8 : 0);
        initBottomNavigation(z);
        this.mTouchView.setClickable(false);
        updateTitleLayout(true);
        if (z) {
            getFaceClusterAnimationHelper().showFaceClusterMergeViewAnimation(this.mFaceClusterMergeTitle, this.mBottomNavigationView);
        }
    }

    /* access modifiers changed from: private */
    public void bindImage(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        MediaItem mediaItem;
        ImageView imageView;
        if (isFaceClusterTipCardHeaderType()) {
            mediaItem = this.mCompareHeaderItem;
            imageView = this.mFaceClusterTipCardTargetImageView;
            ThreadUtil.postOnUiThread(new i(this, 0));
        } else if (isFaceClusterMergeHeaderType()) {
            mediaItem = this.mCompareHeaderItem;
            imageView = this.mPrevImageView;
            ThreadUtil.runOnUiThread(this.mImageViewForFlipPrev, new C0533c(this, mediaItem, bitmap, 7));
        } else {
            mediaItem = this.mMediaItem;
            imageView = this.mImageView;
        }
        ImageView imageView2 = imageView;
        ThreadUtil.runOnUiThread(imageView2, new a8.d((Object) this, (Object) mediaItem, (Object) imageView2, (Object) bitmap, 27));
    }
}
