package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import I5.c;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureNameCmd;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.effects.CardShapeDrawable;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import l6.a;
import u4.C0518a;
import v7.w;
import w4.C0533c;
import x6.f;
import x7.l;
import z5.d;
import z5.e;
import z5.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCreatureHeader2View extends SearchCountHeaderView {
    private final AppBarLayout mAppbar;
    private final ICreatureContactDelegate mContactDelegate;
    private ImageView mContactDetailIcon;
    private View mContactDetailView;
    private BottomNavigationView mContactRecommendBottomNavi;
    private View mContactRecommendCardView;
    private ImageView mContactRecommendThumb;
    private TextView mContactRecommendTitle;
    private final EventContext mEventContext;
    private final FloatingToolbarLayout mFloatingToolbarLayout;
    private boolean mIsEnabled;
    private final boolean mIsPickMode;
    private View.OnLayoutChangeListener mNameCardLayoutChangeListener;
    private TextView mNameCardSubtitle;
    private ImageView mNameCardThumbnail;
    private TextView mNameCardTitle;
    private View mNameCardView;
    private RecommendContactDelegate mRecommendContactDelegate;
    private SearchCreatureSlideshow2 mSlideshow;

    public SearchCreatureHeader2View(ViewGroup viewGroup, ISearchPicturesView iSearchPicturesView) {
        super(viewGroup);
        EventContext eventContext = iSearchPicturesView.getEventContext();
        this.mEventContext = eventContext;
        this.mContactDelegate = iSearchPicturesView.getContactDelegate();
        this.mIsPickMode = PickerUtil.isPickerMode(eventContext.getBlackboard());
        this.mAppbar = iSearchPicturesView.getAppbarLayout();
        this.mFloatingToolbarLayout = (FloatingToolbarLayout) ViewUtils.findParentViewById(iSearchPicturesView.getToolbar(), R.id.sesl_floating_toolbar_layout);
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

    /* access modifiers changed from: private */
    public void bindMainImage(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(this.mNameCardThumbnail, new e(this, bitmap, 1));
    }

    private View.OnLayoutChangeListener getNameCardLayoutChangeListener(View view, Resources resources, int i2) {
        if (this.mNameCardLayoutChangeListener == null) {
            this.mNameCardLayoutChangeListener = new g(this, view, resources, i2);
        }
        return this.mNameCardLayoutChangeListener;
    }

    private void initContactRecommendBottomNavi() {
        this.mContactRecommendBottomNavi.setItemIconTintList((ColorStateList) null);
        ViewUtils.setVisibility(this.mContactRecommendBottomNavi, 0);
        setCustomNavigationItemView(R.id.menu_search_creature_no, R.string.no, R.color.face_merge_different_icon_color);
        setCustomNavigationItemView(R.id.menu_search_creature_yes, R.string.yes, R.color.face_merge_same_icon_color);
        ViewUtils.setHeight(this.mContactRecommendBottomNavi, getContext().getResources().getDimensionPixelOffset(R.dimen.custom_bottom_navigation_view_height));
        this.mContactRecommendBottomNavi.setOnItemSelectedListener(new d(this));
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
        ThumbnailLoader.getInstance().loadThumbnail(creatureNameData.getMediaItem(), ThumbKind.SMALL_NC_KIND, new a(13, this, creatureNameData));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindMainImage$11(Bitmap bitmap) {
        bindImage(this.mMediaItem, this.mNameCardThumbnail, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0() {
        Optional.ofNullable(this.mFloatingToolbarLayout).ifPresent(new w(29));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getNameCardLayoutChangeListener$1(View view, Resources resources, int i2, View view2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15) {
        View findViewById = view.findViewById(R.id.creature_slideshow_name_card_background);
        if (findViewById != null) {
            ViewUtils.setHeight(findViewById, this.mNameCardView.getMeasuredHeight());
            findViewById.setBackground(new CardShapeDrawable.Builder(resources.getColor(R.color.search_creature_slideshow_name_card_color, (Resources.Theme) null), (float) i2).build());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initContactRecommendBottomNavi$13(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_search_creature_no) {
            onClickRecommendContactNegative();
            return true;
        } else if (itemId != R.id.menu_search_creature_yes) {
            return true;
        } else {
            onClickRecommendContactPositive();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initContactRecommendThumbnail$6(Bitmap bitmap) {
        ViewUtils.setVisibleOrGone(this.mContactRecommendThumb, true);
        this.mContactRecommendThumb.setImageBitmap(bitmap);
        this.mContactRecommendThumb.setClipToOutline(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initContactRecommendThumbnail$7(CreatureNameData creatureNameData, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        creatureNameData.setFaceBitmap(bitmap);
        ThreadUtil.postOnUiThread(new e(this, bitmap, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadContactIcon$2(boolean z, AtomicReference atomicReference) {
        ViewUtils.setVisibleOrGone(this.mContactDetailView, z);
        if (atomicReference.get() != null) {
            this.mContactDetailIcon.setImageDrawable((Drawable) atomicReference.get());
            setEnableContactDetail(this.mIsEnabled);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadContactIcon$3(CreatureData creatureData) {
        if (getContext() != null) {
            boolean isLinked = PersonalLinkCore.getInstance().isLinked(getContext(), creatureData.creatureUuid);
            AtomicReference atomicReference = new AtomicReference();
            if (isLinked) {
                try {
                    atomicReference.set(getContext().getPackageManager().getApplicationIcon("com.samsung.android.app.contacts"));
                } catch (Exception e) {
                    A.a.s(e, new StringBuilder("Failed to load contact icon. e="), "SearchCreatureHeader2View");
                }
            }
            ThreadUtil.postOnUiThread(new B8.g((Object) this, isLinked, (Object) atomicReference, 20));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadContactRecommendData$4(CreatureNameData creatureNameData) {
        if (creatureNameData != null) {
            updateRecommendedContactHeader(creatureNameData);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomNavigationItemView$12(int i2, int i7, int i8, MenuItem menuItem) {
        k2.d dVar = (k2.d) this.mContactRecommendBottomNavi.findViewById(i2);
        if (dVar != null) {
            ViewUtils.removeAllViews(dVar);
            View inflate = View.inflate(getContext(), R.layout.custom_bottom_navigation_view_button, (ViewGroup) null);
            int color = getContext().getColor(i7);
            String string = getContext().getString(i8);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.btn_icon);
            Drawable icon = menuItem.getIcon();
            if (icon != null) {
                icon.setTint(color);
                imageView.setImageDrawable(icon);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.btn_title);
            textView.setText(string);
            textView.setContentDescription(string);
            textView.setTextColor(color);
            dVar.addView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnableContactDetail$8(View view) {
        launchContactDetail();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderItemClickListener$9(OnHeaderClickListener onHeaderClickListener, View view) {
        onHeaderClickListener.onHeaderClicked(this.mNameCardView, -1, (MediaItem) null, (Bitmap) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbarAsFloating$14(boolean z) {
        if (z) {
            if (!this.mAppbar.seslCanChangeToHideState()) {
                this.mAppbar.seslSetAllowStateToHide(true, true);
            }
            this.mAppbar.seslSetHide(false);
            this.mAppbar.seslAllowStartNestedScroll(false);
            return;
        }
        this.mAppbar.seslSetExpanded(true);
        this.mAppbar.seslAllowStartNestedScroll(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateRecommendedContactHeader$5(String str, CreatureNameData creatureNameData) {
        ViewUtils.setVisibility(this.mContactRecommendCardView, 0);
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RELATIONSHIP_CONTACT_RECOMMENDED_TIP_CARD_POPUP);
        if (str != null) {
            TextViewUtils.highlightKeyword(getContext(), this.mContactRecommendTitle, (int) R.string.recommend_similar_contact_with_relationship, creatureNameData.getName(), str);
        } else {
            TextViewUtils.highlightKeyword(getContext(), this.mContactRecommendTitle, (int) R.string.recommend_similar_contact, creatureNameData.getName());
        }
        initContactRecommendThumbnail(creatureNameData);
        initContactRecommendBottomNavi();
        updateContactRecommendCardViewWidth();
        setEnableContactRecommendationCard(this.mIsEnabled);
    }

    private void launchContactDetail() {
        ICreatureContactDelegate iCreatureContactDelegate = this.mContactDelegate;
        if (iCreatureContactDelegate != null) {
            iCreatureContactDelegate.launchContactDetail();
        }
    }

    private void loadContactIcon(CreatureData creatureData) {
        if (this.mContactDelegate == null || !PersonalLinkCore.getInstance().isSupported(getContext())) {
            ViewUtils.setVisibility(this.mContactDetailView, 8);
        } else {
            SimpleThreadPool.getInstance().execute(new f(8, this, creatureData));
        }
    }

    private void loadContactRecommendData() {
        if (Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT) && !this.mIsPickMode && this.mMediaItem.isPeople()) {
            RecommendContactDelegate recommendContactDelegate = new RecommendContactDelegate(this.mEventContext, this.mMediaItem);
            this.mRecommendContactDelegate = recommendContactDelegate;
            recommendContactDelegate.setDataLoadListener(new a(this, 0));
            if (this.mRecommendContactDelegate.isNeedToCheckRecommendContact()) {
                this.mRecommendContactDelegate.loadRecommendContact();
            }
        }
    }

    private void loadNameCardData() {
        String str;
        String str2;
        boolean z;
        CreatureData of2 = CreatureData.of(this.mMediaItem);
        boolean isEmpty = TextUtils.isEmpty(of2.creatureName);
        boolean isEmpty2 = TextUtils.isEmpty(of2.relationshipType);
        TextView textView = this.mNameCardTitle;
        if (!isEmpty) {
            str = of2.creatureName;
        } else {
            str = getContext().getString(R.string.add_name);
        }
        textView.setText(str);
        TextView textView2 = this.mNameCardSubtitle;
        if (!isEmpty2) {
            str2 = RelationshipKeySet.getRelationshipName(getContext(), of2.relationshipType);
        } else {
            str2 = getContext().getString(R.string.names_help_you_find_people_in_searches);
        }
        textView2.setText(str2);
        TextView textView3 = this.mNameCardSubtitle;
        if (!isEmpty2 || isEmpty) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(textView3, z);
        loadContactIcon(of2);
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        MediaItem mediaItem = this.mMediaItem;
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        String subCategory = mediaItem.getSubCategory();
        Objects.requireNonNull(subCategory);
        instance.getOrLoad(mediaItem, thumbKind, new M8.a(subCategory, 9), new d(this));
    }

    private void onClickRecommendContactNegative() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RECOMMEND_CONTACT_NO);
        resetRecommendedContact();
        this.mRecommendContactDelegate.onCancel();
    }

    private void onClickRecommendContactPositive() {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_RECOMMEND_CONTACT_YES);
        CreatureNameData personNameData = this.mRecommendContactDelegate.getPersonNameData();
        if (personNameData != null) {
            String name = personNameData.getName();
            String relationship = personNameData.getRelationship();
            CreatureData of2 = CreatureData.of(this.mMediaItem);
            of2.creatureName = name;
            of2.relationshipType = relationship;
            this.mNameCardTitle.setText(name);
            if (!TextUtils.isEmpty(relationship)) {
                ViewUtils.setText(this.mNameCardSubtitle, RelationshipKeySet.getRelationshipName(getContext(), relationship));
            }
            resetRecommendedContact();
            this.mRecommendContactDelegate.onSave(BitmapUtils.getBitmapFromDrawable(this.mNameCardThumbnail.getDrawable()));
        }
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString(), analyticsEventId.toString());
    }

    private void resetRecommendedContact() {
        ViewUtils.setVisibility(this.mContactRecommendCardView, 8);
    }

    private void setBottomNavigationEnabledWithAlpha(boolean z, float f) {
        this.mContactRecommendBottomNavi.setEnabled(z);
        this.mContactRecommendBottomNavi.setAlpha(f);
        setEnableBottomMenuItem(R.id.menu_search_creature_no, z);
        setEnableBottomMenuItem(R.id.menu_search_creature_yes, z);
    }

    private void setCustomNavigationItemView(int i2, int i7, int i8) {
        Optional.ofNullable(this.mContactRecommendBottomNavi.getMenu().findItem(i2)).ifPresent(new c(this, i2, i8, i7, 1));
    }

    private void setEnableBottomMenuItem(int i2, boolean z) {
        MenuItem findItem = this.mContactRecommendBottomNavi.getMenu().findItem(i2);
        if (findItem != null) {
            findItem.setEnabled(z);
        }
    }

    private void setEnableContactDetail(boolean z) {
        z5.f fVar;
        float f;
        if (ViewUtils.isVisible(this.mContactDetailView)) {
            ImageView imageView = this.mContactDetailIcon;
            if (z) {
                fVar = new z5.f(0, this);
            } else {
                fVar = null;
            }
            imageView.setOnClickListener(fVar);
            ImageView imageView2 = this.mContactDetailIcon;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.5f;
            }
            ViewUtils.setAlpha(imageView2, f);
        }
    }

    private void setEnableContactRecommendationCard(boolean z) {
        float f;
        if (ViewUtils.isVisible(this.mContactRecommendCardView)) {
            if (z) {
                f = 1.0f;
            } else {
                f = 0.5f;
            }
            setBottomNavigationEnabledWithAlpha(z, f);
        }
    }

    private void setSlideshowEnabled(boolean z, float f) {
        if (z) {
            this.mSlideshow.onResume();
        } else {
            this.mSlideshow.onPause();
        }
        this.mSlideshow.setAlpha(f);
    }

    private void setToolbarAsFloating(boolean z) {
        AppBarLayout appBarLayout = this.mAppbar;
        if (appBarLayout != null) {
            appBarLayout.post(new y7.e(this, z, 1));
        }
    }

    private void updateContactRecommendCardViewWidth() {
        if (ViewUtils.isVisible(this.mContactRecommendCardView)) {
            try {
                if (!ResourceCompat.isLandscape(this.mView) || this.mEventContext.getActivity().isInMultiWindowMode()) {
                    ViewGroup.LayoutParams layoutParams = this.mContactRecommendCardView.getLayoutParams();
                    layoutParams.width = -1;
                    this.mContactRecommendCardView.setLayoutParams(layoutParams);
                    return;
                }
                ViewUtils.setWidth(this.mContactRecommendCardView, (int) (((double) ResourceCompat.getWindowWidth(getContext())) * 0.55d));
            } catch (NullPointerException e) {
                Log.se("SearchCreatureHeader2View", "updateContactRecommendTipCardWidth failed: " + e);
            }
        }
    }

    private void updateRecommendedContactHeader(CreatureNameData creatureNameData) {
        ThreadUtil.postOnUiThread(new C0533c(this, RelationshipKeySet.getRelationshipName(getContext(), creatureNameData.getRelationship()), creatureNameData, 5));
    }

    public void bindData(MediaData... mediaDataArr) {
        this.mSlideshow.bindData(mediaDataArr[0], PickerUtil.isPickerMode(this.mEventContext.getBlackboard()));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSlideshow = new SearchCreatureSlideshow2(view, new l(10, this));
        this.mNameCardView = view.findViewById(R.id.creature_slideshow_name_card_view);
        Resources resources = view.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.search_creature_slideshow_name_card_radius);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.search_creature_slideshow_name_card_stroke_width);
        this.mNameCardView.setBackground(new CardShapeDrawable.Builder(0, (float) dimensionPixelSize).withGradientStroke(resources.getColor(R.color.search_creature_slideshow_name_card_stroke_color, (Resources.Theme) null), (float) dimensionPixelSize2).build());
        this.mNameCardView.addOnLayoutChangeListener(getNameCardLayoutChangeListener(view, resources, dimensionPixelSize));
        ImageView imageView = (ImageView) view.findViewById(R.id.creature_slideshow_name_card_thumbnail);
        this.mNameCardThumbnail = imageView;
        ViewUtils.setViewShape(imageView, 0, 0.0f);
        this.mNameCardTitle = (TextView) view.findViewById(R.id.creature_slideshow_name_card_title);
        this.mNameCardSubtitle = (TextView) view.findViewById(R.id.creature_slideshow_name_card_subtitle);
        View findViewById = view.findViewById(R.id.creature_slideshow_contact_recommend_card_view);
        this.mContactRecommendCardView = findViewById;
        ViewUtils.setViewShape(findViewById, 1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_contact_recommend_tip_card_radius));
        this.mContactRecommendTitle = (TextView) view.findViewById(R.id.creature_slideshow_contact_recommend_card_title);
        this.mContactRecommendThumb = (ImageView) view.findViewById(R.id.creature_slideshow_contact_recommend_card_thumbnail);
        this.mContactRecommendBottomNavi = (BottomNavigationView) view.findViewById(R.id.creature_slideshow_contact_recommend_card_bottom_navigation);
        this.mContactDetailView = view.findViewById(R.id.contact_detail_view);
        this.mContactDetailIcon = (ImageView) view.findViewById(R.id.contact_detail_icon);
        setToolbarAsFloating(true);
    }

    public int getLayoutId() {
        return R.layout.recycler_item_search_pictures_header_creature_slideshow2;
    }

    public void handleOrientationChange(int i2) {
        Log.s("SearchCreatureHeader2View", "handleOrientationChange {" + i2 + "}");
        if (ViewUtils.isVisible(this.mContactRecommendCardView)) {
            updateContactRecommendCardViewWidth();
        }
        setToolbarAsFloating(this.mSlideshow.getVisibility());
    }

    public void handleResolutionChanged() {
        this.mSlideshow.handleResolutionChanged();
    }

    public void initHeaderItem() {
        if (this.mMediaItem != null) {
            loadNameCardData();
            loadContactRecommendData();
        }
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (ViewUtils.isTouchedOnView(this.mItemCount, motionEvent)) {
            return true;
        }
        if (ViewUtils.isTouchedOnView(this.mNameCardView, motionEvent) || ViewUtils.isTouchedOnView(this.mContactRecommendBottomNavi, motionEvent)) {
            return false;
        }
        return true;
    }

    public void onDestroyView() {
        this.mSlideshow.onDestroyView();
    }

    public boolean onHeaderItemClicked() {
        EditCreatureNameCmd editCreatureNameCmd = new EditCreatureNameCmd();
        EventContext eventContext = this.mEventContext;
        editCreatureNameCmd.execute(eventContext, eventContext.getLocationKey(), this.mMediaItem);
        return true;
    }

    public void onPause() {
        this.mSlideshow.onPause();
    }

    public void onRequestPermissionResult(int i2) {
        RecommendContactDelegate recommendContactDelegate = this.mRecommendContactDelegate;
        if (recommendContactDelegate != null && i2 == 119) {
            recommendContactDelegate.onRequestPermissionResult();
        }
    }

    public void onResume() {
        this.mSlideshow.onResume();
        setToolbarAsFloating(this.mSlideshow.getVisibility());
    }

    public void recycle() {
        super.recycle();
        View view = this.mNameCardView;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.mNameCardLayoutChangeListener);
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
        this.mNameCardView.setEnabled(z);
        setSlideshowEnabled(z, f);
        this.mNameCardTitle.setAlpha(f);
        this.mNameCardSubtitle.setAlpha(f);
        setEnableContactRecommendationCard(z);
        setEnableContactDetail(z);
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(mediaItem);
        this.mMediaItem = cloneCreatureItem;
        if (cloneCreatureItem != null && cloneCreatureItem.getSubCategory() != null) {
            return true;
        }
        Log.se("SearchCreatureHeader2View", "Couldn't setHeaderItem");
        return false;
    }

    public void setHeaderItemClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mNameCardView.setOnClickListener(new C0518a(5, this, onHeaderClickListener));
        this.mSlideshow.setOnClickListener(onHeaderClickListener);
    }

    public void showCountHeaderOnly(boolean z) {
        setToolbarAsFloating(!z);
        ViewUtils.setVisibleOrGone(this.mNameCardView, !z);
        this.mSlideshow.setVisibility(!z);
        if (ViewUtils.isVisible(this.mContactRecommendCardView) && z) {
            ViewUtils.setVisibleOrGone(this.mContactRecommendCardView, false);
        }
    }

    public boolean supportSlideShow() {
        return true;
    }
}
