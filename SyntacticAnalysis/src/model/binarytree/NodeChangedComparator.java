package model.binarytree;

import java.util.Comparator;

public class NodeChangedComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        NodeChanged nodeChange1 = (NodeChanged) o1;
        NodeChanged nodeChange2 = (NodeChanged) o2;

        return nodeChange2.getNewPosition() - nodeChange1.getNewPosition();
    }
}
