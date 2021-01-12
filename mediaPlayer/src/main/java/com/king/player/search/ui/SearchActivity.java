package com.king.player.search.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.search.WebsiteViewModel;


public class SearchActivity extends BaseActivity {

    private View titleView;
    private WebView webView;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private ImageView ibSpread;
    private EditText etUrl;
    private WebSidePop webSitePop;
    String url = "https://www.baidu.com";
    private View divider;

    @Override
    protected void initView() {
        WebsiteViewModel viewModel = new ViewModelProvider(this).get(WebsiteViewModel.class);
        webSitePop = new WebSidePop(this);
        tvTitle = titleView.findViewById(R.id.web_title);
        divider = titleView.findViewById(R.id.divider);
        ibSpread = titleView.findViewById(R.id.ib_spread);
        ibSpread.setOnClickListener(v -> showWebsiteName());
        tvTitle.setOnClickListener(v -> {
            if (webSitePop.isShowing()) {
                webSitePop.dismiss();
            } else {
                webSitePop.showAsDropDown(titleBar);
            }
        });
        etUrl = titleView.findViewById(R.id.et_url);
        etUrl.setText(url);
        progressBar = mContainer.findViewById(R.id.progress_bar);
        webView = mContainer.findViewById(R.id.web_view);
        viewModel.getDefault().observe(this, info -> {
            if (info != null) {
                tvTitle.setText(info.title);
                url = info.url;
                etUrl.setText(url);
            }
        });
        setUpWebView();
    }

    private void showWebsiteName() {
        ibSpread.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
    }

    private void hideWebsiteName() {
        ibSpread.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
    }

    private void setUpWebView() {
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        webView.setWebChromeClient(new CustomWebChromeClient());
        webView.setWebViewClient(new CustomWebViewClient());

        webView.loadUrl("javascript:function()");
        webView.evaluateJavascript("", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this,"appClient");
    }

    @JavascriptInterface
    public void hello(String msg) {
        System.out.println(msg);
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleView = getLayoutInflater().inflate(R.layout.layout_search_title, null);
        titleBar.setTitleView(titleView)
                .setLeftAction(new TitleBar.Action(null, R.drawable.ic_back_black, v -> close()))
                .setRightAction(new TitleBar.Action("搜索", 0, v -> search()))
                .setTitleTextColor(R.color.textBlack)
                .immersive(this, true)
                .invalidate();
    }

    private void close() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    private void search() {
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        webView = null;
        super.onDestroy();
    }

    class CustomWebViewClient extends WebViewClient {
        public CustomWebViewClient() {
            super();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
            hideWebsiteName();
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }


        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            super.onFormResubmission(view, dontResend, resend);
//            resend.sendToTarget(); // 再次请求
        }

        /**
         * 通知宿主应用程序更新其访问的链接数据库
         * @param view
         * @param url
         * @param isReload
         */
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);

        }

        /**
         * 收到SSL 认证错误 日志记录上传服务器 更新证书
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }

        /**
         * 收到证书请求 针对Https
         * @param view
         * @param request
         */
        @Override
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
//            super.onReceivedClientCertRequest(view, request);
//            request.proceed(privateKey, X509Certificate); //处理传入私钥和证书
//            request.ignore(); // 忽略证书请求
//            request.cancel();  // 取消请求
        }

        /**
         * http 请求需要 authentication 参数校验 如登陆 或者其他权限认证
         * @param view
         * @param handler
         * @param host
         * @param realm
         */
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
//            handler.proceed("username","password"); //传入用户名密码 进行认证；
//            handler.useHttpAuthUsernamePassword(); // 使用本地保存的凭证，如cookie中 或者local store中
//            handler.cancel(); //取消访问
        }

        /**
         * 给宿主应用机会来处理按键事件
         * @param view
         * @param event
         * @return
         */
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

            return super.shouldOverrideKeyEvent(view, event);
        }

        /**
         * 通知宿主 有按键事件未处理
         * @param view
         * @param event
         */
        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {

            super.onUnhandledKeyEvent(view, event);
        }

        /**
         * 页面的缩放比例发生变法时回调，可以用于定制缩放指示器
         * @param view
         * @param oldScale  原比例
         * @param newScale  现在的比例
         */
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
        }

        /**
         * 收到登陆请求时
         * @param view
         * @param realm
         * @param account
         * @param args  登陆所需的参数
         */
        @Override
        public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
            super.onReceivedLoginRequest(view, realm, account, args);

        }

        @Override
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            return super.onRenderProcessGone(view, detail);
        }

        /**
         * 通知宿主 当前加载的页面被标记为安全的了
         * @param view
         * @param request
         * @param threatType
         * @param callback
         */
        @Override
        public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
            super.onSafeBrowsingHit(view, request, threatType, callback);
        }
    }

    class CustomWebChromeClient extends WebChromeClient {
        public CustomWebChromeClient() {
            super();
        }

        /**
         *
         * 页面加载进度变化时的回调 定义自己的加载进度展示
         * @param view
         * @param newProgress
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
        }

        /**
         * 页面标题加载完成的回调 定义自己的 标题栏
         * @param view
         * @param title
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
        /**
         * 页面logo 加载完成的回调 定义自己的 标题栏
         * @param view
         * @param icon
         */
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        /**
         * 页面进入全屏的通知
         * @param view 可能是视频播放器
         * @param callback
         */
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        /**
         * 页面退出全屏的通知
         */
        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        /**
         *  使用场景  视屏悬浮弹窗
         * @param view
         * @param isDialog true 是弹窗 false 是全屏
         * @param isUserGesture 是否是手势触发的
         * @param resultMsg 消息
         * @return true 表示遵从请求，将视图添加的新的窗口中。false 不创建新的窗口
         */
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }

        /**
         * 视屏悬浮弹窗 关闭
         * @param window
         */
        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
        }

        /**
         * JS弹窗 将要弹出时的
         * @param view
         * @param url 页面地址
         * @param message 弹窗消息
         * @param result enter 健的结果
         * @return 是否要自行处理弹窗 ，比如自定义Dialog去展示
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//            result.confirm();
//            result.cancel();
            return super.onJsAlert(view,url,message,result);
        }

        /**
         * 弹窗的确认回调
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            result.confirm();
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }


        /**
         * 获取定位权限
         * @param origin
         * @param callback
         */
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        /**
         *
         */
        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        /**
         * 获取权限
         * @param request
         */
        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }

        @Override
        public void onPermissionRequestCanceled(PermissionRequest request) {
            super.onPermissionRequestCanceled(request);
        }

        /**
         * 控制台输出
         *
         * @param consoleMessage
         * @return
         */
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Nullable
        @Override
        public Bitmap getDefaultVideoPoster() {
            return super.getDefaultVideoPoster();
        }

        @Nullable
        @Override
        public View getVideoLoadingProgressView() {
            return super.getVideoLoadingProgressView();
        }

        @Override
        public void getVisitedHistory(ValueCallback<String[]> callback) {
            super.getVisitedHistory(callback);
        }

        /**
         * 展示文件选择器 选择文件的时候 可以自定义文件选择器
         * @param webView
         * @param filePathCallback
         * @param fileChooserParams
         * @return
         */
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }
}
