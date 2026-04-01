package com.samsung.android.gallery.module.publisher.retrieval;

import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.SearchFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface StorageRetrieval {
    Cursor[] getDocumentFileIds(String str, SearchFilter.Builder builder, int i2);

    Cursor[] getDocumentFiles(String str, SearchFilter.Builder builder);

    Cursor[] getExpressionFileIds(String str, SearchFilter.Builder builder, int i2);

    Cursor[] getExpressionFiles(String str, SearchFilter.Builder builder);

    Cursor[] getGeneratedFileIds(String str, int i2);

    Cursor[] getGeneratedFiles(String str);

    Cursor[] getLocationFileIds(String str, SearchFilter.Builder builder, String str2, int i2);

    Cursor[] getLocationFiles(String str, SearchFilter.Builder builder, String str2);

    Cursor[] getMyTagsFileIds(String str, SearchFilter.Builder builder, int i2);

    Cursor[] getMyTagsFiles(String str, SearchFilter.Builder builder);

    Cursor[] getPeopleFileIds(String str, SearchFilter.Builder builder, String str2, int i2);

    Cursor[] getPeopleFileIdsMultiple(String str, SearchFilter.Builder builder, int i2);

    Cursor[] getPeopleFiles(String str, SearchFilter.Builder builder, String str2);

    Cursor[] getPeopleFilesMultiple(String str, SearchFilter.Builder builder);

    Cursor[] getPetFileIds(String str, SearchFilter.Builder builder, String str2, int i2);

    Cursor[] getPetFiles(String str, SearchFilter.Builder builder, String str2);

    Cursor[] getRecentlyEdited(String str);

    Cursor[] getRecentlyEditedFileIds(String str, int i2);

    Cursor[] getSceneFileIds(String str, SearchFilter.Builder builder, String str2, int i2);

    Cursor[] getSceneFiles(String str, SearchFilter.Builder builder, String str2);

    Cursor[] getShotModeFileIds(String str, SearchFilter.Builder builder, int i2);

    Cursor[] getShotModeFiles(String str, SearchFilter.Builder builder);

    void setSupportedTimeline(boolean z) {
    }
}
