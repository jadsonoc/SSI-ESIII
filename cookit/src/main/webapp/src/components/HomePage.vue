<template lang="html">
  <NavMenu />

  <div class="d-flex align-content-center flex-wrap">
    <div class="container-fluid pt-5 my-5 mx-5 px-5">
      <img
        src="../assets/img/logo2.png"
        class="img-fluid rounded mx-auto d-block"
        alt="Imagem responsiva"
      />

      <div class="input-group my-4 py-4">
        <vue3-tags-input
          v-model:tags="tags"
          v-model="tag"
          :select="true"
          :select-items="foods"
          class="form-control"
          @on-select="handleSelectedTag"
          @on-tags-changed="handleChangeTag"
          placeholder="Select the Ingredients"
        >
          <template #item="{ tag }">
            {{ tag.name }}
          </template>
          <template #no-data> No Data </template>
          <template #select-item="tag">
            {{ tag.name }}
          </template>
        </vue3-tags-input>

        <div class="input-group-append">
          <router-link
            :to="`/recipe/search/list/${JSON.stringify(selectedItems)}`"
            class="btn btn-outline-secondary mx-1 py-3 px-5"
            >Go
          </router-link>
        </div>
      </div>
    </div>
  </div>

  <!-- INICIO CARD DECK -->
  <CardGrid />
  <!-- FIM CARD DECK -->

  <FooterComponent />

</template>

<script>
import FooterComponent from "./FooterComponent.vue"
import CardGrid from "./CardGrid.vue";
import NavMenu from "./NavMenu.vue";
import axios from "axios";
import Swal from "sweetalert2";
import Vue3TagsInput from "vue3-tags-input";

export default {
  name: "HomePage",
  props: {},
  components: {
    NavMenu,
    Vue3TagsInput,
    CardGrid,
    FooterComponent,
  },
  data() {
    return {
      tag: "",
      tags: [],
      selectedItems: [],
      foods: [],
    };
  },
  created() {
    axios
      .get("/foods")
      .then((response) => {
        let foodsInfo = response.data;
        foodsInfo.forEach((element) => {
          this.foods.push(element);
        });
        return response;
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "An Error Occured Recovering Food!",
          showConfirmButton: false,
          timer: 1500,
        });
        return error;
      });
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
