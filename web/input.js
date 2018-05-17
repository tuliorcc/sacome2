function SetCPFMask(el) {
    var patCPF = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
    el.oninput = function() {
        var inp = this.value;
        if(patCPF.test(this.value)) return;
        var cnpj = inp.replace(/\D/g, '');
        if(cnpj.length > 9) {
            cnpj = cnpj.substr(0, 3) + "." + cnpj.substr(3, 3) + "." + cnpj.substr(6, 3) + "-" + cnpj.substr(9, 2);
        }else if(cnpj.length > 6) {
            cnpj = cnpj.substr(0, 3) + "." + cnpj.substr(3, 3) + "." + cnpj.substr(6, 3);
        }else if(cnpj.length > 3) {
            cnpj = cnpj.substr(0, 3) + "." + cnpj.substr(3, 3);
        }
        this.value = cnpj;
        var timeout = setTimeout(function(){el.selectionStart = el.selectionEnd = el.value.length;}, 0);
    };
}
var cpfEl = document.getElementById("cpf") || document.getElementById("pac_cpf");
if(cpfEl !== null) SetCPFMask(cpfEl);
