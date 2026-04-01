package com.samsung.android.gallery.module.creature.people;

import E5.a;
import I5.e;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.contact.ContactApi;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.basicdomain.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PersonNameDataLoader extends CreatureNameDataLoader {
    private static HashMap<Long, CreatureNameData> sMyProfileRelationData;
    static HashMap<Integer, String> sProfileRelationMap;
    private final List<Long> mFaceGroupIds;

    public PersonNameDataLoader(List<Long> list) {
        this.mFaceGroupIds = list;
    }

    private CreatureNameData buildAccountProfile(CreatureNameData.Builder builder, Cursor cursor, String str) {
        return builder.setPhotoData(cursor.getBlob(cursor.getColumnIndex("contact_photo_blob"))).setInitialLetter(getInitialLetter(str)).assignAccountProfile().build();
    }

    private CreatureNameData buildContactProfile(CreatureNameData.Builder builder, Cursor cursor, String str) {
        long j2 = cursor.getLong(cursor.getColumnIndex("name_raw_contact_id"));
        return builder.setContactRawId(j2).setPhotoUri(cursor.getString(cursor.getColumnIndex("photo_uri"))).setInitialLetter(getInitialLetter(str)).build();
    }

    public static void clear() {
        sMyProfileRelationData = null;
    }

    private String findRelationFromMyProfile(long j2) {
        CreatureNameData creatureNameData = getRelatedMyProfilePersonNameData().get(Long.valueOf(j2));
        if (creatureNameData == null) {
            return null;
        }
        return creatureNameData.getRelationship();
    }

    private CreatureNameData frequentlyContactedHeader() {
        return new CreatureNameData.Builder("People", CreatureNameData.ContactType.HEADER).build();
    }

    private static CreatureNameData getContactData(long j2) {
        Cursor contactName;
        CreatureNameData creatureNameData = null;
        try {
            contactName = new ContactApi().getContactName(j2);
            if (contactName != null) {
                if (contactName.moveToFirst()) {
                    int columnIndex = contactName.getColumnIndex("display_name");
                    int columnIndex2 = contactName.getColumnIndex("photo_uri");
                    do {
                        String string = contactName.getString(columnIndex);
                        creatureNameData = new CreatureNameData.Builder("People", CreatureNameData.ContactType.CONTACT).setName(string).setContactRawId(j2).setPhotoUri(contactName.getString(columnIndex2)).build();
                    } while (contactName.moveToNext());
                }
            }
            if (contactName != null) {
                contactName.close();
            }
            return creatureNameData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static HashMap<Long, CreatureNameData> getRelatedMyProfilePersonNameData() {
        if (sMyProfileRelationData == null) {
            HashMap<Long, CreatureNameData> hashMap = new HashMap<>();
            Cursor relatedMyProfileContacts = new ContactApi().getRelatedMyProfileContacts();
            if (relatedMyProfileContacts != null) {
                try {
                    if (relatedMyProfileContacts.moveToFirst()) {
                        int columnIndex = relatedMyProfileContacts.getColumnIndex(ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_ID);
                        int columnIndex2 = relatedMyProfileContacts.getColumnIndex("display_name");
                        int columnIndex3 = relatedMyProfileContacts.getColumnIndex("data2");
                        int columnIndex4 = relatedMyProfileContacts.getColumnIndex("photo_uri");
                        do {
                            long j2 = relatedMyProfileContacts.getLong(columnIndex);
                            String string = relatedMyProfileContacts.getString(columnIndex2);
                            int i2 = relatedMyProfileContacts.getInt(columnIndex3);
                            hashMap.put(Long.valueOf(j2), new CreatureNameData.Builder("People", CreatureNameData.ContactType.RELATED_MY_PROFILE_CONTACT).setName(string).setContactRawId(j2).setPhotoUri(relatedMyProfileContacts.getString(columnIndex4)).setRelationship(getRelationShipWithProfileRelationKey(i2)).build());
                        } while (relatedMyProfileContacts.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (relatedMyProfileContacts != null) {
                relatedMyProfileContacts.close();
            }
            sMyProfileRelationData = hashMap;
        }
        return sMyProfileRelationData;
        throw th;
    }

    private static String getRelationShipWithProfileRelationKey(int i2) {
        if (sProfileRelationMap == null) {
            sProfileRelationMap = new ContactApi().getMyProfileRelationsMap();
        }
        String str = sProfileRelationMap.get(Integer.valueOf(i2));
        for (String next : RelationshipKeySet.RELATIONSHIP_MAP.keySet()) {
            if (TextUtils.equals(next, str)) {
                return next;
            }
        }
        return "";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$loadContactDataFromContact$1(Uri uri, LoadFinishedListener loadFinishedListener, ThreadPool.JobContext jobContext) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor contact = new ContactApi().getContact(uri);
        try {
            CreatureNameData.ContactType contactType = CreatureNameData.ContactType.CONTACT;
            ArrayList<CreatureNameData> loadCreatureFromContact = loadCreatureFromContact(contact, contactType);
            Log.d("PersonNameDataLoader", "loadContactDataFromContact {" + contactType + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            loadFinishedListener.onLoadFinished(loadCreatureFromContact);
            if (contact == null) {
                return null;
            }
            contact.close();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static void loadContactDataFromSimilarContact(LoadFinishedListener loadFinishedListener, long[] jArr) {
        loadContactDataFromSimilarContacts(loadFinishedListener, List.of(new Pair(Long.valueOf(jArr[0]), Long.valueOf(jArr[1]))));
    }

    private static void loadContactDataFromSimilarContacts(LoadFinishedListener loadFinishedListener, List<Pair<Long, Long>> list) {
        CreatureNameData contactData;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Pair next : list) {
                long longValue = ((Long) next.first).longValue();
                Long l = (Long) next.second;
                long longValue2 = l.longValue();
                CreatureNameData creatureNameData = getRelatedMyProfilePersonNameData().get(l);
                if (creatureNameData != null) {
                    creatureNameData.setSimilarContactGroupId(longValue);
                    arrayList.add(creatureNameData);
                }
                if (arrayList.isEmpty() && (contactData = getContactData(longValue2)) != null) {
                    contactData.setSimilarContactGroupId(longValue);
                    arrayList.add(contactData);
                }
            }
        }
        Log.d("PersonNameDataLoader", "loadContactDataFromSimilarContacts {" + CreatureNameData.ContactType.RECOMMEND_CONTACT + GlobalPostProcInternalPPInterface.SPLIT_REGEX + arrayList.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        loadFinishedListener.onLoadFinished(arrayList);
    }

    private ArrayList<CreatureNameData> loadCreatureFromContact(Cursor cursor, CreatureNameData.ContactType contactType) {
        ArrayList<CreatureNameData> arrayList = new ArrayList<>();
        if (cursor == null || !cursor.moveToFirst()) {
            return arrayList;
        }
        if (contactType == CreatureNameData.ContactType.FREQUENTLY_CONTACT) {
            arrayList.add(frequentlyContactedHeader());
        }
        int columnIndex = cursor.getColumnIndex("display_name");
        int columnIndex2 = cursor.getColumnIndex("name_raw_contact_id");
        int columnIndex3 = cursor.getColumnIndex("photo_uri");
        do {
            String string = cursor.getString(columnIndex);
            if (string != null) {
                long j2 = cursor.getLong(columnIndex2);
                arrayList.add(new CreatureNameData.Builder("People", contactType).setName(string).setContactRawId(j2).setPhotoUri(cursor.getString(columnIndex3)).setInitialLetter(getInitialLetter(string)).setRelationship(findRelationFromMyProfile(j2)).build());
            }
        } while (cursor.moveToNext());
        return arrayList;
    }

    private ArrayList<CreatureNameData> loadMyProfile(Cursor cursor) {
        boolean z;
        int i2;
        CreatureNameData creatureNameData;
        ArrayList<CreatureNameData> arrayList = new ArrayList<>();
        if (cursor == null || !cursor.moveToFirst()) {
            return arrayList;
        }
        CreatureNameData.Builder relationship = new CreatureNameData.Builder("People", CreatureNameData.ContactType.MY_PROFILE).setRelationship("me");
        int columnIndex = cursor.getColumnIndex("display_name");
        int columnIndex2 = cursor.getColumnIndex("profile_given_name");
        do {
            if (columnIndex >= 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                i2 = columnIndex;
            } else {
                i2 = columnIndex2;
            }
            String string = cursor.getString(i2);
            if (string != null) {
                relationship.setName(string);
                if (z) {
                    creatureNameData = buildContactProfile(relationship, cursor, string);
                } else {
                    creatureNameData = buildAccountProfile(relationship, cursor, string);
                }
                arrayList.add(creatureNameData);
            }
        } while (cursor.moveToNext());
        return arrayList;
    }

    private void loadProfileFromAccount(LoadFinishedListener loadFinishedListener) {
        Cursor myProfile;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            myProfile = new ContactApi().getMyProfile();
            ArrayList<CreatureNameData> loadMyProfile = loadMyProfile(myProfile);
            Log.d("PersonNameDataLoader", "loadProfileFromAccount" + Logger.vt(CreatureNameData.ContactType.MY_PROFILE, Integer.valueOf(loadMyProfile.size()), Long.valueOf(currentTimeMillis)));
            if (!loadMyProfile.isEmpty()) {
                loadFinishedListener.onLoadFinished(loadMyProfile);
            }
            if (myProfile != null) {
                myProfile.close();
                return;
            }
            return;
        } catch (IllegalStateException unused) {
            Log.e("PersonNameDataLoader", "loadProfileFromAccount failed. retry from contact");
            loadProfileFromContact(loadFinishedListener);
            return;
        } catch (Exception e) {
            Log.e((CharSequence) "PersonNameDataLoader", "loadProfileFromAccount failed", (Throwable) e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadProfileFromContact(LoadFinishedListener loadFinishedListener) {
        Cursor myProfile;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            myProfile = new ContactApi().getMyProfile();
            ArrayList<CreatureNameData> loadMyProfile = loadMyProfile(myProfile);
            Log.d("PersonNameDataLoader", "loadProfileFromContact" + Logger.vt(CreatureNameData.ContactType.MY_PROFILE, Integer.valueOf(loadMyProfile.size()), Long.valueOf(currentTimeMillis)));
            if (!loadMyProfile.isEmpty()) {
                loadFinishedListener.onLoadFinished(loadMyProfile);
            }
            if (myProfile != null) {
                myProfile.close();
                return;
            }
            return;
        } catch (Exception e) {
            Log.e((CharSequence) "PersonNameDataLoader", "loadProfileFromContact failed", (Throwable) e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void loadSimilarContactPerson(LoadFinishedListener loadFinishedListener, long[] jArr) {
        ThreadPool.getInstance().submit(new e(5, loadFinishedListener, jArr));
    }

    public void loadContactDataFromContact(Uri uri, LoadFinishedListener loadFinishedListener) {
        ThreadPool.getInstance().submit(new a(this, uri, loadFinishedListener, 5));
    }

    public void loadContactDataFromFreqContact(LoadFinishedListener loadFinishedListener) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor frequentlyContacted = new ContactApi().getFrequentlyContacted();
        try {
            CreatureNameData.ContactType contactType = CreatureNameData.ContactType.FREQUENTLY_CONTACT;
            ArrayList<CreatureNameData> loadCreatureFromContact = loadCreatureFromContact(frequentlyContacted, contactType);
            Log.d("PersonNameDataLoader", "loadContactDataFromFreqContact" + Logger.vt(contactType, Integer.valueOf(loadCreatureFromContact.size()), Long.valueOf(currentTimeMillis)));
            if (!loadCreatureFromContact.isEmpty()) {
                loadFinishedListener.onLoadFinished(loadCreatureFromContact);
            }
            if (frequentlyContacted != null) {
                frequentlyContacted.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void loadContactDataFromMyProfile(LoadFinishedListener loadFinishedListener) {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            loadProfileFromAccount(loadFinishedListener);
        } else {
            loadProfileFromContact(loadFinishedListener);
        }
    }

    public void loadRecommendedContact(LoadFinishedListener loadFinishedListener) {
        List<Long> list = this.mFaceGroupIds;
        if (list != null) {
            loadContactDataFromSimilarContacts(loadFinishedListener, PeopleDataManager.getSimilarContactData(list));
        } else {
            loadFinishedListener.onLoadFinished((ArrayList<CreatureNameData>) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0111 A[LOOP:0: B:12:0x005e->B:29:0x0111, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0129 A[EDGE_INSN: B:41:0x0129->B:36:0x0129 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadTaggedData(com.samsung.android.gallery.module.creature.base.LoadFinishedListener r24) {
        /*
            r23 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            com.samsung.android.gallery.database.dal.mp.helper.PeopleApi r3 = new com.samsung.android.gallery.database.dal.mp.helper.PeopleApi
            r3.<init>()
            android.database.Cursor r3 = r3.getTaggedNameList()
            if (r3 == 0) goto L_0x011d
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x002f }
            if (r4 == 0) goto L_0x011d
            java.lang.String r4 = "__creatureName"
            int r4 = r3.getColumnIndex(r4)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.support.utils.Features r5 = com.samsung.android.gallery.support.utils.Features.SUPPORT_RELATIONSHIP_SEARCH     // Catch:{ all -> 0x002f }
            boolean r5 = com.samsung.android.gallery.support.utils.Features.isEnabled(r5)     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x0033
            java.lang.String r5 = "__creatureRelationship"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ all -> 0x002f }
            goto L_0x0034
        L_0x002f:
            r0 = move-exception
            r1 = r0
            goto L_0x0120
        L_0x0033:
            r5 = -1
        L_0x0034:
            java.lang.String r6 = "__storageType"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x002f }
            java.lang.String r7 = "__cloudTP"
            int r7 = r3.getColumnIndex(r7)     // Catch:{ all -> 0x002f }
            java.lang.String r8 = "__absPath"
            int r8 = r3.getColumnIndex(r8)     // Catch:{ all -> 0x002f }
            java.lang.String r9 = "__creatureFacePosRatio"
            int r9 = r3.getColumnIndex(r9)     // Catch:{ all -> 0x002f }
            java.lang.String r10 = "__orientation"
            int r10 = r3.getColumnIndex(r10)     // Catch:{ all -> 0x002f }
            java.lang.String r11 = "__creatureID"
            int r11 = r3.getColumnIndex(r11)     // Catch:{ all -> 0x002f }
            java.lang.String r12 = "__mediaType"
            int r12 = r3.getColumnIndex(r12)     // Catch:{ all -> 0x002f }
        L_0x005e:
            java.lang.String r13 = r3.getString(r4)     // Catch:{ all -> 0x002f }
            java.lang.String r14 = "null"
            boolean r14 = r14.equals(r13)     // Catch:{ all -> 0x002f }
            if (r14 != 0) goto L_0x0070
            boolean r14 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x002f }
            if (r14 == 0) goto L_0x007e
        L_0x0070:
            r17 = r0
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r1 = r23
            goto L_0x010a
        L_0x007e:
            int r14 = r3.getInt(r6)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.database.dbtype.StorageType r15 = com.samsung.android.gallery.database.dbtype.StorageType.Cloud     // Catch:{ all -> 0x002f }
            int r15 = r15.ordinal()     // Catch:{ all -> 0x002f }
            if (r14 != r15) goto L_0x008f
            java.lang.String r14 = r3.getString(r7)     // Catch:{ all -> 0x002f }
            goto L_0x0093
        L_0x008f:
            java.lang.String r14 = r3.getString(r8)     // Catch:{ all -> 0x002f }
        L_0x0093:
            if (r14 != 0) goto L_0x0096
            goto L_0x0070
        L_0x0096:
            if (r5 >= 0) goto L_0x009a
            r15 = 0
            goto L_0x009e
        L_0x009a:
            java.lang.String r15 = r3.getString(r5)     // Catch:{ all -> 0x002f }
        L_0x009e:
            android.net.Uri r14 = com.samsung.android.gallery.support.utils.FileUtils.getUri(r14)     // Catch:{ all -> 0x002f }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x002f }
            java.lang.String r16 = r3.getString(r9)     // Catch:{ all -> 0x002f }
            r17 = r0
            android.graphics.RectF r0 = com.samsung.android.gallery.support.utils.RectUtils.stringToRectF(r16)     // Catch:{ all -> 0x002f }
            int r1 = r3.getInt(r10)     // Catch:{ all -> 0x002f }
            int r16 = r3.getInt(r12)     // Catch:{ all -> 0x002f }
            r19 = r4
            com.samsung.android.gallery.database.dbtype.MediaType r4 = com.samsung.android.gallery.database.dbtype.MediaType.valueOf((int) r16)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.data.MediaItem r16 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r3)     // Catch:{ all -> 0x002f }
            r20 = r5
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = new com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder     // Catch:{ all -> 0x002f }
            r21 = r6
            java.lang.String r6 = "People"
            r22 = r7
            com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r7 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.TAGGED     // Catch:{ all -> 0x002f }
            r5.<init>(r6, r7)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setName(r13)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setPhotoUri(r14)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r5.setPosRatio(r0)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setRelationship(r15)     // Catch:{ all -> 0x002f }
            long r5 = r3.getLong(r11)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setId(r5)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setOrientation(r1)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setMediaType(r4)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.data.MediaItem r1 = com.samsung.android.gallery.module.data.MediaItemBuilder.cloneCreatureItem(r16)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setMediaItem(r1)     // Catch:{ all -> 0x002f }
            r1 = r23
            java.lang.String r4 = r1.getInitialLetter(r13)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r0 = r0.setInitialLetter(r4)     // Catch:{ all -> 0x002f }
            com.samsung.android.gallery.module.creature.base.CreatureNameData r0 = r0.build()     // Catch:{ all -> 0x002f }
            r2.add(r0)     // Catch:{ all -> 0x002f }
        L_0x010a:
            boolean r0 = r3.moveToNext()     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0111
            goto L_0x0129
        L_0x0111:
            r0 = r17
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            goto L_0x005e
        L_0x011d:
            r17 = r0
            goto L_0x0129
        L_0x0120:
            r3.close()     // Catch:{ all -> 0x0124 }
            goto L_0x0128
        L_0x0124:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0128:
            throw r1
        L_0x0129:
            if (r3 == 0) goto L_0x012e
            r3.close()
        L_0x012e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "loadTaggedData {"
            r0.<init>(r1)
            com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r1 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.TAGGED
            r0.append(r1)
            java.lang.String r1 = ","
            r0.append(r1)
            int r1 = r2.size()
            r0.append(r1)
            java.lang.String r1 = "} +"
            r0.append(r1)
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r17
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "PersonNameDataLoader"
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x0169
            r0 = r24
            r0.onLoadFinished(r2)
        L_0x0169:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.people.PersonNameDataLoader.loadTaggedData(com.samsung.android.gallery.module.creature.base.LoadFinishedListener):void");
    }

    public void loadContactDataFromContact(LoadFinishedListener loadFinishedListener) {
        long currentTimeMillis = System.currentTimeMillis();
        Cursor contactName = new ContactApi().getContactName();
        try {
            CreatureNameData.ContactType contactType = CreatureNameData.ContactType.CONTACT;
            ArrayList<CreatureNameData> loadCreatureFromContact = loadCreatureFromContact(contactName, contactType);
            Log.d("PersonNameDataLoader", "loadContactDataFromContact {" + contactType + GlobalPostProcInternalPPInterface.SPLIT_REGEX + loadCreatureFromContact.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            if (!loadCreatureFromContact.isEmpty()) {
                loadFinishedListener.onLoadFinished(loadCreatureFromContact);
            }
            if (contactName != null) {
                contactName.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
