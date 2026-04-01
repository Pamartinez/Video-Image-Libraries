package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CompositeCapsuleLayout;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "capsuleColor", "Lme/x;", "bind", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)V", "Landroid/view/View;", "view", "addCapsuleView", "(Landroid/view/View;)V", "ignoreMarginOfLastAddedView", "()V", "containerLayout", "Landroid/widget/LinearLayout;", "getContainerLayout", "()Landroid/widget/LinearLayout;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CompositeCapsuleLayout extends LinearLayout {
    public static final Companion Companion = new Companion((e) null);
    private final LinearLayout containerLayout;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CompositeCapsuleLayout$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CompositeCapsuleLayout(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    public final void addCapsuleView(View view) {
        j.e(view, "view");
        this.containerLayout.addView(view);
    }

    public final void bind(CapsuleColor capsuleColor) {
        j.e(capsuleColor, "capsuleColor");
        this.containerLayout.getBackground().setTint(capsuleColor.getEntityBackgroundColor());
    }

    public final LinearLayout getContainerLayout() {
        return this.containerLayout;
    }

    public final void ignoreMarginOfLastAddedView() {
        LinearLayout linearLayout = this.containerLayout;
        View findViewById = linearLayout.getChildAt(linearLayout.getChildCount() - 1).findViewById(R$id.text_extraction_capsule_simple);
        j.d(findViewById, "findViewById(...)");
        ViewGroup.LayoutParams layoutParams = ((LinearLayout) findViewById).getLayoutParams();
        j.c(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        ((LinearLayout.LayoutParams) layoutParams).setMarginEnd(0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CompositeCapsuleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
        setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 80;
        setLayoutParams(layoutParams);
        LayoutInflater.from(context).inflate(R$layout.composite_capsule_view, this, true);
        View findViewById = findViewById(R$id.inner_container);
        j.d(findViewById, "findViewById(...)");
        this.containerLayout = (LinearLayout) findViewById;
    }
}
