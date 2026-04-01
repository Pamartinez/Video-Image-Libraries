package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import a6.C0419b;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.R$layout;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.util.RoleDescriptionAccessibilityDelegate;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import m3.a;
import m3.b;
import me.i;
import me.x;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 62\u00020\u0001:\u00016B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0012J7\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\fH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001f\u0010\u0012J\u001d\u0010$\u001a\u00020\f2\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010'R\u0014\u0010,\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b,\u0010'R\u001c\u0010/\u001a\n .*\u0004\u0018\u00010-0-8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\"8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u00105¨\u00067"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/ExpandableCapsuleLayout;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "capsule", "Lme/x;", "bindIconView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "capsuleColor", "bindSingleItemView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)V", "bindMultipleItemView", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "type", "Landroid/net/Uri;", "icon", "", "text", "parentLayout", "createItemView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;Landroid/net/Uri;Ljava/lang/String;Landroid/widget/LinearLayout;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)Landroid/widget/LinearLayout;", "onDetachedFromWindow", "()V", "bind", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "state", "", "isCollapsingAnimationNeeded", "handleExpandedEvent", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;Z)V", "layout", "Landroid/widget/LinearLayout;", "Landroid/widget/ImageView;", "iconView", "Landroid/widget/ImageView;", "singleItemView", "multipleItemView", "Landroid/view/LayoutInflater;", "kotlin.jvm.PlatformType", "inflater", "Landroid/view/LayoutInflater;", "hasSingleItem", "Z", "Landroid/animation/AnimatorSet;", "currentAnimator", "Landroid/animation/AnimatorSet;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExpandableCapsuleLayout extends LinearLayout {
    public static final Companion Companion = new Companion((e) null);
    private AnimatorSet currentAnimator;
    private boolean hasSingleItem;
    private final ImageView iconView;
    private final LayoutInflater inflater;
    private final LinearLayout layout;
    /* access modifiers changed from: private */
    public final LinearLayout multipleItemView;
    /* access modifiers changed from: private */
    public final LinearLayout singleItemView;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/ExpandableCapsuleLayout$Companion;", "", "<init>", "()V", "TAG", "", "MAX_ITEMS", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExpandableCapsuleLayout(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    private final void bindIconView(ExpandableCapsule expandableCapsule) {
        this.iconView.setImageURI(expandableCapsule.getIcon());
        this.iconView.setOnClickListener(new C0419b(20, expandableCapsule));
        this.iconView.setTooltipText(expandableCapsule.getTitle());
        this.iconView.setContentDescription(expandableCapsule.getTitle());
        ImageView imageView = this.iconView;
        String string = getContext().getResources().getString(R$string.capsule_role_button);
        j.d(string, "getString(...)");
        ViewCompat.setAccessibilityDelegate(imageView, new RoleDescriptionAccessibilityDelegate(string));
    }

    /* access modifiers changed from: private */
    public static final void bindIconView$lambda$2(ExpandableCapsule expandableCapsule, View view) {
        expandableCapsule.expand();
    }

    private final void bindMultipleItemView(ExpandableCapsule expandableCapsule, CapsuleColor capsuleColor) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.expandable_capsule_multiple_item_view);
        for (Map.Entry entry : C1194l.Z0(expandableCapsule.getContents().entrySet())) {
            j.b(linearLayout);
            ExpandableCapsuleLayout expandableCapsuleLayout = this;
            CapsuleColor capsuleColor2 = capsuleColor;
            LinearLayout createItemView = expandableCapsuleLayout.createItemView(expandableCapsule.getActionType(), expandableCapsule.getIcon(), (String) ((i) entry.getValue()).d, linearLayout, capsuleColor2);
            createItemView.setOnClickListener(new a(expandableCapsule, (String) entry.getKey(), 1));
            linearLayout.addView(createItemView, 0);
            createItemView.setTooltipText(expandableCapsule.getTitle());
            createItemView.setContentDescription(expandableCapsule.getTitle());
            this = expandableCapsuleLayout;
            capsuleColor = capsuleColor2;
        }
        ExpandableCapsuleLayout expandableCapsuleLayout2 = this;
        LinearLayout linearLayout2 = (LinearLayout) expandableCapsuleLayout2.multipleItemView.findViewById(R$id.expandable_capsule_collapse_layout);
        linearLayout2.setOnClickListener(new b(expandableCapsuleLayout2, linearLayout2, expandableCapsule, 1));
        ImageView imageView = (ImageView) expandableCapsuleLayout2.multipleItemView.findViewById(R$id.expandable_capsule_collapse_icon);
        if (imageView.getResources().getConfiguration().getLayoutDirection() == 1) {
            imageView.setScaleX(-1.0f);
        }
    }

    /* access modifiers changed from: private */
    public static final void bindMultipleItemView$lambda$12$lambda$11$lambda$10(ExpandableCapsule expandableCapsule, String str, View view) {
        expandableCapsule.clickItem(str);
    }

    /* access modifiers changed from: private */
    public static final void bindMultipleItemView$lambda$15$lambda$14(ExpandableCapsuleLayout expandableCapsuleLayout, LinearLayout linearLayout, ExpandableCapsule expandableCapsule, View view) {
        TextCapsuleAnimator textCapsuleAnimator = TextCapsuleAnimator.INSTANCE;
        Context context = linearLayout.getContext();
        j.d(context, "getContext(...)");
        AnimatorSet hideMoreCapsuleListAnimation = textCapsuleAnimator.hideMoreCapsuleListAnimation(context, expandableCapsuleLayout.multipleItemView, expandableCapsule.getContents().size());
        expandableCapsuleLayout.currentAnimator = hideMoreCapsuleListAnimation;
        if (hideMoreCapsuleListAnimation != null) {
            hideMoreCapsuleListAnimation.start();
        }
        AnimatorSet animatorSet = expandableCapsuleLayout.currentAnimator;
        if (animatorSet != null) {
            animatorSet.addListener(new ExpandableCapsuleLayout$bindMultipleItemView$lambda$15$lambda$14$$inlined$doOnEnd$1(expandableCapsuleLayout));
        }
    }

    private final void bindSingleItemView(ExpandableCapsule expandableCapsule, CapsuleColor capsuleColor) {
        x xVar;
        CharSequence charSequence;
        Iterator<Map.Entry<String, i>> it = expandableCapsule.getContents().entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry next = it.next();
            String str = (String) next.getKey();
            i iVar = (i) next.getValue();
            ImageView imageView = (ImageView) this.singleItemView.findViewById(R$id.expandable_capsule_item_icon);
            imageView.setImageTintList(ColorStateList.valueOf(capsuleColor.getEntityTintColor()));
            imageView.setImageURI(expandableCapsule.getIcon());
            TextView textView = (TextView) this.singleItemView.findViewById(R$id.expandable_capsule_item_text);
            if (C1192j.z0(new CapsuleActionType[]{CapsuleActionType.ENTITY_CALL, CapsuleActionType.ENTITY_SEND_MESSAGE, CapsuleActionType.ENTITY_ADD_CONTACT}).contains(expandableCapsule.getActionType())) {
                charSequence = BidiFormatter.getInstance().unicodeWrap((String) iVar.d, TextDirectionHeuristics.LTR);
            } else {
                charSequence = (CharSequence) iVar.d;
            }
            textView.setText(charSequence);
            textView.setTextColor(capsuleColor.getEntityTintColor());
            this.singleItemView.setOnClickListener(new a(expandableCapsule, str, 0));
            this.singleItemView.setTooltipText(expandableCapsule.getTitle());
            this.singleItemView.setContentDescription(expandableCapsule.getTitle());
            ImageView imageView2 = (ImageView) this.singleItemView.findViewById(R$id.expandable_capsule_more_icon);
            if (imageView2.getResources().getConfiguration().getLayoutDirection() == 1) {
                imageView2.setScaleX(-1.0f);
            }
            LinearLayout linearLayout = this.singleItemView;
            String string = getContext().getResources().getString(R$string.capsule_role_button);
            j.d(string, "getString(...)");
            ViewCompat.setAccessibilityDelegate(linearLayout, new RoleDescriptionAccessibilityDelegate(string));
            xVar = x.f4917a;
        } else {
            xVar = null;
        }
        if (xVar == null) {
            throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
        } else if (!this.hasSingleItem) {
            LinearLayout linearLayout2 = (LinearLayout) this.singleItemView.findViewById(R$id.expandable_capsule_more_icon_layout);
            linearLayout2.setVisibility(0);
            linearLayout2.setOnClickListener(new b(this, linearLayout2, expandableCapsule, 0));
        }
    }

    /* access modifiers changed from: private */
    public static final void bindSingleItemView$lambda$7$lambda$5(ExpandableCapsule expandableCapsule, String str, View view) {
        expandableCapsule.clickItem(str);
    }

    /* access modifiers changed from: private */
    public static final void bindSingleItemView$lambda$9$lambda$8(ExpandableCapsuleLayout expandableCapsuleLayout, LinearLayout linearLayout, ExpandableCapsule expandableCapsule, View view) {
        expandableCapsuleLayout.singleItemView.setVisibility(8);
        int i2 = 0;
        expandableCapsuleLayout.multipleItemView.setVisibility(0);
        int dimensionPixelSize = linearLayout.getContext().getResources().getDimensionPixelSize(R$dimen.capsule_extract_text_action_view_height);
        if (expandableCapsule.getContents().size() > 5) {
            i2 = dimensionPixelSize * 6;
        }
        TextCapsuleAnimator textCapsuleAnimator = TextCapsuleAnimator.INSTANCE;
        Context context = linearLayout.getContext();
        j.d(context, "getContext(...)");
        AnimatorSet showMoreCapsuleListAnimation = textCapsuleAnimator.showMoreCapsuleListAnimation(context, expandableCapsuleLayout.singleItemView.getWidth(), expandableCapsuleLayout.multipleItemView, i2, expandableCapsule.getContents().size());
        expandableCapsuleLayout.currentAnimator = showMoreCapsuleListAnimation;
        if (showMoreCapsuleListAnimation != null) {
            showMoreCapsuleListAnimation.start();
        }
    }

    private final LinearLayout createItemView(CapsuleActionType capsuleActionType, Uri uri, String str, LinearLayout linearLayout, CapsuleColor capsuleColor) {
        View inflate = this.inflater.inflate(R$layout.expandable_capsule_item, linearLayout, false);
        j.c(inflate, "null cannot be cast to non-null type android.widget.LinearLayout");
        LinearLayout linearLayout2 = (LinearLayout) inflate;
        ImageView imageView = (ImageView) linearLayout2.findViewById(R$id.expandable_capsule_list_item_icon);
        TextView textView = (TextView) linearLayout2.findViewById(R$id.expandable_capsule_list_item_text);
        imageView.setImageURI(uri);
        if (capsuleActionType == CapsuleActionType.ENTITY_CALL) {
            str = BidiFormatter.getInstance().unicodeWrap(str, TextDirectionHeuristics.LTR);
        }
        textView.setText(str);
        imageView.setImageTintList(ColorStateList.valueOf(capsuleColor.getEntityTintColor()));
        textView.setTextColor(capsuleColor.getEntityTintColor());
        linearLayout2.setClickable(true);
        linearLayout2.setFocusable(true);
        String string = getContext().getResources().getString(R$string.capsule_role_button);
        j.d(string, "getString(...)");
        ViewCompat.setAccessibilityDelegate(linearLayout2, new RoleDescriptionAccessibilityDelegate(string));
        return linearLayout2;
    }

    public final void bind(ExpandableCapsule expandableCapsule, CapsuleColor capsuleColor) {
        boolean z;
        j.e(expandableCapsule, "capsule");
        j.e(capsuleColor, "capsuleColor");
        this.layout.setTooltipText(expandableCapsule.getTitle());
        this.layout.setContentDescription(expandableCapsule.getTitle());
        LinearLayout linearLayout = this.layout;
        String string = getContext().getResources().getString(R$string.capsule_role_button);
        j.d(string, "getString(...)");
        ViewCompat.setAccessibilityDelegate(linearLayout, new RoleDescriptionAccessibilityDelegate(string));
        LinearLayout linearLayout2 = this.layout;
        Drawable mutate = linearLayout2.getBackground().mutate();
        j.c(mutate, "null cannot be cast to non-null type android.graphics.drawable.RippleDrawable");
        RippleDrawable rippleDrawable = (RippleDrawable) mutate;
        rippleDrawable.setTint(capsuleColor.getEntityBackgroundColor());
        rippleDrawable.setColor(ColorStateList.valueOf(capsuleColor.getRippleColor()));
        linearLayout2.setBackground(rippleDrawable);
        bindIconView(expandableCapsule);
        if (expandableCapsule.getContents().size() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.hasSingleItem = z;
        bindSingleItemView(expandableCapsule, capsuleColor);
        if (expandableCapsule.getContents().size() > 1) {
            bindMultipleItemView(expandableCapsule, capsuleColor);
        }
        handleExpandedEvent(expandableCapsule.getCurrentState(), true);
    }

    public final void handleExpandedEvent(ExpandState expandState, boolean z) {
        j.e(expandState, "state");
        TextView textView = (TextView) this.singleItemView.findViewById(R$id.expandable_capsule_item_text);
        this.singleItemView.setVisibility(0);
        this.multipleItemView.setVisibility(8);
        AnimatorSet animatorSet = this.currentAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        if (expandState instanceof ExpandState.Expanded) {
            this.iconView.setVisibility(8);
            TextCapsuleAnimator textCapsuleAnimator = TextCapsuleAnimator.INSTANCE;
            Context context = getContext();
            j.d(context, "getContext(...)");
            LinearLayout linearLayout = this.singleItemView;
            j.b(textView);
            this.currentAnimator = textCapsuleAnimator.getShowAnimation(context, linearLayout, textView, this.hasSingleItem);
        } else if (!(expandState instanceof ExpandState.Collapsed)) {
            throw new RuntimeException();
        } else if (z) {
            this.iconView.setVisibility(0);
            this.singleItemView.setVisibility(8);
            this.multipleItemView.setVisibility(8);
            return;
        } else {
            this.iconView.setVisibility(8);
            TextCapsuleAnimator textCapsuleAnimator2 = TextCapsuleAnimator.INSTANCE;
            Context context2 = getContext();
            j.d(context2, "getContext(...)");
            LinearLayout linearLayout2 = this.singleItemView;
            j.b(textView);
            this.currentAnimator = textCapsuleAnimator2.getHideAnimation(context2, linearLayout2, textView, this.iconView, this.hasSingleItem);
        }
        AnimatorSet animatorSet2 = this.currentAnimator;
        if (animatorSet2 != null) {
            animatorSet2.start();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.currentAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.currentAnimator = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpandableCapsuleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
        this.inflater = LayoutInflater.from(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 80;
        setLayoutParams(layoutParams);
        LayoutInflater.from(context).inflate(R$layout.expandable_capsule_view, this, true);
        View findViewById = findViewById(R$id.expandable_capsule_root);
        j.d(findViewById, "findViewById(...)");
        this.layout = (LinearLayout) findViewById;
        View findViewById2 = findViewById(R$id.expandable_capsule_single_icon);
        j.d(findViewById2, "findViewById(...)");
        this.iconView = (ImageView) findViewById2;
        View findViewById3 = findViewById(R$id.expandable_capsule_single_item);
        j.d(findViewById3, "findViewById(...)");
        this.singleItemView = (LinearLayout) findViewById3;
        View findViewById4 = findViewById(R$id.expandable_capsule_multiple_item);
        j.d(findViewById4, "findViewById(...)");
        this.multipleItemView = (LinearLayout) findViewById4;
    }
}
