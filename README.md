Care Bear Webserver
==================

Quick Start
---

 - clone the repo
 - `cd` into the directory
 - `mvn clean install`
 - `java -jar jars/server-1.0-jar-with-dependencies.jar -p 5000 -d /full/path/to/web/root`

Browse to `http://localhost:5000/` and you should see the directory listing for the static path. Browse to `http://localhost:5000/hello.jade` to se a simple example of rendering jade templates.

To run the ***Cob Spec FitNesse Tests***, [install Cob Spec](https://github.com/8thlight/cob_spec) and use your path to server-1.0-jar-with-dependencies.jar in the `SERVER_START_COMMAND`.
