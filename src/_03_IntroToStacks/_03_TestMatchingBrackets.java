package _03_IntroToStacks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

public class _03_TestMatchingBrackets {

	@Test
	public void testMatchingBrackets() {
		assertTrue(doBracketsMatch("{}"));
		assertTrue(doBracketsMatch("{{}}"));
		assertTrue(doBracketsMatch("{}{}{{}}"));
		assertFalse(doBracketsMatch("{{}"));
		assertFalse(doBracketsMatch("}{"));
	}

	// USE A STACK TO COMPLETE THE METHOD FOR CHECKING IF EVERY OPENING BRACKET HAS A MATCHING CLOSING BRACKET
	private boolean doBracketsMatch(String b) {
		char[] arr = b.toCharArray();
		Stack<Character> character = new Stack<Character>();
		int num = 0;
		
		for(int i = 0; i < b.length(); i++) {
			character.push(arr[i]);
		}
		for(int i = 0; i < b.length(); i++) {
			char poppedValue = character.pop();
			 if(poppedValue == '{') {		 
				 if(num > 0) {
					 num--;
				 }
				 else {
					 return false;
				 }
			}
			if(poppedValue == '}') {
					num++;
			}
	
		}
		if(num <= 0) {
			return true;
		}
		else {
			return false;
		}
		
	}

}