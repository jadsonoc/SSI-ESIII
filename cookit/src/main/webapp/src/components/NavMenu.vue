<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">
      <img src="../assets/img/logo2_mini_bg.png" alt="" width="90" height="30" />
    </a>
    <div
      class="collapse navbar-collapse d-flex flex-row-reverse"
      id="navbarNavAltMarkup"
    >
      <div class="navbar-nav">
        <a
          class="nav-item nav-link"
          :class="[this.$route.path == '/' ? 'active' : '']"
          href="/"
          >Home</a
        >
        <a
          class="nav-item nav-link"
          :class="[this.$route.path.includes('recipe') ? 'active' : '']"
          href="/recipe/list"
          >All Recipes</a
        >
        <a
          class="nav-item nav-link"
          :class="[this.$route.path.includes('food') ? 'active' : '']"
          href="/food/list"
          >All Ingredients</a
        >
        <div v-if="!logged">
          <GoogleLogin :callback="callback" auto-login /><!-- removi prompt -->
        </div>

        <router-link
          v-if="logged"
          :to="`/user/edit/${loggedUser.id}`"
          class="nav-item nav-link"
          :class="[this.$route.path.includes('user') ? 'active' : '']"
          >Profile</router-link
        >

        <a v-if="logged" class="nav-item nav-link" @click="handleLogout()">
          Log Out
        </a>
      </div>
    </div>
  </nav>
</template>

<script>
import VueCookies from "vue-cookies";
import { decodeCredential } from "vue3-google-login";
import axios from "axios";
//import Swal from "sweetalert2";

export default {
  name: "NavMenu",
  data() {
    return {
      user: {
        name: "",
        email: "",
      },
      loggedUser: {
        id: "",
        name: "",
        email: "",
        lactoseIntolerant: false,
        glutenIntolerant: false,
        oilseedsIntolerant: false,
        favouritedRecipes: [],
        preparedRecipes: [],
      },
      userGoogleLoginData: [],
      logged: false,
    };
  },
  mounted() {
    if (VueCookies.isKey("userId")) {
      this.logged = true;
      this.loggedUser.id = VueCookies.get("userId");
    }
  },
  methods: {
    callback(response) {
      this.userGoogleLoginData = decodeCredential(response.credential);
      this.user.email = this.userGoogleLoginData.email;
      this.user.name = this.userGoogleLoginData.name;
      this.userLogin(this.user);
    },
    handleLogout() {
      this.logged = false;
      if (VueCookies.isKey("userId")) VueCookies.remove("userId");
    },
    userLogin(user) {
      axios
        .post("/userManager/login", user)
        .then((response) => {
          this.loggedUser = response.data;
          VueCookies.set("userId", response.data.id, "15m");
          if (VueCookies.isKey("userId")) {
            this.logged = true;
            VueCookies.set("userLactoseIntolerant", response.data.lactoseIntolerant, "15m");
            VueCookies.set("userGlutenIntolerant", response.data.glutenIntolerant, "15m");
            VueCookies.set("userOilseedsIntolerant", response.data.oilseedsIntolerant, "15m");
          }
          return response;
        })
        .catch((error) => {
          return error;
        });
    },
  },
};
</script>
