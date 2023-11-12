<template>
    <layout-div>
      <h2 class="text-center mt-5 mb-3">Create New Recipe</h2>
      <div class="card">
          <div class="card-header">
              <router-link 
                  class="btn btn-outline-info float-right"
                  to="/recipe/list">View All Recipes
              </router-link>
          </div>
          <div class="card-body">
                 <b className="text-muted">Name:</b>
                 <p>{{recipe.name}}</p>
                 <p>{{foods}}</p>
                 <p>{{ingredientsField}}</p>
          </div>
          <div class="card-body">
              <form>
                <div class="form-group row" v-for="(input, index) in ingredientsField" :key="index">
                  <div class="col-lg-3">
                    <select v-model="input.food" type="text" :name="'ingredient[' + index + '][food]'" class="form-control" placeholder="Ingredient Food">
                      <option disabled value="">Please select one Ingredient</option>
                        <option v-for="food in foods" :key="food.id" :value="food">{{ food.name }}</option>
                    </select>
                  </div>
                  <div class="col-lg-3">
                    <input v-model="input.quantity" type="number" :name="'ingredient[' + index + '][quantity]'" class="form-control" placeholder="Ingredient Quantity">
                  </div>
                  <div class="col-lg-3">
                    <input v-model="input.required" type="checkbox" :name="'ingredient[' + index + '][requrired]'" class="" placeholder="Ingredient Required">
                    <label for="checkbox">{{ input.required }}</label>
                  </div>
                  <div class="col-lg-3">
                    <button type="button" @click="deleteRow(index)" class="btn btn-outline-danger rounded-circle">
                      <i class="fa fa-times"></i>
                    </button>
                  </div>
                </div>
                  <div class="form-group row">
                    <div class="col-lg-12">
                      <button type="button" @click="addRow" class="btn btn-outline-secondary">Add Row</button>
                    </div>
                  </div>
                
                  <!-- INGREGIENTES AQUI -->
                  <button 
                      @click="handleSave()"
                      :disabled="isSaving"
                      type="button"
                      class="btn btn-outline-primary mt-3">
                      Save Ingredients
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
      LayoutDiv
    },
    data() {
      return {
        recipe: {
          name: '',
          ingredients: []
        },
        ingredientsField: [{
          food: '',
          quantity: '',
          required: false  
        }],
        foods: [],
          isSaving: false,
      };
    },
    created() {
        const id = this.$route.params.id;
        axios.get(`/recipes/${id}`)
          .then(response => {
            let recipeInfo = response.data
            this.recipe.name = recipeInfo.name
            return response
          })
          .catch(error => {
            Swal.fire({
              icon: 'error',
              title: 'An Error Occured!',
              showConfirmButton: false,
              timer: 1500
            })
            return error
          });
          axios.get('/foods')
     .then(response => {
         let foodsInfo = response.data
            foodsInfo.forEach(element => {
              this.foods.push(element);
         });
         return response
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
      addRow() {
      this.ingredientsField.push({
        food: "",
        quantity: "",
        required: ""
      });
    },
    deleteRow(index) {
      this.ingredientsField.splice(index, 1);
    },
      handleSave() {
          this.isSaving = true
          axios.put(`/recipes/ingredients/${this.$route.params.id}`, this.ingredientsField)
            .then(response => {
              Swal.fire({
                  icon: 'success',
                  title: 'Ingredients included successfully!',
                  showConfirmButton: false,
                  timer: 1500
              })
              //this.isSaving = false
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