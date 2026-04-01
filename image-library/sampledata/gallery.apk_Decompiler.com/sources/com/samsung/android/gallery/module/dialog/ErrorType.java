package com.samsung.android.gallery.module.dialog;

import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ErrorType {
    ALBUM_NAME_ALREADY_IN_USE(R$string.album_name_already_in_use),
    SHORTCUT_NAME_ALREADY_IN_USE(R$string.shortcut_name_already_in_use),
    FILE_ALREADY_EXIST(R$string.error_file_already_exists),
    UNABLE_TO_CREATE_ALBUM(R$string.unable_to_create_album),
    EXCEPTION_HAPPENED(r1),
    CANNOT_START_WITH_A_PERIOD_FOR_ALBUM_NAME(R$string.cannot_start_with_a_period_for_album_name),
    DUPLICATE_GROUP_NAME(R$string.duplicate_group_name),
    INVALID_CHARACTER(R$string.invalid_character),
    UNABLE_TO_ADD_ITEM(R$string.unable_to_add_item),
    NAME_ALREADY_EXIST(R$string.already_exists),
    DEFAULT_NAME(R$string.people_tag_no_name),
    NONE(r1);
    
    private final int mTitleResId;

    private ErrorType(int i2) {
        this.mTitleResId = i2;
    }

    public int getStringId() {
        return this.mTitleResId;
    }

    public boolean isCreateAlbumErrorCase() {
        if (this == ALBUM_NAME_ALREADY_IN_USE || this == UNABLE_TO_CREATE_ALBUM || this == FILE_ALREADY_EXIST) {
            return true;
        }
        return false;
    }

    public boolean isNone() {
        if (this == NONE) {
            return true;
        }
        return false;
    }
}
