package h3;

import Ae.b;
import com.google.android.appfunctions.schema.photos.v1.Album;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.UriBarcodeAdapterFactory;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.gallery.crossapp.function.AlbumFunctions;
import com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationCommandType;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ a(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return Boolean.valueOf(UriBarcodeAdapterFactory.isQuickShareUri$lambda$0(this.e, (String) obj));
            case 1:
                return Boolean.valueOf(UriBarcodeAdapterFactory.isSamsungAccountUri$lambda$2(this.e, (String) obj));
            case 2:
                return AlbumFunctions.queryAlbum$lambda$3$lambda$1(this.e, (Album) obj);
            case 3:
                return OcrData.toBlockStringList$lambda$5$lambda$4$lambda$3(this.e, (OcrData.WordInfo) obj);
            case 4:
                return OcrData.joinToStringWithSeparator$lambda$9$lambda$8$lambda$7(this.e, (OcrData.WordInfo) obj);
            case 5:
                return VexFwkImageTranslator.getAvailableSourceLanguageTo$lambda$47$lambda$39(this.e, (VexFwkMetadataNative) obj);
            case 6:
                return VexFwkImageTranslator.getAvailableTargetLanguageFrom$lambda$38$lambda$30(this.e, (VexFwkMetadataNative) obj);
            case 7:
                VexFwkMetadataNative vexFwkMetadataNative = (VexFwkMetadataNative) obj;
                vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
                vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, this.e);
                return x.f4917a;
            default:
                VexFwkMetadataNative vexFwkMetadataNative2 = (VexFwkMetadataNative) obj;
                vexFwkMetadataNative2.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
                vexFwkMetadataNative2.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, this.e);
                return x.f4917a;
        }
    }
}
