package com.samsung.android.gallery.database.dal.abstraction;

import android.content.ContentProviderResult;
import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface StoryApi {
    Object[] addContentsToStory(FileItemInterface fileItemInterface, ArrayList<FileItemInterface> arrayList, int i2, Supplier<long[]> supplier, long j2);

    boolean addHideDateRule(long j2, long j3);

    boolean addHideSceneRule(FileItemInterface[] fileItemInterfaceArr, String[] strArr);

    boolean changeStoryCover(int i2, long j2, String str);

    int createStory(ArrayList<FileItemInterface> arrayList, String str, int i2, StoryType storyType, String str2, int i7);

    boolean deleteStory(FileItemInterface[] fileItemInterfaceArr, boolean z);

    boolean deleteStoryFromSaType(int[] iArr);

    long getCmhFileId(long j2);

    Cursor getCollageCursor(int i2);

    int getContentsCount(int i2);

    long getFileId(long j2);

    List<Integer> getHideRuleIds(List<Integer> list);

    List<String> getRecapFilePath(Integer[] numArr);

    Long[] getStoryAlbumContentsIds(int i2);

    long getStoryCoverCmhFileId(int i2);

    int getStoryIdByUgci(String str);

    List<Integer> getStoryIdsFromSaType(int[] iArr);

    long getStoryPeopleHeaderFileId(int i2);

    Map<Long, String[]> getStoryTotalCropInfo(String str);

    int getStoryUpdatedByUser(int i2);

    boolean isEnableFeatureState(String str);

    boolean removeHideRule(int i2);

    boolean removeHideRule(int[] iArr);

    boolean removeItemsFromStory(ArrayList<FileItemInterface> arrayList);

    boolean renameStory(String str, String str2, int i2);

    void resetStoryCover(int i2);

    boolean updateCollageInfo(int i2, int i7, ArrayList<Long> arrayList);

    boolean updateContentOrder(int i2, List<FileItemInterface> list);

    boolean updateFeatureState(String str, int i2);

    boolean updateHideRuleOption(List<Integer> list, int i2);

    void updateMergedCreaturesToHideRule(String str, String str2, String[] strArr, String str3);

    boolean updateStoryBgmInfo(int i2, String str, int i7);

    ContentProviderResult[] updateStoryFavoriteInfo(FileItemInterface[] fileItemInterfaceArr, Map<Integer, Integer> map, boolean z);

    boolean updateStoryFilter(int i2, String str, int i7);

    <T extends FileItemInterface> ArrayList<T> updateStoryPin(T[] tArr, List<Long> list);

    boolean updateStoryThemeAndBgm(int i2, String str, String str2, String str3);

    boolean updateStoryThemeInfo(int i2, String str, String str2, String str3, int i7);

    boolean updateStoryThemeInfo(int i2, HashMap<String, String> hashMap, int i7);

    void updateStoryTime(int i2);

    void updateStoryTotalCropInfo(Map<Long, String[]> map);

    boolean updateStoryType(Integer[] numArr, int i2);

    void updateUnmergedCreatureToHideRule(String str, String[] strArr, String[] strArr2);

    boolean updateUserCuration(int i2, ArrayList<FileItemInterface> arrayList, int i7);

    void updateUserEnter(int i2);
}
