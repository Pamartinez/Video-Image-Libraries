package com.samsung.android.gallery.app.ui.dialog.creature.relationship;

import A4.C0383s;
import Gb.b;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import b9.a;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import q6.e;
import t4.C0517a;
import v4.C0527a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipChoiceAdapter extends RecyclerView.Adapter<RelationshipChoiceViewHolder> {
    private final Context mAppContext;
    private final ArrayList<RelationshipItem> mRelations = new ArrayList<>();
    private int mSelectedPosition = -1;
    private final IRelationshipChoiceDialogView mView;

    public RelationshipChoiceAdapter(Context context, IRelationshipChoiceDialogView iRelationshipChoiceDialogView) {
        this.mAppContext = context;
        this.mView = iRelationshipChoiceDialogView;
        initialize();
    }

    private boolean checkContains(String str, String str2) {
        if (CustomRelationshipKeySet.isCustomRelationshipType(str)) {
            return str.contains(str2);
        }
        List asList = Arrays.asList(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        Collections.sort(asList);
        List asList2 = Arrays.asList(str2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        Collections.sort(asList2);
        boolean z = true;
        for (int i2 = 0; i2 < asList.size() && z; i2++) {
            z = ((String) asList.get(i2)).equals(asList2.get(i2));
        }
        return z;
    }

    private void clickSelectedItem() {
        this.mView.onItemClicked(this.mRelations.get(this.mSelectedPosition).mType);
    }

    private void findSelectedPosition() {
        if (!TextUtils.isEmpty(this.mView.getInitialType())) {
            IntStream.range(0, this.mRelations.size()).filter(new b(7, this)).findFirst().ifPresent(new C0383s(4, this));
        }
    }

    private void initialize() {
        loadPredefinedRelationship();
        if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP) {
            loadCustomRelationshipAndButton();
        }
        findSelectedPosition();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$findSelectedPosition$1(int i2) {
        return checkContains(this.mRelations.get(i2).mType, this.mView.getInitialType());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$findSelectedPosition$2(int i2) {
        this.mSelectedPosition = i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPredefinedRelationship$0(String str, int i2) {
        return TextUtils.equals(str, this.mRelations.get(i2).mName);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$3(int i2, RelationshipChoiceViewHolder relationshipChoiceViewHolder, View view) {
        onListItemClicked(i2, relationshipChoiceViewHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$4(RelationshipChoiceViewHolder relationshipChoiceViewHolder, View view) {
        onStartEditDialog(relationshipChoiceViewHolder, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$5(RelationshipChoiceViewHolder relationshipChoiceViewHolder, View view) {
        onDeleteCustomItem(relationshipChoiceViewHolder.getBindingAdapterPosition());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$onStartEditDialog$8(int i2) {
        return new String[i2];
    }

    private void onDeleteCustomItem(int i2) {
        String str = this.mRelations.get(i2).mName;
        CustomRelationshipKeySet.getInstance().removeFromCloneData(str);
        SimpleThreadPool.getInstance().execute(new a(str, 4));
        this.mRelations.remove(i2);
        this.mView.onItemRemoved();
        int i7 = this.mSelectedPosition;
        if (i7 == i2) {
            this.mSelectedPosition = -1;
        } else if (i7 > i2) {
            this.mSelectedPosition = i7 - 1;
        }
        notifyItemRemoved(i2);
    }

    private void onListItemClicked(int i2, RelationshipChoiceViewHolder relationshipChoiceViewHolder) {
        if (i2 == 0) {
            onStartEditDialog(relationshipChoiceViewHolder, false);
            return;
        }
        notifyItemChanged(this.mSelectedPosition);
        int bindingAdapterPosition = relationshipChoiceViewHolder.getBindingAdapterPosition();
        this.mSelectedPosition = bindingAdapterPosition;
        String str = this.mRelations.get(bindingAdapterPosition).mType;
        relationshipChoiceViewHolder.mRadioButton.setChecked(true);
        this.mView.onItemClicked(str);
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.util.function.Predicate, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Object, java.util.function.Function] */
    private void onStartEditDialog(RelationshipChoiceViewHolder relationshipChoiceViewHolder, boolean z) {
        UriBuilder uriBuilder = new UriBuilder("data://user/dialog/EditRelationshipName");
        if (z) {
            uriBuilder.appendArg("name", relationshipChoiceViewHolder.getName()).appendArg(Message.KEY_POSITION, relationshipChoiceViewHolder.getBindingAdapterPosition());
        }
        uriBuilder.appendArg("relationship_name_list", (String[]) this.mRelations.stream().filter(new Object()).map(new Object()).toArray(new C0517a(7)), "/");
        this.mView.getBlackboard().post(CommandKey.DATA_REQUEST(uriBuilder.build()), (Object) null);
    }

    public void addCustomRelationship(String str) {
        CustomRelationshipKeySet.getInstance().putToCloneData(str);
        int size = this.mRelations.size() - 1;
        this.mRelations.add(size, RelationshipItem.createCustom(str));
        notifyItemChanged(this.mSelectedPosition);
        this.mSelectedPosition = size;
        notifyItemInserted(size);
        clickSelectedItem();
    }

    public int getItemCount() {
        return this.mRelations.size();
    }

    public int getItemViewType(int i2) {
        return this.mRelations.get(i2).mItemViewType;
    }

    public void loadCustomRelationshipAndButton() {
        for (String createCustom : CustomRelationshipKeySet.getInstance().getClonedValues()) {
            this.mRelations.add(RelationshipItem.createCustom(createCustom));
        }
        this.mRelations.add(RelationshipItem.createAddButton(this.mView.getActivity().getString(R.string.add_custom_relationship_button)));
    }

    public void loadPredefinedRelationship() {
        for (Map.Entry next : RelationshipKeySet.RELATIONSHIP_MAP.entrySet()) {
            String string = this.mAppContext.getResources().getString(((Integer) next.getValue()).intValue());
            OptionalInt findFirst = IntStream.range(0, this.mRelations.size()).filter(new Ib.b(this, string, 2)).findFirst();
            if (findFirst.isPresent()) {
                StringBuilder sb2 = new StringBuilder();
                RelationshipItem relationshipItem = this.mRelations.get(findFirst.getAsInt());
                sb2.append(relationshipItem.mType);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append((String) next.getKey());
                relationshipItem.mType = sb2.toString();
            } else {
                this.mRelations.add(RelationshipItem.create(string, (String) next.getKey()));
            }
        }
    }

    public RelationshipChoiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RelationshipChoiceViewHolder relationshipChoiceViewHolder = new RelationshipChoiceViewHolder(C0086a.d(viewGroup, R.layout.relationship_choice_list_view_holder, viewGroup, false), i2);
        relationshipChoiceViewHolder.setOnClickListener(new C0527a(i2, this, relationshipChoiceViewHolder));
        if (i2 == 1) {
            relationshipChoiceViewHolder.setOnEditClickListener(new v4.b(0, this, relationshipChoiceViewHolder));
            relationshipChoiceViewHolder.setOnDeleteClickListener(new v4.b(1, this, relationshipChoiceViewHolder));
        }
        return relationshipChoiceViewHolder;
    }

    public void renameCustomRelationship(int i2, String str) {
        String str2 = this.mRelations.get(i2).mName;
        CustomRelationshipKeySet.getInstance().removeFromCloneData(str2);
        CustomRelationshipKeySet.getInstance().putToCloneData(str);
        SimpleThreadPool.getInstance().execute(new e(19, str2, str));
        this.mRelations.set(i2, RelationshipItem.createCustom(str));
        notifyItemChanged(i2);
    }

    public void onBindViewHolder(RelationshipChoiceViewHolder relationshipChoiceViewHolder, int i2) {
        String str = this.mRelations.get(i2).mType;
        String str2 = this.mRelations.get(i2).mTitle;
        if (str2 == null) {
            A.a.u("Couldn't find the relationship name -> Type is ", str, "RelationshipChoiceAdapter");
            return;
        }
        relationshipChoiceViewHolder.setChecked(relationshipChoiceViewHolder.getBindingAdapterPosition() == this.mSelectedPosition);
        relationshipChoiceViewHolder.bind(str2);
    }
}
