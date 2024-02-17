package test.dfa;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import fa.dfa.DFA;

public class DFATest {
	
	
	//------------------- dfa1 tests ----------------------//
	private DFA dfa1() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		
		assertTrue(dfa.addState("a"));
		assertTrue(dfa.addState("b"));
		assertTrue(dfa.setStart("a"));
		assertTrue(dfa.setFinal("b"));
		
		assertFalse(dfa.addState("a"));
		assertFalse(dfa.setStart("c"));
		assertFalse(dfa.setFinal("c"));
		
		assertTrue(dfa.addTransition("a", "a", '0'));
		assertTrue(dfa.addTransition("a", "b", '1'));
		assertTrue(dfa.addTransition("b", "a", '0'));
		assertTrue(dfa.addTransition("b", "b", '1'));
		
		assertFalse(dfa.addTransition("c", "b", '1'));
		assertFalse(dfa.addTransition("a", "c", '1'));
		assertFalse(dfa.addTransition("a", "b", '2'));
		
		return dfa;
	}
	
	@Test
	public void test1_1() {
		DFA dfa = dfa1();
		System.out.println("dfa1 instantiation pass");
	}

	@Test
	public void test1_2() {
		DFA dfa = dfa1();
		assertNotNull(dfa.getState("a"));
		assertEquals(dfa.getState("a").getName(),"a");
		assertTrue(dfa.isStart("a"));
		assertNotNull(dfa.getState("b"));
		assertEquals(dfa.getState("b").getName(),"b");
		assertTrue(dfa.isFinal("b"));
		assertEquals(dfa.getSigma(), Set.of('0','1'));
		
		System.out.println("dfa1 correctness pass");
	}
	
	@Test
	public void test1_3() {
		DFA dfa = dfa1();
		
		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));
		
		System.out.println("dfa1 accept pass");
	}
	
	@Test
	public void test1_4() {
		DFA dfa = dfa1();
		
		String dfaStr = dfa.toString();
		String expStr = " Q = { a b }\n"
				+ "Sigma = { 0 1 }\n"
				+ "delta =\n"
				+ "		0	1\n"
				+ "	a	a	b\n"
				+ "	b	a	b\n"
				+ "q0 = a\n"
				+ "F = { b }";
		
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa1 toString pass");
	}
	
	
	
	@Test
	public void test1_5() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');
		
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		
		//different state objects
		assertTrue(dfa.getState("a") != dfaSwap.getState("a"));
		assertTrue(dfa.getState("b") != dfaSwap.getState("b"));
		assertEquals(dfa.isStart("a"), dfaSwap.isStart("a"));
		
		//the transitions of the original dfa should not change
		assertFalse(dfa.accepts("0"));
		assertTrue(dfa.accepts("1"));
		assertFalse(dfa.accepts("00"));
		assertTrue(dfa.accepts("101"));
		assertFalse(dfa.accepts("e"));
	
		System.out.println("dfa1Swap instantiation pass");
	}
	
	@Test
	public void test1_6() {
		DFA dfa = dfa1();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("1"));
		assertTrue(dfaSwap.accepts("0"));
		assertFalse(dfaSwap.accepts("11"));
		assertTrue(dfaSwap.accepts("010"));
		assertFalse(dfaSwap.accepts("e"));
		
		System.out.println("dfa1Swap accept pass");
	}

//------------------- dfa2 tests ----------------------//
	private DFA dfa2() {
		DFA dfa = new DFA();
		dfa.addSigma('0');
		dfa.addSigma('1');
		
		assertTrue(dfa.addState("3"));
		assertTrue(dfa.setFinal("3"));
		
		assertTrue(dfa.addState("0"));
		assertTrue(dfa.setStart("0"));
		
		assertTrue(dfa.addState("1"));
		assertTrue(dfa.addState("2"));
		
		
		assertFalse(dfa.setFinal("c"));
		assertFalse(dfa.setStart("a"));
		assertFalse(dfa.addState("2"));
		
		assertTrue(dfa.addTransition("0", "1", '0'));
		assertTrue(dfa.addTransition("0", "0", '1'));
		assertTrue(dfa.addTransition("1", "3", '0'));
		assertTrue(dfa.addTransition("1", "2", '1'));
		assertTrue(dfa.addTransition("2", "1", '0'));
		assertTrue(dfa.addTransition("2", "1", '1'));
		assertTrue(dfa.addTransition("3", "3", '0'));
		assertTrue(dfa.addTransition("3", "3", '1'));
		
		assertFalse(dfa.addTransition("3", "a", '1'));
		assertFalse(dfa.addTransition("c", "a", '1'));
		assertFalse(dfa.addTransition("3", "a", '2'));
		
		return dfa;
	}
	
	@Test
	public void test2_1() {
		DFA dfa = dfa2();
		System.out.println("dfa2 instantiation pass");
	}

	@Test
	public void test2_2() {
		DFA dfa = dfa2();
		assertNotNull(dfa.getState("0"));
		assertEquals(dfa.getState("1").getName(),"1");
		assertTrue(dfa.isStart("0"));
		assertNotNull(dfa.getState("3"));
		assertEquals(dfa.getState("3").getName(),"3");
		assertTrue(dfa.isFinal("3"));
		assertEquals(dfa.getSigma(), Set.of('0','1'));
		
		System.out.println("dfa2 correctness pass");
	}
	
	@Test
	public void test2_3() {
		DFA dfa = dfa2();
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));
	
		System.out.println("dfa2 accept pass");
	}
	
	@Test
	public void test2_4() {
		DFA dfa = dfa2();
		
		String dfaStr = dfa.toString();
		String expStr = "Q={3 0 1 2}\n"
				+ "Sigma = {0 1}\n"
				+ "delta =\n"
				+ "	0	1\n"
				+ "3	3	3\n"
				+ "0	1	0\n"
				+ "1	3	2\n"
				+ "2	1	1\n"
				+ "q0 = 0\n"
				+ "F={3}\n";
		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa2 toString pass");
	}
	
	
	
	@Test
	public void test2_5() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		//different DFA objects
		assertTrue(dfa != dfaSwap);
		//different DFA states
		assertTrue(dfa.getState("0") != dfaSwap.getState("0"));
		assertTrue(dfa.getState("1") != dfaSwap.getState("1"));
		assertTrue(dfa.getState("3") != dfaSwap.getState("3"));
		assertEquals(dfa.isStart("0"), dfaSwap.isStart("0"));
		assertEquals(dfa.isFinal("3"), dfaSwap.isFinal("3"));
		
		//ensure that the transitions of the original DFA don't change
		assertFalse(dfa.accepts("010"));
		assertTrue(dfa.accepts("00"));
		assertFalse(dfa.accepts("101"));
		assertTrue(dfa.accepts("111011111111110"));
		assertFalse(dfa.accepts("1110111111111010"));
		
		System.out.println("dfa2Swap instantiation pass");
	}
	
	@Test
	public void test2_6() {
		DFA dfa = dfa2();
		DFA dfaSwap = dfa.swap('1', '0');
		assertFalse(dfaSwap.accepts("101"));
		assertTrue(dfaSwap.accepts("11"));
		assertFalse(dfaSwap.accepts("010"));
		assertTrue(dfaSwap.accepts("000100000000001"));
		assertFalse(dfaSwap.accepts("0001000000000101"));
		System.out.println("dfa2Swap accept pass");
	}	

