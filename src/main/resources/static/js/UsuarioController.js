class UsuarioController {
    carregarLista(lista) {
        let corpoTabela = document.getElementById("corpoTabela");
        corpoTabela.innerHTML = "";
        for (let i = 0; i < lista.length; i++) {
            let linha = corpoTabela.insertRow();

            let idCell = linha.insertCell();
            idCell.innerHTML = lista[i].id;

            let nomeCell = linha.insertCell();
            nomeCell.innerHTML = lista[i].nome;

            let apagarCell = linha.insertCell();
            apagarCell.innerHTML = `<button 
                         onclick="usuarioController.apagar(${lista[i].id})">Apagar</button>`;

            let editarCell = linha.insertCell();
            editarCell.innerHTML = `<button 
                         onclick="usuarioController.editarItem(${lista[i].id})">Editar</button>`;
        }
    }

    editarItem(id) {
        fetch(`api/usuarios/${id}`, {
            method: "GET"
        }).then((resposta) => {
            if (resposta.ok) {
                resposta.json().then(
                        (item) => {
                    this.atribuirItem(item);
                }
                );
            }
        });
    }
    atribuirItem(item) {
        document.getElementById("id").value = item.id;
        document.getElementById("nome").value = item.nome;
    }

    apagar(id) {
        fetch(`api/usuarios/${id}`, {
            method: "DELETE"
        }
        ).then((resposta) => {
            if (resposta.ok) {
                this.listar();
            } else {
                console.log("Erro ao apagar");
            }

        });

    }

    pesquisar() {
        let pesquisa = document.getElementById("pesquisar").value;
        fetch(`/api/usuarios/pesquisar/nome/?contem=${pesquisa}`, {method: "GET"})
                .then((resultado) => {
                    if (resultado.ok) {
                        resultado.json().then(
                                (lista) => {
                            this.carregarLista(lista);
                            console.log(lista);
                        }
                        );

                    } else {
                        console.log("Erro na excecução");

                    }

                }

                );

    }

    listar() {
        fetch("api/usuarios/", {method: "GET"})
                .then((resultado) => {
                    if (resultado.ok) {
                        resultado.json().then(
                                (lista) => {
                            this.carregarLista(lista);
                            console.log(lista);
                        }
                        );

                    } else {
                        console.log("Erro na excecução");

                    }

                }

                );

    }

    confirmar() {
        let id = document.getElementById("id").value;
        let nome = document.getElementById("nome").value;

        let item = {
            nome: nome,
        };
        if (id == "") {
            this.inserir(item);
        } else {
            this.editar(id, item);
        }
    }

    editar(id, item) {
        fetch(`api/usuarios/${id}`, {
            method: "PUT",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(item)
        }).then((resultado) => {
            if (resultado.ok) {
                this.limpar();
                this.listar();
            }
        });
    }

    limpar() {
        document.getElementById("id").value = "";
        document.getElementById("nome").value = "";
    }

    inserir(item) {
        fetch("api/usuarios/", {
            method: "POST",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(item)
        }).then((resultado) => {
            if (resultado.ok) {
                this.listar();
            } else {
                console.log("Erro na execução");
            }

        });

    } 

}