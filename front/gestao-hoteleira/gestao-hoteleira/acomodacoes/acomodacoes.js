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
        let funcionario = document.querySelector('#campo-func').value;           //obrigatório

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
        } else if (funcionario == ""){
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
            funcionario : funcionario
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
    const tbody = document.querySelector('table tbody');
    tbody.innerHTML = "";
    document.querySelector('#total-registros').textContent = listaAcomodacao.length;

    listaAcomodacao.forEach(function (objeto) {
        // Cria string html com os dados da lista
        let htmlAcoes = `
            <button class="bt-tabela bt-editar" title="Editar"><i class="ph ph-pencil"></i></button>
            <button class="bt-tabela bt-excluir" title="Excluir"><i class="ph ph-trash"></i></button>
        `;

        let htmlColunas = `
            <td>${objeto.id}</td>
            <td>${objeto.nome}</td>
            <td>${objeto.valorDiaria}</td>
            <td>${objeto.limiteHospedes}</td>
            <td>${objeto.descricao}</td>
            <td>${objeto.funcionario}</td>
            <td class="col-acoes">${htmlAcoes}</td>
        `;

        let htmlLinha = `<tr id="linha-${objeto.id}">${htmlColunas}</tr>`;
        tbody.innerHTML += htmlLinha;
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
            let nome = colunas[1].textContent;
            let valorDiaria = colunas[2].textContent;
            let limiteHospedes = colunas[3].textContent;
            let descricao = colunas[4].textContent;
            let funcionario = colunas[5].textContent;

            // Popula os campos do formulário
            document.querySelector('#campo-id').value = id;
            document.querySelector('#campo-nome').value = nome;
            document.querySelector('#campo-valor').value = /*formatarDataParaISO(dataNascimento);*/ valorDiaria;
            document.querySelector('#campo-limite').value = limiteHospedes;
            document.querySelector('#campo-descricao').value = descricao;
            document.querySelector('#campo-func').value = funcionario;

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
                listaAcomodacao.splice(indice, 1);

                // Armazena a lista atualizada no navegador
                localStorage.setItem('listaAcomodacao', JSON.stringify(listaAcomodacao));

                // Recarrega a listagem
                listar();
            }
        });
    });
}

function getIndiceListaPorId(id) {
    indiceProcurado = null;
    listaAcomodacao.forEach(function (objeto, indice) {
        if (id == objeto.id) {
            indiceProcurado = indice;
        }
    });
    return indiceProcurado;
}

function getMaiorIdLista() {
    if (listaAcomodacao.length > 0) {
        return parseInt(listaAcomodacao[listaAcomodacao.length - 1].id);
    } else {
        return 0;
    }
}

function resetarForm() {
    document.querySelector('#bt-cancelar').style.display = 'none';
    document.querySelector('#campo-id').value = "";
    document.querySelector('#campo-nome').value = "";
    document.querySelector('#campo-valor').value = "";
    document.querySelector('#campo-limite').value = "";
    document.querySelector('#campo-descricao').value = "";
    document.querySelector('#campo-func').value = "";
}
