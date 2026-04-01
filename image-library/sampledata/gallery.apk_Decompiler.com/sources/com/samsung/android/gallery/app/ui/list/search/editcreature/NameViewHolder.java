package com.samsung.android.gallery.app.ui.list.search.editcreature;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NameViewHolder extends ListViewHolder {
    ImageView mNameImageView;
    TextView mNameInitialView;
    TextView mNameView;
    CreatureNameData mPersonData;

    public NameViewHolder(View view) {
        super(view, 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: bindImageOnUi */
    public void lambda$bindImage$0(CreatureNameData creatureNameData, Bitmap bitmap) {
        if (!isRecycled(creatureNameData)) {
            bindImage(bitmap);
        }
    }

    public void bindImage(CreatureNameData creatureNameData, Bitmap bitmap) {
        ThreadUtil.runOnUiThread(this.mNameImageView, new C0235b(this, creatureNameData, bitmap, 10));
    }

    public void bindItem(CreatureNameData creatureNameData) {
        this.mPersonData = creatureNameData;
    }

    public final void bindView(View view) {
        this.mNameView = (TextView) view.findViewById(R.id.name_text);
        this.mNameInitialView = (TextView) view.findViewById(R.id.name_image_text);
        this.mNameImageView = (ImageView) view.findViewById(R.id.name_image);
    }

    public boolean isRecycled(CreatureNameData creatureNameData) {
        CreatureNameData creatureNameData2 = this.mPersonData;
        if (creatureNameData2 == null || !creatureNameData2.equals(creatureNameData)) {
            return true;
        }
        return false;
    }

    public void recycle() {
        this.mPersonData = null;
        this.mNameInitialView.setText((CharSequence) null);
        this.mNameImageView.setImageBitmap((Bitmap) null);
        super.recycle();
    }

    public void setImage(CreatureNameData creatureNameData) {
        int i2;
        int i7;
        Bitmap faceBitmap = creatureNameData.getFaceBitmap();
        if (faceBitmap != null) {
            this.mNameImageView.setImageBitmap(faceBitmap);
            this.mNameImageView.setClipToOutline(true);
        } else {
            String initialLetter = creatureNameData.getInitialLetter();
            Drawable contactPresetDrawable = creatureNameData.getContactPresetDrawable(this.mNameImageView.getContext());
            if (contactPresetDrawable != null) {
                this.mNameImageView.setImageDrawable(contactPresetDrawable);
                this.mNameImageView.setClipToOutline(true);
            } else {
                ImageView imageView = this.mNameImageView;
                if (initialLetter != null) {
                    i7 = R.drawable.initial_type_thumbnail;
                } else {
                    i7 = R.drawable.photo_id_default_thumbnail;
                }
                imageView.setImageResource(i7);
                this.mNameInitialView.setTextColor(this.itemView.getContext().getColor(R.color.photo_id_stroke_color));
            }
            this.mNameInitialView.setText(initialLetter);
        }
        TextView textView = this.mNameInitialView;
        if (faceBitmap != null) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        textView.setVisibility(i2);
    }

    public void setName(String str) {
        this.mNameView.setText(str);
    }

    public void bindImage(Bitmap bitmap) {
        this.mNameInitialView.setVisibility(bitmap != null ? 8 : 0);
        this.mNameImageView.setImageBitmap(bitmap);
        this.mNameImageView.setClipToOutline(true);
    }
}
