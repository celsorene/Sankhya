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



import java.math.BigDecimal;

public class xCancelaNota implements AcaoRotinaJava {

	private static BigDecimal nunota = null;
	private HttpURLConnection con; 
	private HttpURLConnection con2; 
	private String _cRetorno; 

	@Override
	public void doAction(ContextoAcao arg0) throws Exception {
		
		// TODO Auto-generated method stub  
		
		//BigDecimal nunota = null;
		Integer nSel = 0; 
		String cRegistro = "0";  
				
		for (int i = 0; i < arg0.getLinhas().length; i++) {
			Registro line = arg0.getLinhas()[i];
			try {				
				nSel += 1;  
				cRegistro = line.getCampo("NUNOTA").toString();
				//BigDecimal nunota = line.getCampo("NUNOTA"); 
				nunota = (BigDecimal) line.getCampo("NUNOTA");
				//i = arg0.getLinhas().length + 1;
			} catch (Exception e) {
				e.printStackTrace();     
			} 
		}
		  
		if (nSel == 1) {
		    
			//AuthenticationInfo authenticationInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, 0);
		    //authenticationInfo.makeCurrent();
		    //AuthenticationInfo.getCurrent();
		    
		    /*EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
			JdbcWrapper jdbc = dwfEntityFacade.getJdbcWrapper();
			jdbc.openSession();
			NativeSql sqlquery = new NativeSql(jdbc); 

			sqlquery.appendSql("select NUNOTA \r\n"
					+ "from TGFCAB WHERE NUNOTA = " + nunota +" ");

			ResultSet rsquery = sqlquery.executeQuery();

			while (rsquery.next()) {
				
				nunota = rsquery.getBigDecimal("NUNOTA");
			}
			rsquery.close();*/
			
		        
		    //BarramentoRegra barramentoConfirmacao = BarramentoRegra.build(CentralFaturamento.class, "regrasConfirmacaoSilenciosa.xml", AuthenticationInfo.getCurrent());
		    //barramentoConfirmacao.setValidarSilencioso(true);
			//ConfirmacaoNotaHelper.confirmarNota(nunota, barramentoConfirmacao);
		    
		    /*String justificativa = "teste do cancelamento da nota: " + cRegistro + " - teste acao." ;
			
			JapeSession.putProperty("br.com.sankhya.mgecom.centralnotas.NotaSendoCancelada", "S");
			JapeSession.putProperty("br.com.sankhya.mgecom.centralnotas.validacaoSubstituicaoNfse", false);

		
			CentralCabecalhoNota centralCabNota = new CentralCabecalhoNota();
			CancelamentoNotaCtx contextoCancelamentoNota = new CentralCabecalhoNota.CancelamentoNotaCtx(new BigDecimal("0"), justificativa );
							
			ArrayList<BigDecimal> notas = new ArrayList<BigDecimal>();
			notas.add(nunota);
 
			Element resposta = centralCabNota.cancelarNotasBatch(notas, contextoCancelamentoNota);
			String qtdCancelada = XMLUtils.getAttributeAsString(resposta, "totalNotasCanceladas");
			
					    
			if ("0".equals(qtdCancelada)) {
				arg0.setMensagemRetorno("Nota não Cancelada: " + nunota); 
			} else {
				System.out.println("Nota Cancelada: " + nunota);
				arg0.setMensagemRetorno("Nota Cancelada: " + nunota);
			}*/
		    
			
			xPostLogin(arg0,cRegistro); //chamando a funcao para logar
			xCancelNota(arg0, cRegistro, _cRetorno);
		    
			arg0.setMensagemRetorno("Teste Post - Mensagem JSON:   " + _cRetorno ); 
		}else {
			arg0.setMensagemRetorno("Inválida seleção!");  
		}
		 
	}

	private void xPostLogin(ContextoAcao ctx ,String reg2) throws Exception {	
		
		//login
		String url = "http://192.168.56.210:8180/mge/service.sbr?serviceName=MobileLoginSP.login&outputType=json"; 
	    //String urlParameters = "{\'serviceName\': \'MobileLoginSP.login\',\'requestBody\': {\'NOMUSU\': {\'$\': \'sup\'},\'INTERNO\':{\'$\':\'\'},\'KEEPCONNECTED\': {\'$\': \'S\' }}}";
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
	            
	            _cRetorno = content.toString();   
	            
	            Integer _nPosInicial = 0;
	            Integer _nPosFinal = 0; 
	            
	            _nPosInicial =  _cRetorno.lastIndexOf("jsessionid") + 18;
	            _nPosFinal = _cRetorno.lastIndexOf("kID") - 4;
	            
	            _cRetorno = _cRetorno.substring(_nPosInicial  , _nPosFinal); //252
	            
	           
	        }

