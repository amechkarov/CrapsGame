## Description:
   This project represents a server which can be used to play the so called Craps game
   and perform minor statistical analysis on the outcomes of long term play.

## DOCKER
This project can be ran through docker as well with command "docker compose up --build" from the command prompt opened from the project's folder.

## Components
- ### GameController
  ##### Consists a single enpoint which supports both single round and multiple rounds play
  ##### Method POST : ".../api/craps/play"
   * Single round example body:
    </br> {
    "type":"CRAPS",
    "stake":100
}
   * Multiple rounds example body:
    </br>  {
    "type":"CRAPS",
    "stake":2132.22,
    "rounds":4
}
- ### Game model
  ##### This is the main object used and it has limitations in setters.
- ### GameService
  ##### 1. Controls the logic with the support of RandomProvider which has a **roll()**  method for random dice combination generation
  ##### 2. Based on the rounds provided the **outcome** of the game may consist different information
