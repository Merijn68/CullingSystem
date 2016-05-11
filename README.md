# CullingSystem
Overlap2d Frustum Culling System

This is a trial to add Frustrum Culling to Overlap2d for Libgdx. 

I noticed that with more objects (1000+) on android I had a noticable performance drop. Now there are many things you could do to increase performance. One of the first things to think about is Frustrum Culling. (https://en.wikipedia.org/wiki/Viewing_frustum). 

As we are working in 2D things are actually a lot simpler. I am using a bounding box aproach. So there is a 2 step process: first identify the bounding boxes for the objects. 2nd step: see what is in the camera's viewport.

Working with Overlap2d I found that there is a complication as the users can use composites and even stack multiple levels of composites on top of each other. In order to know what will be the bounding box we therefor need to calculate if an entity is in a composite, and if that composite might have rotated, scaled etc. All do-able but offcouse that takes processing power as well. And the whole exercise was to gain performance.

Ok. This is free - for you to use in any form you like. I know the code is still not fully optimized. I would appreciate it if you would let me know if you like and / or would use this. Offcourse any improvements you see would also be welcome :)

I did not do anything with Spine animations (Just don't have that). I did add support for Spriter. 

I did not have time to create a nice demo project for this. Maybe I can add that later if that is something you like. 
If you have a Overlap2D project in Libgdx and would like to add this just add a boundingboxComponent to all objects, and initialize both boundingbox and cullingSystems:

	sl.engine.addSystem(new BoundingBoxSystem());
	sl.engine.addSystem(new CullingSystem());
			
	@SuppressWarnings("unchecked")
	ImmutableArray<Entity> dimensionEntities = sl.engine.getEntitiesFor(Family.all(DimensionsComponent.class).get());		
	for (Entity entity : dimensionEntities) {
				entity.add(new BoundingBoxComponent());			
	}

In your main render routine you could debugRender them to see if the boundingboxes fit:


	void debugRender() {
			ShapeRenderer shapeRenderer = Commons.getGame().getDebugRenderer();
			shapeRenderer.setProjectionMatrix( Commons.getGame().getViewport().getCamera().combined);		
			shapeRenderer.begin(ShapeType.Line);		
			shapeRenderer.setColor(Color.RED);		
			
			@SuppressWarnings("unchecked")
			Family bounding = Family.all(BoundingBoxComponent.class).get();
			ImmutableArray<Entity> entities = sl.engine.getEntitiesFor(bounding);						
			
			for (Entity entity : entities) {
				BoundingBoxComponent boundingbox = Mappers.boundingBox.get(entity);
					Rectangle rect = boundingbox.getBoundingRect();											
					shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
					for (int i = 0; i<4; i++) 
					{				
						shapeRenderer.rect(boundingbox.points[i].x-2, boundingbox.points[i].y-2, 4, 4);	
					}				
				}				
			shapeRenderer.setColor(Color.BLUE);
			OrthographicCamera camera = (OrthographicCamera) Commons.getGame().getViewport().getCamera();
			shapeRenderer.rect(camera.position.x - ((camera.viewportWidth * camera.zoom ) / 4 ),
					camera.position.y - ((camera.viewportHeight * camera.zoom ) / 4 ),
					(camera.viewportWidth * camera.zoom) /2 ,
					(camera.viewportHeight * camera.zoom) / 2 ) ;
					
												
			shapeRenderer.end();
			
			
		}
	
	

Happy coding,

Merijn
