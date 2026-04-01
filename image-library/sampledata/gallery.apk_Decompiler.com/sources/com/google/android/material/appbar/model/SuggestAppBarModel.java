package com.google.android.material.appbar.model;

import He.C0748d;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.view.SuggestAppBarView;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001*Bi\b\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\f\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\f\u0010\u0019\u001a\u0004\b\u001f\u0010\u001bR\u0017\u0010\r\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010'\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "T", "Lcom/google/android/material/appbar/model/AppBarModel;", "LHe/d;", "kclazz", "Landroid/content/Context;", "context", "", "title", "", "titleMaxLine", "subTitle", "subTitleMaxLine", "Landroid/graphics/drawable/Drawable;", "imageDrawable", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "closeClickListener", "Lcom/google/android/material/appbar/model/ButtonListModel;", "buttonListModel", "<init>", "(LHe/d;Landroid/content/Context;Ljava/lang/String;ILjava/lang/String;ILandroid/graphics/drawable/Drawable;Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;Lcom/google/android/material/appbar/model/ButtonListModel;)V", "moduleView", "init", "(Lcom/google/android/material/appbar/model/view/SuggestAppBarView;)Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "I", "getTitleMaxLine", "()I", "getSubTitle", "getSubTitleMaxLine", "Landroid/graphics/drawable/Drawable;", "getImageDrawable", "()Landroid/graphics/drawable/Drawable;", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "getCloseClickListener", "()Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "Lcom/google/android/material/appbar/model/ButtonListModel;", "getButtonListModel", "()Lcom/google/android/material/appbar/model/ButtonListModel;", "Builder", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestAppBarModel<T extends SuggestAppBarView> extends AppBarModel<T> {
    private final ButtonListModel buttonListModel;
    private final AppBarModel.OnClickListener closeClickListener;
    private final Drawable imageDrawable;
    private final String subTitle;
    private final int subTitleMaxLine;
    private final String title;
    private final int titleMaxLine;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/google/android/material/appbar/model/SuggestAppBarModel$Builder;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        public Builder(Context context) {
            j.e(context, "context");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SuggestAppBarModel(C0748d dVar, Context context, String str, int i2, String str2, int i7, Drawable drawable, AppBarModel.OnClickListener onClickListener, ButtonListModel buttonListModel2, int i8, e eVar) {
        this(dVar, context, str, (i8 & 8) != 0 ? 0 : i2, (i8 & 16) != 0 ? null : str2, (i8 & 32) != 0 ? 0 : i7, (i8 & 64) != 0 ? null : drawable, (i8 & 128) != 0 ? null : onClickListener, buttonListModel2);
    }

    public final ButtonListModel getButtonListModel() {
        return this.buttonListModel;
    }

    public final AppBarModel.OnClickListener getCloseClickListener() {
        return this.closeClickListener;
    }

    public final Drawable getImageDrawable() {
        return this.imageDrawable;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final int getSubTitleMaxLine() {
        return this.subTitleMaxLine;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTitleMaxLine() {
        return this.titleMaxLine;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuggestAppBarModel(C0748d dVar, Context context, String str, int i2, String str2, int i7, Drawable drawable, AppBarModel.OnClickListener onClickListener, ButtonListModel buttonListModel2) {
        super(dVar, context);
        j.e(dVar, "kclazz");
        j.e(context, "context");
        j.e(buttonListModel2, "buttonListModel");
        this.title = str;
        this.titleMaxLine = i2;
        this.subTitle = str2;
        this.subTitleMaxLine = i7;
        this.imageDrawable = drawable;
        this.closeClickListener = onClickListener;
        this.buttonListModel = buttonListModel2;
    }

    public T init(T t) {
        j.e(t, "moduleView");
        t.setModel(this);
        t.setTitle(this.title, this.titleMaxLine);
        t.setSubTitle(this.subTitle, this.subTitleMaxLine);
        t.setImage(this.imageDrawable);
        t.setCloseClickListener(this.closeClickListener);
        t.setButtonModules(this.buttonListModel);
        Context context = t.getContext();
        j.d(context, "context");
        t.updateResource(context);
        return t;
    }
}
