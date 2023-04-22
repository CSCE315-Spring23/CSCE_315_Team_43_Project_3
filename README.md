# CSCE_315_Team_43_Project_3

## Setup Instructions

Maven is a Java software development build tool that must be installed.
Instructions are [here](https://maven.apache.org/install.html)

## Run Instructions

1. Navigate inside the `smook` directory

2. To build and run the backend locally, navigate to the `smook` folder and run `mvn spring-boot:run`, then navigate to `localhost:8080` to test api calls.
_Note: on a first run, do `mvn clean install` to ensure the dependencies are installed correctly._

3. To run the frontend, navigate to the `frontend/smook_app` folder and run `npm run dev` to run a live server in development mode.
All of your local changes will be automatically updated/reflected in the live server.

4. To deploy to the backend, navigate to the `smook` folder and run `mvn clean package`, then navigate to `src\main\appengine` and run `gcloud app deploy` while logged in (currently only linked to Connor's Google account, login with `gcloud auth login`)
