<template>
  <NavMenu />
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Edit Food</h2>
    <div class="card">
      <div class="card-header">
        <router-link class="btn btn-outline-info float-right" to="/food/list"
          >View All Ingredients
        </router-link>
      </div>
      <div class="card-body">
        <form>
          <div class="form-group py-2">
            <label htmlFor="name">Name</label>
            <input
              v-model="food.name"
              type="text"
              class="form-control"
              id="name"
              name="name"
            />
          </div>
          <div class="form-group py-2">
            <label htmlFor="foodUnit">Unit</label>
            <select
              v-model="food.foodUnit"
              type="text"
              :name="foodUnit"
              class="form-control"
            >
              <option disabled value="">
                Please select one Ingredient Unit
              </option>
              <option v-for="(unit, index) in unities" :key="index">
                {{ unit }}
              </option>
            </select>
          </div>
          <div class="form-group py-2">
            <label htmlFor="lactoseFree">Lactose Free?</label>
            <label class="wrapper flex items-center">
              <input
                v-model="food.lactoseFree"
                class="form-control checkbox"
                type="checkbox"
                name="oilseedFree"
                :value="lactoses"
              />
              <span class="checkmark"></span>
            </label>
          </div>
          <div class="form-group py-2">
            <label htmlFor="glutenFree">Gluten Free?&nbsp;&nbsp;&nbsp;</label>
            <label class="wrapper flex items-center">
              <input
                v-model="food.glutenFree"
                class="form-control checkbox"
                type="checkbox"
                name="glutenFree"
                :value="gluten"
              />
              <span class="checkmark"></span>
            </label>
          </div>
          <div class="form-group py-2">
            <label htmlFor="oilseedFree">Oilseed Free?&nbsp;</label>
            <label class="wrapper flex items-center">
              <input
                v-model="food.oilseedFree"
                class="form-control checkbox"
                type="checkbox"
                name="oilseedFree"
                :value="oilseed"
              />
              <span class="checkmark"></span>
            </label>
          </div>

          <button
            @click="handleSave()"
            :disabled="isSaving"
            type="button"
            class="btn btn-outline-primary mt-3"
          >
            Save Ingredient
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
  name: "FoodEdit",
  components: {
    NavMenu,
    LayoutDiv,
  },
  data() {
    return {
      food: {
        name: "",
        lactoseFree: false,
        glutenFree: false,
        oilseedFree: false,
        foodUnit: "",
      },
      unities: ["g", "Kg", "ml", "L", "un", "tbs", "fatia", "q.b."],
      isSaving: false,
    };
  },
  created() {
    const id = this.$route.params.id;
    axios
      .get(`/foods/${id}`)
      .then((response) => {
        let foodsInfo = response.data;
        this.food.name = foodsInfo.name;
        this.food.lactoseFree = foodsInfo.lactoseFree;
        this.food.glutenFree = foodsInfo.glutenFree;
        this.food.oilseedFree = foodsInfo.oilseedFree;
        this.food.foodUnit = foodsInfo.foodUnit;
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
  methods: {
    handleSave() {
      this.isSaving = true;
      const id = this.$route.params.id;
      axios
        .put(`/foods/${id}`, this.food)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Ingredient updated successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          this.isSaving = false;
          return response;
        })
        .catch((error) => {
          this.isSaving = false;
          Swal.fire({
            icon: "error",
            title: error + "An Error Occured!",
            showConfirmButton: false,
            timer: 1500,
          });
          return error;
        });
    },
  },
};
</script>

<style lang="postcss" scoped>
/* Customize the label (the wrapper) */
.wrapper {
  display: inline-block;
  position: relative;
  margin-left: 30px;
  margin-bottom: 14px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  font-size: 16px;
}
/* Hide the browser's default checkbox */
.wrapper input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}
/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 21px;
  width: 21px;
  border-radius: 2px;
  background-color: #eee;
  border: 1px solid #ccc;
}
/* On mouse-over, add a grey background color */
.wrapper:hover input ~ .checkmark {
  background-color: #ccc;
}
/* When the checkbox is checked, add a blue background */
.wrapper input:checked ~ .checkmark {
  background-color: #1cd4a7;
}
/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}
/* Show the checkmark when checked */
.wrapper input:checked ~ .checkmark:after {
  display: block;
}
/* Style the checkmark/indicator */
.wrapper .checkmark:after {
  left: 7px;
  top: 0px;
  width: 7px;
  height: 15px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
</style>