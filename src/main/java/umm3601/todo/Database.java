package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;

/**
 * A fake "database" of todos
 * <p>
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of todo data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `TodoController` to "query" the "database".
 */
public class Database {

  private Todo[] allTodos;

  public Database(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single todo specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;
    if (queryParams.containsKey("owner")) {
      filteredTodos = filterTodos(filteredTodos, x -> x.owner.equalsIgnoreCase(queryParams.get("owner")[0]));
    }
    if (queryParams.containsKey("category")) {
      filteredTodos = filterTodos(filteredTodos, x -> x.category.equalsIgnoreCase(queryParams.get("category")[0]));
    }
    if (queryParams.containsKey("status")) {
      filteredTodos = filterTodos(filteredTodos, x -> x.status == (queryParams.get("status")[0].equalsIgnoreCase("complete") || queryParams.get("status")[0].equalsIgnoreCase("true") ? true : false));
    }
    if (queryParams.containsKey("contains")) {
      filteredTodos = filterTodos(filteredTodos, x -> x.body.contains(queryParams.get("contains")[0]));
    }
    if (queryParams.containsKey("orderBy")) {
      switch (queryParams.get("orderBy")[0]) {
        case "owner":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.owner));
          break;
        case "category":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.category));
          break;
        case "body":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.body));
          break;
        case "status":
          filteredTodos = sortTodos(filteredTodos, Comparator.comparing(x -> x.status));
          break;
      }
    }
    if (queryParams.containsKey("limit")) {
      filteredTodos = Arrays.stream(filteredTodos).limit(Integer.parseInt(queryParams.get("limit")[0])).toArray(Todo[]::new);
    }
    return filteredTodos;
  }

  /**
   * Get an array of todos filtered by the given predicate
   *
   * @param todos the list of todos to filter
   * @param p     the predicate to filter by
   * @return an array of all the todos from the given list
   * filtered by the given predicate
   */
  public Todo[] filterTodos(Todo[] todos, Predicate<? super Todo> p) {
    return Arrays.stream(todos).filter(p).toArray(Todo[]::new);
  }

  /**
   * Get an array of todos sorted by the given comparator
   *
   * @param todos the list of todos to sort
   * @param c     the comparator to sort by
   * @return an array of all the todos from the given list
   * sorted by the given comparator
   */
  public Todo[] sortTodos(Todo[] todos, Comparator<? super Todo> c) {
    return Arrays.stream(todos).sorted(c).toArray(Todo[]::new);
  }

}
