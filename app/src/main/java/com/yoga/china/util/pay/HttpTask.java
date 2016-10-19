package com.yoga.china.util.pay;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpTask extends Task<Integer> {
	private String url;
	private String content;

	public HttpTask(TaskListener listener, String url, String content) {
		super(listener);
		this.url = url;
		this.content = content;
		cancelConnect = false;
	}

	@Override
	public Integer get() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException,
			UnrecoverableKeyException {

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null, null);
		SSLSocketFactory sf = new EasySSLSocketFactory(trustStore);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", sf, 8602));
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
		HttpClient httpclient = new DefaultHttpClient(ccm, params);
		httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, 120000);
		httpclient.getParams().setIntParameter(HttpConnectionParams.SOCKET_BUFFER_SIZE, 1024 * 4);
		HttpPost httpPost = new HttpPost(this.url.trim());
		StreamEntity se = new StreamEntity();
		se.data = content;
		se.encode = "utf-8";
		HttpEntity entity = new EntityTemplate(se);
		httpPost.setEntity(entity);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String respContent = null;
		try {
			respContent = httpclient.execute(httpPost, responseHandler);
			if (respContent != null && !respContent.equals("")) {
				UmsGlobalInfo.netResult = respContent;
			} else {
				UmsGlobalInfo.netResult = null;
				UmsGlobalInfo.currentHttpTask = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UmsGlobalInfo.netResult = null;
			UmsGlobalInfo.currentHttpTask = null;
			e.printStackTrace();
		}
		return 0;
	}

	class StreamEntity implements ContentProducer {
		public void writeTo(OutputStream outstream) throws IOException {
			Writer writer = new OutputStreamWriter(outstream, this.encode);
			writer.write(this.data);
			writer.flush();
		}

		public String encode;
		public String data;
	}

}
