<template>
    <layout-div>
      <h2 class="text-center mt-5 mb-3">Create New Recipe</h2>
      <div class="card">
          <div class="card-header">
              <router-link 
                  class="btn btn-outline-info float-right"
                  to="/">View All Recipes
              </router-link>
          </div>
          <div class="card-body">
              <form>
                  <div class="form-group">
                      <label htmlFor="name">Name</label>
                      <input 
                          v-model="recipe.name"
                          type="text"
                          class="form-control"
                          id="name"
                          name="name"/>
                  </div>
                  <div class="form-group">
                      <label htmlFor="preparation">Preparation</label>
                      <textarea 
                          v-model="recipe.preparation"
                          class="form-control"
                          id="preparation"
                          rows="6"
                          name="preparation"></textarea>
                  </div>
                  <div class="form-group">
                      <label htmlFor="time">Prep Time</label>
                      <input 
                          v-model="recipe.time"
                          type="number"
                          class="form-control"
                          id="time"
                          name="time"/>
                  </div>
                  <div class="form-group">
                      <label htmlFor="serve">Serves</label>
                      <input 
                          v-model="recipe.serve"
                          type="number"
                          class="form-control"
                          id="serve"
                          name="serve"/>
                  </div>
                  <div class="form-group">
                      <label htmlFor="difficulty">Difficulty</label>
                      <input 
                          v-model="recipe.difficulty"
                          type="number"
                          class="form-control"
                          id="difficulty"
                          name="difficulty"/>
                  </div>
                  <!-- INGREGIENTES AQUI -->
                  <div id="teste">
                    <h1>Add user</h1>
                    <div v-for="(ingredient, index) in ingredientField" :key="index">
                      <input v-model="ingredient.food" />
                      <input v-model="ingredient.quantity" />
                      <button @click="deleteIngredient(index)">
                        delete
                      </button>
                    </div>
                    
                    <button @click="addIngredient()">
                      New Ingredient
                    </button>
                    <pre>{{ $data }}</pre>
     
                  </div>

                  <button 
                      @click="handleSave()"
                      :disabled="isSaving"
                      type="button"
                      class="btn btn-outline-primary mt-3">
                      Save Recipe
                  </button>
              </form>
          </div>
      </div>
    </layout-div>
  </template>
   
  <script>
  import axios from 'axios';
  import LayoutDiv from '../components/LayoutDiv.vue';
  import Swal from 'sweetalert2'
  
  export default {
    name: 'RecipeCreate',
    components: {
      LayoutDiv,
    },
    data() {
      return {
        recipe: {
          name: '',
          preparation: '',
          time: '',
          serve: '',
          difficulty: '', 
          ingredients: [{}],
        },
        ingredientField: [{ food: '', quantity: '' }],
        isSaving:false,
      };
    },
    methods: {
      addIngredient: function () {
          this.ingredientField.push({food: '', quantity: '' });
      },
      deleteIngredient: function (index) {
        console.log(index);
        console.log(this.ingredients);
        this.ingredientField.splice(index, 1);
        if(index===0)
        this.addIngredient()
      },
      handleSave() {
          this.isSaving = true
          axios.post('/recipes', this.recipe)
            .then(response => {
              Swal.fire({
                  icon: 'success',
                  title: 'Recipe saved successfully!',
                  showConfirmButton: false,
                  timer: 1500
              })
              this.isSaving = false
              this.recipe.name = ""
              this.recipe.preparation = ""
              this.recipe.time = ""
              this.recipe.serve = ""
              this.recipe.difficulty = ""
              return response
            })
            .catch(error => {
              this.isSaving = false
              Swal.fire({
                  icon: 'error',
                  title: 'An Error Occured!',
                  showConfirmButton: false,
                  timer: 1500
              })
              return error
            });
      },
    },
  };
  </script>