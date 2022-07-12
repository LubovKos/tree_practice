package ru.ac.uniyar.practic.lubovkos.tree;

import com.sun.xml.txw2.output.IndentingXMLFilter;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Контроллер отвечающий за представление списка.
 */
@Path("/")
public class ListPresentationController {
    private final List<String> list;
    private final List<Node> ListTree;
    private final Node root;
    /**
     * Запоминает список, с которым будет работать.
     * @param list список, с которым будет работать контроллер.
     */
    public ListPresentationController(List<String> list, Node root, List<Node> ListTree) {
        this.list = list;
        this.root = root;
        this.ListTree = ListTree;
    }

    int idx = 4;

    /**
     * Пример вывода простого текста.
     */
    @GET
    @Path("example")
    @Produces("text/plain")
    public String getSimpleText() {
        return "hello world";
    }

    /**
     * Выводит HTML-страницу со списком, ссылками на страницы редактирования и копкой добавления записи.
     * @return HTML-страница со списком.
     */
    @GET
    @Path("/")
    @Produces("text/html")
    public String getList() {
        String result =
                "<html>" +
                "  <head>" +
                "    <title>Вывод списка</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Список</h1>" +
                "    <ul>";

        for (int i = 0; i < list.size(); i++) {
            String listItem = list.get(i);
            result += "<li>" + listItem + " <a href=\"edit/" + i + "\">Редактировать</a> </li>";
        }

        result += "    </ul>" +
                "      <br/>" +
                "      <form method=\"post\" action=\"add_random_item\">" +
                "        <input type=\"submit\" value=\"Add random item\"/>" +
                "      </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    /**
     * Пример обработки POST запроса.
     * Добавляет одну случайную запись в список и перенаправляет пользователя на основную страницу со списком.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("add_random_item")
    @Produces("text/html")
    public Response addRandomItem() {
        list.add("zzz");
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    @POST
    @Path("add_item_tree")
    @Produces("text/html")
    public Response addItemTree() {
        String name = "List" + String.valueOf(idx);
        Node node = new Node(name);
        root.add(node);
        idx++;
        try {
            return Response.seeOther(new URI("/tree")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для редактирования одного элемента.
     * @param itemId индекс элемента списка.
     * @return страничка для редактирования одного элемента.
     */
    @GET
    @Path("/edit/{id}")
    @Produces("text/html")
    public String getEditPage(@PathParam("id") int itemId) {
        String listItem = list.get(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Редактирование элемента списка</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Редактирование элемента списка</h1>" +
                        "    <form method=\"post\" action=\"/edit/" + itemId + "\">" +
                        "      <p>Значение</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + listItem +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    @GET
    @Path("/editTree/{id}")
    @Produces("text/html")
    public String getEditPageTree(@PathParam("id") int itemId) {
        Node listItem = ListTree.get(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Редактирование элемента дерева</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Редактирование элемента дерева</h1>" +
                        "    <form method=\"post\" action=\"/editTree/" + itemId + "\">" +
                        "      <p>Значение</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + listItem.getName() +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                        "  </body>" +
                        "</html>";
        return result;
    }


    /**
     * Редактирует элемент списка на основе полученных данных.
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/edit/{id}")
    @Produces("text/html")
    public Response editItem(@PathParam("id") int itemId, @FormParam("value") String itemValue) {
        list.set(itemId, itemValue);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    @POST
    @Path("/editTree/{id}")
    @Produces("text/html")
    public Response editItemTree(@PathParam("id") int itemId, @FormParam("value") String itemValue) {
        Node node = ListTree.get(itemId);
        node.name = itemValue;
        ListTree.set(itemId, node);
        try {
            return Response.seeOther(new URI("/tree")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }


    /**
     * Пример вывода вложенного списка.
     */
    @GET
    @Path("nested_list")
    @Produces("text/html")
    public String getNestedListExample() {
        return "<html>" +
                "  <head>" +
                "    <title>Hello world</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Hello world</h1>" +
                "    <ul>" +
                "      <li>1</li>" +
                "      <li>2</li>" +
                "      <li>3" +
                "        <ul>" +
                "          <li>3.1</li>" +
                "        </ul>" +
                "      </li>" +
                "    </ul>" +
                "  </body>" +
                "</html>";
    }
    @GET
    @Path("tree")
    @Produces("text/html")
    public String getTree() {
        String result =  "<html>" +
                "  <head>" +
                "    <title>Вывод дерева</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Вывод дерева</h1>" +
                root.printToHtml(root) +
                "  </body>" +
                "</html>";
        //<ul> <li> Корень<ul> <li> Лист1<ul> <li>Лист2<ul></ul> </li></ul> </li> <li>Лист3<ul></ul></li></ul></li>
        String [] path = result.split("<li>");
        String res = "";
        for (int i = 0; i < path.length; i++) {
            String listItem = path[i];
            if (i == path.length - 1)
                res += "<li>" + listItem + " <a href=\"editTree/" + i + "\"></a> </li>";
            else if (i==0)
                res += listItem + " <a href=\"editTree/" + i + "\">Редактировать</a>";
            else
                res += "<li>" + listItem + " <a href=\"editTree/" + i + "\">Редактировать</a> </li>";
        }

        res += "    </ul>" +
                "      <br/>" +
                "      <form method=\"post\" action=\"add_item_tree\">" +
                "        <input type=\"submit\" value=\"Add item tree\"/>" +
                "      </form>" +
                "  </body>" +
                "</html>";
        return res;
    }

}
