package com.zippitybippity.kevinski.scorekeeperpartdeux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int clickedBtnID = 0;
    boolean scoredTDHome = false;
    boolean scoredTDAway = false;
    boolean isHome; // wanted to keep it local/inside the function/method, but i would have had to initialize, which would not make sense.
    boolean isPenalty;
    boolean resIDsPulled = false;

    int homeScore = 0;
    int homePenaltyYards = 0;
//    TextView txtHomeTeam;
    TextView txtHomeScore;
    Button btnHomeTD;
    Button btnHomeXP;
    Button btnHomeFG;
    Button btnHomeSafety;
    TextView txtHomePenaltyYds;
    Button btnHome5YP;
    Button btnHome10YP;
    Button btnHome15YP;

    int awayScore = 0;
    int awayPenaltyYards = 0;
//    TextView txtAwayTeam;
    TextView txtAwayScore;
    Button btnAwayTD;
    Button btnAwayXP;
    Button btnAwayFG;
    Button btnAwaySafety;

    TextView txtAwayPenaltyYds;
    Button btnAway5YP;
    Button btnAway10YP;
    Button btnAway15YP;

    String strPenYards;

    boolean resetIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getResIDs() {

//        txtHomeTeam = findViewById(R.id.txtHomeTeam);
//        txtAwayTeam = findViewById(R.id.txtAwayTeam);
        txtHomeScore = findViewById(R.id.txtHomeScore);
        txtAwayScore = findViewById(R.id.txtAwayScore);
        btnHomeTD = findViewById(R.id.btnHomeTouchdown);
        btnAwayTD = findViewById(R.id.btnAwayTouchdown);
        btnHomeXP = findViewById(R.id.btnHomeXP);
        btnAwayXP = findViewById(R.id.btnAwayXP);
        btnHomeFG = findViewById(R.id.btnHomeFG);
        btnAwayFG = findViewById(R.id.btnAwayFG);
        btnHomeSafety = findViewById(R.id.btnHomeSafety);
        btnAwaySafety = findViewById(R.id.btnAwaySafety);

        txtHomePenaltyYds= findViewById(R.id.txtHomePenYards);
        btnHome5YP= findViewById(R.id.btnHomePen5Y);
        btnHome10YP= findViewById(R.id.btnHomePen10Y);
        btnHome15YP= findViewById(R.id.btnHomePen15Y);
        txtAwayPenaltyYds= findViewById(R.id.txtAwayPenYards);
        btnAway5YP= findViewById(R.id.btnAwayPen5Y);
        btnAway10YP= findViewById(R.id.btnAwayPen10Y);
        btnAway15YP= findViewById(R.id.btnAwayPen15Y);
        
        resIDsPulled=true;
//get resIDs and also set this variable/...constant :)
        strPenYards = getResources().getString(R.string.txtInitialCountPenYds);
        strPenYards = strPenYards.substring(strPenYards.lastIndexOf("0") + 1);
    }

    public void addPenaltyYards (View whatView){
        if (resIDsPulled==false){
            getResIDs();
        }

        clickedBtnID = whatView.getId();
        isPenalty=true;

        if (clickedBtnID == btnHome5YP.getId()) {
            isHome=true;
            homePenaltyYards+=5;
        } else if (clickedBtnID == btnAway5YP.getId()) {
            isHome=false;
            awayPenaltyYards+=5;
        } else if (clickedBtnID == btnHome10YP.getId()) {
            isHome=true;
            homePenaltyYards+=10;
        } else if (clickedBtnID == btnAway10YP.getId()) {
            isHome=false;
            awayPenaltyYards+=10;
        } else if (clickedBtnID == btnHome15YP.getId()) {
            isHome=true;
            homePenaltyYards+=15;
        } else if (clickedBtnID == btnAway15YP.getId()) {
            isHome=false;
            awayPenaltyYards+=15;
        }
        updateScore();
        isPenalty=false;
    }

    public void scorePoints(View whatView) {
        if (resIDsPulled == false) {
            getResIDs();
        }

        clickedBtnID = whatView.getId();

        if (clickedBtnID == btnHomeTD.getId()) {
            homeScore += 6;
            isHome = true;
            scoredTDHome = true;
            btnHomeXP.setEnabled(scoredTDHome);
            btnHomeXP.setTextColor(getResources().getColor(R.color.colorTextDark));
        } else if (clickedBtnID == btnAwayTD.getId()) {
            awayScore += 6;
            isHome = false;
            scoredTDAway = true;
            btnAwayXP.setEnabled(scoredTDAway);
            btnAwayXP.setTextColor(getResources().getColor(R.color.colorTextDark));

        // need a variable (?) which sets after touchdown is pressed which enables
        // the xp button, so it can only be pressed when a touchdown has been scored.
        // disable again after it is no longer "eligible."
        // done

        } else if (clickedBtnID == btnHomeXP.getId()) {
            if (scoredTDHome==true) {
                homeScore += 1;
                isHome = true;
                scoredTDHome = false;
                btnHomeXP.setEnabled(scoredTDHome);
                btnHomeXP.setTextColor(getResources().getColor(R.color.colorTextLight));
            }
        } else if (clickedBtnID == btnAwayXP.getId()) {
            if (scoredTDAway==true) {
                awayScore += 1;
                isHome = false;
                scoredTDAway = false;
                btnAwayXP.setEnabled(scoredTDAway);
                btnAwayXP.setTextColor(getResources().getColor(R.color.colorTextLight));
            }
        } else if (clickedBtnID == btnHomeFG.getId()) {
            homeScore += 3;
            isHome=true;
            scoredTDHome = false;
            btnHomeXP.setEnabled(scoredTDHome);
            btnHomeXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        } else if (clickedBtnID == btnAwayFG.getId()) {
            awayScore += 3;
            isHome=false;
            scoredTDAway = false;
            btnAwayXP.setEnabled(scoredTDAway);
            btnAwayXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        } else if (clickedBtnID == btnHomeSafety.getId()) {
            homeScore += 2;
            isHome=true;
            scoredTDHome = false;
            btnHomeXP.setEnabled(scoredTDHome);
            btnHomeXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        } else if (clickedBtnID == btnAwaySafety.getId()) {
            awayScore += 2;
            isHome=false;
            scoredTDAway = false;
            btnAwayXP.setEnabled(scoredTDAway);
            btnAwayXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        }
        // if isHome !=null???
        updateScore();

//
//        oh just hurry up and display the text already and stop fooling with this log crap
//
//        Log.i("MainActivity.java", "clickedBtnID: " + clickedBtnID);
//        Log.i("MainActivity.java", "HomeID: " + homeTD.getId());
//        Log.i("MainActivity.java", "AwayID: " + awayTD.getId());
//        Log.i("MainActivity.java", "homeScore: " + homeScore);
//        Log.i("MainActivity.java", "awayScore: " + awayScore);
//
//
//        HomeTeam
//        awayTeam
//        homeTD
//        awayTD
//        homeXP
//        awayXP
//        homeFG
//        awayFG
//        homeSafety
//        awaySafety
//
    }

    // you were right here: time to update the score on either the home or away textview
    // done

    private void updateScore () {
//        String strFullPenYards = getResources().getString(R.string.txtInitialCountPenYds);
//        String strPenYards = strFullPenYards.substring(strFullPenYards.lastIndexOf("0") + 2);
        if (resetIt==false) {
            if (isHome == true) {
                if (isPenalty == false) {
                    txtHomeScore.setText(Integer.toString(homeScore)); // if home team and not a penalty, update home score
                } else { // if a penalty on the home team, update their penalty yards
                    //                String strFullPenYards = getResources().getString(R.string.txtInitialCountPenYds);
                    //                String strPenYards = strFullPenYards.substring(strFullPenYards.lastIndexOf("0") + 2);
                    //              Toast.makeText(this, strPenYards, Toast.LENGTH_SHORT).show();
                    //
                    txtHomePenaltyYds.setText(Integer.toString(homePenaltyYards) + strPenYards);

                    //           penalties: model after scores. no problemo.
                    //           ok now you got the string presentation stuff figured out. now you need to go back up
                    //           to the addpenaltyyds function and work through each leg and also update updatescore
                    //           to include different possibilities of ishome and ispenalty, etc.
                    //

                    //           Toast.makeText(this, Integer.toString(homePenaltyYards) + " " + strPenYards, Toast.LENGTH_SHORT).show();
                }
            } else { // if away team
                if (isPenalty == false) { // if it's not a penalty, but a score
                    txtAwayScore.setText(Integer.toString(awayScore)); // update away score
                } else { // but if it *is* a penalty
                    txtAwayPenaltyYds.setText(Integer.toString(awayPenaltyYards) + strPenYards); // update their penalty yards
                }
            }
        } else {
            txtHomeScore.setText(getResources().getString(R.string.txtInitialCount));
            txtHomePenaltyYds.setText(getResources().getString(R.string.txtInitialCountPenYds));
            txtAwayScore.setText(getResources().getString(R.string.txtInitialCount));
            txtAwayPenaltyYds.setText(getResources().getString(R.string.txtInitialCountPenYds));
        }

    }

    public void startAfresh(View whatViewG) {
        resetIt=true;
        homeScore=0;
        awayScore=0;
        clickedBtnID = 0;
        scoredTDHome = false;
        btnHomeXP.setEnabled(scoredTDHome);
        btnHomeXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        scoredTDAway = false;
        btnAwayXP.setEnabled(scoredTDAway);
        btnAwayXP.setTextColor(getResources().getColor(R.color.colorTextLight));
        resIDsPulled = false;
        homePenaltyYards = 0;
        awayPenaltyYards = 0;
        updateScore();
        resetIt=false;
    }
}