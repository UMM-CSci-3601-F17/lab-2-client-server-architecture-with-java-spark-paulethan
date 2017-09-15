package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GetTodoByIDFromDB {

  @Test
  public void get58895985a22c04e761776d54() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo todo = db.getTodo("58895985a22c04e761776d54");
    assertEquals("Incorrect owner","Blanche", todo.owner);
    assertEquals("Incorrect body", "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.", todo.body);
  }

  @Test
  public void get5889598593f949fbeea56296() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo todo = db.getTodo("5889598593f949fbeea56296");
    assertEquals("Incorrect owner", "Roberta", todo.owner);
    assertEquals("Incorrect body", "Nulla incididunt qui nulla nisi anim cillum labore anim laborum ea cupidatat enim non ut. Duis culpa nulla et Lorem sunt in.", todo.body);
  }
}
