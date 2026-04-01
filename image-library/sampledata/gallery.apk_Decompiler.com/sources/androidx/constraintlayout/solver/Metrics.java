package androidx.constraintlayout.solver;

import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Metrics {
    public long bfs;
    public long constraints;
    public long determineGroups;
    public long errors;
    public long extravariables;
    public long fullySolved;
    public long graphOptimizer;
    public long graphSolved;
    public long grouping;
    public long infeasibleDetermineGroups;
    public long iterations;
    public long lastTableSize;
    public long layouts;
    public long linearSolved;
    public long maxRows;
    public long maxTableSize;
    public long maxVariables;
    public long measuredMatchWidgets;
    public long measuredWidgets;
    public long measures;
    public long measuresWrap;
    public long measuresWrapInfeasible;
    public long minimize;
    public long minimizeGoal;
    public long nonresolvedWidgets;
    public long optimize;
    public long pivots;
    public long simpleconstraints;
    public long slackvariables;
    public long tableSizeIncrease;
    public long widgets;

    public String toString() {
        StringBuilder sb2 = new StringBuilder("\n*** Metrics ***\nmeasures: ");
        sb2.append(this.measures);
        sb2.append("\nmeasuresWrap: ");
        sb2.append(this.measuresWrap);
        sb2.append("\nmeasuresWrapInfeasible: ");
        sb2.append(this.measuresWrapInfeasible);
        sb2.append("\ndetermineGroups: ");
        sb2.append(this.determineGroups);
        sb2.append("\ninfeasibleDetermineGroups: ");
        sb2.append(this.infeasibleDetermineGroups);
        sb2.append("\ngraphOptimizer: ");
        sb2.append(this.graphOptimizer);
        sb2.append("\nwidgets: ");
        sb2.append(this.widgets);
        sb2.append("\ngraphSolved: ");
        sb2.append(this.graphSolved);
        sb2.append("\nlinearSolved: ");
        return C0212a.o(sb2, this.linearSolved, "\n");
    }
}
