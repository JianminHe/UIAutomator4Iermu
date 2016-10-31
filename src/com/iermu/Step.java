package com.iermu;

import java.io.FileInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;



public class Step {
	
	int width =0;
	int height =0;
	Bitmap bitmap =null;
	int[] xArray = new int[]{8, 278, 548, 818};
	int[] yArray = new int[]{422, 645, 1095, 1378};
	
	int[] step = new int[4];
    
	  
	    public void loadPng (String url)  
	    {  
	        Bitmap bitmap = null;  
	        
	        try {
	        FileInputStream fis = new FileInputStream(url);
	        bitmap = BitmapFactory.decodeStream(fis);
	        } catch (Exception ex)
	        {
	        	
	        }
	        if(bitmap!=null)
	        {
	        	width = bitmap.getWidth();
	        	height = bitmap.getHeight();
	        	this.bitmap = bitmap;
	        }
	        bitmap.getPixel(8, 324);
	    }  
	    
	    
	    public void calcStep()
	    {
	    	for(int j =0; j<4; j++)
	    	{
	    		for(int i=0; i<4; i++)
	    		{
	    			int color = bitmap.getPixel(xArray[i], yArray[j]);
	    			
	    			int r = Color.red(color);
	    			int g = Color.green(color);
	    			int b = Color.blue(color);
	    			
	    			if( r > 190 && r <220)
	    			{
	  //  			System.out.println(" x=" + xArray[i] + "; y =" +yArray[j] + "; R:" + r + " G:" + g + " B:" + b);
	    				step[4-j-1] = i ; 
	    				    		//	System.out.println("Step = " +  i);
	    			}
	    			
	    		}
	    	}
	    	
	    	for(int i =0; i<4; i++)
	    	{
	    		System.out.println(step[i]);
	    		
	    	
	    	}
	    
	    }
	    
	    
	    

}
