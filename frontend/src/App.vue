<template>
  <div class="wrapper">
    <img class="left-branch" src="@/assets/branch.png" alt="" />
    <img class="right-branch" src="@/assets/branch.png" alt="" />
    <div class="content-wrapper">
      <Navigation />
      <router-view />
    </div>
    <Footer />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation.vue";
import Footer from "@/components/Footer.vue";

export default {
  components: {
    Navigation,
    Footer,
  },
};
</script>

<style lang="scss">
body {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.wrapper {
  max-width: 80%;
  margin: 0 auto;
  .content-wrapper {
    min-height: 100vh;
  }
  .left-branch {
    position: fixed;
    width: 230px;
    rotate: 140deg;
    left: -100px;
    top: 0px;
    transform: scale(-1, 1);
  }
  .right-branch {
    position: fixed;
    rotate: -2deg;
    width: 230px;
    right: -100px;
    bottom: 50px;
  }
}

::-webkit-scrollbar {
  width: 8px; /* ширина scrollbar */
}

::-webkit-scrollbar-track {
  background-color: transparent; /* цвет фона трека */
}

::-webkit-scrollbar-thumb {
  background-color: #666; /* цвет ползунка */
  border-radius: 6px; /* скругление углов ползунка */
}

::-webkit-scrollbar-thumb:hover {
  background-color: #444; /* цвет ползунка при наведении */
}
</style>
