import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import ru.ac.uniyar.practic.lubovkos.tree.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNode {
    @Test
    void createNode(){
        Node node = new Node("Корень");
        assertEquals("Корень", node.getName());
    }

    @Test
    void addNode(){
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
    }

    @Test
    void deleteNodeId(){
        Node root1 = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root1.add(child1);
        root1.add(child2);
        root1.add(child3);

        Node root2 = new Node("Корень");
        root2.add(child1);
        root2.add(child2);

        root1.deleteNodeId(child3.id);
        assertEquals(root1.children, root2.children);
    }

    @Test
    void deleteNodeName(){
        Node root1 = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root1.add(child1);
        root1.add(child2);
        root1.add(child3);

        Node root2 = new Node("Корень");
        root2.add(child2);
        root2.add(child3);

        root1.deleteNodeName("Лист1");
        assertEquals(root1.children, root2.children);
    }

    @Test
    void deleteAllChildren(){
        Node root1 = new Node("Корень");
        Node child = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        Node child4 = new Node("Лист4");
        Node child5 = new Node("Лист5");
        Node child6 = new Node("Лист6");
        root1.add(child);
        child.add(child2);
        root1.add(child3);
        child.add(child4);
        child2.add(child5);
        child5.add(child6);

        Node root2 = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child22 = new Node("Лист2");
        Node child23 = new Node("Лист3");
        Node child24 = new Node("Лист4");
        root2.add(child);
        child1.add(child22);
        root2.add(child23);
        child1.add(child24);

        child2.deleteAllChildren();
        assertEquals(child2.children, child22.children);
    }

    @Test
    void changeNode(){
        Node child = new Node("Лист1");
        Node child1 = new Node("Лист2");

        child.changeNode("Лист2");
        assertEquals(child.name, child1.name);
    }

    @Test
    void findNode(){
        Node root = new Node("Корень");
        Node child = new Node("Лист1");
        root.add(child);

        Node child1 = root.findNode("Лист1");
        assertEquals(child1, child);
    }

    @Test
    void printToString(){
        Node root = new Node("Корень");
        Node child = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.add(child);
        child.add(child2);
        root.add(child3);
        String actual = root.toString();
        String expected = "Корень\n Лист1\n  Лист2\n Лист3\n";
        assertEquals(expected, actual);
    }

    @Test
    void printToJson() throws JsonProcessingException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String actual = objectMapper.writeValueAsString(root);
        System.out.println(actual);
    }

    @Test
    void readFromJson() throws JsonProcessingException {
        String jsonString = "{\n" +
                " \"name\" : \"Корень\", \n" +
                " \"children\" : [ {\n" +
                "   \"name\" : \"Лист\", \n" +
                "   \"children\" : [ ]\n" +
                " } ]\n" +
                "}";
                ;
        ObjectMapper objectMapper = new ObjectMapper();
        Node actual = objectMapper.readValue(jsonString, Node.class);
        assertEquals("Корень", actual.getName());
        assertEquals(1, actual.getChildren().size());
        assertEquals("Лист", actual.getChildren().get(0).getName());
    }
}
