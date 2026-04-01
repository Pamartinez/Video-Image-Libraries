package com.samsung.android.app.sdk.deepsky.textextraction.result;

import android.app.RemoteAction;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrResult;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u0000 \u00192\u00020\u0001:\u0003\u001a\u001b\u0019B!\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData$EntityInfo;", "entityList", "Landroid/graphics/Rect;", "validRect", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getEntityList", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getValidRect", "()Landroid/graphics/Rect;", "Companion", "EntityInfo", "UnderlineInfo", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityData {
    public static final Companion Companion = new Companion((e) null);
    private final List<EntityInfo> entityList;
    private final Rect validRect;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData$Companion;", "", "<init>", "()V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001Bg\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0005\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0017\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b#\u0010\u0019R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R#\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b'\u0010&R\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00058\u0006¢\u0006\f\n\u0004\b\f\u0010$\u001a\u0004\b(\u0010&R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010)\u001a\u0004\b*\u0010+R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010!\u001a\u0004\b/\u0010\u0019¨\u00060"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData$EntityInfo;", "", "", "text", "type", "", "Landroid/graphics/Rect;", "rectList", "", "Landroid/graphics/Point;", "polyList", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData$UnderlineInfo;", "underlineList", "", "score", "Landroid/app/RemoteAction;", "action", "actionId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;FLandroid/app/RemoteAction;Ljava/lang/String;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "toOcrResultEntityInfo$deepsky_sdk_textextraction_8_5_30_release", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "toOcrResultEntityInfo", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getText", "getType", "Ljava/util/List;", "getRectList", "()Ljava/util/List;", "getPolyList", "getUnderlineList", "F", "getScore", "()F", "Landroid/app/RemoteAction;", "getAction", "()Landroid/app/RemoteAction;", "getActionId", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EntityInfo {
        private final RemoteAction action;
        private final String actionId;
        private final List<Point[]> polyList;
        private final List<Rect> rectList;
        private final float score;
        private final String text;
        private final String type;
        private final List<UnderlineInfo> underlineList;

        public EntityInfo(String str, String str2, List<Rect> list, List<Point[]> list2, List<UnderlineInfo> list3, float f, RemoteAction remoteAction, String str3) {
            j.e(str, "text");
            j.e(str2, "type");
            j.e(list, "rectList");
            j.e(list2, "polyList");
            j.e(list3, "underlineList");
            j.e(str3, "actionId");
            this.text = str;
            this.type = str2;
            this.rectList = list;
            this.polyList = list2;
            this.underlineList = list3;
            this.score = f;
            this.action = remoteAction;
            this.actionId = str3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EntityInfo)) {
                return false;
            }
            EntityInfo entityInfo = (EntityInfo) obj;
            if (j.a(this.text, entityInfo.text) && j.a(this.type, entityInfo.type) && j.a(this.rectList, entityInfo.rectList) && j.a(this.polyList, entityInfo.polyList) && j.a(this.underlineList, entityInfo.underlineList) && Float.compare(this.score, entityInfo.score) == 0 && j.a(this.action, entityInfo.action) && j.a(this.actionId, entityInfo.actionId)) {
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

        public final String getText() {
            return this.text;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int i2;
            int a7 = N2.j.a(this.score, C0212a.f(this.underlineList, C0212a.f(this.polyList, C0212a.f(this.rectList, C0212a.d(this.text.hashCode() * 31, 31, this.type), 31), 31), 31), 31);
            RemoteAction remoteAction = this.action;
            if (remoteAction == null) {
                i2 = 0;
            } else {
                i2 = remoteAction.hashCode();
            }
            return this.actionId.hashCode() + ((a7 + i2) * 31);
        }

        public final OcrResult.EntityInfo toOcrResultEntityInfo$deepsky_sdk_textextraction_8_5_30_release() {
            List list;
            String str = this.text;
            String str2 = this.type;
            Rect[] rectArr = (Rect[]) this.rectList.toArray(new Rect[0]);
            Point[][] pointArr = (Point[][]) this.polyList.toArray(new Point[0][]);
            Iterable<UnderlineInfo> iterable = this.underlineList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (UnderlineInfo underlineInfo : iterable) {
                arrayList.add(new OcrResult.UnderlineInfo(underlineInfo.getStart(), underlineInfo.getEnd()));
            }
            OcrResult.UnderlineInfo[] underlineInfoArr = (OcrResult.UnderlineInfo[]) arrayList.toArray(new OcrResult.UnderlineInfo[0]);
            float f = this.score;
            RemoteAction remoteAction = this.action;
            if (remoteAction != null) {
                list = C0246a.e0(remoteAction);
            } else {
                list = C1202t.d;
            }
            return new OcrResult.EntityInfo(str, str2, rectArr, pointArr, underlineInfoArr, f, list);
        }

        public String toString() {
            String str = this.text;
            String str2 = this.type;
            List<Rect> list = this.rectList;
            List<Point[]> list2 = this.polyList;
            List<UnderlineInfo> list3 = this.underlineList;
            float f = this.score;
            RemoteAction remoteAction = this.action;
            String str3 = this.actionId;
            StringBuilder q = C0086a.q("EntityInfo(text=", str, ", type=", str2, ", rectList=");
            q.append(list);
            q.append(", polyList=");
            q.append(list2);
            q.append(", underlineList=");
            q.append(list3);
            q.append(", score=");
            q.append(f);
            q.append(", action=");
            q.append(remoteAction);
            q.append(", actionId=");
            q.append(str3);
            q.append(")");
            return q.toString();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData$UnderlineInfo;", "", "Landroid/graphics/PointF;", "start", "end", "<init>", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroid/graphics/PointF;", "getStart", "()Landroid/graphics/PointF;", "getEnd", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnderlineInfo {
        private final PointF end;
        private final PointF start;

        public UnderlineInfo(PointF pointF, PointF pointF2) {
            j.e(pointF, "start");
            j.e(pointF2, "end");
            this.start = pointF;
            this.end = pointF2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UnderlineInfo)) {
                return false;
            }
            UnderlineInfo underlineInfo = (UnderlineInfo) obj;
            if (j.a(this.start, underlineInfo.start) && j.a(this.end, underlineInfo.end)) {
                return true;
            }
            return false;
        }

        public final PointF getEnd() {
            return this.end;
        }

        public final PointF getStart() {
            return this.start;
        }

        public int hashCode() {
            return this.end.hashCode() + (this.start.hashCode() * 31);
        }

        public String toString() {
            PointF pointF = this.start;
            PointF pointF2 = this.end;
            return "UnderlineInfo(start=" + pointF + ", end=" + pointF2 + ")";
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EntityData(List<EntityInfo> list) {
        this(list, (Rect) null, 2, (e) null);
        j.e(list, "entityList");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityData)) {
            return false;
        }
        EntityData entityData = (EntityData) obj;
        if (j.a(this.entityList, entityData.entityList) && j.a(this.validRect, entityData.validRect)) {
            return true;
        }
        return false;
    }

    public final List<EntityInfo> getEntityList() {
        return this.entityList;
    }

    public int hashCode() {
        return this.validRect.hashCode() + (this.entityList.hashCode() * 31);
    }

    public String toString() {
        List<EntityInfo> list = this.entityList;
        Rect rect = this.validRect;
        return "EntityData(entityList=" + list + ", validRect=" + rect + ")";
    }

    public EntityData(List<EntityInfo> list, Rect rect) {
        j.e(list, "entityList");
        j.e(rect, "validRect");
        this.entityList = list;
        this.validRect = rect;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ EntityData(java.util.List r2, android.graphics.Rect r3, int r4, kotlin.jvm.internal.e r5) {
        /*
            r1 = this;
            r4 = r4 & 2
            if (r4 == 0) goto L_0x0034
            com.samsung.android.app.sdk.deepsky.textextraction.util.OcrUtil r3 = com.samsung.android.app.sdk.deepsky.textextraction.util.OcrUtil.INSTANCE
            r4 = r2
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r0 = 10
            int r0 = ne.C1196n.w0(r4, r0)
            r5.<init>(r0)
            java.util.Iterator r4 = r4.iterator()
        L_0x0018:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x002c
            java.lang.Object r0 = r4.next()
            com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData$EntityInfo r0 = (com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData.EntityInfo) r0
            java.util.List r0 = r0.getRectList()
            r5.add(r0)
            goto L_0x0018
        L_0x002c:
            java.util.ArrayList r4 = ne.C1196n.x0(r5)
            android.graphics.Rect r3 = r3.calculateBoundingRect(r4)
        L_0x0034:
            r1.<init>(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData.<init>(java.util.List, android.graphics.Rect, int, kotlin.jvm.internal.e):void");
    }
}