	        //System.out.println(content.toString());
	        //arg0.setMensagemRetorno("Teste Post - Mensagem JSON:   " + content.toString() ); 
	        	        
	    } finally {  
 
	       con.disconnect(); 
	    } 
	      
	    
	         
	} 
	   
			
	private void xCancelNota(ContextoAcao ctx2 ,String reg2, String _cId ) throws Exception {  
		
	
	String url2 = "http://192.168.56.210:8180/mgecom/service.sbr?serviceName=CACSP.cancelarNota&mgeSession="+_cId+"&outputType=json";
	String urlParameters2 = "{'serviceName':'CACSP.cancelarNota','requestBody':{'notasCanceladas':{ 'nunota':[ {'$':'"+reg2+"'}],'justificativa':'lancamento indevido - teste JSON','validarProcessosWmsEmAndamento':'false'}}}";  
		
	/*SWServiceInvoker si = new SWServiceInvoker("http://192.168.56.210:8180", "SUP", "");   
	try{
		//String xmlAsString  = "'cep':{'74933625'}";
		Document docRet = (Document) si.call("cancelarNota", "mgecom", urlParameters2);
		printResponse(docRet);
	}catch(Exception e){
		e.printStackTrace();
	}*/
	
			
	byte[] postData2 = urlParameters2.getBytes(StandardCharsets.UTF_8);  
	 
	try {

        URL myurl2 = new URL(url2);   
        con2 = (HttpURLConnection) myurl2.openConnection();

        con2.setDoOutput(true);
        con2.setRequestMethod("POST");
        con2.setRequestProperty("User-Agent", "Java client");
        //con2.setRequestProperty("User-Agent", "Mozilla/5.0");
        con2.setRequestProperty("Content-Type", "application/json;charset=ISO-8859-1");  
        con2.setRequestProperty("Cookie", "JSESSIONID="+_cId); //"JSESSIONID=1tWLUFaaAu7Ybtk0OZno1gtMElhBqpZl0757XvQs"
  
        try (DataOutputStream wr2 = new DataOutputStream(con2.getOutputStream())) {
            wr2.write(postData2);
        }

        StringBuilder content2;  

        try (BufferedReader in2 = new BufferedReader(     
                new InputStreamReader(con2.getInputStream()))) { 

            String linePost2;
            content2 = new StringBuilder();

            while ((linePost2 = in2.readLine()) != null) { 
                content2.append(linePost2);
                content2.append(System.lineSeparator()); 
            }
            
            //ctx.setMensagemRetorno("Teste Post - Mensagem JSON:   " + content2.toString() );
            _cRetorno = "JSESSIONID: " +  _cRetorno + " - Json cancelarNota " + content2.toString();
        }
	} finally {

        con2.disconnect();
    }
	
}  
	
	//nao utilizadas funcoes abaixo:	
	private static void printResponse(Document doc) throws Exception {
		javax.xml.transform.Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource();
		transformer.transform(source, result);
		String xmlString = result.getWriter().toString();
		System.out.println("----Inicio reposta >>>");
		System.out.println(xmlString);
		System.out.println("----Fim resposta <<<");
	}
	
	public void xcancelaNota2(ContextoAcao ctx ,BigDecimal reg2) {
		JdbcWrapper jdbc = null;
		try {
			// Cancelando notas na central
			try {
		
		String justificativa = "teste do cancelamento da nota: " + nunota + " - teste acao." ;
		
		JapeSession.putProperty("br.com.sankhya.mgecom.centralnotas.NotaSendoCancelada", "S");
		JapeSession.putProperty("br.com.sankhya.mgecom.centralnotas.validacaoSubstituicaoNfse", false);
		
	
		CentralCabecalhoNota centralCabNota = new CentralCabecalhoNota();
		CancelamentoNotaCtx contextoCancelamentoNota = new CentralCabecalhoNota.CancelamentoNotaCtx(new BigDecimal("0"), justificativa );
						
		ArrayList<BigDecimal> notas = new ArrayList<BigDecimal>();
		notas.add(nunota);

		Element resposta = centralCabNota.cancelarNotasBatch(notas, contextoCancelamentoNota);
		String qtdCancelada = XMLUtils.getAttributeAsString(resposta, "totalNotasCanceladas");
		System.out.println("Notas Canceladas: " + qtdCancelada);
		
	
		} finally {
			JdbcWrapper.closeSession(jdbc);
		}
		} catch (Exception e) {
			RuntimeException re = new RuntimeException(e);
			System.out.println("Erro Exception: " + re);
			throw re;
		}
	
}
	
}
	
