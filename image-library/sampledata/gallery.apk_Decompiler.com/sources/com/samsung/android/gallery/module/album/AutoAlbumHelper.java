package com.samsung.android.gallery.module.album;

import N2.j;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoAlbumHelper {
    private static final AutoAlbumHelper sInstance = new AutoAlbumHelper();

    private AutoAlbumHelper() {
    }

    public static AutoAlbumHelper getInstance() {
        return sInstance;
    }

    public void addAutoAlbumContents(String str, long j2) {
        try {
            DbCompat.autoAlbumApi().addAutoAlbumContents(str, j2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("addAutoAlbumContents failed. e="), "AutoAlbumHelper");
        }
    }

    public void addFamilyAlbumContents(String str, long j2) {
        try {
            DbCompat.autoAlbumApi().addFamilyAlbumContents(str, j2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("addFamilyAlbumContents failed. e="), "AutoAlbumHelper");
        }
    }

    public void changeAutoUpdatingProperty(boolean z, long j2) {
        try {
            DbCompat.autoAlbumApi().changeAutoUpdatingProperty(z ^ true ? 1 : 0, j2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("changeAutoUpdatingProperty failed. e="), "AutoAlbumHelper");
        }
    }

    public int createAutoAlbum(String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Uri createAutoAlbum = DbCompat.autoAlbumApi().createAutoAlbum(str, str2);
            Log.d("AutoAlbumHelper", "createAutoAlbum" + Logger.vt(Logger.getEncodedString((Object) createAutoAlbum), Long.valueOf(currentTimeMillis)));
            if (createAutoAlbum == null || TextUtils.isEmpty(createAutoAlbum.getLastPathSegment())) {
                return -1;
            }
            return Integer.parseInt(createAutoAlbum.getLastPathSegment());
        } catch (Exception e) {
            j.s(e, new StringBuilder("createAutoAlbum failed. e="), "AutoAlbumHelper");
            return -1;
        }
    }

    public int createFamilyAutoAlbum(String str, String str2) {
        try {
            Uri createFamilyAutoAlbum = DbCompat.autoAlbumApi().createFamilyAutoAlbum(str, str2);
            if (createFamilyAutoAlbum != null) {
                return Integer.parseInt(createFamilyAutoAlbum.getLastPathSegment());
            }
            return -1;
        } catch (Exception e) {
            j.s(e, new StringBuilder("createFamilyAutoAlbum failed. e="), "AutoAlbumHelper");
            return -1;
        }
    }

    public int deleteAutoAlbums(List<Long> list) {
        try {
            return DbCompat.autoAlbumApi().deleteAutoAlbum(TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list));
        } catch (Exception e) {
            j.s(e, new StringBuilder("deleteAutoAlbum failed. e="), "AutoAlbumHelper");
            return 0;
        }
    }

    public void deleteFamilyAutoAlbum() {
        DbCompat.autoAlbumApi().deleteFamilyAutoAlbum();
    }

    public Pair<Integer, Integer> getAutoAlbumCount(int i2) {
        try {
            int[] albumCount = DbCompat.autoAlbumApi().getAlbumCount(i2);
            return new Pair<>(Integer.valueOf(albumCount[0]), Integer.valueOf(albumCount[1]));
        } catch (Exception e) {
            j.s(e, new StringBuilder("getAutoAlbumCount failed. e="), "AutoAlbumHelper");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002c A[SYNTHETIC, Splitter:B:11:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> getAutoAlbumIds(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            com.samsung.android.gallery.database.dal.abstraction.AutoAlbumApi r2 = com.samsung.android.gallery.database.dal.DbCompat.autoAlbumApi()     // Catch:{ Exception -> 0x003b }
            android.database.Cursor r2 = r2.getAutoAlbumIds(r3, r4)     // Catch:{ Exception -> 0x003b }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0028 }
            r3.<init>()     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x002a
            boolean r4 = r2.moveToFirst()     // Catch:{ all -> 0x0028 }
            if (r4 == 0) goto L_0x002a
        L_0x0015:
            r4 = 0
            long r0 = r2.getLong(r4)     // Catch:{ all -> 0x0028 }
            java.lang.Long r4 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0028 }
            r3.add(r4)     // Catch:{ all -> 0x0028 }
            boolean r4 = r2.moveToNext()     // Catch:{ all -> 0x0028 }
            if (r4 != 0) goto L_0x0015
            goto L_0x002a
        L_0x0028:
            r3 = move-exception
            goto L_0x0030
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ Exception -> 0x003b }
        L_0x002f:
            return r3
        L_0x0030:
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ Exception -> 0x003b }
        L_0x003a:
            throw r3     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "getAutoAlbumIds failed. e="
            r3.<init>(r4)
            java.lang.String r4 = "AutoAlbumHelper"
            N2.j.s(r2, r3, r4)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AutoAlbumHelper.getAutoAlbumIds(java.lang.String, boolean):java.util.List");
    }

    public int getFamilyAutoAlbumId(String str) {
        Cursor familyAutoAlbumIdCursor;
        try {
            familyAutoAlbumIdCursor = DbCompat.autoAlbumApi().getFamilyAutoAlbumIdCursor(str);
            if (familyAutoAlbumIdCursor != null) {
                if (familyAutoAlbumIdCursor.moveToFirst()) {
                    int i2 = familyAutoAlbumIdCursor.getInt(0);
                    familyAutoAlbumIdCursor.close();
                    return i2;
                }
            }
            if (familyAutoAlbumIdCursor == null) {
                return -1;
            }
            familyAutoAlbumIdCursor.close();
            return -1;
        } catch (Exception e) {
            j.s(e, new StringBuilder("getFamilyAutoAlbumId failed. e="), "AutoAlbumHelper");
            return -1;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Pair<ArrayList<Long>, ArrayList<Long>> getSubscribeCreatureList(long j2) {
        Cursor creatureRuleCursor;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            creatureRuleCursor = DbCompat.autoAlbumApi().getCreatureRuleCursor(j2);
            if (creatureRuleCursor != null) {
                if (creatureRuleCursor.moveToFirst()) {
                    do {
                        if (creatureRuleCursor.getInt(0) == 1) {
                            arrayList.add(Long.valueOf(creatureRuleCursor.getLong(1)));
                        } else if (creatureRuleCursor.getInt(0) == 2) {
                            arrayList2.add(Long.valueOf(creatureRuleCursor.getLong(1)));
                        }
                    } while (creatureRuleCursor.moveToNext());
                }
            }
            if (creatureRuleCursor != null) {
                creatureRuleCursor.close();
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("getSubscribeCreatureList failed. e="), "AutoAlbumHelper");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return new Pair<>(arrayList, arrayList2);
        throw th;
    }

    public ArrayList<Long> getSubscribePeopleList(long j2) {
        Cursor creatureRuleCursor;
        ArrayList<Long> arrayList = new ArrayList<>();
        try {
            creatureRuleCursor = DbCompat.autoAlbumApi().getCreatureRuleCursor(j2);
            if (creatureRuleCursor != null) {
                if (creatureRuleCursor.moveToFirst()) {
                    do {
                        if (creatureRuleCursor.getInt(0) == 1) {
                            arrayList.add(Long.valueOf(creatureRuleCursor.getLong(1)));
                        }
                    } while (creatureRuleCursor.moveToNext());
                }
            }
            if (creatureRuleCursor != null) {
                creatureRuleCursor.close();
            }
            return arrayList;
        } catch (Exception e) {
            j.s(e, new StringBuilder("getSubscribePeopleList failed. e="), "AutoAlbumHelper");
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int getSuggestedContentsRule(long j2) {
        Cursor suggestedContentsRuleCursor;
        try {
            suggestedContentsRuleCursor = DbCompat.autoAlbumApi().getSuggestedContentsRuleCursor(j2);
            if (suggestedContentsRuleCursor != null) {
                if (suggestedContentsRuleCursor.moveToFirst()) {
                    int i2 = suggestedContentsRuleCursor.getInt(0);
                    suggestedContentsRuleCursor.close();
                    return i2;
                }
            }
            if (suggestedContentsRuleCursor != null) {
                suggestedContentsRuleCursor.close();
            }
        } catch (Exception e) {
            j.s(e, new StringBuilder("getSuggestedContentsRule failed. e="), "AutoAlbumHelper");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return 0;
        throw th;
    }

    public boolean isAutoUpdatingEnabled(int i2) {
        Cursor autoUpdateStateCursor;
        boolean z = false;
        try {
            autoUpdateStateCursor = DbCompat.autoAlbumApi().getAutoUpdateStateCursor(i2);
            if (autoUpdateStateCursor != null) {
                if (autoUpdateStateCursor.moveToFirst() && autoUpdateStateCursor.getInt(0) == 0) {
                    z = true;
                }
            }
            if (autoUpdateStateCursor != null) {
                autoUpdateStateCursor.close();
            }
            return z;
        } catch (Exception e) {
            j.s(e, new StringBuilder("isAutoUpdatingEnabled failed. e="), "AutoAlbumHelper");
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean isValidAutoAlbum(int i2) {
        if (i2 != -1) {
            return true;
        }
        return false;
    }

    public void removeAutoAlbumContent(long j2, long j3) {
        try {
            DbCompat.autoAlbumApi().removeAutoAlbumContent(j2, j3);
        } catch (Exception e) {
            j.s(e, new StringBuilder("removeAutoAlbumContents failed. e="), "AutoAlbumHelper");
        }
    }

    public void removeAutoAlbumContents(ArrayList<Long> arrayList, long j2) {
        try {
            DbCompat.autoAlbumApi().removeAutoAlbumContents(arrayList, j2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("removeAutoAlbumContents failed. e="), "AutoAlbumHelper");
        }
    }

    public void renameAutoAlbum(String str, long j2) {
        try {
            DbCompat.autoAlbumApi().renameAutoAlbum(str, j2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("renameAutoAlbum failed. e="), "AutoAlbumHelper");
        }
    }

    public void subscribePeople(List<Pair<Long, String>> list, long j2) {
        try {
            DbCompat.autoAlbumApi().addAutoAlbumRules(list, j2, 1);
        } catch (Exception e) {
            j.s(e, new StringBuilder("subscribePeople failed. e="), "AutoAlbumHelper");
        }
    }

    public void subscribePet(List<Pair<Long, String>> list, long j2) {
        try {
            DbCompat.autoAlbumApi().addAutoAlbumRules(list, j2, 2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("subscribedPet failed. e="), "AutoAlbumHelper");
        }
    }

    public void unsubscribePeople(List<Long> list, long j2) {
        try {
            DbCompat.autoAlbumApi().removeAutoAlbumRules(list, j2, 1);
        } catch (Exception e) {
            j.s(e, new StringBuilder("unsubscribePeople failed. e="), "AutoAlbumHelper");
        }
    }

    public void unsubscribePet(List<Long> list, long j2) {
        try {
            DbCompat.autoAlbumApi().removeAutoAlbumRules(list, j2, 2);
        } catch (Exception e) {
            j.s(e, new StringBuilder("unsubscribePet failed. e="), "AutoAlbumHelper");
        }
    }

    public void updateMergedRecommendedId(long j2, String[] strArr, CreatureData creatureData, boolean z) {
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    long j3 = j2;
                    Log.d("AutoAlbumHelper", "updateMergedRecommendedId", Integer.valueOf(DbCompat.autoAlbumApi().updateRecommendedIds(j3, IdentityCreatureUtil.getUnifiedIdentityIds(Arrays.asList(strArr), z), z, creatureData.faceGroupUuid)), Integer.valueOf(DbCompat.autoAlbumApi().removeDuplicatedRecord(j3)), Long.valueOf(j3), "");
                }
            } catch (Exception e) {
                j.s(e, new StringBuilder("updateMergedRecommendedId failed. e="), "AutoAlbumHelper");
            }
        }
    }

    public void updateRecommendedId(long j2, String str) {
        try {
            long j3 = j2;
            DbCompat.autoAlbumApi().updateRecommendedIds(j3, String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(str)), IdentityCreatureUtil.isPerson(str), (String) null);
            Log.d("AutoAlbumHelper", "updateRecommendedId", Long.valueOf(j3), "");
        } catch (Exception e) {
            j.s(e, new StringBuilder("updateRecommendedId failed. e="), "AutoAlbumHelper");
        }
    }

    public void updateSuggestedContentsRule(int i2, int i7) {
        try {
            DbCompat.autoAlbumApi().updateSuggestedContentsRule(i2, (long) i7);
        } catch (Exception e) {
            j.s(e, new StringBuilder("updateSuggestedContentsRule failed. e="), "AutoAlbumHelper");
        }
    }

    public void updateUnmergedRecommendedId(String str, long[] jArr, String[] strArr) {
        try {
            Log.d("AutoAlbumHelper", "updateUnmergedRecommendedId", Integer.valueOf(DbCompat.autoAlbumApi().insertSplitIds(IdentityCreatureUtil.getUnifiedIdentityId(str), jArr, strArr, IdentityCreatureUtil.isPerson(str))), Integer.valueOf(DbCompat.autoAlbumApi().removeUnmergedId(IdentityCreatureUtil.getUnifiedIdentityId(str), IdentityCreatureUtil.isPerson(str))), "");
        } catch (Exception e) {
            j.s(e, new StringBuilder("updateUnmergedRecommendedId failed. e="), "AutoAlbumHelper");
        }
    }

    public void addAutoAlbumContents(String str, List<Long> list) {
        try {
            DbCompat.autoAlbumApi().addAutoAlbumContents(str, list);
        } catch (Exception e) {
            j.s(e, new StringBuilder("addAutoAlbumContents failed. e="), "AutoAlbumHelper");
        }
    }
}
