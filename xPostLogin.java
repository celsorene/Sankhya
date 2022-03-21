package br.com.sankhya.utilSNKPOA;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;

import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.CentralCabecalhoNota;
import br.com.sankhya.modelcore.comercial.CentralCabecalhoNota.CancelamentoNotaCtx;
import br.com.sankhya.modelcore.comercial.CentralFaturamento;
import br.com.sankhya.modelcore.comercial.ConfirmacaoNotaHelper;
import br.com.sankhya.modelcore.comercial.DadosXMLCancelamento;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import com.gargoylesoftware.htmlunit.javascript.host.Document;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gwt.dev.json.JsonObject;
import com.rsa.certj.xml.Transformer;
import com.sankhya.util.XMLUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom.Element;
import org.omg.CORBA.NameValuePair;
import sun.net.www.http.HttpClient;


import br.com.sankhya.service.SWServiceInvoker;

import org.json.JSONException;
import org.json.JSONObject;


import java.math.BigDecimal;

public class xPostLogin implements AcaoRotinaJava {

	private static BigDecimal nunota = null;
	private HttpURLConnection con; 
	private HttpURLConnection con2; 
	private String _cRetorno; 

	@Override
	public void doAction(ContextoAcao arg0) throws Exception {
		
		// TODO Auto-generated method stub  
		  
		//chama funcao de login
		xLoginPost(arg0);
        
		arg0.setMensagemRetorno("Teste Post - Mensagem JSON:   " + _cRetorno ); 
				
	}
		
	private void xLoginPost(ContextoAcao ctx) throws Exception {	
		
		//login
		String url = "http://192.168.56.210:8180/mge/service.sbr?serviceName=MobileLoginSP.login&outputType=json"; 
		String urlParameters = "{'serviceName': 'MobileLoginSP.login','requestBody': {'NOMUSU': {'$': 'sup'},'INTERNO':{'$':''},'KEEPCONNECTED': {'$': 'S' }}}";
	    
		
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8); 

	    try { 
 
	        URL myurl = new URL(url);
	        con = (HttpURLConnection) myurl.openConnection();

	        con.setDoOutput(true);
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", "Java client");
	        //con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        con.setRequestProperty("Content-Type", "application/json;charset=ISO-8859-1");  

	        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
	            wr.write(postData);
	        }
 
	        StringBuilder content; 
 
	        try (BufferedReader in = new BufferedReader(  
	                new InputStreamReader(con.getInputStream()))) {

	            String linePost;
	            content = new StringBuilder();

	            while ((linePost = in.readLine()) != null) {
	                content.append(linePost);
	                content.append(System.lineSeparator());      
	            }
	            
	            _cRetorno = content.toString(); //Json MobileLoginSP " +
	            
	            //r6gaIReCuOzQAFWNdLSCwPX1eHnn7y6-3A-_KlNp
	            //1tWLUFaaAu7Ybtk0OZno1gtMElhBqpZl0757XvQs
	            
	         
	            Integer _nPosicao = 0;
	            _nPosicao = _cRetorno.lastIndexOf("jsessionid") + 18;
	            _cRetorno = _cRetorno.substring(_nPosicao  , 252);
	             	            
	        }  

	        //System.out.println(content.toString());
	        //arg0.setMensagemRetorno("Teste Post - Mensagem JSON:   " + content.toString() ); 
	        	        

	    } finally { 
 
	        con.disconnect(); 
	    }
	      
	    //arg0.setMensagemRetorno("Teste Post Login - Mensagem JSON:   " + _cRetorno );
	     
	} 
	  
	
}
	
