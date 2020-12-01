# weather

A simple weather REST API with static information.

Maven, Spring boot, Git

Port: 8087
Database: http://localhost:8085/h2-console/ username: "sa" password: ""

1) http://localhost:8087/ -> home, a simple message is displaying

2) http://localhost:8087/cities -> a map with all cities, and one filter by region is display
Example 1: click on Aveiro 
  -> http://localhost:8087/city?name=Aveiro -> will display the name of the city, the atual temperature in Celsius, a table with the information to today weather in that city, and a chart of temperature variation throughout the day.
Example 2: click on Filter -> will show four options: "North", "Center", "South" and "Isles"
click on South -> will show all the Sotuh's cities, for the purpose that the user choose the city that he wants to know the weather for today.
Click on "Beja" it will dispay the equal information of the example one -> http://localhost:8087/city?name=Beja

3) http://localhost:8087/today -> show all the cities with the weather associate for today

4) http://localhost:8087/nextdays -> a form is display for the user choose which city and how many days he wants to see, with the max of 5 days
Example: city: Aveiro , Days: 5
 -> http://localhost:8087/next?city=Aveiro&days=5 -> it will display a table with the weather for that city, in this example Aveiro, to 5 days
