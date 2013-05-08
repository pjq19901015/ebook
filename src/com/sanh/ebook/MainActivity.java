package com.sanh.ebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sanh.ebook.interfaces.ActivityInterface;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity 
			implements ActivityInterface,OnItemClickListener {
	private GridView gridview;
	private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	private int[] images = new int[]{
			R.drawable.cover,R.drawable.first_1,R.drawable.first_4,
			R.drawable.first_7,R.drawable.first_10,R.drawable.first_13,
			R.drawable.first_16
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();
        findView();
        addAction();
        
    }
    
	@Override
	public void initData() {
		for(int i = 0; i < images.length; i++){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("icon", images[i]);
			data.add(map);
		}
		
	}
	
	@Override
	public void findView() {
		gridview = (GridView) this.findViewById(R.id.activity_main_gridview);
	}
	@Override
	public void addAction() {
		SimpleAdapter adapter = new SimpleAdapter(this,
												  data,
												  R.layout.gridview_item,
												  new String[]{"icon"},
												  new int[]{R.id.gridview_item_imageview});
		gridview.setAdapter(adapter);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		
	}
	
	

      
}
