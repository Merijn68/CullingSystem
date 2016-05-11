package com.mvm.lost.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.mvm.lost.Commons;
import com.mvm.lost.Mappers;
import com.mvm.lost.components.BoundingBoxComponent;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.NodeComponent;
import com.uwsoft.editor.renderer.components.ViewPortComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;

public class CullingSystem extends IteratingSystem{

	
	Rectangle view = new Rectangle();
	OrthographicCamera camera;
	
	@SuppressWarnings("unchecked")
	public CullingSystem() {			
		super(Family.all(ViewPortComponent.class).get());							
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		ViewPortComponent viewPort = Mappers.view.get(entity);
		this.camera = (OrthographicCamera) viewPort.viewPort.getCamera();
		view.width = ( camera.viewportWidth * camera.zoom )  ;
		view.height = ( camera.viewportHeight * camera.zoom )  ;		
		view.x = camera.position.x - ( view.width / 2 );
		view.y = camera.position.y - ( view.height / 2 );
		
		NodeComponent node = Mappers.node.get(entity);	
		Entity[] children = node.children.begin();				
		for (int i = 0, n = node.children.size; i < n; i++) {
			Entity child = children[i];
			cull(child);				
		}
		node.children.end();		
	}
	
		
	void cull(Entity entity)
	{
		
		BoundingBoxComponent b = Mappers.boundingBox.get(entity);
		if (b==null) return;									
		PhysicsBodyComponent p = Mappers.physics.get(entity);
		if (p!= null)
			if (p.bodyType > 1) return; 
										
		MainItemComponent m = Mappers.main.get(entity);	
		
		if (view.overlaps(b.rectangle)) {
			Commons.getGame().show++;
			m.visible = true;		
		}
		else {									
			m.visible = false;						
		}
		
		
		if (m.visible) {
			NodeComponent node = Mappers.node.get(entity);	
					
			if (node != null) {
				Entity[] children = node.children.begin();				
				for (int i = 0, n = node.children.size; i < n; i++) {
					Entity child = children[i];
					cull(child);				
				}
				node.children.end();
			}
			
		}
				
	}
}
