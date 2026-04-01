package com.google.android.material.appbar.model.view;

import Ba.g;
import T1.a;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$string;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.theme.SeslThemeResourceHelper;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$OpenThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ThemeResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceDrawable$OpenThemeResourceDrawable;
import androidx.appcompat.util.theme.resource.SeslThemeResourceDrawable$ThemeResourceDrawable;
import androidx.appcompat.widget.TooltipCompat;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.ButtonListModel;
import com.google.android.material.appbar.model.ButtonModel;
import com.google.android.material.appbar.model.ButtonStyle;
import com.google.android.material.appbar.model.SuggestAppBarModel;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0014\u0010\u0013J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0018\u0010\u0011J\u0017\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001d\u001a\u00020\u000f2\u000e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\"\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\n¢\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\n¢\u0006\u0004\b%\u0010#J\u0017\u0010'\u001a\u00020\u000f2\b\u0010&\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b'\u0010(J\u0017\u0010+\u001a\u00020\u000f2\b\u0010*\u001a\u0004\u0018\u00010)¢\u0006\u0004\b+\u0010,J\u0015\u0010/\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100R\u001e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u001b8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b\u001c\u00101R$\u00103\u001a\u0004\u0018\u0001028\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u0010:\u001a\u0004\u0018\u0001098\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R$\u0010@\u001a\u0004\u0018\u0001098\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\b@\u0010;\u001a\u0004\bA\u0010=\"\u0004\bB\u0010?R$\u0010D\u001a\u0004\u0018\u00010C8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR$\u0010K\u001a\u0004\u0018\u00010J8\u0004@\u0004X\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001d\u0010R\u001a\b\u0012\u0004\u0012\u00020\f0Q8\u0006¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U¨\u0006V"}, d2 = {"Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "LT1/a;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Lcom/google/android/material/appbar/model/ButtonModel;", "buttonModel", "", "style", "Landroid/widget/Button;", "generateButton", "(Lcom/google/android/material/appbar/model/ButtonModel;I)Landroid/widget/Button;", "Lme/x;", "addMargin", "()V", "getAppBarSuggestTitleColor", "(Landroid/content/Context;)I", "getAppBarSuggestSubTitleColor", "Landroid/graphics/drawable/Drawable;", "getCloseDrawable", "(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;", "inflate", "updateResource", "(Landroid/content/Context;)V", "Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "model", "setModel", "(Lcom/google/android/material/appbar/model/SuggestAppBarModel;)V", "", "title", "count", "setTitle", "(Ljava/lang/String;I)V", "subTitle", "setSubTitle", "drawable", "setImage", "(Landroid/graphics/drawable/Drawable;)V", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "closeClickListener", "setCloseClickListener", "(Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;)V", "Lcom/google/android/material/appbar/model/ButtonListModel;", "buttonModels", "setButtonModules", "(Lcom/google/android/material/appbar/model/ButtonListModel;)V", "Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "Landroid/widget/ImageView;", "topImageView", "Landroid/widget/ImageView;", "getTopImageView", "()Landroid/widget/ImageView;", "setTopImageView", "(Landroid/widget/ImageView;)V", "Landroid/widget/TextView;", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "setTitleView", "(Landroid/widget/TextView;)V", "subTitleView", "getSubTitleView", "setSubTitleView", "Landroid/widget/ImageButton;", "close", "Landroid/widget/ImageButton;", "getClose", "()Landroid/widget/ImageButton;", "setClose", "(Landroid/widget/ImageButton;)V", "Landroid/view/ViewGroup;", "bottomLayout", "Landroid/view/ViewGroup;", "getBottomLayout", "()Landroid/view/ViewGroup;", "setBottomLayout", "(Landroid/view/ViewGroup;)V", "", "buttons", "Ljava/util/List;", "getButtons", "()Ljava/util/List;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestAppBarView extends a {
    private ViewGroup bottomLayout;
    private final List<Button> buttons;
    private ImageButton close;
    private SuggestAppBarModel<? extends SuggestAppBarView> model;
    private TextView subTitleView;
    private TextView titleView;
    private ImageView topImageView;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SuggestAppBarView(Context context) {
        this(context, (AttributeSet) null, 2, (e) null);
        j.e(context, "context");
    }

    private final void addMargin() {
        View view = new View(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(view.getResources().getDimensionPixelOffset(R.dimen.sesl_appbar_button_side_margin), -1));
        ViewGroup viewGroup = this.bottomLayout;
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private final Button generateButton(ButtonModel buttonModel, int i2) {
        Button button = new Button(getContext(), (AttributeSet) null, 0, i2);
        button.setText(buttonModel.f1396a);
        String str = buttonModel.f1397c;
        if (str != null) {
            button.setContentDescription(str);
        }
        button.setOnClickListener(new g(8, buttonModel, this));
        return button;
    }

    /* access modifiers changed from: private */
    public static final void generateButton$lambda$11$lambda$10(ButtonModel buttonModel, SuggestAppBarView suggestAppBarView, View view) {
        j.e(buttonModel, "$buttonModel");
        j.e(suggestAppBarView, "this$0");
        AppBarModel.OnClickListener onClickListener = buttonModel.b;
        if (onClickListener != null) {
            j.d(view, "it");
            SuggestAppBarModel<? extends SuggestAppBarView> suggestAppBarModel = suggestAppBarView.model;
            if (suggestAppBarModel != null) {
                onClickListener.a(view, suggestAppBarModel);
            } else {
                j.k("model");
                throw null;
            }
        }
    }

    private final int getAppBarSuggestSubTitleColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_sub_title, R.color.sesl_appbar_suggest_sub_title_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_sub_title, R.color.sesl_appbar_suggest_sub_title_dark)));
    }

    private final int getAppBarSuggestTitleColor(Context context) {
        return SeslThemeResourceHelper.Companion.getColorInt(context, new SeslThemeResourceColor$OpenThemeResourceColor(new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_title, R.color.sesl_appbar_suggest_title_dark), new SeslThemeResourceColor$ThemeResourceColor(R.color.sesl_appbar_suggest_title, R.color.sesl_appbar_suggest_title_dark_for_theme)));
    }

    private final Drawable getCloseDrawable(Context context) {
        return SeslThemeResourceHelper.Companion.getDrawable(context, new SeslThemeResourceDrawable$OpenThemeResourceDrawable(new SeslThemeResourceDrawable$ThemeResourceDrawable(R.drawable.sesl_close_button_recoil_background, R.drawable.sesl_close_button_recoil_background_dark), new SeslThemeResourceDrawable$ThemeResourceDrawable(R.drawable.sesl_close_button_recoil_background_for_theme, R.drawable.sesl_close_button_recoil_background_dark_for_theme)));
    }

    /* access modifiers changed from: private */
    public static final void setCloseClickListener$lambda$6$lambda$5(AppBarModel.OnClickListener onClickListener, SuggestAppBarView suggestAppBarView, View view) {
        j.e(suggestAppBarView, "this$0");
        if (onClickListener != null) {
            j.d(view, "it");
            SuggestAppBarModel<? extends SuggestAppBarView> suggestAppBarModel = suggestAppBarView.model;
            if (suggestAppBarModel != null) {
                onClickListener.a(view, suggestAppBarModel);
            } else {
                j.k("model");
                throw null;
            }
        }
    }

    public final ViewGroup getBottomLayout() {
        return this.bottomLayout;
    }

    public final List<Button> getButtons() {
        return this.buttons;
    }

    public final ImageButton getClose() {
        return this.close;
    }

    public final TextView getSubTitleView() {
        return this.subTitleView;
    }

    public final TextView getTitleView() {
        return this.titleView;
    }

    public final ImageView getTopImageView() {
        return this.topImageView;
    }

    public void inflate() {
        ViewGroup viewGroup;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_suggest, this, false);
        ImageButton imageButton = null;
        if (inflate instanceof ViewGroup) {
            viewGroup = (ViewGroup) inflate;
        } else {
            viewGroup = null;
        }
        if (viewGroup != null) {
            this.topImageView = (ImageView) viewGroup.findViewById(R.id.suggest_app_bar_top_image);
            this.titleView = (TextView) viewGroup.findViewById(R.id.suggest_app_bar_title);
            this.subTitleView = (TextView) viewGroup.findViewById(R.id.suggest_app_bar_sub_title);
            ImageButton imageButton2 = (ImageButton) viewGroup.findViewById(R.id.suggest_app_bar_close);
            if (imageButton2 != null) {
                SeslViewReflector.semSetHoverPopupType(imageButton2, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
                imageButton = imageButton2;
            }
            this.close = imageButton;
            this.bottomLayout = (ViewGroup) viewGroup.findViewById(R.id.suggest_app_bar_bottom_layout);
            Context context = getContext();
            j.d(context, "context");
            updateResource(context);
            addView(viewGroup);
        }
    }

    public final void setBottomLayout(ViewGroup viewGroup) {
        this.bottomLayout = viewGroup;
    }

    public final void setButtonModules(ButtonListModel buttonListModel) {
        int i2;
        int i7;
        j.e(buttonListModel, "buttonModels");
        ViewGroup viewGroup = this.bottomLayout;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        this.buttons.clear();
        List list = buttonListModel.b;
        ButtonStyle buttonStyle = buttonListModel.f1395a;
        int i8 = 0;
        for (Object next : list) {
            int i10 = i8 + 1;
            if (i8 >= 0) {
                ButtonModel buttonModel = (ButtonModel) next;
                if (SeslMisc.isLightTheme(getContext())) {
                    i2 = buttonStyle.f1398a;
                } else {
                    i2 = buttonStyle.b;
                }
                Button generateButton = generateButton(buttonModel, i2);
                Resources resources = generateButton.getResources();
                if (list.size() > 1) {
                    i7 = R.dimen.sesl_appbar_button_max_width;
                } else {
                    i7 = R.dimen.sesl_appbar_button_max_width_multi;
                }
                generateButton.setMaxWidth(resources.getDimensionPixelSize(i7));
                if (i8 != 0) {
                    addMargin();
                }
                this.buttons.add(generateButton);
                ViewGroup viewGroup2 = this.bottomLayout;
                if (viewGroup2 != null) {
                    viewGroup2.addView(generateButton);
                }
                i8 = i10;
            } else {
                C1195m.v0();
                throw null;
            }
        }
    }

    public final void setClose(ImageButton imageButton) {
        this.close = imageButton;
    }

    public final void setCloseClickListener(AppBarModel.OnClickListener onClickListener) {
        int i2;
        ImageButton imageButton = this.close;
        if (imageButton != null) {
            if (onClickListener == null) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            imageButton.setVisibility(i2);
            imageButton.setOnClickListener(new g(9, onClickListener, this));
        }
    }

    public final void setImage(Drawable drawable) {
        int i2;
        ImageView imageView = this.topImageView;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            if (drawable != null) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
    }

    public final void setModel(SuggestAppBarModel<? extends SuggestAppBarView> suggestAppBarModel) {
        j.e(suggestAppBarModel, "model");
        this.model = suggestAppBarModel;
    }

    public final void setSubTitle(String str, int i2) {
        int i7;
        TextView textView = this.subTitleView;
        if (textView != null) {
            textView.setText(str);
            if (TextUtils.isEmpty(str)) {
                i7 = 8;
            } else {
                i7 = 0;
            }
            textView.setVisibility(i7);
            if (i2 > 0) {
                textView.setMaxLines(i2);
            }
        }
    }

    public final void setSubTitleView(TextView textView) {
        this.subTitleView = textView;
    }

    public final void setTitle(String str, int i2) {
        int i7;
        TextView textView = this.titleView;
        if (textView != null) {
            textView.setText(str);
            if (TextUtils.isEmpty(str)) {
                i7 = 8;
            } else {
                i7 = 0;
            }
            textView.setVisibility(i7);
            if (i2 > 0) {
                textView.setMaxLines(i2);
            }
        }
    }

    public final void setTitleView(TextView textView) {
        this.titleView = textView;
    }

    public final void setTopImageView(ImageView imageView) {
        this.topImageView = imageView;
    }

    public void updateResource(Context context) {
        j.e(context, "context");
        TextView textView = this.titleView;
        if (textView != null) {
            textView.setTextColor(getAppBarSuggestTitleColor(context));
        }
        TextView textView2 = this.subTitleView;
        if (textView2 != null) {
            textView2.setTextColor(getAppBarSuggestSubTitleColor(context));
        }
        ImageButton imageButton = this.close;
        if (imageButton != null) {
            String string = imageButton.getResources().getString(R$string.sesl_appbar_suggest_dismiss);
            j.d(string, "resources.getString(andr…l_appbar_suggest_dismiss)");
            TooltipCompat.setTooltipText(imageButton, string);
            imageButton.setContentDescription(string);
            imageButton.setBackground(getCloseDrawable(context));
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuggestAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        this.buttons = new ArrayList();
        inflate();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SuggestAppBarView(Context context, AttributeSet attributeSet, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }
}
