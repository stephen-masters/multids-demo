Spring-data multiple datasource demo
====================================

This project generates a .jar with a couple of Spring Data repositories and associated tests. 
I have had trouble getting this structure to work, so this project is intended to simplify 
things as much as possible to establish how best to do it.

It's built with Maven, so from the command line you can run a full build (including tests):

    mvn clean package
    
The configuration of the data sources is in src/test/resources/application.yml  Those settings may be overridden by
an application.yml (or application-test.yml) in the root folder.  Doing this override lets you run with alternate
settings without recompiling the module.

    
    

