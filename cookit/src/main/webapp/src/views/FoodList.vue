<template>
  <NavMenu />
  <layout-div>
    <div class="container">
      <h2 class="text-center mt-5 mb-3">Food Manager</h2>
      <div class="card">
        <div class="card-header">
          <router-link v-if="isLogged" to="/food/create" class="btn btn-outline-primary"
            >Create New Food Ingredient
          </router-link>
        </div>
        <div class="card-body">
          <table class="table table-bordered">
            <thead>
              <tr class="text-center">
                <th>Name</th>
                <th>Unity</th>
                <th>Lactose Free</th>
                <th>Gluten Free</th>
                <th>Oilseed (Nut)</th>
                <th width="240px">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="food in foods" :key="food.id">
                <td>{{ food.name }}</td>
                <td>{{ food.foodUnit }}</td>
                <td v-if="food.lactoseFree" class="text-center">
                  <i class="fa fa-check-circle fa-2x" aria-hidden="true" />
                </td>
                <td v-else class="text-center">
                  <i class="fa fa-times fa-2x" aria-hidden="true" />
                </td>
                <td v-if="food.glutenFree" class="text-center">
                  <i class="fa fa-check-circle fa-2x" aria-hidden="true" />
                </td>
                <td v-else class="text-center">
                  <i class="fa fa-times fa-2x" aria-hidden="true" />
                </td>
                <td v-if="food.oilseedFree" class="text-center">
                  <i class="fa fa-times fa-2x" aria-hidden="true" />
                </td>
                <td v-else class="text-center">
                  <i class="fa fa-check-circle fa-2x" aria-hidden="true" />
                </td>
                <td>
                  <router-link v-if="isLogged"
                    :to="`/food/edit/${food.id}`"
                    class="btn btn-outline-success mx-1"
                    >Edit</router-link
                  >
                  <button v-if="isLogged"
                    @click="handleDelete(food.id)"
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
import VueCookies from 'vue-cookies';
import axios from "axios";
import NavMenu from "../components/NavMenu.vue";
import LayoutDiv from "../components/LayoutDiv.vue";
import Swal from "sweetalert2";

export default {
  name: "FoodList",
  components: {
    NavMenu,
    LayoutDiv,
  },
  data() {
    return {
      foods: [],
      idLogged: false,
    };
  },
  created() {
    this.fetchFoodsList();
    if (VueCookies.isKey('userId')) {
      this.isLogged = true;
    }
  },
  methods: {
    fetchFoodsList() {
      axios
        .get("/foods")
        .then((response) => {
          this.foods = response.data;
          this.foods.sort((a, b) => {
            return a.name < b.name ? -1 : a.name > b.name ? 1 : 0;
          });
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
            .delete(`/foods/${id}`)
            .then((response) => {
              Swal.fire({
                icon: "success",
                title: "Ingredient deleted successfully!",
                showConfirmButton: false,
                timer: 1500,
              });
              this.fetchFoodsList();
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
