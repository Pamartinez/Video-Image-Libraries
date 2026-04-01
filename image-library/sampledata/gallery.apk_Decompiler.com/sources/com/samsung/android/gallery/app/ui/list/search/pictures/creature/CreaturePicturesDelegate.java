package com.samsung.android.gallery.app.ui.list.search.pictures.creature;

import A4.C0381p;
import B8.e;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import o6.p;
import v7.w;
import w4.C0533c;
import x7.l;
import y5.a;
import y5.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePicturesDelegate {
    private ICreatureContactDelegate mContactDelegate;
    private Integer mCreatureCount = null;
    private final boolean mEnabledClusterResult;
    private MediaItem mHeaderItem;
    private boolean mIsFilterChangeOnCluster;
    private Runnable mPendingUpdateRunnable;
    private final ISearchPicturesView mView;

    public CreaturePicturesDelegate(ISearchPicturesView iSearchPicturesView) {
        boolean z;
        this.mView = iSearchPicturesView;
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
            this.mContactDelegate = new CreatureContactDelegate(iSearchPicturesView, new a(0, this));
        }
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster) || !LocationKey.isSearchClusterCategory(iSearchPicturesView.getLocationKey())) {
            z = false;
        } else {
            z = true;
        }
        this.mEnabledClusterResult = z;
    }

    private void addCreatureMainFilterOnHeaderItemLoaded(MediaItem mediaItem) {
        MediaItem cloneCreatureItem;
        String locationKey = this.mView.getLocationKey();
        if (UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(locationKey, "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE)) && Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY) && PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD && ArgumentsUtil.getArgValue(locationKey, "prev_sub_category", (String) null) == null && (cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(mediaItem)) != null) {
            ThumbnailLoader.getInstance().loadThumbnail(cloneCreatureItem, ThumbKind.SMALL_NC_KIND, new e(cloneCreatureItem, 1), new l6.a(12, this, cloneCreatureItem));
        }
    }

    private void bindCreatureHeader(MediaItem mediaItem) {
        if (this.mView.getEventContext() != null) {
            if (headerItemIsNotChanged(this.mView.getEventContext().getHeaderItem(), mediaItem)) {
                Log.s("CreaturePicturesDelegate", "bindCreatureHeader Skipped");
            } else if (mediaItem != null) {
                addCreatureMainFilterOnHeaderItemLoaded(mediaItem);
                this.mView.bindHeader(mediaItem);
            } else if (isFromMyQuery()) {
                MediaItem mediaItem2 = new MediaItem();
                mediaItem2.setSubCategory(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "sub", (String) null));
                lambda$addCreatureMainFilterOnHeaderItemLoaded$5(mediaItem2, (Bitmap) null);
            }
            ICreatureContactDelegate iCreatureContactDelegate = this.mContactDelegate;
            if (iCreatureContactDelegate != null) {
                iCreatureContactDelegate.setItem(mediaItem);
            }
        }
    }

    private boolean enableHideCreatureMenu(boolean z) {
        if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || !z) {
            return false;
        }
        if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES || CreatureData.isEssential(this.mHeaderItem)) {
            return true;
        }
        return false;
    }

    private boolean enableMergeMenu(boolean z) {
        if (!z) {
            return false;
        }
        if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES || CreatureData.isEssential(this.mHeaderItem)) {
            return true;
        }
        return false;
    }

    private boolean enableUnMergeMenu(boolean z) {
        if (!z) {
            return false;
        }
        if (!PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES || CreatureData.isEssential(this.mHeaderItem)) {
            return true;
        }
        return false;
    }

    private boolean equalCreatureGroupIds(MediaItem mediaItem, MediaItem mediaItem2) {
        ExtrasID extrasID = ExtrasID.CREATURE_FACE_GROUP_IDS;
        List list = (List) mediaItem.getExtra(extrasID, new ArrayList());
        List list2 = (List) mediaItem2.getExtra(extrasID, new ArrayList());
        if (list.size() != list2.size() || !new HashSet(list2).containsAll(list)) {
            return false;
        }
        return true;
    }

    private MediaItem findHeaderItem() {
        if (TextUtils.isEmpty(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "SelectedFilter"))) {
            try {
                if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
                    ISearchPicturesView iSearchPicturesView = this.mView;
                    return iSearchPicturesView.getMediaData(iSearchPicturesView.getLocationKey()).read(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "sub", (String) null);
        if (IdentityCreatureUtil.isPerson(argValue)) {
            return PeopleDataManager.loadHeaderItem(argValue);
        }
        return PetDataManager.loadHeaderItem(argValue);
    }

    private int getCreatureCount(int i2) {
        Integer num = this.mCreatureCount;
        if (num == null) {
            num = Integer.valueOf(loadCreatureCount(i2));
            this.mCreatureCount = num;
        }
        return num.intValue();
    }

    private boolean hasMultipleKeyword(String str) {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY || TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, "SelectedFilter", (String) null))) {
            return false;
        }
        return true;
    }

    private boolean hasSubKeyword(String str) {
        return !TextUtils.isEmpty(ArgumentsUtil.getArgValue(str, "sub"));
    }

    private boolean headerItemIsNotChanged(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem != null && mediaItem2 != null && mediaItem.getMediaId() == mediaItem2.getMediaId() && mediaItem.getFileSize() == mediaItem2.getFileSize() && TextUtils.equals(mediaItem.getSubCategory(), mediaItem2.getSubCategory()) && equalCreatureGroupIds(mediaItem, mediaItem2)) {
            CreatureData of2 = CreatureData.of(mediaItem);
            CreatureData of3 = CreatureData.of(mediaItem2);
            if (!TextUtils.equals(of2.creatureName, of3.creatureName) || !TextUtils.equals(of2.relationshipType, of3.relationshipType) || !TextUtils.equals(of2.creatureUuid, of3.creatureUuid)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isFromMyQuery() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MY_QUERY || !ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "my_query", false)) {
            return false;
        }
        return true;
    }

    private boolean isKeywordSearchedOnSupportMultipleKeyword() {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "need_keyword_search", false)) {
            return false;
        }
        return true;
    }

    private boolean isMergedCreature(boolean z) {
        MediaItem mediaItem;
        List<Long> list;
        List<Long> arrayList = new ArrayList<>();
        if (PreferenceFeatures.OneUi7x.SUPPORT_UNMERGE_CREATURE && (mediaItem = this.mHeaderItem) != null) {
            ExtrasID extrasID = ExtrasID.CREATURE_FACE_GROUP_IDS;
            Object extra = mediaItem.getExtra(extrasID);
            if (extra == null) {
                String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "sub", (String) null);
                if (z) {
                    list = PeopleDataManager.getGroupIds(argValue);
                } else {
                    list = PetDataManager.getGroupIds(argValue);
                }
                this.mHeaderItem.setExtra(extrasID, list);
                this.mView.bindHeader(this.mHeaderItem);
                arrayList = list;
            } else {
                arrayList = (List) extra;
            }
        }
        if (arrayList.size() > 1) {
            return true;
        }
        return false;
    }

    private boolean isNamedCreature() {
        String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "title");
        if (TextUtils.isEmpty(argValue) || "null".equals(argValue.toLowerCase(Locale.US))) {
            return false;
        }
        return true;
    }

    private boolean isSameSubKey(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return ArgumentsUtil.getArgValue(str, "sub", "").equals(ArgumentsUtil.getArgValue(str2, "sub", ""));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCreatureMainFilterOnHeaderItemLoaded$6(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new C0533c(this, mediaItem, bitmap, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj) {
        this.mView.bindHeader(this.mHeaderItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$publishCreatureHeaderItem$1(ThreadPool.JobContext jobContext) {
        MediaItem findHeaderItem = findHeaderItem();
        if (findHeaderItem == null) {
            Log.se("CreaturePicturesDelegate", "publish header item : null");
            if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || isFromMyQuery()) {
                return null;
            }
            this.mView.getBlackboard().postEvent(EventMessage.obtain(8501));
            return null;
        }
        this.mHeaderItem = findHeaderItem;
        return findHeaderItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishCreatureHeaderItem$2(Future future) {
        bindCreatureHeader((MediaItem) future.get());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCreatureMenu$3(boolean z, Menu menu, boolean z3, boolean z7, int i2, boolean z9) {
        if (z) {
            setPeopleMenuVisibility(menu, z3, z7, Integer.valueOf(i2), z9);
            return;
        }
        Menu menu2 = menu;
        boolean z10 = z3;
        setPetsMenuVisibility(menu2, z10, z7, Integer.valueOf(i2), z9);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateCreatureMenu$4(Menu menu, ThreadPool.JobContext jobContext) {
        boolean supportPetsMenu;
        int i2;
        IdentityCreatureUtil.Category category;
        String locationKey = this.mView.getLocationKey();
        if (locationKey == null) {
            return null;
        }
        boolean isSearchCategoryPeople = LocationKey.isSearchCategoryPeople(locationKey);
        if (isSearchCategoryPeople) {
            supportPetsMenu = supportPeopleMenu(locationKey);
        } else {
            supportPetsMenu = supportPetsMenu(locationKey);
        }
        boolean z = supportPetsMenu;
        boolean isNamedCreature = isNamedCreature();
        if (z) {
            if (isSearchCategoryPeople) {
                category = IdentityCreatureUtil.Category.PEOPLE;
            } else {
                category = IdentityCreatureUtil.Category.PET;
            }
            i2 = getCreatureCount(category.ordinal());
        } else {
            i2 = 0;
        }
        ThreadUtil.postOnUiThread(new b(this, isSearchCategoryPeople, menu, z, isNamedCreature, i2, isMergedCreature(isSearchCategoryPeople)));
        return null;
    }

    private int loadCreatureCount(int i2) {
        if (i2 == IdentityCreatureUtil.Category.PET.ordinal()) {
            return new MpHelper().getPetsCount();
        }
        return new MpHelper().getPeopleCount();
    }

    /* access modifiers changed from: private */
    /* renamed from: postCreatureMainFilter */
    public void lambda$addCreatureMainFilterOnHeaderItemLoaded$5(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap == null) {
            mediaItem.setBroken(true);
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(this.mView.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
        }
        this.mView.getBlackboard().postEvent(EventMessage.obtain(8523, new Object[]{bitmap, String.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(mediaItem.getSubCategory()))}));
    }

    private void setMenuVisibility(MenuItem menuItem, boolean z) {
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
    }

    private void setPeopleMenuVisibility(Menu menu, boolean z, boolean z3, Integer num, boolean z7) {
        boolean z9;
        setMenuVisibility(menu.findItem(R.id.action_hide_this_person), enableHideCreatureMenu(z));
        boolean z10 = false;
        if (!z || (!z3 && !PreferenceFeatures.OneUi6x.SUPPORT_MERGE_PEOPLE_WITHOUT_NAME)) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z9 && num != null) {
            if (num.intValue() > 1) {
                z9 = true;
            } else {
                z9 = false;
            }
        }
        setMenuVisibility(menu.findItem(R.id.action_merge_people_name), enableMergeMenu(z9));
        MenuItem findItem = menu.findItem(R.id.action_unmerge_people_name);
        if (z && z7) {
            z10 = true;
        }
        setMenuVisibility(findItem, enableUnMergeMenu(z10));
    }

    private void setPetsMenuVisibility(Menu menu, boolean z, boolean z3, Integer num, boolean z7) {
        boolean z9;
        setMenuVisibility(menu.findItem(R.id.action_hide_this_pet), enableHideCreatureMenu(z));
        boolean z10 = false;
        if (!z || (!z3 && !PreferenceFeatures.OneUi6x.SUPPORT_MERGE_PEOPLE_WITHOUT_NAME)) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (z9 && num != null) {
            if (num.intValue() > 1) {
                z9 = true;
            } else {
                z9 = false;
            }
        }
        setMenuVisibility(menu.findItem(R.id.action_merge_pets_name), enableMergeMenu(z9));
        MenuItem findItem = menu.findItem(R.id.action_unmerge_pets_name);
        if (z && z7) {
            z10 = true;
        }
        setMenuVisibility(findItem, enableUnMergeMenu(z10));
    }

    private boolean supportCreatureMenu(String str, boolean z) {
        ISearchPicturesView iSearchPicturesView = this.mView;
        if (iSearchPicturesView == null || iSearchPicturesView.isSelectionMode() || this.mView.isEmptyViewShowing() || (this.mEnabledClusterResult && (this.mView.isPicturesOnlyMode() || this.mIsFilterChangeOnCluster))) {
            return false;
        }
        if (!z ? LocationKey.isSearchCategoryPet(str) : LocationKey.isSearchCategoryPeople(str)) {
            if (isOnMultipleKeyword() || isKeywordSearchedOnSupportMultipleKeyword() || isOnGoingFaceClusterMerge()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean supportPeopleMenu(String str) {
        return supportCreatureMenu(str, true);
    }

    private boolean supportPetsMenu(String str) {
        if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER) || !supportCreatureMenu(str, false)) {
            return false;
        }
        return true;
    }

    private void updateCreatureMenuVisibility(String str, Menu menu) {
        Menu menu2 = menu;
        setPeopleMenuVisibility(menu2, supportPeopleMenu(str), isNamedCreature(), (Integer) null, false);
        setPetsMenuVisibility(menu2, supportPetsMenu(str), isNamedCreature(), (Integer) null, false);
    }

    private void updateLocationKey(String str) {
        String locationKey = this.mView.getLocationKey();
        if (locationKey == null || str == null) {
            Log.se("CreaturePicturesDelegate", "New location key is null");
            return;
        }
        this.mView.refreshLocationKey(ArgumentsUtil.appendArgs(ArgumentsUtil.removeArg(locationKey, "title"), "title", ArgumentsUtil.getArgValue(str, "title", "")));
    }

    public void doPendingUpdate() {
        Runnable runnable = this.mPendingUpdateRunnable;
        if (runnable != null) {
            runnable.run();
            this.mPendingUpdateRunnable = null;
        }
    }

    public ICreatureContactDelegate getContactDelegate() {
        return this.mContactDelegate;
    }

    public void handleUpdateCreatureTagEvent(EventMessage eventMessage) {
        String locationKey = this.mView.getLocationKey();
        if (!TextUtils.isEmpty(locationKey)) {
            String str = (String) eventMessage.obj;
            if (TextUtils.isEmpty(str)) {
                this.mView.onHandleEvent(EventMessage.obtain(1066, locationKey));
            } else if (eventMessage.arg2 == ArgumentsUtil.removeArgs(locationKey).hashCode()) {
                this.mView.onHandleEvent(EventMessage.obtain(1066, str));
                this.mView.invalidateOptionsMenu();
            } else if (isSameSubKey(locationKey, str)) {
                updateLocationKey(str);
                this.mPendingUpdateRunnable = new l(4, this);
            }
        }
    }

    public boolean isOnGoingFaceClusterMerge() {
        if (ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "prev_sub_category") != null) {
            return true;
        }
        return false;
    }

    public boolean isOnMultipleKeyword() {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || TextUtils.isEmpty(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "SelectedFilter"))) {
            return false;
        }
        return true;
    }

    public boolean isRelationshipEnabled(String str) {
        if (TextUtils.isEmpty(str) || getCreatureCount(IdentityCreatureUtil.Category.PEOPLE.ordinal()) <= 0) {
            return false;
        }
        return true;
    }

    public void launchContactDetail() {
        Optional.ofNullable(this.mContactDelegate).ifPresent(new w(14));
    }

    public void publishCreatureHeaderItem() {
        MediaItem mediaItem = (MediaItem) this.mView.getBlackboard().pop("data:///CreatureHeaderItem");
        if (mediaItem != null) {
            this.mHeaderItem = mediaItem;
            bindCreatureHeader(mediaItem);
            return;
        }
        ThreadPool.getInstance().submit(new C0381p(17, this), new p(25, this));
    }

    public void resetCreatureCount() {
        this.mCreatureCount = null;
    }

    public void setFilterChangedOnCluster() {
        this.mIsFilterChangeOnCluster = true;
    }

    public void updateCreatureMenu(Menu menu) {
        if (!Features.isEnabled(Features.IS_GED)) {
            ThreadPool.getInstance().submit(new I5.e(8, this, menu));
        }
    }

    public void updateOptionsMenuAction(String str, Menu menu) {
        boolean z;
        MenuItem findItem = menu.findItem(R.id.action_add_to_quick_search);
        boolean z3 = false;
        if (this.mView.isSelectionMode() || this.mView.getDataCount() <= 0 || !hasSubKeyword(str) || !hasMultipleKeyword(str)) {
            z = false;
        } else {
            z = true;
        }
        setMenuVisibility(findItem, z);
        updateCreatureMenuVisibility(str, menu);
        if (this.mEnabledClusterResult) {
            if (this.mView.isPicturesOnlyMode() && !this.mView.isSelectionMode()) {
                z3 = true;
            }
            setMenuVisibility(menu.findItem(R.id.action_select), z3);
            setMenuVisibility(menu.findItem(R.id.action_create), z3);
            setMenuVisibility(menu.findItem(R.id.action_slideshow), z3);
            setMenuVisibility(menu.findItem(R.id.action_remove_from_result), z3);
        }
    }
}