//------------------- dfa3 tests ----------------------//
private DFA dfa3() {
	DFA dfa = new DFA();
	dfa.addSigma('2');
	dfa.addSigma('1');
	
	assertTrue(dfa.addState("G"));
	assertTrue(dfa.addState("D"));
	
	assertTrue(dfa.setFinal("G"));
	assertTrue(dfa.setFinal("D"));
	
	assertTrue(dfa.addState("A"));
	assertTrue(dfa.setStart("D"));
	assertTrue(dfa.setStart("A"));
	
	assertTrue(dfa.addState("B"));
	assertTrue(dfa.addState("C"));
	assertTrue(dfa.addState("E"));
	assertTrue(dfa.addState("F"));
	
	assertFalse(dfa.addState("A"));
	assertFalse(dfa.setFinal("K"));
	assertFalse(dfa.setStart("BK"));
	
	assertTrue(dfa.addTransition("A", "B", '1'));
	assertTrue(dfa.addTransition("A", "C", '2'));
	
	assertTrue(dfa.addTransition("B", "D", '1'));
	assertTrue(dfa.addTransition("B", "E", '2'));
	
	assertTrue(dfa.addTransition("C", "F", '1'));
	assertTrue(dfa.addTransition("C", "G", '2'));
	
	assertTrue(dfa.addTransition("C", "F", '1'));
	assertTrue(dfa.addTransition("C", "G", '2'));
	
	assertTrue(dfa.addTransition("D", "D", '1'));
	assertTrue(dfa.addTransition("D", "E", '2'));
	
	assertTrue(dfa.addTransition("E", "D", '1'));
	assertTrue(dfa.addTransition("E", "E", '2'));
	
	assertTrue(dfa.addTransition("F", "F", '1'));
	assertTrue(dfa.addTransition("F", "G", '2'));
	
	assertTrue(dfa.addTransition("G", "F", '1'));
	assertTrue(dfa.addTransition("G", "G", '2'));
	
	
	assertFalse(dfa.addTransition("FF", "F", '1'));
	assertFalse(dfa.addTransition("F", "GG", '2'));
	
	assertFalse(dfa.addTransition("G", "F", 'K'));
	assertFalse(dfa.addTransition("A", "K", '7'));
	
	return dfa;
}

@Test
public void test3_1() {
	DFA dfa = dfa3();
	
	System.out.println("dfa3 instantiation pass");
}

@Test
public void test3_2() {
	DFA dfa = dfa3();
	assertNotNull(dfa.getState("A"));
	assertNull(dfa.getState("K"));
	assertEquals(dfa.getState("C").getName(),"C");
	assertTrue(dfa.isStart("A"));
	assertFalse(dfa.isStart("D"));
	assertNotNull(dfa.getState("G"));
	assertEquals(dfa.getState("E").getName(),"E");
	assertTrue(dfa.isFinal("G"));
	assertFalse(dfa.isFinal("B"));
	assertEquals(dfa.getSigma(), Set.of('2','1'));

	System.out.println("dfa3 correctness pass");
}

@Test
public void test3_3() {
	DFA dfa = dfa3();
	assertTrue(dfa.accepts("121212121"));
	assertTrue(dfa.accepts("12221212121"));
	assertFalse(dfa.accepts("12"));
	assertFalse(dfa.accepts("2"));
	assertFalse(dfa.accepts("1212"));

	System.out.println("dfa3 accept pass");
}

@Test
public void test3_4() {
	DFA dfa = dfa3();
	
	String dfaStr = dfa.toString();
	String expStr = "Q={GDABCEF}\n"
			+ "Sigma = {2 1}\n"
			+ "delta =\n"
			+ "	2	1\n"
			+ "G	G	F\n"
			+ "D	E	D\n"
			+ "A	C	B\n"
			+ "B	E	D\n"
			+ "C	G	F\n"
			+ "E	E	D\n"
			+ "F	G	F\n"
			+ "q0 = A\n"
			+ "F = {G D}\n";
	
	assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
	System.out.println(dfaStr);
	System.out.println("dfa3 toString pass");
}



