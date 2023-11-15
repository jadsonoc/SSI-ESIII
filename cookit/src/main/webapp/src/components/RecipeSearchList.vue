<template>
    <layout-div>
          <div class="container">
              <h2 class="text-center mt-5 mb-3">Recipe Manager</h2>
              <div class="card">
                  <div class="card-header">
                      <router-link to="/"
                          class="btn btn-outline-primary"
                          >Home
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
                                  <td>{{recipe.name}}</td>
                                  <td>{{recipe.preparation}}</td>
                                  <td>{{recipe.time}}</td>
                                  <td>{{recipe.serve}}</td>
                                  <td>{{recipe.difficulty}}</td>
                                  <td>
                                      <router-link :to="`/show/${recipe.id}`" class="btn btn-outline-info mx-1">Show</router-link>
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
  import axios from 'axios';
  import LayoutDiv from './LayoutDiv.vue';
  import Swal from 'sweetalert2'
   
  export default {
    name: 'RecipeList',
    components: {
      LayoutDiv,
    },
    data() {
      return {
        posts: [],
          recipes: [],
          ingredients: [],
      };
    },
    created() {
      const param = JSON.parse(this.$route.params.query);
      param.forEach(ingredient => {
        console.log(ingredient);
        this.ingredients.push(ingredient);
      });
        axios.post('/recipes/mainSearchByFoods', this.ings2 )
          .then(response => {
            let recipesInfo = response.data
            recipesInfo.forEach(element => {
              this.recipes.push(element);
            });
            return response
          })
          .catch(error => {
            Swal.fire({
              icon: 'error',
              //title: 'An Error Occured!',
              title: error,
              showConfirmButton: false,
              timer: 1500
            })
            return error
          });
    },
  };
  </script>