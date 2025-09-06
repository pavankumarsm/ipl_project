package ipl;

import java.util.*;

public class MatchesAnalysis {

    // Matches Per Year
    public static Map<Integer, Integer> matchesPerYear(List<Matches> matches) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Matches m : matches) {
            map.put(m.getSeason(), map.getOrDefault(m.getSeason(), 0) + 1);
        }
        return map;
    }

    //Matches Win Per Team
    public static Map<String, Integer> matchesWinPerTeam(List<Matches> matches) {
        Map<String, Integer> win = new HashMap<>();
        for (Matches m : matches) {
            String winner = m.getWinner();
            if (winner == null || winner.isEmpty()) {
                winner = "No Result";
                }
            win.put(winner, win.getOrDefault(winner, 0) + 1);
        }
        return win;
    }

    //Extra Runs 2016
    public static Map<String, Integer> extraRuns2016(List<Matches> matches, List<Deliveries> deliveries) {
        Set<Integer> matchIds2016 = new HashSet<>();
        for (Matches m : matches) {
            if (m.getSeason() == 2016) {
                matchIds2016.add(m.getId());
            }
        }

        Map<String, Integer> extraRunMap = new HashMap<>();
        for (Deliveries d : deliveries) {
            if (matchIds2016.contains(d.getMatchId())) {
                extraRunMap.put(d.getBowlingTeam(),
                extraRunMap.getOrDefault(d.getBowlingTeam(), 0) + d.getExtraRuns());
            }
        }
        return extraRunMap;
    }
    // Top Economical Bowler 2015
    public static Map<String, Double> topEconomicalBowlers2015(List<Matches> matches, List<Deliveries> deliveries) {

        Set<Integer> matchIds2015 = new HashSet<>();
        for(Matches m : matches){
            if(m.getSeason() == 2015){
                matchIds2015.add(m.getId());
            }
        }

        Map<String, Integer> legalDeliveries = new HashMap<>();
        Map<String,Integer> runConceded = new HashMap<>();

        for(Deliveries d: deliveries){
            if(matchIds2015.contains(d.getMatchId())){
                String bowler = d.getBowler();
                int totalruns =d.getTotalRuns();
                int wides = d.getWideRuns();
                int noBall = d.getNoBall();

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

        return top10;
    }

    //Win percentage Team Per Season

    public static Map<String, Double> winPercentageTeam(List<Matches> matches) {

        Map<String, Integer> matchPlayed = new HashMap<>();
        Map<String, Integer>  matchWon = new HashMap<>();

        for(Matches m : matches){
            String team1= m.getTeam1();
            String team2 = m.getTeam2();
            String winner = m.getWinner();
        //Matched played
         matchPlayed.put(team1, matchPlayed.getOrDefault(team1,0)+1);
         matchPlayed.put(team2, matchPlayed.getOrDefault(team2,0)+1);

         //Count the win Match
         if(winner != null && (!winner.trim().isEmpty())){
             matchWon.put(winner,matchWon.getOrDefault(winner,0)+1);
         }

        }
        //Calculate
        Map<String, Double> winPercentage = new HashMap<>();
        for(String team: matchPlayed.keySet()){
            int played = matchPlayed.get(team);
            int won = matchWon.getOrDefault(team,0);
            double percentage = (played>0)? (won*100.0/played) :0.0;
            winPercentage.put(team,percentage);
        }


        return winPercentage;
    }

// Top Wicket Taker Per Season
    public static Map<Integer, String> topWicketTakerPerSeason(List<Matches> matches, List<Deliveries> deliveries) {

        Map<Integer,Integer> MatchByYear= new HashMap<>();
        for(Matches m: matches){
            MatchByYear.put(m.getId(),m.getSeason());
        }
            //track wicket per season
        Map<Integer,Map<String,Integer>> wicketTaker = new HashMap<>();
        for(Deliveries d: deliveries){
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
        return topWicket;

    }

    public static Map<String, Integer> numberOfMatchesPlayedEachCity(List<Matches> matches) {

        Map<String, Integer> matchesPlayedCity = new TreeMap<>();
        for(Matches m: matches){
            String city = m.getCity();
            if(city != null && !city.trim().isEmpty()){
                matchesPlayedCity.put(city,matchesPlayedCity.getOrDefault(city,0)+1);
            }
        }
        return matchesPlayedCity;
    }

    public static Map<String, Integer> wonTossAndWonMatch(List<Matches> matches) {

        Map<String,Integer> matchKey = new TreeMap<>();
        for(Matches m:matches){
            String matchWin = m.getWinner();
            String tossWin = m.getTossWinner();
            if(tossWin != null && tossWin.equalsIgnoreCase(matchWin)){
                matchKey.put(matchWin,matchKey.getOrDefault(matchWin,0)+1);
            }
        }

        return matchKey;
    }

    public static Map<String, Integer> findMostFrequentDismissalPair(List<Deliveries> deliveries) {

        Map<String,Map<String,Integer>> bowlerName = new HashMap<>();
        for(Deliveries d:deliveries){
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
        return result;

    }

    public static Map<String, Integer> mostWickets5Hauls(List<Deliveries> deliveries) {
      Map<Integer, Map<String,Integer>> wicketsPerMatch = new HashMap<>();

      for (Deliveries d:deliveries){
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
        return fiveWicketsHaul;
    }

    public static Map<String, Integer> mostPlayerOfMatchAward(List<Matches> matches) {

        Map<String, Integer> playerAwardCount = new HashMap<>();
        for (Matches match : matches) {
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

       return result;
    }
}