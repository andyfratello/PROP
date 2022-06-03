package test.types;

// import main.domain.classes.Full;
import main.domain.classes.types.TType;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class TestTType
{

	@Test
	public void TestIs1()
	{
		assertTrue(TType.INT.is(TType.NUMBER));
	}

	@Test
	public void TestIs2()
	{
		assertTrue(TType.DOUBLE.is(TType.NUMBER));
	}

	@Test
	public void TestIs3()
	{
		assertTrue(TType.DOUBLE.is(TType.DOUBLE));
	}

	@Test
	public void TestIs4()
	{
		assertTrue(TType.DOUBLE.is(TType.STRING));
	}

	@Test
	public void TestIs5()
	{
		assertFalse(TType.NUMBER.is(TType.DOUBLE));
	}

}
