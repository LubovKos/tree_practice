package ru.ac.uniyar.practic.lubovkos.tree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    //добавление узла в дерево (добавление ребенка)
    public void add(Node _child) {
        children.add(_child);
    }

    //удаление узла по имени
    public void deleteNodeName(String name){
        int idx = -1;
        for (int i = 0; i < children.size(); i++){
            if (children.get(i).name == name)
                idx = i;
            else
                children.get(i).deleteNodeName(name);
        }
        if (idx != -1){
            List<Node> newChildren =  new ArrayList<>();
            for (int i = 0; i< idx; i++)
                newChildren.add(children.get(i));
            for (Node child: children.get(idx).children)
                newChildren.add(child);
            for (int i = idx + 1; i< children.size(); i++)
                newChildren.add(children.get(i));
            children = newChildren;
        }
    }

    //удаление узла по идентификатору
    public void deleteNodeId(String ident){
        int idx = -1;
        for (int i = 0; i < children.size(); i++){
            if (children.get(i).id == ident)
                idx = i;
            else
                children.get(i).deleteNodeId(ident);
        }
        if (idx != -1){
            List<Node> newChildren = new ArrayList<>();
            for (int i = 0; i< idx; i++)
                newChildren.add(children.get(i));
            for (Node child: children.get(idx).children)
                newChildren.add(child);
            for (int i = idx + 1; i< children.size(); i++)
                newChildren.add(children.get(i));
            children = newChildren;
        }
    }

    //удаление всех дочерних узлов
    public void deleteAllChildren(){
        for (int i = 0; i < children.size(); i++)
            children.remove(children.get(i));
    }

    //поиск дочернего узла по имени
    public Node findNode(String name){
        for (Node child: children)
            if (child.name == name)
                return child;
        return null;
    }

    //изменение значения (имени) узла
    public void changeNode(String _name){
        name = _name;
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

    //запись дерева в файл
    public void printToFile(String path) throws IOException {
        String content = toString();
        Files.write(Paths.get(path), content.getBytes());
    }

    //запись дерева для отображения на сайте
    public String printToHtml(Node node) {
        if (node == null) return "";

        String res = "";
        res += "<li>";
        res += node.name;

        if (node.children == null) {
            res += "</li>";
            return res.toString();
        }
        res += "<ul>";
        for (Node child : node.children)
            res += printToHtml(child);
        res += ("</ul>" + "</li>");
        return res.toString();
    }
}
