package androidx.appcompat.widget;

import Ba.g;
import T1.d;
import a6.C0419b;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 22\u00020\u0001:\u0003234B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u001b\u0010\u0013\u001a\u00020\r2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001c8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001fR.\u0010\"\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R.\u0010(\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b(\u0010#\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R*\u0010+\u001a\u00020\b2\u0006\u0010!\u001a\u00020\b8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u0010\u0017R\u0011\u00101\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b0\u0010.¨\u00065"}, d2 = {"Landroidx/appcompat/widget/SeslIndicator;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "sizeType", "Landroidx/appcompat/widget/SeslIndicator$PageIndicatorMarker;", "generateDotIndicator", "(Ljava/lang/Integer;)Landroidx/appcompat/widget/SeslIndicator$PageIndicatorMarker;", "Lme/x;", "invalidateIndicator", "()V", "getAppBarViewPagerIndicatorOffColor", "(Landroid/content/Context;)I", "getAppBarViewPagerIndicatorOnColor", "addIndicator", "(Ljava/lang/Integer;)V", "position", "removeIndicator", "(I)V", "Landroidx/appcompat/widget/SeslIndicator$OnItemClickListener;", "itemClickListener", "setOnItemClickListener", "(Landroidx/appcompat/widget/SeslIndicator$OnItemClickListener;)V", "", "indicator", "Ljava/util/List;", "Landroidx/appcompat/widget/SeslIndicator$OnItemClickListener;", "Landroid/graphics/drawable/Drawable;", "value", "defaultCircle", "Landroid/graphics/drawable/Drawable;", "getDefaultCircle", "()Landroid/graphics/drawable/Drawable;", "setDefaultCircle", "(Landroid/graphics/drawable/Drawable;)V", "selectCircle", "getSelectCircle", "setSelectCircle", "selectedPosition", "I", "getSelectedPosition", "()I", "setSelectedPosition", "getSize", "size", "Companion", "OnItemClickListener", "PageIndicatorMarker", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslIndicator extends LinearLayout {
    public static final Companion Companion = new Companion((e) null);
    private Drawable defaultCircle;
    /* access modifiers changed from: private */
    public List<PageIndicatorMarker> indicator;
    private OnItemClickListener itemClickListener;
    private Drawable selectCircle;
    private int selectedPosition;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/appcompat/widget/SeslIndicator$Companion;", "", "()V", "SIZE_TYPE_LARGE", "", "SIZE_TYPE_SMALL", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Landroidx/appcompat/widget/SeslIndicator$OnItemClickListener;", "", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemClickListener {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SeslIndicator(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    public static /* synthetic */ void addIndicator$default(SeslIndicator seslIndicator, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        seslIndicator.addIndicator(num);
    }

    /* access modifiers changed from: private */
    public static final void addIndicator$lambda$5$lambda$4(SeslIndicator seslIndicator, View view) {
        j.e(seslIndicator, "this$0");
        OnItemClickListener onItemClickListener = seslIndicator.itemClickListener;
        if (onItemClickListener != null) {
            List<PageIndicatorMarker> list = seslIndicator.indicator;
            j.e(list, "<this>");
            ((d) onItemClickListener).a(list.indexOf(view), view);
        }
    }

    private final PageIndicatorMarker generateDotIndicator(Integer num) {
        Context context = getContext();
        j.d(context, "context");
        PageIndicatorMarker pageIndicatorMarker = new PageIndicatorMarker(context, num, (AttributeSet) null, 4, (e) null);
        pageIndicatorMarker.setDefaultCircle(this.defaultCircle);
        pageIndicatorMarker.setSelectCircle(this.selectCircle);
        return pageIndicatorMarker;
    }

    private final int getAppBarViewPagerIndicatorOffColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_off, R$color.sesl_appbar_viewpager_indicator_off_dark), new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_off_for_theme, R$color.sesl_appbar_viewpager_indicator_off_dark_for_theme)));
    }

    private final int getAppBarViewPagerIndicatorOnColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_on), new SeslThemeResourceColor$ThemeResourceColor(R$color.sesl_appbar_viewpager_indicator_on_for_theme)));
    }

    private final void invalidateIndicator() {
        boolean z;
        int size = this.indicator.size();
        for (int i2 = 0; i2 < size; i2++) {
            PageIndicatorMarker pageIndicatorMarker = this.indicator.get(i2);
            if (i2 == this.selectedPosition) {
                z = true;
            } else {
                z = false;
            }
            pageIndicatorMarker.setActive(z);
        }
    }

    /* access modifiers changed from: private */
    public static final void setOnItemClickListener$lambda$9$lambda$8(OnItemClickListener onItemClickListener, SeslIndicator seslIndicator, View view) {
        j.e(onItemClickListener, "$itemClickListener");
        j.e(seslIndicator, "this$0");
        List<PageIndicatorMarker> list = seslIndicator.indicator;
        j.e(list, "<this>");
        ((d) onItemClickListener).a(list.indexOf(view), view);
    }

    public final void addIndicator(Integer num) {
        int i2;
        PageIndicatorMarker generateDotIndicator = generateDotIndicator(num);
        generateDotIndicator.setOnClickListener(new C0419b(14, this));
        this.indicator.add(generateDotIndicator);
        generateDotIndicator.setAccessibilityDelegate(new SeslIndicator$addIndicator$1(this, generateDotIndicator));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (num != null && num.intValue() == 1) {
            i2 = getContext().getResources().getDimensionPixelSize(R$dimen.sesl_viewpager_indicator_horizontal_padding_lg);
        } else {
            i2 = getContext().getResources().getDimensionPixelSize(R$dimen.sesl_viewpager_indicator_horizontal_padding_sm);
        }
        int i7 = i2 / 2;
        layoutParams.setMargins(i7, 0, i7, 0);
        addView(generateDotIndicator, layoutParams);
        if (this.selectedPosition == -1) {
            setSelectedPosition(0);
        }
    }

    public final Drawable getDefaultCircle() {
        return this.defaultCircle;
    }

    public final Drawable getSelectCircle() {
        return this.selectCircle;
    }

    public final int getSelectedPosition() {
        return this.selectedPosition;
    }

    public final int getSize() {
        return this.indicator.size();
    }

    public final void removeIndicator(int i2) {
        if (i2 >= 0 && i2 < this.indicator.size()) {
            removeView(this.indicator.remove(i2));
            if (this.selectedPosition >= this.indicator.size()) {
                setSelectedPosition(this.selectedPosition - 1);
            } else {
                invalidateIndicator();
            }
        }
    }

    public final void setDefaultCircle(Drawable drawable) {
        for (PageIndicatorMarker defaultCircle2 : this.indicator) {
            defaultCircle2.setDefaultCircle(drawable);
        }
        this.defaultCircle = drawable;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        j.e(onItemClickListener, "itemClickListener");
        this.itemClickListener = onItemClickListener;
        for (PageIndicatorMarker onClickListener : this.indicator) {
            onClickListener.setOnClickListener(new g(20, onItemClickListener, this));
        }
    }

    public final void setSelectCircle(Drawable drawable) {
        for (PageIndicatorMarker selectCircle2 : this.indicator) {
            selectCircle2.setSelectCircle(drawable);
        }
        this.selectCircle = drawable;
    }

    public final void setSelectedPosition(int i2) {
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.indicator.size()) {
            i2 = this.indicator.size() - 1;
        }
        this.selectedPosition = i2;
        invalidateIndicator();
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tR.\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R.\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R*\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u00158\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/appcompat/widget/SeslIndicator$PageIndicatorMarker;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "", "sizeType", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Ljava/lang/Integer;Landroid/util/AttributeSet;)V", "Landroid/graphics/drawable/Drawable;", "value", "defaultCircle", "Landroid/graphics/drawable/Drawable;", "getDefaultCircle", "()Landroid/graphics/drawable/Drawable;", "setDefaultCircle", "(Landroid/graphics/drawable/Drawable;)V", "selectCircle", "getSelectCircle", "setSelectCircle", "", "isActive", "Z", "()Z", "setActive", "(Z)V", "Landroid/widget/ImageView;", "imageView", "Landroid/widget/ImageView;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PageIndicatorMarker extends FrameLayout {
        private Drawable defaultCircle;
        private final ImageView imageView;
        private boolean isActive;
        private Drawable selectCircle;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PageIndicatorMarker(Context context, Integer num, AttributeSet attributeSet, int i2, e eVar) {
            this(context, (i2 & 2) != 0 ? 0 : num, (i2 & 4) != 0 ? null : attributeSet);
        }

        public final void setActive(boolean z) {
            Drawable drawable;
            ImageView imageView2 = this.imageView;
            if (z) {
                drawable = this.selectCircle;
            } else {
                drawable = this.defaultCircle;
            }
            imageView2.setImageDrawable(drawable);
            setSelected(z);
            this.isActive = z;
        }

        public final void setDefaultCircle(Drawable drawable) {
            this.defaultCircle = drawable;
            setActive(this.isActive);
        }

        public final void setSelectCircle(Drawable drawable) {
            this.selectCircle = drawable;
            setActive(this.isActive);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PageIndicatorMarker(Context context, Integer num, AttributeSet attributeSet) {
            super(context, attributeSet);
            j.e(context, "context");
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageDrawable(this.selectCircle);
            addView(imageView2);
            if (num != null && num.intValue() == 1) {
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.sesl_viewpager_indicator_size_lg);
                imageView2.getLayoutParams().width = dimensionPixelSize;
                imageView2.getLayoutParams().height = dimensionPixelSize;
            }
            this.imageView = imageView2;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SeslIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable;
        Drawable mutate;
        j.e(context, "context");
        this.indicator = new ArrayList();
        int i2 = R$drawable.sesl_viewpager_indicator_on_off;
        Drawable drawable2 = context.getDrawable(i2);
        Drawable drawable3 = null;
        if (drawable2 == null || (drawable = drawable2.mutate()) == null) {
            drawable = null;
        } else {
            drawable.setTint(getAppBarViewPagerIndicatorOffColor(context));
        }
        this.defaultCircle = drawable;
        Drawable drawable4 = context.getDrawable(i2);
        if (!(drawable4 == null || (mutate = drawable4.mutate()) == null)) {
            mutate.setTint(getAppBarViewPagerIndicatorOnColor(context));
            drawable3 = mutate;
        }
        this.selectCircle = drawable3;
        this.selectedPosition = -1;
    }
}
