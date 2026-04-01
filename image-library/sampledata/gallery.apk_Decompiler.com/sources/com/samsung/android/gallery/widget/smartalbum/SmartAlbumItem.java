package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$styleable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SmartAlbumItem extends RelativeLayout {
    protected Blackboard mBlackboard;
    protected ImageView mImageView;
    protected FrameLayout mImageViewFrame;
    protected TextView mImageViewNew;
    protected TextView mTitleView;

    public SmartAlbumItem(Context context) {
        this(context, (AttributeSet) null);
    }

    private void resizeCircleIcon(int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ((ViewGroup) this.mImageView.getParent()).getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        ((ViewGroup) this.mImageView.getParent()).setLayoutParams(layoutParams);
    }

    public String appendAlbumId(String str, String str2) {
        return ArgumentsUtil.appendArgs(str, "id", String.valueOf(FileUtils.getBucketId(str2)));
    }

    public void bindView(View view) {
        this.mImageView = (ImageView) view.findViewById(R$id.smart_album_image);
        this.mTitleView = (TextView) view.findViewById(R$id.smart_album_title);
        this.mImageViewNew = (TextView) view.findViewById(R$id.smart_album_image_new);
        this.mImageViewFrame = (FrameLayout) view.findViewById(R$id.smart_album_image_layout);
    }

    public abstract String getAnalyticsId();

    public abstract int getDrawableId();

    public abstract int getTitleStringId();

    public abstract int getType();

    public abstract void handleOnClick(boolean z);

    public boolean isEnableInPickMode() {
        return true;
    }

    public boolean isVisibleInPickMode() {
        return true;
    }

    public void moveTo(String str) {
        this.mBlackboard.post("command://MoveURL", str);
    }

    public void setBlackboard(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public void setImageDrawable(Drawable drawable) {
        this.mImageView.setImageDrawable(drawable);
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
    }

    public void updateLayout(int i2, boolean z, boolean z3) {
        float f;
        int i7;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        if (z3) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        layoutParams.weight = f;
        if (z3) {
            i7 = i2;
        } else {
            i7 = 0;
        }
        layoutParams.width = i7;
        if (z && ((float) i2) < ((float) getResources().getDimensionPixelOffset(R$dimen.smart_album_item_circle_size)) * 1.1f) {
            resizeCircleIcon((int) (((double) i2) * 0.85d));
        }
        setLayoutParams(layoutParams);
    }

    public boolean usingFixedImageAndTitle() {
        return true;
    }

    public SmartAlbumItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartAlbumItem(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SmartAlbumItem(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        bindView(LayoutInflater.from(getContext()).inflate(R$layout.smart_album_item, this, true));
        if (usingFixedImageAndTitle() && attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SmartAlbumItem);
            setImageDrawable(obtainStyledAttributes.getDrawable(R$styleable.SmartAlbumItem_albumImage));
            this.mImageView.setClipToOutline(true);
            setTitle(obtainStyledAttributes.getString(R$styleable.SmartAlbumItem_albumTitle));
            obtainStyledAttributes.recycle();
        }
    }

    public void updateDetails() {
    }

    public void setPosition(int i2) {
    }
}
