Visualizer Images:

<img width="1440" alt="Screenshot 2024-03-23 at 9 37 29 AM" src="https://github.com/PranavN1234/nbadashboardjava/assets/44135759/7989d2b9-dcf5-47ce-b794-0e9ca8519d16">
<img width="1440" alt="Screenshot 2024-03-23 at 9 37 39 AM" src="https://github.com/PranavN1234/nbadashboardjava/assets/44135759/26e72ea9-b21a-4e33-bff7-24f778463ad2">
<img width="1440" alt="Screenshot 2024-03-23 at 9 37 49 AM" src="https://github.com/PranavN1234/nbadashboardjava/assets/44135759/98a81b83-79f5-4155-8ae7-ad98aea1a9a2">
<img width="1440" alt="Screenshot 2024-03-23 at 9 37 57 AM" src="https://github.com/PranavN1234/nbadashboardjava/assets/44135759/0c4f4efb-6367-4777-b096-2c681d4583c4">




The NBA dataset visualizer is a Java-powered project that has allowed the
visualization of match information for the past 20 years. The project works in three
stages:- First, using Threading and Batch in Java, We process two CSV files, and
using mappers, We populate the MySQL database with 26000 records that we
downloaded from Kaggle. Second, using JDBC and JPA, We map the object
classes with the MySQL database records and use SQL library in Java to fetch and
process data from the database. Lastly, once the data is fetched, we host the data in
endpoints using SpringBoot, which the frontend application can access.

**Three advanced Java concepts used:**

1) Using Spring Batch in Java to read CSV data, process and
populate the database with 26000 Match records for the last 20 years.

3) JPA and JDBC will map the object classes to the MySQL entities and use
prepared statements to fetch these records for further processing.

3) Using Spring Boot to host endpoints which the frontend can access

**Other Technologies:**
1) ReactJs to create a simple UI to visualize the data
2) Docker to containerize the application for easy use in other systems.

Dataset used:
For the data I used the kaggle NBA dataset which can be found here
https://www.kaggle.com/datasets/nathanlauga/nba-games?select=games_details.cs
v

**Workflow of the project:**

**Data Import:** NBA statistics in CSV format are imported using Java Batch, I have
added multithreading to efficiently perform the processing of the CSV file and
storing into the database

**Backend Processing:** JPA entities, 'Match' and 'Teams', are created and used to
store and manage the data in the database. Spring Boot is then utilized to host these
data on various endpoints.
**Frontend Development:** A React-based frontend is developed to interact with
these endpoints. This frontend enables users to visualize the NBA statistics through
an interactive interface, facilitating easy access and analysis of the data.

Architecture of project 
![image](https://github.com/PranavN1234/nbadashboardjava/assets/44135759/697f4d26-8067-446a-b31b-a084ef2a3c10)




