import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library, dom } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';
import { createRouter, createWebHistory } from 'vue-router';
import HomePage from './components/HomePage';
import RecipeSearchList from './components/RecipeSearchList';
import RecipeList from './views/RecipeList';
import RecipeCreate from './views/RecipeCreate';
import RecipeIngredients from './views/RecipeIngredients';
import RecipeEdit from './views/RecipeEdit';
import RecipeShow from './views/RecipeShow';
dom.watch();
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
      { path: '/recipe/search/list/:query', component: RecipeSearchList },
      { path: '/recipe/list', component: RecipeList },
      { path: '/recipe/create', component: RecipeCreate },
      { path: '/recipe/ingredients/create/:id', component: RecipeIngredients },
      { path: '/recipe/edit/:id', component: RecipeEdit },
      { path: '/recipe/show/:id', component: RecipeShow },
    ],
});
  
library.add(fas, far, fab);

createApp(App)
  .component('fa', FontAwesomeIcon)
  .use(router)
  .mount('#app');
