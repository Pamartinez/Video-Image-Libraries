package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.samsung.android.app.sdk.deepsky.textextraction.R$color;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableCharacter;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableEntity;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;
import ne.C1196n;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J+\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/EntityRenderer;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Canvas;", "canvas", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/TextExtractionState;", "textExtractionState", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;", "selectableEntities", "Lme/x;", "drawEntities", "(Landroid/graphics/Canvas;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/TextExtractionState;Ljava/util/List;)V", "Landroid/content/Context;", "", "isEntityAreaVisible", "Z", "()Z", "setEntityAreaVisible", "(Z)V", "Landroid/graphics/Paint;", "underlinePaint", "Landroid/graphics/Paint;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityRenderer {
    public static final Companion Companion = new Companion((e) null);
    private static final List<EntityType> ENTITY_TYPE_ALLOWLIST = C1195m.q0(EntityType.PHONE, EntityType.EMAIL, EntityType.URL, EntityType.UNIT, EntityType.ADDRESS);
    private final Context context;
    private boolean isEntityAreaVisible = true;
    private final Paint underlinePaint;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/renderer/EntityRenderer$Companion;", "", "<init>", "()V", "TAG", "", "ENTITY_TYPE_ALLOWLIST", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public EntityRenderer(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        Paint paint = new Paint();
        paint.setFlags(1);
        paint.setColor(context2.getColor(R$color.textextraction_entity_area_color));
        paint.setStyle(Paint.Style.FILL);
        this.underlinePaint = paint;
    }

    public final void drawEntities(Canvas canvas, TextExtractionState textExtractionState, List<SelectableEntity> list) {
        j.e(canvas, "canvas");
        j.e(textExtractionState, "textExtractionState");
        j.e(list, "selectableEntities");
        if (this.isEntityAreaVisible && textExtractionState == TextExtractionState.START_BY_BUTTON) {
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object next : list) {
                if (hashSet.add(((SelectableEntity) next).getData())) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (ENTITY_TYPE_ALLOWLIST.contains(((SelectableEntity) next2).getType())) {
                    arrayList2.add(next2);
                }
            }
            ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                Iterable<SelectableCharacter> selectableCharacters = ((SelectableEntity) it2.next()).getSelectableCharacters();
                ArrayList arrayList4 = new ArrayList(C1196n.w0(selectableCharacters, 10));
                for (SelectableCharacter path : selectableCharacters) {
                    arrayList4.add(path.getPath());
                }
                arrayList3.add(arrayList4);
            }
            Iterator it3 = C1196n.x0(arrayList3).iterator();
            while (it3.hasNext()) {
                canvas.drawPath((Path) it3.next(), this.underlinePaint);
            }
        }
    }

    public final boolean isEntityAreaVisible() {
        return this.isEntityAreaVisible;
    }

    public final void setEntityAreaVisible(boolean z) {
        this.isEntityAreaVisible = z;
    }
}
