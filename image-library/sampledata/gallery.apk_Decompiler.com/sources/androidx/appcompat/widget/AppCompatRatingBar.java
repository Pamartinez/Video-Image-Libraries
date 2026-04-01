package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import androidx.appcompat.R$attr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCompatRatingBar extends RatingBar {
    private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.ratingBarStyle);
    }

    public synchronized void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        Bitmap sampleTile = this.mAppCompatProgressBarHelper.getSampleTile();
        if (sampleTile != null) {
            setMeasuredDimension(View.resolveSizeAndState(sampleTile.getWidth() * getNumStars(), i2, 0), getMeasuredHeight());
        }
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatProgressBarHelper appCompatProgressBarHelper = new AppCompatProgressBarHelper(this);
        this.mAppCompatProgressBarHelper = appCompatProgressBarHelper;
        appCompatProgressBarHelper.loadFromAttributes(attributeSet, i2);
    }
}
