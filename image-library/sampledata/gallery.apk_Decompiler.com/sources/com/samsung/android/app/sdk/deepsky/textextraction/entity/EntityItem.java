package com.samsung.android.app.sdk.deepsky.textextraction.entity;

import android.app.RemoteAction;
import android.graphics.Point;
import android.graphics.Rect;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\b\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010\u0014R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010#R#\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00068\u0006¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b$\u0010#R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\u0010\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001f\u001a\u0004\b+\u0010\u0014¨\u0006,"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityItem;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "type", "", "text", "", "Landroid/graphics/Rect;", "rectList", "", "Landroid/graphics/Point;", "polyList", "", "score", "Landroid/app/RemoteAction;", "action", "actionId", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;Ljava/lang/String;Ljava/util/List;Ljava/util/List;FLandroid/app/RemoteAction;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "getType", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "Ljava/lang/String;", "getText", "Ljava/util/List;", "getRectList", "()Ljava/util/List;", "getPolyList", "F", "getScore", "()F", "Landroid/app/RemoteAction;", "getAction", "()Landroid/app/RemoteAction;", "getActionId", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityItem {
    private final RemoteAction action;
    private final String actionId;
    private final List<Point[]> polyList;
    private final List<Rect> rectList;
    private final float score;
    private final String text;
    private final EntityType type;

    public EntityItem(EntityType entityType, String str, List<Rect> list, List<Point[]> list2, float f, RemoteAction remoteAction, String str2) {
        j.e(entityType, "type");
        j.e(str, "text");
        j.e(list, "rectList");
        j.e(list2, "polyList");
        j.e(remoteAction, "action");
        j.e(str2, "actionId");
        this.type = entityType;
        this.text = str;
        this.rectList = list;
        this.polyList = list2;
        this.score = f;
        this.action = remoteAction;
        this.actionId = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityItem)) {
            return false;
        }
        EntityItem entityItem = (EntityItem) obj;
        if (this.type == entityItem.type && j.a(this.text, entityItem.text) && j.a(this.rectList, entityItem.rectList) && j.a(this.polyList, entityItem.polyList) && Float.compare(this.score, entityItem.score) == 0 && j.a(this.action, entityItem.action) && j.a(this.actionId, entityItem.actionId)) {
            return true;
        }
        return false;
    }

    public final RemoteAction getAction() {
        return this.action;
    }

    public final String getActionId() {
        return this.actionId;
    }

    public final List<Point[]> getPolyList() {
        return this.polyList;
    }

    public final List<Rect> getRectList() {
        return this.rectList;
    }

    public final float getScore() {
        return this.score;
    }

    public final String getText() {
        return this.text;
    }

    public final EntityType getType() {
        return this.type;
    }

    public int hashCode() {
        int a7 = N2.j.a(this.score, C0212a.f(this.polyList, C0212a.f(this.rectList, C0212a.d(this.type.hashCode() * 31, 31, this.text), 31), 31), 31);
        return this.actionId.hashCode() + ((this.action.hashCode() + a7) * 31);
    }

    public String toString() {
        EntityType entityType = this.type;
        String str = this.text;
        List<Rect> list = this.rectList;
        List<Point[]> list2 = this.polyList;
        float f = this.score;
        RemoteAction remoteAction = this.action;
        String str2 = this.actionId;
        StringBuilder sb2 = new StringBuilder("EntityItem(type=");
        sb2.append(entityType);
        sb2.append(", text=");
        sb2.append(str);
        sb2.append(", rectList=");
        sb2.append(list);
        sb2.append(", polyList=");
        sb2.append(list2);
        sb2.append(", score=");
        sb2.append(f);
        sb2.append(", action=");
        sb2.append(remoteAction);
        sb2.append(", actionId=");
        return C0212a.p(sb2, str2, ")");
    }
}
