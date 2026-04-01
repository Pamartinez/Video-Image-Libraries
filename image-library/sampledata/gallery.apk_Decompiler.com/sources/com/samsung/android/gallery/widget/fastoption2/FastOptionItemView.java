package com.samsung.android.gallery.widget.fastoption2;

import A2.d;
import Ab.a;
import Ab.b;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.databinding.FastoptionItemBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionItemView extends RelativeLayout {
    private View mBadgeView;
    private FastoptionItemBinding mFastOptionItemBinding;
    private View mIconTextView;
    private ImageView mImageView;
    private ItemSelectedListener mItemSelectedListener;
    private int mMenuId;
    private int mWidthRes;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ItemSelectedListener {
        void onItemSelected(int i2, View view);
    }

    public FastOptionItemView(Context context) {
        super(context);
        initView();
    }

    private void dismissToolTip() {
        CharSequence tooltipText = this.mFastOptionItemBinding.fastOptionItemContainer.getTooltipText();
        if (tooltipText != null) {
            this.mFastOptionItemBinding.fastOptionItemContainer.setTooltipText((CharSequence) null);
            this.mFastOptionItemBinding.fastOptionItemContainer.postDelayed(new d(9, this, tooltipText), 100);
        }
    }

    private int getCalculatedFastOptionViewWidth(int i2) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_horizontal_padding);
        int itemWidth = getItemWidth();
        return (itemWidth * i2) + (dimensionPixelSize * 2) + ((i2 - 1) * getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_item_gap_padding));
    }

    private int getItemWidth() {
        if (this.mWidthRes > 0) {
            return getResources().getDimensionPixelSize(this.mWidthRes);
        }
        if (this.mIconTextView != null) {
            return getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_item_with_text_width);
        }
        return getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_item_width);
    }

    private void initView() {
        FastoptionItemBinding inflate = FastoptionItemBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.mFastOptionItemBinding = inflate;
        inflate.fastOptionItemContainer.setOnClickListener(new a(0, this));
        setDefaultAccessibilityDelegate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissToolTip$0(CharSequence charSequence) {
        this.mFastOptionItemBinding.fastOptionItemContainer.setTooltipText(charSequence);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        dismissToolTip();
        ItemSelectedListener itemSelectedListener = this.mItemSelectedListener;
        if (itemSelectedListener != null) {
            itemSelectedListener.onItemSelected(this.mMenuId, view);
        }
    }

    private void setDefaultAccessibilityDelegate() {
        this.mFastOptionItemBinding.fastOptionItemContainer.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        this.mFastOptionItemBinding.fastOptionItemContainer.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(FastOptionItemView.this.getContext().getString(R$string.speak_button));
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                view.setHovered(true);
            }
        });
    }

    private void setForceDisabledAccessibilityDelegate() {
        this.mFastOptionItemBinding.fastOptionItemContainer.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        this.mFastOptionItemBinding.fastOptionItemContainer.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
                accessibilityNodeInfo.setClickable(false);
                AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
                wrap.setRoleDescription(FastOptionItemView.this.getContext().getString(R$string.speak_button) + ArcCommonLog.TAG_COMMA + FastOptionItemView.this.getContext().getString(R$string.disabled));
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                view.setHovered(true);
            }
        });
    }

    public int getMenuId() {
        return this.mMenuId;
    }

    public void inflateView(boolean z) {
        if (z) {
            this.mIconTextView = this.mFastOptionItemBinding.iconText.inflate();
        } else {
            this.mImageView = (ImageView) this.mFastOptionItemBinding.imgLayout.inflate().findViewById(R$id.fast_option_img_icon);
        }
    }

    public void setClickable(boolean z) {
        this.mFastOptionItemBinding.fastOptionItemContainer.setClickable(z);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mFastOptionItemBinding.fastOptionItemContainer.setEnabled(z);
    }

    public void setImage(Drawable drawable) {
        if (drawable != null) {
            ImageView imageView = this.mImageView;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            }
            View view = this.mIconTextView;
            if (view != null) {
                ((ImageView) view.findViewById(R$id.fast_option_img_icon)).setImageDrawable(drawable);
            }
        }
    }

    public void setItemSelectedListener(ItemSelectedListener itemSelectedListener) {
        this.mItemSelectedListener = itemSelectedListener;
    }

    public void setLayoutParams(int i2) {
        View rootView = getRootView();
        if (rootView.getWidth() == 0) {
            ViewUtils.post(rootView, new b((Object) this, (Object) rootView, i2, 0));
        } else {
            lambda$setLayoutParams$1(rootView, i2);
        }
    }

    public void setMenuId(int i2) {
        this.mMenuId = i2;
    }

    public void setText(int i2) {
        String string = getResources().getString(i2);
        if (!TextUtils.isEmpty(string)) {
            View view = this.mIconTextView;
            if (view != null) {
                ViewUtils.setText((TextView) view.findViewById(R$id.fast_option_img_text), i2);
            }
            this.mFastOptionItemBinding.fastOptionItemContainer.setTooltipText(string);
            this.mFastOptionItemBinding.fastOptionItemContainer.setContentDescription(string);
        }
    }

    public void setWidthRes(int i2) {
        this.mWidthRes = i2;
    }

    public void updateDimWithEnabled(boolean z, boolean z3) {
        float f;
        boolean z7;
        if (getVisibility() == 0) {
            if (z) {
                f = 0.4f;
            } else {
                f = 1.0f;
            }
            setAlpha(f);
            if (!z || z3) {
                z7 = true;
            } else {
                z7 = false;
            }
            setEnabled(z7);
            if (!z || !z3) {
                setDefaultAccessibilityDelegate();
            } else {
                setForceDisabledAccessibilityDelegate();
            }
        }
    }

    public void updateNewBadge(boolean z) {
        ViewStub viewStub;
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            viewStub = this.mFastOptionItemBinding.dotBadge;
        } else {
            viewStub = this.mFastOptionItemBinding.newBadge;
        }
        if (viewStub.getParent() != null) {
            this.mBadgeView = viewStub.inflate();
        }
        View view = this.mBadgeView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: setLayoutParams */
    public void lambda$setLayoutParams$1(View view, int i2) {
        int width = view.getWidth();
        int calculatedFastOptionViewWidth = getCalculatedFastOptionViewWidth(i2);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.fast_menu_imageview_height);
        int i7 = 0;
        if (calculatedFastOptionViewWidth > width) {
            setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f));
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getItemWidth(), dimensionPixelSize);
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            if (viewGroup.indexOfChild(this) > 0) {
                i7 = getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_horizontal_padding);
            }
            layoutParams.setMarginStart(i7);
        }
        setLayoutParams(layoutParams);
    }
}
