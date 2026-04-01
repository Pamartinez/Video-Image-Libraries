package androidx.emoji2.viewsintegration;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class EmojiTransformationMethod implements TransformationMethod {
    private final TransformationMethod mTransformationMethod;

    public EmojiTransformationMethod(TransformationMethod transformationMethod) {
        this.mTransformationMethod = transformationMethod;
    }

    public TransformationMethod getOriginalTransformationMethod() {
        return this.mTransformationMethod;
    }

    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.mTransformationMethod;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        if (charSequence == null || EmojiCompat.get().getLoadState() != 1) {
            return charSequence;
        }
        return EmojiCompat.get().process(charSequence);
    }

    public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i2, Rect rect) {
        TransformationMethod transformationMethod = this.mTransformationMethod;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z, i2, rect);
        }
    }
}
