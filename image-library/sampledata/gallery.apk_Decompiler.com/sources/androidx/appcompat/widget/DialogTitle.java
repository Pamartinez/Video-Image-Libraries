package androidx.appcompat.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import androidx.appcompat.R$dimen;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DialogTitle extends AppCompatTextView {
    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onMeasure(int i2, int i7) {
        int lineCount;
        super.onMeasure(i2, i7);
        Layout layout = getLayout();
        if (layout != null && (lineCount = layout.getLineCount()) > 0 && layout.getEllipsisCount(lineCount - 1) > 0) {
            setSingleLine(false);
            setMaxLines(2);
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.sesl_dialog_title_text_size);
            if (dimensionPixelSize != 0) {
                float f = getContext().getResources().getConfiguration().fontScale;
                float f5 = (float) dimensionPixelSize;
                if (f > 1.3f) {
                    f5 = (f5 / f) * 1.3f;
                }
                setTextSize(0, f5);
            }
            super.onMeasure(i2, i7);
        }
    }
}
