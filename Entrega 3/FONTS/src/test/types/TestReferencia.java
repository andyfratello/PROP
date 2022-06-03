package test.types;

import main.domain.classes.Full;
import main.domain.classes.types.Referencia;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class TestReferencia
{

	@Test
	public void TestToString()
	{
		Full f = new Full(1, "FullTest", 10000000, 100000);
		f.ModificaCela(0,0, "A1");
		
		Referencia r = f.GetReferencia(0, 0);
		
		System.out.println(r.toString());
		
		r = f.GetReferencia(25, 1);
		System.out.println(r.toString());
		
		r = f.GetReferencia(26, 2);
		
		System.out.println(r.toString());
		
		r = f.GetReferencia(27, 4);
		
		System.out.println(r.toString());
		
		r = f.GetReferencia(26+26, 5);
		
		System.out.println(r.toString());
		
		r = f.GetReferencia(27*26, 6);
		
		System.out.println(r.toString());
	}



}

