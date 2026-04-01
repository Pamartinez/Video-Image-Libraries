package com.samsung.android.gallery.widget.tag;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cc.C0589a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagViewHolder2 extends ListViewHolder {
    ImageButton mDeleteButton;
    ImageView mHashTagView;
    LinearLayout mLayout;
    TextView mTitleText;

    public MyTagViewHolder2(View view, int i2) {
        super(view, i2);
        this.itemView.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(1001);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$1(View view) {
        onItemClickInternal(1002);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (getItemViewType() == 0) {
            this.mTitleText.setText(mediaItem.getTitle());
            int i2 = 0;
            this.mHashTagView.setVisibility(0);
            ImageButton imageButton = this.mDeleteButton;
            if (Features.isEnabled(Features.SUPPORT_TAG_EDITOR)) {
                i2 = 8;
            }
            imageButton.setVisibility(i2);
        }
    }

    public void bindView(View view) {
        this.mHashTagView = (ImageView) view.findViewById(R$id.tag_sharp_icon);
        this.mDeleteButton = (ImageButton) view.findViewById(R$id.tag_item_delete);
        this.mLayout = (LinearLayout) view.findViewById(R$id.tag_item_layout);
        this.mTitleText = (TextView) view.findViewById(R$id.tag_item_title);
        this.mLayout.setOnClickListener(new C0589a(this, 0));
        this.mDeleteButton.setOnClickListener(new C0589a(this, 1));
    }

    public void recycle() {
        super.recycle();
        this.mTitleText.setText((CharSequence) null);
        this.mHashTagView.setVisibility(8);
        this.mDeleteButton.setVisibility(8);
    }
}
