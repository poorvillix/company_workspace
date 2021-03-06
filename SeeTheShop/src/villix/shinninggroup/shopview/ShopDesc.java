package villix.shinninggroup.shopview;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.seetheshop.R;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShopDesc extends Fragment
{
	GlobalData.NowDescTypeState m_nFragState;
	int m_nChoice;
	WebView m_WebView;
	
	public ShopDesc(GlobalData.NowDescTypeState nFragState, int nChoice)
	{
		m_nFragState = nFragState;
		m_nChoice = nChoice;
	}
	@SuppressLint("SetJavaScriptEnabled") @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View ShopDescView = inflater.inflate(R.layout.shop_desc, container,false);
		LinearLayout theLayout = (LinearLayout)ShopDescView;
		switch(m_nFragState)
		{
		case enumShopWeb:
			// 開啟網頁
			m_WebView = new WebView(getActivity());
			m_WebView.setWebViewClient(mWebViewClient);
			m_WebView.getSettings().setDomStorageEnabled(true);
			m_WebView.getSettings().setJavaScriptEnabled(true);
			// 通知網頁現在是用手機板
			//m_WebView.getSettings().setUserAgentString("Android");
			m_WebView.getSettings().setBlockNetworkImage(true);
			m_WebView.getSettings().setSupportZoom(true);
			m_WebView.getSettings().setBuiltInZoomControls(true);
		    m_WebView.getSettings().setUseWideViewPort(true);
		    // 一開始出現的大小跟webview 相等
		    //m_WebView.getSettings().setLoadWithOverviewMode(true);
			m_WebView.setWebChromeClient(new WebChromeClient());
			m_WebView.loadUrl(ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[0]);
			theLayout.addView(m_WebView);
			break;
		case enumShopDescription:
			
			String szBackColor = ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[0];
			theLayout.setBackgroundColor(Color.parseColor(szBackColor));
			
			// 一張圖片
			ImageView image01= new ImageView(getActivity());
			String uri = "@drawable/" + ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[1];
			int imageResource = getActivity().getResources().getIdentifier(uri, null, getActivity().getPackageName());
			image01.setImageResource(imageResource);
			theLayout.addView(image01);
			// 一串文字
			TextView text01 = new TextView(getActivity());
			text01.setMovementMethod(new ScrollingMovementMethod());
			String desc = ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[2];
			
			text01.setText(Html.fromHtml(desc));
			text01.setTextSize(18);
			theLayout.addView(text01);
			
			break;
		default:
			break;
		}

		return ShopDescView;
	}
	WebViewClient mWebViewClient = new WebViewClient()
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			System.out.println("shouldOverrideUrlLoading + " + url);
			return true;
		}
        @Override
        public void onPageFinished(WebView view, String url) {
        	super.onPageFinished(view, url);
        	if(m_WebView == null)
        		return;
        	m_WebView.getSettings().setBlockNetworkImage(false);
        	System.out.println("onPageFinished");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                        String description, String failingUrl) {
        	super.onReceivedError(view, errorCode, description, failingUrl);
        	System.out.println("onReceivedError");
                
        }  
	};
}
