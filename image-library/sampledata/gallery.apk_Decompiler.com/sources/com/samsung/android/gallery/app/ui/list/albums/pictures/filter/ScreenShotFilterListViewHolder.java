package com.samsung.android.gallery.app.ui.list.albums.pictures.filter;

import H7.A;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterListViewHolder extends ListViewHolder {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(AppResources.getString(R.string.speak_button));
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }
    };
    private View mButton;
    private TextView mTextView;

    public ScreenShotFilterListViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(1001);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTextView.setText(mediaItem.getTitle());
        this.mTextView.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public void bindView(View view) {
        this.mButton = view.findViewById(R.id.screen_shot_filter_button);
        this.mTextView = (TextView) view.findViewById(R.id.screen_shot_filter_text_view);
        this.mButton.setOnClickListener(new A(11, this));
    }

    public void updateFocus(boolean z) {
        Drawable drawable;
        int i2;
        int i7;
        View view = this.mButton;
        if (z) {
            drawable = this.itemView.getContext().getDrawable(R.drawable.screen_shot_filter_button_background);
        } else {
            drawable = this.itemView.getContext().getDrawable(R.drawable.screen_shot_filter_button_recoil_ripple);
        }
        view.setBackground(drawable);
        TextView textView = this.mTextView;
        if (z) {
            i2 = R.style.OneUiRobotoSemiBold;
        } else {
            i2 = R.style.OneUiRobotoRegular;
        }
        textView.setTextAppearance(i2);
        TextView textView2 = this.mTextView;
        Context context = this.itemView.getContext();
        if (z) {
            i7 = R.color.screen_shot_filter_button_focused_text_color;
        } else {
            i7 = R.color.screen_shot_filter_button_text_color;
        }
        textView2.setTextColor(context.getColor(i7));
        if (this.itemView.isAccessibilityFocused()) {
            Context context2 = this.itemView.getContext();
            SeApiCompat.announceVoiceAssistant(context2, this.mTextView.getText() + ArcCommonLog.TAG_COMMA + this.itemView.getResources().getString(R.string.speak_item_selected));
        }
    }
}
