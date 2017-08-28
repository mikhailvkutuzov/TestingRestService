# TestingRestService
This project intended to give a rest api to TestSuits written on top of TF library. This is a pure abstract project implementing an abstract fyunctionality of rest service and gives a web access to results of a particular TestSuit by its name. Results are implemented in json. 

To use it properly make your on project upon TF library then make a new maven project combining TestingRestService 
and that project together.

How to make your own project upon TF https://github.com/mikhailvkutuzov/TF

How to make a project combining TestingRestService and your functional testing TF project:

0. clone TestingRestService on your machine and make mvn install

1. make a maven project like this:

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>[THE PROJECT GROUP ID]</groupId>
    <artifactId>[THE PROJECT NAME]</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>[YOUR FUNCTIONAL PROJECT PACKAGE]</groupId>
            <artifactId>[YOUR FUNCTIONAL PROJECT WRITTEN UPON TF]</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.testing.service</groupId>
            <artifactId>testing-service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.5.1</version>
                <inherited>true</inherited>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.2.1</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <arguments>
                        <argument>urlToBeTested=[AN URL OF A PRJECT TO BE TESTED (https://google.com if you want to test google =))]</argument>
                        <argument>port=[A PORT YOU WANT THIS REST SERVICE BE LISTENING IN]</argument>
                    </arguments>
                    <classpathScope>test</classpathScope>
                    <mainClass>com.testing.service.ServiceManager</mainClass>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <useFile>false</useFile>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
