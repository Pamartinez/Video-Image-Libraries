package com.samsung.android.gallery.widget.filmstrip3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoSearchView extends View {
    private long mDuration;
    private boolean mIsRtl;
    private Paint mPaint = new Paint();
    private float mSearchMargin;
    private float mSearchRadius;
    private ArrayList<Long[]> mSegmentInfoList;

    public VideoSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initValue(context);
    }

    private void initValue(Context context) {
        this.mIsRtl = Features.isEnabled(Features.IS_RTL);
        this.mSearchMargin = (float) context.getResources().getDimensionPixelSize(R$dimen.film_strip_search_margin);
        this.mSearchRadius = (float) context.getResources().getDimensionPixelSize(R$dimen.film_strip_search_corner_radius);
        this.mPaint.setColor(context.getColor(R$color.filmstrip_video_search));
    }

    public void clear() {
        this.mDuration = 0;
        this.mSegmentInfoList = null;
    }

    public void onDraw(Canvas canvas) {
        if (this.mSegmentInfoList != null) {
            float height = ((float) getHeight()) - this.mSearchMargin;
            float width = ((float) this.mDuration) / (((float) getWidth()) - (this.mSearchMargin * 2.0f));
            Iterator<Long[]> it = this.mSegmentInfoList.iterator();
            while (it.hasNext()) {
                Long[] next = it.next();
                long longValue = next[1].longValue();
                long longValue2 = next[2].longValue();
                if (longValue >= 0 && longValue2 >= 0) {
                    long j2 = this.mDuration;
                    if (longValue <= j2 && longValue2 <= j2) {
                        Pair pair = new Pair(Float.valueOf(((float) longValue) / width), Float.valueOf(((float) longValue2) / width));
                        float floatValue = ((Float) pair.first).floatValue() + this.mSearchMargin;
                        float f = this.mSearchMargin;
                        RectF rectF = new RectF(floatValue, f, ((Float) pair.second).floatValue() + f, height);
                        if (this.mIsRtl) {
                            rectF.left = ((float) getWidth()) - rectF.left;
                            rectF.right = ((float) getWidth()) - rectF.right;
                        }
                        float f5 = this.mSearchRadius;
                        canvas.drawRoundRect(rectF, f5, f5, this.mPaint);
                    }
                }
                Log.e("VideoSearchView", "range value not proper duration = " + this.mDuration + " range = " + longValue + GlobalPostProcInternalPPInterface.SPLIT_REGEX + longValue2);
            }
        }
    }

    public void setSegmentInfo(long j2, ArrayList<Long[]> arrayList) {
        this.mDuration = j2;
        this.mSegmentInfoList = arrayList;
    }
}
