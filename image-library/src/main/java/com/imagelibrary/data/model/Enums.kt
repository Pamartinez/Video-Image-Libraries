package com.imagelibrary.data.model

// ── Shared enums (delegated to common) ──────────────────────────────────────

/** Alias for the common ViewType so existing code needs no import changes. */
typealias ViewType = com.example.common.data.model.ViewType

/**
 * Alias for the common FolderSortOption.
 * image-library historically called this "SortOption".
 */
typealias SortOption = com.example.common.data.model.FolderSortOption

// ── Image-library-specific enums ─────────────────────────────────────────────

enum class ImageSortOption(val id: Int, val label: String) {
    CUSTOM_ORDER(0,       "Custom order"),
    NAME_A_TO_Z(1,        "Name (A to Z)"),
    NAME_Z_TO_A(2,        "Name (Z to A)"),
    DATE_CREATED_ASC(3,   "Date created (ascending)"),
    DATE_CREATED_DESC(4,  "Date created (descending)"),
    DATE_MODIFIED_ASC(5,  "Date modified (ascending)"),
    DATE_MODIFIED_DESC(6, "Date modified (descending)");

    companion object {
        fun fromId(id: Int): ImageSortOption = entries.firstOrNull { it.id == id } ?: CUSTOM_ORDER
    }
}

enum class SortType(val id: Int) {
    DATE(0),
    TITLE(1),
    DATE_ADDED(2),
    /** MediaStore.Images.Media.DATE_TAKEN — EXIF capture time.
     *  Unlike DATE_MODIFIED, this never changes when a photo is edited,
     *  so custom-order sort stays stable. Matches Samsung Gallery's default. */
    DATE_TAKEN(3);

    companion object {
        fun fromId(id: Int): SortType = entries.firstOrNull { it.id == id } ?: DATE
    }
}

enum class SortOrder(val id: Int) {
    ASCENDING(2),
    DESCENDING(3);

    companion object {
        fun fromId(id: Int): SortOrder = entries.firstOrNull { it.id == id } ?: DESCENDING
    }
}
