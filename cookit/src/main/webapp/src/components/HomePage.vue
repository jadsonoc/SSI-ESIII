<template lang="html">

  <NavMenu />
  
  <div class="d-flex align-content-center flex-wrap">
    <div class="container-fluid">
      <img src="../assets/img/logo2.png" class="img-fluid rounded mx-auto d-block" alt="Imagem responsiva">
      {{ selectedItems }}
      <div class="input-group mb-3">
        <vue3-tags-input v-model:tags="tags"
          v-model="tag"
          :select="true"
          :select-items="foods"
          @on-select="handleSelectedTag"
          @on-tags-changed="handleChangeTag"
          placeholder="Select the Ingredients">
          <template #item="{ tag }">
            {{ tag.name }}
          </template>
          <template #no-data>
            No Data
          </template>
          <template #select-item="tag">
            {{ tag.name }}
          </template>
        </vue3-tags-input>

        <div class="input-group-append">
          <router-link 
            :to="`/recipe/search/list/${JSON.stringify(selectedItems)}`"
            class="btn btn-outline-info mx-1">Go
          </router-link>
        </div>
      </div>
    </div>
  </div>

  <!-- INICIO CARD DECK -->
  <div class="card-deck">
    <div class="card">
      <img class="card-img-top" src="../assets/img/number1.png" alt="Imagem de capa do card">
      <div class="card-body">
        <h5 class="card-title">Título do card</h5>
        <p class="card-text">Este é um card mais longo com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional. Este conteúdo é um pouco maior.</p>
        <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
      </div>
    </div>
    <div class="card">
      <img class="card-img-top" src="../assets/img/number2.png" alt="Imagem de capa do card">
      <div class="card-body">
        <h5 class="card-title">Título do card</h5>
        <p class="card-text">Este é um card com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional.</p>
        <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
      </div>
    </div>
    <div class="card">
      <img class="card-img-top" src="../assets/img/number3.png" alt="Imagem de capa do card">
      <div class="card-body">
        <h5 class="card-title">Título do card</h5>
        <p class="card-text">Este é um card maior com suporte a texto embaixo, que funciona como uma introdução a um conteúdo adicional. Este card tem o conteúdo ainda maior que o primeiro, para mostrar a altura igual, em ação.</p>
        <p class="card-text"><small class="text-muted">Atualizados 3 minutos atrás</small></p>
      </div>
    </div>
  </div>
  <!-- FIM CARD DECK -->
</template>

<script>
  import NavMenu from './NavMenu.vue' 
  import axios from 'axios';
  import Swal from 'sweetalert2'
  import Vue3TagsInput from 'vue3-tags-input';

  export default {
    name: 'HomePage',
    props: {
    },
    components: {
      NavMenu,
      Vue3TagsInput
    },
    data() {
      return {
        tag: '',
        tags: [],
        selectedItems: [],
        foods: [],
        };
    },
    created() {
      axios.get('/foods')
           .then(response => {
              let foodsInfo = response.data;
                  foodsInfo.forEach(element => {
                    this.foods.push(element);
                  });
              return response;
          })
          .catch(error => {
              Swal.fire({
                  icon: 'error',
                  title: 'An Error Occured Recovering Food!',
                  showConfirmButton: false,
                  timer: 1500
              })
              return error
          })
    },
    methods: {
      handleSelectedTag(tag) {
        this.selectedItems.push(tag);
        this.tags.push(tag);
      },
      handleChangeTag(tags) {
        this.selectedItems = tags;
        let uniqueTags = tags.filter((elem, pos, self) => {
          return self.indexOf(elem) == pos;
        });
        this.tags = uniqueTags;
        
      },

    },
  };
</script>