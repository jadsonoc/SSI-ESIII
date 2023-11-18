<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
      <img src="../assets/img/logo2_mini.png" alt="" width="90" height="30" />
    </a>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarNavAltMarkup"
      aria-controls="navbarNavAltMarkup"
      aria-expanded="false"
      aria-label="Alterna navegação"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div
      class="collapse navbar-collapse d-flex flex-row-reverse"
      id="navbarNavAltMarkup"
    >
      <div class="navbar-nav">
        <a class="nav-item nav-link active" href="/"
          >Home <span class="sr-only">(Página atual)</span></a
        >
        <a class="nav-item nav-link" href="/recipe/list">Receitas</a>
        <a class="nav-item nav-link" href="/food/list">Ingredientes</a>
        <a class="nav-item nav-link disabled" href="#">Log In</a>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavMenu",
};
</script>
