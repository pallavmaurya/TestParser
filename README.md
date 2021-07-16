# Text Parser using Java 8

This is an example implementation of a basic text parser as per this [assignment](assignment.md)

## prerequisites:

JDK 1.8 or above

Maven 3.6.3 or above

## How to compile and run the project:

_Execute tests:_

mvn test

_Compile the project:_

mvn compiler:compile

_Run the application with **4MB max heap memory** from commandline in target/classes folder (or provide classpath)
providing absolute path of the input file :_

java -Xmx4m com.example.Main <filepath>

_Run the project using maven providing absolute path of the input file :_

mvn exec:java -Dexec.mainClass="com.example.Parser" -Dexec.args=<filepath> -Dexec.classpathScope=runtime

## Assumptions:

`'!', '.' ,'?'` are sentence delimiters as per sample.in and sample.csv

`'.'` will not be considered as a sentence delimiter when part of honorifics i.e. `"Mr.", 'Ms.' and 'Dr.'`

There is an error in sample.csv that first sentence is missing (Refer query 1)

Case-insensitive sort order is to be used i.e., the default sort in natural order will be
`Chinese,I,a,because,couldn't,isn't,mother,my,perhaps,tongue,understand,word` instead it is
`a,because,Chinese,couldn't,I,isn't,mother,my,perhaps,tongue,understand,word` in the sample.csv.

## Query 1:

Below sentence is not parsed into sample.csv

```
Mr. and
Ms. Smith
met Dr. Jekyll
outside.
```

However, below sentence is parsed

```
Nordea Markets is the 
leading international 
capital markets operator 
and investment banking partner 
in the Nordic and 
Baltic Sea regions.
```

The second sentence being parsed signifies that we are not omitting lines without a sentence delimiter. Hence, it is not
clear why the first sentence is not part of sample.csv.
