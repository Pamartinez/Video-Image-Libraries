package com.videolibrary.data.model

enum class ViewType(val id: Int) {
    GRID_SMALL(1),
    GRID_LARGE(2),
    LIST(3);

    companion object {
        fun fromId(id: Int): ViewType = entries.firstOrNull { it.id == id } ?: LIST
    }
}

/** Sort options shown on the **Folders** tab. */
enum class FolderSortOption(val id: Int, val label: String) {
    CUSTOM_ORDER(0, "Custom order"),
    NAME_A_TO_Z(1, "Name (A to Z)"),
    NAME_Z_TO_A(2, "Name (Z to A)"),
    ITEMS_MOST_FIRST(3, "Number of items (most to fewest)"),
    ITEMS_FEWEST_FIRST(4, "Number of items (fewest to most)");

    companion object {
        fun fromId(id: Int): FolderSortOption = entries.firstOrNull { it.id == id } ?: CUSTOM_ORDER
    }
}

/** Sort options shown on the **Videos** tab and inside an album. */
enum class VideoSortOption(val id: Int, val label: String) {
    CUSTOM_ORDER(0, "Custom order"),
    NAME_A_TO_Z(1, "Name (A to Z)"),
    NAME_Z_TO_A(2, "Name (Z to A)"),
    DURATION_ASC(7, "Duration (ascending)"),
    DURATION_DESC(8, "Duration (descending)"),
    DATE_CREATED_ASC(3, "Date created (ascending)"),
    DATE_CREATED_DESC(4, "Date created (descending)"),
    DATE_MODIFIED_ASC(5, "Date modified (ascending)"),
    DATE_MODIFIED_DESC(6, "Date modified (descending)");

    companion object {
        fun fromId(id: Int): VideoSortOption = entries.firstOrNull { it.id == id } ?: CUSTOM_ORDER
    }
}


