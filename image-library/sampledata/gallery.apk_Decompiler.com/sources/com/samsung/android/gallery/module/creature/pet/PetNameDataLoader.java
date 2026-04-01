package com.samsung.android.gallery.module.creature.pet;

import com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PetNameDataLoader extends CreatureNameDataLoader {
    public PetNameDataLoader(List<Long> list) {
    }

    public void loadContactDataFromContact(LoadFinishedListener loadFinishedListener) {
        Log.d(this.TAG, "Contact data is not Supported");
    }

    public void loadContactDataFromFreqContact(LoadFinishedListener loadFinishedListener) {
        Log.d(this.TAG, "Frequently contact data is not Supported");
    }

    public void loadContactDataFromMyProfile(LoadFinishedListener loadFinishedListener) {
        Log.d(this.TAG, "My profile data is not Supported");
    }

    public void loadRecommendedContact(LoadFinishedListener loadFinishedListener) {
        Log.d(this.TAG, "Recommended contact data is not Supported");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ef A[LOOP:0: B:6:0x004c->B:22:0x00ef, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0105 A[EDGE_INSN: B:33:0x0105->B:29:0x0105 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadTaggedData(com.samsung.android.gallery.module.creature.base.LoadFinishedListener r23) {
        /*
            r22 = this;
            r0 = r22
            long r1 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.samsung.android.gallery.database.dal.mp.helper.PetApi r4 = new com.samsung.android.gallery.database.dal.mp.helper.PetApi
            r4.<init>()
            android.database.Cursor r4 = r4.getTaggedNameList()
            if (r4 == 0) goto L_0x00f9
            boolean r5 = r4.moveToFirst()     // Catch:{ all -> 0x0079 }
            if (r5 == 0) goto L_0x00f9
            java.lang.String r5 = "__creatureName"
            int r5 = r4.getColumnIndex(r5)     // Catch:{ all -> 0x0079 }
            java.lang.String r6 = "__storageType"
            int r6 = r4.getColumnIndex(r6)     // Catch:{ all -> 0x0079 }
            java.lang.String r7 = "__cloudTP"
            int r7 = r4.getColumnIndex(r7)     // Catch:{ all -> 0x0079 }
            java.lang.String r8 = "__absPath"
            int r8 = r4.getColumnIndex(r8)     // Catch:{ all -> 0x0079 }
            java.lang.String r9 = "__creatureFacePosRatio"
            int r9 = r4.getColumnIndex(r9)     // Catch:{ all -> 0x0079 }
            java.lang.String r10 = "__orientation"
            int r10 = r4.getColumnIndex(r10)     // Catch:{ all -> 0x0079 }
            java.lang.String r11 = "__creatureID"
            int r11 = r4.getColumnIndex(r11)     // Catch:{ all -> 0x0079 }
            java.lang.String r12 = "__mediaType"
            int r12 = r4.getColumnIndex(r12)     // Catch:{ all -> 0x0079 }
        L_0x004c:
            java.lang.String r13 = r4.getString(r5)     // Catch:{ all -> 0x0079 }
            java.lang.String r14 = "null"
            boolean r14 = r14.equals(r13)     // Catch:{ all -> 0x0079 }
            if (r14 != 0) goto L_0x005e
            boolean r14 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x0079 }
            if (r14 == 0) goto L_0x0068
        L_0x005e:
            r16 = r1
            r19 = r5
            r20 = r6
            r21 = r7
            goto L_0x00e8
        L_0x0068:
            int r14 = r4.getInt(r6)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.database.dbtype.StorageType r15 = com.samsung.android.gallery.database.dbtype.StorageType.Cloud     // Catch:{ all -> 0x0079 }
            int r15 = r15.ordinal()     // Catch:{ all -> 0x0079 }
            if (r14 != r15) goto L_0x007d
            java.lang.String r14 = r4.getString(r7)     // Catch:{ all -> 0x0079 }
            goto L_0x0081
        L_0x0079:
            r0 = move-exception
            r1 = r0
            goto L_0x00fc
        L_0x007d:
            java.lang.String r14 = r4.getString(r8)     // Catch:{ all -> 0x0079 }
        L_0x0081:
            if (r14 != 0) goto L_0x0084
            goto L_0x005e
        L_0x0084:
            android.net.Uri r14 = com.samsung.android.gallery.support.utils.FileUtils.getUri(r14)     // Catch:{ all -> 0x0079 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0079 }
            java.lang.String r15 = r4.getString(r9)     // Catch:{ all -> 0x0079 }
            android.graphics.RectF r15 = com.samsung.android.gallery.support.utils.RectUtils.stringToRectF(r15)     // Catch:{ all -> 0x0079 }
            r16 = r1
            int r1 = r4.getInt(r10)     // Catch:{ all -> 0x0079 }
            int r2 = r4.getInt(r12)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.database.dbtype.MediaType r2 = com.samsung.android.gallery.database.dbtype.MediaType.valueOf((int) r2)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.data.MediaItem r18 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r4)     // Catch:{ all -> 0x0079 }
            r19 = r5
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = new com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder     // Catch:{ all -> 0x0079 }
            r20 = r6
            java.lang.String r6 = "Pet"
            r21 = r7
            com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r7 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.TAGGED     // Catch:{ all -> 0x0079 }
            r5.<init>(r6, r7)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setName(r13)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setPhotoUri(r14)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setPosRatio(r15)     // Catch:{ all -> 0x0079 }
            long r6 = r4.getLong(r11)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r5 = r5.setId(r6)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r1 = r5.setOrientation(r1)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r1 = r1.setMediaType(r2)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.data.MediaItem r2 = com.samsung.android.gallery.module.data.MediaItemBuilder.cloneCreatureItem(r18)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r1 = r1.setMediaItem(r2)     // Catch:{ all -> 0x0079 }
            java.lang.String r2 = r0.getInitialLetter(r13)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData$Builder r1 = r1.setInitialLetter(r2)     // Catch:{ all -> 0x0079 }
            com.samsung.android.gallery.module.creature.base.CreatureNameData r1 = r1.build()     // Catch:{ all -> 0x0079 }
            r3.add(r1)     // Catch:{ all -> 0x0079 }
        L_0x00e8:
            boolean r1 = r4.moveToNext()     // Catch:{ all -> 0x0079 }
            if (r1 != 0) goto L_0x00ef
            goto L_0x0105
        L_0x00ef:
            r1 = r16
            r5 = r19
            r6 = r20
            r7 = r21
            goto L_0x004c
        L_0x00f9:
            r16 = r1
            goto L_0x0105
        L_0x00fc:
            r4.close()     // Catch:{ all -> 0x0100 }
            goto L_0x0104
        L_0x0100:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0104:
            throw r1
        L_0x0105:
            if (r4 == 0) goto L_0x010a
            r4.close()
        L_0x010a:
            java.lang.String r0 = r0.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "loadTaggedData {"
            r1.<init>(r2)
            com.samsung.android.gallery.module.creature.base.CreatureNameData$ContactType r2 = com.samsung.android.gallery.module.creature.base.CreatureNameData.ContactType.TAGGED
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            int r2 = r3.size()
            r1.append(r2)
            java.lang.String r2 = "} +"
            r1.append(r2)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r16
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)
            r0 = r23
            r0.onLoadFinished(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.creature.pet.PetNameDataLoader.loadTaggedData(com.samsung.android.gallery.module.creature.base.LoadFinishedListener):void");
    }
}
