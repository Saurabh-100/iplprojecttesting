package com.mountblue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class MainsTest {
	Mains mains;
	Utils utils;
	@BeforeEach
	void init()
	{
		mains=new Mains();
		utils=new Utils();
	}
	
	@Nested
	class NoOfMatchPerYear{
		@Test
		@DisplayName("Check list is not null")
		void ListNotNull() {
			 List<Match> matches = utils.fetchMatchDataAndStore();
			 assertNotNull(matches);
		   }
		@Test
		@DisplayName("Check list is null")
		void ListisNull() {
			List<Match> matches=utils.fetchMatchDataAndStore();
			assertNull(matches);
		}
		@Test
		@DisplayName("Check key is present ")
		void mapNotContainsKey()
		{
			 List<Match> matches = utils.fetchMatchDataAndStore();
			 Map<Integer,Integer> sample=mains.findNoOfMatchesPerYear(matches);
			 assertFalse(sample.containsKey(2001));
			 ;
		}
		@Test
		@DisplayName("Check key is not present")
		void mapContainsKey()
		{
			List<Match> matches=utils.fetchMatchDataAndStore();
			Map<Integer,Integer> sample=mains.findNoOfMatchesPerYear(matches);
			assertTrue(sample.containsKey(2008));
		}
		@Test
		@DisplayName("Check function prints correct output")
		void correctOutput()
		{
			List<Match> matches=utils.fetchMatchDataAndStore();
			Map<Integer,Integer> sample=mains.findNoOfMatchesPerYear(matches);
			int expected=57;
			assertEquals(expected,sample.get(2009));
		}
		@Test
		@DisplayName("Check function throws correct exception")
		void throwNotNullException() {
			List<Match> matches=null;
			assertThrows(NullPointerException.class,()->mains.findNoOfMatchesPerYear(matches));
		}

		
	}
	
	@Nested
	class NoOfMatchesPerTeam{
		@Test
		@DisplayName("check that matches won by team list is not null")
		void ListNotNull() {
			List<Match> matches=utils.fetchMatchDataAndStore();
			assertNotNull(matches);
		
		}
		@Test
		@DisplayName("check that list is null")
		void ListIsNull(){
			List<Match> matches=utils.fetchMatchDataAndStore();
			assertNull(matches);
			
		}
		@Test
		@DisplayName("Check function prints correct output")
		void correctOutput() {
			int expected=92;
			List<Match> matches=utils.fetchMatchDataAndStore();
			Map<String,Integer> sampleMap=mains.findNoOfWinPerTeam(matches);
			assertEquals(expected,sampleMap.get(" Mumbai Indians "));
			
		}
		@Test
		@DisplayName("check team played Ipl ")
		void teamPlayedIpl() {
			List<Match> matches=utils.fetchMatchDataAndStore();
			Map<String,Integer> sampleMap=mains.findNoOfWinPerTeam(matches);
			assertTrue(sampleMap.containsKey(" Mumbai Indians "));
			
			
		}
		@Test
		@DisplayName("check team name is invalid")
		void teamNotPlayedIpl() {
			List<Match> matches=utils.fetchMatchDataAndStore();
			Map<String,Integer> sampleMap=mains.findNoOfWinPerTeam(matches);
			assertFalse(sampleMap.containsKey("Mumbai Americans"));
			
			
		}
		@Test
		@DisplayName("check function throws correct exception")
		void throwsException() {
			List<Match> matches=null;
			assertThrows(NullPointerException.class,()->mains.findNoOfWinPerTeam(matches));
			
		}
	}

	
}
