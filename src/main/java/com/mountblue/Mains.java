package com.mountblue;

import java.util.*;

public class Mains {
	private static final int MATCHID_ARRAY_SIZE = 2;
    public static int FIRST_MATCH_INDEX = 0;
    public static int LAST_MATCH_INDEX = 1;

    public static void main(String[] args) {
        Utils utils = new Utils();
        List<Match> matches = utils.fetchMatchDataAndStore();
        List<Delivery> deliveries = utils.fetchDeliveryDataAndStore();
        Mains main = new Mains();
        main.findNoOfMatchesPerYear(matches);
        main.findNoOfWinPerTeam(matches);
        main.extraRunConcededByEachTeam2016(matches, deliveries);
        main.findMostEconomicBowler2015(matches, deliveries);

    }

    public Map<Integer,Integer> findNoOfMatchesPerYear(List<Match> matches) throws NullPointerException{
        Map<Integer, Integer> totalMatchPerYear = new TreeMap<>();
        ListIterator<Match> itr = matches.listIterator();
        int season = 0;
        Match match;
        while (itr.hasNext()) {
            match = itr.next();
            season = match.getSeason();
            if (!totalMatchPerYear.containsKey(season)) {
                totalMatchPerYear.put(season, 1);
            } else {
                totalMatchPerYear.put(season, totalMatchPerYear.get(season) + 1);
            }
        }
        //System.out.println("NO of matches per season are :");
        //System.out.println(totalMatchPerYear);
        return totalMatchPerYear;
    }

    public Map<String,Integer> findNoOfWinPerTeam(List<Match> matches) throws NullPointerException {
        Map<String, Integer> totalWinPerTeam = new HashMap<>();
        ListIterator<Match> itr = matches.listIterator();
        String winningTeam;
        Match match;
        while (itr.hasNext()) {
            match = itr.next();
            winningTeam = match.getWinner();
            if (!totalWinPerTeam.containsKey(winningTeam)) {
                totalWinPerTeam.put(winningTeam, 1);
            } else {
                totalWinPerTeam.put(winningTeam, totalWinPerTeam.get(winningTeam) + 1);
            }
        }
       
        return totalWinPerTeam;
    }

    public void extraRunConcededByEachTeam2016(List<Match> matches, List<Delivery> deliveries) {
        int matchYear = 2016;
        HashMap<String, Integer> extraRunConcededPerTeam = new HashMap<>();
        int firstAndLastMatch2016[] = findFirstAndLastMatchOfSeason(matches, matchYear);
        int firstMatch2016 = firstAndLastMatch2016[FIRST_MATCH_INDEX];
        int lastMatch2016 = firstAndLastMatch2016[LAST_MATCH_INDEX];
        ListIterator<Delivery> deliveryListIterator = deliveries.listIterator();
        Delivery delivery;
        int matchId;
        String bowlingTeam;
        int extraRun;
        while (deliveryListIterator.hasNext()) {
            delivery = deliveryListIterator.next();
            matchId = delivery.getMatchId();
            if (matchId >= firstMatch2016 && matchId <= lastMatch2016) {
                extraRun = delivery.getExtraRun();
                bowlingTeam = delivery.getBowlingTeam();
                if (!extraRunConcededPerTeam.containsKey(bowlingTeam)) {
                    extraRunConcededPerTeam.put(bowlingTeam, extraRun);
                } else {
                    extraRunConcededPerTeam.put(bowlingTeam, extraRunConcededPerTeam.get(bowlingTeam) + extraRun);
                }
            }
        }
        System.out.println(" extra runs conceded by each team in IP 2016 is :");
        System.out.println(extraRunConcededPerTeam);
    }

    public void findMostEconomicBowler2015(List<Match> matches, List<Delivery> deliveries) {
        HashMap<String, int[]> economyOfBowlers = new HashMap<>();
        int matchYear = 2015;
        final int runAndBallSize = 2;
        final int runIndex = 0;
        final int ballsIndex = 1;
        int runAndBall[] = new int[runAndBallSize];
        int firstAndLastMatch2015[] = findFirstAndLastMatchOfSeason(matches, matchYear);
        int firstMatchId2015 = firstAndLastMatch2015[FIRST_MATCH_INDEX];
        int lastMatchId2015 = firstAndLastMatch2015[LAST_MATCH_INDEX];
        ListIterator<Delivery> deliveryListIterator = deliveries.listIterator();
        Delivery delivery;
        int matchId = 0;
        String bowlerName = "";
        while (deliveryListIterator.hasNext()) {
            delivery = deliveryListIterator.next();
            matchId = delivery.getMatchId();
            if (matchId >= firstMatchId2015 && matchId <= lastMatchId2015) {
                bowlerName = delivery.getBowler();
                int totalRunConcedPerBall = delivery.getBatsmanRun() + delivery.getNoBallRun() + delivery.getWideRun();
                if (economyOfBowlers.containsKey(bowlerName)) {
                    runAndBall = economyOfBowlers.get(bowlerName);
                    runAndBall[runIndex] = runAndBall[runIndex] + totalRunConcedPerBall;
                    if (delivery.getWideRun() == 0 && delivery.getNoBallRun() == 0) {
                        runAndBall[ballsIndex] = runAndBall[ballsIndex] + 1;
                    }
                    economyOfBowlers.put(bowlerName, runAndBall);
                } else {
                    int[] runAndBallArray = new int[2];
                    runAndBallArray[runIndex] = totalRunConcedPerBall;
                    if (delivery.getWideRun() == 0 && delivery.getNoBallRun() == 0) {
                        runAndBallArray[ballsIndex] = 1;
                    }
                    economyOfBowlers.put(bowlerName, runAndBallArray);
                }
            }
        }
        Map<Double, String> bowlerswithBestEconomies = new TreeMap<>();
        for (Map.Entry<String, int[]> entry : economyOfBowlers.entrySet()) {
            runAndBall = entry.getValue();
            double overBowled = runAndBall[ballsIndex] / 6;

            double economy = runAndBall[runIndex] / overBowled;
            bowlerswithBestEconomies.put(economy, entry.getKey());
        }
        int top10 = 10;
        int bowlersCountWithBestEconomy = 1;
        System.out.println("Top 10 bowlers with best economy in 2015 :");
        for (Map.Entry<Double, String> entry : bowlerswithBestEconomies.entrySet()) {
            if (bowlersCountWithBestEconomy > top10)
                break;
            System.out.println(bowlersCountWithBestEconomy + "-->BOWLER: " + entry.getValue() + "ECONOMY :" + entry.getKey());
            bowlersCountWithBestEconomy += 1;
        }
    }

    public int[] findFirstAndLastMatchOfSeason(List<Match> matches, int matchYear) {
        int firstAndLastMatch[] = new int[MATCHID_ARRAY_SIZE];
        ListIterator<Match> matchListIterator = matches.listIterator();
        Match match;
        while (matchListIterator.hasNext()) {
            match = matchListIterator.next();
            if (match.getSeason() == matchYear && firstAndLastMatch[FIRST_MATCH_INDEX] == FIRST_MATCH_INDEX) {
                firstAndLastMatch[FIRST_MATCH_INDEX] = match.getMatchId();
                firstAndLastMatch[LAST_MATCH_INDEX] = firstAndLastMatch[FIRST_MATCH_INDEX];

            } else if (match.getSeason() == matchYear && firstAndLastMatch[FIRST_MATCH_INDEX] != FIRST_MATCH_INDEX) {
                firstAndLastMatch[LAST_MATCH_INDEX] += 1;
            }
        }
        return firstAndLastMatch;
    }

}
