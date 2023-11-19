<template>
  <NavMenu />
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Recipe Details</h2>
    <div class="card">
      <div class="card-header">
        <router-link class="btn btn-outline-info float-right" to="/recipe/list"
          >View All Recipes
        </router-link>
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
    };
  },
  created() {
    const id = this.$route.params.id;
    axios
      .get(`/recipes/${id}`)
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
  },
  methods: {},
};
</script>
