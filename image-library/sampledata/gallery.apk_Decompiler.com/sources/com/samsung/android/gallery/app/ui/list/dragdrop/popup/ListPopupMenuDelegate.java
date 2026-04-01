package com.samsung.android.gallery.app.ui.list.dragdrop.popup;

import R6.c;
import T3.e;
import U4.a;
import U4.b;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListPopupMenuDelegate implements IPopupMenuDelegate {
    private final Blackboard mBlackboard;
    /* access modifiers changed from: private */
    public Consumer<View> mCallback;
    private View mDimView;
    private PreviewDirection mDirection = PreviewDirection.START;
    /* access modifiers changed from: private */
    public boolean mIsListTouchDown;
    /* access modifiers changed from: private */
    public boolean mIsPreviewPositioned;
    private boolean mIsRtl;
    private final RecyclerView.OnItemTouchListener mItemTouchListener = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            ListPopupMenuDelegate.this.mLastRawX = motionEvent.getRawX();
            ListPopupMenuDelegate.this.mLastRawY = motionEvent.getRawY();
            return ListPopupMenuDelegate.this.isPopupMenuShowing();
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            ListPopupMenuDelegate.this.mLastRawX = motionEvent.getRawX();
            ListPopupMenuDelegate.this.mLastRawY = motionEvent.getRawY();
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }
    };
    /* access modifiers changed from: private */
    public float mLastRawX;
    /* access modifiers changed from: private */
    public float mLastRawY;
    private final View.OnTouchListener mListTouchListener = new View.OnTouchListener() {
        private boolean mIsDragging = false;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            ListPopupMenuDelegate.this.mLastRawX = motionEvent.getRawX();
            ListPopupMenuDelegate.this.mLastRawY = motionEvent.getRawY();
            int action = motionEvent.getAction();
            if (action == 1) {
                this.mIsDragging = false;
                ListPopupMenuDelegate.this.mIsListTouchDown = false;
            } else if (action != 2) {
                if (action == 3) {
                    this.mIsDragging = false;
                }
            } else if (ListPopupMenuDelegate.this.mIsPreviewPositioned) {
                float o2 = ListPopupMenuDelegate.this.mLastRawX - ListPopupMenuDelegate.this.mStartX;
                float p6 = ListPopupMenuDelegate.this.mLastRawY - ListPopupMenuDelegate.this.mStartY;
                ListPopupMenuDelegate listPopupMenuDelegate = ListPopupMenuDelegate.this;
                if (listPopupMenuDelegate.getViewBounds(listPopupMenuDelegate.mPreview).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !this.mIsDragging) {
                    if (Math.sqrt((double) ((p6 * p6) + (o2 * o2))) >= 30.0d) {
                        this.mIsDragging = true;
                        ListPopupMenuDelegate.this.mCallback.accept(ListPopupMenuDelegate.this.mListView);
                        ListPopupMenuDelegate.this.dismissPopupMenu();
                    }
                }
            }
            return ListPopupMenuDelegate.this.isPopupMenuShowing();
        }
    };
    /* access modifiers changed from: private */
    public GalleryListView mListView;
    private int mMinWindowSize = 0;
    private PopupMenu mPopupMenu;
    private int mPopupPreviewOffset = 0;
    private int mPopupSideOffset = 0;
    private final View.OnTouchListener mPopupTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 4) {
                    return false;
                }
                return true;
            } else if (!ListPopupMenuDelegate.this.mIsPreviewPositioned || ListPopupMenuDelegate.this.getViewBounds(view).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                return false;
            } else {
                ListPopupMenuDelegate listPopupMenuDelegate = ListPopupMenuDelegate.this;
                if (listPopupMenuDelegate.getViewBounds(listPopupMenuDelegate.mPreview).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    ListPopupMenuDelegate.this.mCallback.accept(view);
                } else if (ListPopupMenuDelegate.this.mIsListTouchDown) {
                    ListPopupMenuDelegate.this.mCallback.accept(ListPopupMenuDelegate.this.mPreview);
                }
                ListPopupMenuDelegate.this.removeAnchorView();
                return false;
            }
        }
    };
    /* access modifiers changed from: private */
    public ImageView mPreview;
    /* access modifiers changed from: private */
    public float mStartX;
    /* access modifiers changed from: private */
    public float mStartY;
    private final IBaseListView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PreviewDirection {
        START,
        END,
        TOP,
        BOTTOM;

        /* access modifiers changed from: private */
        public boolean isHorizontal() {
            if (this == START || this == END) {
                return true;
            }
            return false;
        }
    }

    public ListPopupMenuDelegate(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
        this.mBlackboard = iBaseListView.getBlackboard();
        initListView();
        initPreview();
        initDimView();
    }

    private void addAnchorView(Runnable runnable) {
        this.mIsPreviewPositioned = false;
        ViewUtils.removeSelf(this.mDimView);
        ViewUtils.addView((ViewGroup) this.mListView.getRootView(), this.mDimView);
        ViewUtils.removeSelf(this.mPreview);
        ViewUtils.setAlpha(this.mPreview, 0.0f);
        ViewUtils.addView((ViewGroup) this.mListView.getRootView(), this.mPreview);
        this.mListView.post(runnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
        r3 = (r1 - r5.mPopupPreviewOffset) - r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustPreviewPosition(int r6) {
        /*
            r5 = this;
            com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate$PreviewDirection r0 = r5.mDirection
            boolean r0 = r0.isHorizontal()
            if (r0 == 0) goto L_0x0055
            com.samsung.android.gallery.widget.listview.GalleryListView r0 = r5.mListView
            int r0 = r0.getWidth()
            android.widget.ImageView r1 = r5.mPreview
            float r1 = r1.getX()
            int r1 = (int) r1
            android.widget.ImageView r2 = r5.mPreview
            int r2 = r2.getWidth()
            com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate$PreviewDirection r3 = r5.mDirection
            com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate$PreviewDirection r4 = com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate.PreviewDirection.START
            if (r3 != r4) goto L_0x003b
            int r2 = r2 + r1
            int r3 = r5.mPopupPreviewOffset
            int r2 = r2 + r3
            int r2 = r2 + r6
            int r6 = r5.mPopupSideOffset
            int r3 = r0 - r6
            if (r3 >= r2) goto L_0x0055
            int r2 = r2 - r0
            int r2 = r2 + r6
            int r1 = r1 - r2
            android.widget.ImageView r5 = r5.mPreview
            r6 = 0
            int r6 = java.lang.Math.max(r6, r1)
            float r6 = (float) r6
            r5.setX(r6)
            return
        L_0x003b:
            com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate$PreviewDirection r4 = com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate.PreviewDirection.END
            if (r3 != r4) goto L_0x0055
            int r3 = r5.mPopupPreviewOffset
            int r3 = r1 - r3
            int r3 = r3 - r6
            int r6 = r5.mPopupSideOffset
            if (r6 <= r3) goto L_0x0055
            int r1 = r1 - r3
            int r1 = r1 + r6
            android.widget.ImageView r5 = r5.mPreview
            int r0 = r0 - r2
            int r6 = java.lang.Math.min(r0, r1)
            float r6 = (float) r6
            r5.setX(r6)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate.adjustPreviewPosition(int):void");
    }

    private void createPopupMenu(Context context) {
        if (this.mPopupMenu == null) {
            this.mPopupMenu = new PopupMenu(context, this.mPreview, 8388611);
        }
    }

    /* access modifiers changed from: private */
    public void dismissPopupMenu() {
        Optional.ofNullable(this.mPopupMenu).ifPresent(new e(11));
    }

    private void erasePopupMenuData() {
        this.mBlackboard.publish("data://floating_pop_menu", Boolean.FALSE);
        this.mBlackboard.publish("data://focused_item", (Object) null);
    }

    private float getCoordinatedXOffset() {
        int[] iArr = new int[2];
        this.mListView.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.mListView.getLocationInWindow(iArr2);
        return (float) (iArr[0] - iArr2[0]);
    }

    private float getCoordinatedYOffset() {
        int[] iArr = new int[2];
        this.mListView.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.mListView.getLocationInWindow(iArr2);
        return (float) (iArr[1] - iArr2[1]);
    }

    private ClipData getDummyClipDataForPreview() {
        ClipData newPlainText = ClipData.newPlainText("", "");
        ClipDataUtils.putStringExtra(newPlainText, "started_location", this.mView.getLocationKey());
        ClipDataUtils.putBooleanExtra(newPlainText, "use_layer_thumb", true);
        return newPlainText;
    }

    private PopupWindow getPopupWindow() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            return popupMenu.seslGetPopupWindow();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Rect getViewBounds(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        return new Rect(i2, iArr[1], view.getWidth() + i2, view.getHeight() + iArr[1]);
    }

    private int getWindowHeight() {
        Context context = this.mView.getContext();
        if (context != null) {
            return ResourceCompat.getWindowHeight(context);
        }
        return this.mListView.getHeight();
    }

    private int getWindowWidth() {
        Context context = this.mView.getContext();
        if (context != null) {
            return ResourceCompat.getWindowWidth(context);
        }
        return this.mListView.getWidth();
    }

    private void initDimView() {
        Optional.ofNullable(this.mView.getContext()).ifPresent(new a(this, 2));
    }

    private void initListView() {
        GalleryListView listView = this.mView.getListView();
        this.mListView = listView;
        Optional.ofNullable(listView).ifPresent(new a(this, 1));
    }

    private void initPreview() {
        Optional.ofNullable(this.mView.getContext()).ifPresent(new a(this, 0));
    }

    private boolean isStartPositioned() {
        if ((((float) this.mPreview.getWidth()) / 2.0f) + this.mPreview.getX() < ((float) getWindowWidth()) / 2.0f) {
            return true;
        }
        return false;
    }

    private boolean isTopPositioned() {
        if ((((float) this.mPreview.getHeight()) / 2.0f) + this.mPreview.getY() < ((float) getWindowHeight()) / 2.0f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$destroy$6(GalleryListView galleryListView) {
        galleryListView.removeExtraOnTouchListener();
        galleryListView.removeOnItemTouchListener(this.mItemTouchListener);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$destroy$7(PopupMenu popupMenu) {
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) null);
        popupMenu.setOnDismissListener((PopupMenu.OnDismissListener) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initDimView$0(Context context) {
        View view = new View(context);
        this.mDimView = view;
        view.setBackgroundColor(context.getColor(R.color.dim_view_color));
        this.mDimView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initListView$1(GalleryListView galleryListView) {
        galleryListView.addExtraOnTouchListener(this.mListTouchListener);
        galleryListView.addOnItemTouchListener(this.mItemTouchListener);
        this.mPopupPreviewOffset = galleryListView.getResources().getDimensionPixelOffset(R.dimen.popup_preview_offset);
        this.mPopupSideOffset = galleryListView.getResources().getDimensionPixelOffset(R.dimen.sesl_menu_popup_offset_horizontal);
        this.mMinWindowSize = galleryListView.getResources().getDimensionPixelOffset(R.dimen.min_popup_window_size);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreview$2(Context context) {
        ImageView imageView = new ImageView(context);
        this.mPreview = imageView;
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPopupWindowProperty$8(View view) {
        adjustPreviewPosition(view);
        showPreview();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPopupWindowProperty$9(PopupWindow popupWindow) {
        popupWindow.setSplitTouchEnabled(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(this.mPopupTouchListener);
        View contentView = popupWindow.getContentView();
        contentView.post(new Ob.a(29, this, contentView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$3(Context context, Consumer consumer) {
        createPopupMenu(context);
        if (!showPopupMenu()) {
            removeAnchorView();
            consumer.accept(this.mListView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$showPopupMenu$4(MenuItem menuItem) {
        return this.mView.onOptionsItemSelected(menuItem, !PickerUtil.isNormalLaunchMode(this.mBlackboard));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPopupMenu$5(PopupMenu popupMenu) {
        removeAnchorView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$showPreview$10(ImageView imageView) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(150);
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationStart(Animation animation) {
                ViewUtils.setAlpha(ListPopupMenuDelegate.this.mPreview, 1.0f);
            }
        });
        return alphaAnimation;
    }

    private int measurePopupWidth() {
        Menu menu = this.mPopupMenu.getMenu();
        View inflate = LayoutInflater.from(this.mView.getContext()).inflate(R.layout.sesl_popup_menu_item_layout, (ViewGroup) null, false);
        int i2 = 0;
        for (int i7 = 0; i7 < menu.size(); i7++) {
            MenuItem item = menu.getItem(i7);
            if (item.isVisible()) {
                ((TextView) inflate.findViewById(R.id.title)).setText(item.getTitle());
                inflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredWidth = inflate.getMeasuredWidth();
                if (measuredWidth > i2) {
                    i2 = measuredWidth;
                }
            }
        }
        return i2;
    }

    private void preparePreview(ListViewHolder listViewHolder, int i2) {
        float coordinatedXOffset = this.mLastRawX - getCoordinatedXOffset();
        float coordinatedYOffset = this.mLastRawY - getCoordinatedYOffset();
        this.mStartX = this.mLastRawX;
        this.mStartY = this.mLastRawY;
        Bitmap dragStartBitmap = new DragShadowCreator().getDragStartBitmap(getDummyClipDataForPreview(), listViewHolder, i2);
        this.mPreview.setImageBitmap(dragStartBitmap);
        this.mPreview.setX(Math.min((float) (getWindowWidth() - dragStartBitmap.getWidth()), Math.max(0.0f, coordinatedXOffset - (((float) dragStartBitmap.getWidth()) / 2.0f))));
        this.mPreview.setY(Math.min((float) (getWindowHeight() - dragStartBitmap.getHeight()), Math.max(0.0f, coordinatedYOffset - (((float) dragStartBitmap.getHeight()) / 2.0f))));
    }

    private void publishPopupMenuData(MediaItem mediaItem) {
        this.mBlackboard.publish("data://floating_pop_menu", Boolean.TRUE);
        this.mBlackboard.publish("data://focused_item", mediaItem);
    }

    /* access modifiers changed from: private */
    public void removeAnchorView() {
        erasePopupMenuData();
        ViewUtils.removeSelf(this.mDimView);
        ViewUtils.removeSelf(this.mPreview);
        this.mIsPreviewPositioned = false;
    }

    private void resetListPopupLayout() {
        this.mPopupMenu.seslSetOffset(0, 0);
        this.mPopupMenu.seslSetHeight(-2);
        this.mPopupMenu.seslSetOverlapAnchor(true);
        this.mPopupMenu.seslForceShowUpper(false);
    }

    private void setPopupGravity() {
        int i2;
        boolean isStartPositioned = isStartPositioned();
        PopupMenu popupMenu = this.mPopupMenu;
        if (this.mIsRtl ^ isStartPositioned) {
            i2 = 8388611;
        } else {
            i2 = 8388613;
        }
        popupMenu.setGravity(i2);
    }

    private void setPopupMenuXOffset() {
        int i2 = ((-this.mPreview.getWidth()) + this.mPopupSideOffset) - this.mPopupPreviewOffset;
        int width = this.mPreview.getWidth() + this.mPopupSideOffset + this.mPopupPreviewOffset;
        if (isStartPositioned()) {
            PopupMenu popupMenu = this.mPopupMenu;
            if (!this.mIsRtl) {
                i2 = width;
            }
            popupMenu.seslSetOffset(i2, 0);
            this.mDirection = PreviewDirection.START;
            return;
        }
        PopupMenu popupMenu2 = this.mPopupMenu;
        if (this.mIsRtl) {
            i2 = width;
        }
        popupMenu2.seslSetOffset(i2, 0);
        this.mDirection = PreviewDirection.END;
    }

    private void setPopupMenuYOffset() {
        if (isTopPositioned()) {
            this.mPopupMenu.seslSetOffset(0, this.mPreview.getHeight() + this.mPopupPreviewOffset);
            this.mPopupMenu.seslSetHeight((int) (((float) getWindowHeight()) - ((this.mPreview.getY() + ((float) this.mPreview.getHeight())) + ((float) this.mPopupPreviewOffset))));
            this.mDirection = PreviewDirection.TOP;
            return;
        }
        this.mPopupMenu.seslSetHeight((int) ((this.mPreview.getY() - ((float) this.mPopupPreviewOffset)) - ((float) WindowUtils.getSystemInsetsTop(this.mPreview.getRootWindowInsets()))));
        this.mPopupMenu.seslForceShowUpper(true);
        this.mDirection = PreviewDirection.BOTTOM;
    }

    private void setPopupWindowProperty() {
        Optional.ofNullable(getPopupWindow()).ifPresent(new a(this, 4));
    }

    private boolean showPopupMenu() {
        this.mIsListTouchDown = true;
        Menu menu = this.mPopupMenu.getMenu();
        if (this.mView.getPresenter().onCreatePopupMenu(menu, this.mPopupMenu.getMenuInflater()) && this.mView.getPresenter().onPreparePopupMenu(menu, 1)) {
            int measurePopupWidth = measurePopupWidth();
            if (updatePopupPosition(measurePopupWidth)) {
                adjustPreviewPosition(measurePopupWidth);
                this.mPopupMenu.seslSetOverflowOnly(false);
                this.mPopupMenu.setOnMenuItemClickListener(new b(this));
                this.mPopupMenu.setOnDismissListener(new b(this));
                this.mPopupMenu.show();
                setPopupWindowProperty();
                return true;
            }
        }
        return false;
    }

    private void showPreview() {
        ViewUtils.startAnimation(this.mPreview, new A5.a(24, this));
        this.mIsPreviewPositioned = true;
    }

    private boolean updatePopupPosition(int i2) {
        if (this.mPreview == null) {
            return false;
        }
        resetListPopupLayout();
        setPopupGravity();
        if (this.mPreview.getWidth() + i2 + this.mPopupPreviewOffset < getWindowWidth()) {
            setPopupMenuXOffset();
            return true;
        }
        setPopupMenuYOffset();
        return true;
    }

    public void destroy() {
        Optional.ofNullable(this.mListView).ifPresent(new a(this, 3));
        this.mListView = null;
        Optional.ofNullable(this.mPopupMenu).ifPresent(new e(12));
        this.mPopupMenu = null;
        this.mCallback = null;
        this.mPreview = null;
        this.mIsPreviewPositioned = false;
    }

    public boolean isPopupMenuShowing() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu == null || !popupMenu.seslIsShowing()) {
            return false;
        }
        return true;
    }

    public void release() {
        if (isPopupMenuShowing()) {
            dismissPopupMenu();
        }
    }

    public void show(ListViewHolder listViewHolder, Consumer<View> consumer, int i2) {
        boolean z;
        boolean z3;
        GalleryListView galleryListView = this.mListView;
        if (galleryListView == null || this.mPreview == null || this.mDimView == null) {
            boolean z7 = false;
            if (galleryListView == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mPreview == null) {
                z3 = true;
            } else {
                z3 = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z3);
            if (this.mDimView == null) {
                z7 = true;
            }
            Log.e((CharSequence) "ListPopupMenuDelegate", "can not show popup, null view", valueOf, valueOf2, Boolean.valueOf(z7));
            return;
        }
        this.mIsRtl = Features.isEnabled(Features.IS_RTL);
        this.mCallback = consumer;
        Context context = this.mView.getContext();
        if (context == null) {
            this.mCallback.accept(this.mListView);
            Log.e("ListPopupMenuDelegate", "can not show popup, null context");
        } else if (Math.max(ResourceCompat.getWindowWidth(context), ResourceCompat.getWindowHeight(context)) < this.mMinWindowSize) {
            this.mCallback.accept(this.mListView);
            Log.e("ListPopupMenuDelegate", "can not show popup, not enough space");
        } else {
            preparePreview(listViewHolder, i2);
            publishPopupMenuData(listViewHolder.getMediaItem());
            addAnchorView(new c(this, context, consumer, 16));
        }
    }

    private void adjustPreviewPosition(View view) {
        if (this.mPreview != null) {
            Rect viewBounds = getViewBounds(view);
            Rect viewBounds2 = getViewBounds(this.mPreview);
            if (!this.mDirection.isHorizontal()) {
                return;
            }
            if (this.mDirection == PreviewDirection.START) {
                this.mPreview.setX((((float) (viewBounds2.left - (viewBounds2.right - viewBounds.left))) - getCoordinatedXOffset()) - ((float) this.mPopupPreviewOffset));
                return;
            }
            ImageView imageView = this.mPreview;
            int i2 = viewBounds2.left;
            imageView.setX((((float) ((viewBounds.right - i2) + i2)) - getCoordinatedXOffset()) + ((float) this.mPopupPreviewOffset));
        }
    }
}
