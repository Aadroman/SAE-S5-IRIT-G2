# Query-tree-modules

Automatic multi-languages (SQL, MongoDB, ...) query rewriter for polystore dataset.

## Description
[description]
## Getting started

### 1. Clone repository

```bash
cd /project_folder
git clone https://gitlab.irit.fr/sig/these-lea-el-ahdab/query-tree-modules.git
```
After the previous step a popup window will ask for your git username and password.

`Note : If you cannot succeed to connect with your password you can try used a gitlab personal access token instead`

[How Generate a gitlab token ?](https://docs.gitlab.com/ee/user/profile/personal_access_tokens.html#create-a-personal-access-token)

### 2. Requirements

`Note : All the tools listed below are indicated with the versions used to develop the project.
To be sure you can run the project, use at least these versions or newer.`

- Java 20 : https://www.oracle.com/fr/java/technologies/downloads/#jdk20-windows
- Select one :
  - IntelliJ IDE 2023 (contain maven): https://www.jetbrains.com/fr-fr/idea/
  - NetBeans 18 (contain maven): https://netbeans.apache.org/
  - Maven 3.9.2 https://maven.apache.org/download.cgi

### 3. Installation

**Note 1 :** Thanks to maven dependencies management you can find all dependencies in [pom.xml](./pom.xml)

**Note 2 :** Program can produce algebraic tree in output but that require unicode characters to display it.
Have a look to the following link to enable all unicode characters in windows : https://itsontreinamentos.com/2022/03/31/comment-changer-codage-des-caracteres-windows-11/

#### 3.1 With IntelliJ IDEA

- Open /cloned_project_folder in IntelliJ
- Open the maven tool window (top right of the IDE) and in **Lifecycle** click on **install**

You can now run the application using IntelliJ run or use the polyglot.jar generated under /target folder with the following cmd :
```shell
java -jar .\target\polyglot.jar --help
```

### 3.2 With NetBeans 
- Open query-tree-modules project under /cloned_project_folder in NetBeans
- Under /Project_Files right click on pom.xml and click on _Run maven > Goals..._
- In Goals field type '**install**' and then **OK**

You can now run the application using NetBeans run or use the polyglot.jar generated under /target folder with the following cmd :
```shell
java -jar .\target\polyglot.jar --help
```

### 3.3 Without IDE
```bash
cd /cloned_project_folder
mvn install
java -jar .\target\polyglot.jar --help
```

## Project Structure
[Bac_A_Sable](./Bac_A_Sable) : Previous module 1 version 

[doc](./doc) : Folder containing uml diagrams (see [doc README](./doc/README.md))

[Resources](./Resources) : Files containing database data (see [resources README](./Resources/README.md))

[src](./src) : Java project (see [next section](#java-project))

## Java project

### Folder structure

You can choose between 2 main programs to run the app :
- **fr.irit.PolyglotCli :** is a CLI version of the app where you can put query in parameter
- **fr.irit.App :** is for development use, you have to put the query in the code and then run the app

```txt
└──src
    ├── main
    |   ├── antlr4
    |   └── java
    |       ├── fr.irit.App (Main)
    |       ├── fr.irit.PolyglotCli (Main)
    |       ├── fr.irit.algebraictree (Algebraic tree classes)
    |       ├── fr.irit.commands (CLI commands classes)
    |       ├── fr.irit.module1 (module 1 classes)
    |       ├── fr.irit.module2 (module 2 classes)
    |       └── fr.irit.module3 (modules 3 classes)
    └── test
```

### Tests
Unit tests are execute using the JUnit framework under the folder [/src/test](./src/test)

They are automatically run when you compile the app with maven (mvn install or mvn compile).

### Command Line Interface (CLI)

Main file **fr.irit.PolyglotCli** allow launch the application as a command line interface.

For create the CLI we use a tool named [Picocli](https://picocli.info/)

All CLI commands are defined under the folder **fr.irit.commands**

```
Usage: java -jar polyglot.jar [-h] [COMMAND]
Commands:
- run (run a sql or mongo query)
  ├── Usage: java -jar polyglot.jar run <query> [-gt] [-mt] [-tt] [--time] [--help]
  ├── Parameters:
  |   <query>               Database query (ex: SELECT * FROM Customers)
  └── Options:
      -gt, --globalTree     Print global tree
      -h, --help            display this help message
      -mt, --multistoreTree Print multi-store tree
      --target-db-name=<TARGET_DB_NAME>
                            Target database name (default : DB1)
      --target-db-type=<TARGET_DB_TYPE>
                            Target database type (default : RELATIONAL)
      --time                Show execution time
      -tt, --transferTree   Print transfer tree
 
- unified-view (Display polystore unified view)
  ├── Usage: java -jar polyglot.jar unified-view <dbType> [--help] [-pimpl]
  ├── Parameters:
  |   <dbType>              Database type (relational or document)
  └── Options:
      -h, --help   display this help message
      -pimpl, --physical-implementation
               show physical implementation in addition of unified view
    
```

### ANTLR
[ANTLR](https://antlr.org) is a parser generator tool that we use for read and recognize DB Queries (sql for the moment)
to translate it into a structured tree.

<u>How to use it ?</u>

Write a .g4 grammar file (lexer & parser rules) under [/src/main/antlr4](./src/main/antlr4)
and generate parser with following cmd :
```shell
mvn generate-sources
```
Copy newly generated antlr folder (here : [./target/generated-sources/antlr4](./target/generated-sources/antlr4))
to antlr4 folder in java folder
(replace : [./src/main/java/fr/irit/module1/antlr4](./src/main/java/fr/irit/module1/antlr4))

## Support
## Roadmap
## Contributing
## Authors and acknowledgment
## License
## Project status
