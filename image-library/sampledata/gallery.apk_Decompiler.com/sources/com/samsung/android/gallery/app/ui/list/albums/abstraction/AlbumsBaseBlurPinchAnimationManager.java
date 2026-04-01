package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A4.H;
import B8.g;
import Bb.l;
import C4.a;
import C4.b;
import C4.c;
import C4.d;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.PinchBlurImageView;
import com.samsung.android.gallery.widget.animator.BlurAnimator;
import com.samsung.android.gallery.widget.animator.GradientAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.LayoutRuleHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBaseBlurPinchAnimationManager extends AlbumsBasePinchAnimationManager {
    private static final boolean ENABLE_BLUR;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType[] r0 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType = r0
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.LIST_TO_GRID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_LIST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_GRID     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || !Features.isEnabled(Features.SUPPORT_REALTIME_BLUR)) {
            z = false;
        } else {
            z = true;
        }
        ENABLE_BLUR = z;
    }

    public AlbumsBaseBlurPinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager);
    }

    private void addBlurAnimator(ListViewHolder listViewHolder, View view, PinchItem pinchItem) {
        ViewUtils.setVisibility(this.mReferenceViewHolder.getTitleView(), 0);
        ViewUtils.setVisibility(this.mReferenceViewHolder.getCountView(), 0);
        remeasure(this.mReferenceViewHolder.getRootView());
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (ENABLE_BLUR) {
            addAnimation((PropertyAnimator) new BlurAnimator(view, getBlurImageInfo(listViewHolder, isFolder(mediaItem), pinchItem.isReverse(), isSkipBlur(mediaItem), isEmpty(mediaItem))));
            return;
        }
        addAnimation((PropertyAnimator) new GradientAnimator(view, getBlurImageInfo(listViewHolder, isFolder(mediaItem), pinchItem.isReverse(), isSkipBlur(mediaItem), isEmpty(mediaItem))));
    }

    private BlurImageInfo getBlurImageInfo(ListViewHolder listViewHolder, boolean z, boolean z3, boolean z7, boolean z9) {
        View view;
        int i2;
        AlbumsBasePinchAnimationManager.TransitionType transitionType = this.mTransitionType;
        AlbumsBasePinchAnimationManager.TransitionType transitionType2 = AlbumsBasePinchAnimationManager.TransitionType.LIST_TO_GRID;
        if (transitionType == transitionType2) {
            view = this.mReferenceViewHolder.getDecoView(29);
        } else {
            view = listViewHolder.getDecoView(29);
        }
        BlurImageInfo.Builder skip = new BlurImageInfo.Builder().setFolder(z).setGridType(this.mTransitionType.equals(AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_GRID)).setListToGrid(this.mTransitionType.equals(transitionType2)).setReverseOperation(z3).setSkip(z7);
        if (view != null) {
            i2 = view.getHeight();
        } else {
            i2 = 0;
        }
        return skip.setTargetViewHeight(i2).setUseEmptyCoverBlur(z9).build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r8.mGridInfo.isGridGettingLarger() != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float[] getTransitionRadius(boolean r9) {
        /*
            r8 = this;
            int[] r0 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager.AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType
            com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = r8.mTransitionType
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 2
            r2 = 2131165320(0x7f070088, float:1.7944854E38)
            r3 = 2131165933(0x7f0702ed, float:1.7946097E38)
            r4 = 2131165319(0x7f070087, float:1.7944852E38)
            r5 = 2131165931(0x7f0702eb, float:1.7946093E38)
            r6 = 1
            if (r0 == r6) goto L_0x004a
            if (r0 == r1) goto L_0x0043
            r2 = 3
            if (r0 != r2) goto L_0x003b
            if (r9 == 0) goto L_0x0039
            com.samsung.android.gallery.widget.listview.pinch.GridInfo r9 = r8.mGridInfo
            boolean r9 = r9.isGridGettingLarger()
            r2 = 2131165932(0x7f0702ec, float:1.7946095E38)
            if (r9 == 0) goto L_0x002e
            r4 = r2
            goto L_0x002f
        L_0x002e:
            r4 = r5
        L_0x002f:
            com.samsung.android.gallery.widget.listview.pinch.GridInfo r9 = r8.mGridInfo
            boolean r9 = r9.isGridGettingLarger()
            if (r9 == 0) goto L_0x0054
        L_0x0037:
            r2 = r5
            goto L_0x0054
        L_0x0039:
            r2 = r4
            goto L_0x0054
        L_0x003b:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            java.lang.String r9 = "No transition can't occur"
            r8.<init>(r9)
            throw r8
        L_0x0043:
            if (r9 == 0) goto L_0x0046
            r4 = r5
        L_0x0046:
            if (r9 == 0) goto L_0x0054
            r2 = r3
            goto L_0x0054
        L_0x004a:
            if (r9 == 0) goto L_0x004d
            r2 = r3
        L_0x004d:
            if (r9 == 0) goto L_0x0051
            r4 = r2
            goto L_0x0037
        L_0x0051:
            r7 = r4
            r4 = r2
            r2 = r7
        L_0x0054:
            com.samsung.android.gallery.widget.pinch.IPinchRecyclerView r9 = r8.getRecyclerView()
            android.content.res.Resources r9 = r9.getResources()
            float r9 = r9.getDimension(r4)
            com.samsung.android.gallery.widget.pinch.IPinchRecyclerView r8 = r8.getRecyclerView()
            android.content.res.Resources r8 = r8.getResources()
            float r8 = r8.getDimension(r2)
            float[] r0 = new float[r1]
            r1 = 0
            r0[r1] = r9
            r0[r6] = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager.getTransitionRadius(boolean):float[]");
    }

    private boolean isEmpty(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isEmptyAlbum()) {
            return true;
        }
        if (!mediaItem.isFolder() || mediaItem.getItemsInFolder().length != 0) {
            return false;
        }
        return true;
    }

    private boolean isFolder(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    private boolean isSkipBlur(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isVirtualAlbum()) {
            return true;
        }
        if (!mediaItem.isFolder() || mediaItem.getItemsInFolder().length <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareAlbumEmptyViewAnimation$2(View view) {
        resetScale(view);
        resetTranslate(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareFolderChildAnimation$0(ListViewHolder listViewHolder, boolean z, boolean z3, View view, int i2, int i7, View view2, View view3) {
        listViewHolder.enableBlur(z);
        if (z3) {
            ViewUtils.setWidth(view, i2);
            ViewUtils.setHeight(view, i7);
        }
        ViewUtils.setWidth(view2, -1);
        ViewUtils.setHeight(view2, -1);
        resetScale(view3);
        resetTranslate(view3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareFolderChildAnimation$1(ListViewHolder listViewHolder, boolean z, View view) {
        listViewHolder.enableBlur(false);
        if (z) {
            ViewUtils.setWidth(view, view.getWidth());
            ViewUtils.setHeight(view, view.getHeight());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareFolderEmptyViewIconAnimation$3(View view) {
        resetScale(view);
        resetTranslate(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareSubViewAnimation$4(View view, View view2) {
        ViewUtils.setWidth(view, -1);
        ViewUtils.setHeight(view, -1);
        resetScale(view2);
        resetTranslate(view2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareThumbnailBlurAnimation$5(ListViewHolder listViewHolder, View view, Drawable drawable, boolean z, PinchBlurImageView pinchBlurImageView, float f, int i2, LayoutRuleHolder layoutRuleHolder, View view2) {
        listViewHolder.enableBlur(true);
        view.setForeground(drawable);
        if (z) {
            pinchBlurImageView.setAspectRatio(f);
            ViewUtils.setWidth(pinchBlurImageView, -1);
            ViewUtils.setHeight(pinchBlurImageView, -2);
            ViewUtils.setHeight(view, i2);
            Optional.ofNullable(layoutRuleHolder).ifPresent(new l(3));
        }
        resetTranslate(view2);
        resetScale(view2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareThumbnailBlurAnimation$6(ListViewHolder listViewHolder, View view, boolean z, PinchBlurImageView pinchBlurImageView, LayoutRuleHolder layoutRuleHolder) {
        listViewHolder.enableBlur(false);
        view.setForeground((Drawable) null);
        if (z) {
            pinchBlurImageView.setAspectRatio(-1.0f);
            ViewUtils.setHeight(view, view.getHeight());
            Optional.ofNullable(layoutRuleHolder).ifPresent(new l(2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareVirtualAlbumViewAnimation$7(View view) {
        resetScale(view);
        resetTranslate(view);
    }

    private void prepareAlbumEmptyViewAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(61);
        if (ViewUtils.isVisible(decoView)) {
            View decoView2 = this.mReferenceViewHolder.getDecoView(61);
            RectF[] transitionRect = getTransitionRect(decoView, decoView2, pinchItem);
            ScaleAnimator scaleAnimator = new ScaleAnimator(decoView, transitionRect[0], transitionRect[1]);
            scaleAnimator.setAnimationListener(new a(this, 2));
            addAnimation((PropertyAnimator) scaleAnimator);
            addTranslationAnimator(decoView, decoView2, this.mReferenceViewHolder, transitionRect[1]);
        }
    }

    private void prepareFolderEmptyViewAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(62);
        if (ViewUtils.isVisible(decoView)) {
            View decoView2 = this.mReferenceViewHolder.getDecoView(62);
            PinchItem pinchItem2 = pinchItem;
            prepareSubViewAnimation(decoView.findViewById(R.id.front_layer), decoView2.findViewById(R.id.front_layer), pinchItem2, true, false);
            prepareSubViewAnimation(decoView.findViewById(R.id.back_layer), decoView2.findViewById(R.id.back_layer), pinchItem2, true, true);
        }
    }

    private void prepareFolderEmptyViewIconAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(63);
        if (ViewUtils.isVisible(decoView)) {
            RectF[] transitionRect = getTransitionRect(decoView, this.mReferenceViewHolder.getDecoView(63), pinchItem);
            ScaleAnimator scaleAnimator = new ScaleAnimator(decoView, transitionRect[0], transitionRect[1]);
            scaleAnimator.pivotX(((float) decoView.getWidth()) / 2.0f);
            scaleAnimator.pivotY(((float) decoView.getHeight()) / 2.0f);
            scaleAnimator.setAnimationListener(new a(this, 1));
            addAnimation((PropertyAnimator) scaleAnimator);
        }
    }

    private void prepareSubViewAnimation(View view, View view2, PinchItem pinchItem, boolean z, boolean z3) {
        RectF[] transitionRect = getTransitionRect(view, view2, pinchItem);
        float[] transitionRadius = getTransitionRadius(z);
        ScaleAnimator scaleAnimator = new ScaleAnimator(view, transitionRect[0], transitionRect[1]);
        scaleAnimator.enableUpdateLayoutParam(true);
        scaleAnimator.setOutlineProvider(transitionRadius[0], transitionRadius[1], new boolean[]{true, true, true, true});
        scaleAnimator.setAnimationListener(new H(2, (Object) this, (Object) view));
        if (z3) {
            scaleAnimator.enableBorder(true, getRecyclerView().getResources().getDimension(R.dimen.album_view_sub_album_border_thickness), transitionRadius[0], transitionRadius[1], getRecyclerView().getResources().getColor(R.color.album_view_sub_album_border_color, (Resources.Theme) null));
        }
        addAnimation((PropertyAnimator) scaleAnimator);
        addTranslationAnimator(view, view2, this.mReferenceViewHolder, transitionRect[1]);
    }

    private void prepareThumbnailBlurAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        boolean z;
        ListViewHolder listViewHolder2 = listViewHolder;
        PinchItem pinchItem2 = pinchItem;
        View scalableView = listViewHolder2.getScalableView();
        if (ViewUtils.isVisible(scalableView)) {
            View scalableView2 = this.mReferenceViewHolder.getScalableView();
            if (this.mTransitionType.equals(AlbumsBasePinchAnimationManager.TransitionType.LIST_TO_GRID) || this.mTransitionType.equals(AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_LIST)) {
                z = true;
            } else {
                z = false;
            }
            int color = getRecyclerView().getResources().getColor(getBorderColorResId(), (Resources.Theme) null);
            float dimension = getRecyclerView().getResources().getDimension(getBorderThicknessResId(false));
            float[] transitionRadii = getTransitionRadii(false);
            RectF[] transitionRect = getTransitionRect(scalableView, scalableView2, pinchItem2);
            LayoutRuleHolder layoutRuleHolder = null;
            PinchBlurImageView pinchBlurImageView = (PinchBlurImageView) listViewHolder2.getImage();
            float aspectRatio = pinchBlurImageView.getAspectRatio();
            View view = (View) pinchBlurImageView.getParent();
            int i2 = view.getLayoutParams().height;
            RectF[] rectFArr = transitionRect;
            Drawable foreground = view.getForeground();
            View decoView = listViewHolder2.getDecoView(28);
            if (decoView != null) {
                layoutRuleHolder = new LayoutRuleHolder(decoView);
            }
            int i7 = i2;
            ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, rectFArr[0], rectFArr[1]);
            scaleAnimator.enableBorder(true, dimension, transitionRadii[0], transitionRadii[1], color);
            scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1]);
            LayoutRuleHolder layoutRuleHolder2 = layoutRuleHolder;
            int i8 = i7;
            View view2 = view;
            LayoutRuleHolder layoutRuleHolder3 = layoutRuleHolder2;
            b bVar = new b(this, listViewHolder2, view2, foreground, z, pinchBlurImageView, aspectRatio, i8, layoutRuleHolder3);
            scaleAnimator.setAnimationListener(bVar);
            PinchBlurImageView pinchBlurImageView2 = pinchBlurImageView;
            boolean z3 = z;
            boolean z7 = z3;
            scaleAnimator.withStartAction(new c(0, listViewHolder, view2, pinchBlurImageView2, layoutRuleHolder3, z3));
            if (z7) {
                scaleAnimator.enableUpdateLayoutParam(true);
            }
            addAnimation((PropertyAnimator) scaleAnimator);
            ListViewHolder listViewHolder3 = listViewHolder;
            PinchItem pinchItem3 = pinchItem2;
            View view3 = scalableView;
            addTranslationAnimator(view3, scalableView2, listViewHolder3, this.mReferenceViewHolder, pinchItem3);
            addBlurAnimator(listViewHolder3, view3, pinchItem3);
        }
    }

    private void prepareVirtualAlbumViewAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(64);
        if (ViewUtils.isVisible(decoView)) {
            View decoView2 = this.mReferenceViewHolder.getDecoView(64);
            View findViewById = decoView.findViewById(R.id.first_layer);
            View view = findViewById;
            PinchItem pinchItem2 = pinchItem;
            prepareSubViewAnimation(view, decoView2.findViewById(R.id.first_layer), pinchItem2, false, true);
            prepareSubViewAnimation(decoView.findViewById(R.id.second_layer), decoView2.findViewById(R.id.second_layer), pinchItem2, false, true);
            prepareSubViewAnimation(decoView.findViewById(R.id.third_layer), decoView2.findViewById(R.id.third_layer), pinchItem2, false, true);
            View decoView3 = listViewHolder.getDecoView(65);
            if (ViewUtils.isVisible(decoView3)) {
                RectF[] transitionRect = getTransitionRect(decoView3, this.mReferenceViewHolder.getDecoView(65), pinchItem);
                ScaleAnimator scaleAnimator = new ScaleAnimator(decoView3, transitionRect[0], transitionRect[1]);
                scaleAnimator.pivotX(((float) decoView3.getWidth()) / 2.0f);
                scaleAnimator.pivotY(((float) decoView3.getHeight()) / 2.0f);
                scaleAnimator.setAnimationListener(new a(this, 0));
                addAnimation((PropertyAnimator) scaleAnimator);
            }
        }
    }

    private void updateReferenceView(View view, View view2) {
        if (ViewUtils.isVisible(view)) {
            ViewUtils.setWidth(view2, view.getWidth());
            ViewUtils.setVisibility(view2, 0);
            return;
        }
        ViewUtils.setVisibility(view2, 8);
    }

    public int getFromRadius(boolean z) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return super.getFromRadius(z);
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new AssertionError("No transition can't occur");
                } else if (z) {
                    if (this.mGridInfo.isGridGettingLarger()) {
                        return R.dimen.folder_image_radius_grid_max_blur;
                    }
                    return R.dimen.folder_image_radius_grid_blur;
                } else if (this.mGridInfo.isGridGettingLarger()) {
                    return R.dimen.album_view_corner_radius_grid_max_blur;
                } else {
                    return R.dimen.album_view_corner_radius_grid_blur;
                }
            } else if (z) {
                return R.dimen.folder_image_radius_grid_blur;
            } else {
                return R.dimen.album_view_corner_radius_grid_blur;
            }
        } else if (z) {
            return R.dimen.folder_image_radius_list_blur;
        } else {
            return R.dimen.album_view_corner_radius_list_blur;
        }
    }

    public int getToRadius(boolean z) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return super.getToRadius(z);
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new AssertionError("No transition can't occur");
                } else if (z) {
                    if (this.mGridInfo.isGridGettingLarger()) {
                        return R.dimen.folder_image_radius_grid_blur;
                    }
                    return R.dimen.folder_image_radius_grid_max_blur;
                } else if (this.mGridInfo.isGridGettingLarger()) {
                    return R.dimen.album_view_corner_radius_grid_blur;
                } else {
                    return R.dimen.album_view_corner_radius_grid_max_blur;
                }
            } else if (z) {
                return R.dimen.folder_image_radius_list_blur;
            } else {
                return R.dimen.album_view_corner_radius_list_blur;
            }
        } else if (z) {
            return R.dimen.folder_image_radius_grid_blur;
        } else {
            return R.dimen.album_view_corner_radius_grid_blur;
        }
    }

    public void prepareAlbumDecoAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            super.prepareAlbumDecoAnimation(listViewHolder, pinchItem);
            return;
        }
        View decoView = listViewHolder.getDecoView(21);
        View decoView2 = this.mReferenceViewHolder.getDecoView(21);
        View view = (View) decoView.getParent();
        View view2 = (View) decoView2.getParent();
        updateReferenceView(decoView, decoView2);
        updateReferenceView(listViewHolder.getDecoView(26), this.mReferenceViewHolder.getDecoView(26));
        updateReferenceView(listViewHolder.getDecoView(45), this.mReferenceViewHolder.getDecoView(45));
        if (view != null && view2 != null) {
            remeasure(this.mReferenceViewHolder.getRootView());
            addTranslationAnimator(view, view2, this.mReferenceViewHolder, pinchItem);
        }
    }

    public void prepareFolderAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        boolean z = PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR;
        if (z) {
            ViewUtils.setVisibility(this.mReferenceViewHolder.getTitleView(), 0);
            ViewUtils.setVisibility(this.mReferenceViewHolder.getCountView(), 0);
            remeasure(this.mReferenceViewHolder.getRootView());
        }
        super.prepareFolderAnimation(listViewHolder, pinchItem);
        if (z) {
            prepareFolderEmptyViewAnimation(listViewHolder, pinchItem);
            prepareFolderEmptyViewIconAnimation(listViewHolder, pinchItem);
            prepareAlbumEmptyViewAnimation(listViewHolder, pinchItem);
            prepareVirtualAlbumViewAnimation(listViewHolder, pinchItem);
        }
    }

    public void prepareFolderChildAnimation(ListViewHolder listViewHolder, ListViewHolder listViewHolder2, ListViewHolder listViewHolder3, PinchItem pinchItem, boolean z, boolean[] zArr) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            super.prepareFolderChildAnimation(listViewHolder, listViewHolder2, listViewHolder3, pinchItem, z, zArr);
            return;
        }
        boolean equals = this.mTransitionType.equals(AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_LIST);
        View scalableView = listViewHolder.getScalableView();
        View scalableView2 = listViewHolder2.getScalableView();
        View view = (View) scalableView.getParent().getParent();
        int i2 = view.getLayoutParams().width;
        int i7 = view.getLayoutParams().height;
        PinchItem pinchItem2 = pinchItem;
        RectF[] transitionRect = getTransitionRect(scalableView, scalableView2, pinchItem2);
        float[] transitionRadii = getTransitionRadii(false);
        ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, transitionRect[0], transitionRect[1]);
        scaleAnimator.enableUpdateLayoutParam(true);
        scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1], zArr);
        scaleAnimator.setAnimationListener(new d(this, listViewHolder, z, equals, view, i2, i7, scalableView));
        scaleAnimator.withStartAction(new g((Object) listViewHolder, equals, (Object) view, 1));
        addAnimation((PropertyAnimator) scaleAnimator);
        addTranslationAnimator(scalableView, scalableView2, listViewHolder2, transitionRect[1]);
        if (!z) {
            return;
        }
        if (ENABLE_BLUR) {
            addAnimation((PropertyAnimator) new BlurAnimator(scalableView, getBlurImageInfo(listViewHolder3, true, pinchItem2.isReverse(), false, false)));
            return;
        }
        addAnimation((PropertyAnimator) new GradientAnimator(scalableView, getBlurImageInfo(listViewHolder3, true, pinchItem2.isReverse(), false, false)));
    }

    public void prepareThumbnailAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            super.prepareThumbnailAnimation(listViewHolder, pinchItem);
        } else {
            prepareThumbnailBlurAnimation(listViewHolder, pinchItem);
        }
    }
}
