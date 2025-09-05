package ipl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvReader {
    private static final String MATCHES_PATH = "src/Resources/matches.csv";
    private static final String DELIVERIES_PATH = "src/Resources/deliveries.csv";

    public static List<Matches> getMatches() {
        List<Matches> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MATCHES_PATH))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Matches m = new Matches();
                m.setId(Integer.parseInt(parts[0]));
                m.setSeason(Integer.parseInt(parts[1]));
                m.setCity(parts[2]);
                m.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(parts[3]));
                m.setTeam1(parts[4]);
                m.setTeam2(parts[5]);
                m.setTossWinner(parts[6]);
                m.setTossDecision(parts[7]);
                m.setResult(parts[8]);
                m.setDlApplied(parts[9].equals("1"));
                m.setWinner(parts[10]);
                m.setWinByRuns(Integer.parseInt(parts[11]));
                m.setWinByWickets(Integer.parseInt(parts[12]));
                m.setPlayerOfMatch(parts[13]);
                m.setVenue(parts[14]);
                m.setUmpire1(parts[15]);
                m.setUmpire2(parts[16]);
                m.setUmpire3(parts[17]);
                list.add(m);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Deliveries> getDeliveries() {
        List<Deliveries> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DELIVERIES_PATH))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Deliveries d = new Deliveries();
                d.setMatchId(Integer.parseInt(parts[0]));
                d.setInning(Integer.parseInt(parts[1]));
                d.setBattingTeam(parts[2]);
                d.setBowlingTeam(parts[3]);
                d.setOver(Integer.parseInt(parts[4]));
                d.setBall(Integer.parseInt(parts[5]));
                d.setBatsman(parts[6]);
                d.setNonStriker(parts[7]);
                d.setBowler(parts[8]);
                d.setSuperOver(parts[9].equals("1"));
                d.setWideRuns(Integer.parseInt(parts[10]));
                d.setByeRuns(Integer.parseInt(parts[11]));
                d.setLegByeRuns(Integer.parseInt(parts[12]));
                d.setNoBall(Integer.parseInt(parts[13]));
                d.setPenaltyRuns(Integer.parseInt(parts[14]));
                d.setBatsmanRuns(Integer.parseInt(parts[15]));
                d.setExtraRuns(Integer.parseInt(parts[16]));
                d.setTotalRuns(Integer.parseInt(parts[17]));
                d.setPlayerDismissed(parts[18]);
                d.setDismissalKind(parts[19]);
                d.setFielder(parts[20]);
                list.add(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
