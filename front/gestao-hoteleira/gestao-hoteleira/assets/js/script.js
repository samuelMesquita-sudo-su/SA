// Script principal

// Aguarda o carregamento do HTML para ser executado
document.addEventListener('DOMContentLoaded', function () {

    carregar();

    document.querySelectorAll('.campo-monetario').forEach((campo) => {
        campo.addEventListener('input', function (e) {
            let value = e.target.value;
            // Remove qualquer caractere que não seja número
            value = value.replace(/\D/g, '');
            // Adiciona vírgula e pontos no formato de moeda brasileira
            value = (value / 100).toFixed(2).replace('.', ',');
            // Adiciona o separador de milhar
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

            // Exibe o valor formatado
            e.target.value = 'R$ ' + value;
        });
    });

});

// Simulação de carregamento da tela
function carregar(mensagem = null) {
    let loading = document.querySelector('#loading');
    loading.style.display = 'flex';
    setTimeout(function () {
        loading.style.display = 'none';
        if (mensagem != null) {
            alert(mensagem);
        }
    }, 1000);
}

// Formatações de data

function formatarDataParaBR(dataParaFormatar) {
    if(dataParaFormatar == "") return "";
    const data = new Date(dataParaFormatar);
    const dataFormatada = new Intl.DateTimeFormat('pt-BR').format(data);
    return dataFormatada;
}

function formatarDataParaISO(dataBR) {
    if(dataBR == "") return "";
    const partes = dataBR.split('/');
    const dataISO = `${partes[2]}-${partes[1]}-${partes[0]}`;
    return dataISO;
}

const camposMonetarios = document.querySelectorAll('.campo-monetario');