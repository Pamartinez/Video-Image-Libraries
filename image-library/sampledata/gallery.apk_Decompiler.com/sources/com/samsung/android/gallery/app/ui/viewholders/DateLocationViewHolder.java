package com.samsung.android.gallery.app.ui.viewholders;

import B2.C0000a;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DateLocationViewHolder extends CheckboxListViewHolder implements View.OnClickListener {
    private final View.AccessibilityDelegate mAccessibilityDelegate;
    protected TextView mDateText;
    private boolean mIsSinglePickMode;
    protected TextView mLocationText;

    public DateLocationViewHolder(View view, int i2) {
        super(view, i2);
        AnonymousClass1 r1 = new View.AccessibilityDelegate() {
            private void setContentDescriptionAfterClicked(View view) {
                ListViewHolder.SelectListener selectListener = DateLocationViewHolder.this.mSelectListener;
                if (selectListener != null) {
                    int selectedCount = selectListener.getSelectedCount();
                    String quantityString = DateLocationViewHolder.this.getContext().getResources().getQuantityString(R.plurals.speak_item_selected_quantity_string, selectedCount, new Object[]{Integer.valueOf(selectedCount)});
                    TextView textView = DateLocationViewHolder.this.mDateText;
                    if (view == textView) {
                        ((View) textView.getParent()).setContentDescription(quantityString);
                    } else {
                        view.setContentDescription(quantityString);
                    }
                }
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                int i2;
                if (view == DateLocationViewHolder.this.mDateText.getParent()) {
                    view.setClickable(DateLocationViewHolder.this.isCheckBoxEnabled());
                }
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                View view2 = (View) DateLocationViewHolder.this.mDateText.getParent();
                int i7 = 2;
                if (DateLocationViewHolder.this.isCheckBoxEnabled()) {
                    i2 = 1;
                } else {
                    i2 = 2;
                }
                view2.setImportantForAccessibility(i2);
                DateLocationViewHolder dateLocationViewHolder = DateLocationViewHolder.this;
                TextView textView = dateLocationViewHolder.mDateText;
                if (!dateLocationViewHolder.isCheckBoxEnabled()) {
                    i7 = 1;
                }
                textView.setImportantForAccessibility(i7);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 32768) {
                    Log.v(DateLocationViewHolder.this.TAG, "Populating accessibility event");
                    DateLocationViewHolder.this.setContentDescription(view);
                } else if (accessibilityEvent.getEventType() == 1 && DateLocationViewHolder.this.isCheckBoxEnabled()) {
                    DateLocationViewHolder.this.mCheckboxView.callOnClick();
                    setContentDescriptionAfterClicked(view);
                }
            }
        };
        this.mAccessibilityDelegate = r1;
        setUseThumbnailCheckbox(false);
        SeApiCompat.setButtonShapeEnabled(this.mLocationText);
        this.mLocationText.setOnClickListener(this);
        this.mLocationText.setAccessibilityDelegate(r1);
    }

    private String getContentDescription(View view) {
        StringBuilder sb2 = new StringBuilder();
        TextView textView = this.mLocationText;
        if (view == textView && textView.getVisibility() == 0) {
            sb2.append(this.mLocationText.getText());
            if (this.mLocationText.getAlpha() < 0.5f) {
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(this.mLocationText.getContext().getString(R.string.speak_dimmed));
            } else {
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(view.getContext().getString(R.string.speak_button));
            }
        } else if (isCheckBoxEnabled()) {
            if (this.mCheckboxView.isChecked()) {
                sb2.append(view.getContext().getString(R.string.speak_checked));
            } else {
                sb2.append(view.getContext().getString(R.string.speak_not_checked));
            }
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(this.mDateText.getText());
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(view.getContext().getString(R.string.speak_checkbox));
        } else {
            sb2.append(this.mDateText.getText());
        }
        return sb2.toString();
    }

    private boolean isClickable(View view) {
        if (!ViewUtils.isVisible(view) || !view.isClickable()) {
            return false;
        }
        return true;
    }

    private boolean isLocationAvailable(MediaItem mediaItem) {
        if (mediaItem == null || TextUtils.isEmpty(mediaItem.getLocation())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDateText$0(View view, boolean z) {
        if (isCheckBoxEnabled()) {
            this.mCheckboxView.requestFocus();
        }
    }

    /* access modifiers changed from: private */
    public void setContentDescription(View view) {
        if (view != null) {
            String contentDescription = getContentDescription(view);
            if (TextUtils.isEmpty(contentDescription)) {
                return;
            }
            if (view != this.mDateText || !isCheckBoxEnabled()) {
                view.setContentDescription(contentDescription);
            } else {
                ((View) this.mDateText.getParent()).setContentDescription(contentDescription);
            }
        }
    }

    private void setDateText(MediaItem mediaItem) {
        if (mediaItem != null) {
            String date = mediaItem.getDate();
            if (!TextUtils.isEmpty(date)) {
                this.mDateText.setText(date);
            }
        }
        this.mDateText.setAccessibilityDelegate(this.mAccessibilityDelegate);
        ((View) this.mDateText.getParent()).setAccessibilityDelegate(this.mAccessibilityDelegate);
        ((View) this.mDateText.getParent()).setOnFocusChangeListener(new C0000a(5, this));
    }

    private void setLocationText(MediaItem mediaItem) {
        if (mediaItem != null) {
            String location = mediaItem.getLocation();
            if (!TextUtils.isEmpty(location)) {
                this.mLocationText.setText(location.replace(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mLocationText.getContext().getString(R.string.comma)));
                this.mLocationText.setVisibility(0);
                return;
            }
            this.mLocationText.setVisibility(8);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setDateText(mediaItem);
        setLocationText(mediaItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDateText = (TextView) view.findViewById(R.id.date);
        this.mLocationText = (TextView) view.findViewById(R.id.location);
    }

    public TextView getDateTextView() {
        return this.mDateText;
    }

    public View getDecoView(int i2) {
        if (i2 == 19) {
            return this.mLocationText;
        }
        return super.getDecoView(i2);
    }

    public TextView getLocationTextView() {
        return this.mLocationText;
    }

    public int getViewItemId(View view) {
        if (view == this.mLocationText) {
            return 3;
        }
        return super.getViewItemId(view);
    }

    public boolean hasCheckbox() {
        return true;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (isClickable(this.mLocationText) && ViewUtils.isTouchedOnView(this.mLocationText, motionEvent)) {
            return false;
        }
        if (!isClickable(this.mCheckboxView) || !ViewUtils.isTouchedOnView(this.mCheckboxView, motionEvent)) {
            return true;
        }
        return false;
    }

    public void onCheckboxViewStubInflated(CheckBox checkBox) {
        super.onCheckboxViewStubInflated(checkBox);
        if (Features.isEnabled(Features.IS_RTL)) {
            getRootView().setLayoutDirection(1);
        }
    }

    public void onClick(View view) {
        ListViewHolder.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, getViewPosition(), getMediaItem(), getViewItemId(view));
        }
    }

    public void recycle() {
        super.recycle();
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.width = -1;
        this.itemView.setLayoutParams(layoutParams);
    }

    public void setCheckboxEnabled(int i2, boolean z) {
        super.setCheckboxEnabled(i2, z);
        if (z) {
            this.mSupportDecoItemType |= 1;
        }
        setCheckboxEnabled(z);
        getCheckbox().setImportantForAccessibility(2);
    }

    public void setLocationTextEnabled(boolean z, boolean z3, boolean z7) {
        float f;
        if (!z || !isLocationAvailable(this.mMediaItem) || !z7) {
            this.mLocationText.setVisibility(8);
            return;
        }
        boolean z9 = false;
        this.mLocationText.setVisibility(0);
        if (!this.mIsSinglePickMode && !isCheckBoxEnabled() && !z3) {
            z9 = true;
        }
        TextView textView = this.mLocationText;
        if (z9) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        textView.setAlpha(f);
        this.mLocationText.setClickable(z9);
        this.mLocationText.setFocusable(z9);
        this.mLocationText.setEnabled(z9);
    }

    public void setSelectListener(ListViewHolder.SelectListener selectListener) {
        super.setSelectListener(selectListener);
        this.mIsSinglePickMode = selectListener.isListSingSelectionMode();
    }

    public String toString() {
        return super.toString();
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 256) == 0) {
            return false;
        }
        setDateText(this.mMediaItem);
        setLocationText(this.mMediaItem);
        if (objArr != null && objArr.length > 0) {
            setLocationTextEnabled(objArr[0].booleanValue(), objArr[1].booleanValue(), objArr[2].booleanValue());
        }
        return true;
    }

    public boolean useSelectListener() {
        return true;
    }
}
