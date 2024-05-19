<template>
  <div class="footer">
    <router-link class="home" to="/"
      ><img src="@/assets/flower.svg" class="purple" alt="" width="60px" />
      <h1>Flower Store</h1></router-link
    >
    <div class="footer-content">
      <div class="info">
        <div class="other">
          <h3>Букеты на заказ</h3>
          <h3>Правовая информация</h3>
        </div>
        <div>
          <h3>Мы в соцсетях</h3>
          <div class="socials">
            <p>YouTube</p>
            <p>Telegram</p>
            <p>ВКонтакте</p>
            <p>WhatsApp</p>
          </div>
        </div>
        <div class="copyright">
          <p>Powered by Vue 3 & SpringBoot</p>
          <p>AMTeam Все праав защищены © 2024</p>
        </div>
      </div>
      <div class="map">
        <h3>Мы на карте</h3>
        <iframe
          src="https://yandex.ru/map-widget/v1/?um=constructor%3Ad85786a514a1588b53c1815fac8873223382e0bb8ad87b3639140658b0a7f13d&amp;source=constructor"
          width="100%"
          height="200"
          frameborder="0"
        ></iframe>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.footer {
  display: flex;
  flex-direction: column;
  font-family: Montserrat;
  padding-bottom: 70px;
  .footer-content {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .purple {
    filter: invert(51%) sepia(98%) saturate(965%) hue-rotate(263deg)
      brightness(95%) contrast(88%);
  }
  .home {
    display: flex;
    flex-direction: row;
    align-items: center;
    text-decoration: none;
    padding-bottom: 10px;
    h1 {
      font-size: 24px;
      margin-left: 10px;
      color: #231f20;
    }
  }
  .socials {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    p {
      font-size: 16px;
      font-weight: 600;
      color: #e264e4;
    }
  }
  .other {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .info {
    width: 35%;
    h3 {
      color: #0e0c0d;
      font-size: 16px;
    }
    .copyright {
      color: #545454;
      font-size: 14px;
      font-weight: 600;
      margin-top: 30px;
    }
  }
  .map {
    width: 50%;
    h3 {
      color: #0e0c0d;
      font-size: 16px;
    }
  }
}
</style>
