Song Analyzer
=============
An application producing reports containing statistics for given songs.


## Available statistics
- Total track time,
- Number of distinct collections,
- Average price of a track.

## Installation
To install the project one needs the Maven build tool. To install the project run:
```
mvn install
```

## Running
To run the application after it has been successfully installed run:
```
java -jar target\CsCodingChallenge-1.0.jar itunes.json
```
`itunes.json` contains the sample data downloaded from iTunes service. In the future there will be now need to use the file as all the data will be downloaded from iTunes directly by the application.

## Data source
iTunes will be used as a main source for the songs' statistics. To provide a simple demo of the application an `itunes.json` file was attached (getting data directly from iTunes is not yet supported by the application).
