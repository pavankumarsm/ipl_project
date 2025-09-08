package ipl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final String MATCHES_PATH = "src/Resources/matches.csv";
    private static final String DELIVERIES_PATH = "src/Resources/deliveries.csv";

    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int MATCH_UMPIRE3 = 17;

    public static final int DELIVERIES_MATCH_ID = 0;
    public static final int DELIVERIES_INNING = 1;
    public static final int DELIVERIES_BATTING_TEAM = 2;
    public static final int DELIVERIES_BOWLING_TEAM = 3;
    public static final int DELIVERIES_OVER = 4;
    public static final int DELIVERIES_BALL = 5;
    public static final int DELIVERIES_BATSMAN = 6;
    public static final int DELIVERIES_NON_STRIKER = 7;
    public static final int DELIVERIES_BOWLER = 8;
    public static final int DELIVERIES_IS_SUPER_OVER = 9;
    public static final int DELIVERIES_WIDE_RUNS = 10;
    public static final int DELIVERIES_BYE_RUNS = 11;
    public static final int DELIVERIES_LEG_BYE_RUNS = 12;
    public static final int DELIVERIES_NO_BALL = 13;
    public static final int DELIVERIES_PENALTY_RUNS = 14;
    public static final int DELIVERIES_BATSMAN_RUNS = 15;
    public static final int DELIVERIES_EXTRA_RUNS = 16;
    public static final int DELIVERIES_TOTAL_RUNS = 17;
    public static final int DELIVERIES_PLAYER_DISMISSED = 18;
    public static final int DELIVERIES_DISMISSAL_KIND = 19;
    public static final int DELIVERIES_FIELDER = 20;

    public static List<Match> fetchMatches() {
        List<Match> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MATCHES_PATH))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Match match = new Match();
                match.setId(Integer.parseInt(parts[MATCH_ID]));
                match.setSeason(Integer.parseInt(parts[MATCH_SEASON]));
                match.setCity(parts[MATCH_CITY]);
                match.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(parts[MATCH_DATE]));
                match.setTeam1(parts[MATCH_TEAM1]);
                match.setTeam2(parts[MATCH_TEAM2]);
                match.setTossWinner(parts[MATCH_TOSS_WINNER]);
                match.setTossDecision(parts[MATCH_TOSS_DECISION]);
                match.setResult(parts[MATCH_RESULT]);
                match.setDlApplied(Integer.parseInt(parts[MATCH_DL_APPLIED]) == 1);
                match.setWinner(parts[MATCH_WINNER]);
                match.setWinByRuns(Integer.parseInt(parts[MATCH_WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(parts[MATCH_WIN_BY_WICKETS]));
                match.setPlayerOfMatch(parts[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(parts[MATCH_VENUE]);
                match.setUmpire1(parts[MATCH_UMPIRE1]);
                match.setUmpire2(parts[MATCH_UMPIRE2]);
                match.setUmpire3(parts[MATCH_UMPIRE3]);
                list.add(match);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Delivery> fetchDeliveries() {
        List<Delivery> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DELIVERIES_PATH))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Delivery delivery = new Delivery();
                delivery.setMatchId(Integer.parseInt(parts[DELIVERIES_MATCH_ID]));
                delivery.setInning(Integer.parseInt(parts[DELIVERIES_INNING]));
                delivery.setBattingTeam(parts[DELIVERIES_BATTING_TEAM]);
                delivery.setBowlingTeam(parts[DELIVERIES_BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(parts[DELIVERIES_OVER]));
                delivery.setBall(Integer.parseInt(parts[DELIVERIES_BALL]));
                delivery.setBatsman(parts[DELIVERIES_BATSMAN]);
                delivery.setNonStriker(parts[DELIVERIES_NON_STRIKER]);
                delivery.setBowler(parts[DELIVERIES_BOWLER]);
                delivery.setSuperOver(Integer.parseInt(parts[DELIVERIES_IS_SUPER_OVER]) == 1);
                delivery.setWideRuns(Integer.parseInt(parts[DELIVERIES_WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(parts[DELIVERIES_BYE_RUNS]));
                delivery.setLegByeRuns(Integer.parseInt(parts[DELIVERIES_LEG_BYE_RUNS]));
                delivery.setNoBallRuns(Integer.parseInt(parts[DELIVERIES_NO_BALL]));
                delivery.setPenaltyRuns(Integer.parseInt(parts[DELIVERIES_PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(parts[DELIVERIES_BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(parts[DELIVERIES_EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(parts[DELIVERIES_TOTAL_RUNS]));
                delivery.setPlayerDismissed(parts[DELIVERIES_PLAYER_DISMISSED]);
                delivery.setDismissalKind(parts[DELIVERIES_DISMISSAL_KIND]);
                delivery.setFielder(parts[DELIVERIES_FIELDER]);
                list.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<Match> matches = fetchMatches();
        List<Delivery> deliveries = fetchDeliveries();

           matchesPerYear(matches);
           matchesWinPerTeam(matches);
           calculateExtraRunsByTeamFor2016(matches, deliveries);
           findTopEconomicalBowlersFor2015(matches, deliveries);
           calculateWinPercentagePerTeam(matches);
           findTopWicketTakerPerSeason(matches, deliveries);
           numberOfMatchesPlayedEachCity(matches);
           wonTossAndWonMatch(matches);
           getMostFrequentBowlerBatsmanPair(deliveries);
           mostFiveWicketsHauls(deliveries);
           mostPlayerOfMatchAward(matches);
           findTopRunScorer(deliveries);
           findBestEconomyBowlerInPowerplay(deliveries);
           findFinalMatchPerSeason(matches);
           countSixesByPlayer(deliveries);
           countTossWinsPerTeam(matches);
           findTopRunScorerPerSeason(matches,deliveries);
           findTeamWithMostBoundariesPerSeason(matches,deliveries);

    }

    public static void matchesPerYear(List<Match> matches) {
        Map<Integer, Integer> seasonMatchCount = new TreeMap<>();
        for (Match match : matches) {
            seasonMatchCount.put(match.getSeason(), seasonMatchCount.getOrDefault(match.getSeason(), 0) + 1);
        }
        System.out.println(seasonMatchCount);
    }

    public static void matchesWinPerTeam(List<Match> matches) {
        Map<String, Integer> win = new HashMap<>();
        for (Match match : matches) {
            String winner = match.getWinner();
            if (winner == null || winner.isEmpty()) {
                winner = "No Result";
            }
            win.put(winner, win.getOrDefault(winner, 0) + 1);
        }
        System.out.println(win);
    }

    public static void calculateExtraRunsByTeamFor2016(List<Match> matches, List<Delivery> deliveries) {
        Set<Integer> matchIds2016 = new HashSet<>();
        for (Match match : matches) {
            if (match.getSeason() == 2016) {
                matchIds2016.add(match.getId());
            }
        }

        Map<String, Integer> extraRunMap = new HashMap<>();
        for (Delivery delivery : deliveries) {
            if (matchIds2016.contains(delivery.getMatchId())) {
                extraRunMap.put(delivery.getBowlingTeam(),
                        extraRunMap.getOrDefault(delivery.getBowlingTeam(), 0) + delivery.getExtraRuns());
            }
        }
        System.out.println(extraRunMap);
    }

    public static void findTopEconomicalBowlersFor2015(List<Match> matches, List<Delivery> deliveries) {

        Set<Integer> matchIds2015 = new HashSet<>();
        for(Match match : matches){
            if(match.getSeason() == 2015){
                matchIds2015.add(match.getId());
            }
        }
        Map<String, Integer> legalDeliveries = new HashMap<>();
        Map<String,Integer> runConceded = new HashMap<>();

        for(Delivery delivery: deliveries){
            if(matchIds2015.contains(delivery.getMatchId())){
                String bowler = delivery.getBowler();
                int totalRuns =delivery.getTotalRuns();
                int wides = delivery.getWideRuns();
                int noBall = delivery.getNoBallRuns();

                runConceded.put(bowler, runConceded.getOrDefault(bowler,0)+ totalRuns);
                if(wides==0 && noBall==0){
                    legalDeliveries.put(bowler, legalDeliveries.getOrDefault(bowler,0)+1);
                }
            }
        }
        Map<String,Double> economy =new HashMap<>();

        for(String bowler: runConceded.keySet()){
            Integer runs = runConceded.get(bowler);
            Integer balls = legalDeliveries.get(bowler);

            if(balls>0){
                Double overs = balls /6.0;
                Double ecorate = runs/overs;
                economy.put(bowler,ecorate);
            }
        }
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(economy.entrySet());
        sorted.sort(Map.Entry.comparingByValue()); // ascending order

        Map<String, Double> top10 = new LinkedHashMap<>();
        sorted.stream().limit(10).forEach(entry -> top10.put(entry.getKey(), entry.getValue()));

        System.out.println(top10);
    }

    public static void calculateWinPercentagePerTeam(List<Match> matches) {
        Map<String, Integer> matchPlayed = new HashMap<>();
        Map<String, Integer>  matchWon = new HashMap<>();

        for(Match match : matches){
            String team1= match.getTeam1();
            String team2 = match.getTeam2();
            String winner = match.getWinner();
            matchPlayed.put(team1, matchPlayed.getOrDefault(team1,0)+1);
            matchPlayed.put(team2, matchPlayed.getOrDefault(team2,0)+1);
            if(winner != null && (!winner.trim().isEmpty())){
                matchWon.put(winner,matchWon.getOrDefault(winner,0)+1);
            }
        }
        Map<String, Double> winPercentage = new HashMap<>();
        for(String team: matchPlayed.keySet()){
            int played = matchPlayed.get(team);
            int won = matchWon.getOrDefault(team,0);
            double percentage = (played>0)?(won*100.0/played) :0.0;
            winPercentage.put(team,percentage);
        }
        System.out.println(winPercentage);
    }

    public static void findTopWicketTakerPerSeason(List<Match> matches, List<Delivery> deliveries) {

        Map<Integer,Integer> MatchByYear= new HashMap<>();
        for(Match match: matches){
            MatchByYear.put(match.getId(),match.getSeason());
        }
        Map<Integer,Map<String,Integer>> wicketTaker = new HashMap<>();
        for(Delivery delivery: deliveries){
            if(delivery.getPlayerDismissed() != null && !delivery.getPlayerDismissed().trim().isEmpty()){
                int season  = MatchByYear.get(delivery.getMatchId());
                String bowler = delivery.getBowler();
                wicketTaker.putIfAbsent(season, new HashMap<>());
                Map<String, Integer> bowlerMap = wicketTaker.get(season);
                bowlerMap.put(bowler,bowlerMap.getOrDefault(bowler,0)+1);
            }
        }
        Map<Integer,String> topWicket = new TreeMap<>();
        for(Map.Entry<Integer,Map<String,Integer>>  seasonEntry : wicketTaker.entrySet()){
            int season = seasonEntry.getKey();
            Map<String,Integer> bowlerWicket = seasonEntry.getValue();
            String topBowler = null;
            int maxWickets = 0;

            for(Map.Entry<String ,Integer> entry : bowlerWicket.entrySet()){
                if(entry.getValue()>maxWickets){
                    maxWickets = entry.getValue();
                    topBowler = entry.getKey();
                }
            }
            topWicket.put(season, topBowler + " (" + maxWickets + " wickets)");
        }
        System.out.println(topWicket);
    }

    public static void numberOfMatchesPlayedEachCity(List<Match> matches) {

        Map<String, Integer> matchesPlayedCity = new TreeMap<>();
        for(Match match: matches){
            String city = match.getCity();
            if(city != null && !city.trim().isEmpty()){
                matchesPlayedCity.put(city,matchesPlayedCity.getOrDefault(city,0)+1);
            }
        }
        System.out.println(matchesPlayedCity);
    }

    public static void wonTossAndWonMatch(List<Match> matches) {

        Map<String,Integer> matchKey = new TreeMap<>();
        for(Match match:matches){
            String matchWin = match.getWinner();
            String tossWin = match.getTossWinner();
            if(tossWin != null && tossWin.equalsIgnoreCase(matchWin)){
                matchKey.put(matchWin,matchKey.getOrDefault(matchWin,0)+1);
            }
        }

        System.out.println(matchKey);
    }

    public static void getMostFrequentBowlerBatsmanPair(List<Delivery> deliveries) {

        Map<String,Map<String,Integer>> bowlerName = new HashMap<>();
        for(Delivery delivery:deliveries){
            String bowler = delivery.getBowler();
            String batsmanOut = delivery.getPlayerDismissed();
            String dismissedType = delivery.getDismissalKind();

            if(batsmanOut!= null && !batsmanOut.trim().isEmpty()
                    && dismissedType!=null
                    && !dismissedType.equalsIgnoreCase("run out")
                    && !dismissedType.equalsIgnoreCase("retired hurt")) {
                bowlerName.putIfAbsent(bowler, new HashMap<>());
                Map<String, Integer> batsmanMap = bowlerName.get(bowler);

                batsmanMap.put(batsmanOut, batsmanMap.getOrDefault(batsmanOut, 0) + 1);
            }
        }
        String topDismissalPair = null;
        int maxDismissals=0;

        for(Map.Entry<String,Map<String,Integer>> bowlerEntry :bowlerName.entrySet() ){
            String bowlerWik =bowlerEntry.getKey();
            for(Map.Entry<String,Integer> batsmanEntry : bowlerEntry.getValue().entrySet()){
                if(batsmanEntry.getValue()>maxDismissals){
                    maxDismissals=batsmanEntry.getValue();
                    topDismissalPair= bowlerWik+" Dismissed "+ batsmanEntry.getKey();

                }
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put(topDismissalPair, maxDismissals);
        System.out.println(result);

    }

    public static void mostFiveWicketsHauls(List<Delivery> deliveries) {
        Map<Integer, Map<String,Integer>> wicketsPerMatch = new HashMap<>();

        for (Delivery delivery:deliveries){
            String bowler = delivery.getBowler();
            String BatsmanName = delivery.getPlayerDismissed();
            String DissimisalType = delivery.getPlayerDismissed();

            if(BatsmanName != null && DissimisalType != null
                    && !BatsmanName.trim().isEmpty()
                    && !DissimisalType.equalsIgnoreCase(" run out")
                    && !DissimisalType.equalsIgnoreCase("retired hurt")){

                int MatchId = delivery.getMatchId();
                wicketsPerMatch.putIfAbsent(MatchId,new HashMap<>());
                Map<String,Integer> bowlerMap = wicketsPerMatch.get(MatchId);
                bowlerMap.put(bowler,bowlerMap.getOrDefault(bowler,0)+1);
            }
        }
        Map<String, Integer> fiveWicketsHaul = new HashMap<>();
        for(Map<String,Integer> bowlerMap : wicketsPerMatch.values()){
            for(Map.Entry<String,Integer> entry : bowlerMap.entrySet()){
                if(entry.getValue()>=5){
                    String bowler = entry.getKey();
                    fiveWicketsHaul.put(bowler,fiveWicketsHaul.getOrDefault(bowler,0)+1);
                }
            }
        }
        System.out.println(fiveWicketsHaul);
    }

    public static void mostPlayerOfMatchAward(List<Match> matches) {

        Map<String, Integer> playerAwardCount = new HashMap<>();
        for (Match match : matches) {
            String playerOfMatch = match.getPlayerOfMatch();
            if (playerOfMatch != null && !playerOfMatch.trim().isEmpty())
                playerAwardCount.put(playerOfMatch, playerAwardCount.getOrDefault(playerOfMatch, 0) + 1);
        }
        String topPlayer = null;
        int maxAwards=0;
        for(Map.Entry<String,Integer> entry:playerAwardCount.entrySet()){
            if(entry.getValue()>maxAwards){
                maxAwards=entry.getValue();
                topPlayer =entry.getKey();
            }
        }
        Map<String,Integer> result = new HashMap<>();
        if(topPlayer !=null){
            result.put(topPlayer,maxAwards);
        }

        System.out.println(result);
    }

    public static void findTopRunScorer(List<Delivery> deliveries) {
        Map<String, Integer> runsByPlayer = new HashMap<>();
        for(Delivery delivery: deliveries){
            String player = delivery.getBatsman();

            if(player != null && !player.trim().isEmpty()){
                int runs = delivery.getBatsmanRuns();
                runsByPlayer.put(player,runsByPlayer.getOrDefault(player,0)+runs);
            }
        }
            String topPlayer = null;
            int maxScore = 0;
        for(Map.Entry<String,Integer> entry : runsByPlayer.entrySet()){
            if(entry.getValue()>maxScore){
                maxScore= entry.getValue();
                topPlayer= entry.getKey();
            }
        }
        System.out.println(topPlayer+" "+maxScore);


    }

    public static void findBestEconomyBowlerInPowerplay(List<Delivery> deliveries) {
        Map<String,Map<String,Integer>> bowlerStatsMap = new HashMap<>();

        for(Delivery delivery: deliveries){
            int over = delivery.getOver();
            if(over>=1 && over<=6){
                String bowlerName = delivery.getBowler();
                int runs = delivery.getTotalRuns();
                bowlerStatsMap.putIfAbsent(bowlerName,new HashMap<>());
                Map<String,Integer> stats = bowlerStatsMap.get(bowlerName);

                stats.put("runs",stats.getOrDefault("runs",0)+runs);
                stats.put("balls",stats.getOrDefault("balls",0)+1);
            }
        }
           String bestBowler = null;
           Double bestEconomy =Double.MAX_VALUE;

            for(Map.Entry<String,Map<String,Integer>> entry: bowlerStatsMap.entrySet()){
                String Bowler = entry.getKey();
                int runs = entry.getValue().getOrDefault("runs",0);
                int balls = entry.getValue().getOrDefault("balls",0);

                if(balls>=12){
                    Double overs = balls/6.0;
                    Double economy = runs/overs;
                    if(economy<bestEconomy){
                        bestEconomy=economy;
                        bestBowler=Bowler;
                    }
                }
            }
            System.out.println(bestBowler+" "+ bestEconomy);
    }

    public static void findFinalMatchPerSeason(List<Match> matches) {

        Map<Integer,Match> finalMatchesBySeason = new HashMap<>();
        for(Match match:matches){
            int season = match.getSeason();

            if(!finalMatchesBySeason.containsKey(season) || match.getId()>finalMatchesBySeason.get(season).getId()){
                finalMatchesBySeason.put(season,match);
            }
        }
        Map<Integer,Map<String,String>> finalMatchDetailsBySeason = new TreeMap<>();
        for(Map.Entry<Integer,Match> entry:finalMatchesBySeason.entrySet()){
            int Season = entry.getKey();
            Match finalMatch = entry.getValue();

            Map<String,String> details = new HashMap<>();
            details.put("winner",finalMatch.getWinner());
            details.put("venue",finalMatch.getVenue());
            details.put("pom",finalMatch.getPlayerOfMatch());

            finalMatchDetailsBySeason.put(Season,details);
        }
        System.out.println(finalMatchDetailsBySeason);
    }

    public static void countSixesByPlayer(List<Delivery> deliveries) {
        Map<String , Integer> sixesByBatsman = new HashMap<>();
        for(Delivery delivery:deliveries){
            String Batsman = delivery.getBatsman();
            int runs = delivery.getBatsmanRuns();
            if (runs == 6) {

                sixesByBatsman.put(Batsman,sixesByBatsman.getOrDefault(Batsman,0)+1);
            }
        }
        System.out.println(sixesByBatsman);
    }

    public static void countTossWinsPerTeam(List<Match> matches) {
        Map<String, Integer> tossWinsByTeam = new HashMap<>();
        for(Match match: matches){
        String tossWinner = match.getTossWinner();
            tossWinsByTeam.put(tossWinner,tossWinsByTeam.getOrDefault(tossWinner,0)+1);
        }
        System.out.println(tossWinsByTeam);

    }

    public static void findTopRunScorerPerSeason(List<Match> matches, List<Delivery> deliveries) {

      Map<Integer,Integer> matchToSeason = new HashMap<>();
      for(Match match: matches){
          matchToSeason.put(match.getId(), match.getSeason());
      }
        Map<Integer, Map<String,Integer>> runsBySeason = new TreeMap<>();
      for(Delivery delivery:deliveries){
          int matchId = delivery.getMatchId();
          int season = matchToSeason.get(matchId);
          String batsman = delivery.getBatsman();
          int runs = delivery.getBatsmanRuns();

          runsBySeason.putIfAbsent(season, new HashMap<>());
          Map<String,Integer> batsmanRuns = runsBySeason.get(season);

          batsmanRuns.put(batsman,batsmanRuns.getOrDefault(batsman,0)+runs);
      }
        for(Map.Entry<Integer,Map<String,Integer>> seasonEntry : runsBySeason.entrySet()){
            int season = seasonEntry.getKey();
            String topBatsman = null;
            int highestRuns = 0;
            System.out.println(season);
            for(Map.Entry<String,Integer> batsmanEntry : seasonEntry.getValue().entrySet()){
                if(batsmanEntry.getValue()>highestRuns){
                    highestRuns=batsmanEntry.getValue();
                    topBatsman= batsmanEntry.getKey();
                }
            }
                System.out.println(topBatsman+" "+ highestRuns);
        }
    }

    public static void findTeamWithMostBoundariesPerSeason(List<Match> matches, List<Delivery> deliveries) {
        Map<Integer,Integer> matchIdSeason = new HashMap<>();
        for(Match match: matches){
            matchIdSeason.put(match.getId(), match.getSeason());
        }

        Map<Integer,Map<String,Integer>> boundariesSeason = new HashMap<>();

        for(Delivery delivery:deliveries){
        int runs = delivery.getBatsmanRuns();
        if(runs ==4 || runs==6){
            int season = matchIdSeason.get(delivery.getMatchId());
            String team = delivery.getBattingTeam();

            boundariesSeason.putIfAbsent(season, new HashMap<>());
            Map<String,Integer> boundaries = boundariesSeason.get(season);
            boundaries.put(team,boundaries.getOrDefault(team,0)+1);
            }
        }
        for(Map.Entry<Integer,Map<String,Integer>> entry : boundariesSeason.entrySet()){
            int season = entry.getKey();
            Map<String,Integer> sessionBoundaries = entry.getValue();

            String teamName = null;
            int boundaries = 0;

            for(Map.Entry<String,Integer> entry1 : sessionBoundaries.entrySet()){
                if(entry1.getValue()>boundaries){
                    boundaries=entry1.getValue();
                    teamName= entry1.getKey();
                }
            }
            System.out.println(season+" "+teamName+" "+ boundaries);
        }
    }


}
