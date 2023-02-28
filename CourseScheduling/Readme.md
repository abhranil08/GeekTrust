# LLD - Course Scheduling 
Problem statement : The head of the Learning management system (LMS) has hired you as a consultant. The LMS team has the goal of upskilling the employees with the latest topics via courses. You need to help build a system to schedule and manage the courses. 

Current Geektrust score - 98. This LLD design earned all the badges - READABILITY, MAINTAINABILITY, FUNCTIONAL / OOP MODELLING, TESTS, CORRECTNESS, BUILD.

<img width="1240" alt="Screenshot 2023-02-28 at 7 39 08 AM" src="https://user-images.githubusercontent.com/17013083/221734861-568b7d1c-2236-4c9a-8739-d16e7c81439f.png">


# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.  Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.geektrust.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.com)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)

# Things to DO :
1. Design and skeleton of classes - Done
2. Implement model classes - Done
3. Implement service classes - Done
4. Implement error classes - proper handling for different actions - Done
5. Remove all redundant maps for scalability - Done
6. Add all tests : >70% coverage, currently its 76% - Done
7. Input error handling - validate inputs differently for 4 services - Done
8. Remove code duplication : Currently found 2 instances.
