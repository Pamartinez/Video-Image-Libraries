package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialogPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import v4.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipChoiceDialogPresenter extends MvpDialogPresenter<IRelationshipChoiceDialogView> {
    private final String mInitialType;
    private String mSelectedType;

    public RelationshipChoiceDialogPresenter(IRelationshipChoiceDialogView iRelationshipChoiceDialogView) {
        super(iRelationshipChoiceDialogView);
        String initialType = iRelationshipChoiceDialogView.getInitialType();
        this.mSelectedType = initialType;
        this.mInitialType = initialType;
    }

    /* access modifiers changed from: private */
    public void addCustomRelationship(Object obj, Bundle bundle) {
        if (obj != null) {
            ((IRelationshipChoiceDialogView) this.mView).getAdapter().addCustomRelationship((String) ((Object[]) obj)[0]);
        }
    }

    private boolean isRenamed() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP) {
            return !TextUtils.equals(this.mInitialType, this.mSelectedType);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void renameCustomRelationship(Object obj, Bundle bundle) {
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            String str = (String) objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            if (intValue == -1) {
                Log.w(this.TAG, "renameCustomRelationship : invalid position, failed");
                return;
            }
            ((IRelationshipChoiceDialogView) this.mView).getAdapter().renameCustomRelationship(intValue, str);
            this.mSelectedType = "<custom>" + str;
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP) {
            arrayList.add(new SubscriberInfo("command://event/RelationshipCustomNameAdded", new c(this, 0)).setWorkingOnUI());
            arrayList.add(new SubscriberInfo("command://event/RelationshipCustomNameChanged", new c(this, 1)).setWorkingOnUI());
        }
    }

    public void onCancel() {
        if (isRenamed()) {
            this.mBlackboard.post("command://event/RelationshipSelected", this.mSelectedType);
        }
    }

    public void onItemClicked(String str) {
        this.mSelectedType = str;
        this.mBlackboard.post("command://event/RelationshipSelected", str);
    }

    public void onItemRemoved() {
        this.mSelectedType = "";
    }
}
