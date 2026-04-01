package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import B2.C0000a;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.CheckboxListViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DuplicateDividerViewHolder extends CheckboxListViewHolder {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            if (view == DuplicateDividerViewHolder.this.mFileName.getParent()) {
                view.setClickable(DuplicateDividerViewHolder.this.isCheckBoxEnabled());
            }
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768) {
                Log.v(DuplicateDividerViewHolder.this.TAG, "Populating accessibility event");
            } else if (accessibilityEvent.getEventType() == 1 && DuplicateDividerViewHolder.this.isCheckBoxEnabled()) {
                DuplicateDividerViewHolder.this.mCheckboxView.callOnClick();
            }
            DuplicateDividerViewHolder duplicateDividerViewHolder = DuplicateDividerViewHolder.this;
            view.setContentDescription(CleanOutDelegate.getDuplicateDividerDescription(duplicateDividerViewHolder, DuplicateDividerViewHolder.this.mFileSize.getText() + ArcCommonLog.TAG_COMMA + DuplicateDividerViewHolder.this.mFileName.getText(), DuplicateDividerViewHolder.this.mSelectListener.getSelectedCount(), accessibilityEvent.getEventType()));
        }
    };
    TextView mFileName;
    TextView mFileSize;

    public DuplicateDividerViewHolder(View view, int i2) {
        super(view, i2);
        setUseThumbnailCheckbox(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(View view, boolean z) {
        if (isCheckBoxEnabled()) {
            this.mCheckboxView.requestFocus();
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mFileName.setText(mediaItem.getTitle());
        this.mFileSize.setText(StringCompat.toReadableSize((double) (mediaItem.getFileSize() * ((long) mediaItem.getCount()))));
        ((View) this.mFileName.getParent()).setAccessibilityDelegate(this.mAccessibilityDelegate);
        ((View) this.mFileName.getParent()).setOnFocusChangeListener(new C0000a(3, this));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFileName = (TextView) view.findViewById(R.id.file_name);
        this.mFileSize = (TextView) view.findViewById(R.id.file_size);
    }

    public void setCheckboxEnabled(int i2, boolean z) {
        super.setCheckboxEnabled(i2, z);
        if (z) {
            this.mSupportDecoItemType |= 1;
        }
        setCheckboxEnabled(z);
        getCheckbox().setImportantForAccessibility(2);
    }

    public boolean useSelectListener() {
        return true;
    }
}
