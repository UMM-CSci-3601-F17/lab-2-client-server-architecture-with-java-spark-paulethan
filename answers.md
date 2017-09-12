# Question Answers

* What role is Gradle playing in the project, and what is the purpose of `build.gradle`?
  - Gradle builds, tests, and runs the project as defined in `build.gradle`. `build.gradle` defines what type of project it is and what to use to build and test it.
* What is the purpose of Travis-CI?
  - Travis-CI builds and tests the project every time new commits are pushed to GitHub.
* What is the purpose of `.gitignore`?
  - `.gitignore` tells git to ignore specific files, like temporary build or environment specific files.
* Explain what a _route_ is.
  - A *route* tells Spark what to return when a specific URL is requested.
* What is the purpose of `umm3601.Server` class?
  - It is the main class for the server that lists all the routes.
* What is the purpose of the `umm3601.user.UserController` class?
  - It handles request for user info, returning info on either a single user or all users.
* Explain what happens when a user accesses each of the following URLs:
  - The page `users`
    - It returns a basic HTML page with some javascript that allows you to see all users or users with a specific age.
  - The page `api/users`
    - This returns the json data of all users
  - The page `api/users?age=25`
    - This returns the json data of all users who's ages are 25
  - The page `api/users/588935f5de613130e931ffd5`
    - This returns json data on that specific user
* What are the contents of the `public` folder? What is the purpose of each of the HTML files there?
  - The public folder contains all HTML, JS, and CSS resources served as the frontend of the application.
  - `about.html` is a basic about page
  - `index.html` is the main starting page
  - `users.html` provides a way to get user data for all users or filter by age using `users.js`
* Describe what happens when you filter users by age in the client? What is read from the web page, and what request is sent to the server? What is received, and how/where is it displayed?
  - In `users.html`, there is an event listener setup for the "get users by age" button:
  ```js
  element = document.getElementById('getUsersByAge');
  element.addEventListener("click", getAllUsersByAge, true);
  ```
  - When that button is clicked, the `getAllYsersByAge` function in `users.js` is called. That function uses `document.getElementById("age").value` to get the age value from the textbox and adds it to the end of `"/api/users?age="`. It then requests that URL and inserts it into the page element `jsonDump`. 
* Where is the client-side JavaScript defined? Name the file(s) in which it is used.
  - The javascript file is `javascript/users.js` and in `users.html` it is imported with 
  ```html
  <script type="text/javascript" src="javascript/users.js"> </script>
  ```
  - 
