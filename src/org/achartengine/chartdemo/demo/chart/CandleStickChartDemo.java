package org.achartengine.chartdemo.demo.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;



public class CandleStickChartDemo extends AbstractDemoChart {
	

	
	private ArrayList<Double>timestamp=new ArrayList<Double>();
	private ArrayList<Double>open=new ArrayList<Double>();
	private ArrayList<String>close=new ArrayList<String>();
	private ArrayList<String>high=new ArrayList<String>();
	private ArrayList<String>low=new ArrayList<String>();
	private ArrayList<String>volume=new ArrayList<String>();
	private ArrayList<String>change=new ArrayList<String>();
	
	
	public CandleStickChartDemo(Context ctx){
		processData(ctx);
	}
	
	
	List<double[]> values = new ArrayList<double[]>();

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
		String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
	    List<double[]> x = new ArrayList<double[]>();
	    for (int i = 0; i < titles.length; i++) {
	      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
	    }
	    List<double[]> values = new ArrayList<double[]>();
	    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
	        13.9 });
	    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
	    values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
	    values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
	    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
	    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
	        PointStyle.TRIANGLE, PointStyle.SQUARE };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
	        Color.LTGRAY, Color.LTGRAY);
	    renderer.setXLabels(12);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setZoomButtonsVisible(true);
	    renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
	    renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

	    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
	    XYSeries series = dataset.getSeriesAt(0);
	    series.addAnnotation("Vacation", 6, 30);
	    Intent intent = ChartFactory.getCandleStickChartIntent(context, dataset, renderer,
	        "Candle Stick");
	    return intent;
	}
	
	
	public void processData(Context ctx){
	//	But
		BufferedReader reader=null;
		CSVReader csv=null;
		try {
			reader=new BufferedReader(new InputStreamReader(ctx.getAssets().open("sample.csv")));
			csv=new CSVReader(reader);
			List<String[]>data=csv.readAll();
			Log.i("TAG","ddd"+data.get(382).toString());
			csv.close();
			List<String[]>arrays=data.subList(6, data.size()-1);
			Log.i("TAG","ddd"+arrays.size());
			//Log.i("TAG","ddd"+arrays);
			//data.clear();
			int size=arrays.size();
			double timestamp[]=new double[size];
			double open[]=new double[size];
			double close[]=new double[size];
			double high[]=new double[size];
			double low[]=new double[size];
			double change[]=new double[size];
			int i=0;
			for (String[] obj:arrays) {
				if(obj.length>0){
				timestamp[i]=Double.parseDouble(obj[0].toString());
				open[i]=Double.parseDouble(obj[1].toString());
				close[i]=Double.parseDouble(obj[2].toString());
				high[i]=Double.parseDouble(obj[3].toString());
				low[i]=Double.parseDouble(obj[4].toString());
				change[i]=Double.parseDouble(obj[6].toString());
				}
				i++;
			}
			arrays.clear();
			data.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
}
