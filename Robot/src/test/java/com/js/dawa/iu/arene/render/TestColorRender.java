package com.js.dawa.iu.arene.render;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestColorRender {
	
	@Test
	void testColor () {
		InfoRender lInfoRender = new InfoRender();
		lInfoRender.setColor("blue");
		
		assertEquals(Color.blue, lInfoRender.getColorForAwt());
		
		lInfoRender.setColor("yellow");
		
		assertEquals(Color.yellow, lInfoRender.getColorForAwt());
	}

    @ParameterizedTest
    @CsvSource(value={
        "C[12,13,13]; java.awt.Color[r=12,g=13,b=13]",
        "C[12,-13,13]; java.awt.Color[r=12,g=0,b=13] ",
        "C[12,-13,500]; java.awt.Color[r=12,g=0,b=0]"
    },delimiter =';')
	void testColorRgb(String pColor,String pColorAttempt) {
		ColorRender lInfoRender = new ColorRender();
		lInfoRender.setColor(pColor);
		Color lRes = lInfoRender.getFromRgb();
		assertEquals(pColorAttempt, lRes.toString());
	}
	
	
	
}
