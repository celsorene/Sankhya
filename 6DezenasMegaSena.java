package br.com.sankhya;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;

import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.Integer;


public class 6DezenasMegaSena implements AcaoRotinaJava {

	@Override
	 public void doAction(final ContextoAcao ctx) throws Exception {

	      Random generate = new Random();
	        
	        boolean lNumber = false;
	        String cNumeros = "";
	        	        
	        ArrayList<Integer> valores = new ArrayList<Integer>();

	        //comando for incremento e expressao
	        for (int i = 0; i < 6;){
	            
	            int number = generate.nextInt(60);
	            
	            //verifica se numero do array ja existe, se existe pula...
	            lNumber = contains(valores, number);
	                        
	            if (number > 0 && lNumber == false ) {                
	                valores.add(number);
	                System.out.println(number);
	                if (cNumeros == ""){                     
	                    cNumeros += number;
	                }else{
	                    cNumeros += "," + number;
	                }
	                i++;
	            }
	        }
	        
	        //ordenando os valores
	        //valores.sort(null);
	        Collections.sort(valores);  //reverseOrder - decrescente
	        cNumeros = "";
	            
	        for (int nfor =0 ; nfor < valores.size(); nfor++ ){
	            if (cNumeros == ""){                    
	                cNumeros += valores.get(nfor);
	            }else{
	                cNumeros += "," + valores.get(nfor);
	            }
	        } 
	        
	                
	        ctx.setMensagemRetorno("Jogue as seguintes 6 dezenas: " + cNumeros );
	        //JOptionPane.showMessageDialog(null, "Jogue as seguintes 6 dezenas: " + cNumeros, "Selecao randomica - MEGASENA", 1);

	    }

	    //funcao utilizada para testar o valor da dezena com o array (evitando duplicidade)
	    public static boolean contains(final ArrayList<Integer> valores, final int number) {
	        boolean found = false;

	        for(int i = 0; i < valores.size(); i++) {
	            if(valores.get(i) == number) {
	                found=true;
	            }
	        }
	        return found;  
	   }

	}
