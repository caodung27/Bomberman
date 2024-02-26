<div id="top" align="center">
<img src="res/images/DemoBackground.gif" alt="Banner">
</div>



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a>
    <img src="res/images/logo3.png" alt="Logo" width="700" height="200">
  </a>
<h3 align="center">BOMBERMAN GAME PROJECT</h3>
</div>


<!-- ABOUT THE PROJECT -->

## I. Introduction<a name="Introduction"></a>:

### Team Members:

| Order |         Name          |     ID      |            Email            |                       Github account                                                     |
| :---: | :-------------------: | :---------: | :-------------------------: | :---------------------------------------------------------: |
|   1   | Cao Xuan Dung         | 21020290    |   21020290@vnu.edu.vn       |          [caodung27](https://github.com/caodung27)          | 
    

<!-- Game -->
<br/>

## II. Game<a name="Game"></a>:
### Description

- ![](res/sprites/player_down.png) *Bomber* is the main character of the game. Bomber can move in 4 directions left / right / up / down according to the control of the player. 

- ![](res/sprites/bomb.png) *Bomb* is the object that Bomber will place. Once placed, Bomber cannot move into Bomb position. However, as soon as the Bomber has placed and activated the Bomb at his position, the Bomber can once go from the location where the Bomb is placed to the next position. After activating for 2.5s, Bomb will explode by itself.

- ![](res/sprites/wall.png) *Wall* is a fixed object, cannot be destroyed by Bomb and cannot be placed on Bomb, Bomber cannot move on this object.

- ![](res/sprites/brick.png) *Brick* is an object that does not allow Bomb to be placed but can be destroyed by Bombs placed nearby. Bomber cannot move into Brick until it is destroyed.

- ![](res/sprites/portal.png) *Portal* is the object hidden behind a Brick object. When that Brick is destroyed, the Portal will appear and if all Enemys have been destroyed the player can move to another Level by moving into the Portal's location.

- ![](res/sprites/grass.png)  *Grass* is an object that does allow Bomb to the placed, Bomber can move on this object.

*Item* is also hidden behind the Brick and only shows up when the Brick is destroyed. Bomber can use Item by moving into Item's position. Information about the function of the Item is listed as follows:
- ![](res/sprites/powerup_speed.png) *SpeedItem* This item will increase Bomber's movement speed.

- ![](res/sprites/powerup_flames.png) *FlameItem* This item increases the Bomb's range when it explodes.

- ![](res/sprites/powerup_bombs.png) *BombItem* This item increases the number of Bombs that can be placed by one.

- ![](res/sprites/powerup_flamepass.png) *BombLimitItem* This item increases the number of limited bombs.

- ![](res/sprites/powerup_detonator.png) *TimeLimitItem* This item increases the number of limited times.

*Enemy* are the objects that the Bomber must destroy in order to pass the Level. Enemy can move randomly or chase Bomber on its own depending on the type of Enemy. Types of Enemy will be described in detail below:

- ![](res/sprites/balloom_dead.png) *Ballom* is the simplest Enemy, moving randomly at a slow speed.

- ![](res/sprites/oneal_dead.png) *Oneal* knows how to chase Bomber when approaching.

- ![](res/sprites/kondoria_dead.png) *Kondoria* is the Enemy that can eat bombs and move around the map without being blocked by anything.

- ![](res/sprites/minvo_dead.png) *Minvo* is the most complex enemy that can dodge bombs and blocks. It can also knows how to chase Bomber when approaching.
<br/>

## III. UML Class Diagram<a name="UML-class-diagram"></a>:

<div>
    <h3>1. entities</h3>
    <div align="center">
        <img src="res/UML/entities.png" alt="">
    </div>
    <h3>2. blocks</h3>
    <div align="center">
        <img src="res/UML/blocks.png" alt="">
    </div>
    <h3>3. bomberman</h3>
    <div align="center">
        <img src="res/UML/bomberman.png" alt="">
    </div>
    <h3>4. enemies</h3>
    <div align="center">
        <img src="res/UML/enemies.png" alt="">
    </div>
    <h3>5. items</h3>
    <div align="center">
        <img src="res/UML/items.png" alt="">
    </div>
    <h3>6. graphics</h3>
    <div align="center">
        <img src="res/UML/graphics.png" alt="">
    </div>
	<h3>7. level</h3>
    <div align="center">
        <img src="res/UML/level.png" alt="">
    </div>
	<h3>8. Menu </h3>
    <div align="center">
        <img src="res/UML/menu.png" alt="">
    </div>
	<h3>9. pathFinding</h3>
    <div align="center">
        <img src="res/UML/pathFinding.png" alt="">
    </div>
	<h3>10. sound</h3>
    <div align="center">
        <img src="res/UML/sound.png" alt="">
    </div>
</div>

<br />

## IV. Summation<a name="Summation"></a>:

<div style = "text-align: justify">
We would like to express our gratitude to our teacher for giving us the opportunity to participate in this project
and put what we have learned in theory into practice. This project took a lot of work and time, but it was well worth it 
for all of us. We learned more about interface technologies to build an application that works with our project. 
We will make sure that all that we have learned is not wasted and we can master it better now.
</div>

<br/>
	<div align="center">
        <img src="res/images/thanks.gif">
    </div>
<br/>


<p align="right">(<a href="#top">Back to top</a>)</p>

