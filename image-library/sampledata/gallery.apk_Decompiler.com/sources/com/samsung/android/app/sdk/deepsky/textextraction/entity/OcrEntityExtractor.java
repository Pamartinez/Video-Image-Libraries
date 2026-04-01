package com.samsung.android.app.sdk.deepsky.textextraction.entity;

import Ae.b;
import L2.a;
import Tf.n;
import android.app.RemoteAction;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.textclassifier.TextClassification;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.EntityUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;
import oe.C1214c;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001:\u000289B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J5\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ;\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u00062\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001e\u0010\u001bJ\u001f\u0010!\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0002¢\u0006\u0004\b!\u0010\"J1\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002¢\u0006\u0004\b!\u0010%J9\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160&0\u00062\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002¢\u0006\u0004\b'\u0010(J\u001f\u0010*\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0016H\u0002¢\u0006\u0004\b*\u0010+J\u001f\u0010,\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0016H\u0002¢\u0006\u0004\b,\u0010+J5\u0010/\u001a\u00020\r2\u0006\u0010.\u001a\u00020-2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b/\u00100J\u0011\u00101\u001a\u00020\u0004*\u00020-¢\u0006\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u0002038\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00105R\u0014\u00107\u001a\u0002038\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u00105¨\u0006:"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor;", "", "<init>", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;", "sequence", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "entityTypes", "Landroid/graphics/Rect;", "validRect", "Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "textClassificationHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;", "extractEntityInternal", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;Ljava/util/List;Landroid/graphics/Rect;Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;)Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;", "Landroid/view/textclassifier/TextClassification;", "classification", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityItem;", "parseActionItems", "(Landroid/view/textclassifier/TextClassification;Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;Landroid/graphics/Rect;)Ljava/util/List;", "seq", "", "start", "end", "entityType", "createRectList", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;IILcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;)Ljava/util/List;", "", "Landroid/graphics/Point;", "createPolyList", "r1", "r2", "sum", "(Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Rect;", "p1", "p2", "([Landroid/graphics/Point;[Landroid/graphics/Point;)[Landroid/graphics/Point;", "Lme/i;", "getLineIndexList", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;II)Ljava/util/List;", "index", "adjustEmptyCharacterStartIndex", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;I)I", "adjustEmptyCharacterEndIndex", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "extractEntity", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;Ljava/util/List;Landroid/graphics/Rect;Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;)Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityExtractionResult;", "toCharacterSequence", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$Character;", "BLOCK_SEPARATOR", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$Character;", "LINE_SEPARATOR", "WORD_SEPARATOR", "CharacterSequence", "Character", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrEntityExtractor {
    private static final Character BLOCK_SEPARATOR = new Character("\n", "WHITESPACE_BLOCK", (Point[]) null, 4, (e) null);
    public static final OcrEntityExtractor INSTANCE = new OcrEntityExtractor();
    private static final Character LINE_SEPARATOR = new Character("\n", "WHITESPACE_LINE", (Point[]) null, 4, (e) null);
    private static final Character WORD_SEPARATOR = new Character(" ", "WHITESPACE_WORD", (Point[]) null, 4, (e) null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\nR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$CharacterSequence;", "", "", "text", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$Character;", "data", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getText", "Ljava/util/List;", "getData", "()Ljava/util/List;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CharacterSequence {
        private final List<Character> data;
        private final String text;

        public CharacterSequence(String str, List<Character> list) {
            j.e(str, "text");
            j.e(list, "data");
            this.text = str;
            this.data = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CharacterSequence)) {
                return false;
            }
            CharacterSequence characterSequence = (CharacterSequence) obj;
            if (j.a(this.text, characterSequence.text) && j.a(this.data, characterSequence.data)) {
                return true;
            }
            return false;
        }

        public final List<Character> getData() {
            return this.data;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return this.data.hashCode() + (this.text.hashCode() * 31);
        }

        public String toString() {
            String str = this.text;
            List<Character> list = this.data;
            return "CharacterSequence(text=" + str + ", data=" + list + ")";
        }
    }

    private OcrEntityExtractor() {
    }

    private final int adjustEmptyCharacterEndIndex(CharacterSequence characterSequence, int i2) {
        List<Character> data = characterSequence.getData();
        int i7 = 0;
        Iterator it = C1194l.Z0(data.subList(0, i2)).iterator();
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            } else if (!j.a(((Character) it.next()).getTag(), "WHITESPACE_WORD")) {
                break;
            } else {
                i7++;
            }
        }
        return i2 - i7;
    }

    private final int adjustEmptyCharacterStartIndex(CharacterSequence characterSequence, int i2) {
        Iterator<Character> it = characterSequence.getData().subList(i2, characterSequence.getData().size()).iterator();
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            } else if (!j.a(it.next().getTag(), "WHITESPACE_WORD")) {
                break;
            } else {
                i7++;
            }
        }
        return i2 + i7;
    }

    private final List<Point[]> createPolyList(CharacterSequence characterSequence, int i2, int i7, EntityType entityType) {
        boolean z;
        if (EntityUtils.INSTANCE.isDateTimeEntity(entityType)) {
            return C1202t.d;
        }
        List<i> lineIndexList = getLineIndexList(characterSequence, i2, i7);
        ArrayList arrayList = new ArrayList();
        for (i next : lineIndexList) {
            List<Character> subList = characterSequence.getData().subList(adjustEmptyCharacterStartIndex(characterSequence, ((Number) next.d).intValue()), adjustEmptyCharacterEndIndex(characterSequence, ((Number) next.e).intValue()));
            if (!subList.isEmpty()) {
                Point[] sum = sum(((Character) C1194l.L0(subList)).getPoly(), ((Character) C1194l.T0(subList)).getPoly());
                if (sum.length == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    arrayList.add(sum);
                }
            }
        }
        return arrayList;
    }

    private final List<Rect> createRectList(CharacterSequence characterSequence, int i2, int i7, EntityType entityType) {
        if (EntityUtils.INSTANCE.isDateTimeEntity(entityType)) {
            return new ArrayList();
        }
        List<i> lineIndexList = getLineIndexList(characterSequence, i2, i7);
        ArrayList arrayList = new ArrayList();
        for (i next : lineIndexList) {
            List<Character> subList = characterSequence.getData().subList(adjustEmptyCharacterStartIndex(characterSequence, ((Number) next.d).intValue()), adjustEmptyCharacterEndIndex(characterSequence, ((Number) next.e).intValue()));
            if (!subList.isEmpty()) {
                Rect sum = sum(((Character) C1194l.L0(subList)).getRect(), ((Character) C1194l.T0(subList)).getRect());
                if (!sum.isEmpty()) {
                    arrayList.add(sum);
                }
            }
        }
        return arrayList;
    }

    private final EntityExtractionResult extractEntityInternal(CharacterSequence characterSequence, List<? extends EntityType> list, Rect rect, TextClassificationHelper textClassificationHelper) {
        TextClassification classifyText$default;
        String k = C0212a.k(characterSequence.hashCode(), "SSS|", "|", C1194l.R0(list, GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (b) null, 62));
        EntityExtractionResult entityExtractionResult = new EntityExtractionResult(k, C1202t.d, "");
        if (n.R0(characterSequence.getText()).toString().length() == 0 || (classifyText$default = TextClassificationHelper.classifyText$default(textClassificationHelper, characterSequence.getText(), (String) null, (String) null, true, 6, (Object) null)) == null) {
            return entityExtractionResult;
        }
        List<EntityItem> parseActionItems = INSTANCE.parseActionItems(classifyText$default, characterSequence, rect);
        String string = classifyText$default.getExtras().getString("language-tags", "");
        int size = parseActionItems.size();
        LibLogger.i("OcrEntityExtractor", "extractEntity, actionItems.size: " + size + ", languageTag: " + string);
        j.b(string);
        return new EntityExtractionResult(k, parseActionItems, string);
    }

    private final List<i> getLineIndexList(CharacterSequence characterSequence, int i2, int i7) {
        String substring = characterSequence.getText().substring(i2, i7);
        j.d(substring, "substring(...)");
        List<String> K02 = n.K0(substring, new String[]{"\n"});
        ArrayList arrayList = new ArrayList();
        int i8 = 0;
        for (String str : K02) {
            int i10 = i2 + i8;
            arrayList.add(new i(Integer.valueOf(i10), Integer.valueOf(str.length() + i10)));
            i8 += str.length() + 1;
        }
        return arrayList;
    }

    private final List<EntityItem> parseActionItems(TextClassification textClassification, CharacterSequence characterSequence, Rect rect) {
        Object obj;
        List list;
        Object obj2;
        Object obj3;
        TextClassification textClassification2 = textClassification;
        CharacterSequence characterSequence2 = characterSequence;
        EntityScoreGenerator entityScoreGenerator = new EntityScoreGenerator();
        List<RemoteAction> actions = textClassification2.getActions();
        j.d(actions, "getActions(...)");
        ArrayList arrayList = new ArrayList();
        for (RemoteAction remoteAction : actions) {
            EntityUtils entityUtils = EntityUtils.INSTANCE;
            j.b(remoteAction);
            String entityText = entityUtils.getEntityText(textClassification2, remoteAction);
            int entityStartIndex = entityUtils.getEntityStartIndex(textClassification2, remoteAction);
            if (entityStartIndex < 0) {
                entityStartIndex = 0;
            }
            int entityEndIndex = entityUtils.getEntityEndIndex(textClassification2, remoteAction);
            int length = characterSequence2.getText().length();
            if (entityEndIndex > length) {
                entityEndIndex = length;
            }
            EntityType.Companion companion = EntityType.Companion;
            EntityType entityType = companion.toEntityType(entityUtils.getEntityTypeId(textClassification2, remoteAction));
            String entityActionId = entityUtils.getEntityActionId(textClassification2, remoteAction);
            boolean entityIsPoiEntity = entityUtils.getEntityIsPoiEntity(textClassification2, remoteAction);
            StringBuilder sb2 = new StringBuilder("parsing actionItem with entityType(");
            sb2.append(entityType);
            sb2.append("), actionId(");
            sb2.append(entityActionId);
            sb2.append(") startIndex(");
            N2.j.x(sb2, entityStartIndex, ") endIndex(", entityEndIndex, ") isPoi(");
            sb2.append(entityIsPoiEntity);
            sb2.append(")");
            LibLogger.i("OcrEntityExtractor", sb2.toString());
            boolean contains = companion.listOfEntityType().contains(entityType);
            EntityItem entityItem = null;
            if (!contains) {
                Rect rect2 = rect;
            } else {
                try {
                    obj = INSTANCE.createRectList(characterSequence2, entityStartIndex, entityEndIndex, entityType);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                List list2 = (List) obj;
                List list3 = C1202t.d;
                if (list2 == null) {
                    list = list3;
                } else {
                    list = list2;
                }
                try {
                    obj2 = INSTANCE.createPolyList(characterSequence2, entityStartIndex, entityEndIndex, entityType);
                } catch (Throwable th2) {
                    obj2 = a.l(th2);
                }
                if (obj2 instanceof me.j) {
                    obj2 = null;
                }
                List list4 = (List) obj2;
                if (list4 != null) {
                    list3 = list4;
                }
                try {
                    obj3 = (Rect) C1194l.L0(list);
                } catch (Throwable th3) {
                    obj3 = a.l(th3);
                }
                if (!(obj3 instanceof me.j)) {
                    entityItem = obj3;
                }
                Rect rect3 = entityItem;
                if (rect3 == null) {
                    rect3 = new Rect();
                }
                entityItem = new EntityItem(entityType, entityText, list, list3, entityScoreGenerator.measureEntityScore(entityText, entityType, rect3, rect), remoteAction, entityActionId);
            }
            if (entityItem != null) {
                arrayList.add(entityItem);
            }
        }
        return arrayList;
    }

    private final Rect sum(Rect rect, Rect rect2) {
        return new Rect(Math.min(rect.left, rect2.left), Math.min(rect.top, rect2.top), Math.max(rect.right, rect2.right), Math.max(rect.bottom, rect2.bottom));
    }

    /* access modifiers changed from: private */
    public static final CharSequence toCharacterSequence$lambda$16(Character character) {
        j.e(character, "it");
        return character.getText();
    }

    public final EntityExtractionResult extractEntity(OcrData ocrData, List<? extends EntityType> list, Rect rect, TextClassificationHelper textClassificationHelper) {
        j.e(ocrData, "ocrData");
        j.e(list, "entityTypes");
        j.e(textClassificationHelper, "textClassificationHelper");
        return extractEntityInternal(toCharacterSequence(ocrData), list, rect, textClassificationHelper);
    }

    public final CharacterSequence toCharacterSequence(OcrData ocrData) {
        OcrData ocrData2 = ocrData;
        j.e(ocrData2, "<this>");
        C1214c R = C0246a.R();
        int i2 = 0;
        for (Object next : ocrData2.getBlockList()) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                OcrData.BlockInfo blockInfo = (OcrData.BlockInfo) next;
                int i8 = 0;
                for (Object next2 : blockInfo.getLineList()) {
                    int i10 = i8 + 1;
                    if (i8 >= 0) {
                        OcrData.LineInfo lineInfo = (OcrData.LineInfo) next2;
                        int i11 = 0;
                        for (Object next3 : lineInfo.getWordList()) {
                            int i12 = i11 + 1;
                            if (i11 >= 0) {
                                Iterable<OcrData.CharInfo> charList = ((OcrData.WordInfo) next3).getCharList();
                                ArrayList arrayList = new ArrayList(C1196n.w0(charList, 10));
                                for (OcrData.CharInfo charInfo : charList) {
                                    arrayList.add(new Character(charInfo.getText(), (String) null, charInfo.getPoly(), 2, (e) null));
                                }
                                R.addAll(arrayList);
                                if (i11 != C1195m.p0(lineInfo.getWordList())) {
                                    R.add(WORD_SEPARATOR);
                                }
                                i11 = i12;
                            } else {
                                C1195m.v0();
                                throw null;
                            }
                        }
                        if (i8 != C1195m.p0(blockInfo.getLineList())) {
                            R.add(LINE_SEPARATOR);
                        }
                        i8 = i10;
                    } else {
                        C1195m.v0();
                        throw null;
                    }
                }
                if (i2 != C1195m.p0(ocrData2.getBlockList())) {
                    Character character = BLOCK_SEPARATOR;
                    R.add(character);
                    R.add(character);
                }
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        C1214c K6 = C0246a.K(R);
        return new CharacterSequence(C1194l.R0(K6, "", (String) null, (String) null, new com.samsung.android.vexfwk.sdk.docscan.b(8), 30), K6);
    }

    private final Point[] sum(Point[] pointArr, Point[] pointArr2) {
        Point point = pointArr[0];
        Point point2 = new Point(point.x, point.y);
        Point point3 = pointArr2[1];
        Point point4 = new Point(point3.x, point3.y);
        Point point5 = pointArr2[2];
        Point point6 = new Point(point5.x, point5.y);
        Point point7 = pointArr[3];
        return new Point[]{point2, point4, point6, new Point(point7.x, point7.y)};
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0015\u0010\u000bR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/OcrEntityExtractor$Character;", "", "", "text", "tag", "", "Landroid/graphics/Point;", "poly", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Landroid/graphics/Point;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getText", "getTag", "[Landroid/graphics/Point;", "getPoly", "()[Landroid/graphics/Point;", "Landroid/graphics/Rect;", "rect", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Character {
        private final Point[] poly;
        private final Rect rect;
        private final String tag;
        private final String text;

        public Character(String str, String str2, Point[] pointArr) {
            Rect rect2;
            j.e(str, "text");
            j.e(str2, "tag");
            j.e(pointArr, "poly");
            this.text = str;
            this.tag = str2;
            this.poly = pointArr;
            if (pointArr.length < 4) {
                rect2 = new Rect();
            } else if (pointArr.length != 0) {
                int i2 = pointArr[0].x;
                int i7 = 1;
                int length = pointArr.length - 1;
                if (1 <= length) {
                    int i8 = 1;
                    while (true) {
                        int i10 = pointArr[i8].x;
                        i2 = i2 > i10 ? i10 : i2;
                        if (i8 == length) {
                            break;
                        }
                        i8++;
                    }
                }
                Point[] pointArr2 = this.poly;
                if (pointArr2.length != 0) {
                    int i11 = pointArr2[0].y;
                    int length2 = pointArr2.length - 1;
                    if (1 <= length2) {
                        int i12 = 1;
                        while (true) {
                            int i13 = pointArr2[i12].y;
                            i11 = i11 > i13 ? i13 : i11;
                            if (i12 == length2) {
                                break;
                            }
                            i12++;
                        }
                    }
                    Point[] pointArr3 = this.poly;
                    if (pointArr3.length != 0) {
                        int i14 = pointArr3[0].x;
                        int length3 = pointArr3.length - 1;
                        if (1 <= length3) {
                            int i15 = 1;
                            while (true) {
                                int i16 = pointArr3[i15].x;
                                i14 = i14 < i16 ? i16 : i14;
                                if (i15 == length3) {
                                    break;
                                }
                                i15++;
                            }
                        }
                        Point[] pointArr4 = this.poly;
                        if (pointArr4.length != 0) {
                            int i17 = pointArr4[0].y;
                            int length4 = pointArr4.length - 1;
                            if (1 <= length4) {
                                while (true) {
                                    int i18 = pointArr4[i7].y;
                                    i17 = i17 < i18 ? i18 : i17;
                                    if (i7 == length4) {
                                        break;
                                    }
                                    i7++;
                                }
                            }
                            rect2 = new Rect(i2, i11, i14, i17);
                        } else {
                            throw new NoSuchElementException();
                        }
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new NoSuchElementException();
            }
            this.rect = rect2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Character)) {
                return false;
            }
            Character character = (Character) obj;
            if (j.a(this.text, character.text) && j.a(this.tag, character.tag) && j.a(this.poly, character.poly)) {
                return true;
            }
            return false;
        }

        public final Point[] getPoly() {
            return this.poly;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final String getTag() {
            return this.tag;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return C0212a.d(this.text.hashCode() * 31, 31, this.tag) + Arrays.hashCode(this.poly);
        }

        public String toString() {
            String str = this.text;
            String str2 = this.tag;
            return C0212a.p(C0086a.q("Character(text=", str, ", tag=", str2, ", poly="), Arrays.toString(this.poly), ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Character(String str, String str2, Point[] pointArr, int i2, e eVar) {
            this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? new Point[0] : pointArr);
        }
    }
}
