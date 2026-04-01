package com.samsung.android.app.sdk.deepsky.textextraction.result;

import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ&\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "entityData", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult;", "toOcrResult", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrResult;", "copy", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "getOcrData", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "getEntityData", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/result/EntityData;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextData {
    public static final Companion Companion = new Companion((e) null);
    private final EntityData entityData;
    private final OcrData ocrData;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData$Companion;", "", "<init>", "()V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextData(OcrData ocrData2, EntityData entityData2) {
        j.e(ocrData2, "ocrData");
        this.ocrData = ocrData2;
        this.entityData = entityData2;
    }

    public static /* synthetic */ TextData copy$default(TextData textData, OcrData ocrData2, EntityData entityData2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            ocrData2 = textData.ocrData;
        }
        if ((i2 & 2) != 0) {
            entityData2 = textData.entityData;
        }
        return textData.copy(ocrData2, entityData2);
    }

    public final TextData copy(OcrData ocrData2, EntityData entityData2) {
        j.e(ocrData2, "ocrData");
        return new TextData(ocrData2, entityData2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextData)) {
            return false;
        }
        TextData textData = (TextData) obj;
        if (j.a(this.ocrData, textData.ocrData) && j.a(this.entityData, textData.entityData)) {
            return true;
        }
        return false;
    }

    public final EntityData getEntityData() {
        return this.entityData;
    }

    public final OcrData getOcrData() {
        return this.ocrData;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.ocrData.hashCode() * 31;
        EntityData entityData2 = this.entityData;
        if (entityData2 == null) {
            i2 = 0;
        } else {
            i2 = entityData2.hashCode();
        }
        return hashCode + i2;
    }

    public final OcrResult toOcrResult() {
        ArrayList arrayList;
        List<EntityData.EntityInfo> entityList;
        Iterable<OcrData.BlockInfo> blockList = this.ocrData.getBlockList();
        ArrayList arrayList2 = new ArrayList(C1196n.w0(blockList, 10));
        for (OcrData.BlockInfo ocrResultBlockInfo$deepsky_sdk_textextraction_8_5_30_release : blockList) {
            arrayList2.add(ocrResultBlockInfo$deepsky_sdk_textextraction_8_5_30_release.toOcrResultBlockInfo$deepsky_sdk_textextraction_8_5_30_release());
        }
        EntityData entityData2 = this.entityData;
        if (entityData2 == null || (entityList = entityData2.getEntityList()) == null) {
            arrayList = new ArrayList();
        } else {
            Iterable<EntityData.EntityInfo> iterable = entityList;
            ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
            for (EntityData.EntityInfo ocrResultEntityInfo$deepsky_sdk_textextraction_8_5_30_release : iterable) {
                arrayList3.add(ocrResultEntityInfo$deepsky_sdk_textextraction_8_5_30_release.toOcrResultEntityInfo$deepsky_sdk_textextraction_8_5_30_release());
            }
            arrayList = C1194l.m1(arrayList3);
        }
        return new OcrResult(arrayList2, arrayList, "");
    }

    public String toString() {
        OcrData ocrData2 = this.ocrData;
        EntityData entityData2 = this.entityData;
        return "TextData(ocrData=" + ocrData2 + ", entityData=" + entityData2 + ")";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TextData(OcrData ocrData2, EntityData entityData2, int i2, e eVar) {
        this(ocrData2, (i2 & 2) != 0 ? null : entityData2);
    }
}
