package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import B2.i;
import E6.a;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.drawer.DrawerDragView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabItemViewHolder extends RecyclerView.ViewHolder implements DrawerDragView {
    View mArrowIcon;
    View mCollapsedNewBadge;
    TextView mCount;
    View mDivider;
    private Drawable mDragFocusedDrawable;
    private Drawable mDynamicDragFocusedDrawable;
    View mDynamicFocusView;
    private Drawable mDynamicSelectFocusedDrawable;
    ImageView mExpandButton;
    ImageView mIcon;
    View mLayout;
    private onLongClickListener mLongClickListener;
    View mNewBadge;
    private onPopupMenuTriggerListener mPopupMenuTriggerListener;
    private boolean mSecondaryButtonClicked;
    private Drawable mSelectFocusedDrawable;
    private DrawerTabItem mTabItem;
    TextView mTitle;
    private float mX;
    private float mY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onLongClickListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onPopupMenuTriggerListener {
    }

    public DrawerTabItemViewHolder(View view) {
        super(view);
        initResources();
        bindView(view);
    }

    private String getContentDescription() {
        int i2;
        StringBuilder sb2 = new StringBuilder(getTitle());
        if (ViewUtils.isVisible(this.mCount) && !TextUtils.isEmpty(this.mTabItem.getCountString())) {
            int parseInt = Integer.parseInt(this.mTabItem.getCountString());
            sb2.append(ArcCommonLog.TAG_COMMA);
            if (parseInt == 1) {
                i2 = R.string.speak_folder_name_1_item;
            } else {
                i2 = R.string.speak_folder_name_n_items;
            }
            sb2.append(String.format(AppResources.getString(i2), new Object[]{Integer.valueOf(parseInt)}));
        }
        if (this.mTabItem.showNewBadge()) {
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(AppResources.getString(R.string.new_content_added));
        }
        return sb2.toString();
    }

    private float getExpandedRotation() {
        if (Features.isEnabled(Features.IS_RTL)) {
            return 180.0f;
        }
        return -180.0f;
    }

    private String getTitle() {
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !this.mTabItem.isVideoStudio()) {
            return this.mTabItem.getTitle();
        }
        return SeApiCompat.naturalizeText(this.itemView.getResources().getString(R.string.video_studio_title, new Object[]{this.mTabItem.getTitle()}));
    }

    private void inflateNewBadge() {
        View view = this.mNewBadge;
        if (view instanceof ViewStub) {
            this.mNewBadge = ((ViewStub) view).inflate();
        }
    }

    private void initResources() {
        Drawable drawable = this.itemView.getContext().getDrawable(R.drawable.drawer_focused_item_foreground);
        this.mSelectFocusedDrawable = drawable;
        if (drawable != null) {
            drawable.setAlpha(12);
        }
        Drawable drawable2 = this.itemView.getContext().getDrawable(R.drawable.drawer_drag_focused_item_foreground);
        this.mDragFocusedDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setAlpha(25);
        }
        Drawable drawable3 = this.itemView.getContext().getDrawable(R.drawable.drawer_focused_item_foreground);
        this.mDynamicSelectFocusedDrawable = drawable3;
        if (drawable3 != null) {
            drawable3.setAlpha(12);
        }
        Drawable drawable4 = this.itemView.getContext().getDrawable(R.drawable.drawer_drag_focused_item_foreground);
        this.mDynamicDragFocusedDrawable = drawable4;
        if (drawable4 != null) {
            drawable4.setAlpha(25);
        }
    }

    private void setDivider() {
        boolean z;
        View view = this.mDivider;
        if (this.mTabItem.isCollection() || this.mTabItem.isEnableDivider()) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleOrGone(view, z);
    }

    private void setIcon() {
        ViewUtils.setImageDrawable(this.mIcon, this.itemView.getContext().getDrawable(this.mTabItem.getIconResId()));
        if (this.mTabItem.isVideoStudio()) {
            ViewUtils.setImageTintColor(this.mIcon, (ColorStateList) null);
        }
    }

    private void setPaddingForDepth() {
        int min = Math.min(this.mTabItem.getDepth(), 3) * this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.drawer_n_depth_padding_start);
        ViewMarginUtils.setStartPadding(this.mLayout, this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen.drawer_tab_item_padding_start) + min);
    }

    private void setTitle() {
        ViewUtils.setText(this.mTitle, getTitle());
    }

    private void setTooltip() {
        if (this.mLayout != null && !hasDepth()) {
            this.mLayout.setTooltipText(getTitle());
        }
    }

    private void updateDimAlpha() {
        float f;
        float f5 = 0.4f;
        if (this.mTabItem.isEnabled()) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        ViewUtils.setAlpha(this.mIcon, f);
        ViewUtils.setAlpha(this.mTitle, f);
        ViewUtils.setAlpha(this.mCount, f);
        ViewUtils.setAlpha(this.mArrowIcon, f);
        ImageView imageView = this.mExpandButton;
        if (this.mTabItem.isExpandable()) {
            f5 = 1.0f;
        }
        ViewUtils.setAlpha(imageView, f5);
    }

    public void bind(DrawerTabItem drawerTabItem) {
        this.mTabItem = drawerTabItem;
        setDivider();
        setPaddingForDepth();
        setIcon();
        setTitle();
        updateEndDecorViews();
        updateFocus();
        updateDimAlpha();
        setTooltip();
    }

    public final void bindView(View view) {
        this.mDivider = view.findViewById(R.id.drawer_tab_divider);
        this.mLayout = view.findViewById(R.id.drawer_tab_item);
        this.mExpandButton = (ImageView) view.findViewById(R.id.drawer_tab_expand_button);
        this.mIcon = (ImageView) view.findViewById(R.id.drawer_tab_icon);
        this.mTitle = (TextView) view.findViewById(R.id.drawer_tab_title);
        this.mCount = (TextView) view.findViewById(R.id.drawer_tab_count);
        this.mNewBadge = view.findViewById(R.id.drawer_tab_new_badge);
        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            this.mArrowIcon = view.findViewById(R.id.drawer_tab_arrow_icon);
        }
        this.mCollapsedNewBadge = view.findViewById(R.id.collapsed_drawer_tab_new_badge);
        this.mDynamicFocusView = view.findViewById(R.id.dynamic_focus_view);
        View view2 = this.mLayout;
        if (view2 != null) {
            view2.setOnLongClickListener(new a(2, this));
            this.mLayout.setOnTouchListener(new i(27, this));
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.drawer_tab_item_container_padding_horizontal);
            int dimensionPixelSize2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.drawer_tab_item_container_padding_vertical);
            ViewUtils.setTouchArea(this.mLayout, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
            ViewUtils.setAccessibilityRoleDescription(this.mLayout, R.string.speak_tab);
        }
    }

    public View getLayout() {
        return this.mLayout;
    }

    public String getLocationKey() {
        return this.mTabItem.getLocationKey();
    }

    public MediaItem getMediaItem() {
        return this.mTabItem.getAlbumItem();
    }

    public boolean hasDepth() {
        if (this.mTabItem.getDepth() > 0) {
            return true;
        }
        return false;
    }

    public void initExpandIconRotation() {
        float f;
        ImageView imageView = this.mExpandButton;
        if (this.mTabItem.isExpanded()) {
            f = getExpandedRotation();
        } else {
            f = 0.0f;
        }
        ViewUtils.setRotation(imageView, f);
    }

    public boolean is2ndDepthAlbum() {
        if (!hasDepth() || getMediaItem() == null || getMediaItem().isFolder()) {
            return false;
        }
        return true;
    }

    public boolean isExpanded() {
        return this.mTabItem.isExpanded();
    }

    public boolean onLongClick(View view) {
        onPopupMenuTriggerListener onpopupmenutriggerlistener = this.mPopupMenuTriggerListener;
        if (onpopupmenutriggerlistener != null) {
            return ((j) onpopupmenutriggerlistener).f2518a.onPopupMenuTriggered(new PopupMenuData((ViewGroup) getLayout(), new PointF(this.mX, this.mY), getMediaItem()));
        }
        onLongClickListener onlongclicklistener = this.mLongClickListener;
        if (onlongclicklistener == null || ((j) onlongclicklistener).f2518a.lambda$onBindViewHolder$2()) {
            return true;
        }
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            if (motionEvent.getButtonState() == 2) {
                this.mSecondaryButtonClicked = true;
            }
        } else if ((action == 1 || action == 3) && this.mSecondaryButtonClicked) {
            onPopupMenuTriggerListener onpopupmenutriggerlistener = this.mPopupMenuTriggerListener;
            if (onpopupmenutriggerlistener != null) {
                boolean unused = ((j) onpopupmenutriggerlistener).f2518a.onPopupMenuTriggered(new PopupMenuData((ViewGroup) getLayout(), new PointF(this.mX, this.mY), getMediaItem()));
            }
            this.mSecondaryButtonClicked = false;
            return true;
        }
        return false;
    }

    public void recycle() {
        ViewUtils.setAlpha(this.mIcon, 1.0f);
        ViewUtils.setAlpha(this.mTitle, 1.0f);
        ViewUtils.setAlpha(this.mCount, 1.0f);
        ViewUtils.setVisibility(this.mCount, 8);
        ViewUtils.setStubVisibility(this.mNewBadge, 8);
        ViewUtils.setVisibility(this.mDynamicFocusView, 8);
        ViewUtils.setAlpha(this.mArrowIcon, 1.0f);
        View view = this.mLayout;
        if (view != null) {
            view.setOnClickListener((View.OnClickListener) null);
        }
    }

    public void removeForeground() {
        ViewUtils.setForeground(this.mLayout, (Drawable) null);
    }

    public void rotateExpandIcon(DrawerTabItem drawerTabItem) {
        float f;
        int i2;
        ImageView imageView = this.mExpandButton;
        if (imageView != null) {
            float rotation = imageView.getRotation();
            if (drawerTabItem.isExpanded()) {
                f = 0.0f;
            } else {
                f = getExpandedRotation();
            }
            ObjectAnimator.ofFloat(this.mExpandButton, View.ROTATION, new float[]{rotation, f}).setDuration(300).start();
            Context context = this.itemView.getContext();
            Resources resources = this.itemView.getResources();
            if (drawerTabItem.isExpanded()) {
                i2 = R.string.collapsed;
            } else {
                i2 = R.string.expanded;
            }
            SeApiCompat.announceVoiceAssistant(context, resources.getString(i2));
        }
    }

    public void setDragFocused(boolean z) {
        this.mTabItem.setDragFocused(z);
    }

    public void setExpandButtonVisibility(int i2) {
        ViewUtils.setVisibility(this.mExpandButton, i2);
    }

    public void setOnLongClickListener(onLongClickListener onlongclicklistener) {
        this.mLongClickListener = onlongclicklistener;
    }

    public void setOnPopupMenuTriggerListener(onPopupMenuTriggerListener onpopupmenutriggerlistener) {
        this.mPopupMenuTriggerListener = onpopupmenutriggerlistener;
    }

    public boolean supportDrop() {
        if (!LocationKey.isAlbumPictures(this.mTabItem.getLocationKey()) || LocationKey.isMxVirtualAlbum(this.mTabItem.getLocationKey())) {
            return false;
        }
        return true;
    }

    public boolean supportExpand() {
        return this.mTabItem.supportExpand();
    }

    public void translateDynamicFocusView(float f) {
        ViewUtils.setTranslationX(this.mDynamicFocusView, f);
    }

    public void updateCollapsedBadgeVisibility(boolean z) {
        boolean z3;
        View view = this.mCollapsedNewBadge;
        if (!this.mTabItem.showNewBadge() || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(view, z3);
    }

    public void updateDynamicFocusViewVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mDynamicFocusView, z);
    }

    public void updateEndDecorViews() {
        if (this.mTabItem.showNewBadge()) {
            inflateNewBadge();
            ViewUtils.setStubVisibility(this.mNewBadge, 0);
        } else {
            ViewUtils.setStubVisibility(this.mNewBadge, 8);
        }
        ViewUtils.setText(this.mCount, this.mTabItem.getCountString());
        ViewUtils.setVisibleOrGone(this.mCount, this.mTabItem.supportCount());
        ViewUtils.setVisibleOrGone(this.mArrowIcon, this.mTabItem.isVideoStudio());
        ViewUtils.setContentDescription(this.mLayout, getContentDescription());
    }

    public void updateFocus() {
        int i2;
        if (this.mLayout != null && this.mTitle != null) {
            updateForeground();
            TextView textView = this.mTitle;
            if (this.mTabItem.isSelectFocused()) {
                i2 = R.style.OneUiRobotoSemiBold;
            } else {
                i2 = R.style.OneUiRobotoRegular;
            }
            textView.setTextAppearance(i2);
            this.mLayout.setSelected(this.mTabItem.isSelectFocused());
            ImageView imageView = this.mIcon;
            if (imageView != null) {
                imageView.setSelected(this.mTabItem.isSelectFocused());
            }
        }
    }

    public void updateForeground() {
        Drawable drawable;
        View view = this.mLayout;
        Drawable drawable2 = null;
        if (this.mTabItem.isDragFocused()) {
            drawable = this.mDragFocusedDrawable;
        } else if (this.mTabItem.isSelectFocused()) {
            drawable = this.mSelectFocusedDrawable;
        } else {
            drawable = null;
        }
        ViewUtils.setForeground(view, drawable);
        View view2 = this.mDynamicFocusView;
        if (this.mTabItem.isDragFocused()) {
            drawable2 = this.mDynamicDragFocusedDrawable;
        } else if (this.mTabItem.isSelectFocused()) {
            drawable2 = this.mDynamicSelectFocusedDrawable;
        }
        ViewUtils.setForeground(view2, drawable2);
    }

    public void updateIconVisibility(boolean z) {
        ViewUtils.setVisibleOrInvisible(this.mIcon, z);
    }
}
