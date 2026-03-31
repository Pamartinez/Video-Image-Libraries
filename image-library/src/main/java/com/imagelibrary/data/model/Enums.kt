package com.imagelibrary.data.model

enum class ViewType(val id: Int) {
    GRID_SMALL(1),
    GRID_LARGE(2);

    companion object {
        fun fromId(id: Int): ViewType = entries.firstOrNull { it.id == id } ?: GRID_LARGE
    }
}

enum class SortOption(val id: Int, val label: String) {
    CUSTOM_ORDER(0,   "Custom order"),
    NAME_A_TO_Z(1,    "Name (A to Z)"),
    NAME_Z_TO_A(2,    "Name (Z to A)"),
    ITEMS_MOST_FIRST(3,    "Items (most to fewest)"),
    ITEMS_FEWEST_FIRST(4,  "Items (fewest to most)");

    companion object {
        fun fromId(id: Int): SortOption = entries.firstOrNull { it.id == id } ?: CUSTOM_ORDER
    }
}

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
    DATE_ADDED(2);

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
