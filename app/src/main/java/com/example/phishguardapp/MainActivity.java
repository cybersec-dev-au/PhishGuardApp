package com.example.phishguardapp;

import android.os.Vibrator;
import android.os.VibrationEffect;
import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etMessage;
    private Button btnScan;
    private TextView tvResult, tvRiskPercent, tvHistory;
    private ProgressBar progressLoading, progressRisk;

    private final OkHttpClient client = new OkHttpClient();
    private final String SERVER_URL = "http://192.168.1.4:5000/predict";

    // Scan history storage
    private ArrayList<String> scanHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = findViewById(R.id.etMessage);
        btnScan = findViewById(R.id.btnScan);
        tvResult = findViewById(R.id.tvResult);
        progressLoading = findViewById(R.id.progressLoading);
        progressRisk = findViewById(R.id.progressRisk);
        tvRiskPercent = findViewById(R.id.tvRiskPercent);
        tvHistory = findViewById(R.id.tvHistory);

        btnScan.setOnClickListener(v -> {
            String message = etMessage.getText().toString().trim();

            if (!message.isEmpty()) {
                scanMessage(message);
            } else {
                tvResult.setText("Please enter a message first.");
                tvResult.setTextColor(Color.GRAY);
            }
        });
    }

    private void scanMessage(String message) {

        progressLoading.setVisibility(View.VISIBLE);
        progressRisk.setVisibility(View.GONE);
        tvRiskPercent.setVisibility(View.GONE);
        btnScan.setEnabled(false);

        tvResult.setText("Analyzing message...");
        tvResult.setTextColor(Color.WHITE);

        JSONObject json = new JSONObject();
        try {
            json.put("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    progressLoading.setVisibility(View.GONE);
                    btnScan.setEnabled(true);

                    tvResult.setText("Error: Cannot connect to server.");
                    tvResult.setTextColor(Color.GRAY);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                runOnUiThread(() -> {
                    progressLoading.setVisibility(View.GONE);
                    btnScan.setEnabled(true);
                });

                if (response.isSuccessful()) {
                    try {
                        String responseData = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseData);

                        String label = jsonResponse.getString("label");
                        double confidence = jsonResponse.getDouble("confidence");

                        int riskScore;

                        // FIXED LOGIC:
                        if (label.equalsIgnoreCase("PHISHING")) {
                            riskScore = (int) confidence;
                        } else {
                            riskScore = 100 - (int) confidence;
                        }

                        runOnUiThread(() -> {

                            progressRisk.setVisibility(View.VISIBLE);
                            tvRiskPercent.setVisibility(View.VISIBLE);

                            progressRisk.setProgress(riskScore);
                            tvRiskPercent.setText("Risk Level: " + riskScore + "%");

                            if (label.equalsIgnoreCase("PHISHING")) {

                                tvResult.setText("🚨 PHISHING DETECTED!\nConfidence: " + confidence + "%");
                                tvResult.setTextColor(Color.parseColor("#FF5252"));
                                progressRisk.setProgressTintList(
                                        ColorStateList.valueOf(Color.parseColor("#FF5252")));

                                // VIBRATION
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (vibrator != null) {
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        vibrator.vibrate(
                                                VibrationEffect.createOneShot(400,
                                                        VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        vibrator.vibrate(400);
                                    }
                                }

                            } else {

                                tvResult.setText("✅ Message appears SAFE.\nConfidence: " + confidence + "%");
                                tvResult.setTextColor(Color.parseColor("#00E676"));
                                progressRisk.setProgressTintList(
                                        ColorStateList.valueOf(Color.parseColor("#00E676")));
                            }

                            // Fade animation
                            tvResult.setAlpha(0f);
                            tvResult.animate().alpha(1f).setDuration(500);

                            // ===== SCAN HISTORY =====
                            String historyEntry = label + " (" + confidence + "%)";
                            scanHistory.add(0, historyEntry);

                            StringBuilder historyText = new StringBuilder("Scan History:\n");

                            for (int i = 0; i < Math.min(scanHistory.size(), 5); i++) {
                                historyText.append("- ").append(scanHistory.get(i)).append("\n");
                            }

                            tvHistory.setText(historyText.toString());
                        });

                    } catch (Exception e) {
                        runOnUiThread(() ->
                                tvResult.setText("Error parsing response.")
                        );
                    }
                } else {
                    runOnUiThread(() ->
                            tvResult.setText("Server error: " + response.code())
                    );
                }
            }
        });
    }
}
