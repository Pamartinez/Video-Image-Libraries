package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data;

import android.graphics.Point;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R.\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b8\u0006¢\u0006\f\n\u0004\b \u0010\u0014\u001a\u0004\b!\u0010\u0016¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableEntity;", "", "", "data", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "type", "", "", "Landroid/graphics/Point;", "polyList", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/Underline;", "underlineList", "<init>", "(Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;Ljava/util/List;Ljava/util/List;)V", "Ljava/lang/String;", "getData", "()Ljava/lang/String;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "getType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "Ljava/util/List;", "getPolyList", "()Ljava/util/List;", "setPolyList", "(Ljava/util/List;)V", "getUnderlineList", "setUnderlineList", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableLine;", "overlappingLines", "getOverlappingLines", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableCharacter;", "selectableCharacters", "getSelectableCharacters", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SelectableEntity {
    private final String data;
    private final List<SelectableLine> overlappingLines = new ArrayList();
    private List<Point[]> polyList;
    private final List<SelectableCharacter> selectableCharacters = new ArrayList();
    private final EntityType type;
    private List<Underline> underlineList;

    public SelectableEntity(String str, EntityType entityType, List<Point[]> list, List<Underline> list2) {
        j.e(str, "data");
        j.e(entityType, "type");
        j.e(list, "polyList");
        j.e(list2, "underlineList");
        this.data = str;
        this.type = entityType;
        this.polyList = list;
        this.underlineList = list2;
    }

    public final String getData() {
        return this.data;
    }

    public final List<SelectableLine> getOverlappingLines() {
        return this.overlappingLines;
    }

    public final List<Point[]> getPolyList() {
        return this.polyList;
    }

    public final List<SelectableCharacter> getSelectableCharacters() {
        return this.selectableCharacters;
    }

    public final EntityType getType() {
        return this.type;
    }

    public final List<Underline> getUnderlineList() {
        return this.underlineList;
    }

    public final void setPolyList(List<Point[]> list) {
        j.e(list, "<set-?>");
        this.polyList = list;
    }
}
