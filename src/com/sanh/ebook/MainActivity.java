package com.sanh.ebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sanh.ebook.interfaces.ActivityInterface;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity 
			implements ActivityInterface,OnItemClickListener {
	private GridView gridview;
	private boolean isExit;
	private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	private int[] images = new int[]{
			R.drawable.cover,R.drawable.first_1,R.drawable.first_4,
			R.drawable.first_7,R.drawable.first_10,R.drawable.first_13,
			R.drawable.first_16
	}; 
	private int[] showTotalImages = new int[]{
		R.drawable.first_1, R.drawable.first_2, R.drawable.first_3,
		R.drawable.first_4, R.drawable.first_5, R.drawable.first_6,
		R.drawable.first_7, R.drawable.first_8, R.drawable.first_9,
		R.drawable.first_10, R.drawable.first_11, R.drawable.first_12,
		R.drawable.first_13, R.drawable.first_14, R.drawable.first_15,
		R.drawable.first_16, R.drawable.first_17, R.drawable.first_18,  
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
		gridview.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) { 
		Intent intent = new Intent(this,LookBookActivity.class);
		int[] showPartImages = cteateImages(5);
		intent.putExtra("images", showPartImages);
		startActivity(intent);
	}

	/**
	 * 创建用于显示的图片
	 * @param num 要显示的页数
	 * @return 图片的资源ID数组
	 */
	private int[] cteateImages(int num) {
		int showPartImages[] = new int[num];
		for(int i = 0;  i < num ; i++){
			int index = new Random().nextInt(showTotalImages.length);
			showPartImages[i] =  showTotalImages[index];
		}    
		return showPartImages;  
	}
	 
	 
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) { 
		if(KeyEvent.ACTION_DOWN == event.getAction() && 
				KeyEvent.KEYCODE_BACK == event.getKeyCode()){
			 /*new AlertDialog.Builder(this)
			 	.setTitle("退出")
			 	.setMessage("你确定要退出捷豹简报吗？")
			 	.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setNegativeButton("取消", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) { 
						
					}
				}).show();*/
			if(isExit){
				finish();
			}else{
				Toast.makeText(this, "再按一次退出", 1).show();
				isExit = true;
			}
			return false;
		}else{
			return super.dispatchKeyEvent(event);
		}
		
	}
   
	@Override
	protected void onResume() {
		isExit = false;
		super.onResume();
	}
}
