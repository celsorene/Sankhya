if (linhas.length) {
    if (linhas.length > 1) {
        mostraErro ('Selecione um registro por vez!');
    } else {
        var registroSelecionado = linhas [0];
        // ...
    }
} else {
    mostraErro ('Selecione, ao menos, um registro!');
}
