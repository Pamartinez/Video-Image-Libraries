package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewHolderTag extends ListViewHolder {
    private final boolean SUPPORT_TAG_EDITOR = Features.isEnabled(Features.SUPPORT_TAG_EDITOR);
    private ImageButton mDeleteView;
    private LinearLayout mItemLayout;
    private TextView mTitleText;

    public ViewHolderTag(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$1(View view) {
        onItemClickInternal(1001);
    }

    private void setTitleText(String str) {
        TextView textView = this.mTitleText;
        if (textView != null) {
            textView.setText(str);
            TextView textView2 = this.mTitleText;
            StringBuilder t = C0212a.t(str, ArcCommonLog.TAG_COMMA);
            t.append(this.mTitleText.getResources().getString(R.string.speak_button));
            textView2.setContentDescription(t.toString());
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setTitleText(mediaItem.getTitle());
        if (!this.SUPPORT_TAG_EDITOR) {
            ViewUtils.setVisibility(this.mDeleteView, 0);
            LinearLayout linearLayout = this.mItemLayout;
            linearLayout.setPaddingRelative(linearLayout.getPaddingStart(), 0, 0, 0);
        }
    }

    public void bindView(View view) {
        this.mTitleText = (TextView) view.findViewById(R.id.moreinfo_item_title);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.moreinfo_item_linearlayout);
        this.mItemLayout = linearLayout;
        linearLayout.setOnClickListener(new D(this, 0));
        if (!this.SUPPORT_TAG_EDITOR) {
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.moreinfo_delete_icon);
            this.mDeleteView = imageButton;
            imageButton.setOnClickListener(new D(this, 1));
        }
    }

    public void recycle() {
        super.recycle();
        setTitleText((String) null);
        ViewUtils.setVisibility(this.mDeleteView, 8);
    }

    public void setTitleMaxWidth(int i2) {
        TextView textView = this.mTitleText;
        if (textView != null) {
            textView.setMaxWidth(i2);
        }
    }
}
