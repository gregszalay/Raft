/*
 * Copyright (c) 2020. Some Rights Reserved. The author of this software, Gergely Szalay [gr.szalay@gmail.com; GitHub: @gregszalay] grants you non-exclusive, royalty free, license to use and modify this source code for personal or educational use with condition of full attribute to the author. This project was made for educational and practice purposes. Commercial use is prohibited without the author’s consent. The author offers no warranty and expressly excludes any and all liability for the work herein. For copyright sources used, see credits.txt file.
 */

package settings;

public enum ResourcesRequired {

    /**
     * How many of each resource is required to build the following utilities?
     */
    RAFT_PANEL(2, 2, 0),
    SPEAR(4, 4, 4),
    CAMPFIRE(2, 4, 3),
    WATERPURIFIER(2, 0, 4),
    FISHING_NET(2, 6, 0);

    private final int planks;
    private final int leaves;
    private final int rubbish;

    ResourcesRequired(int planks, int leaves, int rubbish) {
        this.planks = planks;
        this.leaves = leaves;
        this.rubbish = rubbish;
    }

    public int getPlanksNeeded() {
        return planks;
    }

    public int getLeavesNeeded() {
        return leaves;
    }

    public int getRubbishNeeded() {
        return rubbish;
    }
}
