package br.com.sankhya.utilSNKPOA;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.CentralCabecalhoNota;
import br.com.sankhya.modelcore.comercial.CentralCabecalhoNota.CancelamentoNotaCtx;
import br.com.sankhya.modelcore.comercial.CentralFaturamento;
import br.com.sankhya.modelcore.comercial.ConfirmacaoNotaHelper;
import br.com.sankhya.modelcore.comercial.DadosXMLCancelamento;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;


public class xConfirmarNota implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao arg0) throws Exception {
		
		BigDecimal nunota = null;
				Integer nSel = 0; 
				String cRegistro = "0";  
						
				for (int i = 0; i < arg0.getLinhas().length; i++) {
					Registro line = arg0.getLinhas()[i];
					try {				
						nSel += 1;
						cRegistro = line.getCampo("NUNOTA").toString();
						//BigDecimal nunota = line.getCampo("NUNOTA"); 
						nunota = (BigDecimal) line.getCampo("NUNOTA");
											
						BarramentoRegra barramentoConfirmacao = BarramentoRegra.build(CentralFaturamento.class, "regrasConfirmacaoSilenciosa.xml", AuthenticationInfo.getCurrent());
					    barramentoConfirmacao.setValidarSilencioso(true);
						ConfirmacaoNotaHelper.confirmarNota(nunota, barramentoConfirmacao);
						
						//i = arg0.getLinhas().length + 1;
					} catch (Exception e) {
						e.printStackTrace();  
					} 
				} 

		
	}

}
