import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
import { createRouter, createWebHistory } from 'vue-router';
import HomePage from './components/HomePage';
import RecipeList from './views/RecipeList';
import RecipeCreate from './views/RecipeCreate';
import RecipeIngredients from './views/RecipeIngredients';
import ProjectEdit from './views/ProjectEdit';
import ProjectShow from './views/ProjectShow';

axios.defaults.baseURL = process.env.VUE_APP_API_URL

/* REVER ISSO 
axios.interceptors.request.use(function (config) {
  config.headers['X-Binarybox-Api-Key'] = process.env.VUE_APP_API_KEY;
  return config;
});
*/

const router = createRouter({
    history: createWebHistory(),
  routes: [
      { path: '/', component: HomePage },
      { path: '/recipe/list', component: RecipeList },
      { path: '/recipe/create', component: RecipeCreate },
      { path: '/recipe/ingredients/create/:id', component: RecipeIngredients },
      { path: '/edit/:id', component: ProjectEdit },
      { path: '/show/:id', component: ProjectShow },
    ],
  });

createApp(App).use(router).mount('#app');
