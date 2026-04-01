package androidx.media3.exoplayer.trackselection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ForwardingTrackSelection implements ExoTrackSelection {
    private final ExoTrackSelection trackSelection;

    public ForwardingTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }

    public void disable() {
        this.trackSelection.disable();
    }

    public void enable() {
        this.trackSelection.enable();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ForwardingTrackSelection)) {
            return false;
        }
        return this.trackSelection.equals(((ForwardingTrackSelection) obj).trackSelection);
    }

    public int getIndexInTrackGroup(int i2) {
        return this.trackSelection.getIndexInTrackGroup(i2);
    }

    public int getSelectedIndexInTrackGroup() {
        return this.trackSelection.getSelectedIndexInTrackGroup();
    }

    public ExoTrackSelection getWrappedInstance() {
        return this.trackSelection;
    }

    public int hashCode() {
        return this.trackSelection.hashCode();
    }

    public int indexOf(int i2) {
        return this.trackSelection.indexOf(i2);
    }

    public int length() {
        return this.trackSelection.length();
    }

    public void onDiscontinuity() {
        this.trackSelection.onDiscontinuity();
    }

    public void onPlayWhenReadyChanged(boolean z) {
        this.trackSelection.onPlayWhenReadyChanged(z);
    }

    public void onPlaybackSpeed(float f) {
        this.trackSelection.onPlaybackSpeed(f);
    }

    public void onRebuffer() {
        this.trackSelection.onRebuffer();
    }
}
