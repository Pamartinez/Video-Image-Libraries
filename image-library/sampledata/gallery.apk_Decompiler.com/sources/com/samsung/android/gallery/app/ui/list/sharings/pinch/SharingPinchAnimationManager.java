package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import K.a;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPinchAnimationManager extends AlbumsBaseBlurPinchAnimationManager {
    private ListViewHolder mLastInvitationReferenceHolder;

    /* renamed from: com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchAnimationManager$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchAnimationManager.AnonymousClass1.<clinit>():void");
        }
    }

    public SharingPinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$prepareDividerViewHolder$0(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareDividerViewHolder$1(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareTitleAnimation$3(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -2;
        view.setLayoutParams(layoutParams);
    }

    private void setText(TextView textView, TextView textView2) {
        CharSequence charSequence;
        if (textView != null) {
            if (textView2 != null) {
                charSequence = textView2.getText();
            } else {
                charSequence = "";
            }
            textView.setText(charSequence);
        }
    }

    public void clearAnimationInfo() {
        super.clearAnimationInfo();
        ListViewHolder listViewHolder = this.mLastInvitationReferenceHolder;
        if (listViewHolder != null) {
            View rootView = listViewHolder.getRootView();
            ViewUtils.removeView((ViewGroup) rootView.getParent(), rootView);
            rootView.setVisibility(0);
            this.mLastInvitationReferenceHolder.recycle();
            putRecycledViewPool(this.mLastInvitationReferenceHolder);
        }
        this.mLastInvitationReferenceHolder = null;
    }

    public void createReferenceView() {
        this.mLastInvitationReferenceHolder = createFakeViewHolderInternal(this.mFakeViewParent, -1);
        super.createReferenceView();
    }

    public int getBorderColorResId() {
        return R.color.album_border_color;
    }

    public int getBorderThicknessResId(boolean z) {
        return R.dimen.sharing_view_border_thickness;
    }

    public int getFromRadius(boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 == 1) {
            return R.dimen.sharing_list_radius;
        }
        if (i2 == 2 || i2 == 3) {
            return R.dimen.sharing_grid_radius;
        }
        throw new AssertionError("No transition can't occur");
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        if (ViewHolderValue.isFirstDivider(this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from()))) {
            return this.mLastInvitationReferenceHolder;
        }
        return super.getRefViewHolder(i2, z);
    }

    public int getToRadius(boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 == 1) {
            return R.dimen.sharing_grid_radius;
        }
        if (i2 == 2) {
            return R.dimen.sharing_list_radius;
        }
        if (i2 == 3) {
            return R.dimen.sharing_grid_radius;
        }
        throw new AssertionError("No transition can't occur");
    }

    public void prepareDividerViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
        int hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from());
        int hintWidthSpace2 = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to());
        if (hintWidthSpace != hintWidthSpace2) {
            WidthAnimator widthAnimator = new WidthAnimator(pinchItem.getView(), hintWidthSpace, hintWidthSpace2, new a(17));
            widthAnimator.setAnimationListener(new a(18));
            addAnimation((PropertyAnimator) widthAnimator);
        }
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r8v3, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r8v6 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepareTitleAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r13, com.samsung.android.gallery.widget.listview.pinch.PinchItem r14) {
        /*
            r12 = this;
            android.widget.TextView r0 = r13.getTitleView()
            android.widget.TextView r1 = r13.getCountView()
            r2 = 46
            android.view.View r3 = r13.getDecoView(r2)
            android.widget.TextView r3 = (android.widget.TextView) r3
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4 = r12.mReferenceViewHolder
            android.widget.TextView r4 = r4.getTitleView()
            r5 = 0
            android.view.View r4 = r12.getTitleLayout(r4, r5)
            com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r6 = r12.mTransitionType
            com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r7 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_GRID
            r8 = 0
            if (r6 == r7) goto L_0x003c
            if (r0 == 0) goto L_0x002f
            android.view.ViewParent r13 = r0.getParent()
            android.view.ViewParent r13 = r13.getParent()
            r8 = r13
            android.view.View r8 = (android.view.View) r8
        L_0x002f:
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r13 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r8, (android.view.View) r4, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r13, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            r14 = 1065353216(0x3f800000, float:1.0)
            r12.addAlphaAnimator(r8, r14, r13, r14)
            return
        L_0x003c:
            r6 = 1
            android.view.View r7 = r13.getDecoView(r6)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r9 = r12.mReferenceViewHolder
            android.widget.TextView r9 = r9.getTitleView()
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r10 = r12.mReferenceViewHolder
            android.widget.TextView r10 = r10.getCountView()
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r11 = r12.mReferenceViewHolder
            android.view.View r2 = r11.getDecoView(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r12.setText(r9, r0)
            r12.setText(r10, r1)
            r12.setText(r2, r3)
            if (r7 == 0) goto L_0x0085
            boolean r11 = com.samsung.android.gallery.widget.utils.ViewUtils.isVisible(r7)
            if (r11 == 0) goto L_0x0075
            com.samsung.android.gallery.widget.pinch.IPinchRecyclerView r5 = r12.getRecyclerView()
            android.content.res.Resources r5 = r5.getResources()
            r11 = 2131167041(0x7f070741, float:1.7948344E38)
            int r5 = r5.getDimensionPixelSize(r11)
        L_0x0075:
            com.samsung.android.gallery.widget.utils.ViewMarginUtils.setEndMargin(r9, r5)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r5 = r12.mReferenceViewHolder
            android.view.View r5 = r5.getDecoView(r6)
            int r6 = r7.getVisibility()
            com.samsung.android.gallery.widget.utils.ViewUtils.setVisibility(r5, r6)
        L_0x0085:
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r5 = r12.mReferenceViewHolder
            android.view.View r5 = r5.getRootView()
            r12.remeasure(r5)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r5 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r0, (android.view.View) r9, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r5, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r5 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r1, (android.view.View) r10, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r5, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r1 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r3, (android.view.View) r2, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r1, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r1 = r12.mReferenceViewHolder
            r2 = 47
            android.view.View r1 = r1.getDecoView(r2)
            android.widget.TextView r1 = (android.widget.TextView) r1
            if (r1 == 0) goto L_0x00bd
            android.view.View r2 = r13.getDecoView(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            if (r2 == 0) goto L_0x00b5
            java.lang.CharSequence r8 = r2.getText()
        L_0x00b5:
            r1.setText(r8)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r3 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r2, (android.view.View) r1, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r3, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
        L_0x00bd:
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r1 = r12.mReferenceViewHolder
            r2 = 42
            android.view.View r1 = r1.getDecoView(r2)
            if (r1 == 0) goto L_0x00dc
            android.view.View r13 = r13.getDecoView(r2)
            if (r13 == 0) goto L_0x00d2
            int r2 = r13.getVisibility()
            goto L_0x00d4
        L_0x00d2:
            r2 = 8
        L_0x00d4:
            r1.setVisibility(r2)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = r12.mReferenceViewHolder
            r12.addTranslationAnimator((android.view.View) r13, (android.view.View) r1, (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2, (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r14)
        L_0x00dc:
            if (r0 == 0) goto L_0x010f
            int r13 = r0.getWidth()
            int r14 = r4.getWidth()
            if (r13 > r14) goto L_0x00ee
            boolean r13 = r12.isTextViewEllipsized(r0)
            if (r13 == 0) goto L_0x010f
        L_0x00ee:
            com.samsung.android.gallery.widget.animator.WidthAnimator r13 = new com.samsung.android.gallery.widget.animator.WidthAnimator
            int r14 = r0.getWidth()
            int r1 = r4.getWidth()
            O3.y r2 = new O3.y
            r3 = 9
            r2.<init>(r3, r12)
            r13.<init>(r0, r14, r1, r2)
            K.a r14 = new K.a
            r0 = 19
            r14.<init>(r0)
            r13.setAnimationListener(r14)
            r12.addAnimation((com.samsung.android.gallery.widget.animator.PropertyAnimator) r13)
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchAnimationManager.prepareTitleAnimation(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, com.samsung.android.gallery.widget.listview.pinch.PinchItem):void");
    }

    public void updateDataReferenceViewHolder(ListViewHolder listViewHolder, int i2) {
        this.mLayoutManager.updateAlbumTypeMargin(listViewHolder, i2, true);
        this.mLayoutManager.updateEmptyAlbumViewSize(listViewHolder, i2, true);
    }
}
