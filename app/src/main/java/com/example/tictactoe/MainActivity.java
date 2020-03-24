package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private Boolean player1turn = true;
    private int turns;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button reset;
    private String textP1="";
    private String textP2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        reset = (Button) findViewById(R.id.reset);

        Intent intent = getIntent();
        textP1 = intent.getStringExtra("p1name");
        textP2 = intent.getStringExtra("p2name");
        textViewPlayer1.setText("  "+textP1+": 0");
        textViewPlayer2.setText(textP2+": 0");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset:
                resetClick(v);
            default:
                if(!((Button) v).getText().toString().equals("")){
                    return;
                }
                if(player1turn) {
                    ((Button) v).setText("X");
                    ((Button) v).setTextColor(Color.RED);
                }
                else {
                    ((Button) v).setText("O");
                    ((Button) v).setTextColor(Color.BLUE);
                }

                turns++;

                if(checkForWin()){
                    if(player1turn)
                        player1Wins();
                    else
                        player2Wins();
                }else if(turns == 9)
                    draw();
                else{
                    player1turn = !player1turn;
                }
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //rows
        for(int i=0;i<3;++i){
            if((field[i][0].equals(field[i][1])) && (field[i][0].equals(field[i][2])) && !(field[i][0].equals(""))){
                return true;
            }
        }
        //cols
        for(int i=0;i<3;++i){
            if((field[0][i].equals(field[1][i])) && (field[0][i].equals(field[2][i])) && !(field[0][i].equals(""))){
                return true;
            }
        }
        //diagonals
        if((field[0][0].equals(field[1][1])) && (field[0][0].equals(field[2][2])) && !(field[0][0].equals(""))){
            return true;
        }

        if((field[0][2].equals(field[1][1])) && (field[0][2].equals(field[2][0])) && !(field[0][2].equals(""))){
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, textP1+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, textP2+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("  "+textP1+": " + player1Points);
        textViewPlayer2.setText(textP2+": " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
        turns = 0;
        player1turn = true;
    }


    private void resetClick (View view){
        player1turn = true;
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
}
