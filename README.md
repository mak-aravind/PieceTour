# Piece Tour

### Problem Statement:

The assignment is to write a program that finds at least one
tour for a chess piece on a 10-by-10 chessboard. A tour is
a path for a piece to visit all tiles on the board, following a
set of rules for movement. Assume the piece can start in
any tile.
The four rules of movement for the piece are:
a) The piece can move 3 spaces either North, East, South,
or West.
b) The piece can move 2 spaces diagonally: Northeast,
Southeast, Southwest, or Northwest.
c) Each space can only be visited once.

#### Solution using Warnsdorff's Algorithm: 
The problem is solved using H C Warnsdorff's technique which does the following:

1. Start from any tile and mark it as visited.
2. To decide the next tile in path, look at all possible unmarked tiles based on moving rules.
3. Rank each possibility by the number of next moves from that tile.
4. Move to any tile with the lowest rank.
5. Add chosen tile to knight's tour path (i.e marked) and repeat the process from last chosen tile.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

All dependencies are mentioned in build.gradle

```
Install java 8
Install gradle 5.5
```

### Installing

To build the project

```
gradle clean build
```

And to run the application

```
gradle run
```
Sample Output
```
mak@pop-os:~/MEGAsync/mak/project/PieceTour$ gradle run

> Task :run
Type grid size (Ex: 100 will be of 10 * 10): 
Run in Debug Mode (Y or N): 
Furnishing Chess Board before tour: 
+-+-+-+-+-+-+-+-+-+-+
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
+-+-+-+-+-+-+-+-+-+-+
The size of the chessboard grid
ROW SIZE:10 COLUMN SIZE:10
<RESULT>Furnishing Chess Board after tour: 
+--+--+--+--+--+--+---+--+--+--+
|60|26|15|65|25|14| 66|24|13|67|
| 5|95|58| 4|94|89|  3|73|88| 2|
|16|36|61|91|52|64| 92|68|50|23|
|59|27|41|98|57|72| 99|56|12|74|
| 6|96|53|35|93|90| 51|76|87| 1|
|17|37|62|84|42|63| 85|69|49|22|
|45|28|40|97|54|71|100|55|11|75|
| 7|81|43|34|80|83| 33|77|86|32|
|18|38|46|19|39|47| 20|70|48|21|
|44|29| 8|82|30| 9| 79|31|10|78|
+--+--+--+--+--+--+---+--+--+--+
Number of Positions visited 100
started with Row:4~~Column: 9

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date

```
## Running the tests

To run the tests

```
gradle test
```

The test report will be available in <project-home>/build/reports/tests/test/index.html

## Development

Execute below command to generate a idea Intellij project:

```
gradle idea
```


## Deployment

Execute below command to get an executable jar file under project */build/libs* folder

```
gradle shadowJar
```

Execute below command to execute as java application without scala dependencies from */build/libs* location 

```
java -jar TrueCaller-1.0-all.jar
```

Sample output

```
mak@pop-os:~/MEGAsync/mak/project/PieceTour/build/libs$ java -jar TrueCaller-1.0-all.jar 
Type grid size (Ex: 100 will be of 10 * 10): 
100
Run in Debug Mode (Y or N): 
n
Furnishing Chess Board before tour: 
+-+-+-+-+-+-+-+-+-+-+
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
|0|0|0|0|0|0|0|0|0|0|
+-+-+-+-+-+-+-+-+-+-+
The size of the chessboard grid
ROW SIZE:10 COLUMN SIZE:10
<RESULT>Furnishing Chess Board after tour: 
+--+--+--+--+--+--+--+--+--+---+
| 2|32|39|17|33|38|80|34|37| 81|
|29|19| 4|30|69| 5|53|68| 6| 54|
|40|16| 1|41|91|78|36|94|79| 35|
| 3|31|72|18|52|73|83|55|60| 82|
|28|20|50|75|70|95|90|67| 7| 99|
|43|15|26|42|92|77|59|93|88| 56|
|24|46|71|85|51|74|84|98|61| 11|
|27|21|49|76|65|96|89|66| 8|100|
|44|14|25|45|13|86|58|12|87| 57|
|23|47|64|22|48|63| 9|97|62| 10|
+--+--+--+--+--+--+--+--+--+---+
Number of Positions visited 100
started with Row:2~~Column: 2

```
## Built With

* [Gradle](https://gradle.org/) - Dependency Management