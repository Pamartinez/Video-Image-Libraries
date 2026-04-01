package com.samsung.android.gallery.widget.toolbar;

import android.content.Context;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.ButtonListModel;
import com.google.android.material.appbar.model.ButtonModel;
import com.google.android.material.appbar.model.ButtonStyle;
import com.google.android.material.appbar.model.SuggestAppBarItemModel;
import com.samsung.android.gallery.widget.toolbar.GallerySuggestAppBarView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0013B9\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarModel;", "T", "Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarView;", "Lcom/google/android/material/appbar/model/SuggestAppBarItemModel;", "clazz", "Ljava/lang/Class;", "context", "Landroid/content/Context;", "title", "", "closeClickListener", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "buttonListModel", "Lcom/google/android/material/appbar/model/ButtonListModel;", "<init>", "(Ljava/lang/Class;Landroid/content/Context;Ljava/lang/String;Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;Lcom/google/android/material/appbar/model/ButtonListModel;)V", "init", "moduleView", "(Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarView;)Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarView;", "Builder", "libwidget_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySuggestAppBarModel<T extends GallerySuggestAppBarView> extends SuggestAppBarItemModel<T> {

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\"\u0010\u0011\u001a\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\b\b\u0001\u0010\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0017J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\b\b\u0001\u0010\u0014*\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarModel$Builder;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "title", "", "closeClickListener", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "buttons", "", "Lcom/google/android/material/appbar/model/ButtonModel;", "buttonStyle", "Lcom/google/android/material/appbar/model/ButtonStyle;", "setTitle", "setCloseClickListener", "setButtons", "build", "Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarModel;", "T", "Lcom/samsung/android/gallery/widget/toolbar/GallerySuggestAppBarView;", "viewClass", "Ljava/lang/Class;", "create", "clazz", "libwidget_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ButtonStyle buttonStyle;
        private List<? extends ButtonModel> buttons = C1202t.d;
        private AppBarModel.OnClickListener closeClickListener;
        private final Context context;
        private String title;

        public Builder(Context context2) {
            j.e(context2, "context");
            this.context = context2;
        }

        private final <T extends GallerySuggestAppBarView> GallerySuggestAppBarModel<T> create(Class<T> cls) {
            Context context2 = this.context;
            String str = this.title;
            AppBarModel.OnClickListener onClickListener = this.closeClickListener;
            ButtonStyle buttonStyle2 = this.buttonStyle;
            if (buttonStyle2 == null) {
                buttonStyle2 = new ButtonStyle(2131952049, 2131952048);
            }
            return new GallerySuggestAppBarModel<>(cls, context2, str, onClickListener, new ButtonListModel(buttonStyle2, this.buttons));
        }

        public static /* synthetic */ Builder setButtons$default(Builder builder, List list, ButtonStyle buttonStyle2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                buttonStyle2 = null;
            }
            return builder.setButtons(list, buttonStyle2);
        }

        public final <T extends GallerySuggestAppBarView> GallerySuggestAppBarModel<T> build(Class<T> cls) {
            j.e(cls, "viewClass");
            return create(cls);
        }

        public final Builder setButtons(List<? extends ButtonModel> list) {
            j.e(list, "buttons");
            return setButtons$default(this, list, (ButtonStyle) null, 2, (Object) null);
        }

        public final Builder setCloseClickListener(AppBarModel.OnClickListener onClickListener) {
            this.closeClickListener = onClickListener;
            return this;
        }

        public final Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public final Builder setButtons(List<? extends ButtonModel> list, ButtonStyle buttonStyle2) {
            j.e(list, "buttons");
            this.buttons = list;
            if (buttonStyle2 != null) {
                this.buttonStyle = buttonStyle2;
            }
            return this;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GallerySuggestAppBarModel(java.lang.Class<T> r14, android.content.Context r15, java.lang.String r16, com.google.android.material.appbar.model.AppBarModel.OnClickListener r17, com.google.android.material.appbar.model.ButtonListModel r18) {
        /*
            r13 = this;
            java.lang.String r0 = "clazz"
            kotlin.jvm.internal.j.e(r14, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r15, r0)
            java.lang.String r0 = "buttonListModel"
            r10 = r18
            kotlin.jvm.internal.j.e(r10, r0)
            He.d r2 = a.C0068a.D(r14)
            r11 = 120(0x78, float:1.68E-43)
            r12 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r13
            r3 = r15
            r4 = r16
            r9 = r17
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.toolbar.GallerySuggestAppBarModel.<init>(java.lang.Class, android.content.Context, java.lang.String, com.google.android.material.appbar.model.AppBarModel$OnClickListener, com.google.android.material.appbar.model.ButtonListModel):void");
    }

    public T init(T t) {
        j.e(t, "moduleView");
        t.setModel(this);
        t.setTitle(getTitle(), getTitleMaxLine());
        t.setCloseClickListener(getCloseClickListener());
        t.setButtonModules(getButtonListModel());
        Context context = t.getContext();
        j.d(context, "getContext(...)");
        t.updateResource(context);
        return t;
    }
}
