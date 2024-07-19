

# MultiUserBoardWebSockets Redis

The project is a multiuser drawing board made up of two applications: one serves as the frontend and the other as the backend (API) built with Spring Boot. The backend now uses WebSockets to handle real-time communication between clients and the server. The frontend, developed in React JS, displays a board where users can draw circles by clicking. Being multiplayer, it can run in multiple browsers at once, enabling users to see changes in real-time.

### Features
+ **Draw**: You can draw consecutive circles using the mouse click.
+ **Secure Login**: User authentication with secure login.
+ **HTTPS and WSS**: Secure communication using HTTPS and WebSocket Secure (WSS).

## Getting Started
Download the project from 
[the repository.](https://github.com/Sebasvasquezz/MultiUserBoardWebSockets-Redis)

You can also clone the file using the following command.

```
git clone https://github.com/Sebasvasquezz/MultiUserBoardWebSockets-Redis.git
```

### Prerequisites

* [Maven](https://maven.apache.org/): Automate and standardize the life flow of software construction

* [Git](https://www.git-scm.com/): Decentralized Configuration Manager
* [Docker](https://docs.docker.com/):  Platform that allows developers to automate the deployment of applications inside lightweight, portable containers.

### Installing
1. **Maven**
    * Download Maven in http://maven.apache.org/download.html
    * You need to have Java installed (7 or 8)
    * Follow the instructions in http://maven.apache.org/download.html#Installation

2. **Git**
    * Download git in https://git-scm.com/download/win
    * Follow the instructions in https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

3. **Docker**
    - Install Docker by following the instructions [here](https://docs.docker.com/get-docker/)

### Installing

Once you have the cloned project in your repository. Follow the steps below to launch the program successfully.

#### Run Spring-boot

1. Open a terminal and enter the folder where you cloned the repository and enter the BoardSpring folder.

2. Use the following command to remove files generated in previous builds, compile the code, and package the project into a JAR or WAR file ready for distribution.
    ```
    mvn clean package
    ```


3. **Redis (via Docker)**
    - Run Redis in a Docker container:
    ```sh
    docker run --name some-redis -p 45000:6379 -d redis
    ```

    Verify the installation:
    ```sh
    docker ps
    ```
    Should return something like:
    ```sh
    CONTAINER ID   IMAGE     COMMAND                  CREATED         STATUS         PORTS                     NAMES
    e7e2f46385d1   redis     "docker-entrypoint.s…"   2 minutes ago   Up 2 minutes   0.0.0.0:45000->6379/tcp   some-redis
    ```
    ![Docker](/images/redis.png)
4. Now you can run the project using the following command.

    ```
    mvn spring-boot:run
    ```

5. Now open a browser and go to the following [link](https://localhost:8443/) to login:
![Login page](images/image.png)

6. After logging in you access the board to start drawing
![Execution](images/image-1.png)

7. You can also open a browser and go to the following [link](https://ec2-174-129-46-215.compute-1.amazonaws.com:8443/) to access the project on AWS:
![Execution in AWS](images/aws.png) 

## Running the tests

Unit tests were carried out through Junit to the Ticket Repository and Drawing ServiceController classes.

Once the repository is downloaded, open a command prompt and run the following command to run the unit tests:

```
mvn test
```
The execution:

![Execution of unit tests](images/tests.png)
1. Run the containers in Docker with the following commands.
    ```
     docker run --name some-redis -p 45000:6379 -d redis
    ```
    ```
     docker pull sonarqube
    ```
    ```
     docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
    ```
    ![Containers in Docker](images/docker.png)

2. Then to see the coverage with Jacoco and Sonar.
    + Log in to sonar localhost:9000 change the password, the default username and password is admin.

    + Enter the account options.

    + Once the sonar is running, you must generate a token

    + Run the following command:
    ```
    mvn verify sonar:sonar -D sonar.token=[GENERATED_TOKEN]
    ```
    Now you enter the following path to see the JaCoCo report:
    ```
     target/site/jacoco/index.html
    ``` 

    ![JaCoCo Report](images/jacoco.png)

    Now we enter the following link to see Sonar:

    http://localhost:9000/projects
 
    ![Sonar](images/sonar.png)

## Project Structure

### BackEnd Spring-boot

- **BBApplicationContextAware.java**: Provides access to the Spring application context for non-Spring-managed beans.
- **BBAppStarter.java**: Main class for starting the Spring Boot application.
- **BBConfigurator.java**: Configures the WebSocket server endpoint.
- **BBEndpoint.java**: WebSocket endpoint for handling mouse click data and broadcasting it to all connected clients.
- **DrawingServiceController.java**: REST controller for handling the status endpoint.
- **MvcConfig.java**: Configures view controllers for login and index pages.
- **WebSecurityConfig.java**: Configures Spring Security for user authentication and secure login.
- **RedisConfig.java**: Configuration class for setting up Redis as the data store for session management.

### FrontEnd React Js

#### Editor:

- Contains the main layout of the application including the BBCanvas component.

#### BBCanvas:

- Uses useRef to create references to the WebSocket connection and the p5 instance.
- Defines a sketch to configure and draw on the canvas.
- useEffect initializes the p5 instance and establishes the WebSocket connection when the component mounts.
- Handles incoming WebSocket messages to draw points on the canvas.
- drawPoint draws a circle on the canvas based on received coordinates.

#### WSBBChannel:

- Handles WebSocket connection logic, including opening the connection, receiving messages, handling errors, and sending messages.

#### BBServiceURL:

- Returns the WebSocket URL for the current host.

#### Templates:

- **index.html**: The main HTML template for the application.
- **login.html**: The HTML template for the login page.

#### Resources:

- **application.properties**: Configuration properties for the Spring Boot application.
- **keystore/baeldung.p12**: Keystore file for SSL configuration.

## Architectural Design

![Architectural Design](images/tablero.png)

### Data Flow

#### Initialization:

- Editor mounts BBCanvas.
- BBCanvas initializes the p5 instance and establishes the WebSocket connection when the component mounts.

#### Drawing:

- When the user clicks on the canvas, BBCanvas creates a drawing action and sends it to the server via WebSocket.
- The WebSocket server (BBEndpoint) broadcasts the action to all connected clients.
- BBCanvas immediately draws the action locally on the canvas.

#### Update:

- When BBCanvas receives a drawing action from the WebSocket server, it parses the action and draws the corresponding point on the canvas.
- This ensures that all connected clients see the drawing actions in real-time.

## Built with

* [Maven](https://maven.apache.org/) - Dependency management

## Authors

* **Juan Sebastian Vasquez Vega**  - [Sebasvasquezz](https://github.com/Sebasvasquezz)

## Date

July 18, 2024

## License

This project is licensed under the GNU License - see the [LICENSE.txt](LICENSE.txt) file for details.
