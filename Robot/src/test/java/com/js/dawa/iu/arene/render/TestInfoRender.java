package com.js.dawa.iu.arene.render;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class TestInfoRender {
	
	@Test
	void testColor () {
		InfoRender lInfoRender = new InfoRender();
		lInfoRender.setColor("blue");
		
		assertEquals(Color.blue, lInfoRender.getColorForAwt());
		
		lInfoRender.setColor("yellow");
		
		assertEquals(Color.yellow, lInfoRender.getColorForAwt());
	}

}
