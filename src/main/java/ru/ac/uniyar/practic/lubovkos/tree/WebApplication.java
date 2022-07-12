package ru.ac.uniyar.practic.lubovkos.tree;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web-приложение в котором регистрируются все ресурсы.
 */
public class WebApplication extends Application {

    private List<String> list = new ArrayList<>();
    private List<Node> ListTree = new ArrayList<>();
    private Node root;

    public WebApplication() {
        root = new Node("Root");
        Node child = new Node("List1");
        Node child2 = new Node("List2");
        Node child3 = new Node("List3");
        root.add(child);
        child.add(child2);
        root.add(child3);
        ListTree.add(root);
        ListTree.add(child);
        ListTree.add(child2);
        ListTree.add(child3);

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
    }

    /**
     * Возвращает список всех ресурсов web-приложения.
     * @return список всех ресурсов web-приложения.
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(new ListPresentationController(list, root, ListTree));
        return resources;
    }
}
