package com.example.happyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.happyfood.classification.ClassifierActivity;

import java.util.ArrayList;


public class Inicio extends AppCompatActivity {
    private Button Casa;
    private Button Mapas;
    private Button Camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //startActivity(new Intent(getApplicationContext(), Calculadora.class));
        Casa =(Button) findViewById(R.id.Casa);
        Mapas =(Button) findViewById(R.id.maps);
        Camara =(Button) findViewById(R.id.Camara);
        Mapas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Maps.class));
            }
        });
        Camara.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClassifierActivity.class));
            }
        });


        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(508, 2016));
        visitors.add(new PieEntry(600, 2018));
        visitors.add(new PieEntry(900, 2019));

        PieDataSet pieDataSet= new PieDataSet(visitors, "Macronutrientes");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Macronutrientes");
        pieChart.animate();

        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> platos = new ArrayList<>();
        platos.add(new BarEntry(1,420));
        platos.add(new BarEntry(2,500));
        platos.add(new BarEntry(3,600));
        platos.add(new BarEntry(4,700));
        platos.add(new BarEntry(5,800));
        platos.add(new BarEntry(6,900));
        platos.add(new BarEntry(7,900));

        BarDataSet barDataSet = new BarDataSet(platos,"Dias");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData= new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar CHart Example");
        barChart.animateY(2000);


    }
}