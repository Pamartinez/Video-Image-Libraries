package P2;

import U2.g;
import com.adobe.internal.xmp.options.SerializeOptions;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum q implements g {
    TYPE_HEADER_ITEM(0, "header_item"),
    TYPE_STRING_ID_ITEM(1, "string_id_item"),
    TYPE_TYPE_ID_ITEM(2, "type_id_item"),
    TYPE_PROTO_ID_ITEM(3, "proto_id_item"),
    TYPE_FIELD_ID_ITEM(4, "field_id_item"),
    TYPE_METHOD_ID_ITEM(5, "method_id_item"),
    TYPE_CLASS_DEF_ITEM(6, "class_def_item"),
    TYPE_MAP_LIST(4096, "map_list"),
    TYPE_TYPE_LIST(4097, "type_list"),
    TYPE_ANNOTATION_SET_REF_LIST(4098, "annotation_set_ref_list"),
    TYPE_ANNOTATION_SET_ITEM(4099, "annotation_set_item"),
    TYPE_CLASS_DATA_ITEM(SerializeOptions.SORT, "class_data_item"),
    TYPE_CODE_ITEM(8193, "code_item"),
    TYPE_STRING_DATA_ITEM(8194, "string_data_item"),
    TYPE_DEBUG_INFO_ITEM(8195, "debug_info_item"),
    TYPE_ANNOTATION_ITEM(8196, "annotation_item"),
    TYPE_ENCODED_ARRAY_ITEM(8197, "encoded_array_item"),
    TYPE_ANNOTATIONS_DIRECTORY_ITEM(8198, "annotations_directory_item"),
    TYPE_MAP_ITEM(-1, "map_item"),
    TYPE_TYPE_ITEM(-1, "type_item"),
    TYPE_EXCEPTION_HANDLER_ITEM(-1, "exception_handler_item"),
    TYPE_ANNOTATION_SET_REF_ITEM(-1, "annotation_set_ref_item");
    
    private final String humanName;
    private final int mapValue;
    private final String typeName;

    /* access modifiers changed from: public */
    q(int i2, String str) {
        this.mapValue = i2;
        this.typeName = str;
        this.humanName = (str.endsWith("_item") ? C0280e.d(5, 0, str) : str).replace('_', ' ');
    }

    public final String a() {
        return this.humanName;
    }

    public final int c() {
        return this.mapValue;
    }

    public final String d() {
        return this.typeName;
    }
}
