package com.samsung.android.gallery.app.ui.list.search.editcreature;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IEditCreatureNameView extends IMvpBaseView {
    String getCurrentName();

    boolean isCreatureNameChanged(String str);

    boolean isRelationshipChanged(String str);

    boolean isTaggedName(String str);

    void onCancel(DialogInterface dialogInterface);

    void onHeaderChanged();

    void onItemClicked(CreatureNameData creatureNameData);

    void setHeaderImage(Bitmap bitmap);

    void updateContactLinkView();

    void updateCreatureName(String str);

    void updateRelationship(String str);
}
