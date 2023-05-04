import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;


public class hello {

  public static void main(String []args) {
    System.out.println("Hello, World!");
    
    // // Build SSLContext
    // TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    // tmf.init((KeyStore) null);
    // SSLContext sslContext = SSLContext.getInstance("TLS");
    // sslContext.init(null, tmf.getTrustManagers(), null);

    String port = "6379";
    String host = "test-0001-001.test.07gt2w.apse1.cache.amazonaws.com";
    String password = "HereIsMyAuthToken";

    final URI uri = URI.create("rediss://" + host + ":" + port);
    final SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    // These SSL parameters ensure that we use the same hostname verifier used
    // for HTTPS.
    // Note: this options is only available in Java 7.
    final SSLParameters sslParameters = new SSLParameters();
    sslParameters.setEndpointIdentificationAlgorithm("HTTPS");

    JedisShardInfo shardInfo = new JedisShardInfo(uri, sslSocketFactory, sslParameters, null);
    shardInfo.setPassword(password);

    Jedis jedis = new Jedis(shardInfo);
    jedis.get("foo");
    jedis.disconnect();
    jedis.close();
    
    // JedisShardInfo shardInfo = new JedisShardInfo(uri, sslSocketFactory, sslParameters, null);
    // shardInfo.setPassword(password);

    
    // Jedis jedis = new Jedis(host, port);
    // jedis.auth(password);
    
    System.out.println("Connected to Redis");
  }
}