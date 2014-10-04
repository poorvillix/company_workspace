package villix.shinninggroup.shopview;

import villix.shinninggroup.seetheshop.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ShopWeb extends Fragment
{
	String m_szWebAddress;
	WebView mWebView;
	public ShopWeb(String szWebAddress)
	{
		m_szWebAddress = szWebAddress;
	}
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View ShopWebView = inflater.inflate(R.layout.shop_web, container,false);
		mWebView = (WebView)ShopWebView.findViewById(R.id.shopWebview);
		//mWebView = new WebView(ShopWebView.getContext());
		mWebView.setWebViewClient(mWebViewClient);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setUserAgentString("Android");
		mWebView.getSettings().setBlockNetworkImage(true);
		mWebView.setWebChromeClient(new WebChromeClient());
		mWebView.loadUrl(m_szWebAddress);
		
		System.out.println(m_szWebAddress);
		return ShopWebView;
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
        	mWebView.getSettings().setBlockNetworkImage(false);
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
