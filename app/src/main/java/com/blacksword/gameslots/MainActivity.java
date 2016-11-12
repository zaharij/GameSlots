package com.blacksword.gameslots;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blacksword.gameslots.model.dao.impl.UserDaoImpl;
import com.blacksword.gameslots.model.entities.Tiles;
import com.blacksword.gameslots.model.service.SlotsFoodModel;
import java.util.Timer;
import java.util.TimerTask;
import static com.blacksword.gameslots.model.constants.TilesManager.*;
import static com.blacksword.gameslots.model.constants.Constants.*;

public class MainActivity extends AppCompatActivity {
    private TextView textViewLine, textViewUsersLines, textViewWinningMoney, textViewAccount, textViewDate, textViewBet;
    private SlotsFoodModel slotsFoodModel;
    private Tiles tiles;
    private UserDaoImpl userDao;
    private SharedPreferences sharedPreferences;
    private volatile ImageView slot1, slot2, slot3, slot4, slot5, tile1, tile2, tile3, tile4, tile5
            , tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14, tile15;
    private float bet;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tile1 = (ImageView)  findViewById(R.id.imageV1);
        tile2 = (ImageView)  findViewById(R.id.imageV2);
        tile3 = (ImageView)  findViewById(R.id.imageV3);
        tile4 = (ImageView)  findViewById(R.id.imageV4);
        tile5 = (ImageView)  findViewById(R.id.imageV5);
        tile6 = (ImageView)  findViewById(R.id.imageV6);
        tile7 = (ImageView)  findViewById(R.id.imageV7);
        tile8 = (ImageView)  findViewById(R.id.imageV8);
        tile9 = (ImageView)  findViewById(R.id.imageV9);
        tile10 = (ImageView)  findViewById(R.id.imageV10);
        tile11 = (ImageView)  findViewById(R.id.imageV11);
        tile12 = (ImageView)  findViewById(R.id.imageV12);
        tile13 = (ImageView)  findViewById(R.id.imageV13);
        tile14 = (ImageView)  findViewById(R.id.imageV14);
        tile15 = (ImageView)  findViewById(R.id.imageV15);
        slot1 = (ImageView)  findViewById(R.id.imageViewSlot1);
        slot2 = (ImageView)  findViewById(R.id.imageViewSlot2);
        slot3 = (ImageView)  findViewById(R.id.imageViewSlot3);
        slot4 = (ImageView)  findViewById(R.id.imageViewSlot4);
        slot5 = (ImageView)  findViewById(R.id.imageViewSlot5);
        textViewLine = (TextView) findViewById(R.id.textViewLine);
        textViewUsersLines = (TextView) findViewById(R.id.textViewUsersLines);
        textViewWinningMoney = (TextView) findViewById(R.id.textViewWinningMoney);
        textViewAccount = (TextView) findViewById(R.id.textViewAccount);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewBet = (TextView) findViewById(R.id.textViewBet);
        slotsFoodModel = new SlotsFoodModel();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        userDao = new UserDaoImpl();
        userDao.updateAccount(sharedPreferences);
        bet = MIN_BET;
        textViewLine.setText("" + slotsFoodModel.getWinLines().getWinLinesArray().size());
        stopAnimation();
    }

    /**
     * sets visibility of animation as invisible
     */
    private void setInvisibleSlotView(){
        slot1.setVisibility(View.INVISIBLE);
        slot2.setVisibility(View.INVISIBLE);
        slot3.setVisibility(View.INVISIBLE);
        slot4.setVisibility(View.INVISIBLE);
        slot5.setVisibility(View.INVISIBLE);
    }

    /**
     * stops the animation
     */
    private void stopAnimation(){
        ((AnimationDrawable) slot1.getDrawable()).stop();
        ((AnimationDrawable) slot2.getDrawable()).stop();
        ((AnimationDrawable) slot3.getDrawable()).stop();
        ((AnimationDrawable) slot4.getDrawable()).stop();
        ((AnimationDrawable) slot5.getDrawable()).stop();
    }

    /**
     * start the animation
     * @param view
     */
    public void startSpinning(View view) {
        slot1.setVisibility(View.VISIBLE);
        slot2.setVisibility(View.VISIBLE);
        slot3.setVisibility(View.VISIBLE);
        slot4.setVisibility(View.VISIBLE);
        slot5.setVisibility(View.VISIBLE);
        ((AnimationDrawable) slot1.getDrawable()).start();
        ((AnimationDrawable) slot2.getDrawable()).start();
        ((AnimationDrawable) slot3.getDrawable()).start();
        ((AnimationDrawable) slot4.getDrawable()).start();
        ((AnimationDrawable) slot5.getDrawable()).start();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                stopAnimation();
            }
        };
        timer.schedule(timerTask, SLOTS_RUNNING_TIME);
        int duration = 0;
        for(int i = 0; i < ((AnimationDrawable) slot1.getDrawable()).getNumberOfFrames(); i++){
            duration += ((AnimationDrawable) slot1.getDrawable()).getDuration(i);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickSpin();
            }
        }, duration);
    }

    /**
     * makes slots calculations, inputs images
     */
    public void clickSpin() {
        setInvisibleSlotView();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        float winning;
        int freeBetsNumber = slotsFoodModel.getFreeBetsNumber();
        if (freeBetsNumber > FREE_BETS_DEF){
            bet = slotsFoodModel.getFreeBet(MIN_BET, MAX_SCATTER_BET);
            freeBetsNumber--;
            slotsFoodModel.setFreeBetsNumber(freeBetsNumber);
            float winMoney = slotsFoodModel.getWinMoney(bet);
            if (winMoney < NULL_BET){
                winning = NULL_BET;
            } else {
                winning = winMoney;
            }
        } else {
            try {
                if (bet > userDao.getAccount(sharedPreferences)) {
                    return;
                }
            } catch (NumberFormatException ex) {
                bet = NULL_BET;
            }
            winning = slotsFoodModel.getWinMoney(bet);
        }
        tiles = slotsFoodModel.getTiles();
        tile1.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(0, 0).getName()));
        tile2.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(1, 0).getName()));
        tile3.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(2, 0).getName()));
        tile4.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(3, 0).getName()));
        tile5.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(4, 0).getName()));
        tile6.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(0, 1).getName()));
        tile7.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(1, 1).getName()));
        tile8.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(2, 1).getName()));
        tile9.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(3, 1).getName()));
        tile10.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(4, 1).getName()));
        tile11.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(0, 2).getName()));
        tile12.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(1, 2).getName()));
        tile13.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(2, 2).getName()));
        tile14.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(3, 2).getName()));
        tile15.setImageResource(TILE_IMAGES_MAP.get(tiles.getTile(4, 2).getName()));
        textViewUsersLines.setText("pay lines: " + getChosenLinesStr());
        textViewBet.setText("your bet: " + bet);
        textViewWinningMoney.setText("Your winning $ " + winning);
        textViewLine.setText("" + slotsFoodModel.getWinLines().getWinLinesArray().size());
        userDao.updateAccount(sharedPreferences, winning);
        textViewAccount.setText("Your account $ " + userDao.getAccount(sharedPreferences));
        textViewDate.setText("last entering: " + userDao.getAccountDate(sharedPreferences));
    }

    /**
     * takes away one line
     * @param view
     */
    public void minusLine(View view){
        if (slotsFoodModel.getWinLines().getWinLinesArray().size() > MIN_LINES_NUMBER){
            slotsFoodModel.getWinLines().deleteWinLine(slotsFoodModel.getWinLines().getWinLinesArray().size() - 1);
        }
        textViewLine.setText("" + slotsFoodModel.getWinLines().getWinLinesArray().size());
        textViewUsersLines.setText("pay lines: " + getChosenLinesStr());
    }

    /**
     * adds one line
     * @param view
     */
    public void plusLine(View view){
        if (slotsFoodModel.getWinLines().getWinLinesArray().size() < MAX_LINES_NUMBER){
            slotsFoodModel.getWinLines().setWinLine(slotsFoodModel.getWinLines().getWinLinesArray().size());
        }
        textViewLine.setText("" + slotsFoodModel.getWinLines().getWinLinesArray().size());
        textViewUsersLines.setText("pay lines: " + getChosenLinesStr());
    }

    /**
     * sets bet (10)
     * @param view
     */
    public void setTenBet(View view){
        this.bet = 10;
        textViewBet.setText("your bet: " + bet);
    }

    /**
     * sets bet (20)
     * @param view
     */
    public void setTwentyBet(View view){
        this.bet = 20;
        textViewBet.setText("your bet: " + bet);
    }

    /**
     * sets bet (50)
     * @param view
     */
    public void setFiftyBet(View view){
        this.bet = 50;
        textViewBet.setText("your bet: " + bet);
    }

    /**
     * sets bet (100)
     * @param view
     */
    public void setHundredBet(View view){
        this.bet = 100;
        textViewBet.setText("your bet: " + bet);
    }

    /**
     * returns chosen lines as a string
     * @return chosen lines (String)
     */
    private String getChosenLinesStr(){
        String chosenLinesStr = "";
        for(int currentLine: slotsFoodModel.getWinLines().getWinLinesArray()){
            currentLine += 1;
            chosenLinesStr += " " + currentLine + ",";
        }
        return chosenLinesStr;
    }
}
