package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import a6.C0419b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SimpleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.util.RoleDescriptionAccessibilityDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/SimpleCapsuleLayout;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "capsule", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "capsuleColor", "Lme/x;", "bind", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)V", "layout", "Landroid/widget/LinearLayout;", "Landroid/widget/ImageView;", "iconView", "Landroid/widget/ImageView;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SimpleCapsuleLayout extends LinearLayout {
    public static final Companion Companion = new Companion((e) null);
    private final ImageView iconView;
    private final LinearLayout layout;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/SimpleCapsuleLayout$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SimpleCapsuleLayout(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    /* access modifiers changed from: private */
    public static final void bind$lambda$2(SimpleCapsule simpleCapsule, View view) {
        simpleCapsule.click();
    }

    public final void bind(SimpleCapsule simpleCapsule, CapsuleColor capsuleColor) {
        j.e(simpleCapsule, "capsule");
        j.e(capsuleColor, "capsuleColor");
        this.iconView.setImageURI(simpleCapsule.getIcon());
        this.layout.setTooltipText(simpleCapsule.getTitle());
        this.layout.setContentDescription(simpleCapsule.getTitle());
        LinearLayout linearLayout = this.layout;
        String string = getContext().getResources().getString(R$string.capsule_role_button);
        j.d(string, "getString(...)");
        ViewCompat.setAccessibilityDelegate(linearLayout, new RoleDescriptionAccessibilityDelegate(string));
        LinearLayout linearLayout2 = this.layout;
        Drawable mutate = linearLayout2.getBackground().mutate();
        j.c(mutate, "null cannot be cast to non-null type android.graphics.drawable.RippleDrawable");
        RippleDrawable rippleDrawable = (RippleDrawable) mutate;
        rippleDrawable.setTint(capsuleColor.getGeneralBackgroundColor());
        rippleDrawable.setColor(ColorStateList.valueOf(capsuleColor.getRippleColor()));
        linearLayout2.setBackground(rippleDrawable);
        this.iconView.setImageTintList(ColorStateList.valueOf(capsuleColor.getGeneralTintColor()));
        this.layout.setOnClickListener(new C0419b(21, simpleCapsule));
        this.layout.setClickable(true);
        this.layout.setFocusable(true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleCapsuleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 80;
        setLayoutParams(layoutParams);
        LayoutInflater.from(context).inflate(R$layout.simple_capsule_view, this, true);
        View findViewById = findViewById(R$id.text_extraction_capsule_simple);
        j.d(findViewById, "findViewById(...)");
        this.layout = (LinearLayout) findViewById;
        View findViewById2 = findViewById(R$id.text_extraction_capsule_simple_icon);
        j.d(findViewById2, "findViewById(...)");
        this.iconView = (ImageView) findViewById2;
    }
}
