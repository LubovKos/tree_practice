package ru.ac.uniyar.practic.lubovkos.tree;

public class Main {

    public static void main(String[] args) {
    Node root = new Node("Корень");
    Node child = new Node("Лист1");
    Node child2 = new Node("Лист2");
    Node child3 = new Node("Лист3");
    root.add(child);
    child.add(child2);
    root.add(child3);
    System.out.println(root.printToHtml(root));
    System.out.print(root.toString());
    }
}
