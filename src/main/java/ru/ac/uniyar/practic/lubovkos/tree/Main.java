package ru.ac.uniyar.practic.lubovkos.tree;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
    Node root = new Node("Корень");
    Node child = new Node("Лист1");
    Node child2 = new Node("Лист2");
    Node child3 = new Node("Лист3");
    Node child4 = new Node("Лист4");
    Node child5 = new Node("Лист5");
    Node child6 = new Node("Лист6");
    root.add(child);
    child.add(child2);
    root.add(child3);
    child.add(child4);
    child2.add(child5);
    child5.add(child6);
    System.out.println(root.toString());
    root.deleteNodeName("Лист1");
    System.out.print(root.toString());
    child3.changeNode("Листочек");
    //System.out.println(root.printToHtml(root));
    System.out.print(root.toString());
    String path = "file.txt";
    root.printToFile(path);
    System.out.print(root.findNode("Лист1"));
    }
}
