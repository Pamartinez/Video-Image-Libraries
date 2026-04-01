package com.samsung.android.gallery.app.ui.list.search.editcreature;

import A4.C0381p;
import A4.Q;
import B8.g;
import B8.j;
import C9.a;
import I3.c;
import T8.C0578a;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.controller.creature.CreatureCoverChoiceCmd;
import com.samsung.android.gallery.app.controller.creature.EditCreatureDialogDelegate;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.creature.PersonUnlinkCmd;
import com.samsung.android.gallery.app.controller.creature.people.CommitPeopleNContactCmd;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipChoiceCmd;
import com.samsung.android.gallery.app.controller.creature.pet.CommitPetCmd;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreatureContactDelegate;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import h.C0199b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import n5.e;
import o6.t;
import q5.d;
import q5.f;
import q5.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditCreatureNamePresenter extends MvpBasePresenter<IEditCreatureNameView> {
    private ICreatureContactDelegate mContactDelegate;
    private Boolean mContactLinked;
    private Bitmap mCurrentBitmap;
    private final EditCreatureDialogDelegate mDialogDelegate;
    private String mExternalIdentityInfo;
    private MediaItem mHeaderItem = MediaItemBuilder.cloneCreatureItem((MediaItem) this.mBlackboard.pop("data:///CreatureHeaderItemForEdit"));
    private Boolean mIsCustomCover;
    private boolean mIsFromExternal;
    private MediaItem mMeTaggedItem;
    private MediaItem mOriginalHeaderItem;
    private CreatureNameData mSelectedCreatureData;
    private String mSelectedName;
    private String mSelectedRelationship;
    private final String mSourceKey = ((String) this.mBlackboard.pop("data:///SearchEditCreatureSource"));
    private String mUpdateRelationShip;

    public EditCreatureNamePresenter(Blackboard blackboard, IEditCreatureNameView iEditCreatureNameView) {
        super(blackboard, iEditCreatureNameView);
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        MediaItem mediaItem = this.mHeaderItem;
        this.mOriginalHeaderItem = mediaItem;
        if (mediaItem != null) {
            this.mSelectedRelationship = CreatureData.of(mediaItem).relationshipType;
        }
        this.mSelectedName = getInitialCreatureName();
        this.mDialogDelegate = new EditCreatureDialogDelegate();
        initContactLink();
    }

    private void adjustHeaderImage(Bitmap bitmap) {
        if (this.mHeaderItem != null) {
            this.mCurrentBitmap = bitmap;
            ((IEditCreatureNameView) this.mView).setHeaderImage(bitmap);
        }
    }

    private boolean checkContactLinked(CreatureNameData creatureNameData) {
        Boolean bool = this.mContactLinked;
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        if (creatureNameData.isSameContactPerson(this.mHeaderItem)) {
            Log.w(this.TAG, "ContactLink checkContactLinked but same person and already linked.");
            finish();
            return true;
        } else if (!((IEditCreatureNameView) this.mView).isCreatureNameChanged(this.mSelectedName) && !((IEditCreatureNameView) this.mView).isRelationshipChanged(this.mUpdateRelationShip)) {
            return false;
        } else {
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "ContactLink checkContactLinked some changes happen. [" + creatureNameData + "]");
            ThreadUtil.postOnUiThread(new h(this, creatureNameData, 0));
            return true;
        }
    }

    private void checkMeTaggedItemMerge() {
        SimpleThreadPool.getInstance().execute(new f(this, 2));
    }

    private CreatureNameData createTaggedData() {
        return new CreatureNameData.Builder(getCategoryType(), CreatureNameData.ContactType.TAGGED).setName(this.mSelectedName).setRelationship(this.mSelectedRelationship).build();
    }

    private void executeMerge(Object[] objArr) {
        MediaItem mediaItem = objArr[0];
        String subCategory = mediaItem.getSubCategory();
        MediaItem[] mediaItemArr = objArr[1];
        ThreadPool.getInstance().submit(new c(this, subCategory, getMergedInfo(mediaItem, mediaItemArr), mediaItemArr, 4), new Q((Object) this, (Object) subCategory, (Object) objArr[2], 23));
    }

    /* access modifiers changed from: private */
    public void finish() {
        finish((Boolean) null);
    }

    private String getCategoryType() {
        if (isPeople()) {
            return "People";
        }
        return "Pet";
    }

    private String[] getMergedInfo(MediaItem mediaItem, MediaItem[] mediaItemArr) {
        return (String[]) Arrays.stream(mediaItemArr).filter(new j(mediaItem, 10)).map(new e(19)).toArray(new C0578a(26));
    }

    private String[] getMultiplePrimaryKey(String[] strArr) {
        String str;
        if (isPeople()) {
            str = "recommended_id";
        } else {
            str = "pet_recommended_id";
        }
        return (String[]) Arrays.stream(strArr).map(new a(str.concat("/"), 7)).toArray(new C0578a(27));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0176, code lost:
        if (r3.equals("motherinlaw_wifesmother") == false) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getRelationshipDetail() {
        /*
            r3 = this;
            java.lang.String r3 = r3.mUpdateRelationShip
            java.lang.String r0 = ","
            java.lang.String[] r3 = r3.split(r0)
            r0 = 0
            r3 = r3[r0]
            boolean r1 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP
            if (r1 == 0) goto L_0x001c
            boolean r1 = com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet.isCustomRelationshipType(r3)
            if (r1 == 0) goto L_0x001c
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_CUSTOM
            java.lang.String r3 = r3.toString()
            return r3
        L_0x001c:
            r3.getClass()
            int r1 = r3.hashCode()
            r2 = -1
            switch(r1) {
                case -1670870656: goto L_0x0170;
                case -1659178170: goto L_0x0164;
                case -1281653412: goto L_0x0158;
                case -1068320061: goto L_0x014c;
                case -986397248: goto L_0x0140;
                case -895757675: goto L_0x0134;
                case -793375479: goto L_0x0128;
                case -753293155: goto L_0x011c;
                case 3480: goto L_0x010f;
                case 114066: goto L_0x0101;
                case 3649297: goto L_0x00f3;
                case 94631196: goto L_0x00e5;
                case 533892983: goto L_0x00d7;
                case 563528414: goto L_0x00c9;
                case 652682418: goto L_0x00bb;
                case 755363488: goto L_0x00ad;
                case 866015769: goto L_0x009f;
                case 1269934139: goto L_0x0092;
                case 1537946635: goto L_0x0085;
                case 1623127125: goto L_0x0078;
                case 1738070492: goto L_0x006b;
                case 1773367862: goto L_0x005e;
                case 1823831816: goto L_0x0051;
                case 1879155093: goto L_0x0044;
                case 1894257778: goto L_0x0037;
                case 2092488444: goto L_0x002a;
                default: goto L_0x0027;
            }
        L_0x0027:
            r0 = r2
            goto L_0x017a
        L_0x002a:
            java.lang.String r0 = "maternal_grandmother"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0033
            goto L_0x0027
        L_0x0033:
            r0 = 25
            goto L_0x017a
        L_0x0037:
            java.lang.String r0 = "fatherinlaw_husbandsfather"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0040
            goto L_0x0027
        L_0x0040:
            r0 = 24
            goto L_0x017a
        L_0x0044:
            java.lang.String r0 = "maternal_grandfather"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x004d
            goto L_0x0027
        L_0x004d:
            r0 = 23
            goto L_0x017a
        L_0x0051:
            java.lang.String r0 = "daughter"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x005a
            goto L_0x0027
        L_0x005a:
            r0 = 22
            goto L_0x017a
        L_0x005e:
            java.lang.String r0 = "older_brother_female"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0067
            goto L_0x0027
        L_0x0067:
            r0 = 21
            goto L_0x017a
        L_0x006b:
            java.lang.String r0 = "older_sister_female"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0074
            goto L_0x0027
        L_0x0074:
            r0 = 20
            goto L_0x017a
        L_0x0078:
            java.lang.String r0 = "daughterinlaw"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0081
            goto L_0x0027
        L_0x0081:
            r0 = 19
            goto L_0x017a
        L_0x0085:
            java.lang.String r0 = "soninlaw"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x008e
            goto L_0x0027
        L_0x008e:
            r0 = 18
            goto L_0x017a
        L_0x0092:
            java.lang.String r0 = "husband"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x009b
            goto L_0x0027
        L_0x009b:
            r0 = 17
            goto L_0x017a
        L_0x009f:
            java.lang.String r0 = "paternal_grandmother"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00a9
            goto L_0x0027
        L_0x00a9:
            r0 = 16
            goto L_0x017a
        L_0x00ad:
            java.lang.String r0 = "fatherinlaw_wifesfather"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00b7
            goto L_0x0027
        L_0x00b7:
            r0 = 15
            goto L_0x017a
        L_0x00bb:
            java.lang.String r0 = "paternal_grandfather"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00c5
            goto L_0x0027
        L_0x00c5:
            r0 = 14
            goto L_0x017a
        L_0x00c9:
            java.lang.String r0 = "younger_sister"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00d3
            goto L_0x0027
        L_0x00d3:
            r0 = 13
            goto L_0x017a
        L_0x00d7:
            java.lang.String r0 = "older_brother_male"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00e1
            goto L_0x0027
        L_0x00e1:
            r0 = 12
            goto L_0x017a
        L_0x00e5:
            java.lang.String r0 = "child"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00ef
            goto L_0x0027
        L_0x00ef:
            r0 = 11
            goto L_0x017a
        L_0x00f3:
            java.lang.String r0 = "wife"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00fd
            goto L_0x0027
        L_0x00fd:
            r0 = 10
            goto L_0x017a
        L_0x0101:
            java.lang.String r0 = "son"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x010b
            goto L_0x0027
        L_0x010b:
            r0 = 9
            goto L_0x017a
        L_0x010f:
            java.lang.String r0 = "me"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0119
            goto L_0x0027
        L_0x0119:
            r0 = 8
            goto L_0x017a
        L_0x011c:
            java.lang.String r0 = "older_sister_male"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0126
            goto L_0x0027
        L_0x0126:
            r0 = 7
            goto L_0x017a
        L_0x0128:
            java.lang.String r0 = "parents"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0132
            goto L_0x0027
        L_0x0132:
            r0 = 6
            goto L_0x017a
        L_0x0134:
            java.lang.String r0 = "spouse"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x013e
            goto L_0x0027
        L_0x013e:
            r0 = 5
            goto L_0x017a
        L_0x0140:
            java.lang.String r0 = "motherinlaw_husbandsmother"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x014a
            goto L_0x0027
        L_0x014a:
            r0 = 4
            goto L_0x017a
        L_0x014c:
            java.lang.String r0 = "mother"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0156
            goto L_0x0027
        L_0x0156:
            r0 = 3
            goto L_0x017a
        L_0x0158:
            java.lang.String r0 = "father"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0162
            goto L_0x0027
        L_0x0162:
            r0 = 2
            goto L_0x017a
        L_0x0164:
            java.lang.String r0 = "younger_brother"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x016e
            goto L_0x0027
        L_0x016e:
            r0 = 1
            goto L_0x017a
        L_0x0170:
            java.lang.String r1 = "motherinlaw_wifesmother"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x017a
            goto L_0x0027
        L_0x017a:
            switch(r0) {
                case 0: goto L_0x01fa;
                case 1: goto L_0x01f3;
                case 2: goto L_0x01ec;
                case 3: goto L_0x01e5;
                case 4: goto L_0x01fa;
                case 5: goto L_0x01de;
                case 6: goto L_0x01d7;
                case 7: goto L_0x01d0;
                case 8: goto L_0x01c9;
                case 9: goto L_0x01c2;
                case 10: goto L_0x01bb;
                case 11: goto L_0x01b4;
                case 12: goto L_0x01f3;
                case 13: goto L_0x01d0;
                case 14: goto L_0x01ad;
                case 15: goto L_0x01a6;
                case 16: goto L_0x019f;
                case 17: goto L_0x0198;
                case 18: goto L_0x0191;
                case 19: goto L_0x018a;
                case 20: goto L_0x01d0;
                case 21: goto L_0x01f3;
                case 22: goto L_0x0183;
                case 23: goto L_0x01ad;
                case 24: goto L_0x01a6;
                case 25: goto L_0x019f;
                default: goto L_0x017d;
            }
        L_0x017d:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>()
            throw r3
        L_0x0183:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_DAUGHTER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x018a:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_DAUGHTERINLAW
            java.lang.String r3 = r3.toString()
            return r3
        L_0x0191:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_SONINLAW
            java.lang.String r3 = r3.toString()
            return r3
        L_0x0198:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_HUSBAND
            java.lang.String r3 = r3.toString()
            return r3
        L_0x019f:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_GRANDMOTHER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01a6:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_FATHERINLAW
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01ad:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_GRANDFATHER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01b4:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_CHILD
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01bb:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_WIFE
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01c2:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_SON
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01c9:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_ME
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01d0:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_SISTER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01d7:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_PARENTS
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01de:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_SPOUSE
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01e5:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_MOTHER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01ec:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_FATHER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01f3:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_BROTHER
            java.lang.String r3 = r3.toString()
            return r3
        L_0x01fa:
            com.samsung.android.gallery.support.analytics.AnalyticsDetail r3 = com.samsung.android.gallery.support.analytics.AnalyticsDetail.RELATIONSHIP_MOTHERINLAW
            java.lang.String r3 = r3.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter.getRelationshipDetail():java.lang.String");
    }

    private void initContactLink() {
        if (supportContactLink()) {
            this.mContactDelegate = new CreatureContactDelegate(getView(), new d(this, 0));
            refreshContactLinkState();
        }
    }

    private void insertUpdatedHistory() {
        getBlackboard().postEvent(EventMessage.obtain(8514));
    }

    private boolean isHeaderChanged() {
        MediaItem mediaItem;
        boolean z;
        if (!PreferenceFeatures.OneUi8x.SEARCH_CREATURE_COVER_CHOICE || (mediaItem = this.mOriginalHeaderItem) == null) {
            return false;
        }
        if (CreatureData.of(mediaItem).faceScore >= 900000000) {
            z = true;
        } else {
            z = false;
        }
        Boolean valueOf = Boolean.valueOf(z);
        if (!this.mOriginalHeaderItem.equals(this.mHeaderItem) || valueOf.equals(this.mIsCustomCover)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkContactLinked$14(CreatureNameData creatureNameData) {
        unlinkContact(2);
        this.mContactLinked = Boolean.FALSE;
        publishData(creatureNameData);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkContactLinked$15(CreatureNameData creatureNameData) {
        this.mDialogDelegate.showUnlinkContactDialog(getContext(), R.string.unlink_contact_edit_detail, new h(this, creatureNameData, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkMeTaggedItemMerge$16() {
        if (this.mHeaderItem != null && "me".equals(this.mSelectedRelationship)) {
            if (this.mMeTaggedItem == null) {
                this.mMeTaggedItem = PeopleDataManager.getMeTaggedItem(MyQueryUtil.getInterface());
            }
            MediaItem mediaItem = this.mMeTaggedItem;
            if (mediaItem != null && !TextUtils.equals(mediaItem.getSubCategory(), this.mHeaderItem.getSubCategory())) {
                new MergeCreatureMultipleCmd().execute(this, this.mMeTaggedItem, null, Boolean.FALSE, Boolean.TRUE);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$6(Object obj, Bundle bundle) {
        refreshContactLinkState();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$3(Object obj, Bundle bundle) {
        updateSelectedRelationship(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$4(Object obj, Bundle bundle) {
        onMergeConfirm(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$5(Object obj, Bundle bundle) {
        updateHeader(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$executeMerge$21(String str, String[] strArr, MediaItem[] mediaItemArr, ThreadPool.JobContext jobContext) {
        if (TextUtils.equals(ArgumentsUtil.getArgValue(getLocationKey(), "sub"), str)) {
            CreatureNameData creatureNameData = this.mSelectedCreatureData;
            if (creatureNameData == null) {
                creatureNameData = createTaggedData();
            }
            PeopleDataManager.addName(str, creatureNameData);
        }
        if (isPeople()) {
            PeopleDataManager.merge(strArr, str, MyQueryUtil.getInterface());
            if (supportContactLink()) {
                new PersonUnlinkCmd().execute(this, mediaItemArr);
            }
        } else {
            PetDataManager.merge(strArr, str, MyQueryUtil.getInterface());
        }
        updateHistory(strArr);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeMerge$22(String str, String str2, Future future) {
        if (!TextUtils.equals(ArgumentsUtil.getArgValue(getLocationKey(), "sub"), str)) {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001, 1, ArgumentsUtil.removeArgs(getLocationKey()).hashCode(), ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(getLocationKey(), "title", str2), "isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE), "sub", str), KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, getCategoryType())));
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        } else {
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        }
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finish$26(Boolean bool) {
        int i2;
        V v = this.mView;
        if (v != null && ((IEditCreatureNameView) v).getActivity() != null) {
            FragmentActivity activity = ((IEditCreatureNameView) this.mView).getActivity();
            if (bool.booleanValue()) {
                i2 = -1;
            } else {
                i2 = 0;
            }
            activity.setResult(i2, (Intent) null);
            ((IEditCreatureNameView) this.mView).getActivity().finish();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMergedInfo$23(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2 != mediaItem) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getMergedInfo$25(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getMultiplePrimaryKey$28(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFaceImage$17(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        adjustHeaderImage(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFaceImage$18(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        adjustHeaderImage(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFaceImage$19(MediaItem mediaItem) {
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new q5.e(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFaceImage$20() {
        MediaItem mediaItem;
        if (IdentityCreatureUtil.isPerson(this.mExternalIdentityInfo)) {
            mediaItem = PeopleDataManager.loadHeaderItem(this.mExternalIdentityInfo);
        } else {
            mediaItem = PetDataManager.loadHeaderItem(this.mExternalIdentityInfo);
        }
        this.mHeaderItem = mediaItem;
        this.mOriginalHeaderItem = mediaItem;
        Optional.ofNullable(mediaItem).ifPresent(new d(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onContactLinkAction$8() {
        unlinkContact(1);
        ThreadUtil.postOnUiThreadDelayed(new f(this, 3), 200);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onContactPicked$1(ArrayList arrayList) {
        updateNameData((CreatureNameData) arrayList.get(0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onContactPicked$2(ArrayList arrayList) {
        if (arrayList.isEmpty() || arrayList.get(0) == null) {
            Log.w(this.TAG, "Failed to load contact data");
        } else {
            ThreadUtil.postOnUiThread(new C0199b(29, this, arrayList));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPositiveClicked$10() {
        publishData(createTaggedData());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPositiveClicked$11() {
        this.mDialogDelegate.showTagAnotherCreatureNameDialog(getContext(), this.mSelectedName, new f(this, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPositiveClicked$12(Future future) {
        String str = this.mSelectedName;
        if (str != null) {
            this.mSelectedName = str.trim();
        }
        CreatureNameData creatureNameData = this.mSelectedCreatureData;
        if (creatureNameData != null) {
            publishDataAsSelectedItem(creatureNameData);
        } else if (((IEditCreatureNameView) this.mView).isCreatureNameChanged(this.mSelectedName)) {
            if (((IEditCreatureNameView) this.mView).isTaggedName(this.mSelectedName)) {
                ThreadUtil.postOnUiThread(new f(this, 4));
            } else {
                publishData(createTaggedData());
            }
        } else if (((IEditCreatureNameView) this.mView).isRelationshipChanged(this.mUpdateRelationShip)) {
            publishData(createTaggedData());
            if (!TextUtils.isEmpty(this.mUpdateRelationShip)) {
                postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_RELATIONSHIP_CHOICE_ITEM, getRelationshipDetail());
            }
        } else {
            finish();
        }
        if (isHeaderChanged()) {
            insertUpdatedHistory();
            getBlackboard().postBroadcastEvent(EventMessage.obtain(103));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onPositiveClicked$9(ThreadPool.JobContext jobContext) {
        if (!(this.mOriginalHeaderItem == null || this.mHeaderItem == null || !isHeaderChanged())) {
            updateOldCover(this.mOriginalHeaderItem);
            if (this.mIsCustomCover.booleanValue()) {
                updateNewCover(this.mOriginalHeaderItem, this.mHeaderItem);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishDataAsSelectedItem$13(boolean z, CreatureNameData creatureNameData) {
        if (z) {
            this.mDialogDelegate.showMergeWithLinkedContactDialog(getContext(), this.mSelectedName, creatureNameData, new d(this, 1));
        } else {
            publishData(creatureNameData);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshContactLinkState$7() {
        loadContactLikedState();
        IEditCreatureNameView iEditCreatureNameView = (IEditCreatureNameView) this.mView;
        Objects.requireNonNull(iEditCreatureNameView);
        ThreadUtil.postOnUiThread(new t(12, iEditCreatureNameView));
    }

    private void loadContactLikedState() {
        this.mContactLinked = null;
        if (getContext() != null && this.mHeaderItem != null && PersonalLinkCore.getInstance().isSupported(getContext())) {
            try {
                this.mContactLinked = Boolean.valueOf(PersonalLinkCore.getInstance().isLinked(getContext(), CreatureData.of(this.mHeaderItem).creatureUuid));
            } catch (Exception unused) {
                Log.e(this.TAG, "loadContactLikedState failed");
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onContactPicked */
    public void lambda$initContactLink$0(Uri uri) {
        MediaItem mediaItem;
        if (uri != null && (mediaItem = this.mHeaderItem) != null && !TextUtils.isEmpty(mediaItem.getSubCategory())) {
            new PersonNameDataLoader((List<Long>) null).loadContactDataFromContact(uri, new q5.e(this, 3));
        }
    }

    private void onMergeConfirm(Object obj) {
        if (obj instanceof Object[]) {
            executeMerge((Object[]) obj);
            return;
        }
        this.mSelectedRelationship = null;
        ((IEditCreatureNameView) this.mView).updateRelationship((String) null);
    }

    /* access modifiers changed from: private */
    public void publishData(CreatureNameData creatureNameData) {
        if (!supportContactLink() || !checkContactLinked(creatureNameData)) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP5_CREATURE && this.mHeaderItem != null && !"me".equals(this.mSelectedRelationship) && "me".equals(CreatureData.of(this.mHeaderItem).relationshipType)) {
                MyQueryUtil.deleteTop5();
            }
            if (CustomRelationshipKeySet.isCustomRelationshipType(this.mSelectedRelationship)) {
                CustomRelationshipKeySet.getInstance().saveData(this.mSelectedRelationship);
            }
            tagName(creatureNameData, this.mCurrentBitmap);
            finish(Boolean.TRUE);
        }
    }

    private void publishDataAsSelectedItem(CreatureNameData creatureNameData) {
        if (!supportContactLink() || creatureNameData.isSameContactPerson(this.mHeaderItem)) {
            publishData(creatureNameData);
        } else {
            ThreadUtil.postOnUiThread(new g((Object) this, PeopleDataManager.existTaggedContact(creatureNameData.getContactRawId()), (Object) creatureNameData, 16));
        }
    }

    private void refreshContactLinkState() {
        SimpleThreadPool.getInstance().execute(new f(this, 0));
    }

    private void tagName(CreatureNameData creatureNameData, Bitmap bitmap) {
        String str;
        if (creatureNameData == null) {
            Log.e(this.TAG, "CreatureNameData has no name data");
        } else if (creatureNameData.isPeople()) {
            if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
                if (!TextUtils.isEmpty(creatureNameData.getCreatureUuid())) {
                    str = creatureNameData.getCreatureUuid();
                } else {
                    MediaItem mediaItem = this.mHeaderItem;
                    if (mediaItem != null) {
                        str = CreatureData.of(mediaItem).creatureUuid;
                    } else {
                        str = null;
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = UUID.randomUUID().toString();
                }
                creatureNameData.setCreatureUuid(str);
            }
            new CommitPeopleNContactCmd().execute(this, this.mSourceKey, creatureNameData, bitmap);
        } else {
            new CommitPetCmd().execute(this, this.mSourceKey, creatureNameData, bitmap);
        }
    }

    private void unlinkContact(long j2) {
        new PersonUnlinkCmd().execute(this, new MediaItem[]{this.mHeaderItem}, Long.valueOf(j2));
    }

    private void updateHeader(Object obj) {
        Object[] objArr = (Object[]) obj;
        MediaItem mediaItem = (MediaItem) objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "unable to update header");
            return;
        }
        this.mHeaderItem = mediaItem;
        Boolean bool = (Boolean) objArr[1];
        bool.booleanValue();
        this.mIsCustomCover = bool;
        loadFaceImage();
        ((IEditCreatureNameView) this.mView).onHeaderChanged();
    }

    private void updateHistory(String[] strArr) {
        SearchHistory.getInstance().deleteHistoryWithPrimaryKey(getMultiplePrimaryKey(strArr));
        insertUpdatedHistory();
    }

    private void updateNewCover(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.isPeople()) {
            PeopleDataManager.updateNewCover(mediaItem.getSubCategory(), mediaItem2.getFileId());
        } else {
            PetDataManager.updateNewCover(mediaItem.getSubCategory(), mediaItem2.getFileId());
        }
    }

    private void updateOldCover(MediaItem mediaItem) {
        if (mediaItem.isPeople()) {
            PeopleDataManager.updateOldCover(mediaItem.getSubCategory());
        } else {
            PetDataManager.updateOldCover(mediaItem.getSubCategory());
        }
    }

    private void updateSelectedRelationship(Object obj) {
        String str;
        String str2 = (String) obj;
        this.mUpdateRelationShip = str2;
        this.mSelectedRelationship = str2;
        if (!CustomRelationshipKeySet.isCustomRelationshipType(str2)) {
            str = RelationshipKeySet.getRelationshipName(getApplicationContext(), this.mSelectedRelationship);
        } else if (!PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE || !CustomRelationshipKeySet.getInstance().existName(this.mSelectedRelationship)) {
            str = CustomRelationshipKeySet.getInstance().getNameInCloneData(this.mSelectedRelationship);
        } else {
            str = CustomRelationshipKeySet.getInstance().getName(this.mSelectedRelationship);
        }
        String currentName = ((IEditCreatureNameView) this.mView).getCurrentName();
        if (TextUtils.isEmpty(currentName)) {
            this.mSelectedName = str;
            ((IEditCreatureNameView) this.mView).updateCreatureName(str);
        } else {
            this.mSelectedName = currentName;
        }
        ((IEditCreatureNameView) this.mView).updateRelationship(str);
        this.mSelectedCreatureData = null;
        checkMeTaggedItemMerge();
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("event/personalDataStateChanged", new q5.g(this, 3)).setWorkingOnUI());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://event/RelationshipSelected", new q5.g(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://user/dialog/MergeCreatureMultiple", new q5.g(this, 1)).setWorkingOnUI());
        if (PreferenceFeatures.OneUi8x.SEARCH_CREATURE_COVER_CHOICE) {
            arrayList.add(new SubscriberInfo("command://event/UpdateCreatureNameDialogHeader", new q5.g(this, 2)).setWorkingOnUI());
        }
    }

    public boolean existTaggedFromMyProfile() {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null || mediaItem.isPet()) {
            return false;
        }
        MediaItem meTaggedItem = PeopleDataManager.getMeTaggedItem(MyQueryUtil.getInterface());
        this.mMeTaggedItem = meTaggedItem;
        if (meTaggedItem != null || PeopleDataManager.existTaggedFromMyProfile()) {
            return true;
        }
        return false;
    }

    public List<Long> getFaceGroupIds() {
        List<Long> list;
        ArrayList arrayList = new ArrayList();
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null) {
            return arrayList;
        }
        ExtrasID extrasID = ExtrasID.CREATURE_FACE_GROUP_IDS;
        Object extra = mediaItem.getExtra(extrasID);
        if (extra != null) {
            return (List) extra;
        }
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "sub", (String) null);
        if (this.mHeaderItem.isPet()) {
            list = PetDataManager.getGroupIds(argValue);
        } else {
            list = PeopleDataManager.getGroupIds(argValue);
        }
        this.mHeaderItem.setExtra(extrasID, list);
        return list;
    }

    public String getInitialCreatureName() {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null) {
            return "";
        }
        return CreatureData.of(mediaItem).creatureName;
    }

    public String getInitialRelationship() {
        if (this.mHeaderItem == null) {
            return "";
        }
        return RelationshipKeySet.getRelationshipName(getApplicationContext(), CreatureData.of(this.mHeaderItem).relationshipType);
    }

    public MediaItem[] getSelectedItems() {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem != null) {
            return new MediaItem[]{mediaItem};
        }
        return super.getSelectedItems();
    }

    public Boolean isContactLinked() {
        return this.mContactLinked;
    }

    public boolean isDialogShowing() {
        return this.mDialogDelegate.isShowing();
    }

    public boolean isPeople() {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null || !mediaItem.isPeople()) {
            return false;
        }
        return true;
    }

    public void loadFaceImage() {
        if (this.mHeaderItem != null) {
            ThumbnailLoader.getInstance().loadThumbnail(this.mHeaderItem, ThumbKind.MEDIUM_KIND, new q5.e(this, 2));
        } else if (!TextUtils.isEmpty(this.mExternalIdentityInfo)) {
            SimpleThreadPool.getInstance().execute(new f(this, 6));
        }
    }

    public void onContactLinkAction() {
        Boolean bool = this.mContactLinked;
        if (bool != null) {
            if (bool.booleanValue()) {
                this.mDialogDelegate.showUnlinkContactDialog(getContext(), R.string.unlink_contact_detail, new f(this, 1));
                return;
            }
            ICreatureContactDelegate iCreatureContactDelegate = this.mContactDelegate;
            if (iCreatureContactDelegate != null) {
                iCreatureContactDelegate.launchContactPicker();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.erase("data:///MergeCreatureTarget");
        }
    }

    public void onFaceLayoutClicked() {
        boolean z;
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem != null) {
            Boolean bool = this.mIsCustomCover;
            if (bool != null) {
                z = bool.booleanValue();
            } else if (CreatureData.of(mediaItem).faceScore >= 900000000) {
                z = true;
            } else {
                z = false;
            }
            new CreatureCoverChoiceCmd().execute(this, this.mHeaderItem, Boolean.valueOf(z));
        }
    }

    public void onNegativeClicked() {
        ((IEditCreatureNameView) this.mView).onCancel((DialogInterface) null);
    }

    public void onPositiveClicked() {
        ThreadPool.getInstance().submit(new C0381p(13, this), new q5.e(this, 0));
    }

    public void onRelationshipClicked() {
        new RelationshipChoiceCmd().execute(this, this.mSelectedRelationship);
    }

    public void publishNegativeResult() {
        Blackboard blackboard = getBlackboard();
        Boolean bool = Boolean.FALSE;
        if (((Boolean) blackboard.pop("data://user/faceCluster/isFromFaceMerge", bool)).booleanValue()) {
            getBlackboard().post("data://user/faceCluster/assignedIdentityInfo", (Object) null);
        }
        finish(bool);
    }

    public void showSoftInput(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context != null && (inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(view, 1);
        }
    }

    public boolean supportContactLink() {
        if (!Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK) || this.mIsFromExternal || !isPeople()) {
            return false;
        }
        return true;
    }

    public void updateNameData(CreatureNameData creatureNameData) {
        ((IEditCreatureNameView) this.mView).updateCreatureName(creatureNameData.getName());
        this.mSelectedCreatureData = creatureNameData;
        this.mSelectedRelationship = creatureNameData.getRelationship();
        String relationshipName = RelationshipKeySet.getRelationshipName(getApplicationContext(), this.mSelectedRelationship);
        ((IEditCreatureNameView) this.mView).updateRelationship(relationshipName);
        Log.v(this.TAG, "ContactLink updateNameData", creatureNameData.getName(), relationshipName);
    }

    public void updateSelectedName(String str) {
        this.mSelectedName = str;
        this.mSelectedCreatureData = null;
    }

    private void finish(Boolean bool) {
        if (bool == null || !this.mIsFromExternal) {
            ((IEditCreatureNameView) this.mView).finish();
        } else {
            ThreadUtil.postOnUiThreadDelayed(new C0199b(28, this, bool), 200);
        }
    }
}
