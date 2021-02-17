# Airport Challenge in Java

Please also see my solutions to this challenge in [Ruby](https://github.com/AdamusBG/airport_challenge) and in [JavaScript](https://github.com/AdamusBG/airport_challenge-js).

```
        ______
        _\____\___
=  = ==(____MA____)
          \_____\___________________,-~~~~~~~`-.._
          /     o o o o o o o o o o o o o o o o  |\_
          `~-.__       __..----..__                  )
                `---~~\___________/------------`````
                =  ===(_________)

```

## User stories

As an air traffic controller  
So I can get passengers to a destination  
I want to instruct a plane to land at an airport  
  
As an air traffic controller  
So I can get passengers on the way to their destination  
I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport  
  
As an air traffic controller  
To ensure safety  
I want to prevent landing when the airport is full  
  
As the system designer  
So that the software can be used for many different airports  
I would like a default airport capacity that can be overridden as appropriate  
  
As an air traffic controller  
To ensure safety  
I want to prevent takeoff when weather is stormy  

As an air traffic controller  
To ensure safety  
I want to prevent landing when weather is stormy  

## Information

Edge cases considered:  
* Planes will not be able to take off from an airport that they are not currently in  
* Planes will not be able to take off when already in flight  
* Planes cannot land at an airport if they are already at an airport  
* Planes can neither land nor take off from an airport with bad weather (randomly chosen)

To test the program yourself, open a terminal and navigate to a directory that you would like this repository saved in and then follow the instructions below. Note that to run tests you will need to have Maven installed.  

```
$ git clone https://github.com/AdamusBG/airport_challenge-java.git
$ cd airport_challenge-java
$ mvn test
```

This will run 17 passing tests across three test classes, including a larger feature test, which prove that edge cases have been handled correctly.  
The feature test located at './src/test/Java/FeatureTest.java' tests the behaviour of the program with several airports and planes.  
