# EdataTTDemo
Technical test DEMO

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
