# GoEuro CLI [![Build Status](https://travis-ci.org/jordanabderrachid/go-euro.svg?branch=master)](https://travis-ci.org/jordanabderrachid/go-euro)

**DISCLAIMER: this is not a [go-euro](http://www.goeuro.com/) official software**

The purpose of this project is to build a CLI to call the go-euro search API. Given a city name (eg: "Los Angeles") this
software calls the go-euro API to get position suggestions. The results are stored in the CSV format on a file. The name
of the file is set by default to _out.csv_.

### How to build

This project requires [Maven](https://maven.apache.org/) 3.x and [java](https://java.com/en) 1.8+

```bash
$ git clone https://github.com/jordanabderrachid/go-euro.git
$ cd ./go-euro
$ mvn package
```

### How to run

Build the project or download the _jar_ of the [latest](https://github.com/jordanabderrachid/go-euro/releases/latest) release.

```bash
$ java -jar go-euro-<version>-jar-with-dependencies.jar Berlin
$ cat ./out.csv
  _id,name,type,latitude,longitude
  376217,Berlin,location,52.524370,13.410530
  448103,Berlingo,location,45.502980,10.043660
  425332,Berlingerode,location,51.457750,10.238400
  425326,Bernau bei Berlin,location,52.679820,13.587080
  314826,Berlin Tegel,airport,52.554800,13.289030
  314827,Berlin Schönefeld,airport,52.388726,13.518087
  334196,Berlin Hbf,station,52.525589,13.369548
  333977,Berlin Ostbahnhof,station,52.510972,13.434567
```

### Ideas of improvement

- Add CLI parameters
  - `-f --file <filename>` to specify the output file name
  - `-c --city <city>` to specify the name of the queried city
  - `-t --time-out <timeout in s>` to specify the timeout value
  - `-v --verbose` to run the CLI in verbose mode
