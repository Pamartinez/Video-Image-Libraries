package com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui;

import Ae.b;
import B1.a;
import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.y;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/AccurateWidthTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lme/x;", "onMeasureInternal", "()V", "Landroid/text/Layout;", "layout", "", "getMaxLineWidth", "(Landroid/text/Layout;)F", "Landroid/graphics/Canvas;", "canvas", "xTranslation", "Lkotlin/Function1;", "drawingAction", "drawTranslatedHorizontally", "(Landroid/graphics/Canvas;ILAe/b;)V", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "onDraw", "(Landroid/graphics/Canvas;)V", "getCompoundPaddingRight", "()I", "extraPaddingRight", "Ljava/lang/Integer;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AccurateWidthTextView extends AppCompatTextView {
    private Integer extraPaddingRight;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment r1 = com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment r1 = com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.ExplicitLayoutAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.AccurateWidthTextView.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AccurateWidthTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (e) null);
        j.e(context, "context");
    }

    private final void drawTranslatedHorizontally(Canvas canvas, int i2, b bVar) {
        this.extraPaddingRight = Integer.valueOf(i2);
        canvas.save();
        canvas.translate((float) i2, 0.0f);
        bVar.invoke(canvas);
        this.extraPaddingRight = null;
        canvas.restore();
    }

    private final float getMaxLineWidth(Layout layout) {
        Float f;
        Iterator it = a.Z(0, layout.getLineCount()).iterator();
        if (!it.hasNext()) {
            f = null;
        } else {
            y yVar = (y) it;
            float lineWidth = layout.getLineWidth(yVar.nextInt());
            while (it.hasNext()) {
                lineWidth = Math.max(lineWidth, layout.getLineWidth(yVar.nextInt()));
            }
            f = Float.valueOf(lineWidth);
        }
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    /* access modifiers changed from: private */
    public static final x onDraw$lambda$1(AccurateWidthTextView accurateWidthTextView, Canvas canvas) {
        j.e(canvas, "it");
        super.onDraw(canvas);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x onDraw$lambda$2(AccurateWidthTextView accurateWidthTextView, Canvas canvas) {
        j.e(canvas, "it");
        super.onDraw(canvas);
        return x.f4917a;
    }

    private final void onMeasureInternal() {
        Layout layout = getLayout();
        j.d(layout, "getLayout(...)");
        setMeasuredDimension(getMeasuredWidth() - (getLayout().getWidth() - ((int) ((float) Math.ceil((double) getMaxLineWidth(layout))))), getMeasuredHeight());
    }

    public int getCompoundPaddingRight() {
        Integer num = this.extraPaddingRight;
        if (num != null) {
            return num.intValue();
        }
        return super.getCompoundPaddingRight();
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        if (getLayout() == null || getLayout().getLineCount() < 2) {
            super.onDraw(canvas);
            return;
        }
        Layout layout = getLayout();
        j.d(layout, "getLayout(...)");
        ExplicitLayoutAlignment access$getExplicitAlignment = AccurateWidthTextViewKt.getExplicitAlignment(layout);
        if (access$getExplicitAlignment == ExplicitLayoutAlignment.MIXED) {
            super.onDraw(canvas);
            return;
        }
        int width = getLayout().getWidth();
        Layout layout2 = getLayout();
        j.d(layout2, "getLayout(...)");
        int ceil = (int) ((float) Math.ceil((double) getMaxLineWidth(layout2)));
        if (width == ceil) {
            super.onDraw(canvas);
            return;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[access$getExplicitAlignment.ordinal()];
        if (i2 == 1) {
            drawTranslatedHorizontally(canvas, (width - ceil) * -1, new x3.a(this, 0));
        } else if (i2 != 2) {
            super.onDraw(canvas);
        } else {
            drawTranslatedHorizontally(canvas, ((width - ceil) * -1) / 2, new x3.a(this, 1));
        }
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        if (getLayout() != null && getLayout().getLineCount() >= 2) {
            onMeasureInternal();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AccurateWidthTextView(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AccurateWidthTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
    }
}
