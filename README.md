# CullingSystem
Overlap2d Frustum Culling System

This is a trial to add Frustrum Culling to Overlap2d for Libgdx. 

I noticed that with more objects (1000+) on android I had a noticable performance drop. Now there are many things you could do to increase performance. One of the first things to think about is Frustrum Culling. (https://en.wikipedia.org/wiki/Viewing_frustum). 

As we are working in 2D things are actually a lot simpler. I am using a bounding box aproach. So there is a 2 step process: first identify the bounding boxes for the objects. 2nd step: see what is in the camera's viewport.

Working with Overlap2d I found that there is a complication as the users can use composites and even stack multiple levels of composites on top of each other. In order to know what will be the bounding box we therefor need to calculate if an entity is in a composite, and if that composite might have rotated, scaled etc. All do-able but offcouse that takes processing power as well. And the whole exercise was to gain performance.

Ok. This is free - for you to use in any form you like. I know the code is still not fully optimized. I would appreciate it if you would let me know if you like and / or would use this. Offcourse any improvements you see would also be welcome :)

I did not do anything with Spine animations (Just don't have that). I did add support for Spriter. 

Happy coding,

Merijn
