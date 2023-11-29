<template>
  <NavMenu />
  <layout-div>
    <h2 class="text-center mt-5 mb-3">Edit Profile</h2>
    <div class="card">
      <div class="card-header">
        <router-link class="btn btn-outline-info float-right" to="/"
          >Go back to Home
        </router-link>
      </div>
      <div class="card-body">
        <form>
          <div class="form-group py-2">
            <label htmlFor="name">Full Name</label>
            <input
              v-model="user.name"
              type="text"
              class="form-control"
              id="name"
              name="name"
            />
          </div>
          <div class="form-group py-2">
            <label htmlFor="email">Email</label>
            <input
              v-model="user.email"
              type="text"
              class="form-control"
              id="email"
              name="email"
            />
          </div>
          <div class="form-group py-2">
            <label htmlFor="lactoseIntolerant"
              >Lactose Intolerant or Alergic?</label
            >
            <label class="wrapper flex items-center">
              <input
                v-model="user.lactoseIntolerant"
                class="form-control checkbox"
                type="checkbox"
                name="lactoseIntolerant"
              />
              <span class="checkmark"></span>
            </label>
          </div>
          <div class="form-group py-2">
            <label htmlFor="glutenIntolerant"
              >Gluten Intolerant or Alergic?</label
            >
            <label class="wrapper flex items-center">
              <input
                v-model="user.glutenIntolerant"
                class="form-control checkbox"
                type="checkbox"
                name="glutenIntolerant"
              />
              <span class="checkmark"></span>
            </label>
          </div>
          <div class="form-group py-2">
            <label htmlFor="oilseedsIntolerant">Oilseed Alergic?</label>
            <label class="wrapper flex items-center">
              <input
                v-model="user.oilseedsIntolerant"
                class="form-control checkbox"
                type="checkbox"
                name="oilseedsIntolerant"
              />
              <span class="checkmark"></span>
            </label>
          </div>

          <label class="py-2">Select your dislikes:</label>
          <div class="input-group">
            <vue3-tags-input
              v-model:tags="user.dislikesIngredients"
              v-model="tag"
              :select="true"
              :select-items="foods"
              class="form-control"
              @on-select="handleSelectedTag"
              @on-tags-changed="handleChangeTag"
              placeholder="Select the Ingredients you don't like"
            >
              <template #item="{ tag }">
                {{ tag.name }}
              </template>
              <template #no-data> No Data </template>
              <template #select-item="tag">
                {{ tag.name }}
              </template>
            </vue3-tags-input>
          </div>

          <button
            @click="handleSave()"
            :disabled="isSaving"
            type="button"
            class="btn btn-outline-primary mt-3"
          >
            Save Profile
          </button>
        </form>
      </div>
    </div>
  </layout-div>

  <layout-div>

      
      <div class="card mt-3">
        <div class="card-header">
          <h4 class="text-center mt-2 mb-2">My Favourite Recipes</h4>
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
              <tr v-for="recipe in user.favouritedRecipes" :key="recipe.id">
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
  </layout-div>
</template>

<script>
import axios from "axios";
import NavMenu from "../components/NavMenu.vue";
import LayoutDiv from "../components/LayoutDiv.vue";
import Swal from "sweetalert2";
import Vue3TagsInput from "vue3-tags-input";

export default {
  name: "UserEdit",
  components: {
    NavMenu,
    LayoutDiv,
    Vue3TagsInput,
  },
  data() {
    return {
      userId: "",
      user: {
        name: "",
        email: "",
        lactoseIntolerant: false,
        glutenIntolerant: false,
        oilseedsIntolerant: false,
        favouritedRecipes: [],
        dislikesIngredients: [],
      },
      tag: "",
      tags: [],
      selectedItems: [],
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

    this.userId = this.$route.params.id;
    axios
      .get(`/userManager/${this.userId}`)
      .then((response) => {
        let userInfo = response.data;
        this.user.name = userInfo.name;
        this.user.email = userInfo.email;
        this.user.lactoseIntolerant = userInfo.lactoseIntolerant;
        this.user.glutenIntolerant = userInfo.glutenIntolerant;
        this.user.oilseedsIntolerant = userInfo.oilseedsIntolerant;
        this.user.favouritedRecipes = userInfo.favouritedRecipes;
        this.user.dislikesIngredients = userInfo.dislikesIngredients;
        this.tags = this.user.dislikesIngredients;
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
      axios
        .put(`/userManager/${this.userId}`, this.user)
        .then((response) => {
          Swal.fire({
            icon: "success",
            title: "Profile updated successfully!",
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
    handleSelectedTag(tag) {
      axios
        .put(`/userManager/dislikes/add/${this.userId}/${tag.id}`)
        .then((response) => {
          /*
          Swal.fire({
            icon: "success",
            title: "Dislikes updated successfully!",
            showConfirmButton: false,
            timer: 1500,
          });
          */
          this.isSaving = false;
          this.user.dislikesIngredients.push(tag);
          this.tags.push(tag);
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
    handleChangeTag(tags) {
      let uniqueTags = tags.filter((elem, pos, self) => {
            return self.indexOf(elem) == pos;
      });
      let tagDiff = this.user.dislikesIngredients.filter((element) => !tags.includes(element)).shift();
      axios
        .put(`/userManager/dislikes/remove/${this.userId}/${tagDiff.id}`)
        .then((response) => {
          
          this.user.dislikesIngredients = uniqueTags;
          
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
