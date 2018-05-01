# EdataTTDemo
Technical test DEMO

The project consist on a maven webapp that exposes users and roles models through an API. 
You'll find model, resources and provider packages under the main project group id. 
The UserResorces class provides with the configuration to expose a RESTful API for the users model them roles.
Under the main package there's a DemoApplication class which is the JAX RS main application and takes care of registering resources and providers.
There's an authentication provider which provides with the implementation for the security annotations used in the UserResources (PermitAll and RolesAllowed). This provider check the basic auth header information against the persistence layer (JPA) managed by hibnernate in this case.
The persistence layer is implemented using JPA standard and configured with hibernate and an in-memory H2 databse through the standard persistence.xml file located under main/resources/META-INF folder.
And tha's mainly the Back-end part of the project, not a good set up for production deployment I hope it's fine for this demo.

An angular 5 based application have been integrated within this project performing the following tasks:
- create the app using angular cli under webapp foler (the contents of this folder will be copied to the package)
- configure the package.json to perform an angular production build after npm install
- configure angular-cli.json to build outside sources folder
- add an external maven plugin (frontend-maven-plugin) to install node and npm locally and perform npm install during maven package lifecycle.
- configure maved not to copy angular sources into the package
- configure the angular to be under app demo (ie http://localhost:8080/demo)
- also create a proxy config for developing purposes 

The angular app project is nothing complex:
It's the bootstrap 4 fw integrated, 2 main components (users list and users form) and a very simple auth and error handling mechanism.
The auth service just keeps the user info in memory when user logs in. It doesn't check the credentials against the server, insteand it "trust" the user and uses that information to authenticate when creating a user (using basic Auth, supported by the Back-end). The users list is free for all and it comes with pagination and sorting functionallity.

For the deployment ... just use maven to generate the package and publish under something like http://localhost:8080/demo


**REST API**
- Created a project from webapp jersey maven archetype 
- Configured as a JAX RS standard application
- Exposed User entity through RESTful web services using JSON format
- Mapped generic exceptions to provide JSON format response
- Created an in memory H2 database using Hibernate and following JPA standard
- Populate and seed database during servlet initialization
- Exposed user roles with requested format using Jackson annotations
- Added User service pagination and sorting functionality 
- Created a Basic security provider that supports RolesAllowed and PermitAll security annotations based on User and Role persistent models

**Front-end - API integration**
- created a simple angular app using angular cli 
- set the angular and npm configiguration to build on webapp path accordingly
- added a maven plugin to install node and npm locally on generate-resources phase

**Angular Front-end**
- set up the angular app layout with bootstrap 4
- created user list with sorting a pagination and a service connecting to API
- created a user form with angular reactive forms and a service sending new users to API
- basic error handling
- basic sercurity with non-checking login 
