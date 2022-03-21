//localiza o serviço de impressão de relatórios
var factory = javaStaticMethod('br.com.sankhya.modelcore.PlatformServiceFactory','getInstance',null, null);
var reportService = factory.lookupService('@core:report.service');
var _nQtdImp = getParam("P_QTDIMP");

//ATENÇÃO: trocar o  código 48 pelo Nro. Único do relatório real (tela 'Relatórios formatados')
reportService.set('nurfe',48);

/*
   Estou assumindo um nome padrão para impressoras do report
   Se for usar o SPS (Servidor de Impressão) então deverá haver uma impressora registrada com esse nome ou existir uma roteirização deste nome para o nome real da impressora.
   Caso não use o SPS, então o sistema vai perguntar qual impressora deve ser usada (isso pode ser salvo para não perguntar novamente)
  Pode trocar o printer.name para o nome da impressora LOCAL ou deixar buscar o serviço de impressão do SANKHYA-W, que retorna todas as impressoras instaladas*/

reportService.set('printer.name','\\IRIS\IMP_TI');

for(var i = 0; i < linhas.length; i++){ //para cada nota selecionada
var linha = linhas[i];

//reportService.set('codemp', linha.getCampo("CODEMP") ); //a empresa é importante para que o roteamento seja feito corretamente, caso exista.

var query = getQuery();
query.nativeSelect("SELECT TEXTO FROM TSIPAR WHERE CHAVE = 'SERVDIRMOD'");
query.next()

var params = {}; //parametros de execução do relatório
/*params['PDIR_MODELO'] = query.getString("TEXTO");*/
params['P_NUNOTA'] = linha.getCampo('NROPRODUCAO').toString();
/*params['PK_SEQ'] = linha.getCampo('SEQ');
params['PK_CODEMPRESA'] = linha.getCampo('CODEMPRESA');
params['PK_DTPRODUCAO'] = linha.getCampo('DTPRODUCAO');
params['PK_MISTURADOR'] = linha.getCampo('MISTURADOR');
params['PK_TURNO'] = linha.getCampo('TURNO');
params['PK_CHAVEUNICA'] = linha.getCampo('CHAVEUNICA');*/


reportService.set('report.params', params );  

/*
Preenche o relatório e envia para impressão.
Se a impressora não for encontrada então o sistema pergunta a impressora para o usuário
*/

for(var z = 0; z < _nQtdImp ; z++){
   reportService.execute();
}

} 
