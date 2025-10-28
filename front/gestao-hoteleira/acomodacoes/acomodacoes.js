// Script relacionado ao módulo de acomodações

if(localStorage.getItem('listaAcomodacao') == null){
    listaAcomodacao= [];
    localStorage.setItem('listaAcomodacao', JSON.stringify(listaAcomodacao));
} else{
    listaAcomodacao= JSON.parse(localStorage.getItem('listaAcomodacao'));
}

document.addEventListener('DOMContentLoaded', function(){
    
    listar()

    document.querySelector('#bt-salvar').addEventListener('click', function(){
        // Pega os dados dos campos do formulário
        let id = document.querySelector('#campo-id').value;
        let nome = document.querySelector('#campo-nome').value;                 //obrigatório
        let valorDiaria = document.querySelector('#campo-valor').value;         //obrigatório
        let limiteHospedes = document.querySelector('#campo-limite').value;     //obrigatório 
        let descricao = document.querySelector('#campo-descricao').value;       
        let funconario = document.querySelector('#campo-func').value;           //obrigatório

        //validacoes de campos
        if (nome == "") {
            alert("Nome da acomodação é um campo obrigatório!");
            return;
        } else if (valorDiaria == "") {
            alert("Valor da diária é um campo obrigatório!");
            return;
        } else if (limiteHospedes == "") {
            alert("Limite de hospedes é um campo obrigatório!");
            return;
        } else if (funconario == ""){
            alert("ID de funcionário é obrigatório!");
            return;
        }

        //cria objeto
        let acomodacao = {
            id: (id != "") ? id : getMaiorIdLista() + 1,
            nome : nome,
            valorDiaria : valorDiaria,
            limiteHospedes : limiteHospedes,
            descricao: descricao,
            funconario : funconario
        };

        //Altera ou insere uma posição em um array principal
        if (id != ""){
            let indice = getIndiceListaPorId(id);
            listaAcomodacao[indice] = acomodacao;
        } else{
            listaAcomodacao.push(acomodacao);
        }

        // Armazena a lista atualizada no navegador
        localStorage.setItem('listaAcomodacao', JSON.stringify(listaAcomodacao));

        // Reseta o formulário e recarrega a tabela de listagem
        this.blur();
        resetarForm();

         // Recarrega listagem
        carregar("Salvo com sucesso!");
        listar();

    })

    // Cancelamento de edição
    document.querySelector('#bt-cancelar').addEventListener('click', function () {
        resetarForm();
    });

});


// Funcoes
// TEM QUE ALTERAR

function listar() {
    document.querySelector('table tbody').innerHTML = "";
    document.querySelector('#total-registros').textContent = listaPessoas.length;
    listaPessoas.forEach(function (objeto) {
        // Cria string html com os dados da lista
        let htmlAcoes = "";
        htmlAcoes += '<button class="bt-tabela bt-editar" title="Editar"><i class="ph ph-pencil"></i></button>';
        htmlAcoes += '<button class="bt-tabela bt-excluir" title="Excluir"><i class="ph ph-trash"></i></button>';

        let htmlColunas = "";
        htmlColunas += "<td>" + objeto.id + "</td>";
        htmlColunas += "<td>" + objeto.nomeCompleto + "</td>";
        htmlColunas += "<td>" + objeto.dataNascimento + "</td>";
        htmlColunas += "<td>" + objeto.documento + "</td>";
        htmlColunas += "<td>" + objeto.pais + "</td>";
        htmlColunas += "<td>" + objeto.estado + "</td>";
        htmlColunas += "<td>" + objeto.cidade + "</td>";
        htmlColunas += "<td>" + htmlAcoes + "</td>";

        // Adiciona a linha ao corpo da tabela
        let htmlLinha = '<tr id="linha-' + objeto.id + '">' + htmlColunas + '</tr>';
        document.querySelector('table tbody').innerHTML += htmlLinha;
    });

    eventosListagem();
    carregar();
}

function eventosListagem() {
    // Ação de editar objeto
    document.querySelectorAll('.bt-editar').forEach(function (botao) {
        botao.addEventListener('click', function () {
            // Pega os dados do objeto que será alterado
            let linha = botao.parentNode.parentNode;
            let colunas = linha.getElementsByTagName('td');
            let id = colunas[0].textContent;
            let nomeCompleto = colunas[1].textContent;
            let dataNascimento = colunas[2].textContent;
            let documento = colunas[3].textContent;
            let pais = colunas[4].textContent;
            let estado = colunas[5].textContent;
            let cidade = colunas[6].textContent;

            // Popula os campos do formulário
            document.querySelector('#campo-id').value = id;
            document.querySelector('#campo-nome-completo').value = nomeCompleto;
            document.querySelector('#campo-data-nascimento').value = formatarDataParaISO(dataNascimento);
            document.querySelector('#campo-documento').value = documento;
            document.querySelector('#campo-pais').value = pais;
            document.querySelector('#campo-estado').value = estado;
            document.querySelector('#campo-cidade').value = cidade;

            // Exibe botão de cancelar edição
            document.querySelector('#bt-cancelar').style.display = 'flex';
        });
    });

    // Ação de excluir objeto
    document.querySelectorAll('.bt-excluir').forEach(function (botao) {
        botao.addEventListener('click', function () {
            if (confirm("Deseja realmente excluir?")) {
                // Remove objeto da lista
                let linha = botao.parentNode.parentNode;
                let id = linha.id.replace('linha-', '');
                let indice = getIndiceListaPorId(id);
                listaPessoas.splice(indice, 1);

                // Armazena a lista atualizada no navegador
                localStorage.setItem('listaPessoas', JSON.stringify(listaPessoas));

                // Recarrega a listagem
                listar();
            }
        });
    });
}

function getIndiceListaPorId(id) {
    indiceProcurado = null;
    listaPessoas.forEach(function (objeto, indice) {
        if (id == objeto.id) {
            indiceProcurado = indice;
        }
    });
    return indiceProcurado;
}

function getMaiorIdLista() {
    if (listaPessoas.length > 0) {
        return parseInt(listaPessoas[listaPessoas.length - 1].id);
    } else {
        return 0;
    }
}

function resetarForm() {
    document.querySelector('#bt-cancelar').style.display = 'none';
    document.querySelector('#campo-id').value = "";
    document.querySelector('#campo-nome-completo').value = "";
    document.querySelector('#campo-data-nascimento').value = "";
    document.querySelector('#campo-documento').value = "";
    document.querySelector('#campo-pais').value = "";
    document.querySelector('#campo-estado').value = "";
    document.querySelector('#campo-cidade').value = "";
}
