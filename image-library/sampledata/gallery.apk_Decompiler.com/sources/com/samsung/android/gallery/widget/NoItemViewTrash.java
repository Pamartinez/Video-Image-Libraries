package com.samsung.android.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.samsung.android.gallery.module.trash.expired.TrashExpiredDefault;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoItemViewTrash extends NoItemView {
    public NoItemViewTrash(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDescription(context);
    }

    private void setDescription(Context context) {
        if (!Features.isEnabled(Features.IS_KNOX_MODE)) {
            setDescription(new TrashExpiredDefault().getNoItemDescription(context));
        }
    }
}
