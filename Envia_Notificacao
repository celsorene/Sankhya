var i = 0;
var linha = linhas[i];
var nNunota = linha.getCampo("NUNOTA");
var nUser = getParam("P_USUARIO");
var cMensagComp = getParam("P_MENSAGCOMPLEMENT");
var nlogadoUser = getUsuarioLogado();

var query = getQuery();
query.update("INSERT INTO TSIAVI ( NUAVISO, TITULO, DESCRICAO, SOLUCAO , IDENTIFICADOR,  IMPORTANCIA, CODUSU, TIPO, DHCRIACAO, CODUSUREMETENTE)  "+
"VALUES ((SELECT NVL(MAX(NUAVISO),0)+ 1 FROM TSIAVI ) , " +"'Aviso de revisão pedido de compra: " +nNunota+" ' ,"+ "'Solicitação do usuário: " + nlogadoUser + "'," + "'Revise o pedido: " +nNunota+ " - " + cMensagComp + "  '," + "'PERSONALIZADO'," + "1," + nUser + ", 'P', SYSDATE,"  + nlogadoUser +")");
query.close();

mensagem = "Enviada(s) notificação!";
