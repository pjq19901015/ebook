package com.sanh.ebook;

import com.sanh.ebook.interfaces.ActivityInterface;
import com.sanh.ebook.utils.CurlPage;
import com.sanh.ebook.utils.CurlView;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;

public class LookBookActivity extends Activity implements ActivityInterface{
	private CurlView curlView;
	private  int[] mBitmapIds;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lookbook);
		initData();
		findView();
		addAction();
	}
	@Override
	public void initData() {
		mBitmapIds = getIntent().getExtras().getIntArray("images");
		
	}  
	@Override
	public void findView() {
		curlView = (CurlView) this.findViewById(R.id.lookbook_curlview);
		
	}
	@Override
	public void addAction() {
		curlView.setPageProvider(new PageProvider());
	}   	
	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		/*private int[] mBitmapIds = { R.drawable.first_2, R.drawable.first_3,
									R.drawable.first_4,R.drawable.first_5};*/  
    
		@Override
		public int getPageCount() {  
			return mBitmapIds.length;  
		}     

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);  
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = getResources().getDrawable(mBitmapIds[index]);

			int margin = 7; 
			int border = 3; 
			Rect r = new Rect(margin, margin, width - margin, height - margin);  
			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);  
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;  
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);
 
			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
			Bitmap front = loadBitmap(width, height, index);
			Bitmap back = loadBitmap(width, height, index);
			page.setTexture(front, CurlPage.SIDE_FRONT);
			page.setTexture(back, CurlPage.SIDE_BACK); 
		}

		

	}
}
