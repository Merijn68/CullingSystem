package com.mvm.lost.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

public class BoundingBoxComponent implements Component  {
	
	public Rectangle rectangle = new Rectangle();	
	
	public Vector2[] points = new Vector2[4];
	public float checksum;	
		
	{		
		points[0] = new Vector2();
		points[1] = new Vector2();
		points[2] = new Vector2();
		points[3] = new Vector2();				
	}	

	/**
	* Returns a bounding box for this box.
	* @return the bounding box
	*/
	public Rectangle getBoundingRect(){			
			
		rectangle.x = Math.min(Math.min(Math.min(points[0].x, points[1].x),points[2].x),points[3].x);
		rectangle.width = Math.max(Math.max(Math.max(points[0].x, points[1].x),points[2].x),points[3].x) - rectangle.x;	
		rectangle.y = Math.min(Math.min(Math.min(points[0].y, points[1].y),points[2].y),points[3].y);
		rectangle.height = Math.max(Math.max(Math.max(points[0].y, points[1].y),points[2].y),points[3].y) - rectangle.y;						
		return rectangle;
	}
	
}
