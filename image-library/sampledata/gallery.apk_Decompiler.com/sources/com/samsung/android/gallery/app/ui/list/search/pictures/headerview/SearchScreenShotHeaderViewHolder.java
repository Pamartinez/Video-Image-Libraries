package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import z5.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchScreenShotHeaderViewHolder extends RecyclerView.ViewHolder {
    private View mButton;
    private View.OnClickListener mClickListener;
    private TextView mTextView;

    public SearchScreenShotHeaderViewHolder(View view) {
        super(view);
        bindView(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        this.mClickListener.onClick(view);
    }

    public void bind(MediaItem mediaItem) {
        this.mTextView.setText(mediaItem.getTitle());
    }

    public void bindView(View view) {
        this.mButton = view.findViewById(R.id.screen_shot_filter_button);
        this.mTextView = (TextView) view.findViewById(R.id.screen_shot_filter_text_view);
        this.mButton.setOnClickListener(new f(1, this));
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
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
    }
}
