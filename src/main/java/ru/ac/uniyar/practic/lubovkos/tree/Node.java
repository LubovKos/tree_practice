package ru.ac.uniyar.practic.lubovkos.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    public String id;
    public String name;
    public List<Node> children;

    //конструктор с параметрами
    public Node(String node_name) {
        this.id = UUID.randomUUID().toString();
        this.name = node_name;
        this.children = new ArrayList<>();
    }

    //конструктор по умолчанию
    public Node(){;}

    //список детей узла
    public List<Node> getChildren() {
        return children;
    }

    //имя узла
    public String getName() {
        return name;
    }

    //добавление ребенка
    public void add(Node _child) {
        children.add(_child);
    }

    //удаление узла по идентификатору
    public void deleteNode(String ident){
        int idx = -1;
        for (int i = 0; i<children.size(); i++)
            if (children.get(i).id == ident)
                idx = i;
        if (idx != -1)
            children.remove(idx);
    }

    //преобразование дерева в строку
    @Override
    public String toString() {
        return toString(this, 0);
    }

    private String toString(Node node, int space) {
        String res = "";
        if (node == null) return res;

        for (int i = 0; i < space; i++)
            res += "\t";
        if (node.children == null)
            return res.toString();

        res += node.name;
        res += "\n";

        for (Node child : node.children)
            res += toString(child, space + 1);
        return res.toString();
    }

}
