package androidx.appcompat.widget;

import android.widget.TextView;
import androidx.core.util.Preconditions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class AppCompatTextClassifierHelper {
    private TextView mTextView;

    public AppCompatTextClassifierHelper(TextView textView) {
        this.mTextView = (TextView) Preconditions.checkNotNull(textView);
    }
}
