Spring-data multiple datasource demo
====================================

This project generates a .jar with a couple of Spring Data repositories and associated tests. 
I have had trouble getting this structure to work, so this project is intended to simplify 
things as much as possible to establish how best to do it.

The project is configured to *not* create the schema or tables. It assumes that:
* You have a MySQL instance. Modify the connection settings in `config/application.yml` as appropriate.
* You have two schemas in your database and tables, as can be set up using `db/schema.sql`.

It's built with Maven, so from the command line you can run a full build (including tests):

    mvn clean install
