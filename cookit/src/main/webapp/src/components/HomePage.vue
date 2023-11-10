<template lang="html">
  <!-- Só uma imagem -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">
        <img src="./img/logo2_mini.png" alt="" width="90" height="30">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Alterna navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
          <div class="collapse navbar-collapse d-flex flex-row-reverse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
              <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(Página atual)</span></a>
              <a class="nav-item nav-link" href="/recipe/list">Receitas</a>
              <a class="nav-item nav-link" href="#">Ingredientes</a>
              <a class="nav-item nav-link disabled" href="#">Log In</a>
            </div>
          </div>
    </nav>
    <div class="d-flex align-content-center flex-wrap">
        <div class="container-fluid">
            
            <img src="./img/logo2.png" class="img-fluid rounded mx-auto d-block" alt="Imagem responsiva">

            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="What do you fell like eating?" aria-label="Recipient's username" aria-describedby="button-addon2">
                <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Go!</button>
                </div>
            </div>

        </div>
    </div>
    <div class="card-deck">
        <div class="card">
          <img class="card-img-top" src="./img/number1.png" alt="Imagem de capa do card">
          <div class="card-body">
            <h5 class="card-title">Título do card</h5>
            <p class="card-text">Este é um card mais longo com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional. Este conteúdo é um pouco maior.</p>
            <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
          </div>
        </div>
        <div class="card">
          <img class="card-img-top" src="./img/number2.png" alt="Imagem de capa do card">
          <div class="card-body">
            <h5 class="card-title">Título do card</h5>
            <p class="card-text">Este é um card com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional.</p>
            <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
          </div>
        </div>
        <div class="card">
          <img class="card-img-top" src="./img/number3.png" alt="Imagem de capa do card">
          <div class="card-body">
            <h5 class="card-title">Título do card</h5>
            <p class="card-text">Este é um card maior com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional. Este card tem o conteúdo ainda maior que o primeiro, para mostrar a altura igual, em ação.</p>
            <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
          </div>
        </div>
      </div>
</template>

<script>
export default {
  name: 'HomePage',
  props: {
    msg: String
  }
}
</script>