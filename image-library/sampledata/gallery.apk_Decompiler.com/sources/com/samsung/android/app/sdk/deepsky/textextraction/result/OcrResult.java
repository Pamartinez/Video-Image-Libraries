package com.samsung.android.app.sdk.deepsky.textextraction.result;

import L2.a;
import Tf.n;
import android.app.RemoteAction;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1200r;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 !2\u00020\u0001:\u0007!\"#$%&'B1\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000eJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0019¨\u0006("}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo;", "blockInfoList", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "entityInfoList", "", "languageTags", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "Landroid/os/Parcel;", "in", "(Landroid/os/Parcel;)V", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/util/List;", "getBlockInfoList", "()Ljava/util/List;", "getEntityInfoList", "Ljava/lang/String;", "getLanguageTags", "()Ljava/lang/String;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "getWordInfoList", "wordInfoList", "Companion", "CharInfo", "WordInfo", "LineInfo", "EntityInfo", "UnderlineInfo", "BlockInfo", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrResult implements Parcelable {
    public static final Parcelable.Creator<OcrResult> CREATOR = new OcrResult$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((e) null);
    private final List<BlockInfo> blockInfoList;
    private final List<EntityInfo> entityInfoList;
    private final String languageTags;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public OcrResult(List<BlockInfo> list, List<EntityInfo> list2, String str) {
        j.e(list, "blockInfoList");
        j.e(list2, "entityInfoList");
        j.e(str, "languageTags");
        this.blockInfoList = list;
        this.entityInfoList = list2;
        this.languageTags = str;
    }

    public int describeContents() {
        return 0;
    }

    public final List<WordInfo> getWordInfoList() {
        ArrayList arrayList = new ArrayList();
        for (BlockInfo lineInfo : this.blockInfoList) {
            for (LineInfo wordInfo : lineInfo.getLineInfo()) {
                arrayList.addAll(wordInfo.getWordInfo());
            }
        }
        return arrayList;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeTypedList(this.blockInfoList);
        parcel.writeTypedList(this.entityInfoList);
        parcel.writeString(this.languageTags);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "Landroid/os/Parcelable;", "Landroid/graphics/PointF;", "start", "stop", "<init>", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/graphics/PointF;", "getStart", "()Landroid/graphics/PointF;", "getStop", "CREATOR", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnderlineInfo implements Parcelable {
        public static final CREATOR CREATOR = new CREATOR((e) null);
        private final PointF start;
        private final PointF stop;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class CREATOR implements Parcelable.Creator<UnderlineInfo> {
            public /* synthetic */ CREATOR(e eVar) {
                this();
            }

            private CREATOR() {
            }

            public UnderlineInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                return new UnderlineInfo(parcel);
            }

            public UnderlineInfo[] newArray(int i2) {
                return new UnderlineInfo[i2];
            }
        }

        public UnderlineInfo(PointF pointF, PointF pointF2) {
            this.start = pointF;
            this.stop = pointF2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "parcel");
            parcel.writeParcelable(this.start, i2);
            parcel.writeParcelable(this.stop, i2);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UnderlineInfo(android.os.Parcel r3) {
            /*
                r2 = this;
                java.lang.String r0 = "parcel"
                kotlin.jvm.internal.j.e(r3, r0)
                java.lang.Class<android.graphics.PointF> r0 = android.graphics.PointF.class
                java.lang.ClassLoader r1 = r0.getClassLoader()
                android.os.Parcelable r1 = r3.readParcelable(r1)
                android.graphics.PointF r1 = (android.graphics.PointF) r1
                java.lang.ClassLoader r0 = r0.getClassLoader()
                android.os.Parcelable r3 = r3.readParcelable(r0)
                android.graphics.PointF r3 = (android.graphics.PointF) r3
                r2.<init>(r1, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.result.OcrResult.UnderlineInfo.<init>(android.os.Parcel):void");
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 !2\u00020\u0001:\u0001!B-\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000eJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo;", "lineInfoList", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "Landroid/os/Parcel;", "in", "(Landroid/os/Parcel;)V", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "lineInfo", "Ljava/util/List;", "getLineInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BlockInfo implements Parcelable {
        public static final Parcelable.Creator<BlockInfo> CREATOR = new OcrResult$BlockInfo$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((e) null);
        private final List<LineInfo> lineInfo;
        private final Point[] poly;
        private final Rect rect;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$BlockInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public BlockInfo(List<LineInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "lineInfoList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.lineInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
        }

        public int describeContents() {
            return 0;
        }

        public final List<LineInfo> getLineInfo() {
            return this.lineInfo;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeTypedList(this.lineInfo);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray((Parcelable[]) this.poly, i2);
        }

        public BlockInfo(Parcel parcel) {
            j.e(parcel, "in");
            ArrayList arrayList = new ArrayList();
            this.lineInfo = arrayList;
            parcel.readTypedList(arrayList, LineInfo.CREATOR);
            Rect rect2 = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.rect = rect2 == null ? new Rect() : rect2;
            Point[] pointArr = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.poly = pointArr == null ? new Point[0] : pointArr;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B'\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\t\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo;", "Landroid/os/Parcelable;", "", "character", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "Landroid/os/Parcel;", "in", "(Landroid/os/Parcel;)V", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "string", "Ljava/lang/String;", "getString", "()Ljava/lang/String;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CharInfo implements Parcelable {
        public static final Parcelable.Creator<CharInfo> CREATOR = new OcrResult$CharInfo$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((e) null);
        private final Point[] poly;
        private final Rect rect;
        private final String string;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public CharInfo(String str, Rect rect2, Point[] pointArr) {
            j.e(str, "character");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.string = str;
            this.rect = rect2;
            this.poly = pointArr;
        }

        public int describeContents() {
            return 0;
        }

        public final String getString() {
            return this.string;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeString(this.string);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray((Parcelable[]) this.poly, i2);
        }

        public CharInfo(Parcel parcel) {
            j.e(parcel, "in");
            this.string = parcel.readString();
            Rect rect2 = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.rect = rect2 == null ? new Rect() : rect2;
            Point[] pointArr = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.poly = pointArr == null ? new Point[0] : pointArr;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 !2\u00020\u0001:\u0001!B-\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "wordInfoList", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "Landroid/os/Parcel;", "in", "(Landroid/os/Parcel;)V", "", "describeContents", "()I", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "wordInfo", "Ljava/util/List;", "getWordInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LineInfo implements Parcelable {
        public static final Parcelable.Creator<LineInfo> CREATOR = new OcrResult$LineInfo$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((e) null);
        private final Point[] poly;
        private final Rect rect;
        private final List<WordInfo> wordInfo;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$LineInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public LineInfo(List<? extends WordInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "wordInfoList");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.wordInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
        }

        public int describeContents() {
            return 0;
        }

        public final List<WordInfo> getWordInfo() {
            return this.wordInfo;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeTypedList(this.wordInfo);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray((Parcelable[]) this.poly, i2);
        }

        public LineInfo(Parcel parcel) {
            j.e(parcel, "in");
            ArrayList arrayList = new ArrayList();
            this.wordInfo = arrayList;
            parcel.readTypedList(arrayList, WordInfo.CREATOR);
            Rect rect2 = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.rect = rect2 == null ? new Rect() : rect2;
            Point[] pointArr = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.poly = pointArr == null ? new Point[0] : pointArr;
        }
    }

    public OcrResult(Parcel parcel) {
        j.e(parcel, "in");
        ArrayList arrayList = new ArrayList();
        this.blockInfoList = arrayList;
        parcel.readTypedList(arrayList, BlockInfo.CREATOR);
        ArrayList arrayList2 = new ArrayList();
        this.entityInfoList = arrayList2;
        parcel.readTypedList(arrayList2, EntityInfo.CREATOR);
        String readString = parcel.readString();
        this.languageTags = readString == null ? "" : readString;
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u0000 ,2\u00020\u0001:\u0001,B-\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\u000f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010$R\u0017\u0010&\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0011\u0010+\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b*\u0010)¨\u0006-"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$CharInfo;", "charInfo", "Landroid/graphics/Rect;", "rect", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/util/List;Landroid/graphics/Rect;[Landroid/graphics/Point;)V", "Landroid/os/Parcel;", "in", "(Landroid/os/Parcel;)V", "", "describeContents", "()I", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "Ljava/util/List;", "getCharInfo", "()Ljava/util/List;", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "label", "I", "getLabel", "setLabel", "(I)V", "", "wordString", "Ljava/lang/String;", "getWordString", "()Ljava/lang/String;", "getString", "string", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WordInfo implements Parcelable {
        public static final Parcelable.Creator<WordInfo> CREATOR = new OcrResult$WordInfo$Companion$CREATOR$1();
        public static final Companion Companion = new Companion((e) null);
        private final List<CharInfo> charInfo;
        private int label;
        private final Point[] poly;
        private final Rect rect;
        private final String wordString;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$WordInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public WordInfo(List<? extends CharInfo> list, Rect rect2, Point[] pointArr) {
            j.e(list, "charInfo");
            j.e(rect2, "rect");
            j.e(pointArr, "poly");
            this.charInfo = list;
            this.rect = rect2;
            this.poly = pointArr;
            this.label = -1;
            this.wordString = "";
        }

        public int describeContents() {
            return 0;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final String getString() {
            if (this.wordString.length() > 0) {
                return this.wordString;
            }
            StringBuilder sb2 = new StringBuilder();
            for (CharInfo string : this.charInfo) {
                sb2.append(string.getString());
            }
            String sb3 = sb2.toString();
            j.d(sb3, "toString(...)");
            return n.R0(sb3).toString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            j.e(parcel, "dest");
            parcel.writeTypedList(this.charInfo);
            parcel.writeParcelable(this.rect, i2);
            parcel.writeTypedArray((Parcelable[]) this.poly, i2);
            parcel.writeInt(this.label);
            parcel.writeString(this.wordString);
        }

        public WordInfo(Parcel parcel) {
            j.e(parcel, "in");
            this.label = -1;
            ArrayList arrayList = new ArrayList();
            this.charInfo = arrayList;
            parcel.readTypedList(arrayList, CharInfo.CREATOR);
            Rect rect2 = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            this.rect = rect2 == null ? new Rect() : rect2;
            Point[] pointArr = (Point[]) parcel.createTypedArray(Point.CREATOR);
            this.poly = pointArr == null ? new Point[0] : pointArr;
            this.label = parcel.readInt();
            String readString = parcel.readString();
            this.wordString = readString == null ? "" : readString;
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 52\u00020\u0001:\u00015B_\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012B\u0011\b\u0016\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0011\u0010\u0015J\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b\"\u0010!R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010%R#\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b'\u0010(R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u001eR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b0\u00101R\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u00102\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "Landroid/os/Parcelable;", "", "text", "type", "", "Landroid/graphics/Rect;", "rects", "Landroid/graphics/Point;", "polys", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "underlines", "", "score", "", "Landroid/app/RemoteAction;", "actions", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Landroid/graphics/Rect;[[Landroid/graphics/Point;[Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;FLjava/util/List;)V", "Landroid/os/Parcel;", "parcel", "(Landroid/os/Parcel;)V", "getFlattenedPoly", "()[Landroid/graphics/Point;", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "getType", "[Landroid/graphics/Rect;", "getRects", "()[Landroid/graphics/Rect;", "[[Landroid/graphics/Point;", "getPolys", "()[[Landroid/graphics/Point;", "[Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "getUnderlines", "()[Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$UnderlineInfo;", "label", "I", "getLabel", "F", "getScore", "()F", "Ljava/util/List;", "getActions", "()Ljava/util/List;", "CREATOR", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EntityInfo implements Parcelable {
        public static final CREATOR CREATOR = new CREATOR((e) null);
        private final List<RemoteAction> actions;
        private final int label;
        private final Point[][] polys;
        private final Rect[] rects;
        private final float score;
        private final String text;
        private final String type;
        private final UnderlineInfo[] underlines;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult$EntityInfo;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class CREATOR implements Parcelable.Creator<EntityInfo> {
            public /* synthetic */ CREATOR(e eVar) {
                this();
            }

            private CREATOR() {
            }

            public EntityInfo createFromParcel(Parcel parcel) {
                j.e(parcel, "parcel");
                return new EntityInfo(parcel);
            }

            public EntityInfo[] newArray(int i2) {
                return new EntityInfo[i2];
            }
        }

        public EntityInfo(String str, String str2, Rect[] rectArr, Point[][] pointArr, UnderlineInfo[] underlineInfoArr, float f, List<RemoteAction> list) {
            j.e(str, "text");
            j.e(str2, "type");
            j.e(rectArr, "rects");
            j.e(pointArr, "polys");
            j.e(underlineInfoArr, "underlines");
            j.e(list, "actions");
            this.text = str;
            this.type = str2;
            this.rects = rectArr;
            this.polys = pointArr;
            this.underlines = underlineInfoArr;
            this.label = -1;
            this.score = f;
            this.actions = list;
        }

        private final Point[] getFlattenedPoly() {
            ArrayList arrayList = new ArrayList();
            for (Point[] B02 : this.polys) {
                C1200r.B0(arrayList, B02);
            }
            return (Point[]) arrayList.toArray(new Point[0]);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            Object obj;
            j.e(parcel, "parcel");
            parcel.writeString(this.text);
            parcel.writeString(this.type);
            try {
                obj = (Rect) C1192j.m0(this.rects);
            } catch (Throwable th) {
                obj = a.l(th);
            }
            if (obj instanceof me.j) {
                obj = null;
            }
            Rect rect = (Rect) obj;
            if (rect == null) {
                rect = new Rect();
            }
            Point[] flattenedPoly = getFlattenedPoly();
            parcel.writeParcelable(rect, i2);
            parcel.writeTypedArray((Parcelable[]) flattenedPoly, i2);
            parcel.writeTypedArray((Parcelable[]) this.underlines, i2);
            parcel.writeInt(this.label);
            parcel.writeFloat(this.score);
            parcel.writeTypedList(this.actions);
        }

        public EntityInfo(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String str = "";
            this.text = readString == null ? str : readString;
            String readString2 = parcel.readString();
            this.type = readString2 != null ? readString2 : str;
            Rect rect = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
            rect = rect == null ? new Rect() : rect;
            Point[] pointArr = (Point[]) parcel.createTypedArray(Point.CREATOR);
            pointArr = pointArr == null ? new Point[0] : pointArr;
            UnderlineInfo[] underlineInfoArr = (UnderlineInfo[]) parcel.createTypedArray(UnderlineInfo.CREATOR);
            this.underlines = underlineInfoArr == null ? new UnderlineInfo[0] : underlineInfoArr;
            this.label = parcel.readInt();
            this.score = parcel.readFloat();
            ArrayList arrayList = new ArrayList();
            this.actions = arrayList;
            parcel.readTypedList(arrayList, RemoteAction.CREATOR);
            this.rects = new Rect[]{rect};
            ArrayList arrayList2 = new ArrayList();
            int length = pointArr.length / 4;
            for (int i2 = 0; i2 < length; i2++) {
                int i7 = i2 * 4;
                arrayList2.add(new Point[]{pointArr[i7], pointArr[i7 + 1], pointArr[i7 + 2], pointArr[i7 + 3]});
            }
            this.polys = (Point[][]) arrayList2.toArray(new Point[0][]);
        }
    }
}
