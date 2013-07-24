package org.achartengine.chartdemo.demo.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import au.com.bytecode.opencsv.CSVReader;



public class CandleStickChartDemo extends AbstractDemoChart {
	

	
	private ArrayList<Double>timestamp=new ArrayList<Double>();
	private ArrayList<Double>open=new ArrayList<Double>();
	private ArrayList<String>close=new ArrayList<String>();
	private ArrayList<String>high=new ArrayList<String>();
	private ArrayList<String>low=new ArrayList<String>();
	private ArrayList<String>volume=new ArrayList<String>();
	private ArrayList<String>change=new ArrayList<String>();
	
	

	@Override
	public String getName() {
		return "Candle stick Chart";
	}
	@Override
	public String getDesc() {
		return "Chart for describing Candle stick data";
	}

	@Override
	public Intent execute(Context context) {
		return null;
	}
	
	
	public void processData(Context ctx){
	//	But
		BufferedReader reader=null;
		CSVReader csv=null;
		try {
			reader=new BufferedReader(new InputStreamReader(ctx.getAssets().open("sample.csv")));
			csv=new CSVReader(reader);
			List<String[]>data=csv.readAll();
			List<String[]>arrays=csv.readAll().subList(6, data.size()-1);
			data.clear();
			for (String[] obj:arrays) {
				//timestamp.add(Long.parseLong(obj[0]));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
				
		
				
		
		
		
	}
	
}
