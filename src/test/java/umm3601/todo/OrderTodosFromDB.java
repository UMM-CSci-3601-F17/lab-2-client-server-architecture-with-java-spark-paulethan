package umm3601.todo;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class OrderTodosFromDB {

  Database db;
  Map<String, String[]> queryParams = new HashMap<>();

  @Before
  public void setup() throws IOException {
    db = new Database("src/main/data/todos.json");
  }

  @Test
  public void orderTodosByOwner() {
    queryParams.put("orderBy", new String[] {"owner"});
    Todo[] filteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos", 300, filteredTodos.length);
    assertEquals("Incorrect first todo owner", "Barry", filteredTodos[0].owner);
    assertEquals("Incorrect second todo owner", "Barry", filteredTodos[1].owner);
    assertEquals("Incorrect third todo owner", "Barry", filteredTodos[2].owner);
    assertEquals("Incorrect last todo owner", "Workman", filteredTodos[299].owner);
  }

  @Test
  public void orderTodosByStatus() {
    queryParams.put("orderBy", new String[] {"status"});
    Todo[] filteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos", 300, filteredTodos.length);
    assertEquals("Incorrect first todo status", false, filteredTodos[0].status);
    assertEquals("Incorrect second todo status", false, filteredTodos[1].status);
    assertEquals("Incorrect third todo status", false, filteredTodos[2].status);
    assertEquals("Incorrect last todo status", true, filteredTodos[299].status);
  }

  @Test
  public void orderTodosByCategory() {
    queryParams.put("orderBy", new String[] {"category"});
    Todo[] filteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos", 300, filteredTodos.length);
    assertEquals("Incorrect first todo category", "groceries", filteredTodos[0].category);
    assertEquals("Incorrect second todo category", "groceries", filteredTodos[1].category);
    assertEquals("Incorrect third todo category", "groceries", filteredTodos[2].category);
    assertEquals("Incorrect last todo category", "video games", filteredTodos[299].category);
  }
  @Test
  public void orderTodosByBody() {
    queryParams.put("orderBy", new String[] {"body"});
    Todo[] filteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos", 300, filteredTodos.length);
    assertEquals("Incorrect first todo body", "Ad sint incididunt officia veniam incididunt. Voluptate exercitation eu aliqua laboris occaecat deserunt cupidatat velit nisi sunt mollit sint amet.", filteredTodos[0].body);
    assertEquals("Incorrect second todo body", "Ad sit Lorem magna consectetur ut sit sunt aliquip. Ullamco quis quis commodo irure do nostrud anim sit commodo consequat.", filteredTodos[1].body);
    assertEquals("Incorrect third todo body", "Adipisicing anim ad sunt esse consectetur ipsum sint fugiat. Et minim nulla id amet.", filteredTodos[2].body);
    assertEquals("Incorrect last todo body", "Voluptate sit velit occaecat pariatur. Qui adipisicing ipsum incididunt laborum.", filteredTodos[299].body);
  }

}
