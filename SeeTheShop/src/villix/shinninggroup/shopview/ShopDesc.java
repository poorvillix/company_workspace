package villix.shinninggroup.shopview;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.seetheshop.R;

import android.annotation.SuppressLint;
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
	GlobalData.NowFragState m_nFragState;
	int m_nChoice;
	WebView m_WebView;
	
	public ShopDesc(GlobalData.NowFragState nFragState, int nChoice)
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
			m_WebView = new WebView(getActivity());
			m_WebView.setWebViewClient(mWebViewClient);
			m_WebView.getSettings().setDomStorageEnabled(true);
			m_WebView.getSettings().setJavaScriptEnabled(true);
			m_WebView.getSettings().setUserAgentString("Android");
			m_WebView.getSettings().setBlockNetworkImage(true);
			m_WebView.setWebChromeClient(new WebChromeClient());
			m_WebView.loadUrl(ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[0]);
			theLayout.addView(m_WebView);
			break;
		case enumShopDescription:
			
			ImageView image01= new ImageView(getActivity());
			String uri = "@drawable/" + ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[0];
			int imageResource = getActivity().getResources().getIdentifier(uri, null, getActivity().getPackageName());
			image01.setImageResource(imageResource);
			theLayout.addView(image01);
			
			
			TextView text01 = new TextView(getActivity());
			text01.setMovementMethod(new ScrollingMovementMethod());
			String desc = ShopFragChoice.getInstance().getShopListDescPos(m_nChoice)[1];

			//desc = desc.replace("\\n", "\n");
			
			text01.setText(Html.fromHtml(desc));
			text01.setTextSize(18);
			theLayout.addView(text01);
			break;
		default:
			break;
		}
		//if()
		/*
		int nPicID = getResources().getIdentifier(m_szShopDesc, "drawable", "com.example.seetheshop");
		
		if(nPicID != 0)
		{
			mShopImage = (ImageView)ShopPicView.findViewById(R.id.shopImageView);
			mShopImage.setImageResource(nPicID);
			mShopImage.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					//ShopMain fragMain = ((ShopMain)getActivity().getSupportFragmentManager().findFragmentByTag(GlobalData.NowMainFragState.enumShopList.toString()));
					//fragMain.onChangeShopListFragment(NowShopListFragState.enumShopWeb, m_nChoice);
				}
			});
		}
		*/
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