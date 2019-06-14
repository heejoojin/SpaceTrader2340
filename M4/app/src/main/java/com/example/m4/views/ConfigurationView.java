package com.example.m4.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
// import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import com.example.m4.R;
import com.example.m4.model.Difficulty;
import com.example.m4.viewmodels.ConfigurationViewModel;


public class ConfigurationView extends AppCompatActivity implements OnClickListener {

    private ConfigurationViewModel viewModel;
    private Spinner difficultySpinner;
    private TextView score;

    private Button pilot_plus;
    private Button pilot_minus;
    private Button fighter_plus;
    private Button fighter_minus;
    private Button trader_plus;
    private Button trader_minus;
    private Button engineer_plus;
    private Button engineer_minus;

    private TextView pilot_points;
    private TextView fighter_points;
    private TextView trader_points;
    private TextView engineer_points;

    private Button exit;
    private Button okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        difficultySpinner = findViewById(R.id.difficulty_spinner);

        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        score = findViewById(R.id.skill_points_number);

        pilot_points = findViewById(R.id.pilot_points);
        fighter_points = findViewById(R.id.fighter_points);
        trader_points = findViewById(R.id.trader_points);
        engineer_points = findViewById(R.id.engineer_points);

        pilot_plus = findViewById(R.id.pilot_plus);
        pilot_plus.setOnClickListener(this);

        pilot_minus = findViewById(R.id.pilot_minus);
        pilot_minus.setOnClickListener(this);

        fighter_plus = findViewById(R.id.fighter_plus);
        fighter_plus.setOnClickListener(this);

        fighter_minus = findViewById(R.id.fighter_minus);
        fighter_minus.setOnClickListener(this);

        trader_plus = findViewById(R.id.trader_plus);
        trader_plus.setOnClickListener(this);

        trader_minus = findViewById(R.id.trader_minus);
        trader_minus.setOnClickListener(this);

        engineer_plus = findViewById(R.id.engineer_plus);
        engineer_plus.setOnClickListener(this);

        engineer_minus = findViewById(R.id.engineer_minus);
        engineer_minus.setOnClickListener(this);

        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(this);

        okay = findViewById(R.id.okay_button);
        okay.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    @Override
    public void onClick (View v) {

        if (v.getId() == R.id.exit_button) {
            System.exit(0);
        }

        if (v.getId() == R.id.okay_button) {
            if (viewModel.getScore() != 0) {
                startActivity(new Intent(ConfigurationView.this, ErrorView.class));
            }
        }

        if (viewModel.getScore() <= 16 && viewModel.getScore() >= 0) {

            if (v.getId() == R.id.pilot_plus ||
                    v.getId() == R.id.fighter_plus ||
                    v.getId() == R.id.trader_plus ||
                    v.getId() == R.id.engineer_plus) {

                if (viewModel.getScore() != 0 &&
                        viewModel.getPilot() < 16 &&
                        viewModel.getFighter() < 16 &&
                        viewModel.getTrader() < 16 &&
                        viewModel.getEngineer() < 16) {

                    viewModel.setScore(viewModel.getScore() - 1);
                    score.setText(String.valueOf(viewModel.getScore()));

                    if (v.getId() == R.id.pilot_plus) {
                        viewModel.setPilot(viewModel.getPilot() + 1);
                        pilot_points.setText(String.valueOf(viewModel.getPilot()));

                    } else if (v.getId() == R.id.fighter_plus) {
                        viewModel.setFighter(viewModel.getFighter() + 1);
                        fighter_points.setText(String.valueOf(viewModel.getFighter()));

                    } else if (v.getId() == R.id.trader_plus) {
                        viewModel.settrader(viewModel.getTrader() + 1);
                        trader_points.setText(String.valueOf(viewModel.getTrader()));

                    } else if (v.getId() == R.id.engineer_plus) {
                        viewModel.setEngineer(viewModel.getEngineer() + 1);
                        engineer_points.setText(String.valueOf(viewModel.getEngineer()));

                    }
                }
            }
            if (v.getId() == R.id.pilot_minus ||
                    v.getId() == R.id.fighter_minus ||
                    v.getId() == R.id.trader_minus ||
                    v.getId() == R.id.engineer_minus) {

                if (viewModel.getScore() != 16) {

                    if (v.getId() == R.id.pilot_minus && viewModel.getPilot() > 0) {

                        viewModel.setPilot(viewModel.getPilot() - 1);
                        pilot_points.setText(String.valueOf(viewModel.getPilot()));

                        viewModel.setScore(viewModel.getScore() + 1);
                        score.setText(String.valueOf(viewModel.getScore()));

                    } else if (v.getId() == R.id.fighter_minus && viewModel.getFighter() > 0) {

                        viewModel.setFighter(viewModel.getFighter() - 1);
                        fighter_points.setText(String.valueOf(viewModel.getFighter()));

                        viewModel.setScore(viewModel.getScore() + 1);
                        score.setText(String.valueOf(viewModel.getScore()));

                    } else if (v.getId() == R.id.trader_minus && viewModel.getTrader() > 0) {

                        viewModel.settrader(viewModel.getTrader() - 1);
                        trader_points.setText(String.valueOf(viewModel.getTrader()));

                        viewModel.setScore(viewModel.getScore() + 1);
                        score.setText(String.valueOf(viewModel.getScore()));

                    } else if (v.getId() == R.id.engineer_minus && viewModel.getEngineer() > 0){

                        viewModel.setEngineer(viewModel.getEngineer() - 1);
                        engineer_points.setText(String.valueOf(viewModel.getEngineer()));

                        viewModel.setScore(viewModel.getScore() + 1);
                        score.setText(String.valueOf(viewModel.getScore()));
                    }
                }
            }
        }
    }
}