package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import A4.C0378m;
import Tf.v;
import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.DrawUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001d\u0010\u001f\"\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/DragAndDropHelper;", "", "Landroid/content/Context;", "context", "Landroid/view/View;", "teView", "<init>", "(Landroid/content/Context;Landroid/view/View;)V", "", "dragData", "Landroid/widget/TextView;", "createShadowTextView", "(Ljava/lang/String;)Landroid/widget/TextView;", "textView", "Landroid/view/View$DragShadowBuilder;", "createDragShadowBuilder", "(Landroid/widget/TextView;)Landroid/view/View$DragShadowBuilder;", "getTrimmedDragData", "(Ljava/lang/String;)Ljava/lang/String;", "Lme/x;", "startDragAndDrop", "(Ljava/lang/String;)V", "Landroid/content/Context;", "Landroid/view/View;", "", "shadowMaxWidth", "I", "shadowMaxHeight", "", "isDragAndDropEnabled", "Z", "()Z", "setDragAndDropEnabled", "(Z)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DragAndDropHelper {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private boolean isDragAndDropEnabled = true;
    private final int shadowMaxHeight;
    private final int shadowMaxWidth;
    private final View teView;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/DragAndDropHelper$Companion;", "", "<init>", "()V", "TAG", "", "CLIP_DATA_LABEL", "MAX_LINES", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public DragAndDropHelper(Context context2, View view) {
        j.e(context2, "context");
        j.e(view, "teView");
        this.context = context2;
        this.teView = view;
        DrawUtil drawUtil = DrawUtil.INSTANCE;
        this.shadowMaxWidth = drawUtil.convertDpToPx(context2, Float.valueOf(context2.getResources().getDimension(R$dimen.drag_and_drop_shadow_max_width)));
        this.shadowMaxHeight = drawUtil.convertDpToPx(context2, Float.valueOf(context2.getResources().getDimension(R$dimen.drag_and_drop_shadow_max_height)));
    }

    private final View.DragShadowBuilder createDragShadowBuilder(TextView textView) {
        return new DragAndDropHelper$createDragShadowBuilder$1(textView);
    }

    private final TextView createShadowTextView(String str) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setText(str);
        textView.setGravity(16);
        DrawUtil drawUtil = DrawUtil.INSTANCE;
        Context context2 = textView.getContext();
        j.d(context2, "getContext(...)");
        int convertDpToPx = drawUtil.convertDpToPx(context2, Float.valueOf(textView.getContext().getResources().getDimension(R$dimen.drag_and_drop_shadow_padding)));
        textView.setPadding(convertDpToPx, convertDpToPx, convertDpToPx, convertDpToPx);
        textView.setTextSize(2, textView.getContext().getResources().getDimension(R$dimen.drag_and_drop_text_size));
        textView.setTextColor(textView.getContext().getColor(R$color.drag_and_drop_text_color));
        textView.setMaxWidth(this.shadowMaxWidth);
        textView.setMaxLines(3);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setBackground(ResourcesCompat.getDrawable(textView.getContext().getResources(), R$drawable.drag_and_drop_background, (Resources.Theme) null));
        return textView;
    }

    private final String getTrimmedDragData(String str) {
        return v.s0(str, "\n", " ");
    }

    /* access modifiers changed from: private */
    public static final boolean startDragAndDrop$lambda$0(DragAndDropHelper dragAndDropHelper, View view, DragEvent dragEvent) {
        if (dragEvent.getAction() != 1) {
            return false;
        }
        if (dragEvent.getClipDescription().hasMimeType("text/plain")) {
            dragAndDropHelper.getClass();
        }
        return true;
    }

    public final void startDragAndDrop(String str) {
        j.e(str, "dragData");
        if (this.isDragAndDropEnabled) {
            TextView createShadowTextView = createShadowTextView(getTrimmedDragData(str));
            createShadowTextView.measure(View.MeasureSpec.makeMeasureSpec(this.shadowMaxWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.shadowMaxHeight, Integer.MIN_VALUE));
            createShadowTextView.layout(0, 0, createShadowTextView.getMeasuredWidth(), createShadowTextView.getMeasuredHeight());
            int measuredWidth = createShadowTextView.getMeasuredWidth();
            int measuredHeight = createShadowTextView.getMeasuredHeight();
            LibLogger.i("DragAndDropHelper", "shadow width: " + measuredWidth + ", height: " + measuredHeight);
            this.teView.startDragAndDrop(ClipData.newPlainText("text", str), createDragShadowBuilder(createShadowTextView), (Object) null, 256);
            this.teView.setOnDragListener(new C0378m(4, this));
        }
    }
}
