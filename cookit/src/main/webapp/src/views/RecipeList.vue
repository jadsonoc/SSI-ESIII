<template>
  <NavMenu />
  <layout-div>
    <div class="container">
      <h2 class="text-center mt-5 mb-3">Recipe Manager</h2>
      <div class="card">
        <div class="card-header">
          <router-link to="/recipe/create" class="btn btn-outline-primary"
            >Create New Recipe
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
                  <router-link
                    :to="`/recipe/edit/${recipe.id}`"
                    class="btn btn-outline-success mx-1"
                    >Edit</router-link
                  >
                  <button
                    @click="handleDelete(recipe.id)"
                    className="btn btn-outline-danger mx-1"
                  >
                    Delete
                  </button>
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
import axios from "axios";
import NavMenu from "../components/NavMenu.vue";
import LayoutDiv from "../components/LayoutDiv.vue";
import Swal from "sweetalert2";

export default {
  name: "RecipeList",
  components: {
    NavMenu,
    LayoutDiv,
  },
  data() {
    return {
      posts: [],
      recipes: [],
    };
  },
  created() {
    this.fetchRecipesList();
  },
  methods: {
    fetchRecipesList() {
      axios
        .get("/recipes")
        .then((response) => {
          this.recipes = response.data;
          return response;
        })
        .catch((error) => {
          return error;
        });
    },
    handleDelete(id) {
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
      }).then((result) => {
        if (result.isConfirmed) {
          axios
            .delete(`/recipes/${id}`)
            .then((response) => {
              Swal.fire({
                icon: "success",
                title: "Recipe deleted successfully!",
                showConfirmButton: false,
                timer: 1500,
              });
              this.fetchRecipesList();
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
        }
      });
    },
  },
};
</script>
