package ipl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {



    public static void main(String[] args) {
        List<Matches> matches = CsvReader.getMatches();
        List<Deliveries> deliveries = CsvReader.getDeliveries();

        System.out.println("Matches: " + matches.size());
        System.out.println("Deliveries: " + deliveries.size());

        Map<Integer, Integer> matchesPerYear = MatchesAnalysis.matchesPerYear(matches);
        System.out.println("---------------- Matches per Year ----------------");
        System.out.printf("%-10s | %-10s%n", "Year", "Matches");
        for (Map.Entry<Integer, Integer> entry : matchesPerYear.entrySet()) {
            System.out.printf("%-10d | %-10d%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<String, Integer> winsPerTeam = MatchesAnalysis.matchesWinPerTeam(matches);
        System.out.println("---------------- Matches Won per Team ----------------");
        System.out.printf("%-25s | %-10s%n", "Team", "Wins");
        for (Map.Entry<String, Integer> entry : winsPerTeam.entrySet()) {
            System.out.printf("%-25s | %-10d%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<String, Integer> extraRuns2016 = MatchesAnalysis.extraRuns2016(matches, deliveries);
        System.out.println("---------------- Extra Runs Conceded in 2016 ----------------");
        System.out.printf("%-25s | %-10s%n", "Team", "Extra Runs");
        for (Map.Entry<String, Integer> entry : extraRuns2016.entrySet()) {
            System.out.printf("%-25s | %-10d%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<String, Double> economicalBowler= MatchesAnalysis.topEconomicalBowlers2015(matches,deliveries);
        System.out.println("---------------- Top Economical Bowlers ----------------");
        System.out.printf("%-25s | %-10s%n", "Bowler", "Economical");
        for(Map.Entry<String,Double> entry : economicalBowler.entrySet()){
            System.out.printf("%-25s | %-10.2f%n",entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<String, Double> winPerTeam= MatchesAnalysis.winPercentageTeam(matches);
        System.out.println("---------------- Win % of Each Team ----------------");
        System.out.printf("%-25s | %-10s%n", "Team", "Win%");
        for (Map.Entry<String, Double> entry : winPerTeam.entrySet()) {
            System.out.printf("%-25s | %-10.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<Integer, String> wicketTakerPerSeason = MatchesAnalysis.topWicketTakerPerSeason(matches,deliveries);
        System.out.println("---------------- Top Wicket Taker per Season ----------------");
        System.out.printf("%-10s | %-30s%n", "Season", "Top Bowler");
        for (Map.Entry<Integer, String> entry : wicketTakerPerSeason.entrySet()) {
            System.out.printf("%-10d | %-30s%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        Map<String,Integer> eachCity = MatchesAnalysis.numberOfMatchesPlayedEachCity(matches);
        System.out.println("---------------- Number of matches Played in Each City ----------------");
        System.out.printf("%-10s | %-30s%n", "Number", "City");
        for (Map.Entry<String,Integer> entry:eachCity.entrySet()) {
            System.out.printf("%-10d | %-30s%n", entry.getValue(), entry.getKey());
        }
        System.out.println();


        Map<String, Integer> tossWonAndWinMatch = MatchesAnalysis.wonTossAndWonMatch(matches);
        System.out.println("---------------- Number of Times both Toss and Match Win  ----------------");
        System.out.printf("%-10s | %-30s%n", "Times", "Team");
        for (Map.Entry<String,Integer> entry:tossWonAndWinMatch.entrySet()) {
            System.out.printf("%-10d | %-30s%n", entry.getValue(), entry.getKey());
        }
        System.out.println();

        System.out.println("---------------- Highest Number of times one player dismissed by another player  ----------------");
        Map<String, Integer> dismissedPlayer = MatchesAnalysis.sameBowlerDismissedbySamePlayer(deliveries);

        System.out.println(dismissedPlayer);
        System.out.println();



    }
}
