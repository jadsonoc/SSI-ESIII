<template lang="html">
  <div class="row row-cols-1 row-cols-md-3 g-4 mx-5 px-5 mb-5">
    <div class="col">
      <div class="card h-100">
        <img src="../assets/img/card1.png" class="card-img-top" />
        <div class="card-body">
          <h5 class="card-title">O quê?</h5>
          <p class="card-text">
            Selecione os ingredientes que você tiver na sua geladeira ou na sua despensa...
          </p>
        </div>
      </div>
    </div>

    <div class="col">
      <div class="card h-100">
        <img src="../assets/img/card2.png" class="card-img-top" />
        <div class="card-body">
          <h5 class="card-title">Como?</h5>
          <p class="card-text">
            Veja quanto tempo você tem e qual o tamanho da sua fome...
          </p>
        </div>
      </div>
    </div>

    <div class="col">
      <div class="card h-100">
        <img src="../assets/img/card3.png" class="card-img-top" />
        <div class="card-body">
          <h5 class="card-title">Podemos ajudar?</h5>
          <p class="card-text">
            Escolha sua receita favorita, siga o passo a passo e aproveite!
          </p>
        </div>
      </div>
    </div>
   
  </div>
</template>

<script>
  export default {
      name: 'CardGrid',
  };
  </script>
