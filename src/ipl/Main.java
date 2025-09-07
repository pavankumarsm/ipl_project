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
           extraRuns2016(matches, deliveries);
           topEconomicalBowlers2015(matches, deliveries);
           winPercentageTeam(matches);
           topWicketTakerPerSeason(matches, deliveries);
           numberOfMatchesPlayedEachCity(matches);
           wonTossAndWonMatch(matches);
           findMostFrequentDismissalPair(deliveries);
           mostWickets5Hauls(deliveries);
           mostPlayerOfMatchAward(matches);
           mostRunScorePlayer(deliveries);
           mostEconomyBowlerInPowerPlay(deliveries);
           everyYearFinalMatch(matches);
           sixByPlayer(deliveries);
           numberOfEachTimeWinToss(matches);
           mostRunScorePlayerPeryear(matches,deliveries);
           mostBoundariesPerTeamPerSeason(matches,deliveries);
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
        for (Match m : matches) {
            String winner = m.getWinner();
            if (winner == null || winner.isEmpty()) {
                winner = "No Result";
            }
            win.put(winner, win.getOrDefault(winner, 0) + 1);
        }
        System.out.println(win);
    }

    public static void extraRuns2016(List<Match> matches, List<Delivery> deliveries) {
        Set<Integer> matchIds2016 = new HashSet<>();
        for (Match m : matches) {
            if (m.getSeason() == 2016) {
                matchIds2016.add(m.getId());
            }
        }

        Map<String, Integer> extraRunMap = new HashMap<>();
        for (Delivery d : deliveries) {
            if (matchIds2016.contains(d.getMatchId())) {
                extraRunMap.put(d.getBowlingTeam(),
                        extraRunMap.getOrDefault(d.getBowlingTeam(), 0) + d.getExtraRuns());
            }
        }
        System.out.println(extraRunMap);
    }

    public static void topEconomicalBowlers2015(List<Match> matches, List<Delivery> deliveries) {

        Set<Integer> matchIds2015 = new HashSet<>();
        for(Match m : matches){
            if(m.getSeason() == 2015){
                matchIds2015.add(m.getId());
            }
        }

        Map<String, Integer> legalDeliveries = new HashMap<>();
        Map<String,Integer> runConceded = new HashMap<>();

        for(Delivery d: deliveries){
            if(matchIds2015.contains(d.getMatchId())){
                String bowler = d.getBowler();
                int totalruns =d.getTotalRuns();
                int wides = d.getWideRuns();
                int noBall = d.getNoBallRuns();

                runConceded.put(bowler, runConceded.getOrDefault(bowler,0)+ totalruns);
                if(wides==0 && noBall==0){
                    legalDeliveries.put(bowler, legalDeliveries.getOrDefault(bowler,0)+1);
                }
            }
        }
        // finding economy of the bowler
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
        //Sort
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(economy.entrySet());
        sorted.sort(Map.Entry.comparingByValue()); // ascending order

        Map<String, Double> top10 = new LinkedHashMap<>();
        sorted.stream().limit(10).forEach(entry -> top10.put(entry.getKey(), entry.getValue()));

        System.out.println(top10);
    }

    public static void winPercentageTeam(List<Match> matches) {
        Map<String, Integer> matchPlayed = new HashMap<>();
        Map<String, Integer>  matchWon = new HashMap<>();

        for(Match m : matches){
            String team1= m.getTeam1();
            String team2 = m.getTeam2();
            String winner = m.getWinner();
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
            double percentage = (played>0)? (won*100.0/played) :0.0;
            winPercentage.put(team,percentage);
        }
        System.out.println(winPercentage);
    }

    public static void topWicketTakerPerSeason(List<Match> matches, List<Delivery> deliveries) {

        Map<Integer,Integer> MatchByYear= new HashMap<>();
        for(Match m: matches){
            MatchByYear.put(m.getId(),m.getSeason());
        }
        //track wicket per season
        Map<Integer,Map<String,Integer>> wicketTaker = new HashMap<>();
        for(Delivery d: deliveries){
            if(d.getPlayerDismissed() != null && !d.getPlayerDismissed().trim().isEmpty()){
                int season  = MatchByYear.get(d.getMatchId());
//                System.out.print(bowler);
                String bowler = d.getBowler();
                // if season not present, create new map
                wicketTaker.putIfAbsent(season, new HashMap<>());
                Map<String, Integer> bowlerMap = wicketTaker.get(season);
//
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
        for(Match m: matches){
            String city = m.getCity();
            if(city != null && !city.trim().isEmpty()){
                matchesPlayedCity.put(city,matchesPlayedCity.getOrDefault(city,0)+1);
            }
        }
        System.out.println(matchesPlayedCity);
    }

    public static void wonTossAndWonMatch(List<Match> matches) {

        Map<String,Integer> matchKey = new TreeMap<>();
        for(Match m:matches){
            String matchWin = m.getWinner();
            String tossWin = m.getTossWinner();
            if(tossWin != null && tossWin.equalsIgnoreCase(matchWin)){
                matchKey.put(matchWin,matchKey.getOrDefault(matchWin,0)+1);
            }
        }

        System.out.println(matchKey);
    }

    public static void findMostFrequentDismissalPair(List<Delivery> deliveries) {

        Map<String,Map<String,Integer>> bowlerName = new HashMap<>();
        for(Delivery d:deliveries){
            String bowler = d.getBowler();
            String batsmanOut = d.getPlayerDismissed();
            String dismissedType = d.getDismissalKind();

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

    public static void mostWickets5Hauls(List<Delivery> deliveries) {
        Map<Integer, Map<String,Integer>> wicketsPerMatch = new HashMap<>();

        for (Delivery d:deliveries){
            String bowler = d.getBowler();
            String BatsmanName = d.getPlayerDismissed();
            String DissimisalType = d.getPlayerDismissed();

            if(BatsmanName != null && DissimisalType != null
                    && !BatsmanName.trim().isEmpty()
                    && !DissimisalType.equalsIgnoreCase(" run out")
                    && !DissimisalType.equalsIgnoreCase("retired hurt")){

                int MatchId = d.getMatchId();
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

    public static void mostRunScorePlayer(List<Delivery> deliveries) {
        Map<String, Integer> runsByPlayer = new HashMap<>();
        for(Delivery d: deliveries){
            String player = d.getBatsman();

            if(player != null && !player.trim().isEmpty()){
                int runs = d.getBatsmanRuns();
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

    public static void mostEconomyBowlerInPowerPlay(List<Delivery> deliveries) {
        Map<String,Map<String,Integer>> runsByPlayer = new HashMap<>();

        for(Delivery d: deliveries){
            int over = d.getOver();
            if(over>=1 && over<=6){
                String bowlerName = d.getBowler();
                int runs = d.getTotalRuns();
                runsByPlayer.putIfAbsent(bowlerName,new HashMap<>());
                Map<String,Integer> stats = runsByPlayer.get(bowlerName);

                stats.put("runs",stats.getOrDefault("runs",0)+runs);
                stats.put("balls",stats.getOrDefault("balls",0)+1);
            }
        }
           String bestBowler = null;
           Double runconced =Double.MAX_VALUE;

            for(Map.Entry<String,Map<String,Integer>> entry: runsByPlayer.entrySet()){
                String Bowler = entry.getKey();
                int runs = entry.getValue().getOrDefault("runs",0);
                int balls = entry.getValue().getOrDefault("balls",0);

                if(balls>=12){
                    Double overs = balls/6.0;
                    Double economy = runs/overs;
                    if(economy<runconced){
                        runconced=economy;
                        bestBowler=Bowler;
                    }
                }
            }
            System.out.println(bestBowler+" "+ runconced);
    }

    public static void everyYearFinalMatch(List<Match> matches) {

        Map<Integer,Match> finalMatches = new HashMap<>();
        for(Match match:matches){
            int season = match.getSeason();

            if(!finalMatches.containsKey(season) || match.getId()>finalMatches.get(season).getId()){
                finalMatches.put(season,match);
            }
        }
        Map<Integer,Map<String,String>> matchSeason = new TreeMap<>();
        for(Map.Entry<Integer,Match> entry:finalMatches.entrySet()){
            int Season = entry.getKey();
            Match finalMatch = entry.getValue();

            Map<String,String> details = new HashMap<>();
            details.put("winner",finalMatch.getWinner());
            details.put("venue",finalMatch.getVenue());
            details.put("pom",finalMatch.getPlayerOfMatch());

            matchSeason.put(Season,details);
        }
        System.out.println(matchSeason);
    }

    public static void sixByPlayer(List<Delivery> deliveries) {
        Map<String , Integer> player = new HashMap<>();
        for(Delivery d:deliveries){
            String Batsman = d.getBatsman();
            int runs = d.getBatsmanRuns();
            if (runs == 6) {

                player.put(Batsman,player.getOrDefault(Batsman,0)+1);
            }
        }
        System.out.println(player);
    }

    public static void numberOfEachTimeWinToss(List<Match> matches) {
        Map<String, Integer> teamName = new HashMap<>();
        for(Match match: matches){
        String tossWinner = match.getTossWinner();
        teamName.put(tossWinner,teamName.getOrDefault(tossWinner,0)+1);
        }
        System.out.println(teamName);

    }

    public static void mostRunScorePlayerPeryear(List<Match> matches, List<Delivery> deliveries) {

      Map<Integer,Integer> matchToSeason = new HashMap<>();
      for(Match match: matches){
          matchToSeason.put(match.getId(), match.getSeason());
      }
        Map<Integer, Map<String,Integer>> runsByYear = new TreeMap<>();
      for(Delivery d:deliveries){
          int matchId = d.getMatchId();
          int season = matchToSeason.get(matchId);
          String batsman = d.getBatsman();
          int runs = d.getBatsmanRuns();

          runsByYear.putIfAbsent(season, new HashMap<>());
          Map<String,Integer> batsmanRuns = runsByYear.get(season);

          batsmanRuns.put(batsman,batsmanRuns.getOrDefault(batsman,0)+runs);
      }
        for(Map.Entry<Integer,Map<String,Integer>> seasonEntry : runsByYear.entrySet()){
            int season = seasonEntry.getKey();
            String BatsmanName = null;
            int maxScore = 0;
            System.out.println(season);
            for(Map.Entry<String,Integer> batsmanEntry : seasonEntry.getValue().entrySet()){
                if(batsmanEntry.getValue()>maxScore){
                    maxScore=batsmanEntry.getValue();
                    BatsmanName= batsmanEntry.getKey();
                }
            }
                System.out.println(BatsmanName+" "+ maxScore);
        }
    }

    public static void mostBoundariesPerTeamPerSeason(List<Match> matches, List<Delivery> deliveries) {
        Map<Integer,Integer> matchIdSeason = new HashMap<>();
        for(Match match: matches){
            matchIdSeason.put(match.getId(), match.getSeason());
        }

        Map<Integer,Map<String,Integer>> boundariesSeason = new HashMap<>();

        for(Delivery d:deliveries){
        int runs = d.getBatsmanRuns();
        if(runs ==4 || runs==6){
            int season = matchIdSeason.get(d.getMatchId());
            String team = d.getBattingTeam();

            boundariesSeason.putIfAbsent(season, new HashMap<>());
            Map<String,Integer> boundaries = boundariesSeason.get(season);
            boundaries.put(team,boundaries.getOrDefault(team,0)+1);
            }
        }
        for(Map.Entry<Integer,Map<String,Integer>> entry : boundariesSeason.entrySet()){
            int season = entry.getKey();
            Map<String,Integer> sessionBoundries = entry.getValue();

            String teamName = null;
            int boundires = 0;

            for(Map.Entry<String,Integer> entry1 : sessionBoundries.entrySet()){
                if(entry1.getValue()>boundires){
                    boundires=entry1.getValue();
                    teamName= entry1.getKey();
                }
            }
            System.out.println(season+" "+teamName+" "+ boundires);
        }
    }


}
