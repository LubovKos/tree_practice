package ru.ac.uniyar.practic.lubovkos.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    public String id;
    public String name;
    public List<Node> children;
    //public Node parent;

    public Node(String node_name) {
        this.id = UUID.randomUUID().toString();
        this.name = node_name;
        this.children = new ArrayList<>();
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void add(Node _child) {
        children.add(_child);
    }
    public void deleteNode(String ident){
        int idx = -1;
        for (int i = 0; i<children.size(); i++)
            if (children.get(i).id == ident)
                idx = i;
        if (idx != -1)
            children.remove(idx);
    }
}
