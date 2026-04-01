package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SubCategoryType {
    static final String BUILDING;
    public static final String BUSINESS_CARD;
    public static final String CARTOON;
    public static final ArrayList<String> DOCUMENTS_SUB_GROUP_ONEUI_61 = new ArrayList<String>() {
        {
            add(DocumentSubGroup.PAYMENT_AND_IDENTIFICATION);
            add(DocumentSubGroup.WRITTEN_AND_VISUAL_MEDIA);
            add(DocumentSubGroup.COUPONS_AND_PURCHASE_PROOF);
            add(DocumentSubGroup.OTHER_DOCUMENTS);
        }
    };
    private static final boolean FEATURE_INTELLIGENT_SEARCH;
    static final String FLOWER;
    public static final ArrayList<String> INFORMATIVE_DOCUMENTS_SUB_GROUP = new ArrayList<String>() {
        {
            add(InformativeDocumentSubGroup.DOC_PAYMENT_AND_IDENTIFICATION);
            add(InformativeDocumentSubGroup.DOC_WRITTEN_AND_VISUAL_MEDIA);
            add(InformativeDocumentSubGroup.DOC_COUPONS_AND_PURCHASE_PROOF);
            add(InformativeDocumentSubGroup.D0C_OTHER_DOCUMENTS);
        }
    };
    public static final ArrayList<String> INFORMATIVE_DOCUMENTS_SUB_GROUP_V2 = new ArrayList<String>() {
        {
            add(InformativeDocumentSubGroupV2.DOC_PERSONAL_IDS_LEGAL_DOCUMENTS);
            add(InformativeDocumentSubGroupV2.DOC_WRITTEN_AND_VISUAL_MEDIA);
            add(InformativeDocumentSubGroupV2.DOC_QR_CODES_RECEIPTS_AND_BARCODES);
            add(InformativeDocumentSubGroupV2.D0C_OTHER_DOCUMENTS);
        }
    };
    public static final String MAP;
    public static final String NEWSPAPER;
    public static final String RECEIPT;
    public static final String SIGNS;
    static final String SPORT;
    static final String WATCH;
    public static final String WEBSITE;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentSubGroup {
        public static String COUPONS_AND_PURCHASE_PROOF = "coupons_and_purchase_proof";
        public static String OTHER_DOCUMENTS = "other_documents";
        public static String PAYMENT_AND_IDENTIFICATION = "payment_and_identification";
        public static String WRITTEN_AND_VISUAL_MEDIA = "written_and_visual_media";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InformativeDocumentSubGroup {
        public static String D0C_OTHER_DOCUMENTS = "doc_other_documents";
        public static String DOC_COUPONS_AND_PURCHASE_PROOF = "doc_coupons_and_purchase_proof";
        public static String DOC_PAYMENT_AND_IDENTIFICATION = "doc_payment_and_identification";
        public static String DOC_WRITTEN_AND_VISUAL_MEDIA = "doc_written_and_visual_media";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InformativeDocumentSubGroupV2 {
        public static String D0C_OTHER_DOCUMENTS = "doc_other_documents";
        public static String DOC_PERSONAL_IDS_LEGAL_DOCUMENTS = "doc_personal_ids_and_legal_documents";
        public static String DOC_QR_CODES_RECEIPTS_AND_BARCODES = "doc_qr_codes_receipts_and_barcodes";
        public static String DOC_WRITTEN_AND_VISUAL_MEDIA = "doc_written_and_visual_media";
    }

    static {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH);
        FEATURE_INTELLIGENT_SEARCH = isEnabled;
        if (isEnabled) {
            str = "Buildings";
        } else {
            str = "Building";
        }
        BUILDING = str;
        if (isEnabled) {
            str2 = "Sports";
        } else {
            str2 = "Sport";
        }
        SPORT = str2;
        if (isEnabled) {
            str3 = " Watches";
        } else {
            str3 = "Watch";
        }
        WATCH = str3;
        if (isEnabled) {
            str4 = "Flowers";
        } else {
            str4 = "Flower";
        }
        FLOWER = str4;
        if (isEnabled) {
            str5 = "Business_cards";
        } else {
            str5 = "Business card";
        }
        BUSINESS_CARD = str5;
        if (isEnabled) {
            str6 = "Maps";
        } else {
            str6 = "Map";
        }
        MAP = str6;
        if (isEnabled) {
            str7 = "Cartoons";
        } else {
            str7 = "Cartoon";
        }
        CARTOON = str7;
        if (isEnabled) {
            str8 = "Receipts";
        } else {
            str8 = "Receipt";
        }
        RECEIPT = str8;
        if (isEnabled) {
            str9 = "Signs";
        } else {
            str9 = "Signage";
        }
        SIGNS = str9;
        if (isEnabled) {
            str10 = "Websites";
        } else {
            str10 = "Website";
        }
        WEBSITE = str10;
        if (isEnabled) {
            str11 = "Newspapers";
        } else {
            str11 = "Newspaper";
        }
        NEWSPAPER = str11;
    }

    public static ArrayList<String> getDocumentSubGroupList() {
        if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2)) {
            return INFORMATIVE_DOCUMENTS_SUB_GROUP_V2;
        }
        if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP)) {
            return INFORMATIVE_DOCUMENTS_SUB_GROUP;
        }
        return DOCUMENTS_SUB_GROUP_ONEUI_61;
    }

    public static String getOtherDocumentsName() {
        if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP)) {
            return "doc_other_documents";
        }
        return "other_documents";
    }
}
