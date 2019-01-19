package com.example.amandaungco.buynothingmatcher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Match {

    String title;
    int requestId;
    int offerId;
    String distance;
    int matchId;
    String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static Match makeSingleMatchFromJSON(JSONObject dataForMatchFromJSON) throws JSONException {
        Match itemMatch = new Match();

        itemMatch.setDistance(dataForMatchFromJSON.getString("distance"));
        itemMatch.setMatchId(dataForMatchFromJSON.getInt("id"));
//        itemMatch.setRequestId(dataForMatchFromJSON.getInt("request_id"));
//        itemMatch.setOfferId(dataForMatchFromJSON.getInt("offer_id"));
        itemMatch.setStatus(dataForMatchFromJSON.getString("status"));


        return itemMatch;
    };

    public static ArrayList<Match> makeItemMatchesFromJSON(JSONArray jsonMatches) throws JSONException {
        ArrayList <Match> matches = new ArrayList<>();

        for (int i = 0 ; i < jsonMatches.length(); i++) {
            JSONObject singleJSONMatch = jsonMatches.getJSONObject(i);
            matches.add(makeSingleMatchFromJSON(singleJSONMatch));

        }
        return matches;
    }
        
}

