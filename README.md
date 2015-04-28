# ShopTill

[![CI Status](http://img.shields.io/travis/DayoOliyide/ShopTill.svg?style=flat)](https://travis-ci.org/DayoOliyide/ShopTill)

A simple program that calculates the price of a basket of shopping.

Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana".

Multiple items are present multiple times in the list, so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.

Items are priced as follows:

- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as "buy one get one free"
- Limes are 15p each, but are available in a "three for the price two" offer

## Pre-requisite
You need Java 8 installed on your system

## Installing

- Git clone this project i.e ``` git clone https://github.com/DayoOliyide/ShopTill.git ```
- Once cloned, at the root of the project build the application by running the following command

   For Linux users run
```
./gradlew clean jar
```

   For Windows users run
```
gradlew.bat clean jar
```
- You should now end up with a jar in the ./builds/lib directory

## To run Tests

   For Linux users run
```
./gradlew clean test
```

   For Windows users run
```
gradlew.bat clean test
```

## Usage

### Programmatically
- Create an instance of io.superstore.ShopTill.
- Create and set it's needed services. (See the contents of io.superstore.ShopTill.main() minus the start())
- Pass a List of Itemnames, via the io.superstore.ShopTill.calculateShoppingList() method, to get the total price.

### To Start via console
Type  ``` java -jar build/libs/ShopTill-1.0-SNAPSHOT.jar ``` to bring up the console

To pass the List of names via the console simply enter each item name with space in between.

*Just hit the return key to exit*

### To Enter a shopping list via console
`[item] [item]`

>\> Apple Apple Melon

>Total Price -> 120p

>\> Apple Banana Melon Melon Lime Lime Lime

>Total Price -> 135p

#### NOTE THE ONLY RECOGNIZED ITEMS -> Apple Banana Melon Lime


## License
Do whatever you want