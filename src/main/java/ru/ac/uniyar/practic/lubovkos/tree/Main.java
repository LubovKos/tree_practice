package ru.ac.uniyar.practic.lubovkos.tree;

public class Main {

    public static void main(String[] args) {
	Node root = new Node("Корень");
    Node list1 = new Node("Лист1");
    root.add(list1);
    System.out.print(root.toString());
    }
}
