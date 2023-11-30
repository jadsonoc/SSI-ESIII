<template>
  <NavMenu />
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Recipe Details</h2>
    <div class="card">
      <div class="card-header">
        <div class="row">
          <div class="col">
            <router-link class="btn btn-outline-info" to="/recipe/list"
              >View All Recipes
            </router-link>
          </div>
          <div v-if="isFavourited" class="col">
            <button
              v-if="isLogged"
              @click="handleUnfavourite()"
              className="btn"
            >
              <i class="fa fa-star fa-2x heavy" aria-hidden="true"></i>
            </button>
          </div>
          <div v-else class="col">
            <button v-if="isLogged" @click="handleFavourite()" className="btn">
              <i class="fa fa-star fa-2x light" aria-hidden="true"></i>
            </button>
          </div>
        </div>
      </div>

      <div class="card-body">
        <b className="text-muted">Name:</b>
        <p>{{ recipe.name }}</p>
        <b className="text-muted">Preparation:</b>
        <p>{{ recipe.preparation }}</p>
        <b className="text-muted">Time:</b>
        <p>{{ recipe.time }}</p>
        <b className="text-muted">Serves:</b>
        <p>{{ recipe.serve }}</p>
        <b className="text-muted">Difficulty:</b>
        <p>{{ recipe.difficulty }}</p>

        <b className="text-muted">Ingredients:</b>

        <table class="table table-bordered">
          <thead>
            <tr class="text-center">
              <th>Quantity</th>
              <th>Unity</th>
              <th>Ingredient</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="ingredient in recipe.ingredients" :key="ingredient.id">
              <td>{{ ingredient.quantity }}</td>
              <td>{{ ingredient.food.foodUnit }}</td>
              <td>{{ ingredient.food.name }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </layout-div>
</template>

<script>
import VueCookies from "vue-cookies";
import axios from "axios";
import NavMenu from "../components/NavMenu.vue";
import LayoutDiv from "../components/LayoutDiv.vue";
import Swal from "sweetalert2";

export default {
  name: "RecipeShow",
  components: {
    NavMenu,
    LayoutDiv,
  },
  data() {
    return {
      recipe: {
        name: "",
        preparation: "",
        time: "",
        serve: "",
        difficulty: "",
        ingredients: [],
      },
      favouritedRecipeIds: [],
      isLogged: false,
      isFavourited: false,
    };
  },
  watch: {
    isFavourited(newValue) {
      this.isFavourited = newValue;
    },
  },
  created() {
    if (VueCookies.isKey("userId")) {
      this.isLogged = true;
    }
    const recipeId = this.$route.params.id;
    axios
      .get(`/recipes/${recipeId}`)
      .then((response) => {
        let recipeInfo = response.data;
        this.recipe.name = recipeInfo.name;
        this.recipe.preparation = recipeInfo.preparation;
        this.recipe.time = recipeInfo.time;
        this.recipe.serve = recipeInfo.serve;
        this.recipe.difficulty = recipeInfo.difficulty;
        this.recipe.ingredients = recipeInfo.ingredients;
        return response;
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "An Error Occured Getting the Recipe!",
          showConfirmButton: false,
          timer: 1500,
        });
        return error;
      });
    if (this.isLogged) {
      const userId = VueCookies.get("userId");
      axios
        .get(`/userManager/isFavourite/${userId}/${recipeId}`)
        .then((response) => {
          this.isFavourited = response.data;
          return response;
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "An Error Occured Getting Favourites Recipes!",
            showConfirmButton: false,
            timer: 1500,
          });
          return error;
        });
    }
  },
  methods: {
    handleFavourite() {
      const userId = VueCookies.get("userId");
      const recipeId = this.$route.params.id;
      axios
        .put(`/userManager/favourite/${userId}/${recipeId}`)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Recipe favourited successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          this.isFavourited = true;
          return response;
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "An Error Occured!",
            showConfirmButton: false,
            timer: 1500,
          });
          return error;
        });
    },
    handleUnfavourite() {
      const userId = VueCookies.get("userId");
      const recipeId = this.$route.params.id;
      axios
        .put(`/userManager/unfavourite/${userId}/${recipeId}`)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Recipe unfavourited successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          this.isFavourited = false;
          return response;
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "An Error Occured!",
            showConfirmButton: false,
            timer: 1500,
          });
          return error;
        });
    },
  },
};
</script>

<style scoped>
.light {
  opacity: 0.2; /* Mal consegue ver o texto acima do background */
}
.heavy {
  opacity: 0.9; /* Vê o texto muito claramente acima do background */
  color: goldenrod;
}
</style>
