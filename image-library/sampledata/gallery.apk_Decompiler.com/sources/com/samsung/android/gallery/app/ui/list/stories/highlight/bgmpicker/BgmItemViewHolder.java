package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Consumer<BgmItemViewHolder> mClickConsumer;
    private ImageView mDownloadIcon;
    private View mEqualizer;
    private AnimationDrawable mEqualizerAnimation;
    private View mProgressCircle;
    private TextView mTitle;

    public BgmItemViewHolder(View view) {
        super(view);
        initView(view);
    }

    private void initView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mDownloadIcon = (ImageView) view.findViewById(R.id.download_icon);
        this.mProgressCircle = view.findViewById(R.id.progress_circle);
        View findViewById = view.findViewById(R.id.equalizer);
        this.mEqualizer = findViewById;
        this.mEqualizerAnimation = (AnimationDrawable) findViewById.getBackground();
    }

    public void enableDownloadIcon(boolean z) {
        int i2;
        ImageView imageView = this.mDownloadIcon;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(imageView, i2);
        ViewUtils.setVisibility(this.mProgressCircle, 8);
    }

    public void enableEqualizer(boolean z) {
        int i2;
        View view = this.mEqualizer;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        if (z) {
            this.mEqualizerAnimation.start();
        } else {
            this.mEqualizerAnimation.stop();
        }
    }

    public void enableProgressCircle(boolean z) {
        int i2;
        View view = this.mProgressCircle;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        ViewUtils.setVisibility(this.mDownloadIcon, 8);
    }

    public String getTitle() {
        return (String) this.mTitle.getText();
    }

    public void onClick(View view) {
        Consumer<BgmItemViewHolder> consumer = this.mClickConsumer;
        if (consumer != null) {
            consumer.accept(this);
        }
    }

    public void recycle() {
        this.itemView.setOnClickListener((View.OnClickListener) null);
        this.mClickConsumer = null;
        ViewUtils.setVisibility(this.mProgressCircle, 8);
        ViewUtils.setVisibility(this.mDownloadIcon, 8);
    }

    public void setFocused(boolean z, boolean z3) {
        int i2;
        boolean z7;
        TextView textView = this.mTitle;
        Context context = this.itemView.getContext();
        if (z) {
            i2 = R.color.story_highlight_bgm_selected_title_color;
        } else {
            i2 = R.color.story_highlight_bgm_unselected_title_color;
        }
        textView.setTextColor(context.getColor(i2));
        if (!z || !z3) {
            z7 = false;
        } else {
            z7 = true;
        }
        enableEqualizer(z7);
    }

    public void setOnClickListener(Consumer<BgmItemViewHolder> consumer) {
        this.mClickConsumer = consumer;
        this.itemView.setOnClickListener(this);
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
        this.itemView.setContentDescription(str);
    }
}
