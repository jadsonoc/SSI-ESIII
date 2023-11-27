<template>
  <layout-div>
    <div class="container">
      <h2 class="text-center mt-5 mb-3">Recipe Suggestions</h2>
      <div class="card">
        <div class="card-header">
          <router-link to="/" class="btn btn-outline-primary"
            >Go back to Home
          </router-link>
        </div>
        <div class="card-body">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Name</th>
                <th>Preparation</th>
                <th>Time</th>
                <th>Serve</th>
                <th>Difficulty</th>
                <th width="240px">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="recipe in recipes" :key="recipe.id">
                <td>{{ recipe.name }}</td>
                <td>{{ recipe.preparation }}</td>
                <td>{{ recipe.time }}</td>
                <td>{{ recipe.serve }}</td>
                <td>{{ recipe.difficulty }}</td>
                <td>
                  <router-link
                    :to="`/recipe/show/${recipe.id}`"
                    class="btn btn-outline-info mx-1"
                    >Show</router-link
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </layout-div>
</template>

<script>
import VueCookies from "vue-cookies";
import axios from "axios";
import LayoutDiv from "./LayoutDiv.vue";
import Swal from "sweetalert2";

export default {
  name: "RecipeSearchList",
  components: {
    LayoutDiv,
  },
  data() {
    return {
      ingredients: [],
      recipes: [],
      isLogged: false,
      intoleranceCode: "",
    };
  },
  created() {
    let userId = 0;
    if (VueCookies.isKey("userId")) {
      this.isLogged = true;
      userId = VueCookies.get("userId");
      this.intoleranceCode +=
        VueCookies.get("userLactoseIntolerant") == "true" ? "1" : "0";
      this.intoleranceCode +=
        VueCookies.get("userGlutenIntolerant") == "true" ? "1" : "0";
      this.intoleranceCode +=
        VueCookies.get("userOilseedsIntolerant") == "true" ? "1" : "0";
    }
    const param = JSON.parse(this.$route.params.query);
    param.forEach((ingredient) => {
      this.ingredients.push(ingredient);
    });
    //Isso aqui foi estúpido, visto que o BackEnd possui essas infosmações, bastava passar o idUser
    let url = this.isLogged
      ? "/recipes/mainSearchByFoodsLoggedV2/" + userId //? "/recipes/mainSearchByFoodsLogged/" + this.intoleranceCode
      : "/recipes/mainSearchByFoods";
    axios
      .post(url, this.ingredients)
      .then((response) => {
        if (response.data.length === 0) {
          Swal.fire({
            icon: "warning",
            title:
              "Ops! Unfortunatelly, we don't have any recipe with those ingredients in our database",
            showConfirmButton: false,
            timer: 5500,
          });
        } else {
          let recipesInfo = response.data;
          recipesInfo.forEach((element) => {
            this.recipes.push(element);
          });
        }
        return response;
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: 'An Error Occured!',
          //title: error,
          showConfirmButton: false,
          timer: 1500,
        });
        return error;
      });
  },
};
</script>
