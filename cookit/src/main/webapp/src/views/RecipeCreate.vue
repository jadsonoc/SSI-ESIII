<template>
  <NavMenu />
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Create New Recipe</h2>
    <div class="card">
      <div class="card-header">
        <router-link class="btn btn-outline-info float-right" to="/recipe/list"
          >View All Recipes
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
              name="name"
            />
          </div>
          <div class="form-group">
            <label htmlFor="description">Preparation</label>
            <textarea
              v-model="recipe.preparation"
              class="form-control"
              id="preparation"
              rows="6"
              name="preparation"
            ></textarea>
          </div>
          <div class="form-group">
            <label htmlFor="time">Prep Time</label>
            <input
              v-model="recipe.time"
              type="number"
              class="form-control"
              id="time"
              name="time"
            />
          </div>
          <div class="form-group">
            <label htmlFor="serve">Serves</label>
            <input
              v-model="recipe.serve"
              type="number"
              class="form-control"
              id="serve"
              name="serve"
            />
          </div>
          <div class="form-group">
            <label htmlFor="difficulty">Difficulty</label>
            <input
              v-model="recipe.difficulty"
              type="number"
              class="form-control"
              id="difficulty"
              name="difficulty"
            />
          </div>

          <!-- INGREGIENTES AQUI -->
          <label>Ingredients</label>
          <div
            class="form-group row"
            v-for="(input, index) in recipe.ingredients"
            :key="index"
          >
            <div class="col-lg-6">
              <select
                v-model="input.food"
                type="text"
                :name="'ingredient[' + index + '][food]'"
                class="form-control"
                placeholder="Ingredient Food"
              >
                <option disabled value="">Please select one Ingredient</option>
                <option v-for="food in foods" :key="food.id" :value="food">
                  {{ food.name }}
                </option>
              </select>
            </div>
            <div class="col-lg-2">
              <input
                v-model="input.quantity"
                type="number"
                :name="'ingredient[' + index + '][quantity]'"
                class="form-control"
                placeholder="Quantity"
              />
            </div>
            <div class="col-lg-1">
              <input
                v-model="input.required"
                type="checkbox"
                :name="'ingredient[' + index + '][requrired]'"
                class=""
                placeholder="Ingredient Required"
              />
              <label for="checkbox">{{ input.required }}</label>
            </div>
            <div class="col-lg-1">
              <button
                type="button"
                @click="deleteRow(index)"
                class="btn btn-outline-danger rounded-circle"
              >
                <i class="fa fa-times"></i>
              </button>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-lg-12">
              <button
                type="button"
                @click="addRow"
                class="btn btn-outline-secondary"
              >
                Add Ingredient
              </button>
            </div>
          </div>
          <!-- FIM DE INGREDIENTES -->

          <button
            @click="handleSave()"
            :disabled="isSaving"
            type="button"
            class="btn btn-outline-primary mt-3"
          >
            Save Recipe
          </button>
        </form>
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
  name: "RecipeCreate",
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
        ingredients: [
          {
            food: "",
            quantity: "",
            required: false,
          },
          {
            food: "",
            quantity: "",
            required: false,
          },
          {
            food: "",
            quantity: "",
            required: false,
          },
          {
            food: "",
            quantity: "",
            required: false,
          },
        ],
      },
      foods: [],
      isSaving: false,
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
    addRow() {
      this.recipe.ingredients.push({
        food: "",
        quantity: "",
        required: "",
      });
    },
    deleteRow(index) {
      this.recipe.ingredients.splice(index, 1);
    },
    handleSave() {
      this.isSaving = true;
      axios
        .post("/recipes", this.recipe)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Recipe saved successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          //this.isSaving = false
          //this.recipe.id = response.data.id;
          this.recipe.name = "";
          this.recipe.preparation = "";
          this.recipe.time = "";
          this.recipe.serve = "";
          this.recipe.difficulty = "";
          this.recipe.ingredients = [];
          return response;
        })
        .catch((error) => {
          this.isSaving = false;
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
