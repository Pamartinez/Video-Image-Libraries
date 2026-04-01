package com.samsung.android.gallery.module.publisher;

import A.a;
import D3.j;
import H.b;
import M9.n;
import android.database.Cursor;
import android.database.MergeCursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.SortableMergeCursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesDataPublisherV2 extends StoriesDataPublisher {
    public boolean SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);

    public StoriesDataPublisherV2(Blackboard blackboard) {
        super(blackboard);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishHideSceneSelectionData$3(Cursor[] cursorArr) {
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_SCENE_SELECTION).setTag("type", IdentityCreatureUtil.Category.PEOPLE));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishHideSceneSelectionData$4(Cursor[] cursorArr) {
        cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_SCENE_SELECTION).setTag("type", IdentityCreatureUtil.Category.PET));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishHideSceneSelectionData$5(Cursor[] cursorArr, long j2) {
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishHideSceneSelectionData : " + getCursorListInfo(cursorArr, j2));
        publishCursorArray("location://stories/hideSceneSelection", new Cursor[]{new MergeCursor(cursorArr)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryHideRuleData$0(Cursor[] cursorArr) {
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_SCENE).setTag("type", IdentityCreatureUtil.Category.PEOPLE));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishStoryHideRuleData$1(Cursor[] cursorArr) {
        cursorArr[1] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_SCENE).setTag("type", IdentityCreatureUtil.Category.PET));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishStoryHideRuleData$2(Cursor[] cursorArr, Cursor[] cursorArr2, long j2) {
        cursorArr[1] = new SortableMergeCursor(cursorArr2);
        a.y(new StringBuilder("publishStoryHideRuleData : "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://stories/hideRule", cursorArr);
    }

    public void publishHideSceneSelectionData(Object obj, Bundle bundle) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.SUPPORT_PET_CLUSTER) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        Cursor[] cursorArr = new Cursor[i2];
        LatchBuilder latchBuilder = new LatchBuilder("publishHideSceneSelectionData");
        latchBuilder.addWorker(new n(cursorArr, 6));
        if (this.SUPPORT_PET_CLUSTER) {
            latchBuilder.addWorker(new n(cursorArr, 7));
        }
        latchBuilder.setPostExecutor((Runnable) new b(this, cursorArr, currentTimeMillis, 2)).start();
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [android.database.Cursor[], java.io.Serializable] */
    public void publishStoryHideRuleData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 2;
        Cursor[] cursorArr = {null, null};
        cursorArr[0] = DbCompat.query(new QueryParams(DbKey.STORY_HIDE_RULE_DATE));
        if (!this.SUPPORT_PET_CLUSTER) {
            i2 = 1;
        }
        ? r32 = new Cursor[i2];
        LatchBuilder latchBuilder = new LatchBuilder("publishStoryHideRuleData");
        latchBuilder.addWorker(new n(r32, 8));
        if (this.SUPPORT_PET_CLUSTER) {
            latchBuilder.addWorker(new n(r32, 9));
        }
        latchBuilder.setPostExecutor((Runnable) new j((Object) this, (Object) cursorArr, (Serializable) r32, currentTimeMillis, 4)).start();
    }
}
