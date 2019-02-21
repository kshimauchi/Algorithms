package programming.algorithm.prefixtree;

import java.util.HashMap;
import java.util.Map;

public class PrefixNode {
    char c;
    int id;
    boolean isWord = false;

    Map<Character, PrefixNode> children;

    public PrefixNode() {
        c = 0;
        id = 0;
    }

    public PrefixNode(char c, int id) {
        this.c = c;
        this.id = id;
    }

    public boolean hasChildren(char c) {
        return (children != null && children.containsKey(c));
    }
    public PrefixNode getChildren(char c) {
        if (!hasChildren(c)) return null;
        return children.get(c);
    }

    public void addChildren(PrefixNode node) {
        if (children == null) children = new HashMap<Character, PrefixNode>();
        if (!hasChildren(node.c)) children.put(node.c, node);
    }
    public boolean canDelete() {
        if (children == null || children.size() == 0) return true;
        return false;
    }
    @Override
    public String toString() {
        if (children != null) return c + (isWord ? "." + id : "") + "->[" + children.values() + "]";
        return c + "." + id;
    }
}
