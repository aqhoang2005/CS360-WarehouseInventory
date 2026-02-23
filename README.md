# cs360-WarehouseInventory
 Warehouse inventory app for CS360 Mobile Architect and Programming

The WarehouseInventory App allows users to manage a list of warehouse inventory items in the palm of their hand. It allows users to add items with a name, quantity, and description, as well as editing or even delete data entries. The app has a simple UI that allows users with little to no training to be able to pick up and learn the app with ease. The app also features log ins and databases for user credentials and the warehouse inventory. The needs of the app fulfill users in environments that require tracking quantities of items at a time, such as a shipping company, a dockyard, or even a retail store and its merchandise. 

/- Questions for Module 8 -\
What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
-The application required a login screen, a main inventory display screen, an add/edit item screen, and a user profile screen with logout functionality. The UI was designed with simple and concise layout structure, ensuring intuitive navigation. Input fields were organized logically, and navigation between screens followed a predictable flow. The designs were successful because they maintained clarity, allowing users to complete tasks efficiently without confusion.

How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?
-I approached coding by building the application feature by feature. I began with the UI interactions, database structure, CRUD operations, then implemented user authentication and permissions. Separating the inventory's database from the user authentication development made the development smoother, as both functions could work side by side instead of conflicting with each other. Tests on each feature were immediately done after implementation to reduce debugging complexity and to fix any bugs or errors. These techniques can be applied in future projects by continuing to prioritize structured development, code organization, and to improve reliability and scalability.

How did you test to ensure your code was functional? Why is this process important, and what did it reveal?
-I tested the code through an Android emulator within Android Studio. I ran tests after I implemented every feature, including CRUD operations and user login screens. This process is important because instead of developing the entire code and running it only for it to fail, it's better to develop one feature at a time and catch bugs and errors as you go so you don't spend a lot more time than needed to iron out bugs. 

Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?
-I initially started with the log in and user authentication and then moved onto the main warehouse database. After running into an error where the app would not even launch, I opted to switch the starting screen to the main list of the app instead of the log in screens, as that was the main core of the project and not the user authentication. Prioritizing what to work on and what to keep separate allowed me to finish implementing features in the allotted time given, including the testing with every implemented feature.

In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
-I have taken previous classes with developing web applications with databases and CRUD operations, and I feel the list itself is what I consider successful in demonstrating my knowledge in experience. Using databases softwares such as Firebase or MySQLWorkbench proved helpful in understanding how to utilize database tools with this app. 
