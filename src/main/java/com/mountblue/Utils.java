package com.mountblue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utils {
	 private static final int MATCH_MATCHID_INDEX = 0;
	    private static final int MATCH_SEASON_INDEX = 1;
	    private static final int MATCH_CITY_INDEX = 2;
	    private static final int MATCH_DATE_INDEX = 3;
	    private static final int MATCH_TEAM1_INDEX = 4;
	    private static final int MATCH_TEAM2_INDEX = 5;
	    private static final int MATCH_TOSSWINNER_INDEX = 6;
	    private static final int MATCH_TOSSDECISION_INDEX = 7;
	    private static final int MATCH_RESULT_INDEX = 8;
	    private static final int MATCH_DL_INDEX = 9;
	    private static final int MATCH_WINNER_INDEX = 10;
	    private static final int MATCH_WINBYRUN_INDEX = 11;
	    private static final int MATCH_WINBYWICKET_INDEX = 12;
	    private static final int MATCH_PLAYEROFMATCH_INDEX = 13;
	    private static final int MATCH_VENUE_INDEX = 14;
	    private static final int MATCH_UMPIRE1_INDEX = 15;
	    private static final int MATCH_UMPIRE2_INDEX = 16;
	    private static final int MATCH_UMPIRE3_INDEX = 17;
	    private static final int DELIVERY_MATCHID_INDEX = 0;
	    private static final int DELIVERY_INNING_INDEX = 1;
	    private static final int DELIVERY_BATTIMG_TEAM_INDEX = 2;
	    private static final int DELIVERY_BOWLING_TEAM_INDEX = 3;
	    private static final int DELIVERY_OVER_INDEX = 4;
	    private static final int DELIVERY_BALL_INDEX = 5;
	    private static final int DELIVERY_BATSMAN_INDEX = 6;
	    private static final int DELIVERY_NONSTRIKER_INDEX = 7;
	    private static final int DELIVERY_BOWLER_INDEX = 8;
	    private static final int DELIVERY_IS_SUPER_OVER_INDEX = 9;
	    private static final int DELIVERY_WIDE_INDEX = 10;
	    private static final int DELIVERY_BYE_INDEX = 11;
	    private static final int DELIVERY_LEGBYE_INDEX = 12;
	    private static final int DELIVERY_NO_BALL_INDEX = 13;
	    private static final int DELIVERY_BATSMAN_RUN_INDEX = 15;
	    private static final int DELIVERY_PENALTY_INDEX = 14;
	    private static final int DELIVERY_EXTRA_RUN_INDEX = 16;
	    private static final int DELIVERY_TOTAL_RUN_INDEX = 17;
	    private static final int DELIVERY_PLAYER_DISMISSED_INDEX = 18;
	    private static final int DELIVERY_DISMISSAL_KIND_INDEX = 19;
	    private static final int DELIVERY_FIELDER_INDEX = 20;
	    String line;
	    BufferedReader bufferedReader;
	    Match match;
	    Delivery delivery;
	    String rowArray[];

	    public List<Match> fetchMatchDataAndStore() {
	        List<Match> matchList = new ArrayList<>();
	        try {
	            bufferedReader = new BufferedReader(new FileReader("matches.csv"));
	            bufferedReader.readLine();
	            while ((line = bufferedReader.readLine()) != null) {
	                match = new Match();
	                line = line.replaceAll(",", " , ");
	                rowArray = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
	                match.setMatchId(Integer.parseInt(rowArray[MATCH_MATCHID_INDEX].trim()));
	                match.setSeason(Integer.parseInt(rowArray[MATCH_SEASON_INDEX].trim()));
	                match.setCity(rowArray[MATCH_CITY_INDEX]);
	                match.setDate(rowArray[MATCH_DATE_INDEX]);
	                match.setTeam1(rowArray[MATCH_TEAM1_INDEX]);
	                match.setTeam2(rowArray[MATCH_TEAM2_INDEX]);
	                match.setTossWinner(rowArray[MATCH_TOSSWINNER_INDEX]);
	                match.setTossDecision(rowArray[MATCH_TOSSDECISION_INDEX]);
	                match.setResult(rowArray[MATCH_RESULT_INDEX]);
	                match.setDlApplied(rowArray[MATCH_DL_INDEX]);
	                match.setWinner(rowArray[MATCH_WINNER_INDEX]);
	                match.setWinByRuns(rowArray[MATCH_WINBYRUN_INDEX]);
	                match.setWinByWickets(rowArray[MATCH_WINBYWICKET_INDEX]);
	                match.setPlayerOfMatch(rowArray[MATCH_PLAYEROFMATCH_INDEX]);
	                match.setVenue(rowArray[MATCH_VENUE_INDEX]);
	                match.setUmpire1(rowArray[MATCH_UMPIRE1_INDEX]);
	                match.setUmpire2(rowArray[MATCH_UMPIRE2_INDEX]);
	                match.setUmpire3(rowArray[MATCH_UMPIRE3_INDEX]);
	                matchList.add(match);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return matchList;
	    }

	    public List<Delivery> fetchDeliveryDataAndStore() {
	        List<Delivery> deliveriesList = new ArrayList<>();
	        try {
	            bufferedReader = new BufferedReader(new FileReader("deliveries.csv"));
	            bufferedReader.readLine();
	            while ((line = bufferedReader.readLine()) != null) {
	                delivery = new Delivery();
	                line = line.replaceAll(",", " , ");
	                rowArray = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
	                delivery.setMatchId(Integer.parseInt(rowArray[DELIVERY_MATCHID_INDEX].trim()));
	                delivery.setInning(Integer.parseInt(rowArray[DELIVERY_INNING_INDEX].trim()));
	                delivery.setBattingTeam(rowArray[DELIVERY_BATTIMG_TEAM_INDEX]);
	                delivery.setBowlingTeam(rowArray[DELIVERY_BOWLING_TEAM_INDEX]);
	                delivery.setOver(Integer.parseInt(rowArray[DELIVERY_OVER_INDEX].trim()));
	                delivery.setBall(Integer.parseInt(rowArray[DELIVERY_BALL_INDEX].trim()));
	                delivery.setBatsman(rowArray[DELIVERY_BATSMAN_INDEX]);
	                delivery.setNonStriker(rowArray[DELIVERY_NONSTRIKER_INDEX]);
	                delivery.setBowler(rowArray[DELIVERY_BOWLER_INDEX]);
	                delivery.setIsSuperOver(rowArray[DELIVERY_IS_SUPER_OVER_INDEX]);
	                delivery.setWideRun(Integer.parseInt(rowArray[DELIVERY_WIDE_INDEX].trim()));
	                delivery.setByeRun(Integer.parseInt(rowArray[DELIVERY_BYE_INDEX].trim()));
	                delivery.setLegByeRun(Integer.parseInt(rowArray[DELIVERY_LEGBYE_INDEX].trim()));
	                delivery.setNoBallRun(Integer.parseInt(rowArray[DELIVERY_NO_BALL_INDEX].trim()));
	                delivery.setBatsmanRun(Integer.parseInt(rowArray[DELIVERY_BATSMAN_RUN_INDEX].trim()));
	                delivery.setPenaltyRun(Integer.parseInt(rowArray[DELIVERY_PENALTY_INDEX].trim()));
	                delivery.setExtraRun(Integer.parseInt(rowArray[DELIVERY_EXTRA_RUN_INDEX].trim()));
	                delivery.setTotalRun(Integer.parseInt(rowArray[DELIVERY_TOTAL_RUN_INDEX].trim()));
	                delivery.setPlayerDismissed(rowArray[DELIVERY_PLAYER_DISMISSED_INDEX]);
	                delivery.setDismissalKind(rowArray[DELIVERY_DISMISSAL_KIND_INDEX]);
	                delivery.setFielder(rowArray[DELIVERY_FIELDER_INDEX]);
	                deliveriesList.add(delivery);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return deliveriesList;
	    }

}