@Test
public void test3_5() {
	DFA dfa = dfa3();
	DFA dfaSwap = dfa.swap('2', '1');
	assertTrue(dfa != dfaSwap);
	assertTrue(dfa.getState("A") != dfaSwap.getState("A"));
	assertTrue(dfa.getState("G") != dfaSwap.getState("G"));
	assertTrue(dfa.getState("E") != dfaSwap.getState("E"));
	assertEquals(dfa.isStart("D"), dfaSwap.isStart("D"));
	assertEquals(dfa.isFinal("A"), dfaSwap.isFinal("A"));
	
	//transitions of the original dfa should not change
	assertTrue(dfa.accepts("121212121"));
	assertTrue(dfa.accepts("12221212121"));
	assertFalse(dfa.accepts("12"));
	assertFalse(dfa.accepts("2"));
	assertFalse(dfa.accepts("1212"));

	System.out.println("df31Swap instantiation pass");
}

@Test
public void test3_6() {
	DFA dfa = dfa3();
	DFA dfaSwap = dfa.swap('2', '1');
	assertTrue(dfaSwap.accepts("212121212"));
	assertTrue(dfaSwap.accepts("21112121212"));
	assertFalse(dfaSwap.accepts("21"));
	assertFalse(dfaSwap.accepts("1"));
	assertFalse(dfaSwap.accepts("2121"));
	
	System.out.println("dfa3Swap accept pass");
}
	


	//------------------- dfa4 tests ----------------------//
	private DFA dfa4() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');
		dfa.addSigma('c');
		dfa.addSigma('d');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));

		assertTrue(dfa.setFinal("q2"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q0", 'a'));
		assertTrue(dfa.addTransition("q0", "q1", 'b'));

		assertTrue(dfa.addTransition("q1", "q1", 'b'));
		assertTrue(dfa.addTransition("q1", "q2", 'c'));

		assertTrue(dfa.addTransition("q2", "q1", 'd'));
		assertTrue(dfa.addTransition("q2", "q2", 'a'));


		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test4_1() {
		DFA dfa = dfa4();

		System.out.println("dfa4 instantiation pass");
	}

	@Test
	public void test4_2() {
		DFA dfa = dfa4();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q2"));
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertTrue(dfa.isFinal("q2"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b', 'c', 'd'));

		System.out.println("dfa4 correctness pass");
	}

	@Test
	public void test4_3() {
		DFA dfa = dfa4();
		assertTrue(dfa.accepts("bc"));
		assertTrue(dfa.accepts("abcaaaaaaa"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa4 accept pass");
	}

	@Test
	public void test4_4() {
		DFA dfa = dfa4();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1q2}\n"
				+ "Sigma = {a b c d}\n"
				+ "delta =\n"
				+ "	a	b	c	d\n"
				+ "q0	q0	q1\n"
				+ "q1		q1	q2\n"
				+ "q2	q2			q1\n"
				+ "q0 = q0\n"
				+ "F = {q2}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa4 toString pass");
	}



	@Test
	public void test4_5() {
		DFA dfa = dfa4();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q2"), dfaSwap.isFinal("q2"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("bc"));
		assertTrue(dfa.accepts("abcaaaaaaa"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df41Swap instantiation pass");
	}

	@Test
	public void test4_6() {
		DFA dfa = dfa4();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("bac"));
		assertTrue(dfaSwap.accepts("bacbbbbbb"));
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("baaaa"));
		assertFalse(dfaSwap.accepts("cccbb"));

		System.out.println("dfa4Swap accept pass");
	}

	private DFA dfa5() {
		DFA dfa = new DFA();
		// Define Sigma
		dfa.addSigma('a');
		dfa.addSigma('b');
		// Add States
		assertTrue(dfa.addState("start"));
		assertTrue(dfa.addState("a"));
		assertTrue(dfa.addState("ab"));

		assertTrue(dfa.setStart("start"));
		assertTrue(dfa.setFinal("ab"));

		// Transitions
		assertTrue(dfa.addTransition("start", "a", 'a'));
		assertTrue(dfa.addTransition("start", "start", 'b'));
		assertTrue(dfa.addTransition("a", "ab", 'b'));
		assertTrue(dfa.addTransition("ab", "a", 'a'));
		assertTrue(dfa.addTransition("a", "a", 'a'));
		assertTrue(dfa.addTransition("ab", "ab", 'b'));

		return dfa;
	}
	@Test
	public void test5_1() {
		DFA dfa = dfa5();
		assertNotNull(dfa);
		System.out.println("DFA5 instantiation pass");
	}

	@Test
	public void test5_2() {
		DFA dfa = dfa5();
		assertNotNull(dfa.getState("start"));
		assertTrue(dfa.isStart("start"));
		assertTrue(dfa.isFinal("ab"));
		assertEquals(dfa.getSigma(), Set.of('a', 'b'));
		System.out.println("DFA5 structure correctness pass");
	}

	@Test
	public void test5_3() {
		DFA dfa = dfa5();
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("b"));
		assertTrue(dfa.accepts("ab"));
		assertTrue(dfa.accepts("aab"));
		assertFalse(dfa.accepts("ba"));
		assertTrue(dfa.accepts("babaab"));
		System.out.println("DFA5 acceptance pass");
	}

	@Test
	public void test5_4() {
		DFA dfa = dfa5();

		String dfaStr = dfa.toString();
		String expStr = "Q={ start a ab }\n"
				+ "Sigma = { a b }\n"
				+ "delta =\n"
				+ "	a	b\n"
				+ "start	a	start\n"
				+ "a	a	ab\n"
				+ "ab	a	ab\n"
				+ "q0 = start\n"
				+ "F = {ab}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa5 toString pass");
	}



	@Test
	public void test5_5() {
		DFA dfa = dfa5();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("start") != dfaSwap.getState("start"));
		assertTrue(dfa.getState("a") != dfaSwap.getState("a"));
		assertTrue(dfa.getState("ab") != dfaSwap.getState("ab"));
		assertEquals(dfa.isStart("start"), dfaSwap.isStart("start"));
		assertEquals(dfa.isFinal("ab"), dfaSwap.isFinal("ab"));

		//transitions of the original dfa should not change
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("b"));
		assertTrue(dfa.accepts("ab"));
		assertTrue(dfa.accepts("aab"));
		assertFalse(dfa.accepts("ba"));
		assertTrue(dfa.accepts("babaab"));

		System.out.println("df51Swap instantiation pass");
	}

	@Test
	public void test5_6() {
		DFA dfa = dfa5();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("a"));
		assertTrue(dfaSwap.accepts("ba"));
		assertTrue(dfaSwap.accepts("bba"));
		assertFalse(dfaSwap.accepts("ab"));
		assertTrue(dfaSwap.accepts("ababba"));

		System.out.println("dfa5Swap accept pass");
	}

	//------------------- dfa6 tests ----------------------//
	private DFA dfa6() {
		DFA dfa = new DFA();
		dfa.addSigma('3');
		dfa.addSigma('1');

		assertTrue(dfa.addState("G"));
		assertTrue(dfa.addState("D"));

		assertTrue(dfa.setFinal("G"));
		assertTrue(dfa.setFinal("D"));

		assertTrue(dfa.addState("A"));
		assertTrue(dfa.setStart("D"));
		assertTrue(dfa.setStart("A"));

		assertTrue(dfa.addState("B"));
		assertTrue(dfa.addState("C"));
		assertTrue(dfa.addState("E"));
		assertTrue(dfa.addState("F"));

		assertFalse(dfa.addState("A"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("A", "B", '1'));
		assertTrue(dfa.addTransition("A", "C", '3'));

		assertTrue(dfa.addTransition("B", "D", '1'));
		assertTrue(dfa.addTransition("B", "E", '3'));

		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '3'));

		assertTrue(dfa.addTransition("C", "F", '1'));
		assertTrue(dfa.addTransition("C", "G", '3'));

		assertTrue(dfa.addTransition("D", "D", '1'));
		assertTrue(dfa.addTransition("D", "E", '3'));

		assertTrue(dfa.addTransition("E", "D", '1'));
		assertTrue(dfa.addTransition("E", "E", '3'));

		assertTrue(dfa.addTransition("F", "F", '1'));
		assertTrue(dfa.addTransition("F", "G", '3'));

		assertTrue(dfa.addTransition("G", "F", '1'));
		assertTrue(dfa.addTransition("G", "G", '3'));


		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '3'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test6_1() {
		DFA dfa = dfa6();

		System.out.println("dfa6 instantiation pass");
	}

	@Test
	public void test6_2() {
		DFA dfa = dfa6();
		assertNotNull(dfa.getState("A"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("C").getName(),"C");
		assertTrue(dfa.isStart("A"));
		assertFalse(dfa.isStart("D"));
		assertNotNull(dfa.getState("G"));
		assertEquals(dfa.getState("E").getName(),"E");
		assertTrue(dfa.isFinal("G"));
		assertFalse(dfa.isFinal("B"));
		assertEquals(dfa.getSigma(), Set.of('3','1'));

		System.out.println("dfa6 correctness pass");
	}

	@Test
	public void test6_3() {
		DFA dfa = dfa6();
		assertTrue(dfa.accepts("131313131"));
		assertTrue(dfa.accepts("13331313131"));
		assertFalse(dfa.accepts("13"));
		assertFalse(dfa.accepts("3"));
		assertFalse(dfa.accepts("1313"));

		System.out.println("dfa6 accept pass");
	}

	@Test
	public void test6_4() {
		DFA dfa = dfa6();

		String dfaStr = dfa.toString();
		String expStr = "Q={GDABCEF}\n"
				+ "Sigma = {3 1}\n"
				+ "delta =\n"
				+ "	3	1\n"
				+ "G	G	F\n"
				+ "D	E	D\n"
				+ "A	C	B\n"
				+ "B	E	D\n"
				+ "C	G	F\n"
				+ "E	E	D\n"
				+ "F	G	F\n"
				+ "q0 = A\n"
				+ "F = {G D}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa3 toString pass");
	}



	@Test
	public void test6_5() {
		DFA dfa = dfa6();
		DFA dfaSwap = dfa.swap('3', '1');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("A") != dfaSwap.getState("A"));
		assertTrue(dfa.getState("G") != dfaSwap.getState("G"));
		assertTrue(dfa.getState("E") != dfaSwap.getState("E"));
		assertEquals(dfa.isStart("D"), dfaSwap.isStart("D"));
		assertEquals(dfa.isFinal("A"), dfaSwap.isFinal("A"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("131313131"));
		assertTrue(dfa.accepts("13331313131"));
		assertFalse(dfa.accepts("13"));
		assertFalse(dfa.accepts("3"));
		assertFalse(dfa.accepts("1313"));

		System.out.println("df61Swap instantiation pass");
	}

	@Test
	public void test6_6() {
		DFA dfa = dfa6();
		DFA dfaSwap = dfa.swap('3', '1');
		assertTrue(dfaSwap.accepts("313131313"));
		assertTrue(dfaSwap.accepts("31113131313"));
		assertFalse(dfaSwap.accepts("31"));
		assertFalse(dfaSwap.accepts("1"));
		assertFalse(dfaSwap.accepts("3131"));

		System.out.println("dfa6Swap accept pass");
	}

	//------------------- dfa7 tests ----------------------//
	private DFA dfa7() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');

		assertTrue(dfa.addState("0"));
		assertTrue(dfa.addState("1"));
		assertTrue(dfa.setStart("0"));
		assertTrue(dfa.setFinal("1"));

		assertFalse(dfa.addState("0"));
		assertFalse(dfa.setStart("2"));
		assertFalse(dfa.setFinal("2"));

		assertTrue(dfa.addTransition("0", "1", 'a'));
		assertTrue(dfa.addTransition("0", "1", 'b'));
		assertTrue(dfa.addTransition("1", "0", 'a'));
		assertTrue(dfa.addTransition("1", "1", 'b'));

		assertFalse(dfa.addTransition("2", "b", '1'));
		assertFalse(dfa.addTransition("a", "c", '1'));
		assertFalse(dfa.addTransition("a", "b", '2'));

		return dfa;
	}

	@Test
	public void test7_1() {
		DFA dfa = dfa7();
		System.out.println("dfa7 instantiation pass");
	}

	@Test
	public void test7_2() {
		DFA dfa = dfa7();
		assertNotNull(dfa.getState("0"));
		assertEquals(dfa.getState("0").getName(),"0");
		assertTrue(dfa.isStart("0"));
		assertNotNull(dfa.getState("1"));
		assertEquals(dfa.getState("1").getName(),"1");
		assertTrue(dfa.isFinal("1"));
		assertEquals(dfa.getSigma(), Set.of('a','b'));

		System.out.println("dfa7 correctness pass");
	}

	@Test
	public void test7_3() {
		DFA dfa = dfa7();

		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("b"));
		assertFalse(dfa.accepts("aa"));
		assertTrue(dfa.accepts("bab"));
		assertFalse(dfa.accepts("e"));

		System.out.println("dfa7 accept pass");
	}

	@Test
	public void test7_4() {
		DFA dfa = dfa7();

		String dfaStr = dfa.toString();
		String expStr = " Q = { 0 1 }\n"
				+ "Sigma = { a b }\n"
				+ "delta =\n"
				+ "		a	b\n"
				+ "	0		1	1\n"
				+ "	1		0	1\n"
				+ "q0 = 0\n"
				+ "F = { 1 }";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa7 toString pass");
	}



	@Test
	public void test7_5() {
		DFA dfa = dfa7();
		DFA dfaSwap = dfa.swap('b', 'a');

		//different DFA objects
		assertTrue(dfa != dfaSwap);

		//different state objects
		assertTrue(dfa.getState("0") != dfaSwap.getState("0"));
		assertTrue(dfa.getState("1") != dfaSwap.getState("1"));
		assertEquals(dfa.isStart("0"), dfaSwap.isStart("0"));

		//the transitions of the original dfa should not change
		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("b"));
		assertFalse(dfa.accepts("aa"));
		assertTrue(dfa.accepts("bab"));
		assertFalse(dfa.accepts("e"));

		System.out.println("dfa7Swap instantiation pass");
	}

	@Test
	public void test7_6() {
		DFA dfa = dfa7();
		DFA dfaSwap = dfa.swap('b', 'a');
		assertTrue(dfaSwap.accepts("a"));
		assertTrue(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("bb"));
		assertTrue(dfaSwap.accepts("aba"));
		assertFalse(dfaSwap.accepts("e"));

		System.out.println("dfa7Swap accept pass");
	}

	//------------------- dfa8 tests ----------------------//
	private DFA dfa8() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');
		dfa.addSigma('c');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));
		assertTrue(dfa.addState("q3"));
		assertTrue(dfa.addState("q4"));
		assertTrue(dfa.addState("q5"));

		assertTrue(dfa.setFinal("q5"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q3", 'b'));

		assertTrue(dfa.addTransition("q1", "q2", 'a'));
		assertTrue(dfa.addTransition("q1", "q3", 'c'));

		assertTrue(dfa.addTransition("q2", "q1", 'a'));
		assertTrue(dfa.addTransition("q3", "q4", 'b'));
		assertTrue(dfa.addTransition("q3", "q5", 'c'));

		assertTrue(dfa.addTransition("q4", "q3", 'b'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test8_1() {
		DFA dfa = dfa8();

		System.out.println("dfa4 instantiation pass");
	}

	@Test
	public void test8_2() {
		DFA dfa = dfa8();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q2"));
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertTrue(dfa.isFinal("q5"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b', 'c'));

		System.out.println("dfa8 correctness pass");
	}

	@Test
	public void test8_3() {
		DFA dfa = dfa8();
		assertTrue(dfa.accepts("bc"));
		assertTrue(dfa.accepts("aaacbbbbc"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abc"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa8 accept pass");
	}

	@Test
	public void test8_4() {
		DFA dfa = dfa8();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1q2q3q4q5}\n"
				+ "Sigma = {a b c}\n"
				+ "delta =\n"
				+ "	a	b	c	\n"
				+ "q0	q1	q3\n"
				+ "q1	q2		q3\n"
				+ "q2	q1	\n"
				+ "q3		q4	q5	\n"
				+ "q4		q3	\n"
				+ "q5		\n"
				+ "q0 = q0\n"
				+ "F = {q5}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa8 toString pass");
	}



	@Test
	public void test8_5() {
		DFA dfa = dfa8();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q5"), dfaSwap.isFinal("q5"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("bc"));
		assertTrue(dfa.accepts("aaacbbbbc"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abc"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df81Swap instantiation pass");
	}

	@Test
	public void test8_6() {
		DFA dfa = dfa8();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("ac"));
		assertTrue(dfaSwap.accepts("bbbcaaaac"));
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("bac"));
		assertFalse(dfaSwap.accepts("cccbb"));

		System.out.println("dfa8Swap accept pass");
	}

	//------------------- dfa9 tests ----------------------//
	private DFA dfa9() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');
		dfa.addSigma('c');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));


		assertTrue(dfa.setFinal("q1"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q1", 'b'));
		assertTrue(dfa.addTransition("q0", "q1", 'c'));

		assertTrue(dfa.addTransition("q1", "q0", 'a'));
		assertTrue(dfa.addTransition("q1", "q0", 'c'));
		assertTrue(dfa.addTransition("q1", "q0", 'b'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test9_1() {
		DFA dfa = dfa9();

		System.out.println("dfa9 instantiation pass");
	}

	@Test
	public void test9_2() {
		DFA dfa = dfa9();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertTrue(dfa.isFinal("q1"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b', 'c'));

		System.out.println("dfa9 correctness pass");
	}

	@Test
	public void test9_3() {
		DFA dfa = dfa9();
		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("abcabcabc"));
		assertFalse(dfa.accepts("ab"));
		assertFalse(dfa.accepts("abca"));
		assertFalse(dfa.accepts(" aabbcc"));

		System.out.println("dfa9 accept pass");
	}

	@Test
	public void test9_4() {
		DFA dfa = dfa9();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1}\n"
				+ "Sigma = {a b c}\n"
				+ "delta =\n"
				+ "	a	b	c	\n"
				+ "q0	q1	q1	q1\n"
				+ "q1	q0	q0	q0\n"
				+ "q0 = q0\n"
				+ "F = {q1}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa9 toString pass");
	}



	@Test
	public void test9_5() {
		DFA dfa = dfa9();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q1"), dfaSwap.isFinal("q1"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("abcabcabc"));
		assertFalse(dfa.accepts("ab"));
		assertFalse(dfa.accepts("abca"));
		assertFalse(dfa.accepts(" aabbcc"));

		System.out.println("df91Swap instantiation pass");
	}

	@Test
	public void test9_6() {
		DFA dfa = dfa9();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("a"));
		assertTrue(dfaSwap.accepts("abcabcabc"));
		assertFalse(dfaSwap.accepts("ab"));
		assertFalse(dfaSwap.accepts("abca"));
		assertFalse(dfaSwap.accepts(" aabbcc"));

		System.out.println("dfa9Swap accept pass");
	}

	//------------------- dfa10 tests ----------------------//
	private DFA dfa10() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');
		dfa.addSigma('c');
		dfa.addSigma('d');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));
		assertTrue(dfa.addState("q3"));


		assertTrue(dfa.setFinal("q2"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q2", 'b'));

		assertTrue(dfa.addTransition("q1", "q0", 'a'));

		assertTrue(dfa.addTransition("q2", "q2", 'c'));
		assertTrue(dfa.addTransition("q2", "q3", 'd'));
		assertTrue(dfa.addTransition("q3", "q2", 'd'));



		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test10_1() {
		DFA dfa = dfa10();

		System.out.println("dfa10 instantiation pass");
	}

	@Test
	public void test10_2() {
		DFA dfa = dfa10();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q2"));
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertTrue(dfa.isFinal("q2"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b', 'c', 'd'));

		System.out.println("dfa10 correctness pass");
	}

	@Test
	public void test10_3() {
		DFA dfa = dfa10();
		assertTrue(dfa.accepts("b"));
		assertTrue(dfa.accepts("aabcdd"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa10 accept pass");
	}

	@Test
	public void test10_4() {
		DFA dfa = dfa10();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1q2q3}\n"
				+ "Sigma = {a b c d}\n"
				+ "delta =\n"
				+ "	a	b	c	d\n"
				+ "q0	q1	q2\n"
				+ "q1	q0\n"
				+ "q2			q2	q3\n"
				+ "q3				q2\n"
				+ "q0 = q0\n"
				+ "F = {q2}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa10 toString pass");
	}



	@Test
	public void test10_5() {
		DFA dfa = dfa10();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q2"), dfaSwap.isFinal("q2"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("b"));
		assertTrue(dfa.accepts("aabcdd"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df101Swap instantiation pass");
	}

	@Test
	public void test10_6() {
		DFA dfa = dfa10();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("a"));
		assertTrue(dfaSwap.accepts("bbacdd"));
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("baaaa"));
		assertFalse(dfaSwap.accepts("cccbb"));

		System.out.println("dfa10Swap accept pass");
	}

	//------------------- dfa11 tests ----------------------//
	private DFA dfa11() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');


		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));


		assertTrue(dfa.setFinal("q0"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q0", 'b'));

		assertTrue(dfa.addTransition("q1", "q2", 'a'));
		assertTrue(dfa.addTransition("q1", "q0", 'b'));

		assertTrue(dfa.addTransition("q2", "q0", 'b'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test11_1() {
		DFA dfa = dfa11();

		System.out.println("dfa11 instantiation pass");
	}

	@Test
	public void test11_2() {
		DFA dfa = dfa11();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q2"));
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertTrue(dfa.isFinal("q0"));
		assertFalse(dfa.isFinal("q1"));
		assertEquals(dfa.getSigma(), Set.of('a','b'));

		System.out.println("dfa11 correctness pass");
	}

	@Test
	public void test11_3() {
		DFA dfa = dfa11();
		assertFalse(dfa.accepts("abba"));
		assertFalse(dfa.accepts("aabbabbaa"));
		assertFalse(dfa.accepts("a"));
		assertTrue(dfa.accepts("aab"));
		assertTrue(dfa.accepts("aabbbbbbbb"));

		System.out.println("dfa11 accept pass");
	}

	@Test
	public void test11_4() {
		DFA dfa = dfa11();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1q2}\n"
				+ "Sigma = {a b}\n"
				+ "delta =\n"
				+ "	a	b\n"
				+ "q0	q1	q0\n"
				+ "q1	q2	q0\n"
				+ "q2		q0\n"
				+ "q0 = q0\n"
				+ "F = {q0}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa11 toString pass");
	}



	@Test
	public void test11_5() {
		DFA dfa = dfa11();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q0"), dfaSwap.isFinal("q0"));

		//transitions of the original dfa should not change
		assertFalse(dfa.accepts("abba"));
		assertFalse(dfa.accepts("aabbabbaa"));
		assertFalse(dfa.accepts("a"));
		assertTrue(dfa.accepts("aab"));
		assertTrue(dfa.accepts("aabbbbbbbb"));

		System.out.println("df111Swap instantiation pass");
	}

	@Test
	public void test11_6() {
		DFA dfa = dfa11();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertFalse(dfaSwap.accepts("baab"));
		assertFalse(dfaSwap.accepts("bbaabaabb"));
		assertFalse(dfaSwap.accepts("b"));
		assertTrue(dfaSwap.accepts("bba"));
		assertTrue(dfaSwap.accepts("bbaaaaaaaa"));

		System.out.println("dfa11Swap accept pass");
	}

	//------------------- dfa12 tests ----------------------//
	private DFA dfa12() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');

		assertTrue(dfa.addState("q3"));
		assertTrue(dfa.addState("q4"));
		assertTrue(dfa.addState("q5"));
		assertTrue(dfa.addState("q6"));
		assertTrue(dfa.addState("q7"));


		assertTrue(dfa.setFinal("q7"));

		assertTrue(dfa.setStart("q3"));


		assertFalse(dfa.addState("q3"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q3", "q4", 'a'));
		assertTrue(dfa.addTransition("q3", "q3", 'b'));

		assertTrue(dfa.addTransition("q4", "q3", 'a'));
		assertTrue(dfa.addTransition("q4", "q5", 'b'));

		assertTrue(dfa.addTransition("q5", "q3", 'a'));
		assertTrue(dfa.addTransition("q5", "q6", 'b'));

		assertTrue(dfa.addTransition("q6", "q7", 'a'));
		assertTrue(dfa.addTransition("q6", "q3", 'b'));

		assertTrue(dfa.addTransition("q7", "q7", 'a'));
		assertTrue(dfa.addTransition("q7", "q7", 'b'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test12_1() {
		DFA dfa = dfa12();

		System.out.println("dfa12 instantiation pass");
	}

	@Test
	public void test12_2() {
		DFA dfa = dfa12();
		assertNotNull(dfa.getState("q3"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q4").getName(),"q4");
		assertTrue(dfa.isStart("q3"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q7"));
		assertEquals(dfa.getState("q5").getName(),"q5");
		assertTrue(dfa.isFinal("q7"));
		assertFalse(dfa.isFinal("q3"));
		assertEquals(dfa.getSigma(), Set.of('a','b'));

		System.out.println("dfa12 correctness pass");
	}

	@Test
	public void test12_3() {
		DFA dfa = dfa12();
		assertTrue(dfa.accepts("abba"));
		assertTrue(dfa.accepts("abbabbb"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa12 accept pass");
	}

	@Test
	public void test12_4() {
		DFA dfa = dfa12();

		String dfaStr = dfa.toString();
		String expStr = "Q={q3q4q5q6q7}\n"
				+ "Sigma = {a b}\n"
				+ "delta =\n"
				+ "	a	b\n"
				+ "q3	q4	q3\n"
				+ "q4	q3	q5\n"
				+ "q5	q3	q6\n"
				+ "q6	q7	q3\n"
				+ "q7	q7	q7\n"
				+ "q0 = q3\n"
				+ "F = {q7}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa12 toString pass");
	}



	@Test
	public void test12_5() {
		DFA dfa = dfa12();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q3") != dfaSwap.getState("q3"));
		assertTrue(dfa.getState("q4") != dfaSwap.getState("q4"));
		assertTrue(dfa.getState("q5") != dfaSwap.getState("q5"));
		assertEquals(dfa.isStart("q3"), dfaSwap.isStart("q3"));
		assertEquals(dfa.isFinal("q7"), dfaSwap.isFinal("q7"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("abba"));
		assertTrue(dfa.accepts("abbabbb"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df121Swap instantiation pass");
	}

	@Test
	public void test12_6() {
		DFA dfa = dfa12();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("baab"));
		assertTrue(dfaSwap.accepts("baabaaa"));
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("baaaa"));
		assertFalse(dfaSwap.accepts("cccaa"));

		System.out.println("dfa12Swap accept pass");
	}

	//------------------- dfa13 tests ----------------------//
	private DFA dfa13() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q0q2"));

		assertTrue(dfa.setFinal("q0q2"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q0q2", 'a'));
		assertTrue(dfa.addTransition("q0", "q1", 'b'));

		assertTrue(dfa.addTransition("q1", "q0q2", 'b'));

		assertTrue(dfa.addTransition("q0q2", "q0q2", 'a'));
		assertTrue(dfa.addTransition("q0q2", "q1", 'b'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test13_1() {
		DFA dfa = dfa13();

		System.out.println("dfa13 instantiation pass");
	}

	@Test
	public void test13_2() {
		DFA dfa = dfa13();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q0q2"));
		assertEquals(dfa.getState("q0q2").getName(),"q0q2");
		assertTrue(dfa.isFinal("q0q2"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b'));

		System.out.println("dfa13 correctness pass");
	}

	@Test
	public void test13_3() {
		DFA dfa = dfa13();
		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("bbaaa"));
		assertFalse(dfa.accepts("b"));
		assertFalse(dfa.accepts("abbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa13 accept pass");
	}

	@Test
	public void test13_4() {
		DFA dfa = dfa13();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0 q1 q0q2}\n"
				+ "Sigma = {a b}\n"
				+ "delta =\n"
				+ "	a	b\n"
				+ "q0	q0q2	q1\n"
				+ "q1		q0q2\n"
				+ "q0q2		q0q2	q1\n"
				+ "q0 = q0\n"
				+ "F = {q0q2}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa13 toString pass");
	}



	@Test
	public void test13_5() {
		DFA dfa = dfa13();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q0q2") != dfaSwap.getState("q0q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q0q2"), dfaSwap.isFinal("q0q2"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("a"));
		assertTrue(dfa.accepts("bbaaa"));
		assertFalse(dfa.accepts("b"));
		assertFalse(dfa.accepts("abbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df131Swap instantiation pass");
	}

	@Test
	public void test13_6() {
		DFA dfa = dfa13();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("b"));
		assertTrue(dfaSwap.accepts("aabbb"));
		assertFalse(dfaSwap.accepts("a"));
		assertFalse(dfaSwap.accepts("baaa"));
		assertFalse(dfaSwap.accepts("cccaa"));

		System.out.println("dfa13Swap accept pass");
	}

	//------------------- dfa14 tests ----------------------//
	private DFA dfa14() {
		DFA dfa = new DFA();
		dfa.addSigma('a');
		dfa.addSigma('b');

		assertTrue(dfa.addState("q0"));
		assertTrue(dfa.addState("q1"));
		assertTrue(dfa.addState("q2"));
		assertTrue(dfa.addState("q3"));
		assertTrue(dfa.addState("q4"));

		assertTrue(dfa.setFinal("q4"));

		assertTrue(dfa.setStart("q0"));


		assertFalse(dfa.addState("q0"));
		assertFalse(dfa.setFinal("K"));
		assertFalse(dfa.setStart("BK"));

		assertTrue(dfa.addTransition("q0", "q1", 'a'));
		assertTrue(dfa.addTransition("q0", "q3", 'b'));

		assertTrue(dfa.addTransition("q1", "q3", 'b'));

		assertTrue(dfa.addTransition("q2", "q3", 'b'));
		assertTrue(dfa.addTransition("q3", "q4", 'a'));
		assertTrue(dfa.addTransition("q3", "q2", 'b'));

		assertTrue(dfa.addTransition("q4", "q4", 'a'));

		assertFalse(dfa.addTransition("FF", "F", '1'));
		assertFalse(dfa.addTransition("F", "GG", '2'));

		assertFalse(dfa.addTransition("G", "F", 'K'));
		assertFalse(dfa.addTransition("A", "K", '7'));

		return dfa;
	}

	@Test
	public void test14_1() {
		DFA dfa = dfa14();

		System.out.println("dfa14 instantiation pass");
	}

	@Test
	public void test14_2() {
		DFA dfa = dfa14();
		assertNotNull(dfa.getState("q0"));
		assertNull(dfa.getState("K"));
		assertEquals(dfa.getState("q1").getName(),"q1");
		assertTrue(dfa.isStart("q0"));
		assertFalse(dfa.isStart("q1"));
		assertNotNull(dfa.getState("q2"));
		assertEquals(dfa.getState("q2").getName(),"q2");
		assertTrue(dfa.isFinal("q4"));
		assertFalse(dfa.isFinal("q0"));
		assertEquals(dfa.getSigma(), Set.of('a','b'));

		System.out.println("dfa14 correctness pass");
	}

	@Test
	public void test14_3() {
		DFA dfa = dfa14();
		assertTrue(dfa.accepts("ba"));
		assertTrue(dfa.accepts("abbbbbaaa"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("dfa14 accept pass");
	}

	@Test
	public void test14_4() {
		DFA dfa = dfa14();

		String dfaStr = dfa.toString();
		String expStr = "Q={q0q1q2q3q4}\n"
				+ "Sigma = {a b}\n"
				+ "delta =\n"
				+ "	a	b\n"
				+ "q0	q1	q3\n"
				+ "q1		q3\n"
				+ "q2		q3\n"
				+ "q3	q4	q2\n"
				+ "q4	q4\n"
				+ "q0 = q0\n"
				+ "F = {q4}\n";

		assertTrue(dfaStr.replaceAll("\\s", "").equals(expStr.replaceAll("\\s", "")));
		System.out.println(dfaStr);
		System.out.println("dfa14 toString pass");
	}



	@Test
	public void test14_5() {
		DFA dfa = dfa14();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfa != dfaSwap);
		assertTrue(dfa.getState("q0") != dfaSwap.getState("q0"));
		assertTrue(dfa.getState("q1") != dfaSwap.getState("q1"));
		assertTrue(dfa.getState("q2") != dfaSwap.getState("q2"));
		assertEquals(dfa.isStart("q0"), dfaSwap.isStart("q0"));
		assertEquals(dfa.isFinal("q4"), dfaSwap.isFinal("q4"));

		//transitions of the original dfa should not change
		assertTrue(dfa.accepts("ba"));
		assertTrue(dfa.accepts("abbbbbaaa"));
		assertFalse(dfa.accepts("a"));
		assertFalse(dfa.accepts("abbbb"));
		assertFalse(dfa.accepts("cccaa"));

		System.out.println("df141Swap instantiation pass");
	}

	@Test
	public void test14_6() {
		DFA dfa = dfa14();
		DFA dfaSwap = dfa.swap('a', 'b');
		assertTrue(dfaSwap.accepts("ab"));
		assertTrue(dfaSwap.accepts("baaaaabbb"));
		assertFalse(dfaSwap.accepts("b"));
		assertFalse(dfaSwap.accepts("baaaa"));
		assertFalse(dfaSwap.accepts("cccaa"));

		System.out.println("dfa14Swap accept pass");
	}
}
