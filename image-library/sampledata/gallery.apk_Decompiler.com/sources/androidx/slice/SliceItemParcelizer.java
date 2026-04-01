package androidx.slice;

import androidx.versionedparcelable.VersionedParcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SliceItemParcelizer {
    public static SliceItem read(VersionedParcel versionedParcel) {
        SliceItem sliceItem = new SliceItem();
        sliceItem.mHints = (String[]) versionedParcel.readArray(sliceItem.mHints, 1);
        sliceItem.mFormat = versionedParcel.readString(sliceItem.mFormat, 2);
        sliceItem.mSubType = versionedParcel.readString(sliceItem.mSubType, 3);
        sliceItem.mHolder = (SliceItemHolder) versionedParcel.readVersionedParcelable(sliceItem.mHolder, 4);
        sliceItem.onPostParceling();
        return sliceItem;
    }

    public static void write(SliceItem sliceItem, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(true, true);
        sliceItem.onPreParceling(versionedParcel.isStream());
        versionedParcel.writeArray(sliceItem.mHints, 1);
        versionedParcel.writeString(sliceItem.mFormat, 2);
        versionedParcel.writeString(sliceItem.mSubType, 3);
        versionedParcel.writeVersionedParcelable(sliceItem.mHolder, 4);
    }
}
